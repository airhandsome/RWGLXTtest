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

        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="assets/font-awesome/4.2.0/css/font-awesome.min.css" />

        <!-- page specific plugin styles -->

        <!-- text fonts -->
        <link rel="stylesheet" href="assets/fonts/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

        <!--[if lte IE 9]>
            <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
        <![endif]-->

        <!--[if lte IE 9]>
          <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
        <![endif]-->

        <!-- inline styles related to this page -->

        <!-- ace settings handler -->
        <script src="assets/js/ace-extra.min.js"></script>

        <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

        <!--[if lte IE 8]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->
    </head>

    <body class="no-skin">
        <div id="navbar" class="navbar navbar-default">
            <script type="text/javascript">
                try{ace.settings.check('navbar' , 'fixed')}catch(e){}
            </script>

            <div class="navbar-container" id="navbar-container">
                <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
                    <span class="sr-only">Toggle sidebar</span>

                    <span class="icon-bar"></span>

                    <span class="icon-bar"></span>

                    <span class="icon-bar"></span>
                </button>

                <div class="navbar-header pull-left">
                    <a href="" class="navbar-brand">
                        <small>
                            <i class="menu-icon fa fa-tachometer"></i>
                            全舰任务综合管理
                        </small>
                    </a>
                </div>

                <div class="navbar-buttons navbar-header pull-right" role="navigation">
                    <ul class="nav ace-nav">

                        <li class="light-blue">
                            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                <span>
                                    <small id="operid" data-post='<%=(String)session.getAttribute("post")%>'><%=(String)session.getAttribute("post")%></small>
                                </span>
                                <i class="ace-icon fa fa-caret-down"></i>
                            </a>

                            <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                                <li>
                                    <a href="#logout">
                                        <i class="ace-icon fa fa-power-off"></i>
                                        登出
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div><!-- /.navbar-container -->
        </div>

        <div class="main-container" id="main-container">
            <script type="text/javascript">
                try{ace.settings.check('main-container' , 'fixed')}catch(e){}
            </script>

            <div id="sidebar" class="sidebar responsive">
                <script type="text/javascript">
                    try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
                </script>

                <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                    <div class="sidebar-shortcuts-large blue" id="sidebar-shortcuts-large">
                        <i class="ace-icon fa fa-cogs"></i>
                        <span class="menu-text"> 任务执行 </span>
                    </div>
                </div><!-- /.sidebar-shortcuts -->

                <ul class="nav nav-list">
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-spinner"></i>
                            <span class="menu-text">
                                待执行的DA
                                <span id="Npend" class="badge badge-danger"></span>
                            </span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>

                        <ul id="PendingList" class="submenu">

                        </ul>
                    </li>

                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-tasks"></i>
                            <span class="menu-text">
                                执行中的DA
                                <span id="Nexec" class="badge badge-primary"></span>
                            </span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>

                        <ul id="ExecuteList" class="submenu">

                        </ul>
                    </li>


                </ul><!-- /.nav-list -->

            </div>

            <div class="main-content">
                <div class="main-content-inner">
                    <div class="breadcrumbs" id="breadcrumbs">
                        <script type="text/javascript">
                            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                        </script>

                        <ul class="breadcrumb">
                            <li>任务执行</li>
                            <li id="typex">待执行的DA</li>
                            <li id="namex" class="active">&nbsp;</li>
                        </ul><!-- /.breadcrumb -->
                    </div>
                                        
                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="row">
                                    <div class="space"></div>
                                    <div class="col-sm-7">
                                        <iframe id="ispGraph" src="daExe.jsp" frameborder="0" name="ispGraph" width="100%;" height="500px;" scrolling="no"></iframe>
                                        <div class="well" style="height: 300px; margin: 15px;"></div>
                                    </div>
                                    <div class="col-sm-5">
                                        <div class="row">
                                            <div class="col-sm-8">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> 任务名称 </div>
                                                        <div class="profile-info-value" id="daname">

                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> 任务描述 </div>
                                                        <div class="profile-info-value" id="dadesc">

                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> 操作员 </div>
                                                        <div class="profile-info-value" id="daoper">

                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> 触发方式 </div>
                                                        <div class="profile-info-value" id="datrigger">

                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> 开始时间 </div>
                                                        <div class="profile-info-value" id="dastart">

                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> 持续时间 </div>
                                                        <div class="profile-info-value" id="dadura">

                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> 执行操作 </div>
                                                        <div class="profile-info-value" id="dacode">

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <button id="DABtnBegin" class="btn btn-info pull-right" data-focus="" style="margin-right: 30px;">
                                                    开始执行
                                                    <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                                                </button>
                                                <br /><br /><br />
                                                <button id="DABtnEnd" class="btn btn-success pull-right" data-focus="" style="margin-right: 30px;">
                                                    执行完成
                                                    <i class="ace-icon fa fa-check icon-on-right"></i>
                                                </button>
                                            </div>
                                        </div>
                                        
                                        <div class="space-6"></div>
                                        <div id="alertBegin" class="alert alert-success" style="display: none">
                                            <button class="close" data-dismiss="alert">
                                                <i class="ace-icon fa fa-times"></i>
                                            </button>
                                            任务已开始执行
                                        </div>
                                        <div id="alertEnd" class="alert alert-success" style="display: none">
                                            <button class="close" data-dismiss="alert">
                                                <i class="ace-icon fa fa-times"></i>
                                            </button>
                                            任务已执行结束
                                        </div>
                                        <div class="space"></div>
                                        <div class="tabbable" style="margin: 13px;">
                                            <ul class="nav nav-tabs" id="systemList">

                                            </ul>

                                            <div class="tab-content no-padding" style="height: 560px; overflow-y: scroll;">
                                                <div class="tab-pane fade in active">
                                                    <table class="table table-bordered table-hover">
                                                        <thead><tr>
                                                            <th>测点位置</th>
                                                            <th>测点名称</th>
                                                            <th>实时状态</th>
                                                        </tr></thead>
                                                        <tbody id="xattrList">
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.col -->
                                </div>
                                <!-- /.row -->
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.page-content -->
                </div>
            </div><!-- /.main-content -->
            <div class="footer">
                <div class="footer-inner">
                    <div class="footer-content">
                        <span class="bigger-120">
                            <i class="ace-icon fa fa-exclamation-triangle"></i>
                            <span class="bolder">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <span class="blue">运行状态:</span>&nbsp;1.打开&nbsp;0.关闭&nbsp;&nbsp;<span class="blue">启停状态:</span>&nbsp;1.启动&nbsp;0.停止&nbsp;&nbsp;<span class="blue">故障状态:</span>&nbsp;1.故障&nbsp;0.正常
                        </span>
                    </div>
                </div>
            </div>
            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
            </a>
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
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/bootstrap-editable.min.js"></script>
        <!-- page specific plugin scripts -->

        <!-- ace scripts -->
        <script src="assets/js/ace-editable.min.js"></script>
        <script src="assets/js/ace-elements.min.js"></script>
        <script src="assets/js/ace.min.js"></script>
        <script type="text/javascript">

            $('[data-rel=popover]').popover({html:true});
            
            function addAttr(idx) {
                var sid = '';
                var sname = '';
                $.ajax({
                    url: "getAttrbyId.action",
                    async: false,
                    data: { atrId: idx },
                    dataType: "json",
                    success: function (data) {
                        data = data[0];
                        sid = data.systemId;
                        sname = data.systemName;
                        // var s = '<div class="infobox infobox-blue" style="width:25%" href="#attr';
                        // s += data.id;
                        // s += '" data-sid="' + sid + '" data-sname="' + sname + '" style="display: none;"> <div class="infobox-data"> <span class="infobox-data-number">';
                        // s += data.value;
                        // s += '</span> <div class="infobox-content">';
                        // s += data.name;
                        // s += '</div> </div>';
                        // if (data.delta < 0.0) {
                        //     s += '<div class="stat stat-success">';
                        //     s += (0.0 - data.delta);
                        //     s += '</div>';
                        // } else if (data.delta > 0.0 && data.delta < 9000) {
                        //     s += '<div class="stat stat-important">';
                        //     s += data.delta;
                        //     s += '</div>';
                        // }
                        // s += '</div>';
                        var s = '<tr href="#attr';
                        s += data.id + '" data-sid="' + sid + '" data-sname"' + sname + '" style="display: none;">';
                        s += '<td></td><td>' + data.name + '</td><td>' + data.value + '</td></tr>';
                        $("#xattrList").append(s);
                    },
                });
                var x = 1;
                for(var i = 0; i < $("#systemList").find("a").length; i ++) {
                    var wtf = $("#systemList").find("a").eq(i);
                    if(sid == wtf.attr("href").substr(4)) {
                        x = 0;
                        break;
                    }
                }
                if(x == 1) {
                    $("#systemList").append('<li> <a data-toggle="tab" href="#sid' + sid + '" aria-expanded="false">' + sname + '</a></li>');
                }
                
                
            }

            function loadSideBar() {
                $.ajax({
                    url: "getPDA.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        $("#PendingList").empty();
                        var now = -1;
                        data = data.sort(function(a, b) {
                            if(a.ispid != b.ispid)
                                return a.priority - b.priority;
                            return a.ispid - b.ispid;
                        });
                        s = '';
                        for(var i = 0; i < data.length; i ++) {
                            if(now != data[i].ispid) {
                                if(now != -1) {
                                    s += '</ul> </li>';
                                }
                                s += '<li class> <a href="#" class="dropdown-toggle"> <i class="menu-icon fa fa-caret-right"></i>';
                                s += data[i].ispname;
                                s += '<b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b> <ul class="submenu nav-show" style="display: none;">';
                            }
                            s += '<li class=""> <a href="#DAP';
                            s += data[i].id;
                            s += '"> <i class="menu-icon fa fa-caret-right"></i>';
                            s += '<span class="menu-text">' + data[i].name + '<span class="badge badge-info">' + data[i].priority + '</span></span>';
                            s += '</a> <b class="arrow"></b> </li>';
                            now = data[i].ispid;
                        }
                        
                        $("#Npend").text(data.length);
                        if(data.length == 0) {
                            var s = '<li class><a>';
                            s += '<i class="menu-icon fa fa-cogs"></i> <span class="menu-text">';
                            s += '空';
                            s += '</span> </a> <b class="arrow"></b> </li>';
                            $("#PendingList").html(s);
                        } else {
                            $("#PendingList").html(s + '</ul> </li>');
                        }
                    }
                });
                $.ajax({
                    url: "getEDA.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        $("#ExecuteList").empty();
                        var now = -1;
                        data = data.sort(function(a, b) {
                            return a.ispid - b.ispid;
                        });
                        s = '';
                        for(var i = 0; i < data.length; i ++) {
                            if(now != data[i].ispid) {
                                if(now != -1) {
                                    s += '</ul> </li>';
                                }
                                s += '<li class> <a href="#" class="dropdown-toggle"> <i class="menu-icon fa fa-caret-right"></i>';
                                s += data[i].ispname;
                                s += '<b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b> <ul class="submenu nav-show" style="display: none;">';
                            }
                            s += '<li class=""> <a href="#DAE';
                            s += data[i].id;
                            s += '"> <i class="menu-icon fa fa-caret-right"></i>';
                            s += '<span class="menu-text">' + data[i].name + '<span class="badge badge-info">' + data[i].priority + '</span></span>';
                            s += '</a> <b class="arrow"></b> </li>';
                            now = data[i].ispid;
                        }
                        
                        $("#Nexec").text(data.length);
                        if(data.length == 0) {
                            var s = '<li class><a>';
                            s += '<i class="menu-icon fa fa-cogs"></i> <span class="menu-text">';
                            s += '空';
                            s += '</span> </a> <b class="arrow"></b> </li>';
                            $("#ExecuteList").html(s);
                        } else {
                            $("#ExecuteList").html(s + '</ul> </li>');
                        }
                    }
                });

                $("[href^='#DA']").bind("click", function() {
                    var idx = $(this).attr("href").substr(4);
                    var tp = $(this).attr("href").substr(3, 1);
                    $.ajax({
                        url: "getDAbyId.action",
                        async: false,
                        data: {daId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            // var attr = data.attribute;
                            $("#namex").text(data.name);
                            $("#daname").text(data.name);
                            $("#dadesc").text(data.description);
                            $("#daoper").text($("#operid").text());
                            $("#dastart").text(data.planStart);
                            $("#dadura").text(data.planDuration);
                            $("#dacode").val(data.code);
                            if(data.code == "no data") {
                                $("#dacode").val("---");
                            }
                            if(data.planDuration == "") {
                                $("#dadura").text("---");
                            }
                            if(data.planStart == "") {
                                $("#dastart").text("---");
                            }
                            if(data.triggertype == "state") {
                                $("#datrigger").text("状态触发");
                            } else if(data.triggertype == "time") {
                                $("#datrigger").text("时间触发");
                            } else {
                                $("#datrigger").text("事件触发");
                            }
                            $("[id^='DABtn']").attr("data-focus", idx).hide();

                            if(tp == "P") {
                                $("#DABtnBegin").show();
                                $("#typex").text("待执行的DA");
                            } else {
                                $("#DABtnEnd").show();
                                $("#typex").text("执行中的DA");
                            }
                            $("#ispGraph").attr("src", "daExe.jsp");
                        }
                    });
                    loadAttrList();
                });
            }

            function loadAttrList() {
                var idx = $("#DABtnBegin").attr("data-focus");
                var attr = new Array();
                if(idx != "") {
                    $.ajax({
                        url: "getDAbyId.action",
                        async: false,
                        data: {daId: idx},
                        dataType: "json",
                        success: function(data) {
                            attr = data[0].attribute;
                        }
                    });
                }
                // $("#attrList").empty();
                // for(var i = 0; i < attr.length; i ++) {
                //     $.ajax({
                //         url: "getAttrbyId.action",
                //         async: false,
                //         data: {atrId: attr[i]},
                //         dataType: "json",
                //         success: function(data) {
                //             data = data[0]
                //             var s = '<div class="infobox infobox-blue" style="width: 12%;"> <div class="infobox-data"> <span class="infobox-data-number">';
                //             s += data.value;
                //             s += '</span> <div class="infobox-content">';
                //             s += data.name;
                //             s += '</div> </div>';
                //             if(data.delta < 0.0) {
                //                 s += '<div class="stat stat-success">';
                //                 s += (0.0 - data.delta);
                //                 s += '</div>';
                //             } else if(data.delta > 0.0 && data.delta < 9000) {
                //                 s += '<div class="stat stat-important">';
                //                 s += data.delta;
                //                 s += '</div>';
                //             }
                //             s += '</div>';
                //             $("#attrList").append(s);
                //         }
                //     });
                // }
                // for(var i = 0; (attr.length + i) % 8 != 0; i ++) {
                //     $("#attrList").append('<div class="infobox infobox-blue" style="width: 12%;"></div>');
                // }
                $("#systemList").empty();
                $("#xattrList").empty();
                for (var i = 0; i < attr.length; i++) {
                    addAttr(attr[i]);
                }
                $("a[href^='#sid']").bind("click", function() {
                    var sid = $(this).attr("href").substr(4);
                    $("tr[href^='#attr']").hide();
                    $("tr[data-sid='" + sid + "']").show();
                });
            }

            function refreshTime() {
                $.ajax({
                    url: "checkTime.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        ;
                    },
                });
            }

            $(document).ready(function() {

                $("a[href='#logout']").bind("click", function() {
                    $.ajax({
                        url: "logout.action",
                        data: {},
                        dataType: "json",
                        success: function(data) {
                            location.href = "login_.jsp";
                        },
                    });
                });

                $("#DABtnBegin").bind("click", function() {
                    if($(this).attr("data-focus") == "")
                        return;
                    var idx = $(this).attr("data-focus");
                    $.ajax({
                        url: "startDA.action",
                        data: {
                            daId: idx,
                            //code: $("#dacode").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            $("[id^='DABtn']").attr("data-focus", "");
                            $("#alertBegin").fadeIn();
                            window.setTimeout("window.location='exec_.jsp'", 200);
                        },
                    });
                });

                $("#DABtnEnd").bind("click", function() {
                    if($(this).attr("data-focus") == "")
                        return;
                    var idx = $(this).attr("data-focus");
                    $.ajax({
                        url: "finishDA.action",
                        data: {daId: idx},
                        dataType: "json",
                        success: function(data) {
                            $("[id^=DABtn']").attr("data-focus", "");
                            $("#alertEnd").fadeIn();
                            location.href = 'exec_.jsp';
                        },
                    });
                });

                loadSideBar();
                setInterval(refreshTime, 10000);
                //setInterval(loadAttrList, 2000);
                setInterval(loadSideBar, 20000);
            });
        </script>

        <!-- inline scripts related to this page -->
    </body>
</html>
