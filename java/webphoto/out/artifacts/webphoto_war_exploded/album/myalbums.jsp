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
    <title>我的相册</title>
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
<%--主要内容--%>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <c:if test="${not empty errMsg}">
                <li class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <strong>错误！</strong>${errMsg}
                </li>
            </c:if>
            <div class="page-header" style="color:#ebebed">
                <div align="left" style="float:left">
                    <li>
                    <span class="glyphicon glyphicon-user" style="font-size: 20px;">
                        <c:if test="${owner eq username}">
                             <a href="<%=request.getContextPath()%>/User?method=center">
                               <strong class="text-primary">${owner}</strong>
                             </a>
                            的相册&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${not (owner eq username)}">
                            <a href="#"><strong class="text-primary">${owner}</strong></a>
                            的相册&nbsp;&nbsp;
                        </c:if>
                        <small>数量:<strong class="text-primary"> ${albumCount}</strong></small>
                    </span>
                    </li>
                </div>
                <div align="right">
                    <c:choose>
                        <c:when test="${owner eq username}">
                            <a id="modal-587971" href="#modal-container-587971" role="button" class="btn btn-info"
                               data-toggle="modal">新建相册&nbsp<span class="glyphicon glyphicon-plus"></span></a>
                            <div align="left" class="modal fade" id="modal-container-587971" role="dialog"
                                 aria-labelledby="myModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                ×
                                            </button>
                                            <h4 class="modal-title" id="myModalLabel" style="color:#141721">
                                                新建相册
                                            </h4>
                                        </div>
                                        <div class="modal-body">
                                            <form role="form" action="<%=request.getContextPath()%>/CreateAlbum"
                                                  method="post">
                                                <div class="form-group">
                                                    <label style="color:#141721">要新建的相册名</label>
                                                    <input type="text" class="form-control" name="createAlbumName"
                                                           placeholder="请输入要创建的相册的名称"/><br/>
                                                    <button type="submit" class="btn btn-primary center-block">确定
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="<%=request.getContextPath()%>/Explore" class="btn btn-info">
                                继续探索&nbsp<span class="glyphicon glyphicon-zoom-in"></span>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row">
                <c:set var="count" value="0"/>
                <c:forEach items="${albumList}" var="album">
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <a href="<%=request.getContextPath()%>/Album?albumId=${album.getId()}" target="_blank">
                                <img alt="300x200" src="<%=request.getContextPath()%>${album.getFirstPhotoPath()}"/>
                            </a>
                            <div class="caption">
                                <div align="left" style="float:left">
                                    <a href="<%=request.getContextPath()%>/Album?albumId=${album.getId()}">
                                        <label>
                                                ${album.getName()}
                                        </label>
                                    </a>
                                </div>
                                <c:choose>
                                    <c:when test="${owner eq username}">
                                        <div class="dropdown" align="right">
                                            <button type="button" class="btn dropdown-toggle" id="dropdownMenu1"
                                                    data-toggle="dropdown">
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu"
                                                aria-labelledby="dropdownMenu1">
                                                <li role="presentation">
                                                    <a role="menuitem" tabindex="-1"
                                                       href="<%=request.getContextPath()%>/Album?albumId=${album.getId()}">打开</a>
                                                </li>
                                                <li role="presentation">
                                                    <a role="menuitem" tabindex="-1"
                                                       href="#modal-container-rename-${count}"
                                                       data-toggle="modal">重命名</a>
                                                </li>
                                                <li role="presentation">
                                                    <a role="menuitem" tabindex="-1"
                                                       href="#modal-container-del-${count}" data-toggle="modal">删除</a>
                                                </li>
                                            </ul>
                                            <div align="left" class="modal fade" id="modal-container-rename-${count}"
                                                 role="dialog"
                                                 aria-labelledby="myModalLabel"
                                                 aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">×
                                                            </button>
                                                            <h4 class="modal-title" style="color:#141721">
                                                                重命名相册
                                                            </h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form role="form"
                                                                  action="<%=request.getContextPath()%>/RenameAlbum"
                                                                  method="post">
                                                                <div class="form-group">
                                                                    <label style="color:#141721">新的相册名：</label>
                                                                    <input type="hidden" name="albumId"
                                                                           value="${album.getId()}"/>
                                                                    <input type="text" class="form-control"
                                                                           name="renameAlbumName"
                                                                           placeholder="请输入新的相册名称"/><br/>
                                                                    <button type="submit"
                                                                            class="btn btn-primary center-block">确定
                                                                    </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div align="left" class="modal fade" id="modal-container-del-${count}"
                                                 role="dialog"
                                                 aria-labelledby="myModalLabel"
                                                 aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">×
                                                            </button>
                                                            <h4 class="modal-title text-white-50">
                                                                删除相册
                                                            </h4>
                                                        </div>
                                                        <div class="modal-body center-block">
                                                            <label class="text-info">你确定删除相册：${album.getName()}吗?</label>
                                                        </div>
                                                        <div class="modal-footer center-block">
                                                            <a href="<%=request.getContextPath()%>/DeleteAlbum?albumId=${album.getId()}"
                                                               class="btn btn-primary">确认</a>
                                                            <button class="btn btn-success" data-dismiss="modal">取消
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div align="right">
                                            <a href="<%=request.getContextPath()%>/Album?albumId=${album.getId()}"
                                               class="btn btn-sm btn-info">查看相册</a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
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
