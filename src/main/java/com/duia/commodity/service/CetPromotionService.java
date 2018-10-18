package com.duia.commodity.service;

import com.duia.commodity.core.Service;
import com.duia.commodity.model.CetPromotion;
import com.wxpay.lib.WxPayException;

import java.io.IOException;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/05/30.
 */
public interface CetPromotionService extends Service<CetPromotion> {

    /**
     * 查询四级自测成绩
     *
     * @param openid  公众号openid或者app唯一标识码
     * @param appType
     * @return
     */
    CetPromotion query(String openid, Integer appType);

    /**
     * 四级自测统计计数
     *
     * @param countType
     * @param openid    公众号openid或者app唯一标识码
     * @param appType
     */
    void count(Integer countType, String openid, Integer appType);

    /**
     * 保存四级自测考试信息
     *
     * @param cetPromotion
     * @param accessToken  四级公众号网页授权接口调用凭证
     * @throws IOException
     * @throws WxPayException
     */
    void saveCetPromotion(CetPromotion cetPromotion, String accessToken) throws IOException, WxPayException;

    /**
     * 更新分数等信息
     * @param cetPromotion
     */
    void updateScore(CetPromotion cetPromotion);

    /**
     * 根据端类型获取用户信息
     * @param accessToken
     * @param openid
     * @param appType
     * @return
     * @throws WxPayException
     */
    Map getAuthorizedUser(String accessToken, String openid, Integer appType) throws WxPayException;

}
