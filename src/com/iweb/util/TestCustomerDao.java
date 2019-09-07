package com.iweb.util;

import com.iweb.dao.CustomerDao;
import com.iweb.dao.impl.CustomerDaoImpl;
import com.iweb.entity.Customer;
import org.junit.Test;

/**
 * @author azzhu
 * @create 2019-08-13 16:13:59
 */
public class TestCustomerDao {
    private CustomerDao customerDao = new CustomerDaoImpl();

    @Test
    public void getCustPage() {
        PageBean<Customer> page = customerDao.getPage(2);
        System.out.println(page);
    }
}
