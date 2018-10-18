package com.duia.commodity.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_mailing_address")
public class UserMailingAddress implements Serializable {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

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
     * 是否默认值
     */
    @Column(name = "is_default")
    private Integer isDefault;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除标识位
     */
    @Column(name = "del_flg")
    private Integer delFlg;

    /**
     * QQ号
     */
    @Column(name = "qq_num")
    private String qqNum;

    /**
     * QQ校验信息
     */
    @Column(name = "qq_validate")
    private String qqValidate;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * 获取是否默认值
     *
     * @return is_default - 是否默认值
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认值
     *
     * @param isDefault 是否默认值
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取逻辑删除标识位
     *
     * @return del_flg - 逻辑删除标识位
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 设置逻辑删除标识位
     *
     * @param delFlg 逻辑删除标识位
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 获取QQ号
     *
     * @return qq_num - QQ号
     */
    public String getQqNum() {
        return qqNum;
    }

    /**
     * 设置QQ号
     *
     * @param qqNum QQ号
     */
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }


    public String getQqValidate() {
        return qqValidate;
    }

    public void setQqValidate(String qqValidate) {
        this.qqValidate = qqValidate;
    }
}