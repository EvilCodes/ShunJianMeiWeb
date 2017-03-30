//页面加载  
$(document).ready(function(){
	loadGrid();
});

//加载表格datagrid  
function loadGrid() {
	//加载数据  
	$('#cxdm').datagrid({
		view : detailview,
		width : 'auto',
		height : 600,
		pageSize : 20,
		striped : true,
		singleSelect : true,
		url : 'OrderinfoManage',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
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
			field : 'orderid',
			title : '订单ID',
			align : 'center',
			hidden: true,
			width : 100
		},{
			field : 'user_hairstyle_name',
			title : '发型项目',
			align : 'center',
			width : 100
		},{
			field : 'usersubinfo_name',
			title : '发型师',
			align : 'center',
			width : 100
		},{
			field : 'user_profession_level_name',
			title : '职称等级',
			align : 'center',
			width : 100
		},{
			field : 'appointmenttime',
			title : '预约时间',
			align : 'center',
			width : 100
		} ,{
			field : 'usersubinfo_contactmobile',
			title : '联系人电话',
			align : 'center',
			width : 100
		} ,{
			field : 'storeinfo_name',
			title : '店铺名称',
			align : 'center',
			width : 100
		} ,{
			field : 'storeinfo_tel',
			title : '电话',
			align : 'center',
			width : 100
		} ,{
			field : 'storeinfo_address',
			title : '地址',
			align : 'center',
			width : 100
		} ,{
			field : 'amount',
			title : '支付金额',
			align : 'center',
			width : 100
		} ,{
			field : 'ishairpacked',
			title : '订单类型',
			align : 'center', formatter:function(value, row, index){
                switch (value) {
                case 0:
                    return '普通订单';
                    break;
                case 1:
                    return '套餐订单';  
                    break;  
                }  
			}
		},{
			field : 'paystate',
			title : '订单状态',
			align : 'center', formatter:function(value, row, index){
                switch (value) {
                    case 1:
                        return '待支付';
                        break;
                    case 2:
                        return '待确认';  
                        break;  
                    case 3:
                        return '已预约';
                        break;
                    case 4:
                        return '已签到';
                        break;
                    case 5:
                        return '服务中';
                        break;
                    case 6:
                        return '服务完成';
                        break;
                    case 7:
                        return '评价完成';
                        break;
                    case 8:
                        return '订单完成';
                        break;
                    case 9:
                        return '已取消';
                        break;
                    case 10:
                        return '异常处理';
                        break;
                }  
			}
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

function addData(){
	window.location.href = projectPath + "/goUserAdd";
	return;
} 

function updateData(){
	var row = $('#cxdm').datagrid("getSelections");
	
	if(null == row || row.length == 0){
		$.messager.alert('提示','请选择要修改的信息！','info');
		return;
	}
	
	window.location.href = projectPath + "/goUserEdit/"+row[0].increment_id;
	return;
}

function deleteData() {
	var row = $('#cxdm').datagrid("getSelections");
	
	if(null == row || row.length == 0){
		$.messager.alert('提示','请选择要删除的信息！','info');
		return;
	}
	
	$.messager.confirm('确认删除', '删除当前用户？', function(r){
         if (r){
       	    var delurl = projectPath + "/UserDelete";

			$.post(delurl, {
				"incrementId" : row[0].increment_id
			}, function(data) {
				if (data.delInfo == true) {
					//ok
					//$.messager.alert('成功', data.message);
					$('#cxdm').datagrid('reload');
				} else {
					//$.messager.alert('失败', data.message, 'error');
				}
				alert(data.message);
			}, "json");
		}
	});
	return;
}