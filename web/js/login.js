function checkLoginInfo() {
    var _username = $("#_username").val()
    var _password = $("#_password").val()
    var _usertype = $("#_usertype").val()
    if(_username == ""){
        alert("请填写账号！")
        $("#_loading").hide()
        return false
    }
    if(_password == ""){
        alert("请填写密码！")
        $("#_loading").hide()
        return false
    }
    if(_usertype == -1){
        alert("请选择角色！")
        $("#_loading").hide()
        return false
    }
    return true;
}
//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}
function doAjax() {
    $("#_loading").show()
    if(checkLoginInfo() == false){
        return
    }
    $.ajax({
        type:"POST",
        // url:"http://localhost:8080/IMSSRA/usernameAndPasswordCheck",
        // url:"http://118.24.129.95:8080/IMSSRA/usernameAndPasswordCheck",
        url:getRootPath()+"/usernameAndPasswordCheck",
        data:{"username":$("#_username").val(),"password":$("#_password").val(),"usertype":$("#_usertype").val()},
        success:function(msg)
        {
            if(msg=="failed")//账号或者密码错误！
            {
                $("#_hint").show()
                $("#_loading").hide()
            }else {//登陆成功
                // window.self.location = "http://localhost:8080/IMSSRA/"+msg+"/?type=0"
                window.self.location = getRootPath()+"/"+msg+"/?type=0"
            }
        },
        error:function (jqXHR, textStatus, errorThrown) {
            alert("responseText:"+jqXHR.responseText+"\n"+
                "status:"+jqXHR.status+"\n"+
                "readyState:"+jqXHR.readyState+"\n"+
                "statusText:"+jqXHR.statusText+"\n"+
                "textStatus:"+textStatus+"\n"+
                "errorThrown:"+errorThrown)
        }
    });
}

