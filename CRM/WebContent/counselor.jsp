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
<script type="text/javascript">

/* 1,跟踪：对学生进行后续的跟踪，添加跟踪记录信息   步骤：1.在学生信息列表中找到要跟踪的行
2.点击跟踪按钮，弹出新建跟踪窗口。字段信息：回访时间、回访情况、跟踪方式、下次跟踪时间、备注
3.按要求输入信息
4.点击保存按钮，提交信息；点击取消按钮，关闭窗口

2,日志：查看为该学生添加的跟踪记录信息                 步骤：1.在学生信息列表中找到日志按钮
2.点击日志按钮，弹出跟踪日志窗口，显示跟踪记录列表。列表字段：学生名称，跟踪时间，内容，下次跟踪时间，操作
3.点击跟踪记录列表中指定行的查看按钮，可以查看该行的内容字段

3,查询：根据条件查询已录入的学生信息,         步骤：1．查询条件：姓名关键字、电话、咨询师、是否缴费、是否有效、是否回访、QQ、创建时间/上门时间/首次回访时间/缴费时间/进班时间
2．输入和选定下拉框条件之后点击查询按钮，查询数据  

4,动态设置页面中需要显示的学生列表信息,             步骤：1.点击设置按钮显示学生信息的所有列：创建时间、学员姓名、学员电话、性别、年龄、学历、个人状态、来源渠道、来源网址、来源关键词、姓名（咨询）、所在区域、微信、学员QQ、来源部门、是否报备、课程方向、是否有效、打分、是否回访、首次回访时间、是否上门、上门时间、无效原因、是否缴费、缴费时间、金额、
                                                     是否退费、是否进班、进班时间、进班备注、退费原因、定金金额、定金时间
2．选择需要显示的字段，页面列表会动态显示相应的列， 取消已显示的字段，页面列表会隐藏相应的列。

5,查看：查看对应学生的详情信息                              步骤：1. 点击查看按钮弹出学员信息窗口，该学员信息只能查看，不能修改。

6,编辑：对学生信息进行更新                                     步骤：1.在学生信息列表中找到要编辑的行，点击编辑按钮，弹出学员信息。
2.咨询师录入：姓名（咨询）、课程方向、打分、是否有效、无效原因、是否回访、首访时间、是否上门（是，否）、上门时间、定金金额、定金时间、是否缴费（是，否）、缴费时间、缴费金额、是否退费（是，否）、退费原因、是否进班（是，否）、进班时间、进班备注、咨询师备注
3．对需要更新的字段按要求进行编辑
4．点击保存按钮，提交信息；点击取消按钮，关闭窗口
5．提交信息之后，窗口关闭，刷新学生列表
*/     
$(function(){
	init();
})

function init(){
	 var u_id="<%=session.getAttribute("uid")%>"
	   $("#tab").datagrid({
		   url:'ShowStudent',
		   method:'post',
		   pagination:true,
		   fitColumns:true,
		   singleSelect:true,
		   toolbar:'#seachId',
		   queryParams:{
			   Sname:$("#sname").val(),
			   Phone:$("#phone").val(),
			   QQ:$("#qq").val(),
			   IsReturnVisit:$("#IsReturnVisit").combobox("getValue"),
			   Address:$("#Address").combobox("getValue"),
			   IsPay:$("#isPay").combobox("getValue"),
			   IsValid:$("#isValid").combobox("getValue"),
			   StartcreatTimes:$("#StartcreatTimes").datetimebox("getValue"),
			   EndcreatTimes:$("#EndcreatTimes").datetimebox("getValue"),
			   StarthomeTime:$("#StarthomeTime").datetimebox("getValue"),
			   EndhomeTime:$("#EndhomeTime").datetimebox("getValue"),
			   StartfirstVisitTime:$("#StartfirstVisitTime").datetimebox("getValue"),
			   EndfirstVisitTime:$("#EndfirstVisitTime").datetimebox("getValue"),
			   StartpayTime:$("#StartpayTime").datetimebox("getValue"),
			   EndpayTime:$("#EndpayTime").datetimebox("getValue"),
			   StartinClassTime:$("#StartinClassTime").datetimebox("getValue"),
			   EndinClassTime:$("#EndinClassTime").datetimebox("getValue"),
			   u_id:u_id,
		     }
	   })
	    $("#seachForm").form("reset");
}

