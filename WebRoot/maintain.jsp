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

        <link href="static2/css/prettify-1.0.css" rel="stylesheet">
        <link href="static2/css/base.css" rel="stylesheet">
        <link href="static2/css/bootstrap-datetimepicker.css" rel="stylesheet">
        <link href="static2/css/default.css" rel="stylesheet" type="text/css">
        <script src="static2/js/moment-with-locales.js"></script>
        <script src="static2/js/bootstrap-datetimepicker.js"></script>
        <script src="static/test.js"></script>

        <script type="text/javascript">
        $(document).ready(function() {

            function loadAttrList() {
                var type = new Array();
                $.ajax({
                    async: false,
                    type: "post",
                    url: "getSystem.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        type = data;
                    },
                });

                $("#systemList").empty();
                for(var i = 0; i < type.length; i ++) {
                    $("#systemList").append('<option value="' + type[i].id + '">' + type[i].name + '</option>');
                }

                var s1 = '<ul class="nav nav-tabs" role="tablist">'
                var s2 = '<div class="tab-content">'
                for(var i = 0; i < type.length; i ++) {
                    s1 += '<li role="presentation"><a href="#source' + type[i].id + '" aria-controls="source' + type[i].id + '" role="tab" data-toggle="tab">'+ type[i].name +'</a></li>'
                    s2 += '<div role="tabpanel" class="tab-pane" id="source'+ type[i].id +'"></div>'
                }
                s1 += '</ul><br />';
                s2 += '</div>';
                $("#attrlist").html(s1 + s2);

                s1 = '<div class="col-md-2"><label class"checkbox-inline"><input type="checkbox" value="attr-';
                s2 = '</label></div>';
                var attr = new Array();
                for(var i = 0; i < type.length; i ++) {
                    $.ajax({
                        async: false,
                        url: "getAtrBySystem.action",
                        data: {systemId: type[i].id},
                        dataType: "json",
                        success: function(data) {
                            attr[i] = data;
                        },
                    });
                }
                for(var i = 0; i < type.length; i ++) {
                    var s = '';
                    for(var j = 0; j < attr[i].length; j ++) {
                        s += '<p><a id="modal-' + attr[i][j].id + '" href="#modal-container-164752" role="button" data-toggle="modal" class="edAttr" data-tag="' + attr[i][j].tag + '">' + attr[i][j].name + '</a><p>';
                    }
                    $("#source" + type[i].id).html(s);
                }

                $(".edAttr").bind("click", function() {
                    var idx = $(this).attr("id").substr(6);
                    $("#editAttr").attr("data-focus", idx); 
                    $("#editAttr").attr("data-type", 0);
                    $("#attrName").val($(this).text());
		    $("#attrTag").val($(this).attr("data-tag"));
                    $("#systemList").parent().parent().hide();
                });
                // var t = '';
                // for(var i = 0; i < type.length; i ++) {
                //     t += '<option value="' + type[i].id + '">' + type[i].name + '</option>';
                // }
                // $("#systemList").html(t);
                
                $("[role='presentation']:first").addClass("active");
                $("[role='tabpanel']:first").addClass("active");

            }

            loadAttrList();

            function loadUserList() {
                $.ajax({
                    url: "getUser.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        $("#userTable").empty();
                        for(var i = 0; i < data.length; i ++) {
                            var s = '<tr id="user-' + data[i].id + '"> <td id="username' + data[i].id + '">' + data[i].username + '</td> <td id="password' + data[i].id + '">' + data[i].password + '</td> <td id="department' + data[i].id + '">' + data[i].department + '</td> <td id="post' + data[i].id + '">' + data[i].post + '</td> <td id="role' + data[i].id + '">' + data[i].role + '</td> <td id="fpos' + data[i].id + '">' + data[i].positionName + '</td> <td><a id="modal-10086" href="#modal-container-10086" role="button" data-toggle="modal"><i class="icon-pencil edUser"></i></a>&nbsp;<a><i class="icon-remove rmUser"></i></a> </td> </tr>';
                            $("#userTable").append(s);
                        }
                        $(".edUser").bind("click", function() {
                            var tr = $(this).parent().parent().parent();
                            var idx = tr.attr("id").substr(5);
                            var tds = tr.children();
                            $("#editUser").attr("data-focus", idx);
                            $("#editUser").attr("data-type", 0);
                            $("#username").val($("#username" + idx).text());
                            $("#password").val($("#password" + idx).text());
                            $("#department").val($("#department" + idx).text());
                            $("#post").val($("#post" + idx).text());
                            $("#role").val($("#role" + idx).text());
                        });

                        $(".rmUser").bind("click", function() {
                            var idx = $(this).parent().parent().parent().attr("id").substr(5);
                            $.ajax({
                                url: "deleteUser.action",
                                data: {usersId: idx},
                                dataType: "json",
                                success: function(data) {
                                    alert("删除完成");
                                    loadUserList();
                                },
                            });
                        });
                    },
                });
            }

            loadUserList();

            function loadFightList() {
                $.ajax({
                    url: "getFP.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {

                        for(var i = 0; i < data.length; i ++) {
                            var s = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                            $("#fpos").append(s);
                        }

                        $("#fpTable").empty();
                        for(var i = 0; i < data.length; i ++) {
                            var s = '<tr id="fpos-' + data[i].id + '"> <td id="fpname' + data[i].id + '">' + data[i].name + '</td> <td id="fpdepa' + data[i].id + '">' + data[i].department + '</td> <td id="fppost' + data[i].id + '">' + data[i].post + '</td> <td><a id="modal-920915" href="#modal-container-920915" role="button" data-toggle="modal"><i class="icon-pencil edFP"></i></a> </td> </tr>';
                            $("#fpTable").append(s);
                        }
                        $(".edFP").bind("click", function() {
                            var tr = $(this).parent().parent().parent();
                            var idx = tr.attr("id").substr(5);
                            var tds = tr.children();
                            $("#editFP").attr("data-focus", idx);
                            $("#editFP").attr("data-type", 0);
                            $("#fpname").val($("#fpname" + idx).text());
                            $("#fpdepa").val($("#fpdepa" + idx).text());
                            $("#fppost").val($("#fppost" + idx).text());
                            
                        });
                    },
                });
            }

            loadFightList();

            function loadSysList() {
                $.ajax({
                    url: "getSystem.action",
                    type: "post",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        $("#sysTable").empty();
                        for(var i = 0; i < data.length; i ++) {
                            var s = '<tr id="sys-' + data[i].id + '"> <td id="sysname' + data[i].id + '">' + data[i].name + '</td> <td><a id="modal-6088" href="#modal-container-6088" role="button" data-toggle="modal"><i class="icon-pencil edSys"></i></a> </td> </tr>';
                            $("#sysTable").append(s);
                        }
                        $(".edSys").bind("click", function() {
                            var tr = $(this).parent().parent().parent();
                            var idx = tr.attr("id").substr(4);
                            var tds = tr.children();
                            $("#editSys").attr("data-type", 0);
                            $("#editSys").attr("data-focus", idx);
                            $("#sysname").val($("#sysname" + idx).text());
                        });
                    },
                });
            }
        
            loadSysList();


            $("#addFP").click(function() {
                $("#editFP").attr("data-type", 1);
            });

            $("#editFP").click(function() {
                if($(this).attr("data-type") == 1) {
                    $.ajax({
                        url: "insertFP.action",
                        type: "post",
                        data: {
                            name: $("#fpname").val(),
                            department: $("#fpdepa").val(),
                            post: $("#fppost").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("添加完成");
                            loadFightList();
                        },
                    });
                } else {
                    var idx = $(this).attr("data-focus");
                    $.ajax({
                        type: "post",
                        url: "updateFP.action",
                        data: {
                            fightpostionId: idx,
                            name: $("#fpname").val(),
                            department: $("#fpdepa").val(),
                            post: $("#fppost").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改成功");
                            loadFightList();
                        },
                    });
                }
            });

            $("#addSys").click(function() {
                $("#editSys").attr("data-type", 1);
            });

            $("#editSys").click(function() {
                if($(this).attr("data-type") == 1) {
                    $.ajax({
                        url: "insertSys.action",
                        type: "post",
                        data: {
                            sysname: $("#sysname").val(),
                        },
                        success: function(data) {
                            alert("添加完成");
                            loadAttrList();
                            loadSysList();
                        },
                    });
                } else {
                    var idx = $(this).attr("data-focus");
                    $.ajax({
                        url: "updateSys.action",
                        type: "post",
                        data: {
                            sysId: idx,
                            sysname: $("#sysname").val(),
                        },
                        success: function(data) {
                            alert("修改完成");
                            loadAttrList();
                            loadSysList();
                        },
                    });
                }

            });

            $("#addUser").click(function() {
                $("#editUser").attr("data-type", 1);
            });

            $("#editUser").click(function() {
                if($(this).attr("data-type") == 1) {
                    $.ajax({
                        url: "insertUser.action",
                        type: "post",
                        data: {
                            username: $("#username").val(),
                            password: $("#password").val(),
                            department: $("#department").val(),
                            post: $("#post").val(),
                            role: $("#role").val(),
                            positionId: $("fpos").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("添加成功");
                            loadUserList();
                        },
                    });
                } else {
                    var idx = $(this).attr("data-focus");
                    $.ajax({
                        url: "updateUser.action",
                        type: "post",
                        data: {
                            usersId: idx,
                            username: $("#username").val(),
                            password: $("#password").val(),
                            department: $("#department").val(),
                            post: $("#post").val(),
                            role: $("#role").val(),
                            positionId: $("fpos").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改完成");
                            loadUserList();
                        },
                    });
                }
            });
    
            $("#addAttr").click(function() {
                $("#editAttr").attr("data-type", 1);
                $("#systemList").parent().parent().show();
            });

            $("#editAttr").click(function() {
                if($(this).attr("data-type") == 1) {
                    $.ajax({
                        url: "insertAtr.action",
                        type: "post",
                        data: {
                            systemId: $("#systemList").val(),
                            name: $("#attrName").val(),
                            tag: $("#attrTag").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("添加完成");
                            loadAttrList();
                        },
                    });
                } else {
                    var idx = $(this).attr("data-focus");
                    $.ajax({
                        url: "updateAtr.action",
                        type: "post",
                        data: {
                            atrId: idx,
                            name: $("#attrName").val(),
                            tag: $("#attrTag").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改完成");
                            loadAttrList();
                        },
                    });
                }
            });

            $("[id^='select']").click(function() {
                var btn = $(this);
                var idx = btn.attr("id").substr(6);
                $("[id^='select']").removeClass("active");
                btn.addClass("active");
                $("[id$='list']").hide();
                $("[id$='btns']").hide();
                $("#" + idx + "list").show();
                $("#" + idx + "btns").show();
            });

        });
        
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <br />
            <div class="row" style="height: 7%;">
                <ul class="nav nav-pills pull-right">
                    <li class="active"><a href="maintain.jsp">基础配置维护</a></li>
                    <li><a href="taskDA.jsp">站位执行任务(DA)管理</a></li>
                    <li><a href="taskDS.jsp">条例(DS)管理</a></li>
                    <li><a href="taskCA.jsp">部门复合任务(CA)管理</a></li>
                    <li><a href="taskISP.jsp">任务计划(ISP)管理</a></li>
                </ul>
                <p class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">职位:<%=(String)session.getAttribute("post")%></font></p>
            </div>
            <div class="row">
                <div class="col-md-2 well" style="height: 90%;">
                    <div id="sidebar" style="height: 87%;">
                        <ul class="nav nav-pills nav-stacked">
                            <li id="selectuser" class="active"><a>用户管理</a></li>
                            <li id="selectposi"><a>战位管理</a></li>
                            <li id="selectattr"><a>测点管理</a></li>
                            <li id="selectsys"><a>系统管理</a></li>
                        </ul>
                    </div>
                </div>
                <div id="view" class="col-md-10 well" style="height: 90%; scroll: overflow;">
                    <div id="userlist" style="height: 90%; overflow-y: scroll;">
                        <div class="modal fade" id="modal-container-10086" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h4 class="modal-title" id="myModalLabel">用户信息</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    用户名
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="username" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    密码
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="password" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    部门
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="department" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    职位
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="post" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    角色
                                                </label>
                                                <div class="col-sm-9">
                                                    <select id="role" class="form-control">
                                                        <option value="角色1">角色1</option>
                                                        <option value="角色2">角色2</option>
                                                        <option value="角色3">角色3</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    战位
                                                </label>
                                                <div class="col-sm-9">
                                                    <select id="fpos" class="form-control">
                                                        
                                                    </select>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                        <button id="editUser" type="button" class="btn btn-primary" data-dismiss="modal" data-focus data-type>确认</button>
                                    </div>
                                </div>
                            </div>        
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>人员列表</h4>
                            </div>
                            <table class="table">
                                <thead><tr>
                                    <th>用户名</th>
                                    <th>密码</th>
                                    <th>部门</th>
                                    <th>职位</th>
                                    <th>角色</th>
                                    <th>战位</th>
                                    <th>&nbsp;</th>
                                </tr></thead>
                                <tbody id="userTable"></tbody>
                            </table>
                        </div>
                    </div>
                    <div id="userbtns" style="height: 10%;">
                        <hr>
                        <div class="pull-right">
                            <a id="addUser" href="#modal-container-10086" role="button" class="btn btn-primary" data-toggle="modal">添加用户</a>
                        </div>
                    </div>
                    <div id="attrlist" style="height: 90%; overflow-y: scroll; display: none;">
                        
                    </div>
                    <div id="attrbtns" style="height: 10%; display: none;">
                        <div class="modal fade" id="modal-container-164752" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h4 class="modal-title" id="myModalLabel">添加属性</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    属性名称
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="attrName" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    测点来源
                                                </label>
                                                <div class="col-sm-9"><select id="systemList" class="form-control">
                                                    <!-- <option value="1">动力系统</option>
                                                    <option value="2">损管系统</option>
                                                    <option value="3">航行系统</option> -->
                                                </select></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    TAG
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="attrTag" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
                                        <button id="editAttr" type="button" class="btn btn-primary" data-dismiss="modal" data-type data-focus>保存</button>
                                    </div>
                                </div>
                            </div>          
                        </div>
                        <hr>
                        <div class="pull-right">
                            <a id="addAttr" href="#modal-container-164752" role="button" class="btn btn-primary" data-toggle="modal">添加</a>
                        </div>
                    </div>
                    <div id="posilist" style="height: 90%; overflow-y: scroll; display: none;">
                        <div class="modal fade" id="modal-container-920915" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h4 class="modal-title" id="myModalLabel">编辑战位信息</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    战位名称
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="fpname" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    所属部门
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="fpdepa" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    职位
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="fppost" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                        <button id="editFP" type="button" class="btn btn-primary" data-dismiss="modal" data-focus data-type>确认</button>
                                    </div>
                                </div>
                            </div>        
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>战位列表</h4>
                            </div>
                            <table class="table">
                                <thead><tr>
                                    <th>战位名称</th>
                                    <th>部门</th>
                                    <th>职位</th>
                                    <th>&nbsp;</th>
                                </tr></thead>
                                <tbody id="fpTable"></tbody>
                            </table>
                        </div>
                    </div>
                    <div id="posibtns" style="height: 10%; display: none;">
                        <hr>
                        <div class="pull-right">
                            <a id="addFP" href="#modal-container-920915" role="button" class="btn btn-primary" data-toggle="modal">添加战位</a>
                        </div>
                    </div>
                    <div id="syslist" style="height: 90%; overflow-y: scroll; display: none;">
                        <div class="modal fade" id="modal-container-6088" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h4 class="modal-title" id="myModalLabel">编辑系统信息</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                    系统名称
                                                </label>
                                                <div class="col-sm-9">
                                                    <input id="sysname" type="text" class="form-control"/>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                        <button id="editSys" type="button" class="btn btn-primary" data-dismiss="modal" data-focus data-type>确认</button>
                                    </div>
                                </div>
                            </div> 
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>系统列表</h4>
                            </div>
                            <table class="table">
                                <thead><tr>
                                    <th>系统名称</th>
                                    <th>&nbsp;</th>
                                </tr></thead>
                                <tbody id="sysTable"></tbody>
                            </table>
                        </div>
                    </div>
                    <div id="sysbtns" style="height: 10%; display: none;">
                        <hr>
                        <div class="pull-right">
                            <a id="addSys" href="#modal-container-6088" role="button" class="btn btn-primary" data-toggle="modal">添加系统</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
