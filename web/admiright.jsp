<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/26
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>right</title>
</head>
<link rel="stylesheet" href="css/admiright.css">
<body>
<div>
    <div id="he1">
        <div id="he2">
            <ul>
                <li><a href="">物品管理</a>
                    <ul>
                        <li><a href="manageGoodsServlet?key=1_1">全部物品</a></li>
                        <li><a href="manageGoodsServlet?key=1_2">待审核物品</a></li>
                        <li><a href="manageGoodsServlet?key=1_3">已售出物品</a></li>
                    </ul>
                </li>
                <li><a href="manageUserServlet?key=2_1">用户管理</a></li>
                <li><a href="">需求管理</a></li>
                <li><a href="manageNoticeServlet?key=4_1">公告管理</a></li>
            </ul>
        </div>
    </div>

    <c:if test="${requestScope.modname == 'mod1_1'}">
        <div id="mod1_1">
<%--            <form action="manageGoodsServlet?key=1_1" method="post">--%>
<%--                <input type="hidden" name="hid">--%>
<%--                物品名称：<input type="text" name="gnamechip">--%>
<%--                <input type="submit" value="搜索">--%>
<%--            </form>--%>
<%--            <form action="manageGoodsServlet?key=1_1" method="post">--%>
<%--                <input type="hidden" name="hid">--%>
<%--                物品类型：--%>
<%--                <select name="gtype">--%>
<%--                    <option value="电脑数码">电脑数码</option>--%>
<%--                    <option value="运动户外">运动户外</option>--%>
<%--                    <option value="图书杂志">图书杂志</option>--%>
<%--                    <option value="服饰鞋包">服饰鞋包</option>--%>
<%--                    <option value="护肤化妆">护肤化妆</option>--%>
<%--                    <option value="日用百货">日用百货</option>--%>
<%--                    <option value="礼品配饰">礼品配饰</option>--%>
<%--                    <option value="文化娱乐">文化娱乐</option>--%>
<%--                    <option value="其他">其他</option>--%>
<%--                </select>--%>
<%--                <input type="submit" value="查询">--%>
<%--            </form>--%>
            <p>全部物品</p>
            <table border="1">
                <tr>
                    <th>物品ID</th>
                    <th>物品名称</th>
                    <th>物品类型</th>
                    <th>上传时间</th>
                    <th>查看详情</th>
                    <th>删除</th>
                </tr>
                <c:forEach var="goods" items="${allGoods}" begin="${beginIndex}" end="${endIndex}">
                    <tr>
                        <td>${goods.id}</td>
                        <td>${goods.gname}</td>
                        <td>${goods.gtype}</td>
                        <td>${goods.gtime}</td>
                        <td><a href="manageGoodsServlet?key=1_1&goodsID=${goods.id}&pageCur=${pageCur}">查看详情</a></td>
<%--                        <td><a href="deleteServlet?goodsID=${goods.id}&key=1_1&pageCur=${pageCur}" onclick="deleteask()">删除</a></td>--%>
                        <td><a href="javascript:if(confirm('确定删除？')){
                            window.location='deleteServlet?goodsID=${goods.id}&key=1_1&pageCur=${pageCur}';
                            }">删除</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6">共${totalCount}条记录&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;
                        第${pageCur}页&nbsp;&nbsp;
                        <c:url var="url_pre" value="manageGoodsServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur-1}"/>
                            <c:param name="key" value="1_1"/>
                        </c:url>
                        <c:url var="url_next" value="manageGoodsServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur+1}"/>
                            <c:param name="key" value="1_1"/>
                        </c:url>
<%--                        如果是第一页，不显示“上一页”--%>
                        <c:if test="${pageCur!=1}">
                            <a href="${url_pre}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
