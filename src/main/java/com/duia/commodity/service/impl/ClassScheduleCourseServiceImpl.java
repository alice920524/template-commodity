package com.duia.commodity.service.impl;

import com.duia.commodity.dao.ClassScheduleCourseMapper;
import com.duia.commodity.model.ClassScheduleCourse;
import com.duia.commodity.service.ClassScheduleCourseService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/15.
 */
@Service
@Transactional
public class ClassScheduleCourseServiceImpl extends AbstractService<ClassScheduleCourse> implements ClassScheduleCourseService {
    @Resource
    private ClassScheduleCourseMapper classScheduleCourseMapper;

}
