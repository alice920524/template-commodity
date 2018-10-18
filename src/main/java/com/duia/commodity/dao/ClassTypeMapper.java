package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.ClassType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ClassTypeMapper extends Mapper<ClassType> {
//    Double selectStudyPackagePriceByComIds(String[] comIds);
    Integer findClassTypeCountNum(@Param("classTypeId") Integer classTypeId);
//    List<ClassType> findClassTypeIdByOrderId(@Param("orderId") Integer orderId);
    ClassType findClassTypeMapperById(@Param("id") Integer id);

}