$(function(){
	$("#roleTable").DataTable({
		processing: true,
	    serverSide: true,
	    lengthChange:false,
		paging: true,
	    searching:false,
	    ordering: false,
		info:false,
		ajax: {
			url:ctx+"/role/listRoles",
		},
		columns: [
       		{'data': 'id',
           		render: function(data) {
           			return "<input type='checkbox' id='" + data +"' name='singleCheck' value='" + data +"'>";
           		}},
           	{'data': 'customerId'},
           	{'data': 'name'},
           	{'data': 'desp'}
//         	{'data': 'userIds'},
//         	{'data': "users"}
		]
	});
	
	
	
	
	
	
//增删改
	//全选
	$("#selectAll").on('click',function(){
		$("input[name='singleCheck']").prop('checked',$(this).prop('checked'));
	});
	
	//增
	$("#addBtn").on('click',function(){
		$("#roleForm input").val("");
		$("#roleModal").modal("toggle");
	});
	//改
	
	
	//存
	$("#saveBtn").on('click',function(){
		var username = $("#customerId").val();
		var name = $("#name").val();
		if (customerId == null || customerId == "") {
	   		$("#customerId").css("border-color","#FF0000");
	   		$("#customerId").focus();
	   		return;
	   	}
		if (name == null || name == "") {
	   		$("#name").css("border-color","#FF0000");
	   		$("#name").focus();
	   		return;
	   	}
		//将form中的表单元素序列化成json对象
		var data=$("#roleForm").serializeArray();
		var o = {};
		$.each(data, function() {
			if (o[this.name]) {    
	            if (!o[this.name].push) {    
	                 o[this.name] = [o[this.name]];    
	   	        }    
	   		        o[this.name].push(this.value || '');    
	   	    } else {    
	   		        o[this.name] = this.value || '';    
	   		}   
		});
		var jsonData = JSON.stringify(o);
		console.info(jsonData);
		//保存进url
		$.ajax({
			type:"post",
			url:ctx+"/role/saveRole",
			data: jsonData,
			contentType: "application/json; charset=utf-8", 
			dataType:"json",
			success: function(){
				$("#roleModal").modal("hide");
				alert("保存成功!");
				window.location.reload();
			}
		});
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
				url:ctx+"/role/delRoles",
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
