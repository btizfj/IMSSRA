<%--
  Created by IntelliJ IDEA.
  User: XiaoMi
  Date: 2019/4/3
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script language="JavaScript">
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
                <a class="nav-link" href="${pageContext.request.contextPath}/userMainByType?type=0" onclick="openModel()">我的成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/submitResult" onclick="openModel()">提交成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/userMainByType?type=1" onclick="openModel()">成果审核</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/userMainByType?type=2" onclick="openModel()">成果统计</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" style="color: aqua">个人信息</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout" onclick="openModel()">退出登录</a>
            </li>
        </ul>
    </div>
</nav>
<br>

<div class="container" style="margin-top:80px">
    <form action="${pageContext.request.contextPath}/modifyPersonalInfo" method="post">
    <div class="card" style="margin-bottom: 30px">
        <div class="card-body">
            <div class="row" align="center">
                <div class="col">
                    <h3>${user.userName}个人信息</h3>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="username">用户名:</label>
                        <input type="text" class="form-control" id="username" name="username" value="${user.userName}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="realname">真实姓名:</label>
                        <input type="text" class="form-control" id="realname" name="realname" value="${user.realName}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="sex">性别:</label>
                        <input type="text" class="form-control" id="sex" name="sex" value="${user.sex}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="phonenumber">电话:</label>
                        <input type="number" class="form-control" id="phonenumber" name="phonenumber" value="${user.phoneNumber}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="email">邮箱:</label>
                        <input type="email" class="form-control" id="email" name="email" value="${user.email}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col" align="center">
                    <input type="submit" class="btn btn-info" value="提交按钮">
                </div>
            </div>
        </div>
    </div>
        <input type="hidden" id="url" name="url" value="${pageContext.request.contextPath}/userInfo">
    </form>
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