package com.duia.commodity.service.impl;

import bjca.org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.dto.OrderCreateSuccessDTO;
import com.duia.commodity.common.enums.ErrorResultCode;
import com.duia.commodity.common.util.HttpClientUtils;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.UserIosBalanceMapper;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.UserIosBalance;
import com.duia.commodity.model.UserIosBalanceDetail;
import com.duia.commodity.model.Users;
import com.duia.commodity.service.ClassesService;
import com.duia.commodity.service.PayOrderService;
import com.duia.commodity.service.UserIosBalanceDetailService;
import com.duia.commodity.service.UserIosBalanceService;
import com.duia.constant.Constants;
import com.duia.enums.PayStatus;
import com.duia.enums.PayType;
import com.duia.resultG.Result;
import com.duia.resultG.ResultGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/07/30.
 */
@Service
public class UserIosBalanceServiceImpl extends AbstractService<UserIosBalance> implements UserIosBalanceService {
    private static Logger logger = Logger.getLogger(UserIosBalanceServiceImpl.class);
    @Resource
    private UserIosBalanceMapper userIosBalanceMapper;
    @Resource
    private UserIosBalanceDetailService userIosBalanceDetailService;
    @Resource
    private PayOrderService payOrderService;
    @Resource
    private ClassesService classesService;

    @Value("${pay.domain}")
    private String payDomain;
    public static final String pay = "/balance/pay";

    /**
     * 获取ios用户余额
     * @param userId
     * @return
     */
    @Transactional
    @Override
    public Double getUserIosBalance(Long userId) {
        if (null == userId) {
            return 0.0;
        }

        UserIosBalance userIosBalanceQuery = new UserIosBalance();
        userIosBalanceQuery.setUserId(userId);

        List<UserIosBalance> userIosBalanceList = this.userIosBalanceMapper.select(userIosBalanceQuery);
        if (null == userIosBalanceList || userIosBalanceList.isEmpty()) {
            return 0.0;
        }
        return userIosBalanceList.get(0).getBalance();
    }


    /**
     * ios支付
     * */
    @Override
    public Result createIosPay(Users user, String payNum) {
        OrderCreateSuccessDTO resultObject = new OrderCreateSuccessDTO();
        if (null == payNum) {
            return ResultGenerator.genFailResult("参数不能为空");
        }
        PayOrder payOrder = this.payOrderService.findPayOrderByPayNum(payNum);
        if (null == payOrder) {
            return ResultGenerator.genFailResult("无此订单");
        }

        // case1 零元购 case2 订单已支付
        if (PayStatus.PAY_STATUS_SUCCESS.getState().equals(payOrder.getPayStatus()) || PayType.PAY_TYPE_ZERO.getState().equals(payOrder.getPayType())) {
            // 返回classId
            resultObject.setClassId(this.classesService.findClassId(payOrder));
            return ResultGenerator.genSuccessResult(resultObject);
        }
        // 扣除余额
        UserIosBalanceDetail userIosBalanceDetail = this.subBalance(user, payOrder);

        if (null == userIosBalanceDetail) {
            // 余额不足
            return ResultGenerator.genCustomResult(ErrorResultCode.BALANCE_ERROR.code, "余额不足", null);
        } else {
            Result result = this.openClassesRequest(userIosBalanceDetail.getId(), userIosBalanceDetail.getPayNum());
            if (ResultGenerator.genSuccessResult().getCode()== result.getCode()) {
                resultObject.setClassId(this.classesService.findClassId(payOrder));// 班级的ID
            } else {
                logger.error("IOS 学币支付-开班失败-payNum:" + payOrder.getPayNum() + "-httpResult:" + JSON.toJSONString(result));
            }
            return ResultGenerator.genSuccessResult(resultObject);
        }
    }


    /**
     * 减余额
     * */
    @Transactional
    protected UserIosBalanceDetail subBalance(Users user, PayOrder payOrder) {
        Double balance = this.getUserIosBalance(user.getId());
        Double costPrice = payOrder.getCostPrice();
        if (balance >= costPrice) {
            // 学币扣款成功、扣款明细插入db
            Integer updateCount = this.userIosBalanceMapper.updateSubBalance(costPrice, user.getId());
            if (updateCount > 0) {

                return this.userIosBalanceDetailService.saveByPayOrder(payOrder);
            }
        }
        return null;
    }

    /**
     * 开班请求
     * */
    private Result openClassesRequest(Long balanceId,String payNum) {
        if (null == balanceId || null == payNum) {
            return ResultGenerator.genFailResult("balanceId参数为空");
        }
        Map<String, String> param = new HashMap<>(2);
        param.put("balanceId", balanceId.toString());
        param.put("payNum", payNum);
        String resultString = HttpClientUtils.post(Constants.HTTP + payDomain + pay, param);
        if (null == resultString) {
            return ResultGenerator.genFailResult("httpClient请求返回结果为null");
        }
        return JSON.parseObject(resultString, Result.class);
    }

}
