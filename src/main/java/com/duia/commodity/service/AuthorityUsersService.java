package com.duia.commodity.service;
import com.duia.commodity.common.dto.TeacherDTO;
import com.duia.commodity.model.AuthorityUsers;
import com.duia.commodity.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2017/07/17.
 */
public interface AuthorityUsersService extends Service<AuthorityUsers> {

    /**
     * 通过班级Id查询老师信息
     * @param classId
     * @return
     */
    List<TeacherDTO> findByClassId(Long classId);

    /**
     * 通过email查询用户权限
     * @param email
     * @return
     */
    AuthorityUsers selectUsersByEmail(String email);
}
