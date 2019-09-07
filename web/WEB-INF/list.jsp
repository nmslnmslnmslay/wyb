<%@ page import="java.util.List" %>
<%@ page import="com.iweb.entity.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.iweb.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="static/jquery-1.12.4.js"></script>
    <script>
        $(function () {
            $("tr:gt(0):odd").css("background-color","green");
        });
    </script>
</head>
<body>
    <%--<%=request.getAttribute("custs")%>--%>
<%--    <%
        User user = (User) session.getAttribute("user");
    %>--%>
    欢迎：<%--<%=user.getUserName()%>--%> ${user.userName}   <a href="loginServlet?op=logout">退出</a>

    <%--需要从域中获取数据，遍历并将其放入到表格中--%>
    <c:if test="${not empty custs}">
        <table border="1px solid red" cellspacing="0" cellpadding="0">
            <caption>客户列表</caption>
            <tr>
                <th>
                    <input type="checkbox" name="selectAll">
                </th>
                <th>序号</th>
                <th>名称</th>
                <th>手机号</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${custs}" var="cust" varStatus="vs">
                <tr>
                    <td>
                        <input type="checkbox" name="select" value="11">
                    </td>
                    <td>${vs.count}</td>
                    <td>${cust.name}</td>
                    <td>${cust.cellphone}</td>
                    <td>
                        <a href="#">查看</a>
                        <a href="#">修改</a>
                        <a href="#">删除</a>
                    </td>
                </tr>
            </c:forEach>
    <%--        <%
                List<Customer> custs = (ArrayList)request.getAttribute("custs");

                //遍历custs
                for(int i=0;i<custs.size();i++) {
            %>
                    <tr>
                        <td>
                            <input type="checkbox" name="select" value="11">
                        </td>
                        <td>
                            <%=custs.get(i).getId()%>
                        </td>
                        <td><%=custs.get(i).getName()%></td>
                        <td> <%=custs.get(i).getCellphone()%></td>
                        <td>
                            <a>查看</a>
                            <a>修改</a>
                            <a>删除</a>
                        </td>
                    </tr>
            <%
                }
            %>--%>
        </table>
    </c:if>

    <c:if test="${empty custs}">
        <h1>没有数据</h1>
    </c:if>
</body>
</html>
