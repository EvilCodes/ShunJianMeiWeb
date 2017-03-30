function getStarid(){
	$("#starid").val($("#txtStarid").val());
}

function doSave(){
	var txtStarid = $.trim($("#starid").val());
	
//	if(txtStarid == 0){
//		alert("请选择星座！");
//		$("#txtStarid").focus();
//		return;
//	}
	
	var hairstyle = document.getElementsByName("checkHairstyle");
	var hobby = document.getElementsByName("checkHobby");
	var txtWorkinglife = $.trim($("#workinglife").val());
	
	if("" != txtWorkinglife){
		if(isNaN(txtWorkinglife)){
			alert("从业年限只能为数字！");
			$("#txtWorkinglife").focus();
			return;
		}
	}
	
	//擅长
	if(hairstyle.length > 0){
		var hairstyleStr = "", flag = false;
		for(var i=0; i<hairstyle.length; i++){
			if(hairstyle[i].checked){
				hairstyleStr += hairstyle[i].value + ",";
				flag = true;
			}
		}
		if(flag){
			$("#hairstyle").val(hairstyleStr.substring(0, hairstyleStr.length));
		}else{
			//alert("擅长不能为空！");
			//return;
		}
	}
	
	//个人爱好
	if(hobby.length > 0){
		var hobbyStr = "", flag = false;
		for(var i=0; i<hobby.length; i++){
			if(hobby[i].checked){
				hobbyStr += hobby[i].value + ",";
				flag = true;
			}
		}
		if(flag){
			$("#hobbies").val(hobbyStr.substring(0, hobbyStr.length));
		}else{
			//alert("个人爱好不能为空！");
			//return;
		}
	}
	
	$("#buttonFlag").val("0");//保存
	document.validateinfoFrom.submit();
	
}

function doComplete(){
	var txtNickname = $.trim($("#nickname").val());
	var txtStarid = $.trim($("#starid").val());
	var txtWorkinglife = $.trim($("#workinglife").val());
	var txtIntro = $.trim($("#intro").val());
	var txtWorkhistory = $.trim($("#workhistory").val());
	var txtHairstyle = $.trim($("#hairstyle").val());
	var txtHobbies = $.trim($("#hobbies").val());
	
//	if("" == txtNickname){
//		alert("昵称不能为空！");
//		$("#txtNickname").focus();
//		return;
//	}
	
//	if(txtStarid == 0){
//		alert("请选择星座！");
//		$("#txtStarid").focus();
//		return;
//	}
	
//	if($("#fileName_tx").val() ==""){
//		alert("请上传我的头像！");
//		return;
//	}
	
//	if($("#fileName_xx1").val() =="" && $("#fileName_xx2").val() =="" && $("#fileName_xx3").val() ==""){
//		alert("请上传我的形象！");
//		return;
//	}
	
//	if($("#fileName_zp1").val() =="" && $("#fileName_zp2").val() =="" && $("#fileName_zp3").val() =="" && $("#fileName_zp4").val() ==""){
//		alert("请上传我的代表作品！");
//		return;
//	}
	
//	if("" == txtWorkinglife){
//		alert("从业年限不能为空！");
//		$("#txtWorkinglife").focus();
//		return;
//	}
	if("" != txtWorkinglife){
		if(isNaN(txtWorkinglife)){
			alert("从业年限只能为数字！");
			$("#txtWorkinglife").focus();
			return;
		}
	}
	
	
//	if("" == txtIntro){
//		alert("个人简介不能为空！");
//		$("#txtIntro").focus();
//		return;
//	}
	
//	if("" == txtWorkhistory){
//		alert("工作学历经历不能为空！");
//		$("#txtWorkhistory").focus();
//		return;
//	}
	
	var hairstyle = document.getElementsByName("checkHairstyle");
	var hobby = document.getElementsByName("checkHobby");
	
	//擅长
	if(hairstyle.length > 0){
		var hairstyleStr = "", flag = false;
		for(var i=0; i<hairstyle.length; i++){
			if(hairstyle[i].checked){
				hairstyleStr += hairstyle[i].value + ",";
				flag = true;
			}
		}
		if(flag){
			$("#hairstyle").val(hairstyleStr.substring(0, hairstyleStr.length));
		}else{
			//alert("擅长不能为空！");
			//return;
		}
	}
	
	//个人爱好
	if(hobby.length > 0){
		var hobbyStr = "", flag = false;
		for(var i=0; i<hobby.length; i++){
			if(hobby[i].checked){
				hobbyStr += hobby[i].value + ",";
				flag = true;
			}
		}
		if(flag){
			$("#hobbies").val(hobbyStr.substring(0, hobbyStr.length));
		}else{
			//alert("个人爱好不能为空！");
			//return;
		}
	}
	
	$("#buttonFlag").val("1");//完成
	document.validateinfoFrom.submit();
}

