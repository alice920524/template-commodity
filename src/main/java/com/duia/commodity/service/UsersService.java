package com.duia.commodity.service;

import com.duia.commodity.core.Service;
import com.duia.commodity.model.Users;

/**
 * Created by liuhao on 2017/7/28.
 */
public interface UsersService extends Service<Users> {
    Users findUserById(Long userId);
}
