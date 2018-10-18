package com.duia.commodity.dao;

import com.duia.commodity.common.dto.CommodityBaseDTO;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.Commodity;
import com.duia.security.param.CommodityParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper extends Mapper<Commodity> {

    //加价购商品
    int countByComIdAndAppType(@Param("comId") Long comId, @Param("appType") Integer appType);

    //获得赠品列表
    List<Commodity> selectByIdList(List<Long> idList);

    // 商品列表
    List<CommodityBaseDTO> selectCommodityDTOBySpecialId(@Param("specialId") Long specialId);

    // 单品商品信息
    List<CommodityDTO> selectCommodityByParam(@Param("params") List<CommodityParam> params, @Param("commodityStatus") Integer commodityStatus);

    // 套餐组合商品信息
    List<CommodityDTO> selectSpecialCommodityByParam(@Param("specialId") Long specialId, @Param("params") List<CommodityParam> params, @Param("commodityStatus") Integer commodityStatus);

}