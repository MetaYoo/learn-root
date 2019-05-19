package com.kotall.learn.rbac.security;

import com.alibaba.fastjson.JSON;
import com.kotall.learn.rbac.common.Token;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/14 22:57
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            UsernamePasswordAuthenticationToken authRequest;
            try (InputStream is = request.getInputStream()) {
                Token token = JSON.parseObject(is, Token.class);
                String verifyCode = (String) request.getSession().getAttribute("VERIFY_CODE");
                if (Strings.isBlank(verifyCode) || !verifyCode.equalsIgnoreCase(token.getVerifyCode())) {
                    throw new SessionAuthenticationException("验证码错误");
                }
                authRequest = new UsernamePasswordAuthenticationToken(token.getUserName(), token.getPassword());
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                throw new SessionAuthenticationException("获取用户登录凭证异常");
            }
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}
