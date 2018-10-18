package com.duia.commodity.model;

import javax.persistence.*;

@Table(name = "book_order_mailing_address")
public class BookOrderMailingAddress {
    /**
     * PK，使用bookOrderId
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
     * 邮寄标识[1:未寄,2:已寄]
     */
    @Column(name = "trans_mark")
    private Integer transMark;

    /**
     * 邮寄信息
     */
    @Column(name = "mail_order_info")
    private String mailOrderInfo;

    private String remark;

    private String source;

    /**
     * 获取PK，使用bookOrderId
     *
     * @return id - PK，使用bookOrderId
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置PK，使用bookOrderId
     *
     * @param id PK，使用bookOrderId
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
     * 获取邮寄标识[1:未寄,2:已寄]
     *
     * @return trans_mark - 邮寄标识[1:未寄,2:已寄]
     */
    public Integer getTransMark() {
        return transMark;
    }

    /**
     * 设置邮寄标识[1:未寄,2:已寄]
     *
     * @param transMark 邮寄标识[1:未寄,2:已寄]
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
}