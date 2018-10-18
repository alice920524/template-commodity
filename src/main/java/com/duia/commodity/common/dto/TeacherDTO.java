package com.duia.commodity.common.dto;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.model.AuthorityUsers;
import com.duia.commodity.model.TeacherScore;

/**
 * Created by 李恒名 on 2017/7/17.
 */
public class TeacherDTO {
    private Long id;
    private String name;//老师名称
    private String trueName;
    private String imageUrl;//	头像地址
    private String signature;//个性签名
    private String intro;//老师简介

    private Double  averageScore; //真实平均分
    private Integer  serverNum; //服务学员人数
    private Integer  teachingTime;//授课时长

    public TeacherDTO() {
    }

    public TeacherDTO(AuthorityUsers authorityUsers, TeacherScore teacherScore) {
        // 基本信息(头像、昵称...)
        setId(authorityUsers.getId());
        setTrueName(authorityUsers.getUsername());
        setName(authorityUsers.getNickName());
        setImageUrl(authorityUsers.getSmallImg());
        setSignature(authorityUsers.getSignature());
        setIntro(authorityUsers.getIntroduct());

        // 评分
        setAverageScore(teacherScore.getFakeAverageScore());
        // 假数据
        if (teacherScore.getStatus() != null && TeacherScore.status_false.equals(teacherScore.getStatus())) {
            setServerNum(teacherScore.getFakeServerNum());
            setTeachingTime(teacherScore.getFakeTeachingTime());
            // 真数据
        } else if (teacherScore.getStatus() != null && TeacherScore.status_true.equals(teacherScore.getStatus())) {
            setServerNum(teacherScore.getServerNum());
            setTeachingTime(teacherScore.getTeachingTime());
        }
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getServerNum() {
        return serverNum;
    }

    public void setServerNum(Integer serverNum) {
        this.serverNum = serverNum;
    }

    public Integer getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(Integer teachingTime) {
        this.teachingTime = teachingTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
