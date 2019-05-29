<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/default/easyui.css">
<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	init();
})
   function init(){
	$("#tab").datagrid({
		url:'selectGenZong',
		method:'post',
	    pagination:true,
	   toolbar:'#sousuoId',  
	   queryParams:{
		   StudentName:$("#studentName").val(),
		   LonginName:$("#userName").val(),
		   StartfollowTime:$("#StartfollowTime").datetimebox("getValue"),
		   endfollowTime:$("#endfollowTime").datetimebox("getValue"),
		   StartnextFollowTime:$("#StartnextFollowTime").datetimebox("getValue"),
		   endnextFollowTime:$("#endnextFollowTime").datetimebox("getValue"),
		   IsReturnVisit:$("#isReturnVisit").combobox("getValue"), 
		   followType:$("#followType").val()
	   } 
		
	})
}

 function formatterName(value,row,index){
	return row.users.longinName;
}

  function formatterHui(value,row,index){
	return row.student.isReturnVisit;
}  
</script>
</head>
<body>
<table id="tab" class="easyui-datagrid">
  <div id="sousuoId">
  <form id="seachForm" class="easyui-form">
  学生名称: <input id="studentName"  class="easyui-textbox">
  跟踪者: <input id="userName"  class="easyui-textbox">
  跟踪开始时间:<input id="StartfollowTime"  class="easyui-datetimebox">~ <input id="endfollowTime"  class="easyui-datetimebox">
 <br>跟踪结束时间:<input id="StartnextFollowTime"  class="easyui-datetimebox">~<input id="endnextFollowTime"  class="easyui-datetimebox">
  回访情况:
  <select id="isReturnVisit" class="easyui-combobox">
   <option value=''>--请选择--</option>
   <option>已回访</option>
   <option>未回访</option> 
   <option>思量</option>
   <option>上门未报名</option>
   <option>报名未进班</option>
   <option>未上门</option>
   </select> 
  跟踪方式:<input id="followType"  class="easyui-textbox">
    <br> <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="init()">搜索</a>
  </form>
</div>  
 <thead>
       <tr>
                <th data-options="field:'cid',title:'编号'"></th>
				<th data-options="field:'studentName',title:'学生姓名'"></th>
				<th data-options="field:'followTime',title:'开始追踪时间'"></th>
				<th data-options="field:'nextFollowTime',title:'结束追踪时间'"></th>
				<th data-options="field:'student',title:'回访情况',formatter:formatterHui"></th>
				<th data-options="field:'contents',title:'内容'"></th>
				<th data-options="field:'users',title:'跟踪者',formatter:formatterName"></th>
				<th data-options="field:'followType',title:'追踪方式'"></th>
				<th data-options="field:'creatsTime',title:'创建时间'"></th>
				<th data-options="field:'followState',title:'咨询内容'"></th>
			
        </tr>
  
  </thead> 
</table>
</body>
</html>