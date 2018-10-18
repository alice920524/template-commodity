package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.dto.FQPeriodDTO;
import com.duia.commodity.common.dto.FQShuJuDTO;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.common.util.DateUtils;
import com.duia.commodity.model.PayDiscountDetail;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.CrmReplyService;
import com.duia.commodity.service.PayDiscountDetailService;
import com.duia.commodity.service.PayOrderAuthProxyService;
import com.duia.commodity.service.PayOrderService;
import com.duia.enums.DiscountType;
import com.duia.enums.PayStatus;
import com.duia.enums.PayType;
import com.duia.security.exception.CheckPayOrderParamException;
import com.duia.security.param.PayInfoParam;
import com.duia.security.param.PayOrderInfoParam;
import com.duia.security.param.PayOrderParam;
import com.duia.security.util.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Transactional
@Service("nBackOrderAuthService")
public class PayOrderAuthNBackProxyServiceImpl extends PayOrderAuthProxyServiceImpl implements PayOrderAuthProxyService {
    @Resource
    private PayOrderService payOrderService;
    @Resource
    private CrmReplyService crmReplyService;
    @Resource
    private PayDiscountDetailService payDiscountDetailService;

    @Override
    void constructParameters(HttpServletRequest request, PayOrderParam p, PayOrder payOrder) {
        PayOrderInfoParam i = p.getP();
        PayInfoParam parentPayInfo = i.getParentPayInfo();

        // 设置QQ和备注信息
        payOrder.setQq(i.getQq());
        payOrder.setRemark(i.getRemark());
        //////////////////////////////////////////////////////////////////////////
        // 支付状态
        payOrder.setPayStatus(PayStatus.PAY_STATUS_SUCCESS.getState());
        //////////////////////////////////////////////////////////////////////////
        // 设置终端和来源
        payOrder.setSource(com.duia.commodity.common.Constant.ORDER_SOURCE_NBACK);
        payOrder.setOs(0);
        //////////////////////////////////////////////////////////////////////////
        // 设置创建者
        payOrder.setCreator(i.getCreator());
        //////////////////////////////////////////////////////////////////////////
        if (null != parentPayInfo) {// 添加学员不需要支付信息
            // 下单时间、创建时间、支付时间
            payOrder.setOrderTime(DateUtils.format(i.getParentPayInfo().getOrderTime()));
            payOrder.setPayTime(DateUtils.format(i.getParentPayInfo().getPayTime()));

            // 支付类型
            payOrder.setPayType(parentPayInfo.getPayType());

            // 支付凭证
            payOrder.setOrderNum(parentPayInfo.getOrderNum());
            // 支付昵称
            payOrder.setVoucher(parentPayInfo.getVoucher());
            // 期数
            if (null != parentPayInfo.getDuration()) {
                payOrder.setDuration(parentPayInfo.getDuration());
            }
            // 利率
            if (null != parentPayInfo.getRate()) {
                payOrder.setRate(parentPayInfo.getRate());
            }
        }
    }

    @Override
    void validateAndMakeChildOrders(PayOrder order, PayOrderInfoParam infoParam) {
        // 余额录入
        if (Constant.PAY_ORDER_TYPE_BALANCE.equals(infoParam.getType())) {
            this.invokeBalanceOrder(order, infoParam);
        } else if (!PayType.PAY_TYPE_ADD_STUDENT.getState().equals(order.getPayType())) {
            // 录入子订单数据
            this.insertNBackChildOrder(order, infoParam.getSubPayInfo());
        }

        /**交接回复*/
        this.crmReplyService.saveCrmReply(order);
    }

    @Override
    boolean validateAndSendVipPermissions(PayOrder order) {
        return true;
    }

