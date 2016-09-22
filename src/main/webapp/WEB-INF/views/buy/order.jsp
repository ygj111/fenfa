<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>购买产品</title>

<link href="${ctx}/assets/css/generate.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="${ctx}/assets/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
<script type="text/javascript"
	src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var appname = '${ssAppBean.appname}';
	var appVersion = '${ssAppBean.version}';
	var totalPrice = '${totalPrice}';
	$(function() {
		$('#generateOrderBtn').click(function() {
			$('#appname').val(appname);
			$('#appVersion').val(appVersion);
			$('#totalPrice').val(totalPrice);
			var serviceIdInput = $('input[name=\'serviceId\']');
			var serviceIds = new Array();
			for (var i = 0; i < serviceIdInput.length; i++) {
				serviceIds[i] = serviceIdInput[i].value;
			}
			$('#serviceIds').val(serviceIds);
			$('#generateOrderForm').submit();
		});
	});
	function icon() {
		var obtn = $("#btn-icon");
		if (obtn.hasClass("fa-plus")) {
			obtn.removeClass("fa-plus").addClass("fa-minus");
		} else {
			obtn.removeClass("fa-minus").addClass("fa-plus");
		}
	}
</script>
</head>

<body>
	<div id="container">
		<div id="header">
			<div class="h_inner">
				<div class="hlt">
					<a href="#"><img src="${ctx}/assets/images/h1.png" /></a>
				</div>
				<div class="hrt">
					<span><a href="${ctx}/main/showSsOrder">我的订单</a></span>
					<span>您好！尊敬的用户${loginUser.username}</span> <a href="${ctx}/login"><img
						src="${ctx}/assets/images/black.png" /><span>退出</span></a>
				</div>
			</div>
		</div>

		<div id="main">
			<div class="main_inner">
				<div class="main_top">
		        	<h3>核对订单信息</h3>
		        </div>
		    	<div class="main_txt">
		        	<p>单位名称:${loginUser.company.name}</p>
		        </div>
				<div class="main_cont">
					<h4>商品清单</h4>
				</div>
				<div class="main_bot">
					<div class="m_b_top">
						
					</div>
					<div class="m_b_bot">
						<div class="t_one">
							<img src="${ctx}/assets/images/shengcheng.png" />
						</div>
						<div class="t_two">
							<h4>${ssAppBean.appname}</h4>
							<p>购买附加服务：</p>
						</div>
					</div>
					<div class="m_b_mid">
						<ul>
							<li>
								<c:if test="${fn:length(choseServices) > 1}">
									<button class="btn btn-collapse" data-toggle="collapse"
										data-target="#btn-collapse" onclick="icon();">
										<i id="btn-icon" class="fa fa-plus"></i>
									</button>
								</c:if>
								<c:forEach items="${choseServices}" var="service"
									varStatus="status">
									<c:if test="${status.index eq 0}">
										<input type="hidden" name="serviceId" value="${service.realId}">
										<p class="p01">
											<a>${service.label}</a>
										</p>
										<p class="p2">￥${service.price}</p>
									</c:if>
								</c:forEach></li>
						</ul>
						<div id="btn-collapse" class="collapse ">
							<c:forEach items="${choseServices}" var="service"
								varStatus="status">
								<c:if test="${status.index ne 0}">
									<li><input type="hidden" name="serviceId"
										value="${service.realId}">
										<p class="p1">
											<a href="#">${service.label}</a>
										</p>
										<p class="p2">￥${service.price}</p>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="bug_btn">
					<p style="font-size:20px;color:#424242;line-height:60px;padding-right:24px;">应付总额：<span id="totalPriceVal">￥${totalPrice}</span></p>
					<form id="generateOrderForm" action="${ctx}/buy/generateOrder"
						method="post">
						<input type="hidden" name="appname" id="appname"> <input
							type="hidden" name="appVersion" id="appVersion"> <input
							type="hidden" name="totalPrice" id="totalPrice"> <input
							type="hidden" name="serviceIds" id="serviceIds">
					</form>
						<button id="backBtn" class="btn back-btn" onclick="history.back();">返回上一级</button>
						<button class="btn shopping-btn" id="generateOrderBtn"><h4>生成订单</h4></button>
				</div>
			</div>
		</div>
		<div id="footer">
			<p>技术支持：广州粤建三和软件股份有限公司</p>
		</div>
	</div>
</body>
</html>
