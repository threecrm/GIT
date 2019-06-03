<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/default/easyui.css">
<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/network_trace.js"></script>
</head>

<body>
<input id="u_id" value="<%=session.getAttribute("uid")%>" style="display: none"/>
 <div id="stuTool">
			 <label for="name">学生名称:</label>
			 <input class="easyui-textbox"  id="n_sname" style="width: 100px"/> 
			 <label for="name">回访情况:</label>
			 <input class="easyui-textbox"  id="n_qingkuang" style="width: 100px"/> 
			 
		         跟踪方式:<select id="sn_fangshi"  class="easyui-combobox"  >
					   <option value=""></option>
					   <option>家访</option>
					   <option>校访</option>
					   <option>其他</option>
					   </select>
			 
			<label for="name">跟踪时间:</label>
		    <input class="easyui-datetimebox" id="startTime"  style="width: 100px" /> ~ 
		    <input class="easyui-datetimebox" id="endTime" style="width: 100px" />
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="initStu()">搜索</a>
		    <a href="#" class="easyui-linkbutton"  onclick="breakPage()">返回上一页</a>
	</div>

 

<table id="stuTab" class="easyui-datagrid" >
		<thead>
			<tr> <th field="ck" checkbox="true" title="批量删除"></th> 
			    <th data-options="field:'choose',title:'操作',formatter:formatterChoose"></th>   
				<th data-options="field:'n_id',title:'ID',width:100"></th>
				<th data-options="field:'n_sname',title:'学生姓名',width:100"></th>
				<th data-options="field:'n_date',title:'跟踪开始时间',width:100"></th>
				<th data-options="field:'uname',title:'跟踪者',width:100"></th>
				<th data-options="field:'n_qingkuang',title:'回访情况',width:100"></th>
				<th data-options="field:'n_fangshi',title:'跟踪方式',width:100"></th>
				<th data-options="field:'n_ask',title:'拜访详细',width:100"></th>
				<th data-options="field:'n_aftertime',title:'跟踪结束时间',width:100"></th>
				
			</tr>
		</thead>
	</table>
	    <!--查看追踪弹出框  -->
		<div id="ZhuiZongWin" class="easyui-window" data-options="modal:true,title:'拜访学生页面',closed:true" style="width:600px;height:500px">
	    <form id="ZhuiZongform" class="easyui-form" >
	    <table>
	    <tr>
	     <td><label for="name">拜访客户:</label></td>
	     <td><input class="easyui-validatebox" type="text" id="sname" name="n_sname" disabled="disabled" /></td> 
	    </tr>
	    <tr>
	     <td><label for="name">跟踪开始时间:</label></td>
	     <td><input class="easyui-datetimebox" type="text" id="date" name="n_date" readonly="true"/></td> 
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
	     <td><input class="easyui-textbox" data-options="multiline:true,height:50,prompt:'备注...',readonly:true" type="text" id="ask" name="n_ask"/></td> 
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