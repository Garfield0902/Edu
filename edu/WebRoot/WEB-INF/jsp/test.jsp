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
    <title>My JSP 'peixunxinxi.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resources/styles/yzzx-all.min.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/masklayer.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/style.css">
    <link rel="stylesheet" type="text/css" href="resources/styles/bootstrap-datetimepicker.min.css">
	
  </head>
  
  <body>
    	<p> shiro 标签测试 </p>
    	
    	<div>
			测试是否有权限： user:create ： 
			<shiro:user>  
			欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/logout">退出</a>  
			</shiro:user>   
			
			<shiro:authenticated>  
			    用户[<shiro:principal/>]已身份验证通过  
			</shiro:authenticated>   
			
			<shiro:hasRole name="admin">  
			    用户[<shiro:principal/>]拥有角色admin<br/>  
			</shiro:hasRole>  
			
			<shiro:hasPermission name="user:create">  
			    用户[<shiro:principal/>]拥有权限user:create<br/>  
			</shiro:hasPermission>   
	</div>
  </body>
</html>
