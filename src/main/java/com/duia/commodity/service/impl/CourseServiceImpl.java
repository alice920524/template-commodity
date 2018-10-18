package com.duia.commodity.service.impl;

import com.duia.commodity.dao.CourseMapper;
import com.duia.commodity.model.Course;
import com.duia.commodity.service.CourseService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/08/02.
 */
@Service
@Transactional
public class CourseServiceImpl extends AbstractService<Course> implements CourseService {
    @Resource
    private CourseMapper courseMapper;

}