function doBack(){
	location.href= projectPath + "/goUsersubinfo_verification";
}

function setChecked(checkInput,checkList){
	var hairstyle = $.trim($("#"+checkInput).val());
    var hairstyleList = hairstyle.split(",");
    var checkHairstyle = document.getElementsByName(checkList);
    
    for(var i=0; i<hairstyleList.length; i++){
    	var hairstyleTemp = hairstyleList[i];
    	for(var j=0; j<checkHairstyle.length; j++){
    		if(hairstyleTemp == checkHairstyle[j].value){
    			checkHairstyle[j].checked = true;
    		}
    	}
    }
}


//获取session里的图片ID
function getSessionImageId(fileName) {
	var addurl = projectPath + "/getSessionUserInfoImageId";
	$.post(addurl,{
		"fileType" : fileName
		},
		function(data){
			
			if(data.info == true){
				if(data.fileName.length > 0){
					if(fileName == "fileName_tx"){
						$("#imgFileName_tx").attr("src", projectPath + "/userImg/" +data.fileName);
						$("#imgFileName_tx").attr("width", "100px");
						$("#imgFileName_tx").attr("height", "50px");
						$("#fileName_tx").val(data.fileName);
					}else if(fileName == "fileName_xx1"){
						$("#imgFileName_xx1").attr("src", projectPath + "/userImg/" +data.fileName);
						$("#imgFileName_xx1").attr("width", "100px");
						$("#imgFileName_xx1").attr("height", "50px");
						$("#fileName_xx1").val(data.fileName);
					}else if(fileName == "fileName_xx2"){
						$("#imgFileName_xx2").attr("src", projectPath + "/userImg/" +data.fileName);
						$("#imgFileName_xx2").attr("width", "100px");
						$("#imgFileName_xx2").attr("height", "50px");
						$("#fileName_xx2").val(data.fileName);
					}else if(fileName == "fileName_xx3"){
						$("#imgFileName_xx3").attr("src", projectPath + "/userImg/" +data.fileName);
						$("#imgFileName_xx3").attr("width", "100px");
						$("#imgFileName_xx3").attr("height", "50px");
						$("#fileName_xx3").val(data.fileName);
					}else if(fileName == "fileName_zp1"){
						$("#imgFileName_zp1").attr("src", projectPath + "/userImg/" +data.fileName);
						$("#imgFileName_zp1").attr("width", "100px");
						$("#imgFileName_zp1").attr("height", "50px");
						$("#fileName_zp1").val(data.fileName);
					}else if(fileName == "fileName_zp2"){
						$("#imgFileName_zp2").attr("src", projectPath + "/userImg/" +data.fileName);
						$("#imgFileName_zp2").attr("width", "100px");
						$("#imgFileName_zp2").attr("height", "50px");
						$("#fileName_zp2").val(data.fileName);
					}else if(fileName == "fileName_zp3"){
						$("#imgFileName_zp3").attr("src", projectPath + "/userImg/" +data.fileName);
						$("#imgFileName_zp3").attr("width", "100px");
						$("#imgFileName_zp3").attr("height", "50px");
						$("#fileName_zp3").val(data.fileName);
					}else if(fileName == "fileName_zp4"){
						$("#imgFileName_zp4").attr("src", projectPath + "/userImg/" +data.fileName);
						$("#imgFileName_zp4").attr("width", "100px");
						$("#imgFileName_zp4").attr("height", "50px");
						$("#fileName_zp4").val(data.fileName);
					}
			   }
			
			}else{
				alert(data.msg);
			}
		}, "json");
}

