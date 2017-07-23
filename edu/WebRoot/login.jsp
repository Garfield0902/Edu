<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>登录</title>
	<link href="resources/styles/login.css" rel="stylesheet" type="text/css" />
</head>
<body class="login">
	<div class="login_m">
		<h1 class="login_logo">西科教师能力发展在线管理系统</h1>
		<div class="login_boder">
		
			<form action="login.do" method="post">
				<div class="login_padding" id="login_model">
					<h2>用户名</h2>
					<label> <input type="text" id="username" name="name" class="txt_input txt_input2" /></label>
					<h2>密码</h2>
					<label> <input type="password" name="password" id="userpwd" class="txt_input" /></label>
					<h2>验证码</h2>
					<label class="kaptchaImage">
						<input type="text" id="code" name="checknum" class="txt_input" />
						<img src="getKaptchaImage.do" id="kaptchaImage"/>
					</label>
					<div class="text-danger" id="codeError"></div>
					<div class="rem_sub">
						<div class="rem_sub_l"></div>
						<label> 
						<input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;" /></label>
					</div>
				</div>
			</form>
		</div>
		<!--login_boder end-->
	</div>
	<!--login_m end-->
	<br>
	<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	
<script type="text/javascript">
	$('#kaptchaImage').click(function () {//生成验证码  
    $(this).hide().attr('src', 'getKaptchaImage.do?' + Math.floor(Math.random()*100)).fadeIn();  
   });
	$("#code").focusout(function(){
    	var kaptcha = $('#code').val();
    	if(''===kaptcha){
    		return;
    	}
    	$.ajax({
    		url : 'validateKaptchaImage.do',
    		async : false,
  		 	type : "POST",
  		 	dataType : 'json',
    		data : {"kaptcha":kaptcha},
    		success : function(result){
        		if(result){
        			$('#codeError').text('验证码ok！');
        		}else{
            		$('#codeError').text('验证码错误！');
        			$("#kaptchaImage").attr('src', 'getKaptchaImage.do?' + Math.floor(Math.random()*100)).fadeIn();
        		}
    		}
    	});
    });
</script>
</body>
</html>