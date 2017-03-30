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
		url : 'H_SearchUserHairpackedByStoreid',
		loadMsg : '数据加载中请稍后……',
		pagination : true,
		fitColumns : true,
		queryParams : {
		},
		detailFormatter:function(index,row){ 
			return '<div class="ddv"></div>';  
        },
//        onExpandRow: function(index,row){
//        	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
//            ddv.panel({
//                border:0,
//                cache:true,
//                href: projectPath + '/goOrderinfoDetail/' + row.orderid + '/0',
//                onLoad:function(){
//                    $('#cxdm').datagrid('fixDetailRowHeight',index);
//                    $('#cxdm').datagrid('selectRow',index);
//                    $('#cxdm').datagrid('getRowDetail',index).find('form').form('load',row);
//                }
//            });
//            $('#cxdm').datagrid('fixDetailRowHeight',index);
//        },
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
			field : 'id',
			title : '套餐ID',
			align : 'center',
			hidden: true,
			width : 100
		},{
			field : 'name',
			title : '套餐名称',
			align : 'center',
			width : 100
		},{
			field : 'intro',
			title : '套餐描述',
			align : 'center',
			width : 200
		},{
			field : 'items',
			title : '套餐价格（长、中、短）',
			align : 'center',
			width : 200
		},{
			field : 'times',
			title : '服务时长(分钟)',
			align : 'center',
			width : 100
		} ,{
			field : 'stateString',
			title : '套餐状态',
			align : 'center',
			width : 100
		} ,{
			field : 'hairdressers',
			title : '套餐服务美发师',
			align : 'center',
			width : 100
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter:function(index,rows){  
                var btn = "<a href='/shunjianmeiweb/goUpdatePacked?hairpackid="+rows.id+"'>编辑</a>";  
                return btn;  
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

//function addData(){
//	window.location.href = projectPath + "/goUserAdd";
//	return;
//} 
//
//function updateData(){
//	var row = $('#cxdm').datagrid("getSelections");
//	
//	if(null == row || row.length == 0){
//		$.messager.alert('提示','请选择要修改的信息！','info');
//		return;
//	}
//	
//	window.location.href = projectPath + "/goUserEdit/"+row[0].increment_id;
//	return;
//}
//
//function deleteData() {
//	var row = $('#cxdm').datagrid("getSelections");
//	
//	if(null == row || row.length == 0){
//		$.messager.alert('提示','请选择要删除的信息！','info');
//		return;
//	}
//	
//	$.messager.confirm('确认删除', '删除当前用户？', function(r){
//         if (r){
//       	    var delurl = projectPath + "/UserDelete";
//
//			$.post(delurl, {
//				"incrementId" : row[0].increment_id
//			}, function(data) {
//				if (data.delInfo == true) {
//					//ok
//					//$.messager.alert('成功', data.message);
//					$('#cxdm').datagrid('reload');
//				} else {
//					//$.messager.alert('失败', data.message, 'error');
//				}
//				alert(data.message);
//			}, "json");
//		}
//	});
//	return;
//}