package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.ClassStudentQualityMapper;
import com.duia.commodity.model.ClassStudentQuality;
import com.duia.commodity.service.ClassStudentQualityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2017/08/09.
 */
@Service
@Transactional
public class ClassStudentQualityServiceImpl extends AbstractService<ClassStudentQuality> implements ClassStudentQualityService {
    @Resource
    private ClassStudentQualityMapper classStudentQualityMapper;

    @Override
    public void saveClassStudentQuality(Long userId, Long templateId, Long orderDetailId, Long orderId, Long proId) {
        ClassStudentQuality classStudentQuality = new ClassStudentQuality();
        classStudentQuality.setUserId(userId);
        classStudentQuality.setOrderId(orderId);
        classStudentQuality.setOrderDetailId(orderDetailId);
        classStudentQuality.setAggrementTemplateId(templateId);
        classStudentQuality.setClassId(proId);
        classStudentQuality.setLastClassId(proId);
        classStudentQuality.setCreateTime(new Date());
        classStudentQuality.setValidity(0);
        this.save(classStudentQuality);
    }
}
