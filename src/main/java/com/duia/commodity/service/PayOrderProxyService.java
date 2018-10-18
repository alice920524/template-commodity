package com.duia.commodity.service;

import com.duia.commodity.model.PayOrder;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.PayOrderParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 李国勇 on 2018/6/11.
 */
public interface PayOrderProxyService {
//
//    PayOrder payOrder(HttpServletRequest request, PayOrderParam p);

    /**
     * 构建订单
     *
     * @param payOrder
     * @param p
     * @return
     */
    PayOrder payOrder(PayOrder payOrder, PayOrderInfoParam p, Object o);

}
