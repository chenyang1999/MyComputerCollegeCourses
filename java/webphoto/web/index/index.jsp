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
    <title>主页</title>
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
            <li class="active"><a href="<%=request.getContextPath()%>/Index">
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
<%--主页--%>
<div class="container" style="padding-left: 20px; padding-right: 20px">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="carousel slide" id="carousel-485540">
                <ol class="carousel-indicators">
                    <li class="active" data-slide-to="0" data-target="#carousel-485540">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-485540">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-485540">
                    </li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img alt="" src="<%=request.getContextPath()%>/sources/img/slider1.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                在这里发现美
                            </h4>
                            <p>
                                欢迎来到WebPhoto的世界
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="<%=request.getContextPath()%>/sources/img/slider3.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                在这里发现美
                            </h4>
                            <p>
                                欢迎来到WebPhoto的世界
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="<%=request.getContextPath()%>/sources/img/slider2.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                在这里发现美
                            </h4>
                            <p>
                                欢迎来到WebPhoto的世界
                            </p>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#carousel-485540" data-slide="prev"><span
                        class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control"
                                                                                href="#carousel-485540"
                                                                                data-slide="next"><span
                    class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
            <div class="img-rounded">
                <h3  style="color: white;"> 美好从这里开始：</h3>
            </div>
            <div class="row ">
                <c:forEach items="${albumList}" var="album">
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <a href="<%=request.getContextPath()%>/Album?albumId=${album.getId()}" target="_blank">
                                <img alt="300x200" src="<%=request.getContextPath()%>${album.getFirstPhotoPath()}"/>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
