
	//声明图片集合对象，为全局变量
	var piclist;
	//声明用户关注对象对象，为全局变量，而且是数组对象，后面会用到数组对象的indexOf()方法
	var watchList=new Array();

	/**
	 * 获取用户中心的下拉列表
	 */
	$(".userImgCenter").click(function(){
		$(".centerList").toggleClass("hidden");
	});
	
	
	/**
	 * 举报的异步提交
	 */
	$("#report_submit_btn").click(function(){
		//不为空再提交数据
		if($("#desct_input").val()!=null&&$("#desct_input").val()!=""){
			var options={
					target:null,
					url:'/moment/report/doReport.action',
					success:function(data){
						dataobj=JSON.parse(data);
						if(dataobj.status==1){
							alert(dataobj.msg);
							window.location.href="/moment/user/index.action";
						}else{
							alert("举报失败，请稍后再试");
						}
					},
					error:function(responseTxt,statusTxt,XMLHttpRequest){
						alert("举报失败，请稍后再试");
						alert("Error: "+XMLHttpRequest.status+": "+XMLHttpRequest.statusText);
					}
			};
			//使用表单控件，进行异步更新数据
			$('#report_form').ajaxSubmit(options);
			//防止页面进行刷新
			return false;
		}else{
			alert("具体的举报内容可以让我们更好帮助您！");
			return false;
		}
		
	});
	
	
	/**
	 * 获取摄影日志
	 */
	function getCalendar(){
		var options={
				target:null,
				url:'/moment/calendar/getCalendar.action',
				success:function(data){
					dataobj=JSON.parse(data);
					$(".picCalendar img").attr("src",dataobj.picpath);
					$(".calendar_description").text(dataobj.name);
				}
		};
		//使用表单控件，进行异步更新数据
		$(".picCalendar").ajaxSubmit(options);
		date=new Date();
		$(".cal_time").text(date.getFullYear()+"."+(date.getMonth()+1)+"."+date.getDate());
		var week=['周日','周一','周二','周三','周四','周五','周六'];
		$(".cal_weekday").text(week[date.getDay()]);
		$(".day").text(date.getDate());
		//防止页面进行刷新
		return false;
	}
	
	/**
	 * 获取图片展示,准备数据
	 */
	function getPicList(){
		var options={
				target:null,
				url:'/moment/pic/getPicList.action',
				success:function(data){
					piclist=JSON.parse(data);
					console.log(piclist);
				},
			    error:function(){
			    	alert('更新出错');
			    }
		};
		//使用表单控件，进行异步更新数据
		$(window).ajaxSubmit(options);
		//防止页面进行刷新
		return false;
	}

	/**
	 * 点击搜索栏的按钮，会进行对图片名称以及图片描述的关键字查询
	 */
	$("#key").next("button").click(function(){
		var type = $("#key").val();
		console.log(type) ;
		$.ajax({
			data:{'pickey':type},
			url:'/moment/pic/getPicListByCondition.action',
			dataType:"json",
			success:function(data){
				
				$("#waterfall_ul").children("li").not("#main_window").remove() ;
				piclist = data;
				imgLocation();
				loadPicList() ;
				
			}
		});
		//防止页面进行刷新
		return false;
	});
	
	/**
	 * 以下部分实现图片瀑布流
	 */
	$(window).on("load",function(){
        imgLocation();
        loadPicList() ;
        getWatchList();
         window.onscroll=function(){
        //通过屏幕的滚动来加载图片
            if(scrollside()){
            	loadPicList() ;
        }      
     }
 });
	
	/**
	 * 拼接代码的方式加载图片的呈现方式
	 */
	function loadPicList(){
        var waterfallUl = document.getElementById('waterfall_ul');
        for(i in piclist){
            //创建li
            var pic_card= document.createElement('li');
            pic_card.className = 'pic_card';
            waterfallUl.appendChild(pic_card);
            //创建div
            var piccardDiv= document.createElement('div');
            piccardDiv.className = 'piccardDiv';
            pic_card.appendChild(piccardDiv);
            //创建img
            var img = document.createElement('img');
            piccardDiv.appendChild(img);
            img.className = "picCard_img";
            img.id  = piclist[i].id;
             img.src = piclist[i].picpath;
            img.style.height = 'auto';
            //创建picDetail
            var picDetail = document.createElement('div');
            picDetail.className = 'pic_detail';
            piccardDiv.appendChild(picDetail);
            //创建picdetail中的标题
            var picname = document.createElement('h4');
            picname.className = 'picname';
            picname.innerHTML=piclist[i].name;
            picDetail.appendChild(picname);
            //创建图片描述
            var picdesc = document.createElement('p');
            picdesc.className = 'picdesc';
            picdesc.innerHTML=piclist[i].description;
            picDetail.appendChild(picdesc);
            //创建详细信息
            var ul1=document.createElement('ul');
            picDetail.appendChild(ul1);
            //创建点赞模块
            var like=document.createElement('li');
            like.className = 'like';
            ul1.appendChild(like);
            //创建点赞数
            var piclike=document.createElement('span');
            piclike.className = "piclike piclike-"+ piclist[i].id +"";
            piclike.onclick = function (){
            	praise( $("#hidden-userid").val() ,this);
            }
            piclike.innerHTML=piclist[i].piclike;
            like.appendChild(piclike);
            //创建收藏模块
            var collect=document.createElement('li');
            collect.className = 'collect';
            ul1.appendChild(collect);
            //创建收藏数
            var piccollect=document.createElement('span');
            piccollect.className = 'piccollect';
            piccollect.innerHTML=piclist[i].collect;
            collect.appendChild(piccollect);
            //创建评论模块
            var comment=document.createElement('li');
            comment.className = 'comment';
            comment.onclick = function(){
            	 $displaymodal = $('#displayModal');
            	 $displaymodal.modal('show');
            	 $this = $(this);
            	 var like = $this.siblings("li:eq(0)").children("span").text();
            	 var collect = $this.siblings("li:eq(1)").children("span").text();
            	 var picusername= $this.parent().parent().siblings(".owner_detail").find("span:eq(0)").text();
            	 var piccreatetime= $this.parent().parent().siblings(".owner_detail").find(".time").text();
            	 var picusergrade=$this.parent().parent().siblings(".owner_detail").find("span:eq(1)").text();
            	 var picuserId=$this.parent().parent().siblings(".owner_detail").find("span:eq(2)").text();
            	 var commentnum = $this.children("span").text();
            	 var picname = $this.parent().siblings("h4").text();
            	 var picdes = $this.parent().siblings("p").text();
            	 var imgpath = $this.parent().parent().siblings("img").attr("src");
            	 var userimg = $this.parent().parent().siblings(".owner_detail").children("img").attr("src");
            	 var picid = $this.parent().parent().siblings("img").attr("id");
            	 $displaymodal.find("img:eq(0)").attr("src", imgpath);
            	 $displaymodal.find("img:eq(0)").attr("value",picid);
            	 $displaymodal.find("img:eq(1)").attr("src",userimg);
            	 $displaymodal.find("span:eq(0)").text(like);
            	 $displaymodal.find("span:eq(1)").text(collect);
            	 $displaymodal.find("span:eq(2)").text(commentnum);
            	 $displaymodal.find(".pic-detail").children("p:eq(0)").text(picname);
            	 $displaymodal.find(".pic-detail").children("p:eq(1)").text(picdes);
            	 $displaymodal.find(".user_detail").children("span:eq(0)").text(picusername);
            	 $displaymodal.find(".user_detail").children("span:eq(1)").text(picusergrade);
            	 $displaymodal.find(".user_detail").children("span:eq(2)").text(picuserId);
            	 $displaymodal.find(".user_detail").children("p:eq(1)").text(piccreatetime);
            	 $displaymodal.find(".other-comment").children().remove();
            	//判断这个用户是否在关注列表中，如果是的话，则显示已关注
            	 //用js数组的indexOf判断，如果不存在，索引值为-1
            	 if(watchList.indexOf(picuserId)!=-1){
            		 $displaymodal.find(".watch").addClass("hidden");
            		 $displaymodal.find(".watched").removeClass("hidden");
            	 }else{
            		 $displaymodal.find(".watched").addClass("hidden");
            		 $displaymodal.find(".watch").removeClass("hidden");
            	 }
            	 $.ajax({
            		 url: '/moment/comment/checkcomment.action',
            	        type: 'POST',
            	        data: {
            	        	'id': picid, 
            	        },
            	        dataType: "JSON",
            	    success: function(data){  	
            	    	for(i=0;i<data.length;i++){
            	    		var commentjs = comment1(data[i]);
            	    		$displaymodal.find(".other-comment").append(commentjs);
            	    	}
            	    }
            	 });
            }
            ul1.appendChild(comment);               
            //创建评论数
            var piccomment=document.createElement('span');
            piccomment.className = 'piccomment';
            piccomment.innerHTML=piclist[i].comment;
            comment.appendChild(piccomment);
            //创建举报模块
            if($(".picCalendar").length!=0){
            	var report=document.createElement('li');
                report.className = 'report';
                report.innerHTML='举报';
                $(".report").attr({'data-toggle':'modal','data-target':'#reportModal','data-toggle':'modal','data-target':'#reportModal'});
                ul1.appendChild(report);
                $(".watch_div").removeClass("hidden");
            }
             //创建ownerDetail
            var ownerDetail = document.createElement('div');
            ownerDetail.className = 'owner_detail';
            piccardDiv.appendChild(ownerDetail);
            //创建用户头像
            var userimg = document.createElement('img');
            ownerDetail.appendChild(userimg);
            userimg.src = piclist[i].user.img;
            userimg.className = 'ownerImg';
            //创建新的div
            var div=document.createElement('div');
            ownerDetail.appendChild(div);
           
            //创建ownername
            var ownername=document.createElement('span');
            div.appendChild(ownername);
            ownername.innerHTML=piclist[i].user.name;
            //创建等级
            var ownergrade=document.createElement('span');
            div.appendChild(ownergrade);
            ownergrade.innerHTML="&nbsp;&nbsp;&nbsp;"+piclist[i].user.grade.grade;
            //创建id
            var ownerId=document.createElement('span');
            div.appendChild(ownerId);
            ownerId.innerHTML=piclist[i].user.id;
            ownerId.className = 'ownerId';
            $(".ownerId").addClass("hidden");
            //发表图片
            var publish=document.createElement('p');
            div.appendChild(publish);
            publish.innerHTML="发表图片";
            //时间
             var time=document.createElement('p');
             ownerDetail.appendChild(time);
            time.className='time';
            time.innerHTML=piclist[i].time.split(" ")[0];
        }
        imgLocation();
    }  

