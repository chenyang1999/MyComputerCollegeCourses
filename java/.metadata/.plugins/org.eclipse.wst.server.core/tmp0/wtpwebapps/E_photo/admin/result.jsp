<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'result.jsp' starting page</title>

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
			<td height="380" align="center">${msg}</td>
		</tr>
		<tr>
			<td height="30" align="center"><jsp:include page="Foot.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>
