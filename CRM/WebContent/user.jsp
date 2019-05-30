<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.3/themes/default/easyui.css">
		<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
		<script>
		$(function(){
			searchUser()
		})
		/* 条件查询 */
			function searchUser() {
				$("#tab").datagrid({
					url: "selectUser",
					method:"post",
					toolbar:"#usertb",
					pagination:true,
					fitColumns:true,
					singleSelect:true,
					queryParams:{
						longinName:$("#userName").val(),
						beginDates:$("#beginDates").datebox("getValue"),
						endDates:$("#endDates").datebox("getValue"),
						IsLockout:$("#isLock").combobox("getValue")
					}
				})
				/* 重置表格 */
				$("#tb-frm").form("reset");
			}
		
		function formatterCaozuo(value,row,index){
			
			return "<a href='javascript:void(0)' onclick='edit("+index+")'>修改</a>  <a href='javascript:void(0)' onclick='del("+index+")'>删除</a> <a href='javascript:void(0)' onclick='lookUser("+index+")'>查看</a>"
		}
		/*修改用户信息*/                                                          
		function edit(index){
			var data = $("#tab").datagrid("getData");
			var row = data.rows[index];
			$("#frm1").form("load",row);
			$("#editDialog").dialog("open");
		}
		
		function saveEdit() {
			$.post("updateUser",{
				uid:$("#uid2").val(),
				LonginName:$("#uname").val(),
				ProtectEMail:$("#uemail").val(),
				ProtectMTel:$("#uphone").val(),
			},function(res){
				if(res>0){
					$("#tab").datagrid("reload");
					$("#editDialog").dialog("close");
					$.messager.alert("提示","修改成功");
				}else{
					$.messager.alert("提示","修改失败")
				}
				
			},"json")
		} 
		
		function closeEdit(){
			$("#editDialog").dialog("close");
		}  
		
		/*删除用户*/ 
		function del(index){
			var data=$("#tab").datagrid("getData");
			var row=data.rows[index];
			$.messager.confirm("确认删除","确认删除么？",function(r){
				if(r){
					$.post("delUser",
						{uid:row.uid},
				function(res){
						if(res>0){
							$("#tab").datagrid("reload");
							$.messager.alert("提示","删除成功");
						}else{
							$.messager.alert("提示","删除失败")
						}
					},"json")
				}
				
			})
		}
		
		/*添加用户*/
		function addUser(){
			$("#adduser_dialog").window("open");
		}
		

        /* 手机号验证 */
		$.extend($.fn.validatebox.defaults.rules, {    
		    minLength: {    
		        validator: function(value, param){    
		            return value.length >= param[0];    
		        },    
		        message: '长度不能小于11位数' 
		    }    
		});  
		
		$.extend($.fn.validatebox.defaults.rules, {    
		    maxLength: {    
		        validator: function(value, param){    
		            return value.length <= param[0];    
		        },    
		        message: '长度不能大于11位数' 
		    }    
		});  
		
		function saveAdd(){
			var flag = $("#adduserForm").form("validate");
			var name = $("#name").val();
			var email = $("#email").val();
			var mtel = $("#mtel").val();
			var pwd = $("#pwd").val();
		    var isSuoding=$("#isSuoding").val();
			var wrongPwd=$("#wrongPwd").val()
			if(flag){
				$.post(
					"addUser",
					{
					LonginName:name,
					ProtectEMail:email,
					ProtectMTel:mtel,
					PassWord:pwd,
				    IsLockout:isSuoding,
					PsdWrongTime:wrongPwd
				},

				function(addInfo){
				  if(addInfo>0){
						$.messager.alert("提示","添加成功");
						$("#adduser_dialog").window("close");
						$("#tab").datagrid("reload");
					}else if(addInfo==0){
						$.messager.alert("提示","添加失败");
					}else{
						$.messager.alert("提示","该用户名已存在！");
					}
					
				},"json");
			}
			/* 重置表格 */
			$("#adduserForm").form("reset");
			
			
		}
		
		function closeAdd(){
			$("#adduser_dialog").window("close");
		}
		
		function formatterPsd(value,row,index){
			return row.psdWrongTime-1;
		}
		
		/*查看用户信息*/
		function lookUser(index){
			var data = $("#tab").datagrid("getData");
			var row = data.rows[index];
			$("#lookForm").form("load",row);
			$("#look-window").window("open");
		}
		
		   //重置密码
			function formatterPassword(value, row, index) {
				return "<a href='javascript:void(0)' style='cursor: pointer;' onclick='resetPassword(" + index + ")'>重置密码</a>";
			}
			//重置密码
			function resetPassword(index){
				$.messager.confirm('确认','您确认要重置用户密码吗？',function(r){    
				    if (r){   
				    	var data = $("#tab").datagrid("getData");
						var row = data.rows[index];
						var longinName=row.longinName;
						$.post("chongzhiPwd", {
							LonginName:longinName,
						}, function(res) {
							if(res>0){
								$.messager.alert("提示", "密码重置成功！");
								$("#tab").datagrid("reload");
							}else{
								$.messager.alert("提示","密码重置失败");
							}
						}, "json")
				    }
				});
			}
			
			//锁定和解锁用户
			function formatterSuoDing(value, row, index) {
				return "<a href='javascript:void(0)' style='cursor: pointer;' onclick='lockUser(" + index + ")'>锁定用户</a> <a href='javascript:void(0)' style='cursor: pointer;' onclick='unlockUser(" + index + ")'>解锁用户</a>";
			}
			//锁定用户
			function lockUser(index){
				$.messager.confirm('确认','您确认想要锁定用户吗？',function(r){    
				    if (r){   
				    	var data = $("#tab").datagrid("getData");
						var row = data.rows[index];
						var longinName=row.longinName;
						$.post("lockUser", {
							LonginName:longinName,
						}, function(res) {
							if(res>0){
								$.messager.alert("提示", "锁定成功！");
								$("#tab").datagrid("reload");
							}else{
								$.messager.alert("提示","该用户已被锁定！");
							}
						}, "json")
				    }
				});  
			}
			//解锁用户
			function unlockUser(index){
				$.messager.confirm('确认','您确认想要解锁用户吗？',function(r){    
				    if (r){   
				    	var data = $("#tab").datagrid("getData");
						var row = data.rows[index];
						var longinName=row.longinName;
						$.post("unlockUser", {
							LonginName:longinName,
						}, function(res) {
							if(res>0){
								$.messager.alert("提示", "解锁成功！");
								$("#tab").datagrid("reload");
							}else{
								$.messager.alert("提示","该用户已解锁！");
							}
						}, "json")
				    }
				});
			}
			
			//设置角色权限
			function formatterJuese(value, row, index) {
				return "<a href='javascript:void(0)' style='cursor: pointer;' onclick='setRoles(" + index + ")'>设置角色</a>";
			}
			
			//打开设置权限框
			function setRoles(index){
				var data = $("#tab").datagrid("getData");
				var row = data.rows[index];
				var uid=row.uid;
				var longinName=row.longinName;
				$("#ToRole").window("open");
				$("#parentModulenames").text(longinName);
				//获取系统角色信息
				 $("#juese").datagrid({
					url: 'selectRole', 
					queryParams: { 
						uid:row.uid,
					}
				});
				//获取当前用户角色信息
				$("#User-juese").datagrid({
					url: 'selectRoles', 
					queryParams: { 
						LonginName:longinName,
						
					}
				}); 
			}
			/* 格式化角色名称 */
			function formatterRole(value,row,index) {
				return row.roles.roleName;
			}
			
			/* 格式化角色id */
			function formatterRid(value,row,index){
				return row.roles.rid;
			}
			
			/*新增用户角色*/
			function addUserRole(){
				var data=$("#juese").datagrid("getData");
				
				var datas=$("#User-juese").datagrid("getData");
				
				//获取角色信息
				var roleRow=$("#juese").datagrid("getSelected");
				if(roleRow.roleName=="管理员"){
					return $.messager.alert("提示信息","管理员唯一");
				}
				
				
				
				for(var i=0;i<data.rows.length;i++){
					if(datas.rows[i]!=undefined){
						if(datas.rows[i].roles.roleName=="网络咨询师" && roleRow.roleName=="咨询师"){
							return $.messager.alert("提示信息","不能同时拥有网络咨询师和咨询师");
						}
						if(datas.rows[i].roles.roleName=="咨询师" && roleRow.roleName=="网络咨询师"){
							return $.messager.alert("提示信息","不能同时拥有咨询师和网络咨询师");
						}
					}
				}
				//获取用户信息
				var userRow=$("#tab").datagrid("getSelected");
				
				
				
				 if(userRow!=null && roleRow!=null){
					$.post(
							"addUserRoles",
					{
						userid:userRow.uid,
						roleid:roleRow.rid,
					},function(res){
						if(res>0){
							$.messager.alert("提示","角色设置成功")
							$("#User-juese").datagrid("reload");
						}else if(res==0){
							$.messager.alert("提示","角色设置失败")
						}else{
							$.messager.alert("提示","改用户已有该角色！")
						}
					},"json")
				}else{
					$.messager.alert("提示","请先选择角色！！")
				}
			}
			/*删除用户角色*/
			function deleteUserRole(){
				//获取用户信息
				var userRow=$("#tab").datagrid("getSelected");
				//获取角色信息
				var roleRow=$("#User-juese").datagrid("getSelected");
				 if(userRow!=null && roleRow!=null){
					$.post(
							"delUserRoles",
					{
							userid:userRow.uid,
							roleid:roleRow.roles.rid,
					},function(res){
						if(res>0){
							$.messager.alert("提示","角色移除成功")
							$("#User-juese").datagrid("reload");
						}else{
							$.messager.alert("提示","角色移除失败")
						}
					},"json")
				}else{
					$.messager.alert("提示","请先选择角色！！")
				}
			}
		</script>
	</head>

	<body>
		<table id="tab" class="easyui-datagrid">
			<thead>
				<tr>
					<th data-options="field:'uid',align:'center',width:60">用户ID</th>
					<th data-options="field:'longinName',align:'center',width:100">用户名</th>
					<th data-options="field:'protectEMail',align:'center',width:160">邮箱</th>
					<th data-options="field:'protectMTel',align:'center',width:100,">手机号</th>
					<th data-options="field:'isLockout',align:'center',width:100,">是否锁定</th>
					<th data-options="field:'createTime',align:'center',width:160,">创建时间</th>
					<th data-options="field:'lastLoginTime',align:'center',width:160,">最后登录的时间</th>
					<th data-options="field:'psdWrongTime',align:'center',width:80,formatter:formatterPsd">密码错误次数</th>
					<th data-options="field:'caozuo',align:'center',width:100,formatter:formatterCaozuo">操作</th>
					<th data-options="field:'juese',align:'center',width:60,formatter:formatterJuese">角色设置</th>
					<th data-options="field:'passWord',align:'center',width:60,formatter:formatterPassword">密码设置</th>
					<th data-options="field:'suoDing',align:'center',width:120,formatter:formatterSuoDing">锁定管理</th>
				</tr>
			</thead>
		</table>
		
		<!--多条件查询-->
		<div id="usertb" style="padding:5px; height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">添加</a>
				<form id="tb-frm">
				用户名: <input class="easyui-textbox" type="text" id="userName" name="userName">
				起止时间: <input class="easyui-datebox" id="beginDates" name="beginDates"/>-<input class="easyui-datebox" id="endDates" name="endDates"/>
				是否锁定: <select id="isLock" class="easyui-combobox" name="isLock" style="height:auto;">
						    <option value="">---请选择---</option>
						    <option value="是">是</option>
						    <option value="否">否</option>
					    </select>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchUser()">搜索</a>
				</form>
			</div>
		</div>
