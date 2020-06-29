<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/17
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<link rel="stylesheet" href="css/registration.css">
<body>
<div>
    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-用户登录</p>
            <a href="home">返回首页</a>
        </div>
    </div>
    <div>
        <table>
            <form action="userLoginServlet" method="post">
                <tr>
                    <th>邮箱：</th>
                    <td><input type="text" name="uemail"></td>
                </tr>
                <tr>
                    <th>密码：</th>
                    <td><input type="password" name="upwd"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" value="登  录" id="butt" onclick="return check(this.form)"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="registration.jsp" id="a">前往注册</a></td>
                </tr>
            </form>
        </table>
    </div>
</div>

</body>
</html>
<script type="text/javascript">
    function check(form) {
        if (form.uemail.value == "") {
            alert("请输入邮箱！");
            form.uemail.focus();
            return false;
        }else if (form.upwd.value == "") {
            alert("请输入密码！");
            form.upwd.focus();
            return false;
        }else {
            form.submit();
            return true;
        }
    }

</script>
