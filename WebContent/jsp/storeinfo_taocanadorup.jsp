<%@page import="com.wenyuankeji.spring.util.ShunJianMeiUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String date = ShunJianMeiUtil.getYYYYMMDDhhmmss();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>美发店信息</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset2.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/storeinfo_richengguanli.js?<%=date %>"></script>

<style type="text/css">

.tableClass{
border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;
}
	
.tableClass td{
border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;cursor:pointer;
}

</style>
<script type="text/javascript">
	function tab_click(tabid){
		if(tabid == 1){
			window.location.href="<%=path%>/goIndex";
		}
		if(tabid == 2){
			window.location.href="<%=path%>/goPersonal";
		}
		if(tabid == 3){
			window.location.href="<%=path%>/goMyStore";
		}
	}
</script>
</head>

<body>
	<input type='hidden' value='${storeid}' id='storeid' />
	<input type='hidden' value='${data.id}' id='hairpackedid' />
	<input type='hidden' value="${data.state}" id="state"  />
	<header class="header-wrap">
    	<div class="header">
        	<div class="logo"><img style="margin-top: 20px;" src="<%=path%>/images/shunjian.png"/></div>
            <div class="nav">
            	<ul>
                	<li>
                    	<a href="#" onclick="tab_click(1)">首页</a>
                        <i></i>
                    </li>
                    <li>
                    	<a href="#" onclick="tab_click(2)">我是美发师</a>
                    	<i></i>
                    </li>
                    <li>
                    	<a href="#" onclick="tab_click(3)">我是美发店</a>
                    	<i><img id="tab3" src="<%=path%>/images/jiandao.png"/></i>
                    </li>
                </ul>
            </div>
        </div>
    </header>
    <div class="box main" style="height:820px">
    	<div class="content">
        	<div class="prop" style="top:100px;height: 700px;">
        		<div id="nav">
					<ul>
						<li><a href="<%=path%>/goStoreinfoValidateInfo">验证信息</a></li>
						<li><a href="<%=path%>/goRiZhiGuanLi">日程管理</a></li>
						<li><a href="<%=path%>/goOrderinfoManage">订单管理</a></li>
						<li><a href="<%=path%>/goStoreinfoWodeyue">我的账户</a></li>
						<c:if test="${storeinfo.istype==1}"><li><a href="<%=path%>/goPacked">套餐设置</a></li></c:if>
					</ul>
				</div>
        	
	        	<input id="storeId" name="storeId" type="hidden" value="${storeinfo.storeid}" />
				<table width="100%">
        			<tr>
        				<td width="50%">套餐设置</td>
        				<td width="50%" align="right">
        					<%-- <a href="<%=path%>/pcLogOut/2" style="cursor:hand" class="button pink">退出</a> --%>
        					<!-- <a href="#" style="cursor:hand" class="button pink">添加套餐</a> -->
                		</td>
        			</tr>
        		</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr id="trHouQiRi" >
						<td align="left"  colspan="2">
							<table style="width: 533px" class="tableClass">
								<tr>
									<td class="mytd">套餐名称：</td>
									<td class="mytd"><input type='text' id='name' size="40" <c:if test="${pagetype=='update'}">readonly='readonly' value='${data.name }'</c:if> /></td>
								</tr>
								<tr>
									<td class="mytd" >套餐描述：</td>
									<td class="mytd" ><textarea cols="40" id='intro' rows="8" <c:if test="${pagetype=='update'}">readonly='readonly'</c:if>><c:if test="${pagetype=='update'}">${data.intro}</c:if></textarea></td>
								</tr>
								<tr>
									<td class="mytd">价格（长发）：</td>
									<td class="mytd"><input type='text' id='lprice' size="40" <c:if test="${pagetype=='update'}">readonly='readonly' value='${data.longhair }'</c:if> /></td>
								</tr>
								<tr>
									<td class="mytd">价格（中发）：</td>
									<td class="mytd"><input type='text' id='mprice' size="40" <c:if test="${pagetype=='update'}">readonly='readonly' value='${data.inhair }'</c:if> /></td>
								</tr>
								<tr>
									<td class="mytd">价格（短发）：</td>
									<td class="mytd"><input type='text' id='sprice' size="40" <c:if test="${pagetype=='update'}">readonly='readonly' value='${data.shorthair }'</c:if> /></td>
								</tr>
								<tr>
									<td class="mytd">预计服务时长(分钟)：</td>
									<td class="mytd"><input type='text' id='times' size="40" <c:if test="${pagetype=='update'}">readonly='readonly' value='${data.times }'</c:if> /></td>
								</tr>
								<tr>
									<td class="mytd" colspan="2">以上内容，保存之后不能修改，请认真填写</td>
									<td class="mytd"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="trHouZhou" >
						<td align="left" colspan="2">
							<table style="width: 533px" class="tableClass">
								<c:if test="${pagetype=='update'}">
									<tr>
										<td class="mytd" >状态：</td>
										<td class="mytd" ><input type='button' value='可用' id='btnStart' <c:if test='${data.state==1}'>disabled='disabled'</c:if>  /><input value='不可用' id='btnClose' type='button' /></td>
									</tr>
								</c:if>
								<tr>
									<td class="mytd" colspan="2"  >服务美发师：</td>
								</tr>
								<tr>
									<td class="mytd" colspan="2" >
										<c:forEach var="usersubinfo" items="${usersubinfoList}" varStatus="st">
											<input type='checkbox' name='hairdresserids' value='${usersubinfo.userid }' <c:forEach var="ump" items="${data.userMyhairPackedModels}"><c:if test='${ump.userid==usersubinfo.userid }'>checked='checked'</c:if></c:forEach>  />&nbsp${usersubinfo.nickname}&nbsp&nbsp
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td colspan="2" >
										<c:if test="${pagetype=='add'}">
											<input type='button' value='保存' id='btnSave' />
										</c:if>
										<c:if test="${pagetype=='update'}">
											<input type='button' value='更新状态' id='btnUpdate' />
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
            </div>
        </div>
    </div>
    
