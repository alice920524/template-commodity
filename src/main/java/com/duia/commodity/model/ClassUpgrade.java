package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "class_upgrade")
public class ClassUpgrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "before_classid")
    private Long beforeClassid;

    @Column(name = "after_classid")
    private Long afterClassid;

    private Double price;

    @Column(name = "valid_status")
    private Integer validStatus;

    private Integer num;

    @Column(name = "before_date")
    private Date beforeDate;

    @Column(name = "enroll_num")
    private Integer enrollNum;

    /**
     * 邮寄地址标识[1,2]
     */
    @Column(name = "address_mark")
    private Integer addressMark;

    /**
     * 学习包升级价格
     */
    @Column(name = "study_pack_price")
    private Double studyPackPrice;

    /**
     * 允许用优惠券的最大金额
     */
    @Column(name = "limit_amount")
    private Integer limitAmount;

    /**
     * 是否有上限[0无1有]
     */
    @Column(name = "has_limit")
    private Integer hasLimit;

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
     * @return before_classid
     */
    public Long getBeforeClassid() {
        return beforeClassid;
    }

    /**
     * @param beforeClassid
     */
    public void setBeforeClassid(Long beforeClassid) {
        this.beforeClassid = beforeClassid;
    }

    /**
     * @return after_classid
     */
    public Long getAfterClassid() {
        return afterClassid;
    }

    /**
     * @param afterClassid
     */
    public void setAfterClassid(Long afterClassid) {
        this.afterClassid = afterClassid;
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
     * @return valid_status
     */
    public Integer getValidStatus() {
        return validStatus;
    }

    /**
     * @param validStatus
     */
    public void setValidStatus(Integer validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * @return before_date
     */
    public Date getBeforeDate() {
        return beforeDate;
    }

    /**
     * @param beforeDate
     */
    public void setBeforeDate(Date beforeDate) {
        this.beforeDate = beforeDate;
    }

    /**
     * @return enroll_num
     */
    public Integer getEnrollNum() {
        return enrollNum;
    }

    /**
     * @param enrollNum
     */
    public void setEnrollNum(Integer enrollNum) {
        this.enrollNum = enrollNum;
    }

    /**
     * 获取邮寄地址标识[1,2]
     *
     * @return address_mark - 邮寄地址标识[1,2]
     */
    public Integer getAddressMark() {
        return addressMark;
    }

    /**
     * 设置邮寄地址标识[1,2]
     *
     * @param addressMark 邮寄地址标识[1,2]
     */
    public void setAddressMark(Integer addressMark) {
        this.addressMark = addressMark;
    }

    /**
     * 获取学习包升级价格
     *
     * @return study_pack_price - 学习包升级价格
     */
    public Double getStudyPackPrice() {
        return studyPackPrice;
    }

    /**
     * 设置学习包升级价格
     *
     * @param studyPackPrice 学习包升级价格
     */
    public void setStudyPackPrice(Double studyPackPrice) {
        this.studyPackPrice = studyPackPrice;
    }

    /**
     * 获取允许用优惠券的最大金额
     *
     * @return limit_amount - 允许用优惠券的最大金额
     */
    public Integer getLimitAmount() {
        return limitAmount;
    }

    /**
     * 设置允许用优惠券的最大金额
     *
     * @param limitAmount 允许用优惠券的最大金额
     */
    public void setLimitAmount(Integer limitAmount) {
        this.limitAmount = limitAmount;
    }

    /**
     * 获取是否有上限[0无1有]
     *
     * @return has_limit - 是否有上限[0无1有]
     */
    public Integer getHasLimit() {
        return hasLimit;
    }

    /**
     * 设置是否有上限[0无1有]
     *
     * @param hasLimit 是否有上限[0无1有]
     */
    public void setHasLimit(Integer hasLimit) {
        this.hasLimit = hasLimit;
    }
}