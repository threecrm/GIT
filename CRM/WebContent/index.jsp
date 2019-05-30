<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.net.URLDecoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户资源管理系统</title>
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
<%Cookie[] cookies = request.getCookies(); //读出用户硬盘上的Cookie
			String longinName = null;
			if (cookies != null) {
				response.setContentType("text/html;charset=utf-8");
				for (int i = 0; i < cookies.length; i++) {
					if ("longinName".equals(cookies[i].getName())) {
						longinName = URLDecoder.decode(cookies[i].getValue(),
								"UTF-8");
					}
				}
			}%>
	$(function(){
		var str = "<%=longinName%>";
		if(str!=null && str!="null"){
			$('#tt').tree({    
			    url:'roleModules',
			    method:"post",
			    lines:true,
			    queryParams:{
			    	loginName:str
			    }
			}); 
			$.post("selectRoleName",{loginName:str});
		}else{
			var st = sessionStorage.obj;
			if(st!=null && st!=""){
				var obj = $.parseJSON(st);
				$('#tt').tree({    
				    url:'roleModules',
				    method:"post",
				    lines:true,
				    queryParams:{
				    	loginName:obj
				    }
				}); 
				$.post("selectRoleName",{loginName:obj});
				userinfo(st)
			}
		}
		
	    $('#tt').tree({
	            onSelect:function(node){
	                var isLeaf = $('#tt').tree('isLeaf',node.target); //是否是叶子节点
	                var flag = $("#tabs").tabs('exists',node.text);
	                if (isLeaf) {//只有叶子节点才会在选项卡中创建选项页（每个选项页对应1个功能）
	                	if(!flag){
	                		 $('#tabs').tabs('add', { //在选项卡中，创建1个选项页
	                             title: node.text,   //选项卡中，选项页的标题（在同一个选项卡中，选项页需要保持一致）。
	                             closable: true,
	                             content: "<iframe style='width:100%;height:700px' src='"+node.url+".jsp'/>"    //此处做了调整，推荐使用iframe的方式实现
	                        });
	                	}else{
	                		 $("#tabs").tabs("select",node.text);
	                	}
	                }
	            }
	        });
	})
	function xianshi(){
		$('#mm').menu('show', {    
			  left: 1700,    
			  top: 40   
		});
	}
	function drop(){
		$.messager.confirm("提示信息","确定退出吗？",function(r){
			if(r){
				$.post("drop",{
					drop:""
				},function(res){
					if(res==true){
						sessionStorage.obj = JSON.stringify("no");
						window.location.href="login.jsp";
					}
				},"json")
			}
		})
		
	}
	
	function pim(){
		var uid="<%=session.getAttribute("uid")%>"
		
		$.post("selectUsersInfo",{
			uid:uid
		},function(data){
			
			$("#userlonginName").val(data.longinName)
			$("#userprotectEMail").val(data.protectEMail)
			$("#userprotectMTel").val(data.protectMTel)
		});
		$("#win").window("open")
	}
	 function userinfo(s){
		$.post("userinfo",{
			loginName:s
		});
	} 
	
	//修改用户信息
		function UserSafety(){
			
			$("#UpdateWin").window("open")
		}
	 function submit() {
	   var u_id="<%=session.getAttribute("uid")%>"
		var password = $("#beforpassword").val();
		var newPassword = $("#newPassword").val();
		var trueNewPassword = $("#trueNewPassword").val();
		var flg = $("#Updateform").form("validate");
		if (flg) {
			$.messager.confirm("提示信息", "确定修改吗？", function(r) {
				$.post("updateUserPassword", {
					uid : u_id,
					PassWord : password,
					newPassword : newPassword,
					trueNewPassword : trueNewPassword
				}, function(res) {

					if (res == 0) {
						$.messager.alert("提示", "原密码不正确");
					} else if (res == -1) {
						$.messager.alert("提示", "两次密码不一致");
					} else if (res > 0) {
						$.messager.alert("提示", "修改成功请从新登陆");
						//清除session
						$.post("drop", {}, function(res) {
							if (res == true) {
							}
						})
						$.post("session", {}, function(res) {
							if (res == true) {
							}
						})
						//修改成功后从新转到登陆页面
						window.location.href = "login.jsp";
					} else {
						$.messager.alert("提示", "修改失败");
					}
				}, 'json');
			})
		}
	}

	function closed() {
		$("#UpdateWin").window("close")
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
	
	//修改个人信息
	 function updatePim(){
	    var uid="<%=session.getAttribute("uid")%>"
		var ProtectEMail = $("#userprotectEMail").val();
		var ProtectMTel = $("#userprotectMTel").val();
		$.post("updatePim", {
			uid : uid,
			ProtectEMail : ProtectEMail,
			ProtectMTel : ProtectMTel
		}, function(res) {
			if (res > 0) {
				$("#win").dialog("close");
				$.messager.alert("提示", "修改成功");
			} else {
				$.messager.alert("提示", "修改失败")
			}

		}, "json")

	}

	//签到
	function qiandao() {
		$.post("addSign", {}, function(res) {
			if (res > 0) {
				if (res == 2) {
					$.messager.alert("提示", "你今天已经签到过了");
				} else if (res == 3) {
					$.messager.alert("提示", "上班时间还没到哦");
				} else {
					$.messager.alert("提示", "上班啦！签到成功");
				}

			} else {
				if (res == 0) {
					$.messager.alert("提示", "签到失败");
				} else {
					$.messager.alert("提示", "签到成功,下次注意上班时间不要再迟到了哦");
				}

			}
		}, "json")
	}
	//签退
	function qiantui() {
		$.post("updateSign", {}, function(res) {
			if (res == 1) {
				$.messager.alert("提示", "下班啦,签退成功");
			} else if (res == 0) {
				$.messager.alert("提示", "你今天已经签退过了");
			} else if (res == 3) {
				$.messager.alert("提示", "还没有下班哦,你早退了");
			} else if (res == 2) {
				$.messager.alert("提示", "您超出了签退时间，已为您自动签退");
			}
		}, "json")
	}
</script>
<style type="text/css">
span {
	color: red;
}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="width: 100%; height: 40px;">
		<div style="margin-left: 10%">
			<%
				if (longinName != null) {
			%>
			欢迎<a href="javascript:void(0)" class="easyui-linkbutton" plain="true"><%=longinName%></a>登录
			<%
				} else {
			%>
			欢迎<a href="javascript:void(0)" class="easyui-linkbutton" plain="true">${isdenglu }</a>登录
			<%
				}
			%>
			<span
				style="margin-left: 30%; color: black; font-size: 20px; font-weight: bold;">欢迎使用CRM管理系统</span>
			<span style="margin-left: 40%"><a href="javascript:void(0)"
				class="easyui-linkbutton" plain="true" onclick="xianshi()">设置</a></span>
			<div id="mm" class="easyui-menu" style="width: 120px;">
				<div data-options="" onclick="qiandao()">签到</div>
				<div data-options="" onclick="qiantui()">签退</div>
				<div data-options="" onclick="pim()">个人信息</div>
				<div data-options="" onclick="UserSafety()">安全设置</div>
				<div class="menu-sep"></div>
				<div onclick="drop()">退出</div>
			</div>
		</div>
	</div>
	<div data-options="region:'west',title:'菜单导航'" style="width: 200px;">
		<ul id="tt"></ul>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs">
			<c:if test="${selectName =='true'}">
				<div title="默认页">
					<iframe style="width: 100%; height: 700px" src="image.jsp"></iframe>
				</div>
			</c:if>
		</div>
	</div>
	<!-- 用户信息 -->
	<div id="win" class="easyui-window" title="个人信息" modal="true"
		style="width: 73%; height: 80%;"
		data-options="collapsible:false,minimizable:false,closed:true">
		<div style="margin-left: 40%; margin-top: 10%">
			<label for="name">用户名:</label> <input class="easyui-validatebox"
				type="text" id="userlonginName" readonly="true" /><br> <br>
			<label for="name">邮箱&emsp;:</label> <input class="easyui-validatebox"
				type="text" id="userprotectEMail"
				data-options="required:true,validType:'email'" /><br> <br>
			<label for="name">电话&emsp;:</label> <input class="easyui-validatebox"
				type="text" id="userprotectMTel"
				data-options="required:true,validType:['minLength[11]','maxLength[11]']" /><br>
			<br> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" onclick="updatePim()">修改个人信息</a>
		</div>
	</div>
	<!-- 修改用户 -->
	<div id="UpdateWin" class="easyui-window" title="个人信息" modal="true"
		style="width: 73%; height: 80%;"
		data-options="collapsible:false,minimizable:false,closed:true">
		<form id="Updateform" class="easyui-form"
			style="margin-left: 40%; margin-top: 10%">
			<%
				if (longinName != null) {
			%>
			<div>
				<label for="name">登录名&emsp;:</label> <input
					class="easyui-validatebox" type="text" readonly="true"
					id="updateUserName" value="<%=longinName%>" />
			</div>
			<%
				} else {
			%>
			<div>
				<label for="name">登录名&emsp;:</label> <input
					class="easyui-validatebox" type="text" readonly="true"
					id="updateUserName" value="${ isdenglu}" />
			</div>
			<%
				}
			%>
			<br>
			<div>
				<label for="name">原始密码:</label> <input class="easyui-validatebox"
					type="password" data-options="required:true" id="beforpassword" />
			</div>
			<br>
			<div>
				<label for="name">新改密码:</label> <input class="easyui-validatebox"
					type="password" data-options="required:true" id="newPassword" />
			</div>
			<br>
			<div>
				<label for="name">确认密码:</label> <input class="easyui-validatebox"
					type="password" data-options="required:true" id="trueNewPassword" />
			</div>
			<br> &emsp;&emsp;&emsp;&emsp;&emsp;<a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="submit()">提交</a>&emsp;&emsp; <a
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="closed()">取消</a>
		</form>
	</div>
</body>
</html>