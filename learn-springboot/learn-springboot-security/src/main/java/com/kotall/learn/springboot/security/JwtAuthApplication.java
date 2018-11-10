package com.kotall.learn.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author aracwong
 * @version 1.0.0
 *
 */
@SpringBootApplication
public class JwtAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args).start();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
