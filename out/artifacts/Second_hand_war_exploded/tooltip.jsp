<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/25
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tooltip</title>
</head>
<style type="text/css">
    #tooltip_01{
        width: 300px;
        height: 40px;
        background-color: #c0deed;
        margin: 0 auto;
        position: fixed;
        text-align: center;
        top: 0;
        left: 0;
        right: 0;
        border: #58aced 1px solid;
        border-radius: 5px;
        z-index: 100;
    }
    .sp1{
        color: #2b8aff;
        line-height: 40px;
    }
    .sp2 a{
        color: red;
        border: #2b8aff 1px solid;
        text-decoration: none;
    }
</style>
<body>
<c:if test="${tip != null}">
    <div id="tooltip_01">
        <span class="sp1">${tip}</span>
        <span class="sp2"><a href="javascript:closetip()">X</a></span>
    </div>
    <c:remove var="tip" scope="session"/>
</c:if>
</body>
</html>
<script type="text/javascript">
    /************关闭弹窗*******************/
    function closetip(){
        document.getElementById("tooltip_01").style.display = "none";
    }
</script>