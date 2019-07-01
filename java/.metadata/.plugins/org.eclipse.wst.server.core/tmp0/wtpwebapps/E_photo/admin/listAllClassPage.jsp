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

<title>My JSP 'listAllClass.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
.abc {
	margin-top: 200px;
	margin-left: 500px;
}
</style>
</head>

<body>
	${msg }
	<%-- <table border="1">
		<c:forEach items="${ClassList } " var="photoClass">
			<tr>
				<td><c:out value="${photoClass.className }"></c:out></td>
				<td><c:out value="${photoClass.description }"></c:out></td>
			</tr>
		</c:forEach>
	</table>--%>

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
			<td align="center">
				<table border="1px">
					<tr>
						<td width="70" align=center>编号</td>
						<td width="85" align=center>分类</td>
						<td width="260" align=center>描述</td>
						<td width="90" align=center>照片数量</td>
						<td colspan="3" align=center>管理操作</td>
					</tr>
					<c:if test="${!empty pager.resultList }">
						<c:forEach items="${pager.resultList}" var="photoClass"
							varStatus="status">
							<tr align="center" bgcolor="#FFFFFF">
								<td align=center>${status.count}</td>
								<td align=center><a
									href="PhotoManager?action=seeAllPhotosByClassId&id=${photoClass.id }&num=${photoClass.photoNum}">${photoClass.name
										}</a></td>
								<td align=center><c:out value="${photoClass.shuoming }"></c:out>
								</td>
								<td align=center><c:out value="${photoClass.photoNum }"></c:out>
								</td>
								<td width="79" align=center><a
									href="ClassManager?action=toupdate&id=${photoClass.id}&num=${photoClass.photoNum}">更改</a>
								</td>
								<td width="88" align=center><a
									href="ClassManager?action=delete&id=${photoClass.id}&num=${photoClass.photoNum}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
					<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="70%" height="20" align="right" valign="middle">共有
									<font color="#FF2D00">${pager.rowCount}</font>条信息分 <font
									color="#FF2D00">${pager.pageCount}</font>页 <font
									color="#FF2D00">${pager.pageSize} </font>条/页 当前第 <font
									color="#FF2D00">${pager.pageNo}/${pager.pageCount}</font>页 <a
									href="ClassManager?action=getClassesByPage&pageNo=${pager.firstPageNo}">首页</a>
									<a
									href="ClassManager?action=getClassesByPage&pageNo=${pager.prePageNo}">上页</a>

									<a
									href="ClassManager?action=getClassesByPage&pageNo=${pager.nextPageNo}">下页</a>

									<a
									href="ClassManager?action=getClassesByPage&pageNo=${pager.lastPageNo}">尾页</a>

									<form action="ClassManager?action=getClassesByPage"
										method="post" name="form1" id="pa">
										<input name="pageNo" type="text" class="bd" id="Page"
											value="1" size="3" /> <input name="Submit" type="submit"
											class="an" value="Go" onclick="form1.submit();" />
									</form></td>
							</tr>
						</table>
			</td>
		</tr>
		
		
		
		<tr>
			<td height="30" align="center"><jsp:include page="Foot.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>