    /**
     * 处理余额订单
     *
     * @param order     主订单
     * @param infoParam 订单信息
     */
    private void invokeBalanceOrder(PayOrder order, PayOrderInfoParam infoParam) {
        // 余额只处理分期订单，此种订单只出现在余额分期优惠券上
        if (order.getPayDiscountDetailId() != null && order.getPayDiscountDetailId() > 0) {
            PayDiscountDetail payDiscountDetail = this.payDiscountDetailService.findById(order.getPayDiscountDetailId());
            if (null == payDiscountDetail) {//优惠券不存在，抛出异常
                throw new CheckPayOrderParamException("余额录入订单>>>优惠券不存在");
            }
            if (Objects.equals(DiscountType.FQ.getState(), payDiscountDetail.getDiscountType())) { // 余额分期优惠券
                List<PayInfoParam> subPayInfo = infoParam.getSubPayInfo();
                // 优惠券优惠策略
                FQPeriodDTO fqPeriodDTO = JSON.parseObject(payDiscountDetail.getPeriod(), FQPeriodDTO.class);
                // 优惠券策略
                List<FQShuJuDTO> fqShuJuDTOList = fqPeriodDTO.getShuju();

                /**
                 * 分两笔
                 *  第一笔 余额支付
                 *  第二笔 待支付
                 *
                 * */
                // 分期优惠券中第一期支付的金额(使用的余额)
                Double oldBalance = fqShuJuDTOList.get(0).getPeriodMoney();
                // 后台传递的待支付的金额
                Double inputWaitPayPrice = subPayInfo.get(1).getPrice();

                PayOrder firstPayOrder = this.builderBalanceChildOrder(order);
                firstPayOrder.setPayNum(getChildOrderNum(order.getPayNum(), 1));// 订单编号
                firstPayOrder.setPayStatus(order.getPayStatus());
                firstPayOrder.setPayType(subPayInfo.get(0).getPayType());// pay_type_balance
                firstPayOrder.setRealpayPrice(oldBalance);//余额
                firstPayOrder.setCostPrice(0d);
                firstPayOrder.setPayTime(order.getPayTime());
                firstPayOrder.setOrderParentId(order.getId());// 父订单ID
                firstPayOrder.setOrderInstal(1);// 第一单
                firstPayOrder.setOldBalance(oldBalance);
                payOrderService.save(firstPayOrder);// 第一单子订单 更新信息

                PayOrder secondPayOrder = this.builderBalanceChildOrder(order);
                secondPayOrder.setPayNum(getChildOrderNum(order.getPayNum(), 2));// 订单编号
                secondPayOrder.setPayStatus(PayStatus.PAY_STATUS_NON.getState());
                secondPayOrder.setRealpayPrice(inputWaitPayPrice);
                secondPayOrder.setCostPrice(inputWaitPayPrice);
                try {
                    secondPayOrder.setDeadline(new SimpleDateFormat("yyyy-MM-dd").parse(fqShuJuDTOList.get(1).getPeriodDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                secondPayOrder.setOrderParentId(order.getId());
                secondPayOrder.setOrderInstal(2);
                payOrderService.save(secondPayOrder);// 第二单子订单 更新信息

            }
        }
    }

    /**
     * 余额录入订单model
     */
    private PayOrder builderBalanceChildOrder(PayOrder payOrder) {
        PayOrder childOrder = new PayOrder();
        childOrder.setUserId(payOrder.getUserId());
        childOrder.setOrderType(OrderEnum.ORDER_TYPE_SUB.getKey());
        childOrder.setOrderTime(payOrder.getOrderTime());
        childOrder.setBuyMode(payOrder.getBuyMode());
        childOrder.setPromotion(payOrder.getPromotion());
        childOrder.setPromotionId(payOrder.getPromotionId());
        childOrder.setProgramName(payOrder.getProgramName());
        childOrder.setProgramId(payOrder.getProgramId());
        childOrder.setCourseType(payOrder.getCourseType());
        childOrder.setCategoryName(payOrder.getCategoryName());
        childOrder.setCategoryId(payOrder.getCategoryId());
        childOrder.setCreator(payOrder.getCreator());
        childOrder.setRemark(payOrder.getRemark());
        childOrder.setIsOldUser(payOrder.getIsOldUser());
        childOrder.setBenefitPrice(payOrder.getBenefitPrice());
        childOrder.setType(payOrder.getType());
        childOrder.setCreateTime(payOrder.getCreateTime());
        childOrder.setPhone(payOrder.getPhone());
        childOrder.setQq(payOrder.getQq());
        childOrder.setBankType(payOrder.getBankType());
        childOrder.setPayDiscountDetailId(payOrder.getPayDiscountDetailId());
        childOrder.setTeacherid(payOrder.getTeacherid());
        childOrder.setTeachername(payOrder.getTeachername());
        childOrder.setVoucher(payOrder.getVoucher());
        childOrder.setAuditStatus(payOrder.getAuditStatus());
        childOrder.setDeleteMark(payOrder.getDeleteMark());
        childOrder.setOrderBatch(payOrder.getOrderBatch());
        childOrder.setSource(payOrder.getSource());
        childOrder.setOs(payOrder.getOs());
        childOrder.setProductId(payOrder.getProductId());
        childOrder.setIsTurnOrder(payOrder.getIsTurnOrder());
        childOrder.setIsOld(payOrder.getIsOld());
        childOrder.setIsMail(payOrder.getIsMail());
        return childOrder;
    }

    /**
     * 插入后台订单-子单
     */
    protected void insertNBackChildOrder(PayOrder payOrder, List<PayInfoParam> subPayInfoParams) {
        if (null != subPayInfoParams && !subPayInfoParams.isEmpty()) {
            int size = subPayInfoParams.size();
            for (int i = 0; i < size; i++) {
                PayInfoParam subPayInfo = subPayInfoParams.get(i);

                PayOrder childOrder = payOrderService.generateChildOrder(payOrder, subPayInfo.getPrice());
                childOrder.setCreator(payOrder.getCreator());
                childOrder.setOrderTime(DateUtils.format(subPayInfo.getOrderTime()));// 下单时间
                childOrder.setPayTime(DateUtils.format(subPayInfo.getPayTime()));// 支付时间
                childOrder.setPayNum(getChildOrderNum(payOrder.getPayNum(), i + 1));// 子订单订单号
                childOrder.setPayStatus(PayStatus.PAY_STATUS_SUCCESS.getState());// 支付状态
                childOrder.setPayType(subPayInfo.getPayType());// 支付类型
                childOrder.setOrderNum(subPayInfo.getOrderNum());// 支付凭证
                childOrder.setVoucher(subPayInfo.getVoucher());// 支付昵称
                childOrder.setStudyPackagePrice(null);
                childOrder.setDuration(subPayInfo.getDuration());// 期数
                childOrder.setRate(subPayInfo.getRate());// 分期
                childOrder.setOrderParentId(payOrder.getId());
                childOrder.setOrderMulti(i + 1);
                childOrder.setOrderInstal(payOrder.getOrderInstal());
                childOrder.setProductId(payOrder.getProductId());
                childOrder.setIsMail(payOrder.getIsMail());
                payOrderService.save(childOrder);
            }
        }
    }

    /**
     * 获取子订单订单号
     *
     * @param payNum
     * @param idx
     */
    protected String getChildOrderNum(String payNum, Integer idx) {
        StringBuilder sb = new StringBuilder(payNum);
        if (idx < 10) {
            sb.append("-0");
        } else {
            sb.append("-");
        }
        return sb.append(idx).toString();
    }

}
