package com.duia.commodity.core;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.Constant;
import com.duia.commodity.common.enums.CommodityEnum;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.enums.OrderType;
import com.duia.security.param.PayOrderParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

/**
 * Created by 李国勇 on 2018/4/23.
 */
@org.springframework.stereotype.Service
public class PayOrderProxy implements ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public PayOrder order(PayOrder payOrder, PayOrderParam p) {
        PayOrderProxyService proxyService = null; // 代理类
        logger.info("外部订单创建开始，参数明细：{}", JSON.toJSONString(p));
        if (Objects.equals(p.getType(), com.duia.security.util.Constant.PAYORDERPARAM_TYPE_ADD_U)) { // 升级订单
            if (Objects.equals(p.getSource(), Constant.ORDER_SOURCE_NBACK)) {// 后台订单
                proxyService = context.getBean("payOrderNBackUPProxyServiceImpl", PayOrderProxyService.class);
            } else {// 前台订单
                proxyService = context.getBean("payOrderFrontUPProxyServiceImpl", PayOrderProxyService.class);
            }
            payOrder.setType(OrderType.U.getState());
            proxyService.payOrder(payOrder, p.getP(), p.getU());
        } else {// 非升级订单
            Integer cType = p.getC().getType();// 商品类型
            if (CommodityEnum.COMMODITY_TYPE_SINGLE.getKey().equals(cType)) { // 单个商品
                if (Objects.equals(p.getSource(), Constant.ORDER_SOURCE_NBACK)) {// 后台订单
                    if (Objects.equals(com.duia.security.util.Constant.PAY_ORDER_TYPE_ADD_STUDENT, p.getP().getType())) { // 添加学员
                        payOrder.setPayType(Constant.PAY_TYPE_ADDSTUDENT);
                        proxyService = context.getBean("payOrderNBackASTProxyServiceImpl", PayOrderProxyService.class);
                    } else if (Objects.equals(com.duia.security.util.Constant.PAY_ORDER_TYPE_BALANCE, p.getP().getType())) { // 余额订单
                        proxyService = context.getBean("payOrderNBackBalanceSGProxyServiceImpl", PayOrderProxyService.class);
                    } else { // 录入订单 - 单品
                        proxyService = context.getBean("payOrderNBackSGProxyServiceImpl", PayOrderProxyService.class);
                    }
                } else { // 前台单品订单
                    proxyService = context.getBean("payOrderFrontSGProxyServiceImpl", PayOrderProxyService.class);
                }
                payOrder.setType(OrderType.C.getState());
            } else if (CommodityEnum.COMMODITY_TYPE_COMBO.getKey().equals(cType)
                    || CommodityEnum.COMMODITY_TYPE_COMBINATION.getKey().equals(cType)) { // 套餐、组合
                if (Objects.equals(p.getSource(), Constant.ORDER_SOURCE_NBACK)) {// 后台订单
                    if (Objects.equals(com.duia.security.util.Constant.PAY_ORDER_TYPE_BALANCE, p.getP().getType())) {// 余额录入
                        proxyService = context.getBean("payOrderNBackBalanceSPProxyServiceImpl", PayOrderProxyService.class);
                    } else { // 录入订单 - 套餐、组合
                        proxyService = context.getBean("payOrderNBackSPProxyServiceImpl", PayOrderProxyService.class);
                    }
                } else {// 前台套餐、组合订单
                    proxyService = context.getBean("payOrderFrontSPProxyServiceImpl", PayOrderProxyService.class);
                }
                payOrder.setType(CommodityEnum.COMMODITY_TYPE_COMBO.getKey().equals(cType) ? OrderType.S.getState() : OrderType.R.getState());
            }
            proxyService.payOrder(payOrder, p.getP(), p.getC());
        }
        logger.info("外部订单创建完成，订单明细：{}", JSON.toJSONString(payOrder));
        return payOrder;
    }
}
