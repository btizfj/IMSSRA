<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fkjava" uri="/pager-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>成果展示</title>
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
    <div class="row" style="background: #15757A">
        <div class="col">
            <div style="padding: 20px;color: white" align="center">
                <h1>成果展示</h1>
            </div>
        </div>
    </div>
    <div class="row" style="padding: 20px">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h4 align="center">所有优秀成果</h4>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>成果名称</th>
                            <th>所属大类</th>
                            <th>所属小类</th>
                            <th>所属学院</th>
                            <th>审核状态</th>
                            <th>上传日期</th>
                            <th>成果详情</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${results}" var="result" varStatus="stat">
                            <tr>
                                <td>${stat.index+1}</td>
                                <td>${result.resultname}</td>
                                <td>${type_big[result.typebig]}</td>
                                <td>${type_small[result.typesmall]}</td>
                                <td>${colleges[result.collegename]}</td>
                                <td>${trail_state[result.trailstate]}</td>
                                <td>${result.time}</td>
                                <td><a href="${pageContext.request.contextPath}/viewResultDetail?result_id=${result.id}" target="_blank" class="text-primary">查看</a></td>
                            </tr>
                        </c:forEach>
                        <!-- 分页标签 -->
                        <tr valign="top">
                            <td align="center" colspan="12" rowspan="2">
                                <fkjava:pager
                                        pageIndex="${requestScope.pageModel.pageIndex}"
                                        pageSize="${requestScope.pageModel.pageSize}"
                                        recordCount="${requestScope.pageModel.recordCount}"
                                        submitUrl="${pageContext.request.contextPath}/viewAllGoodResultByPage"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
