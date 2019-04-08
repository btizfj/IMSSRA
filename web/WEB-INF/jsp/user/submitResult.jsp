<%--
  Created by IntelliJ IDEA.
  User: XiaoMi
  Date: 2019/4/4
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>提交成果</title>
    <link rel="icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/imgs/yznu_logo.jpg" type="image/x-icon"/>
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
            window.editor1 = K.create('#column1', {
                uploadJson : '${pageContext.request.contextPath }/kindeditor/upload',
                fileManagerJson : '${pageContext.request.contextPath }/kindeditor/manager',
                allowFileManager : true,
                urlType:"domain",
                resizeType : 1,width:"100%",height:"400px"
            });
        });
        function type_change() {
            var type_big = $('#type_big');
            var type_small = $("#type_small");
            var select_value = type_big.val();
            switch (select_value) {
                case "-1"://所有大类
                    type_small.empty();
                    type_small.append("<option value=\"-1\">请选择类别</option>");
                    break;
                case "0":
                    type_small.empty();
                    type_small.append("<option value=\"-1\">请选择类别</option>");
                    type_small.append("<option value=\"0\">论文和专著</option>");
                    type_small.append("<option value=\"1\">发明专利</option>");
                    type_small.append("<option value=\"2\">实用新型专利</option>");
                    type_small.append("<option value=\"3\">外观设计专利</option>");
                    type_small.append("<option value=\"8\">其他</option>");
                    break;
                case "1":
                    type_small.empty();
                    type_small.append("<option value=\"-1\">请选择类别</option>");
                    type_small.append("<option value=\"4\">自主研发的新产品原型</option>");
                    type_small.append("<option value=\"5\">自主开发的新技术</option>");
                    type_small.append("<option value=\"8\">其他</option>");
                    break;
                case "2":
                    type_small.empty();
                    type_small.append("<option value=\"-1\">请选择类别</option>");
                    type_small.append("<option value=\"6\">基础软件</option>");
                    type_small.append("<option value=\"7\">应用软件</option>");
                    type_small.append("<option value=\"8\">其他</option>");
                    break;
            }
        }
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
                    // alert("文件上传成功！");
                    // alert(filename);
                    // fn = filename;
                    uploadData(filename);//上传其他数据
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    alert("文件上传失败！");
                    // alert("responseText:"+jqXHR.responseText+"\n"+
                    //     "status:"+jqXHR.status+"\n"+
                    //     "readyState:"+jqXHR.readyState+"\n"+
                    //     "statusText:"+jqXHR.statusText+"\n"+
                    //     "textStatus:"+textStatus+"\n"+
                    //     "errorThrown:"+errorThrown)
                }
            });

        }
        function uploadData(filename) {
            var jsonObj = {};
            editor1.sync();
            var rst_name = $("#rst_name").val();//成果名称
            var type_big = $("#type_big").val();//大类
            var type_small = $("#type_small").val();//小类
            var rst_desc = $("#rst_desc").val();//简介
            var column1 = $("#column1").val();//详情
            var rst_instruction = $("#rst_instruction").val();//说明
            var collegename = $("#collegename").val();//学院
            jsonObj.rst_name = rst_name;
            jsonObj.collegename = collegename;
            jsonObj.type_big = type_big;
            jsonObj.type_small = type_small;
            jsonObj.rst_desc = rst_desc;
            jsonObj.detail = column1;
            jsonObj.filename = filename;
            jsonObj.rst_instruction = rst_instruction;
            var jsonResult = JSON.stringify(jsonObj);
            document.getElementById("msg").value = jsonResult;
        }
        function openModel() {
            $('#myModal').modal('show');
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
                <a class="nav-link" href="${pageContext.request.contextPath}/userMainByType?type=0" onclick="openModel()">我的成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/myMessage" onclick="openModel()">我的消息</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#" style="color: aqua">提交成果</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/userMainByType?type=1" onclick="openModel()">成果审核</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/userMainByType?type=2" onclick="openModel()">成果统计</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/userInfo" onclick="openModel()">个人信息</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout" onclick="openModel()">退出登录</a>
            </li>
        </ul>
    </div>
</nav>
<br>

<div class="container" style="margin-top:80px">
    <form id= "uploadForm" action="${pageContext.request.contextPath}/submitResultParam" method="post">
    <div class="card" style="margin-bottom: 30px">
        <div class="card-body">
            <h3 align="center" style="margin: 20px">填写成果信息</h3>
            <div class="row">
                <div class="col-sm-12 col-md-2">
                    <span>成果名称：</span>
                </div>
                <div class="col-sm-12 col-md-10">
                    <input type="text" class="form-control" id="rst_name" name="rst_name">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-2">
                    <span>成果大类：</span>
                </div>
                <div class="col-sm-12 col-md-10">
                    <select class="form-control" id="type_big" name="type_big" onchange="type_change()">
                        <option value="-1">请选择类别</option>
                        <option value="0">基础理论成果</option>
                        <option value="1">应用技术成果</option>
                        <option value="2">软科学成果</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-2">
                    <span>成果小类：</span>
                </div>
                <div class="col-sm-12 col-md-10">
                    <select class="form-control" id="type_small" name="type_small">
                        <option value="-1">请选择类别</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-2">
                    <span>所属学院：</span>
                </div>
                <div class="col-sm-12 col-md-10">
                    <select class="form-control" id="collegename" name="collegename">
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
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-2">
                    <span>成果简介：</span>
                </div>
                <div class="col-sm-12 col-md-10">
                    <textarea class="form-control" rows="5" id="rst_desc" name="rst_desc"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-2">
                    <span>成果详情：</span>
                </div>
                <div class="col-sm-12 col-md-10">
                    <textarea id="column1" name="column1"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-2">
                    <span>成果文件：</span>
                </div>
                <div class="col-sm-12 col-md-10">
                    <input type="file" id="file" name="file">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-2">
                    <span>成果说明：</span>
                </div>
                <div class="col-sm-12 col-md-10">
                    <textarea class="form-control" rows="5" id="rst_instruction" name="rst_instruction"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col" align="center">
                    <input type="hidden" id="msg" name="msg">
                    <input type="hidden" id="url" name="url" value="${pageContext.request.contextPath}/submitResult">
                    <input type="submit" class="btn btn-info" value="提交成果" onclick="doUpload()">
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
