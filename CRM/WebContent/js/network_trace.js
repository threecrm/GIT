$(function(){
	initStu();	
        })
        
     function initStu(){
	var u_id=$("#u_id").val();
    	 $("#stuTab").datagrid({
    		    url:"SeleteNetwork_trace",
    		    method:"post",
    		    pagination:true,
    		    fitColumns : true,
    		    singleSelect:true,
    		    toolbar:'#stuTool', 
    			queryParams: {
    				 searchn_sname:$("#n_sname").val(),
    				 searchn_address:$("#n_address").val(),
    				 searchn_qingkuang:$("#n_qingkuang").val(),
    				 searchn_fangshi:$("#sn_fangshi").combobox("getValue"),
    				 searchstartTime:$("#startTime").datetimebox('getValue'), 
    				 searchendTime:$("#endTime").datetimebox('getValue'), 
    				 uid:u_id, 
    			}
    		});
     }
    
function formatterChoose(value, row, index) {
	return "<a href='javascript:void(0)' onclick='Delete("+ index+")'>删除</a> <a href='javascript:void(0)' onclick='selectInfo("+ index+")'>查看</a> "
}

function selectInfo(index){
	var datas=$("#stuTab").datagrid("getData");
	 var row=datas.rows[index];
	 $("#ZhuiZongWin").dialog("open");
	 //填充表单
	 $("#ZhuiZongform").form("load",row)
}

function Delete(index) {
	//获取要删除的数据
	var arr = $("#stuTab").datagrid("getData");
	var row = arr.rows[index];
	//确认删除
	   $.messager.confirm("确认删除", "确认删除么？", function(r) {
		if (r) {
			$.post("DeleteNetwork_trace", {
				n_id : row.n_id
			}, function(res) {
				if (res > 0) {
					//删除成功
					$.messager.alert("提示", "删除成功");
					$("#stuTab").datagrid("reload");
				} else {
					//失败
					$.messager.alert("提示", "删除失败")
				}
			}, "json")
		}
	})
}

    function breakPage() {
    	window.location.reload(true);
		
	}