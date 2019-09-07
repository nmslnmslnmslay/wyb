package com.iweb.controller;

import com.iweb.entity.Customer;
import com.iweb.service.CustomerService;
import com.iweb.service.impl.CustomerServiceImpl;
import com.iweb.util.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author azzhu
 * @create 2019-08-13 16:33:47
 */
@WebServlet("/customerServlet")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.解决请求的乱码问题---post
       // request.setCharacterEncoding("utf-8");
        //2.解决响应的乱码问题
      //  response.setContentType("text/html;charset=utf-8");

        //获取op
        String op = request.getParameter("op");
        if("pageList".equals(op)) {
            pageList(request,response);
        }else if("delById".equals(op)) {
            delById(request,response);
        } else if("batchDel".equals(op)) {
            batchDel(request,response);
        } else if("toUpdate".equals(op)) {
            toUpdate(request,response);
        } else if("update".equals(op)) {
            update(request,response);
        }
    }

    /**
     * 修改操作
     * @param request
     * @param response
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取请求参数
        String id = request.getParameter("id");
        String pageNum = request.getParameter("pageNum");
        String name = request.getParameter("name");
        String cellphone = request.getParameter("cellphone");

        //2.执行修改
        //一个个参数传递，还是传一个对象 ==> 选择传递对象
        //思考？封装对象的时候麻烦不？ ===
        // 1.可能会涉及到类型转换【类型转换异常】
        // 2.在向对象中设置值的时候，要么定义对应个数的构造方法，要么setXxx设值
        //BeanUtils工具类 ====》 自己
       // request.getParameterMap()
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setCellphone(cellphone);

        customerService.updateCustomer(customer);

        //3.最终要在list2.jsp页面中显示内容
        response.sendRedirect(request.getContextPath()+"/customerServlet?op=pageList&pageNum="+pageNum);
    }

    /**
     * 跳转到修改页面
     * @param request
     * @param response
     */
    private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");

        //2.根据id去数据库查询
        Customer customer =  customerService.getCustById(id);

        //3.放入到request域中
        request.setAttribute("cust",customer);
        //将pageNum也放入到request域中
        request.setAttribute("pageNum",request.getParameter("pageNum"));

        //4.转发到/WEB-INF/update.jsp
        request.getRequestDispatcher("/WEB-INF/update.jsp").forward(request,response);
    }

    /**
     * 批量删除
     * @param request
     * @param response
     */
    private void batchDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ids = request.getParameter("ids");

        customerService.batchDel(ids);

        System.out.println(ids);
        //删除之后，重定向到customerServlet#pageList
        response.sendRedirect(request.getContextPath()+"/customerServlet?op=pageList");
    }

    /**
     * 根据id删除单个
     * @param request
     * @param response
     */
    private void delById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取id
        String id = request.getParameter("id");

        //执行删除
        customerService.delById(id);

        //删除之后，重定向到customerServlet#pageList
        response.sendRedirect(request.getContextPath()+"/customerServlet?op=pageList");
    }

    /**
     * 分页数据
     * @param request
     * @param response
     */
    private void pageList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNum = request.getParameter("pageNum");
        if(pageNum == null) {
            pageNum = "1";
        }
        PageBean<Customer> page = customerService.getPage(Integer.parseInt(pageNum));

        //将这个page放入到request域中
        request.setAttribute("page",page);

        //转发到list2.jsp
        request.getRequestDispatcher("/WEB-INF/list2.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
