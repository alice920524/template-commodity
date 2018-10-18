package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "class_rebuild")
public class ClassRebuild {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学生id
     */
    @Column(name = "student_id")
    private Long studentId;

    /**
     * 重修原因
     */
    private String reason;

    /**
     * 申请日期
     */
    @Column(name = "apply_date")
    private Date applyDate;

    /**
     * 申请状1：申请中； 2：重修完成；3：拒绝；4：教务审核通过；5：学员前台取消订单
     */
    @Column(name = "apply_status")
    private Integer applyStatus;

    /**
     * 审批时间
     */
    @Column(name = "approve_date")
    private Date approveDate;

    /**
     * 审批意见
     */
    @Column(name = "approve_view")
    private String approveView;

    /**
     * 审批人（任务分配）
     */
    private Long approver;

    /**
     * 原班班级id
     */
    @Column(name = "old_class_id")
    private Long oldClassId;

    /**
     * 原始班级号
     */
    @Column(name = "old_class_no")
    private String oldClassNo;

    /**
     * 原始班型id
     */
    @Column(name = "old_class_type_id")
    private Long oldClassTypeId;

    /**
     * 原始班型名称
     */
    @Column(name = "old_class_type_name")
    private String oldClassTypeName;

    /**
     * 原始班级教师名称
     */
    @Column(name = "old_teacher_name")
    private String oldTeacherName;

    /**
     * 新班级id
     */
    @Column(name = "new_class_id")
    private Long newClassId;

    /**
     * 新班级号
     */
    @Column(name = "new_class_no")
    private String newClassNo;

    /**
     * 新班型id
     */
    @Column(name = "new_class_type_id")
    private Long newClassTypeId;

    /**
     * 新班型名称
     */
    @Column(name = "new_class_type_name")
    private String newClassTypeName;

    /**
     * 新班级教师名称
     */
    @Column(name = "new_teacher_name")
    private String newTeacherName;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 修改人
     */
    private Long updater;

    /**
     * 重修凭证url1
     */
    @Column(name = "url_1")
    private String url1;

    /**
     * 重修凭证url2
     */
    @Column(name = "url_2")
    private String url2;

    /**
     * 重修凭证url3
     */
    @Column(name = "url_3")
    private String url3;

    @Column(name = "old_teacher_id")
    private Long oldTeacherId;

    @Column(name = "new_teacher_id")
    private Long newTeacherId;

    private String name;

    private String mobile;

    @Column(name = "qq_no")
    private String qqNo;

    /**
     * 原订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 是否邮寄教材 0不需要 1需要
     */
    @Column(name = "is_mail")
    private Integer isMail;

    /**
     * 重修生成的新订单ID
     */
    @Column(name = "new_order_id")
    private Integer newOrderId;

    /**
     * 新课程金额
     */
    @Column(name = "new_cost_price")
    private Double newCostPrice;

    /**
     * 新课程教材费
     */
    @Column(name = "new_book_price")
    private Double newBookPrice;

    /**
     * 重修订单应付金额
     */
    @Column(name = "new_order_price")
    private Double newOrderPrice;

    /**
     * 订单来源
     */
    private String source;

    /**
     * 一元重修[0:否,1:是]
     */
    @Column(name = "one_rmb_rebuild")
    private Integer oneRmbRebuild;

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
     * 获取重修原因
     *
     * @return reason - 重修原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置重修原因
     *
     * @param reason 重修原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取申请日期
     *
     * @return apply_date - 申请日期
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * 设置申请日期
     *
     * @param applyDate 申请日期
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * 获取申请状1：申请中； 2：重修完成；3：拒绝；4：教务审核通过；5：学员前台取消订单
     *
     * @return apply_status - 申请状1：申请中； 2：重修完成；3：拒绝；4：教务审核通过；5：学员前台取消订单
     */
    public Integer getApplyStatus() {
        return applyStatus;
    }

    /**
     * 设置申请状1：申请中； 2：重修完成；3：拒绝；4：教务审核通过；5：学员前台取消订单
     *
     * @param applyStatus 申请状1：申请中； 2：重修完成；3：拒绝；4：教务审核通过；5：学员前台取消订单
     */
    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    /**
     * 获取审批时间
     *
     * @return approve_date - 审批时间
     */
    public Date getApproveDate() {
        return approveDate;
    }

