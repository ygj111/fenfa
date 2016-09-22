<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="navbar-default sidebar" role="navigation">
				<div class="navbar-collapse">
					
					<ul class="nav level1" id="side-menu">
						<c:choose>
							<c:when test="${!loginUser.isAdmin}">
								<c:forEach items="${menuList}" var="menu">
										<li>
											<a href="${menu.url}"><i class=" fa fa-files-o fa-fw"></i>${menu.name}</a>
										</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li>
									<a href="${ctx }/main/checkOrderList" id="menu_order"><i class=" fa fa-files-o fa-fw"></i> 订单管理</a>
								</li>
								<li>
									<a href="${ctx}/main/checkOrderList2" id="menu_fwd"><i class=" fa fa-files-o fa-fw"></i> 服务单管理</a>
								</li>
								<li>
									<a href="${ctx}/main/fileList" id="menu_ssfwd"><i class=" fa fa-files-o fa-fw"></i> 实施服务单</a>
								</li>
								<li>
									<a href="${ctx}/main/listApp" id="menu_app"><i class=" fa fa-files-o fa-fw"></i> 产品管理</a>
								</li>
								
								<li>
									<a href="${ctx}/admin/main?menuId=menu_dep" id="menu_dep"><i class=" fa fa-files-o fa-fw"></i> 公司架构</a>
								</li>
								<li>
					                 <a href="${ctx}/admin/main?menuId=menu_user" id="menu_user"><i class=" fa fa-files-o fa-fw"></i> 用户管理</a>
					             </li>
					            <li>
					                 <a href="${ctx}/admin/main?menuId=menu_role" id="menu_role"><i class=" fa fa-files-o fa-fw"></i> 角色管理</a>
					            </li>
					            <li>
					                 <a href="${ctx}/admin/main?menuId=menu_resourcse" id="menu_resourcse"><i class=" fa fa-files-o fa-fw"></i> 资源管理</a>
					            </li>
					            <li>
					                 <a href="${ctx}/admin/main?menuId=menu_menu" id="menu_menu"><i class=" fa fa-files-o fa-fw"></i> 菜单管理</a>
					            </li>
					            <li>
					                 <a href="${ctx}/admin/main?menuId=menu_loginLog" id="menu_loginLog"><i class=" fa fa-files-o fa-fw"></i> 登录日志</a>
					            </li>
					            <li>
					                 <a href="${ctx}/admin/main?menuId=menu_dictionary" id="menu_dictionary"><i class=" fa fa-files-o fa-fw"></i> 数据字典</a>
					            </li>
			            	</c:otherwise>
			              </c:choose>
						</div>
					</div>