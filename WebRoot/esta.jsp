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

            function loadSideBar () {

                var s = "<ul style='margin-left:-30'>";
                for(var i = 0; i < TYPE.length; i ++) {
                    s += "<li class='wtf'><a href='#'>" + TYPE[i] + "</a><ul style='margin-left:-20'>";
                    for(var j = 0; j < CA[i].length; j ++) {
                        s += "<li class='wtf2'><a href='#'>" + CA[i][j] + "</a><ul style='margin-left:-20'>";
                        for(var k = 0; k < DA[i][j].length; k ++) {
                            s += "<li id='DA-' class='wtf3'>" + DA[i][j][k] + "</li>";
                        }
                        s += "</ul></li>";
                    }
                    s += "</ul></li><br />";
                }
                s += "</ul>";
                $("#sidebar").html(s);
                $(".wtf").children().not("a").hide();
                $(".wtf2").children().not("a").hide();
                $(".wtf").bind("click", function() {
                    $(this).children().not("a").toggle();
                });
                $(".wtf2").bind("click", function() {
                    $(this).children().not("a").toggle();
                    $(this).parent().parent().children().not("a").toggle();
                });
            }

            loadSideBar();

        });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <br />
            <div class="row" style="height: 7%">
                <ul class="nav nav-pills pull-right">
                    <li id="selectNew" class="active">
                        <a href="esta.jsp">新建任务</a>
                    </li>
                    <li id="selectUse">
                        <a href="invo.jsp">调用案例</a>
                    </li>
                    <li id="selectApp">
                        <a href="appr.jsp"><span id="apprNum" class="badge pull-right">4</span> 审批状态</a>
                    </li>
                    <li id="selectView">
                        <a href="moni-task.jsp">任务监控</a>
                    </li>
                </ul>
                <p class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">职位:<%=(String)session.getAttribute("post")%></font></p>
            </div> 
            <div class="row">
                <div id="sidebar" class="col-md-2 well" style="height: 90%; overflow: scroll;">
                </div>
                <div class="col-md-7 well" style="height: 90%;">
                    <div style="height: 80%; overflow: scroll;">
                        <div class="well">
                            <button class="btn btn-primary pull-right">编辑CA</button>
                            <ul class="list-inline">
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA1</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><i class="icon-cog icon-2x pull-right"></i></li>
                                <li style="width:40;background-color:#FF9900;color:#FFFFFF;">DS1</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><div class="pull-right"><i class="icon-ok"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA2</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><i class="icon-cog icon-2x pull-right"></i></li>
                                <li style="width:40;background-color:#FF9900;color:#FFFFFF;">DS2</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><div class="pull-right"><i class="icon-ok"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA4</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><div class="pull-right"><i class="icon-remove"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA5</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><div class="pull-right"><i class="icon-remove"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA3</li>
                            </ul>
                        </div>
                        <div class="well">
                            <button class="btn btn-primary pull-right">编辑CA</button>
                            <ul class="list-inline">
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA1</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><i class="icon-cog icon-2x pull-right"></i></li>
                                <li style="width:40;background-color:#FF9900;color:#FFFFFF;">DS1</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><div class="pull-right"><i class="icon-ok"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA2</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><i class="icon-cog icon-2x pull-right"></i></li>
                                <li style="width:40;background-color:#FF9900;color:#FFFFFF;">DS2</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><div class="pull-right"><i class="icon-ok"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA4</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><div class="pull-right"><i class="icon-remove"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA5</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><div class="pull-right"><i class="icon-remove"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA3</li>
                            </ul>
                        </div>
                        <div class="well">
                            <button class="btn btn-primary pull-right">编辑CA</button>
                            <ul class="list-inline">
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA1</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><i class="icon-cog icon-2x pull-right"></i></li>
                                <li style="width:40;background-color:#FF9900;color:#FFFFFF;">DS1</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><div class="pull-right"><i class="icon-ok"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA2</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><i class="icon-cog icon-2x pull-right"></i></li>
                                <li style="width:40;background-color:#FF9900;color:#FFFFFF;">DS2</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><div class="pull-right"><i class="icon-ok"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA4</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:200;"><div class="pull-right"><i class="icon-remove"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA5</li>
                            </ul>
                            <ul class="list-inline">
                                <li style="width:100;"><div class="pull-right"><i class="icon-remove"></i><i class="icon-arrow-right"></i></div></li>
                                <li style="width:100;background-color:#0099CC;color:#FFFFFF;">DA3</li>
                            </ul>
                        </div>
                    </div>
                    <div style="height: 10%;">
                        <hr>
                        <div class="pull-right">
                            <button class="btn btn-primary">提交</button>
                            <button class="btn btn-warning">取消</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 well" style="height: 90%; overflow: scroll;">
                    <div id="daform">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务名称
                                </label>
                                <div class="col-sm-8">
                                    <input id="nameISP" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    操作员
                                </label>
                                <div class="col-sm-8">
                                    <input id="xxx" type="text" class="form-control" placeholder=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    起始时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="beginISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    持续时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    触发方式
                                </label>
                                <div class="col-sm-8">
                                    <select class="form-control">
                                        <option value="时间触发">
                                            时间触发
                                        </option>
                                        <option value="条件触发">
                                            条件触发
                                        </option>
                                        <option value="事件触发">
                                            事件触发
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    优先级
                                </label>
                                <div class="col-sm-8">
                                    <select class="form-control">
                                        <option value="时间触发">
                                            1
                                        </option>
                                        <option value="条件触发">
                                            2
                                        </option>
                                        <option value="事件触发">
                                            3
                                        </option>
                                        <option value="时间触发">
                                            4
                                        </option>
                                        <option value="条件触发">
                                            5
                                        </option>
                                        <option value="事件触发">
                                            6
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务描述
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="textarea" class="form-control" />
                                </div>
                            </div>
                            <hr>
                            <p class="pull-right">
                                <!-- <button id="deleISP" type="button" class="btn btn-default" data-focus="">
                                    删除
                                </button> -->
                                <button id="editISP" type="button" class="btn btn-default" data-focus="">
                                    确认
                                </button>
                            </p>
                            <br /><br /><br /><br />
                        </form>
                    </div>
                    <div id="dsform">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    条件名称
                                </label>
                                <div class="col-sm-8">
                                    <input id="nameISP" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    条件内容
                                </label>
                                <div class="col-sm-8">
                                    <input id="xxx" type="text" class="form-control" placeholder=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    前驱DA
                                </label>
                                <div class="col-sm-8">
                                    <input id="beginISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    出口DA1
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    出口DA2
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    有效性
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    条件描述
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="textarea" class="form-control" />
                                </div>
                            </div>
                            <hr>
                            <p class="pull-right">
                                <!-- <button id="deleISP" type="button" class="btn btn-default" data-focus="">
                                    删除
                                </button> -->
                                <button id="editISP" type="button" class="btn btn-default" data-focus="">
                                    确认
                                </button>
                            </p>
                            <br /><br /><br /><br />
                        </form>
                    </div>
                    <div id="caform">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务名称
                                </label>
                                <div class="col-sm-8">
                                    <input id="nameISP" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    操作员
                                </label>
                                <div class="col-sm-8">
                                    <input id="xxx" type="text" class="form-control" placeholder=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    起始时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="beginISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    持续时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    触发方式
                                </label>
                                <div class="col-sm-8">
                                    <select class="form-control">
                                        <option value="时间触发">
                                            时间触发
                                        </option>
                                        <option value="条件触发">
                                            条件触发
                                        </option>
                                        <option value="事件触发">
                                            事件触发
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    优先级
                                </label>
                                <div class="col-sm-8">
                                    <select class="form-control">
                                        <option value="时间触发">
                                            1
                                        </option>
                                        <option value="条件触发">
                                            2
                                        </option>
                                        <option value="事件触发">
                                            3
                                        </option>
                                        <option value="时间触发">
                                            4
                                        </option>
                                        <option value="条件触发">
                                            5
                                        </option>
                                        <option value="事件触发">
                                            6
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务描述
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="textarea" class="form-control" />
                                </div>
                            </div>
                            <hr>
                            <p class="pull-right">
                                <!-- <button id="deleISP" type="button" class="btn btn-default" data-focus="">
                                    删除
                                </button> -->
                                <button id="editISP" type="button" class="btn btn-default" data-focus="">
                                    确认
                                </button>
                            </p>
                            <br /><br /><br /><br />
                        </form>
                    </div>
                    <div id="ispform">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务名称
                                </label>
                                <div class="col-sm-8">
                                    <input id="nameISP" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    起始时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="beginISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    持续时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    优先级
                                </label>
                                <div class="col-sm-8">
                                    <select class="form-control">
                                        <option value="时间触发">
                                            1
                                        </option>
                                        <option value="条件触发">
                                            2
                                        </option>
                                        <option value="事件触发">
                                            3
                                        </option>
                                        <option value="时间触发">
                                            4
                                        </option>
                                        <option value="条件触发">
                                            5
                                        </option>
                                        <option value="事件触发">
                                            6
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    任务描述
                                </label>
                                <div class="col-sm-8">
                                    <input id="durationISP" type="textarea" class="form-control" />
                                </div>
                            </div>
                            <hr>
                            <p class="pull-right">
                                <!-- <button id="deleISP" type="button" class="btn btn-default" data-focus="">
                                    删除
                                </button> -->
                                <button id="editISP" type="button" class="btn btn-default" data-focus="">
                                    确认
                                </button>
                            </p>
                            <br /><br /><br /><br />
                        </form>
                    </div>
                    <br /><br /><br /><br />
                    <br /><br /><br /><br />
                    <ul class="nav nav-pills">
                        <li id="xxda">da</li>
                        <li id="xxds">ds</li>
                        <li id="xxca">ca</li>
                        <li id="xxisp">isp</li>
                    </ul>
                </div>
            </div>
        </div>
        
    </body>
</html>
