<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看处理</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<table class="table">
  <caption>处理记录</caption>
  <thead>
    <tr>
      <th>编号</th>
      <th>处理结果</th>
      <th>处理者ID</th>
      <th>举报者ID</th>
      <th>处理时间</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="DealreportVO" varStatus="status">
    <tr>
      <td>${DealreportVO.id}</td>
      <td>${DealreportVO.dealdetail}</td>
      <td>${DealreportVO.adminid}</td>
      <td>${DealreportVO.reportid}</td>
      <td>${DealreportVO.time}</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</body>
</html>