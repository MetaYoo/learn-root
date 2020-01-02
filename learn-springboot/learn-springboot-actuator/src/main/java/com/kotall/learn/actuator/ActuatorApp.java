package com.kotall.learn.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/1/2 10:38
 * @since 1.0.0
 */
@SpringBootApplication
public class ActuatorApp {

    @Value("${spring.application.name}")
    private String application;

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApp.class, args);
    }

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> configurer() {
        return (registry) -> registry.config().commonTags("application", application);
    }

}
