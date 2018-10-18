package com.duia.commodity.common.dto;

import java.util.List;

public class CommoditySpecialBaseDTO {
    // 套餐/组合 ID
    private Long id;
    // 套餐/组合名称
    private String name;
    // 类型 1:套餐 2:组合
    private Integer type;
    // 价格
    private Double costPrice;
    // 原价
    private Double realpayPrice;
    // 组合优惠策略
    private String discountPrice;
    // 学习包 1:没有  2:有
    private Integer hasStudyPack;
    // 套餐/组合 商品
    private List<CommodityBaseDTO> comList;

    public CommoditySpecialBaseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getRealpayPrice() {
        return realpayPrice;
    }

    public void setRealpayPrice(Double realpayPrice) {
        this.realpayPrice = realpayPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getHasStudyPack() {
        return hasStudyPack;
    }

    public void setHasStudyPack(Integer hasStudyPack) {
        this.hasStudyPack = hasStudyPack;
    }

    public List<CommodityBaseDTO> getComList() {
        return comList;
    }

    public void setComList(List<CommodityBaseDTO> comList) {
        this.comList = comList;
    }
}
