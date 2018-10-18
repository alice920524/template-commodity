package com.duia.commodity.service.impl;

import com.duia.commodity.common.Constant;
import com.duia.commodity.common.comp.OrderAbComp;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.common.enums.CommodityPromotionEnum;
import com.duia.commodity.common.enums.OrderEnum;
import com.duia.commodity.common.util.OrderPriceUtil;
import com.duia.commodity.model.*;
import com.duia.commodity.service.CommodityPromotionService;
import com.duia.commodity.service.PayOrderProxyService;
import com.duia.enums.PayType;
import com.duia.enums.Sales;
import com.duia.security.param.CommodityParam;
import com.duia.security.param.PayOrderCommodityParam;
import com.duia.security.param.PayOrderInfoParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Created by 李国勇 on 2018/6/11.
 */
@Service("payOrderFrontSGProxyServiceImpl")
public class PayOrderFrontSGProxyServiceImpl extends PayOrderFrontProxyServiceImpl implements PayOrderProxyService {

    private static Logger logger = LoggerFactory.getLogger(PayOrderFrontSGProxyServiceImpl.class);

    @Autowired
    private CommodityPromotionService commodityPromotionService;

    @Override
    void extra(PayOrder order, PayOrderInfoParam pi, Object op) {

    }

    @Override
    CommodityPromotion promotionOrder(PayOrder order, Object o) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String appType = request.getParameter("appType");
        if (StringUtils.isBlank(appType) || "null".equals(appType)) {
            appType = Constant.WEB_TYPE.toString();
        }

        PayOrderCommodityParam c = (PayOrderCommodityParam) o;

        CommodityParam param = c.getCommodity();
        CommodityPromotion commodityPromotion = null;

        logger.info("================================{}===" + c.getPromotion() + "==========================================", order.getUserId());
        // 活动ID
        if (null != c.getPromotionId()) {
            commodityPromotion = this.commodityPromotionService.findById(c.getPromotionId());
        // 分享购   1:老数据  4:活动表中type类型
        } else if (Objects.equals(c.getPromotion(), 1) || Objects.equals(c.getPromotion(), 4)) {
            commodityPromotion = commodityPromotionService.findOnLinePromotion(param.getComId(), Integer.valueOf(appType));
        } else {
            // 专题课抢购
            Commodity commodity = this.commodityService.findById(param.getComId());
            if (null != commodity && ClassTypeEnum.COURSE_TYPE_SPECIAL.getKey().equals(commodity.getCourseType())) {
                commodityPromotion = commodityPromotionService.selectLiveCommodityPromotion(param.getComId(), Sales.PANIC.getState(), Integer.valueOf(appType));
            }
        }

        /**
         * 不参与活动
         *  1、没有查到活动数据。
         *  2、抢购模式,指定的质保期类型没有参与活动。
         * */
        if (null == commodityPromotion
                || (CommodityPromotionEnum.TYPE_RUSH.getKey().equals(commodityPromotion.getType()) && !this.commodityPromotionService.isOpenActivity(param.getGuaType(), commodityPromotion))) {
            return null;
        }

        order.setPromotion(commodityPromotion.getType());
        order.setPromotionId(commodityPromotion.getId());

        return commodityPromotion;
    }

    @Override
    boolean isOldUser(PayOrder order, Object o) {
        OrderAbComp abComp = (OrderAbComp) o;
        if (Objects.equals(ClassTypeEnum.COURSE_TYPE_SPECIAL.getKey(), abComp.getCourseType())) {
            return false;
        }
        return super.isOldUser(order, o);
    }

    @Override
    void singleCommodityExtra(PayOrder order, CommodityPromotion cp, List<CommodityDTO> commodityDTOList) {
        CommodityDTO commodityDTO = commodityDTOList.get(0);
        // 分享购、抢购
        if (null != order.getPromotion() && null != cp) {
            // 抢购
            if (CommodityPromotionEnum.TYPE_RUSH.getKey().equals(order.getPromotion())) {
                // 免费
                if (ClassTypeEnum.CHARGE_NO.getKey().equals(cp.getCharge())) {
                    order.setPayType(PayType.PAY_STATUS_FREE.getState());// 支付类型
                    commodityDTO.setComCostPrice(0.0);
                } else {
                    // 抢购价(普通、一类、二类)
                    commodityDTO.setComCostPrice(OrderPriceUtil.getCommodityPrice(cp,commodityDTO.getGuaType()));
                }
                // 分享购
            } else if (CommodityPromotionEnum.TYPE_SHARE.getKey().equals(order.getPromotion())) {
                commodityDTO.setComCostPrice(cp.getCostPrice());
                order.setBuyMode(OrderEnum.BUY_MODE_SHARE.getKey());// 分享购
            }
        }
    }

    /**
     * 优惠券扩展处理:零元购优惠券
     *
     * @param order
     * @param o
     * @param discountDetail
     */
    @Override
    void payDiscountPriceExtra(PayOrder order, Object o, PayDiscountDetail discountDetail) {
        OrderAbComp abComp = (OrderAbComp) o;
        ClassType classType = abComp.commoditys().get(0).getClassType();
        // 零元购商品、零元购优惠券
        if (Objects.equals(classType.getHasLimit(), Constant.DISCOUNT_USE_ZERO_PAY)
                && Objects.equals(Constant.DISCOUNT_TYPE_ZERO, discountDetail.getDiscountType())) {
            order.setPayType(Constant.PAY_TYPE_ZEROPAY); // 设置零元购支付方式
            order.setBuyMode(OrderEnum.BUY_MODE_ZERO.getKey());// 零元购
        }
    }

    @Override
    void _saveOrder(PayOrder order) {
        if (CommodityPromotionEnum.TYPE_RUSH.getKey().equals(order.getPromotion())) {
            commodityPromotionService.rushCommodityAddSales(order.getId(), order.getPromotionId());
        }
    }

    @Override
    void setCostPrice(PayOrder order, OrderAbComp o, double coursePrice, double discountPrice) {
        // 零元购 原价0元
        if (OrderEnum.BUY_MODE_ZERO.getKey().equals(order.getBuyMode())) {
            order.setCostPrice(0.0);
            // 抢购,免费
        } else if (CommodityPromotionEnum.TYPE_RUSH.getKey().equals(order.getPromotion())
                && PayType.PAY_STATUS_FREE.getState().equals(order.getPayType())) {
            order.setCostPrice(0.0);
        } else {
            // 调用通用计算价格方法
            super.setCostPrice(order, o, coursePrice, discountPrice);
        }
    }
}
