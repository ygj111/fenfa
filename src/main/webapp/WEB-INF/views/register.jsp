<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>注册</title>
<link href="${ctx}/assets/css/index.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/assets/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" />
<style>
/*.register-panel{margin-top: 80px;}*/
.form-horizontal .col-md-5, .col-md-4, .col-md-3, .col-md-10 {
	padding: 0;
}

.form-horizontal .form-group {
	padding-right: 15px;
}

.error {
	color: red;
	border-color: red;
}

label.error {
	position: absolute;
	margin-left: 10px;
}

#registerForm label {
	text-align: right;
}

.control-label {
	padding-left: 0px;
}
</style>
<script src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
<script src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/js/json2.min.js"></script>
<script src="${ctx}/assets/jquery/jquery.validate.min.js"></script>
<script type="text/javascript">
	var ctx = '${ctx}';
	$(function() {
		$('#delegationModel').toggle($('#type').val() == '检测单位');
		$('#type').change(function() {
			$('#delegationModel').toggle($('#type').val() == '检测单位');
		});
		$('#firstRegions').change(
				function() {
					$('#secondRegions').empty();
					$('#thirdRegions').empty();
					var firstRegion = $('#firstRegions').val();
					$.ajax({
						type : 'post',
						data : {
							firstRegion : firstRegion
						},
						dataType : 'json',
						url : ctx + '/main/getSecondRegions',
						success : function(data) {
							$('#secondRegions').append('<option>请选择</option>');
							for (var i = 0; i < data.length; i++) {
								$('#secondRegions').append(
										'<option value=\''+data[i]+'\'>'
												+ data[i] + '</option>');
							}
						}
					});
				});
		$('#secondRegions').change(
				function() {
					$('#thirdRegions').empty();
					var firstRegion = $('#firstRegions').val();
					var secondRegion = $('#secondRegions').val();
					$.ajax({
						type : 'post',
						data : {
							firstRegion : firstRegion,
							secondRegion : secondRegion
						},
						dataType : 'json',
						url : ctx + '/main/getThirdRegions',
						success : function(data) {
							$('#thirdRegions').append('<option>请选择</option>');
							for (var i = 0; i < data.length; i++) {
								$('#thirdRegions').append(
										'<option value=\''+data[i]+'\'>'
												+ data[i] + '</option>');
							}
						}
					});
				});
	});

	function register() {
		$.ajax({
			type : 'post',
			data : $('#registerForm').serialize(),
			dataType : 'json',
			url : ctx + '/main/register',
			success : function(data) {
				if (data == 1) {
					alert('注册成功!');
					window.location.href = ctx + '/login';
				} else {
					alert('注册失败!');
				}
			}
		});
	}
</script>

<style>
/*.register-panel{margin-top: 80px;}*/
.form-horizontal .col-md-5, .col-md-4, .col-md-3, .col-md-10 {
	padding: 0;
}

