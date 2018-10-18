package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.ClassRebuild;
import org.apache.ibatis.annotations.Param;

public interface ClassRebuildMapper extends Mapper<ClassRebuild> {
    ClassRebuild selectByOrderId(@Param("orderId") Long orderId);
}