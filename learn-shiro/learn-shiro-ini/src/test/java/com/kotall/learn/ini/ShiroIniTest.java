package com.kotall.learn.ini;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.env.DefaultEnvironment;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class ShiroIniTest {

    @Test
    public void ini() {
        org.apache.shiro.mgt.SecurityManager securityManager = new DefaultEnvironment().getSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zpwang", "wzp123#@!".toCharArray());

        subject.login(token);

        Assert.assertEquals(true, subject.isAuthenticated());
    }
}
