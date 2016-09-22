<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择产品页面</title>
<link href="${ctx}/assets/css/xuanze.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/assets/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
<script src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
	<div id="header">
		<div class="h_inner">
			<div class="hlt">
				<a href="#"><img src="${ctx}/assets/images/h1.png" /></a>
			</div>
			<div class="hrt">
				<span><a href="${ctx}/main/showSsOrder">我的订单</a></span>
				<span>您好！尊敬的${sessionScope.loginUser.username}</span> <a
					href="${ctx}/login"><img src="${ctx}/assets/images/black.png" /><span>退出</span></a>
			</div>
		</div>
	</div>
	<div id="cpmain">
		<div class="cpbt">软件产品</div>
		<br/>
			<!-- 产品显示列表 Begin -->
				<div class="container-fluid"  style="min-height: 450px">
					<div class="row" >
						<!-- 产品显示 -->
						<c:forEach items="${allApp}" var="a" varStatus="status">
						<div class="col-sm-3" >
							<div class="well">
								<div style="width:100%; text-align:center">
            						<form class="buyForm" method="post" action="${ctx }/buy/toBuyApp">
	            	 					<input type="hidden" name="appname"  value="${a.appname }">
            						</form>
								    <a  class="appToBuy" href="#" style="cursor:hand">
										<img src="${ctx}/assets/images/xzsp_0${(status.index%4) +1}.gif" style="margin:30px auto;"/>
									</a>
									<p style="width:98%; text-align:left;padding-left:20px;margin-top:10px;">
										<span class="appname" style="color:#2b2b2b;font-size:20px">${a.appname}</span>
									</p>
									<p style="width:98%; text-align:left;padding-left:20px;margin-top:10px;">
										<span>${a.description}</span>
									</p>
								</div>
							</div>									
						</div>
						<c:if test="${status.index % 4 ==3} }"><br/></c:if>
						</c:forEach>								
					</div>
				</div>
				
			<!-- 产品显示列表 end -->
	</div>

	<div id="footer"></div>
	<script language="Javascript">
		$('[name="nice-select"]').click(function(e) {
			$('[name="nice-select"]').find('ul').hide();
			$(this).find('ul').show();
			e.stopPropagation();
		});
		$('[name="nice-select"] li').hover(function(e) {
			$(this).toggleClass('on');
			e.stopPropagation();
		});
		$('[name="nice-select"] li').click(function(e) {
			var val = $(this).text();
			$(this).parents('[name="nice-select"]').find('input').val(val);
			$('[name="nice-select"] ul').hide();
			e.stopPropagation();
		});
		$(document).click(function() {
			$('[name="nice-select"] ul').hide();
		});
		$(".appToBuy").click(function(){
			$(this).parent().find(".buyForm").submit();
		});
	</script>

</body>
</html>
