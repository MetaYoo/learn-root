package com.kotall.learn.springboot.security;

import com.kotall.learn.springboot.security.dao.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Service("userDetailsService")
@Slf4j
public class JdbcUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            log.info("=== query user by username : {}", username);
            com.kotall.learn.springboot.security.bean.User user = this.userRepository.findByUsername(username);
            log.info("=== user : {}", user);
            userDetails = new User(username, user.getPassword().toLowerCase(),true,true,true,true,new ArrayList<GrantedAuthority>());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userDetails;
    }
}
