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
<link rel="stylesheet" href="${ctx}/assets/jQuery-ui/jquery-ui.min.css" />
<link href="${ctx}/assets/metisMenu/2.2.0/metisMenu.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/assets/css/thePage.css" />
<link rel="stylesheet"
	href="${ctx}/assets/bootstrap-select/css/bootstrap-select.min.css" />
<script src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
<script src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/jsviews.min.js"></script>
<script src="${ctx}/assets/metisMenu/2.2.0/metisMenu.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/thePage.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/jQuery-ui/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/bootstrap-select/js/bootstrap-select.min.js"></script>
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
					<h1 class="header">订单审核列表</h1>
				</div>
			</div>
			<!-- panel -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">

						<div class="panel-body">
							<form id="mainForm" method="get" action="orderSearch">
								<div class="container-fluid">
									<div class="row">
										<div class="col-sm-2 col-sm-offset-10"
											style="margin-bottom: 5px;">
											<select id="basic" class="form-control selectpicker" onchange="getOrderPage(1)">
												<option value="">--选择状态--</option>
												<option value="0" selected="selected">市场人员未审核</option>
												<option value="1">实施管理人员未审核</option>
												<option value="2">实施人员未审核</option>
												<option value="3">已实施</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<!-- data table -->
											<div class="dataTable_wrapper">
												<table
													class="table table-striped table-bordered table-hover text-center"
													id="dict">
													<thead>
														<tr>
															<th class="text-center" style="width: 6%">序号</th>
															<th class="text-center" style="width: 24%">企业名称</th>
															<th class="text-center" style="width: 24%">产品名称</th>
															<th class="text-center" style="width: 16%">订单日期</th>
															<th class="text-center" style="width: 10%">总价</th>
															<th class="text-center" style="width: 10%">状态</th>
															<th class="text-center" style="width: 10%">操作</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
										<div id ="pageNumLabel" class="col-sm-6"></div>
										<div class="col-sm-6">
											<ul id="pagingBar" class="pagination navbar-right"
												style="margin: 0px">
											</ul>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="dictUpdate" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 700px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">订单审核</h4>
				</div>
				<div class="modal-body" id="peopleList"></div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						id="myModal_dialog_ok">确定</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$(function() {
		getOrderPage(1);
	})
	function getOrderPage(pageNum){
		var status = $("#basic").val();
		var targetUrl = '${ctx}/main/getOrdersByStatusAndPage';
		$.ajax({
			type:'post',
			data:{'status':status,'page':pageNum},
			url:targetUrl,
			dataType:'json',
			success:function(data){
				var orders = data.orders;
				var totalPageNum = data.totalPageNum;
				var tbody = "";
				if (orders.length == 0) {
					tbody = "<tr><td colspan='10'>没有记录</td></tr>";
				}else{
					$.each(orders,function(index){
						var time = orders[index].orderDate;
						var dateTime = new Date();
						dateTime.setTime(time);
						time = dateTime.Format("yyyy-MM-dd hh:mm:ss");
						var status = orders[index].status;
						if(status == 0){
							status = '市场人员未审核';
						}else if(status==1){
							status = '实施管理人员未审核';
						}else if(status==2){
							status = '实施人员未审核';
						}else if(status==3){
							status = '已实施';
						}
						tbody = tbody + "<tr><td>"+(index+1)+"</td><td>"+orders[index].orgName+"</td><td>"
						+orders[index].appname+"</td><td>"+time+"</td><td>"+orders[index].total+"</td><td>"+status+"</td>"
						if(status=='市场人员未审核'){
							tbody = tbody + "<td><a href=\"#\" onclick=\"orderCheck('"+orders[index].id+"', true);\"><i class=\"glyphicon glyphicon-eye-open\"></i> 审核</a></td></tr>";
						}else{
							tbody = tbody + "<td></td></tr>";
						}
					});
				}
				
				$("#dict tbody").html(tbody);
				var ul = "";
				if (pageNum>1) {
					ul = ul + "<li><a href=\"javascript:getOrderPage(1)\">首页</a></li>";
				}
				if (pageNum-1>1) {
					ul = ul + "<li><a href=\"javascript:getOrderPage("+pageNum+"-1)\">上页</a></li>";
				}
				if (pageNum+1<totalPageNum) {
					ul = ul + "<li><a href=\"javascript:getOrderPage("+pageNum+"+1)\">下页</a></li>";
				}
				if (pageNum<totalPageNum) {
					ul = ul + "<li><a href=\"javascript:getOrderPage("+totalPageNum+")\">尾页</a></li>";
				}
				$("#pagingBar").html(ul);
				var pageNumLabel = "";
				if (orders.length > 0) {
					pageNumLabel = "<label>第"+pageNum+"页/共"+totalPageNum+"页</label>";
					$("#pageNumLabel").html(pageNumLabel);
				}else{
					$("#pageNumLabel").html("");
				}
			}
		});
	}
	 function orderCheck(id, flag){
		var td = $(this).parent().prevAll();
		$.get("${ctx}/main/findCompanyAndServicesByOrderId?orderId="+id,function(data, status){
			if("success" == status){
				$("#dictUpdate").modal('toggle');
				 data.id = id;
				 data.flag = flag;
				 var myTemplate = $.templates("#peopleTmpl");
				 var html = myTemplate.render(data);
				 $("#peopleList").html(html);
				 if(flag){
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
					 $("#otherdiscount").hide();
					 $("#myModal_dialog_ok").click(function(){
						 var checkbox = $("#optionsRadios4");
						 var opt4 = $("input[name='discount']:checked").val();
						 if(opt4 == "-1"){
							 if(!$("#otherdiscount").val()){
								 alert("请填写折扣！");
								 return false;
							 }else{
								 if($("#otherdiscount").val()>100 || $("#otherdiscount").val()<10){
									 alert("请填写正常折扣！");
									 return false;
								 }
								 checkbox.val($("#otherdiscount").val());
							 }
						 }
						 $("#myform1").submit();
					 });
				}else{
					$("#myModal_dialog_ok").hide();
				}
			}
		});
		 
	 }
