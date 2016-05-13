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
            // var DA = [
            //         [["排水堵漏备便DA","小艇设施备便DA"],["关闭全舰舷窗DA","关闭甲板通风DA","关闭令字水密门舱口盖DA"],["液位控制DA","自由液位控制DA"],["舰内设施固定DA","舰外悬挂清除DA"]],
            //         [["事态感知DA"],["航速航向控制DA","压载水控制DA","减摇鳍控制DA"],["备用舵、锚机备便DA"]],
            //         [["动力辅助设备监控DA","动力推进装置监控DA"]]
            //         ];
            // var CA = [["损管备便","水密控制","液位控制","设施备便"],["航行态势感知","航行运动控制","航行设施备便"],["推进保障"]];
            // var TYPE = ["安全防护","航行组织","综合保障"];

            function loadSideBar() {

                var typeDA = new Array();
                $.ajax({
                    async: false,
                    url: "getType.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        typeDA = data;
                    },
                });
                var DAs = new Array();
                for(var i = 0; i < typeDA.length; i ++) {
                    $.ajax({
                        async: false,
                        url: "getDAbyType.action",
                        data: {typeId: typeDA[i].id},
                        dataType: "json",
                        success: function(data) {
                            DAs[i] = data;
                        },
                    });
                }

                var s = '<ul style="margin-left: -30">';
                for(var i = 0; i < typeDA.length; i ++) {
                    s += '<li class="wtf"><a><i class="icon-wrench"></i> ' + typeDA[i].name + '</a><ul>';
                    for(var j = 0; j < DAs[i].length; j ++) {
                        s += '<li class="wtf2"><a id="DA-' + DAs[i][j].id + '" >' + DAs[i][j].name + '</a></li>'
                    }
                    s += '</ul></li><br />';
                }
                s += '</ul>';
                $("#sidebar").html(s);

                s = '';
                for(var i = 0; i < typeDA.length; i ++) {
                    s += '<option value="' + typeDA[i].id + '">' + typeDA[i].name + '</option>';
                }
                $("#datype").html(s);

                $.ajax({
                    url: "getFP.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        var s = '';
                        for(var i = 0; i < data.length; i ++) {
                            s += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                        }
                        $("#fptype").html(s);
                    },
                });


                $(".wtf").children().not("a").hide();
                $(".wtf").bind("click", function() {
                    $(this).children().not("a").toggle();
                });
                $(".wtf2").bind("click", function() {
                    $(this).children().not("a").toggle();
                    $(this).parent().parent().children().not("a").toggle();
                });

                //$("input").attr("disabled", "disabled");
                $("input[value^='attr-']").removeAttr("checked");
                //$("select").attr("disabled", "disabled");
            }

            loadSideBar();

            function loadAttrList() {
                var type = new Array();
                $.ajax({
                    async: false,
                    url: "getSystem.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        type = data;
                    },
                });

                var s1 = '<ul class="nav nav-tabs" role="tablist">'
                var s2 = '<div class="tab-content">'
                for(var i = 0; i < type.length; i ++) {
                    s1 += '<li role="presentation"><a href="#source' + type[i].id + '" aria-controls="source' + type[i].id + '" role="tab" data-toggle="tab">'+ type[i].name +'</a></li>'
                    s2 += '<div role="tabpanel" class="tab-pane" id="source'+ type[i].id +'"></div>'
                }
                s1 += '</ul><br />';
                s2 += '</div>';
                $("#attrlist").html(s1 + s2);
                s1 = '<div class="col-md-2"><label class="checkbox-inline"><input type="checkbox" value="attr-';
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
                        s += s1 + attr[i][j].id + '" id="attr-' + attr[i][j].id + '">' + attr[i][j].name + s2;
                    }
                    $("#source" + type[i].id).html(s);
                }
                
                $("[role='presentation']:first").addClass("active");
                $("[role='tabpanel']:first").addClass("active");
                $("[id^='DA-']").bind("click", function() {
                    //$("input").attr("disabled", "disabled");
                    //$("select").attr("disabled", "disabled");
                    $("input[value^='attr-']").removeAttr("checked");
                    var btn = $(this);
                    var idx = btn.attr("id").substr(3);
                    var attr = new Array();
                    $("#newDABtn").attr("data-on", idx);
                    $.ajax({
                        async: false,
                        url: "getDAbyId.action",
                        data: {daId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            $("#daname").val(data.name);
                            $("#dadesc").val(data.description);
                            $("#daattr").val(data.code);
                            $("#datype").val(data.Type);
                            $("#fptype").val(data.fightPostion);
                            attr = data.attribute;
                        },
                    });
                    for(var i = 0; i < attr.length; i ++) {
                        $("#attr-" + attr[i]).prop("checked", true);
                    }
                });
            }

            loadAttrList();

            $("#newDABtn").click(function() {
                if($(this).attr("data-on") == 'zzz') {
                    return;
                } else if($(this).attr("data-on") == 'xxx') {
                    var attrs = new Array();
                    var tmp = $("input[type='checkbox']:checked").val([]);
                    for(var i = 0; i < tmp.length; i ++) {
                        attrs[i] = tmp[i].value.substr(5);
                    }
                    $.ajax({
                        url: "insertDA.action",
                        type: "post",
                        traditional: true,
                        data: {
                            name: $("#daname").val(),
                            description: $("#dadesc").val(),
                            typeId: $("#datype").val(),
                            code: $("#daattr").val(),
                            attribute: attrs,
                            fightPostionId: $("#fptype").val(),
                            triggertype: "",
                            priority: 0,
                            state: "template",
                            ifAuto: 0,
                            ifdef: 1,
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("新建DA完成");
                            location.reload();
                        },
                    });
                } else {
                    var idx = $(this).attr("data-on");
                    var attrs = new Array();
                    var tmp = $("input[type='checkbox']:checked").val([]);
                    for(var i = 0; i < tmp.length; i ++) {
                        attrs[i] = tmp[i].value.substr(5);
                    }
                    $.ajax({
                        url: "updateDA.action",
                        type: "post",
                        traditional: true,
                        data: {
                            daId: idx,
                            name: $("#daname").val(),
                            description: $("#dadesc").val(),
                            typeId: $("#datype").val(),
                            code: $("#daattr").val(),
                            attribute: attrs,
                            fightPostionId: $("#fptype").val(),
                            triggertype: "",
                            priority: 0,
                            state: "template",
                            ifAuto: 0,
                            ifdef: 1,
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改DA完成");
                            //loadSideBar();
                        },
                    });
                }
            });

            $("#makeDABtn").click(function() {
                $("#newDABtn").attr("data-on", "xxx");
                $("#daname").val("");
                $("#dadesc").val("");
                //$("input").removeAttr("disabled");
                //$("select").removeAttr("disabled");
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
                    <li class="active"><a href="taskDA.jsp">站位执行任务(DA)管理</a></li>
                    <li><a href="taskDS.jsp">条例(DS)管理</a></li>
                    <li><a href="taskCA.jsp">部门复合任务(CA)管理</a></li>
                    <li><a href="taskISP.jsp">任务计划(ISP)管理</a></li>
                </ul>
                <p class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">职位:<%=(String)session.getAttribute("post")%></font></p>
            </div>
            <div class="row">
                <div class="col-md-2 well" style="height: 90%;">
                    <div id="sidebar" style="height: 87%; overflow-y: scroll;">
                    </div>
                    <hr>
                    <button id="makeDABtn" class="btn btn-primary pull-right">新建DA</button>

                </div>
                <div id="view" class="col-md-10 well" style="height: 90%; scroll: overflow;">
                    <div style="height: 40%;"><form class="form-horizontal">
                        <div class="form-group">
                            <label for="daname" class="col-md-2 control-lable">DA名称</label>
                            <div class="col-md-6"><input type="text" id="daname" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label for="dadesc" class="col-md-2 control-lable">DA描述</label>
                            <div class="col-md-6"><input type="text" id="dadesc" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label for="daattr" class="col-md-2 control-lable">属性设置</label>
                            <div class="col-md-6"><input type="text" id="daattr" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label for="datype" class="col-md-2 control-lable">所属类型</label>
                            <div class="col-md-6"><select id="datype" class="form-control">
                                
                            </select></div>
                        </div>
                        <div class="form-group">
                            <label for="fptype" class="col-md-2 control-lable">战位</label>
                            <div class="col-md-6"><select id="fptype" class="form-control">
                                 
                            </select></div>
                        </div>
                    </form></div>
                    <hr>
                    <div id="attrlist" style="height: 40%; overflow-y: scroll;">
                    </div>
                    <div style="height: 10%;">
                        <hr>
                        <div class="pull-right">
                            <button id="newDABtn" class="btn btn-primary" data-on="zzz">确定</button>
                            <button class="btn btn-warning">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
