package com.duia.commodity.service;

import com.duia.commodity.common.dto.InsuranceDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.AggrementTemplate;
import com.duia.commodity.model.ClassType;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.model.PayOrderDetail;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/15.
 */
public interface AggrementTemplateService extends Service<AggrementTemplate> {

    /**
     * 根据班型id查找协议模板信息
     *
     * @param classTypeIdList
     * @return
     */
    List<InsuranceDTO> findInsuranceByClassTypeIdList(List<Long> classTypeIdList);

    /**
     * 保存学员保险、保过、质保期、退款协议相关信息
     *
     * @param payOrder       订单
     * @param payOrderDetail 订单详情
     * @param classType      班型信息
     * @param insurance   保险数据
     */
    void saveClassStudentAgTemplate(PayOrder payOrder, PayOrderDetail payOrderDetail, ClassType classType, AggrementTemplate insurance);

    /**
     * 根据班型id和保险类型，查询协议相关的内容
     * @param classTypeId
     * @param type
     * @return
     */
    AggrementTemplate findByClassTypeIdAndType(Long classTypeId,Integer type,Integer guaType);
    /**
     * 查询保险协议
     * @param orderDetailId
     * @param type
     * @return
     */
    AggrementTemplate findByOrderDetailId(Long orderDetailId, Integer type);
    /**
     * 查询保险协议
     * @param comId
     * @param aggId
     * @return
     */
    AggrementTemplate findInsuranceAggrementTemplate(Long comId, Long aggId);
}
