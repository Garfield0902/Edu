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
<title>首页</title>
</head>
<body>
	<div id="container" class="effect mainnav-lg home">
		<jsp:include page="header.jsp"></jsp:include>
		<div>
			<div id="content-container">
				<!--面包屑导航-->
				<div id="page-title"></div>
				<div id="page-content">
					<div class="row">
						<div class="col-md-3">
							<div class="panel panel-default tab_panel index-panel">
								<div class="panel-heading">
									<div class="row">
										<h4 class="panel-title">我的信息</h4>
									</div>
								</div>
								<div class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row">
											<div>
												<label><span>姓名：</span><span id="xm"></span></label><br> <label><span>职工号：</span><span id="zgh"></span></label></label><br>
												<label><span>部门：</span><span id="bm"></span></label><br> <label><span>职务：</span><span id="zw"></span></label></label><br>
												<label><span>上次登录时间：</span><span id="sclogintime"></span></label></label><br>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default tab_panel index-panel">
								<div class="panel-heading">
									<div class="row">
										<h4 class="panel-title">日历</h4>
									</div>
								</div>
								<div class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row">
											<div id="datetimepicker"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="panel panel-default tab_panel index-panel">
								<div class="panel-heading">
									<div class="row">
										<h4 class="panel-title">培训信息</h4>
										<a href="<%= basePath %>trainingInfo/trainingInfoPage.do" class="more-link">查看更多</a>
									</div>
								</div>
								<div class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row">
											<table
												class="table table-striped table-bordered table-hover table-condensed">
												<tr>
													<th>活动主题</th>
													<th>活动时间</th>
													<th>名额</th>
													<th>状态</th>
												</tr>
												<tbody id="trainingInfo"></tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default tab_panel index-panel">
								<div class="panel-heading">
									<div class="row">
										<h4 class="panel-title">我的报名</h4>
										<a href="<%= basePath %>trainingInfo/trainingInfoPage.do?tab=my" class="more-link">查看更多</a>
									</div>
								</div>
								<div class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row">
											<table
												class="table table-striped table-bordered table-hover table-condensed">
												<tr>
													<th>活动主题</th>
													<th>活动时间</th>
													<th>活动地点</th>
												</tr>
												<tbody id="bmInfo"></tbody>
											</table>
										</div>
									</div>
								</div>
		
							</div>
							<div class="panel panel-default tab_panel index-panel">
								<div class="panel-heading">
									<div class="row">
										<h4 class="panel-title">培训档案</h4>
										<a href="<%= basePath %>bmpjxx/archives.do" class="more-link">查看更多</a>
									</div>
								</div>
								<div class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row">
											<table
												class="table table-striped table-bordered table-hover table-condensed">
												<tr>
													<th>活动主题</th>
													<th>活动时间</th>
													<th>学分</th>
												</tr>
												<tbody id="pxdaInfo"></tbody>
											</table>
										</div>
									</div>
								</div>
		
							</div>
						</div>
						<div class="col-md-3">
							<div class="panel panel-default tab_panel index-panel">
								<div class="panel-heading">
									<div class="row">
										<h4 class="panel-title">联系我们</h4>
									</div>
								</div>
								<div class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row">
											<div>
												<label><span>地址：</span>wq</label><br> <label><span>电话：</span></label><br>
												<label><span>传真：</span></label><br> <label><span>邮箱：</span></label><br>
												<label><span>QQ群：</span></label><br> <label><span>微信：</span></label><br>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default tab_panel index-panel">
								<div class="panel-heading">
									<div class="row">
										<h4 class="panel-title">通知公告</h4>
										<a href="<%= basePath %>announcement/announcementPage.do" class="more-link">查看更多</a>
									</div>
								</div>
								<div class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row">
											<table
												class="table table-striped table-bordered table-hover table-condensed">
												<tr>
													<th>主题</th>
													<th>时间</th>
												</tr>
												<tbody id="tzggInfo"></tbody>
											</table>
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
			<jsp:include page="footer.jsp"></jsp:include>
			<script type="text/javascript" src="<%=basePath%>/resources/js/home.js"></script>
		</div>
	</div>
</body>
</html>
