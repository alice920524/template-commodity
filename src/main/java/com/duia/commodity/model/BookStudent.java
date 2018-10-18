package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "book_student")
public class BookStudent {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 商品id
     */
    @Column(name = "com_id")
    private Integer comId;

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
     * 删除标识位[0:可用,1:删除]
     */
    private Integer deleted;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
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
     * 获取商品id
     *
     * @return com_id - 商品id
     */
    public Integer getComId() {
        return comId;
    }

    /**
     * 设置商品id
     *
     * @param comId 商品id
     */
    public void setComId(Integer comId) {
        this.comId = comId;
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
     * 获取删除标识位[0:可用,1:删除]
     *
     * @return deleted - 删除标识位[0:可用,1:删除]
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识位[0:可用,1:删除]
     *
     * @param deleted 删除标识位[0:可用,1:删除]
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}