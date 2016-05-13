<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">      
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <meta http-equiv="Content-Type" content="text/html charset=UTF-8">
        <link href="static/bootstrap.min.css" rel="stylesheet">
        <link href="static/Font-Awesome-3.2.1/css/font-awesome.min.css" rel="stylesheet">
        <link href="static/css/main.css" rel="stylesheet">
        <link href="static/css/jquery.jqtimeline.css" rel="stylesheet">
        <script src="static/jquery.min.js"></script>
        <script src="static/bootstrap.min.js"></script>
        <script src="static/js/jquery.jqtimeline.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            $("#loginButton").click(function() {
                $.ajax({
                    url: "login.action",
                    data: {
                        username: $("#loginUsr").val(),
                        password: $("#loginPwd").val(),
                    },
                    dataType: "json",
                    success: function(data) {
                        data = data[0];
                        if(data.msg == "fail") {
                            $("loginError").fadeIn();
                            return;
                        }
                        var idx = data.msg;
                        $("#loginSuccess").fadeIn();
                        if(idx == "jianzhang") {
                            window.setTimeout("window.location='appr.jsp'", 100);
                        } else if(idx == "fujianzhang") {
                            window.setTimeout("window.location='esta-vice.jsp'", 100);
                        } else if(idx == "admin") {
                            window.setTimeout("window.location='taskDA.jsp'", 100);
                        } else if(idx == "bumenzhang") {
                            window.setTimeout("window.location='esta-drt.jsp'", 100);
                        } else if(idx == "executor") {
                            window.setTimeout("window.location='exec.jsp'", 100);
                        }
                    },
                });
            });
        });
        </script>
    </head>
    <body background="static/bg.jpg">
        <br /><br /><br /><br />
        <div class="container">
	    <div class="row clearfix">
		<h1 class="text-center"style="margin-left: -40"><font face="Microsoft Yahei" color="#FF0000">全舰任务综合管理</font></h1>
	    </div>
	    <br /><br /><br /><br />
            <div class="row clearfix">
                <div class="col-md-4 column">
                </div>
                <div class="col-md-4 column">
                    <br /><br />
                    <h2><font face="Microsoft Yahei">系统登录</font></h2><br />
                    <form id="loginForm" class="form-horizontal" role="form" action="{% url 'online:login' %}" method="post">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input id="loginUsr" type="text" class="form-control" placeholder="用户名"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input id="loginPwd" type="password" class="form-control" placeholder="口令"/>
                            </div>
                        </div>
                        <div id="loginError" class="alert alert-warning" style="display:none;">
                            <a  class="close" data-dismiss="alert">
                                &times;
                            </a>
                            <strong>用户名或密码不正确</strong>
                        </div>
                        <div id="loginSuccess" class="alert alert-success" style="display:none;">
                            <a  class="close" data-dismiss="alert">
                                &times;
                            </a>
                            <strong>登陆成功</strong>
                        </div>
			<br />
                        <div class="form-group">
                            <div class="col-sm-10">
                                <button id="loginButton" type="button" class="btn btn-success">登陆</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-4 column">
                </div>
            </div>
        </div>

    </body>
</html>
