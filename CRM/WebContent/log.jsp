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
</head>
<body>

<table>
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
						  首访时间 ：
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
</body>
</html>