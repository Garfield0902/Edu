<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><title>活动管理</title></head>

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
								<span>活动管理  <input id="basePath_" type="hidden" value="<%=basePath %>" /></span>
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
				                    <div class="col-xs-7" name="search_item">
				                        <div class="form-group">
				                            <input type="text" id="startDate" class="form-control" placeholder="起始日期">
				                        </div>
				                        <div class="form-group">
				                            <input type="text" id="endDate" class="form-control" placeholder="结束日期">
				                        </div>
				                        <div class="form-group">
				                            <input type="text" id="hdzt" class="form-control" placeholder="活动主题">
				                        </div>
				                        <button type="button" id="search" class="btn btn-info">查询</button>
				                    </div>
				                    <div class="col-xs-5 text-right">
				                    	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#add">添加</button>
				                        <button type="button" class="btn btn-info" id="modify">修改</button>
				                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#delete">删除</button>
				                        
				                        <form action="<%=basePath%>trainingInfo/importTrainingInfo.do" method="post" enctype ="multipart/form-data">
									  		<input id="upfile" type="file" name="upfile">
									  		<input name="submit" type="submit" value="导入" >
									  	</form>
				                        <a href="<%=basePath %>trainingInfo/exportTrainingInfo.do"><button type="button" class="btn btn-info" id="export">导出</button></a>
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
														<th><input type="checkbox" id="checkAll"/></th>
														<th>序号</th>
														<th>年份</th>
														<th>活动主题</th>
														<th>截止日期</th>
														<th>活动时间</th>
														<th>活动地点</th>
														<th>主讲人</th>
														<th>组织单位</th>
														<th>名额</th>
														<th>活动类型</th>
														<th>状态</th>
														<th>学分</th>
														<th>报名人数</th>
														<th></th>
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
	        <h4 class="modal-title" id="addModalTitle">添加活动</h4>
	      </div>
	      <div class="modal-body" id="signUpModalText">
	        <form class="form-horizontal" action="trainingInfo/addPxhd.do" id="addActivity" method="post">
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">活动主题</label>
                <div class="col-sm-10">
                  <input type="hidden" name="hdid" value="" id="hdidInput" />
                  <input type="text" class="form-control" id="hdzt" name="hdzt" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">年份</label>
                <div class="col-sm-10">
                  <select class="form-control" id="hdnf" name="hdnf">
                  	<option>2010</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">主讲人</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="zjr" name="zjr" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">活动时间</label>
                <div class="col-sm-10">
                	<input type="text" class="form-control" id="hdsj" name="hdsj"/>
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">截止报名时间</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="bmjzsj" name="bmjzsj"/>
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">活动地点</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="hddd" name="hddd" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">名额</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="zdcyrs" name="zdcyrs" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">级别</label>
                <div class="col-sm-10">
                  <select class="form-control" id="hdjb" name="hdjb">
                  	<option>2010</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">学分</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="hdxf" name="hdxf" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">活动类型</label>
                <div class="col-sm-10">
                  <select class="form-control" id="hdlx" name="hdlx">
                  	<option>2010</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">状态</label>
                <div class="col-sm-10">
                  <select class="form-control" id="hdStatus" name="hdStatus">
                  	<option>2010</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">组织单位</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="hdzzdw" name="hdzzdw" placeholder="">
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">活动内容</label>
                <div class="col-sm-10">
                   <div id="activityContent"></div>
                   <input type="hidden" name="hdnr" value="" id="activityContentInput" />
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" id="addActivityBtn" class="btn btn-success">保存</button>
                  <button type="button" class="btn btn-default">取消</button>
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
	<div class="modal fade" id="view" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content modal-lg">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	        <h4 class="modal-title">报名人数</h4>
	      </div>
	      <div class="modal-body" id="deleteWarningText">
	       	<div class="text-center">【活动主题：2017年 教师技能相关培训】报名单</div>
	       	<hr/>
	       	<div>【时间】：<span id="modalTime"></span>【地点】：<span id="ModalAddress"></span>【组织单位】：<span id="company"></span>【活动类型】：<span id="type"></span> 【参与人数】:<span id="number"></span>											
	       	</div>
	       	<hr/>
	       	<div class="row">
				<form name="table_head" class="form-inline" id="activityBm">
                    <div class="col-xs-7" name="search_item">
                        <div class="form-group">
                            <input type="text" id="modalZgh" class="form-control" placeholder="职工号:">
                        </div>
                        <button type="button" id="bmSearch" class="btn btn-info">查询</button>
                    </div>
                    <div class="col-xs-5 text-right">
                    	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#add">添加</button>
                        <button type="button" class="btn btn-info" id="bmDeleteBtn">删除</button>
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
									</tr>
									<tbody id="bMDataList">
									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<div class="bm-search-footer dataTables_paginate paging_full_numbers "> 
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
	</div>
	<div class="modal fade" id="bmDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:2000;">
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
	        <button type="button" id="bmConfirmDelete" class="btn btn-warning" data-dismiss="modal">是</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">否</button>
	      </div>
	    </div>
	  </div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>/resources/js/fuedit.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/js/activityManagement.js"></script>
</body>
</html>

