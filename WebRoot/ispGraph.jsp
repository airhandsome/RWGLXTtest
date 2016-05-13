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
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script id="jquery_183" type="text/javascript" class="library" src="goof/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="goof/default.css">
<script type="text/javascript" src="goof/GooFunc.js"></script>
<title>����ͼDEMO</title>
<!--[if lt IE 9]>
<?import namespace="v" implementation="#default#VML" ?>
<![endif]-->
<script type="text/javascript">
var property={
	width:1240,
	height:830,
	toolBtns:["task","fork"],
	haveHead:true,
	headBtns:["save","undo","redo","reload"],//����haveHead=true��������HEAD���İ�ť
	haveTool:true,
	haveGroup:true,
	useOperStack:true
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
	// demo.onItemDel=function(id,type){
	// 	return confirm("ȷ��Ҫɾ���õ�Ԫ��?");
	// }
	//jsondata={
		//title:"demo",
		//nodes:{
			//demo_node_1:{name:"DA1#809",left:67,top:69,type:"task",width:24,height:24},
			//demo_node_2:{name:"DS1#89",left:219,top:71,type:"fork",width:86,height:24},
			//demo_node_5:{name:"DA2#810",left:380,top:71,type:"task",width:86,height:24}
		//},
		//lines:{
			//demo_line_3:{type:"sl",from:"demo_node_1",to:"demo_node_2",name:"",marked:false},
			//demo_line_6:{type:"sl",from:"demo_node_2",to:"demo_node_5",name:"true",marked:false}
		//},
		//areas:{
			//demo_area_8: {name:"CA1",left:35,top:39,color:"red",width:472,height:114}
		//}
	//};
	//demo.$max=9;
	//demo.loadData(jsondata);
});
</script>
</head>
<body>
<div id="demo" data-focus="-1"></div>
</body>
</html>
