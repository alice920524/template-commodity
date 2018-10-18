package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "book_order_trans")
public class BookOrderTrans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 流水号
     */
    @Column(name = "pay_num")
    private String payNum;

    /**
     * 支付状态[pay_status_non,pay_status_success]
     */
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "trans_date")
    private Date transDate;

    /**
     * 支付时间
     */
    @Column(name = "trans_pay_date")
    private Date transPayDate;

    /**
     * 付款价格
     */
    private Double price;

    /**
     * 支付方式[pay_type_wxzf,pay_type_zfb]
     */
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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取流水号
     *
     * @return pay_num - 流水号
     */
    public String getPayNum() {
        return payNum;
    }

    /**
     * 设置流水号
     *
     * @param payNum 流水号
     */
    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    /**
     * 获取支付状态[pay_status_non,pay_status_success]
     *
     * @return status - 支付状态[pay_status_non,pay_status_success]
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置支付状态[pay_status_non,pay_status_success]
     *
     * @param status 支付状态[pay_status_non,pay_status_success]
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return trans_date - 创建时间
     */
    public Date getTransDate() {
        return transDate;
    }

    /**
     * 设置创建时间
     *
     * @param transDate 创建时间
     */
    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    /**
     * 获取支付时间
     *
     * @return trans_pay_date - 支付时间
     */
    public Date getTransPayDate() {
        return transPayDate;
    }

    /**
     * 设置支付时间
     *
     * @param transPayDate 支付时间
     */
    public void setTransPayDate(Date transPayDate) {
        this.transPayDate = transPayDate;
    }

    /**
     * 获取付款价格
     *
     * @return price - 付款价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置付款价格
     *
     * @param price 付款价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取支付方式[pay_type_wxzf,pay_type_zfb]
     *
     * @return pay_type - 支付方式[pay_type_wxzf,pay_type_zfb]
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付方式[pay_type_wxzf,pay_type_zfb]
     *
     * @param payType 支付方式[pay_type_wxzf,pay_type_zfb]
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }
}