/**
* 瀑布流主函数，定位新出现的图片的位置
* @param  wrap  [Str] 外层元素的ID
* @param  box   [Str] 每一个box的类名
*/
	function imgLocation(){
	    //  1.获得外层以及每一个box
	    var pic_card =$('.pic_card');
	    var boxWidth;
	    if(pic_card!=null){
	    	boxWidth=pic_card.eq(1).width();
	    }else{
	    	boxWidth=0;
	    }
	    
	        /*
	            通过qu(0)来获取第一个盒子的宽度
	            宽度都相同，因此获取那个box的宽度都可以。
	         */
	    var num=Math.floor(1200/boxWidth);
	        /*
	            计算一排能放几个图片 num
	            取整数。
	         */
	    var boxArr=[];
	    pic_card.each(function(index,value){
	        /*
	            index:确定从哪个图片开始
	            value：确定当前是哪个对象
	         */
	        // console.log(index+"  "+value);
	        var boxHeight = pic_card.eq(index).height();
	            /*
	            获取每个盒子的高度
	            其中第一排直接放在数组中。
	             */
	        if(index<num){
	            boxArr[index] = boxHeight;
	        }else{
	            var minBoxHeight=Math.min.apply(null,boxArr);
	            var minBoxIndex=$.inArray(minBoxHeight,boxArr);
	            /*
	                当放置第二排时，需要考虑第一排的高度
	                获取最小盒子的高度
	                从数组中获取最小盒子的位置，从而为了放置下一排的盒子。
	             */
	             $(value).css({
	                 'position':'absolute',
	                 'top':minBoxHeight,
	                 'left':pic_card.eq(minBoxIndex).position().left
	             });
	             /*
	                获取图片的对象，然后对图像进行操作。
	                实际上操作的是box，位置的放置通过CSS来控制。
	              */
	             boxArr[minBoxIndex]+=pic_card.eq(index).height();
	      
	   
	        }
	    });
	}


