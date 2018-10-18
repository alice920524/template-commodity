package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.LiveTeacher;

public interface LiveTeacherMapper extends Mapper<LiveTeacher> {

    LiveTeacher findByAuthUserId(Long authUserId);
}