
$(function(){
	//确定
	$("#btnOK").click(function(){
		
		var loginName = $("#loginName").val();
		var userPwd = $("#userPwd").val();
		
		if(loginName == ""){
			alert("请输入ID！");
			$("#loginName").focus();
			return;
		}
		
		if(userPwd == ""){
			alert("请输入密码！");
			$("#userPwd").focus();
			return;
		}
		
		var addurl = projectPath + "/StoreinfoLogin";
		$.post(addurl,{
			"loginName" : loginName,
			"userPwd" : userPwd
			},
			function(data){
				if(data.editInfo == true){
					if(data.storeinfoState == 2){
						window.location.href = projectPath + "/goRiZhiGuanLi"
					}else{
						window.location.href = projectPath + "/goStoreinfoValidateInfo"
					}
				}else{
					alert(data.message);
					$("#loginName").val("");
					$("#userPwd").val("");
				}
				//$.messager.alert("操作提示", data.message, "error");
			}, "json");
	});
	
});
