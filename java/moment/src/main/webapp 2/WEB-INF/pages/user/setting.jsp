<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账号设置</title>
    <link href="${path}/resources/user/css/bootstrap.min.css"
	rel="stylesheet" //>
    <link href="${path}/resources/user/css/cropper.min.css" rel="stylesheet">
    <link href="${path}/resources/user/css/main.css" rel="stylesheet">
    <link rel="shortcut icon"  href="${path}/resources/user/img/icon.jpg">
    <link rel="stylesheet" href="${path}/resources/user/css/setting.css" />
    <style type="text/css">
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
                <img src="${path}/resources/user/img/user.jpg" class="userImg userImgCenter">
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
    <h4>账号设置</h4>
    <hr>
    <form enctype="multipart/form-data" method="post">
        <div class="form_group">
            <label>用户昵称</label>
            <input type="text" placeholder="请输入用户昵称" value="${user.name }">
            
        </div>
        <hr>
        <div class="form_group">
            <label>用户头像</label>
            <div class="userimg_block" id="crop-avatar">

             <!-- Current avatar -->
            <div class="avatar-view" title="Change the avatar">
                <img src="${path}/resources/user/img/user.jpg" alt="Avatar">
            </div>

            <!-- Cropping modal -->
            <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <form class="avatar-form" action="crop.php" enctype="multipart/form-data" method="post">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal" type="button">&times;</button>
                                <h4 class="modal-title" id="avatar-modal-label">上传头像</h4>
                            </div>
                            <div class="modal-body">
                                <div class="avatar-body">

                                    <!-- Upload image and data -->
                                <div class="avatar-upload">
                                    <input class="avatar-src" name="avatar_src" type="hidden">
                                    <input class="avatar-data" name="avatar_data" type="hidden">
                                    <label for="avatarInput" style="width:160px;">选择头像</label>
                                    <input class="avatar-input" id="avatarInput" name="avatar_file" type="file">
                                </div>

                                    <!-- Crop and preview -->
                                <div class="row">
                                    <div class="col-md-9">
                                        <div class="avatar-wrapper"></div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="avatar-preview preview-lg"></div>
                                        <div class="avatar-preview preview-md"></div>
                                        <div class="avatar-preview preview-sm"></div>
                                    </div>
                                </div>

                                <div class="row avatar-btns">
                                    <div class="col-md-9">
                                        <div class="btn-group">
                                        <button class="btn btn-primary" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees">左转</button>
                                        </div>
                                        <div class="btn-group">
                                        <button class="btn btn-primary" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees">右转</button>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <button class="btn btn-primary btn-block avatar-save" type="submit">提交</button>
                                    </div>
                                </div>
                                </div>
                            </div>
            <!-- <div class="modal-footer">
              <button class="btn btn-default" data-dismiss="modal" type="button">Close</button>
            </div> -->
                        </form>
                    </div>
                </div>
            </div><!-- /.modal -->

            </div>
           
        </div>
         <hr>
        <div class="form_group">
            <label>绑定邮箱</label>
            <input type="text" placeholder="未绑定" disabled="disabled" value="${user.email }">
            
        </div>
        <hr>
        <div class="form_group">
            <label>绑定手机</label>
            <input type="text" placeholder="未绑定" disabled="disabled" value="${user.phonum }">
           
        </div>
         <hr>
        <div class="form_group pwd_block">
            <label>登录密码</label>
            <button class="set_pwd">编辑</button>
            <div class="hidden">
                <div class="form_group" >
                    <label>旧密码</label>
                    <input type="password" placeholder="请输入旧密码" >

                    <p class="red oldFlag">密码错误</p>
                </div>
                <div>    
                    <label>新密码</label>
                    
                    <input type="password" placeholder="请输入新密码" disabled>
                    <p class="red newFlag">密码格式不正确</p>
                </div>
                <div>
                    <label>确认密码</label>
                    <input type="password" placeholder="请再次输入密码" disabled>
                    
                    <p class="red againFlag">两次密码不同</p>
                </div>
            </div>
        </div>
          <hr>
        <button class="sub_btn">提交修改</button>
    </form>
</div>
<fotter>
</fotter>
<script src="${path}/resources/user/js/jquery-2.2.3.min.js"></script>
<script src="${path}/resources/user/js/bootstrap.min.js"></script>
<script src="${path}/resources/user/js/jquery.form.js"></script>
<script src="${path}/resources/user/js/cropper.min.js"></script>
<script src="${path}/resources/user/js/userImg.js"></script>
<script src="${path}/resources/user/js/setting.js"></script>
</body>
</html>