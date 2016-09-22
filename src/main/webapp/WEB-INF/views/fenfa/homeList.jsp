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
					<h1 class="header">产品列表</h1>
				</div>
			</div>
			<!-- panel -->
             <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                     
                      
                        <div class="panel-body">
                        	<form id="mainForm" method="post">
                        	<div class="container-fluid">
                              
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- data table -->
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover text-center" id="dict">
                                                <thead>
                                                    <tr>
                                                      <!--   <th class="text-center"><input type="checkbox" id="checkAll" name="checkAll"></th> -->
                                                          <th class="text-center" width="5%">序号</th>
                                                        <th class="text-center">产品</th>
                                                        <th class="text-center">版本</th>
                                                         <th class="text-center">关联单位类型</th>
                                                         <th class="text-center">中文描述</th>
                                                        <th class="text-center" width="10%">操作</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
													<c:if test="${empty ssApp}">
														<tr>
															<td colspan="6">没有记录</td>
														</tr>
													</c:if>
													<c:if test="${!empty ssApp}">
														<c:forEach items="${ssApp}" var="s" varStatus="status">
															<tr>
																<td width="5%">${status.index + 1}</td>  
																<td><c:out value="${s.appname}" /></td>	
																<td><c:out value="${s.version}"></c:out></td>
																<td><c:out value="${s.type}"></c:out></td>
																<td><c:out value="${s.description}"></c:out></td>
																<td width="15%">
		                                                         <a href="${pageContext.request.contextPath}/main/toSelectService?appname=${s.appname}" ><i class="fa fa-download"> 导入服务</i></a>
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
                                            <a href="${ctx}/main/welcome?page=1">首页</a></li></c:if>
                                        	<c:if test="${pageNum > 1 }"><li>
                                            <a href="${ctx}/main/welcome?page=${pageNum - 1}">上一页</a></li></c:if>
                                            <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/welcome?page=${pageNum + 1}">下一页</a></li></c:if>
                                             <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/welcome?page=${totalPageNum}">最后一页</a></li></c:if>
                                        </ul>
                                    </div>
                                 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
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
                    <form id="dictForm" role="form" method="post">
                        <div class="form-group">
                            <label for="app_appname" class="control-label">产品名称</label>
                            <input type="text" class="form-control" name="appname" id="appname">
                        </div>
                        <div class="form-group">
                            <label for="app_description" class="control-label">中文描述</label>
                            <input type="text" class="form-control" name="description" id="description">
                        </div>
                       
                        <div class="form-group">
                            <label for="app_version" class="control-label">版本</label>
                            <input type="text" class="form-control" id="version" name="version">
                        </div>
                       
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="btn_save" class="btn btn-primary" onclick="dictForm.submit();">
                        	保存
                    </button>
                    </form>
                </div>
            </div>
        </div>
<!--         <script type="text/javascript"> -->
//         	$(".sidebar ul li").eq(1).addClass("active");
//         	$(".sidebar ul li a").eq(1).css("background-color","#636A71");
//         	$(".sidebar ul li a").eq(1).css("color","#FFF");
        
<!--         </script> -->
     
</body>
</html>