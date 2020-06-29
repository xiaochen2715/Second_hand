<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/23
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
</head>
<link rel="stylesheet" href="css/registration.css">
<body>
<div>
    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-管理员登录</p>
            <a href="home">返回首页</a>
        </div>
    </div>
    <div>
        <table>
            <form action="admiLoginServlet" method="post">
                <tr>
                    <th>账号：</th>
                    <td><input type="text" name="aid"></td>
                </tr>
                <tr>
                    <th>密码：</th>
                    <td><input type="password" name="apwd"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" value="登  录" id="butt" onclick="return check(this.form)"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="userlogin.jsp" id="a">我是用户</a></td>
                </tr>
            </form>
        </table>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function check(form) {
        if (form.aid.value == "") {
            alert("请输入账号！");
            form.aid.focus();
            return false;
        }else if (form.apwd.value == "") {
            alert("请输入密码！");
            form.apwd.focus();
            return false;
        }else {
            form.submit();
            return true;
        }
    }

</script>
