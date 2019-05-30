<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	$(function(){
		init();
	
	})
	
	function init(){
		$("#tab").datagrid({
			url:'selectNetGenZong',
			method:'post',
			pagination:true,
			toolbar:'#stuTool', 
			queryParams:{
				 uname:$("#LoginUserName").val(),
				 searchn_sname:$("#n_sname").val(),
			     n_address:$("#n_address").val(),
				 n_qingkuang:$("#n_qingkuang").val(),
				 n_fangshi:$("#n_fangshi").val(),
				 searchstartTime:$("#startTime").datetimebox('getValue'), 
				 searchendTime:$("#endTime").datetimebox('getValue') 
			}
		})
		
	
	}
	
	
	

	</script>
</head>
<body>
<input type="hidden" id="LoginUserName" value="${LoginUserName}">

 <div id="stuTool">
			 <label for="name">学生名称:</label>
			 <input class="easyui-textbox"  id="n_sname" style="width: 100px"/> 
			 <label for="name">回访情况:</label>
			 <input class="easyui-textbox"  id="n_qingkuang" style="width: 100px"/> 
			 <label for="name">跟踪方式:</label>
			 <input class="easyui-textbox"  id="n_fangshi" style="width: 100px"/> 
			<label for="name">跟踪时间:</label>
		    <input class="easyui-datetimebox" id="startTime"  style="width: 100px" /> ~ 
		    <input class="easyui-datetimebox" id="endTime" style="width: 100px" />
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="init()">搜索</a>
	</div>
<table id="tab" class="easyui-datagrid">
<thead>
         <tr>
               <th data-options="field:'n_id',title:'ID',width:100"></th>
				<th data-options="field:'n_sname',title:'学生姓名',width:100"></th>
				<th data-options="field:'n_date',title:'跟踪开始时间',width:100"></th>
				<th data-options="field:'uname',title:'跟踪者',width:100"></th>
				<th data-options="field:'n_qingkuang',title:'回访情况',width:100"></th>
				<th data-options="field:'n_fangshi',title:'跟踪方式',width:100"></th>
				<th data-options="field:'n_ask',title:'跟踪内容',width:100"></th>
				<th data-options="field:'n_aftertime',title:'跟踪结束时间',width:100"></th>
				
         </tr>
</thead>
</table>
</body>
</html>