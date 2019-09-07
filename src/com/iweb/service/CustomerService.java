package com.iweb.service;

import com.iweb.entity.Customer;
import com.iweb.util.PageBean;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCusts();

    /**
     * 分页数据
     * @param pageNum
     * @return
     */
    public PageBean<Customer> getPage(int pageNum);
    void delById(String id);

    /**
     * 批量删除
     * @param ids
     */
    void batchDel(String ids);

    Customer getCustById(String id);

    void updateCustomer(Customer customer);

}
