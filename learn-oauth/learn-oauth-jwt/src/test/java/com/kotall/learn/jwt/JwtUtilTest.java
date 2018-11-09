package com.kotall.learn.jwt;

import com.kotall.learn.User;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.time.format.DateTimeFormatter;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class JwtUtilTest {

    @Test
    public void testGetJwt() {
        User user = new User();
        user.setId(100001);
        user.setUsername("aracwong");
        user.setPassword("123456");
        String token = JwtUtil.createJWT(3600 * 1000 * 10, user);
        System.out.println(token);
    }

    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcmFjd29uZyIsInBhc3N3b3JkIjoiMTIzNDU2IiwiaWQiOjEwMDAwMSwiZXhwIjoxNTQxODA0MTMzLCJpYXQiOjE1NDE3NjgxMzMsImp0aSI6ImNmZWY3MTU1LWNlNjQtNDIxZC1hMGE0LTk0MjVhYzA1M2ZiMiIsInVzZXJuYW1lIjoiYXJhY3dvbmcifQ.NA5S4OWK9s2_kbh4iB-VkapJ3PtuTXjXlwlWdYwefKA";
        User user = new User();
        user.setPassword("123456");
        Claims claims = JwtUtil.parseJWT(token, user);
        System.out.println(claims.get("id"));
        System.out.println(claims.get("username"));
        System.out.println(claims.get("password"));
        System.out.println(claims.getExpiration());
    }
}
