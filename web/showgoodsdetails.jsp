<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/19
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品详情页面</title>
</head>
<link rel="stylesheet" type="text/css" href="css/showgoodsdetails.css"/>
<body>

<div id="main">
    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-物品详情</p>
            <a href="home">返回首页</a>
        </div>
    </div>
    <div id="main_main">
        <br><b><p>当前位置：<a href="home">返回首页</a>>${goodsDetails.gtype}</p></b><br>
        <div id="main_left">
            <div id="main_img">
                <img src="uploadFiles/${goodsDetails.gpicture}" alt="校园二手宝贝图">
            </div>
            <div id="main_text">
                <br><h2>${goodsDetails.gname}</h2>
                <br><p><b>出手价</b>&nbsp;&nbsp;<b style="color: red;font-size: 25px;">${goodsDetails.gprice}</b><b>&nbsp;&nbsp;元</b></p>
                <br><p><b>新旧程度：</b>${goodsDetails.gdegree}</p>
                <br><p><b>交易地点：</b>${goodsDetails.gsite}</p>
                <br><p><b>状态：</b><b style="color: darkred;font-size: 24px;">&nbsp;&nbsp;${goodsDetails.gstate}</b></p>
                <br><p><b>上架时间：</b>${goodsDetails.gtime}</p>
                <br><p><b>物品描述：</b>${goodsDetails.gintro}</p>
            </div>
            <div id="collect">
                <c:if test="${isFocus=='已关注'}">
                    <a href="javascript:ajaxAdd(${goodsDetails.id});" onclick="clickadd();" id="collectn" style="display: none;"><img src="imgs/weishoucang.png" alt="未收藏" title="点击收藏"></a>
                    <a href="javascript:ajaxDel(${goodsDetails.id});" onclick="clickdel();" id="collecty"><img src="imgs/yishoucang.png" alt="已收藏" title="点击取消收藏"></a>
                </c:if>
                <c:if test="${isFocus=='未关注'}">
                    <a href="javascript:ajaxAdd(${goodsDetails.id});" onclick="clickadd();" id="collectn"><img src="imgs/weishoucang.png" alt="未收藏" title="点击收藏"></a>
                    <a href="javascript:ajaxDel(${goodsDetails.id});" onclick="clickdel();" id="collecty" style="display: none;"><img src="imgs/yishoucang.png" alt="已收藏" title="点击取消收藏"></a>
                </c:if>

                <p id="test"></p>
            </div>
            <div id="main_message">
                <h2>留言：</h2>
                <form action="goodsDetailsServlet?goodsID=${goodsDetails.id}&key=fb" method="post">
                    <!-- <input type="text" class="message" name=""><br> -->
                    <textarea class="message" placeholder="说点什么..." name="mcontent"></textarea><br>
                    <input type="button" class="butt" value="发表留言" onclick="return messstate(this.form);">
                </form>
                <br><hr><br>
                <div id="message_log">
                    <c:forEach var="message" items="${allMessage}">
                        <br>
                        <p id="message_log_main"><b>${message.username}：</b>${message.mcontent}</p>
                        <div style="margin-right: 10px;width: 300px;float: right;">
                            <c:if test="${loginUser == message.user_uid}">
                                <a href="javascript:if (confirm('确定删除这条留言？'))location.replace('goodsDetailsServlet?goodsID=${goodsDetails.id}&key=sc&messageID=${message.id}');">删除</a>
                            </c:if>
                            <span style="float: right;">${message.mtime}</span>
                        </div>
                        <br>
                        <hr>
                    </c:forEach>

<%--                    <p id="message_log_vice"><b>谁谁谁参与讨论：</b>啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦--%>
<%--                        啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦</p>--%>
<%--                    <br>--%>
<%--                    <hr>--%>

                </div>
            </div>
        </div>
        <div id="main_right">
            <div id="main_user">
                <br><h2>物品主人信息</h2>
                <br><p><b>姓名：</b>${owner.uname}</p>
                <br><p><b>性别：</b>${owner.usex}</p>
                <br><p><b>院系年级：</b>${owner.uclass}</p>
                <br><p><b>Email：</b>${owner.uemail}</p>
                <br><p><b>联系方式：</b>${owner.ucontentway}</p>
                <br><p><b>个人说明：</b></p>
                <textarea class="description" placeholder="他没有留下什么..." readonly="readonly">${owner.udesc}</textarea>
            </div>
            <div>
                <h2>相关物品推荐</h2>
                <table border="0">
                    <c:forEach var="othergoods" items="${otherGoods}" end="4">
                        <tr>
                            <th class="right_related_name"><a href="goodsDetailsServlet?goodsID=${othergoods.id}"><p>${othergoods.gname}</p></a></th>
                            <th class="right_related_price">${othergoods.gprice}</th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function clickadd() {
        document.getElementById("collectn").style.display = "none";
        document.getElementById("collecty").style.display = "inline";
    }
    function clickdel() {
        document.getElementById("collectn").style.display = "inline";
        document.getElementById("collecty").style.display = "none";
    }

    function createXHR() {
        var xmlhttp;
        if (window.XMLHttpRequest) {    //若支持XMLHttpRequest
            xmlhttp = new XMLHttpRequest();
        }else{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlhttp;
    }
    function ajaxAdd(goodsID) {
        xhr = new XMLHttpRequest();;
        var url = "focusFunctionServlet?key=add&goodsID="+goodsID;
        xhr.open("GET",url,true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resData = xhr.responseText;
                document.getElementById("test").innerText=resData;
            }
        };
        xhr.send();
    }
    function ajaxDel(goodsID) {
        xhr = new XMLHttpRequest();
        var url = "focusFunctionServlet?key=del&goodsID="+goodsID;
        xhr.open("GET",url,true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resData = xhr.responseText;
                document.getElementById("test").innerText=resData;
            }
        };
        xhr.send();
    }

    function messstate(form) {
        if (form.mcontent.value == "") {
            form.mcontent.focus();
            alert("留言不能为空！");
            return false;
        }else{
            if (confirm("确定发表此留言？")) {
                form.submit();
                return true;
            }else{
                return false;
            }
        }
    }
</script>