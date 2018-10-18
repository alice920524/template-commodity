package com.duia.commodity.dao;

import com.duia.commodity.common.dto.DiscountDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.VoucherDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoucherDetailMapper extends Mapper<VoucherDetail> {
    /**
     * 获取优惠特权列表
     * */
    List<DiscountDTO> selectYHTQDiscountList(@Param("appType") Integer appType, @Param("userId") Long userId);
}