<%--                        如果是最后一页，不显示“下一页”--%>
                        <c:if test="${pageCur!=totalPage && totalPage!=0}">
                            <a href="${url_next}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>

    <c:if test="${requestScope.modname == 'mod1_1_1'}">
        <div id="mod1_1_1">
            <p>物品详情</p><a href="manageGoodsServlet?key=1_1&pageCur=${pageCur}">返回</a>
            <table border="1">
                <tr>
                    <th>物品ID</th>
                    <td>${goodsDetails.id}</td>
                    <td rowspan="6" colspan="2"><img src="uploadFiles/${goodsDetails.gpicture}" alt="物品图片" style="width: 240px;height: 320px;"></td>
                </tr>
                <tr>
                    <th>物品名称</th>
                    <td>${goodsDetails.gname}</td>
                </tr>
                <tr>
                    <th>新旧程度</th>
                    <td>${goodsDetails.gdegree}</td>
                </tr>
                <tr>
                    <th>物品价格</th>
                    <td>${goodsDetails.gprice}</td>
                </tr>
                <tr>
                    <th>物品类型</th>
                    <td>${goodsDetails.gtype}</td>
                </tr>
                <tr>
                    <th>交易地点</th>
                    <td>${goodsDetails.gsite}</td>
                </tr>

                <tr>
                    <th>物品描述</th>
                    <td colspan="3" style="width: 500px;">${goodsDetails.gintro}</td>
                </tr>
                <tr>
                    <th>上传时间</th>
                    <td>${goodsDetails.gtime}</td>
                    <th>物品状态</th>
                    <td>${goodsDetails.gstate}</td>
                </tr>
                <tr>
                    <th>审核情况</th>
                    <c:if test="${goodsDetails.gputaway == 0}">
                        <td>等待审核...</td>
                    </c:if>
                    <c:if test="${goodsDetails.gputaway == 1}">
                        <td>已通过审核</td>
                    </c:if>
                    <th>物品所属着ID</th>
                    <td>${goodsDetails.user_uid}</td>
                </tr>

            </table>
        </div>
    </c:if>

    <c:if test="${requestScope.modname == 'mod1_2'}">
        <div id="mod1_2">
            <br><p>待审核物品</p>
            <table border="1">
                <c:forEach var="goods" items="${waitGoods}" begin="${beginIndex}" end="${endIndex}">
                    <tr>
                        <td rowspan="5">
                            <img src="uploadFiles/${goods.gpicture}" alt="物品图片" style="width: 240px;height: 320px;">
                        </td>
                        <td><b>物品ID：</b>${goods.id}&nbsp;&nbsp;&nbsp;&nbsp;<b>上传者ID：</b>${goods.user_uid}</td>
                        <td>
                            <b>物品类型(可修改)：</b>
                            <select name="gtype" id="gtype">
                                <option value="${goods.gtype}">${goods.gtype}</option>
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
                        <td><b>名称：</b>${goods.gname}</td>
                        <td><b>交易地点：</b>${goods.gsite}</td>
                    </tr>
                    <tr>
                        <td><b>价格：</b>${goods.gprice}</td>
                        <td>${goods.gstate}</td>
                    </tr>
                    <tr>
                        <td><b>新旧程度：</b>${goods.gdegree}</td>
                        <td style="color: #cb5921;">等待管理员审核...</td>
                    </tr>
                    <tr>
                        <td style="width: 400px;"><b>物品描述：</b>${goods.gintro}</td>
                        <td>
                            <b><a href="javascript:if(confirm('将会被删除'))location.replace('deleteServlet?goodsID=${goods.id}&key=1_2&pageCur=${pageCur}')">不通过</a></b>&nbsp;&nbsp;&nbsp;&nbsp;
                            <b><a href="javascript:if(confirm('确定通过审核？'))location.replace('alterGputawayServlet?goodsID=${goods.id}&gtype='+document.getElementById('gtype').value+'&key=1_2&pageCur=${pageCur}')">通过</a></b>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6">共${totalCount}条记录&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;
                        第${pageCur}页&nbsp;&nbsp;
                        <c:url var="url_pre" value="manageGoodsServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur-1}"/>
                            <c:param name="key" value="1_2"/>
                        </c:url>
                        <c:url var="url_next" value="manageGoodsServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur+1}"/>
                            <c:param name="key" value="1_2"/>
                        </c:url>
                            <%--                        如果是第一页，不显示“上一页”--%>
                        <c:if test="${pageCur!=1}">
                            <a href="${url_pre}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                            <%--                        如果是最后一页，不显示“下一页”--%>
                        <c:if test="${pageCur!=totalPage && totalPage!=0}">
                            <a href="${url_next}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>

    <c:if test="${requestScope.modname == 'mod1_3'}">
        <div id="mod1_3">
            <br><p>已售出物品</p>
            <table border="1">
                <c:forEach var="goods" items="${sellGoods}" begin="${beginIndex}" end="${endIndex}">
                    <tr>
                        <td rowspan="5">
                            <img src="uploadFiles/${goods.gpicture}" alt="物品图片" style="width: 240px;height: 320px;">
                        </td>
                        <td><b>物品ID：</b>${goods.id}&nbsp;&nbsp;&nbsp;&nbsp;<b>上传者ID：</b>${goods.user_uid}</td>
                        <td>
                            <b>物品类型：</b>${goods.gtype}</td>
                    </tr>
                    <tr>
                        <td><b>名称：</b>${goods.gname}</td>
                        <td><b>交易地点：</b>${goods.gsite}</td>
                    </tr>
                    <tr>
                        <td><b>价格：</b>${goods.gprice}</td>
                        <td style="color: #cb5921;">${goods.gstate}</td>
                    </tr>
                    <tr>
                        <td><b>新旧程度：</b>${goods.gdegree}</td>
                        <c:if test="${goods.gputaway==0}">
                            <td>等待管理员审核...</td>
                        </c:if>
                        <c:if test="${goods.gputaway==1}">
                            <td>以通过审核</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td style="width: 400px;"><b>物品描述：</b>${goods.gintro}</td>
                        <td>
                            <b><a href="javascript:if(confirm('将会被删除'))location.replace('deleteServlet?goodsID=${goods.id}&key=1_3&pageCur=${pageCur}')">删除</a></b>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6">共${totalCount}条记录&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;
                        第${pageCur}页&nbsp;&nbsp;
                        <c:url var="url_pre" value="manageGoodsServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur-1}"/>
                            <c:param name="key" value="1_3"/>
                        </c:url>
                        <c:url var="url_next" value="manageGoodsServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur+1}"/>
                            <c:param name="key" value="1_3"/>
                        </c:url>
                            <%--                        如果是第一页，不显示“上一页”--%>
                        <c:if test="${pageCur!=1}">
                            <a href="${url_pre}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                            <%--                        如果是最后一页，不显示“下一页”--%>
                        <c:if test="${pageCur!=totalPage && totalPage!=0}">
                            <a href="${url_next}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>

    <c:if test="${requestScope.modname == 'mod2_1'}">
        <div id="mod2_1">
            <br><br><p>全部用户</p>
            <table border="1">
                <c:forEach var="user" items="${allUsers}" begin="${beginIndex}" end="${endIndex}">
                    <tr>
                        <td><b>ID：</b>${user.uid}</td>
                        <td><b>用户Email：</b>${user.uemail}</td>
                    </tr>
                    <tr>
                        <td><b>用户姓名：</b>${user.uname}</td>
                        <td><b>班级：</b>${user.uclass}</td>
                    </tr>
                    <tr>
                        <td><b>性别：</b>${user.usex}</td>
                        <td><b>联系方式：</b>${user.ucontentway}</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="width: 600px;"><b>个人说明：</b>${user.udesc}</td>
                    </tr>
                    <tr><td colspan="2" style="height: 10px;border: 2px;"></td></tr>
                </c:forEach>
                <tr>
                    <td colspan="6">共${totalCount}条记录&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;
                        第${pageCur}页&nbsp;&nbsp;
                        <c:url var="url_pre" value="manageUserServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur-1}"/>
                            <c:param name="key" value="2_1"/>
                        </c:url>
                        <c:url var="url_next" value="manageUserServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur+1}"/>
                            <c:param name="key" value="2_1"/>
                        </c:url>
                            <%--                        如果是第一页，不显示“上一页”--%>
                        <c:if test="${pageCur!=1}">
                            <a href="${url_pre}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                            <%--                        如果是最后一页，不显示“下一页”--%>
                        <c:if test="${pageCur!=totalPage && totalPage!=0}">
                            <a href="${url_next}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>

    <c:if test="${requestScope.modname == 'mod3_1'}">
        <div id="mod3_1">
            <p>需求管理</p>
            <table border="1">
                <tr>
                    <th>需求ID</th>
                    <th>用户姓名</th>
                    <th>需求内容</th>
                    <th>发表时间</th>
                    <th>删除</th>
                </tr>

            </table>
        </div>
    </c:if>

    <c:if test="${requestScope.modname == 'mod4_1'}">
        <div id="mod4_1">
            <br><p>公告管理</p>
            <p><a href="addNoticeServlet?addN=t">发表公告</a></p>
            <table border="1">
                <tr>
                    <th>公告ID</th>
                    <th>公告标题</th>
                    <th>发表时间</th>
                    <th>公告内容</th>
                    <th>删除</th>
                </tr>
                <c:forEach var="notice" items="${allNotice}" begin="${beginIndex}" end="${endIndex}">
                    <tr>
                        <td>${notice.id}</td>
                        <td>${notice.ntitle}</td>
                        <td>${notice.ntime}</td>
                        <td><a href="manageNoticeServlet?noticeID=${notice.id}">查看内容</a></td>
                        <td><a href="javascript:if(confirm('确定删除此条公告？'))location.replace('deleteServlet?noticeID=${notice.id}&key=4_1&pageCur=${pageCur}')">删除</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6">共${totalCount}条记录&nbsp;&nbsp;共${totalPage}页&nbsp;&nbsp;
                        第${pageCur}页&nbsp;&nbsp;
                        <c:url var="url_pre" value="manageNoticeServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur-1}"/>
                            <c:param name="key" value="4_1"/>
                        </c:url>
                        <c:url var="url_next" value="manageNoticeServlet" scope="request">
                            <c:param name="pageCur" value="${pageCur+1}"/>
                            <c:param name="key" value="4_1"/>
                        </c:url>
                            <%--                        如果是第一页，不显示“上一页”--%>
                        <c:if test="${pageCur!=1}">
                            <a href="${url_pre}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                            <%--                        如果是最后一页，不显示“下一页”--%>
                        <c:if test="${pageCur!=totalPage && totalPage!=0}">
                            <a href="${url_next}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </c:if>

    <c:if test="${requestScope.modname =='mod4_1_1'}">
    <div id="mod4_1_1">
        <br><p>公告详情&nbsp;&nbsp;&nbsp;&nbsp;<a href="manageNoticeServlet?key=4_1">返回</a></p>
        <table border="1">
            <form action="alterServlet?noticeID=${noticeDetails.id}" method="post">
                <tr>
                    <th>公告ID</th>
                    <td>${noticeDetails.id}</td>
                </tr>
                <tr>
                    <th>公告标题</th>
                    <td><input type="text" name="ntitle" value="${noticeDetails.ntitle}"style="width: 300px"></td>
                </tr>
                <tr>
                    <th>公告内容</th>
                    <td><textarea name="ncontent" id="" cols="50" rows="20">${noticeDetails.ncontent}</textarea></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <input type="submit" value="提交">&nbsp;&nbsp;
                        <a href="javascript:if(confirm('确定删除此条公告？'))location.replace('deleteServlet?noticeID=${notice.id}&key=4_1&pageCur=${pageCur}')">删除</a>
                    </th>
                </tr>
            </form>
        </table>
    </div>
    </c:if>

    <c:if test="${requestScope.modname =='mod4_1_2'}">
        <div id="mod4_1_2">
            <br><p>添加公告&nbsp;&nbsp;&nbsp;&nbsp;<a href="manageNoticeServlet?key=4_1">返回</a></p>
            <table border="1">
                <form action="addNoticeServlet?addN=f" method="post">
                    <tr>
                        <th>公告标题</th>
                        <td><input type="text" name="ntitle" style="width: 300px"></td>
                    </tr>
                    <tr>
                        <th>公告内容</th>
                        <td><textarea name="ncontent" cols="50" rows="20"></textarea></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <input type="submit" value="提交">
                        </th>
                    </tr>
                </form>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>
