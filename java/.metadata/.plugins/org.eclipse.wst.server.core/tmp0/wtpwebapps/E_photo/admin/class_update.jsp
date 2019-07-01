<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName

			() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'class_update.jsp' starting page</title>

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
			<td height="380" align="center"><c:if
					test="${!empty photoClass}">
					<form name="" method="post" action="ClassManager?action=update">
						<table>
							<tr>
								<td>类别名称：：</td>
								<td><input type="text" name="name"
									value="${photoClass.name}" />
								</td>
							</tr>
							<tr>
								<td>类别描述：</td>
								<td><input type="text" name="shuoming"
									value="${photoClass.shuoming}" />
								</td>
							</tr>
							<tr>
								<td>创建时间：</td>
								<td><input type="text" name="price"
									value="${photoClass.contenttime}" readonly="readonly" /></td>
							</tr>
							<tr>
								<td><input type="submit" name="submit" value="提交"><input
									type="hidden" name="id" value="${photoClass.id }" />
								</td>
							</tr>
						</table>
					</form>
				</c:if></td>
		</tr>
		<tr>
			<td height="30" align="center"><jsp:include page="Foot.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>
