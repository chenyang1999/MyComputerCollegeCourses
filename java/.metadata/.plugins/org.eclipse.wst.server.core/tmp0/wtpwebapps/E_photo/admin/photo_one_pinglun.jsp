<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>图片管理</title>
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
				<form
					<%--action="${pageContext.request.contextPath}/PhotoManager?action=update"--%> method="post"
					name="form1">
					<table width="700" border="1" align="center" cellpadding="5"
						cellspacing="0">
						<tr>
							<td width="200" align="right">图片名称：</td>
							<td><c:out value="${photo.name }" />&nbsp;&nbsp;</td>
						</tr>

						<tr>
							<td align="right">图片地址：</td>
							<td><img src="${photo.path}">&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td align="right">图片简介：</td>
							<td><c:out value="${photo.shuoming }" /></td>
						</tr>
						<tr>
							<td align="right">上传时间：</td>
							<td><c:out value="${photo.contentTime }" /></td>
						</tr>
						<tr align="center">
							<td colspan="2"><a
								href="PhotoManager?action=toupdate&id=${photo.id }">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="PhotoManager?action=delete&id=${photo.id }">删除</a></td>
						</tr>
					</table>
				</form></td>
		</tr>
		<tr>
			<td align="center">
				<table width="900" border="0" align="center" cellpadding="1"
					cellspacing="1" bgcolor="#108ac6">
					<tr bgcolor="#FFFFFF">
						<td align="left" colspan="4">&nbsp;所&nbsp;有&nbsp;评&nbsp;论：</td>

					</tr>
					<c:if test="${empty pingLunList }">
						<tr bgcolor="#FFFFFF">
							<td align="left"><font color="red"><span>&nbsp;&nbsp;&nbsp;暂无评论!</span>
							</font></td>
						</tr>
					</c:if>
					<tr>
						<td bgcolor="#FFFFFF"><c:forEach var="pinglun"
								items="${pingLunList}" varStatus="status">
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0" bordercolor="#FF0000">
									<tr>
										<td width="8%" align="center" valign="middle"
											bgcolor="#FFFFFF"><font color="#666666">${status.count}</font>&nbsp;&nbsp;楼：</td>
										<td width="25%" align="left" valign="middle" bgcolor="#FFFFFF">
											&nbsp;&nbsp;&nbsp;昵称 <font color="#666666">：${pinglun.name}</font>
										</td>
										<%-- <td width="24%" align="left" valign="middle" bgcolor="#FFFFFF">
								&nbsp;&nbsp;发表时间 <font color="#666666">：${pinglun.contentTime
									}</font>
							</td>--%>
										<td width="43%" align="center" valign="middle"
											bgcolor="#FFFFFF">
											<a
											href="PingLunManager?action=delete&id=${pinglun.id}&pid=${pinglun.pid}">删除</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
											<a
											href="PingLunManager?action=deleteAll&id=${pinglun.id}&pid=${pinglun.pid}">全部删除</a>
										</td>
									</tr>
									<tr align="center">
										<td align="right" bgcolor="#FFFFFF">内&nbsp;&nbsp;容：</td>
										<td colspan="3" align="left" bgcolor="#FFFFFF">&nbsp; <font
											color="#666666">${pinglun.contentText }</font></td>
									</tr>
								</table>
								<hr>
							</c:forEach></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="30" align="center"><jsp:include page="Foot.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>
