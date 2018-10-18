package com.duia.commodity.service;
import com.duia.commodity.common.dto.ClassTypeEvaluateDTO;
import com.duia.commodity.model.ClassTypeEvaluate;
import com.duia.commodity.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/12/01.
 */
public interface ClassTypeEvaluateService extends Service<ClassTypeEvaluate> {
    /**
     * 通过班级id和学员id查询评论相关信息
     * @param classId
     * @param userId
     * @return
     */
    ClassTypeEvaluate selectByClassIdAndUserId(Long classId,Long userId);
    /**
     * 通过classTypeId查询评论相关内容
     */
    List<ClassTypeEvaluateDTO> selectEvaluateContentByClassTypeId(Long classTypeId);
    /**
     * 查询评论的总数量
     */
    Integer selectEvaluateCountByClassTypeId(Long classTypeId);
}
