package com.duia.commodity.service;

import com.duia.commodity.core.Service;
import com.duia.commodity.model.UserIosBalance;
import com.duia.commodity.model.Users;
import com.duia.resultG.Result;


/**
 * Created by CodeGenerator on 2018/07/30.
 */
public interface UserIosBalanceService extends Service<UserIosBalance> {

    /**
     * 获取ios用户余额
     */
    public Double getUserIosBalance(Long userId);

    /**
     * ios支付
     * */
    public Result createIosPay(Users user, String payNum);

}
