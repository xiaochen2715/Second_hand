<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/26
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
</head>
<frameset rows="100px,*,50px" framespacing=0 border=1 frameborder="1">
    <frame noresize name="head" scrolling="no" src="admiheader.jsp">
    <frameset cols="300px,*" id="resize">
        <frame noresize name="left" scrolling="no" src="admileft.jsp">
        <frame noresize name="right" scrolling="no" src="admiright.jsp">
    </frameset>
    <frame noresize name="foot" scrolling="no" src="admifooter.jsp">
</frameset>
<body>
</body>
</html>
