package com.duia.commodity.service;

import com.duia.commodity.core.Service;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.PayOrderTrans;

/**
 * Created by CodeGenerator on 2017/07/12.
 */
public interface PayOrderTransService extends Service<PayOrderTrans> {
    PayOrderTrans saveTrans(PayOrder order);
}
