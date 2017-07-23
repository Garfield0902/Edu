<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>培训信息</title>
  </head>
  
  <body>
    <div id="container" class="effect mainnav-lg">
        <jsp:include page="header.jsp"></jsp:include>
        <div>
            <div id="content-container"><!--面包屑导航-->
				<div id="page-content">
			        <div class="panel">
			            <div class="panel-body">
			            	<ul class="nav nav-tabs">
				                <li class="active"><a href="#home" data-toggle="tab">培训信息</a></li>
				                <li><a href="#profile" class="" data-toggle="tab">我的报名</a></li>
				            </ul>
            				<div class="tab-content">
            					<div class="tab-pane fade in active tab_border" id="home" aria-labelledby="home-tab">
            						<div class="row">
										<div class="col-sm-12">
											<div id="goodsBuy_table_wrapper"
												class="dataTables_wrapper form-inline dt-bootstrap no-footer">
												<div class="row">
													<div class="col-sm-12">
														<table
															class="table table-striped table-bordered table-hover text-center dataTable">
															<tr>
																<th>序号</th>
							                                    <th>活动主题</th>
							                                    <th>截止日期</th>
							                                    <th>活动时间</th>
							                                    <th>活动地点</th>
							                                    <th>组织单位</th>
							                                    <th>名额</th>
							                                    <th>活动类型</th>
							                                    <th>状态</th>
							                                    <th>操作</th>
															</tr>
															<tbody id="dataList">
															</tbody>
														</table>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-4"></div>
													<div class="col-sm-8">
														<div class="search-footer dataTables_paginate paging_full_numbers "> 
															<ul id="pageBar" class="pagination">
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
            					</div>
            					<div class="tab-pane fade tab_border" id="profile" aria-labelledby="profile-tab">
            						<div class="row">
										<div class="col-sm-12">
											<div id="goodsBuy_table_wrapper"
												class="dataTables_wrapper form-inline dt-bootstrap no-footer">
												<div class="row">
													<div class="col-sm-12">
														<table
															class="table table-striped table-bordered table-hover text-center dataTable">
															<tr>
																<th>序号</th>
							                                    <th>活动主题</th>
							                                    <th>活动时间</th>
							                                    <th>活动地点</th>
							                                    <th>组织单位</th>
							                                    <th>名额</th>
							                                    <th>活动类型</th>
							                                    <th>操作</th>
															</tr>
															<tbody id="dataListBm">
															</tbody>
														</table>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-4"></div>
													<div class="col-sm-8">
														<div class="search-footer dataTables_paginate paging_full_numbers "> 
															<ul id="pageBarBm" class="pagination">
															</ul>
														</div>
													</div>
												</div>
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
    <div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        <h4 class="modal-title" id="myModalLabel">提示</h4>
	      </div>
	      <div class="modal-body" id="signUpModalText">
	        
	      </div>
	    </div>
	  </div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>/resources/js/trainingInfo.js"></script>
  </body>
</html>
