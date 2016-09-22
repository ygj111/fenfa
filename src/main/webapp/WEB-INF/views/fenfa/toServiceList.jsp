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
					<h1 class="header">选择服务列表</h1>
				</div>
			</div>
			<!-- panel -->
             <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                     
                      
                        <div class="panel-body">
                        	<form id="mainForm" method="get">
                        	<div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-3" style="margin-left: 0px; margin-bottom: 5px">
                                        <div class=" input-group">
                                            <input type="text" class="form-control" placeholder="Search...">
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" >
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-9 text-right">
                                       
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- data table -->
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover text-center" id="dict">
                                                <thead>
                                                    <tr>
                                                     <th class="text-center"><input type="checkbox" id="checkAll" name="checkAll"></th>
                                                      <th class="text-center">序号</th>
                                                        
                                                        <th class="text-center">客户标识</th>
                                                       
                                                        <th class="text-center">产品（应用）名称</th>
                                                        <th class="text-center">服务名称</th>
                                                        <th class="text-center">标准单价</th>
                                          
                                                        
                                                    </tr>
                                                </thead>
                                                <tbody>
													<c:if test="${empty serviceList}">
														<tr>
															<td colspan="10">没有记录</td>
														</tr>
													</c:if>
													<c:if test="${!empty serviceList}">
														<c:forEach var="s" items="${serviceList}" varStatus="status">
															<tr>
															<td class="text-center"><input type='checkbox' name='checkid' value="${s.id}"></td>
																<td>${ status.index + 1}</td>
																
																<td><c:out value="${s.customerId}"></c:out></td>
																
																<td><c:out value="${s.ssApp.description}"></c:out></td>
																<td><c:out value="${s.label}" /></td>
																<td><c:out value="${s.price}"></c:out></td>
															</tr>
														</c:forEach>
													</c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                       
                                    </div>
                                </div>
                               
                            </div>
                            <input type="hidden" name="id" value="">
                            <center>
                             <input type="button" value='返回' onclick="back()" class="btn btn-primary"/>
                            <input type="button" value="确定" onclick="check();"  class="btn btn-primary">
                            </center>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
           
		</div>
		
	</div>  
	
	<script type="text/javascript">
	// 全选方法
		$('#checkAll').on('click', function() {
			console.info('checkall checkid:' + $(this).prop("checked"));
	   		$("input[name='checkid']").prop('checked', $(this).prop('checked'));
	   	});
 	
		 
		  function check(){
	          
				 var checkid;

	   			$('input[name="checkid"]').each(function(i) {
	   				if ($(this).prop('checked')) {
	   					checkid = $(this).attr('value');	
	   		      	}
	   			
	   			})		
		   		if (checkid == null) {
	  				alert("请选择需要生成订单的数据！");
	  				return false;
	  				
   			}		
		   					
				 var result =confirm("确认生成订单？");
                 if(result==true){
                	 $('#mainForm').attr('action', '${ctx}/main/insertSsOrder');
    				 $('#mainForm').submit();
    				 alert("订单生成成功");
                 }else{
                	 return false;
                 }
			
		 }  
		
		  function back(){
			   //alert("确认返回？");
			   var result=confirm("订单未生产，确定放弃本次操作？");
			   if(result==true){
				   $('#mainForm').attr('action', '${ctx}/main/welcome');
				   $('#mainForm').submit();
			   }else{
				   return false;
			   }
			  
		   }  
	
	</script>
	
	
	
	              
</body>
</html>