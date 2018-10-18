package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "live_teacher")
public class LiveTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "teach_num")
    private String teachNum;

    @Column(name = "teach_name")
    private String teachName;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "create_date")
    private Date createDate;

    private String phone;

    private String introduction;

    /**
     * 1：有效 2：无效
     */
    private Integer flag;

    @Column(name = "teacher_type")
    private Integer teacherType;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "live_pic_url")
    private String livePicUrl;

    @Column(name = "app_big_pic")
    private String appBigPic;

    @Column(name = "app_pic")
    private String appPic;

    @Column(name = "app_head_pic")
    private String appHeadPic;

    @Column(name = "teach_real_name")
    private String teachRealName;

    @Column(name = "yx_account")
    private String yxAccount;

    @Column(name = "yx_token")
    private String yxToken;

    @Column(name = "yx_type")
    private Integer yxType;

    @Column(name = "yx_pic")
    private String yxPic;

    @Column(name = "yx_qq")
    private String yxQq;

    /**
     * 老师描述
     */
    @Column(name = "yx_describe")
    private String yxDescribe;

    /**
     * 公开课QQ
     */
    @Column(name = "course_qq")
    private String courseQq;

    /**
     * 公开课QQ名称
     */
    @Column(name = "course_qq_name")
    private String courseQqName;

    /**
     * 公开课图片
     */
    @Column(name = "course_pic")
    private String coursePic;

    /**
     * authority_users 表的主键
     */
    @Column(name = "authority_user_id")
    private Long authorityUserId;

    public LiveTeacher() {
    }

    public LiveTeacher(Long id, String teachName) {
        this.id = id;
        this.teachName = teachName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return teach_num
     */
    public String getTeachNum() {
        return teachNum;
    }

    /**
     * @param teachNum
     */
    public void setTeachNum(String teachNum) {
        this.teachNum = teachNum;
    }

    /**
     * @return teach_name
     */
    public String getTeachName() {
        return teachName;
    }

    /**
     * @param teachName
     */
    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    /**
     * @return item_id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * @return introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取1：有效 2：无效
     *
     * @return flag - 1：有效 2：无效
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置1：有效 2：无效
     *
     * @param flag 1：有效 2：无效
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * @return teacher_type
     */
    public Integer getTeacherType() {
        return teacherType;
    }

    /**
     * @param teacherType
     */
    public void setTeacherType(Integer teacherType) {
        this.teacherType = teacherType;
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
     * @return live_pic_url
     */
    public String getLivePicUrl() {
        return livePicUrl;
    }

    /**
     * @param livePicUrl
     */
    public void setLivePicUrl(String livePicUrl) {
        this.livePicUrl = livePicUrl;
    }

    /**
     * @return app_big_pic
     */
    public String getAppBigPic() {
        return appBigPic;
    }

    /**
     * @param appBigPic
     */
    public void setAppBigPic(String appBigPic) {
        this.appBigPic = appBigPic;
    }

    /**
     * @return app_pic
     */
    public String getAppPic() {
        return appPic;
    }

    /**
     * @param appPic
     */
    public void setAppPic(String appPic) {
        this.appPic = appPic;
    }

    /**
     * @return app_head_pic
     */
    public String getAppHeadPic() {
        return appHeadPic;
    }

    /**
     * @param appHeadPic
     */
    public void setAppHeadPic(String appHeadPic) {
        this.appHeadPic = appHeadPic;
    }

    /**
     * @return teach_real_name
     */
    public String getTeachRealName() {
        return teachRealName;
    }

    /**
     * @param teachRealName
     */
    public void setTeachRealName(String teachRealName) {
        this.teachRealName = teachRealName;
    }

    /**
     * @return yx_account
     */
    public String getYxAccount() {
        return yxAccount;
    }

    /**
     * @param yxAccount
     */
    public void setYxAccount(String yxAccount) {
        this.yxAccount = yxAccount;
    }

    /**
     * @return yx_token
     */
    public String getYxToken() {
        return yxToken;
    }

    /**
     * @param yxToken
     */
    public void setYxToken(String yxToken) {
        this.yxToken = yxToken;
    }

    /**
     * @return yx_type
     */
    public Integer getYxType() {
        return yxType;
    }

    /**
     * @param yxType
     */
    public void setYxType(Integer yxType) {
        this.yxType = yxType;
    }

    /**
     * @return yx_pic
     */
    public String getYxPic() {
        return yxPic;
    }

    /**
     * @param yxPic
     */
    public void setYxPic(String yxPic) {
        this.yxPic = yxPic;
    }

    /**
     * @return yx_qq
     */
    public String getYxQq() {
        return yxQq;
    }

    /**
     * @param yxQq
     */
    public void setYxQq(String yxQq) {
        this.yxQq = yxQq;
    }

    /**
     * 获取老师描述
     *
     * @return yx_describe - 老师描述
     */
    public String getYxDescribe() {
        return yxDescribe;
    }

    /**
     * 设置老师描述
     *
     * @param yxDescribe 老师描述
     */
    public void setYxDescribe(String yxDescribe) {
        this.yxDescribe = yxDescribe;
    }

    /**
     * 获取公开课QQ
     *
     * @return course_qq - 公开课QQ
     */
    public String getCourseQq() {
        return courseQq;
    }

    /**
     * 设置公开课QQ
     *
     * @param courseQq 公开课QQ
     */
    public void setCourseQq(String courseQq) {
        this.courseQq = courseQq;
    }

    /**
     * 获取公开课QQ名称
     *
     * @return course_qq_name - 公开课QQ名称
     */
    public String getCourseQqName() {
        return courseQqName;
    }

    /**
     * 设置公开课QQ名称
     *
     * @param courseQqName 公开课QQ名称
     */
    public void setCourseQqName(String courseQqName) {
        this.courseQqName = courseQqName;
    }

    /**
     * 获取公开课图片
     *
     * @return course_pic - 公开课图片
     */
    public String getCoursePic() {
        return coursePic;
    }

    /**
     * 设置公开课图片
     *
     * @param coursePic 公开课图片
     */
    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    /**
     * 获取authority_users 表的主键
     *
     * @return authority_user_id - authority_users 表的主键
     */
    public Long getAuthorityUserId() {
        return authorityUserId;
    }

    /**
     * 设置authority_users 表的主键
     *
     * @param authorityUserId authority_users 表的主键
     */
    public void setAuthorityUserId(Long authorityUserId) {
        this.authorityUserId = authorityUserId;
    }
}