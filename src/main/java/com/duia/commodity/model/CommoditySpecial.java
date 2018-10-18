package com.duia.commodity.model;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "commodity_special")
public class CommoditySpecial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * SKU
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 特殊商品名称
     */
    private String name;

    /**
     * 原始价格
     */
    @Column(name = "cost_price")
    private Double costPrice;

    /**
     * 真实价格
     */
    @Column(name = "real_price")
    private Double realPrice;

    /**
     * 1：优惠套装；2：推荐组合
     */
    private Integer type;

    /**
     * 上架时间
     */
    @Column(name = "shelf_on_time")
    private Date shelfOnTime;

    /**
     * 下架时间
     */
    @Column(name = "shelf_off_time")
    private Date shelfOffTime;

    /**
     * 上下架状态
     */
    private Integer status;

    /**
     * 1：优惠；2：不优惠
     */
    @Column(name = "is_discount")
    private Integer isDiscount;

    /**
     * 优惠价格
     */
    @Column(name = "discount_price")
    private String discountPrice;

    /**
     * 变更标识位
     */
    @Column(name = "is_change")
    private Integer isChange;

    /**
     * 备注
     */
    private String description;

    private Long creator;

    @Column(name = "create_time")
    private Date createTime;

    private Long updator;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 1是老生优惠，2是没有优惠
     */
    @Column(name = "benefit_mark")
    private Integer benefitMark;

    /**
     * 老生优惠价格
     */
    @Column(name = "benefit_price")
    private Integer benefitPrice;

    /**
     * 是否有上限[0无1有]
     */
    @Column(name = "has_limit")
    private Integer	hasLimit;

    /**
     * 允许用优惠券的最大金额
     */
    @Column(name = "limit_amount")
    private Integer	limitAmount;

    @Column(name="coupon")
    private  Integer coupon;
    /**
     * 优惠券选择[0:不可用,1:可用]
     * */
    @Column(name = "voucher_able")
    private Integer voucherAble;


    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取SKU
     *
     * @return sku_id - SKU
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置SKU
     *
     * @param skuId SKU
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取特殊商品名称
     *
     * @return name - 特殊商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置特殊商品名称
     *
     * @param name 特殊商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取原始价格
     *
     * @return cost_price - 原始价格
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * 设置原始价格
     *
     * @param costPrice 原始价格
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 获取真实价格
     *
     * @return real_price - 真实价格
     */
    public Double getRealPrice() {
        return realPrice;
    }

    /**
     * 设置真实价格
     *
     * @param realPrice 真实价格
     */
    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * 获取1：优惠套装；2：推荐组合
     *
     * @return type - 1：优惠套装；2：推荐组合
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1：优惠套装；2：推荐组合
     *
     * @param type 1：优惠套装；2：推荐组合
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取上架时间
     *
     * @return shelf_on_time - 上架时间
     */
    public Date getShelfOnTime() {
        return shelfOnTime;
    }

    /**
     * 设置上架时间
     *
     * @param shelfOnTime 上架时间
     */
    public void setShelfOnTime(Date shelfOnTime) {
        this.shelfOnTime = shelfOnTime;
    }

    /**
     * 获取下架时间
     *
     * @return shelf_off_time - 下架时间
     */
    public Date getShelfOffTime() {
        return shelfOffTime;
    }

    /**
     * 设置下架时间
     *
     * @param shelfOffTime 下架时间
     */
    public void setShelfOffTime(Date shelfOffTime) {
        this.shelfOffTime = shelfOffTime;
    }

    /**
     * 获取上下架状态
     *
     * @return status - 上下架状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置上下架状态
     *
     * @param status 上下架状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    /**
     * 获取优惠价格
     *
     * @return discount_price - 优惠价格
     */
    public String getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 设置优惠价格
     *
     * @param discountPrice 优惠价格
     */
    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 获取变更标识位
     *
     * @return is_change - 变更标识位
     */
    public Integer getIsChange() {
        return isChange;
    }

    /**
     * 设置变更标识位
     *
     * @param isChange 变更标识位
     */
    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    /**
     * 获取备注
     *
     * @return description - 备注
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注
     *
     * @param description 备注
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return updator
     */
    public Long getUpdator() {
        return updator;
    }

    /**
     * @param updator
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取1是老生优惠，2是没有优惠
     *
     * @return benefit_mark - 1是老生优惠，2是没有优惠
     */
    public Integer getBenefitMark() {
        return benefitMark;
    }

    /**
     * 设置1是老生优惠，2是没有优惠
     *
     * @param benefitMark 1是老生优惠，2是没有优惠
     */
    public void setBenefitMark(Integer benefitMark) {
        this.benefitMark = benefitMark;
    }

    /**
     * 获取老生优惠价格
     *
     * @return benefit_price - 老生优惠价格
     */
    public Integer getBenefitPrice() {
        return benefitPrice;
    }

    /**
     * 设置老生优惠价格
     *
     * @param benefitPrice 老生优惠价格
     */
    public void setBenefitPrice(Integer benefitPrice) {
        this.benefitPrice = benefitPrice;
    }

    public Integer getHasLimit() {
        return hasLimit;
    }

    public void setHasLimit(Integer hasLimit) {
        this.hasLimit = hasLimit;
    }

    public Integer getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Integer limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public Integer getVoucherAble() {
        return voucherAble;
    }

    public void setVoucherAble(Integer voucherAble) {
        this.voucherAble = voucherAble;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}