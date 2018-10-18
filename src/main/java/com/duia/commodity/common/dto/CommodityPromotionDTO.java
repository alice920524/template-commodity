package com.duia.commodity.common.dto;

import java.util.Date;

public class CommodityPromotionDTO {

    private Long id;

    private Date startDate;// 开始时间

    private Date endDate;// 结束时间

    private Integer maximum;// 数量上限

    private Integer sales;// 销量
    private Integer charge;// 收费类型

    private Date serverDate;// 服务器时间

    private Integer rushType;// 抢购类型
    private Double costPrice;// 活动价格

    private Integer status;// 活动状态
    private Double firstPrice;// 一类质保期活动价
    private Double secondPrice;// 一类质保期活动价
    private Integer guaMul;
    private Integer firstPro;//一类质保期是否参与活动[0:不参与,1:参与]
    private Integer secondPro;//二类质保期是否参与活动[0:不参与,1:参与]
    private Integer courseType;//课程类型

    private Integer type;//活动类型

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Integer getFirstPro() {
        return firstPro;
    }

    public void setFirstPro(Integer firstPro) {
        this.firstPro = firstPro;
    }

    public Integer getSecondPro() {
        return secondPro;
    }

    public void setSecondPro(Integer secondPro) {
        this.secondPro = secondPro;
    }

    public Integer getGuaMul() {
        return guaMul;
    }

    public void setGuaMul(Integer guaMul) {
        this.guaMul = guaMul;
    }

    public Double getFirstPrice() {
        return firstPrice;
    }

    public Double getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Double secondPrice) {
        this.secondPrice = secondPrice;
    }

    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
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

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Date getServerDate() {
        return serverDate;
    }

    public void setServerDate(Date serverDate) {
        this.serverDate = serverDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Integer getRushType() {
        return rushType;
    }

    public void setRushType(Integer rushType) {
        this.rushType = rushType;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }
}
