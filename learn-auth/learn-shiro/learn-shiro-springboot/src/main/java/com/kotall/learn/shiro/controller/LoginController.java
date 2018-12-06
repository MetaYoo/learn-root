package com.kotall.learn.shiro.controller;

import com.kotall.learn.shiro.auth.AccessToken;
import com.kotall.learn.shiro.bean.AuthToken;
import com.kotall.learn.shiro.bean.AuthUser;
import com.kotall.learn.shiro.dao.AuthUserDao;
import com.kotall.learn.shiro.dao.AuthTokenDao;
import com.kotall.learn.shiro.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/15 0015 下午 6:18
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    AuthUserDao userDao;

    @Autowired
    AuthTokenDao tokenDao;

    /**
     * 根据传递的 登录用户信息 生成 token
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password")String password) {
        AccessToken token = null;
        try {
            log.info("======username:{}, password:{}", username, password);
            Date expireTime = new Date(System.currentTimeMillis() + 1000 * 60 * 30);
            AuthToken authToken = this.tokenDao.save(new AuthToken(username, IdGenerator.generateValue(), expireTime));
            token = new AccessToken(authToken.getToken());
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                subject.login(token);
            }
        } catch (Exception e) {
            log.error("failed to login ", e);
        }
        return Result.ok().put("token", token.getToken());
    }

    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            AuthUser user = (AuthUser) SecurityUtils.getSubject().getPrincipal();
            AuthToken qbe = new AuthToken();
            qbe.setId(user.getName());
            Example<AuthToken> example = Example.of(qbe);
            Optional<AuthToken> optional = this.tokenDao.findOne(example);
            AuthToken token = optional.get();
            token.setToken(IdGenerator.generateValue());
            this.tokenDao.save(token);
            SecurityUtils.getSubject().logout();
        }

        return Result.ok();
    }
}
