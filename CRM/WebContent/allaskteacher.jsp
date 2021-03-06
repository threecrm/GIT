<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.4.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.4.3/themes/default/easyui.css">
<script type="text/javascript"
	src="js/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		$.post("updateAskStatue", {}, function(res) {
			init();
		});

		//下拉框(一加载就执行)
		$('#roleNames').combobox({
			url : 'selectA',
			method : "post",
			valueField : 'roleName',
			textField : 'roleName'
		});
		//下拉框(一加载就执行)
		$('#updateroleNames').combobox({
			url : 'selectA',
			method : "post",
			valueField : 'roleName',
			textField : 'roleName'
		});
		$('#dd').dialog({    
		    closed: true,
		    cache: false
		});
	})

	function init() {
		$("#tab").datagrid({
			url : 'selectAskAll',
			method : 'post',
			pagination : true,
			singleSelect:true,
			fitColumns:true,
			toolbar : '#seachid',
			queryParams : {
				askName : $("#askName").val(),
				roleNames : $("#roleNames").combobox("getValue"),
				Statue : $("#QianDaostatue").combobox("getValue")
			}
		})
	}
	function formatterSet(value, row, index) {
		return " <a href='javascrip:void(0)' onclick='update(" + index
				+ ")'>编辑</a>  "
				+ " <a href='javascrip:void(0)'  onclick='look(" + index
				+ ")'>网络学生</a> "
				+ "<a href='javascrip:void(0)'  onclick='qiandao(" + index
				+ ")'>签到</a> "
				+ "<a href='javascrip:void(0)'  onclick='qiantui(" + index
				+ ")'>签退</a> <a href='javascrip:void(0)'  onclick='message("
				+ index + ")'>消息</a>"
	}
	var u = "<%=session.getAttribute("uid")%>";
	var webscoket = new WebSocket("ws:localhost:8080/CRM/webscoket/" + u);
	function send() {
		var data = $("#tab").datagrid("getSelected");
		$.post("selectUsersNameMessage", {
			Name : data.askName
		}, function(d) {
			var da = $("#message").val();
			webscoket.send("" + u + "," + d.uid + "," + da + "");
			$('#dd').dialog({
				title : '消息',
				width : 400,
				height : 200,
				closed : true,
				cache : false
			})
			alert("提示成功")
		})
	}
	function message(index) {
		$('#dd').dialog({
			title : '消息',
			width : 400,
			height : 200,
			closed : false,
			cache : false,
			modal : true,
			buttons : [ {
				text : '发送',
				handler : function() {
					send();
				}
			}, {
				text : '关闭',
				handler : function() {

				}
			} ]
		});
	}
	//查看网络学生
	function look(index) {
		var date = $("#tab").datagrid("getData");
		var row = date.rows[index];
		$("#netaskteacherTab").datagrid({
			url : 'selectStudent',
			method : 'post',
			queryParams : {
				askId : row.askId
			}
		})
		$("#netaskteacher").window("open");
	}
	//签到
	function qiandao(index) {
		var date = $("#tab").datagrid("getData");
		var row = date.rows[index];
		$.post("updateQianDao", {
			askId : row.askId,
			askName : row.askName
		}, function(res) {
			if (res > 0) {
				if (res == 2) {
					$.messager.alert("提示", "该用户已经签到过了");
				} else if (res == 3) {
					$.messager.alert("提示", "上班时间还没到哦");
				} else {
					$.messager.alert("提示", "签到成功");
					$("#tab").datagrid("reload");
				}

			} else {
				if (res == -1) {
					$.messager.alert("提示", "签到成功,下次注意上班时间不要再迟到了哦");
					$("#tab").datagrid("reload");
				} else {
					$.messager.alert("提示", "签到失败");
				}

			}
		}, "json")
	}
	//签退
	function qiantui(index) {
		var date = $("#tab").datagrid("getData");
		var row = date.rows[index];
		$.post("updateQianTui", {
			askId : row.askId,
			askName : row.askName
		}, function(res) {
			if (res == 1) {
				$.messager.alert("提示", "下班啦,签退成功");
				$("#tab").datagrid("reload");
			} else if (res == 0) {
				$.messager.alert("提示", "你今天已经签退过了");
			} else if (res == 3) {
				$.messager.alert("提示", "还没有下班哦,你早退了");
				$("#tab").datagrid("reload");
			} else if (res == 2) {
				$.messager.alert("提示", "您超出了签退时间，已为您自动签退");
				$("#tab").datagrid("reload");
			} else if (res == -1) {
				$.messager.alert("提示", "今日还未签到");
			}
		}, "json")

	}
	//修改
	function update(index) {
		var date = $("#tab").datagrid("getData");
		var row = date.rows[index];
		$("#updateForm").form("load", row);
		$("#updateWindow").window("open");

	}
	//修改(确认按钮)
	function updatetrue() {
		$.post("updateAsk", {
			askId : $("#updateaskId").val(),
			askName : $("#updateaskName").val(),
			checkState : $("#updatecheckState").combobox("getValue"),
			checkInTime : $("#updatecheckInTime").datetimebox("getValue"),
			changeState : $("#updatechangeState").combobox("getValue"),
			weights : $("#updateweights").val(),
			roleNames : $("#updateroleNames").combobox("getValue"),
			bakContent : $("#updatebakContent").val()

		}, function(res) {
			if (res > 0) {
				$.messager.alert("提示", "操作成功");
				$("#updateWindow").window("close");
				$("#tab").datagrid("reload");
			} else {
				$.messager.alert("提示", "操作失败");
			}
		}, "json")

	}
	//修改(取消按钮)
	function updateclose() {
		$("#updateWindow").window("close");
	}
	//权重排序
	function paixun() {
		$("#tab").datagrid({
			url : 'paixu',
			method : 'post'
		})
	}
	//重置
	function chongzhiAsk() {
		$("#tab").datagrid({
			url : 'selectAskAll',
			method : 'post',
			pagination : true
		})
	}

	//一键签退
	function allQiantui() {
		$.post("allQiantui", {
			statue : $("#statue").val(),
		}, function(res) {
			if (res > 0) {
				$.messager.alert("提示", "所有上班员工签退成功");
				$("#tab").datagrid("reload");
			} else {
				$.messager.alert("提示", "没有上班后未签退的员工哦");
			}
		}, "json")
	}
