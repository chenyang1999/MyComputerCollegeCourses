<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 引入bootstrap-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/3.3.7/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--保证缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1.25, shrink-to-fit=no">
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
    <title>用户中心</title>
</head>
<body class="body">
<div class="modal fade" id="modal-container-logout" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    确认注销
                </h4>
            </div>
            <div class="modal-body">
                您是否要退出当前登录状态？
            </div>
            <div class="modal-footer">
                <a class="btn btn-primary"
                   href="<%=request.getContextPath()%>/User?method=logout"
                   role="button">确认</a>
                <a class="btn btn-default" data-dismiss="modal" role="button">取消</a>
            </div>
        </div>
    </div>
</div>
<%--导航条--%>
<nav class="navbar nav-pills navbar-inverse navbar-fixed-top" role="navigation">
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
            <c:choose>
                <c:when test="${not empty username}">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-user" style="font-size: 15px;"> ${username}</span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/User?method=center">用户中心</a></li>
                            <li class="divider"></li>
                            <li><a href="<%=request.getContextPath()%>/MyAlbums">我的相册</a></li>
                            <li class="divider"></li>
                            <li><a href="#">取消</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#modal-container-logout" role="button" class="btn"
                           data-toggle="modal">
                            <span class="glyphicon glyphicon-log-out" style="font-size: 15px;"> 注销</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="<%=request.getContextPath()%>/user/login.jsp">
                            <span class="glyphicon glyphicon-log-in" style="font-size: 15px;"> 登录</span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/user/register.jsp">
                            <span class="glyphicon glyphicon-user" style="font-size: 15px;"> 注册</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
</nav>
<%--内容--%>
<div class="container">
    <div class="row mt-5">
        <div class="col-md-3">
        </div>
        <div class="col-md-6">
            <c:if test="${not empty centerEditErrMsg}">
                <div class="alert alert-danger">
                    <strong>错误!</strong> &nbsp;${centerEditErrMsg}
                    <%request.removeAttribute("centerEditErrMsg");%>
                </div>
            </c:if>
            <c:if test="${not empty centerEditSucMsg}">
                <div class="alert alert-success">
                    <strong>成功!</strong> &nbsp;${centerEditSucMsg}
                    <%request.removeAttribute("centerEditSucMsg");%>
                </div>
            </c:if>
            <h2 align="center" style="color: white;">用户中心</h2>
            <label style="color: white;font-size: large">用户昵称：${username}</label><br/><br/>
            <label style="color: white;font-size: large">注册时间：${regTime}</label><br/><br/>
            <label style="color: white;font-size: large">激活状态：${isActive}</label><br/><br/>
            <label style="color: white;font-size: large">相册数量：${albumCount}</label><br/><br/>

            <a id="modal-47353" href="#modal-container-47353" role="button"
               class="btn btn-info" data-toggle="modal">修改昵称</a>
            <div class="modal fade" id="modal-container-47353" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                            </button>
                            <h4 class="modal-title" id="myModalLabel">
                                修改昵称
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form role="form" action="<%=request.getContextPath()%>/User?method=rename" method="post">
                                <div class="form-group">
                                    <label>新的昵称</label>
                                    <input type="text" class="form-control" id="newUsername" name="newUsername"
                                           placeholder="请输入名称"/><br/>
                                    <button type="submit" class="btn btn-primary center-block">确定</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            &nbsp;&nbsp;&nbsp;&nbsp;
            <a id="modal-47353" href="#modal-container-47354" role="button"
               class="btn btn-info" data-toggle="modal">修改密码</a>
            <div class="modal fade" id="modal-container-47354" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                            </button>
                            <h4 class="modal-title" id="myModalLabel">
                                修改密码
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form role="form" action="<%=request.getContextPath()%>/User?method=rePassword"
                                  method="post">
                                <div class="form-group">
                                    <label>当前密码</label>
                                    <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                                           placeholder="请输入当前密码"/><br/>
                                    <label>新的密码</label>
                                    <input type="password" class="form-control" id="newPassword" name="newPassword"
                                           placeholder="请输入修改后密码"/><br/>
                                    <label>确认密码</label>
                                    <input type="password" class="form-control" id="sureNewPassword"
                                           name="sureNewPassword"
                                           placeholder="请确认输入的密码"/><br/>
                                    <button type="submit" class="btn btn-primary center-block">确定</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            &nbsp;&nbsp;&nbsp;&nbsp;
            <a class="btn btn-primary" href="<%=request.getContextPath()%>/MyAlbums"
               role="button">个人相册
            </a>
        </div>
        <div class="col-md-3">
        </div>
    </div>
</div>
</body>
</html>
