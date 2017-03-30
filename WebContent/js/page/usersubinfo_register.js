
function doRegister(){
	var bindphone = $.trim($("#bindphone").val());
	var password = $.trim($("#password").val());
	
	//获取验证码
	var verificationCode = $.trim($("#verificationCode").val());
	var confirmPassword = $.trim($("#confirmPassword").val());
	
	if(checknull(bindphone)){
		alert("请输入手机号！");
		$("#bindphone").focus();
		return;
	}
	if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(bindphone))){ 
		alert("手机号格式不正确！");
		$("#bindphone").focus(); 
        return; 
    } 
	
	if(checknull(verificationCode)){
		alert("请输入验证码！");
		$("#verificationCode").focus();
		return;
	}
	if(checknull(password)){
		alert("请输入密码！");
		$("#password").focus();
		return;
	}
	if(password.length < 6 || password.length > 12){
		alert("请输入6-12位密码！");
		$("#password").focus();
		return;
	}
	
	if(password != confirmPassword){
		alert("两次密码输入不一致，请确认！");
		$("#confirmPassword").focus();
		return;
	}
	
	var registerUrl = projectPath + "/doUsersubinfoRegister";
	$.post(registerUrl,{
		"bindphone" : bindphone,
		"password" : password,
		"verificationCode" : verificationCode
		},
		function(data){
			if(data.info == true){
				top.location.href= projectPath + "/goPersonal";
			}else{
				alert(data.msg);
			}
		}, "json");
}



function getverificationCode(btn){
	
	var bindphone = $.trim($("#bindphone").val());
	
	if(checknull(bindphone)){
		alert("请输入手机号！");
		$("#bindphone").focus();
		return;
	}
	
	if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(bindphone))){ 
		alert("手机号格式不正确！");
		$("#bindphone").focus(); 
        return; 
    }
	
	var url = projectPath + "/getCommonSendVCode";
	$.post(url,{
		"userName" : bindphone
		},
		function(data){
			if(data.info == true){
				//限制60秒后再次获取验证码
				time(btn);
			}else{
				alert(data.msg);
			}
		}, "json");
	
}

//限制60秒后再次获取验证码
var wait = 60;
function time(o) {
	if (wait == 0) {
		o.removeAttribute("disabled");
		o.value = "获取验证码";
		wait = 60;
	} else {
		o.setAttribute("disabled", true);
		o.value = "重新发送(" + wait + ")";
		wait--;
		setTimeout(function() {
			time(o)
		}, 1000)
	}
}

function checknull(val){
	if(null == val || "" == val || val.length == 0){
		return true;
	}else{
		return false;
	}
}