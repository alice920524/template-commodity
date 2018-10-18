package com.duia.commodity.service.impl;

import com.duia.commodity.dao.ClassStudentAgreementMapper;
import com.duia.commodity.model.ClassStudentAgreement;
import com.duia.commodity.service.ClassStudentAgreementService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/26.
 */
@Service
@Transactional
public class ClassStudentAgreementServiceImpl extends AbstractService<ClassStudentAgreement> implements ClassStudentAgreementService {
    @Resource
    private ClassStudentAgreementMapper classStudentAgreementMapper;

    /**
     * 查询学员保险、保过信息
     *
     * @param userId
     * @param classId
     * @return
     */
    @Override
    public List<ClassStudentAgreement> findClassStudentAgreementByClassId(Long userId, Long classId) {
        Condition condition = new Condition(ClassStudentAgreement.class);
        condition.createCriteria().andEqualTo("userId", userId).andEqualTo("lastClassId", classId).andEqualTo("validity", 1).andEqualTo("status", 0);
        return this.findByCondition(condition);
    }

    @Override
    public void saveClassStudentAgreement(Long userId, Long templateId, Integer type, Long orderDetailId, Long orderId, Long proId) {
        ClassStudentAgreement agreement = new ClassStudentAgreement();
        agreement.setUserId(userId);
        agreement.setOrderId(orderId);
        agreement.setOrderDetailId(orderDetailId);
        agreement.setAggrementTemplateId(templateId);
        agreement.setType(type);
        agreement.setClassId(proId);
        agreement.setLastClassId(proId);
        agreement.setCreateTime(new Date());
        agreement.setStatus(0);
        agreement.setValidity(0);
        this.save(agreement);
    }
}
