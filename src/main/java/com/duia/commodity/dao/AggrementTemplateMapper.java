package com.duia.commodity.dao;

import com.duia.commodity.common.dto.InsuranceDTO;
import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.AggrementTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AggrementTemplateMapper extends Mapper<AggrementTemplate> {

    List<InsuranceDTO> selectInsuranceByClassTypeIdList(List<Long> classTypeIdList);

    /**
     * 查询协议内容
     *
     * @param aggId
     * @param comId
     * @return
     */
    AggrementTemplate selectInsuranceAggrementTemplate(@Param("aggId") Long aggId, @Param("comId") Long comId);
    /**
     * 查询协议内容
     *
     * @param orderDetailId
     * @param aggType
     * @return
     */
    AggrementTemplate selectAggrementTemplateByOrderDetailId(@Param("orderDetailId") Long orderDetailId, @Param("aggType") Integer aggType);


    /**
     * 查询协议
     * @param classTypeId
     * @param type
     * @param guaType
     * @return
     */
    AggrementTemplate selectByClassTypeIdAndType(@Param("classTypeId")Long classTypeId, @Param("type")Integer type,@Param("guaType")Integer guaType);

    /**
     * 根据班型查询协议
     * @param classTypeId
     * @return
     */
    List<AggrementTemplate> selectByClassId(@Param("classTypeId") Long classTypeId);
}