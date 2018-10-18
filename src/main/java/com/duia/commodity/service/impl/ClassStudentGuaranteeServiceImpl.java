package com.duia.commodity.service.impl;

import com.duia.commodity.dao.ClassStudentGuaranteeMapper;
import com.duia.commodity.model.AggrementTemplate;
import com.duia.commodity.model.ClassStudentGuarantee;
import com.duia.commodity.model.CommodityProduct;
import com.duia.commodity.service.ClassStudentGuaranteeService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2017/08/10.
 */
@Service
@Transactional
public class ClassStudentGuaranteeServiceImpl extends AbstractService<ClassStudentGuarantee> implements ClassStudentGuaranteeService {
    @Resource
    private ClassStudentGuaranteeMapper classStudentGuaranteeMapper;

    @Override
    public Integer findUnSignGuarantee(Long classId, Long userId) {
        return classStudentGuaranteeMapper.selectUnSignGuarantee(classId, userId);
    }

    @Override
    public void saveClassStudentGuarantee(Long userId, Long templateId, Long orderDetailId, Long orderId, Long proId) {
        ClassStudentGuarantee classStudentGuarantee = new ClassStudentGuarantee();
        classStudentGuarantee.setUserId(userId);
        classStudentGuarantee.setOrderId(orderId);
        classStudentGuarantee.setOrderDetailId(orderDetailId);
        classStudentGuarantee.setAggrementTemplateId(templateId);
        classStudentGuarantee.setClassId(proId);
        classStudentGuarantee.setLastClassId(proId);
        classStudentGuarantee.setCreateTime(new Date());
        classStudentGuarantee.setStatus(0);
        classStudentGuarantee.setValidity(0);
        this.save(classStudentGuarantee);
    }
}
