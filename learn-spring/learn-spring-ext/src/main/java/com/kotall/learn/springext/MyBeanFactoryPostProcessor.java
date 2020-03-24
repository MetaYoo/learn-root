package com.kotall.learn.springext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/24 16:08
 * @since 1.0.0
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("plainBean");
        //beanDefinition.setScope("prototype");
        beanDefinition.setScope("singleton");
    }
}
