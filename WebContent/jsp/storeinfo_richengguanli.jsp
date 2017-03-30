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
        				<td width="50%">日程管理</td>
        				<td width="50%" align="right">
        					<a href="<%=path%>/pcLogOut/2" style="cursor:hand" class="button pink">退出</a>
                		</td>
        			</tr>
        		</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					
					<tr>
						<td align="left" width="150px">预约管理</td>
						<td align="left">
							<input type="button" id="btnHouQiTian" value="后7天">
							<input type="button" id="btnHouZhou" style="margin-left: 18px" value="${td8}日">
						</td>
					</tr>
					<tr id="trHouQiRi" >
						<td align="left"  colspan="2">
							<table style="width: 533px" class="tableClass">
								<tr>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td1}')" id="td1">${td1}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td2}')" id="td2">${td2}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td3}')" id="td3">${td3}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td4}')" id="td4">${td4}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td5}')" id="td5">${td5}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td6}')" id="td6">${td6}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td7}')" id="td7">${td7}</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr id="trHouZhou"  style="display: none;">
						<td align="left" colspan="2">
							<table style="width: 533px" class="tableClass">
								<tr>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td8}')" id="td8">${td8}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td9}')" id="td9">${td9}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td10}')" id="td10">${td10}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td11}')" id="td11">${td11}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td12}')" id="td12">${td12}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td13}')" id="td13">${td13}</td>
									<td class="mytd" onclick="setStoreOrderInfo(this,'${td14}')" id="td14">${td14}</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td align="left">可预约座位数:</td>
						<td align="left">
							<select id="selectZuoWeiNum" style="width: 50px">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
							</select>
							&nbsp;&nbsp;
							<input type="button" id="btnAllOk" value="全部可预约">
						</td>
					</tr>
					<tr>
						<td align="right">
							&nbsp;
						</td>
						<td align="left"><input type="button" style="margin-left: 70px;width: 80px" id="btnAllNo" value="全部取消"></td>
					</tr>
					<!-- ------------------ -->
					<tr>
						<td align="left"  colspan="2">
							<table style="width: 533px" class="tableClass">
								<tr>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'10')">
										10:00
										<br>
										<span id="span10" class="spanCss">--</span>座
										<input id="hidOrderid10" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'11')">
										11:00
										<br>
										<span id="span11" class="spanCss">--</span>座
										<input id="hidOrderid11" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'12')">
										12:00
										<br>
										<span id="span12" class="spanCss">--</span>座
										<input id="hidOrderid12" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'13')">
										13:00
										<br>
										<span id="span13" class="spanCss">--</span>座
										<input id="hidOrderid13" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'14')">
										14:00
										<br>
										<span id="span14" class="spanCss">--</span>座
										<input id="hidOrderid14" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'15')">
										15:00
										<br>
										<span id="span15" class="spanCss">--</span>座
										<input id="hidOrderid15" class="hidOrderidCss" type="hidden">
									</td>
								</tr>
								<tr>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'16')">
										16:00
										<br>
										<span id="span16" class="spanCss">--</span>座
										<input id="hidOrderid16" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'17')">
										17:00
										<br>
										<span id="span17" class="spanCss">--</span>座
										<input id="hidOrderid17" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'18')">
										18:00
										<br>
										<span id="span18" class="spanCss">--</span>座
										<input id="hidOrderid18" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'19')">
										19:00
										<br>
										<span id="span19" class="spanCss">--</span>座
										<input id="hidOrderid19" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'20')">
										20:00
										<br>
										<span id="span20" class="spanCss">--</span>座
										<input id="hidOrderid20" class="hidOrderidCss" type="hidden">
									</td>
									<td align="center" class="gongweiTd" onclick="checkYuYue(this,'21')">
										21:00
										<br>
										<span id="span21" class="spanCss">--</span>座
										<input id="hidOrderid21" class="hidOrderidCss" type="hidden">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<!-- ------------------ -->
					
					<tr>
						<td align="left" colspan="2">
							时间点状态：可预约/不可预约/已约满
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
						<select id="selectZuoWeiNum2" style="width: 50px">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
							</select>
							&nbsp;&nbsp;
							<input type="button" id="btnOk" value="可预约" disabled="disabled">
							&nbsp;&nbsp;
							<input type="button" id="btnNo" value="不可预约" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td align="left" colspan="2">
							<hr>
						</td>
					</tr>
					<!-- ------------------ -->
					<tr id="trInfo" style="display: none;">
						<td align="left" colspan="2" style="color: red;">
							没有订单信息。
						</td>
					</tr>
					<tr id="trOrderInfo" style="display: none;">
						<td colspan="3">
							<table  id="tableOrderInfo" >
								
							</table>
						</td>
					</tr>
				</table>
            </div>
        </div>
    </div>
    
</body>
</html>