package com.duia.commodity.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "class_student_guarantee")
public class ClassStudentGuarantee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学员ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 订单详情id
     */
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    /**
     * 保过协议ID
     */
    @Column(name = "aggrement_template_id")
    private Long aggrementTemplateId;

    /**
     * 班级ID
     */
    @Column(name = "class_id")
    private Long classId;

    /**
     * 学员最后班级ID
     */
    @Column(name = "last_class_id")
    private Long lastClassId;

    /**
     * 学员姓名
     */
    private String username;

    /**
     * 性别[1:男,2:女]
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 状态[0:未签署,1:已签署,2:已废弃]
     */
    private Integer status;

    /**
     * 有效性[0:无效,1:有效]
     */
    private Integer validity;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改者
     */
    private Long updator;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 签署时间
     */
    @Column(name = "sign_date")
    private Date signDate;

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
     * 获取学员ID
     *
     * @return user_id - 学员ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置学员ID
     *
     * @param userId 学员ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单详情id
     *
     * @return order_detail_id - 订单详情id
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * 设置订单详情id
     *
     * @param orderDetailId 订单详情id
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * 获取保过协议ID
     *
     * @return aggrement_template_id - 保过协议ID
     */
    public Long getAggrementTemplateId() {
        return aggrementTemplateId;
    }

    /**
     * 设置保过协议ID
     *
     * @param aggrementTemplateId 保过协议ID
     */
    public void setAggrementTemplateId(Long aggrementTemplateId) {
        this.aggrementTemplateId = aggrementTemplateId;
    }

    /**
     * 获取班级ID
     *
     * @return class_id - 班级ID
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 设置班级ID
     *
     * @param classId 班级ID
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 获取学员最后班级ID
     *
     * @return last_class_id - 学员最后班级ID
     */
    public Long getLastClassId() {
        return lastClassId;
    }

    /**
     * 设置学员最后班级ID
     *
     * @param lastClassId 学员最后班级ID
     */
    public void setLastClassId(Long lastClassId) {
        this.lastClassId = lastClassId;
    }

    /**
     * 获取学员姓名
     *
     * @return username - 学员姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置学员姓名
     *
     * @param username 学员姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取性别[1:男,2:女]
     *
     * @return sex - 性别[1:男,2:女]
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别[1:男,2:女]
     *
     * @param sex 性别[1:男,2:女]
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取身份证号
     *
     * @return id_card - 身份证号
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号
     *
     * @param idCard 身份证号
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取状态[0:未签署,1:已签署,2:已废弃]
     *
     * @return status - 状态[0:未签署,1:已签署,2:已废弃]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态[0:未签署,1:已签署,2:已废弃]
     *
     * @param status 状态[0:未签署,1:已签署,2:已废弃]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取有效性[0:无效,1:有效]
     *
     * @return validity - 有效性[0:无效,1:有效]
     */
    public Integer getValidity() {
        return validity;
    }

    /**
     * 设置有效性[0:无效,1:有效]
     *
     * @param validity 有效性[0:无效,1:有效]
     */
    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(Long creator) {
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
    public Long getUpdator() {
        return updator;
    }

    /**
     * 设置最后修改者
     *
     * @param updator 最后修改者
     */
    public void setUpdator(Long updator) {
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
     * 获取签署时间
     *
     * @return sign_date - 签署时间
     */
    public Date getSignDate() {
        return signDate;
    }

    /**
     * 设置签署时间
     *
     * @param signDate 签署时间
     */
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}