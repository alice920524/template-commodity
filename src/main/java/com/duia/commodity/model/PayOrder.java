package com.duia.commodity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "pay_order")
public class PayOrder implements Serializable {

     /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 级别[0:普通,1:父订单,2:子订单]
     */
    @Column(name = "order_type")
    private Integer orderType;

    /**
     * 下单时间
     */
    @Column(name = "order_time")
    private String orderTime;

    /**
     * 订单号
     */
    @Column(name = "order_num")
    private String orderNum;

    /**
     * 购买模式[0:普通购，1:零元购]
     */
    @Column(name = "buy_mode")
    private Integer buyMode;

    /**
     * 促销类型[0:未参与促销,1:套餐,2组合,4:分享购,7:抢购]
     */
    private Integer promotion;
    private Long promotionId;

    @Column(name = "pay_num")
    private String payNum;

    /**
     * 支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     */
    @Column(name = "pay_status")
    private String payStatus;

    /**
     * 支付类型(pay_type_cod:货到付款;pay_type_zfb:支付宝;pay_type_yzf_bank:网上银行;pay_type_yhfk:银行付款;pay_type_kq:快钱)
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 方案名称
     */
    @Column(name = "program_name")
    private String programName;

    /**
     * 方案ID
     */
    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "course_type")
    private Integer courseType;// 班型类型

    /**
     * 项目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 项目ID
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 实际支付价格
     */
    @Column(name = "realpay_price")
    private Double realpayPrice;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private String payTime;

    @Column(name = "is_old_user")
    private Integer isOldUser;

    @Column(name = "benefit_price")
    private Integer benefitPrice;

    /**
     * 支付商品类型(p:商品v:视频,y:YY直播,e:题库)
     */
    private String type;

    /**
     * 创建人
     */
    @Column(name = "creator")
    private Long creator;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    private String phone;

    private String qq;

    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "bank_type")
    private String bankType;

    @Column(name = "pay_discount_detail_id")
    private Long payDiscountDetailId;

    @Column(name = "voucher_order")
    private Integer voucherOrder;

    @Column(name = "voucher_detail_id")
    private Long voucherDetailId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "authority_user_id")
    private Long authorityUserId;

    @Column(name = "teacherid")
    private Long teacherid;

    @Column(name = "teachername")
    private String teachername;

    private Date deadline;

    private String voucher;

    @Column(name = "audit_status")
    private String auditStatus;

    @Column(name = "back_content")
    private String backContent;

    @Column(name = "back_price")
    private Double backPrice;

    @Column(name = "back_type")
    private String backType;

    private String ip;

    private String address;

    @Column(name = "delete_mark")
    private Integer deleteMark;

    /**
     * 分批支付父ID
     */
    @Column(name = "order_parent_id")
    private Long orderParentId;

    /**
     * 分批支付 第几个子订单
     */
    @Column(name = "order_batch")
    private Integer orderBatch;

    /**
     * 分期付款当前期数 0：未分期
     */
    @Column(name = "order_instal")
    private Integer orderInstal;

    /**
     * 订单来源
     */
    private String source;

    /**
     * 操作系统[0:pc,1:ios,2:android]
     */
    private Integer os;

    /**
     * 存放购买得商品、班级升级的存放班级升级id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 转订单id（旧订单上标记新单的id）
     */
    @Column(name = "turn_order_id")
    private Long turnOrderId;

    /**
     * 是否是转订单1是2否
     */
    @Column(name = "is_turn_order")
    private Integer isTurnOrder;

    /**
     * 原单余额
     */
    @Column(name = "old_balance")
    private Double oldBalance;

    /**
     * 是否老订单1：是老订单，2：新单（新后台上线后成交的订单）
     */
    @Column(name = "is_old")
    private Integer isOld;

    /**
     * 学习包总价格(默认值是0)重点
     */
    @Column(name = "study_package_price")
    private Double studyPackagePrice;

    /**
     * 是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     */
    @Column(name = "is_mail")
    private Integer isMail;

    /**
     * qq验证信息
     */
    @Column(name = "qq_validate")
    private String qqValidate;

    /**
     * 微信公众号openId
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 贷款期数
     */
    private Integer duration;

    /**
     * 分期利率
     */
    private Double rate;


    /**
     * 多凭证第几笔【0:父 1，2，3笔】
     */
    @Column(name = "order_multi")
    private Integer orderMulti;

    /**
     * 备注
     */
    private String remark;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取级别[0:普通,1:父订单,2:子订单]
     *
     * @return order_type - 级别[0:普通,1:父订单,2:子订单]
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置级别[0:普通,1:父订单,2:子订单]
     *
     * @param orderType 级别[0:普通,1:父订单,2:子订单]
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取下单时间
     *
     * @return order_time - 下单时间
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * 设置下单时间
     *
     * @param orderTime 下单时间
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取订单号
     *
     * @return order_num - 订单号
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 设置订单号
     *
     * @param orderNum 订单号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取购买模式[0:普通购,1:零元购]
     *
     * @return buy_mode - 购买模式[0:普通购,1:零元购]
     */
    public Integer getBuyMode() {
        return buyMode;
    }

    /**
     * 设置购买模式[0:普通购,1:零元购]
     *
     * @param buyMode 购买模式[0:普通购,1:零元购]
     */
    public void setBuyMode(Integer buyMode) {
        this.buyMode = buyMode;
    }

    /**
     * 获取促销类型[0:未参与促销,1:套餐,2组合,4:分享购]
     *
     * @return promotion - 促销类型[0:未参与促销,1:套餐,2组合,4:分享购]
     */
    public Integer getPromotion() {
        return promotion;
    }

    /**
     * 设置促销类型[0:未参与促销,1:套餐,2组合,4:分享购]
     *
     * @param promotion 促销类型[0:未参与促销,1:套餐,2组合,4:分享购]
     */
    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    /**
     * @return pay_num
     */
    public String getPayNum() {
        return payNum;
    }

    /**
     * @param payNum
     */
    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    /**
     * 获取支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     *
     * @return pay_status - 支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     *
     * @param payStatus 支付状态(pay_status_non:未支付;pay_status_failed:支付失败;pay_status_success;支付成功)
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取支付类型(pay_type_cod:货到付款;pay_type_zfb:支付宝;pay_type_yzf_bank:网上银行;pay_type_yhfk:银行付款;pay_type_kq:快钱)
     *
     * @return pay_type - 支付类型(pay_type_cod:货到付款;pay_type_zfb:支付宝;pay_type_yzf_bank:网上银行;pay_type_yhfk:银行付款;pay_type_kq:快钱)
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付类型(pay_type_cod:货到付款;pay_type_zfb:支付宝;pay_type_yzf_bank:网上银行;pay_type_yhfk:银行付款;pay_type_kq:快钱)
     *
     * @param payType 支付类型(pay_type_cod:货到付款;pay_type_zfb:支付宝;pay_type_yzf_bank:网上银行;pay_type_yhfk:银行付款;pay_type_kq:快钱)
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取方案名称
     *
     * @return program_name - 方案名称
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * 设置方案名称
     *
     * @param programName 方案名称
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * 获取方案ID
     *
     * @return program_id - 方案ID
     */
    public Integer getProgramId() {
        return programId;
    }

    /**
     * 设置方案ID
     *
     * @param programId 方案ID
     */
    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    /**
     * 获取项目名称
     *
     * @return category_name - 项目名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置项目名称
     *
     * @param categoryName 项目名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取项目ID
     *
     * @return category_id - 项目ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置项目ID
     *
     * @param categoryId 项目ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取实际支付价格
     *
     * @return realpay_price - 实际支付价格
     */
    public Double getRealpayPrice() {
        return realpayPrice;
    }

    /**
     * 设置实际支付价格
     *
     * @param realpayPrice 实际支付价格
     */
    public void setRealpayPrice(Double realpayPrice) {
        this.realpayPrice = realpayPrice;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public String getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    /**
     * @return is_old_user
     */
    public Integer getIsOldUser() {
        return isOldUser;
    }

    /**
     * @param isOldUser
     */
    public void setIsOldUser(Integer isOldUser) {
        this.isOldUser = isOldUser;
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
     * 获取支付商品类型(p:商品v:视频,y:YY直播,e:题库)
     *
     * @return type - 支付商品类型(p:商品v:视频,y:YY直播,e:题库)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置支付商品类型(p:商品v:视频,y:YY直播,e:题库)
     *
     * @param type 支付商品类型(p:商品v:视频,y:YY直播,e:题库)
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @return cost_price
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * @return bank_type
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * @param bankType
     */
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    /**
     * @return pay_discount_detail_id
     */
    public Long getPayDiscountDetailId() {
        return payDiscountDetailId;
    }

    /**
     * @param payDiscountDetailId
     */
    public void setPayDiscountDetailId(Long payDiscountDetailId) {
        this.payDiscountDetailId = payDiscountDetailId;
    }

    public Integer getVoucherOrder() {
        return voucherOrder;
    }

    public void setVoucherOrder(Integer voucherOrder) {
        this.voucherOrder = voucherOrder;
    }

    public Long getVoucherDetailId() {
        return voucherDetailId;
    }

    public void setVoucherDetailId(Long voucherDetailId) {
        this.voucherDetailId = voucherDetailId;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getAuthorityUserId() {
        return authorityUserId;
    }

    public void setAuthorityUserId(Long authorityUserId) {
        this.authorityUserId = authorityUserId;
    }

    /**
     * @return teacherid
     */
    public Long getTeacherid() {
        return teacherid;
    }

    /**
     * @param teacherid
     */
    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * @return teachername
     */
    public String getTeachername() {
        return teachername;
    }

    /**
     * @param teachername
     */
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    /**
     * @return deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return voucher
     */
    public String getVoucher() {
        return voucher;
    }

    /**
     * @param voucher
     */
    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    /**
     * @return audit_status
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * @param auditStatus
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @return back_content
     */
    public String getBackContent() {
        return backContent;
    }

    /**
     * @param backContent
     */
    public void setBackContent(String backContent) {
        this.backContent = backContent;
    }

    /**
     * @return back_price
     */
    public Double getBackPrice() {
        return backPrice;
    }

    /**
     * @param backPrice
     */
    public void setBackPrice(Double backPrice) {
        this.backPrice = backPrice;
    }

    /**
     * @return back_type
     */
    public String getBackType() {
        return backType;
    }

    /**
     * @param backType
     */
    public void setBackType(String backType) {
        this.backType = backType;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return delete_mark
     */
    public Integer getDeleteMark() {
        return deleteMark;
    }

    /**
     * @param deleteMark
     */
    public void setDeleteMark(Integer deleteMark) {
        this.deleteMark = deleteMark;
    }

    /**
     * 获取分批支付父ID
     *
     * @return order_parent_id - 分批支付父ID
     */
    public Long getOrderParentId() {
        return orderParentId;
    }

    /**
     * 设置分批支付父ID
     *
     * @param orderParentId 分批支付父ID
     */
    public void setOrderParentId(Long orderParentId) {
        this.orderParentId = orderParentId;
    }

    /**
     * 获取分批支付 第几个子订单
     *
     * @return order_batch - 分批支付 第几个子订单
     */
    public Integer getOrderBatch() {
        return orderBatch;
    }

    /**
     * 设置分批支付 第几个子订单
     *
     * @param orderBatch 分批支付 第几个子订单
     */
    public void setOrderBatch(Integer orderBatch) {
        this.orderBatch = orderBatch;
    }

    /**
     * 获取分期付款当前期数 0：未分期
     *
     * @return order_instal - 分期付款当前期数 0：未分期
     */
    public Integer getOrderInstal() {
        return orderInstal;
    }

    /**
     * 设置分期付款当前期数 0：未分期
     *
     * @param orderInstal 分期付款当前期数 0：未分期
     */
    public void setOrderInstal(Integer orderInstal) {
        this.orderInstal = orderInstal;
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
     * 获取操作系统[0:pc,1:ios,2:android]
     *
     * @return os - 操作系统[0:pc,1:ios,2:android]
     */
    public Integer getOs() {
        return os;
    }

    /**
     * 设置操作系统[0:pc,1:ios,2:android]
     *
     * @param os 操作系统[0:pc,1:ios,2:android]
     */
    public void setOs(Integer os) {
        this.os = os;
    }

    /**
     * 获取存放购买得商品、班级升级的存放班级升级id
     *
     * @return product_id - 存放购买得商品、班级升级的存放班级升级id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置存放购买得商品、班级升级的存放班级升级id
     *
     * @param productId 存放购买得商品、班级升级的存放班级升级id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取转订单id（旧订单上标记新单的id）
     *
     * @return turn_order_id - 转订单id（旧订单上标记新单的id）
     */
    public Long getTurnOrderId() {
        return turnOrderId;
    }

    /**
     * 设置转订单id（旧订单上标记新单的id）
     *
     * @param turnOrderId 转订单id（旧订单上标记新单的id）
     */
    public void setTurnOrderId(Long turnOrderId) {
        this.turnOrderId = turnOrderId;
    }

    /**
     * 获取是否是转订单1是2否
     *
     * @return is_turn_order - 是否是转订单1是2否
     */
    public Integer getIsTurnOrder() {
        return isTurnOrder;
    }

    /**
     * 设置是否是转订单1是2否
     *
     * @param isTurnOrder 是否是转订单1是2否
     */
    public void setIsTurnOrder(Integer isTurnOrder) {
        this.isTurnOrder = isTurnOrder;
    }

    /**
     * 获取原单余额
     *
     * @return old_balance - 原单余额
     */
    public Double getOldBalance() {
        return oldBalance;
    }

    /**
     * 设置原单余额
     *
     * @param oldBalance 原单余额
     */
    public void setOldBalance(Double oldBalance) {
        this.oldBalance = oldBalance;
    }

    /**
     * 获取是否老订单1：是老订单，2：新单（新后台上线后成交的订单）
     *
     * @return is_old - 是否老订单1：是老订单，2：新单（新后台上线后成交的订单）
     */
    public Integer getIsOld() {
        return isOld;
    }

    /**
     * 设置是否老订单1：是老订单，2：新单（新后台上线后成交的订单）
     *
     * @param isOld 是否老订单1：是老订单，2：新单（新后台上线后成交的订单）
     */
    public void setIsOld(Integer isOld) {
        this.isOld = isOld;
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
     * 获取是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     *
     * @return is_mail - 是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     */
    public Integer getIsMail() {
        return isMail;
    }

    /**
     * 设置是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     *
     * @param isMail 是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     */
    public void setIsMail(Integer isMail) {
        this.isMail = isMail;
    }

    /**
     * 获取qq验证信息
     *
     * @return qq_validate - qq验证信息
     */
    public String getQqValidate() {
        return qqValidate;
    }

    /**
     * 设置qq验证信息
     *
     * @param qqValidate qq验证信息
     */
    public void setQqValidate(String qqValidate) {
        this.qqValidate = qqValidate;
    }

    /**
     * 获取微信公众号openId
     *
     * @return open_id - 微信公众号openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信公众号openId
     *
     * @param openId 微信公众号openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取贷款期数
     *
     * @return duration - 贷款期数
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置贷款期数
     *
     * @param duration 贷款期数
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 获取分期利率
     *
     * @return rate - 分期利率
     */
    public Double getRate() {
        return rate;
    }

    /**
     * 设置分期利率
     *
     * @param rate 分期利率
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }


    /**
     * 获取多凭证第几笔【0:父 1，2，3笔】
     *
     * @return order_multi - 多凭证第几笔【0:父 1，2，3笔】
     */
    public Integer getOrderMulti() {
        return orderMulti;
    }

    /**
     * 设置多凭证第几笔【0:父 1，2，3笔】
     *
     * @param orderMulti 多凭证第几笔【0:父 1，2，3笔】
     */
    public void setOrderMulti(Integer orderMulti) {
        this.orderMulti = orderMulti;
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

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }
}