package com.kotall.learn.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.kotall.learn.jpa.dal")
@SpringBootApplication
public class JpaDemo {
    public static void main(String[] args) {
        SpringApplication.run(JpaDemo.class, args);
    }
}
