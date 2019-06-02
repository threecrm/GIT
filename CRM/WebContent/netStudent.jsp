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
<!--导出excel的文件包 -->
<script type="text/javascript"
	src="js/jquery-easyui-1.4.3/datagrid-export.js"></script>
<style type="text/css">
.a {
	width: 100px;
}
</style>
<script type="text/javascript">
	$(function() {
		init();
		fenliang();
	})
	function fenliang(){
		$.post("selectFenLiangOpen",{
			AskName:"${LoginUserName}"
		},function(data){
			if(data.bakContent=="已关闭"){
				 $('#tur').switchbutton({ 
				      checked: false,
				      readonly:true
				    }) 
				 document.getElementById("fenliang").style.display = "block"; 
			}else{
				 $('#tur').switchbutton({ 
				      checked: true,
				      readonly:true
				    }) 
				document.getElementById("fenliang").style.display = "block"; 
			}
		})
	}
	function formatterSet(value, row, index) {
		return "<a href='javascrip:void(0)' onclick='look("
				+ index
				+ ")'>查看</a>  <a href='javascrip:void(0)' onclick='updateNetStudent("
				+ index
				+ ")'>修改 </a>   <a href='javascrip:void(0)'  onclick='updateShixiao("
				+ index + ")'>失效</a>  "
				+"<a href='javascrip:void(0)'  onclick='genzongrizhi("+index+")'>查看跟踪日志</a>"
	}
	function init() {
		/* 	alert($("#LoginUserName").val()) */
		$("#tabs")
				.datagrid(
						{
							url : 'selectNetStudent',
							method : 'post',
							pagination : true,
							toolbar : '#seachId',
							queryParams : {
								askName : $("#LoginUserName").val(),
								sname : $("#sname").val(),
								isReturnVisit : $("#isReturnVisit").combobox(
										"getValue"),
								phone : $("#phone").val(),
								QQ : $("#qq").val(),
								isPay : $("#isPay").datetimebox("getValue"),
								isValid : $("#isValid").datetimebox("getValue"),
								StartcreatTimes : $("#StartcreatTimes")
										.datetimebox("getValue"),
								EndcreatTimes : $("#EndcreatTimes")
										.datetimebox("getValue"),
								StarthomeTime : $("#StarthomeTime")
										.datetimebox("getValue"),
								EndhomeTime : $("#EndhomeTime").datetimebox(
										"getValue"),
								StartfirstVisitTime : $("#StartfirstVisitTime")
										.datetimebox("getValue"),
								EndfirstVisitTime : $("#EndfirstVisitTime")
										.datetimebox("getValue"),
								StartpayTime : $("#StartpayTime").datetimebox(
										"getValue"),
								EndpayTime : $("#EndpayTime").datetimebox(
										"getValue"),
								StartinClassTime : $("#StartinClassTime")
										.datetimebox("getValue"),
								EndinClassTime : $("#EndinClassTime")
										.datetimebox("getValue")
							}

						})
	}
 
	//动态显示
	function shezhi() {
		$("#dtcx_students").window("open");
	}
	function checkOrClose(object) {
		if (object.checked == true) {
			$("#tabs").datagrid("showColumn", object.name);
		} else {
			$("#tabs").datagrid("hideColumn", object.name);
		}
	}
	function checkOrCloseAll(obj) {
		$(".checkeds").prop('checked', $(obj).prop('checked'));
		if (obj.checked == true) {
			$(".checkeds:checked").each(function() {
				$("#tabs").datagrid("showColumn", this.name);
			});
		} else {
			$(".checkeds").each(function() {
				$("#tabs").datagrid("hideColumn", this.name);
			});
		}
	}
	// 添加学生
	function add() {
		$("#addWindow").window("open")
	}
	// 添加学生确认按钮
	function addtrue() {
		var tur = $("#tur").switchbutton("options").checked;
		$.post("addNetStudent", {
			AskerId : $("#uid").val(),
			Sname : $("#addSname").val(),
			Sex : $("#addSex").combobox("getValue"),
			Age : $("#addAge").val(),
			Phone : $("#addPhone").val(),
			StuStatus : $("#addStuStatus").combobox("getValue"),
			PerStatus : $("#addPerStatus").combobox("getValue"),
			MsgSource : $("#addMsgSource").combobox("getValue"),
			SourceUrl : $("#addSourceUrl").combobox("getValue"),
			SourceKeyWord : $("#addSourceKeyWord").val(),
			QQ : $("#addQQ").val(),
			WeiXin : $("#addWeiXin").val(),
			isBaoBei : $("#addisBaoBei").combobox("getValue"),
			Content : $("#addContent").val(),
			IsReturnVisit : $("#AddisReturnVisit").combobox("getValue"),
			tur:tur
		}, function(res) {
			if (res == -1) {
				$.messager.alert("提示", "请输入姓名");
			} else if (res == -2) {
				$.messager.alert("提示", "请输入年龄");
			} else if (res == -3) {
				$.messager.alert("提示", "请输入正确格式电话，手机号长度必须为11位");
			} else if (res == -4) {
				$.messager.alert("提示", "请输入来源关键字");
			} else if (res == -5) {
				$.messager.alert("提示", "请输入正确格式的邮箱");
			} else if (res == -6) {
				$.messager.alert("提示", "请输入微信号");
			} else if (res == -7) {
				$.messager.alert("提示", "请输入在线备注");
			} else if (res == -8) {
				$.messager.alert("提示", "请输入性别");
			} else if (res == -9) {
				$.messager.alert("提示", "请输入状态");
			} else if (res == -10) {
				$.messager.alert("提示", "请输入学历");
			} else if (res == -11) {
				$.messager.alert("提示", "请输入来源渠道");
			} else if (res == -12) {
				$.messager.alert("提示", "请输入来源网站");
			} else if (res == -13) {
				$.messager.alert("提示", "请输入是否报备");
			} else if (res == -14) {
				$.messager.alert("提示", "请输入回访情况");
			} else if (res > 0) {
				$.messager.alert("提示", "添加成功");
				$("#addWindow").window("close");
				$("#tabs").datagrid("reload");
				$("#addForm").form("reset");
			} else {
				$.messager.alert("提示", "添加失败");
			}

		}, "json")
	}
	// 添加学生取消按钮
	function addfalse() {
		$("#addWindow").window("close");
	}


	//所有学生
	function AllshixiaoStudent() {
		$("#tabs").datagrid({
			url : 'selectAllNetStudent',
			method : 'post',
			queryParams : {
				askName : $("#LoginUserName").val()
			}
		})
	}
	//修改
	function updateNetStudent(index) {
		var data = $("#tabs").datagrid("getData");
		var row = data.rows[index];
		$("#updateForm").form("load", row);
		$("#updateWindow").window("open");
	}
	// 修改确认按钮
	function updatetrue() {
		$.post("updateShixiaoStudent",
				{
					sid : $("#updatesid").val(),
					LearnForword : $("#updateLearnForword")
							.combobox("getValue"),
					IsReturnVisit : $("#updateIsReturnVisit").combobox(
							"getValue"),
					FirstVisitTime : $("#updateFirstVisitTime").datetimebox(
							"getValue"),
					isHome : $("#updateisHome").combobox("getValue"),
					homeTime : $("#updatehomeTime").datetimebox("getValue"),
					IsPay : $("#updateIsPay").combobox("getValue"),
					PayTime : $("#updatePayTime").datetimebox("getValue")
				}, function(res) {
					if (res > 0) {
						$.messager.alert("提示", "修改成功");
						$("#updateWindow").window("close");
						$("#tabs").datagrid("reload");
					} else {
						$.messager.alert("提示", "修改失败");
					}
				}, "json")

	}
	// 修改取消按钮
	function updatefalse() {
		$("#updateWindow").window("close");

	}
	//修改
	function updateNetStudent(index) {
		var data = $("#tabs").datagrid("getData");
		var row = data.rows[index];
		$("#updateForm").form("load", row);
		$("#updateWindow").window("open");
	}
	// 修改确认按钮
	function updatetrue() {
		$.post("updateNetStudent",
				{
					sid : $("#updatesid").val(),
					LearnForword : $("#updateLearnForword")
							.combobox("getValue"),
					IsReturnVisit : $("#updateIsReturnVisit").combobox(
							"getValue"),
					FirstVisitTime : $("#updateFirstVisitTime").datetimebox(
							"getValue"),
					isHome : $("#updateisHome").combobox("getValue"),
					homeTime : $("#updatehomeTime").datetimebox("getValue"),
					IsPay : $("#updateIsPay").combobox("getValue"),
					PayTime : $("#updatePayTime").datetimebox("getValue")
				}, function(res) {
					if (res > 0) {
						$.messager.alert("提示", "修改成功");
						$("#updateWindow").window("close");
						$("#tabs").datagrid("reload");
					} else {
						$.messager.alert("提示", "修改失败");
					}
				}, "json")

	}
	// 修改取消按钮
	function updatefalse() {
		$("#updateWindow").window("close");

	}

	//失效
	function updateShixiao(index) {
		var data = $("#tabs").datagrid("getData");
		var row = data.rows[index];
		$.post("updateShixiaoStudent", {
			Sname : row.sname
		}, function(res) {
			if (res > 0) {
				$.messager.alert("提示", "设置成功");
				$("#tabs").datagrid("reload");
			} else {
				$.messager.alert("提示", "你已经在跟踪中，不能进行失效");
			}
		}, "json")
	}
	//失效学生
	function shixiaoStudent() {
		$("#tabs").datagrid({
			url : 'selectShiXiaoStudent',
			method : 'post',
			queryParams : {
				askName : $("#LoginUserName").val()
			}
		})
	}
	//查看
	function look(index) {
		var data = $("#tabs").datagrid("getData");
		var row = data.rows[index];
		$("#updateForm").form("load", row);
		$("#updateWindow").window("open");
		$.post("selectAsks", {
			id : row.sid
		}, function(res) {
			$("#updateTeacher").textbox("setText", res.askName);//往input里面赋值（赋的值是另一个表的内容）
			$("#updateJiaose").textbox("setText", res.roleNames);

		})

	}
	//跟踪
	function genzong() {
		var row = $("#tabs").datagrid("getSelected"); // 获取表格table选中的行
		$("#genzongForm").form("load", row);
		var rows = $("#tabs").datagrid("getSelections"); // 获取复选框选中的行
		if (rows.length == 0) {
			$.messager.alert("提示", "请选择要操作的行！", "info");
			return;
		} else {
			$("#genzongid").window("open");
		}
	}

	function genzongtrue() {
		$.post("addNetGenzong", {
			StudentId : $("#studentID1").val(),
			n_sname : $("#studentsname1").val(),
			n_date : $("#n_date").datetimebox("getValue"),
			n_aftertime : $("#n_aftertime").datetimebox("getValue"),
			n_ask : $("#n_ask").val(),
			uname : $("#LoginUserName").val(),
			n_fangshi : $("#n_fangshi").combobox("getValue"),
			n_qingkuang : $("#n_qingkuang").combobox("getValue")

		}, function(res) {
			if (res > 0) {
				$.messager.alert("提示", "跟踪成功");
				$("#genzongid").window("close");
				$("#tabs").datagrid("reload");
				$("#genzongForm").form("reset");

			} else {
				$.messager.alert("提示", $("#studentsname1").val()
						+ "你已跟踪完成，请选择其他学生进行跟踪");
				$("#genzongid").window("close");
				$("#genzongForm").form("reset");
			}

		}, "json")

	}
	function genzongfalse() {
		$("#genzongid").window("close")
	}

	// 导出excel(导 js/jquery-easyui-1.4.3/datagrid-export.js 包)
	function exportExcel() {
		$('#tabs').datagrid('toExcel', 'dg.xls');
	}
	
	//查看跟踪日志
	function genzongrizhi(index){
		 var date=$("#tabs").datagrid("getData");
		 var row=date.rows[index];
		  $("#tab").datagrid({
			 	 url:'selectgenzongrizhi',
			 	 method:'post',
			 	queryParams:{
			 	 name:row.sname
			 				 }
			 		 })
			 $("#genzongrizhiId").window("open") ;
	}
