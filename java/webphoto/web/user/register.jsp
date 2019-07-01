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
        .body{
            background-image: url(<%=request.getContextPath()%>/sources/img/bg.jpg);
            background-size: 100%;
            -moz-background-size: 100% 100%;
            -webkit-background-size: 100% 100%;
            background-repeat:no-repeat;
            background-attachment: fixed;
            padding-top: 75px;
        }
    </style>
    <title>注册</title>
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
            <li>
                <a href="<%=request.getContextPath()%>/user/login.jsp">
                    <span class="glyphicon glyphicon-log-in" style="font-size: 15px;"> 登录</span>
                </a>
            </li>
            <li class="active">
                <a href="<%=request.getContextPath()%>/user/register.jsp">
                    <span class="glyphicon glyphicon-user" style="font-size: 15px;"> 注册</span>
                </a>
            </li>
        </ul>
    </div>
</nav>
<%--注册表单--%>
<div class="container">
    <div class="row mt-5">
        <div class="col-md-3">
        </div>
        <div class="col-md-6">
            <form action="<%=request.getContextPath()%>/User?method=register" method="post">
                <div class="form-group">
                    <c:if test="${not empty databaseErrMsg}">
                        <div class="alert alert-danger">
                            <a href="#" class="close" data-dismiss="alert">
                                &times;
                            </a>
                            <strong>错误!</strong> ${databaseErrMsg}
                        </div>
                    </c:if>
                    <label style="color: white">用户名</label>
                    <input type="text" class="form-control" id="input-username" name="username"
                           value="${requestScope.get("username")}" placeholder="请输入你喜欢的用户名">
                </div>
                <c:if test="${not empty usernameErrMsg}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert">
                            &times;
                        </a>
                        <strong>错误!</strong> ${usernameErrMsg}
                    </div>
                </c:if>
                <div class="form-group">
                    <label style="color: white">密码</label> <input type="password" class="form-control"
                                                                  id="input-password" name="password"
                                                                  value="${requestScope.get("password")}"
                                                                  placeholder="请输入你的密码">
                </div>
                <div class="form-group">
                    <label style="color: white">确认</label> <input type="password" class="form-control"
                                                                  id="input-surepassword"
                                                                  name="surePassword"
                                                                  value="${requestScope.get("surePassword")}"
                                                                  placeholder="请确定密码一致">
                </div>
                <c:if test="${not empty passwordErrMsg}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert">
                            &times;
                        </a>
                        <strong>错误!</strong> ${passwordErrMsg}
                    </div>
                </c:if>
                <button type="submit" class="btn btn-lg btn-primary">注册</button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a class="btn btn-default" href="<%=request.getContextPath()%>/index/index.jsp"
                   role="button">取 消</a>
                &nbsp;&nbsp;
                <a class="btn btn-default" href="<%=request.getContextPath()%>/user/login.jsp"
                   role="button">登 录</a>
            </form>
        </div>
        <div class="col-md-3">
        </div>
    </div>
</div>
</body>

</html>
