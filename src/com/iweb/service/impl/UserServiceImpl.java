package com.iweb.service.impl;

import com.iweb.dao.UserDao;
import com.iweb.dao.impl.UserDaoImpl;
import com.iweb.entity.User;
import com.iweb.service.UserService;

/**
 * @author azzhu
 * @create 2019-08-11 10:38:12
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(User user) {
        return userDao.findUser(user);
    }
}
