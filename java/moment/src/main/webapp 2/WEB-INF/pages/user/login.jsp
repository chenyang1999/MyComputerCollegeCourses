<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录页面</title>
    <!-- 控制移动端浏览器视口的大小和缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="${path}/resources/user/css/bootstrap.min.css" rel="stylesheet">
    <!--  jQuery文件务必在bootstrap.min.js 之前引入  -->
    <script src="${path}/resources/user/js/jquery-2.2.3.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${path}/resources/user/js/bootstrap.min.js"></script>
    <!-- 引入login.css -->
    <link href="${path}/resources/user/css/login.css" rel="stylesheet">
    <script src="${path}/resources/user/js/login.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	
	<!-- 遮盖层 -->
	<div id="blackbox"></div>
	
	<!-- 清除设置透明度的继承影响 -->
	<div class="emptybox"></div>
	<div class="loginbox">
		<img src="${path}/resources/user/img/logo.png" class="logo" />

		<!--登录表单-->
		<form action="${path}/user/dologin.action" class="form-horizontal login_form" method="post" role="form">
			<div class="form-group">
				<label class="col-sm-6 font_wei font_14">邮箱/手机号码</label><br>
				<div class="col-sm-12">
					<input type="text" class="form-control textinput" name="account"
						placeholder="请输入邮箱/手机号码">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-6 font_wei font_14">密码</label><br>
				<div class="col-sm-12">
					<input type="password" class="form-control textinput"
						name="password" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6">
					<div class="checkbox">
						<label> <input type="checkbox" class="checkbox_input">记住密码
						</label>
					</div>
				</div>
				<div class="col-sm-6">
					<a href="#" class="forget_text font_12">忘记密码</a>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12">
					<button type="submit" class="btn btn-default col-sm-12 btn_input">登录</button>
				</div>
			</div>
			<div class="col-sm-offset-3 col-sm-9" style="color: white">
				尚未有账户？<a href="#" style="color: #b37529" onclick="moveout()">注册</a>
			</div>
		</form>

	</div>

	<!--右侧滑出的注册页-->
	<div id="register_box">
		<div class="register_content">
			<div class="register_head">
				<a href="#" class="col-sm-3" onclick="movein()"></a>
				<p class="font_wei col-sm-9">账户注册</p>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<img src="${path}/resources/user/img/blacklogo.png" alt="logo" />
			</div>
			<div class="clear"></div>
			<!--注册表单-->
			<form id="register_form" action="${path}/user/doregister.action" onsubmit="return checkAccount()&&checkPwd()" 
			class="form-horizontal register_form" method="post" role="form">
				<div class="form-group">
					<label for="regs_account" class="col-sm-12 font_wei color_grey">电子邮箱/手机号码</label><br>
					<div class="col-sm-12">
						<input type="text" class="form-control register_text"
							name="account" id="regs_account" onblur=" checkAccount()">
					</div>
					<div class="col-sm-12 font_wei font_12" id="account_error">您输入的邮箱或号码格式有误,请检查后再输入</div>
				</div>
				<div class="form-group">
					<label for="pwd" class="col-sm-12 font_wei color_grey ">创建密码</label><br>
					<div class="col-sm-12">
						<input name="password" type="password" class="form-control register_text" id="pwd">
					</div>
				</div>
				<div class="form-group">
					<label for="secpwd" class="col-sm-12 font_wei color_grey">确认密码</label><br>
					<div class="col-sm-12">
						<input type="password" class="form-control register_text"
							name="confirmpwd" id="secpwd" onblur="checkPwd()">
					</div>
					<div class="col-sm-12 font_wei font_12" id="pwd_error">两次密码输入不一致</div>
				</div>
				<div class="form-group">
					<label for="code" class="col-sm-12 font_wei color_grey">输入验证码</label><br>
					<div class="col-sm-6">
						<input name="code" type="text" class="form-control register_text">
					</div>
					<div class="col-sm-6">
						<img id="code" src="${path}/code/<%=new Date() %>.action" alt="code"  onclick="refreshCode()"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12">
						<button type="submit"
							class="btn btn-default col-sm-12 register_btn font_wei">注册</button>
					</div>
				</div>
			</form>
		</div>
	</div>



</body>
</html>