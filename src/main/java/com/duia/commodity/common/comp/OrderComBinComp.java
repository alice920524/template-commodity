package com.duia.commodity.common.comp;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.dto.CommodityDTO;
import com.duia.commodity.common.enums.ClassTypeEnum;
import com.duia.commodity.model.CommoditySpecial;
import com.duia.enums.Benefit;
import com.duia.security.param.PayOrderCommodityParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 组合对象
 * <p>
 * Created by 李国勇 on 2018/9/1.
 */
public class OrderComBinComp extends OrderAbComp {

    private final Object o;
    private final CommoditySpecial special;
    private final List<CommodityDTO> commodityDTOS;

    public OrderComBinComp(Object o, CommoditySpecial special, List<CommodityDTO> commodityDTOS) {
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
        return new BigDecimal(this.getCommodityTotalPrice(commodityDTOS)).subtract(new BigDecimal(this.discountPrice(special, commodityDTOS))).doubleValue();
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

    /**
     * 组合满减策略
     */
    private Double discountPrice(CommoditySpecial special, List<CommodityDTO> commodityDTOList) {
        if (null == special.getDiscountPrice() || "".equals(special.getDiscountPrice())) {
            return 0d;
        }
        Map<String, String> price = JSON.parseObject(special.getDiscountPrice(), Map.class);
        int size = commodityDTOList.size();
        if (null == price || !price.containsKey(size + "")) {
            return 0d;
        }
        return Double.valueOf(price.get(size + ""));
    }
}
