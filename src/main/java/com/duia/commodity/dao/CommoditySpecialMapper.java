package com.duia.commodity.dao;

import com.duia.commodity.common.dto.CommoditySpecialBaseDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.CommoditySpecial;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CommoditySpecialMapper extends Mapper<CommoditySpecial> {
    /**
     * @param comId 商品ID
     * @param type  类型，1：套餐；2：组合
     */
    List<CommoditySpecialBaseDTO> selectByComIdAndType(@Param("comId") Long comId,
                                                       @Param("type") Integer type,
                                                       @Param("appType") Integer appType);

    Integer selectCountByComIdAndType(@Param("comId") Long comId,
                                                   @Param("type") Integer type,
                                                   @Param("appType") Integer appType);


//    Integer countBySpecialIdAndComIds(@Param("specialId") Long specialId, @Param("comIds") String[] comIds);

    List<Double>  selectTzCountByComIdAndType(@Param("comId") Long comId, @Param("appType") Integer appType);
}