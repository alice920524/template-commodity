package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "voucher_detail")
public class VoucherDetail {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * sku
     */
    private Integer sku;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 使用订单id
     */
    @Column(name = "use_order_id")
    private Integer useOrderId;

    /**
     * 优惠特权id
     */
    @Column(name = "voucher_id")
    private Integer voucherId;

    /**
     * 优惠特权金额
     */
    private Double price;

    /**
     * 有效期-起始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 有效期-结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 状态[0:未使用,1:已使用]
     */
    private Integer status;

    /**
     * 创建者
     */
    private Integer creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改者
     */
    private Integer updator;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除状态[0:未删除,1:已删除]
     */
    private Integer deleted;

    /**
     * 描述
     */
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取PK
     *
     * @return id - PK
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置PK
     *
     * @param id PK
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取sku
     *
     * @return sku - sku
     */
    public Integer getSku() {
        return sku;
    }

    /**
     * 设置sku
     *
     * @param sku sku
     */
    public void setSku(Integer sku) {
        this.sku = sku;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取使用订单id
     *
     * @return use_order_id - 使用订单id
     */
    public Integer getUseOrderId() {
        return useOrderId;
    }

    /**
     * 设置使用订单id
     *
     * @param useOrderId 使用订单id
     */
    public void setUseOrderId(Integer useOrderId) {
        this.useOrderId = useOrderId;
    }

    /**
     * 获取优惠特权id
     *
     * @return voucher_id - 优惠特权id
     */
    public Integer getVoucherId() {
        return voucherId;
    }

    /**
     * 设置优惠特权id
     *
     * @param voucherId 优惠特权id
     */
    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    /**
     * 获取优惠特权金额
     *
     * @return price - 优惠特权金额
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置优惠特权金额
     *
     * @param price 优惠特权金额
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取有效期-起始时间
     *
     * @return start_date - 有效期-起始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置有效期-起始时间
     *
     * @param startDate 有效期-起始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取有效期-结束时间
     *
     * @return end_date - 有效期-结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置有效期-结束时间
     *
     * @param endDate 有效期-结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取状态[0:未使用,1:已使用]
     *
     * @return status - 状态[0:未使用,1:已使用]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态[0:未使用,1:已使用]
     *
     * @param status 状态[0:未使用,1:已使用]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改者
     *
     * @return updator - 最后修改者
     */
    public Integer getUpdator() {
        return updator;
    }

    /**
     * 设置最后修改者
     *
     * @param updator 最后修改者
     */
    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    /**
     * 获取最后修改时间
     *
     * @return update_time - 最后修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取删除状态[0:未删除,1:已删除]
     *
     * @return deleted - 删除状态[0:未删除,1:已删除]
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除状态[0:未删除,1:已删除]
     *
     * @param deleted 删除状态[0:未删除,1:已删除]
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}