package com.duia.commodity.service.impl;

import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.common.enums.MqEnum;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.core.PayOrderProxy;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.PayOrderAuthProxyService;
import com.duia.commodity.service.PayOrderService;
import com.duia.enums.PayStatus;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.PayOrderParam;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by 李国勇 on 2018/6/11.
 */
@Service
public abstract class PayOrderAuthProxyServiceImpl implements PayOrderAuthProxyService {
    private static Logger logger = Logger.getLogger(PayOrderAuthProxyServiceImpl.class);
    @Resource
    private PayOrderProxy payOrderProxy;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private PayOrderService payOrderService;

    @Override
    public PayOrder process(HttpServletRequest request, PayOrderParam p) {

        // 构建订单信息
        PayOrder order = payOrderProxy.order(this.init(request, p), p);

        // 构建子订单及相关验证
        this.validateAndMakeChildOrders(order, p.getP());

        // 验证订单并处理用户授权
        if (this.validateAndSendVipPermissions(order)) {
            logger.info("mq>>>开班>>>" + order.getPayNum());
            rabbitTemplate.convertAndSend(MqEnum.NBACK_ORDER_OPEN_CLASSES.getExchange(), MqEnum.NBACK_ORDER_OPEN_CLASSES.getRoutingKey(), order.getPayNum());
        }

        return order;
    }

    /**
     * 构建参数信息
     *
     * @param request
     * @param p
     * @param payOrder
     * @return
     */
    abstract void constructParameters(HttpServletRequest request, PayOrderParam p, PayOrder payOrder);

    /**
     * 构建子订单及相关验证
     *
     * @param order
     * @param infoParam
     */
    abstract void validateAndMakeChildOrders(PayOrder order, PayOrderInfoParam infoParam);

    /**
     * 验证订单并处理用户授权
     *
     * @param order
     */
    abstract boolean validateAndSendVipPermissions(PayOrder order);

    /**
     * 创建订单通用信息
     *
     * @return
     */
    private PayOrder init(HttpServletRequest request, PayOrderParam p) {
        PayOrder order = new PayOrder();
        order.setParentId(0L); // 未被使用
        order.setUserId(p.getP().getUserId());
        order.setOrderType(OrderEnum.ORDER_TYPE_COMMON.getKey());
        order.setOrderTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        order.setCreateTime(order.getOrderTime());
        order.setPayNum(this.genOutTradeNo());
        order.setIsOld(OrderEnum.NEW_ORDER.getKey());
        order.setIsTurnOrder(OrderEnum.TURN_ORDER_NO.getKey());
        order.setOrderInstal(OrderEnum.INSTAL_BATCH_MULTI_DEFAULT.getKey()); // 分批
        order.setOrderBatch(OrderEnum.INSTAL_BATCH_MULTI_DEFAULT.getKey()); // 分期
        order.setOrderMulti(OrderEnum.INSTAL_BATCH_MULTI_DEFAULT.getKey()); // 多凭证
        order.setPayStatus(PayStatus.PAY_STATUS_NON.getState()); // 默认订单未支付
        order.setIsMail(OrderEnum.MAIL_CLASS_TYPE_NO.getKey()); // 默认班型未配置学习包
        order.setDeleteMark(OrderEnum.ORDER_DELETE_NO.getKey());
        order.setVoucherOrder(OrderEnum.VOUCHER_ORDER_NO.getKey());// 默认非特权订单
        order.setBuyMode(OrderEnum.BUY_MODE_COMMON.getKey());// 默认普通购
        order.setCourseType(ClassTypeEnum.COURSE_TYPE_SYSTEM.getKey());// 默认系统班
        order.setAuditStatus("AUDIT_NOT_DO");
        order.setOldBalance(0.0);// 原单余额
        order.setDuration(0);// 贷款期数
        order.setRate(0.0);// 分期利率
        constructParameters(request, p, order);
        return order;
    }

    /**
     * 生成订单编号
     *
     * @return
     */
    protected String genOutTradeNo() {
        for (; ; ) {
            String outTradeNo = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
                    + RandomStringUtils.randomNumeric(12);
            if (payOrderService.findBy("payNum", outTradeNo) == null) {//由于该字段没有添加唯一性索引，需要确定是否重复
                return outTradeNo;
            }
        }
    }


}
