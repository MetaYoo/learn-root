package com.kotall.learn.ini;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.env.DefaultEnvironment;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;


public class ShiroIniTest {

    @Test
    public void ini() {
        DefaultEnvironment environment = new DefaultEnvironment();

        environment.setSecurityManager(new DefaultSecurityManager(new IniRealm("classpath:shiro.ini")));

        SecurityUtils.setSecurityManager(environment.getSecurityManager());

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zpwang", "wzp123#@!".toCharArray());

        subject.login(token);

        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();

        Assert.assertEquals(false, subject.isAuthenticated());

    }

    @Test
    public void testJ2SeForShiro() {
        SecurityManager securityManager = new DefaultSecurityManager(new IniRealm("classpath:shiro.ini"));
        // 设置SecurityManager
        SecurityUtils.setSecurityManager(securityManager);
        // 获取Subject门面
        Subject subject = SecurityUtils.getSubject();

        // 登录
        subject.login(new UsernamePasswordToken("zpwang", "wzp123#@!"));

        Assert.assertTrue(subject.isAuthenticated());
    }

    @Test
    public void code() {
        String str = "hello world";
        String codeStr = Base64.encodeToString(str.getBytes());

        System.out.println(codeStr);

        String str2 = new String(Base64.decodeToString(codeStr.getBytes()));

        Assert.assertEquals(str, str2);

    }
}
