package com.duia.commodity.service;

import com.duia.commodity.common.dto.ClassTypePriceConfigureDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.ClassType;
import com.duia.commodity.model.ClassTypeDeadline;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/15.
 */
public interface ClassTypeService extends Service<ClassType> {

//    Double findStudyPackagePriceByComIds(String[] comIds);

    List<Integer> getAggrements(ClassType classType);

    ClassTypeDeadline getClassTypeDeadline(Long classTypeId, Integer mode, Integer validity, Integer guaType);

    /**
     * 获取价格配置
     * */
    ClassTypePriceConfigureDTO getClassTypePriceConfigure(ClassType classType, Integer guaType);
    /**
     * 获取价格配置
     * */
    ClassTypePriceConfigureDTO getClassTypePriceConfigure(Long classTypeId, Integer guaType);
}
