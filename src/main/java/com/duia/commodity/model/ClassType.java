package com.duia.commodity.model;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "class_type")
public class ClassType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "qq_num")
    private String qqNum;

    private Double price;

    @Column(name = "cover_url")
    private String coverUrl;

    private String description;

    private Integer base;

    @Column(name = "course_code")
    private String courseCode;

    private Integer sku;

    @Column(name = "course_type")
    private Integer courseType;//班型类型

    private Long creator;

    @Column(name = "create_time")
    private Date createTime;

    private Long updator;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "vip_sku")
    private String vipSku;

    @Column(name = "cover_url_two")
    private String coverUrlTwo;

    @Column(name = "cover_url_three")
    private String coverUrlThree;

    /**
     * 邮寄地址标识[1,2]
     */
    @Column(name = "address_mark")
    private Integer addressMark;

    /**
     * vip有效标识[1有效,2无效]
     */
    @Column(name = "vip_mark")
    private Integer vipMark;

    /**
     * 班级有效期(月)
     */
    private Integer validity;

    /**
     * 1是老生优惠，2是没有优惠
     */
    @Column(name = "iosComId")
    private Integer ioscomid;

    /**
     * 老生优惠价格
     */
    @Column(name = "benefit_mark")
    private Integer benefitMark;

    @Column(name = "benefit_price")
    private Integer benefitPrice;

    /**
     * 是否应用于面试宝 0否；1是
     */
    @Column(name = "is_msb")
    private Integer isMsb;

    /**
     * 教材费
     */
    @Column(name = "book_price")
    private Double bookPrice;

    /**
     * 是否含有学习包[0:不含,1:包含]
     */
    @Column(name = "has_study_pack")
    private Integer hasStudyPack;

    /**
     * 学习包id
     */
    @Column(name = "study_pack_id")
    private Long studyPackId;

    /**
     * 描述2
     */
    @Column(name = "description_two")
    private String descriptionTwo;

    /**
     * 保险[0:无保险,1:有保险]
     */
    private Integer insurance;


    /**
     * 证书考试[0:无证书考试,1:有证书考试]
     */
    @Column(name = "cert_exam")
    private Integer certExam;

    /**
     * 证书考试费用
     */
    @Column(name = "cert_exam_expense")
    private Double certExamExpense;

    /**
     * 保过协议[0:无,1有]
     */
    @Column(name = "guarantee_aggrement")
    private Integer guaranteeAggrement;

    /**
     * 是否可使用优惠券[0:不可使用,1:可使用]
     */
    private Integer coupon;

    /**
     * 课表时间是否显示[0:不显示,1:显示]
     */
    @Column(name = "schedule_time")
    private Integer scheduleTime;

    /**
     * 班型优惠图
     */
    @Column(name = "cover_url_discount")
    private String coverUrlDiscount;

    /**
     * 班型优惠图使用[0:使用默认图,1:使用优惠图]
     */
    @Column(name = "cover_url_discount_use")
    private Integer coverUrlDiscountUse;

    /**
     * 班级论坛[0:无,1:有]
     */
    @Column(name = "class_forum")
    private Integer classForum;

    /**
     * 旁听规则[0:不能旁听,1:新班级,2:老班级,3:随意听]
     */
    @Column(name = "audit_rules")
    private Integer auditRules;

    /**
     * vip视频权限,例[{sku:1},{sku:2}]
     */
    @Column(name = "vip_courses")
    private String vipCourses;

    /**
     * 专属视频赠送,例[{course:1,course:2}]
     */
    @Column(name = "exclusive_courses")
    private String exclusiveCourses;

    /**
     * 状态[0:不可用[未完成服务],1:可用(完成服务配置)]
     */
    private Integer status;

    /**
     * 排序
     */
    @Column(name = "type_order")
    private Integer typeOrder;

    /**
     * 保质期协议[0:不显示,1:显示]
     */
    @Column(name = "guarantee_status")
    private Integer guaranteeStatus;

    /**
     * 退款协议[0:不显示,1:显示]
     */
    @Column(name = "refund_status")
    private Integer refundStatus;

    /**
     * 质保期模式[0:月份数，1:截止日期]
     */
    @Column(name = "mode")
    private Integer mode;

    /**
     * 是否启用客服[0无1有]
     */
    @Column(name = "has_service")
    private Integer	hasService;

    /**
     * 客服key
     */
    @Column(name = "service_key")
    private String	serviceKey;

    /**
     * 智齿客服类型[0:个人,1:小组（默认）]
     */
    @Column(name = "key_type")
    private Integer keyType;

    /**
     * 机器人Id
     */
    @Column(name = "robot_id")
    private String  robotId;

    /**
     * 接待模式[1:仅机器人2:仅人工3:优先机器人4:优先人工]
     */
    @Column(name = "reception_mode")
    private Integer  receptionMode;

    /**
     * 是否有上限[0无1有2零元购]
     */
    @Column(name = "has_limit")
    private Integer	hasLimit;

    /**
     * 允许用优惠券的最大金额
     */
    @Column(name = "limit_amount")
    private Integer	limitAmount;
    /**
     * 人脸识别[0:停用,1:启用]
     * */
    @Column(name = "face_recognition")
    private Integer faceRecognition;
    /**
     * 七天价保[0:无,1:有]
     */
    @Column(name = "price_protect")
    private Integer priceProtect;
    /**
     * 多类型质保期[0:普通,1:多类型]
     * */
    @Column(name = "gua_mul")
    private Integer guaMul;
    /**
     * 一类价格
     * */
    @Column(name = "first_price")
    private Double firstPrice;
    /**
     * 一类配置信息{title:标题，price:价格，gua:延保[0:否,1:是]，delay:延期[0:否,1:是]}
     * */
    @Column(name = "first_data")
    private String firstData;
    /**
     * '二类价格'
     * */
    @Column(name = "second_price")
    private Double secondPrice;
    /**
     * 二类配置信息{title:标题，price:价格，gua:延保[0:否,1:是]，delay:延期[0:否,1:是]}
     * */
    @Column(name = "second_data")
    private String secondData;

    /**
     * 优惠特权id
     * */
    @Column(name = "voucher_id")
    private Long voucherId;

    /**
     * 优惠券选择[0:不可用,1:可用]
     * */
    @Column(name = "voucher_able")
    private Integer voucherAble;

    /**
     * 教材类型[0:普通,1:区分初高中]
     */
    @Column(name="book_type")
    private Integer bookType;

    /**
     * 能否创建优惠特权[0:不能,1:能]
     */
    @Column(name = "voucher")
    private Integer voucher;

    public Integer getVoucher() {
        return voucher;
    }

    public void setVoucher(Integer voucher) {
        this.voucher = voucher;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return qq_num
     */
    public String getQqNum() {
        return qqNum;
    }

    /**
     * @param qqNum
     */
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
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
     * @return cover_url
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * @param coverUrl
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return base
     */
    public Integer getBase() {
        return base;
    }

    /**
     * @param base
     */
    public void setBase(Integer base) {
        this.base = base;
    }

    /**
     * @return course_code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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
     * @return vip_sku
     */
    public String getVipSku() {
        return vipSku;
    }

    /**
     * @param vipSku
     */
    public void setVipSku(String vipSku) {
        this.vipSku = vipSku;
    }

    /**
     * @return cover_url_two
     */
    public String getCoverUrlTwo() {
        return coverUrlTwo;
    }

    /**
     * @param coverUrlTwo
     */
    public void setCoverUrlTwo(String coverUrlTwo) {
        this.coverUrlTwo = coverUrlTwo;
    }

    /**
     * @return cover_url_three
     */
    public String getCoverUrlThree() {
        return coverUrlThree;
    }

    /**
     * @param coverUrlThree
     */
    public void setCoverUrlThree(String coverUrlThree) {
        this.coverUrlThree = coverUrlThree;
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
     * 获取vip有效标识[1有效,2无效]
     *
     * @return vip_mark - vip有效标识[1有效,2无效]
     */
    public Integer getVipMark() {
        return vipMark;
    }

    /**
     * 设置vip有效标识[1有效,2无效]
     *
     * @param vipMark vip有效标识[1有效,2无效]
     */
    public void setVipMark(Integer vipMark) {
        this.vipMark = vipMark;
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

    /**
     * 获取1是老生优惠，2是没有优惠
     *
     * @return iosComId - 1是老生优惠，2是没有优惠
     */
    public Integer getIoscomid() {
        return ioscomid;
    }

    /**
     * 设置1是老生优惠，2是没有优惠
     *
     * @param ioscomid 1是老生优惠，2是没有优惠
     */
    public void setIoscomid(Integer ioscomid) {
        this.ioscomid = ioscomid;
    }

    /**
     * 获取老生优惠价格
     *
     * @return benefit_mark - 老生优惠价格
     */
    public Integer getBenefitMark() {
        return benefitMark;
    }

    /**
     * 设置老生优惠价格
     *
     * @param benefitMark 老生优惠价格
     */
    public void setBenefitMark(Integer benefitMark) {
        this.benefitMark = benefitMark;
    }

    /**
     * @return benefit_price
     */
    public Integer getBenefitPrice() {
        return benefitPrice;
    }

    /**
     * @param benefitPrice
     */
    public void setBenefitPrice(Integer benefitPrice) {
        this.benefitPrice = benefitPrice;
    }

    /**
     * 获取是否应用于面试宝 0否；1是
     *
     * @return is_msb - 是否应用于面试宝 0否；1是
     */
    public Integer getIsMsb() {
        return isMsb;
    }

    /**
     * 设置是否应用于面试宝 0否；1是
     *
     * @param isMsb 是否应用于面试宝 0否；1是
     */
    public void setIsMsb(Integer isMsb) {
        this.isMsb = isMsb;
    }

    /**
     * 获取教材费
     *
     * @return book_price - 教材费
     */
    public Double getBookPrice() {
        return bookPrice;
    }

    /**
     * 设置教材费
     *
     * @param bookPrice 教材费
     */
    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * 获取是否含有学习包[0:不含,1:包含]
     *
     * @return has_study_pack - 是否含有学习包[0:不含,1:包含]
     */
    public Integer getHasStudyPack() {
        return hasStudyPack;
    }

    /**
     * 设置是否含有学习包[0:不含,1:包含]
     *
     * @param hasStudyPack 是否含有学习包[0:不含,1:包含]
     */
    public void setHasStudyPack(Integer hasStudyPack) {
        this.hasStudyPack = hasStudyPack;
    }

    /**
     * 获取学习包id
     *
     * @return study_pack_id - 学习包id
     */
    public Long getStudyPackId() {
        return studyPackId;
    }

    /**
     * 设置学习包id
     *
     * @param studyPackId 学习包id
     */
    public void setStudyPackId(Long studyPackId) {
        this.studyPackId = studyPackId;
    }

    /**
     * 获取描述2
     *
     * @return description_two - 描述2
     */
    public String getDescriptionTwo() {
        return descriptionTwo;
    }

    /**
     * 设置描述2
     *
     * @param descriptionTwo 描述2
     */
    public void setDescriptionTwo(String descriptionTwo) {
        this.descriptionTwo = descriptionTwo;
    }

    /**
     * 获取保险[0:无保险,1:有保险]
     *
     * @return insurance - 保险[0:无保险,1:有保险]
     */
    public Integer getInsurance() {
        return insurance;
    }

    /**
     * 设置保险[0:无保险,1:有保险]
     *
     * @param insurance 保险[0:无保险,1:有保险]
     */
    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
    }


    /**
     * 获取证书考试[0:无证书考试,1:有证书考试]
     *
     * @return cert_exam - 证书考试[0:无证书考试,1:有证书考试]
     */
    public Integer getCertExam() {
        return certExam;
    }

    /**
     * 设置证书考试[0:无证书考试,1:有证书考试]
     *
     * @param certExam 证书考试[0:无证书考试,1:有证书考试]
     */
    public void setCertExam(Integer certExam) {
        this.certExam = certExam;
    }

    /**
     * 获取证书考试费用
     *
     * @return cert_exam_expense - 证书考试费用
     */
    public Double getCertExamExpense() {
        return certExamExpense;
    }

    /**
     * 设置证书考试费用
     *
     * @param certExamExpense 证书考试费用
     */
    public void setCertExamExpense(Double certExamExpense) {
        this.certExamExpense = certExamExpense;
    }

    /**
     * 获取保过协议[0:无,1有]
     *
     * @return guarantee_aggrement - 保过协议[0:无,1有]
     */
    public Integer getGuaranteeAggrement() {
        return guaranteeAggrement;
    }

    /**
     * 设置保过协议[0:无,1有]
     *
     * @param guaranteeAggrement 保过协议[0:无,1有]
     */
    public void setGuaranteeAggrement(Integer guaranteeAggrement) {
        this.guaranteeAggrement = guaranteeAggrement;
    }

    /**
     * 获取是否可使用优惠券[0:不可使用,1:可使用]
     *
     * @return coupon - 是否可使用优惠券[0:不可使用,1:可使用]
     */
    public Integer getCoupon() {
        return coupon;
    }

    /**
     * 设置是否可使用优惠券[0:不可使用,1:可使用]
     *
     * @param coupon 是否可使用优惠券[0:不可使用,1:可使用]
     */
    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    /**
     * 获取课表时间是否显示[0:不显示,1:显示]
     *
     * @return schedule_time - 课表时间是否显示[0:不显示,1:显示]
     */
    public Integer getScheduleTime() {
        return scheduleTime;
    }

    /**
     * 设置课表时间是否显示[0:不显示,1:显示]
     *
     * @param scheduleTime 课表时间是否显示[0:不显示,1:显示]
     */
    public void setScheduleTime(Integer scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    /**
     * 获取班型优惠图
     *
     * @return cover_url_discount - 班型优惠图
     */
    public String getCoverUrlDiscount() {
        return coverUrlDiscount;
    }

    /**
     * 设置班型优惠图
     *
     * @param coverUrlDiscount 班型优惠图
     */
    public void setCoverUrlDiscount(String coverUrlDiscount) {
        this.coverUrlDiscount = coverUrlDiscount;
    }

    /**
     * 获取班型优惠图使用[0:使用默认图,1:使用优惠图]
     *
     * @return cover_url_discount_use - 班型优惠图使用[0:使用默认图,1:使用优惠图]
     */
    public Integer getCoverUrlDiscountUse() {
        return coverUrlDiscountUse;
    }

    /**
     * 设置班型优惠图使用[0:使用默认图,1:使用优惠图]
     *
     * @param coverUrlDiscountUse 班型优惠图使用[0:使用默认图,1:使用优惠图]
     */
    public void setCoverUrlDiscountUse(Integer coverUrlDiscountUse) {
        this.coverUrlDiscountUse = coverUrlDiscountUse;
    }

    /**
     * 获取班级论坛[0:无,1:有]
     *
     * @return class_forum - 班级论坛[0:无,1:有]
     */
    public Integer getClassForum() {
        return classForum;
    }

    /**
     * 设置班级论坛[0:无,1:有]
     *
     * @param classForum 班级论坛[0:无,1:有]
     */
    public void setClassForum(Integer classForum) {
        this.classForum = classForum;
    }

    /**
     * 获取旁听规则[0:不能旁听,1:新班级,2:老班级,3:随意听]
     *
     * @return audit_rules - 旁听规则[0:不能旁听,1:新班级,2:老班级,3:随意听]
     */
    public Integer getAuditRules() {
        return auditRules;
    }

    /**
     * 设置旁听规则[0:不能旁听,1:新班级,2:老班级,3:随意听]
     *
     * @param auditRules 旁听规则[0:不能旁听,1:新班级,2:老班级,3:随意听]
     */
    public void setAuditRules(Integer auditRules) {
        this.auditRules = auditRules;
    }

    /**
     * 获取vip视频权限,例[{sku:1},{sku:2}]
     *
     * @return vip_courses - vip视频权限,例[{sku:1},{sku:2}]
     */
    public String getVipCourses() {
        return vipCourses;
    }

    /**
     * 设置vip视频权限,例[{sku:1},{sku:2}]
     *
     * @param vipCourses vip视频权限,例[{sku:1},{sku:2}]
     */
    public void setVipCourses(String vipCourses) {
        this.vipCourses = vipCourses;
    }

    /**
     * 获取专属视频赠送,例[{course:1,course:2}]
     *
     * @return exclusive_courses - 专属视频赠送,例[{course:1,course:2}]
     */
    public String getExclusiveCourses() {
        return exclusiveCourses;
    }

    /**
     * 设置专属视频赠送,例[{course:1,course:2}]
     *
     * @param exclusiveCourses 专属视频赠送,例[{course:1,course:2}]
     */
    public void setExclusiveCourses(String exclusiveCourses) {
        this.exclusiveCourses = exclusiveCourses;
    }

    /**
     * 获取状态[0:不可用[未完成服务],1:可用(完成服务配置)]
     *
     * @return status - 状态[0:不可用[未完成服务],1:可用(完成服务配置)]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态[0:不可用[未完成服务],1:可用(完成服务配置)]
     *
     * @param status 状态[0:不可用[未完成服务],1:可用(完成服务配置)]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取排序
     *
     * @return type_order - 排序
     */
    public Integer getTypeOrder() {
        return typeOrder;
    }

    /**
     * 设置排序
     *
     * @param typeOrder 排序
     */
    public void setTypeOrder(Integer typeOrder) {
        this.typeOrder = typeOrder;
    }

    /**
     * 获取保质期协议[0:不显示,1:显示]
     *
     * @return guarantee_status - 保质期协议[0:不显示,1:显示]
     */
    public Integer getGuaranteeStatus() {
        return guaranteeStatus;
    }

    /**
     * 设置保质期协议[0:不显示,1:显示]
     *
     * @param guaranteeStatus 保质期协议[0:不显示,1:显示]
     */
    public void setGuaranteeStatus(Integer guaranteeStatus) {
        this.guaranteeStatus = guaranteeStatus;
    }

    /**
     * 获取退款协议[0:不显示,1:显示]
     *
     * @return refund_status - 退款协议[0:不显示,1:显示]
     */
    public Integer getRefundStatus() {
        return refundStatus;
    }

    /**
     * 设置退款协议[0:不显示,1:显示]
     *
     * @param refundStatus 退款协议[0:不显示,1:显示]
     */
    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getHasService() {
        return hasService;
    }

    public void setHasService(Integer hasService) {
        this.hasService = hasService;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public Integer getReceptionMode() {
        return receptionMode;
    }

    public void setReceptionMode(Integer receptionMode) {
        this.receptionMode = receptionMode;
    }

    public Integer getHasLimit() {
        return hasLimit;
    }

    public void setHasLimit(Integer hasLimit) {
        this.hasLimit = hasLimit;
    }

    public Integer getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Integer limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Integer getFaceRecognition() {
        return faceRecognition;
    }

    public void setFaceRecognition(Integer faceRecognition) {
        this.faceRecognition = faceRecognition;
    }

    public Integer getPriceProtect() {
        return priceProtect;
    }

    public void setPriceProtect(Integer priceProtect) {
        this.priceProtect = priceProtect;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Integer getGuaMul() {
        return guaMul;
    }

    public void setGuaMul(Integer guaMul) {
        this.guaMul = guaMul;
    }

    public Double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public String getFirstData() {
        return firstData;
    }

    public void setFirstData(String firstData) {
        this.firstData = firstData;
    }

    public Double getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Double secondPrice) {
        this.secondPrice = secondPrice;
    }

    public String getSecondData() {
        return secondData;
    }

    public void setSecondData(String secondData) {
        this.secondData = secondData;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getVoucherAble() {
        return voucherAble;
    }

    public void setVoucherAble(Integer voucherAble) {
        this.voucherAble = voucherAble;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}