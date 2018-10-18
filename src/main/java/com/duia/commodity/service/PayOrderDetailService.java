package com.duia.commodity.service;

import com.duia.commodity.common.dto.PayTipsDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.PayOrderDetail;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
public interface PayOrderDetailService extends Service<PayOrderDetail> {

    /**
     * 保存订单详情
     *
     * @param payOrder
     * @param o
     */
    void savePayOrderDetail(PayOrder payOrder, Object o);

    /**
     * 订单商品名称
     *
     * @param orderId
     */
    List<String> selectOrderCommodityName(Long orderId);

    /**
     * 支付成功提示信息
     *
     * @param payNum
     * @return
     */
    List<PayTipsDTO> findPayTips(String payNum);


}
