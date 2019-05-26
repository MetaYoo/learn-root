package com.kotall.learn.rbac.security;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/14 22:59
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String code = (String) attr.getRequest().getAttribute("code");
        if (Strings.isBlank(code) || !code.equalsIgnoreCase(((CustomWebAuthenticationDetails) authentication.getDetails()).getCode())) {
            throw new SessionAuthenticationException("验证码错误");
        }
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
