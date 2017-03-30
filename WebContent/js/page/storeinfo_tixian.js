
$(function(){
	//确定
	$("#btnOK").click(function(){
		var txtAmount = $("#txtAmount").val();
		var txtNumber = $("#txtNumber").val();
		var txtUserName = $("#txtUserName").val();
		var txtBank = $("#txtBank").val();
		var yajin = $("#yajin").val();
		
		if(txtAmount == ""){
			alert("请输入提现金额！");
			$("#txtAmount").focus();
			return;
		}
		if(txtAmount <= 0){
			alert("提现金额不能小于等于0！");
			$("#txtAmount").focus();
			return;
		}
		if(txtAmount.indexOf(".") < 0 ){
			txtAmount += ".0"
		}
		if(parseFloat(txtAmount) > parseFloat(yajin)){
			alert("提现金额不能大于可提取余额！");
			$("#txtAmount").focus();
			return;
		}
		
		if(txtNumber == ""){
			alert("请输入银行卡账户！");
			$("#txtNumber").focus();
			return;
		}
		
		if(txtUserName == ""){
			alert("请输入银行卡户名！");
			$("#txtUserName").focus();
			return;
		}
		
		if(txtBank == ""){
			alert("请输入开户行！");
			$("#txtBank").focus();
			return;
		}
		
		var addurl = projectPath + "/StoreinfoTixian";
		$.post(addurl,{
			"amount" : txtAmount,
			"carNumber" : txtNumber,
			"userName" : txtUserName,
			"bank" : txtBank
			},
			function(data){
				if(data.editInfo == true){
					alert("提现成功！");
					window.location.href = projectPath + "/goStoreinfoTixian";
					$("#txtAmount").val("");
					$("#txtNumber").val("");
					$("#txtUserName").val("");
					$("#txtBank").val("");
				}else{
					$("#txtName").focus();
				}
				//$.messager.alert("操作提示", data.message, "error");
			}, "json");
	});
	
});
