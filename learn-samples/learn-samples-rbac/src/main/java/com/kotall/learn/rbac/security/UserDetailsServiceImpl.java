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
 * @time: 2019/5/26 16:56
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.userDao.getByUsername(username);
        User user = new User(sysUser.getUsername(), sysUser.getPassword(),null);
        return user;
    }
}
