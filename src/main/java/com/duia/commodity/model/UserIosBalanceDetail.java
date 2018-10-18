package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_ios_balance_detail")
public class UserIosBalanceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 金额
     */
    private Double money;

    /**
     * 支付类型：1，充值（加）  2，支付（减）
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 充值时记录ios票据，支付时记录流水号
     */
    @Column(name = "pay_num")
    private String payNum;

    @Column(name = "pay_status")
    private String payStatus;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取支付类型：1，充值（加）  2，支付（减）
     *
     * @return pay_type - 支付类型：1，充值（加）  2，支付（减）
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置支付类型：1，充值（加）  2，支付（减）
     *
     * @param payType 支付类型：1，充值（加）  2，支付（减）
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 获取充值时记录ios票据，支付时记录流水号
     *
     * @return pay_num - 充值时记录ios票据，支付时记录流水号
     */
    public String getPayNum() {
        return payNum;
    }

    /**
     * 设置充值时记录ios票据，支付时记录流水号
     *
     * @param payNum 充值时记录ios票据，支付时记录流水号
     */
    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    /**
     * @return pay_status
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * @param payStatus
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
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
}