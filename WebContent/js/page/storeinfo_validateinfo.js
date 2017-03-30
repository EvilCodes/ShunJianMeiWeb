//查询省份列表
function getBaseProvinces() {
	var url = projectPath + "/getBaseProvinces";
	$.post(url, function(data) {
		if (data.selInfo == true) {
			$("#txtProvinceid").empty();
			for (var i = 0; i < data.baseProvinceList.length; i++) {
				$("#txtProvinceid").append(
						"<option value='" + data.baseProvinceList[i].provinceid
								+ "'>" + data.baseProvinceList[i].provincename
								+ "</option>");
			}
		}
	}, "json");
}

// 查询城市列表
function getBaseCitys() {
	var url = projectPath + "/getBaseCitys/" + $("#txtProvinceid").val();
	$.post(url, function(data) {
		if (data.selInfo == true) {
			$("#txtCityid").empty();
			for (var i = 0; i < data.baseCityList.length; i++) {
				$("#txtCityid").append(
						"<option value='" + data.baseCityList[i].cityid + "'>"
								+ data.baseCityList[i].cityname + "</option>");
			}
		}
	}, "json");
}

// 查询区县列表
function getBaseDistricts() {
	var url = projectPath + "/getBaseDistricts/" + $("#txtCityid").val();

	$.post(url, function(data) {
		if (data.selInfo == true) {
			$("#txtDistrictid").empty();
			for (var i = 0; i < data.baseDistrictList.length; i++) {
				$("#txtDistrictid").append(
						"<option value='" + data.baseDistrictList[i].districtid
								+ "'>" + data.baseDistrictList[i].districtname
								+ "</option>");
			}
		}
	}, "json");
}

// 查询商圈列表
function getBaseBusinessareas() {
	var url = projectPath + "/getBaseBusinessareas/" + $("#txtCityid").val();
	$.post(url, function(data) {
		if (data.selInfo == true) {
			$("#txtAreaid").empty();
			for (var i = 0; i < data.baseBusinessareaList.length; i++) {
				$("#txtAreaid").append(
						"<option value='" + data.baseBusinessareaList[i].areaid
								+ "'>" + data.baseBusinessareaList[i].areaname
								+ "</option>");
			}
		}
	}, "json");
}

//获取session里的图片ID
function getSessionImageId(fileName) {
	var addurl = projectPath + "/getSessionImageId";
	$.post(addurl,{
		"fileType" : fileName
		},
		function(data){
			if(data.info){
				if(data.fileName.length > 0){
					if(fileName == "fileName1"){
						$("#imgFileName1").attr("src", projectPath + data.fileName);
						$("#imgFileName1").attr("width", "100px");
						$("#imgFileName1").attr("height", "50px");
						$("#fileName1").val(data.fileName.substring(1, data.fileName.length));
						//$("#fileName1").val(data.fileName);
					}else if(fileName == "fileName2"){
						$("#imgFileName2").attr("src", projectPath + data.fileName);
						$("#imgFileName2").attr("width", "100px");
						$("#imgFileName2").attr("height", "50px");
						$("#fileName2").val(data.fileName.substring(1, data.fileName.length));
						//$("#fileName2").val(data.fileName);
					}else if(fileName == "fileName3"){
						$("#imgFileName3").attr("src", projectPath + data.fileName);
						$("#imgFileName3").attr("width", "100px");
						$("#imgFileName3").attr("height", "50px");
						$("#fileName3").val(data.fileName.substring(1, data.fileName.length));
						//$("#fileName3").val(data.fileName);
					}
				}
			}else{
				alert(data.msg);
			}
		}, "json");
}



