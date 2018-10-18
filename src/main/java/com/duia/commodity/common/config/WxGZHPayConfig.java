package com.duia.commodity.common.config;

import com.wxpay.lib.JProperties;

/**
 * Created by admin on 2017/8/4.
 */
public class WxGZHPayConfig {
    // AppID
    public static String APPID = JProperties.getProperties(JProperties.loadProperties("wxpay"), "app_id");
    // App密钥
    public static String APPSECRET = JProperties.getProperties(JProperties.loadProperties("wxpay"), "app_secret");

}
