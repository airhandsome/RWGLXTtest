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
        <link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
        <link rel="stylesheet" href="assets/css/jquery.gritter.min.css" />
        <link rel="stylesheet" href="assets/css/select2.min.css" />
        <link rel="stylesheet" href="assets/css/bootstrap-editable.min.css" />

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
                            <a href="maintain_.jsp">
                                <i class="ace-icon fa fa-users"></i>
                                <span>基础配置维护</span>
                            </a>
                        </li>
                        <li>
                            <a href="taskDA_.jsp">
                                <i class="ace-icon fa fa-cog"></i>
                                <span>原子任务(DA)管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="taskDS_.jsp">
                                <i class="ace-icon fa fa-random"></i>
                                <span>条例(DS)管理</span>
                            </a>
                        </li>
                        <!-- <li>
                            <a href="taskCA_.jsp">
                                <i class="ace-icon fa fa-cogs"></i>
                                <span>复合任务(CA)管理</span>
                            </a>
                        </li> -->
                        <li>
                            <a href="taskISP_.jsp">
                                <i class="ace-icon fa fa-anchor"></i>
                                <span>任务计划(ISP)管理</span>
                            </a>
                        </li>
                        <li>
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
                        <i class="ace-icon fa fa-users"></i>
                        <span class="menu-text"> 基础配置维护 </span>
                    </div>
                </div><!-- /.sidebar-shortcuts -->

                <ul class="nav nav-list">
                    <li class="">
                        <a href="#boxUser">
                            <i class="menu-icon fa fa-user"></i>
                            <span class="menu-text"> 用户管理 </span>
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="#boxFP">
                            <i class="menu-icon fa fa-binoculars"></i>
                            <span class="menu-text"> 站位管理 </span>
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="#boxSystem">
                            <i class="menu-icon fa fa-sitemap"></i>
                            <span class="menu-text"> 系统管理 </span>
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="#boxAttr">
                            <i class="menu-icon fa fa-tags"></i>
                            <span class="menu-text"> 测点管理 </span>
                        </a>

                        <b class="arrow"></b>
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
                            <li>基础配置管理</li>
                            <li id="typex">用户管理</li>
                        </ul><!-- /.breadcrumb -->
                    </div>

                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="row">
                                    <div class="space"></div>
                                    <div class="col-sm-12">

                                        <div id="boxUser" class="widget-box widget-color-blue2">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-user"></i>
                                                    人员列表
                                                </h5>
                                                <div class="pull-right">
                                                    <form class="pull-right form-search">
                                                        <span class="input-icon">
                                                            <input type="text" class="nav-search-input" id="userSearch" autocomplete="off">
                                                            <i class="ace-icon fa fa-search nav-search-icon"></i>
                                                        </span>
                                                    </form>
                                                </div>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main no-padding" style="height: 750px; overflow-y: scroll;">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead class="thin-border-bottom">
                                                            <tr>
                                                                <th>用户名</th>
                                                                <th>口令</th>
                                                                <th>部门</th>
                                                                <th>职位</th>
                                                                <th>角色</th>
                                                                <th>战位</th>
                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="userList">
                                                            
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="widget-toolbox clearfix">
                                                    <button id="addUserBtn" class="btn btn-xs btn-success pull-right">
                                                        <span class="bigger-110">添加用户</span>
                                                        <i class="ace-icon fa fa-plus icon-on-right"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>

                                        <div id="boxFP" id="flist" class="widget-box widget-color-blue2" style="display: none;">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-binoculars"></i>
                                                    站位列表
                                                </h5>
                                                <div class="pull-right">
                                                    <form class="pull-right form-search">
                                                        <span class="input-icon">
                                                            <input type="text" class="nav-search-input" id="FPSearch" autocomplete="off">
                                                            <i class="ace-icon fa fa-search nav-search-icon"></i>
                                                        </span>
                                                    </form>
                                                </div>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main no-padding" style="height: 750px; overflow-y: scroll;">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead class="thin-border-bottom">
                                                            <tr>
                                                                <th>站位名称</th>
                                                                <th>部门</th>
                                                                <th>职位</th>
                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="fightList">
                                                            <tr>

                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <div id="boxSystem" class="widget-box widget-color-blue2" style="display: none;">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-sitemap"></i>
                                                    系统列表
                                                </h5>
                                                <div class="pull-right">
                                                    <form class="pull-right form-search">
                                                        <span class="input-icon">
                                                            <input type="text" class="nav-search-input" id="systemSearch" autocomplete="off">
                                                            <i class="ace-icon fa fa-search nav-search-icon"></i>
                                                        </span>
                                                    </form>
                                                </div>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main no-padding" style="height: 750px; overflow-y: scroll;">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead class="thin-border-bottom">
                                                            <tr>
                                                                <th>系统名称</th>
                                                                <th>父系统</th>
                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="systemList">
                                                            <tr>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>

                                            </div>
                                        </div>

                                        <div id="boxAttr" class="widget-box widget-color-blue2" style="display: none;">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-sitemap"></i>
                                                    测点列表
                                                </h5>
                                                <div class="pull-right">
                                                    <form class="pull-right form-search">
                                                        <span class="input-icon">
                                                            <input type="text" class="nav-search-input" id="attrSearch" autocomplete="off">
                                                            <i class="ace-icon fa fa-search nav-search-icon"></i>
                                                        </span>
                                                    </form>
                                                </div>
                                            </div> 
                                            
                                            <div class="widget-body">
                                                <div class="widget-main no-padding row" style="height: 750px;">
                                                    <div class="col-md-2">
                                                        <ul id="tree"></ul>
                                                    </div>
                                                    <div id="sys3" class="col-md-10 tab-content no-padding" style="height: 750px; overflow-y: scroll;">
                                                    </div>
                                                </div>

                                            </div>
                                            <form action="uploadExc.action" method = "post" enctype="multipart/form-data">
                                                <input type="file" class="form-control" accept="application/vnd.ms-excel" value="上传" name="attrfile"/>
                                                <input type="submit" class="form-control" value="submit"/>
                                            </form>
                                            <script type="text/javascript">
                                                var $assets = "dist";//this will be used in fuelux.tree-sampledata.js
                                            </script>

                                                                
                                            <!--<div class="tabbale tab-left">
                                                <ul class="nav nav-tabs" id="systemListx"></ul>
                                                <div id="sys3" class="tab-content no-padding" style="height: 750px; overflow-y: scroll;">
                                                </div>
                                            </div>-->
                                            <!--<div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-tags"></i>
                                                    测点列表
                                                </h5>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main no-padding" style="height: 750px; overflow-y: scroll;">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead class="thin-border-bottom">
                                                            <tr>
                                                                <th>测点名称</th>
                                                                <th>所属系统</th>
                                                                <th>测点标签</th>
                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="attrList">
                                                            <tr>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>-->
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.page-content -->
                </div>
            </div><!-- /.main-content -->

            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
            </a>
        </div><!-- /.main-container -->
        <div style="display: none;">
            <div id="xfplist"></div>
            <div id="xsystemlist"></div>
            <div id="ysystemlist"></div>
        </div>
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
        <script src="assets/js/jquery.gritter.min.js"></script>
        <script src="assets/js/bootbox.min.js"></script>
        <script src="assets/js/jquery.easypiechart.min.js"></script>
        <script src="assets/js/bootstrap-datepicker.min.js"></script>
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
        <script src="assets/js/fuelux.tree.min.js"></script>

        <!-- ace scripts -->
        <script src="assets/js/ace-editable.min.js"></script>
        <script src="assets/js/ace-elements.min.js"></script>
        <script src="assets/js/ace.min.js"></script>
        <script type="text/javascript">

            

            function loadFPList(search) {
                var action = "getFP.action"
                var dic = {}
                if(search != '') {
                    dic = {strname: search};
                    action = "getFPbySearch.action";
                }
                $.ajax({
                    url: action,
                    data: dic,
                    dataType: "json",
                    type: 'post',
                    success: function(data) {
                        var s = '<select class="form-control input-xs">';
                        for(var i = 0; i < data.length; i ++) {
                            s += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                        }
                        s += '</select>';
                        $("#xfplist").html(s);

                        $("#fightList").empty();
                        for(var i = 0; i < data.length; i ++) {
                            var s = '<tr data-id="' + data[i].id + '">';
                            s += ' <td class="xinput">' + data[i].name + '</td>';
                            s += ' <td class="xinput">' + data[i].department + '</td>';
                            s += ' <td class="xinput">' + data[i].post + '</td>';
                            s += ' <td> <div class="btn-group">';
                            s += ' <button class="editFP btn btn-success btn-xs"> <i class="ace-icon fa fa-pencil bigger-120"></i> </button>';
                            s += ' <button class="saveFP btn btn-info btn-xs" style="display: none;"> <i class="ace-icon fa fa-check bigger-120"></i> </button>';
                            s += ' <button class="rmFP btn btn-danger btn-xs"> <i class="ace-icon fa fa-trash-o bigger-120"></i> </button> </div> </td></tr>';
                            $("#fightList").append(s);
                        }

                        $(".editFP").bind("click", function() {
                            $(this).hide();
                            $(this).siblings(".saveFP").show();
                            var x = $(this).parents("td");
                            for(var i = 0; i < x.siblings(".xinput").length; i ++) {
                                var y = x.siblings(".xinput").eq(i);
                                var s = y.text();
                                y.html('<input class="form-control input-xs" />');
                                y.children("input").val(s);
                            }
                        });

                        $(".saveFP").bind("click", function() {
                            $(this).hide();
                            $(this).siblings(".editFP").show();
                            var x = $(this).parents("td");
                            for(var i = 0; i < x.siblings(".xinput").length; i ++) {
                                var y = x.siblings(".xinput").eq(i);
                                y.text(y.children("input").val());
                            }
                            $.ajax({
                                url: "updateFP.action",
                                type: "post",
                                data: {
                                    fightpostionId: $(this).parents("tr").attr("data-id"),
                                    name: x.siblings(".xinput").eq(0).text(),
                                    department: x.siblings(".xinput").eq(1).text(),
                                    post: x.siblings(".xinput").eq(2).text(),
                                },
                                dataType: "json",
                                success: function(data) {
                                    alert("战位更新完成");
                                },
                            });
                        });

                        $(".rmFP").bind("click", function() {
                            $.ajax({
                                url: "deleteFP.action",
                                data: {fightpostionId: $(this).parents("tr").attr("data-id")},
                                dataType: "json",
                                success: function(data) {
                                    alert("战位删除完成");
                                    loadFPList();
                                },
                            });
                        });
                    },
                });
            }

            function loadUserList(search) {
                var action = "getUser.action"
                var dic = {}
                if(search != '') {
                    dic = {strname: search};
                    action = "getUserbySearch.action";
                }
                $.ajax({
                    url: action,
                    data: dic,
                    dataType: "json",
                    type: 'post',
                    success: function(data) {
                        $("#userList").empty();
                        for(var i = 0; i < data.length; i ++) {
                            var s = '<tr data-id="' + data[i].id + '">';
                            s += ' <td class="xinput">' + data[i].username + '</td>';
                            s += ' <td class="xinput">' + data[i].password + '</td>';
                            s += ' <td class="xinput">' + data[i].department + '</td>';
                            s += ' <td class="xinput">' + data[i].post + '</td>';
                            s += ' <td class="xinput">' + data[i].role + '</td>';
                            s += ' <td class="xselect" data-value="' + data[i].positionId + '">' + data[i].positionName + '</td>';
                            s += ' <td> <div class="btn-group">';
                            s += ' <button class="editUser btn btn-success btn-xs"> <i class="ace-icon fa fa-pencil bigger-120"></i> </button>';
                            s += ' <button class="saveUser btn btn-info btn-xs" style="display: none;"> <i class="ace-icon fa fa-check bigger-120"></i> </button>';
                            s += ' <button class="rmUser btn btn-danger btn-xs"> <i class="ace-icon fa fa-trash-o bigger-120"></i> </button> </div> </td></tr>';
                            $("#userList").append(s);
                        }

                        $(".editUser").bind("click", function() {
                            $(this).hide();
                            $(this).siblings(".saveUser").show();
                            var x = $(this).parents("td");
                            for(var i = 0; i < x.siblings(".xinput").length; i ++) {
                                var y = x.siblings(".xinput").eq(i);
                                var s = y.text();
                                y.html('<input class="form-control input-xs" />');
                                y.children("input").val(s);
                            }
                            y = x.siblings(".xselect").eq(0);
                            y.html($("#xfplist").html());
                            y.children("select").val(y.attr("data-value"));
                        });

                        $(".saveUser").bind("click", function() {
                            $(this).hide();
                            $(this).siblings(".editUser").show();
                            var x = $(this).parents("td");
                            for(var i = 0; i < x.siblings(".xinput").length; i ++) {
                                var y = x.siblings(".xinput").eq(i);
                                y.text(y.children("input").val());
                            }
                            y = x.siblings(".xselect").eq(0);
                            y.attr("data-value", y.children("select").val());
                            y.text(y.find("option:selected").text());
                            $.ajax({
                                url: "updateUser.action",
                                type: "post",
                                data: {
                                    usersId: $(this).parents("tr").attr("data-id"),
                                    username: x.siblings(".xinput").eq(0).text(),
                                    password: x.siblings(".xinput").eq(1).text(),
                                    department: x.siblings(".xinput").eq(2).text(),
                                    post: x.siblings(".xinput").eq(3).text(),
                                    role: x.siblings(".xinput").eq(4).text(),
                                    positionId: x.siblings(".xselect").eq(0).attr("data-value"),
                                },
                                dataType: "json",
                                success: function(data) {
                                    alert("人员更新完成");
                                },
                            });
                        });

                        $(".rmUser").bind("click", function() {
                            $.ajax({
                                url: "deleteUser.action",
                                data: {usersId: $(this).parents("tr").attr("data-id")},
                                dataType: "json",
                                success: function(data) {
                                    alert("人员删除完成");
                                    loadUserList();
                                },
                            });
                        });
                    },
                });
            }

            function insertSystem(idx, namex, idy, namey) {
                var s = '<tr data-id="' + idx + '">';
                s += ' <td class="xinput">' + namex + '</td>';
                s += ' <td class="xselect" data-value="' + idy + '">' + namey + '</td>';
                s += ' <td> <div class="btn-group">';
                s += ' <button class="editSys btn btn-success btn-xs"> <i class="ace-icon fa fa-pencil bigger-120"></i> </button>';
                s += ' <button class="saveSys btn btn-info btn-xs" style="display: none;"> <i class="ace-icon fa fa-check bigger-120"></i> </button>';
                s += ' <button class="rmSys btn btn-danger btn-xs"> <i class="ace-icon fa fa-trash-o bigger-120"></i> </button> </div> </td></tr>';
                $("#systemList").append(s);
            }

            function loadSystemList(search) {
                var system = [];
                $.ajax({
                    url: "getSystem.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        system = data;
                    },
                });

                var system2 = [];
                for(var i = 0; i < system.length; i ++) {
                    $.ajax({
                        url: "getSystembyId.action",
                        async: false,
                        data: {sysId: system[i].id},
                        dataType: "json",
                        success: function(data) {
                            system2[i] = data;
                        },
                    });
                }

                var system3 = [];
                for(var i = 0; i < system.length; i ++) {
                    system3[i] = [];
                    for(var j = 0; j < system2[i].length; j ++) {
                        $.ajax({
                            url: "getSystembyId.action",
                            async: false,
                            data: {sysId: system2[i][j].id},
                            dataType: "json",
                            success: function(data) {
                                system3[i][j] = data;
                            },
                        });
                    }
                }

                var s = '<select class="form-control input-xs"> <option value="-1">---</option>';
                var t = '<select class="form-control input-xs">';
                for(var i = 0; i < system.length; i ++) {
                    s += '<option value="' + system[i].id + '">' + system[i].name + '</option>';
                    for(var j = 0; j < system2[i].length; j ++) {
                        s += '<option value="' + system2[i][j].id + '">' + system2[i][j].name + '</option>';
                        for(var k = 0; k < system3[i][j].length; k ++) {
                            t += '<option value="' + system3[i][j][k].id + '">' + system3[i][j][k].name + '</option>';
                        }
                    }
                }
                s += '</select>';
                t += '</select>';
                $("#xsystemlist").html(s);
                $("#ysystemlist").html(t);
                $("#systemList").empty();
                for(var i = 0; i < system.length; i ++) {
                    if((system[i].name).indexOf(search) != -1)
                        insertSystem(system[i].id, system[i].name, -1, '---');
                    for(var j = 0; j < system2[i].length; j ++) {
                        if((system2[i][j].name).indexOf(search) != -1)
                            insertSystem(system2[i][j].id, system2[i][j].name, system[i].id, system[i].name);
                        for(var k = 0; k < system3[i][j].length; k ++) {
                            if((system3[i][j][k].name).indexOf(search) != -1)
                                insertSystem(system3[i][j][k].id, system3[i][j][k].name, system2[i][j].id, system2[i][j].name);
                        }
                    }
                }

                $(".editSys").bind("click", function() {
                    $(this).hide();
                    $(this).siblings(".saveSys").show();
                    var x = $(this).parents("td");
                    for(var i = 0; i < x.siblings(".xinput").length; i ++) {
                        var y = x.siblings(".xinput").eq(i);
                        var s = y.text();
                        y.html('<input class="form-control input-xs" />');
                        y.children("input").val(s);
                    }
                    y = x.siblings(".xselect").eq(0);
                    y.html($("#xsystemlist").html());
                    y.children("select").val(y.attr("data-value"));
                });

                $(".saveSys").bind("click", function() {
                    $(this).hide();
                    $(this).siblings(".editSys").show();
                    var x = $(this).parents("td");
                    for(var i = 0; i < x.siblings(".xinput").length; i ++) {
                        var y = x.siblings(".xinput").eq(i);
                        y.text(y.children("input").val());
                    }
                    y = x.siblings(".xselect").eq(0);
                    y.attr("data-value", y.children("select").val());
                    y.text(y.find("option:selected").text());
                    $.ajax({
                        url: "updateSys.action",
                        type: "post",
                        data: {
                            sysId: $(this).parents("tr").attr("data-id"),
                            sysname: x.siblings(".xinput").eq(0).text(),
                            parent: x.siblings(".xselect").eq(0).attr("data-value"),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("系统更新完成");
                        },
                    });
                });
            }

            function loadAttrList() {
                var t = $("#ysystemlist").find("option");
                for(var i = 0; i < t.length; i ++) {
                    var attr = [];
                    $.ajax({
                        async: false,
                        url: "getAtrBySystem.action",
                        data: {systemId: t.eq(i).attr("value")},
                        dataType: "json",
                        success: function(data) {
                            attr = data;
                            var s = '<div id="xx' + t.eq(i).attr("value") + '" class="tab-pane">';
                            s += '<table class="table table-striped table-bordered table-hover">';
                            s += '<thead class="thin-border-bottom">';
                            s += '<tr><th>测点名称</th><th>所属系统</th><th>测点标签</th><th></th></tr>';
                            s += '</thead><tbody id="xxx';
                            s += t.eq(i).attr("value") + '"><tr></tr></tbody></table>';
                            $("#sys3").append(s);
                        },
                    });
                    for(var j = 0; j < attr.length; j ++) {
                        var s = '<tr data-id="' + attr[j].id + '">';
                        s += ' <td class="xinput">' + attr[j].name + '</td>';
                        s += ' <td class="xselect" data-value="' + t.eq(i).attr("value") + '">' + t.eq(i).text() + '</td>';
                        s += ' <td class="xinput">' + attr[j].tag + '</td>';
                        s += ' <td> <div class="btn-group">';
                        s += ' <button class="editAttr btn btn-success btn-xs"> <i class="ace-icon fa fa-pencil bigger-120"></i> </button>';
                        s += ' <button class="saveAttr btn btn-info btn-xs" style="display: none;"> <i class="ace-icon fa fa-check bigger-120"></i> </button>';
                        s += ' <button class="rmAttr btn btn-danger btn-xs"> <i class="ace-icon fa fa-trash-o bigger-120"></i> </button> </div> </td></tr>';
                        $("#xxx" + t.eq(i).attr("value")).append(s);
                    }
                }

                $(".editAttr").bind("click", function() {
                    $(this).hide();
                    $(this).siblings(".saveAttr").show();
                    var x = $(this).parents("td");
                    for(var i = 0; i < x.siblings(".xinput").length; i ++) {
                        var y = x.siblings(".xinput").eq(i);
                        var s = y.text();
                        y.html('<input class="form-control input-xs" />');
                        y.children("input").val(s);
                    }
                    y = x.siblings(".xselect").eq(0);
                    y.html($("#ysystemlist").html());
                    y.children("select").val(y.attr("data-value"));
                });

                $(".saveAttr").bind("click", function() {
                    $(this).hide();
                    $(this).siblings(".editAttr").show();
                    var x = $(this).parents("td");
                    for(var i = 0; i < x.siblings(".xinput").length; i ++) {
                        var y = x.siblings(".xinput").eq(i);
                        y.text(y.children("input").val());
                    }
                    y = x.siblings(".xselect").eq(0);
                    y.attr("data-value", y.children("select").val());
                    y.text(y.find("option:selected").text());
                    $.ajax({
                        url: "updateAtr.action",
                        type: "post",
                        data: {
                            atrId: $(this).parents("tr").attr("data-id"),
                            name: x.siblings(".xinput").eq(0).text(),
                            systemId: x.siblings(".xselect").eq(0).attr("data-value"),
                            tag: x.siblings(".xinput").eq(1).text(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("测点更新完成");
                        },
                    });
                })
            }
            
            function loadSystemx() {
                var tree = {};
                var system = [];
                $.ajax({
                    url: "getSystem.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        system = data;
                        for(var i = 0; i < data.length; i ++) {
                            tree[i] = {text: data[i].name, type: 'folder'};
                            console.log(tree);
                        }
                    },
                });

                var system2 = [];
                for(var i = 0; i < system.length; i ++) {
                    $.ajax({
                        url: "getSystembyId.action",
                        async: false,
                        data: {sysId: system[i].id},
                        dataType: "json",
                        success: function(data) {
                            system2[i] = data;
                            tree[i]['additionalParameters'] = {'children': []};
                            for(var j = 0; j < data.length; j ++) {
                                tree[i]['additionalParameters']['children'][j] = {text: data[j].name, type: 'folder'};
                            }
                        },
                    });
                }

                var system3 = [];
                for(var i = 0; i < system.length; i ++) {
                    system3[i] = [];
                    for(var j = 0; j < system2[i].length; j ++) {
                        $.ajax({
                            url: "getSystembyId.action",
                            async: false,
                            data: {sysId: system2[i][j].id},
                            dataType: "json",
                            success: function(data) {
                                system3[i][j] = data;
                                tree[i]['additionalParameters']['children'][j]['additionalParameters']= {'children': []};
                                for(var k = 0; k < data.length; k ++) {
                                    tree[i]['additionalParameters']['children'][j]['additionalParameters']['children'][k] = 
                                    {text: '<a href="#xx' + data[k].id + '" data-toggle="tab">' + data[k].name + '</a>', type: 'item'};
                                }
                            },
                        });
                    }
                }
                
                return tree;
               
                // $("#systemListx").empty();
                // for(var i = 0; i < system.length; i ++) {
                //     var s = '<li class="dropdown"> <a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">';
                //     s += system[i].name;
                //     s += '&nbsp; <i class="ace-icon fa fa-caret-down bigger-110 width-auto"></i> </a> <ul class="dropdown-menu">';
                //     for(var j = 0; j < system2[i].length; j ++) {
                //         s += '<li class="dropdown dropdown-hover"> <a href="#" class="clearfix"> <span class="pull-left">';
                //         s += system2[i][j].name;
                //         s += '</span> <i class="ace-icon fa fa-caret-right pull-right"></i> </a> <ul class="dropdown-menu">';
                //         for(var k = 0; k < system3[i][j].length; k ++) {
                //             s += '<li> <a href="#xx';
                //             s += system3[i][j][k].id;
                //             s += '" data-toggle="tab">';
                //             s += system3[i][j][k].name;
                //             s += '</a> </li>';
                //         }
                //         s += '</ul> </li>';
                //     }
                //     s += '</ul> </li>';
                //     $("#systemListx").append(s);
                // }
                // $("a[href^='#xx']").bind("click", function() {
                //     $("#systemListx").find("li.active").removeClass("active");
                // });
            }
            
            function initData(tree) {
                var tree_data = tree;
                var dataSource = function(options, callback){
                    var $data = null
                    if(!("text" in options) && !("type" in options)){
                        $data = tree_data;//the root tree
                        callback({ data: $data });
                        return;
                    }
                    else if("type" in options && options.type == "folder") {
                        if("additionalParameters" in options && "children" in options.additionalParameters)
                            $data = options.additionalParameters.children || {};
                        else $data = {}//no data
                    }
                    
                    if($data != null)//this setTimeout is only for mimicking some random delay
                        setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);

                    //we have used static data here
                    //but you can retrieve your data dynamically from a server using ajax call
                    //checkout examples/treeview.html and examples/treeview.js for more info
                }
                return {'dataSource': dataSource};
            }
            
            $(document).ready(function() {
                
                //loadSystemx();
                loadFPList('');
                loadUserList('');
                loadSystemList('');
                loadAttrList();
                $('#tree').ace_tree({
                    dataSource: initData(loadSystemx())['dataSource'] ,
                    loadingHTML:'<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>',
                    'open-icon' : 'ace-icon fa fa-folder-open',
                    'close-icon' : 'ace-icon fa fa-folder',
                    'selectable' : true,
                    multiSelect: false,
                    'selected-icon' : 'ace-icon fa fa-check',
                    'unselected-icon' : null
                });

                // $("div[id^='box']").hide();
                // $("#boxUser").show();

                $("[href^='#box']").bind("click", function() {
                    $("div[id^='box']").hide();
                    $($(this).attr("href")).show();
                    $("#typex").text($(this).find("span").text());
                })

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

                $("#userSearch").change(function() {
                    loadUserList($(this).val());
                });
                $("#FPSearch").change(function() {
                    loadFPList($(this).val());
                });
                $("#systemSearch").change(function() {
                    loadSystemList($(this).val());
                })
            });
        </script>

        <!-- inline scripts related to this page -->
    </body>
</html>
