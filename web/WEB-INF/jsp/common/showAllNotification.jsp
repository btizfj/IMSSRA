<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fkjava" uri="/pager-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: XiaoMi
  Date: 2019/4/7
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <c:choose>
        <c:when test="${n_type == 0}">
            <title>通知公告</title>
        </c:when>
        <c:when test="${n_type == 1}">
            <title>信息公开</title>
        </c:when>
    </c:choose>
    <meta charset="utf-8">
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row" style="background: #17A2B8">
        <div class="col">
            <div style="padding: 20px;color: white" align="center">
                <c:choose>
                    <c:when test="${n_type == 0}">
                        <h1>通知公告</h1>
                    </c:when>
                    <c:when test="${n_type == 1}">
                        <h1>信息公开</h1>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="row" style="padding: 20px">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <c:choose>
                        <c:when test="${n_type == 0}">
                            <h4 align="center">所有通知</h4>
                        </c:when>
                        <c:when test="${n_type == 1}">
                            <h4 align="center">所有信息</h4>
                        </c:when>
                    </c:choose>
                    <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>类型</th>
                            <th>标题</th>
                            <th>附件</th>
                            <th>时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${notifications}" var="notification" varStatus="stat">
                            <tr>
                                <td>${stat.index+1}</td>
                                <td>${resultType[notification.type]}</td>
                                <td><a href="${pageContext.request.contextPath}/viewNotificationDetail?type=${n_type}&id=${notification.id}" target="_blank" class="text-primary">${notification.title}</a></td>
                                <td>${notification.filename}</td>
                                <td>${notification.time}</td>
                                <%--<td><a href="${pageContext.request.contextPath}/showComment?result_id=${result.id}" target="_blank" class="text-primary">查看</a></td>--%>
                            </tr>
                        </c:forEach>
                        <!-- 分页标签 -->
                        <tr valign="top">
                            <td align="center" colspan="12" rowspan="2">
                                <fkjava:pager
                                        pageIndex="${requestScope.pageModel.pageIndex}"
                                        pageSize="${requestScope.pageModel.pageSize}"
                                        recordCount="${requestScope.pageModel.recordCount}"
                                        submitUrl="${pageContext.request.contextPath}/viewAllNotificationByPage?n_type=${n_type}&pageIndex={0}"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
