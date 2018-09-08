package com.kotall.learn.oauth.service.impl;

import com.kotall.learn.oauth.model.Permission;
import com.kotall.learn.oauth.model.SysUser;
import com.kotall.learn.oauth.repository.SysUserRepository;
import com.kotall.learn.oauth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//自定义userdetailservice
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.getUserByName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (sysUser != null) {
            System.err.println("id===============" + sysUser.getId());
            //获取用户的授权
            List<Permission> permissions = permissionService.findByAdminUserId(sysUser.getId());
            //声明授权文件
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }

        }
        return new User(sysUser.getName(), sysUser.getPassword(), grantedAuthorities);
    }
}
