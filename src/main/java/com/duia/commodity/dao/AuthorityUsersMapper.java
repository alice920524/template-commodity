package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.AuthorityUsers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityUsersMapper extends Mapper<AuthorityUsers> {

    List<AuthorityUsers> selectAuthorityUsers(@Param("teacherUserIds") List<Long> teacherUserIds);

    AuthorityUsers selectByEmail(String email);

}