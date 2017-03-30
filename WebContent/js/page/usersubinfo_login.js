function goUsersubinfo_login() {
	var loginName = $.trim($("#loginName").val());
	var password = $.trim($("#password").val());
	
	if(loginName == ""){
		//$.messager.alert("操作提示","登录名不能为空！","error");
		alert("用户名不能为空！");
		$("#loginName").focus();
		return;
	}
	
	if(password == ""){
		alert("密码不能为空！");
		$("#password").focus();
		return;
	}
	var loginUrl = projectPath + "/doUserSubInfoLogin";
	$.post(loginUrl,{
		"loginName" : loginName,
		"password" : password
		},
		function(data){
			if(data.info == true){
				location.href= projectPath + "/goUsersubinfo_verification";
			}else{
				alert("用户名或密码错误！");
				//top.location.href= projectPath + "/goUsersubinfoRegister";
			}
		}, "json");
}

function goUsersubinfo_register() {
	location.href= projectPath + "/goUsersubinfoRegister";
}
