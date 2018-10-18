package com.duia.commodity.service;

import com.duia.commodity.model.ClassTypeDeadline;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2017/11/07.
 */
public interface ClassTypeDeadlineService extends Service<ClassTypeDeadline> {

    /**
     * 根据班型id查询质保期信息
     *
     * @param classTypeId
     * @return
     */
//    ClassTypeDeadline findByClassTypeId(Long classTypeId);

    ClassTypeDeadline findByClassTypeId(Long classTypeId, Integer guaType);

}
