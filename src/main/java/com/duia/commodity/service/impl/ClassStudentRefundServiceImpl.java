package com.duia.commodity.service.impl;

import com.duia.commodity.dao.ClassStudentRefundMapper;
import com.duia.commodity.model.ClassStudentRefund;
import com.duia.commodity.service.ClassStudentRefundService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by CodeGenerator on 2017/08/09.
 */
@Service
@Transactional
public class ClassStudentRefundServiceImpl extends AbstractService<ClassStudentRefund> implements ClassStudentRefundService {
    @Resource
    private ClassStudentRefundMapper classStudentRefundMapper;

    @Override
    public void saveClassStudentRefund(Long userId, Long templateId, Long orderDetailId, Long orderId, Long proId) {
        ClassStudentRefund classStudentRefund = new ClassStudentRefund();
        classStudentRefund.setUserId(userId);
        classStudentRefund.setOrderId(orderId);
        classStudentRefund.setOrderDetailId(orderDetailId);
        classStudentRefund.setAggrementTemplateId(templateId);
        classStudentRefund.setClassId(proId);
        classStudentRefund.setLastClassId(proId);
        classStudentRefund.setCreateTime(new Date());
        classStudentRefund.setValidity(0);
        this.save(classStudentRefund);
    }
}
