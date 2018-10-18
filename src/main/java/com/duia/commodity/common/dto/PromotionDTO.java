package com.duia.commodity.common.dto;

/**
 * @Description:分享购
 * @Date: 14:00 2018/5/14
 */
public class PromotionDTO {
    // 分享购价格
    private Double costPrice;
    // 分享购状态
    private boolean status;

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
