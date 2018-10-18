package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.ClassTypeEvaluateDTO;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.ClassTypeEvaluateMapper;
import com.duia.commodity.model.ClassTypeEvaluate;
import com.duia.commodity.service.ClassTypeEvaluateService;
import com.duia.commodity.service.PicDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/12/01.
 */
@Service
@Transactional
public class ClassTypeEvaluateServiceImpl extends AbstractService<ClassTypeEvaluate> implements ClassTypeEvaluateService {
    @Resource
    private ClassTypeEvaluateMapper classTypeEvaluateMapper;
    @Resource
    private PicDataService picDataService;

    @Override
    public ClassTypeEvaluate selectByClassIdAndUserId(Long classId,Long userId) {
        return classTypeEvaluateMapper.selectByClassIdAndUserId(classId, userId);
    }

    /**
     * 通过classTypeId查询评论相关内容
     */
    @Override
    public List<ClassTypeEvaluateDTO> selectEvaluateContentByClassTypeId(Long classTypeId) {
        List<ClassTypeEvaluateDTO> classTypeEvaluateDTOList = classTypeEvaluateMapper.selectEvaluateContentByClassTypeId(classTypeId);

        for (ClassTypeEvaluateDTO classTypeEvaluateDTO : classTypeEvaluateDTOList) {

            String mobile = classTypeEvaluateDTO.getMobile();
            if (StringUtils.isNotEmpty(mobile)) {
                //中间4位*
                if (mobile.length() == 11) {
                    classTypeEvaluateDTO.setMobile(new StringBuilder(mobile).replace(3, 7, "****").toString());
                }
            }

            String unionName = classTypeEvaluateDTO.getUnionName();
            //如果不为空
            if(StringUtils.isNotEmpty(unionName)){
                //如果小于四位数，则截取前两位,后面的用*表示
                if (unionName.length() > 4) {
                    classTypeEvaluateDTO.setUnionName(new StringBuilder(unionName).replace(2, unionName.length() - 2, "***").toString());
                }
            }

            classTypeEvaluateDTO.setEvaluatePics(picDataService.selectPicDataByClassType(classTypeEvaluateDTO.getId()));
        }
        return classTypeEvaluateDTOList;
    }
    /**
     * 查询评论的总数量
     */
    public Integer selectEvaluateCountByClassTypeId(Long classTypeId){
        return classTypeEvaluateMapper.selectEvaluateCountByClassTypeId(classTypeId);
    }

}
