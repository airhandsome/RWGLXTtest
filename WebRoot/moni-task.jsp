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
        <script src="static/pic4.js"></script>
	<script src="static/div1.js"></script>
	<script src="static/div2.js"></script>
	<script src="static/div3.js"></script>
	<script src="static/P11.js"></script>
        <script src="static/pic2.js.js"></script>
        <script type="text/javascript">
  
	     function getidx(idx) {
		
                $("[id$='form']").hide();
	        
                if(idx[1] == 'A') {
		    $("#sidebar").attr("data-focus", idx.substr(2));
		    refreshAttr();
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
	    
	    function refreshAttr() {
		loadAttrList();
		$("[role='presentation']").hide();
		$("[role='tabpanel']").hide();
		$("[role='tabpanel']").children().hide();

		var daid = $("#sidebar").attr("data-focus");
		if(daid == -1) {
		    return;
		}
		var attr = new Array();
		$.ajax({
		    async: false,
		    url: "getDAbyId.action",
		    data: {daId: daid},
		    dataType: "json",
		    success: function(data) {
			attr = data[0].attribute;
		    },
 		});
		for(var i = 0; i < attr.length; i ++) {
		    $.ajax({
			url: "getAttrbyId.action",
			data: {atrId: attr[i]},
			dataType: "json",
			success: function(data) {
			    data = data[0];
			    var idx = data.id;
			    var sysid = data.systemId;
			    $("#attr-" + idx).show();
			    $("#attr-" + idx).parent().show();
			    
			    $("a[href='#source" + sysid + "']").parent().show();
			},
		    });
		}
		$("[role='presentation']:visible:first").addClass("active");
                $("[role='tabpanel']:visible:first").addClass("active");
	    
	    }

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

                var s1 = '<ul class="nav nav-tabs" role="tablist">';
                var s2 = '<div class="tab-content">';
		

                for(var i = 0; i < type.length; i ++) {
                    s1 += '<li role="presentation"><a href="#source' + type[i].id + '" aria-controls="source' + type[i].id + '" role="tab" data-toggle="tab">'+ type[i].name +'</a></li>';
                    s2 += '<div role="tabpanel" class="tab-pane" id="source'+ type[i].id +'"></div>';
                }
                s1 += '</ul><br />';
                s2 += '</div>';
                $("#attrlist").html(s1 + s2);

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
                        s += '<div id="attr-' + attr[i][j].id + '" class="col-md-6"> <p>&nbsp;' + attr[i][j].name + ':&nbsp;<strong>' + attr[i][j].value + '</strong></p></div>';
                    }
                    $("#source" + type[i].id).html(s);
                }
                
             }
	    

        $(document).ready(function() {
	    
	   

            $("[id^='select']").click(function() {
                $("[id^='select']").attr("class", "");
                var btn = $(this);
                btn.attr("class", "active");
            });

            $("[id$='form']").hide();
            $("#daform").show();
            $("[id^='xx']").click(function() {
                var btn = $(this);
                var idx = btn.attr("id").substr(2);
                $("[id$='form']").hide();
                $("#" + idx + "form").show();
            });

            function loadSideBar() {
                $.ajax({
                    async: false,
                    url: "getExcutingISP.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        $("#sidebar").empty();
                        for(var i = 0; i < data.length; i ++) {
                            $("#sidebar").append('<li><a id="EISP' + data[i].id + '">' + data[i].name + '</a></li>');
                        }
                    },
                });

                $("[id^='EISP']").bind("click", function() {

                    $("#ispx").attr("data-focus", "#" + $(this).attr("id"));
                    $("#ispview").empty();
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
                        var m = 0;
                        for(var i = 0; i < DA.length; i ++) {
                            var status = 3;
                            if(DA[i].state == "Undo") {
                                status = 4;
                            } else if(DA[i].state == "Finish") {
                                status = 1;
                            } else if(DA[i].state == "Executing") {
                                status = 2;
                            }

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
                                jsonx.DA[m] = {id: DA[i].id, name: DA[i].name, length: 100, status: status};
                            } else if(preDA === 0) {
                                jsonx.DA[m] = {id: DA[i].id, name: DA[i].name, length: 100, preDS: preDS, status: status};
                            } else if(preDS === 0) {
                                jsonx.DA[m] = {id: DA[i].id, name: DA[i].name, length: 100, preDA: preDA, status: status};
                            } else {
                                jsonx.DA[m] = {id: DA[i].id, name: DA[i].name, length: 100, preDA: preDA, preDS: preDS, status: status};
                            }
                            m += 1;
                        }

                        // var s = '<div class="well" style="height: 60%; overflow-y: scroll;"><canvas id="ca-' + idx + '" width="1500" height="1500" onclick="getidx(show(event,\'ca-' + idx + '\'));"></canvas></div>';
                        var s = '<canvas class="well" id="ca-' + idx + '" width="1500" height="1500" onclick="getidx(show(event,\'ca-' + idx + '\'));"></canvas>';
                        $("#ispview").append(s);
			var color = new Array();
			for(var i = 0; i < jsonx.DA.length; i ++) {
			    color[i] = jsonx.DA[i].status;
			}if($("#ispx").attr("data-focus") == "#EISP12"){
			    P0(color, "ca-" + idx);
			}else if($("#ispx").attr("data-focus") == "#EISP13"){
			    P1(color, "ca-" + idx);
			}else if($("#ispx").attr("data-focus") == "#EISP14"){
			    P2(color, "ca-" + idx);
			}else if($("#ispx").attr("data-focus") == "#EISP15"){
			    P3(color, "ca-" + idx);
			}else if($("#ispx").attr("data-focus") == "#EISP16"){
			    P11(color, "ca-" + idx);
			}else{
			    P(color, "ca-" + idx);
			}
                    }
                });
		if($("#postnamex").attr("data-name") != "舰长") {
		    $("#selectApp").hide();
		} else {
	  	    $("#selectNew").hide();
		}
 		if($("#postnamex").attr("data-name").indexOf("副舰长") != -1) {
		    $("#selectNew").children().attr("href", "esta-vice.jsp");
		} else {
		    $("#selectNew").children().attr("href", "esta-drt.jsp");
		}
		
		if($("#ispx").attr("data-focus") != "0") {
		    $($("#ispx").attr("data-focus")).trigger("click");
		}
            }

            loadSideBar(); 
	    setInterval(loadSideBar, 3000);
	    
	    setInterval(refreshAttr, 3000);
	
        });

        </script>
    </head>
    <body>
        <div class="container-fluid" id="postnamex" data-name="<%=(String)session.getAttribute("post")%>">
            <br />
            <div class="row" style="height: 7%">
                <div class="col-md-3">
                    <p class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">职位:<%=(String)session.getAttribute("post")%></font></p>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    
                </div>
                <div class="col-md-4">
                    <ul class="nav nav-pills pull-right">
                        <li id="selectNew">
                            <a href="">新建任务</a>
                        </li>
                        <li id="selectApp">
                            <a href="appr.jsp">审批状态</a>
                        </li>
                        <li id="selectView" class="active">
                            <a href="moni-task.jsp">任务监控</a>
                        </li>
 			<li>
                            <a href="exec.jsp" target="_blank">任务执行</a>
                        </li>
                    </ul>
                    
                </div>
            </div> 
            <div class="row">
                <div class="col-md-2 well" style="height: 90%;">
                    <div style="height: 55%; overflow-y: scroll;">
                        <p id="ispx" data-focus="0">执行中ISP列表</p>
                        <ul id="sidebar" data-focus="-1">
                            
                        </ul>
                    </div>
                    <div style="height: 40%;">
			<hr>
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
                                    <input id="startDA" type="text" class="form-control" readonly />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    结束时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="endDA" type="text" class="form-control" readonly />
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
                </div>
                <div class="col-md-7 well" style="height: 90%; overflow: scroll;">
		    <div>
			<button class="btn btn-primary">待执行</button>
			<button class="btn btn-warning">执行中</button>
			<button class="btn btn-success">执行完毕</button>
		    </div>
		    <br />
                    <div id="ispview" style="">
                    </div>
                </div>
                <div class="col-md-3 well" style="height: 90%;">
                    <div id="attrlist" style="height: 100%; overflow-y: scroll;">
                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
