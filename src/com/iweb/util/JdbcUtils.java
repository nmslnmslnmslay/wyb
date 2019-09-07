package com.iweb.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 配置相关的，我们都单独放在配置文件中xx.properties后缀
 * @author azzhu
 * @create 2019-08-11 09:34:42
 */
public class JdbcUtils {
    private static String url = null;
    private static String user = null;
    private static String pwd = null;
    private static String driver = null;
    /*
    关键是如何从jdbc.properties配置文件中

    1.读到jdbc.properties文件
    2.根据jdbc.driver获取后面的值
    3.加载驱动和获取连接
    * */
    static {
        //静态代码块，只会执行一次，而且在构造方法之前
        //类加载器去加载配置文件:从类路径下获取资源
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        //针对xxx.properties文件，用Properties类，该类跟Map类似
        Properties prop = new Properties();
        try {
            //通过prop加载is
            prop.load(is);
            //根据键读取值
            driver = prop.getProperty("jdbc.driver");
            url = prop.getProperty("jdbc.url");
            user =prop.getProperty("jdbc.user");
            pwd = prop.getProperty("jdbc.pwd");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加载配置文件失败...");
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("加载驱动失败......");
        }
    }
    /*
    获取连接
    静态方法中，只能调用静态成员变量
    * */
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn =DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Sql注入问题 ---不安全
     * 可以通过sql的字符串拼接跳过登录，比如密码错误也能登录成功
     * ps：通过预编译，效率较高，而且不会存在sql注入问题
     * statement：通过字符串拼接实现加参数，比较麻烦，存在sql注入问题
     * 获取方式不同：ps = conn.ps(sql)
     *             st = conn.createStatement()
     * @param conn
     * @param rs
     * @param ps
     */
    public static void release(Connection conn, ResultSet rs, PreparedStatement ps){
        try{
            if(rs != null) {
                rs.close();
            }

            if(ps != null) {
                ps.close();
            }
            if(conn != null) {
                conn.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
