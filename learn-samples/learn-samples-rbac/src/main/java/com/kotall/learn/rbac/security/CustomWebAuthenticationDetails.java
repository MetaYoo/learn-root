package com.kotall.learn.rbac.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/26 17:01
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    /**
     * 验证码
     */
    private String code;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            code = (String) request.getAttribute("code");
        }
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
