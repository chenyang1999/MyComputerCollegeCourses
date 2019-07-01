<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<body>
	<table>
		<tr>
			<td align="center">管理选项：<a
				href="${pageContext.request.contextPath}/PhotoManager?action=getAll">管理首页</a>
				|<a
				href="${pageContext.request.contextPath}/PhotoManager?action=addPhoto">添加图片</a>
				|<a
				href="${pageContext.request.contextPath}/ClassManager?action=addClass">添加分类</a>
				|<a
				href="${pageContext.request.contextPath}/ClassManager?action=getClassesByPage">分类管理</a>
				|<a href="${pageContext.request.contextPath}/admin/modifyPass.jsp">修改密码</a>
				|<a href="${pageContext.request.contextPath}/logout.jsp">退出管理</a></td>
		</tr>
	</table>
</body>
</html>