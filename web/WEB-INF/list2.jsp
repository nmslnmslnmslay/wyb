<%@ page import="java.util.List" %>
<%@ page import="com.iweb.entity.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.iweb.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="static/jquery-1.12.4.js"></script>
    <script>
        $(function () {
            $("tr:gt(0):odd").css("background-color","green");

            // $("a[id]").click(function () {
            //     alert($(this).attr(id))
            // });

            //1.为批量删除绑定单击事件
            $("#batchDel").on("click",function () {
                var ids = new Array();
                //2.遍历<input type="checkbox" name="select"，判断哪些被选中
                $("input[name='select']").each(function () {
                    if(this.checked) {
                        //3.若被选择，需要拼接字符串，采用数组的push
                        ids.push(this.value);
                    }
                });

                //4.根据数组的长度，判断是否选中
                if(ids.length > 0) {
                   // console.log(ids)
                    //加一个确认框
                    var flag = confirm("确认要删除吗？");
                    if(flag){
                        location.href = "customerServlet?op=batchDel&ids="+ids;
                    }
                } else {
                    alert("没有选择要删除的....")
                }
            });
        });
    </script>
</head>
<body>
    欢迎：${user.userName}   <a href="loginServlet?op=logout">退出</a>

    <%--需要从域中获取数据，遍历并将其放入到表格中--%>
    <c:if test="${not empty page.listData}">
        <table border="1px solid red" cellspacing="0" cellpadding="0">
            <caption>客户列表</caption>
            <tr>
                <th colspan="5">
                    <button id="checkAll">全选</button>
                    <button id="reverseCheck">反选</button>
                    <button id="noCheck">全不选</button>
                    <button id="batchDel">批量删</button>
                    <button id="export">导出</button>
                    <button id="xx">某天出生人数【柱状图】</button>
                    <button id="xxx">客户分类【饼图】</button>
                    <button id="xxxxx">客户出生年份【折线图】</button>
                </th>
            </tr>
            <tr>
                <th>
                    <input type="checkbox" name="selectAll">
                </th>
                <th>序号</th>
                <th>名称</th>
                <th>手机号</th>
                <th>生日</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${page.listData}" var="cust" varStatus="vs">
                <tr>
                    <td>
                        <input type="checkbox" name="select" value="${cust.id}">
                    </td>
                    <td>${vs.count}</td>
                    <td>${cust.name}</td>
                    <td>${cust.cellphone}</td>
                    <td>
                        <fmt:formatDate value="${cust.birthday}" pattern="yyyy-MM"/>
                    </td>
                    <td>
                        <a href="customerServlet?op=view&id=${cust.id}">查看</a>
                        <a href="customerServlet?op=toUpdate&id=${cust.id}&pageNum=${page.pageNum}">修改</a>
                        <a href="customerServlet?op=delById&id=${cust.id}" id="${cust.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4">
                    <a href="customerServlet?op=pageList&pageNum=1">首页</a> &nbsp;&nbsp;
                    <a href="#">【${page.pageNum}】</a> &nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${page.pageNum < page.totalPages}">
                            <a href="customerServlet?op=pageList&pageNum=${page.pageNum+1}">下一页</a> &nbsp;&nbsp;
                        </c:when>
                    </c:choose>
                    <a href="customerServlet?op=pageList&pageNum=${page.totalPages}">末页</a> &nbsp;&nbsp;
                    <span>总共【${page.totalCount}】条记录</span> &nbsp;&nbsp;
                    <span>总共【${page.totalPages}】页</span>
                </td>
            </tr>
        </table>
    </c:if>

    <c:if test="${empty page.listData}">
        <h1>没有数据</h1>
    </c:if>
</body>
</html>
