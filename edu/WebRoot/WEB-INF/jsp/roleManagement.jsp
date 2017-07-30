<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/js/ztree/zTreeStyle.css">
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
								<span>角色管理</span>
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
				                <div class="form-inline">
				                    <div class="col-xs-8">
				                    </div>
				                    <div class="col-xs-4 text-right">
				                        <button type="button" class="btn btn-info" id="addBtn">添加</button>
				                        <button type="button" class="btn btn-info" id="deleteBtn">删除</button>
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
														<th>角色名称	</th>
														<th>权限说明	</th>
														<th>欢迎界面</th>
														<th>数据范围</th>
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
	        <h4 class="modal-title" id="addModalTitle">添加角色</h4>
	      </div>
	      <div class="modal-body" id="">
	        <form class="form-horizontal" action="roleManagement/addRoleManagement.do" id="addRoleManagementForm" method="post">
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">角色名称</label>
                <div class="col-sm-10">
                	<input type="hidden" name="id" value="" id="roleManagementIdInput">
                  	<input type="text" class="form-control" id="name" name="name" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">角色说明</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="desc" name="desc" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">欢迎界面</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="welcomPage" name="welcomPage" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">数据范围</label>
                <div class="col-sm-10">
                  <select class="form-control" id="dataRange" name="dataRange" placeholder="">
                  	<option value="aaa">aaaa</option>
                  	<option value="bbb">bbbb</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" id="addRoleManagementBtn" class="btn btn-success">保存</button>
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
	<div class="modal fade" id="permissionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        <h4 class="modal-title">角色分配管理</h4>
	      </div>
	      <div class="modal-body">
	       	<div id="permissionTree" class="ztree"></div>
	      </div>
	    </div>
	  </div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>/resources/js/ztree/jquery.ztree.all.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/js/roleManagement.js"></script>
</body>
</html>

