package com.duia.commodity.service.impl;

import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.security.param.PayOrderInfoParam;
import org.springframework.stereotype.Service;

/**
 * Created by 李国勇 on 2018/6/11.
 */
@Service("payOrderNBackSGProxyServiceImpl")
public class PayOrderNBackSGProxyServiceImpl extends PayOrderNBackProxyServiceImpl implements PayOrderProxyService {

    @Override
    protected void orderModelExtra(PayOrder order, PayOrderInfoParam pi, Object op) {

    }

    @Override
    void _saveOrder(PayOrder order) {
    }

}
