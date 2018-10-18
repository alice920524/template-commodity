package com.duia.commodity.common.dto;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.model.CommodityPromotion;
import com.duia.commodity.model.PayOrderMailingAddress;

/**
 * Created by 李恒名 on 2017/7/19.
 */
public class OrderParamDTO {
    private Long comId;
    private Integer comType;
    private String comIds;
    private Long specialId;
    private Long upgradeId;
    private Integer orderType;
    private Boolean selectStudyPack;
    private String selectInsuranceIds;
    private PayOrderMailingAddress address;
    private String qq;
    private String answer;
    private String discountId;
    private String saleCommodityIds;
    private Integer promotion;
    private CommodityPromotion commodityPromotion;
    private String source;
    private String remark;
    public OrderParamDTO() {
    }

    public OrderParamDTO(String source,String qq,String remark) {
        setSource(source);
        setQq(qq);
        setRemark(remark);
    }

    public OrderParamDTO(Integer comType, Long specialId) {
        setComType(comType);
        setSpecialId(specialId);
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public Integer getComType() {
        return comType;
    }

    public void setComType(Integer comType) {
        this.comType = comType;
    }

    public String getComIds() {
        return comIds;
    }

    public void setComIds(String comIds) {
        this.comIds = comIds;
    }

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }

    public Long getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(Long upgradeId) {
        this.upgradeId = upgradeId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Boolean getSelectStudyPack() {
        return selectStudyPack;
    }

    public void setSelectStudyPack(Boolean selectStudyPack) {
        this.selectStudyPack = selectStudyPack;
    }

    public String getSelectInsuranceIds() {
        return selectInsuranceIds;
    }

    public void setSelectInsuranceIds(String selectInsuranceIds) {
        this.selectInsuranceIds = selectInsuranceIds;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getSaleCommodityIds() {
        return saleCommodityIds;
    }

    public void setSaleCommodityIds(String saleCommodityIds) {
        this.saleCommodityIds = saleCommodityIds;
    }

    public PayOrderMailingAddress getAddress() {
        return address;
    }

    public void setAddress(PayOrderMailingAddress address) {
        this.address = address;
    }

    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    public CommodityPromotion getCommodityPromotion() {
        return commodityPromotion;
    }

    public void setCommodityPromotion(CommodityPromotion commodityPromotion) {
        this.commodityPromotion = commodityPromotion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
