<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>组织机构</title>

<link rel="stylesheet" type="text/css"
	href="resources/styles/yzzx-all.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/masklayer.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/js/ztree/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="resources/styles/style.css">

</head>

<body>
	<div id="container" class="effect mainnav-lg">
		<header id="navbar" class="index_fixed">
		<div id="navbar-container">
			<div class="navbar-header">
				<a class="navbar-brand">
					<div class="brand-title">
						<span class="storename" data-lang="">招生录取</span>
					</div>
				</a>
			</div>
			<div class="navbar-content clearfix">
				<ul class="nav navbar-top-links pull-left">
					<li class="tgl-menu-btn"><a class="mainnav-toggle" href=""
						style=" padding-left: 0; padding-bottom: 2px;"> </a></li>
				</ul>
				<ul class="nav navbar-top-links pull-right">
					<li class="dropdown"><a class="lang-selector dropdown-toggle"
						href="" data-toggle="dropdown" data-original-title="" title="">
							<span class="lang-selected" style="font-size: 24px;"> <i
								class="ion-earth"></i>
						</span>
					</a></li>
					<li id="dropdown-user" class="dropdown"><a href=""
						data-toggle="dropdown" class="dropdown-toggle text-right"
						data-original-title="" title="">
							<div class="username hidden-xs">test</div>
					</a>
						<div
							class="dropdown-menu dropdown-menu-md dropdown-menu-right panel-default">
							<div class="pad-all bord-btm">
								<p class="text-lg text-semibold mar-btm storename">西安安心月子服务有限公司</p>
							</div>

							<ul class="head-list">
								<li><a href="javascript:;" name="editpwd">修改密码 </a></li>
							</ul>

							<!-- Dropdown footer -->
							<div class="pad-all text-right">
								<a href="javascript:;" class="btn btn-primary exitlogin"> <span
									data-lang="main_exit">退出登录</span>
								</a>
							</div>
						</div></li>

				</ul>
			</div>
		</div>
		</header>

		<div>
			<div id="content-container">
				<!--面包屑导航-->
				<div id="page-title"></div>
				<div id="page-content">
					<div class="panel" name="datatable">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span data-lang="goodsBuy_title">组织机构</span>
							</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<form name="table_head" class="form-inline">
									<div class="col-xs-8" name="search_item"></div>
									<div class="col-xs-4 text-right">
										<!-- <input id="txtId" type="hidden" value="" /><br />  
        								<input id="txtAddress" type="hidden" value="" />   -->
										<button type="button" class="btn btn-info" data-toggle="modal"
											data-target="#adddept">
											<span> 添加</span>
										</button>
										<button type="button" class="btn btn-info" data-toggle="modal" id="delete_btn"
											data-target="#delete">
											<span>删除</span>
										</button>
									</div>
								</form>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<div class="jiegou">
										<div>结构列表</div>
										<div class="ztree" id="list"></div>
									</div>
								</div>
								<div class="col-sm-9">
									<div id="goodsBuy_table_wrapper"
										class="dataTables_wrapper form-inline dt-bootstrap no-footer">
										<div class="row">
											<div class="col-sm-12">
												<table
													class="table table-striped table-bordered table-hover text-center dataTable">
													<tr>
														<th class="text-center"><input id="selectAll"
															type="checkbox"></th>
														<th>机构名称</th>
														<th>机构代码</th>
														<th>机构类型</th>
														<th>上级机构</th>
														<th>机构顺序</th>
														<th>操作</th>
													</tr>
													<tbody  id="dataList">
                               						</tbody>
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
			</div>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>

	<div class="modal fade" id="adddept" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加机构</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="department/addDept.do" method="post" id="adddept_form">
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*上级机构：</label>
							<div class="col-sm-10">
								<select class="form-control" name="pid" >
									<option value="0">选择机构</option>
									<c:forEach var="item" items="${departs}">
	                            		<option value="${item.id }" dcode="${item.dcode }" dtype="${item.dtype }">${item.dname}</option>
	                            	</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构名称：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="dname" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构代码：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="dcode" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构类型：</label>
							<div class="col-sm-10">
								<select class="form-control" name="dtype">
									<c:forEach var="item" items="${dtypes}">
	                            		<option value="${item.value }" dtype="${item.type }">${item.key}</option>
	                            	</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构顺序：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="dorder" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" id="adddept_submit" class="btn btn-primary"
									data-dismiss="modal">保存</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="modal fade" id="viewdept" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">查看机构</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*上级机构：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="view_did" placeholder=""  readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构名称：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="view_dname" placeholder=""  readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构代码：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="view_dcode" placeholder=""  readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构类型：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="view_dtype" placeholder=""  readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构顺序：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="view_dorder" placeholder="" readonly="readonly">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div> -->
	
	<div class="modal fade" id="modifydept" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改机构</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="department/updateDept.do" method="post" id="modifydept_form">
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*上级机构：</label>
							<div class="col-sm-10">
								<select class="form-control"  name="pid" >
									<option value="0">选择机构</option>
									<c:forEach var="item" items="${departs}">
	                            		<option value="${item.id }" dcode="${item.dcode }" dtype="${item.dtype }">${item.dname}</option>
	                            	</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构名称：</label>
							<div class="col-sm-10">
								<input type="hidden" class="form-control" id="edit_did" name="id" value="" placeholder="">
								<input type="text" class="form-control" id="edit_dname" name="dname" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构代码：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_dcode" name="dcode" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构类型：</label>
							<div class="col-sm-10">
								<%-- <select class="form-control" id="edit_dtype" name="dtype">
									<c:forEach var="item" items="${dtypes}">
	                            		<option value="${item.value }" dtype="${item.type }">${item.key}</option>
	                            	</c:forEach>
								</select> --%>
								<select class="form-control" name="dtype">
									<c:forEach var="item" items="${dtypes}">
	                            		<option value="${item.value }" dtype="${item.type }">${item.key}</option>
	                            	</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">*机构顺序：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_dorder" name="dorder" value="" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" id="modifydept_submit" class="btn btn-primary"
									data-dismiss="modal">保存</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body">确定删除？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">是</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">否</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="resources/js/ztree/jquery.ztree.all.js"></script>
	<script type="text/javascript" src="resources/js/main.js"></script>
	<script type="text/javascript" src="resources/js/department.js"></script>
	<!-- <script type="text/javascript">
                var setting = {  
                        view: {  
                            selectedMulti: false        //禁止多点选中  
                        },  
                        data: {  
                            simpleData: {  
                                enable:true,  
                                idKey: "id",  
                                pIdKey: "pId",  
                                rootPId: ""  
                            }  
                        },  
                        callback: {  
                            onClick: function(treeId, treeNode) {  
                                var treeObj = $.fn.zTree.getZTreeObj(treeNode);  
                                var selectedNode = treeObj.getSelectedNodes()[0];  
                                $("#txtId").val(selectedNode.id);  
                                $("#txtAddress").val(selectedNode.name);  
                            }  
                        }  
                    };  

                var zNodes = [{"id":1, "pId":0, "name":"test1"},   
                                 {"id":11, "pId":1, "name":"test11"},   
                                 {"id":12, "pId":1, "name":"test12"},   
                                 {"id":111, "pId":11, "name":"test111"},   
                             ];   
                $(document).ready(function(){
                    $.fn.zTree.init($("#list"), setting, zNodes);
                });
     </script> -->
</body>
</html>
