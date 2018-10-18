package com.duia.commodity.service.impl;

import com.duia.commodity.dao.UserIosBalanceDetailMapper;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.UserIosBalanceDetail;
import com.duia.commodity.service.UserIosBalanceDetailService;
import com.duia.commodity.core.AbstractService;
import com.duia.enums.PayStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2018/07/30.
 */
@Service
@Transactional
public class UserIosBalanceDetailServiceImpl extends AbstractService<UserIosBalanceDetail> implements UserIosBalanceDetailService {
    @Resource
    private UserIosBalanceDetailMapper userIosBalanceDetailMapper;

    @Override
    public UserIosBalanceDetail saveByPayOrder(PayOrder payOrder) {
        Date date = new Date();
        UserIosBalanceDetail userIosBalanceDetail = new UserIosBalanceDetail();
        userIosBalanceDetail.setUserId(payOrder.getUserId());
        userIosBalanceDetail.setMoney(payOrder.getCostPrice());
        userIosBalanceDetail.setUpdateTime(date);
        userIosBalanceDetail.setCreateTime(date);
        userIosBalanceDetail.setPayNum(payOrder.getPayNum());
        userIosBalanceDetail.setPayStatus(PayStatus.PAY_STATUS_NON.getState());
        userIosBalanceDetail.setPayType(2);
        userIosBalanceDetailMapper.insert(userIosBalanceDetail);
        return userIosBalanceDetail;
    }
}
