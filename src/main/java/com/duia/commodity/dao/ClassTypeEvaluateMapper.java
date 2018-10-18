package com.duia.commodity.dao;

import com.duia.commodity.common.dto.ClassTypeEvaluateDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.ClassTypeEvaluate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassTypeEvaluateMapper extends Mapper<ClassTypeEvaluate> {
    ClassTypeEvaluate selectByClassIdAndUserId(@Param("classId") Long classId,@Param("userId") Long userId);
    /**
     * 商品评价内容
     * @param classTypeId
     * @return 班型ID
     */
    List<ClassTypeEvaluateDTO> selectEvaluateContentByClassTypeId(@Param("classTypeId")Long classTypeId);

    /**
     * 查询评论的总数量
     */
    Integer selectEvaluateCountByClassTypeId(@Param("classTypeId")Long classTypeId);
}

