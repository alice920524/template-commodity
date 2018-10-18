package com.duia.commodity.dao;

import com.duia.commodity.common.dto.ClassesStudentDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.ClassStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassStudentMapper extends Mapper<ClassStudent> {

    List<ClassesStudentDTO> selectByClassId(Long classId);

    Integer findUserIsBuyClassId(@Param("classId") Long classId, @Param("userId") Long userId);

    Long selectHasServiceClassByOrderId(@Param("orderId") Long orderId);

    ClassStudent findUserIsBuyClassIdStudentId(@Param("classId") Long classId, @Param("studentId") Integer studentId);
}