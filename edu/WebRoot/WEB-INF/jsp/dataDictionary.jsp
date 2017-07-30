<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><title>数据字典</title></head>

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
								<span>数据字典</span>
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
				                <div class="form-inline">
				                    <div class="col-xs-8">
				                    	<form name="table_head" class="form-inline">
					                        <div class="form-group">
					                            <select class="form-control" id="typeform">
					                            	<option value="">选择类型</option>
					                            	<option value="aaa">aaaa</option>
					                            	<option value="bbb">bbbb</option>
					                            </select>
					                        </div>
					                        <button type="button" id="search" class="btn btn-info">查询</button>
						                </form>
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
														<th>数据类型</th>
														<th>键值	</th>
														<th>顺序</th>
														<th>描述</th>
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
	        <h4 class="modal-title" id="addModalTitle">添加数据类型</h4>
	      </div>
	      <div class="modal-body" id="signUpModalText">
	        <form class="form-horizontal" action="dataDictionary/addDataDictionary.do" id="addDataDictionaryForm" method="post">
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">字典类型</label>
                <div class="col-sm-10">
                	<input type="hidden" name="id" value="" id="dataDictionaryIdInput">
                  <select class="form-control" id="type" name="type" placeholder="">
                  	<option value="aaa">aaaa</option>
                  	<option value="bbb">bbbb</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">键值</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="value" name="value" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="orderData" name="orderData" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">描述</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="des" name="des" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" id="addDataDictionaryBtn" class="btn btn-success">保存</button>
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
	<script type="text/javascript" src="<%=basePath%>/resources/js/fuedit.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/js/dataDictionary.js"></script>
</body>
</html>

