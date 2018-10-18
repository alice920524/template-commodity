package com.duia.commodity.model;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "class_schedule_chapter")
public class ClassScheduleChapter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_schedule_id")
    private Long classScheduleId;

    @Column(name = "chapter_name")
    private String chapterName;

    @Column(name = "chapter_order")
    private Integer chapterOrder;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
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
     * @return chapter_name
     */
    public String getChapterName() {
        return chapterName;
    }

    /**
     * @param chapterName
     */
    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    /**
     * @return chapter_order
     */
    public Integer getChapterOrder() {
        return chapterOrder;
    }

    /**
     * @param chapterOrder
     */
    public void setChapterOrder(Integer chapterOrder) {
        this.chapterOrder = chapterOrder;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}