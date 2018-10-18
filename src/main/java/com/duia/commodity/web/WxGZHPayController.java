package com.duia.commodity.web;

import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderService;
import com.duia.commodity.service.WxService;
import com.duia.constant.Constants;
import com.wxpay.lib.WxPayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * 微信公众号支付
 */
@Controller
@RequestMapping("/wx/gzh")
public class WxGZHPayController {

    // 日志
    private Logger logger = LoggerFactory.getLogger(WxGZHPayController.class);

    @Resource
    private PayOrderService orderService;
    @Resource
    private WxService wxService;

    @Value("${item.domain}")
    private String itemDomain;


    /**
     * 微信回调授权接口
     *
     * @param code
     * @param state
     * @param response
     * @throws IOException
     * @throws WxPayException
     */
    @RequestMapping(value = "/authorityNotify")
    public void authorityNotify(String code, String state, String data, HttpServletResponse response) throws IOException, WxPayException {
        //获取openid
        String openid = wxService.getOpenid(code);
        // 更新订单表中的openId
        PayOrder payOrder = orderService.findBy("payNum", data);
        payOrder.setOpenId(openid);
        orderService.update(payOrder);
        // 重定向
        response.sendRedirect(Constants.HTTPS + itemDomain + state + (state.indexOf("?") == -1 ? "?" : "&") + "num=" + data);
    }

    /**
     * @param * @param null
     * @Author: Hero
     * @Description: 路由重定向
     * @Date: Created in 13:56 2018/5/14
     */
    @RequestMapping(value = "/getOpenid")
    public void getOpenid(String code, String state, HttpServletResponse response) throws IOException, WxPayException {
        //获取openid
        String openid = wxService.getOpenid(code);
        logger.info("user's openid:" + openid);
        // 重定向
        response.sendRedirect(Constants.HTTPS + itemDomain + state + (state.indexOf("?") == -1 ? "?" : "&") + "openid=" + openid);
    }

    /**
     * 英语四级路由重定向
     *
     * @param code
     * @param state
     * @param response
     * @throws IOException
     * @throws WxPayException
     */
    @RequestMapping(value = "/getCetGzhInfo")
    public void getCetGzhInfo(String code, String state, HttpServletResponse response) throws IOException, WxPayException {
        Map dataMap = wxService.getOpenInfo(code);
        //获取openid
        String openid = dataMap.get("openid") + "";
        String access_token = dataMap.get("access_token") + "";
        logger.info("user's openid:" + openid + "，access_token：" + access_token);
        // 重定向
        String url = state + (state.indexOf("?") == -1 ? "?" : "&") + "openid=" + openid + "&accessToken=" + access_token;
        response.sendRedirect(url);
    }

}