    /**
     * 设置审批时间
     *
     * @param approveDate 审批时间
     */
    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    /**
     * 获取审批意见
     *
     * @return approve_view - 审批意见
     */
    public String getApproveView() {
        return approveView;
    }

    /**
     * 设置审批意见
     *
     * @param approveView 审批意见
     */
    public void setApproveView(String approveView) {
        this.approveView = approveView;
    }

    /**
     * 获取审批人（任务分配）
     *
     * @return approver - 审批人（任务分配）
     */
    public Long getApprover() {
        return approver;
    }

    /**
     * 设置审批人（任务分配）
     *
     * @param approver 审批人（任务分配）
     */
    public void setApprover(Long approver) {
        this.approver = approver;
    }

    /**
     * 获取原班班级id
     *
     * @return old_class_id - 原班班级id
     */
    public Long getOldClassId() {
        return oldClassId;
    }

    /**
     * 设置原班班级id
     *
     * @param oldClassId 原班班级id
     */
    public void setOldClassId(Long oldClassId) {
        this.oldClassId = oldClassId;
    }

    /**
     * 获取原始班级号
     *
     * @return old_class_no - 原始班级号
     */
    public String getOldClassNo() {
        return oldClassNo;
    }

    /**
     * 设置原始班级号
     *
     * @param oldClassNo 原始班级号
     */
    public void setOldClassNo(String oldClassNo) {
        this.oldClassNo = oldClassNo;
    }

    /**
     * 获取原始班型id
     *
     * @return old_class_type_id - 原始班型id
     */
    public Long getOldClassTypeId() {
        return oldClassTypeId;
    }

    /**
     * 设置原始班型id
     *
     * @param oldClassTypeId 原始班型id
     */
    public void setOldClassTypeId(Long oldClassTypeId) {
        this.oldClassTypeId = oldClassTypeId;
    }

    /**
     * 获取原始班型名称
     *
     * @return old_class_type_name - 原始班型名称
     */
    public String getOldClassTypeName() {
        return oldClassTypeName;
    }

    /**
     * 设置原始班型名称
     *
     * @param oldClassTypeName 原始班型名称
     */
    public void setOldClassTypeName(String oldClassTypeName) {
        this.oldClassTypeName = oldClassTypeName;
    }

    /**
     * 获取原始班级教师名称
     *
     * @return old_teacher_name - 原始班级教师名称
     */
    public String getOldTeacherName() {
        return oldTeacherName;
    }

    /**
     * 设置原始班级教师名称
     *
     * @param oldTeacherName 原始班级教师名称
     */
    public void setOldTeacherName(String oldTeacherName) {
        this.oldTeacherName = oldTeacherName;
    }

    /**
     * 获取新班级id
     *
     * @return new_class_id - 新班级id
     */
    public Long getNewClassId() {
        return newClassId;
    }

    /**
     * 设置新班级id
     *
     * @param newClassId 新班级id
     */
    public void setNewClassId(Long newClassId) {
        this.newClassId = newClassId;
    }

    /**
     * 获取新班级号
     *
     * @return new_class_no - 新班级号
     */
    public String getNewClassNo() {
        return newClassNo;
    }

    /**
     * 设置新班级号
     *
     * @param newClassNo 新班级号
     */
    public void setNewClassNo(String newClassNo) {
        this.newClassNo = newClassNo;
    }

    /**
     * 获取新班型id
     *
     * @return new_class_type_id - 新班型id
     */
    public Long getNewClassTypeId() {
        return newClassTypeId;
    }

    /**
     * 设置新班型id
     *
     * @param newClassTypeId 新班型id
     */
    public void setNewClassTypeId(Long newClassTypeId) {
        this.newClassTypeId = newClassTypeId;
    }

    /**
     * 获取新班型名称
     *
     * @return new_class_type_name - 新班型名称
     */
    public String getNewClassTypeName() {
        return newClassTypeName;
    }

    /**
     * 设置新班型名称
     *
     * @param newClassTypeName 新班型名称
     */
    public void setNewClassTypeName(String newClassTypeName) {
        this.newClassTypeName = newClassTypeName;
    }

    /**
     * 获取新班级教师名称
     *
     * @return new_teacher_name - 新班级教师名称
     */
    public String getNewTeacherName() {
        return newTeacherName;
    }

