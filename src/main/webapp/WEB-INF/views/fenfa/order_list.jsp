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
					<h1 class="header">订单列表</h1>
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
                                                         <th class="text-center">产品名称</th>
                                                        <th class="text-center">服务名称</th>
                                                        <!-- <th class="text-center">服务单价</th> -->
                                                        <th class="text-center">订单日期</th>
                                                        <th class="text-center">订单总价</th>
                                                        
                                                        <th class="text-center">状态</th>
                                                        <th class="text-center">操作</th>
                                                        
                                                    </tr>
                                                </thead>
                                                <tbody>
													<c:if test="${empty ssOrderList}">
														<tr>
															<td colspan="10">没有记录</td>
														</tr>
													</c:if>
													<c:if test="${!empty ssOrderList}">
														<c:forEach items="${ssOrderList}" var="s" varStatus="status">
															<tr>
																<td>${ status.index + 1}</td>
																<td><c:out value="${s.id}"></c:out></td>
																<td><c:out value="${s.customerId}"></c:out></td>
																<td><c:out value="${s.orgName}"></c:out></td>
																<td><c:out value="${s.appname}"></c:out></td>
																<td><c:out value="${s.services}"></c:out></td>
																<td><c:out value="${s.orderDate}" /></td>
																<td><c:out value="${s.total}"></c:out></td>
																
																<td><c:out value="${s.status}"></c:out></td>
																
																
																<td>
																<c:if test="${s.status=='未提交'}">
																<%-- <a href="${pageContext.request.contextPath}/submitSsOrder?id=${s.id}"  class="btn btn-primary">订单提交</a>  --%>
																 <input type="button" value="订单提交" onclick="orderSubmit(this,'${s.id}', '${s.status}');" class="btn btn-primary">
																</c:if>
																<c:if test="${s.status!='未提交'}">																
																 <input type="button" value="订单提交" disabled="disabled" class="btn btn-primary">
																</c:if>
																</td>
																
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
                                            <a href="${ctx}/main/showSsOrder?page=1">首页</a></li></c:if>
                                        	<c:if test="${pageNum > 1 }"><li>
                                            <a href="${ctx}/main/showSsOrder?page=${pageNum - 1}">上一页</a></li></c:if>
                                            <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/showSsOrder?page=${pageNum + 1}">下一页</a></li></c:if>
                                             <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/showSsOrder?page=${totalPageNum}">最后一页</a></li></c:if>
                                        </ul>
                                    </div>
                                 
                                </div>
                            </div>
                            <input type="hidden" name="id" value="">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
           
		</div>
		
	</div>  
	
	
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
                    <form id="dictForm" role="form" name="dictForm" method="get" action="submitSsOrder" >
                       <div class="form-group">
                       <label for="app_customerId" class="control-label">订单号</label>
                         <input type="text" name="id" id="id" class="form-control" readOnly="true" >
                      </div>
                        <div class="form-group">
                            <label for="app_customerId" class="control-label">客户标识</label>
                            <input type="text" class="form-control" name="customerId" id="customerId" readOnly="true" >
                        </div>
                        <div class="form-group">
                            <label for="app_orgName" class="control-label">企业名称</label>
                            <input type="text" class="form-control" name="orgName" id="orgName" readOnly="true" >
                        </div>
                        <div class="form-group">
                            <label for="app_orgName" class="control-label">产品名称</label>
                            <input type="text" class="form-control" name="appname" id="appname" readOnly="true" >
                        </div>
                         <div class="form-group">
                            <label for="app_services" class="control-label">产品服务</label>
                            <input type="text" class="form-control" name="services" id="services" readOnly="true" >
                        </div>
                        <div class="form-group">
                            <label for="app_orderDate" class="control-label">订单日期</label>
                            <input type="text" class="form-control" name="orderDate" id="orderDate" readOnly="true" >
                        </div>
                        <div class="form-group">
                            <label for="app_total" class="control-label">订单总价</label>
                            <input type="text" class="form-control" name="total" id="total" readOnly="true" >
                        </div>
                       
                       <div class="form-group">
                            <label for="app_status" class="control-label">状态</label>
                            <input type="text" class="form-control" name="status" id="status" readOnly="true" >
                        </div>
                        
                     <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                     </button>
                     <button type="button" id="btn_save" class="btn btn-primary" onclick="suresubmit();">
                        	确认递交
                     </button>
                  </form>        
                </div>
               
            </div>
        </div>
       </div> 
	
	
	
	
	
	<script type="text/javascript">
		
		 
		  function orderSubmit(obj,id, status){
			
			  var td = $(obj).parent().prevAll();
				//赋值
			   $('#id').val(td.eq(7).text());
				$('#customerId').val(td.eq(6).text());
				$('#orgName').val(td.eq(5).text());
				$('#appname').val(td.eq(4).text());
				$('#services').val(td.eq(3).text());
				$('#orderDate').val(td.eq(2).text());
				$('#total').val(td.eq(1).text());
				$('#status').val(td.eq(0).text());
				
				$('#myModalLabel').text('订单详情信息');		
				 $("#dictUpdate").modal('toggle');
				 $("input[name=id]").val(id); 	  
			
			
	 }  
		 
		 function suresubmit(){
			 
			 if (confirm("确认订单提交？")) {
					
					document.forms['dictForm'].submit();
					
					alert("订单提交成功！");

		      } else {

		           return false;

		      }
			 
		 }
		 
	</script>
	
	
<!-- 	<script type="text/javascript"> -->
//         	$(".sidebar ul li").eq(1).addClass("active");
//         	$(".sidebar ul li a").eq(2).css("background-color","#636A71");
//         	$(".sidebar ul li a").eq(2).css("color","#FFF");
        
<!--         </script> -->
	              
</body>
</html>