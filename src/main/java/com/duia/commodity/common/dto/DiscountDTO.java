package com.duia.commodity.common.dto;

import java.util.Date;

public class DiscountDTO {
    private String discountDetailId;
    // 优惠券码
    private String code;
    // 优惠券额度
    private Double price;
    // 优惠券类型
    private String type;
    // 开始时间
    private Date startDate;
    // 结束时间
    private Date endDate;
    // 创建时间
    private Date createDate;
    // SKU ID
    private Integer skuId;
    // SKU名称
    private String skuName;
    // 商品ID
    private Long comId;
    // 商品名称
    private String comName;
    // 课程类型[-1:通用,0:系统班,1:专题课]
    private Integer courseType;
    // 不可用类型
    private Integer unAvailableType;

    public String getDiscountDetailId() {
        return discountDetailId;
    }

    public void setDiscountDetailId(String discountDetailId) {
        this.discountDetailId = discountDetailId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Integer getUnAvailableType() {
        return unAvailableType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUnAvailableType(Integer unAvailableType) {
        this.unAvailableType = unAvailableType;
    }
}
