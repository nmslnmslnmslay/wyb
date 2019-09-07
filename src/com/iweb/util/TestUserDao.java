package com.iweb.util;

import com.iweb.dao.UserDao;
import com.iweb.dao.impl.UserDaoImpl;
import com.iweb.entity.User;
import org.junit.Test;

/**
 * @author azzhu
 * @create 2019-08-11 10:34:39
 */
public class TestUserDao {
    UserDao userDao = new UserDaoImpl();    //多态

    @Test
    public void findUser(){
        User user = new User();
        user.setUserName("admin");
        user.setPassword("123456");

        User loginUser = userDao.findUser(user);
        System.out.println(loginUser);
    }
}
