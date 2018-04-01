package com.kotall.learn.shiro.dao;

import com.kotall.learn.shiro.bean.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/25 0025 上午 9:36
 */
public interface AuthTokenDao extends JpaRepository<AuthToken, String> {

    @Query(value = "select * from auth_token t where t.token =:token", nativeQuery = true)
    AuthToken findByToken(@Param("token") String accessToken);
}
