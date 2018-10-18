package com.duia.commodity.service.impl;

import com.duia.commodity.common.comp.OrderAbComp;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.model.*;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.enums.Status;
import com.duia.security.exception.CheckPayOrderParamException;
import com.duia.security.param.PayInfoParam;
import com.duia.security.param.PayOrderInfoParam;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by 李国勇 on 2018/6/11.
 */
@Service
public abstract class PayOrderNBackProxyServiceImpl extends PayOrderProxyServiceImpl implements PayOrderProxyService {

    @Override
    void extra(PayOrder order, PayOrderInfoParam pi, Object op) {
        // 处理业绩信息
        this.setLiveTeacher(order, pi, op);
        // 订单表字段扩展方法
        this.orderModelExtra(order, pi, op);
    }

    @Override
    boolean isOldUser(PayOrder order, Object o) {
        return false;
    }

    double findVoucherPrice(PayOrder order, Object o) {
        OrderAbComp abComp = (OrderAbComp) o;
        if (abComp.getVoucherDetailId() != null && abComp.getVoucherDetailId() > 0) { //有特权券
            VoucherDetail voucherDetail = voucherDetailService.findById(abComp.getVoucherDetailId());
            this.checkVoucherDetail(voucherDetail);
            order.setVoucherOrder(OrderEnum.VOUCHER_ORDER_YES.getKey()); // 特权订单
            order.setVoucherDetailId(abComp.getVoucherDetailId()); // 特权详情ID
            return voucherDetail.getPrice();
        }
        return 0d;
    }

    @Override
    void _saveOrder(PayOrder order) {

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

    /**
     * 订单参数扩展
     *
     * @param order
     * @param pi
     * @param op
     */
    abstract void orderModelExtra(PayOrder order, PayOrderInfoParam pi, Object op);

    @Override
    void verifyOrderCostPrice(PayOrder payOrder, PayOrderInfoParam pi, PayDiscountDetail discountDetail, Double coursePrice) {
        // 获取商品价格最小值
        Double minCostPrice = new BigDecimal(payOrder.getRealpayPrice())
                .add(new BigDecimal(1))
                .subtract(new BigDecimal(coursePrice)).doubleValue();
        // 校验商品价格区间 - 后台订单可以手动输入实付价格,所以需要校验价格
        if (Objects.equals(pi.getType(), com.duia.security.util.Constant.PAY_ORDER_TYPE_MULTI)) {//多凭证
            List<PayInfoParam> payInfoParams = pi.getSubPayInfo();
            BigDecimal totalPrice = new BigDecimal(0);
            for (PayInfoParam payInfoParam : payInfoParams) {
                totalPrice = totalPrice.add(new BigDecimal(payInfoParam.getPrice()));
            }
            // 设置订单类型为父订单
            payOrder.setOrderType(OrderEnum.ORDER_TYPE_PARENT.getKey());
            // 校验订单价格
            this.verifyCostPrice(minCostPrice, totalPrice.doubleValue(), payOrder.getCostPrice());
        } else if (Objects.equals(pi.getType(), com.duia.security.util.Constant.PAY_ORDER_TYPE_NO_MULTI)) {// 普通下单
            // 校验订单价格
            this.verifyCostPrice(minCostPrice, pi.getParentPayInfo().getPrice(), payOrder.getCostPrice());
        }
        // 更新订单支付价格
        payOrder.setCostPrice(pi.getParentPayInfo().getPrice());
    }

    /**
     * 校验订单价格
     *
     * @param minCostPrice
     * @param inputCostPrice
     * @param costPrice
     */
    private void verifyCostPrice(Double minCostPrice, Double inputCostPrice, Double costPrice) {
        if (!(inputCostPrice >= minCostPrice && inputCostPrice <= costPrice)) {
            throw new CheckPayOrderParamException("录入订单价格错误");
        }
    }

    /**
     * 获取老师信息
     *
     * @param order
     * @param pi
     * @param op
     */
    void setLiveTeacher(PayOrder order, PayOrderInfoParam pi, Object op) {
        super.setTeacherInfo(order, new LiveTeacher(pi.getTeacherId(), pi.getTeacherName()));
    }

    /**
     * 校验一下优惠特权是否能用
     *
     * @param voucherDetail
     */
    private void checkVoucherDetail(VoucherDetail voucherDetail) {
        if (null == voucherDetail || Objects.equals(voucherDetail.getDeleted(), Status.VALID.getState())) {
            throw new CheckPayOrderParamException("优惠特权不存在或已删除");
        }
        if (Objects.equals(voucherDetail.getStatus(), Status.VALID.getState())) {
            throw new CheckPayOrderParamException("优惠特权已被使用");
        }
    }

}
