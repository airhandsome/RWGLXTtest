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

            function loadSideBar() {
                var typeDS = new Array();
                $.ajax({
                    async: false,
                    url: "getDSType.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        typeDS = data;
                    },
                });
                var DSs = new Array();
                for(var i = 0; i < typeDS.length; i ++) {
                    $.ajax({
                        async: false,
                        url: "getDSbyType.action",
                        data: {dstypeId: typeDS[i].id},
                        dataType: "json",
                        success: function(data) {
                            DSs[i] = data;
                        },
                    });
                }

                var s = '<ul style="margin-left: -30">';
                for(var i = 0; i < typeDS.length; i ++) {
                    s += '<li class="wtf"><a>' + typeDS[i].name + '</a><ul>';
                    for(var j = 0; j < DSs[i].length; j ++) {
                        s += '<li class="wtf2"><a id="DS-' + DSs[i][j].id + '" >' + DSs[i][j].name + '</a></li>'
                    }
                    s += '</ul></li><br />';
                }
                s += '</ul>';
                $("#sidebar").html(s);
                $(".wtf").children().not("a").hide();
                $(".wtf").bind("click", function() {
                    $(this).children().not("a").toggle();
                });
                $(".wtf2").bind("click", function() {
                    $(this).children().not("a").toggle();
                    $(this).parent().parent().children().not("a").toggle();
                });

                s = '';
                for(var i = 0; i < typeDS.length; i ++) {
                    s += '<option value="' + typeDS[i].id + '">' + typeDS[i].name + '</option>';
                }
                $("#dstype").html(s);
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
                    }
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
                        s += '<div class="col-md-3">' +
                                  '<div class="input-group">' + 
                                      '<span class="input-group-addon"><input type="checkbox" value="attr-' + attr[i][j].id + '"></span>' + 
                                      '<span class="input-group-addon">' + attr[i][j].name + '</span>' + 
                                      '<span class="input-group-addon"><select id="sel-' + attr[i][j].id + '">' + 
                                          '<option value="1">=</option>' +
                                          '<option value="2">&gt;</option>' +
                                          '<option value="3">&lt;</option>' +
                                          '<option value="4">&ge;</option>' +
                                          '<option value="5">&le;</option>' +
                                      '</select></span>' +

                                      '<input id="txt-' + attr[i][j].id + '" type="text" class="form-control" value="50">' +
                                '</div>' + 
                            '</div>';
                    }
                    $("#source" + type[i].id).html(s);
                }
                
                $("[role='presentation']:first").addClass("active");
                $("[role='tabpanel']:first").addClass("active");

                //$("input").attr("disabled", "disabled");
                $("input[value^='attr-']").removeAttr("checked");
                //$("select").attr("disabled", "disabled");
            }

            loadAttrList();

            $("#newDSBtn").click(function() {
                if($(this).attr("data-on") == 'zzz') {
                    return;
                } else if($(this).attr("data-on") == 'xxx') {
                    var attrs = new Array();
                    var tmp = $("input[type='checkbox']:checked").val([]);
                    for(var i = 0; i < tmp.length; i ++) {
                        var idx = tmp[i].value.substr(5);
                        var sig = $("#sel-" + idx).val();
                        var vl = $("#txt-" + idx).val();
                        attrs[i] = idx + ',' + sig + ',' + vl;
                    }
                    $.ajax({
                        url: "insertDS.action",
                        traditional: true,
                        type: "post",
                        data: {
                            name: $("#dsname").val(),
                            description: $("#dsdesc").val(),
                            dstypeId: $("#dstype").val(),
                            attribute: attrs,
                            ifdef: 1,
                            dsbelong: 0,
                            logic: "",
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("新建DS完成");
                            location.reload();
                        },
                    });
                } else {
                    var attrs = new Array();
                    var tmp = $("input[type='checkbox']:checked").val([]);
                    for(var i = 0; i < tmp.length; i ++) {
                        var idx = tmp[i].value.substr(5);
                        var sig = $("#sel-" + idx).val();
                        var vl = $("#txt-" + idx).val();
                        attrs[i] = idx + ',' + sig + ',' + vl;
                    }
                    $.ajax({
                        url: "updateDS.action",
                        traditional: true,
                        type: "post",
                        data: {
                            dsId: $(this).attr("data-on"),
                            name: $("#dsname").val(),
                            description: $("#dsdesc").val(),
                            dstypeId: $("#dstype").val(),
                            attribute: attrs,
                            ifdef: 1,
                            dsbelong: 0,
                            logic: "",
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改DS完成");
                        },
                    });
                }
            });

            $("#makeDSBtn").click(function() {
                $("#newDSBtn").attr("data-on", "xxx");
                $("#dsname").val("");
                $("#dsdesc").val("");
                //$("input").removeAttr("disabled");
                //$("select").removeAttr("disabled");
            });

            $("[id^='DS-']").bind("click", function() {
                var btn = $(this);
                var idx = btn.attr("id").substr(3);
                //$("input").attr("disabled", "disabled");
                //$("select").attr("disabled", "disabled");
                $("input[value^='attr-']").removeAttr("checked");
                $("#newDSBtn").attr("data-on", idx);
                var attr = new Array();
                $.ajax({
                    async: false,
                    url: "getDSbyId.action",
                    data: {dsId: idx},
                    dataType: "json",
                    success: function(data) {
                        data = data[0];
                        $("#dsname").val(data.name);
                        $("#dsdesc").val(data.description);
                        $("#dstype").val(data.Type);
                        attr = data.attribute;
                    },
                });
                for(var i = 0; i < attr.length; i ++) {
                    var x = attr[i].split(",")
                    $("input[value='attr-" + x[0] + "']").prop("checked", true);
                    $("#sel-" + x[0]).val(x[1]);
                    $("#txt-" + x[0]).val(x[2]);
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
                    <li class="active"><a href="taskDS.jsp">条例(DS)管理</a></li>
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
                    <button id="makeDSBtn" class="btn btn-primary pull-right">新建DS</button>

                </div>
                <div id="view" class="col-md-10 well" style="height: 90%; scroll-y: overflow;">
                    <div style="height: 30%;"><form class="form-horizontal">
                        <div class="form-group">
                            <label for="dsname" class="col-md-2 control-lable">DS名称</label>
                            <div class="col-md-6"><input type="text" id="dsname" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label for="dsdesc" class="col-md-2 control-lable">DS描述</label>
                            <div class="col-md-6"><input type="text" id="dsdesc" class="form-control"></div>
                        </div>
                        <div class="form-group">
                            <label for="dstype" class="col-md-2 control-lable">所属类型</label>
                            <div class="col-md-6"><select id="dstype" class="form-control">
                                
                            </select></div>
                        </div>
                    </form></div>
                    <hr>
                    <div id="attrlist" style="height: 50%; overflow: scroll;">
                        
                    </div>
                    <hr>
                    <div style="height: 10%;">
                        <div class="pull-right">
                            <button id="newDSBtn" class="btn btn-primary" data-on="xxx">确定</button>
                            <button class="btn btn-warning">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
