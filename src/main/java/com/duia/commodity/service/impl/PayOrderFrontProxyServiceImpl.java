package com.duia.commodity.service.impl;

import com.duia.commodity.model.PayDiscountDetail;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.security.param.PayOrderInfoParam;
import org.springframework.stereotype.Service;

/**
 * Created by 李国勇 on 2018/6/11.
 */
@Service
public abstract class PayOrderFrontProxyServiceImpl extends PayOrderProxyServiceImpl implements PayOrderProxyService {

    @Override
    void extra(PayOrder order, PayOrderInfoParam pi, Object op) {

    }

    @Override
    boolean isOldUser(PayOrder order, Object o) {
        return commodityService.validateAccordOldSale(order.getUserId());
    }

    /**
     * 前台不直接使用特权券
     *
     * @param order
     * @param o
     * @return
     */
    double findVoucherPrice(PayOrder order, Object o) {
        return 0d;
    }

    @Override
    void verifyOrderCostPrice(PayOrder payOrder, PayOrderInfoParam pi, PayDiscountDetail discountDetail, Double coursePrice) {
        // 空方法，什么也不需要做
    }

    @Override
    void _saveOrder(PayOrder order) {

    }
}
