package com.duia.commodity.common.comp;

import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.model.ClassType;
import com.duia.enums.Benefit;
import com.duia.security.param.PayOrderCommodityParam;

import java.util.List;
import java.util.Objects;

/**
 * 单品对象
 * <p>
 * Created by 李国勇 on 2018/9/1.
 */
public class OrderSingleComp extends OrderAbComp {

    private final Object o;
    private final CommodityDTO commodityDTO;
    private final ClassType classType;
    private final List<CommodityDTO> commodityDTOS;

    public OrderSingleComp(Object o, List<CommodityDTO> commodityDTOS) {
        this.o = o;
        this.commodityDTO = commodityDTOS.get(0);
        this.classType = commodityDTO.getClassType();
        this.commodityDTOS = commodityDTOS;
    }


    /**
     * 获取sku
     *
     * @return
     */
    public Integer getSku() {
        return classType.getSku();
    }

    /**
     * 获取课程类型
     *
     * @return
     */
    public Integer getCourseType() {
        return classType.getCourseType();
    }

    /**
     * 获取产品id
     *
     * @return
     */
    public Long getProductId() {
        return commodityDTO.getId();
    }

    /**
     * 获取产品名称
     *
     * @return
     */
    public String getProductName() {
        return commodityDTO.getName();
    }

    /**
     * 获取产品价格
     *
     * @return
     */
    public double getPrice() {
        return commodityDTO.getComCostPrice();
    }
//
//    /**
//     * 获取邮寄地址标记
//     *
//     * @return
//     */
//    public Integer getAddressMark() {
//        return special.get
//    }

//    /**
//     * 获取图书价格
//     *
//     * @return
//     */
//    public double getBookPrice() {
//        throw new UnsupportedOperationException();
//    }

    /**
     * 获取老生优惠金额
     *
     * @return
     */
    public Integer getBenefitPrice() {
        return (Objects.equals(Benefit.VALID.getState(), classType.getBenefitMark())) ? classType.getBenefitPrice() : 0;
    }

    /**
     * 获取优惠券id
     *
     * @return
     */
    public String getDiscountId() {
        return ((PayOrderCommodityParam) o).getDiscountId();
    }

    /**
     * 获取特权id
     *
     * @return
     */
    public Long getVoucherDetailId() {
        return ((PayOrderCommodityParam) o).getVoucherDetailId();
    }

    /**
     * 获取商品列表集合
     *
     * @return
     */
    public List<CommodityDTO> commoditys() {
        return commodityDTOS;
    }
}
