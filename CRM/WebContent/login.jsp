<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
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
<%
	Cookie[] cookies=request.getCookies(); //读出用户硬盘上的Cookie
	String longinName=null;
	String passWord=null;
	String isLogin=null;
	if(cookies!=null){
		response.setContentType("text/html;charset=utf-8");
		for(int i=0;i<cookies.length;i++){
			 if("longinName".equals(cookies[i].getName())){
				 longinName = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
			 }
			 if("passWord".equals(cookies[i].getName())){
				 passWord = URLDecoder.decode(cookies[i].getValue(), "UTF-8");    
			 }  
			 if("isLogin".equals(cookies[i].getName())){
				 isLogin = cookies[i].getValue();    
			 }
		}
	}
%>
	$(function() {
		$("#login").dialog({
			width : 500,
			height : 300,
			title : '登录系统',
			closable : false,
			draggable:false
		}); 
		var str = "<%=longinName%>";
		if(str!=null && str!="null"){
			window.location.href="index.jsp"
		}else{
			var str = sessionStorage.obj;
			if($("#is").val()=="yes"){
				if(str!=null){
					var obj = $.parseJSON(str);
					if(obj=="no"){
						var box=document.getElementById("isLogin");      
						box.checked=true
					}else{
						var box=document.getElementById("isLogin");      
						box.checked=true;
					}
				}else{
					var box=document.getElementById("isLogin");      
					box.checked=true;
				}
			}	
		}
	});
	function sub() {
		var isLogin = $("#isLogin").prop("checked");
		if(isLogin){
			isLogin="yes"
		}else{
			isLogin="no"
		}
		var uname = $("#uname").val();
		var pwd = $("#pwd").val();
		var flg = $("#loginForm").form("validate");
		var yzm = $("#yzm").val();
		if (flg) {
			$.post('login', {
				LonginName : uname,
				PassWord : pwd,
				isLogin : isLogin,
				yzm:yzm
			}, function(data) {
				if (data.success) {
					//alert(JSON.stringify(data.message));
					//$("#p").show();
					var value = $('#p').progressbar('getValue');
					var set = setInterval(function(){
						value+= 10;
						$('#p').progressbar('setValue', value);
						if(value>100){
							window.clearInterval(set);
							$.messager.alert("提示", data.remark);
							sessionStorage.obj = JSON.stringify(data.message.LonginName);
							window.location.href = "index.jsp";
						}
					}, 50);
				} else {
					$.messager.alert("错误提示", data.remark);
				}
			}, 'json');
		}
	}
	function clearVal() {
		$("#uname").val("");
		$("#pwd").val("");
		$("#verifyCode").val("");
	}
	function reloadCode(){
		var time = new Date();
		document.getElementById('safecode').src = '<%=request.getContextPath()%>/image?d=' + time;
	}
</script>
<style type="text/css">
#login {
	font-size: 15px;
}

#pwd {
	margin-bottom: 10px;
}

.inp {
	width: 180px;
	height: 22px;
	border-radius: 5px;
}

form {
	margin-top:50px;
	margin-left: 100px;
}
</style>
</head>
<body>
<div id="p" class="easyui-progressbar" data-options="value:0" style="display:none;width:100%;margin-top: 100px"></div> 
	<div id="login">
		<form id="loginForm">
		<input style="display: none" id="is" value="<%=isLogin%>">
			<table>
				<tr>
					<td>用户名</td>
					<td><input type="text" class="easyui-validatebox inp"
						data-options="required:true" missingMessage="用户名不能为空"
						invalidMessage="用户名必须填写" id="uname" value=""/><br /></td>
				</tr>
				<tr>
					<td>密     码</td>
					<td><input type="password" class="easyui-validatebox inp"
						data-options="required:true" missingMessage="密码不能为空"
						invalidMessage="密码必须填写" id="pwd" value=""
						placeholder="第三次密码错误将锁定用户" /><br /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="checkbox" id="isLogin"
						name="isLogin">七天免登录
					</td>
				</tr>
				<tr>
					<td>验证码</td>
					<td>
						<img src="<%=request.getContextPath()%>/image" onclick="reloadCode()" id="safecode"><br/>
						<input type="text" class="easyui-validatebox" id="yzm"  name="verifyCode" size="5" />
    				</td>
				</tr>
				<tr>
					<td></td>
					<td><a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'" onclick="sub()">登录系统</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'" onclick="clearVal()">清空输入</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>