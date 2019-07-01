<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
					name="form2">
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
							<td><c:out value="${photo.shuoming }" />
							</td>
						</tr>
						<tr>
							<td align="right">上传时间：</td>
							<td><c:out value="${photo.contentTime }" />
							</td>
						</tr>
					</table>
				</form>
			</td>
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
							</font>
							</td>
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
										<td width="24%" align="left" valign="middle" bgcolor="#FFFFFF">
											&nbsp;&nbsp;发表时间 <font color="#666666">：${pinglun.contentTime
												}</font></td>
									</tr>
									<tr align="center">
										<td align="right" bgcolor="#FFFFFF">内&nbsp;&nbsp;容：</td>
										<td colspan="3" align="left" bgcolor="#FFFFFF">&nbsp; <font
											color="#666666">${pinglun.contentText }</font>
										</td>
									</tr>
								</table>
								<hr>
							</c:forEach>
						</td>
					</tr>
				</table>
			</td>
		</tr>		
			<tr>
				<td align="center"><font color="#FF0000"><span id="msg"></span>
				</font></td>
			</tr>
			<tr>
				<td>
					<form name="form1" method="post" onsubmit="checkForm()"
						action="PingLunManager?action=insert&pid=${photo.id }">
						<table width="100%" height="155" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="1" align="right" bgcolor="#FFFFFF" colspan="2"></td>
							</tr>
							<tr>
								<td width="32%" height="38" align="center" valign="middle"
									bgcolor="#FFFFFF">昵称：</td>
								<td width="68%" bgcolor="#FFFFFF"><input name="name"
									type="text" class="box" id="name" >
								</td>
							</tr>
							<tr>
								<td align="center" valign="middle" bgcolor="#FFFFFF">评论：</td>
								<td valign="middle" bgcolor="#FFFFFF"><textarea
										name="pinglun" cols="25" rows="4" class="box" id="pinglun"></textarea>
								</td>
							</tr>
							<tr align="center">
								<td colspan="2" valign="middle" bgcolor="#FFFFFF" align="center"><input
									name="submit" type="submit" class="box" id="submit"
									value="提 交  "> &nbsp;&nbsp;&nbsp;&nbsp; <input
									name="reset" type="reset" class="box" id="reset" value="重 置  ">
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
