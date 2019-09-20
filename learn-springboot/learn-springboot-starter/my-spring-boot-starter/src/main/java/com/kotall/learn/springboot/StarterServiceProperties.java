package com.kotall.learn.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/9/20 17:03
 * @since 1.0.0
 */

@ConfigurationProperties("example.service")
public class StarterServiceProperties {
    private String config;

    public void setConfig(String config) {
        this.config = config;
    }

    public String getConfig() {
        return config;
    }
}
