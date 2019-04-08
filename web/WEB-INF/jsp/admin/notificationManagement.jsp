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
    <title>优秀成果</title>
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <style type="text/css">
        .row{
            margin: 8px;
        }
    </style>
    <script language="JavaScript">
        KindEditor.ready(function(K) {
            window.editor1 = K.create('#n_content', {
                uploadJson : '${pageContext.request.contextPath }/kindeditor/upload',
                fileManagerJson : '${pageContext.request.contextPath }/kindeditor/manager',
                allowFileManager : true,
                urlType:"domain",
                resizeType : 1,width:"100%",height:"400px"
            });
        });
        function doUpload() {
            var formData = new FormData($( "#uploadForm" )[0]);
            // var fn;//返回的文件名
            $.ajax({
                url: '${pageContext.request.contextPath }/uploadFile' ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (filename) {
                    uploadData(filename);//上传其他数据
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    alert("文件上传失败！");
                }
            });

        }
        function uploadData(filename) {
            var jsonObj = {};
            editor1.sync();
            var n_name = $("#n_name").val();
            var n_type = $("#n_type").val();
            var n_content = $("#n_content").val();//小类
            jsonObj.n_name = n_name;
            jsonObj.n_type = n_type;
            jsonObj.n_content = n_content;
            jsonObj.filename = filename;
            var jsonResult = JSON.stringify(jsonObj);
            document.getElementById("msg").value = jsonResult;
        }
        function openModel() {
            $('#myModal').modal('show');
        }
        function toVaild(){
            var n_name = $("#n_name").val();
            var n_content = $("#n_content").val();
            var n_type = $("#n_type").val();
            if(n_type == -1){
                alert("请选择类型！");
                return false;
            }else if (n_name == ""){
                alert("请填写标题！");
                return false;
            } else if (n_content == ""){
                alert("请填写内容！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<nav class="navbar navbar-expand-md bg-success navbar-dark fixed-top">
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
                <a class="nav-link" href="${pageContext.request.contextPath}/resultTrial" onclick="openModel()">成果审核</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/adminMain">优秀成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/deleteResult" onclick="openModel()">删除成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/accountAssignment" onclick="openModel()">账号分配</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/closeWebsite" onclick="openModel()">运行时段</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/notificationManagement" onclick="openModel()" style="color: aqua">通知管理</a>
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
    <form id= "uploadForm" action="${pageContext.request.contextPath}/submitNotification" method="post" onsubmit="return toVaild()">
        <div class="card" style="margin-bottom: 30px">
            <div class="card-body">
                <h3 align="center" style="margin: 20px">填写通知信息</h3>
                <div class="row">
                    <div class="col-sm-12 col-md-2">
                        <span>通知名称：</span>
                    </div>
                    <div class="col-sm-12 col-md-10">
                        <input type="text" class="form-control" id="n_name" name="n_name">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-2">
                        <span>通知类型：</span>
                    </div>
                    <div class="col-sm-12 col-md-10">
                        <select class="form-control" id="n_type" name="n_type">
                            <option value="-1">请选择类别</option>
                            <option value="0">通知公告</option>
                            <option value="1">信息公开</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-2">
                        <span>通知内容：</span>
                    </div>
                    <div class="col-sm-12 col-md-10">
                        <textarea id="n_content" name="n_content"></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-2">
                        <span>通知文件：</span>
                    </div>
                    <div class="col-sm-12 col-md-10">
                        <input type="file" id="file" name="file">
                    </div>
                </div>
                <div class="row">
                    <div class="col" align="center">
                        <input type="hidden" id="msg" name="msg">
                        <input type="hidden" id="url" name="url" value="${pageContext.request.contextPath}/notificationManagement">
                        <input type="submit" class="btn btn-info" value="发布通知" onclick="doUpload()">
                    </div>
                </div>
            </div>
        </div>
    </form>
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
