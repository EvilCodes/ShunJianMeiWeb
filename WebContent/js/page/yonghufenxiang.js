//领取优惠券
function updateCoupon(){
	
	//手机号
	var mobile = $("#mobile").val();
	if(checknull(mobile)){
		alert("请输入手机号！");
		$("#mobile").focus();
		return;
	}
	
	//验证手机号
	if(!(/^1[3|4|5|7|8][0-9]\d{8}$/.test(mobile))){ 
		alert("手机号格式不正确！");
		$("#mobile").focus(); 
        return; 
    } 
	
	//评论ID
	var evaid = $("#hidEvaid").val();
	var editurl = projectPath + "/updateCoupon";
	
	$.post(editurl, {
		"evaid": evaid,
		"mobile" : mobile
	}, function(data) {
		if (data.editInfo) {
			alert(data.message);
		} else {
			alert(data.message);
		}
	}, "json");
}

function checknull(val){
	if(null == val || "" == val || val.length == 0){
		return true;
	}else{
		return false;
	}
}

//跳转中间页面
function goAppDownload(){
	window.location.href = projectPath + "/goAppDownload";
	return;
}

//安卓下载
function appDownload(){
	window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=cc.ruit.shunjianmei&g_f=991653";
	return;
}

//ios下载
function iOSDownload(){
	window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=cc.ruit.shunjianmei&g_f=991653";
	return;
}
