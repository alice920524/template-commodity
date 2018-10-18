package com.duia.commodity.service;

import com.wxpay.lib.WxPayException;

import java.util.Map;

/**
 * Created by Hero on 2018/5/15.
 */
public interface WxService {
    /**
    * @Author: Hero
    * @Description: 获取openid
    * @Date: Created in 20:19 2018/5/15
    * @param  * @param null
    */
    String getOpenid(String code) throws WxPayException;

    /**
     * Description：获取openid,access_token
     * @param code
     * @return
     * @throws WxPayException
     */
    Map getOpenInfo(String code) throws WxPayException;

    /**
     * @Description: 获取公众号授权用户信息
     * @param openid
     * @param accessToken
     * @return
     * @throws WxPayException
     */
    Map getAuthorizedUser(String openid, String accessToken) throws WxPayException;

}