function formatterSet(value,row,index){
	
	   return "<a href='javascript:void(0)' onclick='Choose(" + index+ ")'>回访</a> <a href='javascript:void(0)' onclick='log(" + index+ ")'>日志</a> <a href='javascrip:void(0)' onclick='look("+index+")'>查看</a> <a href='javascrip:void(0)' onclick='update("+index+")'>编辑</a>  "
} 

/* 回访 */
 function Choose(index){
	 var datas=$("#tab").datagrid("getData");
	 var row=datas.rows[index];
	 
	 $("#address").val("");
	 $("#date").datetimebox("setValue","");
	 $("#ask").val("");
	 $("#aftertime").datetimebox("setValue","");
	 //打开弹窗
	 $("#ZhuiZongWin").dialog("open");
	 //填充表单
	 $("#ZhuiZongform").form("load",row)
} 

function submit(){
	 var u_id="<%=session.getAttribute("uid")%>"
	 var data=$("#tab").datagrid("getSelected");
	 
	var sname=$("#sName").val();
	var uname=$("#userlonginName").val();
	var n_qingkuang=$("#n_qingkuang").val();
	var n_fangshi=$("#sn_fangshi").combobox("getValue");
	var date=$("#date").datetimebox('getValue');
	var ask=$("#ask").val();
	var aftertime=$("#aftertime").datetimebox('getValue');
	var flg = $("#ZhuiZongform").form("validate");
	if (flg) {
		if(date<=aftertime){
			$.post("insert",{
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
					//拜访成功
					$("#ZhuiZongWin").dialog("close");
					
					$.messager.alert("提示", "回访成功");
				} else {
					//拜访失败
					$.messager.alert("提示", "回访失败")
				}
	    	},"json")
		}else{
			$.messager.alert("提示", "结束日期要大于开始日期哦")
		}
		
	}
}

    function look(index){
    	  var data=$("#tab").datagrid("getData");
      	   var row=data.rows[index];
      	   $("#lookForm").form("load",row);
        	$("#lookWindow").window("open");
    }
    
  //日志
  function log(index) {
	    var data=$("#tab").datagrid("getData");
 	   var row=data.rows[index];
	  $('#dg').datagrid({ 
		    method:"post",
		    pagination:true,
		    fitColumns : true,
		    singleSelect:true,
		  queryParams: {
			  sid:row.sid, 
			},
		    url:'SeleteNetwork_traceLog',    
		    columns:[[    
		        {field:'n_sname',title:'学生姓名',width:100},    
		        {field:'n_date',title:'跟踪开始时间',width:100},    
		        {field:'uname',title:'跟踪者',width:100,},
		        {field:'n_qingkuang',title:'回访情况',width:100},
		        {field:'n_fangshi',title:'跟踪方式',width:100},
		        {field:'n_ask',title:'拜访详细',width:100},
		        {field:'n_aftertime',title:'跟踪结束时间',width:100},
		    ]]    
		});
	  $("#SeleteNetwork_traceLogWindow").window("open"); 
}

  /* 编辑 */
  function update(index){
      	   var data=$("#tab").datagrid("getData");
       	   var row=data.rows[index];
       	   $("#updateForm").form("load",row);
           $("#updateWindow").window("open");
  }
  
