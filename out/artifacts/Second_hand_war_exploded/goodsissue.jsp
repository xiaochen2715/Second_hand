<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/20
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>我的发布</title>
</head>
<link rel="stylesheet" type="text/css" href="css/goodsissue.css"/>
<script src="js/goodsissue.js"></script>
<body>
<div>
    <%@include file="tooltip.jsp"%>

    <div id="header">
        <div id="header_1">
            <p id="tit">校园二手交易-我的发布</p>
            <a href="home">返回首页</a>
        </div>
    </div>
    <div id="fun">
        <a href="javascript:fck()">已发布物品</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:fmain()">我要发布</a>
    </div>

    <div id="ck">
        <table id="table1" cellspacing="20">
<%--            倒序遍历--%>
            <c:forEach items="${myGoods}" varStatus="stat">
                <c:set var="goods" value="${myGoods[fn:length(myGoods)-stat.index-1]}" scope="page"/>
                <form action="alterGoodsServlet" method="post">
                    <tr>
                        <td>
                            <input type="hidden" name="id" value="${goods.id}">
                            <div id="box">
                                <div id="box_1"><img id="img" src="uploadFiles/${goods.gpicture}" alt="校园二手"></div>
                                <div id="box_2">
                                    <p><b style="font-size: 25px;">${goods.gname}</b></p><br>
                                    <p><b>${goods.gdegree}</b>&nbsp;&nbsp;&nbsp;&nbsp;<b>${goods.gtype}</b></p><br>
                                    <p><b>编号:&nbsp;&nbsp;</b>${goods.id}&nbsp;&nbsp;&nbsp;&nbsp;<b>发布时间：</b>${goods.gtime}</p><br>
                                    <textarea name="gintro" placeholder="物品描述">${goods.gintro}</textarea>
                                </div>
                                <div id="box_3">
                                    <div id="box_3_1">
                                        <p>转手价格：<input type="text" name="gprice" value="${goods.gprice}"></p><br>
                                        <p>交易地点：<input type="text" name="gsite" value="${goods.gsite}"></p><br>
                                        <p>
                                            <c:if test="${goods.gstate=='已售出'}" >
                                                <input class="rad" type="radio" name="gstate" value="未售出" >未售出
                                                <input class="rad" type="radio" name="gstate" value="已售出" checked>已售出
                                            </c:if>
                                            <c:if test="${goods.gstate=='未售出'}" >
                                                <input class="rad" type="radio" name="gstate" value="未售出" checked>未售出
                                                <input class="rad" type="radio" name="gstate" value="已售出" >已售出
                                            </c:if>
                                        </p>
                                    </div>
                                    <div id="box_3_2">
                                        <c:if test="${goods.gputaway==0}">
                                            <p style="font-size: 22px;color: #cb5921">等待管理员审核...</p>
                                        </c:if>
                                        <c:if test="${goods.gputaway==1}">
                                            <p><a style="text-decoration: none;font-size: 22px;color: #5ecb21;" title="点击前往"
                                                    href="goodsDetailsServlet?goodsID=${goods.id}">已通过审核(点击查看)</a></p>
                                        </c:if>
                                        <br>
                                        <input type="button" value="确认修改" id="butt_1" onclick="return check1(this.form)">
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>

    <div id="main">
        <table cellspacing="20px">
            <form action="goodsIssueServlet" method="post" enctype="multipart/form-data">
                <tr>
                    <th>物品名称：</th>
                    <td><input type="text" name="gname"/></td>
                </tr>
                <tr>
                    <th>新旧程度：</th>
                    <td>
                        <select name="gdegree">
                            <option value ="全新">全新</option>
                            <option value ="九成新">九成新</option>
                            <option value ="八成新">八成新</option>
                            <option value ="七成新">七成新</option>
                            <option value ="六成新">六成新</option>
                            <option value ="五成新">五成新</option>
                            <option value ="四成新">四成新</option>
                            <option value ="三成新">三成新</option>
                            <option value ="二成新">二成新</option>
                            <option value ="一成新">一成新</option>
                        </select>
                    </td>
<%--                    图片预览区域--%>
                    <td rowspan="5">
                        <div id="imgarea" style="background-image: url(imgs/bg_showimg.png)">
                            <img src="imgs/bg_djsc.png" id="img3">
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>物品价格：</th>
                    <td><input type="text" name="gprice" style="width: 60px"/>&nbsp;&nbsp;元</td>
                </tr>
                <tr>
                    <th>物品类型：</th>
                    <td>
                        <select name="gtype">
                            <option value="电脑数码">电脑数码</option>
                            <option value="运动户外">运动户外</option>
                            <option value="图书杂志">图书杂志</option>
                            <option value="服饰鞋包">服饰鞋包</option>
                            <option value="护肤化妆">护肤化妆</option>
                            <option value="日用百货">日用百货</option>
                            <option value="礼品配饰">礼品配饰</option>
                            <option value="文化娱乐">文化娱乐</option>
                            <option value="其他">其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>交易地点：</th>
                    <td><input type="text" name="gsite"/></td>
                </tr>
                <tr>
                    <th>上传图片：</th>
                    <td>
                        <div id="sp1"><input type="file" id="file" name="gpicture" onchange="changepic(this)">选择图片</div>
                    </td>
                </tr>
                <tr>
                    <th>物品描述：</th>
                    <td colspan="2">
                        <textarea name="gintro" placeholder="介绍越详细,出售的几率越大..."></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center">
                        <input type="submit" value="确 认 发 布" id="butt" onclick="return check(this.form);" style="height: 50px;"/>
                    </td>
                </tr>
            </form>
        </table>
    </div>
</div>
</body>
</html>

