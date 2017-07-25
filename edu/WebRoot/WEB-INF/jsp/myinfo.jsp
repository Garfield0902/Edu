<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>个人信息</title>
</head>
<body>
	<div id="container" class="effect mainnav-lg">
		<jsp:include page="header.jsp"></jsp:include>

		<div>
			<div id="content-container">
				<!--面包屑导航-->
				<div id="page-title"></div>
				<div id="page-content">
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span>个人信息</span>
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-12">
									<div id="goodsBuy_table_wrapper"
										class="dataTables_wrapper form-inline dt-bootstrap no-footer">
										<div class="row">
											<div class="col-sm-12">
												<table
													class="table table-striped table-bordered table-hover text-center dataTable">
													<tr>
														<td class="text-right" width="20%">教职工号</td><td class="text-left"><span id="jzg"></span></td>
													</tr>
													<tr>
														<td class="text-right">姓名</td><td class="text-left"><span id="name"></span></td>
													</tr>
													<tr>
														<td class="text-right">性别</td><td class="text-left"><span id="sex"></span></td>
													</tr>
													<tr>
														<td class="text-right">职称</td><td class="text-left"><span id="job"></span></td>
													</tr>
													<tr>
														<td class="text-right">学历</td><td class="text-left"><span id="xueli"></span></td>
													</tr>
													<tr>
														<td class="text-right">出身日期</td><td class="text-left"><span id="date"></span></td>
													</tr>
													<tr>
														<td class="text-right">部门信息</td><td class="text-left"><span id="department"></span></td>
													</tr>
													<tr>
														<td class="text-right">单位电话</td><td class="text-left"><span id="phone"></span></td>
													</tr>
													<tr>
														<td class="text-right">个人手机</td><td class="text-left"><span id="mobile"></span></td>
													</tr>
													<tr>
														<td class="text-right">入校时间</td><td class="text-left"><span id="ruxiao"></span></td>
													</tr>
													<tr>
														<td class="text-right">上次任职时间</td><td class="text-left"><span id="prevJob"></span></td>
													</tr>
													<tr>
														<td class="text-right">电子邮件</td><td class="text-left"><span id="email"></span></td>
													</tr>
													
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--左侧垂直导航-->
				<jsp:include page="leftnav.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="resources/js/fuedit.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/js/myinfo.js"></script>
</body>
</html>
