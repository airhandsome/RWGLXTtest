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

            $("[id^='select']").click(function() {
                $("[id$='BtnX']").toggle();
                $("[id^='select']").removeClass("active");
                $(this).addClass("active");
                var idx = $(this).attr("id").substr(6) + 'List';
                $("[id$='List']").hide();
                $("#" + idx).show();
                $("[id$='BtnX']").attr("data-focus", "");
            });

            function loadSideBar() {
		var pn = $("#PendingList").children().length;
                $.ajax({
                    url: "getPDA.action", //update here
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        var s = '';
                        for(var i = 0; i < data.length; i ++) {
                            s += '<button id="PDA-' + data[i].id + '" type="button" class="btn btn-link btn-lg">' + data[i].name + '</button>';
                        }
                        $("#PendingList").html(s);
			if(pn != data.length) {
			    alert("待执行任务变更");
			}
                    },
                });
                $.ajax({
                    url: "getEDA.action", //update here
                    async: false,
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        var s = '';
                        for(var i = 0; i < data.length; i ++) {
                            s += '<button id="EDA-' + data[i].id + '" type="button" class="btn btn-link btn-lg">' + data[i].name + '</button>';
                        }
                        $("#ExecuteList").html(s);
			
                    },
                });

                $("[id^='PDA']").bind("click", function() {
                    var idx = $(this).attr("id").substr(4);
                    $.ajax({
                        url: "getDAbyId.action",
                        data: {daId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            var attr = data.attribute;
                            $("#daname").text(data.name);
                            $("#dadesc").text(data.description);
                            $("#daoper").text($("#namex").attr("data-name"));
                            $("#dastart").text(data.planStart);
                            $("#dadura").text(data.planDuration);
			    $("#dacode").text(data.code);
		 	    if(data.code == "no data") {
				$("#dacode").text("---");
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
                            $("#beginBtnX").attr("data-focus", idx);
                            loadAttrList(data.attribute);
                        },
                    });
                });

                $("[id^='EDA']").bind("click", function() {
                    var idx = $(this).attr("id").substr(4);
                    $.ajax({
                        url: "getDAbyId.action",
                        data: {daId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            var attr = data.attribute;
                            $("#daname").text(data.name);
                            $("#dadesc").text(data.description);
                            $("#daoper").text($("#namex").attr("data-name"));
                            $("#dastart").text(data.planStart);
                            $("#dadura").text(data.planDuration);
			    $("#dacode").text(data.code);
                            if(data.planDuration == "") {
                                $("#dadura").text("---");
                            }
                            if(data.planStart == "") {
                                $("#dastart").text("---");
                            }
			    if(data.code == "no data") {
				$("#dacode").text("---");
		  	    }
                            if(data.triggertype == "state") {
                                $("#datrigger").text("状态触发");
                            } else if(data.triggertype == "time") {
                                $("#datrigger").text("时间触发");
                            } else {
                                $("#datrigger").text("事件触发");
                            }
                            $("#overBtnX").attr("data-focus", idx);
			    $("#codeBtnX").attr("data-focus", idx);
                            loadAttrList(data.attribute);
                        },
                    });
                }); 
            }

            loadSideBar();

	    setInterval(loadSideBar, 2000);

            function loadAttrList(attr) {
                $("#attrListX").empty();
                for(var i = 0; i < attr.length; i ++) {
                    $.ajax({
			async: false,
                        url: "getAttrbyId.action",
                        data: {atrId: attr[i]},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            $("#attrListX").append('<div class="col-md-3"> <p>&nbsp;' + data.name + ':&nbsp;<strong>' + data.value + '</strong></p></div>');
                        },
                    });
                }
            }
                            
            $("#beginBtnX").click(function() {
                if($(this).attr("data-focus") == "")
                    return;
                var idx = $(this).attr("data-focus");
                $.ajax({
                    url: "startDA.action",
                    data: {daId: idx},
                    dataType: "json",
                    success: function(data) {
                        alert("任务开始执行");
                        location.reload();
                    },
                });
            });
	
	    $("#codeBtnX").click(function() {
		$.ajax({
		    url: "executeOPC.action",
		    data: {daId: $(this).attr("data-focus")},
		    dataType: "json",
		    success: function(data) {
			alert("操作完成");
		    },
		});
	    });

            $("#overBtnX").click(function() {
                if($(this).attr("data-focus") == "")
                    return;
                var idx = $(this).attr("data-focus");
                $.ajax({
                    url: "finishDA.action",
                    data: {daId: idx},
                    dataType: "json",
                    success: function(data) {
                        alert("任务已结束");
                        location.reload();
                    },
                });
            });

            function loadX() {
                var attr = new Array();
                $.ajax({
                    async: false,
                    url: "getDAbyId.action",
                    data: {daId: $("#beginBtnX").attr("data-focus") + $("#overBtnX").attr("data-focus")},
                    dataType: "json",
                    success: function(data) {
                        loadAttrList(data[0].attribute);
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

            setInterval(refreshTime, 10000);
            setInterval(loadX, 2000);
            setInterval(loadSideBar, 2000);

        });

        </script>
    </head>
    <body>
        <div class="container-fluid" id="namex" data-name="<%=(String)session.getAttribute("post")%>">
            <br />
            <div class="row" style="height: 7%">
                <p class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">职位:<%=(String)session.getAttribute("post")%></font></p>
            </div> 
            <div class="row">
                <div class="col-md-3 well" style="height: 90%; overflow-y: scroll;">
                    <div id="sidebar">
                        <ul class="nav nav-pills">
                            <li id="selectPending" class="active"><a>待执行的DA</a></li>
                            <li id="selectExecute"><a>正在执行的DA</a></li>
                        </ul>
                        <hr>
                        <div id="PendingList">
                        </div>
                        <div id="ExecuteList" style="display: none;">
                        </div>
                        
                    </div>
                </div>
                <div class="col-md-9 well" style="height: 90%;">
                    <div class="row" style="height: 20%;">
                        <div class="col-md-4">
                            <p>&nbsp;任务名称:&nbsp;
                                <strong id="daname"></strong>
                            </p>
                            <p>&nbsp;任务描述:&nbsp;
                                <strong id="dadesc"></strong>
                            </p>
                        </div>
                        <div class="col-md-4">
                            <p>&nbsp;操作员:&nbsp;
                                <strong id="daoper"></strong>
                            </p>
                            <p>&nbsp;触发方式:&nbsp;
                                <strong id="datrigger"></strong>
                            </p>
                        </div>
                        <div class="col-md-4">
                            <p>&nbsp;起始时间:&nbsp;
                                <strong id="dastart"></strong>
                            </p>
                            <p>&nbsp;持续时间:&nbsp;
                                <strong id="dadura"></strong>
                            </p>
                        </div>
                        <div class="col-md-4">
                            <p>&nbsp;执行操作:&nbsp;
                                <strong id="dacode"></strong>
                            </p>
                   
                        </div>
                    </div>
                    <hr>
                    <div id="attrListX" class="row" style="height: 60%; overflow-y: scroll;">
                        
                    </div>
                    <div style="height: 10%;">
                        <hr>
			<div class="pull-left">
			    备注:&nbsp;&nbsp;运行状态:&nbsp;1.打开&nbsp;0.关闭&nbsp;&nbsp;启停状态:&nbsp;1.启动&nbsp;0.停止&nbsp;&nbsp;故障状态:&nbsp;1.故障&nbsp;0.正常
			</div>
                        <div class="pull-right">
                            <button id="beginBtnX" class="btn btn-primary" data-focus>开始执行</button>
			    <button id="codeBtnX" class="btn btn-primary" style="display: none;" data-focus>执行操作</button>
                            <button id="overBtnX" class="btn btn-primary" style="display: none;" data-focus>执行完成</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
