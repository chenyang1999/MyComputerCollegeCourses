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
            padding-top: 75px;
            color: white;
        }
    </style>
    <title>美好就在这里</title>
</head>
<body class="body">
<div class="modal fade" id="modal-container-logout" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="color: black">
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
        <div class="col-md-1 column">
        </div>
        <div class="col-md-10 column">
            <div class="thumbnail">
                <img alt="300x200" src="<%=request.getContextPath()%>${photo.getPhotopath()}"/>
            </div>
        </div>
        <div class="col-md-1 column">
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" action="<%=request.getContextPath()%>/Comment" method="post">
                <div class="form-group">
                    <label style="font-size: large">请在这里留下你善意的评论吧</label>
                    <input type="text" style="font-size: larger" class="form-control" name="comment"
                           placeholder="精彩的评论即将在这里书写"/>
                    <input type="hidden" name="photoId" value="${photo.getId()}">
                </div>
                <button type="submit" class="btn btn-info btn" style="float: right">评论</button>
            </form>
            <c:choose>
                <c:when test="${comments.size() eq 0}">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <label style="font-size: x-large">
                                还没有人评论哎，等着你的精彩评论！
                            </label>
                        </div>
                    </div>
                    <br/>
                </c:when>
                <c:otherwise>
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <label style="font-size: x-large">
                                来看看大家的评论：
                            </label>
                        </div>
                    </div>
                    <br/>
                    <c:forEach items="${comments}" var="comment">
                        <div class="row  img-rounded" style="background: #6c757d">
                            <div class="col-md-2 column" style="padding-top: 20px">
                                <blockquote>
                                    <label>
                                <span class="glyphicon glyphicon-user"
                                      style="color:#d6d8d9"></span>&nbsp;${comment.getUsername()}
                                    </label>
                                </blockquote>
                            </div>
                            <div class="col-md-8 column" style="padding-top: 30px">
                                <label style="font-size: larger">
                                        ${comment.getComment()}
                                </label>
                            </div>
                            <div class="col-md-2 column center-block" style="padding-top: 20px;" align="right">
                                <c:if test="${(not empty userid)and (userid eq comment.getUserid())}">
                                    <a href="<%=request.getContextPath()%>/DeleteComment?commentId=${comment.getId()}&photoId=${photo.getId()}">
                                    <span class="glyphicon glyphicon-remove-circle btn"
                                          style="color:#a0a5a0;font-size: xx-large">
                                    </span>
                                    </a>
                                </c:if>
                                <span class="glyphicon glyphicon-thumbs-up btn"
                                      style="color:#efffff;font-size: xx-large">
                                </span>
                            </div>
                            <br/>
                        </div>
                        <br/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
