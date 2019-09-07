package com.iweb.dao.impl;

import com.iweb.dao.CustomerDao;
import com.iweb.entity.Customer;
import com.iweb.util.JdbcUtils;
import com.iweb.util.PageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author azzhu
 * @create 2019-08-12 14:26:56
 */
public class CustomerDaoImpl implements CustomerDao {
    @Override
    public List<Customer> getAllCusts() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> custs = new ArrayList<>();
        Customer customer = null;
        try{
            conn = JdbcUtils.getConn();
            String sql = "select * from customer where status=1";
            ps = conn.prepareStatement(sql);

            //执行查询
            rs = ps.executeQuery();

            //处理结果集
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getString("id"));
                customer.setName(rs.getString("name"));
                customer.setGender(rs.getString("gender"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setCellphone(rs.getString("cellphone"));
                customer.setEmail(rs.getString("email"));
                customer.setHobby(rs.getString("hobby"));
                customer.setType(rs.getString("type"));
                customer.setDescription(rs.getString("description"));

                custs.add(customer);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,rs,ps);
        }

        return custs;
    }

    @Override
    public PageBean<Customer> getPage(Integer pageNum) {
        PageBean<Customer> page = new PageBean<>();
        //注意：下面要做的是将page中的属性值设置好
        page.setPageNum(pageNum);
        //获取totalCount
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Integer totalCounts = 0;
            conn = JdbcUtils.getConn();
            String sql = "select count(*) from customer where status=1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            //处理结果集
            while (rs.next()) {
                totalCounts = rs.getInt(1);
            }
            //设置
            page.setTotalCount(totalCounts);

            //查询listData  --- oracle  start----
            sql = "SELECT t.id,t.name,t.gender,t.birthday,t.cellphone,t.email,t.hobby,t.type,t.description,t.status from(\n" +
                    "select c.*,rownum rn from customer c where status = 1 AND rownum <= ? ) t WHERE t.rn > ?";
            ps = conn.prepareStatement(sql);
            //填充占位符
            ps.setInt(1,page.getPageNum()*page.getPageSize());
            ps.setInt(2,(page.getPageNum()-1)*page.getPageSize());

            //    --- oracle  end ----

            // =======mysql start ===============
//            sql = "select * from customer where status = 1 limit ?,?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,(page.getPageNum()-1)*page.getPageSize());
//            ps.setInt(2,page.getPageSize());
            // ======mysql end ===========
            //执行
            rs = ps.executeQuery();
            List<Customer> list = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getString("id"));
                customer.setName(rs.getString("name"));
                customer.setGender(rs.getString("gender"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setCellphone(rs.getString("cellphone"));
                customer.setEmail(rs.getString("email"));
                customer.setHobby(rs.getString("hobby"));
                customer.setType(rs.getString("type"));
                customer.setDescription(rs.getString("description"));

                list.add(customer);
            }
            //将list设置到page
            page.setListData(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,rs,ps);
        }

        return page;
    }


    @Override
    public void delById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JdbcUtils.getConn();
            String sql = "update customer set status=0 where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,null,ps);
        }
    }

    @Override
    public void batchDel(String ids) {
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JdbcUtils.getConn();
            StringBuilder sb = new StringBuilder("update customer set status=0 where id in (");
            //处理ids，将字符串变为数组
            String[] splits = ids.split(",");
            for (int i = 0; i < splits.length-1; i++) {
                sb.append("'"+splits[i]+"'"+",");
            }
            sb.append("'"+splits[splits.length-1]+"')");

            System.out.println("=========>"+sb.toString());
            ps = conn.prepareStatement(sb.toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,null,ps);
        }
    }

    @Override
    public Customer getCustById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try{
            conn = JdbcUtils.getConn();
            String sql = "select * from customer where status=1 and id=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1,id);

            //执行查询
            rs = ps.executeQuery();

            //处理结果集
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getString("id"));
                customer.setName(rs.getString("name"));
                customer.setGender(rs.getString("gender"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setCellphone(rs.getString("cellphone"));
                customer.setEmail(rs.getString("email"));
                customer.setHobby(rs.getString("hobby"));
                customer.setType(rs.getString("type"));
                customer.setDescription(rs.getString("description"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn,rs,ps);
        }

        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JdbcUtils.getConn();
            String sql = "update customer set name=?,cellphone=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getCellphone());
            ps.setString(3,customer.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,null,ps);
        }
    }
}