//编辑确认按钮   
  function UpdateStudenntInfo(){
  	$.post("UpdateStudentInfo",{
  		sid:$("#Updatesid1").val(),
  		LearnForword:$("#learnForword1").val(),
  		IsValid:$("#isValid1").combobox("getValue"),
  		LostValid:$("#lostValid1").val(),
  		IsReturnVisit:$("#isReturnVisit1").combobox("getValue"),
  		FirstVisitTime:$("#firstVisitTime1").datetimebox("getValue"),
  		isHome:$("#isHome1").combobox("getValue"),
  		homeTime:$("#homeTime1").datetimebox("getValue"),
  		ZiXunName:$("#ziXunName1").val(),
  		stuConcern:$("#stuConcern1").val(),
  		Reoord:$("#reoord1").val(),
  		IsPay:$("#isPay1").combobox("getValue"),
  		PayTime:$("#payTime1").datetimebox("getValue"),
  		Money:$("#money1").val(),
  		isReturnMoney:$("#isReturnMoney1").combobox("getValue"),
  		ReturnMoneyReason:$("#returnMoneyReason1").val(),
  		isInClass:$("#isInClass1").combobox("getValue"),
  		inClassTime:$("#inClassTime1").datetimebox("getValue"),
  		inClassContent:$("#inClassContent1").val(),
  		AskerContent:$("#askerContent1").val(),
  		FromPart:$("#fromPart1").val(),
  		isBaoBei:$("#isBaoBei1").combobox("getValue"),
  		CreatUser:$("#creatUser1").val(),
  		
  	},function(res){
  		  if(res>0){
				   $.messager.alert("提示","编辑成功");
				   $("#updateWindow").window("close");
				   $("#tab").datagrid("reload");
			   }else{
				   $.messager.alert("提示","编辑失败");
			   }
  	},"json")
  	
  }
//修改取消按钮
  function updatefalse(){
  	 $("#updateWindow").window("close");
  }
  
//动态设置显示列（第一步）
  function shezhi() {
  	$("#win").window("open");
  }
  // 动态设置显示列（第二步）
  function test(row) {// 接受文本框this自身的所有的值
  	var checked = $('p input:checkbox:checked');// 获取p标签所有选中的复选框
  	checked.each(function(i) {// 依次存储到localStorage里面
  		localStorage.setItem(i, this.value);
  		localStorage.setItem('length', i);
  	});
  	console.log(localStorage.getItem('length'));// 控制台输出
  	if (row.checked == true) {
  		$('#tab').datagrid('showColumn', row.value);// 显示
  	} else {
  		$('#tab').datagrid('hideColumn', row.value);// 隐藏
  	}
  }
  // 动态设置显示列（第三步）
  $(function() {
  	// 取得本地存储的被选中checkbox的个数，循环将checkbox选中
  	var length = localStorage.getItem('length');// 获取localStorage数据
  	for (var i = 0; i <= length; i++) {
  		var a = localStorage.getItem(i);
  		$("p input:checkbox[value=" + a + "]").attr("checked", "checked");// 选中状态
  	}
  	var checked = $('p input:checkbox:not(:checked)');// 获取所有未选中的复选框
  	checked.each(function() {// 遍历

  		$('#tab').datagrid('hideColumn', this.value);// 将没选中的列隐藏起来
  	});
  });
  
</script>
</head>
<body>

<div id="SeleteNetwork_traceLogWindow" class="easyui-window" data-options="modal:true,title:'跟踪日志',closed:true" style="width:1000px;height:500px">
<table id="dg">
</table>
</div>


