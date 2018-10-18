package com.duia.commodity.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "teacher_score")
public class TeacherScore {
    // 状态真假值
    public static final Integer status_true = 1;
    public static final Integer status_false = 0;
    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 老师id
     */
    @Column(name = "authority_user_id")
    private Long authorityUserId;

    /**
     * 真实平均分
     */
    @Column(name = "average_score")
    private Double averageScore;

    /**
     * 服务学员人数
     */
    @Column(name = "server_num")
    private Integer serverNum;

    /**
     * 授课时长
     */
    @Column(name = "teaching_time")
    private Integer teachingTime;

    /**
     * 假平均分
     */
    @Column(name = "fake_average_score")
    private Double fakeAverageScore;

    /**
     * 假服务学员人数
     */
    @Column(name = "fake_server_num")
    private Integer fakeServerNum;

    /**
     * 假授课时长
     */
    @Column(name = "fake_teaching_time")
    private Integer fakeTeachingTime;

    /**
     * 评分的总人数
     */
    @Column(name = "scorer_num")
    private Integer scorerNum;

    /**
     * 总分数
     */
    @Column(name = "total_score_num")
    private Integer totalScoreNum;

    /**
     * 真假分值[0:假分值,1:真分值]
     */
    private Integer status;

    /**
     * 操作时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 操作人
     */
    private Integer updator;

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
     * 获取老师id
     *
     * @return authority_user_id - 老师id
     */
    public Long getAuthorityUserId() {
        return authorityUserId;
    }

    /**
     * 设置老师id
     *
     * @param authorityUserId 老师id
     */
    public void setAuthorityUserId(Long authorityUserId) {
        this.authorityUserId = authorityUserId;
    }

    /**
     * 获取真实平均分
     *
     * @return average_score - 真实平均分
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * 设置真实平均分
     *
     * @param averageScore 真实平均分
     */
    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * 获取服务学员人数
     *
     * @return server_num - 服务学员人数
     */
    public Integer getServerNum() {
        return serverNum;
    }

    /**
     * 设置服务学员人数
     *
     * @param serverNum 服务学员人数
     */
    public void setServerNum(Integer serverNum) {
        this.serverNum = serverNum;
    }

    /**
     * 获取授课时长
     *
     * @return teaching_time - 授课时长
     */
    public Integer getTeachingTime() {
        return teachingTime;
    }

    /**
     * 设置授课时长
     *
     * @param teachingTime 授课时长
     */
    public void setTeachingTime(Integer teachingTime) {
        this.teachingTime = teachingTime;
    }

    /**
     * 获取假平均分
     *
     * @return fake_average_score - 假平均分
     */
    public Double getFakeAverageScore() {
        return fakeAverageScore;
    }

    /**
     * 设置假平均分
     *
     * @param fakeAverageScore 假平均分
     */
    public void setFakeAverageScore(Double fakeAverageScore) {
        this.fakeAverageScore = fakeAverageScore;
    }

    /**
     * 获取假服务学员人数
     *
     * @return fake_server_num - 假服务学员人数
     */
    public Integer getFakeServerNum() {
        return fakeServerNum;
    }

    /**
     * 设置假服务学员人数
     *
     * @param fakeServerNum 假服务学员人数
     */
    public void setFakeServerNum(Integer fakeServerNum) {
        this.fakeServerNum = fakeServerNum;
    }

    /**
     * 获取假授课时长
     *
     * @return fake_teaching_time - 假授课时长
     */
    public Integer getFakeTeachingTime() {
        return fakeTeachingTime;
    }

    /**
     * 设置假授课时长
     *
     * @param fakeTeachingTime 假授课时长
     */
    public void setFakeTeachingTime(Integer fakeTeachingTime) {
        this.fakeTeachingTime = fakeTeachingTime;
    }

    /**
     * 获取评分的总人数
     *
     * @return scorer_num - 评分的总人数
     */
    public Integer getScorerNum() {
        return scorerNum;
    }

    /**
     * 设置评分的总人数
     *
     * @param scorerNum 评分的总人数
     */
    public void setScorerNum(Integer scorerNum) {
        this.scorerNum = scorerNum;
    }

    /**
     * 获取总分数
     *
     * @return total_score_num - 总分数
     */
    public Integer getTotalScoreNum() {
        return totalScoreNum;
    }

    /**
     * 设置总分数
     *
     * @param totalScoreNum 总分数
     */
    public void setTotalScoreNum(Integer totalScoreNum) {
        this.totalScoreNum = totalScoreNum;
    }

    /**
     * 获取真假分值[0:假分值,1:真分值]
     *
     * @return status - 真假分值[0:假分值,1:真分值]
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置真假分值[0:假分值,1:真分值]
     *
     * @param status 真假分值[0:假分值,1:真分值]
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取操作时间
     *
     * @return update_time - 操作时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置操作时间
     *
     * @param updateTime 操作时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取操作人
     *
     * @return updator - 操作人
     */
    public Integer getUpdator() {
        return updator;
    }

    /**
     * 设置操作人
     *
     * @param updator 操作人
     */
    public void setUpdator(Integer updator) {
        this.updator = updator;
    }
}