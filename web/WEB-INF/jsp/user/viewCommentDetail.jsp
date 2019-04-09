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
    <title>查看审核信息</title>
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
                <h1>查看审核信息</h1>
            </div>
        </div>
    </div>
    <div class="row" style="padding: 20px">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h4 align="center">审核信息</h4>
                    <div class="card" style="margin-bottom: 20px;margin-top: 20px">
                        <div class="card-body">
                            ${comment.comment}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
