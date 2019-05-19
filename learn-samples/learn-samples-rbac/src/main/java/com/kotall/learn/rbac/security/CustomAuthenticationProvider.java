package com.kotall.learn.rbac.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/14 22:59
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 这个获取表单输入中返回的用户名;
        String userName = authentication.getName();
        // 这个是表单中输入的密码
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
        if (userDetails == null) {
            throw new BadCredentialsException("用户名不存在");
        }

        if (!userDetails.getPassword().equals(password)) {
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);

    }

}
