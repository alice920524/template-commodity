package com.duia.commodity.model;

import javax.persistence.*;

@Table(name = "pay_order_mailing_address")
public class PayOrderMailingAddress {
    /**
     * PK，使用payOrderId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 收件人
     */
    private String addressee;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String county;

    /**
     * 详细地址
     */
    @Column(name = "detail_address")
    private String detailAddress;

    /**
     * 面试科目
     */
    private String subject;

    /**
     * 邮寄标识[1未寄2已寄]
     */
    @Column(name = "trans_mark")
    private Integer transMark;

    /**
     * 邮寄信息
     */
    @Column(name = "mail_order_info")
    private String mailOrderInfo;

    private String remark;

    @Column(name = "qq_num")
    private String qqNum;

    private String source;

    /**
     * 是否邮寄教材（或者说学习包）是否邮寄教材 0:学员不需要 1:需要 2:是班型不需要
     */
    @Column(name = "is_mail")
    private Integer isMail;

    /**
     * QQ验证密码
     */
    @Column(name = "qq_validate")
    private String qqValidate;

    /**
     * 获取PK，使用payOrderId
     *
     * @return id - PK，使用payOrderId
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置PK，使用payOrderId
     *
     * @param id PK，使用payOrderId
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取收件人
     *
     * @return addressee - 收件人
     */
    public String getAddressee() {
        return addressee;
    }

    /**
     * 设置收件人
     *
     * @param addressee 收件人
     */
    public void setAddressee(String addressee) {
        this.addressee = addressee;
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
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取县
     *
     * @return county - 县
     */
    public String getCounty() {
        return county;
    }

    /**
     * 设置县
     *
     * @param county 县
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * 获取详细地址
     *
     * @return detail_address - 详细地址
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * 设置详细地址
     *
     * @param detailAddress 详细地址
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    /**
     * 获取面试科目
     *
     * @return subject - 面试科目
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置面试科目
     *
     * @param subject 面试科目
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取邮寄标识[1未寄2已寄]
     *
     * @return trans_mark - 邮寄标识[1未寄2已寄]
     */
    public Integer getTransMark() {
        return transMark;
    }

    /**
     * 设置邮寄标识[1未寄2已寄]
     *
     * @param transMark 邮寄标识[1未寄2已寄]
     */
    public void setTransMark(Integer transMark) {
        this.transMark = transMark;
    }

    /**
     * 获取邮寄信息
     *
     * @return mail_order_info - 邮寄信息
     */
    public String getMailOrderInfo() {
        return mailOrderInfo;
    }

    /**
     * 设置邮寄信息
     *
     * @param mailOrderInfo 邮寄信息
     */
    public void setMailOrderInfo(String mailOrderInfo) {
        this.mailOrderInfo = mailOrderInfo;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
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
     * 获取QQ验证密码
     *
     * @return qq_validate - QQ验证密码
     */
    public String getQqValidate() {
        return qqValidate;
    }

    /**
     * 设置QQ验证密码
     *
     * @param qqValidate QQ验证密码
     */
    public void setQqValidate(String qqValidate) {
        this.qqValidate = qqValidate;
    }
}