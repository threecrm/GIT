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
				$("#tre").tree({
					lines:true,
					url:"modules",
					method:'post',
					queryParams:{
						user:$("#modules1").val()
					},onContextMenu:function(e,node){
						e.preventDefault();
						$("#tre").tree('select',node.target);
						$("#caozuo").menu('show',{
							left:e.pageX,
							top:e.pageY
						});
					}
				})
			})
			
			//添加父节点
			
			function addModules(){
                $("#add_window").window("open");
			}
			
			 function saveAdd1(){
			    var flag=$("append-form1").form("validate");
				var mName=$("#Modulename1").val();
				var mPath=$("#ModuleUrl1").val();
				var mWeight=$("#Moduleweight1").val();
				var mParentId=$("#ParentId1").val();
				 if(flag){
					$.post(
							"addParentModules",
					{
					ModuleName:mName,
					Path:mPath,
					Weight:mWeight,
					ParentId:mParentId
				}, function(res) {
					if(res>0){
						$.messager.alert("提示", "添加成功!");
						$("#add_window").window("close");
						$("#tre").tree("reload");
					}else if(res=0){
						$.messager.alert("提示", "添加失败!");
					}else{
						$.messager.alert("提示", "该模块已存在!");
					}
				}, "json")
				} 
				  
				 /* 重置表格 */
				 $("#append-form1").form("reset");
			} 
			
			function closeAdd1(){
			$("#add_window").window("close");
		}
			
			
			//添加子节点
			function append(){
				var nodes = $('#tre').tree('getSelected');
				if(nodes!=null){
					$("#parentModulename").text(nodes.text);
                	$("#add_dialog").window("open");
				}else{
					$.messager.alert("请选择父节点！");
				}
			}
			
			function saveAdd(){
				var nodes = $('#tre').tree('getSelected');
				var flag=$("append-form").form("validate");
				var ModuleName=$("#Modulename").val();
				var Path=$("#ModuleUrl").val();
				var Weight=$("#Moduleweight").val();
				 if(flag){
					$.post(
							"addModules",
					{
					parentId:nodes.id, 
					ModuleName:ModuleName,
					Path:Path,
					Weight:Weight
				}, function(res) {
					if(res>0){
						$.messager.alert("提示", "添加成功!");
						$("#add_dialog").window("close");
						$("#tre").tree("reload");
					}else if(res=0){
						$.messager.alert("提示", "添加失败!");
					}else{
						$.messager.alert("提示", "该模块已存在!");
					}
				}, "json")
				} 
				 
				 /* 重置表格 */
				$("#append-form").form("reset");
			}
			
			function closeAdd(){
			$("#add_dialog").window("close");
		}
			
			  //修改
			  function edit(){
				var nodes = $('#tre').tree('getSelected');
				$("#mid").textbox("setText",nodes.id); 
				$("#ModuleName").textbox("setText",nodes.text);
				$("#Weight").textbox("setText",nodes.Weight);
				$("#Path").textbox("setText",nodes.url); 
				$("#edit-window").window("open");
				if(nodes!=null){
				  $.post("GetModuleById", {
					mid:nodes.id,
				}, function(res) {
					if(res>0){
						var msg=eval("("+res.message+")");
						$("#edit-form").form("load", msg);
					}
				}, "json")
				}else{
					$.messager.alert("请选择要修改的节点！")
				}
				
			} 
			
			
			function Editform(){
				var nodes = $('#tre').tree('getSelected');
				var flag=$("#edit-form").form("validate");
				var mid = nodes.id;
				var ModuleName=$("#ModuleName").textbox("getText");
				var Weight=$("#Weight").textbox("getText");
				var Path=$("#Path").textbox("getText");
				if(flag){
					$.post(
							"updateModules",
				{
					mid:nodes.id,
					ModuleName:ModuleName,
					Path:Path,
					Weight:Weight,
				}, function(res) {
					if(res>0){
						$.messager.alert("提示", "修改成功!");
						$("#edit-window").window("close");
						$("#tre").tree("reload");
					}else if(res=0){
						$.messager.alert("提示", "修改失败!");
					}else{
						$.messager.alert("提示", "该模块已存在!");
					}
				}, "json")
				}
			} 
			
			function clearForm(){
			$("#edit-window").window("close");
		}
			
			
			/* 删除 */
			function del(){
				var nodes = $('#tre').tree('getSelected');
				if(nodes!=null){
					$.post(
							"deleteModules",
				{
					mid:nodes.id,
				}, function(res) {
					if(res>0){
						$.messager.alert("提示", "删除成功!");
						$("#tre").tree("reload");
					}else if(res=0){
						$.messager.alert("提示", "删除失败!");
					}else{
						$.messager.alert("提示", "该模块已被分配不能删除!");
					}
				}, "json")
				}else{
					$.messager.alert("请选择要删除的节点！")
				}
			}
		</script>
	</head>
	<body>
	   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addModules()">添加</a>
	<input style="display: none" id="modules1" value="${LonginName }">
		<div>
			<ul id="tre" class="easyui-tree"></ul>
		</div>
		<div id="caozuo" class="easyui-menu"  style="width: 160px;">
			<div onclick="append()"  data-options="iconCls:'icon-add'">添加</div>
			<div onclick="edit()"  data-options="iconCls:'icon-edit'">修改</div>
			<div onclick="del()"  data-options="iconCls:'icon-remove'">删除</div>
		</div>
		
		<!-- 添加父节点  -->
		<div id="add_window" class="easyui-dialog" data-options="modal:true,title:'添加',closed:true,
	    buttons:[{
		text:'保存',
		handler:function(){
			saveAdd1();
		}
	},{
		text:'取消',
		handler:function(){
			closeAdd1();
		}
	}]
	">
         <form id="append-form1">
                <table cellpadding="5">
					<tr>
						<td>节点名称：</td>
						<td>
							<input type="text" class="easyui-textbox" id="Modulename1" name="Modulename" style="width:120px"required="true" >
						</td>
					</tr>
					<tr>
						<td>权重：</td>
						<td>
							<input type="text" class="easyui-numberbox" id="Moduleweight1" name="Moduleweight" style="width:120px" required="true" >
						</td>
					</tr>
					<tr>
						<td>url：</td>
						<td>
						<input type="text" class="easyui-textbox" id="ModuleUrl1" name="ModuleUrl" style="width:120px"required="true" >
						</td>
					</tr>
					<tr style="display: none">
						<td>父节点ID：</td>
						<td>
							<input type="text" class="easyui-textbox" id="ParentId1" name="ParentId" style="width:120px"required="true" >
						</td>
					</tr>
                </table>
         </form>
    </div>
		
		<!-- 添加子节点 -->
		<div id="add_dialog" class="easyui-dialog" data-options="modal:true,title:'添加',closed:true,
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
         <form id="append-form">
                <table cellpadding="5">
                
                   <tr>
						<td>父节点名称：</td>
						<td>
							<div id="parentModulename"></div>
						</td>
					</tr>
					<tr>
						<td>节点名称：</td>
						<td>
							<input type="text" class="easyui-textbox" id="Modulename" name="Modulename" style="width:120px"required="true" >
						</td>
					</tr>
					<tr>
						<td>权重：</td>
						<td>
							<input type="text" class="easyui-numberbox" id="Moduleweight" name="Moduleweight" style="width:120px" required="true" >
						</td>
					</tr>
					<tr>
						<td>url：</td>
						<td>
						<input type="text" class="easyui-textbox" id="ModuleUrl" name="ModuleUrl" style="width:120px"required="true" >
						</td>
					</tr>
                    
                </table>
         </form>
    </div>
		
		<!--修改-->
		<div id="edit-window" class="easyui-window" title="修改信息" style="width:400px;height:300px" data-options="iconCls:'icon-save',modal:true,closed:true">
			<form id="edit-form" class="easyui-form" style="text-align: center;">
				<table cellpadding="5">
				
					 <tr>
						<td>模块Id：</td>
						<td>
							<input type="text" class="easyui-textbox" id="mid" style="width:120px" data-options="readonly:true" disabled="disabled" >
						</td>
					</tr>  
					
					<tr>
						<td>模块名称：</td>
						<td>
							<input type="text" class="easyui-textbox" id="ModuleName" style="width:120px" required="true" >
						</td>
					</tr>
					<tr>
						<td>权重：</td>
						<td>
							<input type="text" class="easyui-textbox" id="Weight" style="width:120px" required="true" >
						</td>
					</tr>
					<tr>
						<td>url：</td>
						<td>
						<input type="text" class="easyui-textbox" id="Path" style="width:120px" required="true" >
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="Editform()">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearForm()">取消</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
