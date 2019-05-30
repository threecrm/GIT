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
		function formatterChoose(value, row, index) {
			return "<a href='javascript:void(0)' onclick='selectInfo("+ index+")'>查看</a> "
		}
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
				 n_sname:$("#n_sname").val(),
				 n_qingkuang:$("#n_qingkuang").val(),
				 n_fangshi:$("#n_fangshi").val(),
				 searchstartTime:$("#startTime").datetimebox('getValue'), 
				 searchendTime:$("#endTime").datetimebox('getValue') 
			}
		})
		
	
	}
	
	
//查看
	function selectInfo(index){
		var datas=$("#tab").datagrid("getData");
		 var row=datas.rows[index];
		 $("#ZhuiZongWin").dialog("open");
		 //填充表单
		 $("#ZhuiZongform").form("load",row)
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
				<th data-options="field:'choose',title:'操作',formatter:formatterChoose"></th> 
         </tr>
</thead>
</table>
<div id="ZhuiZongWin" class="easyui-window" data-options="modal:true,title:'拜访学生页面',closed:true" style="width:350px;height:400px">
	    <form id="ZhuiZongform" class="easyui-form" >
	    <table>
	    <tr>
	     <td><label for="name">拜访客户:</label></td>
	     <td><input class="easyui-validatebox" type="text" id="sname" name="n_sname" disabled="disabled" /></td> 
	    </tr>
	    <tr>
	     <td><label for="name">跟踪开始时间:</label></td>
	     <td><input class="easyui-datetimebox" type="text" id="date" name="n_date" readonly="true" /></td> 
	    </tr>
	    <tr>
	     <td><label for="name">跟踪者:</label>   
	     <td><input class="easyui-validatebox" type="text" id="uname" name="uname" readonly="true"/></td> 
	    </tr>
	    <tr>
	     <td><label for="name">回访情况:</label></td>
	     <td><input class="easyui-validatebox" type="text" id="n_qingkuang" name="n_qingkuang" readonly="true"/></td> 
	    </tr>
	    <tr> 
	     <td><label for="name">跟踪方式:</label></td>
	     <td><input class="easyui-validatebox" type="text" id="n_fangshi"  name="n_fangshi" readonly="true"/></td> 
	    </tr>
	    <tr> 
	     <td><label for="name">拜访详细:</label></td>
	     <td><input class="easyui-validatebox" type="text" id="ask" name="n_ask" readonly="true"/></td> 
	    </tr>
	    <tr>
	     <td> <label for="name">跟踪结束时间:</label>  </td>
	     <td><input class="easyui-datetimebox" type="text" id="aftertime"  name="n_aftertime" readonly="true"/></td> 
	    </tr>
	  
      </table>
         </form>
	    </div>
</body>
</html>