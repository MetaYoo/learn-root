package com.kotall.learn.rbac.security;

import com.kotall.learn.rbac.common.dao.UserDao;
import com.kotall.learn.rbac.common.entity.SysUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/14 22:52
 */
@Component("userDetailsService")
public class CustomUserDetailServiceImpl implements UserDetailsService {


    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        SysUser sysUser = this.userDao.getByUsername(userName);

        return new User(sysUser.getUsername(), sysUser.getPassword(), null);
    }

}
