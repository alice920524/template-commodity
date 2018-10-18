package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.ClassStudentInsurance;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface ClassStudentInsuranceMapper extends Mapper<ClassStudentInsurance> {
    /**
     * 查询学员未签署的保险个数-该班级
     *
     * @param classId
     * @param userId
     * @return
     */
    Integer selectUnSignInsurance(@Param("classId") Long classId, @Param("userId") Long userId);
}