package com.duia.commodity.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sys_dict")
public class SysDict implements Serializable {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字典类型
     */
    @Column(name = "dic_type")
    private String dicType;

    /**
     * 字典代码
     */
    @Column(name = "dic_code")
    private Integer dicCode;

    /**
     * 字典名称
     */
    @Column(name = "dic_name")
    private String dicName;

    /**
     * 字典顺序
     */
    @Column(name = "dic_order")
    private Integer dicOrder;

    /**
     * 有效标记
     */
    @Column(name = "valid_sign")
    private Integer validSign;

    /**
     * 是否默认值
     */
    @Column(name = "is_default")
    private Integer isDefault;

    @Column(name = "dic_short")
    private String dicShort;

    private String code;

    @Column(name = "qqKey")
    private String qqkey;

    @Column(name = "mgrKey")
    private String mgrkey;

    @Column(name = "vip_sku")
    private String vipSku;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "dic_image")
    private String dicImage;

    @Column(name = "videoQqKey")
    private String videoqqkey;

    @Column(name = "videoQqKeyDesc")
    private String videoqqkeydesc;

    @Column(name = "duibaDesc")
    private String duibadesc;

    private Integer visible;

    @Column(name = "live_desc")
    private String liveDesc;

    private String forum;

    private String question;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "live_playback")
    private String livePlayback;

    @Column(name = "app_function")
    private String appFunction;

    @Column(name = "app_function_sp")
    private String appFunctionSp;

    @Column(name = "app_qq")
    private String appQq;

    @Column(name = "app_xiaoneng")
    private String appXiaoneng;

    @Column(name = "ad_href")
    private String adHref;

    @Column(name = "ad_img")
    private String adImg;

    @Column(name = "ad_bg")
    private String adBg;

    @Column(name = "app_baoban")
    private String appBaoban;

    @Column(name = "sp_href")
    private String spHref;

    @Column(name = "sp_img")
    private String spImg;

    @Column(name = "sp_bg")
    private String spBg;

    @Column(name = "domainUrl")
    private String domainurl;

    @Column(name = "tk_href")
    private String tkHref;

    @Column(name = "tk_img")
    private String tkImg;

    @Column(name = "tk_bg")
    private String tkBg;

    @Column(name = "live_playBack_new")
    private String livePlaybackNew;

    @Column(name = "web_teacher")
    private String webTeacher;

    private String template;

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
     * 获取字典类型
     *
     * @return dic_type - 字典类型
     */
    public String getDicType() {
        return dicType;
    }

    /**
     * 设置字典类型
     *
     * @param dicType 字典类型
     */
    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    /**
     * 获取字典代码
     *
     * @return dic_code - 字典代码
     */
    public Integer getDicCode() {
        return dicCode;
    }

    /**
     * 设置字典代码
     *
     * @param dicCode 字典代码
     */
    public void setDicCode(Integer dicCode) {
        this.dicCode = dicCode;
    }

    /**
     * 获取字典名称
     *
     * @return dic_name - 字典名称
     */
    public String getDicName() {
        return dicName;
    }

    /**
     * 设置字典名称
     *
     * @param dicName 字典名称
     */
    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    /**
     * 获取字典顺序
     *
     * @return dic_order - 字典顺序
     */
    public Integer getDicOrder() {
        return dicOrder;
    }

    /**
     * 设置字典顺序
     *
     * @param dicOrder 字典顺序
     */
    public void setDicOrder(Integer dicOrder) {
        this.dicOrder = dicOrder;
    }

    /**
     * 获取有效标记
     *
     * @return valid_sign - 有效标记
     */
    public Integer getValidSign() {
        return validSign;
    }

    /**
     * 设置有效标记
     *
     * @param validSign 有效标记
     */
    public void setValidSign(Integer validSign) {
        this.validSign = validSign;
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
     * @return dic_short
     */
    public String getDicShort() {
        return dicShort;
    }

    /**
     * @param dicShort
     */
    public void setDicShort(String dicShort) {
        this.dicShort = dicShort;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return qqKey
     */
    public String getQqkey() {
        return qqkey;
    }

    /**
     * @param qqkey
     */
    public void setQqkey(String qqkey) {
        this.qqkey = qqkey;
    }

    /**
     * @return mgrKey
     */
    public String getMgrkey() {
        return mgrkey;
    }

    /**
     * @param mgrkey
     */
    public void setMgrkey(String mgrkey) {
        this.mgrkey = mgrkey;
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
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return dic_image
     */
    public String getDicImage() {
        return dicImage;
    }

    /**
     * @param dicImage
     */
    public void setDicImage(String dicImage) {
        this.dicImage = dicImage;
    }

    /**
     * @return videoQqKey
     */
    public String getVideoqqkey() {
        return videoqqkey;
    }

    /**
     * @param videoqqkey
     */
    public void setVideoqqkey(String videoqqkey) {
        this.videoqqkey = videoqqkey;
    }

    /**
     * @return videoQqKeyDesc
     */
    public String getVideoqqkeydesc() {
        return videoqqkeydesc;
    }

    /**
     * @param videoqqkeydesc
     */
    public void setVideoqqkeydesc(String videoqqkeydesc) {
        this.videoqqkeydesc = videoqqkeydesc;
    }

    /**
     * @return duibaDesc
     */
    public String getDuibadesc() {
        return duibadesc;
    }

    /**
     * @param duibadesc
     */
    public void setDuibadesc(String duibadesc) {
        this.duibadesc = duibadesc;
    }

    /**
     * @return visible
     */
    public Integer getVisible() {
        return visible;
    }

    /**
     * @param visible
     */
    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    /**
     * @return live_desc
     */
    public String getLiveDesc() {
        return liveDesc;
    }

    /**
     * @param liveDesc
     */
    public void setLiveDesc(String liveDesc) {
        this.liveDesc = liveDesc;
    }

    /**
     * @return forum
     */
    public String getForum() {
        return forum;
    }

    /**
     * @param forum
     */
    public void setForum(String forum) {
        this.forum = forum;
    }

    /**
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return mobile_phone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return live_playback
     */
    public String getLivePlayback() {
        return livePlayback;
    }

    /**
     * @param livePlayback
     */
    public void setLivePlayback(String livePlayback) {
        this.livePlayback = livePlayback;
    }

    /**
     * @return app_function
     */
    public String getAppFunction() {
        return appFunction;
    }

    /**
     * @param appFunction
     */
    public void setAppFunction(String appFunction) {
        this.appFunction = appFunction;
    }

    /**
     * @return app_function_sp
     */
    public String getAppFunctionSp() {
        return appFunctionSp;
    }

    /**
     * @param appFunctionSp
     */
    public void setAppFunctionSp(String appFunctionSp) {
        this.appFunctionSp = appFunctionSp;
    }

    /**
     * @return app_qq
     */
    public String getAppQq() {
        return appQq;
    }

    /**
     * @param appQq
     */
    public void setAppQq(String appQq) {
        this.appQq = appQq;
    }

    /**
     * @return app_xiaoneng
     */
    public String getAppXiaoneng() {
        return appXiaoneng;
    }

    /**
     * @param appXiaoneng
     */
    public void setAppXiaoneng(String appXiaoneng) {
        this.appXiaoneng = appXiaoneng;
    }

    /**
     * @return ad_href
     */
    public String getAdHref() {
        return adHref;
    }

    /**
     * @param adHref
     */
    public void setAdHref(String adHref) {
        this.adHref = adHref;
    }

    /**
     * @return ad_img
     */
    public String getAdImg() {
        return adImg;
    }

    /**
     * @param adImg
     */
    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    /**
     * @return ad_bg
     */
    public String getAdBg() {
        return adBg;
    }

    /**
     * @param adBg
     */
    public void setAdBg(String adBg) {
        this.adBg = adBg;
    }

    /**
     * @return app_baoban
     */
    public String getAppBaoban() {
        return appBaoban;
    }

    /**
     * @param appBaoban
     */
    public void setAppBaoban(String appBaoban) {
        this.appBaoban = appBaoban;
    }

    /**
     * @return sp_href
     */
    public String getSpHref() {
        return spHref;
    }

    /**
     * @param spHref
     */
    public void setSpHref(String spHref) {
        this.spHref = spHref;
    }

    /**
     * @return sp_img
     */
    public String getSpImg() {
        return spImg;
    }

    /**
     * @param spImg
     */
    public void setSpImg(String spImg) {
        this.spImg = spImg;
    }

    /**
     * @return sp_bg
     */
    public String getSpBg() {
        return spBg;
    }

    /**
     * @param spBg
     */
    public void setSpBg(String spBg) {
        this.spBg = spBg;
    }

    /**
     * @return domainUrl
     */
    public String getDomainurl() {
        return domainurl;
    }

    /**
     * @param domainurl
     */
    public void setDomainurl(String domainurl) {
        this.domainurl = domainurl;
    }

    /**
     * @return tk_href
     */
    public String getTkHref() {
        return tkHref;
    }

    /**
     * @param tkHref
     */
    public void setTkHref(String tkHref) {
        this.tkHref = tkHref;
    }

    /**
     * @return tk_img
     */
    public String getTkImg() {
        return tkImg;
    }

    /**
     * @param tkImg
     */
    public void setTkImg(String tkImg) {
        this.tkImg = tkImg;
    }

    /**
     * @return tk_bg
     */
    public String getTkBg() {
        return tkBg;
    }

    /**
     * @param tkBg
     */
    public void setTkBg(String tkBg) {
        this.tkBg = tkBg;
    }

    /**
     * @return live_playBack_new
     */
    public String getLivePlaybackNew() {
        return livePlaybackNew;
    }

    /**
     * @param livePlaybackNew
     */
    public void setLivePlaybackNew(String livePlaybackNew) {
        this.livePlaybackNew = livePlaybackNew;
    }

    /**
     * @return web_teacher
     */
    public String getWebTeacher() {
        return webTeacher;
    }

    /**
     * @param webTeacher
     */
    public void setWebTeacher(String webTeacher) {
        this.webTeacher = webTeacher;
    }

    /**
     * @return template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template
     */
    public void setTemplate(String template) {
        this.template = template;
    }
}