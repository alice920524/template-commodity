package com.duia.commodity.common.dto;

import com.alibaba.fastjson.JSON;
import tk.mybatis.mapper.util.StringUtil;

import java.io.Serializable;

/**
 * Created by 李恒名 on 2017/7/13.
 */
public class CourseDTO implements Serializable {
    private Long id;
    private String name;
    private String courseDate;
    private String startTime;
    private String endTime;
    private Integer type;
    private Integer playType;
    private Long lectureId;
    private String videoLength;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public String getVideoLength() {
        return videoLength;
    }

    public Integer getPlayType() {
        return playType;
    }

    public void setPlayType(Integer playType) {
        this.playType = playType;
    }

    public void setVideoLength(String videoLength) {
        if(StringUtil.isNotEmpty(videoLength) && videoLength.indexOf(":") != -1){
            String length = videoLength.split(":")[0];
            videoLength = length;
        }
        this.videoLength = videoLength;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
