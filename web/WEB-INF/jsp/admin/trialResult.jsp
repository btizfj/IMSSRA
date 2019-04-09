<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>成果审核</title>
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
                <h1>成果审核</h1>
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
                        <tr>
                            <td align="center" colspan="2">
                                <form action="${pageContext.request.contextPath}/submitResultComment">
                                <div class="card">
                                    <div class="card-body">
                                        <h3 align="center">审核区</h3>
                                        <select class="form-control" id="result_trialstate" name="result_trialstate">
                                            <option value="-1">选择审核</option>
                                            <option value="1">驳回审核</option>
                                            <option value="2">通过审核</option>
                                        </select>
                                        <br>
                                        <textarea class="form-control" rows="5" id="result_comment" name="result_comment" placeholder="输入评语"></textarea>
                                        <br>
                                        <input type="hidden" id="result_id" name="result_id" value="${result.id}">
                                        <input type="hidden" id="url" name="url" value="${pageContext.request.contextPath}/trialResult?result_id=${result.id}">
                                        <input type="submit" class="btn btn-info" value="确认提交">
                                    </div>
                                </div>
                                </form>
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
