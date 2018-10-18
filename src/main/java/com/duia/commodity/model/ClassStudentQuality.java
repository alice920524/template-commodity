package com.duia.commodity.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "class_student_quality")
public class ClassStudentQuality implements Serializable {
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
     * 质保期协议ID
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
     * 获取质保期协议ID
     *
     * @return aggrement_template_id - 质保期协议ID
     */
    public Long getAggrementTemplateId() {
        return aggrementTemplateId;
    }

    /**
     * 设置质保期协议ID
     *
     * @param aggrementTemplateId 质保期协议ID
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
}