<table id="tab" class="easyui-datagrid">
   <div id="seachId">
  <form id="seachForm" class="easyui-form">
 
   学生姓名:<input class="easyui-textbox" id="sname" style="width: 75px">
     电话：<input class="easyui-textbox" id="phone" style="width: 75px">
 QQ：<input class="easyui-textbox" id="qq" style="width: 75px">
     是否缴费：
      <select id="isPay" class="easyui-combobox">
   <option value=''>--请选择--</option>
   <option>是</option>
   <option>否</option> 
   </select>
     是否有效：
      <select id="isValid" class="easyui-combobox">
   <option value=''>--请选择--</option>
   <option>是</option>
   <option>否</option> 
   </select>
   
 回访状态:
      <select id="IsReturnVisit" class="easyui-combobox">
		   <option value=''>--请选择--</option>
		   <option>已回访</option>
		   <option>未回访</option> 
      </select>
 所在区域:     
      <select id="Address" class="easyui-combobox">
		   <option value=''>--请选择--</option>
		   <option>郑州 </option>
		   <option>开封 </option>
		   <option>洛阳 </option>
		   <option>周口 </option>
		   <option>商丘 </option>
		   <option>新乡 </option>
		   <option>漯河 </option>
		   <option>鹤壁 </option>
		   <option>南阳 </option>
		   <option>三门峡 </option>
		   <option>驻马店 </option>
		   <option>平顶山 </option>
		   <option>焦作 </option>
		   <option>濮阳 </option>
		   <option>安阳 </option> 
		   <option>信阳 </option>
		   <option>济源 </option>
		   <option>未知 </option>
		   <option>其它 </option>
		  
      </select>
  
  <br><br>
   创建时间：&emsp;&emsp;<input class="easyui-datetimebox" id="StartcreatTimes">~<input class="easyui-datetimebox" id="EndcreatTimes">
   上门时间：<input class="easyui-datetimebox" id="StarthomeTime">~<input class="easyui-datetimebox" id="EndhomeTime">
   进班时间:<input class="easyui-datetimebox" id="StartinClassTime" >~<input class="easyui-datetimebox" id="EndinClassTime" >
 <br> <br>
   首次回访时间：<input class="easyui-datetimebox" id="StartfirstVisitTime">~<input class="easyui-datetimebox" id="EndfirstVisitTime">
   缴费时间：<input class="easyui-datetimebox" id="StartpayTime">~<input class="easyui-datetimebox" id="EndpayTime"> 
   <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="init()">搜索</a>
    <br> 
    <br> 
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick='shezhi()'>设置</a>
  </form>  
  </div> 

  <thead>
       <tr>     
               <!-- <th field="ck" checkbox="true"></th> -->
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
               	<th data-options="field:'qq',title:'qq号'"></th>
               	<th data-options="field:'weiXin',title:'微信号'"></th>
               	<th data-options="field:'content',title:'备注'"></th>
               	<th data-options="field:'creatTimes',title:'创建时间'"></th>
               	<th data-options="field:'learnForword',title:'课程方向'"></th>
               	<th data-options="field:'isValid',title:'是否有效'"></th>
               	<th data-options="field:'reoord',title:'学生简述'"></th>
               	<th data-options="field:'isReturnVisit',title:'是否回访'"></th>
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
                <!-- <th data-options="field:'isDel',title:''"></th> -->
               	<th data-options="field:'fromPart',title:'打分'"></th>
               	<th data-options="field:'stuConcern',title:'学生担忧情况'"></th>
               	<th data-options="field:'isBaoBei',title:'是否报备'"></th>
               	<th data-options="field:'ziXunName',title:'咨询内容'"></th>
               	<th data-options="field:'creatUser',title:'咨询师名字'"></th>
               	<th data-options="field:'returnMoneyReason',title:'退费原因'"></th>
              	<!-- <th data-options="field:'preMoney',title:'缴费金额'"></th>
               	<th data-options="field:'preMoneyTime',title:'交定金时间'"></th> -->
               	<!-- <th data-options="field:'askerId',title:'咨询师编号'"></th> -->
               	<th data-options="field:'',title:'操作' ,formatter:formatterSet"></th>
        </tr>
  </thead>
  </table>
  
  <!-- 设置显示列 -->
	<div id="win" class="easyui-window" title="设置" style="width: 750px; height: 400px" data-options="iconCls:'icon-save',modal:true,closed:true">
		<table style="margin: 10px 30px">
			<tr>
				<td><input type="checkbox" value="sid" onclick="test(this)" />编号</td>
				<td><input type="checkbox" value="sname" onclick="test(this)" />学生姓名</td>
				<td><input type="checkbox" value="age" onclick="test(this)" />年龄</td>
				<td><input type="checkbox" value="sex" onclick="test(this)" />性别</td>
				<td><input type="checkbox" value="phone" onclick="test(this)" />电话</td>
				<td><input type="checkbox" value="stuStatus"
					onclick="test(this)" />学生学历</td>
				<td><input type="checkbox" value="firstVisitTime"
					onclick="test(this)" />第一次回访时间</td>
				<td><input type="checkbox" value="perStatus"
					onclick="test(this)" />个人状态</td>
			</tr>
			<tr>
				<td><input type="checkbox" value="msgSource"
					onclick="test(this)" />来源渠道</td>
				<td><input type="checkbox" value="sourceUrl"
					onclick="test(this)" />来源网址</td>
				<td><input type="checkbox" value="sourceKeyWord"
					onclick="test(this)" />来源关键字</td>
				<td><input type="checkbox" value="address" onclick="test(this)" />学生地址</td>
				<td><input type="checkbox" value="qq" onclick="test(this)" />qq号</td>
				<td><input type="checkbox" value="weiXin" onclick="test(this)" />微信号</td>
				<td><input type="checkbox" value="content" onclick="test(this)" />备注</td>
				<td><input type="checkbox" value="creatTimes"
					onclick="test(this)" />创建时间</td>
			</tr>
			<tr>
				<td><input type="checkbox" value="learnForword"
					onclick="test(this)" />课程方向</td>
				<td><input type="checkbox" value="isValid" onclick="test(this)" />是否有效
				</td>
				<td><input type="checkbox" value="isReturnVisit"
					onclick="test(this)" />回访情况</td>
				<td><input type="checkbox" value="isHome" onclick="test(this)" />是否家访</td>
				<td><input type="checkbox" value="homeTime"
					onclick="test(this)" />家访时间</td>
				<td><input type="checkbox" value="lostValid"
					onclick="test(this)" />无效原因</td>
				<td><input type="checkbox" value="isPay" onclick="test(this)" />是否缴费</td>
				<td><input type="checkbox" value="payTime" onclick="test(this)" />缴费时间</td>
			</tr>
			<tr>
				<td><input type="checkbox" value="money" onclick="test(this)" />缴费金额</td>
				<td><input type="checkbox" value="isReturnMoney"
					onclick="test(this)" />是否退费</td>
				<td><input type="checkbox" value="isInClass"
					onclick="test(this)" />是否进班</td>
				<td><input type="checkbox" value="inClassContent"
					onclick="test(this)" />进班备注</td>
				<td><input type="checkbox" value="askerContent"
					onclick="test(this)" />咨询师备注</td>
				<td><input type="checkbox" value="stuConcern"
					onclick="test(this)" />学生担忧情况</td>
				<td><input type="checkbox" value="isHome" onclick="test(this)" />是否报备</td>
				<td><input type="checkbox" value="ziXunName"
					onclick="test(this)" />咨询内容</td>
			</tr>
			<tr>
				<td><input type="checkbox" value="returnMoneyReason"
					onclick="test(this)" />退费原因</td>
				<td><input type="checkbox" value="preMoney"
					onclick="test(this)" />定金金额</td>
				<td><input type="checkbox" value="preMoneyTime"
					onclick="test(this)" />交定金时间</td>
				<td><input type="checkbox" value="askerId" onclick="test(this)" />咨询师编号</td>
				<td><input type="checkbox" value="hahha" onclick="test(this)" />操作</td>
			</tr>
		</table>
	</div>
  
  <!-- 查看 -->
  <div id="lookWindow" class="easyui-window" modal="true" data-options="closed:true,title:'查看表单'" style="width:900px;height:550px">
 <form id="lookForm">
         <table style="margin-left:7%;margin-top:2%"> 
                  <tr>
					<td>
						学生姓名：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="sname" />
					</td>
					
					<td>
						学生年龄：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="age" />
					</td>
					<td>
						学生性别：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="sex" />
					</td>
					
				</tr>
				
				 <tr>
					<td>
						学生电话：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="phone" />
					</td>
					<td>
						学生学历：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="stuStatus" />
					</td>
					<td>
						个人状态：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="perStatus" />
					</td>
				</tr>
				
				<tr>
					<td>
						来源渠道：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="msgSource" />
					</td>
					<td>
						来源网址：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="sourceUrl" />
					</td>
					<td>
						来源关键字：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="sourceKeyWord" />
					</td>
				</tr>
				
				
				<tr>
					<td>
						学生地址：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="address" />
					</td>
					<td>
						学生QQ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="qq" />
					</td>
					<td>
						学生微信：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="sname" />
					</td>
				</tr>
				
				<tr>
					<td>
						  学生备注 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" content" />
					</td>
					<td>
						  创建时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" creatTimes" />
					</td>
					<td>
						 课程方向 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" learnForword" />
					</td>
				</tr>
				
				<tr>
					<td>
						  是否有效 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isValid" />
					</td>
					<td>
						  学生简述：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" reoord" />
					</td>
					<td>
						  是否回访：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isReturnVisit" />
					</td>
				</tr>
				
				<tr>
					<td>
						  第一次回访时间 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" firstVisitTime" />
					</td>
					<td>
						  是否家访：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isHome" />
					</td>
					<td>
						  家访时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" homeTime" />
					</td>
				</tr>
				
				<tr>
					<td>
						  无效原因 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" lostValid" />
					</td>
					<td>
						  是否缴费：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isPay" />
					</td>
					<td>
						  缴费时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" payTime" />
					</td>
				</tr>
				
				<tr>
					<td>
						  缴费金额 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" money" />
					</td>
					<td>
						  是否退费：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isReturnMoney" />
					</td>
					<td>
						  是否进班：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isInClass" />
					</td>
				</tr>
				
				
				<tr>
					<td>
						  进班时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" inClassTime" />
					</td>
					<td>
						  进班备注：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" inClassContent" />
					</td>
					<td>
						  咨询师备注：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" askerContent" />
					</td>
				</tr>
				<tr>
					<td>
						  预留：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isDel" />
					</td>
					<td>
						 预留：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" fromPart" />
					</td>
					<td>
						  学生担忧：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" stuConcern" />
					</td>
				</tr>
				<tr>
					<td>
						  是否报备 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isBaoBei" />
					</td>
					<td>
						  咨询内容：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" ziXunName" />
					</td>
					<td>
						  咨询师名字：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" creatUser" />
					</td>
				</tr>
				<tr>
					<td>
						  退费原因 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" returnMoneyReason" />
					</td>
					<td>
						  定金金额：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" preMoneyTime" />
					</td>
					<td>
						 交定金时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name=" isInClass" />
					</td>
				</tr> 
       </table>
 </form>
 </div> 
   
 <!--修改-->
 <div id="updateWindow" class="easyui-window" data-options="closed:true,title:'编辑'" modal="true" style="width:700px;height:600px; "  >
 <form id="updateForm">
 <table style="margin-left:27%; margin-top: 1%"> 
                 <tr>
					<td>
						<label for="name">id:</label> 
					</td>
					<td>
						<input class="easyui-textbox" type="text" id="Updatesid1" name="sid" readonly="readonly"/>
					</td>
				</tr>
                <tr>
					<td>
						<label for="name">咨询师姓名:</label> 
					</td>
					<td>
						<input class="easyui-textbox" type="text" id="creatUser1" value="<%=session.getAttribute("LoginUserName")%>" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">课程方向:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="learnForword1" name="learnForword"/>
					</td>
				</tr>
				
				 <tr>
				     <td>
						<label for="name">是否有效:</label> 
					</td>
					<td>
					   <select id="isValid1"  class="easyui-combobox" >
					   <option value="">--请选择--</option>
					   <option>是</option>
					   <option>否</option>
					   </select>
					</td>
				</tr> 
				
		 		<tr>
					<td>
						<label for="name">无效原因:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="lostValid1" name="lostValid"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">是否回访:</label> 
					</td>
					<td>
					   <select id="isReturnVisit1"  class="easyui-combobox" >
					   <option value="">--请选择--</option>
					   <option>是</option>
					   <option>否</option>
					   </select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">首访时间:</label> 
					</td>
					<td>
						<input class="easyui-datetimebox" type="text" id="firstVisitTime1" name="firstVisitTime"/>
					</td>
				</tr>	
				<tr>
					<td>
						<label for="name">是否家访:</label> 
					</td>
					<td>
					   <select id="isHome1"  class="easyui-combobox" >
					   <option value="">--请选择--</option>
					   <option>是</option>
					   <option>否</option>
					   </select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">家访时间:</label> 
					</td>
					<td>
						<input class="easyui-datetimebox" type="text" id="homeTime1" name="homeTime"/>
					</td>
				</tr>
				 <tr>
					<td>
						<label for="name">担忧情况:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="stuConcern1" name="money"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">咨询内容:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="ziXunName1" name="payTime"/>
					</td>
				</tr> 
				
				<tr>
					<td>
						<label for="name">学生简述:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="reoord1" name="reoord"/>
					</td>
				</tr> 
				
				<tr>
					<td>
						<label for="name">是否缴费:</label> 
					</td>
					<td>
						<select id="isPay1"  class="easyui-combobox" >
					   <option value="">--请选择--</option>
					   <option>是</option>
					   <option>否</option>
					   </select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">缴费时间:</label> 
					</td>
					<td>
						<input class="easyui-datetimebox" type="text" id="payTime1" name="payTime"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">缴费金额:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="money1" name="money"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">是否退费:</label> 
					</td>
					<td>
						<select id="isReturnMoney1"  class="easyui-combobox" >
					   <option value="">--请选择--</option>
					   <option>是</option>
					   <option>否</option>
					   </select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">退费原因:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="returnMoneyReason1" name="returnMoneyReason"/>
					</td>
				</tr> 
				<tr>
					<td>
						<label for="name">是否进班:</label> 
					</td>
					<td>
						<select id="isInClass1"  class="easyui-combobox"  >
					   <option value=" ">--请选择--</option>
					   <option>是</option>
					   <option>否</option>
					   </select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">进班时间:</label> 
					</td>
					<td>
						<input class="easyui-datetimebox" type="text" id="inClassTime1" name="inClassTime"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">进班备注:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="inClassContent1" name="inClassContent"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">咨询师备注:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="askerContent1" name="askerContent"/>
					</td>
				</tr> 
				
				<tr>
					<td>
						<label for="name">是否报备:</label> 
					</td>
					<td>
						<select id="isBaoBei1"  class="easyui-combobox"  >
					   <option value=" ">--请选择--</option>
					   <option>是</option>
					   <option>否</option>
					   </select>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">打分:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="fromPart1" name="fromPart"/>
					</td>
				</tr>
				
				<tr>
					<td>
						<a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="UpdateStudenntInfo()" >提交</a>
					</td>
					<td>
						<a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="updatefalse()" >取消</a>
					</td>
				</tr>
 </table>
 
 </form>
 </div> 
 
 <!-- 日志弹窗 -->
 
 <!-- 添加追踪的弹窗 -->
	<div id="ZhuiZongWin" class="easyui-window" data-options="modal:true,title:'拜访学生页面',closed:true" style="width:600px;height:500px;">
	    <form id="ZhuiZongform" class="easyui-form" >
	    <table style="margin-left: 25%;margin-top: 10%">
	    <tr>
	     <td><label for="name">拜访客户:</label></td>
	     <td><input class="easyui-validatebox" type="text" id="sName" name="sname" disabled="disabled" /></td> 
	    </tr>
	    <tr>
	     <td><label for="name">跟踪开始时间:</label></td>
	     <td><input class="easyui-datetimebox" type="text" id="date" data-options="required:true" /></td> 
	    </tr>
	    <tr>
	     <td><label for="name">跟踪者:</label>   
	     <td><input class="easyui-validatebox" type="text" id="userlonginName" value="<%=session.getAttribute("LoginUserName")%>" disabled="disabled" /></td> 
	    </tr>
	    <tr>
	     <td><label for="name">回访情况:</label></td>
	     <td><input class="easyui-validatebox" type="text" id="n_qingkuang" data-options="required:true" /></td> 
	    </tr>
	    <tr> 
	     <td><label for="name">跟踪方式:</label></td>
	     <td><select id="sn_fangshi"  class="easyui-combobox" style="width:157px" >
					   <option value=''>--请选择--</option>
					   <option>家访</option>
					   <option>校访</option>
					   <option>其他</option>
					   </select>
	     </td> 
	    </tr>
	    <tr> 
	     <td><label for="name">拜访详细:</label></td>
	     <td><input class="easyui-textbox" type="text" id="ask" data-options="required:true "/></td> 
	    </tr>
	    <tr>
	     <td> <label for="name">跟踪结束时间:</label>  </td>
	     <td><input class="easyui-datetimebox" type="text" id="aftertime" data-options="required:true"/></td> 
	    </tr>
	  
	    <tr>
	     <td></td>
	     <td> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit()" >提交</a>
              <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closed()" >取消</a> </td> 
	    </tr>
      </table>
         </form>
	    </div>
  
</body>
</html>