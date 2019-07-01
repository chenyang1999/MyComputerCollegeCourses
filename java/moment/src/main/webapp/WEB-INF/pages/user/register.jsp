<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${msg }<br>
	<form action="<%=request.getContextPath()%>/user/doregister.action" method="post">
		请输入您的邮箱或手机号码：<br>
		<input type="text" name="account"><br>
		创建密码：<br>
		<input type="password" name="password"><br>
		确认密码:<br>
		<input type="password" name="confirmpwd"><br>
		请输入图片中的验证码：<br>
		<input type="text" name="code">
		<img alt="code" src="<%=request.getContextPath()%>/code/.action">
		<input type="submit" value="注册">  
	</form>
	
</body>
</html>