</script>
</head>
<body>
	<input type="hidden" id="LoginUserName" value="${LoginUserName}">
	<!--获取登陆者姓名  -->
	<input type="hidden" id="uid" value="${uid}">
	<!--获取登陆者id  -->
	<div id="seachId">
		<table>
			<tr>
				<td>学生姓名:<input class="easyui-textbox a" id="sname"></td>
				<td>电话：<input class="easyui-textbox a" id="phone"></td>
				<td>邮箱：<input class="easyui-textbox a" id="qq"></td>
				<td>是否有效： <select id="isValid" class="easyui-combobox a" data-options="editable:false">
						<option value=''>--请选择--</option>
						<option>是</option>
						<option>否</option>
				</select></td>
				<td>回访情况: <select id="isReturnVisit" class="easyui-combobox a" data-options="editable:false">
						<option value=''>--请选择--</option>
						<option>已回访</option>
						<option>未回访</option>
						<option>思量</option>
						<option>上门未报名</option>
						<option>报名未进班</option>
						<option>未上门</option>
				</select></td>
				<td>是否缴费： <select id="isPay" class="easyui-combobox a" data-options="editable:false">
						<option value=''>--请选择--</option>
						<option>是</option>
						<option>否</option>
				</select></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>创建时间：<input class="easyui-datetimebox" id="StartcreatTimes">~<input
					class="easyui-datetimebox" id="EndcreatTimes">
				</td>
				<td>上门时间:<input class="easyui-datetimebox" id="StarthomeTime">~<input
					class="easyui-datetimebox" id="EndhomeTime"></td>
				<td>首次回访时间：<input class="easyui-datetimebox"
					id="StartfirstVisitTime">~<input class="easyui-datetimebox"
					id="EndfirstVisitTime"></td>
			</tr>
			<tr>
				<td>缴费时间：<input class="easyui-datetimebox" id="StartpayTime">~<input
					class="easyui-datetimebox" id="EndpayTime"></td>
				<td>进班时间:<input class="easyui-datetimebox"
					id="StartinClassTime">~<input class="easyui-datetimebox"
					id="EndinClassTime">
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="init()">搜索</a></td>
				<td><a class="easyui-linkbutton" plain="true"
					onclick="exportExcel()" id="serach"
					data-options="iconCls:'icon-print'">导出excel</a></td>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" onclick="add()">添加</a></td>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-edit'" onclick='shezhi()'>设置</a></td>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick='genzong()'>跟踪</a></td>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" onclick='shixiaoStudent()'>失效学生</a></td>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" onclick='AllshixiaoStudent()'>所有学生</a></td>
			</tr>
		</table>
	</div>
	<table id="tabs" class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
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
				<th data-options="field:'qq',title:'邮箱'"></th>
				<th data-options="field:'weiXin',title:'微信号'"></th>
				<th data-options="field:'content',title:'备注'"></th>
				<th data-options="field:'creatTimes',title:'创建时间'"></th>
				<th data-options="field:'learnForword',title:'课程方向'"></th>
				<th data-options="field:'isValid',title:'是否有效'"></th>
				<!-- <th data-options="field:'reoord',title:''"></th> -->
				<th data-options="field:'isReturnVisit',title:'回访情况'"></th>
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
				<!-- <th data-options="field:'isDel',title:''"></th>
				<th data-options="field:'fromPart',title:''"></th> -->
				<th data-options="field:'stuConcern',title:'学生担忧情况'"></th>
				<th data-options="field:'isBaoBei',title:'是否报备'"></th>
				<th data-options="field:'ziXunName',title:'咨询内容'"></th>
				<!-- <th data-options="field:'creatUser',title:''"></th> -->
				<th data-options="field:'returnMoneyReason',title:'退费原因'"></th>
				<th data-options="field:'preMoney',title:'定金金额'"></th>
				<th data-options="field:'preMoneyTime',title:'交定金时间'"></th>
				<th data-options="field:'askerId',title:'咨询师编号'"></th>
				<th data-options="field:'hahha',title:'操作' ,formatter:formatterSet"></th>
			</tr>
		</thead>
	</table>
	<!-- 动态显示 -->
	<div id="dtcx_students" class="easyui-dialog" title="设置要显示的列"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 500px; height: 200px; padding: 10px;">
		<table>
			<tr>
				<td><input checked="checked" type="checkbox"
					onclick="checkOrCloseAll(this)" /></td>
				<td>全选</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="sid" onclick="checkOrClose(this)" /></td>
				<td>学生编号</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="sname" onclick="checkOrClose(this)" /></td>
				<td>姓名</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="age" onclick="checkOrClose(this)" /></td>
				<td>年龄</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="sex" onclick="checkOrClose(this)" /></td>
				<td>性别</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="phone" onclick="checkOrClose(this)" /></td>
				<td>电话</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="stuStatus" onclick="checkOrClose(this)" /></td>
				<td>学生状态</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="perStatus" onclick="checkOrClose(this)" /></td>
				<td>个人状态</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="msgSource" onclick="checkOrClose(this)" /></td>
				<td>来源渠道</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="sourceUrl" onclick="checkOrClose(this)" /></td>
				<td>来源网址</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="sourceKeyWord" onclick="checkOrClose(this)" /></td>
				<td>来源关键字</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="address" onclick="checkOrClose(this)" /></td>
				<td>学生地址</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="qq" onclick="checkOrClose(this)" /></td>
				<td>qq号</td>

			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="weiXin" onclick="checkOrClose(this)" /></td>
				<td>微信号</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="content" onclick="checkOrClose(this)" /></td>
				<td>备注</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="creatTimes" onclick="checkOrClose(this)" /></td>
				<td>创建时间</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="learnForword" onclick="checkOrClose(this)" /></td>
				<td>课程方向</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="isValid" onclick="checkOrClose(this)" /></td>
				<td>是否有效</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="isReturnVisit" onclick="checkOrClose(this)" /></td>
				<td>回访情况</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="firstVisitTime" onclick="checkOrClose(this)" /></td>
				<td>第一次回访时间</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="isHome" onclick="checkOrClose(this)" /></td>
				<td>是否家访</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="homeTime" onclick="checkOrClose(this)" /></td>
				<td>家访时间</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="lostValid" onclick="checkOrClose(this)" /></td>
				<td>无效原因</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="isPay" onclick="checkOrClose(this)" /></td>
				<td>是否缴费</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="payTime" onclick="checkOrClose(this)" /></td>
				<td>缴费时间</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="money" onclick="checkOrClose(this)" /></td>
				<td>缴费金额</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="isReturnMoney" onclick="checkOrClose(this)" /></td>
				<td>是否退费</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="isInClass" onclick="checkOrClose(this)" /></td>
				<td>是否进班</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="inClassTime" onclick="checkOrClose(this)" /></td>
				<td>进班时间</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="inClassContent" onclick="checkOrClose(this)" /></td>
				<td>进班备注</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="askerContent" onclick="checkOrClose(this)" /></td>
				<td>咨询师备注</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="stuConcern" onclick="checkOrClose(this)" /></td>
				<td>学生担忧情况</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="isBaoBei" onclick="checkOrClose(this)" /></td>
				<td>是否报备</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="ziXunName" onclick="checkOrClose(this)" /></td>
				<td>咨询内容</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="returnMoneyReason" onclick="checkOrClose(this)" /></td>
				<td>退费原因</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="preMoney" onclick="checkOrClose(this)" /></td>
				<td>定金金额</td>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="preMoneyTime" onclick="checkOrClose(this)" /></td>
				<td>交定金时间</td>
			</tr>
			<tr>
				<td><input class="checkeds" checked="checked" type="checkbox"
					name="askerId" onclick="checkOrClose(this)" /></td>
				<td>咨询师编号</td>
			</tr>
		</table>
	</div>
	<!--  添加-->
	<div id="addWindow" class="easyui-window"
		data-options="closed:true,width:600,title:'添加表单'">
		<form id="addForm">
			<table>
				<tr>
					<td>姓名:&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input
						class="easyui-validatebox" type="text" id="addSname"
						placeholder="请输入姓名" data-options="required:true" /></td>
					<td>性别:&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<select id="addSex"
						class="easyui-combobox a" data-options="editable:false">
							<option>--请选择--</option>
							<option>男</option>
							<option>女</option>
					</select></td>
				</tr>
				<tr>
					<td>年龄:&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input
						class="easyui-validatebox" type="text" id="addAge"
						data-options="required:true" placeholder="请输入年龄" /></td>
					<td>学历:&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<select
						id="addStuStatus" class="easyui-combobox a" data-options="editable:false">
							<option>--请选择--</option>
							<option>未知</option>
							<option>大专</option>
							<option>高中</option>
							<option>中专</option>
							<option>初中</option>
							<option>本科</option>
							<option>其它</option>
					</select></td>
				</tr>
				<tr>
					<td>电话:&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input
						class="easyui-validatebox" type="text" id="addPhone"
						data-options="required:true" placeholder="请输入电话" /></td>
					<td>状态:&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<select
						id="addPerStatus" class="easyui-combobox a" data-options="editable:false">
							<option>--请选择--</option>
							<option>未知</option>
							<option>待业</option>
							<option>在职</option>
							<option>在读</option>
					</select></td>
				</tr>
				<tr>
					<td>来源关键词:&ensp;&ensp;<input class="easyui-validatebox"
						type="text" id="addSourceKeyWord" data-options="required:true"
						placeholder="请输入来源关键字" /></td>
					<td>来源渠道:&ensp;&ensp;<select id="addMsgSource"
						class="easyui-combobox a" data-options="editable:false">
							<option>--请选择--</option>
							<option>未知</option>
							<option>百度</option>
							<option>百度移动端</option>
							<option>360</option>
							<option>360移动端</option>
							<option>搜狗</option>
							<option>搜狗移动端</option>
							<option>UC移动端</option>
							<option>直接输入</option>
							<option>自然流量</option>
							<option>直电</option>
							<option>微信</option>
							<option>QQ</option>
					</select></td>
				</tr>
				<tr>
					<td>邮箱::&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;<input
						class="easyui-validatebox" type="text" id="addQQ"
						data-options="required:true" placeholder="请输入学院QQ" /></td>
					<td>回访情况:<select id="AddisReturnVisit"
						class="easyui-combobox a" data-options="editable:false">
							<option value=''>--请选择--</option>
							<option>已回访</option>
							<option>未回访</option>
							<option>思量</option>
							<option>上门未报名</option>
							<option>报名未进班</option>
							<option>未上门</option>
					</select></td>
				</tr>
				<tr>
					<td>微信号::&ensp;&ensp;&ensp;&ensp;<input
						class="easyui-validatebox" type="text" id="addWeiXin"
						data-options="required:true" placeholder="请输入微信号" /></td>
					<td>来源网站:<select id="addSourceUrl" class="easyui-combobox a" data-options="editable:false">
							<option>--请选择--</option>
							<option>其它</option>
							<option>职英B站</option>
							<option>职英A站</option>
							<option>高考站</option>
					</select></td>
				</tr>
				<tr>
					<td>在线备注::&ensp;&ensp;<input class="easyui-validatebox"
						type="text" id="addContent" data-options="required:true"
						placeholder="请输入在线备注" /></td>
					<td>是否报备:<select id="addisBaoBei" class="easyui-combobox a" data-options="editable:false">
							<option value="">--请选择--</option>
							<option>是</option>
							<option>否</option>
					</select></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><a href="javascript:void(0)" icon="icon-ok"
						class="easyui-linkbutton" onclick="addtrue()">提交</a></td>
					<td><a href="javascript:void(0)" icon="icon-cancel"
						class="easyui-linkbutton" onclick="addfalse()">取消</a></td>
					<td style="display: none" id="fenliang">分量开关<input id="tur" class="easyui-switchbutton" data-options="disabled:true"></td>
				</tr>
			</table>
		</form>
	</div>
	<!--修改-->
	<div id="updateWindow" class="easyui-window"
		data-options="closed:true,title:'修改表单'"
		style="width: 400px; height: 400px">
		<form id="updateForm">
			<table>
				<tr>
					<td><label for="name">id:</label></td>
					<td><input class="easyui-textbox" type="text" id="updatesid"
						name="sid" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询师:</label></td>
					<td><input class="easyui-textbox" type="text"
						id="updateTeacher" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询师角色:</label></td>
					<td><input class="easyui-textbox" type="text"
						id="updateJiaose" disabled="disabled" /></td>
				</tr>

				<tr>
					<td><label for="name">姓名:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateSname" name="sname" disabled="disabled" /></td>
				</tr>

				<tr>
					<td><label for="name">性别:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateSex" name="sex" disabled="disabled" /></td>
				</tr>

				<tr>
					<td><label for="name">年龄:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateAge" name="age" disabled="disabled" /></td>
				</tr>

				<tr>
					<td><label for="name">电话:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatePhone" name="phone" disabled="disabled" /></td>
				</tr>

				<tr>
					<td><label for="name">学历:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateStuStatus" name="stuStatus" disabled="disabled" /></td>
				</tr>

				<tr>
					<td><label for="name">状态:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatePerStatus" name="perStatus" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">来源渠道:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateMsgSource" name="msgSource" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">来源网站:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateSourceUrl" name="sourceUrl" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">来源关键词:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateSourceKeyWord" name="sourceKeyWord" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">学生地址:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateAddress" name="address" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询师:</label></td>
					<td><input class="easyui-validatebox" type="text" id="update"
						name="" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">邮箱:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateQQ" name="qq" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">微信号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateWeiXin" name="weiXin" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">备注:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateContent" name="content" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">创建时间:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateCreatTimes" name="creatTimes" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">课程方向:</label></td>
					<td><select id="updateLearnForword" name="learnForword"
						class="easyui-combobox" data-options="editable:false">
							<option value="">--请选择--</option>
							<option>软件开发</option>
							<option>软件设计</option>
							<option>网络营销</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">是否有效:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateIsValid" name="isValid" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">是否回访:</label></td>
					<td><select id="updateIsReturnVisit" name="isReturnVisit"
						class="easyui-combobox" data-options="editable:false">
							<option value=''>--请选择--</option>
							<option>已回访</option>
							<option>未回访</option>
							<option>思量</option>
							<option>上门未报名</option>
							<option>报名未进班</option>
							<option>未上门</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">第一次回访时间:</label></td>
					<td><input class="easyui-datetimebox" type="text"
						id="updateFirstVisitTime" name="firstVisitTime" /></td>
				</tr>
				<tr>
					<td><label for="name">是否家访:</label></td>
					<td><select id="updateisHome" name="isHome"
						class="easyui-combobox" data-options="editable:false">
							<option value="">--请选择--</option>
							<option>是</option>
							<option>否</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">家访时间:</label></td>
					<td><input class="easyui-datetimebox" type="text"
						id="updatehomeTime" name="homeTime" /></td>
				</tr>
				<tr>
					<td><label for="name">无效原因:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateLostValid" name="lostValid" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">是否缴费:</label></td>
					<td><select id="updateIsPay" name="isPay"
						class="easyui-combobox" data-options="editable:false">
							<option value="">--请选择--</option>
							<option>是</option>
							<option>否</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">缴费时间:</label></td>
					<td><input class="easyui-datetimebox" type="text"
						id="updatePayTime" name="payTime" /></td>
				</tr>
				<tr>
					<td><label for="name">缴费金额:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateMoney" name="money" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">是否退费:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateisReturnMoney" name="isReturnMoney" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">是否进班:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateisInClass" name="isInClass" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">是否有效： </label></td>
					<td><select id="isValid" class="easyui-combobox"
						disabled="disabled" data-options="editable:false">
							<option value=''>--请选择--</option>
							<option>是</option>
							<option>否</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">进班时间:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateinClassTime" name="inClassTime" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">进班备注:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateinClassContent" name="inClassContent"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询师备注:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateAskerContent" name="askerContent" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">学生担忧情况:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatestuConcern" name="stuConcern" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">是否报备:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateisBaoBei" name="isBaoBei" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询内容:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateZiXunName" name="ziXunName" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">退费原因:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateReturnMoneyReason" name="returnMoneyReason"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">定金金额:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatePreMoney" name="preMoney" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">交定金时间:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatePreMoneyTime" name="preMoneyTime" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><a href="javascript:void(0)" icon="icon-ok"
						class="easyui-linkbutton" onclick="updatetrue()">提交</a></td>
					<td><a href="javascript:void(0)" icon="icon-cancel"
						class="easyui-linkbutton" onclick="updatefalse()">取消</a></td>
				</tr>


			</table>

		</form>
	</div>

	<!--  添加跟踪-->
	<div id="genzongid" class="easyui-window"
		data-options="closed:true,title:'添加跟踪'"
		style="width: 350px; height: 400px">
		<form id="genzongForm" class="easyui-form">
			<table>
				<tr>
					<td><input class="easyui-textbox" id="studentID1" name="sid"
						type="hidden"></td>
				</tr>
				<tr>
					<td><input class="easyui-textbox" id="studentsname1"
						name="sname" type="hidden"></td>
				</tr>
				<tr>
					<td><input type="hidden" id="LoginUserName"
						value="${LoginUserName}"></td>
				</tr>

				<tr>
					<td>开始跟踪时间:</td>
					<td><input class="easyui-datetimebox" id="n_date"></td>
				</tr>
				<tr>
					<td>结束跟踪时间:</td>
					<td><input class="easyui-datetimebox" id="n_aftertime"></td>
				</tr>
				<tr>
					<td>内容:</td>
					<td><input class="easyui-textbox" id="n_ask"></td>
				</tr>
				<tr>
					<td>追踪方式:</td>
					<td>
					<select id="n_fangshi" class="easyui-combobox" data-options="editable:false">
							<option value=''>--请选择--</option>
							<option>电话</option>
							<option>网络</option>
							<option>QQ</option>
							<option>微信</option>
						   <option>家访</option>
					      <option>校访</option>
							<option>其他</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>跟踪状态:</td>
					<td><select id="n_qingkuang" class="easyui-combobox" data-options="editable:false">
							<option value=''>--请选择--</option>
							<option>已跟踪</option>
							<option>跟踪中</option>
							<option>跟踪完成</option>
							<option>思量</option>
							<option>上门未报名</option>
							<option>报名未进班</option>
							<option>未上门</option>
					</select></td>
				</tr>
				<tr>
					<td><a href="javascript:void(0)" icon="icon-ok"
						class="easyui-linkbutton" onclick="genzongtrue()">提交</a></td>
					<td><a href="javascript:void(0)" icon="icon-cancel"
						class="easyui-linkbutton" onclick="genzongfalse()">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
	<!--  查看跟踪日志-->
	<div id="genzongrizhiId" class="easyui-window" title="跟踪信息" style="width:100%;height:700px;"   
	        data-options="iconCls:'icon-save',closed:true">
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
	        
	        </div>
</body>
</html>