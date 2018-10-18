package com.duia.commodity.common.dto;


public class CommodityResultDTO {
    // 类型 0:单品 1:套餐 2:组合
    private Integer type;
    // 老生优惠价格
    private Integer oldStudentSalePrice;
    // 是否显示优惠券
    private Integer discount;
    // sku
    private Long sku;
    // 单品
    private CommodityBaseDTO commodity;
    // 套餐/组合
    private CommoditySpecialBaseDTO commoditySpecial;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public CommoditySpecialBaseDTO getCommoditySpecial() {
        return commoditySpecial;
    }

    public void setCommoditySpecial(CommoditySpecialBaseDTO commoditySpecial) {
        this.commoditySpecial = commoditySpecial;
    }

    public Integer getOldStudentSalePrice() {
        return oldStudentSalePrice;
    }

    public void setOldStudentSalePrice(Integer oldStudentSalePrice) {
        this.oldStudentSalePrice = oldStudentSalePrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public CommodityBaseDTO getCommodity() {
        return commodity;
    }

    public void setCommodity(CommodityBaseDTO commodity) {
        this.commodity = commodity;
    }
}
