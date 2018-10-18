package com.duia.commodity.common.realm;

import com.duia.commodity.model.Users;
import com.duia.commodity.service.UsersService;
import com.duia.sso.client.service.AbstractSessionClient;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dutianzhao on 2015/7/7.
 */

public class DuiaSessionClient extends AbstractSessionClient {
    @Resource
    private UsersService usersService;

    @Override
    public Map<String, Object> somePutSession(Long userId) {
        return null;
    }

    @Override
    public void beforeLogin(HttpServletRequest httpServletRequest) {

    }

    @Override
    public Object getUser(Long userId) {
        Users users = usersService.findUserById(userId);
        String pic = users.getPicUrl();
        if (StringUtils.isEmpty(pic)) {
            pic = "/temp/duia.png";
        }
        if (!pic.startsWith("/")) {
            pic = "/" + pic;
        }
        users.setPicUrl(pic);
        return users;
    }

    @Override
    public void afterLogin(HttpServletRequest request) {
    }

    @Override
    public void beforeLogout(HttpServletRequest request) {
    }

    @Override
    public void afterLogout(HttpServletRequest request) {

    }

    @Override
    public boolean isExeLogout(String path) {
        return false;
    }

}
