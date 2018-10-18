package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.comp.OrderAbComp;
import com.duia.commodity.common.dto.FQPeriodDTO;
import com.duia.commodity.common.dto.FQShuJuDTO;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.model.LiveTeacher;
import com.duia.commodity.model.PayDiscountDetail;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.constant.Constants;
import com.duia.enums.DiscountType;
import com.duia.security.exception.CheckPayOrderParamException;
import com.duia.security.param.PayInfoParam;
import com.duia.security.param.PayOrderCommodityParam;
import com.duia.security.param.PayOrderInfoParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

/**
 * 余额录入订单服务基类
 */
@Service
public abstract class PayOrderNBackBalanceProxyServiceImpl extends PayOrderNBackProxyServiceImpl implements PayOrderProxyService {

    @Value("${balance.domain}")
    private String balancePath;

    /**
     * 学员选择学习包
     *
     * @return
     */
    boolean isStudentSelectStudyPack(Object o) {
        PayOrderCommodityParam param = (PayOrderCommodityParam) o;
        return param.isStudentSelectStudyPack();
    }

    @Override
    protected void orderModelExtra(PayOrder order, PayOrderInfoParam pi, Object op) {

    }

    @Override
    void setLiveTeacher(PayOrder order, PayOrderInfoParam pi, Object op) {
        PayOrderCommodityParam payOrderCommodityParam = (PayOrderCommodityParam) op;
        super.setTeacherInfo(order, this.getBalanceLiveTeacher(payOrderCommodityParam.getDiscountId(), pi.getTeacherId(), pi.getTeacherName()));
    }

    @Override
    void setCostPrice(PayOrder order, OrderAbComp o, double coursePrice, double discountPrice) {
        order.setCostPrice(0.0);
    }

    @Override
    void verifyOrderCostPrice(PayOrder payOrder, PayOrderInfoParam pi, PayDiscountDetail payDiscountDetail, Double coursePrice) {
        PayInfoParam parentPayInfo = pi.getParentPayInfo();
        // 获取用户余额金额
        Double userBalance = this.getUserBalancePrice(pi.getUserId());
        // 余额不足，抛出异常
        if (userBalance == null || userBalance <= 0) {
            throw new CheckPayOrderParamException("余额录入订单>>>学员余额不足");
        }
        // 用户可使用的余额金额
        Double userUseBalancePrice = 0d;
        // 校验订单金额与用户余额
        if (payDiscountDetail != null) {
            // 获取优惠券设置的优惠价格
            double discountPrice = StringUtils.isNotBlank(payDiscountDetail.getDiscount())
                    ? Double.parseDouble(payDiscountDetail.getDiscount()) : 0d;
            // 普通优惠券
            if (Objects.equals(DiscountType.PT.getState(), payDiscountDetail.getDiscountType())) {
                // 订单总额-优惠券金额=需要支付的余额
                userUseBalancePrice = new BigDecimal(parentPayInfo.getPrice()).subtract(new BigDecimal(discountPrice)).doubleValue();
            } else if (Objects.equals(DiscountType.FQ.getState(), payDiscountDetail.getDiscountType())) {
                // 设置订单类型为父订单
                payOrder.setOrderType(OrderEnum.ORDER_TYPE_PARENT.getKey());
                ////////////////////////////////////////////////////////////////////////////////////////////////
                List<PayInfoParam> subPayInfo = pi.getSubPayInfo();
                // 优惠券优惠策略
                FQPeriodDTO fqPeriodDTO = JSON.parseObject(payDiscountDetail.getPeriod(), FQPeriodDTO.class);
                if (null == fqPeriodDTO) {
                    throw new CheckPayOrderParamException("余额录入订单>>>此优惠券不能使用>>>优惠券类型>>>" + payDiscountDetail.getDiscountType());
                }
                // 严格控制优惠策略数据
                if (!fqPeriodDTO.getFq().equals(2) || fqPeriodDTO.getShuju().size() != 2) {
                    throw new CheckPayOrderParamException("余额录入订单>>>分期期数错误>>>" + payDiscountDetail.getPeriod());
                }
                // 优惠券策略
                List<FQShuJuDTO> fqShuJuDTOList = fqPeriodDTO.getShuju();
                // 校验待支付金额
                if (subPayInfo.size() != fqShuJuDTOList.size()) {
                    throw new CheckPayOrderParamException("余额录入订单>>>子订单数量不匹配");
                }
                /**
                 * 分两笔
                 *  第一笔 余额支付
                 *  第二笔 待支付
                 *
                 * */
                // 分期优惠券中第一期支付的金额(使用的余额)
                userUseBalancePrice = fqShuJuDTOList.get(0).getPeriodMoney();
                // 需要支付的余额(参数传递的扣款余额)
                Double inputOldBalance = subPayInfo.get(0).getPrice();
                // 待支付金额(支付总金额-优惠金额-使用的余额)
                Double waitPayPrice = new BigDecimal(payOrder.getRealpayPrice()).subtract(new BigDecimal(discountPrice)).subtract(new BigDecimal(userUseBalancePrice)).doubleValue();
                // 后台传递的待支付的金额
                Double inputWaitPayPrice = subPayInfo.get(1).getPrice();
                if (!waitPayPrice.equals(inputWaitPayPrice)) {
                    throw new CheckPayOrderParamException("余额录入订单>>>待支付金额不匹配");
                }
                if (!userUseBalancePrice.equals(inputOldBalance)) {
                    throw new CheckPayOrderParamException("余额录入订单>>>余额不匹配");
                }
            } else {
                throw new CheckPayOrderParamException("余额录入订单>>>优惠券使用错误");
            }
        } else {
            userUseBalancePrice = parentPayInfo.getPrice();
        }
        // 执行校验并设置用户余额
        this.verifyAndSetOrderBalancePrice(payOrder, parentPayInfo, userUseBalancePrice, userBalance);
    }

