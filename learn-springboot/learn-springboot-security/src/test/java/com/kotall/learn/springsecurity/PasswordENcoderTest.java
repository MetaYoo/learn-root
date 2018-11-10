package com.kotall.learn.springsecurity;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class PasswordENcoderTest {

    @Test
    public void testEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("123456");
        System.out.println(encodedPassword);

        System.out.println(passwordEncoder.matches("123456", "$2a$10$ltIhc5nHjSfkkXnKBRHQiOL5JRKb4pjddvEf.VjwhFkKCtp18k/e2"));
    }
}
