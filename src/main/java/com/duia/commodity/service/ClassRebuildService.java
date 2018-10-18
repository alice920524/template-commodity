package com.duia.commodity.service;
import com.duia.commodity.model.ClassRebuild;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2017/10/22.
 */
public interface ClassRebuildService extends Service<ClassRebuild> {

    ClassRebuild findByOrderId(Long orderId);

}
