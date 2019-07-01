/**
 * 回复评论
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
	 var userid = $("#hidden-userid").val();
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
				return false;
	
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
 $(".reply_button").click(function(){
 	$this=$(this);
 	$desc = $("#desct_input1")
 	var picid  = $desc.parent().parent().siblings().find(".displaypic-div").children("img").attr("value");
	    var piccomment = $desc.val();
	    var commentid=$this.siblings(".comment-name").text();
	    var commentname=$this.siblings(".comment-name").attr("value");
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
 			 
 		 }
 	 });
 });
 	

 
 /**
  * 实现评论展示
  */
 function comment1(data){
 	var userid = $("#hidden-userid").val();
 	if(userid==data.userid){
 		var delmsg ="<p class='reply_button' value='"+data.id+"' onclick='deletemsg(this)'>删除</p>";
 	}else{
 		var delmsg="<p class='reply_button'>回复</p>";
 		//新添元素必须手动为其绑定事件
 		$(".reply_button").attr("onclick","reply(this)");
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
	
	