<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理举报</title>
    <link rel="stylesheet" href="${path}/resources/admin/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${path }/resources/admin/css/index.css" />
    <link href="${path }/resources/admin/css/cropper.min.css" rel="stylesheet">
    <link href="${path }/resources/admin/css/main.css" rel="stylesheet">
</head>
<body>
<table class="table table-striped">
  <caption>处理举报</caption>
  <thead>
    <tr>
     <th>编号</th>
	 <th>举报者姓名</th>							
	 <th>图片ID</th>
	 <th>举报内容</th>
	 <th>举报时间</th>
	 <th>操作</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list}" var="reportVO" varStatus="status">
						    <tr>
							<td>${reportVO.id}</td>
							<td>${reportVO.userid}</td>						
							<td>${reportVO.picid}</td>
							<td>${reportVO.description}</td>
							<td>${reportVO.time}</td>
							<td>
							<a href="<%=request.getContextPath() %>/dealreportController/edit.action?id=${reportVO.id}" style="color:black">编辑</a>
							<a href="<%=request.getContextPath() %>/report/doDelete.action?id=${reportVO.id}" style="color:black">删除</a>
							</td>
							</tr>
	</c:forEach> 
  </tbody>
</table>

</body>
</html>