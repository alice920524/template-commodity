package com.duia.commodity.service;

import com.duia.commodity.common.dto.CommodityPromotionDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.CommodityPromotion;
import com.duia.resultG.Result;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/03/16.
 */
public interface CommodityPromotionService extends Service<CommodityPromotion> {

    /**
     * 查询有有效的分享购信息
     *
     * @param comId
     * @return
     */
    CommodityPromotion findOnLinePromotion(Long comId, Integer appType);

    /**
     * 是否分享商品
     *
     * @param comId
     * @param userId
     * @param appType
     * @return
     */
    CommodityPromotion isShareCommodity(Long comId, Long userId, Integer appType);

    /**
     * 是否分享
     * */
    boolean isShareCommodity(Long comId, Long userId);

    /**
     * 是否开启活动
     * */
    boolean isOpenActivity(Integer guaType, CommodityPromotion commodityPromotion);

    /**
     * 商品详情页活动数据
     *
     * @Date: 17:22 2018/6/6
     */
    CommodityPromotionDTO selectCommodityRushDetail(Long comId, Integer appType);

    /**
     * 直播课商品活动信息
     *
     * @Date: 17:41 2018/6/7
     */
    CommodityPromotion selectLiveCommodityPromotion(Long comId, Integer activityType, Integer appType);

    /**
     * 获取下架的直播课商品活动信息
     *
     * @Date: 17:27 2018/6/26
     */
    CommodityPromotionDTO selectInvalidLiveCommodityPromotion(Long comId, Integer activityType, Integer appType);

    /**
     * 抢购商品增加销量
     */
    void rushCommodityAddSales(Long orderId, Long promotionId);

    /**
     * 减少销量
     * */
    Result subSales(List<Long> orderIds);

}
