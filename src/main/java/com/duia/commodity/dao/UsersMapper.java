package com.duia.commodity.dao;

import com.duia.commodity.core.Mapper;
import com.duia.commodity.model.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper extends Mapper<Users> {
    Users findUserById(@Param("userId") Long userId);
}