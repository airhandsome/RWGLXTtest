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
        <style type="text/css"> .can{min-width:200px; max-width:200px; }</style> 
        <script type="text/javascript">
        $(document).ready(function() {
            function getRand() {
                return parseInt(Math.random() * 10000000);
            }

            function loadSideBar1() {
                type = new Array();
                $.ajax({
                    async: false,
                    url: "getType.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        type = data;
                    },
                });
                CAs = new Array();
                for(var i = 0; i < type.length; i ++) {
                    $.ajax({
                        async: false,
                        url: "getCAbyType.action",
                        data: {typeId: type[i].id},
                        dataType: "json",
                        success: function(data) {
                            CAs[i] = data;
                        },
                    });
                }

                var s = '<ul style="margin-left: -30">';
                for(var i = 0; i < type.length; i ++) {
                    s += '<li class="wtf3"><a>' + type[i].name + '</a><ul>';
                    for(var j = 0; j < CAs[i].length; j ++) {
                        s += '<li class="wtf4"><a id="CA-' + CAs[i][j].id + '" ><i class="icon-wrench"></i> ' + CAs[i][j].name + '</a></li>'
                    }
                    s += '</ul></li><br />';
                }
                s += '</ul>';
                $("#sidebar1").html(s);
                $(".wtf3").children().not("a").hide();
                $(".wtf3").bind("click", function() {
                    $(this).children().not("a").toggle();
                });
                $(".wtf4").bind("click", function() {
                    $(this).children().not("a").toggle();
                    $(this).parent().parent().children().not("a").toggle();
                });
            }

            loadSideBar1();

  	   
                    
            function loadSideBar2() {

                var s1 = '<ul class="nav nav-tabs" role="tablist">';
                var s2 = '<div class="tab-content">';
                s1 += '<li role="presentation" class="active"><a href="#sourceDA" aria-controls="sourceDA" role="tab" data-toggle="tab">DA</a></li>';
                s2 += '<div role="tabpanel" class="tab-pane active" id="sourceDA"></div>';
                s1 += '<li role="presentation"><a href="#sourceDS" aria-controls="sourceDS" role="tab" data-toggle="tab">DS</a></li>';
                s2 += '<div role="tabpanel" class="tab-pane" id="sourceDS"></div>';
                s1 += '</ul><br />';
                s2 += '</div>';
                $("#sidebar2").html(s1 + s2);

                var smodals = '';
                var type = new Array();
                $.ajax({
                    async: false,
                    url: "getType.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        type = data;
                    },
                });
                var DAs = new Array();
                for(var i = 0; i < type.length; i ++) {
                    $.ajax({
                        async: false,
                        url: "getDAbyType.action",
                        data: {typeId: type[i].id},
                        dataType: "json",
                        success: function(data) {
                            DAs[i] = data;
                        },
                    });
                }

                var s = '<ul style="margin-left: -30">';
                for(var i = 0; i < type.length; i ++) {
                    s += '<li class="wtf"><a >' + type[i].name + '</a><ul>';
                    for(var j = 0; j < DAs[i].length; j ++) {
                        s += '<li class="wtf2"><a id="DA-' + DAs[i][j].id + '" href="#modal-container-DA-' + DAs[i][j].id + '" role="button" data-toggle="modal"><i class="icon-dashboard"></i> ' + DAs[i][j].name + '</a></li>';
                        smodals += '<div class="modal fade" id="modal-container-DA-' + DAs[i][j].id + '" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-desc="' + DAs[i][j].description + '" data-type="' + DAs[i][j].Type + '"><div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> <h4 class="modal-title" id="myModalLabel">' + DAs[i][j].name + '</h4> </div> <div class="modal-body"> '+
                        '<form class="form-horizontal" role="form"> '+
                        '<div class="form-group"> <label class="col-sm-3 control-label"> 触发方式 </label> <div class="col-sm-9"><select id="trigger-' + DAs[i][j].id + '" class="form-control"> <option value="time" selected="selected">时间触发</option> <option value="event">事件触发</option> <option value="state">状态触发</option> </select></div> </div>'+ 
                        '<div class="form-group form-time"> <label class="col-sm-3 control-label"> 开始时间 </label> <div class="col-sm-9"> <input type="text" class="form-control" id="planStarttime-' + DAs[i][j].id + '"/> </div> </div>'+
                        '<div class="form-group form-time"> <label class="col-sm-3 control-label"> 结束时间 </label> <div class="col-sm-9"> <input id="planEndtime-' + DAs[i][j].id + '" type="text" class="form-control" /> </div> </div>' +
                        '<div class="form-group form-event" style="display: none;"> <label class="col-sm-3 control-label"> 前驱DA编号 </label> <div class="col-sm-9"><select id="dalistm-' + DAs[i][j].id + '" class="form-control"> </select></div> </div>'+
                        '<div class="form-group form-state" style="display: none;"> <label class="col-sm-3 control-label"> 触发状态 </label> <div class="col-sm-9"><select id="xattr-' + DAs[i][j].id + '" class="form-control"> </select></div> </div>'+
                        '<div class="form-group form-state" style="display: none;"> <label class="col-sm-3 control-label"> 触发逻辑 </label> <div class="col-sm-9"><select id="xlogic-' + DAs[i][j].id + '" class="form-control"><option value="1">=</option><option value="2">&gt;</option><option value="3">&lt;</option><option value="4">&ge;</option><option value="5">&le;</option> </select></div> </div>'+
                        '<div class="form-group form-state" style="display: none;"> <label class="col-sm-3 control-label"> 触发数值 </label> <div class="col-sm-9"><input id="xval-' + DAs[i][j].id + '" type="text" class="form-control" /></div> </div>'+
                        '</form> </div> <div class="modal-footer"> <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button data-xx="DA' + DAs[i][j].id + '" type="button" class="btn btn-primary" data-dismiss="modal">保存</button> </div> </div> </div> </div>';
                    }
                    s += '</ul></li><br />';
                }
                s += '</ul>';
                $("#sourceDA").html(s);
		
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

                s = '<ul style="margin-left: -30">';
                for(var i = 0; i < typeDS.length; i ++) {
                    s += '<li class="wtf"><a >' + typeDS[i].name + '</a><ul>';
                    for(var j = 0; j < DSs[i].length; j ++) {
                        s += '<li class="wtf2"><a id="DS-' + DSs[i][j].id + '" href="#modal-container-DS-' + DSs[i][j].id + '" role="button" data-toggle="modal"><i class="icon-cogs"></i> ' + DSs[i][j].name + '</a></li>';
                        smodals += '<div class="modal fade" id="modal-container-DS-' + DSs[i][j].id + '" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-desc="' + DSs[i][j].description + '" data-type="' + DSs[i][j].Type + '"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> <h4 class="modal-title" id="myModalLabel">' + DSs[i][j].name + '</h4> </div> <div class="modal-body"> <form class="form-horizontal" role="form"> <div class="form-group"> <label class="col-sm-3 control-label"> 前驱DA编号 </label> <div class="col-sm-9"><select id="dalistx-' + DSs[i][j].id + '" class="form-control"> </select></div> </div> <div class="form-group"> <label class="col-sm-3 control-label"> 出口DA编号1 </label> <div class="col-sm-9"><select id="dalisty-' + DSs[i][j].id + '" class="form-control"> </select></div> </div> <div class="form-group"> <label class="col-sm-3 control-label"> 出口DA编号2 </label> <div class="col-sm-9"><select id="dalistz-' + DSs[i][j].id + '" class="form-control"> </select></div> </div> </form> </div> <div class="modal-footer"> <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button data-xx="DS' + DSs[i][j].id + '" type="button" class="btn btn-primary" data-dismiss="modal">保存</button> </div> </div> </div> </div>';
                    }
                    s += '</ul></li><br />';
                }
                s += '</ul>';
                $("#sourceDS").html(s);
                $("#modals").html(smodals);


		$("[id^='trigger-']").bind("change", function() {
		    var typename = $(this).children("option:selected").val();
		    $(".form-time").hide();
		    $(".form-event").hide();
		    $(".form-state").hide();
		    $(".form-" + typename).show();
		});

                $("[id^='dalistm']").append('<option value="xxxxxxxx">---</option>');

                //$("#sidebar2").html(s);
                $(".wtf").children().not("a").hide();
                $(".wtf").bind("click", function() {
                    $(this).children().not("a").toggle();
                });
                $(".wtf2").bind("click", function() {
                    $(this).children().not("a").toggle();
                    $(this).parent().parent().children().not("a").toggle();
                });

                s = '';
                for(var i = 0; i < type.length; i ++) {
                    s += '<option value="' + type[i].id + '">' + type[i].name + '</option>';
                }
                $("#typelist").html(s);


                $.ajax({
                    async: false,
                    url: "getSystem.action",
                    data: {},
                    dataType: "json",
                    success: function(data) {
                        type = data;
                    },
                });

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
                for(var i = 0; i < attr.length; i ++) {
                    for(var j = 0; j < attr[i].length; j ++) {
                        $("[id^='xattr-']").append('<option value="' + attr[i][j].id + '">' + attr[i][j].name + '</option>');
                    }
                    $("[id^='xattr-']").append('<option disabled>---</option>');
                }
            }

            loadSideBar2();

            $("[id^='CA-']").click(function() {
                var btn = $(this);
                var idx = btn.attr("id").substr(3);
                $("#newCABtn").attr("data-caid", idx);
                var DA = new Array();
                var DS = new Array();
                $.ajax({
                    async: false,
                    url: "getDAbyCA.action",
                    data: {caId: parseInt(idx)},
                    dataType: "json",
                    success: function(data) {
                        DA = data;
                    },
                });
                $.ajax({
                    async: false,
                    url: "getDSbyCA.action",
                    data: {caId: parseInt(idx)},
                    dataType: "json",
                    success: function(data) {
                        DS = data;
                    },
                });
                $("#DAtable").empty();
                $("#DStable").empty();
                for(var i = 0; i < DA.length; i ++) {
                    var uid = getRand();
                    var s = '<tr id="XDA-' + DA[i].id + '"> <td>' + DA[i].id + '</td> <td class="daname">' + DA[i].name + '</td> <td>' + DA[i].planStart + '</td> <td>' + DA[i].planEnd + '</td> <td>' + DA[i].triggertype + '</td> <td><a><i id="DA' + uid + '" class="icon-remove"></i></a></td> </tr>';
                    $("#DAtable").append(s);
                    bindRemoveBtn(0, uid);
                }
                for(var i = 0; i < DS.length; i ++) {
                    var uid = getRand();
                    var s = '<tr id="XDS-' + DS[i].id + '"> <td>' + (i + 1) + '</td> <td>' + DS[i].name + '</td> <td>' + DS[i].preDA + '</td> <td>' + DS[i].trueExit + '</td> <td>' + DS[i].falseExit + '</td> <td><a><i id="DS' + uid + '" class="icon-remove"></i></a></td> </tr>';
                    $("#DStable").append(s);
                    bindRemoveBtn(1, uid);
                }
            });
    
            function bindRemoveBtn(tp, uid) {
                if(tp == 0) {
                    $("#DA" + uid).bind("click", function() {
                        var da = $(this).parent().parent().parent().attr("id").substr(4);
                        $.ajax({
                            async: false,
                            url: "deleteDA.action",
                            data: {
                                daId: da,
                                caId: $("#newCABtn").attr("data-caid"),
                            },
                            dataType: "json",
                            success: function(data2) {
                                if(data2 == 1) {
                                    $("#XDA-" + da).remove();
                                    $("option[value='" + da + "']").remove();
                                    $("#DStable").empty();
                                    var DS = new Array();
                                    $.ajax({
                                        async: false,
                                        url: "getDSbyCA.action",
                                        data: {caId: $("#newCABtn").attr("data-caid")},
                                        dataType: "json",
                                        success: function(data) {
                                            DS = data;
                                        },
                                    });
                                    for(var i = 0; i < DS.length; i ++) {
                                        var uidx = getRand();
                                        var s = '<tr id="XDS-' + DS[i].id + '"> <td>' + (i + 1) + '</td> <td>' + DS[i].name + '</td> <td>' + DS[i].preDA + '</td> <td>' + DS[i].trueExit + '</td> <td>' + DS[i].falseExit + '</td> <td><a><i id="DS' + uidx + '" class="icon-remove"></i></a></td> </tr>';
                                        $("#DStable").append(s);
                                        bindRemoveBtn(1, uidx);
                                    }
                                } else {
                                    alert("无法删除");
                                }
                            }
                        });
                    });
                } else {
                    $("#DS" + uid).bind("click", function() {
                        var ds = $(this).parent().parent().parent().attr("id").substr(4);
                        $.ajax({
                            async: false,
                            url: "deleteDS.action",
                            data: {
                                dsId: ds,
                                caId: $("#newCABtn").attr("data-caid"),
                            },
                            dataType: "json",
                            success: function(data2) {
                                if(data2 == 1) {
                                    $("#XDS-" + ds).remove();
                                    $("option[value='" + ds + "']").remove();
                                } else {
                                    alert("无法删除");
                                }
                            }
                        });
                    });
                }
            }

            $("button[data-xx^='DA']").bind("click", function() {
                var btn = $(this);
                var idx = btn.attr("data-xx").substr(2);
                //var cont = $("#modal-container-DA-" + idx);
                var pre = null;
                if($("#dalistm-" + idx).val().length != 8) {
                    pre = $("#dalistm-" + idx).val();
                }
                $.ajax({
                    url: "DaDef.action",
                    type: "post",
                    traditional: true,
                    data: {
                        daId: idx,
                        caId: $("#newCABtn").attr("data-caid"),
                        preDA: pre,
                        triggertype: $("#trigger-" + idx).val(),
                        planStart: $("#planStarttime-" + idx).val(),
                        planEnd: $("#planEndtime-" + idx).val(),
                        startattributeId: $("#xattr-" + idx).val(),
                        startsymbol: $("#xlogic-" + idx).val(),
                        startvalue: $("#xval-" + idx).val(),
                    },
                    dataType: "json",
                    success: function(data) {
                        //alert(data);
                        var uid = getRand();
                        var da = data;
                        var leng = $("#DAtable").children().length;
                        var s = '<tr id="XDA-' + da + '"> <td>' + da + '</td> <td class="daname">' + $("#DA-" + idx).text() + '</td> <td>' + $("#planStarttime-" + idx).val() + '</td> <td>' + $("#planEndtime-" + idx).val() + '</td> <td>' + $("#trigger-" + idx).val() + '</td> <td><a><i id="DA' + uid + '" class="icon-remove"></i></a></td> </tr>';
                        $("#DAtable").append(s);
                        bindRemoveBtn(0, uid);
                        s = '<option value="' + da + '">' + '#' + da + ': ' + $("#DA-" + idx).text() + '</option>';
                        $("[id^='dalist']").append(s);
                    },
                });
            });
        
            $("button[data-xx^='DS']").click(function() {
                var btn = $(this);
                var idx = btn.attr("data-xx").substr(2);
                //var cont = $("#modal-container-DS-" + idx);
                $.ajax({
                    url: "DsDef.action",
                    type: "post",
                    traditional: true,
                    data: {
                        caId: $("#newCABtn").attr("data-caid"),
                        dsId: idx,
                        daByPreDaId: $("#dalistx-" + idx).val(),
                        daByTrueExitId: $("#dalisty-" + idx).val(),
                        daByFalseExitId: $("#dalistz-" + idx).val(),
                    },
                    dataType: "json",
                    success: function(data) {
                        //alert(data);
                        var uid = getRand();
                        var ds = data;
                        var leng = $("#DStable").children().length;
                        var s = '<tr id="XDS-' + ds + '"> <td>' + (leng + 1) + '</td> <td>' + $("#DS-" + idx).text() + '</td> <td>' + $("#dalistx-" + idx).val() + '</td> <td>' + $("#dalisty-" + idx).val() + '</td> <td>' + $("#dalistz-" + idx).val() + '</td> <td><a><i id="DS' + uid + '" class="icon-remove ds"></i></a></td> </tr>';
                        //s += t + t + t + '<td><a href="#remove"><i class="icon-remove"></i></a></td> </tr>'
                        $("#DStable").append(s);
                        bindRemoveBtn(1, uid);
                    },
                });
            });

            $("#previewBtn").click(function() {
                $("#viewcax").toggle();
                $("#viewline").toggle();
                var DS = new Array();
                var DA = new Array();
                $.ajax({
                    async: false,
                    url: "getDSbyCA.action",
                    data: {caId: $("#newCABtn").attr("data-caid")},
                    dataType: "json",
                    success: function(data) {
                        DS = data;
                    },
                });
                $.ajax({
                    async: false,
                    url: "getDAbyCA.action",
                    data: {caId: $("#newCABtn").attr("data-caid")},
                    dataType: "json",
                    success: function(data) {
                        DA = data;
                    }
                });
                var jsonx = new Object();
                jsonx.DA = new Array();
                jsonx.DS = new Array();
                
                for(var i = 0; i < DS.length; i ++) {
                    jsonx.DS[i] = {id: DS[i].id, name: DS[i].name, preDA: DS[i].preDA, trueExitDA: DS[i].trueExit, falseExitDA: DS[i].falseExit};
                }
                for(var i = 0; i < DA.length; i ++) {
                    var preDS = 0;
                    var preDA = 0;
                    for(var j = 0; j < DS.length; j ++) {
                        if(DS[j].trueExit == DA[i].id || DS[j].falseExit == DA[i].id) {
                            preDS = DS[j].id;
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
                        jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100};
                    } else if(preDA === 0) {
                        jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDS: preDS};
                    } else if(preDS === 0) {
                        jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDA: preDA};
                    } else {
                        jsonx.DA[i] = {id: DA[i].id, name: DA[i].name, length: 100, preDA: preDA, preDS: preDS};
                    }
                }
                P(jsonx, "cawell");
                $.ajax({
                    url: "getCAbyId.action",
                    data: {caId: $("#newCABtn").attr("data-caid")},
                    dataType: "json",
                    success: function(data) {
                        $("#nameCA").val(data.name);
                        $("#descCA").val(data.description);
                    },
                });
            });

            $("#reverseBtn").click(function() {
                $("#viewline").toggle();
                $("#viewcax").toggle();
            });

            $("#newCABtn").click(function() {
                $("[id^='sidebar']").toggle();
                $("#modal-164752").hide();
                $.ajax({
                    url: "insertCA.action",
                    traditional: true,
                    type: "post",
                    data: {
                        typeId: $("#typelist").val(),
                        name: $("#CAname").val(),
                        description: $("#CAdesc").val(),
                        triggertype: "",
                        priority: 0,
                        state: "template",
                        ifAuto: 0,
                        ifdef: 1,
                    },
                    dataType: "json",
                    success: function(data) {
                        $("#newCABtn").attr("data-caid", data);
                        $("#DAtable").empty();
                        $("#DStable").empty();
                    },
                });
            });

            $("#confirmBtn").click(function() {
                alert("新建CA完成");
                location.reload();
            });

            $("#cancelBtn").click(function() {
                $.ajax({
                    url: "deleteCA.action",
                    data: {caId: $("#newCABtn").attr("data-caid")},
                    dataType: "json",
                    success: function(data) {
                        $("tbody").empty();
                        alert("删除完成");
                        //$("[id^='sidebar']").toggle();
                    },
                });
            });

            $("#spcBtn").click(function() {
                var idx = $("#spcBtn").attr("data-focus");
                if(idx[1] == 'A') {
                    idx = idx.substr(2);
                    var attr = new Array();
                    var type = 0;
                    $.ajax({
                        async: false,
                        url: "getDAbyId.action",
                        data: {daId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            type = data.Type;
                            attr = data.attribute;
                        },
                    });
                    
                    $.ajax({
                        async: false,
                        url: "updateDA.action",
                        type: "post",
                        traditional: true,
                        data: {
                            daId: idx,
                            name: $("#nameDA").val(),
                            description: $("#descDA").val(),
                            typeId: type,
                            code: $("#codeDA").val(),
                            attribute: attr,
                            fightPostionId: 1,
                            priority: 0,
                            state: "instance",
                            ifAuto: 0,
                            ifdef: 0,
                            planStart: $("#startDA").val(),
                            planEnd: $("#endDA").val(),
                            triggertype: $("#triggerDA").val(),
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改完成");
                        },
                    });


                } else if(idx[1] == 'S') {
                    idx = idx.substr(2);
                    var attr = $("[id^='sel-']").attr("id");
                    //alert(attr);
                    var atr = new Array();
                    for(var i = 0; i < attr.length; i ++) {
                        var xid = attr[i].attr("id").substr(4);
                        var sel = $("#sel-" + xid).val();
                        var txt = $("#txt-" + xid).val();
                        atr[i] = [xid, sel, txt];
                    }

                    var name, desc, type;
                    $.ajax({
                        url: "getDSbyId.action",
                        data: {dsId: idx},
                        dataType: "json",
                        success: function(data) {
                            data = data[0];
                            name = data.name;
                            desc = data.description;
                            type = data.Type;
                        },
                    });

                    $.ajax({
                        url: "updateDS.action",
                        traditional: true,
                        data: {
                            name: name,
                            description: desc,
                            dstypeId: type,
                            attribute: atr,
                            ifdef: 0,
                            dsbelong: 0,
                            logic: "",
                        },
                        dataType: "json",
                        success: function(data) {
                            alert("修改成功");
                        },
                    });
                    
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
                    <li><a href="taskDS.jsp">条例(DS)管理</a></li>
                    <li class="active"><a href="taskCA.jsp">部门复合任务(CA)管理</a></li>
                    <li><a href="taskISP.jsp">任务计划(ISP)管理</a></li>
                </ul>
                <p class="pull-left">&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">职位:<%=(String)session.getAttribute("post")%></font></p>
            </div>
            <div class="row">
                <div class="col-md-2 well" style="height: 90%;">
                    <div id="sidebar1" style="height: 87%; overflow-y: scroll;">
                    </div>
                    <div id="sidebar2" style="height: 87%; overflow-y: scroll; display: none;">
                    </div>
                    <hr>
                    <div id="modals"></div>
                    <a id="modal-164752" href="#modal-container-164752" role="button" class="btn btn-primary pull-right" data-toggle="modal">新建CA</a>
                    <div class="modal fade" id="modal-container-164752" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">新建复合任务(CA)</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">
                                                任务名称
                                            </label>
                                            <div class="col-sm-9">
                                                <input id="CAname" type="text" class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">
                                                任务描述
                                            </label>
                                            <div class="col-sm-9">
                                                <input id="CAdesc" type="text" class="form-control" placeholder=""/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">
                                                所属域
                                            </label>
                                            <div class="col-sm-9"><select id="typelist" class="form-control">
                                                <option>1</option>
                                            </select></div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
                                    <button id="newCABtn" type="button" class="btn btn-primary" data-dismiss="modal" data-caid="">保存</button>
                                </div>
                            </div>
                        </div>          
                    </div>
                </div>
                <div id="viewcax" class="col-md-10 well" style="height: 90%;">
                    <div style="height: 87%; overflow: scroll;">
                        <table class="table table-bordered">
                            <thead><tr> 
                                <th>DA编号</th>
                                <th>任务名称</th>
                                <th>起始时间</th>
                                <th>结束时间</th>
                                <th>触发方式</th>
                                <th>&nbsp;</th>
                            </tr></thead> 
                            <tbody id="DAtable">
                                
                            </tbody>
                        </table>
                        <hr>
                        <table class="table table-bordered">
                            <thead><tr> 
                                <th>DS编号</th>
                                <th>条件名称</th>
                                <th>前驱DA编号</th>
                                <th>出口DA编号1</th>
                                <th>出口DA编号2</th>
                                <th>&nbsp;</th>
                            </tr></thead> 
                            <tbody id="DStable">
                                
                            </tbody>
                        </table>
                    </div>
                    <div style="height: 10%;">
                        <hr>
                        <div class="pull-right">
                            <button id="previewBtn" class="btn btn-primary">预览</button>
                            <button id="cancelBtn" class="btn btn-warning">删除</button>
                        </div>
                    </div>
                </div>
                <div id="viewline" style="display: none;">
                    <div class="col-md-7 well" style="height: 90%;">
                        <div style="height: 90%; overflow: scroll;">
                            <canvas id="cawell" width="1600" height="1600" onclick="getidx(show(event,'cawell'));"></canvas>
                        </div>
                        <div style="height: 10%">
                            <hr>
                            <div class="pull-right">
                                <button id="confirmBtn" class="btn btn-primary">确定</button>
                                <button id="reverseBtn" class="btn btn-warning">返回</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 well" style="height: 90%;">
                        <div style="height: 90%;"><form id="daform" class="form-horizontal" role="form" style="display: none;">
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
                                    <input id="startDA" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">
                                    结束时间
                                </label>
                                <div class="col-sm-8">
                                    <input id="endDA" type="text" class="form-control" />
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
                            <div id="attrapp"></div>
                            
                        </form></div>
                        <div style="height: 10%;">
                            <hr>
                            <p class="pull-right">
                                <button id="spcBtn" type="button" class="btn btn-primary" data-focus="">
                                    确认
                                </button>
                            </p>
                            <br /><br /><br /><br />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                $("[id^='planStarttime']").datetimepicker({
                    format: 'YYYY-MM-DD HH:mm:ss'
                });
                $("[id^='planEndtime']").datetimepicker({
                    format: 'YYYY-MM-DD HH:mm:ss'
                });
            });

            function getidx(idx) {
                $("[id$='form']").hide();
                if(idx[1] == 'A') {
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
                                    '<input id="' + x[0] + '" type="text" class="form-control" value="' + name + '" readonly />' +
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
        </script>
    </body>
</html>
