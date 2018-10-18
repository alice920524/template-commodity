package com.duia.commodity.model;

import javax.persistence.*;

@Table(name = "book_order")
public class BookOrder {
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
     * 下单时间
     */
    @Column(name = "order_time")
    private String orderTime;

    /**
     * 支付流水号
     */
    @Column(name = "order_num")
    private String orderNum;

    /**
     * 订单号
     */
    @Column(name = "pay_num")
    private String payNum;

    /**
     * 支付状态[pay_status_non:未支付;pay_status_success:支付成功]
     */
    @Column(name = "pay_status")
    private String payStatus;

    /**
     * 支付类型(pay_type_zfb:支付宝;pay_type_wxzf:APP微信支付)
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
    private Long programId;

    @Column(name = "cover_url")
    private String coverUrl;



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
     * 原始价格
     */
    @Column(name = "realpay_price")
    private Double realpayPrice;

    /**
     * 实付金额
     */
    @Column(name = "cost_price")
    private Double costPrice;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private String payTime;

    @Column(name = "open_id")
    private String openId;

    /**
     * 备注
     */
    private String remark;

    private String ip;

    private String address;

    @Column(name = "delete_mark")
    private Integer deleteMark;

    /**
     * 订单来源
     */
    private String source;

    /**
     * 平台[1:ios,2:android]
     */
    private Integer os;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 创建人
     */
    private Long creator;



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
     * 获取支付流水号
     *
     * @return order_num - 支付流水号
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 设置支付流水号
     *
     * @param orderNum 支付流水号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取订单号
     *
     * @return pay_num - 订单号
     */
    public String getPayNum() {
        return payNum;
    }

    /**
     * 设置订单号
     *
     * @param payNum 订单号
     */
    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    /**
     * 获取支付状态[pay_status_non:未支付;pay_status_success:支付成功]
     *
     * @return pay_status - 支付状态[pay_status_non:未支付;pay_status_success:支付成功]
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态[pay_status_non:未支付;pay_status_success:支付成功]
     *
     * @param payStatus 支付状态[pay_status_non:未支付;pay_status_success:支付成功]
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取支付类型(pay_type_zfb:支付宝;pay_type_wxzf:APP微信支付)
     *
     * @return pay_type - 支付类型(pay_type_zfb:支付宝;pay_type_wxzf:APP微信支付)
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付类型(pay_type_zfb:支付宝;pay_type_wxzf:APP微信支付)
     *
     * @param payType 支付类型(pay_type_zfb:支付宝;pay_type_wxzf:APP微信支付)
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
    public Long getProgramId() {
        return programId;
    }

    /**
     * 设置方案ID
     *
     * @param programId 方案ID
     */
    public void setProgramId(Long programId) {
        this.programId = programId;
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
     * 获取原始价格
     *
     * @return realpay_price - 原始价格
     */
    public Double getRealpayPrice() {
        return realpayPrice;
    }

    /**
     * 设置原始价格
     *
     * @param realpayPrice 原始价格
     */
    public void setRealpayPrice(Double realpayPrice) {
        this.realpayPrice = realpayPrice;
    }

    /**
     * 获取实付金额
     *
     * @return cost_price - 实付金额
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * 设置实付金额
     *
     * @param costPrice 实付金额
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
     * 获取平台[1:ios,2:android]
     *
     * @return os - 平台[1:ios,2:android]
     */
    public Integer getOs() {
        return os;
    }

    /**
     * 设置平台[1:ios,2:android]
     *
     * @param os 平台[1:ios,2:android]
     */
    public void setOs(Integer os) {
        this.os = os;
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
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

}