</body>
<script type="text/javascript">
$(function(){

	function validateInput(pack){
		if(pack.name.length==0){
			alert("名称不能为空");
			return ;
		}
		if(pack.intro.length==0){
			alert("描述不能为空");
			return ;
		}
		if(pack.lprice.length==0){
			alert("长发不能为空");
			return ;
		}
		if(pack.mprice.length==0){
			alert("中发不能为空");
			return ;
		}
		if(pack.sprice.length==0){
			alert("短发不能为空");
			return ;
		}
		if(pack.times.length==0){
			alert("时间不能为空");
			return ;
		}
	}
	
	$('#btnSave').click(function(){
		
		var pack={};
		var storeid=$('input#storeid').val();
		pack.storeid=storeid;
		pack.name=$('input#name').val();
		pack.intro=$('textarea#intro').val();
		pack.times=$('input#times').val();
		pack.style=0;
		var lprice=$('input#lprice').val();
		pack.lprice=lprice;
		var mprice=$('input#mprice').val();
		pack.mprice=mprice;
		var sprice=$('input#sprice').val();
		pack.sprice=sprice;
		
		if(isNaN(pack.times)){
			alert('时间必须是数字');
			return ;
		}
		if(isNaN(lprice)){
			alert('长发必须是数字');
			return ;
		}
		if(isNaN(mprice)){
			alert('中发必须是数字');
			return ;
		}
		if(isNaN(sprice)){
			alert('短发必须是数字');
			return ;
		}
		validateInput(pack);
		
		
		pack.prices='1-'+lprice+'_2-'+mprice+'_3-'+sprice;
		var hairdresserids='';
		$('input[name=hairdresserids]:checked').each(function(){
			hairdresserids+=$(this).val()+'_';
		});
		hairdresserids=hairdresserids.substring(0,hairdresserids.lastIndexOf('_'));
		pack.hairdresserids=hairdresserids;
		
		var jsonPack=JSON.stringify(pack);
		
		$.ajax({
			type : 'POST',
			dataType:'json',
			data:{"jsonPack":jsonPack},
			url:'/shunjianmeiweb/H_AddUserHairpacked', 
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(result) {
				if(result.code=1000){
					alert('添加成功');
					document.location.reload();
				}
			},
			error:function(result){
				alert('error');
			}
		});
		//*/
	});
	
	$('#btnUpdate').click(function(){
		/* packid
		state */
		var model={};
		model.packid=$('#hairpackedid').val();
		model.state=$('#state').val();
		var jsonString=JSON.stringify(model);
		$.ajax({
				type : 'POST',
				dataType:'json',
				data:{"jsonString":jsonString},
				url:'/shunjianmeiweb/modifyHairPacked', 
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(result) {
					if(result.code=1000){
						alert('更新成功');
						document.location.reload();
					}
				},
				error:function(result){
					alert('error');
				}
			});
	});
	
	$('#btnStart').click(function(){
		$('#state').val(1);
		$(this).attr("disabled",true);
	});
	$('#btnClose').click(function(){
		$('#state').val(0);
		$('#btnStart').attr("disabled",false);
	});
	
	$('input[type=checkbox]').change(function(){
		var pagetype='${pagetype}';
		if(pagetype=='update'){
			var c=$(this).prop('checked');
			//更改美发师绑定和解绑
			var model={};
			model.hairpackedid=$('#hairpackedid').val();
			model.userid=$(this).val();
			if(c==true){
				model.operation=1;//添加
			}else if(c==false){
				model.operation=2;//减少
			}
			//alert(JSON.stringify(model));
			var jsonString=JSON.stringify(model);
			$.ajax({
				type : 'POST',
				dataType:'json',
				data:{"jsonString":jsonString},
				url:'/shunjianmeiweb/modifyMyHairPacked', 
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(result) {
					if(result.code=1000){
						alert('更新成功');
						document.location.reload();
					}
				},
				error:function(result){
					alert('error');
				}
			}); 
		}
	});
})
</script>
</html>