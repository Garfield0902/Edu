<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>">

<link rel="stylesheet" type="text/css"
	href="resources/styles/yzzx-all.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/masklayer.css">
<link rel="stylesheet" type="text/css" href="resources/styles/style.css">
<link rel="stylesheet" type="text/css"
	href="resources/styles/bootstrap-datetimepicker.min.css">

</head>
<header id="navbar" class="index_fixed">
    <div id="navbar-container">
        <div class="navbar-header">
            <a class="navbar-brand">
                <div class="brand-title">
                    <span class="storename" data-lang=""></span>
                </div>
            </a>
        </div>
        <div class="navbar-content clearfix">
            <ul class="nav navbar-top-links pull-left">
                <li class="tgl-menu-btn">
                    <a class="mainnav-toggle" href="" style=" padding-left: 0; padding-bottom: 2px;">西安科技大学 教师发展在线能力系统
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-top-links pull-right">
                <li id="dropdown-user" class="dropdown">
                    <a href="" data-toggle="dropdown" class="dropdown-toggle text-right" data-original-title="" title="">
                        <div class="username hidden-xs">修改密码和退出</div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-md dropdown-menu-right panel-default">
                        <ul class="head-list">
                            <li>
                                <a href="javascript:;" name="editpwd">修改密码
                                </a>
                            </li>
                        </ul>
                        <!-- Dropdown footer -->
                        <div class="pad-all text-right">
                            <a href="javascript:;" class="btn btn-primary exitlogin">
                            <span data-lang="main_exit">退出登录</span>
                            </a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</header>