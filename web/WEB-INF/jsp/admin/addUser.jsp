<%--
  Created by IntelliJ IDEA.
  User: XiaoMi
  Date: 2019/4/6
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加角色</title>
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script language="JavaScript">
        function checkInfo(){
            var username = $("#username").val();//大类
            var password = $("#password").val();//小类
            var collegename = $("#collegename").val();//学院
            var role = $("#role").val();//角色
            if(username == ""){
                alert("请填写用户名！");
                return false;
            } else if (password == "") {
                alert("请填写密码！");
                return false;
            }else if (collegename == -1) {
                alert("请选择学院！");
                return false;
            } else if (role == -1) {
                alert("请选择角色！");
                return false;
            }
            return true;
        }

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="background: green">
        <div class="col">
            <div style="padding: 20px;color: white" align="center">
                <h1>添加角色</h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/addUserToDB" onsubmit="return checkInfo()">
        <div class="card" style="margin-bottom: 30px">
            <div class="card-body">
                <div class="row" align="center">
                    <div class="col">
                        <h3>填写信息</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="username">用户名:</label>
                            <input type="text" class="form-control" id="username" name="username">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="password">密码:</label>
                            <input type="text" class="form-control" id="password" name="password">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="collegename">所属学院:</label>
                            <select class="form-control" id="collegename" name="collegename">
                                <option value="-1">选择学院</option>
                                <option value="0">文学院</option>
                                <option value="1">传媒学院</option>
                                <option value="2">计算机学院</option>
                                <option value="3">财经学院</option>
                                <option value="4">管理学院</option>
                                <option value="5">美术学院</option>
                                <option value="6">体育学院</option>
                                <option value="7">化工化学学院</option>
                                <option value="8">外国语学院</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="role">角色类型:</label>
                            <select class="form-control" id="role" name="role">
                                <option value="-1">选择类型</option>
                                <option value="0">学生/教师</option>
                                <option value="1">管理人员</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col" align="center">
                        <input type="hidden" id="url" name="url" value="${pageContext.request.contextPath}/addUser">
                        <input type="submit" class="btn btn-info" value="确定添加">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
