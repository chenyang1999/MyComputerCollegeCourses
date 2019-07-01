jQuery(document).ready(function($) {
	$(".userImgCenter").click(function(){
		$(".centerList").toggleClass("hidden");
	});
	$("button").click(function(){
		$("button").toggleClass("orange");
	});
	$(".set_pwd").click(function(){
		$(".pwd_block>div").toggleClass("hidden");
	});


});