<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/22
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<link rel="stylesheet" href="css/registration.css">
<script src="js/registration.js"></script>
<body>
<div>
    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-用户注册</p>
            <a href="home">返回首页</a>
        </div>
    </div>
    <div>
        <table >
            <form action="registrationServlet" method="post">
                <tr>
                    <th>姓名：</th>
                    <td><input type="text" name="uname" placeholder="建议输入真实姓名"></td>
                </tr>
                <tr>
                    <th>年级：</th>
                    <td><input type="text" name="uclass" placeholder="如：16级计算机一班"></td>
                </tr>
                <tr>
                    <th>联系方式：</th>
                    <td><input type="text" name="ucontentway" placeholder="便于买家联系"></td>
                </tr>
                <tr>
                    <th>邮箱(账号)：</th>
                    <td><input type="text" name="uemail" placeholder="如：112233@163.com" onblur="ajaxFunction1(this.value)"></td>
                </tr>
                <tr>
                    <th>密码：</th>
                    <td><input id="pwd1" type="password" name="upwd"></td>
                </tr>
                <tr>
                    <th>确认密码：</th>
                    <td><input id="pwd2" type="password"></td>
                </tr>
                <tr>
                    <th>输入验证码：</th>
                    <td><input type="text" name="uvc" id="yzm" onblur="ajaxFunction()" placeholder="区分大小写"></td>
<%--                    存放验证码的隐藏表单--%>
                    <input type="hidden" id="hid1" name="hid1" value="">
                    <input type="hidden" id="hid2" name="hid2" value="">
                </tr>
                <tr>
                    <td colspan="2">
                        <img id="code" src="validateCode">
                        <a href="javascript:refreshCode();"style="font-size: 15px">看不清，换一张！</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input id="butt" type="button" value="注  册" onclick="return check(this.form)"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="userlogin.jsp" id="a">前往登录</a></td>
                </tr>
            </form>
        </table>
    </div>
</div>
</body>
</html>
