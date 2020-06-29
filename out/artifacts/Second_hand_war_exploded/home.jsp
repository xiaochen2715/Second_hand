<%--
  Created by IntelliJ IDEA.
  User: 鹏
  Date: 2020/4/18
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>校园二手</title>
</head>
<link rel="stylesheet" type="text/css" href="css/homecss.css"/>
<body>
<div id="top"></div>
<jsp:include page="headerpage.jsp" />
<div>
    <div>
        <table border="0px">
            <tr>
                <td colspan="5">
                    <div id="div2">
                        <div id="navigation">
                            <ul>
                                <li><a href="home">全部分类</a>
                                    <ul>
                                        <li><a href="searchServlet?hid=gtype&gtype=电脑数码">电脑数码</a></li>
                                        <li><a href="searchServlet?hid=gtype&gtype=运动户外">运动户外</a></li>
                                        <li><a href="searchServlet?hid=gtype&gtype=图书杂志">图书杂志</a></li>
                                        <li><a href="searchServlet?hid=gtype&gtype=服饰鞋包">服饰鞋包</a></li>
                                        <li><a href="searchServlet?hid=gtype&gtype=护肤化妆">护肤化妆</a></li>
                                        <li><a href="searchServlet?hid=gtype&gtype=日用百货">日用百货</a></li>
                                        <li><a href="searchServlet?hid=gtype&gtype=礼品配饰">礼品配饰</a></li>
                                        <li><a href="searchServlet?hid=gtype&gtype=文化娱乐">文化娱乐</a></li>
                                        <li><a href="searchServlet?hid=gtype&gtype=其他">其他</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div id="othershow">
                            <div id="recom">
                                <h2>人气排行</h2>
                                <c:forEach var="pvgoods" items="${sessionScope.allpvGoods}" begin="0" end="11">
                                    <p>
                                        &diams;&nbsp;&nbsp;
                                        <a href="goodsDetailsServlet?goodsID=${pvgoods.id}">${pvgoods.gname}</a>----------
                                        <b style="color: red;font-style: italic;text-align: right;">${pvgoods.gpv}</b>
                                    </p>
                                </c:forEach>
                            </div>
                            <div id="noandde">
                                <div id="notice">
                                    <h2>公告栏</h2>
                                    <c:forEach var="notice" items="${sessionScope.allNotices}" begin="0" end="4">
                                        <p>
                                            &diams;&nbsp;&nbsp;
                                            <a href="javascript:ajaxN(${notice.id})" onclick="showF()" title="点击查看详情">${notice.ntitle}</a>
                                        </p>
                                    </c:forEach>
                                </div>
                                <div id="demand">
                                    <h2>用户需求&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="http://localhost:8888/Second_hand/home" style="font-size: 16px;">换一批</a>
                                    </h2>

                                    <div style="width: 200px;float: left;">
                                        <c:forEach var="demand" items="${sessionScope.allDemands}" begin="0" end="4">
                                            <p>
                                                &diams;&nbsp;&nbsp;
                                                <a href="javascript:ajaxD(${demand.id})" onclick="showF()" title="点击查看详情">${demand.dtitle}</a>
                                            </p>
                                        </c:forEach>
                                    </div>
                                    <div style="width: 200px;float: left;">
                                        <c:forEach var="demand" items="${sessionScope.allDemands}" begin="5" end="9">
                                            <p>
                                                &diams;&nbsp;&nbsp;
                                                <a href="javascript:ajaxD(${demand.id})" onclick="showF()" title="点击查看详情">${demand.dtitle}</a>
                                            </p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <c:forEach varStatus="status" begin="0" end="${allGoodsSize-1}" step="5">
                <tr>
                    <c:forEach items="${allGoods}" var="goods" begin="${status.index}" end="${status.index+4}">
                        <td>
<%--                            获取该商品详情的地址--%>
                            <a href="goodsDetailsServlet?goodsID=${goods.id}">
                                <div id="goodscase">
                                    <div><img src="uploadFiles/${goods.gpicture}" alt="校园二手宝贝图"></div>
                                    <p id="goodscase_p1"><b>${goods.gname}(${goods.gdegree})</b></p>
                                    <p id="goodscase_p2">出手价：<b style="color: red;font-size: 21px">${goods.gprice}</b>元</p>
                                </div>
                            </a>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
<div id="aa">
    <a href="#top"><img src="imgs/fanhuidingbu.png" alt="返回顶部" title="返回顶部"></a>
</div>
<%--悬浮窗--%>
<div id="hiddiv"></div>
<div id="floating" class="floating">
    <div id="floating_close" class="floating_close"><a href="javascript:closeF()" title="关闭">X</a></div>
    <div id="floating_main" class="floating_main">
        <h2 align="center" class="fmw" id="ftitle"></h2>
        <p align="right" class="fmw" id="ftime"></p>
        <p align="left" class="fmw" id="fcontentway" style="font-weight: bold"></p>
        <p align="left" class="fmw" id="fcontent"></p>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function showF(){
        // ajaxN(nid);
        document.getElementById("hiddiv").style.display = "inline";
        document.body.style.overflow = "hidden";
        document.getElementById("floating").style.display = "inline";
    }
    function closeF(){
        document.getElementById("floating").style.display = "none";
        document.getElementById("hiddiv").style.display = "none";
        document.body.style.overflow = "auto";
        //清空标签内容
        var resData = xhr.responseText.split("#*#");
        document.getElementById("ftitle").innerText = "";
        document.getElementById("ftime").innerText = "";
        document.getElementById("fcontentway").innerText = "";
        document.getElementById("fcontent").innerText = "";
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
    function ajaxN(nid) {
        //创建XMLHttpRequest对象
        xhr = createXHR();
        //设定请求地址
        var url = "noticeDetailsServlet?noticeID=" + nid;
        //建立对服务器的调用
        xhr.open("GET",url,true);
        //指定响应事件处理函数
        xhr.onreadystatechange = function () {
            //当readyState = 4且状态为200时，表示响应已就绪
            if (xhr.readyState == 4 && xhr.status == 200) {
                //对响应结果进行处理
                var resData = xhr.responseText.split("#*#");
                //将响应数据更新到页面控件中显示
                document.getElementById("ftitle").innerText = resData[0];
                document.getElementById("ftime").innerText = resData[1];
                document.getElementById("fcontent").innerText = resData[2];
            }
        };
        //向服务器发出请求
        xhr.send();
    }
    function ajaxD(did) {
        xhr = createXHR();
        var url = "demandDetailsServlet?demandID=" + did;
        xhr.open("GET",url,true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var resData = xhr.responseText.split("#*#");
                document.getElementById("ftitle").innerText = resData[0];
                document.getElementById("ftime").innerText = resData[1];
                document.getElementById("fcontentway").innerText = "联系方式：" + resData[2];
                document.getElementById("fcontent").innerText = resData[3];
            }
        };
        xhr.send();
    }
</script>