/**
* 计算出滑动滑动条时新一列图片的添加位置
*/
	function scrollside(){
	    var pic_card=$(".pic_card");
	    var lastboxHeight;
	    if(pic_card.length!=0){
	    	lastboxHeight = pic_card.last().get(0).offsetTop+Math.floor(pic_card.last().height()/2);
		}else{
			lastboxHeight=0;
		}
	        /*
	            获取最后一个盒子的高度也就是最高的盒子的高度。
	         */
	    var documentHeight=$(document).width();
	        //获取当前屏幕的高度
	    var scrollHeight=$(window).scrollTop();
	        //获取窗口的滚动高度 滚动到距顶端的位置

	    return (lastboxHeight<scrollHeight+documentHeight)?true:false;
	    /*
	        当前文档的高度+滚动的高度大于最高的图片的高度的时候，进行图片的加载。
	     */
	}	

	
/**
 * 获取登陆用户关注用户列表
 * */
function getWatchList(){
	var watchuserId=$(".userId").text();
	var options={
			target:null,
			url:'/moment/concern/getListBywatchuserId.action?watchuserId='+watchuserId,
			success:function(data){
				watchList=data;
			},
			error:function(responseTxt,statusTxt,XMLHttpRequest){
				console.log("Error: "+XMLHttpRequest.status+": "+XMLHttpRequest.statusText);
			}
	};
	$(window).ajaxSubmit(options);
	return false;
}

