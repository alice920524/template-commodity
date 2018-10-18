package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.Classes;
import org.apache.ibatis.annotations.Param;

public interface ClassesMapper extends Mapper<Classes> {
    Classes selectOneByClassId(@Param("classId") Long classId);
}