package com.kotall.learn.rbac.common.dao;

import com.kotall.learn.rbac.common.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/19 16:54
 */
public interface UserDao extends JpaRepository<SysUser, Integer> {

    @Query("select t.* from t_user t where t.username=#{userName}")
    SysUser getByUsername(String userName);
}
