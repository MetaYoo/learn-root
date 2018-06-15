package com.kotall.learn.i18n.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/8/12 0012 上午 11:11
 * @version: 1.0.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /** Spring应用上下文环境 */
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }
}
