package com.duia.commodity.dao;

import com.duia.commodity.common.dto.DiscountDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.PayDiscountDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayDiscountDetailMapper extends Mapper<PayDiscountDetail> {
    /**
     * 可用优惠券列表
     * */
    List<DiscountDTO> selectDiscountList(@Param("appType") Integer appType, @Param("userId") Long userId);
}