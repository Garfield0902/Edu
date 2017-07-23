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
    <title>教师管理模块</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/bootstrap-datetimepicker.min.css">
  </head>
  <body>
    <div id="container" class="effect mainnav-lg">
        <jsp:include page="header.jsp"></jsp:include>

        <div>
            <div id="content-container"><!--面包屑导航-->
<div id="page-title">
</div>
<div id="page-content">
        <div class="panel" name="datatable">
            <div class="panel-heading">
                <h3 class="panel-title"><span data-lang="goodsBuy_title">教师管理</span></h3>
            </div>
            <div class="panel-body">
            <div class="row">
                <form name="table_head" class="form-inline">
                    <div class="col-xs-8" name="search_item">
                        <div class="form-group">
                            <input type="text" id="zgh" name="zgh" class="form-control" placeholder="职工号">
                        </div>
                        <div class="form-group">
                            <input type="text" id="xm" name="xm" class="form-control" placeholder="姓名">
                        </div>
                        <button type="button" id="search" class="btn btn-info">查询</button>
                    </div>
                </form>
            </div>
            
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="goodsBuy_table_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer"><div class="row"><div class="col-sm-12">
                            <table class="table table-striped table-bordered table-hover text-center dataTable">
                                <tr>
                                    <th>序号</th>
                                    <th>教工号</th>
                                    <th>姓名</th>
                                    <th>学院</th>
                                    <!-- <th>活动地点</th>
                                    <th>组织单位</th>
                                    <th>名额</th>
                                    <th>活动类型</th>
                                    <th>状态</th> -->
                                    <th>操作</th>
                                </tr>
                                <tbody  id="dataList">
                                </tbody>
                            </table>
                        </div></div>
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

            <!--左侧垂直导航-->
            <jsp:include page="leftnav.jsp"></jsp:include>
        </div>
        
    </div>
			<div class="modal fade" tabindex="-1" role="dialog" id="tianjiaxuefen">
				<div class="modal-dialog" role="document">
					<div class="modal-content modal-lg">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">添加学分</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" action="jsjbxx/addJsxf.do" method="post" id="_tjxf_">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">姓名*</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="tjxf_xm" name="xm" placeholder="姓名">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">职工号*</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="tjxf_zgh" name="zgh" placeholder="职工号">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">学院</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="tjxf_xy" name="xy" placeholder="学院" readonly="readonly">
										<!-- <select class="form-control" id="" name="tjxf_xy" placeholder="" disabled="disabled">
										</select> -->
									</div>
								</div>
								
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">活动主题</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="tjxf_xy" name="hdzt" placeholder="活动主题">
									</div>
								</div>
								<!-- <div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">活动类型</label>
									<div class="col-sm-10">
										<select class="form-control" id="tjxf_" placeholder="">
										</select>
									</div>
								</div> -->
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">活动时间</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="datetimepicker" name="hdsj" placeholder="活动时间">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">学分</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="tjxf_pjfs" name="pjfs" placeholder="学分">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">组织单位</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="tjxf_hdzzdw" name="hdzzdw" placeholder="组织单位">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">地点</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="hddd" name="hddd" placeholder="地点">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">活动内容</label>
									<div class="col-sm-10">
										<div id="tjxf_hdnr"></div>
									</div>
									<input type="hidden" id="_hdnr_" name="hdnr">
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" id="tjxf_save" class="btn btn-success">保存</button>
										<button type="button" class="btn btn-default">取消</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="footer.jsp"></jsp:include>
  </body>
  <script type="text/javascript" src="resources/js/fuedit.js"></script>
  <script type="text/javascript" src="resources/js/teachermanage.js"></script>
		
</html>
