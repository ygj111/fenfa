<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="X-UA-Compatible" content="IE=9">
<link href="${ctx}/assets/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
<link href="${ctx}/assets/metisMenu/2.2.0/metisMenu.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/assets/css/thePage.css" />
<script src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
<script src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/metisMenu/2.2.0/metisMenu.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/thePage.js"></script>

</head>
<body>
	<div id="wrapper">
		<!-- 回到最顶端的按钮 -->
		<div id="scrollpoint" class="hidden">
			<a class="goup" href="#"> <i class="fa fa-angle-up fa-3x"></i>
			</a>
		</div>
	
		<jsp:include page="/WEB-INF/views/admin/inc/nav_top.jsp"></jsp:include>
		
		<jsp:include page="/WEB-INF/views/admin/inc/sidebar.jsp"></jsp:include>
		
		<div id="showspace">
			<div class=" row">
				<div class="col-lg-12">
					<h1 class="header">产品服务列表 [${appName }]</h1>
				</div>
			</div>
			<!-- panel -->
             <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                     
                      
                        <div class="panel-body">
                            
                             
                            
                        	<form id="mainForm" method="post" enctype="multipart/form-data">
                        	<div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-3" style="margin-left: 0px; margin-bottom: 5px">
                                        <!-- <div class=" input-group">
                                            <input type="text" class="form-control" placeholder="Search...">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" >
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div> -->
                                        [<a href="${ctx }/main/listApp">返回产品列表</a>]
                                    </div>
                                    
                                    
                                    
                                    <div class="col-sm-9 text-right"  style="margin-bottom: 5px">
                                    
                                       <div class="btn-group">
                                           <button class="btn btn-default" id="btn_download" type="button">
                                                <i class="fa fa-download"></i> 模板下载
                                            </button>
                                            <button class="btn btn-default" id="btn_imports" type="button">
                                                <i class="fa fa-pencil"></i> 导入
                                            </button>
                                             
                                            <!-- <input type="file" name="uploadExcel" id="uploadExcel"/>
                                            <input type="button" value="导入" class="btn btn-default" onclick="importExcel()"/> -->
                            
                                           
