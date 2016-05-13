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
                    
                }
            });

            function loadSideBar1() {
                var ISP = new Array();
                $.ajax({
                    async: false,
                    url: "getPendingISP.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        ISP = data;
                    },
                });
                var CAs = new Array();
                for(var i = 0; i < ISP.length; i ++) {
                    $.ajax({
                        async: false,
                        url: "getCAbyISP.action",
                        data: {ispId: ISP[i].id},
                        dataType: "json",
                        success: function(data) {
                            CAs[i] = data;
                        },
                    });
                }

                var s = '<ul style="margin-left: -30">';
                for(var i = 0; i < ISP.length; i ++) {
                    s += '<li class="wtf3"><a id="ISP-' + ISP[i].id + '">' + ISP[i].name + '</a><ul>';
                    for(var j = 0; j < CAs[i].length; j ++) {
                        s += '<li class="wtf4"><a id="CA-' + CAs[i][j].id + '" ><i class="icon-wrench"></i> ' + CAs[i][j].name + '</a></li>'
                    }
                    s += '</ul></li><br />';
                }
                s += '</ul>';
                $("#sidebar1").html(s);
                $(".wtf3").children().not("a").hide();
                $(".wtf3").bind("click", function() {
                    $(this).children().not("a").toggle();
                });
                $(".wtf4").bind("click", function() {
                    $(this).children().not("a").toggle();
                    $(this).parent().parent().children().not("a").toggle();
                });
                $("[id^='ISP-']").click(function() {
                    var idx = $(this).attr("id").substr(4);
                    $("#apprBtn").attr("data-focus", idx);
                    $("#rejcBtn").attr("data-focus", idx);
                });

                $("[id^='CA-']").click(function() {
                    $("[id$='form']").hide();
                    $("#caform").show();
                    //add info here
                    var DS = new Array();
                    var DA = new Array();
                    var caid = $(this).attr("id").substr(3);
                    $.ajax({
                        url: "getCAbyId.action",
                        data: {caId: caid},
                        dataType: "json",
                        success: function(data) {
                            $("#nameCA").val(data.name);
                            $("#descCA").val(data.description);
                        },
                    });
                    $.ajax({
                        async: false,
                        url: "getDSbyCA.action",
                        data: {caId: caid},
                        dataType: "json",
                        success: function(data) {
                            DS = data;
                        },
                    });
                    $.ajax({
                        async: false,
                        url: "getDAbyCA.action",
                        data: {caId: caid},
                        dataType: "json",
                        success: function(data) {
                            DA = data;
                        }
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
                    $("#canvasx").html('<canvas id="cawell" width="1600" height="1600" onclick="getidx(show(event,\'cawell\'));"></canvas>');
                    //P(jsonx, "cawell");
                });
            }

            loadSideBar1();
            
            $("#apprBtn").click(function() {
                if($(this).attr("data-focus").length == "")
                    return;
                $.ajax({
                    url: "apprISP.action",
                    data: {ispId: $(this).attr("data-focus")},
                    dataType: "json",
                    success: function(data) {
                        alert("任务已批准");
                        loadSideBar1();
                    },
                });
            });

            $("#rejcBtn").click(function() {
                if($(this).attr("data-focus").length == "")
                    return;
                $.ajax({
                    url: "rejctISP.action",
                    data: {ispId: $(this).attr("data-focus")},
                    dataType: "json",
                    success: function(data) {
                        alert("任务已驳回");
                        loadSideBar1();
                    },
                });
            });
        });
        </script>
    </head>
        <body>
        <div class="container-fluid">
            <br />
            <div class="row" style="height: 7%">
                <ul class="nav nav-pills pull-right">
                    
                    <li id="selectApp" class="active">
                        <a href="">审批状态</a>
                    </li>
                    <li id="selectView">
                        <a href="moni-task.jsp">任务监控</a>
                    </li>
		    <li>
                        <a href="exec.jsp" target="_blank">任务执行</a>
                    </li>
                </ul>
                <p class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">职位:<%=(String)session.getAttribute("post")%></font></p>
            </div> 
            <div class="row">
                <div class="col-md-2 well" style="height: 90%;">
                    <div id="sidebar1" style="height: 87%; overflow-y: scroll;">
                    </div>
                    <hr>
                    <div class="pull-right">
                        <button id="apprBtn" class="btn btn-primary" data-focus="">批准</button>
                        <button id="rejcBtn" class="btn btn-warning" data-focus="">驳回</button>
                    </div>
                </div>
                <div id="canvasx" class="col-md-7 well" style="height: 90%;">

                </div>
                <div class="col-md-3 well" style="height: 90%; overflow: scroll;">
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
                            
                        </form>
                    </div>
                    <div style="height: 10%;">
                        <hr>
                        <p class="pull-right">
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
                                    '<input id="' + x[0] + '" type="text" class="form-control" value="' + name + '" readonly />' +
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
