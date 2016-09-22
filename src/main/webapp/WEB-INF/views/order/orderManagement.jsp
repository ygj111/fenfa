<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/assets/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/assets/css/management.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/assets/css/orderDetail.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/assets/fontawesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<title>我的订单</title>
<script src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
<script src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script >
	var ctx='${ctx}';
</script>
<style>
#orderDetail {
	height: 630px;
}
#orderDetail .modal-body {
	height: 400px;
	overflow: auto;
}
/*
滚动整个modal
#myModal .modal-content
{
  height:200px;
  overflow:auto;
}
*/
</style>

</head>

<body>
	<div id="container">
		<div id="header">
			<div class="h_inner">
				<div class="hlt">
					<a href="${ctx}/main/welcome"><img src="${ctx}/assets/images/h1.png" /></a>
				</div>
				<div class="hrt">
					<span>您好！尊敬的${sessionScope.loginUser.username}</span> <a
						href="${ctx}/login"><img src="${ctx}/assets/images/black.png" /><span>退出</span></a>
				</div>
			</div>
		</div>
		<div id="main" style="min-height:500px">
			<div class="main_inner">
				<div class="main_top" style="position:relative;">
					<img src="${ctx}/assets/images/dingdan_icon.png" />
					<h3>我的订单</h3>&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="position:absolute; bottom:5px;">[<a href="${ctx}/main/welcome">返回首页</a>]</span>
				</div>
				<!--  <div class="main_cont">
        	<ul>
            	<li>我的交易提醒：</li>
            	<li>未支付<span class="txt_red">1</span>个</li>
            	<li>已支付<span class="txt_green">4</span>个</li>
            </ul>
        </div> -->
				<form action="" id="orderManagement" method="get">
					<div class="main_bot">
						<div class="m_b_top">
							<div class="one">序号</div>
							<div class="two">订单号</div>
							<div class="three">产品名称</div>
							<div class="four">销售版本</div>
							<div class="six">总金额(元)</div>
							<div class="seven">购买日期</div>
							<div class="eight">操作</div>
						</div>
						<c:forEach items="${ssOrderList}" var="s" varStatus="status">
							<div class="m_b_cont">
								<div class="one">
									<p>${status.index + 1}</p>
								</div>
								<div class="two">
									<p>${s.id}</p>
								</div>
								<div class="three">
									<img src="${ctx}/assets/images/dingdan_pic.png" />
									<ul>
										<li class="li_top">${s.appname}</li>
									</ul>
								</div>
								<div class="four">
									<p>${s.appVersion}</p>
								</div>
								<div class="six">
									<p>${s.total}</p>
								</div>
								<div class="seven">${s.orderDate}</div>
								<div class="eight">
									<ul>
										<li>
											<%-- <a href="${ctx}/main/ordertail?id=${s.id}" class="li_left">查看订单详情</a> --%>
											<a href="#"
											onclick="forceCheck('${s.id}');"><span
												class="glyphicon glyphicon-eye-open"></span> 查看订单详情</a>
										</li>
										<%-- <li><a  onclick="cancel('${s.appname}')">取消订单</a></li>  --%>

									</ul>
								</div>
								<jsp:include page="orderDetailDialog.jsp" />
							</div>

						</c:forEach>
					</div>
				</form>
				<div class="row">

					<div class="pull-right">
						第
						<c:out value="${pageNum }" />
						页/共
						<c:out value="${totalPageNum }" />
						页
						<c:if test="${pageNum > 1}">
							<a href="${ctx}/main/showSsOrder?page=1">首页</a>
						</c:if>
						<c:if test="${pageNum > 1 }">
							<a href="${ctx}/main/showSsOrder?page=${pageNum - 1}">上一页</a>
						</c:if>
						<c:if test="${pageNum < totalPageNum }">
							<a href="${ctx}/main/showSsOrder?page=${pageNum + 1}">下一页</a>
						</c:if>
						<c:if test="${pageNum < totalPageNum }">
							<a href="${ctx}/main/showSsOrder?page=${totalPageNum}">最后一页</a>
						</c:if>
					</div>


				</div>
			</div>
		</div>

		<script type="text/javascript">
			function cancel() {
				var result = confirm("你确定返回？");
				if (result) {
					$('#orderManagement').attr('action', '${ctx}/main/welcome');
					$('#orderManagement').submit();
				} else {
					return false;
				}
			}
			function forceCheck(id) {
				$.ajax({
					type:'get',
					url:ctx + '/main/orderDetailAndService',
					data:{id:id},
					dataType:'json',
					success:function(data){
						var order = data.order;
						var serviceList = data.serviceList;
						$('#appname').text(order.appname);
						$('#price').text(order.total);
						$('#totalPriceVal').text(order.total);
						$('.ul11').empty();
						for(var i=0;i<serviceList.length;i++){
							$('.ul11').append("<li>"+
								"<p style='line-height:20px;width: 40%;font-size:16px;color:#666666;text-align:left;'><a href='#'>"+serviceList[i].label+"</a></p>"+
								"<p class='p2' style='text-align:right;line-height:20px;'>￥"+serviceList[i].price+"</p>"+
								"</li>"); 
						}
						
					}
				});
				$("#orderDetail").modal('toggle');
			}
		</script>
		<div id="footer">
			<p>技术支持：广州粤建三和软件股份有限公司</p>
		</div>
	</div>
</body>
</html>
