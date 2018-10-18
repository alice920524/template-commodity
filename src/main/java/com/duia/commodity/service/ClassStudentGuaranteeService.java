package com.duia.commodity.service;

import com.duia.commodity.model.ClassStudentGuarantee;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2017/08/10.
 */
public interface ClassStudentGuaranteeService extends Service<ClassStudentGuarantee> {

    /**
     * 判断学员未签署的协议
     *
     * @param classId
     * @param userId
     * @return
     */
    Integer findUnSignGuarantee(Long classId, Long userId);

    /**
     * 保存学员课程协议表
     *
     * @param userId        用户id
     * @param templateId    协议id
     * @param orderDetailId 订单详情id
     * @param orderId       订单id
     * @param proId         关联id - classId
     */
    void saveClassStudentGuarantee(Long userId, Long templateId, Long orderDetailId, Long orderId, Long proId);
}
