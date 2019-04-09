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
    <title>删除成果</title>
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
                case "-1"://所有大类
                    type_small.empty();
                    type_small.append("<option value=\"-1\">所有小类</option>");
                    break;
                case "0":
                    type_small.empty();
                    type_small.append("<option value=\"-1\">所有小类</option>");
                    type_small.append("<option value=\"0\">论文和专著</option>");
                    type_small.append("<option value=\"1\">发明专利</option>");
                    type_small.append("<option value=\"2\">实用新型专利</option>");
                    type_small.append("<option value=\"3\">外观设计专利</option>");
                    type_small.append("<option value=\"8\">其他</option>");
                    break;
                case "1":
                    type_small.empty();
                    type_small.append("<option value=\"-1\">所有小类</option>");
                    type_small.append("<option value=\"4\">自主研发的新产品原型</option>");
                    type_small.append("<option value=\"5\">自主开发的新技术</option>");
                    type_small.append("<option value=\"8\">其他</option>");
                    break;
                case "2":
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

<nav class="navbar navbar-expand-md bg-info navbar-dark fixed-top">
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
                <a class="nav-link" href="#" style="color: aqua">删除成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/accountAssignment" onclick="openModel()">账号分配</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/closeWebsite" onclick="openModel()">运行时段</a>
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

<div class="container" style="margin-top:80px">
    <form action="${pageContext.request.contextPath}/deleteResult">
        <div class="row">
            <!--<div class="col-1" style="line-height: 38px">条件选择</div>-->
            <div class="col-sm-12 col-xl-2 col-md-4">
                <select class="form-control" id="year" name="year">
                    <option value="-1">选择年份</option>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>
                    <option value="2019">2019</option>
                </select>
            </div>
            <div class="col-sm-12 col-xl-2 col-md-4">
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
            <div class="col-sm-12 col-xl-2 col-md-4">
                <select class="form-control" id="trialstate" name="trialstate">
                    <option value="-1">所有状态</option>
                    <option value="0">正在审核</option>
                    <option value="1">审核失败</option>
                    <option value="2">审核通过</option>
                </select>
            </div>
            <div class="col-sm-12 col-xl-2 col-md-4">
                <select class="form-control" id="type_big" name="type_big" onchange="type_change()">
                    <option value="-1">所有大类</option>
                    <option value="0">基础理论成果</option>
                    <option value="1">应用技术成果</option>
                    <option value="2">软科学成果</option>
                </select>
            </div>
            <div class="col-sm-12 col-xl-2 col-md-4">
                <select class="form-control" id="type_small" name="type_small">
                    <option value="-1">所有小类</option>
                </select>
            </div>
            <div class="col-sm-12 col-xl-2 col-md-4" style="line-height: 35px">
                <input type="hidden" id="type" name="type" value="0">
                <button type="submit" class="btn btn-primary btn-sm">立即查询</button>
            </div>
        </div>
    </form>
    <div class="row" style="margin-top: 10px">
        <div class="table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>编号</th>
                <th>年份</th>
                <th>成果名称</th>
                <th>所属大类</th>
                <th>所属小类</th>
                <th>所属学院</th>
                <th>审核状态</th>
                <th>上传日期</th>
                <th>删除成果</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${results}" var="result" varStatus="stat">
                <tr>
                    <td>${stat.index+1}</td>
                    <td>${fn:split(result.time, "-")[0]}</td>
                    <td>${result.resultname}</td>
                    <td>${type_big[result.typebig]}</td>
                    <td>${type_small[result.typesmall]}</td>
                    <td>${colleges[result.collegename]}</td>
                    <td>${trail_state[result.trailstate]}</td>
                    <td>${result.time}</td>
                    <td><a href="${pageContext.request.contextPath}/deleteResultById?id=${result.id}" class="text-primary">删除</a></td>
                </tr>
            </c:forEach>
            <!-- 分页标签 -->
            <tr valign="top">
                <td align="center" colspan="12" rowspan="2">
                    <fkjava:pager
                            pageIndex="${requestScope.pageModel.pageIndex}"
                            pageSize="${requestScope.pageModel.pageSize}"
                            recordCount="${requestScope.pageModel.recordCount}"
                            submitUrl="${pageContext.request.contextPath}/deleteResult?pageIndex={0}"/>
                </td>
            </tr>
            </tbody>
        </table>
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
