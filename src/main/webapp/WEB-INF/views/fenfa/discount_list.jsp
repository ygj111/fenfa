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
					<h1 class="header">折扣设置列表</h1>
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
                                                      <th class="text-center">序号</th>
                                                        <th class="text-center">订单号</th>
                                                        <th class="text-center">客户标识</th>
                                                        <th class="text-center">企业名称</th>
                                                        <th class="text-center">产品及服务项</th>
                                                        <!-- <th class="text-center">服务单价</th> -->
                                                        <th class="text-center">订单日期</th>
                                                        <th class="text-center">订单总价</th>
                                                        
                                                        <th class="text-center">状态</th>
                                                       
                                                        
                                                    </tr>
                                                </thead>
                                                <tbody>
													<c:if test="${empty order}">
														<tr>
															<td colspan="10">没有记录</td>
														</tr>
													</c:if>
													<c:if test="${!empty order}">
														
															<tr>
																<td>${ status.index + 1}</td>
																<td><c:out value="${order.id}"></c:out></td>
																<td><c:out value="${order.customerId}"></c:out></td>
																<td><c:out value="${order.orgName}"></c:out></td>
																<td><c:out value="${order.services}"></c:out></td>
																<td><c:out value="${order.orderDate}" /></td>
																<td><c:out value="${order.total}"></c:out></td>
																
																<td><c:out value="${order.status}"></c:out></td>
																
																
																
																
															</tr>
														
													</c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                       
                                    </div>
                                </div>
                               
                            </div>
                            <input type="hidden" name="id" value="">
                             <input type="button" value='返回' onclick="back()" class="btn btn-primary"/>
                            <input type="button" value="确认审核" onclick="ddd('${order.id}', '${order.status}');"  class="btn btn-primary">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
           
		</div>
		
	</div>  
	
	<script type="text/javascript">
		
		 
		  function ddd(id, status){
			alert(id);
			alert(status)
			 
			 if("0" == status){
				 alert("确认订单审核？");
				 $("input[name=id]").val(id);
				 $('#mainForm').attr('action', '${ctx}/checkOrder');
				 $('#mainForm').submit();
				 alert("订单审核成功");
				 
				 
				
				 
			 }else{
				 
				alert("订单处于"+status+"状态"+",不可以重复审核");
				 return;
				 
			 }
			
			
			 
			
		 }  
		
		  function back(){
			   alert("确认返回？");
			   
			   $('#mainForm').attr('action', '${ctx}/fenfa_home');
			   $('#mainForm').submit();
		   }  
	
	</script>
	
	
	
	              
</body>
</html>