</script>
</head>
<body>
	<table id="tab" class="easyui-datagrid">
		<div id="seachid">
			<form id="seachForm" class="easyui-form">
				姓名：<input id="askName" class="easyui-textbox"> 角色：<input
					id="roleNames" class="easyui-combobox"> 签到状态： <select
					id="QianDaostatue" class="easyui-combobox">
					<option value="">--请选择--</option>
					<option>已签到</option>
					<option>未签到</option>
					<option>已签退</option>
					<option>迟到</option>
				</select> <a class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					onclick="init()">搜索</a> <a class="easyui-linkbutton"
					data-options="iconCls:'icon-redo'" onclick="paixun()">权重排序</a> <a
					class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
					onclick='chongzhiAsk()'>所有咨询师</a> <a class="easyui-linkbutton"
					data-options="iconCls:'icon-sum'" onclick='allQiantui()'>一键签退</a>
			</form>
		</div>
		<thead>
			<tr>
				<th data-options="field:'askId',align:'center',title:'编号'"></th>
				<th data-options="field:'askName',align:'center',title:'姓名'"></th>
				<th data-options="field:'checkState',align:'center',title:'咨询状态'"></th>
				<th data-options="field:'checkInTime',align:'center',title:'签到时间'"></th>
				<th data-options="field:'changeState',align:'center',title:'上班状态'"></th>
				<th data-options="field:'weights',align:'center',title:'权重'"></th>
				<th data-options="field:'roleNames',align:'center',title:'角色'"></th>
				<th data-options="field:'bakContent',align:'center',title:'分量状态'"></th>
				<th data-options="field:'statue',align:'center',title:'状态'"></th>
				<th data-options="field:'caozuo',align:'center',title:'操作' ,formatter:formatterSet"></th>

			</tr>

		</thead>
	</table>
	<!-- 查看网络学生 -->
	<div id="netaskteacher" class="easyui-window" title="网络学生"
		style="width: 100%; height: 700px;"
		data-options="iconCls:'icon-save',closed:true">
		<table id="netaskteacherTab" class="easyui-datagrid">
			<thead>
				<tr>
					<th field="ck" checkbox="true"></th>
					<th data-options="field:'sid',title:'编号'"></th>
					<th data-options="field:'sname',title:'学生姓名'"></th>
					<th data-options="field:'age',title:'年龄'"></th>
					<th data-options="field:'sex',title:'性别'"></th>
					<th data-options="field:'phone',title:'电话'"></th>
					<th data-options="field:'stuStatus',title:'学生学历'"></th>
					<th data-options="field:'perStatus',title:'个人状态'"></th>
					<th data-options="field:'msgSource',title:'来源渠道'"></th>
					<th data-options="field:'sourceUrl',title:'来源网址'"></th>
					<th data-options="field:'sourceKeyWord',title:'来源关键字'"></th>
					<th data-options="field:'address',title:'学生地址'"></th>
					<th data-options="field:'qq',title:'qq号'"></th>
					<th data-options="field:'weiXin',title:'微信号'"></th>
					<th data-options="field:'content',title:'备注'"></th>
					<th data-options="field:'creatTimes',title:'创建时间'"></th>
					<th data-options="field:'learnForword',title:'课程方向'"></th>
					<th data-options="field:'isValid',title:'是否有效'"></th>
					<th data-options="field:'reoord',title:''"></th>
					<th data-options="field:'isReturnVisit',title:'是否回访'"></th>
					<th data-options="field:'firstVisitTime',title:'第一次回访时间'"></th>
					<th data-options="field:'isHome',title:'是否家访'"></th>
					<th data-options="field:'homeTime',title:'家访时间'"></th>
					<th data-options="field:'lostValid',title:'无效原因'"></th>
					<th data-options="field:'isPay',title:'是否缴费'"></th>
					<th data-options="field:'payTime',title:'缴费时间'"></th>
					<th data-options="field:'money',title:'缴费金额'"></th>
					<th data-options="field:'isReturnMoney',title:'是否退费'"></th>
					<th data-options="field:'isInClass',title:'是否进班'"></th>
					<th data-options="field:'inClassTime',title:'进班时间'"></th>
					<th data-options="field:'inClassContent',title:'进班备注'"></th>
					<th data-options="field:'askerContent',title:'咨询师备注'"></th>
					<th data-options="field:'isDel',title:''"></th>
					<th data-options="field:'fromPart',title:''"></th>
					<th data-options="field:'stuConcern',title:'学生担忧情况'"></th>
					<th data-options="field:'isBaoBei',title:'是否报备'"></th>
					<th data-options="field:'ziXunName',title:'咨询内容'"></th>
					<th data-options="field:'creatUser',title:''"></th>
					<th data-options="field:'returnMoneyReason',title:'退费原因'"></th>
					<th data-options="field:'preMoney',title:'定金金额'"></th>
					<th data-options="field:'preMoneyTime',title:'交定金时间'"></th>
					<th data-options="field:'askerId',title:'咨询师编号'"></th>
				</tr>
			</thead>
		</table>
	</div>

	<!--  修改-->
	<div id="updateWindow" class="easyui-window"
		data-options="closed:true,title:'修改咨询师权重'">
		<form id="updateForm">
			<table>
				<tr>
					<td><label for="name">编号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateaskId" name="askId" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">姓名:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateaskName" name="askName" disabled="disabled" /></td>
				</tr>

				<tr>
					<td><label for="name">咨询状态:</label></td>
					<td><select class="easyui-combobox" id="updatecheckState">
							<option value="">--请选择--</option>
							<option>已咨询</option>
							<option>未咨询</option>
					</select></td>
				</tr>

				<tr>
					<td><label for="name">咨询日期:</label></td>
					<td><input class="easyui-datetimebox" type="text"
						id="updatecheckInTime" name="checkInTime" /></td>
				</tr>

				<tr>
					<td><label for="name">上班状态:</label></td>
					<td><select class="easyui-combobox" id="updatechangeState">
							<option value="">--请选择--</option>
							<option>上班</option>
							<option>下班</option>
					</select></td>
				</tr>

				<tr>
					<td><label for="name">权重:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateweights" name="weights" /></td>
				</tr>

				<tr>
					<td><label for="name">角色:</label></td>
					<td><input id="updateroleNames" class="easyui-combobox">
					</td>
				</tr>
				<tr>
					<td><label for="name">咨询内容:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatebakContent" name="bakContent" /></td>
				</tr>
				<tr>
					<td><label for="name">状态</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatebakContent" name="statue" /></td>
				</tr>
				<tr>
					<td><a href="javascript:void(0)" icon="icon-ok"
						class="easyui-linkbutton" onclick="updatetrue()">提交</a></td>
					<td><a href="javascript:void(0)" icon="icon-cancel"
						class="easyui-linkbutton" onclick="updateclose()">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dd">
		<input class="easyui-textbox" id="message" data-options="multiline:true,height:50,prompt:'备注...'" type="text" name="s_inClassContent"/>
	</div>
</body>
</html>