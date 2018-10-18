package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.PayOrderDetail;

import java.util.List;

public interface PayOrderDetailMapper extends Mapper<PayOrderDetail> {
    /**
     * 订单商品名称
     * */
    List<String> selectOrderCommodityName(Long orderId);

    /**
     * 通过订单号查询协议
     */
    List<PayOrderDetail> selectTipsByPayNum(String payNum);
}