package com.iweb.test;

import com.iweb.util.JdbcUtils;
import org.junit.Test;

/**
 * 这里使用Junit单元测试
 * @Test ：该注解实现的功能类似main方法
 * 在一个类中，可以写多个@Test测试方法
 * @author azzhu
 * @create 2019-08-11 09:48:37
 */
public class TestJdbcUtil {

    @Test
    public void testGetConn() {
        System.out.println(JdbcUtils.getConn());
    }
}
