<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<title>My JSP 'class_add.jsp' starting page</title>

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
	<table width="980" border="1" align="center">
		<tr>
			<td width="980" height="60"><jsp:include page="Top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td width="980" height="30" align="center"><jsp:include
					page="navibar.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td width="980" height="380" align="center" valign="middle">
				<form id="form1" name="form1" method="post"
					action="${pageContext.request.contextPath}/ClassManager?action=add">
					<table width="343" border="1">
						<tr>
							<td width="139" height="49">分类名称：</td>
							<td width="188"><label for="textfield"></label> <input
								type="text" name="name" id="name" />
							</td>
						</tr>
						<tr>
							<td height="45">分类简介：</td>
							<td><label for="textfield2"></label> <input type="text"
								name="shuoming" id="shuoming" />
							</td>
						</tr>
						<tr>
							<td height="43" colspan="2" align="center"><input
								type="submit" name="submit" id="submit" value="提交" />
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td width="980" height="30"><jsp:include page="Foot.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>
