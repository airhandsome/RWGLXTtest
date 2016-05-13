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
        <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />

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

                        <li>
                            <a href="esta_.jsp">
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
                        <i class="ace-icon fa fa-anchor"></i>
                        <span class="menu-text"> 任务计划(ISP)管理 </span>
                    </div>
                </div><!-- /.sidebar-shortcuts -->

                <ul id="ISPList" class="nav nav-list">
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-spinner"></i>
                            <span class="menu-text">
                                ISP模板
                                <span id="Npend" class="badge badge-danger"></span>
                            </span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>

                        <ul id="templateList" class="submenu">

                        </ul>
                    </li>

                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-tasks"></i>
                            <span class="menu-text">
                                ISP实例
                                <span id="Nexec" class="badge badge-primary"></span>
                            </span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>

                        <ul id="instanceList" class="submenu">

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
                            <li>ISP管理</li>
                            <li id="typex">--</li>
                            <li id="namex" class="active">--</li>
                        </ul><!-- /.breadcrumb -->
                        <a id="newBtn" class="btn btn-primary pull-right">
                            新建任务 &nbsp;
                            <i class="ace-icon fa fa-pencil"></i>
                        </a>
                    </div>

                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-9">
                                <ul class="list-inline">
                                    <li><label>任务名称</label></li>
                                    <li><input id="ispname" type="text" /></li>
                                    <li><label>任务描述</label></li>
                                    <li><input id="ispdesc" type="text" /></li>
                                    <li><label>执行日期</label></li>
                                    <li><input id="starttimex" class="form-control date-picker" type="text" data-date-format="yyyy-mm-dd" /></li>      
                                    <button id="confBtn" class="btn btn-success btn-sm" data-focus="">提交</button>
                                    <button id="submBtn" class="btn btn-success btn-sm" data-focus="" style="display: none;">生成实例</button>
                                    
                                    <button id="watchBtn" class="btn btn-watch btn-sm" data-focus="">预览</button>
                                    <button id="returnBtn" class="btn btn-inverse btn-sm" data-focus=""  style="display: none;">返回</button>
                                     
                                </ul>
                                <iframe id="ispGraph" src="ispGraph_.jsp" frameborder="0" name="ispGraph" width="100%;" height="785px;" scrolling="no"></iframe>
                            </div>
                            <div class="col-xs-3">
                                <br />
                                <ul class="nav nav-pills">
                                    <li class="active">
                                        <a href="#DAL", data-toggle="pill">任务关联DA</a>
                                    </li>
                                    <li>
                                        <a href="#DSL", data-toggle="pill">任务关联DS</a>
                                    </li>
                                </ul>
                                <div id="modal-container" class="modal fade" aria-hidden="true">
                                    <div class="modal-dialog">
                                         <div class="modal-content">
                                             <div class="modal-header">
                                                 <button class="close" aria-hidden="true" data-dismiss="modal"> x </button>
                                                 <h4 class="modal-title">触发条件编辑</h4>
                                             </div>
                                             <div class="modal-body">
                                                 <h5> 任务开始条件</h5>
                                                 <form id="startAttrs">
                                                     <button class="btn btn-info btn-block" href="#add-0">添加条件</button>
                                                 </form>
                                                 <br />
                                                 <hr>
                                                 <h5> 任务结束条件</h5>
                                                 <form id="stopAttrs">
                                                     <button class="btn btn-info btn-block" href="#add-1">添加条件</button>
                                                 </form>
                                                 
                                                 <button class="btn btn-success" id="submitAttr">保存</button>
                                             </div>
                                             
                                                 
                                             
                                         </div>
                                    </div>
                                </div> 
                                <div class="tab-content no-padding no-border">
                                    <div id="DAL" class="tab-pane fade in active">
                                        <div class="widget-box widget-color-blue2">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-cog"></i>
                                                    任务关联DA
                                                </h5>
                                                <div class="widget-toolbar">
                                                    <a href="#xda" data-action="0" class="white">
                                                        <i class="ace-icon fa fa-arrow-left"></i>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="widget-body">
                                                <div class="widget-main" style="height: 700px; overflow-y: scroll;">
                                                    <div id="DAList1">
                                                    </div>
                                                    <div id="DAList2" style="display: none;">
                                                    </div>
                                                    <div id="DAList3" style="display: none;">
                                                        <form class="form-horizontal" role="form">
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label no-padding-right" for="daname"> 任务名称 </label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" id="daname" class="col-xs-10 col-sm-10" disabled="disabled">
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label no-padding-right" for="triggertype"> 触发类型 </label>
                                                                <div class="col-sm-9">
                                                                    <select id="triggertype" class="col-xs-10 col-sm-10">
                                                                        <option value="time">时间触发</option>
                                                                        <option value="event">事件触发</option>
                                                                        <option value="state">状态触发</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label no-padding-right" for="autotype"> 启动形式 </label>
                                                                <div class="col-sm-9">
                                                                    <select id="autotype" class="col-xs-10 col-sm-10">
                                                                        <option value="0">手动</option>
                                                                        <option value="1">自动</option>
                                                                        <option value="2">半自动</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label no-padding-right" for="starttime"> 起始时间 </label>
                                                                <div class="col-sm-9">
                                                                    <div class="input-group col-xs-10 col-sm-10">
                                                                        <input id="starttime2" class="form-control date-picker" type="text" data-date-format="yyyy-mm-dd" />
                                                                        <span class="input-group-addon">
                                                                            <i class="fa fa-clock-o bigger-110"></i>
                                                                        </span>
                                                                        <input id="starttime" type="text" class="form-control" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label no-padding-right" for="endtime"> 结束时间 </label>
                                                                <div class="col-sm-9">
                                                                    <div class="input-group col-xs-10 col-sm-10">
                                                                        <input id="endtime2" class="form-control date-picker" type="text" data-date-format="yyyy-mm-dd" />
                                                                        <span class="input-group-addon">
                                                                            <i class="fa fa-clock-o bigger-110"></i>
                                                                        </span>
                                                                        <input id="endtime" type="text" class="form-control" />
                                                                    </div>

                                                                </div>
                                                            </div>
                                                            <hr>
                                                            <h4>任务开始触发条件：</h4>
                                                            <div id="startAttrsx">
                                                                
                                                                <div class="form-group">
                                                                    <div class="col-sm-12">
                                                                        <h4 style="margin-left: 40px;">123123123 &lt; 20 and </h4>
                                                                        
                                                                    </div>
                                                                </div>
                                                                
                                                            </div>
                                                            <hr>
                                                            <h4>任务结束触发条件：</h4>
                                                            <div id="stopAttrsx">
                                                                
                                                            </div>
                                                            
                                                            <!--<div class="form-group">
                                                                <label class="col-sm-3 control-label no-padding-right" for="attrid"> 触发状态 </label>
                                                                <div class="col-sm-9">
                                                                    <select id="attrid" class="col-xs-10 col-sm-10">
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label no-padding-right" for="attrsyb"> 触发逻辑 </label>
                                                                <div class="col-sm-9">
                                                                    <select id="attrsyb" class="col-xs-10 col-sm-10">
                                                                        <option value="1">=</option>
                                                                        <option value="2">&gt;</option>
                                                                        <option value="3">&lt;</option>
                                                                        <option value="4">&ge;</option>
                                                                        <option value="5">&le;</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label no-padding-right" for="attrval"> 触发数值 </label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" id="attrval" class="col-xs-10 col-sm-10">
                                                                </div>
                                                            </div>-->
                                                            
                                                            <hr>
                                                            <button class="btn btn-info" data-toggle="modal" href="#modal-container">
                                                                修改触发条件
                                                            </button>
                                                            <hr>
                                                            <div class="form-group">
                                                                <div class="col-sm-1"></div>
                                                                <div class="col-sm-9">
                                                                    <div class="input-group">
                                                                        <select id="insinfo" type="text" class="form-control"></select>
                                                                        <span class="input-group-btn">
                                                                            <button id="getinsBtn" type="button" class="btn btn-success btn-sm">
                                                                                更新实例
                                                                                <span class="ace-icon fa fa-check icon-on-right bigger-110"></span>
                                                                            </button>
                                                                        </span>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="DSL" class="tab-pane fade">
                                        <div class="widget-box widget-color-blue2">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-random"></i>
                                                    任务关联DS
                                                </h5>
                                                <div class="widget-toolbar">
                                                    <a href="#xds" data-action="0" class="white">
                                                        <i class="ace-icon fa fa-arrow-left"></i>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="widget-body">
                                                <div class="widget-main" style="height: 700px; overflow-y: scroll;">
                                                    <div id="DSList1">
                                                    </div>
                                                    <div id="DSList2">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.page-content -->
                </div>
            </div><!-- /.main-content -->

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

            $('[data-rel=tooltip]').tooltip();
            $('#starttime').timepicker({
                    minuteStep: 10,
                    showSeconds: true,
                    showMeridian: false
                }).next().on(ace.click_event, function(){
                    $(this).prev().focus();
                });
            $('#endtime').timepicker({
                    minuteStep: 10,
                    showSeconds: true,
                    showMeridian: false
                }).next().on(ace.click_event, function(){
                    $(this).prev().focus();
                });

            $('#starttime2').datepicker({
                    autoclose: true,
                    todayHighlight: true
                })
                //show datepicker when clicking on the icon
                .next().on(ace.click_event, function(){
                    $(this).prev().focus();
                });
            $('#endtime2').datepicker({
                    autoclose: true,
                    todayHighlight: true
                })
                //show datepicker when clicking on the icon
                .next().on(ace.click_event, function(){
                    $(this).prev().focus();
                });
                $('#starttimex').datepicker({
                    autoclose: true,
                    todayHighlight: true
                })
                //show datepicker when clicking on the icon
                .next().on(ace.click_event, function(){
                    $(this).prev().focus();
                });
                $(document).one('ajaxloadstart.page', function(e) {
                    $('textarea[class*=autosize]').trigger('autosize.destroy');
                    $('.limiterBox,.autosizejs').remove();
                    $('.daterangepicker.dropdown-menu,.colorpicker.dropdown-menu,.bootstrap-datetimepicker-widget.dropdown-menu').remove();
                });

            //$('#endtime').timepicker({
                    //minuteStep: 10,
                    //showSeconds: false,
                    //showMeridian: false
                //}).next().on(ace.click_event, function(){
                    //$(this).prev().focus();
                //});


            function loadSideBar() {
                var isp = [];
                $.ajax({
                    url: "getISPdef.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        isp = data;
                    },
                });
                var s = '';
                for(var i = 0; i < isp.length; i ++) {
                    s += '<li> <a class="tisp" href="#ispId';
                    s += isp[i].id;
                    s += '"> <i class="menu-icon fa fa-tags"></i> <span class="menu-text">';
                    s += isp[i].name;
                    s += '</span> </a> <b class="arrow"></b> </li>';
                }
                $("#templateList").html(s);

                $.ajax({
                    url: "getToBeApprovedISP.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        isp = data;
                    },
                });
                var s = '';
                for(var i = 0; i < isp.length; i ++) {
                    s += '<li> <a class="iisp" href="#ispId';
                    s += isp[i].id;
                    s += '"> <i class="menu-icon fa fa-tags"></i> <span class="menu-text">';
                    s += isp[i].name;
                    s += '</span> </a> <b class="arrow"></b> </li>';
                }
                if(isp.length == 0) {
                            s = '<li class><a>';
                            s += '<i class="menu-icon fa fa-cogs"></i> <span class="menu-text">';
                            s += '空';
                            s += '</span> </a> <b class="arrow"></b> </li>';
                            
                        }
                $("#instanceList").html(s);

                $("a.tisp").bind("click", function() {
                    $("#typex").text("调用模板");
                    $("#confBtn").hide();
                    $("#submBtn").show();
                    $("#namex").text($(this).find("span").text());
                    var idx = $(this).attr("href").substr(6);
                    $("#confBtn").attr("data-focus", idx);
                    $("#watchBtn").attr("data-focus", idx);
                    $("#returnBtn").attr("data-focus", idx);
                    $("#watchBtn").attr("style","display:  inline;")
                    $("#returnBtn").attr("style","display: none;")
                    $.ajax({
                        url: "getDefISPbyId.action",
                        data: {ispId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            $("#ispGraph").attr("src", "ispGraph_.jsp");
                            $("#ispname").val(data.name);
                            $("#ispdesc").val(data.description);
                        },
                    });
                });

                $("a.iisp").bind("click", function() {
                    $("#typex").text("更新实例");
                    $("#confBtn").show();
                    $("#submBtn").hide();
                    $("#namex").text($(this).find("span").text());
                    var idx = $(this).attr("href").substr(6);
                    $("#confBtn").attr("data-focus", idx);
                    $.ajax({
                        url: "getISPbyId.action",
                        data: {ispId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            $("#ispGraph").attr("src", "ispGraph_.jsp");
                            $("#ispname").val(data.name);
                            $("#ispdesc").val(data.description);
                        },
                    });
                });


            }

            function loadDARelatives() {
                // $("#insinfo").bind("mouseover", function() {
                //     $("#insinfo").empty();
                //     var docs = $(window.frames["ispGraph"].document).find(".GooFlow_item");
                    
                //     for(var i = 0; i < docs.length; i ++) {
                //         if(docs.eq(i).find("b").attr("class") == "ico_task") {
                            
                //             var s = '<option value="' + docs.eq(i).attr("id") + '">' + docs.eq(i).find("td").eq(1).text() + '</option>';
                //             $("#insinfo").append(s)
                //         }
                //     }
                // });
                
                
                
                var attr = [];
                // $.ajax({
                //     url: "getAllAtr.action",
                //     async: false,
                //     data: {},
                //     dataType: "json",
                //     success: function(data) {
                //         attr = data;
                //     },
                // });
                // for(var i = 0; i < attr.length; i ++) {
                //     var s = '<option value="';
                //     s += attr[i].id + '">';
                //     s += attr[i].name + '</option>';
                //     $("#attrid").append(s);
                // }

                var typeDA = [];
                $.ajax({
                    url: "getType.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        typeDA = data;
                    },
                });
                for(var i = 0; i < typeDA.length; i ++) {
                    var s = '<button type="button" class="btn btn-white btn-info btn-round" style="width: 32%;" href="#typeDA';
                    s += typeDA[i].id;
                    s += '"> ';
                    s += typeDA[i].name;
                    $("#DAList1").append(s);
                }
                $("button[href^='#typeDA']").bind("click", function() {
                    $("a[href='#xda']").attr("data-action", "1");
                    var idx = $(this).attr("href").substr(7);
                    var DAs = [];
                    $.ajax({
                        url: "getDAbyType.action",
                        async: false,
                        data: {typeId: idx},
                        dataType: "json",
                        success: function(data) {
                            DAs = data;
                        },
                    });
                    $("#DAList2").empty();
                    for(var i = 0; i < DAs.length; i ++) {
                        var s = '<button type="button" class="btn btn-white btn-info btn-round" style="width: 32%;" href="#DA';
                        s += DAs[i].id + '">';
                        s += DAs[i].name;
                        s += '</button>';
                        $("#DAList2").append(s);
                    }
                    $("#DAList1").hide();
                    $("#DAList2").fadeIn();
                    $("button[href^='#DA']").bind("click", function() {
                        $("#insinfo").empty();
                        var docs = $(window.frames["ispGraph"].document).find(".GooFlow_item");
                        for(var i = 0; i < docs.length; i ++) {
                            if(docs.eq(i).find("b").attr("class") == "ico_task") {
                                var s = '<option value="' + docs.eq(i).attr("id") + '">' + docs.eq(i).find("td").eq(1).text() + '</option>';
                                $("#insinfo").append(s)
                            }
                        }
                        var ins = $(window.frames["ispGraph"].document).find("#demo").attr("data-focus").split('#')[0];
                        $("option[value='" + ins + "']").attr("selected", true);
                        var idx = $(this).attr("href").substr(3);
                        var name = $(this).text();
                        $("a[href='#xda']").attr("data-action", "2");
                        $("#DAList3").attr("data-focus", idx);
                        $("#daname").val(name);
                        $("#DAList2").hide();
                        $("#DAList3").fadeIn();
                        getClickedDA(0 - idx);
                    });
                });

                $("a[href='#xda']").bind("click", function() {
                    if($(this).attr("data-action") == "1") {
                        $("#DAList2").hide();
                        $("#DAList1").fadeIn();
                        $(this).attr("data-action", "0");
                    } else if($(this).attr("data-action") == "2") {
                        $("#DAList3").hide();
                        $("#DAList2").fadeIn();
                        $(this).attr("data-action", "1");
                    }
                });
                
                $("#getinsBtn").bind("click", function() {
                    var idx = $("#DAList3").attr("data-focus") > 0? $("#DAList3").attr("data-focus"): 0 - $("#DAList3").attr("data-focus");
                    // if($("#DAList3").attr("data-focus") > 0) {
                        alert($("#autotype").val())
                        $.ajax({
                            async: false,
                            url: "DaDef.action",
                            type: "post",
                            traditional: true,
                            data: {
                                daId: idx,
                                triggertype: $("#triggertype").val(),
                                planStart: $("#starttime2").val() + " " + $("#starttime").val(),
                                planEnd: $("#endtime2").val() + " " + $("#endtime").val(),
                                ifAuto: $("#autotype").val(),
                                // startattributeId: $("#attrid").val(),
                                // startsymbol: $("#attrsyb").val(),
                                // startvalue: $("#attrval").val(),
                            },
                            dataType: "json",
                            success: function(data) {
                                var idx = $("#insinfo").val();
                                $("#DAList3").attr("data-focus", "-" + data);
                                $(window.frames["ispGraph"].document).find("#" + idx).find("td").eq(1).text($("#daname").val() + "#" + data);
                                alert("更新完成");
                            }
                        });
                    // }
                });
                $("#submitAttr").bind("click", function() {
                    for(var j = 0; j < 2; j ++) {
                        var attrid = '';
                        var symbs = '';
                        var vals = '';
                        var logic = '';
                        var x = $("#startAttrs").children();
                        if(j == 1)
                            x = $("#stopAttrs").children();
                        for(var i = 0; i < x.length - 1; i ++) {
                            attrid += '#' + x.eq(i).find("select").eq(0).val();
                            symbs += '#' + x.eq(i).find("select").eq(1).val();
                            vals += '#' + x.eq(i).find("input").eq(0).val();
                            logic += '#' + x.eq(i).find("select").eq(2).val();
                        }
                        $.ajax({
                            url: "setTriggersbyId.action",
                            type: "post",
                            dataType: "json",
                            data: {
                                daId: $("#DAList3").attr("data-focus").substr(1),
                                status: j,
                                attrid: attrid.substr(1),
                                symbs: symbs.substr(1),
                                vals: vals.substr(1),
                                logics: logic.substr(1),
                            },
                            success: function() {
                                alert("更新完成");
                            }
                        });
                    }
                });

                
            }

            function loadDSRelatives() {
                
                var typeDS = [];
                $.ajax({
                    url: "getDSType.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        typeDS = data;
                    }
                });
                for(var i = 0; i < typeDS.length; i ++) {
                    var s = '<button type="button" class="btn btn-white btn-info btn-round" style="width: 32%;" href="#typeDS';
                    s += typeDS[i].id;
                    s += '"> ';
                    s += typeDS[i].name;
                    $("#DSList1").append(s);
                }
                $("button[href^='#typeDS']").bind("click", function() {
                    $("a[href='#xds']").attr("data-action", "1");
                    var idx = $(this).attr("href").substr(7);
                    var DSs = [];
                    $.ajax({
                        url: "getDSbyType.action",
                        async: false,
                        data: {dstypeId: idx},
                        dataType: "json",
                        success: function(data) {
                            DSs = data;
                        },
                    });
                    $("#DSList2").empty();
                    $("#DSList2").append('<div class="col-sm-1"></div> <div class="col-sm-9"> <div class="input-group"> <select id="insinfo2" type="text" class="form-control"></select> <span class="input-group-btn"> <button id="getinsBtn2" type="button" class="btn btn-success btn-sm"> 更新实例 <span class="ace-icon fa fa-check icon-on-right bigger-110"></span> </button> </span> </div> </div> <br /><hr />');
                    for(var i = 0; i < DSs.length; i ++) {
                        var s = '<button class="btn btn-white btn-info btn-round" style="width: 32%;" href="#DS';
                        s += DSs[i].id + '">';
                        s += DSs[i].name;
                        s += '</button>';
                        $("#DSList2").append(s);
                    }
                    
                    $("#DSList1").hide();
                    $("#DSList2").fadeIn();
                    $("#insinfo2").bind("mouseover", function() {
                        $("#insinfo2").empty();
                        docs = $(window.frames["ispGraph"].document).find(".GooFlow_item");
                        for(var i = 0; i < docs.length; i ++) {
                            if(docs.eq(i).find("b").attr("class") == "ico_fork") {
                                
                                var s = '<option value="' + docs.eq(i).attr("id") + '">' + docs.eq(i).find("td").eq(1).text() + '</option>';
                                $("#insinfo2").append(s)
                            }
                        }
                    });
                    

                    $("button[href^='#DS']").bind("click", function() {
                        var idx = $(this).attr("href").substr(3);
                        var name = $(this).text();
                        $.ajax({
                            url: "DsDef.action",
                            type: "post",
                            traditional: true,
                            data: {
                                dsId: idx,
                            },
                            dataType: "json",
                            success: function(data) {
                                var idx = $("#insinfo2").val();
                                $(window.frames["ispGraph"].document).find("#" + idx).find("td").eq(1).text(name + "#" + data);
                            },
                        });
                    });
                });

                $("a[href='#xds']").bind("click", function() {
                    if($(this).attr("data-action") == "1") {
                        $("#DSList2").hide();
                        $("#DSList1").fadeIn();
                        $(this).attr("data-action", "0");
                    }
                });
            }
            
          
            function getClickedDA(idx) {
                $("#startAttrs").html('<div class="from-group"><div class="col-md-12"><button class="btn btn-info btn-block" href="#add-0">添加触发条件</button></div></div>');
                $("#stopAttrs").html('<div class="from-group"><div class="col-md-12"><button class="btn btn-info btn-block" href="#add-1">添加触发条件</button></div></div>');
                $("#startAttrsx").html('');
                $("#stopAttrsx").html('');
                
                var attr = [];
                var t = '';
                $.ajax({
                    async: false,
                    url: "getDAbyId.action",
                    type: "post",
                    dataType: "json", 
                    data: {daId: idx > 0? idx: 0 - idx},
                    success: function(data) {
                        data = data[0];
                        $("option[value='" + data.triggertype + "']").attr("selected", true);
                        $("#starttime2").val(data.planStart.split(' ')[0])
                        $("#starttime").val(data.planStart.split(' ')[1])
                        $("#endtime2").val(data.planEnd.split(' ')[0])
                        $("#endtime").val(data.planEnd.split(' ')[1])
                        $("#autotype").val(data.ifAuto);
                        //$("#startAttrs").empty();
                        attr = data.attribute;
                        for(var i = 0; i < attr.length; i ++) {
                            $.ajax({
                                async: false,
                                url: "getAttrbyId.action",
                                type: "post",
                                data: { atrId: attr[i] },
                                success: function(data) {
                                    data = data[0];
                                    t += '<option value="' + data.id + '">' + data.name + '</option>';
                                }
                            });
                        }
                    }
                });
                $("button[href^='#add-']").bind("click", function(){
                    var x = $(this).attr("href").substr(5)
                        // $("a[href^='#add-']").off("click");
                        // $(this).children("i").removeClass("green fa-plus-circle").addClass("red fa-times-circle");
                    // $(this).attr("href", "#del");
                    var s = '<div class="form-group"> <div class="col-sm-12"> <div class="input-group"> <select class="form-control" style="width: 40">' + t;
                    s += '</select><span class="input-group-addon" style="padding: 6px 0px; border: 0"></span>';
                    s += '<select class="form-control" style="width: 20">';
                    s += '<option value="1">=</option><option value="2">&gt;</option><option value="3">&lt;</option><option value="4">&ge;</option><option value="5">&le;</option>'
                    s += '</select><span class="input-group-addon" style="padding: 6px 0px; border: 0"></span><input type="text" class="form-control vlholder" />';
                    s += '<span class="input-group-addon" style="padding: 6px 0px; border: 0"></span><select class="form-control" style="width: 40">';
                    s += '<option value="1">and&nbsp;</option><option value="2">or&nbsp;</option></select>';
                    s += '<span class="input-group-addon"><a href="#del"><i class="ace-icon fa fa-times-circle red"></i></a></span></div></div></div>';
                    
                    if(x == 0)
                        $("#startAttrs").prepend(s);
                    else 
                        $("#stopAttrs").prepend(s);
                    $("a[href='#del']").on("click", function() {
                        $(this).parent().parent().parent().parent().remove();
                    });
                    // $("a[href^='#add-']").click(fn);
                });
                for(var j = 0; j < 2; j ++) {
                    // var s = '<div class="form-group"> <div class="col-sm-12"> <div class="input-group"> <select class="form-control" style="width: 40">' + t;
                    // s += '</select><span class="input-group-addon" style="padding: 6px 0px; border: 0"></span>';
                    // s += '<select class="form-control" style="width: 20">';
                    // s += '<option value="1">=</option><option value="2">&gt;</option><option value="3">&lt;</option><option value="4">&ge;</option><option value="5">&le;</option>'
                    // s += '</select><span class="input-group-addon" style="padding: 6px 0px; border: 0"></span><input type="text" class="form-control vlholder" />';
                    // s += '<span class="input-group-addon" style="padding: 6px 0px; border: 0"></span><select class="form-control" style="width: 40">';
                    // s += '<option value="1">and&nbsp;</option><option value="2">or&nbsp;</option></select>';
                    // s += '<span class="input-group-addon"><a href="#add-' + j + '"><i class="ace-icon fa fa-plus-circle green"></i></a></span></div></div></div>'; 
                    // var s = '<div class="from-group"><div class="col-md-12"><button class="btn btn-info btn-block" href="#add-' + j + '">添加触发条件</button></div></div>';
                    // if(j == 0)
                    //     $("#startAttrs").html(s);
                    // else
                    //     $("#stopAttrs").html(s); 
                    
                    
                    if(idx > 0) {
                        $.ajax({
                            async: false,
                            url: "getTriggersbyId.action",
                            type: "post",
                            data: { daId: idx, status: j },
                            success: function(data) {
                                // data = data[0];
                                // var attrid = data.attrid.split('#');
                                // var symbs = data.symbs.split('#');
                                // var vals = data.vals.split('#');
                                // var logics = data.logics.split('#');
                                // attrid += '#' + x.eq(i).find("select").eq(0).val();
                                // symbs += '#' + x.eq(i).find("select").eq(1).val();
                                // vals += '#' + x.eq(i).find("input").eq(0).val();
                                // logic += '#' + x.eq(i).find("select").eq(2).val();
                                s = '';
                                for(var i = 0; i < data.length; i ++) {
                                    s += '<div class="form-group"> <div class="col-sm-12"> <div class="input-group"> <select class="form-control" style="width: 40">' + t;
                                    s += '</select><span class="input-group-addon" style="padding: 6px 0px; border: 0"></span>';
                                    s += '<select class="form-control" style="width: 20">';
                                    s += '<option value="1">=</option><option value="2">&gt;</option><option value="3">&lt;</option><option value="4">&ge;</option><option value="5">&le;</option>'
                                    s += '</select><span class="input-group-addon" style="padding: 6px 0px; border: 0"></span><input type="text" class="form-control" />';
                                    s += '<span class="input-group-addon" style="padding: 6px 0px; border: 0"></span><select class="form-control" style="width: 40">';
                                    s += '<option value="1">and&nbsp;</option><option value="2">or&nbsp;</option></select>';
                                    s += '<span class="input-group-addon"><a href="#del"><i class="ace-icon fa fa-times-circle red"></i></a></span></div></div></div>'; 
                                }
                                if(j == 0) {
                                    $("#startAttrs").prepend(s);
                                    x = $("#startAttrs").children();
                                    for(var i = 0; i < x.length; i ++) {
                                        x.eq(i).find("select").eq(0).find("[value='" + data[i].attrid + "']").attr("selected", true);
                                        x.eq(i).find("select").eq(1).find("[value='" + data[i].symbs + "']").attr("selected", true);
                                        x.eq(i).find("select").eq(2).find("[value='" + data[i].logics + "']").attr("selected", true);
                                        x.eq(i).find("input").eq(0).val(data[i].vals);
                                        var st = data[i].st + ' ';
                                        $("#startAttrsx").prepend('<div class="form-group"><div class="col-sm-12"><h4 style="margin-left: 40px;">' + data[i].st + '</h4></div><div>')
                                    }
                                    
                                    
                                }
                                    
                                else {
                                    $("#stopAttrs").prepend(s);
                                    x = $("#stopAttrs").children();
                                    for(var i = 0; i < x.length; i ++) {
                                        x.eq(i).find("select").eq(0).find("[value='" + data[i].attrid + "']").attr("selected", true);
                                        x.eq(i).find("select").eq(1).find("[value='" + data[i].symbs + "']").attr("selected", true);
                                        x.eq(i).find("select").eq(2).find("[value='" + data[i].logics + "']").attr("selected", true);
                                        x.eq(i).find("input").eq(0).val(data[i].vals);
                                        var st = data[i].st + ' ';
                                        $("#stopAttrsx").prepend('<div class="form-group"><div class="col-sm-12"><h4 style="margin-left: 40px;">' + data[i].st + '</h4></div><div>')
                                    }
                                }
                                
                            }
                            
                        });
                    }
                    $("a[href='#del']").on("click", function() {
                        $(this).parent().parent().parent().parent().remove();
                    });
                }
                
            }
            
            
            function getCurrentDA() {
                var ins = $(window.frames["ispGraph"].document).find("#demo").attr("data-focus");
                if(ins.split('#').length == 2) {
                    var idx = ins.split('#')[1];
                    var name = ins.split('#')[0];
                    $("#insinfo").empty();
                    var docs = $(window.frames["ispGraph"].document).find(".GooFlow_item");
                    for(var i = 0; i < docs.length; i ++) {
                        if(docs.eq(i).find("b").attr("class") == "ico_task") {
                            var s = '<option value="' + docs.eq(i).attr("id") + '">' + docs.eq(i).find("td").eq(1).text() + '</option>';
                            $("#insinfo").append(s)
                        }
                    }
                    $("option[value='" + name + "']").attr("selected", true);
                    $("a[href='#xda']").attr("data-action", "2");
                    $("#DAList3").attr("data-focus", "-" + idx);
                    $("#daname").val(name);
                    $(window.frames["ispGraph"].document).find("#demo").attr("data-focus", "-1");
                    $("#DAList1").hide();
                    $("#DAList2").hide();
                    $("#DAList3").fadeIn();
                    getClickedDA(idx);
                }
            }
            

            $(document).ready(function() {
                loadSideBar();
                loadDARelatives();
                loadDSRelatives();
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
                $("#newBtn").bind("click", function() {
                    $("#confBtn").attr("data-focus", "-1");
                    $("#ispname").val("");
                    $("#ispdesc").val("");
                });

                $("#confBtn").bind("click", function() {
                    if($(this).attr("data-focus") == "-1") {
                        $.ajax({
                            url: "insertISP.action",
                            async: false,
                            data: {
                                name: $("#ispname").val(),
                                description: $("#ispdesc").val(),
                                priority: 1,
                                ifdef: 0,
                                ifAuto: 1,
                                state: "CheckPending",
                                triggertype: "",
                            },
                            dataType: "json",
                            type: "post",
                            success: function(data) {
                                $("#ispGraph").attr("src", "ispGraph_.jsp");
                            },
                        });
                    }
                });

                $("#submBtn").bind("click", function() {
                    var idx = $("#confBtn").attr("data-focus");
                    $.ajax({
                        url: "IspDef.action",
                        data: {
                            ispId: idx, 
                            name: $("#ispname").val(),
                            desc: $("#ispdesc").val(),
                            time: $("#starttimex").val()
                        },
                        type: 'post',
                        dataType: "json",
                        success: function(data) {
                            alert("实例化完成");
                            loadSideBar();
                        }
                    });
                });
                
                $("#watchBtn").bind("click", function() {
                    if($(this).attr("data-focus") == "")
                        return;
                     $("#watchBtn").attr("style","display: none;")
                    $("#returnBtn").attr("style","display: inline;")
                    $("#typex").text($(this).find("span").text());               
//                     alert($("#apprBtn").attr("data-focus"));  
                    var idx = $("#confBtn").attr("data-focus");
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
       
                     $("#returnBtn").bind("click", function() {
	                    if($(this).attr("data-focus") == "")
	                        return;
	                  $("#watchBtn").attr("style","display:  inline;")
                    $("#returnBtn").attr("style","display: none;")
	                    $("#typex").text($(this).find("span").text());               
	                    var idx = $("#confBtn").attr("data-focus");
	                    $.ajax({
	                        url: "getISPbyId.action",
	                        data: {ispId: idx},
	                        dataType: "json",
	                        success: function(data) {
	                            data = data[0];
	                            $("#ispGraph").attr("src", "ispGraph_.jsp");//!@#ispGraph2_
	                            $("#ispname").val(data.name);
	                            $("#ispdesc").val(data.description);
	                        },
	                    });
	                });
                
                
                
                
                
                setInterval(getCurrentDA, 1000);
            });
        </script>

        <!-- inline scripts related to this page -->
    </body>
</html>
