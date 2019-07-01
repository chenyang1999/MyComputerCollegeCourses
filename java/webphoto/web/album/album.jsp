<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 引入bootstrap-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap/3.3.7/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--保证缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1.5, shrink-to-fit=no">
    <style>
        .body {
            background-image: url(<%=request.getContextPath()%>/sources/img/bg.jpg);
            background-size: 100%;
            -moz-background-size: 100% 100%;
            -webkit-background-size: 100% 100%;
            background-repeat: no-repeat;
            background-attachment: fixed;
            padding-top: 50px;
        }
    </style>
    <title>${albumName}--${creator}</title>
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
            <li class="active"><a href="<%=request.getContextPath()%>/Explore">
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
<%--主要内容--%>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <c:if test="${not empty uploadErrMsg}">
                <li class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <strong>错误！</strong>${uploadErrMsg}
                    <%request.getSession().removeAttribute("uploadErrMsg");%>
                </li>
            </c:if>
            <div class="page-header">
                <div align="left" style="float: left;color: #f8f9fa">
                    <li style="font-size: x-large">
                        <span class="glyphicon glyphicon-picture"></span>
                        &nbsp;<strong>${albumName}</strong>&nbsp;&nbsp;&nbsp;&nbsp;
                        <strong class="text-primary" style="font-size: large">创建者：${creator}</strong>
                    </li>
                </div>
                <c:if test="${creator eq username}">
                    <div align="right">
                        <a id="modal-587971" href="#modal-container-import" role="button" class="btn btn-info"
                           data-toggle="modal">导入图片&nbsp<span class="glyphicon glyphicon-plus"></span></a>
                        <div align="left" class="modal fade" id="modal-container-import" role="dialog"
                             aria-labelledby="myModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            ×
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel" style="color:#141721">
                                            请添加你要导入的图片
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <form role="form" action="<%=request.getContextPath()%>/Upload"
                                              method="post" enctype="multipart/form-data">
                                            <div class="form-group">
                                                <div align="left" style="float: left">
                                                    <input type="file" class="center-block" name="uploadPhoto">
                                                    <input type="hidden" name="albumId" value="${albumId}">
                                                </div>
                                                <div align="right">
                                                    <button type="submit" class="btn btn-primary">确定
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${not (creator eq username)}">
                    <div align="right">
                        <a href="<%=request.getContextPath()%>/MyAlbums?creator=${creator}"
                           class="btn btn-info">更多${creator}的相册</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row">
                <c:set var="count" value="0"/>
                <c:forEach items="${photoList}" var="photo">
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <a href="<%=request.getContextPath()%>/Photo?photoId=${photo.getId()}" target="_blank">
                                <img alt="300x200" src="<%=request.getContextPath()%>${photo.getPhotopath()}"/>
                            </a>
                            <c:choose>
                                <c:when test="${not empty userid}">
                                    <div align="left" style="float: left">
                                        <a class="btn" href="<%=request.getContextPath()%>/Photo?photoId=${photo.getId()}">看评论</a>
                                    </div>
                                    <div align="right">
                                        <a class="btn"
                                           href="<%=request.getContextPath()%>/DeletePhoto?photoId=${photo.getId()}&albumId=${albumId}">删除
                                        </a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div align="left" style="float: left">
                                        <a class="btn" href="<%=request.getContextPath()%>/Photo?photoId=${photo.getId()}">评论</a>
                                    </div>
                                    <div align="right">
                                        <a href="<%=request.getContextPath()%>/Photo?photoId=${photo.getId()}" class="btn"
                                           target="_blank">大图</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <c:set var="count" value="${count+1}"/>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
