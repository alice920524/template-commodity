package com.duia.commodity.model;

import javax.persistence.*;

@Table(name = "class_live_room")
public class ClassLiveRoom {
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联ID
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 关联类型[0：直播课, 1:公开课]
     */
    @Column(name = "relation_type")
    private Integer relationType;

    /**
     * 房间类型[1:CC,2:展视]
     */
    private Integer type;

    /**
     * 视频节id
     */
    @Column(name = "lecture_id")
    private Long lectureId;

    /**
     * 房间ID
     */
    @Column(name = "live_room_id")
    private String liveRoomId;

    /**
     * 录播ID
     */
    @Column(name = "video_id")
    private String videoId;

    /**
     * [CC]下载ID
     */
    @Column(name = "video_down_id")
    private String videoDownId;

    /**
     * 录播文件地址
     */
    @Column(name = "video_back_url")
    private String videoBackUrl;

    /**
     * 房间模式[0:普通模式,1:无PPT模式]
     */
    private Integer mode;

    /**
     * [CC 0:接口校验,1:密码校验,2:免密码校验]
     */
    private Integer verify;

    /**
     * [CC]讲师密码
     */
    @Column(name = "publisher_pass")
    private String publisherPass;

    /**
     * [CC]播放端密码
     */
    @Column(name = "play_pass")
    private String playPass;

    /**
     * 回放的时长
     */
    @Column(name = "video_length")
    private String videoLength;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    /**
     * 获取关联类型[0：直播课, 1:公开课]
     *
     * @return relation_type - 关联类型[0：直播课, 1:公开课]
     */
    public Integer getRelationType() {
        return relationType;
    }

    /**
     * 设置关联类型[0：直播课, 1:公开课]
     *
     * @param relationType 关联类型[0：直播课, 1:公开课]
     */
    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    /**
     * 获取房间类型[1:CC,2:展视]
     *
     * @return type - 房间类型[1:CC,2:展视]
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置房间类型[1:CC,2:展视]
     *
     * @param type 房间类型[1:CC,2:展视]
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取房间ID
     *
     * @return live_room_id - 房间ID
     */
    public String getLiveRoomId() {
        return liveRoomId;
    }

    /**
     * 设置房间ID
     *
     * @param liveRoomId 房间ID
     */
    public void setLiveRoomId(String liveRoomId) {
        this.liveRoomId = liveRoomId;
    }

    /**
     * 获取录播ID
     *
     * @return video_id - 录播ID
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置录播ID
     *
     * @param videoId 录播ID
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取[CC]下载ID
     *
     * @return video_down_id - [CC]下载ID
     */
    public String getVideoDownId() {
        return videoDownId;
    }

    /**
     * 设置[CC]下载ID
     *
     * @param videoDownId [CC]下载ID
     */
    public void setVideoDownId(String videoDownId) {
        this.videoDownId = videoDownId;
    }

    /**
     * 获取录播文件地址
     *
     * @return video_back_url - 录播文件地址
     */
    public String getVideoBackUrl() {
        return videoBackUrl;
    }

    /**
     * 设置录播文件地址
     *
     * @param videoBackUrl 录播文件地址
     */
    public void setVideoBackUrl(String videoBackUrl) {
        this.videoBackUrl = videoBackUrl;
    }

    /**
     * 获取房间模式[0:普通模式,1:无PPT模式]
     *
     * @return mode - 房间模式[0:普通模式,1:无PPT模式]
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 设置房间模式[0:普通模式,1:无PPT模式]
     *
     * @param mode 房间模式[0:普通模式,1:无PPT模式]
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    /**
     * 获取[CC 0:接口校验,1:密码校验,2:免密码校验]
     *
     * @return verify - [CC 0:接口校验,1:密码校验,2:免密码校验]
     */
    public Integer getVerify() {
        return verify;
    }

    /**
     * 设置[CC 0:接口校验,1:密码校验,2:免密码校验]
     *
     * @param verify [CC 0:接口校验,1:密码校验,2:免密码校验]
     */
    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    /**
     * 获取[CC]讲师密码
     *
     * @return publisher_pass - [CC]讲师密码
     */
    public String getPublisherPass() {
        return publisherPass;
    }

    /**
     * 设置[CC]讲师密码
     *
     * @param publisherPass [CC]讲师密码
     */
    public void setPublisherPass(String publisherPass) {
        this.publisherPass = publisherPass;
    }

    /**
     * 获取[CC]播放端密码
     *
     * @return play_pass - [CC]播放端密码
     */
    public String getPlayPass() {
        return playPass;
    }

    /**
     * 设置[CC]播放端密码
     *
     * @param playPass [CC]播放端密码
     */
    public void setPlayPass(String playPass) {
        this.playPass = playPass;
    }

    /**
     * 获取回放的时长
     *
     * @return video_length - 回放的时长
     */
    public String getVideoLength() {
        return videoLength;
    }

    /**
     * 设置回放的时长
     *
     * @param videoLength 回放的时长
     */
    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength;
    }
}