<!-- 修改的对话框 -->
 <div id="editDialog" class="easyui-dialog" data-options="modal:true,title:'修改用户信息',closed:true,
	buttons:[{
		text:'保存',
		handler:function(){
			saveEdit();
		}
	},{
		text:'取消',
		handler:function(){
			closeEdit();
		}
	}]
	">
		<form class="easyui-form" id="frm1">
			<table cellpadding="5">
				<tr>
					<td>
						<label for="name">用户Id:</label>
					</td>
					<td>
						 <input disabled="disabled" class="easyui-validatebox" type="text" id="uid2" name="uid" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">用户名:</label>
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="uname" name="longinName" data-options="required:true,validType:'longinName'" disabled="disabled" />
					</td>
				</tr>
				
				<tr>
					<td>
						<label for="name">邮箱:</label>
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="uemail" name="protectEMail" data-options="required:true,validType:'email'" />
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">手机号:</label>
					</td>
					<td>
						<input class="easyui-numberbox" type="text" id="uphone" name="protectMTel"  data-options="required:true,validType:['minLength[11]','maxLength[11]']"  />
					</td>
				</tr>
			</table>
		</form>
	</div> 
		
		<!-- 查看用户信息 -->
		 <div id="look-window" title="查看用户信息" class="easyui-window" data-options="modal:true,closed:true" style="width: 300px;height: 400px;">
			<form id="lookForm">
			<table cellpadding="5">
			          <tr>
                        <td>用户ID:</td>
                        <td><input disabled="disabled" type="text" name="uid" id="uid"></input></td>
                     </tr>
                    <tr>
                        <td>用户名:</td>
                        <td><input disabled="disabled" type="text" name="longinName" id="longinName"></input></td>
                    </tr>
                    <tr>
                        <td>邮箱:</td>
                        <td><input disabled="disabled" type="text" name="protectEMail" id="protectEMail"></input></td>
                    </tr>
                    <tr>
                        <td>手机号:</td>
                        <td><input disabled="disabled" type="text" name="protectMTel" id="protectMTel"></input></td>
                    </tr>
                    
                    <tr>
                        <td>是否锁定:</td>
                        <td><input disabled="disabled" type="text" name="isLockout" id="isLockout"></input></td>
                    </tr>
               
                    <tr>
                        <td>创建时间:</td>
                        <td><input disabled="disabled" type="text" name="createTime" id="createTime"></input></td>
                    </tr> 
                    
                    <tr>
                        <td>最后登录时间:</td>
                        <td><input disabled="disabled" type="text" name="lastLoginTime" id="lastLoginTime"></input></td>
                    </tr> 
                 </table>
			</form>
		</div>
		
 <!--  添加用户 -->
	<div id="adduser_dialog" class="easyui-dialog" data-options="modal:true,title:'新增用戶信息',closed:true,
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
         <form id="adduserForm">
                <table cellpadding="5">
                    <tr>
                        <td>用户名:</td>
                        <td><input class="easyui-textbox" type="text" name="name" id="name" data-options="required:true,missingMessage:'请输入用户名'"></input></td>
                    </tr>
                    <tr>
                        <td>用戶密码:</td>
                        <td><input type="text" class="easyui-textbox" id="pwd" name="pwd" data-options="required:true"></td>
                    </tr>
                    <tr>
                        <td>邮箱:</td>
                        <td><input class="easyui-textbox" type="text" name="email" id="email" data-options="required:true,validType:'email'"></input></td>
                    </tr>
               
                    <tr>
                        <td>手机号:</td>
                        <td><input type="text" class="easyui-numberbox" id="mtel" name="mtel" data-options="required:true,validType:['minLength[11]','maxLength[11]']"></td>
                    </tr> 
                    <tr style="display: none">
                        <td>是否锁定:</td> 
                        <td><input type="text" class="easyui-numberbox" id="isSuoding" name="isSuoding"></td>
                    </tr> 
                    <tr style="display: none"> 
                        <td>密码错误次数:</td>
                        <td><input type="text" class="easyui-numberbox" id="wrongPwd" name="wrongPwd" ></td>
                    </tr>  
                    
                </table>
         </form>
    </div>
    
    <!--设置用户角色-->
		<div id="ToRole" class="easyui-window" title="设置用户角色" style="width:700px;height:520px" data-options="iconCls:'icon-save',modal:true,closed:true">
			<div style="width:200px;float: left;">
			<table  class="easyui-datagrid" id="juese" title="系统角色列表" data-options="rownumbers:true,singleSelect:true,pagination:true,method:'post',pageSize:10">
			<thead>
				<tr>
					<th data-options="field:'rid',width:280,hidden:true">ID</th>
					<th data-options="field:'roleName',width:100">角色名称</th>
				</tr>
			</thead>
			</table>
			</div>
				<div style="width: 100px;float: left;text-align: center;padding-top: 150px;padding-left: 120px;">
					您当前正在为：<div id="parentModulenames" style="font-size: 25px;color: red;"></div>设置角色！
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="addUserRole()">添加角色</a><br />
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteUserRole()">移除角色</a>
				</div>
			<div style="width:200px;float:right;">
			<table  class="easyui-datagrid" id="User-juese" title="当前用户角色列表" data-options="rownumbers:true,singleSelect:true,pagination:true,method:'post',pageSize:10">
			<thead>
				<tr>
					<th data-options="field:'rid',width:280,hidden:true,formatter:formatterRid">ID</th>
					<th data-options="field:'roleName',width:100,formatter:formatterRole">角色名称</th>
				</tr>
			</thead>
			</table>
			</div>
		</div>
		
	</body>

</html>