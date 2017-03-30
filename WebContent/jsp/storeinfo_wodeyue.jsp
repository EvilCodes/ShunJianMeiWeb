<%@page import="com.wenyuankeji.spring.util.ShunJianMeiUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String data = ShunJianMeiUtil.getYYYYMMDDhhmmss();
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
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/storeinfo_wodeyue.js?<%=data %>"></script>
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
    <div class="box main" style="height:2000px">
    	<div class="content">
        	<div class="prop_store" style="top:100px;height:1850px">
        	
				<div id="nav">
					<ul>
						<li><a href="<%=path%>/goStoreinfoValidateInfo">验证信息</a></li>
						<li><a href="<%=path%>/goRiZhiGuanLi">日程管理</a></li>
						<li><a href="<%=path%>/goOrderinfoManage">订单管理</a></li>
						<li><a href="<%=path%>/goStoreinfoWodeyue">我的账户</a></li>
						<c:if test="${storeinfo.istype==1}"><li><a href="<%=path%>/goPacked">套餐设置</a></li></c:if>
					</ul>
				</div>
        		
	        	<input id="yajin" name="yajin" type="hidden" value="${yajin}" />
				<table width="100%">
        			<tr>
        				<td width="50%">我的余额</td>
        				<td width="50%" align="right">
        					<a href="<%=path%>/pcLogOut/2" style="cursor:hand" class="button pink">退出</a>
                		</td>
        			</tr>
        		</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="center" width="50%" style="color:red;font-size:30px;">¥${balance}</td>
					</tr>
					<tr>
						<td align="center" width="50%" style="font-size:14px;">可提取余额<font color="red">¥${yajin}</font></td>
					</tr>
					<tr>
						<td align="center" width="50%">
							<!--<a id="btnRecharge" style="cursor:hand" class="button pink">充值</a>
							--><a id="btnWithdrawals" style="cursor:hand" class="button pink">提现</a>
							<!-- <input id="btnRecharge" type="button" value="充值" />
							<input id="btnWithdrawals" type="button" value="提现" /> -->
						</td>
					</tr>
				</table>
				<hr/>
				<table id="trOrderInfo" style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left"  width="50%">
							<a id="btnZhuangTai"  class="button" style="cursor:hand" >按状态选择</a>
							<select id="selState" onchange="loadGrid()">
								<option value="0">全部</option>
								<option value="1">待支付</option>
								<option value="2">待确认</option>
								<option value="3">已预约</option>
								<option value="4">已签到</option>
								<option value="5">服务中</option>
								<option value="6">服务完成</option>
								<option value="7">评价完成</option>
								<option value="8">订单完成</option>
								<option value="9">已取消</option>
								<option value="10">异常处理</option>
							</select>
						</td>
						<td align="right" width="50%">
							<a id="btnDateTime"   class="button" style="cursor:hand" >按时间筛选</a>
							<select id="selDateTime" onchange="loadGrid()">
								<option value="1">降序</option>
								<option value="2">升序</option>
							</select>
						</td>
					</tr>
				</table>
				订单流水
				<hr/>
				<div style="width:100%; margin:20px auto;">
					<div style="height: 320px">
						<!-- ------------------ -->
						<table id="cxdm"></table>
						<!-- ------------------- -->
					</div>
				</div>
				提现流水
				<hr/>
				<div style="width:100%; margin:20px auto;">
					<div style="height: 320px">
						<!-- ------------------ -->
						<table id="tixian"></table>
						<!-- ------------------- -->
					</div>
				</div>
				闪惠流水
				<hr/>
				<div style="width:100%; margin:20px auto;">
					<div style="height: 320px">
						<!-- ------------------ -->
						<table id="shanhui"></table>
						<!-- ------------------- -->
					</div>
				</div>
				
            </div>
        </div>
    </div>
</body>
</html>