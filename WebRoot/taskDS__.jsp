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
                        <li>
                            <a href="taskDA_.jsp">
                                <i class="ace-icon fa fa-cog"></i>
                                <span>原子任务(DA)管理</span>
                            </a>
                        </li>
                        <li class="light-blue">
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
                        <i class="ace-icon fa fa-random"></i>
                        <span class="menu-text"> 条例(DS)管理 </span>
                    </div>
                </div><!-- /.sidebar-shortcuts -->

                <ul id="DSList" class="nav nav-list">


                </ul><!-- /.nav-list -->

            </div>

            <div class="main-content">
                <div class="main-content-inner">
                    <div class="breadcrumbs" id="breadcrumbs">
                        <script type="text/javascript">
                            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                        </script>

                        <ul class="breadcrumb">
                            <li>DS管理</li>
                            <li id="typex">--</li>
                            <li id="namex" class="active">--</li>
                        </ul><!-- /.breadcrumb -->

                        <button id="saveBtn" class="btn btn-success pull-right" data-daid="-1">
                            保存修改 &nbsp;
                            <i class="ace-icon fa fa-save"></i>
                        </button>
                        <button id="newBtn" class="btn btn-primary pull-right">
                            新建条例 &nbsp;
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
                                                    <span class="editable editable-click" id="dsname">---</span>
                                                </div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 任务描述 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="dsdesc">---</span>
                                                </div>
                                            </div>
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 所属类型 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="dstype">---</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> 任务备注 </div>
                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="dsbz">---</span>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="space"></div>
                                        <!-- <div class="widget-box widget-color-blue2">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-code"></i>
                                                    条例语义
                                                </h5>
                                            </div>
                                            <div class="widget-body">
                                                <div class="widget-main no-padding prettyprint prettyprinted" style="height: 80px; overflow-y: scroll;">
                                                </div>
                                            </div>
                                        </div> -->
                                        <pre class="prettyprint" style="margin-left: 12px; margin-right: 12px; height: 120px; overflow-y: scroll;">
                                        </pre>

                                    </div>
                                    <div class="col-sm-7">
                                        <div class="widget-box widget-color-blue2">
                                            <div class="widget-header widget-header-flat widget-header-small">
                                                <h5 class="widget-title smaller">
                                                    <i class="ace-icon fa fa-dashboard"></i>
                                                    条例关联测点
                                                </h5>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main no-padding" style="height: 200px; overflow-y: scroll;">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead class="thin-border-bottom">
                                                            <tr>
                                                                <th>属性名称</th>
                                                                <th>属性符号</th>
                                                                <th>数值</th>
                                                                <th>组合逻辑</th>
                                                                <th></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody id="xattrList">
                                                            <!-- <tr>
                                                                <td>123</td>
                                                                <td>
                                                                    <select class="syb form-control input-sm">
                                                                        <option value="1">=</option>
                                                                        <option value="2">&gt;</option>
                                                                        <option value="3">&lt;</option>
                                                                        <option value="4">&ge;</option>
                                                                        <option value="5">&le;</option>
                                                                    </select>
                                                                </td>
                                                                <td>
                                                                    <input class="form-control input-sm" />
                                                                </td>
                                                                <td>
                                                                    <select class="log form-control input-sm">
                                                                        <option value="1">and</option>
                                                                        <option value="2">or</option>
                                                                        <option value="0">结束</option>
                                                                    </select>
                                                                </td>
                                                                <td>
                                                                    <div class="btn-group">
                                                                        <button class="btn btn-success btn-xs">
                                                                            <i class="ace-icon fa fa-angle-double-up bigger-120"></i>
                                                                        </button>
                                                                        <button class="btn btn-info btn-xs">
                                                                            <i class="ace-icon fa fa-angle-double-down bigger-120"></i>
                                                                        </button>
                                                                        <button class="btn btn-danger btn-xs">
                                                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                        </button>
                                                                    </div>
                                                                </td>
                                                            </tr> -->
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="widget-toolbox clearfix">
                                                    <button id="getRegBtn" class="btn btn-xs btn-success pull-right">
                                                        <span class="bigger-110">生成条例</span>
                                                        <i class="ace-icon fa fa-check icon-on-right"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="hr hr-double hr-dotted hr18"></div>
                                <div class="space"></div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="tabbable">
                                            <ul class="nav nav-tabs" id="systemList">

                                            </ul>

                                            <div id="attrList" class="tab-content" style="height: 450px; overflow-y: scroll;">
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

            $('#dsname').editable({type: 'text', name: 'dsname'});
            $('#dsdesc').editable({type: 'text', name: 'dsdesc'});
            $('#dsbz').editable({type: 'text', name: 'dsbz'});

            function addAttr(attr) {
                var x = attr.split(',');
                console.log(x);
                var s = '<tr id="xattr' + x[0] + '"> <td>' + x[1] + '</td>';
                s += '<td> <select class="syb form-control input-sm"> <option value="1">=</option> <option value="2">&gt;</option>';
                s += '<option value="3">&lt;</option> <option value="4">&ge;</option> <option value="5">&le;</option> </select> </td>';
                s += '<td> <input class="form-control input-sm" /> </td>';
                s += '<td> <select class="log form-control input-sm"> <option value="1">and</option> <option value="2">or</option>';
                s += '<option value="0">结束</option> </select> </td>';
                s += '<td> <div class="btn-group"> <button class="goup btn btn-danger btn-xs">';
                // s += '<i class="ace-icon fa fa-angle-double-up bigger-120"></i> </button> <button class="godown btn btn-info btn-xs">';
                // s += '<i class="ace-icon fa fa-angle-double-down bigger-120"></i> </button> <button class="trash btn btn-danger btn-xs">';
                s += '<i class="ace-icon fa fa-trash-o bigger-120"></i> </button> </div> </td> </tr>';
                $("#xattrList").append(s);
                $("#xattr" + x[0]).find("select.syb").eq(0).val(x[2]);
                $("#xattr" + x[0]).find("input").eq(0).val(x[3]);
                $("#xattr" + x[0]).find("select.log").eq(0).val(x[4]);

                $("button.goup").bind("click", function() {
                    var tr = $(this).parents("tr").html();
                    var idx = $(this).parents("tr").attr("id");
                    $(this).parents("tr").prev().before('<tr id="' + idx + '">' + tr + '</tr>');
                    $(this).parents("tr").remove();
                });

                $("button.godown").bind("click", function() {
                    var tr = $(this).parents("tr").html();
                    var idx = $(this).parents("tr").attr("id");
                    $(this).parents("tr").next().after('<tr id="' + idx + '">' + tr + '</tr>');
                    $(this).parents("tr").remove();
                });

                $("button.trash").bind("click", function() {
                    $(this).parents("tr").remove();
                })

            }

            function loadSideBar() {
                var typeDS = new Array();
                $.ajax({
                    url: "getDSType.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        typeDS = data;
                    }
                });
                var DSs = new Array();
                for(var i = 0; i < typeDS.length; i ++) {
                    $.ajax({
                        url: "getDSbyType.action",
                        async: false,
                        data: {dstypeId: typeDS[i].id},
                        dataType: "json",
                        success: function(data) {
                            DSs[i] = data;
                        },
                    });
                }
                var s = '';
                for(var i = 0; i < typeDS.length; i ++) {
                    s += '<li> <a href="#" class="dropdown-toggle"> <i class="menu-icon fa fa-tags"></i> <span class="menu-text">';
                    s += typeDS[i].name;
                    s += '</span> <b class="arrow fa fa-angle-down"></b> </a> <b class="arrow"></b> <ul class="submenu">';
                    for(var j = 0; j < DSs[i].length; j ++) {
                        s += '<li class=""> <a href="#DS';
                        s += DSs[i][j].id;
                        s += '" data-type="';
                        s += typeDS[i].name;
                        s += '" onclick=";"> <i class="menu-icon fa fa-caret-right"></i>';
                        s += DSs[i][j].name;
                        s += '</a> <b class="arrow"></b> </li>';
                    }
                    if(DSs[i].length == 0) {
                        s += '<li class><a>';
                        s += '<i class="menu-icon fa fa-cogs"></i> <span class="menu-text">';
                        s += '空';
                        s += '</span> </a> <b class="arrow"></b> </li>';
                        
                    }
                    s += '</ul> </li>';
                }
                $("#DSList").html(s);

                var dstypes = [];
                $.each(typeDS, function(k, v) {
                    dstypes.push({id: v.name + "#" + v.id, text: v.name + "#" + v.id});
                });

                $('#dstype').editable({
                    type: 'select2',
                    value : dstypes[0].text,
                    source: dstypes,
                    select2: {
                        'width': 140
                    }
                });


                $("a[href^='#DS']").bind("click", function() {
                    var idx = $(this).attr("href").substr(3);
                    $("#xattrList").empty();
                    $("#saveBtn").attr("data-daid", idx);
                    var attr = [];
                    $.ajax({
                        url: "getDSbyId.action",
                        data: {dsId: idx},
                        dataType: "json",
                        async: false,
                        success: function(data) {
                            data = data[0];
                            $("#dsname").text(data.name);
                            $("#dsdesc").text(data.description);
                            $("#namex").text(data.name);
                            attr = data.attr;
                            for(var i = 0; i < typeDS.length; i ++) {
                                if(typeDS[i].id == data.Type) {
                                    $("#typex").text(typeDS[i].name);
                                    $("#dstype").text(typeDS[i].name + "#" + typeDS[i].id);
                                }
                            }
                            
                        },
                    });
                    $("#xattrList").empty();
                    for(var i = 0; i < attr.length; i ++) {
                        addAttr(attr[i]);
                    }
                    checkLogic();
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
                    var s = '<li class="dropdown level1"> <a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">';
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
                    $(this).parents("li.level1").addClass("active");
                    var attr = [];
                    var idx = $(this).attr("href").substr(7);
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
                        var idx = $(this).attr("href").substr(5);
                        var namex = $(this).text();
                        addAttr(idx + "," + namex + ",1,0,0");
                    });
                });
            }

            function checkLogic() {
                var s = '';
                var attr = [];
                for(var i = 0; i < $("#xattrList").children().length; i ++) {
                    var now = $("#xattrList").children().eq(i);
                    var idx = now.attr("id").substr(5);
                    var name = now.children().eq(0).text();
                    var syb = now.find("select.syb").val();
                    var val = now.find("input").val();
                    var log = now.find("select.log").val();
                    var map = {
                        '1':'<span class="pun">=</span>',
                        '2':'<span class="pun">&gt;</span>',
                        '3':'<span class="pun">&lt;</span>',
                        '4':'<span class="pun">&ge;</span>',
                        '5':'<span class="pun">&le;</span>',
                    };
                    var map2 = {
                        '1':'<span class="blue">and</span>',
                        '2':'<span class="blue">or</span>',
                    };
                    s += '<span class="pln"> </span>';
                    s += '<span class="green">' + name + '</span><span class="pln"> </span>';
                    s += map[syb];
                    s += '<span class="pln"> </span><span class="red">' + val + '</span><span class="pln"> </span>';
                    attr[i] = idx + "," + syb + "," + val + "," + log;
                    if(log == 0) {
                        $(".prettyprint").html(s);
                        return attr;
                    } else {
                        s += map2[log];
                    }
                }
                // console.log(syb);
                // console.log(s);
                $(".prettyprint").html(s);
                return attr;
            }
            
            function checkLogic2() {
                var s = '';
                
                for(var i = 0; i < $("#xattrList").children().length; i ++) {
                    var now = $("#xattrList").children().eq(i);
                    var idx = now.attr("id").substr(5);
                    var name = now.children().eq(0).text();
                    var syb = now.find("select.syb").val();
                    var val = now.find("input").val();
                    var log = now.find("select.log").val();
                    var map = {
                        '1':'=',
                        '2':'>',
                        '3':'<',
                        '4':'>=',
                        '5':'<=',
                    };
                    var map2 = {
                        '1':'and',
                        '2':'or',
                    };
                    s += ' ';
                    s += name + ' ';
                    s += map[syb];
                    s += ' ' + val + ' ';
                    
                    if(log == 0) {
                        return s;
                    } else {
                        s += map2[log];
                    }
                }
                // console.log(syb);
                // console.log(s);
                return s;
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

                $("#newBtn").bind("click", function() {
                    $("#saveBtn").attr("data-daid", "-1");
                    $("#xattrList").empty();
                    $("#dsname").text("新条例");
                    $("#dadesc").text("---");
                    $(".prettyprinted").empty();
                });

                $("#saveBtn").bind("click", function() {
                    var attr = checkLogic();
                    if($(this).attr("data-daid") == "-1") {
                        $("#typex").text("新建条例");
                        $("#namex").text("");
                        $.ajax({
                            url: "insertDS.action",
                            traditional: true,
                            type: "post",
                            data: {
                                name: $("#dsname").text(),
                                description: $("#dsdesc").text(),
                                dstypeId: $("#dstype").text().substr($("#dstype").text().indexOf("#") + 1),
                                attribute: attr,
                                ifdef: 1,
                                dsbelong: 0,
                                logic: checkLogic2(),
                            },
                            dataType: "json",
                            success: function(data) {
                                alert("条例新建完成");
                                //location.reload();
                            },
                        });
                    } else {
                        var idx = $(this).attr("data-daid");
                        $.ajax({
                            url: "updateDS.action",
                            traditional: true,
                            type: "post",
                            data: {
                                dsId: idx,
                                name: $("#dsname").text(),
                                description: $("#dsdesc").text(),
                                dstypeId: $("#dstype").text().substr($("#dstype").text().indexOf("#") + 1),
                                attribute: attr,
                                ifdef: 1,
                                dsbelong: 0,
                                logic: checkLogic2(),
                            },
                            dataType: "json",
                            success: function(data) {
                                alert("条例更新完成");
                            },
                        });
                    }
                });

                $("#getRegBtn").bind("click", function() {
                    checkLogic();
                });

            });
        </script>

        <!-- inline scripts related to this page -->
    </body>
</html>
