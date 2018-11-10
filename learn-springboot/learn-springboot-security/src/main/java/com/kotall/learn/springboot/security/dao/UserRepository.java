package com.kotall.learn.springboot.security.dao;

import com.kotall.learn.springboot.security.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
