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
<script type="text/javascript"
	src="js/jquery-easyui-1.4.3/datagrid-export.js"></script>
<!--导出excel的文件包 -->
<script type="text/javascript" src="js/student.js"></script>
<style type="text/css">
.a {
	width: 100px;
}
</style>
</head>
<body>
	<div id="seachId">
		<table>
			<tr>
				<td>咨询师名称: <input id="roleNames" class="easyui-combobox a"></td>
				<td>学生姓名:<input class="easyui-textbox a" id="sname"></td>
				<td>电话：<input class="easyui-textbox a" id="phone"></td>
				<td>QQ：<input class="easyui-textbox a" id="qq"></td>
				<td>是否有效： <select id="isValid" class="easyui-combobox a">
						<option value=''>--请选择--</option>
						<option>是</option>
						<option>否</option>
				</select></td>
				<td>回访情况: <select id="isReturnVisit" class="easyui-combobox a">
						<option value=''>--请选择--</option>
						<option>已回访</option>
						<option>未回访</option>
						<option>思量</option>
						<option>上门未报名</option>
						<option>报名未进班</option>
						<option>未上门</option>
				</select></td>
				<td>是否缴费： <select id="isPay" class="easyui-combobox a">
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
					data-options="iconCls:'icon-ok'" onclick="Yifenpei()">已分配</a></td>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-no'" onclick="Weifenpei()">未分配</a></td>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" onclick="caozuo()">手动分配</a></td>
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-add'" onclick='fenpei()'>自动分配</a></td>
				<!-- <td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-undo'" onclick='genzong()'>跟踪</a></td> -->
				<td><a class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" onclick='chongzhiStudent()'>所有学生</a></td>
			</tr>
		</table>
	</div>
	<table id="tab" class="easyui-datagrid">
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
				<th data-options="field:'qq',title:'qq号'"></th>
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
	<!-- 设置显示列 -->
	<div id="win" class="easyui-window" title="设置"
		style="width: 750px; height: 400px"
		data-options="iconCls:'icon-save',modal:true,closed:true">
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
	<!-- 添加追踪的弹窗 -->
	<div id="genzongid" class="easyui-window"
		data-options="modal:true,title:'拜访学生页面',closed:true"
		style="width: 600px; height: 500px;">
		<form id="genzongForm" class="easyui-form">
			<table style="margin-left: 25%; margin-top: 10%">
			<input id="u_id" value="<%=session.getAttribute("uid")%>" style="display: none"/>
				<tr>
					<td><label for="name">拜访客户:</label></td>
					<td><input class="easyui-validatebox" type="text" id="sName"
						name="sname" disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">跟踪开始时间:</label></td>
					<td><input class="easyui-datetimebox" type="text" id="date"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label for="name">跟踪者:</label>
					<td><input class="easyui-validatebox" type="text"
						id="userlonginName"
						value="<%=session.getAttribute("LoginUserName")%>"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td><label for="name">回访情况:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="n_qingkuang" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label for="name">跟踪方式:</label></td>
					<td><select id="n_fangshi"  class="easyui-combobox" data-options="required:true" >
					   <option value=" ">--请选择--</option>
					   <option>家访</option>
					   <option>校访</option>
					   <option>其他</option>
					   </select></td>
				</tr>
				<tr>
					<td><label for="name">拜访详细:</label></td>
					<td><input class="easyui-validatebox" type="text" id="ask"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label for="name">跟踪结束时间:</label></td>
					<td><input class="easyui-datetimebox" type="text"
						id="aftertime" data-options="required:true" /></td>
				</tr>

				<tr>
					<td></td>
					<td><a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="submitZuiZong()">提交</a> <a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="genzongfalse()">取消</a></td>
				</tr>
			</table>
			</form>
	</div>
	<!--批量操作  -->
	<div id="caozuoWindow" class="easyui-window"
		data-options="closed:true,title:'咨询师'"
		style="width: 200px; height: 200px;">
		<form id="caozuoForm">
			<br> <br>
			<tr>
				<td>咨询师姓名</td>
				<td><input id="cc" class="easyui-combobox"></td>
			</tr>
			<br>
			<tr>
				<td><a href="javascript:void(0)" icon="icon-ok"
					class="easyui-linkbutton" onclick="zixuntrue()">提交</a></td>
				<td><a href="javascript:void(0)" icon="icon-cancel"
					class="easyui-linkbutton" onclick="zixunfalse()">取消</a></td>
			</tr>
		</form>
	</div>
	<!--  添加-->
	<div id="addWindow" class="easyui-window"
		data-options="closed:true,width:600,title:'添加表单'">
		<form id="addForm">
			<table>
				<tr>
					<td><label for="name">姓名:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="addSname" /></td>
					<td><label for="name">性别:</label></td>
					<td><select id="addSex" class="easyui-combobox a">
							<option>--请选择--</option>
							<option>男</option>
							<option>女</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">年龄:</label></td>
					<td><input class="easyui-validatebox" type="text" id="addAge" />
					</td>
					<td><label for="name">状态:</label></td>
					<td><select id="addPerStatus" class="easyui-combobox a">
							<option>--请选择--</option>
							<option>未知</option>
							<option>待业</option>
							<option>在职</option>
							<option>在读</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">电话:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="addPhone" /></td>
					<td><label for="name">学历:</label></td>
					<td><select id="addStuStatus" class="easyui-combobox a">
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
					<td><label for="name">来源关键词:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="addSourceKeyWord" /></td>
					<td><label for="name">来源渠道:</label></td>
					<td><select id="addMsgSource" class="easyui-combobox a">
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
					<td><label for="name">学员QQ:</label></td>
					<td><input class="easyui-validatebox" type="text" id="addQQ" />
					</td>
					<td><label for="name">来源网站:</label></td>
					<td><select id="addSourceUrl" class="easyui-combobox a">
							<option>--请选择--</option>
							<option>其它</option>
							<option>职英B站</option>
							<option>职英A站</option>
							<option>高考站</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">微信号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="addWeiXin" /></td>
					<td><label for="name">是否报备:</label></td>
					<td><select id="addisBaoBei" class="easyui-combobox a">
							<option value="">--请选择--</option>
							<option>是</option>
							<option>否</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">在线备注:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="addContent" /></td>
					<td><label for="name">回访情况:</label></td>
					<td><select id="AddisReturnVisit" class="easyui-combobox a">
							<option value=''>--请选择--</option>
							<option>已回访</option>
							<option>未回访</option>
							<option>思量</option>
							<option>上门未报名</option>
							<option>报名未进班</option>
							<option>未上门</option>
					</select></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><a href="javascript:void(0)" icon="icon-ok"
						class="easyui-linkbutton" onclick="addtrue()">提交</a></td>
					<td><a href="javascript:void(0)" icon="icon-cancel"
						class="easyui-linkbutton" onclick="addfalse()">取消</a></td>
					<td style="color: red">自动分配<input id="tur"
						class="easyui-switchbutton" checked></td>
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
						name="sid" readonly="readonly" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询师:</label></td>
					<td><input class="easyui-textbox" type="text"
						id="updateTeacher" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询师角色:</label></td>
					<td><input class="easyui-textbox" type="text"
						id="updateJiaose" /></td>
				</tr>

				<tr>
					<td><label for="name">姓名:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateSname" name="sname" /></td>
				</tr>

				<tr>
					<td><label for="name">性别:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateSex" name="sex" /></td>
				</tr>

				<tr>
					<td><label for="name">年龄:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateAge" name="age" /></td>
				</tr>

				<tr>
					<td><label for="name">电话:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatePhone" name="phone" /></td>
				</tr>

				<tr>
					<td><label for="name">学历:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateStuStatus" name="stuStatus" /></td>
				</tr>

				<tr>
					<td><label for="name">状态:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatePerStatus" name="perStatus" /></td>
				</tr>
				<tr>
					<td><label for="name">来源渠道:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateMsgSource" name="msgSource" /></td>
				</tr>
				<tr>
					<td><label for="name">来源网站:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateSourceUrl" name="sourceUrl" /></td>
				</tr>
				<tr>
					<td><label for="name">来源关键词:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateSourceKeyWord" name="sourceKeyWord" /></td>
				</tr>
				<tr>
					<td><label for="name">学生地址:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateAddress" name="address" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询师:</label></td>
					<td><input class="easyui-validatebox" type="text" id="update"
						name="" /></td>
				</tr>
				<tr>
					<td><label for="name">qq号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateQQ" name="qq" /></td>
				</tr>
				<tr>
					<td><label for="name">微信号:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateWeiXin" name="weiXin" /></td>
				</tr>
				<tr>
					<td><label for="name">备注:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateContent" name="content" /></td>
				</tr>
				<tr>
					<td><label for="name">创建时间:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateCreatTimes" name="creatTimes" /></td>
				</tr>
				<tr>
					<td><label for="name">课程方向:</label></td>
					<td><select id="updateLearnForword" name="learnForword"
						class="easyui-combobox">
							<option value="">--请选择--</option>
							<option>软件开发</option>
							<option>软件设计</option>
							<option>网络营销</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="name">是否有效:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateIsValid" name="isValid" /></td>
				</tr>
				<tr>
					<td><label for="name">是否回访:</label></td>
					<td><select id="updateIsReturnVisit" name="isReturnVisit"
						class="easyui-combobox">
							<option value="">--请选择--</option>
							<option>是</option>
							<option>否</option>
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
						class="easyui-combobox">
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
						id="updateLostValid" name="lostValid" /></td>
				</tr>
				<tr>
					<td><label for="name">是否缴费:</label></td>
					<td><select id="updateIsPay" name="isPay"
						class="easyui-combobox">
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
						id="updateMoney" name="money" /></td>
				</tr>
				<tr>
					<td><label for="name">是否退费:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateisReturnMoney" name="isReturnMoney" /></td>
				</tr>
				<tr>
					<td><label for="name">是否进班:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateisInClass" name="isInClass" /></td>
				</tr>
				<tr>
					<td><label for="name">进班时间:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateinClassTime" name="inClassTime" /></td>
				</tr>
				<tr>
					<td><label for="name">进班备注:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateinClassContent" name="inClassContent" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询师备注:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateAskerContent" name="askerContent" /></td>
				</tr>
				<tr>
					<td><label for="name">学生担忧情况:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatestuConcern" name="stuConcern" /></td>
				</tr>
				<tr>
					<td><label for="name">是否报备:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateisBaoBei" name="isBaoBei" /></td>
				</tr>
				<tr>
					<td><label for="name">咨询内容:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateZiXunName" name="ziXunName" /></td>
				</tr>
				<tr>
					<td><label for="name">退费原因:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updateReturnMoneyReason" name="returnMoneyReason" /></td>
				</tr>
				<tr>
					<td><label for="name">定金金额:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatePreMoney" name="preMoney" /></td>
				</tr>
				<tr>
					<td><label for="name">交定金时间:</label></td>
					<td><input class="easyui-validatebox" type="text"
						id="updatePreMoneyTime" name="preMoneyTime" /></td>
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

</body>
</html>