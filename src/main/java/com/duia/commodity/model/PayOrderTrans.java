package com.duia.commodity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "pay_order_trans")
public class PayOrderTrans implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "pay_num")
    private String payNum;

    private String status;

    @Column(name = "trans_date")
    private Date transDate;

    @Column(name = "trans_pay_date")
    private Date transPayDate;

    private Double price;

    @Column(name = "bank_type")
    private String bankType;

    @Column(name = "pay_type")
    private String payType;

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
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return pay_num
     */
    public String getPayNum() {
        return payNum;
    }

    /**
     * @param payNum
     */
    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return trans_date
     */
    public Date getTransDate() {
        return transDate;
    }

    /**
     * @param transDate
     */
    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    /**
     * @return trans_pay_date
     */
    public Date getTransPayDate() {
        return transPayDate;
    }

    /**
     * @param transPayDate
     */
    public void setTransPayDate(Date transPayDate) {
        this.transPayDate = transPayDate;
    }

    /**
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return bank_type
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * @param bankType
     */
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    /**
     * @return pay_type
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }
}