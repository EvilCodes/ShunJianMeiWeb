//筛选
function searchInfo(){
	loadGrid();
}
//重置
function reset(){
	$("#userid").val("");
	$("#bindphone").val("")
	$("#txtdateFrom").datebox('');
	$("#txtdateTo").datebox('');
	$("#txtState").val("0");
}

//加载表格datagrid  
function loadGrid() {
	//城市ID
	var cityId = 1;
	//美发师ID
	var userid = $("#userid").val();
	//美发师手机
	var tel = $("#bindphone").val();
	//开始时间
	var startTime = $("#txtdateFrom").datebox('getValue');
	//结束时间
	var endTime = $("#txtdateTo").datebox('getValue');
	//审核状态
	var checkstate = $("#txtState").val();

	//加载数据  
	$('#cxdm').datagrid({
		view : detailview,
		width : 'auto',
		height : 400,
		pageSize : 10,
		striped : true,
		singleSelect : true,
		url : 'UsersubinfoManage',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
			"cityid" : cityId,
			"userid" : userid,
			"tel" : tel,
			"startTime" : startTime,
			"endTime" : endTime,
			"checkstate" : checkstate
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
        onExpandRow: function(index,row){
        	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            ddv.panel({
                border:0,
                cache:true,
                href: projectPath + '/goOrderinfoDetail/' + row.orderid + '/0',
                onLoad:function(){
                    $('#cxdm').datagrid('fixDetailRowHeight',index);
                    $('#cxdm').datagrid('selectRow',index);
                    $('#cxdm').datagrid('getRowDetail',index).find('form').form('load',row);
                }
            });
            $('#cxdm').datagrid('fixDetailRowHeight',index);
        },
/*        toolbar : [ '-', {
			id : 'btnAdd',
			text : '添加用户',
			iconCls : 'icon-add',
			handler : function() {
				addData();
			}
		}, '-', {
			id : 'btnEdit',
			text : '修改用户',
			iconCls : 'icon-edit',
			handler : function() {
				updateData();
			}
		}, '-', {
			id : 'btnDel',
			text : '删除用户',
			iconCls : 'icon-remove',
			handler : function() {
				deleteData();
			}
		} ],*/
		columns : [ [ {
			field : 'userid',
			title : 'ID',
			align : 'center',
			width : 100
		},{
			field : 'bindphone',
			title : '手机',
			align : 'center',
			width : 100
		},{
			field : 'nickname',
			title : '昵称',
			align : 'center',
			width : 100
		},{
			field : 'score',
			title : '服务星级',
			align : 'center', formatter:function(value, row, index){
                switch (value) {
                    case 1:
                        return '一星';
                        break;
                    case 2:
                        return '二星';  
                        break;  
                    case 3:
                        return '三星';
                        break;
                    case 4:
                        return '四星';
                        break;
                    case 5:
                        return '五星';
                        break;
                }  
			},
			width : 100
		},{
			field : 'levelname',
			title : '定级',
			align : 'center',
			width : 100
		} ,{
			field : 'checkstate',
			title : '状态',
			align : 'center', formatter:function(value, row, index){
                switch (value) {
                    case 1:
                        return '审核中';
                        break;
                    case 2:
                        return '审核通过';  
                        break;  
                    case 3:
                        return '审核失败';
                        break;
                }  
			},
			width : 100
		} ] ],
		onLoadError : function() {
			XW_dialog.alert('', '加载数据失败！');
		}
	});

	//设置分页控件  
	var p = $('#cxdm').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,//每页显示的记录条数，默认为20  
		pageList : [ 5, 10, 20, 30 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
