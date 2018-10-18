package com.duia.commodity.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_no")
    private String classNo;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "class_type_id")
    private Long classTypeId;

    private Integer status;

    @Column(name = "enroll_num")
    private Integer enrollNum;

    @Column(name = "enroll_des")
    private String enrollDes;

    @Column(name = "enroll_class_hour")
    private Integer enrollClassHour;

    @Column(name = "visit1_start_date")
    private Date visit1StartDate;

    @Column(name = "visit1_end_date")
    private Date visit1EndDate;

    @Column(name = "visit2_start_date")
    private Date visit2StartDate;

    @Column(name = "visit2_end_date")
    private Date visit2EndDate;

    @Column(name = "visit3_start_date")
    private Date visit3StartDate;

    @Column(name = "visit3_end_date")
    private Date visit3EndDate;

    private Integer sku;

    private Long creator;

    @Column(name = "create_time")
    private Date createTime;

    private Long updator;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "teacher_userId")
    private Long teacherUserid;

    @Column(name = "visit_teacher_id")
    private Long visitTeacherId;

    @Column(name = "visit_teacher_userId")
    private Long visitTeacherUserid;

    private String uuid;

    @Column(name = "class_schedule_id")
    private Long classScheduleId;

    @Column(name="class_type")
    private Integer classType;

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    /**
     * 主讲老师
     */
    @Column(name = "authority_user_id")
    private Long authorityUserId;

    /**
     * 有效状态[0:不可用[未完成配置],1:可用(完成配置)]
     */
    private Integer validity;

    /**
     * 预约上架[0:未预约,1:已预约]
     */
    @Column(name = "appointment_up")
    private Integer appointmentUp;

    /**
     * 预约上架时间
     */
    @Column(name = "appointment_up_time")
    private Date appointmentUpTime;

    /**
     * 讲师userId
     */
    @Column(name = "teachers")
    private String teachers;

    /**
     * 结课时间
     */
    @Column(name="class_end")
    private Date classEnd;

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
     * @return class_no
     */
    public String getClassNo() {
        return classNo;
    }

    /**
     * @param classNo
     */
    public void setClassNo(String classNo) {
        this.classNo = classNo;
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
     * @return class_type_id
     */
    public Long getClassTypeId() {
        return classTypeId;
    }

    /**
     * @param classTypeId
     */
    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
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
     * @return enroll_des
     */
    public String getEnrollDes() {
        return enrollDes;
    }

    /**
     * @param enrollDes
     */
    public void setEnrollDes(String enrollDes) {
        this.enrollDes = enrollDes;
    }

    /**
     * @return enroll_class_hour
     */
    public Integer getEnrollClassHour() {
        return enrollClassHour;
    }

    /**
     * @param enrollClassHour
     */
    public void setEnrollClassHour(Integer enrollClassHour) {
        this.enrollClassHour = enrollClassHour;
    }

    /**
     * @return visit1_start_date
     */
    public Date getVisit1StartDate() {
        return visit1StartDate;
    }

    /**
     * @param visit1StartDate
     */
    public void setVisit1StartDate(Date visit1StartDate) {
        this.visit1StartDate = visit1StartDate;
    }

    /**
     * @return visit1_end_date
     */
    public Date getVisit1EndDate() {
        return visit1EndDate;
    }

    /**
     * @param visit1EndDate
     */
    public void setVisit1EndDate(Date visit1EndDate) {
        this.visit1EndDate = visit1EndDate;
    }

    /**
     * @return visit2_start_date
     */
    public Date getVisit2StartDate() {
        return visit2StartDate;
    }

    /**
     * @param visit2StartDate
     */
    public void setVisit2StartDate(Date visit2StartDate) {
        this.visit2StartDate = visit2StartDate;
    }

    /**
     * @return visit2_end_date
     */
    public Date getVisit2EndDate() {
        return visit2EndDate;
    }

    /**
     * @param visit2EndDate
     */
    public void setVisit2EndDate(Date visit2EndDate) {
        this.visit2EndDate = visit2EndDate;
    }

    /**
     * @return visit3_start_date
     */
    public Date getVisit3StartDate() {
        return visit3StartDate;
    }

    /**
     * @param visit3StartDate
     */
    public void setVisit3StartDate(Date visit3StartDate) {
        this.visit3StartDate = visit3StartDate;
    }

    /**
     * @return visit3_end_date
     */
    public Date getVisit3EndDate() {
        return visit3EndDate;
    }

    /**
     * @param visit3EndDate
     */
    public void setVisit3EndDate(Date visit3EndDate) {
        this.visit3EndDate = visit3EndDate;
    }

    /**
     * @return sku
     */
    public Integer getSku() {
        return sku;
    }

    /**
     * @param sku
     */
    public void setSku(Integer sku) {
        this.sku = sku;
    }

    /**
     * @return creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Long creator) {
        this.creator = creator;
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
     * @return updator
     */
    public Long getUpdator() {
        return updator;
    }

    /**
     * @param updator
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
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
     * @return teacher_userId
     */
    public Long getTeacherUserid() {
        return teacherUserid;
    }

    /**
     * @param teacherUserid
     */
    public void setTeacherUserid(Long teacherUserid) {
        this.teacherUserid = teacherUserid;
    }

    /**
     * @return visit_teacher_id
     */
    public Long getVisitTeacherId() {
        return visitTeacherId;
    }

    /**
     * @param visitTeacherId
     */
    public void setVisitTeacherId(Long visitTeacherId) {
        this.visitTeacherId = visitTeacherId;
    }

    /**
     * @return visit_teacher_userId
     */
    public Long getVisitTeacherUserid() {
        return visitTeacherUserid;
    }

    /**
     * @param visitTeacherUserid
     */
    public void setVisitTeacherUserid(Long visitTeacherUserid) {
        this.visitTeacherUserid = visitTeacherUserid;
    }

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return class_schedule_id
     */
    public Long getClassScheduleId() {
        return classScheduleId;
    }

    /**
     * @param classScheduleId
     */
    public void setClassScheduleId(Long classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    /**
     * 获取主讲老师
     *
     * @return authority_user_id - 主讲老师
     */
    public Long getAuthorityUserId() {
        return authorityUserId;
    }

    /**
     * 设置主讲老师
     *
     * @param authorityUserId 主讲老师
     */
    public void setAuthorityUserId(Long authorityUserId) {
        this.authorityUserId = authorityUserId;
    }

    /**
     * 获取有效状态[0:不可用[未完成配置],1:可用(完成配置)]
     *
     * @return validity - 有效状态[0:不可用[未完成配置],1:可用(完成配置)]
     */
    public Integer getValidity() {
        return validity;
    }

    /**
     * 设置有效状态[0:不可用[未完成配置],1:可用(完成配置)]
     *
     * @param validity 有效状态[0:不可用[未完成配置],1:可用(完成配置)]
     */
    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    /**
     * 获取预约上架[0:未预约,1:已预约]
     *
     * @return appointment_up - 预约上架[0:未预约,1:已预约]
     */
    public Integer getAppointmentUp() {
        return appointmentUp;
    }

    /**
     * 设置预约上架[0:未预约,1:已预约]
     *
     * @param appointmentUp 预约上架[0:未预约,1:已预约]
     */
    public void setAppointmentUp(Integer appointmentUp) {
        this.appointmentUp = appointmentUp;
    }

    /**
     * 获取预约上架时间
     *
     * @return appointment_up_time - 预约上架时间
     */
    public Date getAppointmentUpTime() {
        return appointmentUpTime;
    }

    /**
     * 设置预约上架时间
     *
     * @param appointmentUpTime 预约上架时间
     */
    public void setAppointmentUpTime(Date appointmentUpTime) {
        this.appointmentUpTime = appointmentUpTime;
    }

    public String getTeachers() {
        return teachers;
    }
    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public Date getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(Date classEnd) {
        this.classEnd = classEnd;
    }
}