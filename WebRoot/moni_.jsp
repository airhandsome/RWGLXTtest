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
                    <li id="shenpi" class="light-blue" style="display: none;">
                        <a href="appr_.jsp">
                            <i class="ace-icon fa fa-users"></i>
                            <span>任务审批</span>
                        </a>
                    </li>
                    <li id="bianji" class="light-blue">
                        <a href="esta_.jsp">
                            <i class="ace-icon fa fa-users"></i>
                            <span>任务编辑</span>
                        </a>
                    </li>
                    <li>
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
                    <i class="ace-icon fa fa-binoculars"></i>
                    <span class="menu-text"> 任务监控 </span>
                </div>
            </div>
            <!-- /.sidebar-shortcuts -->

            <ul id="ispList" class="nav nav-list">

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
                        <li>任务监控</li>
                        <li id="namex" class="active">&nbsp;</li>
                    </ul>
                    <!-- /.breadcrumb -->
                </div>

                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="row">
                                <div class="space"></div>
                                <div class="col-sm-8">
                                    <iframe id="ispGraph" src="ispGraph2_.jsp" frameborder="0" name="ispGraph" width="100%;" height="830px;" scrolling="no"></iframe>
                                </div>
                                <div class="col-sm-4">
                                    <!--<div class="profile-user-info profile-user-info-striped">
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
                                            <div class="profile-info-name"> 执行操作 </div>
                                            <div class="profile-info-value" id="dacode">

                                            </div>
                                        </div>
                                    </div>-->
                                    <div class="tabble">
                                        <ul class="nav nav-tabs">
                                            <li class="active">
                                                <a data-toggle="tab" href="#startAttrsx" aria-expanded="true">启动触发条件</a>
                                            </li>
                                            <li>
                                                <a data-toggle="tab" href="#stopAttrsx" aria-expanded="true">停止触发条件</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div id="startAttrsx" class="tab-pane fade active"></div>
                                            <div id="stopAttrsx" class="tab-pane fade"></div>
                                        </div>
                                    </div>
                                    <div class="space"></div>
                                    <div class="tabbable" style="margin: 13px;">
                                        <ul class="nav nav-tabs" id="systemList">

                                        </ul>

                                        <div class="tab-content no-padding" style="height: 620px; overflow-y: scroll;">
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
                    </div>
                    <!-- /.row -->
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
        if('ontouchstart' in document.documentElement) document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
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

            // function loadSystem() {
            //     var system = [];
            //     $.ajax({
            //         url: "getSystem.action",
            //         async: false,
            //         data: {},
            //         dataType: "json",
            //         success: function(data) {
            //             system = data;
            //         },
            //     });

            //     var system2 = [];
            //     for(var i = 0; i < system.length; i ++) {
            //         $.ajax({
            //             url: "getSystembyId.action",
            //             async: false,
            //             data: {sysId: system[i].id},
            //             dataType: "json",
            //             success: function(data) {
            //                 system2[i] = data;
            //             },
            //         });
            //     }

            //     var system3 = [];
            //     for(var i = 0; i < system.length; i ++) {
            //         system3[i] = [];
            //         for(var j = 0; j < system2[i].length; j ++) {
            //             $.ajax({
            //                 url: "getSystembyId.action",
            //                 async: false,
            //                 data: {sysId: system2[i][j].id},
            //                 dataType: "json",
            //                 success: function(data) {
            //                     system3[i][j] = data;
            //                 },
            //             });
            //         }
            //     }

            //     for(var i = 0; i < system.length; i ++) {
            //         var s = '<li class="dropdown level1"> <a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">';
            //         s += system[i].name;
            //         s += '&nbsp; <i class="ace-icon fa fa-caret-down bigger-110 width-auto"></i> </a> <ul class="dropdown-menu">';
            //         for(var j = 0; j < system2[i].length; j ++) {
            //             s += '<li class="dropdown dropdown-hover"> <a href="#" class="clearfix"> <span class="pull-left">';
            //             s += system2[i][j].name;
            //             s += '</span> <i class="ace-icon fa fa-caret-right pull-right"></i> </a> <ul class="dropdown-menu">';
            //             for(var k = 0; k < system3[i][j].length; k ++) {
            //                 s += '<li> <a href="#system';
            //                 s += system3[i][j][k].id;
            //                 s += '" data-toggle="tab">';
            //                 s += system3[i][j][k].name;
            //                 s += '</a> </li>';
            //             }
            //             s += '</ul> </li>';
            //         }
            //         s += '</ul> </li>';
            //         $("#systemList").append(s);
            //     }

            // }

            // function loadAttrList() {
            //     $("a[href^='#system']").bind("click", function() {
            //         $("#systemList").find("li.active").removeClass("active");
            //         $(this).parents("li").addClass("active");

            //         var attr = [];
            //         var idx = $(this).attr("href").substr(7);
            //         $.ajax({
            //             url: "getAtrBySystem.action",
            //             async: false,
            //             data: {systemId: idx},
            //             dataType: "json",
            //             success: function(data) {
            //                 attr = data;
            //                 $("#dropdowns").removeClass("active").removeClass("in").empty();
            //             },
            //         });
            //         for(var i = 0; i < attr.length; i ++) { //update
            //             var s = '<div class="infobox infobox-blue" style="width:25%" href="#attr';
            //             s += attr[i].id;
            //             s += '"> <div class="infobox-data"> <span class="infobox-data-number">';
            //             s += attr[i].value;
            //             s += '</span> <div class="infobox-content">';
            //             s += attr[i].name;
            //             s += '</div> </div>';
            //             if(attr[i].delta < 0.0) {
            //                 s += '<div class="stat stat-success">';
            //                 s += (0.0 - attr[i].delta);
            //                 s += '</div>';
            //             } else if(attr[i].delta > 0.0 && attr[i].delta < 9000) {
            //                 s += '<div class="stat stat-important">';
            //                 s += attr[i].delta;
            //                 s += '</div>';
            //             }
            //             s += '</div>';
            //             $("#dropdowns").append(s);
            //         }
            //         for(var i = 0; (attr.length + i) % 4 != 0; i ++) {
            //             $("#dropdowns").append('<div class="infobox infobox-blue" style="width: 25%;"></div>');
            //         }
            //         $("#dropdowns").addClass("in").addClass("active");

            //     });
            // }

            function loadSideBar() {

                $.ajax({
                    async: false,
                    url: "getExeISP.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        $("#ispList").empty();
                        for(var i = 0; i < data.length; i ++) {
                            var s = '<li class> <a href="#ISP';
                            s += data[i].id;
                            s += '"> <i class="menu-icon fa fa-cogs"></i> <span class="menu-text">';
                            s += data[i].name;
                            s += '</span> </a> <b class="arrow"></b> </li>';
                            $("#ispList").append(s);
                        }
                        if(data.length == 0) {
                            var s = '<li class>';
                            s += '<i class="menu-icon fa fa-cogs"></i> <span class="menu-text">';
                            s += '空';
                            s += '</span> </a> <b class="arrow"></b> </li>';
                            $("#ispList").append(s);
                        }
                    },
                });
                $("a[href^='#ISP']").bind("click", function() {
                    var idx = $(this).attr("href").substr(4);
                    $(this).parents("ul").children("li.active").removeClass("active");
                    $(this).parents("li").addClass("active");
                    $.ajax({
                        url: "getISPbyId.action",
                        data: {ispId: idx},
                        dataType: "json",
                        success: function(data) {
                            $("#daname").text(data[0].name);
                            $("#dadesc").text(data[0].description);
                            $("#ispGraph").attr("src", "ispGraph2_.jsp");
                        }
                    });

                    // $("#systemList").children("li.daTitle").remove();
                    // $.ajax({
                    //     url: "getDaByIsp.action",
                    //     async: false,
                    //     data: {ispId: idx},
                    //     dataType: "json",
                    //     success: function(data) {
                    //         for(var i = 0; i < data.length; i ++) {
                    //             var s = '<li class="daTitle" style="width: 100px;"> <a data-toggle="tab" href="#datitle';
                    //             s += data[i].id + '">';
                    //             s += data[i].name + '</a> </li>';
                    //             $("#systemList").append(s);
                    //         }
                    //     },
                    // });


                    // $("a[href^='#datitle']").bind("click", function() {
                    //     $("#systemList").find("li.active").removeClass("active");
                    //     $(this).parents("li").addClass("active");

                    //     var attr = [];
                    //     var idx = $(this).attr("href").substr(8);

                    //     $.ajax({
                    //         url: "getDAbyId.action",
                    //         async: false,
                    //         data: {daId: idx},
                    //         dataType: "json",
                    //         success: function(data) {
                    //             attr = data[0].attribute;
                    //             $("#dropdowns").removeClass("active").removeClass("in").empty();
                    //         },
                    //     });
                    //     for(var i = 0; i < attr.length; i ++) {
                    //         $.ajax({
                    //             url: "getAttrbyId.action",
                    //             async: false,
                    //             data: {atrId: attr[i]},
                    //             dataType: "json",
                    //             success: function(data) {
                    //                 data = data[0];
                    //                 var s = '<div class="infobox infobox-blue" style="width:25%" href="#attr';
                    //                 s += data.id;
                    //                 s += '"> <div class="infobox-data"> <span class="infobox-data-number">';
                    //                 s += data.value;
                    //                 s += '</span> <div class="infobox-content">';
                    //                 s += data.name;
                    //                 s += '</div> </div>';
                    //                 if(data.delta < 0.0) {
                    //                     s += '<div class="stat stat-success">';
                    //                     s += (0.0 - data.delta);
                    //                     s += '</div>';
                    //                 } else if(data.delta > 0.0 && data.delta < 9000) {
                    //                     s += '<div class="stat stat-important">';
                    //                     s += data.delta;
                    //                     s += '</div>';
                    //                 }
                    //                 s += '</div>';
                    //                 $("#dropdowns").append(s);
                    //             },
                    //         });
                    //     }

                    //     for(var i = 0; (attr.length + i) % 4 != 0; i ++) {
                    //         $("#dropdowns").append('<div class="infobox infobox-blue" style="width: 25%;"></div>');
                    //     }
                    //     $("#dropdowns").addClass("in").addClass("active");

                    // });
                });
            }
            
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
                
                // $("a[href^='#sid']").bind("click", function() {
                //     $("div[href^='#xattr']").hide();
                //     $("div[data-sid='" + sid + "']").show();
                // })
            }
            
            function getCurrentDA() {
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
                var datypes = [];
                $.each(typeDA, function(k, v) {
                    datypes.push({id: v.name + "#" + v.id, text: v.name + "#" + v.id});
                });

                var fptypes = [];
                $.ajax({
                    url: "getFP.action",
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        $.each(data, function(k, v) {
                            fptypes.push({id: v.name + "#" + v.id, text: v.name + "#" + v.id});
                        });
                    },
                });

                $.ajax({
                    url: "getCurrDa.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        data = data[0];
                        if(data.id == 0)
                            return;
                        // $("#daname").text(data.name);
                        // $("#dadesc").text(data.description);
                        // $("#dacode").text(data.code);
                        //$("#datype").text(data.Type);
                        //$("#fptype").text(data.fightPostion);
                        var attr = data.attribute;
                        //$("#typex").text(data.Type);
                        // $("#namex").text(data.name);
                        // for(var i = 0; i < fptypes.length; i ++) {
                        //     if(fptypes[i].id == data.fightPostion)
                        //         $("#fptype").text(fptypes[i].name + "#" + fptypes[i].id);
                        // }
                        // for(var i = 0; i < typeDA.length; i ++) {
                        //     if(typeDA[i].id == data.Type) {
                        //         //$("#typex").text(typeDA[i].name);
                        //         $("#datype").text(typeDA[i].name + "#" + typeDA[i].id);
                        //     }
                        // }
                        
                        $.ajax({
                            // async: false,
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
                                    }
                                    var st = data[i].attrname + ' ';
                                    $("#startAttrsx").prepend('<div class="form-group"><div class="col-sm-12"><h4 style="margin-left: 40px;">' + data[i].st + '</h4></div><div>')
                                    
                                }
                                    
                                else {
                                    $("#stopAttrs").prepend(s);
                                    x = $("#stopAttrs").children();
                                    for(var i = 0; i < x.length; i ++) {
                                        x.eq(i).find("select").eq(0).find("[value='" + data[i].attrid + "']").attr("selected", true);
                                        x.eq(i).find("select").eq(1).find("[value='" + data[i].symbs + "']").attr("selected", true);
                                        x.eq(i).find("select").eq(2).find("[value='" + data[i].logics + "']").attr("selected", true);
                                        x.eq(i).find("input").eq(0).val(data[i].vals);
                                    }
                                    $("#stopAttrsx").prepend('<div class="form-group"><div class="col-sm-12"><h4 style="margin-left: 40px;">' + data[i].st + '</h4></div><div>')
                                }
                                
                            }
                            
                        });
                        
                        $("#systemList").empty();
                        $("#xattrList").empty();
                        for(var i = 0; i < attr.length; i ++) {
                            addAttr(attr[i]);
                        }
                        $("a[href^='#sid']").bind("click", function() {
                            var sid = $(this).attr("href").substr(4);
                            $("tr[href^='#attr']").hide();
                            $("tr[data-sid='" + sid + "']").show();
                            $("#xattrList").attr("data-sid", sid);
                        });
                        $("tr[data-sid='" + $("#xattrList").attr("data-sid") + "']").show();
                    },
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
                
                if($("#operid").attr("data-post") == "舰长") {
                    $("#shenpi").show();
                    $("#bianji").hide();
                }
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

                loadSideBar();
                //loadSystem();
                //loadAttrList();
                setInterval(loadSideBar, 2000);
                setInterval(getCurrentDA, 2000);

            });
    </script>

    <!-- inline scripts related to this page -->
</body>

</html>