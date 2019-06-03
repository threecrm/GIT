<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/default/easyui.css">
<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
     <script>
     
    
     $(function(){
    	 searchRole();
		})
		/* 条件查询 */
			function searchRole() {
				$("#tab").datagrid({
					url: 'selectRole',
					method:"post",
					pagination:true,
					fitColumns:true,
					singleSelect:true,
					toolbar:"#Roletb",
					queryParams:{
						RoleName:$("#RoleName").val(),
					}
				});
				
				/* 重置表格 */
				$("#tb-frm").form("reset");
			}
     
		
     function formatterCaozuo(value,row,index){
			
			return "<a href='javascript:void(0)' onclick='editRole("+index+")'>修改</a>  <a href='javascript:void(0)' onclick='delRole("+index+")'>删除</a>"
		}
     /*添加角色*/
		function addRole(){
			$("#addRole_dialog").window("open");
		}
		
		function saveAdd(){
			var flag = $("#addRoleForm").form("validate");
			var roleName = $("#roleName1").val();
			if(flag){
				$.post(
					"addRole",
					{
						RoleName:roleName,
				},
				function(res){
					if(res>0){
						$.messager.alert("提示","添加成功");
						$("#addRole_dialog").window("close");
						$("#tab").datagrid("reload");
					}else if(res=0){
						$.messager.alert("提示","添加失败");
					}else{
						$.messager.alert("提示","该角色已存在！");
					}
					
				},"json");
			}
			/* 重置表格 */
			$("#addRoleForm").form("reset");
			
			
		}
		
		function closeAdd(){
			$("#addRole_dialog").window("close");
		}
		
		/* 修改 */
		function editRole(index){
			var data = $("#tab").datagrid("getData");
			var row = data.rows[index];
			$("#updateRoleForm").form("load",row);
			$("#updateRole_dialog").dialog("open");
		}
		
		function saveUpdate() {
			$.post(
					"updateRole",
			{
				rid:$("#rid2").val(),
				roleName:$("#roleName2").val(),
			},function(res){
				if(res>0){
					$("#tab").datagrid("reload");
					$("#updateRole_dialog").dialog("close");
					$.messager.alert("提示","修改成功");
				}else if(res=0){
					$.messager.alert("提示","修改失败");
				}else{
					$.messager.alert("提示","该角色已存在！");
				}
				
			},"json")
		} 
		
		function closeUpdate(){
			$("#updateRole_dialog").dialog("close");
		}  
	/* 删除角色 */
	function delRole(index){
			var data=$("#tab").datagrid("getData");
			var row=data.rows[index];
			$.messager.confirm("确认删除","确认删除么？",function(r){
				if(r){
					$.post(
						    "delRoles",
						{rid:row.rid},
				function(res){
						if(res>0){
							$("#tab").datagrid("reload");
							$.messager.alert("提示","删除成功");
						}else if(res=0){
							$.messager.alert("提示","删除失败")
						}else{
							$.messager.alert("提示","该角色已被分配角色权限不能删除!")
						}
					},"json")
				}
				
			})
		}
     
   //设置角色权限
		function formatterQuanXian(value, row, index) {
			return "<a href='javascript:void(0)' style='cursor: pointer;' onclick='showQuanxian(" + index + ")'>设置权限</a>";
		}
   //角色权限设置框
   function showQuanxian(index) {
	   //获取tab数据表格所有数据
	    var data = $("#tab").datagrid("getData");
		var row = data.rows[index];//index下标
	 	var roleName=row.roleName;//获取到角色名称
	 	var rid=row.rid;//获取到角色Id
	 		$("#quanxian").dialog({
	 			closed:false,
	 			title:"您正在设置："+roleName,
	 			 toolbar:[{//设置对话框窗口顶部工具栏
	 				text:"提交",
	 				iconCls:'icon-ok',
	 				handler:function(){
	 					var data = $("#tab").datagrid("getSelected");//第一个被选中的
	 					var nodes=$("#tre").tree('getChecked',['checked','indeterminate']);//获取所有选中的节点
	 					var n="";
	 					for(var i=0;i<nodes.length;i++){//拼接字符串获取到所有的选中的节点id
	 						if(n==""){
	 							n+=nodes[i].id;
	 						}else{
	 							n += ",";
	 							n += nodes[i].id;
	 						}
	 					}
	 					$.post(
	 						"rolesModules",
	 						{
	 							parentIds:n,//所有的选中的节点id
								rId:data.rid//角色id
							}, function(res){
								if(res==true){
									$.messager.alert("消息","保存权限成功！");
									$("#quanxian").window("close");
									$("#tab").datagrid("reload");
									window.parent.location.reload();
								}
						}, "json")
	 				}
	 			},{
	 				text:"取消",
	 				iconCls:'icon-cancel',
	 				handler:function(){
	 					$("#quanxian").window("close");
	 				}
	 			}] 
	 		});
	 		$.post(//查询出所有模块
	 				"selectModules",
	 				{
						rid:rid,
					}, function(res) {
						checked:true; 
						$("#tre").tree("loadData",res);
				}, "json")
	
  }
   
		</script>
	</head>
	<body>
		<table id="tab" class="easyui-datagrid">
			<thead>
				<tr>
					<th data-options="field:'rid',width:20">角色ID</th>
					<th data-options="field:'roleName',width:30">角色名称</th>
					<th data-options="field:'caozuo',width:30,formatter:formatterCaozuo">操作</th>
					<th data-options="field:'quanXian',width:30,formatter:formatterQuanXian">权限设置</th>
				</tr>
			</thead>
		</table>
		
		<!--多条件查询-->
		<div id="Roletb" style="padding:5px; height:auto">
			<div style="margin-bottom:5px">
			  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRole()">添加</a>
				<form id="tb-frm">
				      角色名称: <input class="easyui-textbox" type="text" id="RoleName" name="RoleName">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchRole()">检索</a>
				</form>
			</div>
		</div>
		
<!--  添加角色 -->
	<div id="addRole_dialog" class="easyui-dialog" data-options="modal:true,title:'新增角色信息',closed:true,
	    buttons:[{
		text:'保存',
		handler:function(){
			saveAdd();
		}
	},{
		text:'取消',
		handler:function(){
			closeAdd();
		}
	}]
	">
         <form id="addRoleForm">
                <table cellpadding="5">
                    <tr>
                        <td>角色名称:</td>
                        <td><input class="easyui-textbox" type="text" name="roleName" id="roleName1" data-options="required:true"></input></td>
                    </tr>
                </table>
         </form>
    </div>
   <!--  修改角色 -->
   <div id="updateRole_dialog" class="easyui-dialog" data-options="modal:true,title:'修改角色信息',closed:true,
	    buttons:[{
		text:'保存',
		handler:function(){
			saveUpdate();
		}
	},{
		text:'取消',
		handler:function(){
			closeUpdate();
		}
	}]
	">
         <form id="updateRoleForm">
                <table cellpadding="5">
                <tr>
                        <td>角色Id:</td>
                        <td><input class="easyui-textbox" type="text" name="rid" id="rid2" data-options="required:true" disabled="disabled"></input></td>
                    </tr>
                    <tr>
                        <td>角色名称:</td>
                        <td><input class="easyui-textbox" type="text" name="roleName" id="roleName2" data-options="required:true"></input></td>
                    </tr>
                </table>
         </form>
    </div>
    
    <!--权限设置-->
		<div id="quanxian" class="easyui-dialog"  style="width:300px;height:500px" data-options="iconCls:'icon-save',modal:true,closed:true">
			<ul id="tre" class="easyui-tree" checkbox="true"></ul>
		<div>
	</body>
</html>
