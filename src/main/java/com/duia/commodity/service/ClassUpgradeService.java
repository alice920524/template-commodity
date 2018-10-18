package com.duia.commodity.service;

import com.duia.commodity.model.ClassUpgrade;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2018/03/16.
 */
public interface ClassUpgradeService extends Service<ClassUpgrade> {

    /**
     * 根据升级前后班级id获取升级信息
     *
     * @param beforeClassId
     * @param afterClassId
     * @return
     */
    ClassUpgrade findClassUpgrade(Long beforeClassId, Long afterClassId);

}