    /**
     * 余额录入业绩归属人
     * <p>
     * 不用优惠券->录入人
     * 使用券->券对应销售
     *
     * @param discountId
     * @param teacherId
     * @param teacherName
     * @return
     */
    private LiveTeacher getBalanceLiveTeacher(String discountId, Long teacherId, String teacherName) {
        LiveTeacher liveTeacher;
        if (StringUtils.isEmpty(discountId)) {
            if (teacherId == null) {
                throw new CheckPayOrderParamException("业绩归属人不能为空");
            } else {
                liveTeacher = new LiveTeacher(teacherId, teacherName);
            }
        } else {
            liveTeacher = super.getDiscountLiveTeacher(discountId);
        }
        return liveTeacher;
    }

    /**
     * 校验并设置用户余额使用
     *
     * @param payOrder
     * @param parentPayInfo
     * @param userBalance
     */
    private void verifyAndSetOrderBalancePrice(PayOrder payOrder, PayInfoParam parentPayInfo, Double userUseBalance, Double userBalance) {
        // 订单总金额计算
        this.checkBalanceOrderTotalPrice(payOrder.getRealpayPrice(), parentPayInfo.getPrice());
        this.checkUserBalance(userUseBalance, userBalance);// 校验余额
        payOrder.setOldBalance(userUseBalance);
    }

    /**
     * 校验余额录入订单总金额
     *
     * @param realpayPrice
     * @param inputOrderPrice
     */
    private void checkBalanceOrderTotalPrice(Double realpayPrice, Double inputOrderPrice) {
        if (!realpayPrice.equals(inputOrderPrice)) {
            throw new CheckPayOrderParamException("余额录入订单>>>订单总金额错误");
        }
    }

    /**
     * 校验余额和使用的余额
     *
     * @param oldBalance
     * @param userBalance
     */
    private void checkUserBalance(Double oldBalance, Double userBalance) {
        if (oldBalance > userBalance)
            throw new CheckPayOrderParamException("余额录入订单>>>学员余额不足");
    }

    /**
     * 获取用户余额
     *
     * @Date: 11:17 2018/6/25
     */
    private Double getUserBalancePrice(Long userId) {
        Double defaultMoney = 0.0;// 默认余额
        String queryUrl = Constants.HTTP + balancePath + "/money/get_money?user_id=" + userId;
        try {
            ResponseEntity<Map> responseEntity = new RestTemplate().getForEntity(queryUrl, Map.class, new HashMap<>());
            if (200 == responseEntity.getStatusCodeValue()) {
                // {"state":0,"stateInfo":"success","result":{"userId":11325599,"money":20811.0}}
                Map<String, Object> result = responseEntity.getBody();
                if ("success".equals(result.get("stateInfo"))) {
                    Object resultObject = result.get("result");
                    if (null == resultObject) {
                        return defaultMoney;
                    }
                    result = (LinkedHashMap) resultObject;
                    Object moneyObject = result.get("money");
                    if (moneyObject == null) {
                        return defaultMoney;
                    }
                    return (Double) moneyObject;
                } else {
                    throw new IllegalArgumentException("获取余额接口异常！！！");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CheckPayOrderParamException("余额录入订单>>>余额查询失败！！！！>>>" + queryUrl);
        }
        return defaultMoney;
    }

}
