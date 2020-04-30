package com.kotall.learn.springboot.mystarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AutoConfigurationProperties.class)
@ConditionalOnClass(GetHashCodeClass.class)
public class AutoConfigurationClass {

    @Autowired
    private AutoConfigurationProperties autoConfigurationProperties;

    @ConditionalOnMissingBean
    @Bean
    public GetHashCodeClass getHashCodeClass() {
        return new GetHashCodeClass(autoConfigurationProperties.getTarget());
    }
}
