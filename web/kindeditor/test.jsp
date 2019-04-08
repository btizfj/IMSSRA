<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {
            window.editor1 = K.create('#editor_id1', {
                width: '700',
                height: '300',
                uploadJson : '${pageContext.request.contextPath }/kindeditor/upload',
                fileManagerJson : '${pageContext.request.contextPath }/kindeditor/manager',
                allowFileManager : true,
                urlType:"domain"
            });
            // window.editor2 = K.create('#editor_id2');
        });
        function getText() {
            editor1.sync();
            // 取得HTML内容
            var html1 = document.getElementById('editor_id1').value; // 原生API
            // var html2 = editor2.html();
            alert(html1)
            // alert(html2)
        }
    </script>
</head>
<body>
<p>1</p>
<textarea id="editor_id1" name="content">
</textarea>
<%--<img src=\"http://localhost:8080/attached/image/20180614/20180614192817_312.jpg\" alt=\\ />--%>
<%--<p>2</p>--%>
<%--<textarea id="editor_id2" name="content" style="width:700px;height:300px;">--%>
<%--</textarea>--%>
<input type="button" onclick="getText()">
</body>
</html>