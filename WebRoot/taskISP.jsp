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
        <style type="text/css"> .can{min-width:200px; max-width:200px; }</style> 
        <script type="text/javascript">
        $(document).ready(function() {

            function loadSideBar1() {
                isp = new Array();
                $.ajax({ 
                    async: false,
                    url: "getISPdef.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        isp = data;
                    },
                });
                var s = '<h4>ISP列表</h4><br /><ul>';
                for(var i = 0; i < isp.length; i ++) {
                    s += '<li><a id="ISP-' + isp[i].id + '"><i class="icon-retweet"></i> ' + isp[i].name + '</a></li>';
                }
                s += '</ul>';

                $("#sidebar1").html(s);
            }

            loadSideBar1();

            function loadSideBar2() {
                type = new Array();
                $.ajax({
                    async: false,
                    url: "getType.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        type = data;
                    },
                });
                CAs = new Array();
                for(var i = 0; i < type.length; i ++) {
                    $.ajax({
                        async: false,
                        url: "getCAbyType.action",
                        data: {typeId: type[i].id},
                        dataType: "json",
                        success: function(data) {
                            CAs[i] = data;
                        },
                    });
                }

                var s = '<ul style="margin-left: -30">';
                for(var i = 0; i < type.length; i ++) {
                    s += '<li class="wtf3"><a>' + type[i].name + '</a><ul>';
                    for(var j = 0; j < CAs[i].length; j ++) {
                        s += '<li class="wtf4"><i class="icon-wrench"></i> <a id="CA-' + CAs[i][j].id + '" >' + CAs[i][j].name + '</a></li>';
                    }
                    s += '</ul></li><br />';
                }
                s += '</ul>';
                $("#sidebar2").html(s);
                $(".wtf3").children().not("a").hide();
                $(".wtf3").bind("click", function() {
                    $(this).children().not("a").toggle();
                });
                $(".wtf4").bind("click", function() {
                    $(this).children().not("a").toggle();
                    $(this).parent().parent().children().not("a").toggle();
                });
            }

            loadSideBar2();

            $("[id^='ISP-']").click(function() {
                $("#ispview").empty();
                $("#caview").empty();
                var CA = new Array();
                $.ajax({
                    async: false,
                    url: "getCAbyISP.action",
                    data: {ispId: $(this).attr("id").substr(4)},
                    dataType: "json",
                    success: function(data) {
                        CA = data;
                    }
                });
                for(var j = 0; j < CA.length; j ++) {
                    var DA = new Array();
                    var DS = new Array();
                    var idx = CA[j].id;
                    var name = CA[j].name;
                    $.ajax({
                        async: false,
                        url: "getDAbyCA.action",
                        data: {caId: CA[j].id},
                        dataType: "json",
                        success: function(data) {
                            DA = data;
                        },
                    });
                    $.ajax({
                        async: false,
                        url: "getDSbyCA.action",
                        data: {caId: CA[j].id},
                        dataType: "json",
                        success: function(data) {
                            DS = data;
                        },
                    });
                    var jsonx = new Object();
                    jsonx.DA = new Array();
                    jsonx.DS = new Array();
                    for(var i = 0; i < DS.length; i ++) {
                        jsonx.DS[i] = {id: DS[i].id, name: DS[i].name, preDA: DS[i].preDA, trueExitDA: DS[i].trueExit, falseExitDA: DS[i].falseExit};
                    }
                    for(var i = 0; i < DA.length; i ++) {
                        var preDS = 0;
                        var preDA = 0;
                        for(var k = 0; k < DS.length; k ++) {
                            if(DS[k].trueExit == DA[i].id || DS[k].falseExit == DA[i].id) {
                                preDS = DS[k].id;
                            }
                        }
                        $.ajax({
                            async: false,
                            url: "getDAPre.action",
                            data: {daId: DA[i].id},
                            dataType: "json",
                            success: function(data) {
                                preDA = parseInt(data);
                            },
                        });
                        if(preDA === 0 && preDS === 0) {
                            jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100};
                        } else if(preDA === 0) {
                            jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDS: preDS};
                        } else if(preDS === 0) {
                            jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDA: preDA};
                        } else {
                            jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDA: preDA, preDS: preDS};
                        }
                    }

                    // var s = '<div class="well" style="height: 60%; overflow-y: scroll;"><canvas id="ca-' + idx + '" width="950" height="500" onclick="getidx(show(event,\'ca-' + idx + '\'));"></canvas></div>';
                    var s = '<div class="well" style="height: 60%; overflow-y: scroll;"><div><button id="cabtn-' + idx + '" class="btn btn-info pull-left">' + name + '</button></div><canvas id="ca-' + idx + '" width="950" height="500" onclick="getidx(show(event,\'ca-' + idx + '\'));"></canvas></div>';
                    $("#ispview").append(s);
                    //P(jsonx, "ca-" + idx);
                }
            });

            $("[id^='CA-']").click(function() {
                $("#ispview").empty();
                var btn = $(this);
                var idx = btn.attr("id").substr(3);
                var name = $(this).text();
                $.ajax({
                    async: false,
                    url: "CaDef.action",
                    data: {
                        ispId: $("#newISPBtn").attr("data-ispid"),
                        caId: idx,
                        triggertype: "time",
                        planStarttime: "",
                        planEndtime: "",
                    },
                    dataType: "json",
                    success: function(data) {
                        idx = data;
                    },
                });

                var DA = new Array();
                var DS = new Array();
                $.ajax({
                    async: false,
                    url: "getDAbyCA.action",
                    data: {caId: parseInt(idx)},
                    dataType: "json",
                    success: function(data) {
                        DA = data;
                    },
                });
                $.ajax({
                    async: false,
                    url: "getDSbyCA.action",
                    data: {caId: parseInt(idx)},
                    dataType: "json",
                    success: function(data) {
                        DS = data;
                    },
                });
                var jsonx = new Object();
                jsonx.DA = new Array();
                jsonx.DS = new Array();
                for(var i = 0; i < DS.length; i ++) {
                    jsonx.DS[i] = {id: DS[i].id, name: DS[i].name, preDA: DS[i].preDA, trueExitDA: DS[i].trueExit, falseExitDA: DS[i].falseExit};
                }
                for(var i = 0; i < DA.length; i ++) {
                    var preDS = 0;
                    var preDA = 0;
                    for(var j = 0; j < DS.length; j ++) {
                        if(DS[j].trueExit == DA[i].id || DS[j].falseExit == DA[i].id) {
                            preDS = DS[j].id;
                        }
                    }
                    $.ajax({
                        async: false,
                        url: "getDAPre.action",
                        data: {daId: DA[i].id},
                        dataType: "json",
                        success: function(data) {
                            preDA = parseInt(data);
                        },
                    });
                    if(preDA === 0 && preDS === 0) {
                        jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100};
                    } else if(preDA === 0) {
                        jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDS: preDS};
                    } else if(preDS === 0) {
                        jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDA: preDA};
                    } else {
                        jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDA: preDA, preDS: preDS};
                    }
                }

                var s = '<div class="well" style="height: 60%; overflow-y: scroll;"><div><button id="cabtn-' + idx + '" class="btn btn-info pull-left">' + name + '</button></div><canvas id="ca-' + idx + '" width="950" height="500" onclick="getidx(show(event,\'ca-' + idx + '\'));"></canvas></div>';
                $("#caview").append(s);

                //P(jsonx, "ca-" + idx);

                //<canvas id="cawell" width="1600" height="1600" onclick="getidx(show(event,'cawell'));"></canvas>
        
            });

            $("#newISPBtn").click(function() {
                $("#cancelBtn").toggle();
                $("#modal-164752").hide();
                $.ajax({
                    url: "insertISP.action",
                    traditional: true,
                    type: "post",
                    data: {
                        name: $("#ISPname").val(),
                        description: $("#ISPdesc").val(),
                        priority: 1,
                        ifdef: 1,
                        ifAuto: 1,
                        state: "CheckPending",
                        triggertype: "",
                    },
                    dataType: "json",
                    success: function(data) {
                        $("#newISPBtn").attr("data-ispid", data);
                        $("[id^='sidebar']").toggle();
                        $("#ispview").toggle();
                        $("#caview").toggle();
                        $("#confBtn").show();
                    },
                });
            });

            $("#cancelBtn").click(function() {
                $("#isplist").toggle();
                $("#calist").toggle();
                $("#ispview").toggle();
                $("#caview").toggle();
            });

            $("#confBtn").click(function() {
                $("#newISPBtn").attr("data-ispid", "");
                $("#caview").empty();
                alert("新建ISP完成");
                $(this).hide();
                loadSideBar1();
                $("[id^='loadSideBar']").toggle();
            });

            $("[id^='cabtn']").click(function() {
                var idx = 'CC' + $(this).attr("id").substr(5);
                $("#spcBtn").attr("data-focus", idx);
            });

            $("#spcBtn").click(function() {
                var idx = $("#spcBtn").attr("data-focus");
                if(idx[1] == 'A') {
                    idx = idx.substr(2);
                    var attr = new Array();
                    var type = 0;
                    $.ajax({
                        async: false,
                        url: "getDAbyId.action",
                        data: {daId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            type = data.Type;
                            attr = data.attribute;
                        },
                    });
                    
                    $.ajax({
                        async: false,
                        url: "updateDA.action",
                        type: "post",
                        traditional: true,
                        data: {
                            daId: idx,
                            name: $("#nameDA").val(),
                            description: $("#descDA").val(),
                            typeId: type,
                            code: $("#codeDA").val(),
                            attribute: attr,
                            fightPostionId: 1,
                            priority: 0,
                            state: "instance",
                            ifAuto: 0,
                            ifdef: 0,
                            planStart: $("#startDA").val(),
                            planEnd: $("#endDA").val(),
                            triggertype: $("#triggerDA").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改完成");
                        },
                    });


                } else if(idx[1] == 'S') {
                    idx = idx.substr(2);
                    var attr = $("[id^='sel-']").attr("id");
                    //alert(attr);
                    var atr = new Array();
                    for(var i = 0; i < attr.length; i ++) {
                        var xid = attr[i].attr("id").substr(4);
                        var sel = $("#sel-" + xid).val();
                        var txt = $("#txt-" + xid).val();
                        atr[i] = [xid, sel, txt];
                    }

                    var name, desc, type;
                    $.ajax({
                        url: "getDSbyId.action",
                        data: {dsId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            name = data.name;
                            desc = data.description;
                            type = data.Type;
                        },
                    });

                    $.ajax({
                        url: "updateDS.action",
                        traditional: true,
                        data: {
                            name: name,
                            description: desc,
                            dstypeId: type,
                            attribute: atr,
                            ifdef: 0,
                            dsbelong: 0,
                            logic: "",
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改成功");
                        },
                    });
                    
                } else {
                    var ca;
                    $.ajax({
                        async: false,
                        url: "getCAbyId.action",
                        data: {caId: $("#spcBtn").attr("data-focus").substr(2)},
                        dataType: "json",
                        success: function(data) {
                            ca = data[0];
                        }
                    });

                    $.ajax({
                        url: "updateCA.action",
                        traditional: true,
                        type: "post",
                        data: {
                            caId: ca.id,
                            typeId: ca.Type,
                            name: $("#nameCA").val(),
                            description: $("#descCA").val(),
                            triggertype: "",
                            priority: 0,
                            state: "instance",
                            ifAuto: 0,
                            ifdef: 0,
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改成功");
                        },
                    });
                }
            });
        });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <br />
            <div class="row" style="height: 7%;">
                <ul class="nav nav-pills pull-right">
                    <li><a href="maintain.jsp">基础配置维护</a></li>
                    <li><a href="taskDA.jsp">站位执行任务(DA)管理</a></li>
                    <li><a href="taskDS.jsp">条例(DS)管理</a></li>
                    <li><a href="taskCA.jsp">部门复合任务(CA)管理</a></li>
                    <li class="active"><a href="taskISP.jsp">任务计划(ISP)管理</a></li>
                </ul>
                <p class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">职位:<%=(String)session.getAttribute("post")%></font></p>
            </div>
            <div class="row">
                <div id="isplist" class="col-md-2 well" style="height: 90%;">
                    <div id="sidebar1" style="height: 90%; overflow-y: scroll;">
                    </div>
                    <div id="sidebar2" style="height: 90%; overflow-y: scroll; display: none;">
                    </div>
                    <hr>
                    <a id="modal-164752" href="#modal-container-164752" role="button" class="btn btn-primary pull-right" data-toggle="modal">新建ISP</a>
                    <div class="modal fade" id="modal-container-164752" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">新建ISP</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">
                                                任务名称
                                            </label>
                                            <div class="col-sm-9">
                                                <input id="ISPname" type="text" class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">
                                                任务描述
                                            </label>
                                            <div class="col-sm-9">
                                                <input id="ISPdesc" type="text" class="form-control" placeholder=""/>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
                                    <button id="newISPBtn" type="button" class="btn btn-primary" data-dismiss="modal" data-ispid="">保存</button>
                                </div>
                            </div>
                        </div>          
                    </div>
                    
                    <button id="cancelBtn" class="btn btn-warning pull-right" style="display: none;">取消</button>
                </div>
                <div id="view" class="col-md-7 well" style="height: 90%;">
                    <div id="ispview" style="height: 90%; overflow-y: scroll;">
                    </div>
                    <div id="caview" style="height: 90%; overflow-y: scroll; display: none;">
                    </div>
                    <div style="height: 10%;">
                        <hr>
                        <div class="pull-right">
                            <button id="confBtn" class="btn btn-primary" style="display: none;">确定</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 well" style="height: 90%;">
                    <div style="height: 90%;">
                        <form id="daform" class="form-horizontal" role="form" style="display: none;">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务名称
                                </label>
                                <div class="col-sm-8">
                                    <input id="nameDA" type="text" class="form-control" readonly />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务描述
                                </label>
                                <div class="col-sm-8">
                                    <input id="descDA" type="text" class="form-control" readonly />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务属性
                                </label>
                                <div class="col-sm-8">
                                    <input id="codeDA" type="text" class="form-control" readonly />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    起始时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="startDA" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    结束时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="endDA" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    触发方式
                                </label>
                                <div class="col-sm-8">
                                    <input id="triggerDA" type="text" class="form-control" readonly />
                                </div>
                            </div>
                        </form>
                        <form id="caform" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务名称
                                </label>
                                <div class="col-sm-8">
                                    <input id="nameCA" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务描述
                                </label>
                                <div class="col-sm-8">
                                    <input id="descCA" type="text" class="form-control" />
                                </div>
                            </div>
                            
                        </form>
                        <form id="dsform" class="form-horizontal" role="form" style="display: none;">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    条例名称
                                </label>
                                <div class="col-sm-8">
                                    <input id="nameDS" type="text" class="form-control" readonly />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    条例描述
                                </label>
                                <div class="col-sm-8">
                                    <input id="descDS" type="text" class="form-control" readonly />
                                </div>
                            </div>
                            <div id="attrapp"></div>
                        </form>
                    </div>
                    <div style="height: 10%;">
                        <hr>
                        <p class="pull-right">
                            <!-- <button id="deleISP" type="button" class="btn btn-warning" data-focus="">
                                删除
                            </button> -->
                            <button id="spcBtn" type="button" class="btn btn-primary" data-focus="">
                                确认
                            </button>
                        </p>
                        <br /><br /><br /><br />
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function getidx(idx) {
                $("[id$='form']").hide();
                if(idx[1] == 'A') {
                    $("#daform").show();
                    $.ajax({
                        url: "getDAbyId.action",
                        data: {daId: idx.substr(2)},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            $("#spcBtn").attr("data-focus", idx);
                            $("#nameDA").val(data.name);
                            $("#descDA").val(data.description);
                            $("#codeDA").val(data.code);
                            $("#startDA").val(data.planStart);
                            $("#endDA").val(data.planEnd);
                            $("#triggerDA").val(data.triggertype);
                        },
                    });
                } else if(idx[1] == 'S') {
                    $("#dsform").show();
                    var attr = new Array();
                    $.ajax({
                        async: false,
                        url: "getDSbyId.action",
                        data: {dsId: idx.substr(2)},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            $("#spcBtn").attr("data-focus", idx);
                            $("#nameDS").val(data.name);
                            $("#descDS").val(data.description);
                            attr = data.attribute;
                        },
                    });
                    var s = '';
                    for(var i = 0; i < attr.length; i ++) {
                        var x = attr[i].split(",");
                        var name = '';
                        $.ajax({
                            async: false,
                            url: "getAtrbyId.action",
                            data: {atrId: x[0]},
                            dataType: "json",
                            success: function(data) {
                                name = data[0].name;
                            },
                        });
                        s += '<div class="form-group">' +
                                '<div class="col-sm-4" style="margin-left:30; margin-right:-15;">' +
                                    '<input id=type="text" class="form-control" value="' + name + '" readonly />' +
                                '</div>' +
                                '<div class="col-sm-2" style="margin-left:-15; margin-right:-15;">' +
                                '<select id="sel-' + x[0] + '" value="' + x[1] + '">' + 
                                          '<option value="1">=</option>' +
                                          '<option value="2">&gt;</option>' +
                                          '<option value="3">&lt;</option>' +
                                          '<option value="4">&ge;</option>' +
                                          '<option value="5">&le;</option>' +
                                      '</select>' +
                                '</div>' + 
                                 '<div class="col-sm-6" style="margin-left:-15;">' + 
                                      '<input id="txt-' + x[0] + '" type="text" class="form-control col-sm-6" value="' + x[2] + '">' +
                                '</div>' + 
                            '</div>'; 
                    }
                    $("#attrapp").html(s);
                }
            }
        </script>
    </body>
</html>
