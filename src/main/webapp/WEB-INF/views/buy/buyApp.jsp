<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
<title>购买产品</title>
<link href="${ctx}/assets/css/goumai.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/assets/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jstree-3.3.0/themes/default/style.min.css" />
<link rel="stylesheet" href="${ctx}/assets/fontawesome/css/font-awesome.min.css" />
<script type="text/javascript" src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jstree-3.3.0/jstree.min.js"></script>
<script type="text/javascript">
	var ctx='${ctx}';
	var appname='${ssAppBean.appname}';
</script>
</head>

<body>
<div id="header">
	<div class="h_inner">
    	<div class="hlt">
        <a href="#"><img src="${ctx}/assets/images/h1.png" /></a>
        </div>
        <div class="hrt">
        <span><a href="${ctx}/main/showSsOrder">我的订单</a></span>
        <span>您好！尊敬的${sessionScope.loginUser.username}</span>
        <a href="${ctx}/login"><img src="${ctx}/assets/images/black.png" /><span>退出</span></a>
        </div>
    </div>
</div>


<div id="position">
<!-- 	<div class="p_inner"> -->
<%--     <img src="${ctx}/assets/images/position.png" /> --%>
<!--     <p>工程质量管理  >  建设工程质量监督管理系统软件  >  建设工程质量检测监管信息系统</p> -->
<!--     </div> -->
</div>


<div id="main">
	<div class="m_inner">
        <div class="main_left">
            <img src="${ctx}/assets/images/main_cp.png" />
        </div>
        <div class="main_right">
        	<div class="m_r_top">
            	<h2>${ssAppBean.appname}</h2>
                <p>${ssAppBean.description}</p>
            </div>
            <div class="m_r_xz">
                <div class="xz_top">
                    <ul>
                        <li>价格<span class="b_red" id="totalPrice">0</span></li>
                        <li><span class="b_black"></span></li>
                    </ul>
                    <ul class="nav nav-pills">
                    	<c:forEach items="${versionList}" var="version">
							<li>
								<button class="btn btn-white" onclick="chooseVersion(this,'${version.appVersion}')">${version.appVersion}</button>
<%-- 								<a onclick="initTree('${version.appVersion}')" data-toggle="modal" data-target="#myModal">更多服务</a> --%>
							</li>
						</c:forEach>
					</ul>
					<input type="hidden" id="appVersionTemp"/>
					<div id="moreService" style="margin-top:10px;display:none"><a onclick="initTree()" data-toggle="modal" data-target="#myModal">更多服务</a></div>
                </div>   
<!--                 <div class="xz_bot"> -->
<!--                 	<p>附加服务:</p> -->
<!--                     <div class="xz_red"><a href="#">维护1年  ¥300</a></div> -->
<!--                 </div>  -->
            </div>
            <div class="m_r_bot">
            	<form id="buyForm" method="post">
	            	 <input type="hidden" name="appname" id="appname">
<!-- 	            	 <input type="hidden" name="totalPrice" id="totalPriceVal"> -->
	            	 <input type="hidden" name="serviceIds" id="serviceIds">
            	</form>
	            	 <button id="buyBtn" class="btn buy-btn">立即购买</button>
	            	 <button id="backBtn" class="btn back-btn" onclick="history.back();">返回</button>
            </div>
        </div>
<!--         <div class="main_cont"> -->
<!--         	<div class="m_c_left">商品详情</div> -->
<!--         </div> -->
<!--         <div class="main_bot"> -->
<!--         	<div class="m_b_top"> -->
<!--                 <h5>商品介绍</h5> -->
<!--             </div> -->
<!--                 <div class="m_b_bot"> -->
<!--                 <h4>建设工程质量检测监管信息系统</h4> -->
<!--                 <p>建设工程质量检测监管信息系统主要完成检测机构资质、检测人员、检测设备的备案，信用评价落实；完成对检测机构在线情况、上传数据及时性和数据修改等行为的监管；实现见证取样、工程备案以及现场检测计划的申报；完成检测数据的汇总和数据分析工作。对检测结果，尤其是不合格数据进行跟踪处理，无缝对接混凝土质量跟踪系统和工程质量监督管理系统，更好地为政府行政主管部门、检测监管机构、工程质量监督机构、工程建设单位、工程监理企业、施工单位、检测机构以及社会公众服务。建设工程质量检测监管信息系统结构图如下：</p> -->
<%--                 <img src="${ctx}/assets/images/main_pic.png" /> --%>
<!--                 <p>此外，建设工程质量检测监管信息系统与工程质量检测机构管理系统相结合，采用B/S架构，实现了对工程质量检测数据和报告的在线实时监管，具有自动采集、实时上传、自动识别、多种对比、分类归档等功能。这样有助于主管部门提高监管效率，规范建设工程检测市场，提高检测结果的科学性、公正性和准确性，为工程建筑质量监督提供有力支撑。</p> -->
<!--             </div> -->
<!--         </div> -->
    </div>
