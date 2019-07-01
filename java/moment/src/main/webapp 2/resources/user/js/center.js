jQuery(document).ready(function($) {
	$(".userImgCenter").click(function(){
		$(".centerList").toggleClass("hidden");
	});
	$("button").click(function(){
		$("button").toggleClass("orange");
	});
	//实现栏目的切换
	$("#picnum").click(function(){
		if($("#collnum").hasClass("grey")){
			$("#collnum").removeClass("grey");
			$(".coll_waterfall").addClass("hidden");
		}else if($("#likenum").hasClass("grey")){
			$("#likenum").removeClass("grey");
			$(".like_waterfall").addClass("hidden");
		}
		$("#picnum").addClass("grey");
		$(".pic_waterfall").removeClass("hidden");
	});
	$("#collnum").click(function(){
		if($("#picnum").hasClass("grey")){
			$("#picnum").removeClass("grey");
			$(".pic_waterfall").addClass("hidden");
		}else if($("#likenum").hasClass("grey")){
			$("#likenum").removeClass("grey");
			$(".like_waterfall").addClass("hidden");
		}
		$("#collnum").addClass("grey");
		$(".coll_waterfall").removeClass("hidden");
	});
	$("#likenum").click(function(){
		if($("#collnum").hasClass("grey")){
			$("#collnum").removeClass("grey");
			$(".coll_waterfall").addClass("hidden");
		}else if($("#picnum").hasClass("grey")){
			$("#picnum").removeClass("grey");
			$(".pic_waterfall").addClass("hidden");
		}
		$("#likenum").addClass("grey");
		$(".like_waterfall").removeClass("hidden");
	});

});