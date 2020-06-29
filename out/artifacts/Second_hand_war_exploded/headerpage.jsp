<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/17
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导航</title>
</head>
<link rel="stylesheet" type="text/css" href="css/headerpage.css"/>
<script src="js/headerpage.js"></script>
<body>
<%
    String s1 ;
    String s2;
    if (session.getAttribute("user")!=null){
        s1 = "none";
        s2 = "online";
    }else{
        s1 = "online";
        s2 = "none";
    }
%>
<div id="alldis">
    <div id="dis">
        <h2 id="tit"><a href="http://localhost:8888/Second_hand/home" style="text-decoration: none">校园二手交易</a></h2>
        <div id="dis_1" style="display: <%=s1%>">
            <a href="registration.jsp">前往注册</a>&nbsp;&nbsp;
            <a href="userlogin.jsp">用户登录</a>&nbsp;&nbsp;
            <a href="admilogin.jsp">管理员登录</a>&nbsp;&nbsp;
        </div>
        <div id="dis_2" style="display: <%=s2%>">
            <p>当前用户：${user.uname}</p>
            <p><a href="alterinfor.jsp">个人资料</a>&nbsp;&nbsp;<a href="releasedGoodsServlet">我的发布</a></p>
            <p><a href="demandIssueServlet">我的需求</a>&nbsp;&nbsp;<a href="myFocusServlet">我的关注</a></p>
            <p><a href="logoutServlet" onclick="return isLogout()">退出登录</a></p>
        </div>
        <div id="dis_3">
            <form action="searchServlet" method="post">
                <input id="dis_3_1" type="text" name="gnamechip">
                <input id="dis_3_2" type="submit" value="搜索">
<%--                隐藏表单存入一个值--%>
                <input type="hidden" value="gnamechip" name="hid">
            </form>
        </div>
    </div>
</div>

</body>
</html>
