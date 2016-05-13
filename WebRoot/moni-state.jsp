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
        <%
		Integer daid = Integer.parseInt(request.getParameter("daid"));
	%>
        <link href="static2/css/prettify-1.0.css" rel="stylesheet">
        <link href="static2/css/base.css" rel="stylesheet">
        <link href="static2/css/bootstrap-datetimepicker.css" rel="stylesheet">
        <link href="static2/css/default.css" rel="stylesheet" type="text/css">
        <script src="static2/js/moment-with-locales.js"></script>
        <script src="static2/test.js"></script>

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
		if($("#attrlist").attr("data-page") != -1) {
		var daname='';
		var attr = new Array();
		$.ajax({
			url:"getDAbyId.action",
			data:{daId:$("#attrlist").attr("data-page")},
			dataType:"json",
			success:function(data){
					attr=data[0].attribute;
					daname=data[0].name;
			},
		});
		s1 +='<li role="presentation"><a href="#sourceDA" aria-controls="sourceDA" role="tab" data-toggle="tab">'+daname+'</a></li>';
                s2 += '<div role="tabpanel" class ="tab-pane" id="sourceDA"></div>';
                }
                for(var i = 0; i < type.length; i ++) {
                    s1 += '<li role="presentation"><a href="#source' + type[i].id + '" aria-controls="source' + type[i].id + '" role="tab" data-toggle="tab">'+ type[i].name +'</a></li>';
                    s2 += '<div role="tabpanel" class="tab-pane" id="source'+ type[i].id +'"></div>';
                }
                s1 += '</ul><br />';
                s2 += '</div>';
                $("#attrlist").html(s1 + s2);
		
		if($("#attrlist").attr("data-page") != -1) {
           	var t='';
		for(var i=0;i<attr.length;i++){
			$.ajax({
				url:"getAttrbyId.action",
				data:{atrId:attr[i]},
				dataType:"json",
				success:function(data){
					t+='<div class="col-md-3"><p>&nbsp;'+data.name+':&nbsp;<strong>'+data.value+'</strong></p></div>';
					},
			});
		}
		$("#sourceDA").html(t);
		}
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
                        s += '<div class="col-md-3"> <p>&nbsp;' + attr[i][j].name + ':&nbsp;<strong>' + attr[i][j].value + '</strong></p></div>';
                    }
                    $("#source" + type[i].id).html(s);
                }

                
                
                $("[role='presentation']:first").addClass("active");
                $("[role='tabpanel']:not(:empty):gt(1)").addClass("active");
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
            }

            loadAttrList();

	   

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
                    <ul class="nav nav-pills pull-right">
                        <li><a href="moni-task.jsp">流程监控页面</a></li>
                        <li class="active"><a href="moni-state.jsp">状态监控页面</a></li>
                    </ul>
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
                    </ul>
                    
                </div>
            </div> 
            <div class="row">
                
                <div class="col-md-12 well" style="height: 90%; overflow: scroll;">
                    <div id="attrlist" style="height: 90%; overflow-y: scroll;" data-page="<%=daid%>">
                        
                    </div>
                </div>
                
                
            </div>
        </div>
        
    </body>
</html>
