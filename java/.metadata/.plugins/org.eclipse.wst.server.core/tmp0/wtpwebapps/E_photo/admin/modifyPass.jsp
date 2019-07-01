<%@ page language="java" import="java.util.*"
	import="com.fsq.beans.User" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'modifyPass.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<table width="980" height="500" border="1" align="center"
		cellpadding="1" cellspacing="0">
		<tr>
			<td height="60" align="center"><jsp:include page="Top.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height="30" align="center"><jsp:include page="navibar.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td height="380" align="center">
				<form id="form1" name="form1" method="post"	action="<%=request.getContextPath()%>/UserManager?action=update">
					<table width="412" height="189" border="0">
						<tr>
							<td width="98" align="right">用户名：</td>
							<td width="304" align="left"><input name="username"	type="text" id="username"	value="${user.username}" readonly style="width:150px; height:15px;" /> </td>
						</tr>
						<tr>
							<td align="right">新密码：</td>
							<td align="left"><input name="password" type="password"
								id="password" style="width:150px; height;15px;" /></td>
						</tr>
						<tr>
							<td align="right">确认密码：</td>
							<td align="left"><input name="confirmpass" type="password"
								id="confirmpass" style="width:150px; height;15px;" /> 两次密码必须一致</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								name="submit" id="submit" value="提交" /> <input type="reset"
								name="reset" id="reset" value="重置" />
							</td>
						</tr>
					</table>
				</form></td>
		</tr>
		<tr>
			<td height="30" align="center"><jsp:include page="Foot.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>
