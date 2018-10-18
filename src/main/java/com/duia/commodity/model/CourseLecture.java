package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "course_lecture")
public class CourseLecture {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 1：javascript 2：web；
     */
    @Column(name = "chapter_id")
    private Long chapterId;

    @Column(name = "lecture_name")
    private String lectureName;

    @Column(name = "lecture_order")
    private Integer lectureOrder;

    /**
     * 1、讲座 2、测验
     */
    @Column(name = "lecture_type")
    private Integer lectureType;

    /**
     * 该字段针对讲座使用1、视频 2、ppt文档3、混搭
     */
    @Column(name = "lecture_content_type")
    private Integer lectureContentType;

    /**
     * 存储辅助材料的存放路径
     */
    @Column(name = "lecture_material_url")
    private String lectureMaterialUrl;

    /**
     * 如果讲座内容是视频，并且通过马尔斯上传视频，存储该视频的路径
     */
    @Column(name = "lecture_video_url")
    private String lectureVideoUrl;

    /**
     * 存储上传的ppt的存放路径如果讲座内容是ppt时使用该字段
     */
    @Column(name = "lecture_ppt_url")
    private String lecturePptUrl;

    @Column(name = "lecture_ppt_pagenum")
    private Integer lecturePptPagenum;

    /**
     * 如果使用第三方的视频，存储第三方视频的url比如优酷网、土豆网的视频链接地址
     */
    @Column(name = "video_other_url")
    private String videoOtherUrl;

    /**
     * 如果使用cc视频，则存储视频在cc平台中的视频代码
     */
    @Column(name = "video_id")
    private String videoId;

    @Column(name = "video_length")
    private String videoLength;

    @Column(name = "quiz_num")
    private Integer quizNum;

