<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>美发店信息</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset2.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/common.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
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
    
    <div class="box main">
    	<div class="content">
        	<div class="prop_store" style="height:800px">
				<p style="font-size:14px">订单</p>
				<hr>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
					<tr>
						<td align="left">订单号：${orderinfo.orderid}</td>
					</tr>
					<tr>
						<td align="left">订单状态：${orderinfo.paystate}</td>
					</tr>
					<tr>
						<td align="left">下一步：&nbsp;&nbsp;&nbsp;${nextStatus} </a></td>
					</tr>
				</table>
				
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
					<tr>
						<td align="left" colspan="2">预约时间：${orderinfo.servicebegintime}</td>
					</tr>
					<tr>
						<td align="left">
							<table>
								<tr>
									<td rowspan="3"><img src="<%=path%>/${userPhoto}" width="100px" height="100px"></td>
								</tr>
								<tr>
									<td>${usersubinfo.truename}&nbsp;&nbsp;&nbsp;&nbsp;${userLevel}</td>
								</tr>
								<tr>
									<td>接单量${usersubinfo.ordernum}次</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td align="left">
							<table>
								<tr>
									<td rowspan="4"><img src="<%=path%>/${storePhoto}" width="100px" height="100px"></td>
								</tr>
								<tr>
									<td>${storeinfo.name}</td>
								</tr>
								<tr>
									<td>地址：${storeinfo.address}</td>
								</tr>
								<tr>
									<td>接单量${storeinfo.orderquantity}次 &nbsp;&nbsp;&nbsp;&nbsp;${storeinfo.carnumber}个停车位</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">${userHairstyle}&nbsp;&nbsp;&nbsp;&nbsp;¥${orderinfo.amount}</td>
					</tr>
				</table>
				
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
					<tr>
						<td align="left">项目：${usersubinfo.hairstyle}</td>
					</tr>
					<tr>
						<td align="left">加单：${orderinfo.additionalcontent} &nbsp;&nbsp;&nbsp; ¥${orderinfo.additionalamount}</td>
					</tr>
				</table>
				
				<p style="font-size:14px">支付信息</p>
				<hr>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;font-size:14px">
					<tr>
						<td align="left">基本支付：¥${orderinfo.amount}</td>
					</tr>
					<tr>
						<td align="left">加单支付：¥${orderinfo.additionalamount}</td>
					</tr>
					<tr>
						<td align="left">优惠：¥${orderinfo.couponamount}</td>
					</tr>
					<tr>
						<td align="right"><a id="btnBack" class="button pink" onclick="window.history.back(-1);">返回</a></td>
					</tr>
				</table>
            </div>
        </div>
    </div>

</body>
</html>