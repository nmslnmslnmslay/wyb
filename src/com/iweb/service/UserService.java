package com.iweb.service;

import com.iweb.entity.User;

/**
 * @author azzhu
 * @create 2019-08-11 10:37:36
 */
public interface UserService {
    /**
     * 做登录处理
     * @param user
     * @return
     */
    public User login(User user);
}
