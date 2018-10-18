package com.duia.commodity.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "class_schedule_course")
public class ClassScheduleCourse implements Serializable {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_schedule_id")
    private Long classScheduleId;

    @Column(name = "chapter_id")
    private Long chapterId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "class_date")
    private Date classDate;

    private String week;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "class_order")
    private Integer classOrder;

    private Integer status;

    @Column(name = "live_room_id")
    private String liveRoomId;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "video_date")
    private Date videoDate;

    @Column(name = "video_status")
    private Integer videoStatus;

    @Column(name = "ppt_url")
    private String pptUrl;

    @Column(name = "ppt_date")
    private Date pptDate;

    private Integer sku;

    @Column(name = "teacher_userId")
    private Long teacherUserid;

    private Integer type;

    private Integer playType; // 播放类型[1:直播,2:视频,3:回放]

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "video_back_url")
    private String videoBackUrl;

    @Column(name = "mt_id")
    private String mtId;

    @Column(name = "mt_video_id")
    private String mtVideoId;

    /**
     * 授课老师ID
     */
    @Column(name = "authority_user_id")
    private Long authorityUserId;

    /**
     * 运营商[0:展示互动ID,1:CC]
     */
    @Column(name = "operator_company")
    private Integer operatorCompany;

    /**
     * CC直播ID
     */
    @Column(name = "cc_room_id")
    private String ccRoomId;

    /**
     * 推流端密码，即讲师密码
     */
    @Column(name = "publisher_pass")
    private String publisherPass;

    /**
     * 播放端密码
     */
    @Column(name = "play_pass")
    private String playPass;

    /**
     * 视频课id
     */
    @Column(name = "lecture_id")
    private Long lectureId;

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
     * @return class_schedule_id
     */
    public Long getClassScheduleId() {
        return classScheduleId;
    }

    /**
     * @param classScheduleId
     */
    public void setClassScheduleId(Long classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    /**
     * @return chapter_id
     */
    public Long getChapterId() {
        return chapterId;
    }

    /**
     * @param chapterId
     */
    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    /**
     * @return course_name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return teacher_id
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return class_date
     */
    public Date getClassDate() {
        return classDate;
    }

    /**
     * @param classDate
     */
    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    /**
     * @return week
     */
    public String getWeek() {
        return week;
    }

    /**
     * @param week
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * @return start_time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return class_order
     */
    public Integer getClassOrder() {
        return classOrder;
    }

    /**
     * @param classOrder
     */
    public void setClassOrder(Integer classOrder) {
        this.classOrder = classOrder;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return live_room_id
     */
    public String getLiveRoomId() {
        return liveRoomId;
    }

    /**
     * @param liveRoomId
     */
    public void setLiveRoomId(String liveRoomId) {
        this.liveRoomId = liveRoomId;
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
     * @return video_date
     */
    public Date getVideoDate() {
        return videoDate;
    }

    /**
     * @param videoDate
     */
    public void setVideoDate(Date videoDate) {
        this.videoDate = videoDate;
    }

    /**
     * @return video_status
     */
    public Integer getVideoStatus() {
        return videoStatus;
    }

    /**
     * @param videoStatus
     */
    public void setVideoStatus(Integer videoStatus) {
        this.videoStatus = videoStatus;
    }

    /**
     * @return ppt_url
     */
    public String getPptUrl() {
        return pptUrl;
    }

    /**
     * @param pptUrl
     */
    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl;
    }

    /**
     * @return ppt_date
     */
    public Date getPptDate() {
        return pptDate;
    }

    /**
     * @param pptDate
     */
    public void setPptDate(Date pptDate) {
        this.pptDate = pptDate;
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
     * @return teacher_userId
     */
    public Long getTeacherUserid() {
        return teacherUserid;
    }

    /**
     * @param teacherUserid
     */
    public void setTeacherUserid(Long teacherUserid) {
        this.teacherUserid = teacherUserid;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return video_url
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * @return video_back_url
     */
    public String getVideoBackUrl() {
        return videoBackUrl;
    }

    /**
     * @param videoBackUrl
     */
    public void setVideoBackUrl(String videoBackUrl) {
        this.videoBackUrl = videoBackUrl;
    }

    /**
     * @return mt_id
     */
    public String getMtId() {
        return mtId;
    }

    /**
     * @param mtId
     */
    public void setMtId(String mtId) {
        this.mtId = mtId;
    }

    /**
     * @return mt_video_id
     */
    public String getMtVideoId() {
        return mtVideoId;
    }

    /**
     * @param mtVideoId
     */
    public void setMtVideoId(String mtVideoId) {
        this.mtVideoId = mtVideoId;
    }

    /**
     * 获取授课老师ID
     *
     * @return authority_user_id - 授课老师ID
     */
    public Long getAuthorityUserId() {
        return authorityUserId;
    }

    /**
     * 设置授课老师ID
     *
     * @param authorityUserId 授课老师ID
     */
    public void setAuthorityUserId(Long authorityUserId) {
        this.authorityUserId = authorityUserId;
    }

    /**
     * 获取运营商[0:展示互动ID,1:CC]
     *
     * @return operator_company - 运营商[0:展示互动ID,1:CC]
     */
    public Integer getOperatorCompany() {
        return operatorCompany;
    }

    /**
     * 设置运营商[0:展示互动ID,1:CC]
     *
     * @param operatorCompany 运营商[0:展示互动ID,1:CC]
     */
    public void setOperatorCompany(Integer operatorCompany) {
        this.operatorCompany = operatorCompany;
    }

    /**
     * 获取CC直播ID
     *
     * @return cc_room_id - CC直播ID
     */
    public String getCcRoomId() {
        return ccRoomId;
    }

    /**
     * 设置CC直播ID
     *
     * @param ccRoomId CC直播ID
     */
    public void setCcRoomId(String ccRoomId) {
        this.ccRoomId = ccRoomId;
    }

    /**
     * 获取推流端密码，即讲师密码
     *
     * @return publisher_pass - 推流端密码，即讲师密码
     */
    public String getPublisherPass() {
        return publisherPass;
    }

    /**
     * 设置推流端密码，即讲师密码
     *
     * @param publisherPass 推流端密码，即讲师密码
     */
    public void setPublisherPass(String publisherPass) {
        this.publisherPass = publisherPass;
    }

    /**
     * 获取播放端密码
     *
     * @return play_pass - 播放端密码
     */
    public String getPlayPass() {
        return playPass;
    }

    /**
     * 设置播放端密码
     *
     * @param playPass 播放端密码
     */
    public void setPlayPass(String playPass) {
        this.playPass = playPass;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public Integer getPlayType() {
        return playType;
    }

    public void setPlayType(Integer playType) {
        this.playType = playType;
    }
}