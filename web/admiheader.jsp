<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/26
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
</head>
<style type="text/css">
    *{
        margin: 0;
        padding: 0;
    }
    #header{
        width: 100%;
        height: 100px;
        background-color: #8dbfd9;
    }
    #header_1{
        width: 1200px;
        height: 100px;
        margin: 0 auto;
        position: relative;
        background-color: #8dbfd9;
    }
    #tit{
        font-size: 40px;
        color: #0085b5;
        font-family: "华文行楷";
        position: absolute;
        top: 30px;
        left: 400px;
    }
    #header_1 a{
        position: absolute;
        top: 10px;
        right: 0px;
        color: #e6f2f7;
        font-size: 20px;
        text-decoration: none;
        font-weight: bold;
    }
    #header_1 a:hover{
        color: #0085b5;
    }
    /*************************************/
</style>
<body>
<div>
    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-后台管理</p>
            <a href="">退出系统</a>
        </div>
    </div>
</div>
</body>
</html>
