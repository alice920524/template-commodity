package com.duia.commodity.service.impl;

import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderProxyService;
import org.springframework.stereotype.Service;

/**
 * 余额录入订单服务类
 */
@Service("payOrderNBackBalanceSPProxyServiceImpl")
public class PayOrderNBackBalanceSPProxyServiceImpl extends PayOrderNBackBalanceProxyServiceImpl implements PayOrderProxyService {

    @Override
    void _saveOrder(PayOrder order) {

    }

}
