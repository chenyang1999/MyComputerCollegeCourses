<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理举报</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
	a{
		text-decoration: none;
		color: black;
	}
	a:HOVER {
		text-decoration: none;
		color: black;
}
</style>	
</head>

<body>
  

<form role="form" action="<%=request.getContextPath() %>/dealreportController/addDetail.action" method="post">
   <h3>处理举报</h3>
   <button type="button" class="btn btn-danger"><a href="<%=request.getContextPath() %>/dealreportController/deletePic.action?id=${picid}">删除举报的图片</a>
   </button>
  <div class="form-group">
  	<input type="hidden" name="reportid" value="${reportid}">
    <input type="hidden" name="adminid" value="1">
    <label for="name">处理结果</label>
    <input type="text" class="form-control" name="dealdetail" value="${dealVo.dealdetail}" placeholder="请输入处理结果">
  </div>
  <button type="submit" class="btn btn-default">提交</button>
</form>
</body>
</html>