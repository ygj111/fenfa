<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<nav class="navbar">
				<!--标题-->
				<div class="navbar-header">
					<a class="navbar-brand" href="">欢迎来到分发管理系统</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
				<!--消息提示-->
					<!-- <li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="fa fa-bell fa-fw"></i>
							<i class="badge">2</i>
							<i class="fa fa-caret-down"></i>
						</a>
						<ul class="dropdown-menu dropdown-message">
							<li><a href="#"><i class="fa fa-comment fa-fw"></i> Jump Over The Lazy Dog.</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-comment fa-fw"></i>  The Lazy Dog.</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-comment fa-fw"></i> Brown Fox Jump  Dog.</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-comment fa-fw"></i> The Quick Brown Fox Jump Over The Lazy Dog.</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="fa fa-comment fa-fw"></i> The Quick Brown Fox Jump .</a></li>
							<li class="divider"></li>
							<li><a class="text-center" href="#"><strong>View More>> </strong><i class="fa fa-angle-right"></i></a></li>
						</ul>
					</li> -->
				<!--个人信息-->
					<li class="dropdown ">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="fa fa-user fa-fw"></i>
							<i class="fa fa-caret-down"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<!-- <li><a href="#"><i class="fa fa-user fa-fw"></i> Edit Profile</a></li>
							<li><a href="#"><i class="fa fa-gear fa-fw"></i> setting</a></li> 
							<li class="divider"></li>-->
							<li><a href="${ctx}/login"><i class="fa fa-sign-out fa-fw"></i> 退出</a></li>
						</ul>
					</li>
				</ul>
			</nav>