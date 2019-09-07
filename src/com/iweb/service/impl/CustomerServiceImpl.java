package com.iweb.service.impl;


import com.iweb.dao.CustomerDao;
import com.iweb.dao.impl.CustomerDaoImpl;
import com.iweb.entity.Customer;
import com.iweb.service.CustomerService;
import com.iweb.util.PageBean;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public List<Customer> getCusts() {
        return customerDao.getAllCusts();
    }

    @Override
    public PageBean<Customer> getPage(int pageNum) {
        return customerDao.getPage(pageNum);
    }

    @Override
    public void delById(String id) {

    }

    @Override
    public void batchDel(String ids) {

    }

    @Override
    public Customer getCustById(String id) {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }
}
