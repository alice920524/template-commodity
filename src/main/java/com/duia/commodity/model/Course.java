package com.duia.commodity.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Course implements Serializable {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 课程标题
     */
    private String title;

    /**
     * 课程副标题
     */
    private String subtitle;

    /**
     * 课程的作者
     */
    private Integer userid;

    /**
     * 课程关键词，便于搜索
     */
    private String keywords;

    /**
     * 1、会计 2、心理
     */
    private Integer category;

    /**
     * 该作者所有课程的顺序
     */
    @Column(name = "order_index")
    private Integer orderIndex;

    /**
     * 1、初级 2、中级 3、高级 4、所有级别
     */
    private Integer level;

    /**
     * 存储课程封面图片的url地址
     */
    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "video_id")
    private String videoId;

    /**
     * 存储课程宣传片视频的url地址
     */
    @Column(name = "video_url")
    private String videoUrl;

    /**
     * 0、未发布1、已发布
     */
    @Column(name = "publish_state")
    private Integer publishState;

    /**
     * 发布课程时维护该字段
     */
    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 取消发布课程时维护该字段
     */
    @Column(name = "publish_cancel_time")
    private Date publishCancelTime;

    /**
     * 0、免费课程 1、vip；  2 收费课程
     */
    private Integer type;

    private Double price;

    /**
     * 学生没收藏一次该课程，则该字段加1
     */
    @Column(name = "students_num")
    private Integer studentsNum;

    /**
     * 分数是学员打的平均分
     */
    private Integer score;

    /**
     * 存储字典表里的代码??????有??标记）针对企业注册时的邮箱验证 0：未验证；1：已验证
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 验证码或验证url发送时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_headline")
    private String authorHeadline;

    @Column(name = "author_pic")
    private String authorPic;

    @Column(name = "score_num")
    private Integer scoreNum;

    private Integer series;

    @Column(name = "qqKey")
    private String qqkey;

    @Column(name = "qqTitle")
    private String qqtitle;

    @Column(name = "qqImgUrl")
    private String qqimgurl;

    @Column(name = "qq_describe")
    private String qqDescribe;

    @Column(name = "weixin_img_url")
    private String weixinImgUrl;

    @Column(name = "tempPrice")
    private Double tempprice;

    @Column(name = "adPicture")
    private String adpicture;

    @Column(name = "adHref")
    private String adhref;

    /**
     * 播放器类型(1:cc播放器 2:乐视播放器)
     */
    @Column(name = "player_type")
    private Integer playerType;

    /**
     * 有效期，以月为单位
     */
    @Column(name = "effective_time")
    private Integer effectiveTime;

    /**
     * 课程的概要介绍
     */
    private String summary;

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
     * 获取课程标题
     *
     * @return title - 课程标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置课程标题
     *
     * @param title 课程标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取课程副标题
     *
     * @return subtitle - 课程副标题
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 设置课程副标题
     *
     * @param subtitle 课程副标题
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取课程的作者
     *
     * @return userid - 课程的作者
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置课程的作者
     *
     * @param userid 课程的作者
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取课程关键词，便于搜索
     *
     * @return keywords - 课程关键词，便于搜索
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置课程关键词，便于搜索
     *
     * @param keywords 课程关键词，便于搜索
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取1、会计 2、心理
     *
     * @return category - 1、会计 2、心理
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 设置1、会计 2、心理
     *
     * @param category 1、会计 2、心理
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 获取该作者所有课程的顺序
     *
     * @return order_index - 该作者所有课程的顺序
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }

    /**
     * 设置该作者所有课程的顺序
     *
     * @param orderIndex 该作者所有课程的顺序
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * 获取1、初级 2、中级 3、高级 4、所有级别
     *
     * @return level - 1、初级 2、中级 3、高级 4、所有级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置1、初级 2、中级 3、高级 4、所有级别
     *
     * @param level 1、初级 2、中级 3、高级 4、所有级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取存储课程封面图片的url地址
     *
     * @return cover_url - 存储课程封面图片的url地址
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置存储课程封面图片的url地址
     *
     * @param coverUrl 存储课程封面图片的url地址
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * @return video_id
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * @param videoId
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取存储课程宣传片视频的url地址
     *
     * @return video_url - 存储课程宣传片视频的url地址
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 设置存储课程宣传片视频的url地址
     *
     * @param videoUrl 存储课程宣传片视频的url地址
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 获取0、未发布1、已发布
     *
     * @return publish_state - 0、未发布1、已发布
     */
    public Integer getPublishState() {
        return publishState;
    }

    /**
     * 设置0、未发布1、已发布
     *
     * @param publishState 0、未发布1、已发布
     */
    public void setPublishState(Integer publishState) {
        this.publishState = publishState;
    }

    /**
     * 获取发布课程时维护该字段
     *
     * @return publish_time - 发布课程时维护该字段
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置发布课程时维护该字段
     *
     * @param publishTime 发布课程时维护该字段
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取取消发布课程时维护该字段
     *
     * @return publish_cancel_time - 取消发布课程时维护该字段
     */
    public Date getPublishCancelTime() {
        return publishCancelTime;
    }

    /**
     * 设置取消发布课程时维护该字段
     *
     * @param publishCancelTime 取消发布课程时维护该字段
     */
    public void setPublishCancelTime(Date publishCancelTime) {
        this.publishCancelTime = publishCancelTime;
    }

    /**
     * 获取0、免费课程 1、vip；  2 收费课程
     *
     * @return type - 0、免费课程 1、vip；  2 收费课程
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0、免费课程 1、vip；  2 收费课程
     *
     * @param type 0、免费课程 1、vip；  2 收费课程
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 获取学生没收藏一次该课程，则该字段加1
     *
     * @return students_num - 学生没收藏一次该课程，则该字段加1
     */
    public Integer getStudentsNum() {
        return studentsNum;
    }

    /**
     * 设置学生没收藏一次该课程，则该字段加1
     *
     * @param studentsNum 学生没收藏一次该课程，则该字段加1
     */
    public void setStudentsNum(Integer studentsNum) {
        this.studentsNum = studentsNum;
    }

    /**
     * 获取分数是学员打的平均分
     *
     * @return score - 分数是学员打的平均分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置分数是学员打的平均分
     *
     * @param score 分数是学员打的平均分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取存储字典表里的代码??????有??标记）针对企业注册时的邮箱验证 0：未验证；1：已验证
     *
     * @return create_date - 存储字典表里的代码??????有??标记）针对企业注册时的邮箱验证 0：未验证；1：已验证
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置存储字典表里的代码??????有??标记）针对企业注册时的邮箱验证 0：未验证；1：已验证
     *
     * @param createDate 存储字典表里的代码??????有??标记）针对企业注册时的邮箱验证 0：未验证；1：已验证
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取验证码或验证url发送时间
     *
     * @return update_date - 验证码或验证url发送时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置验证码或验证url发送时间
     *
     * @param updateDate 验证码或验证url发送时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return author_name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * @return author_headline
     */
    public String getAuthorHeadline() {
        return authorHeadline;
    }

    /**
     * @param authorHeadline
     */
    public void setAuthorHeadline(String authorHeadline) {
        this.authorHeadline = authorHeadline;
    }

    /**
     * @return author_pic
     */
    public String getAuthorPic() {
        return authorPic;
    }

    /**
     * @param authorPic
     */
    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    /**
     * @return score_num
     */
    public Integer getScoreNum() {
        return scoreNum;
    }

    /**
     * @param scoreNum
     */
    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    /**
     * @return series
     */
    public Integer getSeries() {
        return series;
    }

    /**
     * @param series
     */
    public void setSeries(Integer series) {
        this.series = series;
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
     * @return qqTitle
     */
    public String getQqtitle() {
        return qqtitle;
    }

    /**
     * @param qqtitle
     */
    public void setQqtitle(String qqtitle) {
        this.qqtitle = qqtitle;
    }

    /**
     * @return qqImgUrl
     */
    public String getQqimgurl() {
        return qqimgurl;
    }

    /**
     * @param qqimgurl
     */
    public void setQqimgurl(String qqimgurl) {
        this.qqimgurl = qqimgurl;
    }

    /**
     * @return qq_describe
     */
    public String getQqDescribe() {
        return qqDescribe;
    }

    /**
     * @param qqDescribe
     */
    public void setQqDescribe(String qqDescribe) {
        this.qqDescribe = qqDescribe;
    }

    /**
     * @return weixin_img_url
     */
    public String getWeixinImgUrl() {
        return weixinImgUrl;
    }

    /**
     * @param weixinImgUrl
     */
    public void setWeixinImgUrl(String weixinImgUrl) {
        this.weixinImgUrl = weixinImgUrl;
    }

    /**
     * @return tempPrice
     */
    public Double getTempprice() {
        return tempprice;
    }

    /**
     * @param tempprice
     */
    public void setTempprice(Double tempprice) {
        this.tempprice = tempprice;
    }

    /**
     * @return adPicture
     */
    public String getAdpicture() {
        return adpicture;
    }

    /**
     * @param adpicture
     */
    public void setAdpicture(String adpicture) {
        this.adpicture = adpicture;
    }

    /**
     * @return adHref
     */
    public String getAdhref() {
        return adhref;
    }

    /**
     * @param adhref
     */
    public void setAdhref(String adhref) {
        this.adhref = adhref;
    }

    /**
     * 获取播放器类型(1:cc播放器 2:乐视播放器)
     *
     * @return player_type - 播放器类型(1:cc播放器 2:乐视播放器)
     */
    public Integer getPlayerType() {
        return playerType;
    }

    /**
     * 设置播放器类型(1:cc播放器 2:乐视播放器)
     *
     * @param playerType 播放器类型(1:cc播放器 2:乐视播放器)
     */
    public void setPlayerType(Integer playerType) {
        this.playerType = playerType;
    }

    /**
     * 获取有效期，以月为单位
     *
     * @return effective_time - 有效期，以月为单位
     */
    public Integer getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * 设置有效期，以月为单位
     *
     * @param effectiveTime 有效期，以月为单位
     */
    public void setEffectiveTime(Integer effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * 获取课程的概要介绍
     *
     * @return summary - 课程的概要介绍
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置课程的概要介绍
     *
     * @param summary 课程的概要介绍
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}