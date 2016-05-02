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
<script type="text/javascript" src="goof/GooFunc.js"></script>

<link rel="stylesheet" href="goof/bootstrap.min.css">
<script type="text/javascript" src="goof/bootstrap.min.js"></script>
<title>流程图DEMO</title>
<!--[if lt IE 9]>
<?import namespace="v" implementation="#default#VML" ?>
<![endif]-->
<script type="text/javascript">
var property={
	width:1530,
	height:820,
	toolBtns:["task","fork"],
	haveHead:false,//false
	headBtns:["save","undo","redo","reload"],//如果haveHead=true，则定义HEAD区的按钮
	haveTool:false, //打开timearea
	haveGroup:true,
	useOperStack:false
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
	
	
	var s = 0;
	var rate = 1;
	$.ajax({
			type:"post",
			url:"getISPStartTime.action",
			async:false,
			success: function(data){
				console.log(data);
				s = data[0].totaltime;
				rate = data[0].proportion;
// 				s = parseInt(data);
   			},
   			error:function(){
//    				alert(1);
                   ;
   			}
		});
	console.log("ee ："+s);
	console.log("rate:"+rate);
    
	var AllTime=24*60*60;
    var TimeArea_Wdith=parseInt(1000*rate);//  长度变化需要修改这里就行 这里要重新计算
     $(".tag").css({opacity:"0"});
     
     
 //对固定时间点进行划分
 var num=6; //num是默认的时间点个数，还需同时修改GooFunc.js中的num的判断条件(搜索 时间点)，以及修改css中的location类的width值;
 var location_X=0;
 var n=0;
 var loc_hour,loc_min,loc_sec,theTime;
 for(n=0;n<num;n++){
	   location_X =  $("#lo"+n).position().left;
	    theTime=parseInt(location_X/TimeArea_Wdith*AllTime);
	    theTime+=s;
	    theTime=theTime%AllTime;
	    loc_hour = parseInt(theTime/3600);
	    loc_min = parseInt((theTime -  loc_hour * 3600) / 60);
		loc_sec = theTime - 3600 * loc_hour - 60 *  loc_min;
		if( loc_hour<10)
			 loc_hour="0"+ loc_hour;
		if( loc_min<10)
			 loc_min="0"+ loc_min;
	    if(loc_sec<10)
			loc_sec="0"+loc_sec;
	    $("#lo"+n+ " div").html(loc_hour+":"+loc_min+":"+loc_sec);
//     $("#lo"+n+ " div").html(location_X );
 }
//固定时间点代码 到此为止

    $(".timearea").mouseover(function(e){ //查询时间函数
    var pointX = e.pageX;
    pointX=pointX-$(this).offset().left;
   if(pointX<=TimeArea_Wdith){
    var pointTime=parseInt(pointX/TimeArea_Wdith*AllTime);
    pointTime+=s;
    pointTime=pointTime%AllTime;
    var  ch = parseInt(pointTime/3600);
   
    var  cm = parseInt((pointTime - ch * 3600) / 60);
    
	var cs = pointTime - 3600 * ch - 60 * cm;

	if(cm<10)
	cm="0"+cm;
	if(cs<10)
	cs="0"+cs;
    if(ch<10)
	ch="0"+ch;
//   alert(ch+":"+cm+":"+cs+rate);
    
// 这里可以得到鼠标Y坐标
    var pointY = e.pageY;
    $(".tag").css({opacity:"1"});
    $(".tag").css({"marginLeft":pointX+"px"});
    $(".tag em").css({"Left":pointX+"px"});
    $(".tag span").css({"Left":pointX+"px"});
    $(".tag div").html(ch+":"+cm+":"+cs);
//     $(".tag div").html(pointX);
    

}
   });
    $(".timearea").mouseout(function(){
     $(".tag").css({opacity:"0"});
    
    });
    demo.loadMonitor(123);   //already exist
	demo.getNodesId(1);
	demo.loadDataAjax();
    $("[data-toggle='popover']").popover({html:true});
	setInterval(demo.loadDataAjax, 2000);
    
});

</script>
</head>
<body>
<div id="demo"></div>
</body>
</html>
