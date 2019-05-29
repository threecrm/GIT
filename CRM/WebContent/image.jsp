<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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

<script type="text/javascript">
	$(function() {
		intTab();
	})

	function intTab() {
		$("#tab").datagrid({
			url : "selectMySign",
			method : "post",
			pagination : true,
			fitColumns : true,
			singleSelect : true,
		})
	}
</script>
<script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
<script
	src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
<script
	src="https://code.highcharts.com.cn/highcharts/modules/series-label.js"></script>
<script src="https://code.highcharts.com.cn/highcharts/modules/oldie.js"></script>
<script
	src="https://code.highcharts.com.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>
<body>
	<table>
		<tr>
			<td><div id="container" style="min-width: 500px; height: 300px"></div></td>
			<td><div id="container2" style="min-width: 500px; height: 300px"></div></td>
		</tr>
		<tr>
			<td><div id="container1" style="min-width: 400px; height: 300px"></div></td>
		</tr>
	</table>
	<script>
		$(function() {
			//个人考勤信息
			personage();
		})
		function personage() {
			$
					.post(
							"getHigcharts",
							{
								uid : 1
							},
							function(data) {
								var zt = data.zt;
								var zc = data.zc;
								var cd = data.cd;
								Highcharts
										.chart(
												'container',
												{
													chart : {
														plotBackgroundColor : null,
														plotBorderWidth : null,
														plotShadow : false,
														type : 'pie'
													},
													title : {
														text : '当天员工签到统计'
													},
													tooltip : {
														pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
													},
													plotOptions : {
														pie : {
															allowPointSelect : true,
															cursor : 'pointer',
															dataLabels : {
																enabled : true,
																format : '<b>{point.name}</b>: {point.percentage:.1f} %',
																style : {
																	color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
																			|| 'black'
																}
															}
														}
													},
													series : [ {
														name : 'Brands',
														colorByPoint : true,
														data : [ {
															name : '正常签到',
															y : zc,
															sliced : true,
															selected : true
														}, {
															name : '迟到',
															y : cd
														}, {
															name : '请假',
															y : zt
														} ]
													} ]
												});
							}, 'json')
		}
	</script>
	<div style="display: none">
		<table id="tab" class="easyui-datagrid">
			<thead>
				<tr>
					<th data-options="field:'uid',align:'center',width:60">我的ID</th>
					<th data-options="field:'creat_time',align:'center',width:120">签到时间</th>
					<th data-options="field:'end_time',align:'center',width:120">签退时间</th>
					<th data-options="field:'state',align:'center',width:100">签到状态</th>
					<th data-options="field:'endState',align:'center',width:100">签退状态</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>