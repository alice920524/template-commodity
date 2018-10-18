package com.duia.commodity.service.impl;

import com.duia.commodity.dao.ClassRebuildMapper;
import com.duia.commodity.model.ClassRebuild;
import com.duia.commodity.service.ClassRebuildService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/22.
 */
@Service
@Transactional
public class ClassRebuildServiceImpl extends AbstractService<ClassRebuild> implements ClassRebuildService {
    @Resource
    private ClassRebuildMapper classRebuildMapper;

    @Override
    public ClassRebuild findByOrderId(Long orderId) {
        return classRebuildMapper.selectByOrderId(orderId);
    }
}
