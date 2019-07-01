jQuery(document).ready(function($) {
	$(".userImgCenter").click(function(){
		$(".centerList").toggleClass("hidden");
	});
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
});