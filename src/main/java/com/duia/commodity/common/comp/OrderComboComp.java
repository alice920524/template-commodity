package com.duia.commodity.common.comp;

import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.model.CommoditySpecial;
import com.duia.enums.Benefit;
import com.duia.security.param.PayOrderCommodityParam;

import java.util.List;
import java.util.Objects;

/**
 * 套餐对象
 * <p>
 * Created by 李国勇 on 2018/9/1.
 */
public class OrderComboComp extends OrderAbComp {

    private final Object o;
    private final CommoditySpecial special;
    private final List<CommodityDTO> commodityDTOS;


    public OrderComboComp(Object o, CommoditySpecial special, List<CommodityDTO> commodityDTOS) {
        this.o = o;
        this.special = special;
        this.commodityDTOS = commodityDTOS;
    }


    /**
     * 获取sku
     *
     * @return
     */
    public Integer getSku() {
        return special.getSkuId();
    }

    /**
     * 获取课程类型
     *
     * @return
     */
    public Integer getCourseType() {
        return ClassTypeEnum.COURSE_TYPE_SYSTEM.getKey();
    }

    /**
     * 获取产品id
     *
     * @return
     */
    public Long getProductId() {
        return special.getId();
    }

    /**
     * 获取产品名称
     *
     * @return
     */
    public String getProductName() {
        return special.getName();
    }

    /**
     * 获取产品价格
     *
     * @return
     */
    public double getPrice() {
        return special.getRealPrice();
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
        return (Objects.equals(Benefit.VALID.getState(), special.getBenefitMark())) ? special.getBenefitPrice() : 0;
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
