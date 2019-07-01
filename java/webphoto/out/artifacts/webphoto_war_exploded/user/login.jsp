<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="UTF-8">
    <!-- 引入bootstrap-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <!--保证缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1.2, shrink-to-fit=no">
    <style>
        .body {
            background-image: url(<%=request.getContextPath()%>/sources/img/bg.jpg);
            background-size: 100%;
            -moz-background-size: 100% 100%;
            -webkit-background-size: 100% 100%;
            background-repeat: no-repeat;
            background-attachment: fixed;
            padding-top: 75px;
        }
    </style>
    <title>登陆</title>
</head>
<body class="body">

<%--导航栏--%>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand text-white" href="<%=request.getContextPath()%>/Index">WebPhoto</a>
        </div>
        <ul class="nav navbar-nav navbar-left">
            <li><a href="<%=request.getContextPath()%>/Index">
                <span class="glyphicon glyphicon-th-large" style="font-size: 15px;"> 首页</span> </a></li>
            <li><a href="<%=request.getContextPath()%>/Explore">
                <span class="glyphicon glyphicon-zoom-in" style="font-size: 15px;"> 探索</span> </a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="active">
                <a href="#">
                    <span class="glyphicon glyphicon-log-in" style="font-size: 15px;"> 登录</span>
                </a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/user/register.jsp">
                    <span class="glyphicon glyphicon-user" style="font-size: 15px;"> 注册</span>
                </a>
            </li>
        </ul>
    </div>
</nav>
<%--登录表单--%>
<div class="container">
    <div class="row m-5">
        <div class="col-md-3">
        </div>
        <div class="col-md-6">
            <c:if test="${not empty register}">
                <div class="alert alert-success">
                    <a href="#" class="close" data-dismiss="alert">
                        &times;
                    </a>
                    <strong>成功!</strong> 您已成功注册WebPhoto账户
                </div>
            </c:if>
            <c:if test="${not empty databaseErrMsg}">
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert">
                        &times;
                    </a>
                    <strong>错误!</strong> ${databaseErrMsg}
                </div>
            </c:if>
            <c:if test="${not empty centerEditSucMsg}">
                <div class="alert alert-success">
                    <a href="#" class="close" data-dismiss="alert">
                        &times;
                    </a>
                    <strong>成功!</strong> &nbsp;${sessionScope.centerEditSucMsg}
                    <%request.getSession().removeAttribute("centerEditSucMsg");%>
                </div>
            </c:if>
            <c:if test="${not empty usernameErrMsg}">
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert">
                        &times;
                    </a>
                    <strong>错误!</strong> ${usernameErrMsg}
                </div>
            </c:if>
            <c:if test="${not empty commentErrMsg}">
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <strong>错误！</strong>${commentErrMsg}
                </div>
            </c:if>
            <form action="<%=request.getContextPath()%>/User?method=login" style="align-items: center" method="post">
                <div class="form-group">
                    <label style="color: white">用户名</label>
                    <input type="text" class="form-control" id="input-username" name="username"
                           value="${username}" placeholder="你的用户名">
                </div>
                <c:if test="${not empty errMsg}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert">
                            &times;
                        </a>
                        <strong>错误!</strong> ${errMsg}
                    </div>
                </c:if>
                <div class="form-group">
                    <label style="color: white">密码</label>
                    <input type="password" class="form-control" id="input-password" name="password"
                           value="${password}" placeholder="你的密码">
                </div>
                <c:if test="${not empty passwordErrMsg}">
                    <div class="alert alert-danger">
                        <strong>错误!</strong> ${passwordErrMsg}
                    </div>
                </c:if>
                <button type="submit" class="btn btn-lg btn-primary px-5">登 录</button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a class="btn btn-default" href="<%=request.getContextPath()%>/index/index.jsp" role="button">取 消</a>
                &nbsp;&nbsp;
                <a class="btn btn-default" href="<%=request.getContextPath()%>/user/register.jsp" role="button">注 册</a>
            </form>
        </div>
        <div class="col-md-3">
        </div>
    </div>
</div>
</body>
</html>
