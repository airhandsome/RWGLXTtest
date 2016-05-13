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
<link rel="stylesheet" type="text/css" href="goof/default.css">
<link rel="stylesheet" href="goof/bootstrap.min.css">
<script type="text/javascript" src="goof/GooFunc.js"></script>
<script type="text/javascript" src="goof/bootstrap.min.js"></script>
<title></title>
<!--[if lt IE 9]>
<?import namespace="v" implementation="#default#VML" ?>
<![endif]-->
<script type="text/javascript">
var property={
	width:950,
	height:490,
	toolBtns:["task","fork"],
	haveHead:false,
	headBtns:["save","undo","redo","reload"],//����haveHead=true��������HEAD���İ�ť
	haveTool:false,
	isExe:true,
	haveGroup:true,
	useOperStack:false
};
var remark={
	cursor:"ѡ��ָ��",
	direct:"����˳��",
	task:"��������DA",
	fork:"����DS",
	group:"��֯����CA"
};
var demo;
$(document).ready(function(){
	demo=$.createGooFlow($("#demo"),property);
	demo.setNodeRemarks(remark);
	
	demo.loadCAforExecute(1);
	demo.getNodesId(1);
    demo.getCaRunningState();
	$("[data-toggle='popover']").popover({html:true});
});

</script>
</head>
<body>
<div id="demo"></div>
</body>
</html>
