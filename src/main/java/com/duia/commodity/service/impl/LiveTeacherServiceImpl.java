package com.duia.commodity.service.impl;

import com.duia.commodity.dao.LiveTeacherMapper;
import com.duia.commodity.model.AuthorityUsers;
import com.duia.commodity.model.LiveTeacher;
import com.duia.commodity.service.AuthorityUsersService;
import com.duia.commodity.service.LiveTeacherService;
import com.duia.commodity.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/05/28.
 */
@Service
@Transactional
public class LiveTeacherServiceImpl extends AbstractService<LiveTeacher> implements LiveTeacherService {
    @Resource
    private LiveTeacherMapper liveTeacherMapper;
    @Resource
    private AuthorityUsersService authorityUsersService;

    @Override
    public LiveTeacher getLiveTeacherByUserEmail(String email) {
        AuthorityUsers authorityUsers = this.authorityUsersService.selectUsersByEmail(email);
        if (null == authorityUsers) {
            return new LiveTeacher();
        }
        LiveTeacher liveTeacher = this.liveTeacherMapper.findByAuthUserId(authorityUsers.getId());
        if (null == liveTeacher) {
            return new LiveTeacher();
        }

        return liveTeacher;
    }

    @Override
    public LiveTeacher getLiveTeacherByAuthorityUserId(Long authorityUserId) {
        return this.liveTeacherMapper.findByAuthUserId(authorityUserId);
    }
}
