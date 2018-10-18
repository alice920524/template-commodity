package com.duia.commodity.dao;


import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.PayOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayOrderMapper extends Mapper<PayOrder> {
    List<PayOrder> findChildsPayOrder(@Param("orderParentId") Long orderParentId);
    Integer payOrderSuccess(@Param("orderParentId") Long orderParentId);

    List<PayOrder> findPayStatusNonOrder(@Param("orderIds") List<Long> orderIds);
}
