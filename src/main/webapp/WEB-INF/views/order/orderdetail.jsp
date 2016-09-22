<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<head>
<link href="${ctx}/assets/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>购买产品</title>
<link href="${ctx}/assets/css/management.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/assets/jquery/1.11.3/jquery.min.js"></script>
</head>

<body>
<div id="container">
<div id="header">
	<div class="h_inner">
    	<div class="hlt">
        <a href="#"><img src="${ctx}/assets/images/h1.png" /></a>
        </div>
        <div class="hrt">
        <span>您好！尊敬的${sessionScope.loginUser.username}</span>
        <a href="${ctx}/login"><img src="${ctx}/assets/images/black.png" /><span>退出</span></a>
        </div>
    </div>
</div>


 
<div id="main">
    <div class="main_inner">
    	<div class="main_top">
        	<img src="${ctx}/assets/images/dingdan_icon.png" />
            <h3>订单详情页面</h3>
        </div>
       <form id="orderForm" action="" method="get">
        <div class="main_bot">
        	<div class="m_b_top" >
            	<!-- <div class="one" >序号</div> -->
            	<div class="two">订单号</div>
            	<div class="three">商品名称</div>
            	<div class="four">销售版本</div> 
            	<div class="five">产品服务</div>
            	<div class="six">总金额(元)</div>
            	<div class="seven">购买日期</div>
            	<div class="eight">状态</div>
            	<div class="nine">客户标识</div>
            	<div class="ten">企业类型</div>
            </div>
           
        	  <div class="m_b_cont">
            	<%-- <div class="one"><p>${status.index + 1}</p></div> --%>
            	<div class="two"><p>${order.id}</p></div>
            	<div class="three">
                	<img src="${ctx}/assets/images/dingdan_pic.png" />
                    <ul>
                    	<li class="li_top">${order.appname}</li>
                    	<li>（<span>一年系统维护服务</span>）</li>
                    </ul>
                </div>
            	<div class="four"><p>${appVersion}</p></div>
            	<div class="five"><p>${service}</p></div>
            	<div class="six"><p>${order.total}</p></div>
            	<div class="seven">${order.orderDate}</div>
            	<div class="eight"><p>${order.status}</p></div>
                <div class="nine"><p>${order.customerId }</p></div>
                 <div class="ten"><p>${order.orgName }</p></div>
            </div>
              
                 <div class="btnSpace">
                 <button type="button" class="btn " style="margin-top: 20px;width:50px; height:40px;background-color:#2cb1f4;color:#FFF"
                        onclick="cancel()">返回
                 </button>
                 <%-- <button type="button" style="width:100px; height:40px; color:#FFF; background-color:#2cb1f4;" id="btn_save" class="btn " onclick="submitSsOrder('${order.id}', '${order.status}')">
                    	订单提交
                 </button> --%>
               </div>
               
            </div>
            <input type="hidden" name="id" value="${order.id }"></input>
        </form>	
        </div>
    </div>
</div>

	<script type="text/javascript">
	
    /*  function submitSsOrder(id,status){
		   
		    if("未递交"== status ){
				if(confirm("你确定订单提交？")){

					   $('#orderForm').attr('action', '${ctx}/main/submitSsOrder?id='+id);
					   $('#orderForm').submit();
					alert("订单提交成功！");	
				}else{
					return false; 
					
				 }
		    }else{
		    	alert("订单处于"+status+"状态,不能提交！")
		    	return false; 
				
		    }	 
   } */
     function cancel(){
		    //alert(111); 
			var result=confirm("你确定返回？");
			if(result){
			$('#orderForm').attr('action', '${ctx}/main/showSsOrder');
			$('#orderForm').submit();
		}else{
			return false; 
		}	
     }	
    
	</script>
<div id="footer">
	<p>技术支持：广州粤建三和软件股份有限公司</p>
</div>
</body>
</html>
