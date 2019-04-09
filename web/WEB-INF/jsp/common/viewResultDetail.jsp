<%--
  Created by IntelliJ IDEA.
  User: XiaoMi
  Date: 2019/4/6
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>成果详情</title>
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
                <h1>成果详情</h1>
            </div>
        </div>
    </div>
    <div class="row" style="padding: 20px">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>成果编号：${result.id}</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>成果名称：${result.resultname}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>成果大类：${type_big[result.typebig]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>成果小类：${type_small[result.typesmall]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>所属学院：${colleges[result.collegename]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>成果简介：${result.description}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>成果详情：</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="2">${result.detail}</td>
                        </tr>
                        <tr>
                            <td>成果说明：${result.instruction}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>审核状态：${trail_state[result.trailstate]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>下载成果：<a href="${pageContext.request.contextPath}/downloadFile?type=1&rst_id=${result.id}" class="text-primary">导出</a></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>成果作者：${user.userName}</td>
                            <td></td>
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
