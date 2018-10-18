package com.duia.commodity.service;

import com.duia.commodity.common.dto.ClassesStudentDTO;
import com.duia.commodity.core.Service;
import com.duia.commodity.model.ClassStudent;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/15.
 */
public interface ClassStudentService extends Service<ClassStudent> {
    List<ClassesStudentDTO> findByClassId(Long classId);
    List<ClassStudent> findByOrderId(Long orderId);
    Long findCSByOrderId(Long orderId);

    /**
     * 检测学员班级购买情况
     * @param classId
     * @param studentId
     * @return
     */
    ClassStudent findUserIsBuyClassIdAndStudentId(Long classId, Integer studentId);
    Integer findUserIsBuyClassId(Long classId,Long userId);

    /**
     *
     * 查询当前班型购买的人数
     * @param classId
     * @return
     */
    Integer findCountIsBuyClassId(Long classId);

}