//用json传递Date类型数据，格式化需要的方法
 Date.prototype.Format = function(fmt) {  
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
	 function search(){
		 //alert(111);
		 $('#mainForm').submit();	
		var status=$("#status").val();
		alert(status);
	 }  
	   
// $("#menu_ordermanager").addClass("active"); 
 
	</script>
	<script id="peopleTmpl" type="text/x-jsrender">
<form class="form-horizontal" id="myform1" role="form" action="${ctx}/main/checkOrder">
<h5>&nbsp;&nbsp;&nbsp;&nbsp;订单编号:{{:id}}</h5><input type="hidden" name="id" value="{{:id}}"/>
<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="panel-heading" id="companyinfo">
      <h4 class="panel-title">单位信息 </h4>
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
<div class="form-group">
  <label for="lastname" class="col-sm-2 control-label">折扣</label>
    <div class="col-sm-10" id="discount_div">
          <label class="checkbox-inline">
      		<input type="radio" name="discount" id="optionsRadios" 
         value="95" checked> 95折
   		</label>
   		<label class="checkbox-inline">
      		<input type="radio" name="discount" id="optionsRadios1" 
         value="90"> 9折
   		</label>
		<label class="checkbox-inline">
      		<input type="radio" name="discount" id="optionsRadios2" 
         value="85"> 85折
   		</label>
		<label class="checkbox-inline">
      		<input type="radio" name="discount" id="optionsRadios3" 
         value="80"> 8折
   		</label>
		<label class="checkbox-inline">
      		<input type="radio" name="discount" id="optionsRadios4" 
         value="-1"> 其他
   		</label>
		<label class="checkbox-inline">
      		<input type="number" name="otherdiscount" id="otherdiscount" min="10" max="100" length="10">
   		</label>
    </div>
</div>
<div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">有效期至</label>
         <div class="col-sm-10">
               	<input type="text" id="expired" name="expired" readonly>
         </div>
      </div>
   </div>
</from>
</script>

</body>
</html>