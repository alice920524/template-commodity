package com.duia.commodity.service.impl;

import com.duia.commodity.core.AbstractService;
import com.duia.commodity.dao.UsersMapper;
import com.duia.commodity.model.Users;
import com.duia.commodity.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liuhao on 2017/7/28.
 */
@Service
public class UsersServiceImpl extends AbstractService<Users> implements UsersService {
    @Resource
    private UsersMapper usersMapper;
    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public Users findUserById(Long userId) {
        logger.info("usersMapper:->{},{}", usersMapper, userId);
        return usersMapper.findUserById(userId);
    }
}