/**
 * 点赞功能的实现
 */
function praise( username,element) {
	$pic = $(element);
	$picparent = $pic.parent("li");
	var picnum = parseInt($pic.text());
	var picid = $pic.parent().parent().parent().siblings("img").attr("id");
	if(username!=""){
    $.ajax({
        url: '/moment/PiclikeController/dolike.action',
        type: 'POST',
        data: {
        	'picid': picid, 
        },
        dataType: "JSON",
    success: function(data){
    	if(data.status==1){
    	  $pic.text(picnum+1);
        	  $picparent.removeClass();
        	  $picparent.addClass('like1');
    	}else{
    	  $pic.text(picnum-1);
        	  $picparent.removeClass();
        	  $picparent.addClass('like');
    	}
      }
	})             
  }else{
	  alert("请先进行登录");
	  window.location.href="/moment/user/login.action";
  }
}



/**
 * 实现评论展示
 */
function comment1(data){
	var userid = $(".userId").text();
	if(userid==data.userid){
		//新添元素必须手动添加事件
		var delmsg ="<p class='reply_button' value='"+data.id+"' onclick='deletemsg(this)'>删除</p>";
	}else{
		var delmsg="<p class='reply_button'  onclick='reply(this)'>回复</p>";
	}
	var commentstr = "<div class='cpmment-content'>"+
	"<img src="+data.userimg+" class='comment-user'>"+
	"<p class='comment-name' value='"+data.userid +"'>"+data.username+"</p>"+"&nbsp:"+
	"<p class='comment-text'>"+data.content+"</p>"+
	delmsg+"</div>"+
    "<hr>";
	var commentstr1 = "<div class='cpmment-content'>"+
	"<img src="+data.userimg+" class='comment-user'>"+
	"<p class='comment-name' value='"+data.userid +"'>"+data.username+"</p>"+"&nbsp:"+
	"<p class='comment-text'>"+data.content+"</p>"+
    "</div>"+
    "<hr>";
	
	var recomment1 = "<div class='cpmment-content'>"+
		"<img src="+data.userimg+" class='comment-user'>"+
		"<p class='comment-name' value='"+data.userid +"'>"+data.username+"</p>"+
		"<p class='reply'>&nbsp;回复&nbsp;</p>"+
		"<p class='comment-name'>"+data.commentname+"</p>"+"&nbsp:"+
		"<p class='comment-text'>"+data.content+"</p>"+
	    "</div>"+
	    "<hr>";
	 
	 var recomment = "<div class='cpmment-content'>"+
	"<img src="+data.userimg+" class='comment-user'>"+
	"<p class='comment-name' value='"+data.userid +"'>"+data.username+"</p>"+
	"<p class='reply'>&nbsp;回复&nbsp;</p>"+
	"<p class='comment-name'>"+data.commentname+"</p>"+"&nbsp:"+
	"<p class='comment-text'>"+data.content+"</p>"+delmsg+
    "</div>"+
    "<hr>";
	 if(userid==""){
		 if(data.commentid==null){
				return commentstr1;	
			}else{
				return recomment1;
			}
     }else{
	     if(data.commentid==null){
			     return commentstr;	
		    }else{
			    return recomment;
		    }
    }
}