$(function(){  
	
    var starid = $.trim($("#starid").val());
    $("#txtStarid").val(starid);
    
    
    setChecked("hairstyle","checkHairstyle");//设置擅长选中
    setChecked("hobbies","checkHobby");//设置个人爱好选中
    
	var checkFlag = $.trim($("#checkFlag").val());
	if(checkFlag == 1 || checkFlag == 2){
		$('input').attr("readonly",true);
		$('textarea').attr("readonly",true);
		$('select').attr("disabled",true);
		$("input[type=checkbox]").attr("disabled",true);
		$("#saveButton").attr("disabled",true);
		$("#completeButton").attr("disabled",true);
	}else{
		$('input').attr("readonly",false);
		$('textarea').attr("readonly",false);
		$('select').attr("disabled",false);
		$("input[type=checkbox]").attr("disabled",false);
		$("#saveButton").attr("disabled",false);
		$("#completeButton").attr("disabled",false);
		
		
		/*
		 * 我的头像
		 */
		$("#imgFileName_tx").click(function() {
			var button = $('#imgFileName_tx'), interval;
			var fileName = "fileName_tx"
			new AjaxUpload(button, {
				action : projectPath + '/UploadUserInfoImages',
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
		$("#imgFileName_tx").click();
		
		/*
		 * 我的形象1
		 */
		$("#imgFileName_xx1").click(function() {
			var button = $('#imgFileName_xx1'), interval;
			var fileName = "fileName_xx1"
			new AjaxUpload(button, {
				action : projectPath + '/UploadUserInfoImages',
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
		$("#imgFileName_xx1").click();
		
		/*
		 * 我的形象2
		 */
		$("#imgFileName_xx2").click(function() {
			var button = $('#imgFileName_xx2'), interval;
			var fileName = "fileName_xx2"
			new AjaxUpload(button, {
				action : projectPath + '/UploadUserInfoImages',
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
		$("#imgFileName_xx2").click();
		
		/*
		 * 我的形象3
		 */
		$("#imgFileName_xx3").click(function() {
			var button = $('#imgFileName_xx3'), interval;
			var fileName = "fileName_xx3"
			new AjaxUpload(button, {
				action : projectPath + '/UploadUserInfoImages',
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
		$("#imgFileName_xx3").click();
		
		/*
		 * 我的作品1
		 */
		$("#imgFileName_zp1").click(function() {
			var button = $('#imgFileName_zp1'), interval;
			var fileName = "fileName_zp1"
			new AjaxUpload(button, {
				action : projectPath + '/UploadUserInfoImages',
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
		$("#imgFileName_zp1").click();
		
		/*
		 * 我的作品2
		 */
		$("#imgFileName_zp2").click(function() {
			var button = $('#imgFileName_zp2'), interval;
			var fileName = "fileName_zp2"
			new AjaxUpload(button, {
				action : projectPath + '/UploadUserInfoImages',
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
		$("#imgFileName_zp2").click();
		
		/*
		 * 我的作品3
		 */
		$("#imgFileName_zp3").click(function() {
			var button = $('#imgFileName_zp3'), interval;
			var fileName = "fileName_zp3"
			new AjaxUpload(button, {
				action : projectPath + '/UploadUserInfoImages',
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
		$("#imgFileName_zp3").click();
		
		/*
		 * 我的作品4
		 */
		$("#imgFileName_zp4").click(function() {
			var button = $('#imgFileName_zp4'), interval;
			var fileName = "fileName_zp4"
			new AjaxUpload(button, {
				action : projectPath + '/UploadUserInfoImages',
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
		$("#imgFileName_zp4").click();
	}
	
	
	 var errorMsg = $.trim($("#errorMsg").val());
		if("" != errorMsg){
			alert(errorMsg);
		}
});
	
	
	
	
