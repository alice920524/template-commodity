package com.duia.commodity.common.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by liuhao on 2017/7/17.
 */
public class NormalRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        Object principal = usernamePasswordToken.getPrincipal();
        Object credentials = usernamePasswordToken.getCredentials();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, getName());
        return info;
    }
}