/**
 * 实现评论回复判断，如果评论框中出现“回复评论者”，则下面的按钮是“回复”
 */
$("#desct_input1").blur(function(){
	   $this=$(this);
	   //这里不能用text()来访问textarea的值，因为textrarea只会取第一次的值，后面输入框的内容再变化，取得也是第一次的值
		  if($this.val().indexOf(":")!=-1&&$this.val().split(":")[0].substring(0,2)=="回复"){
			  $this.siblings("button").text("回复");
		  }else{
			  $this.siblings("button").text("发表评论");
		  }
	});


/**
 * 点击评论后面的评论二字会在评论框中出现“回复评论者”
 */
function reply(element){
	   var username = $(element).siblings(".comment-name").text();
	   var userid =  $(element).siblings(".comment-name").attr("value");
	   $textarea = $("#desct_input1");
	   $textarea.siblings("button").text("回复");
	   $textarea.siblings("button").attr("onclick","dorecomment("+username+","+userid+")");
	   $textarea.focus();
	   $textarea.text("回复"+username+":");
 }

/**
 *实现评论增加 
*/   
$(".commentbutton").click(function(){
	$userimg = $(".userImgCenter").attr("src")
	$desc = $("#desct_input1")
	var picid  = $desc.parent().parent().siblings().find(".displaypic-div").children("img").attr("value");
	var piccomment = $desc.val();
	 var userid = $(".userId").text();
	 if(userid!=""){
		 if(piccomment!=""){
			 var options={
						target:null,
						url:'/moment/comment/doEdit.action',
						data: {
				        	'picid': picid, 
				        	'content':piccomment,
				        	'userid' :userid
				        },
				        dataType: "JSON",
				        successs:function(data){
				        	//这里并没有完成完整的ajax，没有成功返回data
					    	if(data==1){
					    var commentjs1 = comment1(picomment,$(".userImgCenter").attr("src"));
					        $('#displayModal').find(".other-comment").append(commentjs1);
					       }
					    	
					    },
						error:function(responseTxt,statusTxt,XMLHttpRequest){
							console.log("Error: "+XMLHttpRequest.status+": "+XMLHttpRequest.statusText);
						}
				};
				//使用表单控件，进行异步更新数据
				$(".userImgCenter").ajaxSubmit(options);
				window.location.reload();
	
	}else{
	   alert("评论不能为空");
	   return false;
	}
	 }else{
		 alert("评论请进行登录！");
		 window.location.href="/moment/user/login.action";
	 }
});


