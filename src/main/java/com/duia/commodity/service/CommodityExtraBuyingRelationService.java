package com.duia.commodity.service;

import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.dto.CommodityExtraDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.CommodityExtraBuyingRelation;

import java.util.List;

public interface CommodityExtraBuyingRelationService extends Service<CommodityExtraBuyingRelation> {

    /**
     * 查询组合加价购商品、单品加价购商品、套餐加价购商品
     * */
    CommodityDTO selectCommodity(Long relationId,Integer type, Long comId);

    /**
     * 商品加价购列表
     * comId 商品id
     *  comType 商品类型(0：单品；1：套餐；2：组合) ,查询数据需要处理数据库(0：单品；1：套餐；2：组合)
     *   appType 终端类型
     *   specialId 套餐或组合商品id
     * */
    List<CommodityExtraDTO> findCommodityExtra(Long comId, Long specialId, Integer comType, Integer appType);
}
