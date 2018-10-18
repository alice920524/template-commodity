package com.duia.commodity.service;

import com.duia.commodity.core.Service;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.UserIosBalanceDetail;


/**
 * Created by CodeGenerator on 2018/07/30.
 */
public interface UserIosBalanceDetailService extends Service<UserIosBalanceDetail> {

    public UserIosBalanceDetail saveByPayOrder(PayOrder payOrder);
}
