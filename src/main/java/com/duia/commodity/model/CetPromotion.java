package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cet_promotion")
public class CetPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公众号openid,或者app的唯一标识码
     */
    private String openid;

    /**
     * 名称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户图像
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 学校
     */
    private String school;

    /**
     * 测试时间
     */
    private Date optime;

    /**
     * 总分数
     */
    @Column(name = "total_score")
    private Float totalScore;

    /**
     * 选词填空分数
     */
    @Column(name = "cloze_score")
    private Float clozeScore;

    /**
     * 阅读理解分数
     */
    @Column(name = "reading_score")
    private Float readingScore;

    /**
     * 翻译分数
     */
    @Column(name = "translate_score")
    private Float translateScore;

    /**
     * 听力分数
     */
    @Column(name = "listening_score")
    private Float listeningScore;

    /**
     * 是否进入测试页，1是；0否
     */
    @Column(name = "is_first")
    private Integer isFirst;

    /**
     * 是否再次测试，1是；0否
     */
    @Column(name = "is_again")
    private Integer isAgain;

    /**
     * 是否进入测试题1，1是；0否
     */
    @Column(name = "is_making")
    private Integer isMaking;

    /**
     * 是否完成做题，1是；0否
     */
    @Column(name = "is_finish")
    private Integer isFinish;

    /**
     * 是否分享，1是；0否
     */
    @Column(name = "is_share")
    private Integer isShare;

    /**
     * 是否点击彩蛋，1是；0否
     */
    @Column(name = "click_egg")
    private Integer clickEgg;

    /**
     * 是否点击购买，1是；0否
     */
    @Column(name = "click_buy")
    private Integer clickBuy;

    /**
     * 客户端平台
     */
    @Column(name = "app_type")
    private Integer appType;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取公众号openid,或者app的唯一标识码
     *
     * @return openid - 公众号openid,或者app的唯一标识码
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置公众号openid,或者app的唯一标识码
     *
     * @param openid 公众号openid,或者app的唯一标识码
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取名称
     *
     * @return nick_name - 名称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置名称
     *
     * @param nickName 名称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取用户图像
     *
     * @return img_url - 用户图像
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置用户图像
     *
     * @param imgUrl 用户图像
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取学校
     *
     * @return school - 学校
     */
    public String getSchool() {
        return school;
    }

    /**
     * 设置学校
     *
     * @param school 学校
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * 获取测试时间
     *
     * @return optime - 测试时间
     */
    public Date getOptime() {
        return optime;
    }

    /**
     * 设置测试时间
     *
     * @param optime 测试时间
     */
    public void setOptime(Date optime) {
        this.optime = optime;
    }

    /**
     * 获取总分数
     *
     * @return total_score - 总分数
     */
    public Float getTotalScore() {
        return totalScore;
    }

    /**
     * 设置总分数
     *
     * @param totalScore 总分数
     */
    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * 获取选词填空分数
     *
     * @return cloze_score - 选词填空分数
     */
    public Float getClozeScore() {
        return clozeScore;
    }

    /**
     * 设置选词填空分数
     *
     * @param clozeScore 选词填空分数
     */
    public void setClozeScore(Float clozeScore) {
        this.clozeScore = clozeScore;
    }

    /**
     * 获取阅读理解分数
     *
     * @return reading_score - 阅读理解分数
     */
    public Float getReadingScore() {
        return readingScore;
    }

    /**
     * 设置阅读理解分数
     *
     * @param readingScore 阅读理解分数
     */
    public void setReadingScore(Float readingScore) {
        this.readingScore = readingScore;
    }

    /**
     * 获取翻译分数
     *
     * @return translate_score - 翻译分数
     */
    public Float getTranslateScore() {
        return translateScore;
    }

    /**
     * 设置翻译分数
     *
     * @param translateScore 翻译分数
     */
    public void setTranslateScore(Float translateScore) {
        this.translateScore = translateScore;
    }

    public Float getListeningScore() {
        return listeningScore;
    }

    public void setListeningScore(Float listeningScore) {
        this.listeningScore = listeningScore;
    }

    /**
     * 获取是否进入测试页，1是；0否
     *
     * @return is_first - 是否进入测试页，1是；0否
     */
    public Integer getIsFirst() {
        return isFirst;
    }

    /**
     * 设置是否进入测试页，1是；0否
     *
     * @param isFirst 是否进入测试页，1是；0否
     */
    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    /**
     * 获取是否再次测试，1是；0否
     *
     * @return is_again - 是否再次测试，1是；0否
     */
    public Integer getIsAgain() {
        return isAgain;
    }

    /**
     * 设置是否再次测试，1是；0否
     *
     * @param isAgain 是否再次测试，1是；0否
     */
    public void setIsAgain(Integer isAgain) {
        this.isAgain = isAgain;
    }

    /**
     * 获取是否进入测试题1，1是；0否
     *
     * @return is_making - 是否进入测试题1，1是；0否
     */
    public Integer getIsMaking() {
        return isMaking;
    }

    /**
     * 设置是否进入测试题1，1是；0否
     *
     * @param isMaking 是否进入测试题1，1是；0否
     */
    public void setIsMaking(Integer isMaking) {
        this.isMaking = isMaking;
    }

    /**
     * 获取是否完成做题，1是；0否
     *
     * @return is_finish - 是否完成做题，1是；0否
     */
    public Integer getIsFinish() {
        return isFinish;
    }

    /**
     * 设置是否完成做题，1是；0否
     *
     * @param isFinish 是否完成做题，1是；0否
     */
    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    /**
     * 获取是否分享，1是；0否
     *
     * @return is_share - 是否分享，1是；0否
     */
    public Integer getIsShare() {
        return isShare;
    }

    /**
     * 设置是否分享，1是；0否
     *
     * @param isShare 是否分享，1是；0否
     */
    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    /**
     * 获取是否点击彩蛋，1是；0否
     *
     * @return click_egg - 是否点击彩蛋，1是；0否
     */
    public Integer getClickEgg() {
        return clickEgg;
    }

    /**
     * 设置是否点击彩蛋，1是；0否
     *
     * @param clickEgg 是否点击彩蛋，1是；0否
     */
    public void setClickEgg(Integer clickEgg) {
        this.clickEgg = clickEgg;
    }

    /**
     * 获取是否点击购买，1是；0否
     *
     * @return click_buy - 是否点击购买，1是；0否
     */
    public Integer getClickBuy() {
        return clickBuy;
    }

    /**
     * 设置是否点击购买，1是；0否
     *
     * @param clickBuy 是否点击购买，1是；0否
     */
    public void setClickBuy(Integer clickBuy) {
        this.clickBuy = clickBuy;
    }

    /**
     * 获取客户端平台
     *
     * @return app_type - 客户端平台
     */
    public Integer getAppType() {
        return appType;
    }

    /**
     * 设置客户端平台
     *
     * @param appType 客户端平台
     */
    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}