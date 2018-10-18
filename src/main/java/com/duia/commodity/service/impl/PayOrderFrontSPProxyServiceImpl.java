package com.duia.commodity.service.impl;

import com.duia.commodity.model.CommodityPromotion;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.security.param.PayOrderInfoParam;
import org.springframework.stereotype.Service;

/**
 * Created by 李国勇 on 2018/6/11.
 */
@Service("payOrderFrontSPProxyServiceImpl")
public class PayOrderFrontSPProxyServiceImpl extends PayOrderFrontProxyServiceImpl implements PayOrderProxyService {

    @Override
    void extra(PayOrder order, PayOrderInfoParam pi, Object op) {

    }

    /**
     * 订单促销活动
     *
     * @param order
     * @param o
     * @return
     */
    @Override
    CommodityPromotion promotionOrder(PayOrder order, Object o) {
        return null;
    }

    @Override
    void _saveOrder(PayOrder order) {

    }

}
