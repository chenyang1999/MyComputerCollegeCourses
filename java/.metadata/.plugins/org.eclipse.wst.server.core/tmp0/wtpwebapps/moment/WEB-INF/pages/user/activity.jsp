<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动页面</title>
<style type="text/css">
 		body{
		 	margin: 20px 10px auto ;
		 	}
		a{
			text-decoration: none;
		}
		#nav span{
			float: right;
		}
		#navi select{
			float: right;
			margin-left: 8px;
		}
		#navi{
			margin-top: 10px;
		}
		#container{
		width: 100%;
		height: 300px;
		}
		#img img{
			float:left;
			max-height:300px;
			max-width: 300px;
			padding: 20px;
		}
		#cont{
			float: left;
			width:70%;
		}
		#cont div{
					float: left;
				}
</style>
</head>
<body>
<div id="nav">
    			<a href="">全部></a>
    			<a href="">所有时间></a>
    			<a href="">摄影外拍></a>
    			<select>
    			<option>-选择城市-</option>
    		    </select>
    		    <span><a href="">图文列表|</a><a href="">图片列表</a></span>
    	</div>
    	<div id="navi">
    		<a href="">最新</a>|
    		<a href="">热门</a>|
    		<a href="">今天</a>|
    		<a href="">周末</a>|
    		<a href="">未来一周</a>|
    		<a href="">未来全部</a>|
    		<a href="">活动回顾</a>|
    		<a href="">选择时间</a>
    		<select>
    			<option>不限摄影地区站</option>
    		</select>
    		<select>
    			<option>活动金额</option>
    		</select>
    		<select>
    			<option>发起者</option>
    		</select>
    	</div>
<c:forEach items="${list}" var="activity" varStatus="status">
	<div id="container">
    		<div id="img"><img alt="图片" src="${activity.picpath}"></div>
    		<div id="cont">
    			<h3>${activity.theme} <img src="moment/img/ing.png" name="img" id="ing"></h3>
    			<h6>活动组织者：${activity.organizer}</h6>
    			<p>${activity.content}</p>
    			<div>
    				<p>活动类别:${activity.category}</p>
    				<p>报名人数：0/${activity.number}人</p>
    			</div>
    			<div>
    				<p>活动时间：${activity.atime}</p>
    				<p>活动地点:${activity.place}</p>
    			</div>
    			<div>
    				<p>活动费用：${activity.cost}元/人</p>
    				<p>详细地址：${activity.address}</p>
    			</div>
    		</div>
    	</div>
</c:forEach>
</body>
</html>