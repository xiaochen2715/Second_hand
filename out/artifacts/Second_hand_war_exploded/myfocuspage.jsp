<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/30
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的关注</title>
</head>
<link rel="stylesheet" href="css/myfocuspage.css">
<body>
<div>
    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-我的关注</p>
            <a href="home">返回首页</a>
        </div>
    </div>
    <div id="main">
        <table>
            <tr>
                <th style="width: 150px;">图片</th>
                <th style="width: 300px;">名称</th>
                <th style="width: 150px;">价格</th>
                <th style="width: 150px;">状态</th>
                <th style="width: 100px;">查看详情</th>
            </tr>
            <tr><th colspan="5"><hr></th></tr>
            <c:forEach var="goods" items="${myGoods}">
                <tr>
                    <td><img src="uploadFiles/${goods.gpicture}" alt="图" style="width: 150px;height: 200px;"></td>
                    <td>${goods.gname}</td>
                    <td style="color: red;">${goods.gprice}</td>
                    <td style="color: darkred">${goods.gstate}</td>
                    <td><a href="goodsDetailsServlet?goodsID=${goods.id}">查看详情</a></td>
                </tr>
                <tr><th colspan="5"><hr></th></tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
