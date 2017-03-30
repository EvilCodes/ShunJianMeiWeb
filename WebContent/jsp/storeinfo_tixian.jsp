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
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/storeinfo_tixian.js"></script>
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
    <div class="box main" style="height:620px">
    	<div class="content">
        	<div class="prop_store"  style="top:100px;height:500px;">
        	
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
        				<td width="50%">账户余额</td>
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
				</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="right" width="25%">
						提&nbsp;现&nbsp;金&nbsp;额&nbsp;：
						</td>
						<td align="left" width="75%">
						<input id="txtAmount" name="txtAmount" type="text" /></td>
					</tr>
					<tr>
						<td align="right" width="25%">
						银行卡账户：
						</td>
						<td align="left" width="75%">
						<input id="txtNumber" maxlength="20" name="txtNumber" readonly="readonly" value="${cardnumber}" type="text" /></td>
					</tr>
					<tr>
						<td align="right" width="25%">
						银行卡户名：
						</td>
						<td align="left" width="75%">
						<input id="txtUserName" maxlength="20" name="txtUserName" readonly="readonly" value="${ownername}" type="text" /></td>
					</tr>
					<tr>
						<td align="right" width="25%">
						开&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;行&nbsp;：
						</td>
						<td align="left" width="75%">
						<input id="txtBank" maxlength="50" name="txtBank" readonly="readonly" value="${bank}" type="text" /></td>
					</tr>
					<tr>
						<td align="right" width="25%">
						</td>
						<td align="left" width="75%">
						<a id="btnOK" style="cursor:hand" class="button pink">确定</a></td>
					</tr>
				</table>
				
            </div>
        </div>
    </div>
    
</body>
</html>