package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.ClassStudentGuarantee;
import org.apache.ibatis.annotations.Param;

public interface ClassStudentGuaranteeMapper extends Mapper<ClassStudentGuarantee> {
    Integer selectUnSignGuarantee(@Param("classId") Long classId, @Param("userId") Long userId);
}