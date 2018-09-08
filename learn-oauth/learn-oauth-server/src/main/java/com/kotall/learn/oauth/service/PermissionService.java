package com.kotall.learn.oauth.service;


import com.kotall.learn.oauth.model.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    List<Permission> findByAdminUserId(int userId);
}
