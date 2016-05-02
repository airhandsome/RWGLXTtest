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
    <link rel="stylesheet" href="assets/css/chosen.min.css" />
    <link rel="stylesheet" href="assets/css/datepicker.min.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css" />
    <link rel="stylesheet" href="assets/css/daterangepicker.min.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" href="assets/css/colorpicker.min.css" />

    <link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
    <link rel="stylesheet" href="assets/css/jquery.gritter.min.css" />
    <link rel="stylesheet" href="assets/css/select2.min.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-editable.min.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" /> <!-- text fonts -->
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
                    <li>
                        <a href="appr_.jsp">
                            <i class="ace-icon fa fa-users"></i>
                            <span>任务编辑</span>
                        </a>
                    </li>
                    <li class="light-blue">
                        <a href="moni_.jsp">
                            <i class="ace-icon fa fa-cog"></i>
                            <span>任务监控</span>
                        </a>
                    </li>
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
                                    <i class="ace-icon fa fa-power-off"></i> 登出
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /.navbar-container -->
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
                    <i class="ace-icon fa fa-anchor"></i>
                    <span class="menu-text"> 任务计划(ISP)审批 </span>
                </div>
            </div>
            <!-- /.sidebar-shortcuts -->

            <ul id="ISPList" class="nav nav-list">
                <li class="">
                    <a href="taskDA_.html" target="test">
                        <i class="menu-icon fa fa-tachometer"></i>
                        <span class="menu-text"> test </span>
                    </a>

                    <b class="arrow"></b>
                </li>

            </ul>
            <!-- /.nav-list -->

        </div>

        <div class="main-content">
            <div class="main-content-inner">
                <div class="breadcrumbs" id="breadcrumbs">
                    <script type="text/javascript">
                        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                    </script>

                    <ul class="breadcrumb">
                        <li>任务计划审批</li>
                        <li id="typex">--</li>
                        
                    </ul>
                    <!-- /.breadcrumb -->

                    
                </div>

                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12">
                            <ul class="list-inline">
                                <li>
                                    <label>任务名称</label>
                                </li>
                                <li>
                                    <input id="ispname" type="text" disabled/>
                                </li>
                                <li>
                                    <label>任务描述</label>
                                </li>
                                <li>
                                    <input id="ispdesc" type="text" disabled/>
                                </li>
                                <button id="apprBtn" class="btn btn-success btn-sm" data-focus="">批准</button>
                                <button id="rejcBtn" class="btn btn-warning btn-sm" data-focus="">驳回</button>
                                <button id="watchBtn" class="btn btn-watch btn-sm" data-focus="">预览</button>
                            </ul>
                            <iframe id="ispGraph" src="check.jsp" frameborder="0" name="ispGraph" width="100%;" height="785px;" scrolling="no"></iframe>
                        </div>
                        
                    </div>
                </div>
                <!-- /.page-content -->
            </div>
        </div>
        <!-- /.main-content -->

        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
        </a>
    </div>
    <!-- /.main-container -->

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
    <script src="assets/js/jquery-ui.custom.min.js"></script>
    <script src="assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="assets/js/chosen.jquery.min.js"></script>
    <script src="assets/js/fuelux.spinner.min.js"></script>
    <script src="assets/js/bootstrap-datepicker.min.js"></script>
    <script src="assets/js/bootstrap-timepicker.min.js"></script>
    <script src="assets/js/moment.min.js"></script>
    <script src="assets/js/daterangepicker.min.js"></script>
    <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
    <script src="assets/js/bootstrap-colorpicker.min.js"></script>
    <script src="assets/js/jquery.knob.min.js"></script>
    <script src="assets/js/jquery.autosize.min.js"></script>
    <script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
    <script src="assets/js/jquery.maskedinput.min.js"></script>
    <script src="assets/js/bootstrap-tag.min.js"></script>


    <script src="assets/js/jquery-ui.custom.min.js"></script>
    <script src="assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="assets/js/jquery.gritter.min.js"></script>
    <script src="assets/js/bootbox.min.js"></script>
    <script src="assets/js/jquery.easypiechart.min.js"></script>
    <script src="assets/js/bootstrap-timepicker.min.js"></script>
    <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
    <script src="assets/js/jquery.hotkeys.min.js"></script>
    <script src="assets/js/bootstrap-wysiwyg.min.js"></script>
    <script src="assets/js/select2.min.js"></script>
    <script src="assets/js/fuelux.spinner.min.js"></script>
    <script src="assets/js/bootstrap-editable.min.js"></script>
    <script src="assets/js/ace-editable.min.js"></script>
    <script src="assets/js/jquery.maskedinput.min.js"></script>

    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/bootstrap-editable.min.js"></script>
    <!-- page specific plugin scripts -->

    <!-- ace scripts -->
    <script src="assets/js/ace-editable.min.js"></script>
    <script src="assets/js/ace-elements.min.js"></script>
    <script src="assets/js/ace.min.js"></script>
    <script type="text/javascript">

            function loadSideBar() {
                var isp = [];
                $.ajax({
                    url: "getPendingISP.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        isp = data;

                    },
                });
                var s = '';
                for(var i = 0; i < isp.length; i ++) {
                    s += '<li> <a class="isp" href="#ispId';
                    s += isp[i].id
                    s += '"> <i class="menu-icon fa fa-tags"></i> <span class="menu-text">';
                    s += isp[i].name;
                    s += '</span> </a> <b class="arrow"></b> </li>';
                }
                $("#ISPList").html(s);

                $("a.isp").bind("click", function() {
                    $("#typex").text($(this).find("span").text());
                    
                    var idx = $(this).attr("href").substr(6);
                    $("#apprBtn").attr("data-focus", idx);
                    $("#watchBtn").attr("data-focus", idx);
                    $.ajax({
                        url: "getISPbyId.action",
                        data: {ispId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            $("#ispGraph").attr("src", "check.jsp");//!@#ispGraph2_
                            $("#ispname").val(data.name);
                            $("#ispdesc").val(data.description);
                        
                            // loadDARelatives();
                            // loadDSRelatives();
                        },
                    });
                });
            }

            $(document).ready(function() {
                loadSideBar();

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
                
                $("#apprBtn").bind("click", function() {
                    if($(this).attr("data-focus") == "")
                        return;
                    $.ajax({
                        url: "apprISP.action",
                        data: {ispId: $(this).attr("data-focus")},
                        dataType: "json",
                        success: function() {
                            alert("任务已批准");
                            loadSideBar();
                        }
                    });
                });
                $("#rejcBtn").bind("click", function() {
                    if($("#apprBtn").attr("data-focus") == "")
                        return;
                    $.ajax({
                        url: "rejctBtn.action",
                        data: {ispId: $("#apprBtn").attr("data-focus")},
                        dataType: "json",
                        success: function() {
                            alert("任务已驳回");
                            loadSideBar();
                        }
                    });
                });
              $("#watchBtn").bind("click", function() {
                    if($(this).attr("data-focus") == "")
                        return;
                    $("#typex").text($(this).find("span").text());               
//                     alert($("#apprBtn").attr("data-focus"));  
                    var idx = $("#apprBtn").attr("data-focus");
//                     $("#apprBtn").attr("data-focus", idx);
// 					alert(idx);
                    $.ajax({
                        url: "getISPbyId.action",
                        data: {ispId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            $("#ispGraph").attr("src", "ispGraph3_.jsp");//!@#ispGraph2_
                            $("#ispname").val(data.name);
                            $("#ispdesc").val(data.description);
                        },
                    });
                });
       
            });
    </script>

    <!-- inline scripts related to this page -->
</body>

</html>