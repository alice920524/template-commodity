package com.duia.commodity.common.comp;

import com.duia.commodity.common.dto.CommodityDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 抽象类，提供通用方法
 * <p>
 * Created by 李国勇 on 2018/9/1.
 */
public abstract class OrderAbComp {

    /**
     * 获取sku
     *
     * @return
     */
    public Integer getSku() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取课程类型
     *
     * @return
     */
    public Integer getCourseType() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取产品id
     *
     * @return
     */
    public Long getProductId() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取产品名称
     *
     * @return
     */
    public String getProductName() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取产品价格
     *
     * @return
     */
    public double getPrice() {
        throw new UnsupportedOperationException();
    }

//    /**
//     * 获取邮寄地址标记
//     *
//     * @return
//     */
//    public Integer getAddressMark() {
//        throw new UnsupportedOperationException();
//    }
//
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
        throw new UnsupportedOperationException();
    }

    /**
     * 获取优惠券id
     *
     * @return
     */
    public String getDiscountId() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取特权id
     *
     * @return
     */
    public Long getVoucherDetailId() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取商品列表集合
     *
     * @return
     */
    public List<CommodityDTO> commoditys() {
        throw new UnsupportedOperationException();
    }

    /**
     * 累计商品总价格
     *
     * @param commodityDTOList
     * @return
     */
    protected Double getCommodityTotalPrice(List<CommodityDTO> commodityDTOList) {
        BigDecimal commodityPrice = new BigDecimal(0);
        if (null != commodityDTOList) {
            for (CommodityDTO commodityDTO : commodityDTOList) {
                commodityPrice = commodityPrice.add(new BigDecimal(commodityDTO.getComCostPrice()));
            }
        }
        return commodityPrice.doubleValue();
    }
}
