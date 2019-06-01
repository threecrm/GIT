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

	function formatterSet(value,row,index){
		   return " <a href='javascrip:void(0)' onclick='update("+index+")'>编辑</a>  "
		   +" <a href='javascrip:void(0)'  onclick='Looknetaskteacher("+index+")'>网络学生</a> "	   
	} 
	$(function() {
		tabask();
		$("#updateroleNames").combobox({
			   url:'selectA',
			    method:"post",
			    valueField:'roleName',    
			    textField:'roleName'    
		})
	})

	function tabask() {
		$("#asktab").datagrid({
			url : 'selectInternetAsk',
			method : 'post',
			pagination : true,
			title : '咨询信息'

		})
	}
	function update(index) {
		var data = $("#asktab").datagrid("getData");
		var row = data.rows[index];
		$("#updateForm").form("load", row);
		$("#updateWindow").window("open")

	}
	function updatetrue() {
		$.post("updateAsk", {
			askId : $("#updateaskId").val(),
			askName : $("#updateaskName").val(),
			checkState : $("#updatecheckState").val(),
			checkInTime : $("#updatecheckInTime").datetimebox("getValue"),
			changeState : $("#updatechangeState").val(),
			weights : $("#updateweights").val(),
			roleNames : $("#updateroleNames").combobox("getValue"),
			bakContent : $("#updatebakContent").val()

		}, function(res) {
			if (res > 0) {
				$.messager.alert("提示", "修改成功");
				$("#updateWindow").window("close");
				$("#asktab").datagrid("reload");

			} else {
				$.messager.alert("提示", "修改失败");
			}

		}, "json")

	}
	function updateclose() {
		$("#updateWindow").window("close")
	}
	
	function Looknetaskteacher(index) {
		var data=$("#asktab").datagrid("getData");
		var row = data.rows[index];
		 $("#netaskteacherTab").datagrid({
			 url:'selectStudent',
			 method:'post',
			queryParams:{
			 askId:row.askId
						 }
				 })	
	 $("#netaskteacher").window("open");
		
	}
	
</script>
</head>
<body>
	<table id="asktab" class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'askId',title:'编号'"></th>
				<th data-options="field:'askName',title:'姓名'"></th>
				<th data-options="field:'checkState',title:'咨询状态'"></th>
				<th data-options="field:'checkInTime',title:'咨询日期'"></th>
				<th data-options="field:'changeState',title:'上班状态'"></th>
				<th data-options="field:'weights',title:'权重'"></th>
				<th data-options="field:'roleNames',title:'角色'"></th>
				<th data-options="field:'bakContent',title:'分量状态'"></th>
				<th data-options="field:'caozuo',title:'操作' ,formatter:formatterSet"></th>
			</tr>

		</thead>
	</table>

	<!--  修改-->
	<div id="updateWindow" class="easyui-window"
		data-options="closed:true,title:'修改咨询师'">
		<form id="updateForm">

			<table>
				<tr>
					<td><label for="name">编号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateaskId" name="askId" /></td>
				</tr>
				<tr>
					<td><label for="name">姓名:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateaskName" name="askName" /></td>
				</tr>

				<tr>
					<td><label for="name">咨询状态:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatecheckState" name="checkState" /></td>
				</tr>

				<tr>
					<td><label for="name">咨询日期:</label></td>
					<td><input class="easyui-datetimebox" type="text"
						id="updatecheckInTime" name="checkInTime" /></td>
				</tr>

				<tr>
					<td><label for="name">上班状态:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatechangeState" name="changeState" /></td>
				</tr>

				<tr>
					<td><label for="name">权重:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateweights" name="weights" /></td>
				</tr>

				<tr>
					<td><label for="name">角色:</label></td>
					<td><input id="updateroleNames" name="roleNames" class="easyui-combobox"></td>
				</tr>
				<tr>
					<td><label for="name">咨询内容:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatebakContent" name="bakContent" /></td>
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
	<!--网络学生  -->
	<div id="netaskteacher" class="easyui-window" title="网络学生" style="width:100%;height:700px;"   
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
	
</body>
</html>