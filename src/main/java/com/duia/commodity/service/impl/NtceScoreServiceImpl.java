package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.NtceCertifyDTO;
import com.duia.commodity.common.dto.NtceInterviewDTO;
import com.duia.commodity.common.dto.NtceWrittenDTO;
import com.duia.commodity.service.NtceScoreService;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhenghui on 2018/3/30.
 */
@Service
public class NtceScoreServiceImpl implements NtceScoreService {
    protected static Log logger = LogFactory.getLog(NtceScoreServiceImpl.class);
    protected final static String PASSED = "合格";

    /**
     * 考试成绩查询
     *
     * @param doc
     * @param resultMap
     * @return
     */
    @Override
    public HashMap query(Document doc, HashMap resultMap) {
        //面试数据
        List interviews = this.getInterviews(doc);
        //笔试数据
        List writtens = this.getWrittens(doc);
        //合格证明
        List certifys = this.getCertifys(doc);
        //是否有面试
        boolean exist = !CollectionUtils.isEmpty(interviews);
        //面试是否合格
        boolean ipass = exist ? this.checkPassed(this.getCurrentBatch(interviews), interviews) : false;
        //笔试是否合格
        boolean wpass = exist ? true : this.checkPassed(this.getCurrentBatch(writtens), writtens);
        resultMap.put("ipass", ipass);
        resultMap.put("wpass", wpass);
        resultMap.put("exist", exist);
        resultMap.put("interviews", interviews);
        resultMap.put("writtens", writtens);
        resultMap.put("certifys", certifys);
        return resultMap;
    }

    /**
     * 获取访问站点
     *
     * @return
     */
    @Override
    public String getServerPath() {
        String serverPath = "http://chafen.ntce.cn";
        String[] serverPaths = new String[]{
                "http://chafen.ntce.cn",
                "http://ntcechafen1.neea.edu.cn",
                "http://ntcechafen2.neea.edu.cn",
                "http://ntcechafen3.neea.edu.cn"};
        if (serverPaths != null && serverPaths.length > 0) {
            try {
                for (String path : serverPaths) {
                    URL urlObj = new URL(path);
                    HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
                    oc.setUseCaches(false);
                    oc.setConnectTimeout(3000); // 设置超时时间
                    // 请求状态
                    if (200 == oc.getResponseCode()) {
                        // 200是请求地址顺利连通。。
                        serverPath = path;
                        break;
                    }
                }
            } catch (MalformedURLException ex) {
                logger.error("NTCE维护中" + ex);
            } catch (IOException ex) {
                logger.error("IOException：" + ex);
            }
        }
        return serverPath;
    }

    /**
     * 获取面试成绩
     *
     * @param doc
     * @return
     */
    private List getInterviews(Document doc) {
        List interviewList = Lists.newArrayList();
        Elements elements = doc.select("span:contains(面试成绩)");
        if (!elements.isEmpty()) {
            Element interview = elements.get(0).nextElementSibling();
            //获取列表数据
            Elements select = interview.select("tbody").get(0).select("tr");
            //去除第1行
            select.remove(0);
            NtceInterviewDTO interviewDto;
            for (Element tr : select) {
                Elements tds = tr.getElementsByTag("td");
                interviewDto = new NtceInterviewDTO();
                interviewDto.setSubject(tds.eq(0).text());
                interviewDto.setPassed(tds.eq(1).text());
                interviewDto.setExaminee(tds.eq(2).text());
                interviewDto.setBatch(tds.eq(3).text());
                interviewDto.setProvince(tds.eq(4).text());
                interviewList.add(interviewDto);
            }
        }
        return interviewList;
    }

    /**
     * 获取笔试成绩
     *
     * @param doc
     * @return
     */
    private List getWrittens(Document doc) {
        List writtenList = Lists.newArrayList();
        Elements elements = doc.select("span:contains(笔试成绩)");
        if (!elements.isEmpty()) {
            Element written = elements.get(0).nextElementSibling();
            Elements select = written.select("tbody").get(1).select("tr");
            NtceWrittenDTO writtenDto;
            for (Element tr : select) {
                Elements tds = tr.getElementsByTag("td");
                writtenDto = new NtceWrittenDTO();
                writtenDto.setSubject(tds.eq(0).text());
                writtenDto.setScore(tds.eq(1).text());
                writtenDto.setPassed(tds.eq(2).text());
                writtenDto.setExaminee(tds.eq(3).text());
                writtenDto.setBatch(tds.eq(4).text());
                writtenDto.setDeadline(tds.eq(5).text());
                writtenDto.setProvince(tds.eq(6).text());
                writtenList.add(writtenDto);
            }
        }
        return writtenList;
    }

    /**
     * 获取合格证明信息
     *
     * @param doc
     * @return
     */
    private List getCertifys(Document doc) {
        List certifyList = Lists.newArrayList();
        Elements elements = doc.select("span:contains(考试合格证明)");
        if (!elements.isEmpty()) {
            Element certify = elements.get(0).nextElementSibling();
            //获取列表数据
            Elements select = certify.select("table tbody").get(1).select("tr");
            NtceCertifyDTO certifyDto;
            for (Element tr : select) {
                Elements tds = tr.getElementsByTag("td");
                certifyDto = new NtceCertifyDTO();
                certifyDto.setCategory(tds.eq(0).text());
                certifyDto.setCertifyNo(tds.eq(1).text());
                certifyDto.setDeadline(tds.eq(2).text());
                certifyList.add(certifyDto);
            }
        }
        return certifyList;
    }

    /**
     * 验证成绩是否合格
     *
     * @param curbatch
     * @param ntceList
     * @return
     */
    private Boolean checkPassed(String curbatch, List<Object> ntceList) {
        Boolean passed = true;
        String tempPass = null, tempBatch = null;
        for (Object obj : ntceList) {
            if (obj instanceof NtceInterviewDTO) {
                tempPass = ((NtceInterviewDTO) obj).getPassed();
                tempBatch = ((NtceInterviewDTO) obj).getBatch();
            } else if (obj instanceof NtceWrittenDTO) {
                tempPass = ((NtceWrittenDTO) obj).getPassed();
                tempBatch = ((NtceWrittenDTO) obj).getBatch();
            }
            //最新批次 存在不合格时返回false 跳出循环
            if (tempBatch.equals(curbatch) && !tempPass.equals(PASSED)) {
                passed = false;
                break;
            } else if (tempBatch.equals(curbatch) && tempPass.equals(PASSED)) {
                passed = true;
            } else {
                passed = false;
            }
        }
        return passed;
    }

    /**
     * 获取当前批次
     *
     * @return
     */
    private String getCurrentBatch(List<Object> ntceList) {
        String current = "";
        try {
            if (!CollectionUtils.isEmpty(ntceList)) {
                Date time = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                for (Object ntceDto : ntceList) {
                    String batch = null;
                    if (ntceDto instanceof NtceInterviewDTO) {
                        batch = ((NtceInterviewDTO) ntceDto).getBatch();
                    } else if (ntceDto instanceof NtceWrittenDTO) {
                        batch = ((NtceWrittenDTO) ntceDto).getBatch();
                    }
                    String t = batch.replace("年上半年", "06").replace("年下半年", "12");
                    Date ctime = sdf.parse(t);
                    if (time == null || time.getTime() < ctime.getTime()) {
                        time = ctime;
                        current = batch;
                    }
                }
            }
        } catch (ParseException e) {
            logger.info("日期转换异常");
        }
        return current;
    }

}
