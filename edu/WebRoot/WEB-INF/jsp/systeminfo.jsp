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
<title>系统信息</title>
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
								<span>系统信息</span>
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
														<td class="text-right" width="20%">系统运行时间</td><td class="text-left"><span id="xysj"></span></td>
													</tr>
													<tr>
														<td class="text-right">在线人数</td><td class="text-left"><span id="zxrs"></span></td>
													</tr>
													<tr>
														<td class="text-right">处理器个数</td><td class="text-left"><span id="clqhs"></span></td>
													</tr>
													<tr>
														<td class="text-right">可分配内存</td><td class="text-left"><span id="kfpnc"></span></td>
													</tr>
													<tr>
														<td class="text-right">操作系统</td><td class="text-left"><span id="czxt"></span></td>
													</tr>
													<tr>
														<td class="text-right">JAVA版本</td><td class="text-left"><span id="javabb"></span></td>
													</tr>
													<tr>
														<td class="text-right">数据库版本</td><td class="text-left"><span id="sjkbb"></span></td>
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
	<script type="text/javascript" src="<%=basePath%>/resources/js/teachermanage.js"></script>
</body>
</html>
