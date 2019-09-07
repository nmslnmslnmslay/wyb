<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="static/jquery-1.12.4.js"></script>
    <style>
        .red {
            color: red;
        }
    </style>
    <script>
        $(function () {
            //表单的前端校验
            $("form").submit(function () {
                //获取对应的用户名和密码，以及验证码
                //用户名不能为空，长度3-6位
                var uname = $("input[name='username']").val();
                if(uname=="" || uname.length >6 || uname.length < 3){
                    //给出错误提示
                    $("#uname").html("用户名长度3-6位").addClass("red");
                    return false;
                }

                //密码6位
                var pwd = $("input[name='pwd']").val();
                if(pwd.length != 6){
                    //给出错误提示
                    $("#pwd").html("密码密码6位").addClass("red");
                    return false;
                }

                //验证码不能为空，且长度为4位
                var code = $("input[name='code']").val();
                if(code.length != 4){
                    //给出错误提示
                    $("#mycode").html("验证码需为4位").addClass("red");
                    return false;
                }
            });

            //切换验证码：即发送不同的请求，为了防止浏览器缓存，我们一般在后面加参数
            //比如，math的随机数、时间戳等
            $("#code").click(function () {
                $(this).attr("src","code?t="+new Date());
            });
        })
    </script>

</head>
<body>
<form action="loginServlet?op=login" method="post">
    用户名:<input type="text" name="username"/> <span id="uname"></span>
    <br />
    密码:<input type="password" name="pwd"/><span id="pwd"></span>
    <br />
    验证码：<input type="text" name="code"> <img src="code" id="code">
    <span id="mycode">
        <%--若验证码没有错误，此处应该什么都不显示--%>
        <%=request.getAttribute("msg")%>
    </span>
    <br />
    <input type="submit" value="登录">
</form>
</body>
</html>