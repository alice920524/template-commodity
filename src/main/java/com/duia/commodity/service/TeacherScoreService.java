package com.duia.commodity.service;

import com.duia.commodity.core.Service;
import com.duia.commodity.model.TeacherScore;

public interface TeacherScoreService extends Service<TeacherScore> {


    TeacherScore getTeacherScore(Long userId);
}