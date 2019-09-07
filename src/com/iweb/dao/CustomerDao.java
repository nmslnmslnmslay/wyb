package com.iweb.dao;

import com.iweb.entity.Customer;
import com.iweb.util.PageBean;

import java.util.List;

public interface CustomerDao {
    public List<Customer> getAllCusts();
    public PageBean<Customer> getPage(Integer pageNum);
    void delById(String id);

    /**
     * 批量删
     * @param ids
     */
    void batchDel(String ids);

    Customer getCustById(String id);

    void updateCustomer(Customer customer);
}