    /**
     * 0、未发布1、已发布
     */
    @Column(name = "publish_state")
    private Integer publishState;

    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "file_title")
    private String fileTitle;

    @Column(name = "lecture_converswf_url")
    private String lectureConverswfUrl;

    @Column(name = "conver_time")
    private Date converTime;

    @Column(name = "conver_fail_num")
    private Integer converFailNum;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "cost_type")
    private Integer costType;

    @Column(name = "videoSize")
    private String videosize;

    @Column(name = "videoUrl")
    private String videourl;

    @Column(name = "praise_count")
    private Integer praiseCount;

    /**
     * 乐视视频播放ID,通过此ID与乐视连接播放视频
     */
    @Column(name = "ls_video_id")
    private String lsVideoId;

    /**
     * 乐视视频ID
     */
    @Column(name = "letv_video_id")
    private Integer letvVideoId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "lecture_audio_url")
    private String lectureAudioUrl;

    @Column(name = "lecture_handouts_url")
    private String lectureHandoutsUrl;

    @Column(name = "study_num")
    private String studyNum;

    /**
     * CC视频ID
     */
    @Column(name = "cc_video_id")
    private String ccVideoId;

    /**
     * 播放器类型(1:cc播放器 2:乐视播放器)
     */
    @Column(name = "player_type")
    private Integer playerType;

    /**
     * 服务端（0：全部；1:WEB;2：APP）
     */
    @Column(name = "server_type")
    private Integer serverType;

    /**
     * 网易视频id
     */
    @Column(name = "vcloud_video_id")
    private Integer vcloudVideoId;

    /**
     * 网易视频播放地址
     */
    @Column(name = "vcloud_video_url")
    private String vcloudVideoUrl;

    @Column(name = "lecture_desc")
    private String lectureDesc;

    /**
     * 考点名称，适用于课程历史的保存
     */
    @Column(name = "exam_points")
    private String examPoints;

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
     * 获取1：javascript 2：web；
     *
     * @return chapter_id - 1：javascript 2：web；
     */
    public Long getChapterId() {
        return chapterId;
    }

    /**
     * 设置1：javascript 2：web；
     *
     * @param chapterId 1：javascript 2：web；
     */
    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    /**
     * @return lecture_name
     */
    public String getLectureName() {
        return lectureName;
    }

    /**
     * @param lectureName
     */
    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    /**
     * @return lecture_order
     */
    public Integer getLectureOrder() {
        return lectureOrder;
    }

    /**
     * @param lectureOrder
     */
    public void setLectureOrder(Integer lectureOrder) {
        this.lectureOrder = lectureOrder;
    }

    /**
     * 获取1、讲座 2、测验
     *
     * @return lecture_type - 1、讲座 2、测验
     */
    public Integer getLectureType() {
        return lectureType;
    }

    /**
     * 设置1、讲座 2、测验
     *
     * @param lectureType 1、讲座 2、测验
     */
    public void setLectureType(Integer lectureType) {
        this.lectureType = lectureType;
    }

    /**
     * 获取该字段针对讲座使用1、视频 2、ppt文档3、混搭
     *
     * @return lecture_content_type - 该字段针对讲座使用1、视频 2、ppt文档3、混搭
     */
    public Integer getLectureContentType() {
        return lectureContentType;
    }

    /**
     * 设置该字段针对讲座使用1、视频 2、ppt文档3、混搭
     *
     * @param lectureContentType 该字段针对讲座使用1、视频 2、ppt文档3、混搭
     */
    public void setLectureContentType(Integer lectureContentType) {
        this.lectureContentType = lectureContentType;
    }

    /**
     * 获取存储辅助材料的存放路径
     *
     * @return lecture_material_url - 存储辅助材料的存放路径
     */
    public String getLectureMaterialUrl() {
        return lectureMaterialUrl;
    }

    /**
     * 设置存储辅助材料的存放路径
     *
     * @param lectureMaterialUrl 存储辅助材料的存放路径
     */
    public void setLectureMaterialUrl(String lectureMaterialUrl) {
        this.lectureMaterialUrl = lectureMaterialUrl;
    }

    /**
     * 获取如果讲座内容是视频，并且通过马尔斯上传视频，存储该视频的路径
     *
     * @return lecture_video_url - 如果讲座内容是视频，并且通过马尔斯上传视频，存储该视频的路径
     */
    public String getLectureVideoUrl() {
        return lectureVideoUrl;
    }

    /**
     * 设置如果讲座内容是视频，并且通过马尔斯上传视频，存储该视频的路径
     *
     * @param lectureVideoUrl 如果讲座内容是视频，并且通过马尔斯上传视频，存储该视频的路径
     */
    public void setLectureVideoUrl(String lectureVideoUrl) {
        this.lectureVideoUrl = lectureVideoUrl;
    }

    /**
     * 获取存储上传的ppt的存放路径如果讲座内容是ppt时使用该字段
     *
     * @return lecture_ppt_url - 存储上传的ppt的存放路径如果讲座内容是ppt时使用该字段
     */
    public String getLecturePptUrl() {
        return lecturePptUrl;
    }

    /**
     * 设置存储上传的ppt的存放路径如果讲座内容是ppt时使用该字段
     *
     * @param lecturePptUrl 存储上传的ppt的存放路径如果讲座内容是ppt时使用该字段
     */
    public void setLecturePptUrl(String lecturePptUrl) {
        this.lecturePptUrl = lecturePptUrl;
    }

    /**
     * @return lecture_ppt_pagenum
     */
    public Integer getLecturePptPagenum() {
        return lecturePptPagenum;
    }

    /**
     * @param lecturePptPagenum
     */
    public void setLecturePptPagenum(Integer lecturePptPagenum) {
        this.lecturePptPagenum = lecturePptPagenum;
    }

    /**
     * 获取如果使用第三方的视频，存储第三方视频的url比如优酷网、土豆网的视频链接地址
     *
     * @return video_other_url - 如果使用第三方的视频，存储第三方视频的url比如优酷网、土豆网的视频链接地址
     */
    public String getVideoOtherUrl() {
        return videoOtherUrl;
    }

    /**
     * 设置如果使用第三方的视频，存储第三方视频的url比如优酷网、土豆网的视频链接地址
     *
     * @param videoOtherUrl 如果使用第三方的视频，存储第三方视频的url比如优酷网、土豆网的视频链接地址
     */
    public void setVideoOtherUrl(String videoOtherUrl) {
        this.videoOtherUrl = videoOtherUrl;
    }

    /**
     * 获取如果使用cc视频，则存储视频在cc平台中的视频代码
     *
     * @return video_id - 如果使用cc视频，则存储视频在cc平台中的视频代码
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置如果使用cc视频，则存储视频在cc平台中的视频代码
     *
     * @param videoId 如果使用cc视频，则存储视频在cc平台中的视频代码
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * @return video_length
     */
    public String getVideoLength() {
        return videoLength;
    }

    /**
     * @param videoLength
     */
    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength;
    }

    /**
     * @return quiz_num
     */
    public Integer getQuizNum() {
        return quizNum;
    }

    /**
     * @param quizNum
     */
    public void setQuizNum(Integer quizNum) {
        this.quizNum = quizNum;
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
     * @return publish_date
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return file_title
     */
    public String getFileTitle() {
        return fileTitle;
    }

    /**
     * @param fileTitle
     */
    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    /**
     * @return lecture_converswf_url
     */
    public String getLectureConverswfUrl() {
        return lectureConverswfUrl;
    }

    /**
     * @param lectureConverswfUrl
     */
    public void setLectureConverswfUrl(String lectureConverswfUrl) {
        this.lectureConverswfUrl = lectureConverswfUrl;
    }

    /**
     * @return conver_time
     */
    public Date getConverTime() {
        return converTime;
    }

    /**
     * @param converTime
     */
    public void setConverTime(Date converTime) {
        this.converTime = converTime;
    }

    /**
     * @return conver_fail_num
     */
    public Integer getConverFailNum() {
        return converFailNum;
    }

    /**
     * @param converFailNum
     */
    public void setConverFailNum(Integer converFailNum) {
        this.converFailNum = converFailNum;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return cost_type
     */
    public Integer getCostType() {
        return costType;
    }

    /**
     * @param costType
     */
    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    /**
     * @return videoSize
     */
    public String getVideosize() {
        return videosize;
    }

    /**
     * @param videosize
     */
    public void setVideosize(String videosize) {
        this.videosize = videosize;
    }

    /**
     * @return videoUrl
     */
    public String getVideourl() {
        return videourl;
    }

    /**
     * @param videourl
     */
    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    /**
     * @return praise_count
     */
    public Integer getPraiseCount() {
        return praiseCount;
    }

    /**
     * @param praiseCount
     */
    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    /**
     * 获取乐视视频播放ID,通过此ID与乐视连接播放视频
     *
     * @return ls_video_id - 乐视视频播放ID,通过此ID与乐视连接播放视频
     */
    public String getLsVideoId() {
        return lsVideoId;
    }

    /**
     * 设置乐视视频播放ID,通过此ID与乐视连接播放视频
     *
     * @param lsVideoId 乐视视频播放ID,通过此ID与乐视连接播放视频
     */
    public void setLsVideoId(String lsVideoId) {
        this.lsVideoId = lsVideoId;
    }

    /**
     * 获取乐视视频ID
     *
     * @return letv_video_id - 乐视视频ID
     */
    public Integer getLetvVideoId() {
        return letvVideoId;
    }

    /**
     * 设置乐视视频ID
     *
     * @param letvVideoId 乐视视频ID
     */
    public void setLetvVideoId(Integer letvVideoId) {
        this.letvVideoId = letvVideoId;
    }

    /**
     * @return course_id
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return lecture_audio_url
     */
    public String getLectureAudioUrl() {
        return lectureAudioUrl;
    }

    /**
     * @param lectureAudioUrl
     */
    public void setLectureAudioUrl(String lectureAudioUrl) {
        this.lectureAudioUrl = lectureAudioUrl;
    }

    /**
     * @return lecture_handouts_url
     */
    public String getLectureHandoutsUrl() {
        return lectureHandoutsUrl;
    }

    /**
     * @param lectureHandoutsUrl
     */
    public void setLectureHandoutsUrl(String lectureHandoutsUrl) {
        this.lectureHandoutsUrl = lectureHandoutsUrl;
    }

    /**
     * @return study_num
     */
    public String getStudyNum() {
        return studyNum;
    }

    /**
     * @param studyNum
     */
    public void setStudyNum(String studyNum) {
        this.studyNum = studyNum;
    }

    /**
     * 获取CC视频ID
     *
     * @return cc_video_id - CC视频ID
     */
    public String getCcVideoId() {
        return ccVideoId;
    }

    /**
     * 设置CC视频ID
     *
     * @param ccVideoId CC视频ID
     */
    public void setCcVideoId(String ccVideoId) {
        this.ccVideoId = ccVideoId;
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
     * 获取服务端（0：全部；1:WEB;2：APP）
     *
     * @return server_type - 服务端（0：全部；1:WEB;2：APP）
     */
    public Integer getServerType() {
        return serverType;
    }

    /**
     * 设置服务端（0：全部；1:WEB;2：APP）
     *
     * @param serverType 服务端（0：全部；1:WEB;2：APP）
     */
    public void setServerType(Integer serverType) {
        this.serverType = serverType;
    }

    /**
     * 获取网易视频id
     *
     * @return vcloud_video_id - 网易视频id
     */
    public Integer getVcloudVideoId() {
        return vcloudVideoId;
    }

    /**
     * 设置网易视频id
     *
     * @param vcloudVideoId 网易视频id
     */
    public void setVcloudVideoId(Integer vcloudVideoId) {
        this.vcloudVideoId = vcloudVideoId;
    }

    /**
     * 获取网易视频播放地址
     *
     * @return vcloud_video_url - 网易视频播放地址
     */
    public String getVcloudVideoUrl() {
        return vcloudVideoUrl;
    }

    /**
     * 设置网易视频播放地址
     *
     * @param vcloudVideoUrl 网易视频播放地址
     */
    public void setVcloudVideoUrl(String vcloudVideoUrl) {
        this.vcloudVideoUrl = vcloudVideoUrl;
    }

    /**
     * @return lecture_desc
     */
    public String getLectureDesc() {
        return lectureDesc;
    }

    /**
     * @param lectureDesc
     */
    public void setLectureDesc(String lectureDesc) {
        this.lectureDesc = lectureDesc;
    }

    /**
     * 获取考点名称，适用于课程历史的保存
     *
     * @return exam_points - 考点名称，适用于课程历史的保存
     */
    public String getExamPoints() {
        return examPoints;
    }

    /**
     * 设置考点名称，适用于课程历史的保存
     *
     * @param examPoints 考点名称，适用于课程历史的保存
     */
    public void setExamPoints(String examPoints) {
        this.examPoints = examPoints;
    }
}