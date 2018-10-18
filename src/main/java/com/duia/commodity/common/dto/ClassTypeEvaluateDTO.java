package com.duia.commodity.common.dto;

import java.util.Date;
import java.util.List;

public class ClassTypeEvaluateDTO {
    private Long id;
    private String username;// 昵称
    private String mobile;// 手机号
    private String headPicUrl;// 头像
    private String unionName;//
    private Integer star;// 星级
    private String content;//评论内容
    private List<PicDataDTO> evaluatePics;//评论图片
    private Date evalDate;// 评论时间
    private Integer evalStatus;//评价状态[0:隐藏,1:显示]
    private Date replyDate;//回复时间
    private String replyContent;// 回复内容
    private Integer replyStatus;// 回复状态[0:未回复,1:已回复]
    private Integer top;// '置顶[0:普通，1:置顶]'
    private Date topTime;// '置顶时间'


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {this.unionName = unionName;}

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public Integer getStar() {
        return star;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<PicDataDTO> getEvaluatePics() {
        return evaluatePics;
    }

    public void setEvaluatePics(List<PicDataDTO> evaluatePics) {
        this.evaluatePics = evaluatePics;
    }

    public Date getEvalDate() {
        return evalDate;
    }

    public void setEvalDate(Date evalDate) {
        this.evalDate = evalDate;
    }

    public Integer getEvalStatus() {
        return evalStatus;
    }

    public void setEvalStatus(Integer evalStatus) {
        this.evalStatus = evalStatus;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Date getTopTime() {
        return topTime;
    }

    public void setTopTime(Date topTime) {
        this.topTime = topTime;
    }


}
