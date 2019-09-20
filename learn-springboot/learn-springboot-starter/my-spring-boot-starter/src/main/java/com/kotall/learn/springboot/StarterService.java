package com.kotall.learn.springboot;

import org.springframework.util.StringUtils;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/9/20 17:02
 * @since 1.0.0
 */
public class StarterService {
    private String config;

    public StarterService(String config) {
        this.config = config;
    }

    public String[] split(String separatorChar) {
        return StringUtils.split(this.config, separatorChar);
    }
}
