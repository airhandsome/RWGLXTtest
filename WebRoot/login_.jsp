<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />

        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

        <!-- text fonts -->
        <link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="assets/css/ace.min.css" />

        <!--[if lte IE 9]>
            <link rel="stylesheet" href="assets/css/ace-part2.min.css" />
        <![endif]-->
        <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

        <!--[if lte IE 9]>
          <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
        <![endif]-->

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->
    </head>

    <body class="login-layout" background="assets/img/bg.jpg">
        <div class="main-container">
            <div class="main-content">
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <div class="login-container">
                            <div class="space-24"></div>
                            <div class="space-24"></div>
                            <div class="space-12"></div>
                            <div class="center">
                                <h1>
                                    <i class="ace-icon fa fa-ship red"></i>
                                    <span class="red"><font face="Microsoft Yahei" color="#FF0000">全舰任务综合管理</font></span>
                                </h1>
                            </div>
                            <div class="space-24"></div>


                            <div class="position-relative">
                                <div id="login-box" class="login-box visible widget-box no-border">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <h4 class="header blue lighter bigger">
                                                <i class="ace-icon fa fa-coffee green"></i>
                                                请输入用户名及口令
                                            </h4>

                                            <div class="space-6"></div>

                                            <form>
                                                <fieldset>
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input id="name" type="text" class="form-control" placeholder="用户名" />
                                                            <i class="ace-icon fa fa-user"></i>
                                                        </span>
                                                    </label>

                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input id="pswd" type="password" class="form-control" placeholder="口令"  onkeydown="if(event.keyCode==13) document.getElementById('loginBtn').click();" />
                                                            <i class="ace-icon fa fa-lock"></i>
                                                        </span>
                                                    </label>

                                                    <div class="space"></div>

                                                    <div class="clearfix">
                                                        <!-- <label class="inline">
                                                            <input type="checkbox" class="ace" />
                                                            <span class="lbl"> Remember Me</span>
                                                        </label> -->

                                                        <button id="loginBtn" type="button" class="width-35 pull-right btn btn-sm btn-primary " >
                                                            <i class="ace-icon fa fa-key"></i>
                                                            <span class="bigger-110">登录</span>
                                                        </button>
                                                    </div>

                                                    <div class="space-4"></div>
                                                </fieldset>
                                            </form>


                                        </div><!-- /.widget-main -->


                                    </div><!-- /.widget-body -->
                                </div><!-- /.login-box -->



                            </div><!-- /.position-relative -->


                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.main-content -->
        </div><!-- /.main-container -->

        <!-- basic scripts -->

        <!--[if !IE]> -->
        <script src="assets/js/jquery.2.1.1.min.js"></script>

        <!-- <![endif]-->

        <!--[if IE]>
<script src="assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

        <!--[if !IE]> -->
        <script type="text/javascript">
            window.jQuery || document.write("<script src='assets/js/jquery.min.js'>"+"<"+"/script>");
        </script>

        <!-- <![endif]-->

        <!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
        <script type="text/javascript">
            if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
        </script>

        <!-- inline scripts related to this page -->
        <script type="text/javascript">
        $(document).ready(function() {
            $("#loginBtn").bind("click", function() {
                $.ajax({
                    url: "login.action",
                    data: {
                        username: $("#name").val(),
                        password: $("#pswd").val(),
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
                            window.setTimeout("window.location='moni_.jsp'", 100);
                        } else if(idx == "fujianzhang") {
                            window.setTimeout("window.location='esta_.jsp'", 100);
                        } else if(idx == "admin") {
                            window.setTimeout("window.location='maintain_.jsp'", 100);
                        } else if(idx == "bumenzhang") {
                            window.setTimeout("window.location='esta_.jsp'", 100);
                        } else if(idx == "executor") {
                            window.setTimeout("window.location='exec_.jsp'", 100);
                        }
                    },
                });
            });
        });
        </script>
    </body>
</html>
