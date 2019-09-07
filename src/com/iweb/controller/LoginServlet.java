package com.iweb.controller;

import com.iweb.entity.User;
import com.iweb.service.CustomerService;
import com.iweb.service.UserService;
import com.iweb.service.impl.CustomerServiceImpl;
import com.iweb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author azzhu
 * @create 2019-08-11 09:06:23
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
//    private CustomerService customerService = new CustomerServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.解决请求的乱码问题---post
        request.setCharacterEncoding("utf-8");
        //2.解决响应的乱码问题
        response.setContentType("text/html;charset=utf-8");

        //获取op的值，根据p的值，调用不同的方法
        String op = request.getParameter("op");
        if("login".equals(op)) {
            login(request,response);
        } else if("logout".equals(op)) {
            logout(request,response);
        }
    }

    /**
     * 退出功能
     * @param request
     * @param response
     */
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //只需要从session中移除用户信息，然后回到登录页面即可
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        //转发和重定向选哪个？①是否需要携带域中的数据；②url地址栏是否需要变化  ==》重定向
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

    /**
     * 登录方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");

        //封装对象
        User user = new User();
        user.setUserName(username);
        user.setPassword(pwd);

        //调用service的login方法
        User loginUser = userService.login(user);

        //根据上面的返回值，判断用户登录是否成功
        PrintWriter writer = response.getWriter();
        //需要验证码通过之后，再去查询数据库
        //===============验证码start 2019.8.12==================
        //1.获取session
        HttpSession session = request.getSession();
        //2.从session中获取验证码
        Object yzm = session.getAttribute("yzm");
        //3.强转
        String sessYzm = (String)yzm;
        //4.获取前端传递过来的参数
        String code = request.getParameter("code");
        //5.比对
        if(code.equalsIgnoreCase(sessYzm)){
            //验证码通过之后，再判断数据库中用户是否存在
            //6.验证码用完之后，需要从session中移除
            session.removeAttribute("yzm");
            if(loginUser != null) {
                //3.将用户信息放入到session中:放在转发之前
                session.setAttribute("user",loginUser);

                //实际应该去一个主页面
                //需求：到一个主页面，在主页面中显示客户列表信息
                //1.客户列表信息放哪？域 request中
//                request.setAttribute("custs",customerService.getCusts());

                //2.只能通过转发到list.jsp,思考：能否转发到CustomerServlet，在这个Servlet的list方法中，再转发到list.jsp
//                request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request,response);

                //TODO 优化：采用曲线救国，即我们转发到custServelt#list/pageList，通过list/pageList转发到/WEB-INF/list2.jsp
//                request.getRequestDispatcher("/customerServlet?op=pageList").forward(request,response);
                //writer.write("登录成功.....");
                //TODO 0815 优化：上述转发，不能防止表单重复提交,所以使用重定向
                response.sendRedirect(request.getContextPath()+"/customerServlet?op=pageList");
                //writer.write("登录成功.....");

            } else {
                //带着错误信息，回到登录页面:可以细化，到底是用户名不对还是密码不对
                //没有必要将错误信息放入到session中
                request.setAttribute("msg","用户名或密码错误.....");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                // writer.write("用户名或密码错误.....");
            }
        } else {
            request.setAttribute("msg","验证码错误.....");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            // System.out.println("验证码错误....");
        }

        //===============验证码end ==================

//        if(loginUser != null) {
//            //实际应该去一个主页面
//           writer.write("登录成功.....");
//        } else {
//            //带着错误信息，回到登录页面
//            writer.write("用户名或密码错误.....");
//        }
        // System.out.println(username);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
