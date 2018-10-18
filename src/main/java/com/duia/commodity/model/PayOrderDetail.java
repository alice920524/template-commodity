package com.duia.commodity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "pay_order_detail")
public class PayOrderDetail implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    /**
     * 班型id
     */
    @Column(name = "class_type_id")
    private Long classTypeId;

    /**
     * 特权券id
     * */
    @Column(name = "voucher_id")
    private Long voucherId;

    /**
     * 班型类型[0:系统班,1:专题课]
     * */
    @Column(name = "course_type")
    private Integer courseType;

    @Column(name = "com_id")
    private Long comId;

    @Column(name = "com_name")
    private String comName;

    @Column(name = "com_cost_price")
    private Double comCostPrice;

    @Column(name = "com_real_price")
    private Double comRealPrice;

    @Column(name = "com_cover_url")
    private String comCoverUrl;

    @Column(name = "com_num")
    private Integer comNum;

    /**
     * 1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     */
    @Column(name = "com_type")
    private Integer comType;

    /**
     * 1: 组合购买 2:单买
     */
    @Column(name = "com_mode")
    private Integer comMode;

    /**
     * 特殊组合商品ID
     */
    @Column(name = "special_com_id")
    private Long specialComId;

    /**
     * 科目
     */
    private String subject;

    @Column(name = "subjectFlag")
    private String subjectflag;

    /**
     * 是否是赠品
     */
    private Integer gifts;

    /**
     * 学习包总价格(默认值是0)重点
     */
    @Column(name = "study_package_price")
    private Double studyPackagePrice;

    /**
     * 班型有效期
     */
    @Column(name = "class_type_validity")
    private Integer classTypeValidity;

    /**
     * 购买类型[0:正常购买,1:加价购]
     */
    @Column(name = "buy_type")
    private Integer buyType;

    /**
     * 质保模式[0:月份,1:截止日期]
     */
    private Integer mode;

    /**
     * 截止日期
     */
    private Date deadline;

    /**
     * 班级有效期(月)
     */
    private Integer validity;

    /**
     * 是否有学习包[1:没有,2有]
     */
    @Column(name = "address_mark")
    private Integer addressMark;

    /**
     * 教材类型[0:普通,1:初中,2:高中]
     * */
    @Column(name = "book_type")
    private Integer bookType;

    /**
     * 课程协议[0,1,3,4,6]
     */
    private String agreements;
    /**
     * 保险价格
     * */
    private Double insurancePrice;
    /**
     * 质保期类型[0:普通,1:1类,2:2类]
     * */
    private Integer guaType;
    /**
     *允许延保[0:不允许,1:允许]
     * */
    private Integer allowGua;
    /**
     * 允许延期[0:不允许,1:允许]
     * */
    private Integer allowDelay;
    /**
     * 人脸识别[0:停用,1:启用]
     */
    @Column(name = "face_recognition")
    private Integer faceRecognition;

    public Integer getFaceRecognition() {
        return faceRecognition;
    }

    public void setFaceRecognition(Integer faceRecognition) {
        this.faceRecognition = faceRecognition;
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
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
     * 获取班型id
     *
     * @return class_type_id - 班型id
     */
    public Long getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    /**
     * @return com_id
     */
    public Long getComId() {
        return comId;
    }

    /**
     * @param comId
     */
    public void setComId(Long comId) {
        this.comId = comId;
    }

    /**
     * @return com_name
     */
    public String getComName() {
        return comName;
    }

    /**
     * @param comName
     */
    public void setComName(String comName) {
        this.comName = comName;
    }

    /**
     * @return com_cost_price
     */
    public Double getComCostPrice() {
        return comCostPrice;
    }

    /**
     * @param comCostPrice
     */
    public void setComCostPrice(Double comCostPrice) {
        this.comCostPrice = comCostPrice;
    }

    /**
     * @return com_real_price
     */
    public Double getComRealPrice() {
        return comRealPrice;
    }

    /**
     * @param comRealPrice
     */
    public void setComRealPrice(Double comRealPrice) {
        this.comRealPrice = comRealPrice;
    }

    /**
     * @return com_cover_url
     */
    public String getComCoverUrl() {
        return comCoverUrl;
    }

    /**
     * @param comCoverUrl
     */
    public void setComCoverUrl(String comCoverUrl) {
        this.comCoverUrl = comCoverUrl;
    }

    /**
     * @return com_num
     */
    public Integer getComNum() {
        return comNum;
    }

    /**
     * @param comNum
     */
    public void setComNum(Integer comNum) {
        this.comNum = comNum;
    }

    /**
     * 获取1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     *
     * @return com_type - 1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     */
    public Integer getComType() {
        return comType;
    }

    /**
     * 设置1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     *
     * @param comType 1：直播课；
            2：书；
            3：视频下载服务；
            4：答疑；
            5：题库
     */
    public void setComType(Integer comType) {
        this.comType = comType;
    }

    /**
     * 获取1: 组合购买 2:单买
     *
     * @return com_mode - 1: 组合购买 2:单买
     */
    public Integer getComMode() {
        return comMode;
    }

    /**
     * 设置1: 组合购买 2:单买
     *
     * @param comMode 1: 组合购买 2:单买
     */
    public void setComMode(Integer comMode) {
        this.comMode = comMode;
    }

    /**
     * 获取特殊组合商品ID
     *
     * @return special_com_id - 特殊组合商品ID
     */
    public Long getSpecialComId() {
        return specialComId;
    }

    /**
     * 设置特殊组合商品ID
     *
     * @param specialComId 特殊组合商品ID
     */
    public void setSpecialComId(Long specialComId) {
        this.specialComId = specialComId;
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
     * @return subjectFlag
     */
    public String getSubjectflag() {
        return subjectflag;
    }

    /**
     * @param subjectflag
     */
    public void setSubjectflag(String subjectflag) {
        this.subjectflag = subjectflag;
    }

    /**
     * 获取是否是赠品
     *
     * @return gifts - 是否是赠品
     */
    public Integer getGifts() {
        return gifts;
    }

    /**
     * 设置是否是赠品
     *
     * @param gifts 是否是赠品
     */
    public void setGifts(Integer gifts) {
        this.gifts = gifts;
    }

    /**
     * 获取学习包总价格(默认值是0)重点
     *
     * @return study_package_price - 学习包总价格(默认值是0)重点
     */
    public Double getStudyPackagePrice() {
        return studyPackagePrice;
    }

    /**
     * 设置学习包总价格(默认值是0)重点
     *
     * @param studyPackagePrice 学习包总价格(默认值是0)重点
     */
    public void setStudyPackagePrice(Double studyPackagePrice) {
        this.studyPackagePrice = studyPackagePrice;
    }

    /**
     * 获取班型有效期
     *
     * @return class_type_validity - 班型有效期
     */
    public Integer getClassTypeValidity() {
        return classTypeValidity;
    }

    /**
     * 设置班型有效期
     *
     * @param classTypeValidity 班型有效期
     */
    public void setClassTypeValidity(Integer classTypeValidity) {
        this.classTypeValidity = classTypeValidity;
    }

    /**
     * 获取购买类型[0:正常购买,1:加价购]
     *
     * @return buy_type - 购买类型[0:正常购买,1:加价购]
     */
    public Integer getBuyType() {
        return buyType;
    }

    /**
     * 设置购买类型[0:正常购买,1:加价购]
     *
     * @param buyType 购买类型[0:正常购买,1:加价购]
     */
    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
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
     * 获取截止日期
     *
     * @return deadline - 截止日期
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * 设置截止日期
     *
     * @param deadline 截止日期
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * 获取班级有效期(月)
     *
     * @return validity - 班级有效期(月)
     */
    public Integer getValidity() {
        return validity;
    }

    /**
     * 设置班级有效期(月)
     *
     * @param validity 班级有效期(月)
     */
    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    /**
     * 获取是否有学习包[1:没有,2有]
     *
     * @return address_mark - 是否有学习包[1:没有,2有]
     */
    public Integer getAddressMark() {
        return addressMark;
    }

    /**
     * 设置是否有学习包[1:没有,2有]
     *
     * @param addressMark 是否有学习包[1:没有,2有]
     */
    public void setAddressMark(Integer addressMark) {
        this.addressMark = addressMark;
    }

    /**
     * 获取课程协议[0,1,3,4,6]
     *
     * @return agreements - 课程协议[0,1,3,4,6]
     */
    public String getAgreements() {
        return agreements;
    }

    /**
     * 设置课程协议[0,1,3,4,6]
     *
     * @param agreements 课程协议[0,1,3,4,6]
     */
    public void setAgreements(String agreements) {
        this.agreements = agreements;
    }

    public Double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(Double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Integer getGuaType() {
        return guaType;
    }

    public void setGuaType(Integer guaType) {
        this.guaType = guaType;
    }

    public Integer getAllowGua() {
        return allowGua;
    }

    public void setAllowGua(Integer allowGua) {
        this.allowGua = allowGua;
    }

    public Integer getAllowDelay() {
        return allowDelay;
    }

    public void setAllowDelay(Integer allowDelay) {
        this.allowDelay = allowDelay;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }
}