</div>

<div id="footer">
	<p>技术支持：广州粤建三和软件股份有限公司</p>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				                  &times;
				            </button>
						<h4 class="modal-title" id="myModalLabel">
				              	 选购你所需要的服务
				            </h4>
					</div>
					<div class="modal-body ">
						<div class="row">
						<div id="tree" class="col-lg-6"></div>
						<div id="tree2" class="col-lg-6"></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal" id="treeOKBtn">确认</button>
					</div>
				</div>
			</div>
		</div>
		<script>
			var ids;
			var appVersionTemp;
			function chooseVersion(obj,appVersion){
				$('#moreService').css('display','block');
				appVersionTemp = appVersion;
				$(obj).parent().siblings().children('button').removeClass('btn-red');
				$(obj).addClass('btn-red');
				ids = new Array();
				$.ajax({
					 type:'post',
					 url:ctx+'/buy/getAllServices',
					 async: false,
					 data:{appname:appname,
						 appVersion:appVersion},
					 dataType:'json',
					 success:function(data){
						 for(var i=0;i<data.length;i++){
							 if(data[i].parent!='#'&&data[i].state.selected)ids.push(data[i].id);
						 }
					 }
				 });
				$.ajax({
					 type:'post',
					 url:ctx+'/buy/getTotalPriceByServices',
					 data:{ids:ids},
					 dataType:'json',
					 success:function(data){
// 						 $('#totalPriceVal').val(data);
						 $('#totalPrice').text(data);
					 }
				 });
			}
			
			function initTree(){
				var appVersion = appVersionTemp;
				var object = {
						'core' : {
							  'data' : {
								    'url' : ctx + '/buy/getAllServices',
								    'data' : {
								    	appname:appname,
								    	appVersion:appVersion,
								    	serviceType:0
								    },
								    'type':'post'
							    },
						},
						"checkbox" : {
	      					"keep_selected_style" : false
	    				},
	    				"plugins" : [ "checkbox"]
					};
				var object2 = {
						'core' : {
							  'data' : {
								    'url' : ctx + '/buy/getAllServices',
								    'data' : {
								    	appname:appname,
								    	appVersion:appVersion,
								    	serviceType:1
								    },
								    'type':'post'
							    },
						},
						"checkbox" : {
	      					"keep_selected_style" : false
	    				},
	    				"plugins" : [ "checkbox"]
					};
				$('#tree').jstree('destroy');
				$('#tree2').jstree('destroy');
				$('#tree').jstree(object);
				$('#tree2').jstree(object2);
			}
			//去除数组中某个元素
			function removeByValue(arr, val) {
				var newArr = new Array();
				  for(var i=0; i<arr.length; i++) {
				    if(arr[i] != val) {
					  newArr.push(arr[i]);
				    }
				  }
				  return newArr;
			}
			$(function() {
				$('#buyBtn').click(function(){
					if($('#totalPrice').text()==0){
						alert('请选择购买的产品');
						return false;
					}
					$('#buyForm').attr('action',ctx+'/buy/buy');
					$('#appname').val(appname);
// 					$('#totalPrice').val(totalPrice);
					$('#serviceIds').val(ids);
					$('#buyForm').submit();
				});
				$('#treeOKBtn').click(function(){
					 var ids1 = $('#tree').jstree(true).get_selected();
					 var ids2 = $('#tree2').jstree(true).get_selected();
					 ids = ids1.concat(ids2);
					 console.info('ids.start:'+ids);
					 ids = removeByValue(ids,0);
					 console.info('ids.end:'+ids);
					 $.ajax({
						 type:'post',
						 url:ctx+'/buy/getTotalPriceByServices',
						 data:{ids:ids},
						 dataType:'json',
						 success:function(data){
							 $('#totalPriceVal').val(data);
							 $('#totalPrice').text(data);
						 }
					 });
				}); 
			})
		</script>
</body>
</html>
