package com.duia.commodity.common.dto;

import com.duia.commodity.model.PayOrder;

import java.util.Date;
import java.util.List;

public class OrderCreateSuccessDTO {
    private List<String> commodityNames;
    private Double costPrice;
    private String payNum;
    private String orderNum;
    private Long orderId;
    private Long sku;
    private Long classId;
    private boolean paid;
    private String payType;
    private Date payTime;

    public OrderCreateSuccessDTO() {
    }

    public OrderCreateSuccessDTO(PayOrder payOrder) {
        setCostPrice(payOrder.getCostPrice());
        setPayNum(payOrder.getPayNum());
        setOrderNum(payOrder.getOrderNum());
        setOrderId(payOrder.getId());
        setSku(payOrder.getCategoryId().longValue());
        setPayType(payOrder.getPayType());
    }

    public List<String> getCommodityNames() {
        return commodityNames;
    }

    public void setCommodityNames(List<String> commodityNames) {
        this.commodityNames = commodityNames;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
