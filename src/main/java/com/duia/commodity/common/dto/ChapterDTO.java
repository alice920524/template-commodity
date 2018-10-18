package com.duia.commodity.common.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李恒名 on 2017/7/13.
 */
public class ChapterDTO implements Serializable {
    private Long id;
    private String name;
    private List<CourseDTO> courseList;

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

    public List<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
