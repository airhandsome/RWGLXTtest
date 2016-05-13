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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script id="jquery_183" type="text/javascript" class="library" src="goof/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="goof/bootstrap.min.css">
<script type="text/javascript" src="goof/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="goof/default.css">
<script type="text/javascript" src="goof/GooFunc.js"></script>


<title>流程图</title>
<!--[if lt IE 9]>
<?import namespace="v" implementation="#default#VML" ?>
<![endif]-->
<script type="text/javascript">
var property={
	width:1240,
	height:780,
	toolBtns:["task","fork"],
	haveHead:true,
	headBtns:["save","undo","redo","reload"],//如果haveHead=true，则定义HEAD区的按钮
	haveTool:true,
	haveGroup:true,
	useOperStack:true
};
var remark={
	cursor:"选择指针",
	direct:"流程顺序",
	task:"任务结点DA",
	fork:"条例DS",
	group:"组织划分CA"
};
var demo;
$(document).ready(function(){
	demo=$.createGooFlow($("#demo"),property);
	demo.setNodeRemarks(remark);
	
	demo.reloadTitle(123);
// 	单击查看详细信息
// 	demo.loadMonitor(123);
// 	demo.getNodesId(1);
// 	demo.loadDataAjax();
//     $("[data-toggle='popover']").popover({html:true});
// 	setInterval(demo.loadDataAjax, 2000);
    
	
});  
</script>
</head>
<body>
<div id="demo" data-focus=""></div>
</body>
</html>