/**
 * 评论回复
 */
 function dorecomment(commentname,commentid){
 	$desc = $("#desct_input1")
 	var picid  = $desc.parent().parent().siblings().find(".displaypic-div").children("img").attr("value");
	    var piccomment = $desc.val();
 	 $.ajax({
 		 url:'/moment/comment/dorecomment.action',
 			 type: 'POST',
 		        data: {
 		        	'commentid': commentid, 
 		        	'commentname':commentname,
 		        	'picid': picid, 
 		        	'content':piccomment,
 		        	'userid' :userid
 		        },
 		        dataType: "JSON",
 		 success:function(data){
 			//这里并没有完成完整的ajax，没有成功返回data
 		 }
 	 });
 	window.location.reload();
 }

/**
 * 实现评论删除
 */
  function deletemsg(tha){
	  $this =$(tha)
	  $desc = $("#desct_input1")
	 var picid  = $desc.parent().parent().siblings().find(".displaypic-div").children("img").attr("value");
	 var commentlistid = $this.attr("value");
	  $.ajax({
		  url : '/moment/comment/doDelete.action',
		  type: 'POST',
	        data: {
	        	'id': commentlistid, 
	        	'picid':picid
	        },
	        dataType: "JSON",
		    successs:function(data){
		    	
		    	window.location.reload();
		    }
	        
	  })
	         alert("删除成功");
	         window.location.reload();
  }

  

/**
* 加关注
* */
$(".watch").click(function(){
	var beWatchId=$(".picownerId").text(),
		watchId=$(".userId").text();
	if(watchId!=""&&watchId!=null){
		var options={
				target:null,
				url:'/moment/concern/doAdd.action?beWatchId='+beWatchId+'&watchId='+watchId,
				success:function(data){
					dataobj=JSON.parse(data);
					if(dataobj.status==1){
						$(".watch").addClass("hidden");
						$(".watched").removeClass("hidden");
					}else{
						alert("添加关注失败，请稍后再试");
					}
				},
				error:function(responseTxt,statusTxt,XMLHttpRequest){
					alert("添加关注失败，请稍后再试");
					console.log("Error: "+XMLHttpRequest.status+": "+XMLHttpRequest.statusText);
				}
		};
		//使用表单控件，进行异步更新数据
		$(window).ajaxSubmit(options);
		return false;
	}else{
		alert("请先登录");
	}
	
});


/**
* 取消关注
**/
$(".watched").click(function(){
	var beWatchId=$(".picownerId").text(),
	watchId=$(".userId").text();
	var options={
			target:null,
			url:'/moment/concern/doDel.action?beWatchId='+beWatchId+'&watchId='+watchId,
			success:function(data){
				dataobj=JSON.parse(data);
				if(dataobj.status==1){
					$(".watched").addClass("hidden");
					$(".watch").removeClass("hidden");
				}else{
					alert("取消关注失败，请稍后再试");
				}
			},
			error:function(responseTxt,statusTxt,XMLHttpRequest){
				alert("取消关注失败，请稍后再试");
				console.log("Error: "+XMLHttpRequest.status+": "+XMLHttpRequest.statusText);
			}
	};
	//使用表单控件，进行异步更新数据
	$(window).ajaxSubmit(options);
	return false;
});



	//在首页更新时刷新页面
	getPicList();
	//获取摄影日志
	getCalendar();
	
