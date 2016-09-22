<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
<title>购买产品</title>
<link href="${ctx}/assets/css/fail.css" rel="stylesheet" type="text/css">
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
    	<img src="${ctx}/assets/images/shibai.png" />
    	<p>服务器遇到错误，我们正在积极检修。</p>
        <h2>非常抱歉！订单不存在或已过期，请重新购买。</h2>
        <a href="#">返回上一级</a>
    </div>
</div>


<div id="footer">
	<p>技术支持：广州粤建三和软件股份有限公司</p>
</div>
</div>
</body>
</html>
