package com.duia.commodity.service.impl;

import com.alibaba.fastjson.JSON;
import com.duia.commodity.common.Constant;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.ClassesMapper;
import com.duia.commodity.model.ClassRebuild;
import com.duia.commodity.model.Classes;
import com.duia.commodity.model.PayOrder;
import com.duia.commodity.service.ClassRebuildService;
import com.duia.commodity.service.ClassStudentService;
import com.duia.commodity.service.ClassesService;
import com.google.common.collect.Lists;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by CodeGenerator on 2017/11/24.
 */
@Service
@Transactional
public class ClassesServiceImpl extends AbstractService<Classes> implements ClassesService {
    @Resource
    private ClassesMapper classesMapper;
    @Resource
    private ClassRebuildService classRebuildService;
    @Resource
    private ClassStudentService classStudentService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 通过班级id获取老师id
     * @param classId
     * @return
     */
    @Override
    public List<Long> findTeacherUserIds(Long classId) {
        List<Long> teacherIds = new ArrayList<>();
        String key = Constant.CACHE_KEY_PREFIX_TEACHER_LIST + classId;
        if (redisTemplate.hasKey(key)) {
            teacherIds = (List<Long>) redisTemplate.boundValueOps(key).get();
            return teacherIds;
        } else {
            Classes classes = this.classesMapper.selectOneByClassId(classId);
            if (null != classes) {
                String teachers = classes.getTeachers();
                if (null != teachers) {
                    try {
                        teacherIds = JSON.parseArray(teachers, Long.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // 主讲老师用户ID
                        teacherIds.add(classes.getAuthorityUserId());
                    }
                }
                redisTemplate.opsForValue().set(key, teacherIds, 1, TimeUnit.DAYS);
                return teacherIds;
            }
        }
        return Lists.newArrayList();
    }

    /**
     * 获取班级id
     *    新班级id或者老班班级id
     * @param payOrder
     * @return
     */
    @Override
    public Long findClassId(PayOrder payOrder) {

        if ("x".equals(payOrder.getType())) {
            ClassRebuild classRebuild = classRebuildService.findByOrderId(payOrder.getId());
            if (classRebuild != null) {
                return classRebuild.getNewClassId();
            }
        } else {
            Long classId = classStudentService.findCSByOrderId(payOrder.getId());
            if (classId != null) {
                return classId;
            }
        }
        return null;
    }
}
