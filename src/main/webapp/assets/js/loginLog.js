$(function(){
	$("#loginTable").DataTable({
		processing: true,
	    serverSide: true,
	    lengthChange:false,
		paging: true,
	    searching:false,
	    ordering: false,
		info:false,
		ajax: {
			url:ctx+"/loginLog/listLoginLog",
			type:'post'
		},
		columns: [
       		{'data': 'id',
           		render: function(data) {
           			return "<input type='checkbox' id='" + data +"' name='singleCheck' value='" + data +"'>";
           		}},
           	{'data': 'customerId'},
           	{'data': 'loginName'},
           	{'data': "loginTime"},
           	{'data': "ip"}
		]
		
	});
	
	
	
//增删改
	//全选
	$("#selectAll").on('click',function(){
		$("input[name='singleCheck']").prop('checked',$(this).prop('checked'));
	});
	//删
	$("#delBtn").on('click',function(){
		var ids =new Array();
		$("input[name='singleCheck']").each(function(i){
			if($(this).prop('checked')){
				var id = $(this).attr("value");
				ids.push(id);
				return;
			}
		});
		if (ids.length==0) {
   				alert("请选择需要删除的用户！");
   				return;
   			}
		$.ajax({
				url:ctx+"/loginLog/delLoginLogs",
				type: 'Post',
				data: {ids:ids},
				dataType:'json',
				success:function(data){
					alert("删除成功");
					window.location.reload();
				}
			});
	});
	
	
	
	
	
	
	
	
	
	
});
