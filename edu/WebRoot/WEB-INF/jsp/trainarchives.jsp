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
    <title>培训档案模块</title>
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
                <h3 class="panel-title"><span data-lang="goodsBuy_title">培训档案</span></h3>
            </div>
            <div class="panel-body">
            <div class="row">
                <form name="table_head" class="form-inline">
                    <div class="col-xs-8" name="search_item">
                        <div class="form-group">
                            <select class="form-control" name="pjnf">
                            	<option value="-1">年度</option>
                            	<option value="2016">2016</option>
                            	<option value="2017">2017</option>
                            	<option value="2018">2018</option>
                            	<option value="2019">2019</option>
                            	<option value="2020">2020</option>
                            	<option value="2021">2021</option>
                            	<option value="2022">2022</option>
                            	<option value="2023">2023</option>
                            	<option value="2024">2024</option>
                            	<option value="2025">2025</option>
                            </select>
                            <input type="radio" name="searchType" value="nd" checked="checked">
                        </div>
                        &nbsp;&nbsp;
                        <div class="form-group">
                        	<input type="text" class="form-control" id="time_start" name="time_start" placeholder="开始时间">
                        	 ~ 
                        	<input type="text" class="form-control" id="time_end" name="time_end" placeholder="结束时间">
                        	<input type="radio" name="searchType" value="qj">
                        </div>
                        &nbsp;&nbsp;
                        <div class="form-group">
                            <select class="form-control" name="rzzgmcm">
                            	<option value="1">任职</option>
                            	<option value="2">职务一</option>
                            	<option value="3">职务二</option>
                            </select>
                            <input type="radio" name="searchType" value="zw">
                        </div>
                        <!-- <div class="form-group">
                            <input type="text" id="zgh" name="zgh" class="form-control" placeholder="职工号">
                        </div>
                        <div class="form-group">
                            <input type="text" id="xm" name="xm" class="form-control" placeholder="姓名">
                        </div> -->
                        <button type="button" id="search" class="btn btn-info">查询</button>
                        <button type="button" id="打印" class="btn btn-info">打印</button>
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
                                    <th>年份</th>
                                    <th>活动主题</th>
                                    <th>活动时间</th>
                                    <th>组织单位</th>
                                    <th>活动类型</th>
                                    <th>学分</th>
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
			<jsp:include page="footer.jsp"></jsp:include>
  </body>
  <script type="text/javascript" src="resources/js/fuedit.js"></script>
  <script type="text/javascript" src="resources/js/trainarchives.js"></script>
		
</html>
