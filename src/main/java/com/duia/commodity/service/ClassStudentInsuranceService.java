package com.duia.commodity.service;

import com.duia.commodity.model.ClassStudentInsurance;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.PayOrderDetail;


/**
 * Created by CodeGenerator on 2017/07/19.
 */
public interface ClassStudentInsuranceService extends Service<ClassStudentInsurance> {

    /**
     * 查询学员未签署的保险个数-该班级
     *
     * @param classId
     * @param userId
     * @return
     */
    Integer findUnSignInsurance(Long classId, Long userId);

    /**
     * 保存学员保险信息
     *
     * @param userId        用户id
     * @param templateId    协议id
     * @param orderDetailId 订单详情id
     * @param orderId       订单id
     * @param proId         关联id - 班级id
     */
    void saveClassStudentInsurance(Long userId, Long templateId, Long orderDetailId, Long orderId, Long proId);
}
