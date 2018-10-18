package com.duia.commodity.service.impl;

import com.duia.commodity.dao.ClassStudentInsuranceMapper;
import com.duia.commodity.model.ClassStudentInsurance;
import com.duia.commodity.model.PayOrderDetail;
import com.duia.commodity.service.ClassStudentInsuranceService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
@Service
@Transactional
public class ClassStudentInsuranceServiceImpl extends AbstractService<ClassStudentInsurance> implements ClassStudentInsuranceService {
    @Resource
    private ClassStudentInsuranceMapper classStudentInsuranceMapper;

    @Override
    public Integer findUnSignInsurance(Long classId, Long userId) {
        return classStudentInsuranceMapper.selectUnSignInsurance(classId, userId);
    }

    @Override
    public void saveClassStudentInsurance(Long userId, Long templateId, Long orderDetailId, Long orderId, Long proId) {
        ClassStudentInsurance classStudentInsurance = new ClassStudentInsurance();
        classStudentInsurance.setUserId(userId);
        classStudentInsurance.setOrderId(orderId);
        classStudentInsurance.setOrderDetailId(orderDetailId);
        classStudentInsurance.setAggrementTemplateId(templateId);
        classStudentInsurance.setClassId(proId);
        classStudentInsurance.setLastClassId(proId);
        classStudentInsurance.setCreateTime(new Date());
        classStudentInsurance.setStatus(0);
        classStudentInsurance.setValidity(0);
        this.save(classStudentInsurance);
    }
}
