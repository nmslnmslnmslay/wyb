<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新客户信息</title>
</head>
<body>
    <form action="customerServlet?op=update" method="post">
        <%--id要放在隐藏域中--%>
        <input type="hidden" value="${pageNum}" name="pageNum">
        <input type="hidden" value="${cust.id}" name="id">
        客户名称：<input type="text" name="name" value="${cust.name}"> <br/>
        联系方式：<input type="text" name="cellphone" value="${cust.cellphone}"> <br />
        <input type="submit" value="修改">
        <input type="button" value="返回" onclick="javascript:history.back()" />
    </form>
</body>
</html>
