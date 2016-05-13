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
                        <li>
                            <a href="maintain_.jsp">
                                <i class="ace-icon fa fa-users"></i>
                                <span>基础配置维护</span>
                            </a>
                        </li>
                        <li class="light-blue">
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
                        <i class="ace-icon fa fa-cog"></i>
                        <span class="menu-text"> 原子任务(DA)管理 </span>
                    </div>
                </div><!-- /.sidebar-shortcuts -->

                <ul id="DAList" class="nav nav-list">


                </ul><!-- /.nav-list -->

            </div>

            <div class="main-content">
                <div class="main-content-inner">
                    <div class="breadcrumbs" id="breadcrumbs">
                        <script type="text/javascript">
                            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                        </script>

                        <ul class="breadcrumb">
                            <li>DA管理</li>
                            <li id="typex">--</li>
                            <li id="namex" class="active">--</li>
                        </ul><!-- /.breadcrumb -->

                        <button id="saveBtn" class="btn btn-success pull-right" data-daid="-1">
                            保存修改 &nbsp;
                            <i class="ace-icon fa fa-save"></i>
                        </button>
                        <button id="newBtn" class="btn btn-primary pull-right">
                            新建任务 &nbsp;
                            <i class="ace-icon fa fa-pencil"></i>
                        </button>
                    </div>

                    <div class="page-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="row">
                                    <div class="space"></div>
                                    <div class="col-sm-5">
                                        <div class="profile-user-info profile-user-info-striped">
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 任务名称 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="daname">---</span>
                                                </div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 任务描述 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="dadesc">---</span>
                                                </div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 所属类型 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="datype">---</span>
                                                </div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 执行站位 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="fptype">---</span>
                                                </div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 任务备注 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="dabz">---</span>
                                                </div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 外部通信协议 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="dacode">---</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="space"></div>
                                    </div>
                                    <div class="col-sm-7">
                                        <div class="tabbable">
                                            <ul class="nav nav-tabs" id="sysList"></ul>
                                            <div class="tab-content" style="height: 150px; overflow-y: scroll;">
                                                <div id="xattrList" class="tab-pane fade in active">
                                                </div>
                                            </div>
                                        </div>
                                        <!--<div class="widget-box widget-color-blue2">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-dashboard"></i>
                                                    任务关联测点
                                                </h5>
                                            </div>
                                            <div class="widget-body">
                                                <div id="xattrList" class="widget-main" style="height: 150px; overflow-y: scroll;">
                                                </div>
                                            </div>
                                        </div>-->
                                    </div>
                                </div>
                                <div class="hr hr-double hr-dotted hr18"></div>
                                <div class="space"></div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="tabbable">
                                            <ul class="nav nav-tabs" id="systemList">

                                            </ul>

                                            <div id="attrList" class="tab-content" style="height: 500px; overflow-y: scroll;">
                                                <div id="dropdowns" class="tab-pane fade in active">

                                                </div>
                                            </div>
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

        <!-- ace scripts -->
        <script src="assets/js/ace-editable.min.js"></script>
        <script src="assets/js/ace-elements.min.js"></script>
        <script src="assets/js/ace.min.js"></script>
        <script type="text/javascript">

            $('#daname').editable({type: 'text', name: 'daname'});
            $('#dadesc').editable({type: 'text', name: 'dadesc'});
            $('#dabz').editable({type: 'text', name: 'dabz'});
            $('#dacode').editable({type: 'text', name: 'dacode'});

            $('[data-rel=tooltip]').tooltip();

            function addAttr(idx, namex, sid, sname) {
                for(var i = 0; i < $("#xattrList").children().length; i ++) {
                    var wtf = $("#xattrList").children("button").eq(i);
                    if(idx == wtf.attr("href").substr(6)) {
                        alert("该属性已存在");
                        return;
                    }
                }
                var x = 1;
                for(var i = 0; i < $("#sysList").find("a").length; i ++) {
                    var wtf = $("#sysList").find("a").eq(i);
                    if(sid == wtf.attr("href").substr(4)) {
                        x = 0;
                        break;
                    }
                }
                if(x == 1) {
                    $("#sysList").append('<li> <a data-toggle="tab" href="#sid' + sid + '" aria-expanded="false">' + sname + '</a></li>');
                }
                var s = '<button type="button" class="btn btn-white btn-info btn-round" style="width:24%" href="#xattr';
                s += idx + '" data-sid="' + sid + '" data-sname="' + sname + '" style="display: none;">' + namex;
                s += '<i class="ace-icon fa fa-times red2 pull-right"></i> </button>';
                $("#xattrList").append(s);
                $("button[href^='#xattr']").bind("click", function() {
                    $(this).remove();
                });
                $("a[href^='#sid']").bind("click", function() {
                    $("button[href^='#xattr']").hide();
                    $("button[data-sid='" + sid + "']").show();
                })
            }

            function loadSideBar() {
                var typeDA = new Array();
                $.ajax({
                    url: "getType.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        typeDA = data;
                    }
                });
                var DAs = new Array();
                for(var i = 0; i < typeDA.length; i ++) {
                    $.ajax({
                        url: "getDAbyType.action",
                        async: false,
                        data: {typeId: typeDA[i].id},
                        dataType: "json",
                        success: function(data) {
                            DAs[i] = data;
                        },
                    });
                }
                var s = '';
                for(var i = 0; i < typeDA.length; i ++) {
                    s += '<li> <a href="#" class="dropdown-toggle"> <i class="menu-icon fa fa-tags"></i> <span class="menu-text">';
                    s += typeDA[i].name;
                    s += '</span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b> <ul class="submenu">';
                    for(var j = 0; j < DAs[i].length; j ++) {
                        s += '<li class=""> <a href="#DA';
                        s += DAs[i][j].id;
                        s += '" data-type="';
                        s += typeDA[i].name;
                        s += '" onclick=";"> <i class="menu-icon fa fa-caret-right"></i>';
                        s += DAs[i][j].name;
                        s += '</a> <b class="arrow"></b> </li>';
                    }
                    if(DAs[i].length == 0) {
                            s += '<li class><a>';
                            s += '<i class="menu-icon fa fa-cogs"></i> <span class="menu-text">';
                            s += '空';
                            s += '</span> </a> <b class="arrow"></b> </li>';
                            $("#ExecuteList").html(s);
                        }
                    s += '</ul> </li>';
                }
                $("#DAList").html(s);

                var datypes = [];
                $.each(typeDA, function(k, v) {
                    datypes.push({id: v.name + "#" + v.id, text: v.name + "#" + v.id});
                });

                $('#datype').editable({
                    type: 'select2',
                    value : datypes[0].text,
                    source: datypes,
                    select2: {
                        'width': 140
                    }
                });

                var fptypes = [];
                var tmp = [];
                $.ajax({
                    url: "getFP.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        tmp = data;
                        $.each(data, function(k, v) {
                            fptypes.push({id: v.name + "#" + v.id, text: v.name + "#" + v.id});
                        });
                    },
                });

                $('#fptype').editable({
                    type: 'select2',
                    value : fptypes[0].text,
                    source: fptypes,
                    select2: {
                        'width': 140
                    }
                });

                $("a[href^='#DA']").bind("click", function() {
                    var idx = $(this).attr("href").substr(3);
                    $("#xattrList").empty();
                    $("#saveBtn").attr("data-daid", idx);
                    var attr = [];
                    $.ajax({
                        url: "getDAbyId.action",
                        data: {daId: idx},
                        dataType: "json",
                        async: false,
                        success: function(data) {
                            data = data[0];
                            $("#daname").text(data.name);
                            $("#dadesc").text(data.description);
                            $("#dacode").text(data.code);
                            //$("#datype").text(data.Type);
                            //$("#fptype").text(data.fightPostion);
                            attr = data.attribute;
                            //$("#typex").text(data.Type);
                            $("#namex").text(data.name);
                            for(var i = 0; i < tmp.length; i ++) {
                                if(tmp[i].id == data.fightPostion)
                                    $("#fptype").text(tmp[i].name + "#" + tmp[i].id);
                            }
                            for(var i = 0; i < typeDA.length; i ++) {
                                if(typeDA[i].id == data.Type) {
                                    $("#typex").text(typeDA[i].name);
                                    $("#datype").text(typeDA[i].name + "#" + typeDA[i].id);
                                }
                            }
                        },
                    });
                    $("#sysList").empty();
                    for(var i = 0; i < attr.length; i ++) {
                        $.ajax({
                            url: "getAttrbyId.action",
                            data: {atrId: attr[i]},
                            dataType: "json",
                            success: function(data) {
                                data = data[0];
                                addAttr(data.id, data.name, data.systemId, data.systemName);
                            },
                        });
                    }
                });

            }

            function loadSystem() {
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

                $("#systemList").empty();
                for(var i = 0; i < system.length; i ++) {
                    var s = '<li class="dropdown"> <a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">';
                    s += system[i].name;
                    s += '&nbsp; <i class="ace-icon fa fa-caret-down bigger-110 width-auto"></i> </a> <ul class="dropdown-menu">';
                    for(var j = 0; j < system2[i].length; j ++) {
                        s += '<li class="dropdown dropdown-hover"> <a href="#" class="clearfix"> <span class="pull-left">';
                        s += system2[i][j].name;
                        s += '</span> <i class="ace-icon fa fa-caret-right pull-right"></i> </a> <ul class="dropdown-menu">';
                        for(var k = 0; k < system3[i][j].length; k ++) {
                            s += '<li> <a href="#system';
                            s += system3[i][j][k].id;
                            s += '" data-toggle="tab">';
                            s += system3[i][j][k].name;
                            s += '</a> </li>';
                        }
                        s += '</ul> </li>';
                    }
                    s += '</ul> </li>';
                    $("#systemList").append(s);
                }
            }

            function loadAttrList() {
                $("a[href^='#system']").bind("click", function() {
                    $("#systemList").find("li.active").removeClass("active");
                    var attr = [];
                    var idx = $(this).attr("href").substr(7);
                    var sname = $(this).text();
                    $.ajax({
                        url: "getAtrBySystem.action",
                        async: false,
                        data: {systemId: idx},
                        dataType: "json",
                        success: function(data) {
                            attr = data;
                            $("#dropdowns").removeClass("active").removeClass("in").empty();
                        },
                    });
                    for(var i = 0; i < attr.length; i ++) {
                        var s = '<button type="button" class="btn btn-white btn-info btn-round" style="width:14%" href="#attr';
                        s += attr[i].id;
                        s += '"> ';
                        s += attr[i].name;
                        s += '<i class="ace-icon fa fa-plus green pull-right"></i> </button>';
                        $("#dropdowns").append(s);
                    }
                    $("#dropdowns").addClass("in").addClass("active");
                    $("button[href^='#attr']").bind("click", function() {
                        var idxx = $(this).attr("href").substr(5);
                        var namex = $(this).text();
                        addAttr(idxx, namex, idx, sname);
                    });
                });
            }

            $(document).ready(function() {

                loadSideBar();
                loadSystem();
                loadAttrList();

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

                $("#saveBtn").bind("click", function() {
                    var attr = [];
                    for(var i = 0; i < $("#xattrList").children().length; i ++) {
                        attr[i] = $("#xattrList").children().eq(i).attr("href").substr(6);
                    }
                    if($(this).attr("data-daid") == "-1") {
                        $("#typex").text("新建任务");
                        $("#namex").text("");
                        $.ajax({
                            url: "insertDA.action",
                            type: "post",
                            traditional: true,
                            data: {
                                name: $("#daname").text(),
                                description: $("#dadesc").text(),
                                typeId: $("#datype").text().substr($("#datype").text().indexOf("#") + 1),
                                code: $("#dacode").text(),
                                attribute: attr,
                                fightPostionId: $("#fptype").text().substr($("#fptype").text().indexOf("#") + 1),
                                triggertype: "",
                                priority: 0,
                                state: "template",
                                ifAuto: 0,
                                ifdef: 1,
                            },
                            dataType: "json",
                            success: function(data) {
                                alert("原子任务新建完成");
                                //location.reload();
                            },
                        });
                    } else {
                        var idx = $(this).attr("data-daid");
                        $.ajax({
                            url: "updateDA.action",
                            type: "post",
                            traditional: true,
                            data: {
                                daId: idx,
                                name: $("#daname").text(),
                                description: $("#dadesc").text(),
                                typeId: $("#datype").text().substr($("#datype").text().indexOf("#") + 1),
                                code: $("#dacode").text(),
                                attribute: attr,
                                fightPostionId: $("#fptype").text().substr($("#fptype").text().indexOf("#") + 1),
                                triggertype: "",
                                priority: 0,
                                state: "template",
                                ifAuto: 0,
                                ifdef: 1,
                            },
                            dataType: "json",
                            success: function(data) {
                                alert("原子任务更新完成");
                            },
                        });
                    }
                });

                $("#newBtn").bind("click", function() {
                    $("#saveBtn").attr("data-daid", "-1");
                    $("#xattrList").empty();
                    $("#daname").text("新任务");
                    $("#dadesc").text("---");
                    $("#dacode").text("no data");
                    $("#dabz").text("---");
                });
            });
        </script>

        <!-- inline scripts related to this page -->
    </body>
</html>
