package com.duia.commodity.service;

import com.duia.commodity.model.ClassStudentAgreement;
import com.duia.commodity.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/26.
 */
public interface ClassStudentAgreementService extends Service<ClassStudentAgreement> {

    /**
     * 查询学员保险、保过信息
     *
     * @param userId
     * @param classId
     * @return
     */
    List<ClassStudentAgreement> findClassStudentAgreementByClassId(Long userId, Long classId);

    /**
     * 保存用户协议
     *
     * @param userId
     * @param templateId
     * @param type
     * @param orderDetailId
     * @param orderId
     * @param proId
     */
    void saveClassStudentAgreement(Long userId, Long templateId, Integer type, Long orderDetailId, Long orderId, Long proId);
}
