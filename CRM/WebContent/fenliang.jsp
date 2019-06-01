<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>分量设置页面</title>
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
		$("#tab").datagrid({
			url : "selectAskAll",
			method : "post",
			pagination : true,
			fitColumns : true,
			singleSelect : true
		})
	})
	function formatterSet(value, row, index) {
		return " <a href='javascrip:void(0)' onclick='FenLiangOpen(" + index
				+ ")'>开启分量</a>  "
				+ " <a href='javascrip:void(0)'  onclick='FenLiangClose("
				+ index + ")'>关闭分量</a> "
	}
	//开启分量
	function FenLiangOpen(index) {
		var data = $("#tab").datagrid("getData").rows[index];
		$.messager.confirm("提示信息", "确定开起吗？", function(r) {
			if (r) {
				if (data.bakContent == "已开启") {
					return $.messager.alert("提示", "已经开启");
				} else {
					$.post("FenLiangOpen", {
						askId : data.askId,
						bakContent : "已开启"
					}, function(res) {
						if (res > 0) {
							$.messager.alert("提示", "开启成功");
							$("#tab").datagrid("reload");
						} else {
							$.messager.alert("提示", "开启失败")
						}
					})
				}
			}
		})
	}
	//关闭分量
	function FenLiangClose(index) {
		var data = $("#tab").datagrid("getData").rows[index];
		$.messager.confirm("提示信息", "确定关闭吗？", function(r) {
			if (r) {
				if (data.bakContent == "已关闭") {
					return $.messager.alert("提示", "已经关闭");
				} else {
					$.post("FenLiangOpen", {
						askId : data.askId,
						bakContent : "已关闭"
					}, function(res) {
						if (res > 0) {
							$.messager.alert("提示", "关闭成功");
							$("#tab").datagrid("reload");
						} else {
							$.messager.alert("提示", "关闭失败")
						}
					})
				}
			}
		})
	}
</script>

</head>
<body>
	<table id="tab" class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'askId',align:'center',title:'编号'"></th>
				<th data-options="field:'askName',align:'center',title:'员工姓名'"></th>
				<th data-options="field:'weights',align:'center',title:'权重'"></th>
				<th data-options="field:'bakContent',align:'center',title:'分量状态'"></th>
				<th
					data-options="field:'caozuo',align:'center',title:'操作',formatter:formatterSet"></th>
			</tr>
		</thead>
	</table>
</body>
</html>