<!--                                             <button class="btn btn-default" id="btn_add" type="button"> -->
<!--                                                 <i class="fa fa-plus"></i> 新增 -->
<!--                                             </button> -->
<!--                                             <button class="btn btn-default" id="btn_del" type="button" data-toggle="tooltip" -->
<!--                                                     data-placement="right" title="删除"> -->
<!--                                                 <i class="fa fa-minus"></i> 删除 -->
<!--                                             </button> -->
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- data table -->
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover text-center" id="dict">
                                                <thead>
                                                    <tr>
                                                      <th class="text-center">序号</th>
                                                        
                                                        <th class="text-center">服务ID</th>
                                                        <th class="text-center">服务描述</th>
                                                        <th class="text-center">服务单价</th>
                                                        <th class="text-center">上级服务ID</th>
                                                        <th class="text-center">服务序号</th>
                                                        <th class="text-center">服务类型</th>
                                                        <th class="text-center">销售版本</th>
                                                        <th class="text-center">备注</th>
                                                         
                                                         <!-- <th class="text-center">订单提交</th> -->
                                                    </tr>
                                                </thead>
                                                <tbody>
													<c:if test="${empty serviceList}">
														<tr>
															<td colspan="10">没有记录</td>
														</tr>
													</c:if>
													<c:if test="${!empty serviceList}">
														<c:forEach items="${serviceList}" var="s" varStatus="status">
															<tr>
																<td>${ status.index + 1}</td>
																
																<td><c:out value="${s.realId}"></c:out></td>
																<td><c:out value="${s.label}"></c:out></td>
																<td><c:out value="${s.price}"></c:out></td>
																<td><c:out value="${s.pid}" /></td>
																<td><c:out value="${s.seq}"></c:out></td>
																<td>
																	<c:choose>
																		<c:when test="${s.serviceType eq 0}">
																			功能类型
																		</c:when>
																		<c:otherwise>
																			非功能类型
																		</c:otherwise>
																	</c:choose>
																</td>
																<td><c:out value="${s.appVersion}"></c:out></td>
																<td><c:out value="${s.remark}"></c:out></td>
																
															</tr>
														</c:forEach>
													</c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                       
                                    </div>
                                </div>
                                <div class="row">
                                    <!-- pagination -->
                                    <div class="col-sm-6">第<c:out value="${pageNum }"/>页/共<c:out value="${totalPageNum }"/>页</div>
                                    <div class="col-sm-6">
                                        <ul class="pagination navbar-right" style="margin: 0px">
                                        
                                        	<c:if test="${pageNum > 1}"><li>
                                            <a href="${ctx}/main/serviceList?page=1">首页</a></li></c:if>
                                        	<c:if test="${pageNum > 1 }"><li>
                                            <a href="${ctx}/main/serviceList?page=${pageNum - 1}">上一页</a></li></c:if>
                                            <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/serviceList?page=${pageNum + 1}">下一页</a></li></c:if>
                                             <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/serviceList?page=${totalPageNum}">最后一页</a></li></c:if>
                                        </ul>
                                    </div>
                                 
                                </div>
                            </div>
                            </form>
                        </div>
                    
                    </div>
                </div>
                
            </div>
           
		</div>
		
	</div>  
	
	<!--Modal-->
    <div class="modal fade" id="dictUpdate" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        	模态框（Modal）标题
                    </h4>
                </div>
                <div class="modal-body">
                    <form id="dictForm" role="form" method="post" action="saveService">
                        <div class="form-group">
                            <label for="ss_customerId" class="control-label">客户标识</label>
                            <input type="text" class="form-control" name="customerId" id="customerId">
                        </div>
                        <div class="form-group">
                            <label for="ss_appname" class="control-label">产品应用ID</label>
                            <input type="text"  class="form-control" id="appname" name="ssApp.appname"></input>
                        	<select id="select" name="ssApp.appname" style="display: none;width:570px; height:35px" >
                        		<c:forEach var="a" items="${appList}">
                        		   <option value="${a.appname}">${a.appname}</option>
                        	   </c:forEach>
                        		
                        	</select>
                        </div>
                        <div class="form-group">
                            <label for="ss_realId" class="control-label">真实服务ID</label>
                            <input type="text" class="form-control" name="realId" id="realId">
                        </div>
                      
                        <div class="form-group">
                            <label for="ss_label" class="control-label">真实服务中文描述</label>
                            <input type="text" class="form-control" id="label" name="label">
                        </div>
                        <div class="form-group">
                            <label for="ss_price" class="control-label">标准单价</label>
                            <input type="text" class="form-control" id="price" name="price">
                        </div>
                        
                         <div class="form-group">
                            <label for="ss_pid" class="control-label">上级服务ID</label>
                            <input type="text" class="form-control" id="pid" name="pid">
                        </div>
                        
                        <div class="form-group">
                            <label for="ss_seq" class="control-label">服务序号</label>
                            <input type="text" class="form-control" id="seq" name="seq">
                        </div>
                     
                        <div class="form-group">
                            <label for="ss_serviceType" class="control-label">服务类型</label>
                            <input type="text" class="form-control" id="serviceType" name="serviceType">
                        </div>
                        
                        <div class="form-group">
                            <label for="ss_appVersion" class="control-label">销售版本</label>
                            <input type="text" class="form-control" id="appVersion" name="appVersion">
                        </div>
                        <div class="form-group">
                        
                            <label for="ss_seq" class="control-label">备注</label>
                            <input type="text" class="form-control" id="remark" name="remark">
                        </div>
                      </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="btn_save" class="btn btn-primary" onclick="save();">
                        	保存
                    </button>
                   
                </div>
            </div>
        </div>
       </div> 
       
       
       <div class="modal fade" id="importUpdate" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="mydialog">
                        	模态框（Modal）标题
                    </h4>
                </div>
                <div class="modal-body">
                    <form id="importForm"  enctype="multipart/form-data" method="post">
                        <input type="text" name="appName"  id="111" style="display:none;" value="${appName }"> 
                       <div class="form-group">
                            <input type="file" class="form-control" name="uploadExcel" id="uploadExcel">
                            <input type="button" value="文件导入" class="btn btn-primary" onclick="importExcel()"/>
                        </div> 
                      </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                   	<div style="display:none;">
                   		<form id="fromService"  method="post">
                   			<input type="text" name="appname" id="fromAppname"/>
                   		</form>
                   	</div>
                </div>
                
            </div>
        </div>
       </div> 
       
       
       
       
	<script type="text/javascript">
	
	
	// 全选方法
		$('#checkAll').on('click', function() {
			console.info('checkall id:' + $(this).prop("checked"));
	   		$("input[name='id']").prop('checked', $(this).prop('checked'));
	   	});
 	
		// 新增操作
		$('#btn_add').on('click', function() { 
			// 清空所有的input
			$('#dictForm input').val("");
			
			var id;
			var flag = false;
			$('input[name="id"]').each(function(i) {
				if ($(this).prop('checked')) {
					id = $(this).attr('value');
					
					// 将选中行的数据填充到模态窗口
					var td = $(this).parent().nextAll();
					
					$('#pid').val(td.eq(2).text());
					$('#appname').val(td.eq(5).text());
				    flag = true;
					
				    
					return;
				}
				
			});
			
			if(flag){
				$("#select").attr("disabled","disabled");
				$("#select").css("display","none");
				$("#appname").removeAttr("disabled");
				$("#appname").css("display","block");
			}else{
				$("#select").removeAttr("disabled");
				$("#select").css("display","block");
				$("#appname").attr("disabled","disabled");
				$("#appname").css("display","none");
			}
			
			$('#myModalLabel').text('新增服务');		
			$("#dictUpdate").modal('toggle');
			
			
			/* // 更换Action位置
			$('#dictForm').attr('action', '${ctx}/saveService'); */
		
		});
		//保存
		function save(){
			
			    var realId=$("#realId").val()
	   			if(realId==null || realId==""){
	   				alert("真实服务Id不能为空")
	   				return false;
	   			}
			    var price=$("#price").val()
	   			if(price==null || price==""){
	   				alert("单价不能为空")
	   				return false;
	   			}
			 
			    var seq=$("#seq").val()
	   			if(seq==null || seq==""){
	   				alert("服务序号不能为空")
	   				
	   				return false;
	   			}
			 
			 
                if (confirm("确认保存？")) {
   				
   				document.forms['dictForm'].submit();
   				
   				alert("保存成功！");
   				

   	      } else {

   	           return false;

   	      }
   	}
		
		
		
		
		// 删除操作
		$('#btn_del').on('click', function() {
			// 判断表格中是否有行被选中
			var id;
			
			$('input[name="id"]').each(function(i) {
				if ($(this).prop('checked')) {
					id += $(this).attr('value') + ";";
				}
			});
			
			if (id == null) {
				alert("请选择一条需要删除的数据！");
				return;
			}
			else {
				if (confirm("确认删除选中的服务！？")) {
					// 更换Action位置
		   			$('#mainForm').attr('action', '${ctx}/main/deleteService');
					$('#mainForm').submit();
					alert("删除成功");
					
				}
				
			}
		});
		 
		
		//导入操作
		$('#btn_imports').on('click', function() {
			
			$('#mydialog').text('导入文件');		
			$("#importUpdate").modal('toggle');
			
		})
		function importExcel(){
			var result=confirm("你确定导入文件？")
			if(result){
				var importFileName=$("#uploadExcel").val();
				   
				   if(importFileName=="" ||importFileName==null ){
					   alert("文件不能为空，请选择文件!");
					   return false;
				}else{
					var d1=/\.[^\.]+$/.exec(importFileName);  
					 if(d1==".xls"){  
						 var formData = new FormData($( "#importForm" )[0]);
						 //$('#importForm').attr('action', '${ctx}/main/importExcel1');
						// $('#importForm').submit(); 
						   $.ajax({
								type : "POST",
								url : "${ctx}/main/importExcel1",
								cache: false,
								async: true,
								contentType: false,  
						        processData: false, 
								data :  formData, 
								dataType : "json",
								success : function(data) {
									var appname="${appName}";
									if(data == true){
										alert("导入成功");
										$("#fromService").attr("action","${ctx}/main/serviceList1");
										$("#fromAppname").val(appname);
										$("#fromService").submit();
										//window.location.href="${ctx}/main/serviceList1?appname="+appname;
									} else {
										alert("产品名称与导入文件中产品不相同，导入失败");
									}
								}
								
							});	   
					 }else{
						 alert("文件格式不对，请选择xls格式文件！");  
					 }
					
					 
					 
					}
			  }else{
				  return false;
			  }
		   
		}
		
		// 下载模板
		$('#btn_download').click(function() {
			window.open("${ctx}/assets/download/template.xls");
		})
		 
	</script>
	              
</body>
</html>