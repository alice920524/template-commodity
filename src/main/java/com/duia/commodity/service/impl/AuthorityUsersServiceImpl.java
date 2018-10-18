package com.duia.commodity.service.impl;

import com.duia.commodity.common.dto.TeacherDTO;
import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.AuthorityUsersMapper;
import com.duia.commodity.model.AuthorityUsers;
import com.duia.commodity.service.AuthorityUsersService;
import com.duia.commodity.service.ClassesService;
import com.duia.commodity.service.TeacherScoreService;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by CodeGenerator on 2017/07/17.
 */
@Service
@Transactional
public class AuthorityUsersServiceImpl extends AbstractService<AuthorityUsers> implements AuthorityUsersService {
    @Resource
    private AuthorityUsersMapper authorityUsersMapper;

    @Resource
    private ClassesService classesService;

    @Resource
    private TeacherScoreService teacherScoreService;

    /**
     * 通过班级Id查询老师信息
     * @param classId
     * @return
     */
    @Override
    public List<TeacherDTO> findByClassId(Long classId) {
        //查询出班级中的所有老师ID(第一个是主讲)
        List<Long> teacherUserId = classesService.findTeacherUserIds(classId);
        if (null != teacherUserId && !teacherUserId.isEmpty()) {
            List<TeacherDTO> teacherDTOList = new LinkedList<>();
            //查询出所有老师的基本信息(头像、昵称...)
            Map<Long, AuthorityUsers> authorityUsersMap = this.convertToMap(this.authorityUsersMapper.selectAuthorityUsers(teacherUserId));

            for (Long tuserId : teacherUserId) {
                AuthorityUsers authorityUsers = authorityUsersMap.get(tuserId);
                // 获取评分等信息(没有评分,初始化默认值并返回)
                TeacherDTO teacherDTO = new TeacherDTO(authorityUsers, this.teacherScoreService.getTeacherScore(authorityUsers.getId()));

                teacherDTOList.add(teacherDTO);
            }

            return teacherDTOList;
        }
        return new ArrayList<>();
    }

    /**
     * 通过email查询用户权限
     * @param email
     * @return
     */
    @Override
    public AuthorityUsers selectUsersByEmail(String email) {
        return this.authorityUsersMapper.selectByEmail(email);
    }

    /**
     * @Author: zhangchenxu
     * @Description:list转map 为了排序
     * @Date: 18:25 2018/4/9
     */
    private Map<Long, AuthorityUsers> convertToMap(List<AuthorityUsers> authorityUsersList) {
        return Maps.uniqueIndex(authorityUsersList, new Function<AuthorityUsers, Long>() {
            @Override
            public Long apply(AuthorityUsers authorityUsers) {
                return authorityUsers.getId();
            }
        });
    }


}
