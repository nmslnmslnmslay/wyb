package com.iweb.dao;

import com.iweb.entity.User;

/**
 * 用户操作接口
 * @author azzhu
 * @create 2019-08-11 10:26:14
 */
public interface UserDao {

    /**
     * 根据用户名和密码查询用户，参数你也可以写usename和password
     * @param user
     * @return
     */
    public User findUser(User user);
}
