
var ordeyType = 2;

var ordeyPaystate = 1;

var ordeyCreatetime = 1;


$(function(){
	//返回
	$("#btnRecharge").click(function(){
		
	});
	
	$("#btnWithdrawals").click(function(){
		window.location.href = projectPath + "/goStoreinfoTixian";
		return;
	});
	
	$("#btnZhuangTai").click(function(){
		ordeyType = 1;
		
		if(ordeyPaystate == 1){
			ordeyPaystate = 2;
		}else{
			ordeyPaystate = 1;
		}
		getOrderInfoByID(ordeyPaystate);
	});
	$("#btnDateTime").click(function(){
		ordeyType = 2;
		if(ordeyCreatetime == 1){
			ordeyCreatetime = 2;
		}else{
			ordeyCreatetime = 1;
		}
		getOrderInfoByID(ordeyCreatetime);
	});
	
	loadGrid();
	loadTX();
	loadSH();
});

function getOrderInfoByID(){
	var url = projectPath + "/getOrderInfoByID";
	$.post(url, {
		"orderType" : $("#selState").val(),
		"orderVal" : $("#selDateTime").val()
	}, function(data) {
		$(".trClass").remove();

		if(data.orderinfoList.length != 0){
			var $trOrderInfo = $("#trOrderInfo");
			for(var i = 0;i < data.orderinfoList.length;i++){
				
				
				var tr = '<tr class="trClass" >';
				tr = tr + '	<td colspan="2">';
				tr = tr + '		<table style="width:100%; margin:auto;">';
				tr = tr + '			<tr>';
				tr = tr + '				<td align="left">订单编号：'+data.orderinfoList[i].ordercode+'</td>';
				tr = tr + '				<td align="right" >时间：'+data.orderinfoList[i].createtime+'</td>';
				tr = tr + '			</tr>';
				tr = tr + '			<tr>';
				tr = tr + '				<td align="left">'+data.orderinfoList[i].user_hairstyle_name+'</td>';
				tr = tr + '				<td align="right" >支付：¥'+data.orderinfoList[i].total+'</td>';
				tr = tr + '			</tr>';
				tr = tr + '			<tr>';
				tr = tr + '				<td colspan="2" align="left">预约时间：'+data.orderinfoList[i].appointmenttime+'</td>';
				tr = tr + '			</tr>';
				tr = tr + '		</table>';
				tr = tr + '	</td>';
				tr = tr + '</tr>';
				$trOrderInfo.append(tr);
			}
			
		}
		
	}, "json");
}

//加载表格datagrid  
function loadGrid() {
	//加载数据  
	$('#cxdm').datagrid({
		//view : detailview,
		width : 'auto',
		height : 320,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'getOrderInfoByID',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"orderType" : $("#selState").val(),
			"orderVal" : $("#selDateTime").val()
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
		columns : [ [ {
			field : 'ordercode',
			title : '订单编号',
			align : 'center',
			//hidden: true,
			width : 100
		},{
			field : 'stylename',
			title : '发型',
			align : 'center',
			width : 50
		},{
			field : 'amount',
			title : '支付',
			align : 'center',
			width : 50
		},{
			field : 'appointmenttime',
			title : '预约时间',
			align : 'center',
			width : 100
		},{
			field : 'createtime',
			title : '时间',
			align : 'center',
			width : 100
		} ] ],
		onLoadError : function() {
			XW_dialog.alert('', '加载数据失败！');
		}
	});

	//设置分页控件  
	var p = $('#cxdm').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为20  
		pageList : [ 5, 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}


//加载表格datagrid  
function loadTX() {
	//加载数据  
	$('#tixian').datagrid({
		//view : detailview,
		width : 'auto',
		height : 320,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'getStoreinfoTixian',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
      },
		columns : [ [ {
			field : 'ownername',
			title : '帐号',
			align : 'center',
			//hidden: true,
			width : 50
		},{
			field : 'bank',
			title : '开户行',
			align : 'center',
			width : 50
		},{
			field : 'cardnumber',
			title : '卡号',
			align : 'center',
			width : 100
		},{
			field : 'amount',
			title : '金额',
			align : 'center',
			width : 50
		},{
			field : 'remark',
			title : '备注',
			align : 'center',
			width : 50
		},{
			field : 'createtime',
			title : '时间',
			align : 'center',
			width : 100
		} ] ],
		onLoadError : function() {
			XW_dialog.alert('', '加载数据失败！');
		}
	});

	//设置分页控件  
	var p = $('#tixian').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为20  
		pageList : [ 5, 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}


//加载表格datagrid  
function loadSH() {
	//加载数据  
	$('#shanhui').datagrid({
		//view : detailview,
		width : 'auto',
		height : 320,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'getUserQuickpay',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
      },
		columns : [ [ {
			field : 'ordercode',
			title : '闪惠编号',
			align : 'center',
			//hidden: true,
			width : 60 
		},{
			field : 'payamount',
			title : '支付',
			align : 'center',
			width : 40
		},{
			field : 'mastername',
			title : '美发师姓名',
			align : 'center',
			width : 60
		},{
			field : 'customreduce',
			title : '优惠金额',
			align : 'center',
			width : 50
		},{
			field : 'storereduce',
			title : '分成金额',
			align : 'center',
			width : 50
		},{
			field : 'payTotal',
			title : '得到金额',
			align : 'center',
			width : 50
		} 
		,{
			field : 'username',
			title : '用户电话',
			align : 'center',
			width : 75
		},{
			field : 'createtime',
			title : '支付时间',
			align : 'center',
			width : 105
		}
		] ],
		onLoadError : function() {
			XW_dialog.alert('', '加载数据失败！');
		}
	});

	//设置分页控件  
	var p = $('#shanhui').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为20  
		pageList : [ 5, 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}




