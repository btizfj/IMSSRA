<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>提交项目成功</title>
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <style>
        body {
            margin: 0;
            padding: 0;
            border-top: 1px solid gray;
        }
    </style>
</head>
<body>
<div align="center" style="display: flex;justify-content: center;align-content: center;margin-top: 200px">
    <img src="${pageContext.request.contextPath}/imgs/ic_success.png" width="70px" height="70px"
         style="margin-right: 30px;vertical-align: middle">
    <span style="color: green;font-size: 30px;margin-top: 13px">操作成功</span>
</div>
<div align="center" style="margin-top: 100px"><a href="${requestScope.url}" target="_self"
                                                 style="text-decoration: none;color: blue;">返回上一页</a></div>
</body>
</html>