$(function() {

	//省份
	if ($("#provinceid").val() != "") {
		$("#txtProvinceid").val($("#provinceid").val());
	} else {
		getBaseProvinces();
	}

	//城市
	if ($("#cityid").val() != "") {
		$("#txtCityid").val($("#cityid").val());
	} else {
		getBaseCitys();
	}

	//区县
	if ($("#districtid").val() != "") {
		$("#txtDistrictid").val($("#districtid").val());
	} else {
		getBaseDistricts();
	}

	//商圈
	if ($("#areaid").val() != "") {
		$("#txtAreaid").val($("#areaid").val());
	} else {
		getBaseBusinessareas();
	}
	
	//营业时间
	$("#selBusinesshoursStart").val($("#businesshoursStart").val());
	$("#selBusinesshoursEnd").val($("#businesshoursEnd").val());
	$("#txtEmpiricalmode").val($("#empiricalmode").val());

	// 返回
	$("#btnBack").click(function() {
		window.history.back(-1);
		return;
	});

	$("#btnOk").click(function() {

		$("#hidSaveOrOk").val(1);
		if ($("#storeName").val() == "") {
			alert("请输入商户名称！");
			$("#storeName").focus();
			return;
		}

		if ($("#txtProvinceid").val() == 0) {
			alert("请选择省份！");
			$("#txtProvinceid").focus();
			return;
		}

		if ($("#txtCityid").val() == 0) {
			alert("请选择城市！");
			$("#txtCityid").focus();
			return;
		}

		if ($("#txtDistrictid").val() == 0) {
			alert("请选择区县！");
			$("#txtDistrictid").focus();
			return;
		}

		if ($("#address").val() == "") {
			alert("请输入地址！");
			$("#address").focus();
			return;
		}

		if ($("#tel").val() == "") {
			alert("请输入商户电话！");
			$("#tel").focus();
			return;
		}
		var telCheck = /^1[3|4|5|8][0-9]\d{8}$|^(0\d{2,3}-?|\(0\d{2,3}\))?[1-9]\d{4,7}(-\d{1,8})?$/;
		
		if(!(telCheck.test($("#tel").val()))){ 
			alert("商户电话格式不正确！");
			$("#tel").focus(); 
	        return; 
	    }

		if ($("#carnumber").val() == "") {
			alert("请输入停车位！");
			$("#carnumber").focus();
			return;
		}

		if ($("#businesshoursStart").val() == "") {
			alert("请输入开始营业时间！");
			$("#businesshoursStart").focus();
			return;
		}

		if ($("#businesshoursEnd").val() == "") {
			alert("请输入结束营业时间！");
			$("#businesshoursEnd").focus();
			return;
		}

		if ($("#longitude").val() == "") {
			alert("请输入经度！");
			$("#longitude").focus();
			return;
		}
		
		if ($("#latitude").val() == "") {
			alert("请输入纬度！");
			$("#latitude").focus();
			return;
		}
		
		if ($("#txtAreaid").val() == 0) {
			alert("请选择商圈！");
			$("#txtAreaid").focus();
			return;
		}

		if ($("#intro").val() == "") {
			alert("请输入客户简介！");
			$("#intro").focus();
			return;
		}
		
		if ($("#ownername").val() == "") {
			alert("请输入开户姓名！");
			$("#ownername").focus();
			return;
		}
		
		if ($("#bank").val() == "") {
			alert("请输入开户行！");
			$("#bank").focus();
			return;
		}
		
		if ($("#cardnumber").val() == "") {
			alert("请输入卡号！");
			$("#cardnumber").focus();
			return;
		}
		
		var cardnumberReg = /^(\d{16}|\d{19})$/;
		if(!(cardnumberReg.test($("#cardnumber").val()))){ 
			alert("卡号格式不正确！");
			$("#cardnumber").focus(); 
	        return; 
	    } 

		if ($("#bossname").val() == "") {
			alert("请输入接口人姓名！");
			$("#bossname").focus();
			return;
		}

		if ($("#bossmobile").val() == "") {
			alert("请输入接口人手机号！");
			$("#bossmobile").focus();
			return;
		}
		
		if(!(telCheck.test($("#bossmobile").val()))){ 
			alert("接口人手机号格式不正确！");
			$("#bossmobile").focus(); 
	        return; 
	    } 

		if ($("#storemanagername").val() == "") {
			alert("请输入店主姓名！");
			$("#storemanagername").focus();
			return;
		}

		if ($("#storemanagermobile").val() == "") {
			alert("请输入店主手机号！");
			$("#storemanagermobile").focus();
			return;
		}
		
		if(!(telCheck.test($("#storemanagermobile").val()))){ 
			alert("店主手机号格式不正确！");
			$("#storemanagermobile").focus(); 
	        return; 
	    }
		
		var defaultImg = "images/defaultAvatar.png";
		
		if($("#fileName1").val() == defaultImg){
			alert("请上传商户经营执照！");
			return;
		}
		
		if($("#fileName2").val() == defaultImg){
			alert("请上传商户正面照！");
			return;
		}
		
		if($("#fileName3").val() == defaultImg){
			alert("请上传店内照！");
			return;
		}
		
		$("#provinceid").val($("#txtProvinceid").val());
		$("#cityid").val($("#txtCityid").val());
		$("#districtid").val($("#txtDistrictid").val());
		$("#areaid").val($("#txtAreaid").val());
		$("#empiricalmode").val($("#txtEmpiricalmode").val());
		$("#businesshoursStart").val($("#selBusinesshoursStart").val());
		$("#businesshoursEnd").val($("#selBusinesshoursEnd").val());

		document.validateinfoFrom.submit();
	});
	
	//保存
	$("#btnSave").click(function() {
		$("#hidSaveOrOk").val(0);
		
		$("#provinceid").val($("#txtProvinceid").val());
		$("#cityid").val($("#txtCityid").val());
		$("#districtid").val($("#txtDistrictid").val());
		$("#areaid").val($("#txtAreaid").val());
		$("#empiricalmode").val($("#txtEmpiricalmode").val());
		$("#businesshoursStart").val($("#selBusinesshoursStart").val());
		$("#businesshoursEnd").val($("#selBusinesshoursEnd").val());

		document.validateinfoFrom.submit();
	});

	if ($("#hidmsg").val() != "") {
		alert($("#hidmsg").val());
	}

	if ($("#hidstate").val() == "1" || $("#hidstate").val() == "2") {
		if($("#hidstate").val() == "1"){
			$("#stateMsg").text("状态：审核中。");
		}
		if($("#hidstate").val() == "2"){
			$("#stateMsg").text("状态：审核通过。");
		}
		$('input').attr("readonly", true);
		$('select').attr("disabled", true);
		
		$("#intro").attr("disabled", true);
		$("#btnSave").attr("disabled", true);
		$("#btnOk").attr("disabled", true);
	}else if($("#hidstate").val() == "0" || $("#hidstate").val() == "3"){
		/*
		 * 商户经营执照
		 */
		$("#upload_file1").click(function() {
			var button = $('#upload_file1'), interval;
			var fileName = "fileName1"
			new AjaxUpload(button, {
				action : projectPath + '/UploadImages',
				data : {"fileType": fileName},
				name : 'fileName',
				onSubmit : function(file, ext) {
					button.text('文件上传中！！！');
					interval = window.setInterval(function() {
						var text = button.text();
						if (text.length < 14) {
							button.text(text + '.');
						} else {
							button.text('文件上传中....');
						}
					}, 2000);
				},
				onComplete : function(file, response) {

					getSessionImageId(fileName);
					button.text('上传');
					window.clearInterval(interval);
					this.enable();
				}
			});
		});

		/*
		 * 商户正面照
		 */
		$("#upload_file2").click(function() {
			var button = $('#upload_file2'), interval;
			var fileName = "fileName2"
				
			new AjaxUpload(button, {
				action : projectPath + '/storeUploadImages',
				data : {"fileType": fileName},
				name : 'fileName',
				onSubmit : function(file, ext) {
					button.text('上传中！');
					interval = window.setInterval(function() {
						var text = button.text();
						if (text.length < 14) {
							button.text(text + '.');
						} else {
							button.text('上传中.');
						}
					}, 2000);
				},
				onComplete : function(file, response) {

					getSessionImageId(fileName);
					button.text('上传');
					window.clearInterval(interval);
					this.enable();
				}
			});
		});

		/*
		 * 店内照
		 */
		$("#upload_file3").click(function() {
			var button = $('#upload_file3'), interval;
			var fileName = "fileName3"
			new AjaxUpload(button, {
				action : projectPath + '/storeUploadImages',
				data : {"fileType": fileName},
				name : 'fileName',
				onSubmit : function(file, ext) {
					button.text('上传中！');
					interval = window.setInterval(function() {
						var text = button.text();
						if (text.length < 14) {
							button.text(text + '.');
						} else {
							button.text('上传中.');
						}
					}, 2000);
				},
				onComplete : function(file, response) {
					//alert(response);
					getSessionImageId(fileName);
					button.text('上传');
					window.clearInterval(interval);
					this.enable();
				}
			});
		});

		$("#upload_file1").click();
		$("#upload_file2").click();
		$("#upload_file3").click();
		
		if($("#hidstate").val() == "0"){
			$("#stateMsg").text("状态：未审核。");
		}else if($("#hidstate").val() == "3"){
			$("#stateMsg").text("状态：审核失败。");
		}
		$('input').attr("readonly", false);
		$('select').attr("disabled", false);
		
		$("#intro").attr("disabled", false);
		$("#btnSave").attr("disabled", false);
		$("#btnOk").attr("disabled", false);
	}
	
});
