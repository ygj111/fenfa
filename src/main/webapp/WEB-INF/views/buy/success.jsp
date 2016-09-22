<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
<title>购买产品</title>
<link href="${ctx}/assets/css/success.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="container">
<div id="header">
	<div class="h_inner">
    	<div class="hlt">
        <a href="#"><img src="${ctx}/assets/images/h1.png" /></a>
        </div>
        <div class="hrt">
        <span>您好！尊敬的用户</span>
        <a href="#"><img src="${ctx}/assets/images/black.png" /><span>退出</span></a>
        </div>
    </div>
</div>


 
<div id="main">
	<div class="main_inner">
        <div class="main_top">
            <h2>订单提交成功！</h2>
            <p>您的订单已进入审核程序，我们将尽快通知您结果！</p>
        </div>
        <div class="m_content">
            <ul>
                <li class="konge">订单号：<span>776655488844</span></li>
                <li class="konge">金额：<span>¥2299.00</span></li>
                <li class="konge">支付方式：<span>货到付款</span></li>
                <li>交货方式：<span>上门安装</span></li>
            </ul>
        </div>
        <form name="bug" action="#">
             <button class="btn shopping-btn" type="submit">继续购物</button>
        </form>
    </div>
</div>


<div id="footer">
	<p>技术支持：广州粤建三和软件股份有限公司</p>
</div>
</div>
</body>
</html>
