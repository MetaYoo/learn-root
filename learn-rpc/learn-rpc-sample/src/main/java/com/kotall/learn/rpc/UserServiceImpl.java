package com.kotall.learn.rpc;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/3 15:53
 * @since 1.0.0
 */
public class UserServiceImpl {

    public User findById(int id) {
        return new User(id, "Alice");
    }
}
