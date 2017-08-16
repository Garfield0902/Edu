<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><title>用户管理</title></head>

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
								<span>用户管理</span>
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<form name="table_head" class="form-inline" id="systemLogForm">
				                    <div class="col-xs-7" name="search_item">
				                        <div class="form-group">
				                            <input type="text" name="name" id="search_name" class="form-control" placeholder="用户名">
				                        	<select class="form-control" id="search_status" name="status">
				                            	<option value="">状态</option>
				                            	<option value="0">无效</option>
				                            	<option value="1">有效</option>
				                            </select>
				                        </div>
				                        <button type="button" id="search" class="btn btn-info">查询</button>
				                    </div>
				                    <div class="col-xs-4 text-right">
				                        <button type="button" class="btn btn-info" id="addBtn">添加</button>
				                        <button type="button" class="btn btn-info" id="deleteBtn">删除</button>
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
													<tr><th><input type="checkbox" id="checkAll" /></th>			
														<th>用户名</th>
														<th>状态</th>
														<th>创建时间</th>
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
					</div>
				</div>

				<!--左侧垂直导航-->
				<jsp:include page="leftnav.jsp"></jsp:include>
			</div>
		</div>
	</div>
	
		<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        <h4 class="modal-title" id="addModalTitle">添加用户</h4>
	      </div>
	      <div class="modal-body" id="">
	        <form class="form-horizontal" action="user/addUser.do" id="addUserForm" method="post">
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">用户名称</label>
                <div class="col-sm-10">
                	<input type="hidden" name="id" value="" id="roleManagementIdInput">
                  	<input type="text" class="form-control" id="name" name="name" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="password" name="password" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">状态</label>
                <div class="col-sm-10">
                  <select class="form-control" id="status" name="status" placeholder="">
                  	<option value="0">无效</option>
                  	<option value="1">有效</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="button" id="addUserBtn" class="btn btn-success">保存</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
              </div>
            </form>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        <h4 class="modal-title" id="addModalTitle">提示</h4>
	      </div>
	      <div class="modal-body">
	       	<div>确定删除？</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="confirmDelete" class="btn btn-warning" data-dismiss="modal">是</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">否</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="modal fade" id="deleteWarning" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        <h4 class="modal-title">提示</h4>
	      </div>
	      <div class="modal-body" id="deleteWarningText">
	       	<div>删除成功！</div>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>/resources/js/user.js"></script>
</body>
</html>

