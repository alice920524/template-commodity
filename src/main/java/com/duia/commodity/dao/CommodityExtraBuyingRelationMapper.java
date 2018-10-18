package com.duia.commodity.dao;

import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.dto.CommodityExtraDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.CommodityExtraBuyingRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityExtraBuyingRelationMapper extends Mapper<CommodityExtraBuyingRelation> {
    /**
     * 查询加价购商品
     * */
    CommodityDTO selectCommodity(@Param("relationId") Long relationId, @Param("type") Integer type, @Param("comId") Long comId);

    /**
     * @Author: Hero
     * @Description: 单品加价购列表
     * @Date: Created in 13:33 2018/5/16
     * @param  * @param comId
     * @param comType
     * @param appType
     */
    List<CommodityExtraDTO> findCommodityExtra(@Param("comId") Long comId, @Param("comType") Integer comType, @Param("appType") Integer appType);
    /**
     * @Author: Hero
     * @Description:套餐或组合加价购列表
     * @Date: Created in 13:33 2018/5/16
     * @param specialId
     * @param comType
     * @param appType
     */
    List<CommodityExtraDTO> findSpecialCommodityExtra(@Param("specialId") Long specialId, @Param("comType") Integer comType, @Param("appType") Integer appType);

}