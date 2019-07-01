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

<title>My JSP 'main.jsp' starting page</title>

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
	<table width='100%' border='0' cellspacing='0' cellpadding='5'>
		<tr>
			<td align="center" colspan="4"><jsp:include
					page="/admin/Top.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td align="center" colspan="4"><jsp:include
					page="/admin/navibar.jsp"></jsp:include></td>
		</tr>
		<tr align='center'>
			<c:forEach var="photo" items="${pager.resultList}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 4 == 0}">
						<td width='25%'>
							<table border=1 align=center cellpadding=3 cellspacing=1
								bordercolor="#666666" bgcolor='#ffffff' class='bk1'>
								<tr>
									<td align="center"><a
										href="PhotoManager?action=selOne&id=${photo.id}&str=client"
										target='_blank'> <img src="uploadimg/${photo.path}"
											alt="${photo.shuoming }" width=150 height=113 border='0'
											align="middle" class='bk1'> </a>
									</td>
								</tr>
							</table> <br> <a
							href="PhotoManager?action=selOne&id=${photo.id}&str=client">${photo.name}</a>&nbsp;&nbsp;|&nbsp;&nbsp;点击：${photo.dianji}
						</td>
						<c:out value="</tr>" escapeXml="false" />
						<c:out value="<tr align='center'>" escapeXml="false" />
					</c:when>
					<c:otherwise>
						<td width='25%'>
							<table border=1 align=center cellpadding=3 cellspacing=1
								bordercolor="#666666" bgcolor='#ffffff' class='bk1'>
								<tr>
									<td align="center"><a
										href="PhotoManager?action=selOne&id=${photo.id}&str=client"
										target='_blank'> <img src="uploadimg/${photo.path}"
											alt="${photo.shuoming }" width=150 height=113 border='0'
											align="middle"> </a>
									</td>
								</tr>
							</table> <br> <a
							href="PhotoManager?action=selOne&id=${photo.id}&str=client">${photo.name}</a>&nbsp;&nbsp;|&nbsp;&nbsp;点击：${photo.dianji}
						</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>
	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="1" class="bg">
		<tr>
			<td width="100%" height="24" align="right" bgcolor="#F1F3F5">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="70%" height="20" align="right" valign="middle">共有
							<font color="#FF2D00">${pager.rowCount}</font>条信息分 <font
							color="#FF2D00">${pager.pageCount}</font>页 <font color="#FF2D00">${pager.pageSize}
						</font>条/页 当前第 <font color="#FF2D00">${pager.pageNo}/${pager.pageCount}</font>页
							<a
							href="PhotoManager?action=getAllPhotosByPage&pageNo=${pager.firstPageNo}">首页</a>
							<a
							href="PhotoManager?action=getAllPhotosByPage&pageNo=${pager.prePageNo}">上页</a>

							<a
							href="PhotoManager?action=getAllPhotosByPage&pageNo=${pager.nextPageNo}">下页</a>

							<a
							href="PhotoManager?action=getAllPhotosByPage&pageNo=${pager.lastPageNo}">尾页</a>

							<form action="PhotoManager?action=getAllPhotosByPage"
								method="post" name="form1" id="pa">
								<input name="pageNo" type="text" class="bd" id="Page" value="1"
									size="3" /> <input name="Submit" type="submit" class="an"
									value="Go" onclick="form1.submit();" />
							</form></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
