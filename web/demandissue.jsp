<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/30
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的需求</title>
</head>
<link rel="stylesheet" href="css/demandissue.css">
<body>
<div>
    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-我的需求</p>
            <a href="home">返回首页</a>
        </div>
    </div>
    <div id="fun">
        <a href="javascript:fmyd()">我的需求</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:fwantd()">发布需求</a>
    </div>
    <div id="main">
        <div id="dleft">
            <div id="myd">
                <hr><br>
                <c:forEach var="demand" items="${myDemand}">
                    <table border="0" cellspacing="10px">
                        <tr>
                            <th>标题</th>
                            <td>${demand.dtitle}</td>
                        </tr>
                        <tr>
                            <th>发表时间</th>
                            <td>${demand.dtime}</td>
                        </tr>
                        <tr>
                            <th>联系方式</th>
                            <td>${ucontentway}</td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td style="width: 600px;">${demand.dcontent}</td>
                            <td><a href="javascript:if(confirm('确定删除该需求？'))location.replace('demandIssueServlet?key=del&demandID=${demand.id}')">删除</a></td>
                        </tr>
                    </table>
                    <br><hr>
                </c:forEach>
            </div>
            <div id="fwantd" style="display: none;">
                <hr><br>
                <table border="0" cellspacing="10px">
                    <form action="demandIssueServlet?key=alt" method="post">
                        <tr>
                            <th>标题</th>
                            <td><input type="text" name="dtitle" id="inpu"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td colspan="2"><textarea name="dcontent" placeholder="建议说明想要的物品要求(如新旧程度,价位等)"></textarea></td>
                        </tr>
                        <tr>
                            <th>联系方式</th>
                            <td>${ucontentway}</td>
                            <td><input type="button" value="提交" id="butt" onclick="return check(this.form)"></td>
                        </tr>
                    </form>
                </table>
                <br><hr>
            </div>
        </div>
        <div id="dright">
            <h2>看看别人想要什么...</h2><br>
            <c:forEach var="allDemands" items="${sessionScope.allDemands}" begin="0" end="19">
                <p>&diams;&nbsp;&nbsp;<a title="联系方式：${ucontentway}内容：${allDemands.dcontent}">${allDemands.dtitle}</a></p>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function fmyd(){
        document.getElementById("myd").style.display = "block";
        document.getElementById("fwantd").style.display = "none";
    }
    function fwantd(){
        document.getElementById("myd").style.display = "none";
        document.getElementById("fwantd").style.display = "block";
    }

    function check(form) {
        if (form.dtitle.value == "") {
            alert("请输入标题！");
            form.dtitle.focus();
            return false;
        }else {
            if(confirm("确定发布需求？")){
                form.submit();
                return true;
            }else{
                return false;
            }
        }
    }
</script>
