package com.duia.commodity.core;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.Constant;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderAuthProxyService;
import com.duia.security.exception.CheckPayOrderParamException;
import com.duia.security.param.PayInfoParam;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.PayOrderParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 李国勇 on 2018/4/23.
 */
@org.springframework.stereotype.Service
public class PayOrderAuthProxy implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * 订单授权
     *
     * @param request
     * @param p
     */
    @Transactional
    public PayOrder auth(HttpServletRequest request, PayOrderParam p) {
        PayOrderAuthProxyService proxyService;
        if (Objects.equals(p.getSource(), Constant.ORDER_SOURCE_NBACK)) { // 后台订单
            if (!this.verifyNBackOrderParams(p.getP())) { // 参数校验外置
                throw new IllegalArgumentException("参数校验异常！");
            }
            proxyService = context.getBean("nBackOrderAuthService", PayOrderAuthProxyService.class);
        } else { // 前台订单
            proxyService = context.getBean("frontOrderAuthService", PayOrderAuthProxyService.class);
        }
        logger.info("外部订单创建开始，参数明细：{}", JSON.toJSONString(p));
        PayOrder order = proxyService.process(request, p);
        logger.info("外部订单创建完成，订单明细：{}", JSON.toJSONString(order));
        return order;
    }


    /**
     * 校验后台订单参数
     *
     * @param info
     * @return
     * @throws CheckPayOrderParamException
     */
    boolean verifyNBackOrderParams(PayOrderInfoParam info) throws CheckPayOrderParamException {
        // 校验学员信息
        if (info == null || info.getUserId() == null) {
            throw new CheckPayOrderParamException("学员信息不能为空");
        }

        // 校验订单类型
        if (info.getType() == null) {
            throw new CheckPayOrderParamException("TYPE不能为空");
        }

        // 订单类型：多凭证、非多凭证 ？
        if (com.duia.security.util.Constant.PAY_ORDER_TYPE_NO_MULTI.equals(info.getType())
                || com.duia.security.util.Constant.PAY_ORDER_TYPE_MULTI.equals(info.getType())) {

            // 校验学员QQ信息
            if (null == info.getQq()) {
                throw new CheckPayOrderParamException("QQ信息不能为空");
            }
            // 支付信息校验
            PayInfoParam parentPayInfo = info.getParentPayInfo();
            //多凭证
            Integer multi = com.duia.security.util.Constant.PAY_ORDER_TYPE_MULTI;
            if (Objects.equals(info.getType(), multi)) {
                List<PayInfoParam> subPayInfos = info.getSubPayInfo();
                // 校验子订单支付信息
                this.verifyPayInfoParam(subPayInfos, parentPayInfo.getPrice());

                // 支付时间重复则校验不通过
                Collections.sort(subPayInfos, new Comparator<PayInfoParam>() {
                    @Override
                    public int compare(PayInfoParam o1, PayInfoParam o2) {
                        if (o1.getOrderTime().equals(o2.getOrderTime())) {
                            throw new CheckPayOrderParamException("支付时间不能相同");
                        }
                        return o1.getOrderTime().compareTo(o2.getOrderTime());
                    }
                });
                // 多凭证的时候，父订单时间和子订单最小时间相同
                parentPayInfo.setOrderTime(subPayInfos.get(0).getOrderTime());
            }
            // 校验父订单支付信息
            this.verifyPayInfoParam(Arrays.asList(parentPayInfo), parentPayInfo.getPrice());
        }
        return true;
    }

    /**
     * 支付信息校验
     *
     * @param payInfoParams
     * @param costPrice
     * @throws CheckPayOrderParamException
     */
    private void verifyPayInfoParam(List<PayInfoParam> payInfoParams, Double costPrice) throws CheckPayOrderParamException {
        // 校验订单支付信息
        if (payInfoParams == null) {
            throw new CheckPayOrderParamException("支付方式不能为空");
        }
        BigDecimal subTotalPrice = new BigDecimal(0);
        // 检验订单支付信息
        for (PayInfoParam payInfoParam : payInfoParams) {
            if (payInfoParam.getOrderTime() == null || payInfoParam.getPrice() == null || payInfoParam.getPayType() == null) {
                throw new CheckPayOrderParamException("支付方式参数不能为空");
            }
            subTotalPrice = subTotalPrice.add(new BigDecimal(payInfoParam.getPrice()));
        }
        if (!costPrice.equals(subTotalPrice.doubleValue())) {
            throw new CheckPayOrderParamException("支付价格计算错误");
        }
    }

}
