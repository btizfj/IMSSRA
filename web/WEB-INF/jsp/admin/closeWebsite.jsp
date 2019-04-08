<%--
  Created by IntelliJ IDEA.
  User: XiaoMi
  Date: 2019/4/3
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fkjava" uri="/pager-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>关闭网站</title>
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <style type="text/css">
        td{
            text-align: center;
        }
        thead{
            text-align: center;
        }
    </style>
    <script language="JavaScript">
        function type_change() {
            var type_big = $('#type_big');
            var type_small = $("#type_small");
            var select_value = type_big.val();
            switch (select_value) {
                case "0"://所有大类
                    type_small.empty();
                    type_small.append("<option value=\"-1\">所有小类</option>");
                    break;
                case "1":
                    type_small.empty();
                    type_small.append("<option value=\"-1\">所有小类</option>");
                    type_small.append("<option value=\"0\">论文和专著</option>");
                    type_small.append("<option value=\"1\">发明专利</option>");
                    type_small.append("<option value=\"2\">实用新型专利</option>");
                    type_small.append("<option value=\"3\">外观设计专利</option>");
                    type_small.append("<option value=\"8\">其他</option>");
                    break;
                case "2":
                    type_small.empty();
                    type_small.append("<option value=\"-1\">所有小类</option>");
                    type_small.append("<option value=\"4\">自主研发的新产品原型</option>");
                    type_small.append("<option value=\"5\">自主开发的新技术</option>");
                    type_small.append("<option value=\"8\">其他</option>");
                    break;
                case "3":
                    type_small.empty();
                    type_small.append("<option value=\"-1\">所有小类</option>");
                    type_small.append("<option value=\"6\">基础软件</option>");
                    type_small.append("<option value=\"7\">应用软件</option>");
                    type_small.append("<option value=\"8\">其他</option>");
                    break;
            }
        }
        function openModel() {
            $('#myModal').modal('show');
        }
    </script>
</head>
<body>

<nav class="navbar navbar-expand-md bg-success navbar-dark fixed-top">
    <a class="navbar-brand" href="#">
        <img src="http://static.runoob.com/images/mix/bird.jpg" alt="logo" style="width:40px;border-radius: 50%">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/adminMain" onclick="openModel()">成果查看</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/myMessage" onclick="openModel()">我的消息</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/resultTrial" onclick="openModel()">成果审核</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/setGoodResult" onclick="openModel()">优秀成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/deleteResult" onclick="openModel()">删除成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/accountAssignment" onclick="openModel()">账号分配</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" style="color: aqua">运行时段</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/deliverManagement" onclick="openModel()">发布通知</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/notificationManagement" onclick="openModel()">管理通知</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/exportInfo" onclick="openModel()">信息导出</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout" onclick="openModel()">退出登录</a>
            </li>
        </ul>
    </div>
</nav>
<br>

<div class="container" style="margin-top:150px">
    <div class="card" align="center">
        <div class="card-body">
            <div class="row">
                <div class="col">
                    <span style="font-size: 20px"><span style="color: red">网站运行状态：</span>
                        <c:choose>
                            <c:when test="${webSatet.isopen == 1}">
                                <span style="color: green">运行中</span>
                            </c:when>
                            <c:when test="${webSatet.isopen == 0}">
                                <span style="color: red">已关闭</span>
                            </c:when>
                        </c:choose>
                    </span>
                </div>
            </div>
            <div class="row" style="margin-top: 20px;margin-bottom: 20px">
                <div class="col">
                    <c:choose>
                        <c:when test="${webSatet.isopen == 1}">
                            <a href="${pageContext.request.contextPath}/modifyWebsiteState?state=0&url=${pageContext.request.contextPath}/closeWebsite" class="btn btn-danger btn-lg">关闭网站</a>
                        </c:when>
                        <c:when test="${webSatet.isopen == 0}">
                            <a href="${pageContext.request.contextPath}/modifyWebsiteState?state=1&url=${pageContext.request.contextPath}/closeWebsite" class="btn btn-primary btn-lg">打开网站</a>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <span style="font-size: 20px"><span style="color: red">注意：</span><span>关闭网站后其他非管理员用户不可访问该网站！</span></span>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框 -->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog modal-lg" style="margin-top: 270px" align="center">
            <!--正在加载...-->
            <div class="mx-auto">
                <img width="30px" height="30px" src="${pageContext.request.contextPath}/imgs/pic_loading.gif">
            </div>
        </div>
    </div>
</div>
</body>
</html>
