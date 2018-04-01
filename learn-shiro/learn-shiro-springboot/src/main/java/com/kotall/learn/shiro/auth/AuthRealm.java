package com.kotall.learn.shiro.auth;

import com.kotall.learn.shiro.bean.AuthToken;
import com.kotall.learn.shiro.bean.AuthUser;
import com.kotall.learn.shiro.dao.AuthTokenDao;
import com.kotall.learn.shiro.dao.AuthUserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/15 0015 下午 6:30
 */
@Slf4j
@Component("authRealm")
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private AuthUserDao userDao;
    @Autowired
    private AuthTokenDao tokenDao;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AccessToken;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO: 2018/4/1 0001
        log.info("=======doGetAuthorizationInfo========");
        return info;
    }

    /***
     *  认证 登录
     *  根据 token  设置 AuthenticationInfo
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("=======doGetAuthenticationInfo========");
        AccessToken accessToken = (AccessToken)token.getPrincipal();
        // 查询 token
        AuthToken authToken = this.tokenDao.findByToken(accessToken.getToken());
        if (authToken == null || authToken.getExpireTime().before(new Date())) {
            throw new ExpiredCredentialsException("token expired");
        }
        // 查询用户
        AuthUser user = this.userDao.findByName(authToken.getId());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