    /**
     * 设置新班级教师名称
     *
     * @param newTeacherName 新班级教师名称
     */
    public void setNewTeacherName(String newTeacherName) {
        this.newTeacherName = newTeacherName;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取修改人
     *
     * @return updater - 修改人
     */
    public Long getUpdater() {
        return updater;
    }

    /**
     * 设置修改人
     *
     * @param updater 修改人
     */
    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    /**
     * 获取重修凭证url1
     *
     * @return url_1 - 重修凭证url1
     */
    public String getUrl1() {
        return url1;
    }

    /**
     * 设置重修凭证url1
     *
     * @param url1 重修凭证url1
     */
    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    /**
     * 获取重修凭证url2
     *
     * @return url_2 - 重修凭证url2
     */
    public String getUrl2() {
        return url2;
    }

    /**
     * 设置重修凭证url2
     *
     * @param url2 重修凭证url2
     */
    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    /**
     * 获取重修凭证url3
     *
     * @return url_3 - 重修凭证url3
     */
    public String getUrl3() {
        return url3;
    }

    /**
     * 设置重修凭证url3
     *
     * @param url3 重修凭证url3
     */
    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    /**
     * @return old_teacher_id
     */
    public Long getOldTeacherId() {
        return oldTeacherId;
    }

    /**
     * @param oldTeacherId
     */
    public void setOldTeacherId(Long oldTeacherId) {
        this.oldTeacherId = oldTeacherId;
    }

    /**
     * @return new_teacher_id
     */
    public Long getNewTeacherId() {
        return newTeacherId;
    }

    /**
     * @param newTeacherId
     */
    public void setNewTeacherId(Long newTeacherId) {
        this.newTeacherId = newTeacherId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return qq_no
     */
    public String getQqNo() {
        return qqNo;
    }

    /**
     * @param qqNo
     */
    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    /**
     * 获取原订单id
     *
     * @return order_id - 原订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置原订单id
     *
     * @param orderId 原订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取是否邮寄教材 0不需要 1需要
     *
     * @return is_mail - 是否邮寄教材 0不需要 1需要
     */
    public Integer getIsMail() {
        return isMail;
    }

    /**
     * 设置是否邮寄教材 0不需要 1需要
     *
     * @param isMail 是否邮寄教材 0不需要 1需要
     */
    public void setIsMail(Integer isMail) {
        this.isMail = isMail;
    }

    /**
     * 获取重修生成的新订单ID
     *
     * @return new_order_id - 重修生成的新订单ID
     */
    public Integer getNewOrderId() {
        return newOrderId;
    }

    /**
     * 设置重修生成的新订单ID
     *
     * @param newOrderId 重修生成的新订单ID
     */
    public void setNewOrderId(Integer newOrderId) {
        this.newOrderId = newOrderId;
    }

    /**
     * 获取新课程金额
     *
     * @return new_cost_price - 新课程金额
     */
    public Double getNewCostPrice() {
        return newCostPrice;
    }

    /**
     * 设置新课程金额
     *
     * @param newCostPrice 新课程金额
     */
    public void setNewCostPrice(Double newCostPrice) {
        this.newCostPrice = newCostPrice;
    }

    /**
     * 获取新课程教材费
     *
     * @return new_book_price - 新课程教材费
     */
    public Double getNewBookPrice() {
        return newBookPrice;
    }

    /**
     * 设置新课程教材费
     *
     * @param newBookPrice 新课程教材费
     */
    public void setNewBookPrice(Double newBookPrice) {
        this.newBookPrice = newBookPrice;
    }

    /**
     * 获取重修订单应付金额
     *
     * @return new_order_price - 重修订单应付金额
     */
    public Double getNewOrderPrice() {
        return newOrderPrice;
    }

    /**
     * 设置重修订单应付金额
     *
     * @param newOrderPrice 重修订单应付金额
     */
    public void setNewOrderPrice(Double newOrderPrice) {
        this.newOrderPrice = newOrderPrice;
    }

    /**
     * 获取订单来源
     *
     * @return source - 订单来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置订单来源
     *
     * @param source 订单来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取一元重修[0:否,1:是]
     *
     * @return one_rmb_rebuild - 一元重修[0:否,1:是]
     */
    public Integer getOneRmbRebuild() {
        return oneRmbRebuild;
    }

    /**
     * 设置一元重修[0:否,1:是]
     *
     * @param oneRmbRebuild 一元重修[0:否,1:是]
     */
    public void setOneRmbRebuild(Integer oneRmbRebuild) {
        this.oneRmbRebuild = oneRmbRebuild;
    }
}