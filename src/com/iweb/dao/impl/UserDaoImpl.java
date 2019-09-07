package com.iweb.dao.impl;

import com.iweb.dao.UserDao;
import com.iweb.entity.User;
import com.iweb.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author azzhu
 * @create 2019-08-11 10:27:52
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User findUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User loginUser = null;
        try{
            conn = JdbcUtils.getConn();
            String sql = "select * from t_user where username=? and password=?";
            ps = conn.prepareStatement(sql);

            //填充占位符
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());

            //执行查询
            rs = ps.executeQuery();

            //处理结果集
            while (rs.next()) {
                loginUser = new User();
                loginUser.setUserName(rs.getString("username"));
                loginUser.setPassword(rs.getString("password"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,rs,ps);
        }

        return loginUser;
    }
}
