package com.duia.commodity.service.impl;

import com.duia.commodity.dao.UsersDepGrpRelMapper;
import com.duia.commodity.model.LiveTeacher;
import com.duia.commodity.model.UsersDepGrpRel;
import com.duia.commodity.service.LiveTeacherService;
import com.duia.commodity.service.UsersDepGrpRelService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
@Service
@Transactional
public class UsersDepGrpRelServiceImpl extends AbstractService<UsersDepGrpRel> implements UsersDepGrpRelService {
    @Resource
    private UsersDepGrpRelMapper usersDepGrpRelMapper;
    @Resource
    private LiveTeacherService liveTeacherService;

    @Override
    public boolean isGroup(Long teacherId) {
        LiveTeacher teacher = this.liveTeacherService.findById(teacherId);
        if (null == teacher) {
            return false;
        }

        UsersDepGrpRel query = new UsersDepGrpRel();
        query.setAuthUserId(teacher.getAuthorityUserId());
        query.setStatus(1);

        return (null != usersDepGrpRelMapper.selectOne(query));
    }

}
