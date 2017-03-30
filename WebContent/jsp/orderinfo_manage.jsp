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
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset2.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/orderinfo_manage.js"></script>
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
        	<div class="prop_store" style="top:100px;margin-left:-325px;height:700px;width:900px;">
        	
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
        				<td width="50%">订单管理</td>
        				<td width="50%" align="right">
        					<a href="<%=path%>/pcLogOut/2" style="cursor:hand" class="button pink">退出</a>
                		</td>
        			</tr>
        		</table>
				<hr/>
				
				<div style="width:100%; margin:20px auto;">
					<div style="height: 600px">
						<!-- ------------------ -->
						<table id="cxdm"></table>
						<!-- ------------------- -->
					</div>
				</div>
				
            </div>
        </div>
    </div>

</body>
</html>