package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.TeacherScore;

public interface TeacherScoreMapper extends Mapper<TeacherScore> {

    TeacherScore selectTeacherByUserId(Long userId);

    void initTeacherScore(TeacherScore teacherScore);
}