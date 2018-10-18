package com.duia.commodity.service.impl;

import com.duia.commodity.model.CommodityPromotion;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.UpgradeParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by 李国勇 on 2018/6/11.
 */
@Service("payOrderFrontUPProxyServiceImpl")
public class PayOrderFrontUPProxyServiceImpl extends PayOrderFrontProxyServiceImpl implements PayOrderProxyService {

    @Override
    void extra(PayOrder order, PayOrderInfoParam pi, Object op) {
        UpgradeParam u = (UpgradeParam) op;
        if (StringUtils.isEmpty(u.getDiscountId())) {
            super.setTeacherInfo(order, super.getUpgradeBeforeTeacher(u.getUpgradeId()));
        }
    }

    /**
     * 老生优惠是否可以使用标记(升级订单不能使用老生优惠)
     * */
    @Override
    boolean isOldUser(PayOrder order, Object o) {
        return false;
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
