function formatterSet(value, row, index) {
	return "<a href='javascrip:void(0)' onclick='look(" + index
			+ ")'>查看</a>  <a href='javascrip:void(0)' onclick='update(" + index
			+ ")'>修改</a>   <a href='javascrip:void(0)'  onclick='del(" + index
			+ ")'>失效</a>  <a href='javascrip:void(0)'  onclick='GenZong(" + index
			+ ")'>跟踪</a> "
			+"<a href='javascrip:void(0)'  onclick='genzongrizhi("+index+")'>查看跟踪日志</a>"
}
$(function() {
	init();
	$('#roleNames').combobox({
		url : 'selectNames',
		method : "post",
		valueField : 'askId',
		textField : 'askName'
	});
	$("#userId1").combobox({
		url : 'selectNames',
		method : "post",
		valueField : 'askId',
		textField : 'askName'
	})
})

function init() {
	$("#tab")
			.datagrid(
					{
						url : 'selectAllStudent',
						method : 'post',
						pagination : true,
						fitColumns : true,
						toolbar : '#seachId',
						queryParams : {
							AskerId : $("#roleNames").combobox("getValue"),
							sname : $("#sname").val(),
							isReturnVisit : $("#isReturnVisit").combobox(
									"getValue"),
							phone : $("#phone").val(),
							QQ : $("#qq").val(),
							isPay : $("#isPay").datetimebox("getValue"),
							isValid : $("#isValid").datetimebox("getValue"),
							StartcreatTimes : $("#StartcreatTimes")
									.datetimebox("getValue"),
							EndcreatTimes : $("#EndcreatTimes").datetimebox(
									"getValue"),
							StarthomeTime : $("#StarthomeTime").datetimebox(
									"getValue"),
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
							EndinClassTime : $("#EndinClassTime").datetimebox(
									"getValue")
						}
					})
	$("#seachForm").form("clear");
}
// 查看学生信息
function look(index) {
	var data = $("#tab").datagrid("getData");
	var row = data.rows[index];
	$("#updateForm").form("load", row);
	$("#updateWindow").window("open");
	$.post("selectAsk", {
		id : row.sid
	}, function(res) {
		/* setText往表格里写入 */
		$.post("selectAsk", {
			id : row.askerId
		}, function(res) {
			$("#updateTeacher").textbox("setText", res.askName), $(
					"#updateJiaose").textbox("setText", res.roleNames)

		})

	})

}
// 添加学生
function add() {
	$("#addWindow").window("open")
}
// 添加学生确认按钮
function addtrue() {
	var tur = $("#tur").switchbutton("options").checked;
	$.post("addStudent", {
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
		}else if(res>0){
			$.messager.alert("提示", "添加成功");
			$("#addWindow").window("close");
			$("#tab").datagrid("reload");
			$("#addForm").form("reset");
		}else{
			$.messager.alert("提示", "添加失败");
		}
	}, "json")
}
// 添加学生取消按钮
function addfalse() {
	$("#addWindow").window("close");
}
// 修改
function update(index) {
	var data = $("#tab").datagrid("getData");
	var row = data.rows[index];
	$("#updateForm").form("load", row);
	$("#updateWindow").window("open");
}
// 修改确认按钮
function updatetrue() {
	$.post("updateStudent", {
		sid : $("#updatesid").val(),
		LearnForword : $("#updateLearnForword").combobox("getValue"),
		IsReturnVisit : $("#updateIsReturnVisit").combobox("getValue"),
		FirstVisitTime : $("#updateFirstVisitTime").datetimebox("getValue"),
		isHome : $("#updateisHome").combobox("getValue"),
		homeTime : $("#updatehomeTime").datetimebox("getValue"),
		IsPay : $("#updateIsPay").combobox("getValue"),
		PayTime : $("#updatePayTime").datetimebox("getValue")
	}, function(res) {
		if (res > 0) {
			$.messager.alert("提示", "修改成功");
			$("#updateWindow").window("close");
			$("#tab").datagrid("reload");

		} else {
			$.messager.alert("提示", "修改失败");
		}
	}, "json")

}
// 修改取消按钮
function updatefalse() {
	$("#updateWindow").window("close");
}
// 失效
function del(index) {
	var data = $("#tab").datagrid("getData");
	var row = data.rows[index];  
	$.post("updateShixiaoStudents", {
		Sname : row.sname
	}, function(res) {
		if (res > 0) {
			$.messager.alert("提示", "设置成功");
			$("#tab").datagrid("reload");
		} else {
			$.messager.alert("提示", "你已经在跟踪中，不能进行失效");
		}
	}, "json")
}

