package com.duia.commodity.service;

import com.duia.commodity.model.ClassStudentRefund;
import com.duia.commodity.core.Service;


/**
 * Created by CodeGenerator on 2017/08/09.
 */
public interface ClassStudentRefundService extends Service<ClassStudentRefund> {

    /**
     * 保存订单退款协议
     *
     * @param userId        用户id
     * @param templateId    协议id
     * @param orderDetailId 订单详情id
     * @param orderId       订单id
     * @param proId         关联id - classId
     */
    void saveClassStudentRefund(Long userId, Long templateId, Long orderDetailId, Long orderId, Long proId);

}
