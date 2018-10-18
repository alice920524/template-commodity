package com.duia.commodity.dao;

import com.duia.commodity.common.dto.CommodityPromotionDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.CommodityPromotion;
import org.apache.ibatis.annotations.Param;

public interface CommodityPromotionMapper extends Mapper<CommodityPromotion> {

    CommodityPromotion selectCommodityPromotionOnLine(@Param("comId") Long comId, @Param("appType") Integer appType);
    /**
     * 下架的最新一条活动信息
     * @Date: 18:02 2018/6/26
     */
    CommodityPromotionDTO selectInvalidLiveCommodityPromotion(@Param("comId") Long comId, @Param("activityType") Integer activityType);
    /**
     * 增加活动的销量值
     * */
    void updateAddSales(Long id);
    /**
     * 减少活动的销量值
     * */
    void updateSubSales(Long id);

}