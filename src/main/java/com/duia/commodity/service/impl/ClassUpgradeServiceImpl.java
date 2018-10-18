package com.duia.commodity.service.impl;

import com.duia.commodity.dao.ClassUpgradeMapper;
import com.duia.commodity.model.ClassUpgrade;
import com.duia.commodity.service.ClassUpgradeService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/16.
 */
@Service
@Transactional
public class ClassUpgradeServiceImpl extends AbstractService<ClassUpgrade> implements ClassUpgradeService {
    @Resource
    private ClassUpgradeMapper classUpgradeMapper;

    @Override
    public ClassUpgrade findClassUpgrade(Long beforeClassId, Long afterClassId) {
        ClassUpgrade condition = new ClassUpgrade();
        condition.setBeforeClassid(beforeClassId);
        condition.setAfterClassid(afterClassId);
        return classUpgradeMapper.selectOne(condition);
    }
}
