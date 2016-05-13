<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="jquery-1.9.1.js">
	</script>
		<script
			src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js">
	</script>
	<script type="text/javascript">
	function getExeDa() {                                                  //新建DA模板-->点击新建DA模板按钮
		$.ajax( {
			type : "POST",
			url : "getExe.action",
			dataType : "json",
			traditional : true,
			data : {
				fightPostionId: 1
			},
			success : function(data) {
			alert(data[0]);
			}
		});
	}
	
	function getDAbyState() {                                                  //新建DA模板-->点击新建DA模板按钮
		$.ajax( {
			type : "POST",
			url : "getDAbyState.action",
			dataType : "json",
			traditional : true,
			data : {
				state: "yijihuo"
			},
			success : function(data) {
			alert(data.length);
			}
		});
	}
	
	function getDAbyId() {                                                  //新建DA模板-->点击新建DA模板按钮
		$.ajax( {
			type : "POST",
			url : "getDAbyId.action",
			dataType : "json",
			traditional : true,
			data : {
				daId: 79
			},
			success : function(data) {
			alert(data[0].state);
			}
		});
	}
	
	function insertDA() {                                                  //新建DA模板-->点击新建DA模板按钮
		$.ajax( {
			type : "POST",
			url : "insertDA.action",
			dataType : "json",
			traditional : true,
			data : {
				fightPostionId: 1, 
				usersId: 1, 
				caId: 1, 
				typeId: 1, 
				dsId: null, 
				defbelong: null, 
				name: "zuozhan", 
				description: "haishangzuozhan", 
				triggertype: "zhuangtai", 
				priority: "5", 
				state: "已激活", 
				ifAuto: 1, 
				ifdef: 1, 
				planStart: "2015-07-30 18:56:18", 
				planDuration: null, 
				planEnd: "2015-07-30 18:56:18", 
				terminate: "zhuangtai", 
				excuteStart: null, 
				excuteEnd: null,
				code: null,
				attribute: [1,2],
				eventlogic: "and", 
				eventcode: null, 
				eventatr: [],
				endeventlogic: "and", 
				endeventcode: null, 
				endeventatr: ['1,10,0','2,20,1'],
				preDA: 1,
				preCA: null
			},
			success : function(data) {
			alert(data);
			}
		});
	}
	
		function updateDA() {                                                  //新建DA模板-->点击新建DA模板按钮
		$.ajax( {
			type : "POST",
			url : "updateDA.action",
			dataType : "json",
			traditional : true,
			data : {
				daId: 8,
				fightPostionId: 1, 
				usersId: 1, 
				caId: 1, 
				typeId: 1, 
				dsId: null, 
				defbelong: null, 
				name: "sunguan", 
				description: "haishangsunguan", 
				triggertype: "zhuangtai", 
				priority: "3", 
				state: "daiqueren", 
				ifAuto: 1, 
				ifdef: 1, 
				planStart: null, 
				planDuration: null, 
				planEnd: null, 
				terminate: "zhuangtai", 
				excuteStart: null, 
				excuteEnd: null,
				code: null,
				eventTriggerByEventId: 7,
				eventlogic: "or", 
				eventcode: "code", 
				eventatr: ['1,15,0','2,10,1'],
				eventTriggerByEndEventId: 8,
				endeventlogic: "or", 
				endeventcode: null, 
				endeventatr: ['1,20,1','2,10,0'],
				preDA: 2,
				preCA: null
			},
			success : function(data) {
			alert("yeah");
			}
		});
	}
	
	function getDAPost() {                                                  //新建DA模板-->点击新建DA模板按钮
		$.ajax( {
			type : "POST",
			url : "getDAPost.action",
			dataType : "json",
			traditional : true,
			data : {
				pre: 2
			},
			success : function(data) {
			alert(data[0].daId);
			}
		});
	}
	
	function deleteDA() {                                                  //新建DA模板-->点击新建DA模板按钮
		$.ajax( {
			type : "POST",
			url : "deleteDA.action",
			dataType : "json",
			traditional : true,
			data : {
				daId: 10
			},
			success : function(data) {
			alert(data.length);
			}
		});
	}
	
	function insertCA() {                                                  //新建DA模板-->点击新建DA模板按钮
		$.ajax( {
			type : "POST",
			url : "insertCA.action",
			dataType : "json",
			traditional : true,
			data : { 
				usersId: 1, 
				ispId: 1, 
				typeId: 1,  
				defbelong: null, 
				name: "作战", 
				description: "haishangzuozhan", 
				triggertype: "zhuangtai", 
				priority: "5", 
				state: "yijihuo", 
				ifAuto: 1, 
				ifdef: 1, 
				planStarttime: null,  
				planEndtime: null, 
				terminate: "zhuangtai", 
				excuteStart: null, 
				excuteEnd: null,
				eventlogic: "and", 
				eventcode: null, 
				eventatr: ['1,100,3','2,60,2'],
				endeventlogic: "and", 
				endeventcode: null, 
				endeventatr: ['1,105,1','2,45,3'],
				preDA: 1,
				preCA: null
			},
			success : function(data) {
			alert("yeah");
			}
		});
	}
	
	function updateCA() {                                                 
		$.ajax( {
			type : "POST",
			url : "updateCA.action",
			dataType : "json",
			traditional : true,
			data : {
				caId: 4, 
				usersId: 1, 
				ispId: 1, 
				typeId: 1,  
				defbelong: null, 
				name: "hanghai", 
				description: "haixingrenwu", 
				triggertype: "zhuangtai", 
				priority: "3", 
				state: "daipizhun", 
				ifAuto: 1, 
				ifdef: 1, 
				planStarttime: null,  
				planEndtime: null, 
				terminate: "zhuangtai", 
				excuteStart: null, 
				excuteEnd: null,
				eventTriggerByEventId: 87,
				eventlogic: "and", 
				eventcode: null, 
				eventatr: ['1,13,2','2,63,2'],
				eventTriggerByEndEventId: 88,
				endeventlogic: "and", 
				endeventcode: null, 
				endeventatr: ['1,15,1','2,45,3'],
				preDA: 8,
				preCA: null
			},
			success : function(data) {
			alert("yeah");
			}
		});
	}
	
	function deleteCA() {                                                 
		$.ajax( {
			type : "POST",
			url : "deleteCA.action",
			dataType : "json",
			traditional : true,
			data : {
				caId: 5
			},
			success : function(data) {
			alert("yeah");
			}
		});
	}
	
	function insertDS() {                                                 
		$.ajax( {
			type : "POST",
			url : "insertDS.action",
			dataType : "json",
			traditional : true,
			data : {
				daByTrueExitId: 8,
				daByFalseExitId: 9, 
				daByPreDaId: 3, 
				dstypeId: 2,
				name: "panduanhangsu",
				description: "duihangsujinxingpanduan",
				code: null, 
				ifdef: 0, 
				dsbelong: 0, 
				logic: "AND", 
				attribute:['1,23,2','2,97,2']
			},
			success : function(data) {
			alert("yeah");
			}
		});
	}
	
	function updateDS() {                                                 
		$.ajax( {
			type : "POST",
			url : "updateDS.action",
			dataType : "json",
			traditional : true,
			data : {
				dsId: 5,
				daByTrueExitId: 3,
				daByFalseExitId: 9, 
				daByPreDaId: 8, 
				dstypeId: 1,
				name: "panduanwendu",
				description: "duiwendujinxingpanduan",
				code: null, 
				ifdef: 0, 
				dsbelong: 0, 
				logic: "OR", 
				attribute:['1,45,2','2,91,2']
			},
			success : function(data) {
			alert("yeah");
			}
		});
	}
	
	function deleteDS() {                                                 
		$.ajax( {
			type : "POST",
			url : "deleteDS.action",
			dataType : "json",
			traditional : true,
			data : {
				dsId: 5
			},
			success : function(data) {
			alert("yeah");
			}
		});
	}
	
	function getDSbyCA() {                                                 
		$.ajax( {
			type : "POST",
			url : "getDSbyCA.action",
			dataType : "json",
			traditional : true,
			data : {
				caId: 2
			},
			success : function(data) {
			alert(data.length);
			}
		});
	}
	
	function insertISP() {                                                 
		$.ajax( {
			type : "POST",
			url : "insertISP.action",
			dataType : "json",
			traditional : true,
			data : {
				usersId: 1, 
				name: "dafenglang", 
				description: "dafenglanghangxing", 
				triggertype: "zhuangtai", 
				priority: 5, 
				ifAuto: 0, 
				ifdef: 0, 
				state: "daiqueren", 
				planStarttime: null, 
				planEndtime: null, 
				excuteStarttime: null, 
				excuteEndtime: null, 
				terminate: null, 
				eventlogic: "OR", 
				eventcode: null, 
				eventatr: ['1,15,2','2,55,2'], 
				endeventlogic: null, 
				endeventcode: null, 
				endeventatr:[]
			},
			success : function(data) {
			alert("yeah");
			}
		});
	}
	
	function getExeISP() {                                                 
		$.ajax( {
			type : "POST",
			url : "getExeISP.action",
			dataType : "json",
			traditional : true,
			data : {
				
			},
			success : function(data) {
			alert(data.length);
			}
		});
	}
	
	function getISPbyId() {                                                 
		$.ajax( {
			type : "POST",
			url : "getISPbyId.action",
			dataType : "json",
			traditional : true,
			data : {
				ispId: 3
			},
			success : function(data) {
			alert(data.length);
			}
		});
	}
	
	function DaDef() {                                                 
		$.ajax( {
			type : "POST",
			url : "DaDef.action",
			dataType : "json",
			traditional : true,
			data : {
				daId: 93,
				planStart: "2015-08-18 06:00:00",
				planEnd: "2015-08-19 06:00:00",
				preDA: 100,
				triggertype: "time",
				
			},
			success : function(data) {
			alert(data.length);
			}
		});
	}
	
	function DsDef() {                                                 
		$.ajax( {
			type : "POST",
			url : "DsDef.action",
			dataType : "json",
			traditional : true,
			data : {
				dsId: 26,
				daByTrueExitId: 99,
				daByFalseExitId: 103,
				daByPreDaId: 98,
				
			},
			success : function(data) {
			alert(data.length);
			}
		});
	}
	
	function CaDef() {                                                 
		$.ajax( {
			type : "POST",
			url : "CaDef.action",
			dataType : "json",
			traditional : true,
			data : {
				caId: 64,
				triggertype: "time",
				planStarttime: "2015-08-18 06:00:00",
				planEndtime: "2015-08-19 06:00:00",
				preCA: 57,
				
			},
			success : function(data) {
			
			}
		});
	}
	
	</script>
  </head>
  
  <body>
    LOGIN. <br>
    <form  method="post" action="Login.action">
		用户名：<input name="username" type="text" />
		密码：<input name="password" type="password"  />
		<input name="Submits" type="submit" 	value="登录" /> 				
		<input name="ReSet" type="reset" value="重置" />
</form>
	<button id="newRef" type="button" class="btn btn-default"
			onclick="CaDef()">
			TEST
	</button>
    
  </body>
</html>