.form-horizontal .form-group {
	padding-right: 15px;
}
.star {
    margin-left: 5px;
    font-size: larger;
}
</style>
</head>
<body>
	<div id="header">
		<div class="h_inner">
			<a href="#"><img src="${ctx}/assets/images/logo.png" /></a>
			<p>
				全国营销与客服热线：<br /> <a href="#">400-700-0272</a>
			</p>
		</div>
	</div>
	<div id="main">
		<div class="m_inner">
			<div class="content2">
				<div class="col-lg-10 col-lg-offset-1">
					<form id="registerForm" class="form-horizontal"
						action="${ctx}/main/register" method="post">
						<div class="register-panel panel panel-primary">
							<div class="panel-body">

								<div class="form-group">
									<label class="col-md-2 control-label">单位类型<span class="text-danger star">*</span></label>
									<div class="col-md-3">
										<select class="form-control" id="type" name="company.type">
											<c:forEach items="${orgType}" var="orgType">
												<option value="${orgType}">${orgType}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">所有行政区域<span class="text-danger star">*</span></label>
									<div class="col-md-3">
										<select class="form-control" id="firstRegions"
											name="company.firstRegion">
											<c:forEach items="${firstRegions}" var="firstRegions">
												<option value="${firstRegions}">${firstRegions}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-md-3">
										<select class="form-control" id="secondRegions"
											name="company.secondRegion">
										</select>
									</div>
									<div class="col-md-4">
										<select class="form-control" id="thirdRegions"
											name="company.thirdRegion">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位名称<span class="text-danger star">*</span></label>
									<div class="col-md-10">
										<input class="form-control" type="text" name="company.name" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位地址<span class="text-danger star">*</span></label>
									<div class="col-md-10">
										<input class="form-control" type="text" name="company.address" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位电话<span class="text-danger star">*</span></label>
									<div class="col-md-4">
										<input class="form-control" type="text" name="company.phone" />
									</div>
									<label class="col-md-4 control-label" style="text-align:left;">(请填写真实电话号码)</label>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位负责人<span class="text-danger star">*</span></label>
									<div class="col-md-4">
										<input class="form-control" type="text" name="company.header" />
									</div>
									<label class="col-md-2 control-label">单位负责人手机<span class="text-danger star">*</span></label>
									<div class="col-md-4">
										<input class="form-control" type="text" name="company.headerPhone" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位负责人邮箱<span class="text-danger star">*</span></label>
									<div class="col-md-10">
										<input class="form-control" type="text" name="company.headerEmail" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位管理员账号<span class="text-danger star">*</span></label>
									<div class="col-md-4">
										<input class="form-control" type="text" name="username" />
									</div>
									<!-- 							<div>(只能输入字母或数字，并长度为6-20位)</div> -->
									<label class="col-md-2 control-label">密码<span class="text-danger star">*</span></label>
									<div class="col-md-4">
										<input class="form-control" type="password" name="password" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位管理员姓名<span class="text-danger star">*</span></label>
									<div class="col-md-4">
										<input class="form-control" type="text" name="displayName" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位管理员邮箱<span class="text-danger star">*</span></label>
									<div class="col-md-10">
										<input class="form-control" type="text" name="email" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">单位管理员手机<span class="text-danger star">*</span></label>
									<div class="col-md-4">
										<input class="form-control" type="text" name="phone" />
									</div>
									<div id="delegationModel">
										<label class="col-md-2 control-label">电子委托模式</label>
										<div class="col-md-4">
											<select class="form-control" name="company.delegationModel">
												<option value="检测机构模式">检测机构模式</option>
												<option value="见证管理模式">见证管理模式</option>
											</select>
										</div>
									</div>
								</div>

							</div>
							<hr style="margin-top: 0px; margin-bottom: 10px;" />
							<div style="text-align: center; margin-bottom: 8px;">
								<button class="btn btn-success" id="registerBtn">确定</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-success" type="reset">重置</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="btn btn-success" onclick="javascript:window.location.href='${ctx}/login'">返回</a>
							</div>
						</div>
					</form>
				</div>
			</div>
			<script type="text/javascript" src="${ctx}/assets/js/placeholder.js"></script>
		</div>
	</div>


	<div id="footer">
		<p style="margin-bottom: 0px;">技术支持：广州粤建三和软件股份有限公司</p>
	</div>

	<script>
		$.validator.setDefaults({
			submitHandler : function() {
				register();
			}
		});
		 jQuery.validator.addMethod("sameUser",function(value, element){  
             var returnVal = false;  
             var username = $("input[name='username']").val(); 
             var succ ="";
             var firstRegion = $('#firstRegions').val();
         	$.ajax({
				type : "post",
				url : ctx+'/main/getUsername',
				data :  {username:value}, 
				dataType : "json",
				async:false,
				success : function(data) {
					//产品不存在
					if(data == 1){
						returnVal = true;
					}
				}
			});
             return returnVal;  
        },"用户已存在！");  
		$.validator.addMethod("chineseAndEnglish", function (value, element) {
			var mail =  /^[\u4e00-\u9fa5]|[a-zA-Z]+.*$/;
			return this.optional(element) || (mail.test(value));
		}, "首字母为中文或字母");
		$(function() {
			
			$("#registerForm").validate({
				rules : {
					"company.name" : {
						required : true,
						chineseAndEnglish : true
					},
					"company.address" : {
						required : true,
						chineseAndEnglish : true
					},
					"company.headerPhone" : {
						required : true,
						digits : true
					},
					"company.phone" : {
						required : true,
						digits : true
					},
					"company.header" : {
						required : true,
						chineseAndEnglish : true
					},
					"company.headerEmail": {
						required : true,
						email : true
					},
					username : {
						required : true,
						minlength : 6,
						maxlength : 20,
						sameUser:true,
					},
					password : {
						required : true,
						minlength : 6
					},
					displayName : {
						required : true,
						chineseAndEnglish : true
					},
					email : {
						required : true,
						email : true
					},
					phone : {
						required : true,
						minlength : 11,
						maxlength : 11,
						digits : true
					},
				},
				messages : {
					"company.name" : {
						required : "必填",
						chineseAndEnglish : "首字母为中文或字母"
					},
					"company.address" : {
						required : "必填",
						chineseAndEnglish : "首字母为中文或字母"
					},
					"company.headerPhone" : {
						required : "必填",
						digits : "请输入数字"
					},
					"company.phone" : {
						required : "必填",
						digits : "请输入数字"
					},
					"company.header" : {
						required : "必填",
						chineseAndEnglish : "首字母为中文或字母"
					},
					"company.headerEmail" : {
						required : "必填",
						email : "请输入正确的邮箱"
					},
					username : {
						required : "必填，只能输入字母或数字",
						minlength : "长度不能小于 6位",
						maxlength : "长度不能大于 20位"
					},
					password : {
						required : "请输入密码",
						minlength : "密码长度不能小于 6位"
					},
					displayName : {
						required : "必填",
						chineseAndEnglish : "首字母为中文或字母"
					},
					email : {
						required : "必填",
						email : "请输入正确的邮箱"
					},
					phone : {
						required : "必填",
						digits : "请输入正确电话号码",
						minlength : "请输入正确电话号码",
						maxlength : "请输入正确电话号码"
					},
				}
			});
		})
	</script>
</body>
</html>
