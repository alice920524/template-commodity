package com.duia.commodity.common.dto;

import java.util.Collection;

// 支付提示
public class PayTipsDTO {

    /**
     * 提示信息
     * */
    private Collection tips;
    /**
     * 状态
     * */
    private Integer status;

    /**
     * 排序字段
     * */
    private Integer order;

    /**
     * 商品数量
     * */
    private Integer comNumber;


    public PayTipsDTO() {
    }

    public PayTipsDTO(Collection tips, Integer status, Integer order) {
        this.tips = tips;
        this.status = status;
        this.order = order;
    }

    public PayTipsDTO(Collection tips, Integer status, Integer order, Integer comNumber) {
        this.tips = tips;
        this.status = status;
        this.order = order;
        this.comNumber = comNumber;
    }

    public Collection getTips() {
        return tips;
    }

    public void setTips(Collection tips) {
        this.tips = tips;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getComNumber() {
        return comNumber;
    }

    public void setComNumber(Integer comNumber) {
        this.comNumber = comNumber;
    }
}
