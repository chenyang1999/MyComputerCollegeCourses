jQuery(document).ready(function($) {
	$(".userImgCenter").click(function(){
		$(".centerList").toggleClass("hidden");
	});
	$("button").click(function(){
		$("button").toggleClass("orange");
	});
	$("span.set_pwd").click(function(){
		$(".pwd_block>div").toggleClass("hidden");
	});
	//密码验证
	$("#OldPassword").blur(function(){
		id=$("#userIDHidden").val();
		password=$("#OldPassword").val();
		var options={
				target:null,
				url:'/moment/user/validate.action?id='+id+"&password="+password,
				success:function(data){
					dataobj=JSON.parse(data);
					if(dataobj.status==1){
						$("#newPassword").removeAttr("disabled");
						$("#passwordAgain").removeAttr("disabled");
					}else{
						$(".oldFlag").removeClass("hidden");
					}
				},
				error:function(){
					$(".oldFlag").removeClass("hidden");
				}
		};
		//使用表单控件，进行异步更新数据
		$("#userForm").ajaxSubmit(options);
		return false;
	});
	$("#subBtn").click(function(){
		var options={
				target:null,
				url:'/moment/user/update.action',
				success:function(data){
					dataobj=JSON.parse(data);
					if(dataobj.status==1){
						alert(dataobj.msg);
						window.location.href=window.location;
					}else{
						alert(dataobj.msg);
					}
				},
				error:function(){
					alert("更新失败，请稍后再试");
				}
		};
		//使用表单控件，进行异步更新数据
		$("#userForm").ajaxSubmit(options);
		return false;
	});
});