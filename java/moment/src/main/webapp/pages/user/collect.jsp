<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Moment收藏夹</title>
    <link rel="stylesheet" href="${path}/resources/user/css/bootstrap.min.css" />
    <link href="${path}/resources/user/css/cropper.min.css" rel="stylesheet">
    <link href="${path}/resources/user/css/main.css" rel="stylesheet">
    <link rel="shortcut icon"  href="${path}/resources/user/img/icon.jpg">
    <link rel="stylesheet" href="${path}/resources/user/css/center.css" />
    <style type="text/css">
    	 .setting {
            background: url(${path}/resources/userfonts/shezhi.svg) no-repeat 2px 13px;
            background-size: 29px 28px;
            width: 35px;
            height: 55px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .like{
            background: url(${path}/resources/user/fonts/xihuan.svg) no-repeat 1px 3px;
            background-size:  16px 16px;
            width: 35px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .like1{
            background: url(${path}/resources/user/fonts/like1.svg) no-repeat 1px 3px;
            background-size:  16px 16px;
            width: 35px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .collect{
           background: url(${path}/resources/user/fonts/shoucang.svg) no-repeat 0px -1px;
            background-size: 18px 26px;
            width: 35px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .comment{
            background: url(${path}/resources/user/fonts/pinglun.svg) no-repeat -1px 1px;
            background-size: 19px 24px;
            width: 35px;
            display: inline-block;
            border: none;
            background-color: rgba(224, 224, 224, 0.84);
            float: right;
            cursor: pointer;
        }
        .exit{
            background: url(${path}/resources/user/fonts/tuichu.svg) no-repeat 4px 5px;
            background-size: 19px 24px;
            width: 35px;
        }
        .inform{
            background: url(${path}/resources/user/fonts/inform.svg) no-repeat 4px 5px;
            background-size: 19px 24px;
            width: 35px;
        }
        .user{
            background: url(${path}/resources/user/fonts/user.svg) no-repeat 4px 5px;
            background-size: 19px 24px;
            width: 35px;
        }
        .setting1{
            background: url(${path}/resources/user/fonts/setting1.svg) no-repeat 4px 5px;
            background-size: 19px 24px;
            width: 35px;
        }
    </style>
</head>
<body>
<div class="top_content">
       <header>
        <div>
             <ul>
            <li>
                <a href="${path}/user/index.action"><img src="${path}/resources/user/img/logo.png" class="logo"></a>
            </li>
            <li>
                <a href="${path}/user/index.action">首页</a>
            </li>
            <li>
                <a href="">外拍</a>
            </li>
            <li>
                <a href="">论坛</a>
            </li>
            <li>
                <a href="">干货</a>
            </li>
            <li class="userCenter">
                <img src="${user.img}" class="userImg userImgCenter">
                    <ul class="centerList hidden">
                        <li>
                            <a href="${path}/user/center.action" class="user">个人中心</a>
                        </li>
                        <li>
                            <a href class="inform">重要通知</a>
                        </li>
                        <li>
                            <a href="${path}/user/setting.action" class="setting1">账号设置</a>
                        </li>
                        <li>
                            <a href="${path}/user/logout.action" class="exit">退出账号</a>
                        </li>
                    </ul>
            </li>
        </ul>
        </div>
       
    </header>
</div>
<div class="container">
<div class="detail_block">
		<div>
			<img src="${user.img}">
			<div>
				<div>
					<ul class="imgBelow">
						<li>
							<span class="fansnum">${user.fansnum }</span><p>粉丝</p>
						</li>
						<li>
							<span class="concernnum">${user.concernnum }</span><p>关注</p>
						</li>
					</ul>
				</div>
			</div>
			<div>
				<h4 class="username">${user.name }</h4>
				<a href="${path}/user/setting.action"><button>账号设置</button></a>
			</div>
		</div>
	</div>
	

	<div class="like_waterfall">
		<ul>
			<li>
				<img src="${path}/resources/user/img/test.jpg">
                <div class="pic_detail">
                    <h4 class="picname">图片名称孩图片名称孩</h4>
                    <p class="picdesc">图片描述图片描述</p>
                </div>
                 <div class="owner_detail">
                        <img src="${path}/resources/user/img/user.jpg" class="ownerImg">
                        <div>
                            <span class="ownername">XXX</span>
                            <span class="ownergrade">LV0</span>
                            <p>发表图片<p>
                        </div>
                        <p class="time">2017-04-30</p>
                </div>
			</li>
		</ul>
	</div>
</div>
<footer>
</footer>
<script src="${path}/resources/user/js/jquery-2.2.3.min.js"></script>
<script src="${path}/resources/user/js/bootstrap.min.js"></script>
<script src="${path}/resources/user/js/jquery.form.js"></script>
<script src="${path}/resources/user/js/center.js"></script>
<script src="${path}/resources/user/js/cropper.min.js"></script>
<script src="${path}/resources/user/js/main.js"></script>
</body>
</html>