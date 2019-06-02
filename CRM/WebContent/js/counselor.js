$(function(){
	init();
})

function init(){
	 var u_id=$("#u_id").val();
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
	 var u_id=$("#u_id").val();
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