/**
 * Created by lzw on 2017/5/1.
 */
//验证账号是否已经存在

function checkAccount(){
    var account =document.getElementById("regs_account");
    var error_msg = document.getElementById("account_error");
    //alert(account.value);
    //var email_reg = new RegExp("/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
    var email_reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    var pho_reg = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
    var options={
			target:null,
			url:"/moment/user/checkAccount.action?account="+account.value,
			success:function(data){
				dataobj=JSON.parse(data);
				if(dataobj.status==0){
					$("#account_error").text(dataobj.msg);
					 error_msg.style.display="block";
			            error_msg.style.color="red";
			            return false;
				}else{
					 if(email_reg.test(account.value)==false){
					        if(pho_reg.test(account.value)==true){
					            error_msg.style.display="none";
					            return true;
					        }
					        else{
					            error_msg.style.display="block";
					            error_msg.style.color="red";
					            return false;
					        }
					    }else{
					        error_msg.style.display="none";
					        return true;
					    }
				}
			},
			error:function(responseTxt,statusTxt,XMLHttpRequest){
				
			}
	};
	//使用表单控件，进行异步更新数据
	$(window).ajaxSubmit(options);
   
}

function checkPwd(){
    var pwd = document.getElementById("pwd").value;
    var secpwd = document.getElementById("secpwd").value;
    var error_msg = document.getElementById("pwd_error");
    //alert(pwd);
    //alert(secpwd);
    if(pwd!=secpwd){
        error_msg.style.display="block";
        error_msg.style.color="red";
        return false;
    }else{
        error_msg.style.display="none";
        return true;
    }
}

function moveout(){

    var movebox = document.getElementById("register_box");
    var iTimer = null;
    var screenwidth = document.body.clientWidth;
    var boxwidth = movebox.offsetWidth;
    var blackbox = document.getElementById("blackbox");

    blackbox.style.display = "block";

    clearInterval(iTimer);  //运动前清除定时器
    iTimer = setInterval(function() {
        if (movebox.offsetLeft == (screenwidth-boxwidth)) {
            clearInterval(iTimer);   //满足条件清除定时器
        } else {
            movebox.style.left = movebox.offsetLeft - 50 + 'px'; //每次向前移动
        }

    }, 30);

}

function movein(){

    var movebox = document.getElementById("register_box");
    var iTimer = null;
    var screenwidth = document.body.clientWidth;
    var boxwidth = movebox.offsetWidth;
    var blackbox = document.getElementById("blackbox");

    blackbox.style.display = "none";

    clearInterval(iTimer);  //运动前清除定时器
    iTimer = setInterval(function() {
        if (movebox.offsetLeft ==(screenwidth+boxwidth)) {
            clearInterval(iTimer);   //满足条件清除定时器
        } else {
            movebox.style.left = movebox.offsetLeft + 50 + 'px'; //每次向前移动
        }

    }, 30);

}

function refreshCode(){
	/*var code = document.getElementById("code");
	var url = code.src;
    url = url.substr(0,url.lastIndexOf('/')+1);
    url = url + (new Date()).valueOf()+".action";
    code.src = url;*/
	var code = $("#code");
	var url = code.prop('src');
    url = url.substr(0,url.lastIndexOf('/')+1);
    url = url + (new Date()).valueOf()+".action";
    code.prop('src',url);
}

