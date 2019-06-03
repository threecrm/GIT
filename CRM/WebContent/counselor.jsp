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

 <!-- 1,跟踪：对学生进行后续的跟踪，添加跟踪记录信息   步骤：1.在学生信息列表中找到要跟踪的行
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
5．提交信息之后，窗口关闭，刷新学生列表 -->

<script type="text/javascript" src="js/counselor.js"></script>
</head>
<body>
<input id="u_id" value="<%=session.getAttribute("uid")%>" style="display: none"/>
<div id="SeleteNetwork_traceLogWindow" class="easyui-window" data-options="modal:true,title:'跟踪日志',closed:true" style="width:1000px;height:500px">
<table id="dg">
</table>
</div>

<!-- 模糊查询导航条 -->
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
		   <option>思量</option>
      </select>
 所在区域:   <select id="Address" class="easyui-combobox">
		   <option value=''>--请选择--</option>
		   <option>郑州</option>
		   <option>开封</option>
		   <option>洛阳</option>
		   <option>周口</option>
		   <option>商丘</option>
		   <option>新乡</option>
		   <option>漯河</option>
		   <option>鹤壁</option>
		   <option>南阳</option>
		   <option>三门峡</option>
		   <option>驻马店</option>
		   <option>平顶山</option>
		   <option>焦作</option>
		   <option>濮阳</option>
		   <option>安阳</option> 
		   <option>信阳</option>
		   <option>济源</option>
		   <option>未知</option>
		   <option>其它</option> 
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
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick='lookstu()'>设置</a>
  </form>  
  </div> 

