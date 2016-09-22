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
<link rel="stylesheet" href="${ctx}/assets/jstree-3.3.0/themes/default/style.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jQuery-ui/jquery-ui.min.css" />
<script src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
<script src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/metisMenu/2.2.0/metisMenu.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="${ctx}/assets/echart/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jQuery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jstree-3.3.0/jstree.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jsviews.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/thePage.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/admin/flow.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/tmpl/sysmanager.js"></script>

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
		
		<!-- content -->
		<div class="main-content" id="showspace">
			<div class=" row">
				<div class="col-lg-12">
					<h3 class="header" id="title_text"></h3>
				</div>

			</div>

			<div id="main-content">
				
			</div>
		</div>
		<!-- End content -->
	</div>
	<script type="text/javascript">
		function GetQueryString(name)
		{
		     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		     var r = window.location.search.substr(1).match(reg);
		     if(r!=null)return  unescape(r[2]); return null;
		}
		window.onload=function() {
			var menuId=GetQueryString("menuId");
			var flow = workflow();
			if ("menu_dep" == menuId) {
				$("#title_text").text("公司架构");
				flow.departmanager();
			} else if ("menu_user" == menuId) {
				$("#title_text").text("用户管理");
				flow.userlist(0);
			} else if ("menu_role" == menuId) {
				$("#title_text").text("角色管理");				flow.rolelist(0);
			} else if ("menu_resourcse" == menuId) {
				$("#title_text").text("资源管理");
				flow.resourcselist(0);
			} else if ("menu_menu" == menuId) {
				$("#title_text").text("菜单管理");
				flow.menumanager();
			} else if ("menu_loginLog" == menuId) {
				$("#title_text").text("登陆日志");
				flow.loginloglist(0);
			} else if ("menu_flow_definition" == menuId) {
				flow.getFlowList(0,'');
			} else if ("menu_dictionary" == menuId) {
				$("#title_text").text("数据字典");
				flow.dictionarylist(0);
			} else if ("menu_flow_instance" == menuId){
				flow.floworderlist(0);
			}

		}
	</script>
	<div class="modal fade" id="myModal_dialog" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body" id="mydialog_body"></div>
				<div class="modal-footer" >
				<ul id="dialog_pagination" style="margin: 0;" class="pagination pagination-sm pull-left"></ul>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
					<button type="button" class="btn btn-primary" id="myModal_dialog_ok">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
     
</body>
</html>