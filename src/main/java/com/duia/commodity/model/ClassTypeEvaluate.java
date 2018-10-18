package com.duia.commodity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "class_type_evaluate")
public class ClassTypeEvaluate {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学员id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 班级id
     */
    @Column(name = "class_id")
    private Integer classId;

    /**
     * 班型id
     */
    @Column(name = "class_type_id")
    private Integer classTypeId;

    /**
     * 星级
     */
    private Integer star;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    @Column(name = "eval_date")
    private Date evalDate;

    /**
     * 评价状态[0:隐藏,1:显示]
     */
    @Column(name = "eval_status")
    private Integer evalStatus;

    /**
     * 回复时间
     */
    @Column(name = "reply_date")
    private Date replyDate;

    /**
     * 回复内容
     */
    @Column(name = "reply_content")
    private String replyContent;

    /**
     * 回复状态[0:未回复,1:已回复]
     */
    @Column(name = "reply_status")
    private Integer replyStatus;

    /**
     * 回复人id
     */
    @Column(name = "authority_user_id")
    private Integer authorityUserId;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学员id
     *
     * @return user_id - 学员id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置学员id
     *
     * @param userId 学员id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取班级id
     *
     * @return class_id - 班级id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 设置班级id
     *
     * @param classId 班级id
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * 获取班型id
     *
     * @return class_type_id - 班型id
     */
    public Integer getClassTypeId() {
        return classTypeId;
    }

    /**
     * 设置班型id
     *
     * @param classTypeId 班型id
     */
    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }

    /**
     * 获取星级
     *
     * @return star - 星级
     */
    public Integer getStar() {
        return star;
    }

    /**
     * 设置星级
     *
     * @param star 星级
     */
    public void setStar(Integer star) {
        this.star = star;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论时间
     *
     * @return eval_date - 评论时间
     */
    public Date getEvalDate() {
        return evalDate;
    }

    /**
     * 设置评论时间
     *
     * @param evalDate 评论时间
     */
    public void setEvalDate(Date evalDate) {
        this.evalDate = evalDate;
    }

    /**
     * 获取评价状态[0:隐藏,1:显示]
     *
     * @return eval_status - 评价状态[0:隐藏,1:显示]
     */
    public Integer getEvalStatus() {
        return evalStatus;
    }

    /**
     * 设置评价状态[0:隐藏,1:显示]
     *
     * @param evalStatus 评价状态[0:隐藏,1:显示]
     */
    public void setEvalStatus(Integer evalStatus) {
        this.evalStatus = evalStatus;
    }

    /**
     * 获取回复时间
     *
     * @return reply_date - 回复时间
     */
    public Date getReplyDate() {
        return replyDate;
    }

    /**
     * 设置回复时间
     *
     * @param replyDate 回复时间
     */
    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    /**
     * 获取回复内容
     *
     * @return reply_content - 回复内容
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 设置回复内容
     *
     * @param replyContent 回复内容
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * 获取回复状态[0:未回复,1:已回复]
     *
     * @return reply_status - 回复状态[0:未回复,1:已回复]
     */
    public Integer getReplyStatus() {
        return replyStatus;
    }

    /**
     * 设置回复状态[0:未回复,1:已回复]
     *
     * @param replyStatus 回复状态[0:未回复,1:已回复]
     */
    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    /**
     * 获取回复人id
     *
     * @return authority_user_id - 回复人id
     */
    public Integer getAuthorityUserId() {
        return authorityUserId;
    }

    /**
     * 设置回复人id
     *
     * @param authorityUserId 回复人id
     */
    public void setAuthorityUserId(Integer authorityUserId) {
        this.authorityUserId = authorityUserId;
    }
}