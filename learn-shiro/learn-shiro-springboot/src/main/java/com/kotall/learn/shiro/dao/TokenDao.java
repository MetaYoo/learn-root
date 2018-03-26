package com.kotall.learn.shiro.dao;

import com.kotall.learn.shiro.bean.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/25 0025 上午 9:36
 */
public interface TokenDao extends JpaRepository<AccessToken, Integer> {

}
