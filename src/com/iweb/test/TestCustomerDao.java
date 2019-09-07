package com.iweb.test;

import com.iweb.dao.CustomerDao;
import com.iweb.dao.impl.CustomerDaoImpl;
import com.iweb.entity.Customer;
import org.junit.Test;

import java.util.List;

public class TestCustomerDao {
    private CustomerDao customerDao = new CustomerDaoImpl();

    @Test
    public void testGetAll() {
        List<Customer> allCusts = customerDao.getAllCusts();
        allCusts.forEach(System.out::println);
    }
    @Test
    public void testGetCustById() {
        Customer customer = customerDao.getCustById("fdagffaa-fff");
        System.out.println(customer);
    }

    @Test
    public void testUpdateCustomerById() {
        Customer customer = customerDao.getCustById("fdaaa-fff");
        customer.setName("哈哈哈哈哈");
        customer.setCellphone("999999999");
        customerDao.updateCustomer(customer);
    }
}
