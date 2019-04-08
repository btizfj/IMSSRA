<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>成果查询</title>
    <meta charset="utf-8">
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script language="JavaScript">
        function toVaild(){
            var rst_number = $("#rst_number").val();
            var rst_name = $("#rst_name").val();
            if(rst_name == ""){
                alert("请输入关键字！");
                return false;
            }
            if(rst_number == -1){
                alert("请选择类型！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div class="container-fluid">
    <div class="row" style="background: #15757A">
        <div class="col">
            <div style="padding: 20px;color: white" align="center">
                <h1>成果查询</h1>
            </div>
        </div>
    </div>
    <div class="row" style="padding: 20px">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h4 align="center">${notification.title}</h4>
                    <div class="card" style="margin-bottom: 20px;margin-top: 20px">
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/resultSearch" onsubmit="return toVaild()">
                            <h3 align="center">填写信息</h3>
                            <input type="text" class="form-control" id="rst_name" name="rst_name" placeholder="输入成果编号或者名称来查询" style="margin-top: 20px;margin-bottom: 20px">
                            <select class="form-control" id="rst_number" name="rst_number" style="margin-bottom: 20px">
                                <option value="-1">请选择类别</option>
                                <option value="0">按编号查询</option>
                                <option value="1">按名称查询</option>
                            </select>
                            <div align="center"><input type="submit" class="btn btn-info" value="提交成果"></div>
                            </form>
                        </div>
                    </div>
                    <div><span style="color: #AA2428">查询结果：</span></div>
                    <c:choose>
                        <c:when test="${results == null}">
                            <div><span>无结果！</span></div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${results}" var="result" varStatus="status">
                                <a href="${pageContext.request.contextPath}/viewResultDetail?result_id=${result.id}" class="text-muted">${result.resultname}</a><br>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
