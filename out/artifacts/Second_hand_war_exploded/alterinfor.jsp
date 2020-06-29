<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/23
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人资料</title>
</head>
<link rel="stylesheet" href="css/alterinfor.css">
<body>

<%@include file="tooltip.jsp"%>

<div>
    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-个人资料</p>
            <a href="home">返回首页</a>
        </div>
    </div>
    <div>
        <table border="0" cellspacing="20px">
            <form action="alterInforServlet" method="post">
                <tr>
                    <th>编号：</th>
                    <td>${user.uid}</td>
                </tr>
                <tr>
                    <th>邮箱：</th>
                    <td>${user.uemail}</td>
                </tr>
                <tr>
                    <th>姓名：</th>
                    <td><input type="text" name="uname" value="${user.uname}"></td>
                </tr>
                <tr>
                    <th>性别：</th>
                    <td>
                        <%
                            String s1 = null;
                            String s2 = null;
                            String s3 = null;
                            User user1 = new User();
                            user1 = (User)session.getAttribute("user");
                            if (user1.getUsex().equals("保密")){
                                s1 = "checked";
                            }else if (user1.getUsex().equals("男")){
                                s2 = "checked";
                            }else{
                                s3 = "checked";
                            }
                        %>
                        <input name="usex" type="radio" value="保密" <%=s1%>>保密&nbsp;&nbsp;&nbsp;
                        <input name="usex" type="radio" value="男" <%=s2%>>男&nbsp;&nbsp;&nbsp;
                        <input name="usex" type="radio" value="女" <%=s3%>>女&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                    <th>年级：</th>
                    <td><input type="text" name="uclass" value="${user.uclass}"></td>
                </tr>
                <tr>
                    <th>联系方式：</th>
                    <td><input type="text" name="ucontentway" value="${user.ucontentway}"></td>
                </tr>
                <tr>
                    <th>个人说明：</th>
                    <td><textarea name="udesc" placeholder="随便说点什么...">${user.udesc}</textarea></td>
                </tr>
                <tr>
                    <th colspan="2"><input type="button" name="butt" id="butt" value="修改信息" onclick="return check(this.form)" /></th>
                </tr>
            </form>
        </table>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function check(form) {
        if(form.uname.value=="") {
            alert("请填写姓名！");
            form.uname.focus();
            return false;
        }else if(form.uclass.value=="") {
            alert("请填写年级！");
            form.uclass.focus();
            return false;
        }else if(form.ucontentway.value=="") {
            alert("请输入联系方式！");
            form.ucontentway.focus();
            return false;
        }else{
            if(confirm("确定修改信息吗？")){
                form.submit();
                return true;
            }else{
                return false;
            }
        }
    }
</script>