<table id="tab" class="easyui-datagrid">
  <thead>
       <tr>     
               <!-- <th field="ck" checkbox="true"></th> -->
                <th data-options="field:'sid',title:'编号'"></th>  
				<th data-options="field:'sname',title:'姓名'"></th>
				<th data-options="field:'age',title:'年龄'"></th>
				<th data-options="field:'sex',title:'性别'"></th>
				<th data-options="field:'phone',title:'电话'"></th>
				<th data-options="field:'stuStatus',title:'学历'"></th>
				<th data-options="field:'perStatus',title:'状态'"></th>
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
               	<th data-options="field:'isReturnVisit',title:'回访状态'"></th>
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
               	<th data-options="field:'caozuo',title:'操作' ,formatter:formatterSet"></th>
        </tr>
  </thead>
  </table>
  
  <div id="hiddenColumn_dialog" class="easyui-dialog"
		data-options="title:'设置隐藏列',modal:true,closed:'true',
			buttons:[{
				text:'保存',
				handler:function(){
				saveColumn();<!-- 保存 -->
				initStu();<!-- 刷新 -->
				closed_hiddenColumn();<!-- 关闭弹窗 -->
				}
			},{
				text:'关闭',
				handler:function(){
				closed_hiddenColumn();
				}
			}]">
		<form style="width: 450px; height: 300px;" id="hiddenColumn_form"
			class="easyui-form">
			<a href="javascript:void()" class="easyui-linkbutton" id="isQuanXuan"
				onclick="ChooseAll()" data-options="iconCls:'icon-edit'">全选</a>
			<table>
				<tr>
					<td><input type="checkbox" value="sid" />编号</td>
					<td><input type="checkbox" value="sname" />姓名</td>
					<td><input type="checkbox" value="sex" />性别</td>
					<td><input type="checkbox" value="age" />年龄</td>
				</tr>
				
				<tr>
					<td><input type="checkbox" value="phone" />电话</td>
					<td><input type="checkbox" value="stuStatus" />学历</td>
					<td><input type="checkbox" value="perStatus" />状态</td>
					<td><input type="checkbox" value="msgSource" />来源渠道</td>
				</tr>
				
				<tr>
					<td><input type="checkbox" value="sourceUrl" />来源网址</td>
					<td><input type="checkbox" value="sourceKeyWord" />来源关键字</td>
					<td><input type="checkbox" value="address" />学生地址</td>
					<td><input type="checkbox" value="qq" />QQ号</td>
				</tr>
				<tr>
					<td><input type="checkbox" value="weiXin" />微信号</td>
					<td><input type="checkbox" value="content" />备注</td>
					<td><input type="checkbox" value="creatTimes" />创建时间</td>
					<td><input type="checkbox" value="learnForword" />课程方向</td>
				</tr>
				<tr>
					<td><input type="checkbox" value="isValid" />是否有效</td>
					<td><input type="checkbox" value="reoord" />学生简述</td>
					<td><input type="checkbox" value="isReturnVisit" />是否回访</td>
					<td><input type="checkbox" value="firstVisitTime" />第一次回访时间</td>
					
				</tr>
				<tr>
					<td><input type="checkbox" value="isHome" />是否家访</td>
					<td><input type="checkbox" value="homeTime" />家访时间</td>
					<td><input type="checkbox" value="lostValid" />无效原因</td>
					<td><input type="checkbox" value="isPay" />是否缴费</td>
				</tr>
				<tr>
					<td><input type="checkbox" value="payTime" />缴费时间</td>
					<td><input type="checkbox" value="money" />缴费金额</td>
					<td><input type="checkbox" value="isReturnMoney" />是否退费</td>
					<td><input type="checkbox" value="returnMoneyReason" />退费原因</td>
				</tr>
				<tr>
				   <td><input type="checkbox" value="isInClass" />是否进班</td>
					<td><input type="checkbox" value="inClassTime" />进班时间</td>
					<td><input type="checkbox" value="inClassContent" />进班备注</td>
					<td><input type="checkbox" value="askerContent" />咨询师备注</td>
				</tr>
				<tr>
				    <td><input type="checkbox" value="fromPart" />打分</td>
					<td><input type="checkbox" value="stuConcern" />学生担忧情况</td>
					<td><input type="checkbox" value="isBaoBei" />是否报备</td>
					<td><input type="checkbox" value="creatUser" />咨询师姓字</td>
					<td><input type="checkbox" value="ziXunName" />咨询内容</td>
				</tr>
			</table>
		</form>
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
						<input class="easyui-textbox" type="text"  name="sname" readonly="true"/>
					</td>
					
					<td>
						学生年龄：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="age" readonly="true"/>
					</td>
					<td>
						学生性别：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="sex" readonly="true"/>
					</td>
				</tr>
				
				 <tr>
					<td>
						学生电话：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="phone" readonly="true"/>
					</td>
					<td>
						学生学历：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="stuStatus" readonly="true"/>
					</td>
					<td>
						个人状态：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="perStatus" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td>
						来源渠道：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="msgSource" readonly="true"/>
					</td>
					<td>
						来源网址：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="sourceUrl" readonly="true"/>
					</td>
					<td>
						来源关键字：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="sourceKeyWord" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>
						学生地址：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="address" readonly="true"/>
					</td>
					<td>
						学生QQ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="qq" readonly="true"/>
					</td>
					<td>
						学生微信：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="weiXin" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td>
						  学生备注 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="content" readonly="true"/>
					</td>
					<td>
						  创建时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="creatTimes" readonly="true"/>
					</td>
					<td>
						 课程方向 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text"  name="learnForword" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td>
						  是否有效 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="isValid" readonly="true"/>
					</td>
					<td>
						  学生简述：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="reoord" readonly="true"/>
					</td>
					<td>
						  是否回访：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="isReturnVisit" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td>
						  第一次回访时间 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="firstVisitTime" readonly="true"/>
					</td>
					<td>
						  是否家访：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="isHome" readonly="true"/>
					</td>
					<td>
						  家访时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="homeTime" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td>
						  无效原因 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="lostValid" readonly="true"/>
					</td>
					<td>
						  是否缴费：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="isPay" readonly="true"/>
					</td>
					<td>
						  缴费时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="payTime" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td>
						  缴费金额 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="money" readonly="true"/>
					</td>
					<td>
						  是否退费：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="isReturnMoney" readonly="true"/>
					</td>
					<td>
						  退费原因 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="returnMoneyReason" readonly="true"/>
					</td>
				</tr>
				
				<tr>
				     <td>
						  是否进班：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="isInClass" readonly="true"/>
					</td>
					<td>
						  进班时间：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="inClassTime" readonly="true"/>
					</td>
					<td>
						  进班备注：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="inClassContent" readonly="true"/>
					</td>
					
				</tr>
				<tr>
					<td>
						  咨询师备注：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="askerContent" readonly="true"/>
					</td>
					<td>
						 打分：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="fromPart" readonly="true"/>
					</td>
					<td>
						  学生担忧：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="stuConcern" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>
						  是否报备 ：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="isBaoBei" readonly="true"/>
					</td>
					<td>
						  咨询内容：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="ziXunName" readonly="true"/>
					</td>
					<td>
						  咨询师名字：
					</td>
					<td>
						<input class="easyui-textbox" type="text" name="creatUser" readonly="true"/>
					</td>
				</tr>
				
       </table>
 </form>
 </div> 
   
 <!--修改-->
 <div id="updateWindow" class="easyui-window" data-options="closed:true,title:'编辑'" modal="true" style="width:700px;height:750px; "  >
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
					  <select id="learnForword1"  class="easyui-combobox" name="learnForword" data-options="required:true,editable:false">
					   <option value="">--请选择--</option>
					   <option>网络开发</option>
					   <option>网站运营</option>
					   </select>
					</td>
				</tr>
				
				 <tr>
				     <td>
						<label for="name">是否有效:</label> 
					</td>
					<td>
					   <select id="isValid1"  class="easyui-combobox" name="isValid" data-options="required:true,editable:false">
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
						<input class="easyui-validatebox" type="text" id="lostValid1" name="lostValid" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">是否回访:</label> 
					</td>
					<td>
					   <select id="isReturnVisit1"  class="easyui-combobox" name="isReturnVisit" data-options="required:true,editable:false">
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
						<input class="easyui-datetimebox" type="text" id="firstVisitTime1" name="firstVisitTime" data-options="required:true,editable:false"/>
					</td>
				</tr>	
				<tr>
					<td>
						<label for="name">是否家访:</label> 
					</td>
					<td>
					   <select id="isHome1"  class="easyui-combobox"  name="isHome" data-options="required:true,editable:false">
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
						<input class="easyui-datetimebox" type="text" id="homeTime1" name="homeTime" data-options="required:true,editable:false"/>
					</td>
				</tr>
				 <tr>
					<td>
						<label for="name">担忧情况:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="stuConcern1" name="money" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">咨询内容:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="ziXunName1" name="payTime" data-options="required:true"/>
					</td>
				</tr> 
				
				<tr>
					<td>
						<label for="name">学生简述:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="reoord1" name="reoord" data-options="required:true"/>
					</td>
				</tr> 
				
				<tr>
					<td>
						<label for="name">是否缴费:</label> 
					</td>
					<td>
						<select id="isPay1"  class="easyui-combobox" name="isPay"  data-options="required:true,editable:false">
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
						<input class="easyui-datetimebox" type="text" id="payTime1" name="payTime" data-options="required:true,editable:false"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">缴费金额:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="money1" name="money" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">是否退费:</label> 
					</td>
					<td>
						<select id="isReturnMoney1"  class="easyui-combobox" name="isReturnMoney" data-options="required:true,editable:false" >
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
						<input class="easyui-validatebox" type="text" id="returnMoneyReason1" name="returnMoneyReason" data-options="required:true"/>
					</td>
				</tr> 
				<tr>
					<td>
						<label for="name">是否进班:</label> 
					</td>
					<td>
						<select id="isInClass1"  class="easyui-combobox" name="isInClass" data-options="required:true,editable:false" >
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
						<input class="easyui-datetimebox" type="text" id="inClassTime1" name="inClassTime" data-options="required:true,editable:false"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">进班备注:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="inClassContent1" name="inClassContent" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>
						<label for="name">咨询师备注:</label> 
					</td>
					<td>
						<input class="easyui-validatebox" type="text" id="askerContent1" name="askerContent" data-options="required:true"/>
					</td>
				</tr> 
				
				<tr>
					<td>
						<label for="name">是否报备:</label> 
					</td>
					<td>
						<select id="isBaoBei1"  class="easyui-combobox" name="isBaoBei" data-options="required:true,editable:false">
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
						<input class="easyui-validatebox" type="text" id="fromPart1" name="fromPart" data-options="required:true"/>
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
	     <td><input class="easyui-datetimebox" type="text" id="date" data-options="required:true,editable:false" /></td> 
	    </tr>
	    <tr>
	     <td><label for="name">跟踪者:</label>   
	     <td><input class="easyui-validatebox" type="text" id="userlonginName" value="<%=session.getAttribute("LoginUserName")%>" disabled="disabled" /></td> 
	    </tr>
	    <tr>
	     <td><label for="name">回访情况:</label></td>
	     <td><input class="easyui-validatebox" type="text" id="n_qingkuang" data-options="required:true,editable:false" /></td> 
	    </tr>
	    <tr> 
	     <td><label for="name">跟踪方式:</label></td>
	     <td><select id="sn_fangshi"  class="easyui-combobox" style="width:157px" data-options="required:true,editable:false" >
					   <option value=''>--请选择--</option>
					   <option>家访</option>
					   <option>校访</option>
					   <option>其他</option>
					   </select>
	     </td> 
	    </tr>
	    <tr> 
	     <td><label for="name">拜访详细:</label></td>
	     <td><input class="easyui-textbox" type="text" id="ask" data-options="required:true,multiline:true,height:50,prompt:'备注...' "/></td> 
	    </tr>
	    <tr>
	     <td> <label for="name">跟踪结束时间:</label>  </td>
	     <td><input class="easyui-datetimebox" type="text" id="aftertime" data-options="required:true,editable:false"/></td> 
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