package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.config.WxGZHPayConfig;
import com.duia.commodity.service.WxService;
import com.wxpay.lib.HttpService;
import com.wxpay.lib.WxPayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hero on 2018/5/15.
 */
@Service
public class WxServiceImpl implements WxService {
    private static final Logger logger = LoggerFactory.getLogger(WxServiceImpl.class);
    // 获取用户openId调取接口地址
    private static final String USER_ACCESS_TOKEN_API = " https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // 获取公众号用户信息
    public static String SNS_USER_INFO_API = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    @Override
    public String getOpenid(String code) throws WxPayException { // 配置访问地址
        String url = USER_ACCESS_TOKEN_API.replace("APPID", WxGZHPayConfig.APPID)
                .replace("SECRET", WxGZHPayConfig.APPSECRET)
                .replace("CODE", code);
        // 发送get请求
        String result = HttpService.Get(url);
        Map token = JSON.parseObject(result, HashMap.class);
        //获取openid
        String openid = (String) token.get("openid");
        return openid;
    }

    @Override
    public Map getOpenInfo(String code) throws WxPayException { // 配置访问地址
        String url = USER_ACCESS_TOKEN_API.replace("APPID", WxGZHPayConfig.APPID)
                .replace("SECRET", WxGZHPayConfig.APPSECRET)
                .replace("CODE", code);
        // 发送get请求
        String result = HttpService.Get(url);
        Map token = JSON.parseObject(result, HashMap.class);
        return token;
    }

    @Override
    public Map getAuthorizedUser(String openid, String accessToken) throws WxPayException {
        logger.info("参数：[openid:" + openid + ",accessToken:" + accessToken + "]");
        String url = SNS_USER_INFO_API.replace("OPENID", openid)
                .replace("ACCESS_TOKEN", accessToken);
        // 发送get请求
        String result = HttpService.Get(url);
        Map token = JSON.parseObject(result, HashMap.class);
        return token;
    }
}
