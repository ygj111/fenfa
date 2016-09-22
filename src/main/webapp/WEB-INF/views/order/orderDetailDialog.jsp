<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 本页面对应css在/assets/css/orderDetail.css中 -->
<script type="text/javascript">
	function icon() {
		var obtn = $("#btn-icon");
		if (obtn.hasClass("fa-plus")) {
			obtn.removeClass("fa-plus").addClass("fa-minus");
			$(".m_b_mid").attr("style","display:block");
		} else {
			obtn.removeClass("fa-minus").addClass("fa-plus");
			$(".m_b_mid").attr("style","display:none");
		}
	}
</script>
<div class="modal fade" id="orderDetail" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="gridSystemModalLabel">订单详情</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="detail_inner">
						<div class="detail_org_name">单位名称${loginUser.company.name}</div>
						<div class="detail_table_title">商品清单</div>
						<div class="detail_table">
							<div class="detail_table_header">
								<div class="detail_table_header_c1">商品图片</div>
								<div class="detail_table_header_c2">商品名称</div>
								<div class="detail_table_header_c3">金额</div>
							</div>
							<div class="detail_table_content">
								<div class="detail_table_content_c1">
									<span></span>
									<!-- 不要去掉这个空标签，用来做图片垂直居中 -->
									<img src="${ctx}/assets/images/shengcheng.png" />
								</div>
								<div class="detail_table_content_c2" id="appname"></div>
								<div class="detail_table_content_c3" id="price"></div>
							</div>
							<div style="margin-left:30%;">附加服务：
								<a class="btn btn-collapse"  onclick="icon();">
									<i id="btn-icon" class="fa fa-minus"></i>
								</a> 
							</div>
							<div id="btn-collapse" class="m_b_mid">
								<ul class="ul11">
									
								</ul>
							</div>
						</div>
						<div class="detail_price">
							应付总额：<span id="totalPriceVal"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
