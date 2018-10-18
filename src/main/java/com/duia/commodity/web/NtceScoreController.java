package com.duia.commodity.web;

import com.duia.commodity.common.util.ImageUtils;
import com.duia.commodity.service.NtceScoreService;
import com.duia.resultG.Result;
import com.duia.resultG.ResultCode;
import com.duia.resultG.ResultGenerator;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 郑辉 on 2018/2/8.
 * NTCE - 中国教育考试网
 * 中小学教师资格考试(笔试)成绩查询
 */
@Controller
@RequestMapping("/wap/ntce/score")
public class NtceScoreController {
    protected static Log logger = LogFactory.getLog(NtceScoreController.class);

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private NtceScoreService ntceScoreService;

    @Value("${file.upload.server.path}")
    private String realPath;
    @Value("${file.upload.server.ntce.share.image.path}")
    private String ntceSharePath;

    protected final static String ERROR_CAPTCHA = "验证码输入有误";
    protected final static String ERROR_NOT_FOUND = "未找到姓名";
    protected final static String ERROR_RESET = "重　置";

    /**
     * 获取验证码图片
     *
     * @param sid 对方服务器jsessionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void getPicYZM(@RequestParam String sid, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            byte[] buff = this.getYZM(sid).getBody();
            response.setHeader("content-type", "text/html;charset=UTF-8");
            out = response.getOutputStream();
            IOUtils.write(buff, out);
            out.flush();
        } catch (Exception ex) {
            logger.error("NTCE验证码获取失败" + ex);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                logger.error("IO关闭异常不处理" + e);
            }
        }
    }

    /**
     * 查询成绩初始化
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(Model model) {
        return "resultSerch";
    }

    /**
     * 请求失败重置
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public Result reset() {
        HashMap result = Maps.newHashMap();
        try {
            String sid = "";
            ResponseEntity<byte[]> response = this.getYZM(null);
            List<String> cooks = response.getHeaders().get("Set-Cookie");
            if (cooks != null && !cooks.isEmpty()) {
                for (int i = 0; i < cooks.size(); i++) {
                    List<HttpCookie> cookies = HttpCookie.parse(cooks.get(i));
                    for (int j = 0; j < cookies.size(); j++) {
                        if (cookies.get(j).getName().equals("JSESSIONID")) {
                            sid = cookies.get(j).getValue();
                        }
                    }
                }
            }
            result.put("sid", sid);
        } catch (Exception ex) {
            result.put("sid", "");
            logger.error("NTCE维护中" + ex);
        }
        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * 笔试查询成绩
     *
     * @param name 姓名
     * @param zjhm 证件号码
     * @param yzm  验证码
     * @param sid  jsessionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Result queryScore(@RequestParam String name, @RequestParam String zjhm, @RequestParam String yzm, @RequestParam String sid) {
        //返回用户信息
        try {
            String serverPath = ntceScoreService.getServerPath();
            HashMap resultMap = Maps.newHashMap();
            resultMap.put("name", name);
            resultMap.put("examinee", zjhm);
            //拿到header信息
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Cookie", "JSESSIONID=" + sid);
            MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
            param.add("name", name);
            param.add("zjhm", zjhm);
            param.add("yzm", yzm);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(param, requestHeaders);
            String html = restTemplate.postForObject(serverPath + "/selectScore.do?method=getMyScore", requestEntity, String.class);
            Document doc = Jsoup.parse(html);
            String msg = doc.select("div").text();
            if (msg.contains(ERROR_CAPTCHA)) {
                return ResultGenerator.genCustomResult(ResultCode.UNAUTHORIZED.code, ERROR_CAPTCHA, resultMap);
            } else if (msg.contains(ERROR_NOT_FOUND)) {
                return ResultGenerator.genCustomResult(ResultCode.NOT_FOUND.code, ERROR_NOT_FOUND, resultMap);
            } else if (msg.contains(ERROR_RESET)) {
                // 登录失败需刷新页面 标识码206
                return ResultGenerator.genCustomResult(206, ERROR_RESET, resultMap);
            }
            resultMap = ntceScoreService.query(doc, resultMap);
            return ResultGenerator.genSuccessResult(resultMap);
        } catch (Exception e) {
            return ResultGenerator.genFailResult("查询成绩失败");
        }
    }

    /**
     * 分享图片生成
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/share")
    public Result share(@RequestParam String name, @RequestParam String batch, @RequestParam String examType) {
        String[] params = new String[5];
        params[0] = name + "，";
        params[1] = batch;
        params[2] = examType;
        params[3] = realPath;
        params[4] = ntceSharePath;
        String path = ImageUtils.exportImg(params);
        if (StringUtil.isNotEmpty(path)) {
            return ResultGenerator.genSuccessResult(path);
        } else {
            return ResultGenerator.genFailResult("分享图片生成失败");
        }
    }

    /**
     * 获取验证码私有方法
     *
     * @param sid
     * @return
     */
    private ResponseEntity<byte[]> getYZM(String sid) throws Exception {
        String serverPath = ntceScoreService.getServerPath();
        HttpHeaders headers = new HttpHeaders();
        if (sid != null) {
            headers.add("Cookie", "JSESSIONID=" + sid);
        }
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(serverPath + "/getYZM?s=" + System.currentTimeMillis(), HttpMethod.GET, entity, byte[].class);
    }

    /**
     * 获取NTCE网 JSESSIONID
     *
     * @return
     */
    public String getJSESSIONID() throws Exception {
        String JSESSIONID = null;
        ResponseEntity<byte[]> response = this.getYZM(null);
        List<String> cooks = response.getHeaders().get("Set-Cookie");
        if (cooks != null && !cooks.isEmpty()) {
            for (int i = 0; i < cooks.size(); i++) {
                List<HttpCookie> cookies = HttpCookie.parse(cooks.get(i));
                for (int j = 0; j < cookies.size(); j++) {
                    if (cookies.get(j).getName().equals("JSESSIONID")) {
                        JSESSIONID = cookies.get(j).getValue();
                        break;
                    }
                }
            }
        }
        return JSESSIONID;
    }
}
