package com.duia.commodity.common.dto;

public class CommodityExtraDTO extends CommodityBaseDTO{
    // 加价购价格
    private Double extraPrice;
    // 加价购与商品的关联id
    private Long extraRId;

    private Integer skuId;

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Long getExtraRId() {
        return extraRId;
    }

    public void setExtraRId(Long extraRId) {
        this.extraRId = extraRId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
}
