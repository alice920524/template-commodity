package com.duia.commodity.service.impl;

import com.duia.commodity.dao.CourseLectureMapper;
import com.duia.commodity.model.CourseLecture;
import com.duia.commodity.service.CourseLectureService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/02/26.
 */
@Service
@Transactional
public class CourseLectureServiceImpl extends AbstractService<CourseLecture> implements CourseLectureService {
    @Resource
    private CourseLectureMapper courseLectureMapper;

}
