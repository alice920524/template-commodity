package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.ClassesStudentDTO;
import com.duia.commodity.dao.ClassStudentMapper;
import com.duia.commodity.dao.ClassTypeMapper;
import com.duia.commodity.dao.ClassesMapper;
import com.duia.commodity.model.ClassStudent;
import com.duia.commodity.model.ClassType;
import com.duia.commodity.model.Classes;
import com.duia.commodity.service.ClassStudentService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/15.
 */
@Service
@Transactional
public class ClassStudentServiceImpl extends AbstractService<ClassStudent> implements ClassStudentService {
    @Resource
    private ClassStudentMapper classStudentMapper;
    @Resource
    private ClassesMapper classesMapper;
    @Resource
    private ClassTypeMapper classTypeMapper;

    /**
     * 通过班级id查询学生信息及支付时间
     * @param classId
     * @return
     */
    @Override
    public List<ClassesStudentDTO> findByClassId(Long classId) {
        return classStudentMapper.selectByClassId(classId);
    }

    @Override
    public List<ClassStudent> findByOrderId(Long orderId) {
        ClassStudent condition = new ClassStudent();
        condition.setOrderId(orderId);
        return classStudentMapper.select(condition);
    }

    @Override
    public Long findCSByOrderId(Long orderId) {
        // 查询配置的客服服务的班级id
        return classStudentMapper.selectHasServiceClassByOrderId(orderId);
    }

    /**
     * 检测学员班级购买情况
     * @param classId
     * @param studentId
     * @return
     */
    @Override
    public ClassStudent findUserIsBuyClassIdAndStudentId(Long classId, Integer studentId) {
        return classStudentMapper.findUserIsBuyClassIdStudentId(classId, studentId);
    }

    @Override
    public Integer findUserIsBuyClassId(Long classId, Long userId) {
        return classStudentMapper.findUserIsBuyClassId(classId, userId);
    }


    /**
     * 查询当前班型购买的人数
     *
     * @param classId
     * @return
     */
    @Override
    public Integer findCountIsBuyClassId(Long classId) {
        Classes classes = classesMapper.selectOneByClassId(classId);
        Integer count = classTypeMapper.findClassTypeCountNum(classes.getClassTypeId().intValue());
        ClassType classType = classTypeMapper.findClassTypeMapperById(classes.getClassTypeId().intValue());
        Integer base = classType.getBase();
        if (null == base) {
            base = 0;
        }
        return (base + count);
    }
}
