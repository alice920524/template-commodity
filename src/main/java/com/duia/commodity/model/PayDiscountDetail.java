package com.duia.commodity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "pay_discount_detail")
public class PayDiscountDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount_id")
    private Long discountId;

    @Column(name = "discount_code")
    private String discountCode;

    private Integer type;

    private String discount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "use_condition")
    private String useCondition;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "used_date")
    private Date usedDate;

    @Column(name = "user_id")
    private Long userId;

    private Integer status;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "commod_id")
    private Long commodId;

    @Column(name = "discount_type")
    private String discountType;

    private String period;

    //前台用户是否可见
    @Column(name = "user_visible")
    private Integer userVisible;


    @Column(name = "course_type")
    private Integer courseType;

    /**
     * 1是绑定了订单
     */
    @Column(name = "pay_status")
    private Integer payStatus;

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
     * @return discount_id
     */
    public Long getDiscountId() {
        return discountId;
    }

    /**
     * @param discountId
     */
    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    /**
     * @return discount_code
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return discount
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * @param discount
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     * @return start_date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return end_date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return use_condition
     */
    public String getUseCondition() {
        return useCondition;
    }

    /**
     * @param useCondition
     */
    public void setUseCondition(String useCondition) {
        this.useCondition = useCondition;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return used_date
     */
    public Date getUsedDate() {
        return usedDate;
    }

    /**
     * @param usedDate
     */
    public void setUsedDate(Date usedDate) {
        this.usedDate = usedDate;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return teacher_id
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return sku_id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * @param skuId
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * @return commod_id
     */
    public Long getCommodId() {
        return commodId;
    }

    /**
     * @param commodId
     */
    public void setCommodId(Long commodId) {
        this.commodId = commodId;
    }

    /**
     * @return discount_type
     */
    public String getDiscountType() {
        return discountType;
    }

    /**
     * @param discountType
     */
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    /**
     * @return period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * 获取1是绑定了订单
     *
     * @return pay_status - 1是绑定了订单
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置1是绑定了订单
     *
     * @param payStatus 1是绑定了订单
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getUserVisible() {
        return userVisible;
    }

    public void setUserVisible(Integer userVvisible) {
        this.userVisible = userVvisible;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }
}