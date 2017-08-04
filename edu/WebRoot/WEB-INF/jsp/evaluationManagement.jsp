<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><title>评价管理</title></head>

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
								<span>评价管理</span>
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
				                <div class="form-inline">
				                    <div class="col-xs-8">
				                    	<form name="table_head" class="form-inline" id="evaluationManagementForm">
					                        <div class="form-group">
					                            <input type="text" class="form-control" id="startDate" placeholder="起始日期">
					                        </div>
					                        <div class="form-group">
					                            <input type="text" class="form-control" id="endDate" placeholder="结束日期">
					                        </div>
					                        <div class="form-group">
					                            <input type="text" class="form-control" id="hdzt" placeholder="活动主题">
					                        </div>
					                        <button type="button" id="search" class="btn btn-info">查询</button>
						                </form>
				                    </div>
				                </div>
				            </div>
							<div class="row">
								<div class="col-sm-12">
									<div id="goodsBuy_table_wrapper"
										class="dataTables_wrapper form-inline dt-bootstrap no-footer">
										<div class="row">
											<div class="col-sm-12">
												<table
													class="table table-striped table-bordered table-hover text-center dataTable">
													<tr>
														<th><input type="checkbox" id="checkAll" /></th>
														<th>序号</th>
														<th>年份	</th>
														<th>活动主题</th>
														<th>截止日期</th>
														<th>活动时间</th>
														<th>活动地点</th>
														<th>主讲人</th>
														<th>组织单位</th>
														<th>名额</th>
														<th>活动类型</th>
														<th>状态	</th>
														<th>学分</th>
														<th>培训名单</th>
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
					</div>
				</div>

				<!--左侧垂直导航-->
				<jsp:include page="leftnav.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<div class="modal fade" id="view" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content modal-lg">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        <h4 class="modal-title">报名人数</h4>
	      </div>
	      <div class="modal-body" id="deleteWarningText">
	       	<div class="text-center">【活动主题：2017年 教师技能相关培训】参训教师名单</div>
	       	<hr/>
	       	<div>【时间】：<span id="modalTime"></span>【地点】：<span id="ModalAddress"></span>【组织单位】：<span id="company"></span>【活动类型】：<span id="type"></span> 【参与人数】:<span id="number"></span>											
	       	</div>
	       	<hr/>
	       	<div class="row">
				<form name="table_head" class="form-inline" id="activityBm">
                    <div class="col-xs-7" name="search_item">
                        <div class="form-group">
                        	<input type="hidden" id="modalhdid" >
                            <input type="text" id="modalZgh" class="form-control" placeholder="职工号">
                        </div>
                        <button type="button" id="bmSearch" class="btn btn-info">查询</button>
                    </div>
                    <div class="col-xs-5 text-right">
                    	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#add">添加</button>
                        <button type="button" class="btn btn-info" id="bmDeleteBtn">删除</button>
                        <button type="button" class="btn btn-info" id="mulExport">批量导出</button>
                        <button type="button" class="btn btn-info" id="export">导出</button>
                    </div>
               </form>
             </div>
             <div class="row">
				<div class="col-sm-12">
					<div id="goodsBuy_table_wrapper"
						class="dataTables_wrapper form-inline dt-bootstrap no-footer">
						<div class="row">
							<div class="col-sm-12">
								<table
									class="table table-striped table-bordered table-hover text-center dataTable">
									<tr>
										<th><input type="checkbox" id="bmCheckAll"/></th>
										<th>序号</th>
										<th>教职工号</th>
										<th>姓名</th>
										<th>学院名称</th>
										<th>添加学分时间</th>
										<th>培训学分</th>
										<th>操作</th>
									</tr>
									<tbody id="bmDataList">
									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<div class="bm-search-footer dataTables_paginate paging_full_numbers "> 
									<ul id="bmPageBar" class="pagination">
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
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>/resources/js/fuedit.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/js/evaluationManagement.js"></script>
</body>
</html>

