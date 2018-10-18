package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.Constant;
import com.duia.commodity.common.dto.ClassTypePriceConfigureDTO;
import com.duia.commodity.common.util.DeadlineUtils;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.ClassTypeMapper;
import com.duia.commodity.model.ClassType;
import com.duia.commodity.model.ClassTypeDeadline;
import com.duia.commodity.service.ClassTypeDeadlineService;
import com.duia.commodity.service.ClassTypeService;
import com.duia.enums.AgTemplate;
import com.duia.enums.QaMode;
import com.duia.enums.Status;
import com.duia.enums.StudyPackage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by CodeGenerator on 2017/07/15.
 */
@Service
@Transactional
public class ClassTypeServiceImpl extends AbstractService<ClassType> implements ClassTypeService {
    @Resource
    private ClassTypeMapper classTypeMapper;
    @Resource
    private ClassTypeDeadlineService classTypeDeadlineService;

//    @Override
//    public Double findStudyPackagePriceByComIds(String[] comIds) {
//        return classTypeMapper.selectStudyPackagePriceByComIds(comIds);
//    }

    @Override
    public List<Integer> getAggrements(ClassType classType) {
        List agreements = new ArrayList();
        //协议列表 0:学习包 1:保险 2:保过 3:质保期 4:退款 6:七天价保
        if(Objects.equals(classType.getAddressMark(),StudyPackage.VALID.getState())){
            agreements.add(AgTemplate.BOOK.getState());
        }
        if (Objects.equals(classType.getInsurance(), Status.VALID.getState())) {
            agreements.add(AgTemplate.INSURANCE.getState());
        }
        if (Objects.equals(classType.getGuaranteeAggrement(), Status.VALID.getState())) {
            agreements.add(AgTemplate.CURRICULUM.getState());
        }
        if (Objects.equals(classType.getGuaranteeStatus(), Status.VALID.getState())) {
            agreements.add(AgTemplate.WARRANTY.getState());
        }
        if (Objects.equals(classType.getRefundStatus(), Status.VALID.getState())) {
            agreements.add(AgTemplate.REFUND.getState());
        }
        if (Objects.equals(classType.getPriceProtect(), Status.VALID.getState())) {
            agreements.add(AgTemplate.PROTECT.getState());
        }
        return agreements;
    }

    @Override
    public ClassTypeDeadline getClassTypeDeadline(Long classTypeId, Integer mode, Integer validity, Integer guaType) {
        if (Objects.equals(QaMode.DEAD_LINE.getState(), mode)) {
            ClassTypeDeadline classTypeDeadline = this.classTypeDeadlineService.findByClassTypeId(classTypeId, guaType);

            return classTypeDeadline;
        }

        ClassTypeDeadline classTypeDeadline = new ClassTypeDeadline();
        classTypeDeadline.setDeadline(DeadlineUtils.convertToDate(mode, validity));
        return classTypeDeadline;
    }

    @Override
    public ClassTypePriceConfigureDTO getClassTypePriceConfigure(ClassType classType, Integer guaType) {
        if (null == guaType || Constant.GUA_TYPE_COMMON.equals(guaType)) {
            return new ClassTypePriceConfigureDTO(null, 0.0, 0, 0);
        }
        // 根据guaType 取 班型 firstData 或 secondData
        String configure = null;
        if (Constant.GUA_TYPE_FIRST.equals(guaType)) {
            configure = classType.getFirstData();
        } else if (Constant.GUA_TYPE_SECOND.equals(guaType)) {
            configure = classType.getSecondData();
        }
        if (null == configure) {
            return new ClassTypePriceConfigureDTO(null, 0.0, 0, 0);
        }
//        {"gua":"0","delay":"0","price":0,"title":"2年质保期"}
//        {"gua":"1","delay":"0","price":0,"title":"5年质保期"}

        return JSON.parseObject(configure, ClassTypePriceConfigureDTO.class);
    }

    @Override
    public ClassTypePriceConfigureDTO getClassTypePriceConfigure(Long classTypeId, Integer guaType) {
        return this.getClassTypePriceConfigure(this.findById(classTypeId), guaType);
    }
}
