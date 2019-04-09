<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: XiaoMi
  Date: 2019/3/17
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>科研成果管理系统</title>
  <meta charset="utf-8">
  <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
  <script src="${pageContext.request.contextPath}/js/login.js"></script>
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
  <script language="JavaScript">
    $(document).ready(function(){
      $("#_hint").hide()
      $("#_loading").hide()
      $("#_username").change(function(){
        $("#_hint").hide()
        $("#_loading").hide()
      });
      $("#_password").change(function(){
        $("#_hint").hide()
        $("#_loading").hide()
      });
      $("#_usertype").change(function(){
        $("#_hint").hide()
        $("#_loading").hide()
      });
    });
    function onClickResultSearch() {
      window.open("${pageContext.request.contextPath}/resultSearch");
    }
    function onClickNotification(t) {
      window.open("${pageContext.request.contextPath}/viewAllNotificationByPage?n_type="+t);
    }
    function onClickShowGoodResult() {
      window.open("${pageContext.request.contextPath}/viewAllGoodResultByPage");
    }
  </script>
  <style type="text/css">
    #grad {
      background: -webkit-linear-gradient(left, #155997 , #15925E); /* Safari 5.1 - 6.0 */
      background: -o-linear-gradient(right, #155997, #15925E); /* Opera 11.1 - 12.0 */
      background: -moz-linear-gradient(right, #155997, #15925E); /* Firefox 3.6 - 15 */
      background: linear-gradient(to right, #155997 , #15925E); /* 标准的语法（必须放在最后） */
    }
    .my_btn{
      width: 100%;
      height: 50px;
      background: transparent;
      border: 1px solid #558FAA;
      border-radius: 3px;
      color: #BFD5DF;
    }
    .my_btn:hover{
      background: #40839F;
    }
  </style>
</head>
<body>

<div class="container-fluid">

  <div class="row" align="center" id="grad" style="height: 470px">
    <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
      <h1 style="color: white;margin-top: 40px" align="center">科研成果管理系统</h1>
    </div>
    <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
      <h5 style="color: #B8D4D8;" align="center">为科学研究而生</h5>
    </div>
    <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
      <button type="button" class="my_btn" data-toggle="modal" data-target="#myModal">登录系统</button>
    </div>
    <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
      <button type="button" class="my_btn" onclick="onClickResultSearch()">成果查询</button>
    </div>
    <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
      <button type="button" class="my_btn" onclick="onClickNotification(0)">通知公告</button>
    </div>
    <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
      <button type="button" class="my_btn" onclick="onClickShowGoodResult()">成果展示</button>
    </div>
    <div class="col-sm-12 col-md-12 col-lg-3 col-xl-3">
      <button type="button" class="my_btn" onclick="onClickNotification(1)">信息公开</button>
    </div>
    <!--</div>-->
  </div>
  <div class="row">
    <div class="col">
      <div class="container">
        <div class="row">
          <div class="col">
            <h1 style="color: #159957;margin-top: 30px">通知公告</h1>
            <hr>
            <h5 style="color: gray;margin-top: 20px;margin-bottom: 20px">通知公告公布网站的通知公告</h5>
            <c:forEach items="${notes}" var="note" varStatus="status">
              <a href="${pageContext.request.contextPath}/viewNotificationDetail?type=0&id=${note.id}" target="_blank" class="text-muted">${status.index+1}.${note.title}</a><br>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/viewAllNotificationByPage?n_type=0" target="_blank" class="text-primary" style="color: deepskyblue">更多通知</a><br>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h1 style="color: #159957;margin-top: 30px">成果展示</h1>
            <hr>
            <h5 style="color: gray;margin-top: 20px;margin-bottom: 20px">展示网站上的优秀项目</h5>
            <c:forEach items="${goodResult}" var="result" varStatus="status">
              <a href="${pageContext.request.contextPath}/viewResultDetail?result_id=${result.id}" target="_blank" class="text-muted">${status.index+1}.${result.resultname}</a><br>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/viewAllGoodResultByPage" target="_blank" class="text-primary" style="color: deepskyblue">更多展示</a><br>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h1 style="color: #159957;margin-top: 30px">信息公开</h1>
            <hr>
            <h5 style="color: gray;margin-top: 20px;margin-bottom: 20px">发布网站的公开信息</h5>
            <c:forEach items="${infos}" var="info" varStatus="status">
              <a href="${pageContext.request.contextPath}/viewNotificationDetail?type=1&id=${info.id}" target="_blank" class="text-muted">${status.index+1}.${info.title}</a><br>
            </c:forEach>
            <a href="${pageContext.request.contextPath}/viewAllNotificationByPage?n_type=1" target="_blank" class="text-primary" style="color: deepskyblue">更多信息</a><br>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h1 style="color: #159957;margin-top: 30px">致谢</h1>
            <hr>
            <h6 style="color: gray;margin-top: 20px;margin-bottom: 20px">感谢腾讯云服务器提供技术支持。</h6>
            <h6 style="color: gray;margin-top: 20px;margin-bottom: 20px">感谢GitHub提供技术支持。</h6>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h1 style="color: #159957;margin-top: 30px">联系作者</h1>
            <hr>
            <h6 style="color: gray;margin-top: 20px;margin-bottom: 20px">邮箱：1490785540@qq.com</h6>
            <h6 style="color: gray;margin-top: 20px;margin-bottom: 20px">电话：18580451667</h6>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <h1 style="color: #159957;margin-top: 30px">版权所有</h1>
            <hr>
            <h6 style="color: gray;margin-top: 20px;margin-bottom: 20px">此项目为本人的本科毕业设计，未经本人允许，不得参与以营利为目的活动。</h6>
            <h6 style="color: gray;margin-top: 20px;margin-bottom: 20px">项目已提交至GitHub：<a class="text-primary" href="https://github.com/BTIZFJ/IMSSRA">https://github.com/BTIZFJ/IMSSRA</a></h6>
          </div>
        </div>
      </div>
    </div>

  </div>
  <!-- 模态框 -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <!-- 模态框主体 -->
        <div class="modal-body" align="center">
          <h3 align="center">登录</h3>
          <input type="text" class="form-control" placeholder="输入账号" id="_username" name="_username">
          <br>
          <input type="password" class="form-control" placeholder="输入密码" id="_password" name="_password">
          <br>
          <select class="form-control" id="_usertype" name="_usertype">
            <option value="-1">请选择角色</option>
            <option value="0">学生</option>
            <option value="1">老师</option>
            <option value="2">管理员</option>
          </select>
          <br>
          <div id="_hint" class="login_hint">账号密码错误！</div>
          <div id="_loading" class="login_loading">正在登陆...</div>
          <br>
          <input onclick="doAjax()" type="button" class="btn btn-primary" value="登 录">
        </div>
      </div>
    </div>
  </div>
</div>


</body>
</html>