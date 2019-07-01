<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>上传图片</title>
</head>
<script>
	function checkForm() {
		var theForm = document.form1;
		if ("" == theForm.name.value) {
			alert("图片名称必须填写");
		} else if ("" == theForm.lid.value) {
			alert("请选择图片的类型");
		} else if ("" == theForm.file1.value) {
			alert("请选择图图片");
		} else {
			theForm.submit();
		}
	}
</script>
<body>
<table width="980" height="500" border="1" align="center" cellpadding="1" cellspacing="0">
   		<tr>
   			<td height="60" align="center">
   			<jsp:include page="Top.jsp"></jsp:include>
   			</td>
   		</tr>
   		<tr>
   			<td height="30" align="center">
   			<jsp:include page="navibar.jsp"></jsp:include>
   			</td>
   		</tr>
   		<tr>
   			<td height="380" align="center">
	<form action="${pageContext.request.contextPath}/PhotoManager?action=add" method="post" name="form1"
		enctype="multipart/form-data">
		<table width="700" border="1" align="center" cellpadding="5"
			cellspacing="0">
			<tr>
				<td width="200" align="right">图片名称：</td>
				<td><input name="name" type="text" />&nbsp;&nbsp;<b
					style="color: red;">*</b>
				</td>
			</tr>			
			<tr>
				<td align="right">所属分类：</td>
				<td><select name="lid" size="1">
						<c:forEach var="list" items="${list}">
							<option value="${list.id}">${list.name}</option>
						</c:forEach>
				</select>&nbsp;&nbsp;<b style="color: red;">*</b></td>
			</tr>
			<tr>
				<td align="right">图片地址：</td>
				<td><input type="file" name="file1">&nbsp;&nbsp;<b
					style="color: red;">*</b>
				</td>
			</tr>
	 		<tr>
				<td align="right">图片简介：</td>
				<td><textarea name="shuoming" cols="25" rows="3"></textarea>
				</td>
			</tr> 			
			<tr align="center">
				<td colspan="2"><input type="button"  value="提交" onclick="JavaScript:checkForm();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重置">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">请注意：带"*"项目必须填写，上传文件时，
					注意图片不要过大，建议先进行处理。</td>
			</tr>
		</table>
	</form>
	</td>
   		</tr>
   		<tr>
   			<td height="30" align="center">
   				<jsp:include page="Foot.jsp"></jsp:include>
   			</td>
   		</tr>
   </table>
</body>
</html>
