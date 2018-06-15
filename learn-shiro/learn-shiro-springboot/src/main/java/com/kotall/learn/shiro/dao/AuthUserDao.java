package com.kotall.learn.shiro.dao;

import com.kotall.learn.shiro.bean.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/4/1 0001 上午 9:24
 */
public interface AuthUserDao extends JpaRepository<AuthUser, String> {

    /**
     * 根据用户名查找
     *
     * @param name
     * @return
     */
    @Query(value = "select t.* from auth_user t where t.name =:name", nativeQuery=true)
    AuthUser findByName(@Param("name") String name);
}
