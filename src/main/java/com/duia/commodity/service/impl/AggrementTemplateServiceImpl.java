package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.InsuranceDTO;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.AggrementTemplateMapper;
import com.duia.commodity.model.*;
import com.duia.commodity.service.AggrementTemplateService;
import com.duia.commodity.service.ClassStudentAgreementService;
import com.duia.commodity.service.ClassUpgradeService;
import com.duia.commodity.service.CommodityProductService;
import com.duia.enums.AgTemplate;
import com.duia.enums.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * Created by CodeGenerator on 2017/07/15.
 */
@Service
@Transactional
public class AggrementTemplateServiceImpl extends AbstractService<AggrementTemplate> implements AggrementTemplateService {
    @Resource
    private AggrementTemplateMapper aggrementTemplateMapper;
    @Resource
    private CommodityProductService commodityProductService;
    @Resource
    private ClassUpgradeService classUpgradeService;
    @Resource
    private ClassStudentAgreementService classStudentAgreementService;

    @Override
    public List<InsuranceDTO> findInsuranceByClassTypeIdList(List<Long> classTypeIdList) {
        return aggrementTemplateMapper.selectInsuranceByClassTypeIdList(classTypeIdList);
    }

    @Override
    public void saveClassStudentAgTemplate(PayOrder payOrder, PayOrderDetail payOrderDetail, ClassType classType, AggrementTemplate insurance) {

        Long classId;
        if (Objects.equals(payOrder.getType(), "u")) {
            // 查询升级信息
            ClassUpgrade classUpgrade = classUpgradeService.findById(payOrderDetail.getComId());
            classId = classUpgrade.getAfterClassid();
        } else {
            // 查询订单详情关联的班级信息
            CommodityProduct commodityProduct = commodityProductService.findBy("comId", payOrderDetail.getComId());
            classId = commodityProduct.getProId();
        }
        // 获取学员保险信息,并进行保存
        AggrementTemplate template = insurance;
        if (template != null) {
            // 记录学员保险协议信息
            classStudentAgreementService.saveClassStudentAgreement(payOrder.getUserId(), template.getId(), 1, payOrderDetail.getId(), payOrderDetail.getOrderId(), classId);
        }

        /**
         * 根据班型查询配置的协议信息(循环里面会有过滤)
         * */

        List<AggrementTemplate> templates = this.aggrementTemplateMapper.selectByClassId(classType.getId());

        for (int i = 0; i < templates.size(); i++) {
            template = templates.get(i);

            // 保险协议和奖学金协议忽略
            if ((Objects.equals(template.getType(), 1) || Objects.equals(template.getType(), 5))) {// 课程保险 - 已在上部进行保存
                continue;
            }

            // 班型上是否选择了该协议
            if (isSelect(classType, payOrderDetail, template)) {
                // 记录学员非保险和奖学金的协议信息
                classStudentAgreementService.saveClassStudentAgreement(payOrder.getUserId(), template.getId(), template.getType(), payOrderDetail.getId(), payOrderDetail.getOrderId(), classId);
            }
        }
    }

    /**
     * 班型上是否选择了该协议(不包含保险)
     *
     * @return true 已选择,false 未选择
     */
    private boolean isSelect(ClassType classType, PayOrderDetail payOrderDetail, AggrementTemplate template) {
        if (template == null || template.getType() == null) {
            return false;
        }

        if (template.getType().equals(AgTemplate.CURRICULUM.getState())) {// 保过
            return Objects.equals(classType.getGuaranteeAggrement(), Status.VALID.getState());
        }

        if (template.getType().equals(AgTemplate.WARRANTY.getState()) && Objects.equals(template.getGuaType(), payOrderDetail.getGuaType())) {// 质保期
            return Objects.equals(classType.getGuaranteeStatus(), Status.VALID.getState());
        }

        if (template.getType().equals(AgTemplate.REFUND.getState())) {// 退款
            return Objects.equals(classType.getRefundStatus(), Status.VALID.getState());
        }

        if (template.getType().equals(AgTemplate.PROTECT.getState())) {// 价保
            return Objects.equals(classType.getPriceProtect(), Status.VALID.getState());
        }
        return false;
    }

    @Override
    public AggrementTemplate findByClassTypeIdAndType(Long classTypeId, Integer type,Integer guaType) {
        return aggrementTemplateMapper.selectByClassTypeIdAndType(classTypeId,type,guaType);
    }

    @Override
    public AggrementTemplate findByOrderDetailId(Long orderDetailId, Integer type) {
        return this.aggrementTemplateMapper.selectAggrementTemplateByOrderDetailId(orderDetailId, type);
    }

    @Override
    public AggrementTemplate findInsuranceAggrementTemplate(Long comId, Long aggId) {
        return this.aggrementTemplateMapper.selectInsuranceAggrementTemplate(aggId, comId);
    }
}