// 手动添加咨询师（第一步弹窗）
function caozuo() {
	var rows = $("#tab").datagrid("getSelections"); // 复选框 获取所有选中的行
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择要操作的行！", "info");
		return;
	} else {
		$("#caozuoWindow").window("open");
	}

	$('#cc').combobox({
		url : 'selectAskName',
		method : "post",
		valueField : 'askId',
		textField : 'askName'
	});

}
// 手动添加咨询师确认按钮
function zixuntrue() {
	var rows = $("#tab").datagrid("getSelections"); // 获取所有选中的行
	var AskerId = [];
	//循环rows取出所有的学生Id并添加到数组AskerId
	for (var i = 0; i < rows.length; i++) {
		AskerId[i] = rows[i].sid;
	}
	//将AskerId转换成字符串
	var b = AskerId.join(",");
	$.post("addAskName", {
		//将所有的学生id以字符串的形式传递到后台
		stuid : b,
		askid : $("#cc").combobox("getValue")//获取到咨询师Id
	}, function(res) {
		if (res > 0) {
			$.messager.alert("提示", "操作成功");
			$("#caozuoWindow").window("close");
			$("#tab").datagrid("reload");
		} else if(res==-1){
			$.messager.alert("提示", "已分配过了，不能二次分配");
			$("#caozuoWindow").window("close");
		}else if(res==-2){
			$.messager.alert("提示", "当天没有签到的咨询师");
		}
	}, "json")
}
// 手动添加咨询师取消按钮
function zixunfalse() {
	$("#caozuoWindow").window("close")
}
// 自动添加咨询师
function fenpei() {
	var rows = $("#tab").datagrid("getSelections"); // 获取复选框选中的行
	var AskerId = [];
	for (var i = 0; i < rows.length; i++) {
		AskerId[i] = rows[i].sid;
	}
	var b = AskerId.join(",");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择要操作的行！", "info");
		return;
	} else {
		$.post("addAskNames", {
			askerId : b
		}, function(res) {
			if (res > 0) {
				$.messager.alert("提示", "自动分配成功");
				$("#tab").datagrid("reload");
			}else if(res==-2){
				$.messager.alert("提示", "当天没有咨询师签到");
			}else {
				$.messager.alert("提示", "已分配过了，不能二次分配");
			}
		}, "json")
	}
}

// 已分配
function Yifenpei() {
	$("#tab").datagrid({
		url : 'selectYifenpei',
		mehtod : 'post'
	})
}
// 未分配
function Weifenpei() {
	$("#tab").datagrid({
		url : 'selectWeifenpei',
		mehtod : 'post'
	})
}
//动态显示
function shezhi() {
	$("#dtcx_students").window("open");
}
function checkOrClose(object) {
	if (object.checked == true) {
		$("#tab").datagrid("showColumn", object.name);
	} else {
		$("#tab").datagrid("hideColumn", object.name);
	}
}
function checkOrCloseAll(obj) {
	$(".checkeds").prop('checked', $(obj).prop('checked'));
	if (obj.checked == true) {
		$(".checkeds:checked").each(function() {
			$("#tab").datagrid("showColumn", this.name);
		});
	} else {
		$(".checkeds").each(function() {
			$("#tab").datagrid("hideColumn", this.name);
		});
	}
}
//跟踪
function GenZong(index){
	 var datas=$("#tab").datagrid("getData");
	 var row=datas.rows[index];
	 
	 $("#address").val("");
	 $("#date").datetimebox("setValue","");
	 $("#ask").val("");
	 $("#aftertime").datetimebox("setValue","");
	 //打开弹窗
	 $("#genzongid").dialog("open");
	 //填充表单
	 $("#genzongForm").form("load",row)
} 

// 跟踪确认按钮
function submitZuiZong(){
	var u_id=$("#u_id").val();
	var data=$("#tab").datagrid("getSelected");
	var sname=$("#sName").val();
	var uname=$("#userlonginName").val();
	var n_qingkuang=$("#n_qingkuang").combobox('getValue');
	var n_fangshi=$("#n_fangshi").combobox("getValue");
	var date=$("#date").datetimebox('getValue');
	var ask=$("#ask").val();
	var aftertime=$("#aftertime").datetimebox('getValue');
	
	var flg = $("#ZhuiZongform").form("validate");
	if (flg) {
		if(date<=aftertime){
			$.post("addgenzong",{
				sid:data.sid,
	    		n_sname:sname,
	    		uname:uname,
	    		n_qingkuang:n_qingkuang,
	    		n_fangshi:n_fangshi,
	    		n_date:date,
	    		n_ask:ask,
	    		n_aftertime:aftertime,
	    		uid:u_id
	    	},function(res){
	    		if (res > 0) {
					$.messager.alert("提示", "你对学生"+$("#sName").val()+"跟踪成功");
					$("#genzongid").dialog("close");
					$("#tab").datagrid("reload");
					$("#genzongForm").form("reset");
				} else {
					$.messager.alert("提示", $("#sName").val()+"你已跟踪完成，请选择其他学生进行跟踪！");
					$("#genzongid").dialog("close");
					$("#tab").datagrid("reload");
					$("#genzongForm").form("reset");
					
				}
	    	},"json")
		}else{
			$.messager.alert("提示", "结束日期要大于开始日期哦")
		}
		
	}
}

// 跟踪取消按钮
function genzongfalse() {
	$("#genzongid").window("close");
}
// 导出excel(导 js/jquery-easyui-1.4.3/datagrid-export.js 包)
function exportExcel() {
	$('#tab').datagrid('toExcel', 'dg.xls');
}
// 重置
function chongzhiStudent() {
	$("#tab").datagrid({
		url : 'selectAllStudent',
		method : 'post',
		pagination : true
	})
}

function genzongrizhi(index){
	 var date=$("#tab").datagrid("getData");
	 var row=date.rows[index];
	  $("#tabs").datagrid({
		 	 url:'selectgenzongrizhi',
		 	 method:'post',
		 	queryParams:{
		 	 name:row.sname
		 				 }
		 		 })
		 $("#genzongrizhiId").window("open") ;
}

function shixiaoStudent(){
	$("#tab").datagrid({
		url:'selectshixiaostudents',
		method:'post'
	})
}