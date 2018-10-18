package com.duia.commodity.model;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "class_student")
public class ClassStudent implements Serializable {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 班级id
     */
    @Column(name = "class_id")
    private Long classId;

    /**
     * 学生id
     */
    @Column(name = "student_id")
    private Long studentId;

    /**
     * 用户成功购买班级时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    @Column(name = "stop_time")
    private Date stopTime;

    @Column(name = "user_guide")
    private Integer userGuide;

    /**
     * 1:正式学员，2:体验学员
     */
    @Column(name = "student_type")
    private Integer studentType;

    /**
     * 0:不是临时班级 1:临时班级
     */
    @Column(name = "temp_class")
    private Integer tempClass;

    /**
     * 科目
     */
    private String subject;

    /**
     * 支付状态 0：已支付 1：支付中（分期）
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "yx_teamid")
    private String yxTeamid;

    @Column(name = "yx_open")
    private Integer yxOpen;

    /**
     * 原订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 订单详情ID
     */
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    /**
     * 转班过[0:无,1:有]
     */
    @Column(name = "change_his")
    private Integer changeHis;

    /**
     * 重修过[0:无,1:有]
     */
    @Column(name = "rebuild_his")
    private Integer rebuildHis;

    /**
     * 休学状态[0:未休学,1:休学中]
     */
    @Column(name = "dropout_status")
    private Integer dropoutStatus;

    /**
     * 质保模式[0:月份,1:截止日期]
     */
    private Integer mode;

    /**
     * 删除标记[0:未删除,1:已删除]
     */
    private Integer deleted;

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
     * 获取班级id
     *
     * @return class_id - 班级id
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 设置班级id
     *
     * @param classId 班级id
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 获取学生id
     *
     * @return student_id - 学生id
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * 设置学生id
     *
     * @param studentId 学生id
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取用户成功购买班级时间
     *
     * @return pay_time - 用户成功购买班级时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置用户成功购买班级时间
     *
     * @param payTime 用户成功购买班级时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * @return stop_time
     */
    public Date getStopTime() {
        return stopTime;
    }

    /**
     * @param stopTime
     */
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * @return user_guide
     */
    public Integer getUserGuide() {
        return userGuide;
    }

    /**
     * @param userGuide
     */
    public void setUserGuide(Integer userGuide) {
        this.userGuide = userGuide;
    }

    /**
     * 获取1:正式学员，2:体验学员
     *
     * @return student_type - 1:正式学员，2:体验学员
     */
    public Integer getStudentType() {
        return studentType;
    }

    /**
     * 设置1:正式学员，2:体验学员
     *
     * @param studentType 1:正式学员，2:体验学员
     */
    public void setStudentType(Integer studentType) {
        this.studentType = studentType;
    }

    /**
     * 获取0:不是临时班级 1:临时班级
     *
     * @return temp_class - 0:不是临时班级 1:临时班级
     */
    public Integer getTempClass() {
        return tempClass;
    }

    /**
     * 设置0:不是临时班级 1:临时班级
     *
     * @param tempClass 0:不是临时班级 1:临时班级
     */
    public void setTempClass(Integer tempClass) {
        this.tempClass = tempClass;
    }

    /**
     * 获取科目
     *
     * @return subject - 科目
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置科目
     *
     * @param subject 科目
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取支付状态 0：已支付 1：支付中（分期）
     *
     * @return pay_status - 支付状态 0：已支付 1：支付中（分期）
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态 0：已支付 1：支付中（分期）
     *
     * @param payStatus 支付状态 0：已支付 1：支付中（分期）
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return yx_teamid
     */
    public String getYxTeamid() {
        return yxTeamid;
    }

    /**
     * @param yxTeamid
     */
    public void setYxTeamid(String yxTeamid) {
        this.yxTeamid = yxTeamid;
    }

    /**
     * @return yx_open
     */
    public Integer getYxOpen() {
        return yxOpen;
    }

    /**
     * @param yxOpen
     */
    public void setYxOpen(Integer yxOpen) {
        this.yxOpen = yxOpen;
    }

    /**
     * 获取原订单id
     *
     * @return order_id - 原订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置原订单id
     *
     * @param orderId 原订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单详情ID
     *
     * @return order_detail_id - 订单详情ID
     */
    public Long getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * 设置订单详情ID
     *
     * @param orderDetailId 订单详情ID
     */
    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * 获取转班过[0:无,1:有]
     *
     * @return change_his - 转班过[0:无,1:有]
     */
    public Integer getChangeHis() {
        return changeHis;
    }

    /**
     * 设置转班过[0:无,1:有]
     *
     * @param changeHis 转班过[0:无,1:有]
     */
    public void setChangeHis(Integer changeHis) {
        this.changeHis = changeHis;
    }

    /**
     * 获取重修过[0:无,1:有]
     *
     * @return rebuild_his - 重修过[0:无,1:有]
     */
    public Integer getRebuildHis() {
        return rebuildHis;
    }

    /**
     * 设置重修过[0:无,1:有]
     *
     * @param rebuildHis 重修过[0:无,1:有]
     */
    public void setRebuildHis(Integer rebuildHis) {
        this.rebuildHis = rebuildHis;
    }

    /**
     * 获取休学状态[0:未休学,1:休学中]
     *
     * @return dropout_status - 休学状态[0:未休学,1:休学中]
     */
    public Integer getDropoutStatus() {
        return dropoutStatus;
    }

    /**
     * 设置休学状态[0:未休学,1:休学中]
     *
     * @param dropoutStatus 休学状态[0:未休学,1:休学中]
     */
    public void setDropoutStatus(Integer dropoutStatus) {
        this.dropoutStatus = dropoutStatus;
    }

    /**
     * 获取质保模式[0:月份,1:截止日期]
     *
     * @return mode - 质保模式[0:月份,1:截止日期]
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 设置质保模式[0:月份,1:截止日期]
     *
     * @param mode 质保模式[0:月份,1:截止日期]
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    /**
     * 获取删除标记[0:未删除,1:已删除]
     *
     * @return deleted - 删除标记[0:未删除,1:已删除]
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标记[0:未删除,1:已删除]
     *
     * @param deleted 删除标记[0:未删除,1:已删除]
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}