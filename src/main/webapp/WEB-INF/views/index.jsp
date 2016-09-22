<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>分发管理</title>
    <link href="${ctx}/assets/css/index.css" rel="stylesheet" type="text/css">
<%--     <link href="${ctx}/assets/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" /> --%>
<%-- 	<script src="${ctx}/assets/bootstrap/3.3.5/js/bootstrap.min.js"></script> --%>
    <script src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery.validate.min.js" ></script>
    <style>
    	.error{
    		color:red;
    	}
    </style>
    <script type="text/javascript">
    	var ctx='${ctx}';
    	var result ='${result}';
	    $(function(){
	    	if(result == "fail"){
	    		$(".errorDiv").attr("style","display:block;");
	    		$(".zc").attr("style","padding-top:20px");
	    	}
	        $('#kaptchaImage').click(function () { 
	            $(this).attr('src', 'captcha-image.jpg?' + Math.floor(Math.random()*100) ); 
	        })
	         jQuery.validator.addMethod("sameUser",function(value, element){  
             var returnVal = false;  
             var username = $("input[username]").val(); 
             var succ ="";
         	$.ajax({
				type : "POST",
				url : "${ctx}/main/getUsername",
				data :  {username:value}, 
				dataType : "json",
				async:false,
				success : function(data) {
					//用户存在
					if(data != 1){
						returnVal = true;
					}
				}
				
			});
             return returnVal;  
        },"用户不存在！");  
	        $("#loginForm").validate( {
				rules: {
					username: "required",
					password: "required",
					verifyCode: "required",
					username: {
						required: true,
						minlength: 5,
						sameUser:true
					},
					password: {
						required: true,
						minlength: 6
					}
				},
				messages: {
					username: "请输入用户",
					password: "请输入密码",
					verifyCode: "请输入验证码",
					username: {
						required: "请输入您的用户名",
						minlength: "用户名不能小于5个字符"
					},
					password: {
						required: "请输入密码",
						minlength: "密码长度不能小于6位"
					}
				}
			} );
	    });
	    
	    function  changeVerifyCode(){
	        var verifyCodeValue = $("#verifyCode").val();
	           if(verifyCodeValue.replace(/\s/g,"") == "") {
	               alert("请输入验证码");
	           }else {
	               //异步检查验证码是否输入正确
	    
	               var verifyUrl = "${pageContext.request.contextPath}/checkVerificationCode";
	               $.ajax({
	                   type:"POST",
	                   url:verifyUrl,
	                   data:{"verifyCode":verifyCodeValue},
	                   success:function(data){
	                       if(data==false) {
	                    	   alert("请输入正确的验证码！");
	                       }
	                   }
	               });
	           }
	       }
	    
	    function showRegisterWindow(){
	    	window.location.href=ctx+'/main/toRegister';
	    }
    </script>
</head>

<body>
<div id="header">
	<div class="h_inner">
    	<a href="#"><img src="${ctx}/assets/images/logo.png" /></a>
        <p>全国营销与客服热线：<br /><a href="#">400-700-0272</a></p>
    </div>
</div>

<!-- 注册模态框 -->
<!-- 	<div class="modal fade" id="register_modal" tabindex="-1" role="dialog" -->
<!-- 		aria-labelledby="registerModalLabel" aria-hidden="true"> -->
<!-- 		<div class="modal-dialog"> -->
<!-- 			<div class="modal-content"> -->
<!-- 				<div class="modal-header"> -->
<!-- 					<button type="button" class="close" data-dismiss="modal" -->
<!-- 						aria-hidden="true">×</button> -->
<!-- 					<h4 class="modal-title" id="registerModalLabel">注册</h4> -->

<!-- 				</div> -->
<!-- 				<div class="modal-body"> -->
<!-- 					<form id="package_form" role="form" method="post" action=""> -->
<!-- 						<input id="id" name="id" type="hidden" class="form-control" /> -->
<!-- 						<div class="form-group input-group"> -->
<!-- 							<span for="" class="input-group-addon">单位类型&nbsp;&nbsp;&nbsp;</span> -->
<!-- 							<input id="" name="" type="text" -->
<!-- 								class="form-control" /> -->
<!-- 						</div> -->
<!-- 						<div class="form-group input-group"> -->
<!-- 							<span for="" class="input-group-addon">所在行政区域&nbsp;&nbsp;&nbsp;</span> -->
<!-- 							<select -->
<!-- 								id="" name="" class="form-control"> -->
<!-- 								<option value="1"></option> -->
<!-- 								<option value="0"></option> -->
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 						<div class="form-group input-group"> -->
<!-- 							<span for="" class="input-group-addon">单位名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> -->
<!-- 							<input id="" name="" type="text" class="form-control" /> -->
<!-- 						</div> -->
<!-- 						<div class="form-group input-group"> -->
<!-- 							<span for="" class="input-group-addon">单位地址</span> <input -->
<!-- 								id="" name="" type="text" -->
<!-- 								class="form-control" /> -->
<!-- 						</div> -->
<!-- 						<div class="form-group input-group"> -->
<!-- 							<span for="" class="input-group-addon">密码</span>  -->
<!-- 						</div> -->
<!-- 					</form> -->
<!-- 				</div> -->
<!-- 				<div class="modal-footer"> -->
<!-- 					<button id="btn_save" type="button" class="btn btn-primary">保存</button> -->
<!-- 					<button id="btn_cancel" type="button" class="btn btn-default" -->
<!-- 						data-dismiss="modal">取消</button> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

	<div id="main">
	<div class="m_inner">
    <div class="content">
		<div class="title hide">管理登录</div>
		<form id="loginForm" name="loginForm" action="${ctx}/login" method="post">
			<fieldset>
				<div class="input">
					<input class="input_all name" name="username" id="username" placeholder="用户名" type="text" onFocus="this.className='input_all name_now';" onBlur="this.className='input_all name'" maxLength="24" />
				</div>
				<div class="input">
					<input class="input_all password" name="password" id="password" type="password" placeholder="密码" onFocus="this.className='input_all password_now';" onBlur="this.className='input_all password'" maxLength="24" />
				</div>
<!-- 				<div class="checkbox"> -->
<!-- 					<input type="checkbox" name="rememberMe" id="rememberMe" /><label for="remember"><span>记住密码</span><a href="#">忘记密码?</a></label> -->
<!-- 				</div> -->
                <div class="input_bot">
					<input class="input_b yzm" name="verifyCode" id="verifyCode" placeholder="验证码" onFocus="this.className='input_all yzm_now';" onBlur="this.className='input_b yzm'" onchange="changeVerifyCode();" maxLength="24" />
               	<a href="#"><img id="kaptchaImage" src="captcha-image.jpg" width="116" height="49"/></a>
				</div>
				<div class="enter">
					<input class="button hide" name="submit" type="submit" value="登录" />
				</div>
			</fieldset>
		</form>
		<div class="errorDiv" style="display:none;"><p style="color:red;text-align:center;font-size:18px;">账号或密码错误！</p></div>
        <div class="zc" ><p>没有帐号？<a href="javascript:showRegisterWindow();">现在注册</a></p></div>
        </div>
<script type="text/javascript" src="${ctx}/assets/js/placeholder.js"></script>
    </div>
</div>


<div id="footer">
	<p>技术支持：广州粤建三和软件股份有限公司</p>
</div>
</body>
</html>