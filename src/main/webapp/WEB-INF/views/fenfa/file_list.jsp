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
<script type="text/javascript" src="${ctx}/assets/js/jsviews.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jQuery-ui/jquery-ui.min.js"></script>

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
					<h1 class="header">实施服务单列表</h1>
				</div>
			</div>
			<!-- panel -->
             <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                     
                      
                        <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- data table -->
                                        <div class="dataTable_wrapper">
                                            <table class="table table-striped table-bordered table-hover text-center" id="dict">
                                                <thead>
                                                    <tr>
                                                   
                                                      <th class="text-center">序号</th>
                                                        <th class="text-center">订单编号</th>
                                                        <th class="text-center">企业名称</th>
                                                        <th class="text-center">订单日期</th>
                                                          <th class="text-center">状态</th>
                                                         <th class="text-center">操作</th>
                                                         <!-- <th class="text-center">文件</th> -->
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
																<td><c:out value="${s.orgName}"></c:out></td>
																<td><c:out value="${s.orderDate}" /></td>
																<td>
																	<c:if test="${s.status==0}">
																			市场人员未审核
																		</c:if>
																		<c:if test="${s.status==1}">
																			实施管理人员未审核
																		</c:if>
																		<c:if test="${s.status==2}">
																			实施人员未审核
																		</c:if>
																		<c:if test="${s.status==3}">
																			已实施
																		</c:if>
																</td>
																<td>
																 <a href="javascript:forceCheck('${s.id}');" ><i class="fa fa-file-text-o"></i> 查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
																 <c:if test="${s.status==2}">
																 	<a href="javascript:download('${s.id}');" ><i class="fa fa-download"></i> License</a>
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
                                            <a href="${ctx}/main/fileList?page=1">首页</a></li></c:if>
                                        	<c:if test="${pageNum > 1 }"><li>
                                            <a href="${ctx}/main/fileList?page=${pageNum - 1}">上一页</a></li></c:if>
                                            <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/fileList?page=${pageNum + 1}">下一页</a></li></c:if>
                                             <c:if test="${pageNum < totalPageNum }">
                                            <li><a href="${ctx}/main/fileList?page=${totalPageNum}">最后一页</a></li></c:if>
                                        </ul>
                                    </div>
                                 
                                </div>
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
                        data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">实施服务单详情</h4>
            </div>
            <div class="modal-body" id="peopleList"></div>
           <div class="modal-footer" >
			<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button> 
		</div>
        </div>
    </div>
   </div> 	

<script type="text/javascript">
	  function download(id){
		  var o;
		  if (confirm("确认下载license文件？")) {
			o = window.open('${ctx}/main/download?id='+id);
	      } 
		  setTimeout(function(){
			    if(o){
			       window.location.reload();
			    }
			},100)
  }
	  
  function forceCheck(id){
	  $.get("${ctx}/main/findCompanyAndServicesByOrderId?orderId="+id,function(data, status){
			if("success" == status){
				$("#dictUpdate").modal('toggle');
				 var myTemplate = $.templates("#peopleTmpl");
				 var html = myTemplate.render(data);
				 $("#peopleList").html(html);
					 var time = new Date();
					 time.setFullYear(time.getFullYear()+1);
					 $("#expired").val(time.Format("yyyy-MM-dd"));
					 $("#expired").datepicker({
							dateFormat : "yy-mm-dd"
						});
					 $('#companyinfo').click(function(){
						 $('#collapseOne').collapse('show');
						 $('#collapseTwo').collapse('hide');
					 });
					 
					 $('#buyservice').click(function(){
						 $('#collapseOne').collapse('hide');
						 $('#collapseTwo').collapse('show');
					 });
					 
					 $('#collapseOne').collapse('show');
					 $("#discount_div").click(function(){
						 var dis = $("#optionsRadios4");
						 if(dis[0].checked){
							 $("#otherdiscount").show();
						 }else{
							 $("#otherdiscount").hide();
						 }
					 });
				}
			});
 }
  
  Date.prototype.Format = function(fmt)   
  { //author: meizz   
    var o = {   
      "M+" : this.getMonth()+1,                 //月份   
      "d+" : this.getDate(),                    //日   
      "h+" : this.getHours(),                   //小时   
      "m+" : this.getMinutes(),                 //分   
      "s+" : this.getSeconds(),                 //秒   
      "q+" : Math.floor((this.getMonth()+3)/3), //季度   
      "S"  : this.getMilliseconds()             //毫秒   
    };   
    if(/(y+)/.test(fmt))   
      fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
    for(var k in o)   
      if(new RegExp("("+ k +")").test(fmt))   
    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
    return fmt;   
  }
</script>
<script id="peopleTmpl" type="text/x-jsrender" >
<h5>&nbsp;&nbsp;&nbsp;&nbsp;产品名称:{{:order.appname}}</h5>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;订单编号:{{:order.id}}</h5>
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading" id="companyinfo">
      <h4 class="panel-title">客户信息</h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body">
       <div class="row">
      		<label class="col-sm-2 text-right">类型</label>
      		<div class="col-sm-10">{{:company.type}}</div>
   		</div>
		<div class="row">
      		<label class="col-sm-2 text-right">名称</label>
      		<div class="col-sm-10">{{:company.name}}</div>
   		</div>
		<div class="row">
      		<label class="col-sm-2 text-right">地址</label>
      		<div class="col-sm-10">{{:company.address}}</div>
   		</div>
		<div class="row">
      		<label class="col-sm-2 text-right">负责人</label>
      		<div class="col-sm-4">{{:company.header}}</div>
			<label class="col-sm-2 text-right">负责人电话</label>
      		<div class="col-sm-4">{{:company.headerPhone}}</div>
   		</div>
		<div class="row">
      		<label class="col-sm-2 text-right">联系电话</label>
      		<div class="col-sm-4">{{:company.phone}}</div>
			<label class="col-sm-2 text-right">邮箱</label>
      		<div class="col-sm-4">{{:company.headerEmail}}</div>
   		</div>

      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" id="buyservice">
      <h4 class="panel-title">购买服务列表</h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse">
      <div class="panel-body" style="height:300px;overflow:auto;" >
	{{for serviceList}}
       <span class="label label-info" style="line-height:2;font-size:18px;">{{:ssServiceBean.label}}</span>
	{{/for}}
      </div>
    </div>
  </div>
</div>
</script>
	              
</body>
</html>