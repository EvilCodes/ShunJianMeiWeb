//1:后七天 2：后周
var riqiType = 1;
// 1:全部可预约 2：取消预约
var commitType = 0;

var myhour = 0;

// 1:可预约 2：不可预约
var gongweiCommitType = 0;

var wdid = 0;

var myswdDate = "";

var paystateMap = {};
paystateMap[1] = "待支付";
paystateMap[2] = "待确认";
paystateMap[3] = "已预约";
paystateMap[4] = "已签到";
paystateMap[5] = "服务中";
paystateMap[6] = "服务完成";
paystateMap[7] = "评价完成";
paystateMap[8] = "订单完成";
paystateMap[9] = "已取消";
paystateMap[10] = "异常处理";

$(function() {

	$("#btnHouZhou").click(function() {

		$("#trHouZhou").show();
		$("#trHouQiRi").hide();
		riqiType = 2;
		closs();
		var $td = $("#td8");
		setStoreOrderInfo($td,$td.text());
	});

	$("#btnHouQiTian").click(function() {

		$("#trHouZhou").hide();
		$("#trHouQiRi").show();
		riqiType = 1;
		closs();
		var $td = $("#td1");
		setStoreOrderInfo($td,$td.text());
	});

	// 全部预约
	$("#btnAllOk").click(function() {
		commitType = 1;
		doRiChengGuanLi();
	});

	$("#btnAllNo").click(function() {
		if(wdid != 0){
			commitType = 2;
			doRiChengGuanLi();
		}
		
	});

	// 可预约
	$("#btnOk").click(function() {
		gongweiCommitType = 1;
		doUpdateStoreWorkhour();
	});

	$("#btnNo").click(function() {
		gongweiCommitType = 2;
		doUpdateStoreWorkhour();
	});
	
	var $td = $("#td1");
	setStoreOrderInfo($td,$td.text());

});

function doUpdateStoreWorkhour() {

	var zuoWeiNum = $("#selectZuoWeiNum2").val();
	var url = projectPath + "/doUpdateStoreWorkhour";
	$.post(url, {

		"wdid" : wdid,
		"type" : gongweiCommitType,
		"chairNum" : $("#selectZuoWeiNum2").val(),
		"hour" : myhour
	}, function(data) {

		if (data.info == true) {

			if (gongweiCommitType == 1) {
				$("#span" + myhour).text(zuoWeiNum);
				$("#hidOrderid" + myhour).val(0);
			} else {
				$("#span" + myhour).text("--");
				$("#hidOrderid" + myhour).val("");
			}

		} else {
			alert("操作失败");
		}
	}, "json");

}

function closs() {
	$(".mytd").css("background-color", '#EFEFEF');
	$(".spanCss").text("--");
	$(".hidOrderidCss").val("");
	$("#btnOk").attr("disabled", "disabled");
	$("#btnNo").attr("disabled", "disabled");
	$(".gongweiTd").css("background-color", '#EFEFEF');
	myhour = 0;
	myswdDate = "";
}

function checkYuYue(td, hour) {

	$(".gongweiTd").css("background-color", '#EFEFEF');
	$(td).css("background-color", '#AFEEEE');
	myhour = hour;
	var hidOrderid = $("#hidOrderid" + hour).val();
	// 判断订单数
	if ("0" == hidOrderid || "" == hidOrderid) {
		
		if(wdid != 0){
			$("#btnOk").removeAttr("disabled");
			$("#btnNo").removeAttr("disabled");
		}else{
			$("#btnOk").attr("disabled", "disabled");
			$("#btnNo").attr("disabled", "disabled");
			alert("没有工作日信息，请先执行全部预约");
			$(".gongweiTd").css("background-color", '#EFEFEF');
		}
		
	} else {
		$("#btnOk").attr("disabled", "disabled");
		$("#btnNo").attr("disabled", "disabled");
	}
}

// 设置工位
function setGongWei(swdData) {

	$(".gongweiTd").css("background-color", '#EFEFEF');
	myhour = 0;
	var storeId = $("#storeId").val();
	var url = projectPath + "/getGongWeiByGongZuoRi";
	$.post(url, {

		"swdDate" : swdData,
		"storeId" : storeId
	}, function(data) {

		if (data.info == true) {

			$(".spanCss").text("--");
			$(".hidOrderidCss").val("");

			for (var i = 0; i < data.data.length; i++) {

				$("#span" + data.data[i].hour).text(data.data[i].chairidCount);
				$("#hidOrderid" + data.data[i].hour).val(data.data[i].orderid);

			}

			wdid = data.wdid;
			
		} else {
			$(".spanCss").text("--");
			$(".hidOrderidCss").val("");

		}
	}, "json");

}

// 取得订单信息
function setStoreOrderInfo(td, swdData) {

	wdid = 0;
	$("#trInfo").hide();
	var storeId = $("#storeId").val();
	myswdDate = swdData;
	setGongWei(swdData);

	$("#btnOk").attr("disabled", "disabled");
	$("#btnNo").attr("disabled", "disabled");
	
	$("#tableOrderInfo").children().remove();

	$(".mytd").css("background-color", '#EFEFEF');
	$(td).css("background-color", '#AFEEEE');

	
	/**/
	var url = projectPath + "/getOrderinfoBySwdid";
	$.post(
					url,
					{

						"swdDate" : swdData,
						"storeId" : storeId
					},
					function(data) {

						if (data.info == true) {

							$("#trOrderInfo").show();

							for (var i = 0; i < data.data.length; i++) {
								var url = projectPath + '/goOrderinfoDetail/'
										+ data.data[i].orderid + '/1';
								var tr = '<tr style="height: 30px"><td align="left" width="100px">订单号:</td>';
								tr = tr + '<td align="left" width="200px">'
										+ data.data[i].ordercode + '</td>';
								tr = tr + '<td align="left" width="100px">'
										+ paystateMap[data.data[i].paystate]
										+ '</td>';
								tr = tr
										+ '<td align="right" width="200px"><a href="'
										+ url + '">查看详细</a></td></tr>';

								$("#tableOrderInfo").append(tr);
							}

							
						} else {
							$("#trInfo").show();
							$("#tableOrderInfo").children().remove();
						}
					}, "json");
					

}

// 预约事件
function doRiChengGuanLi() {

	if ("" == myswdDate) {
		alert("请选择工作日");
		return;
	}

	$("#btnAllOk").attr("disabled", "disabled");
	$("#btnAllNo").attr("disabled", "disabled");
	
	var addurl = projectPath + "/doAddRiZhiGuanLi";
	$.post(addurl, {

		"swdDate" : myswdDate,
		"commitType" : commitType,
		"storeId" : $("#storeId").val(),
		"chairNum" : $("#selectZuoWeiNum").val()
	}, function(data) {
		alert(data.msg);
		
		$("#btnAllOk").removeAttr("disabled");
		$("#btnAllNo").removeAttr("disabled");
		
		if (data.info == true) {

			if (commitType == 1) {
				$(".spanCss").text($("#selectZuoWeiNum").val());
				$(".hidOrderidCss").val("0");
				wdid = data.wdid;
			} else if (commitType == 2) {
				$(".spanCss").text("--");
				$(".hidOrderidCss").val("");
				wdid = 0;
			}

		} 
	}, "json");

}
