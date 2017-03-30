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
<title>美发师信息</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/usersubinfo_manage.js"></script>
</head>
<body>

    <div class="box main" style="height:850px;">
    	<div class="content">
        	<div class="prop_store" style="height:700px;">
        	
				<p>美发师管理</p>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="25%">美发师ID:</td>
						<td align="left" width="75%">
						<input id="userid" name="userid" type="text" style="width:210px;"/></td>
					</tr>
					<tr>
						<td align="left" width="25%">美发师手机:</td>
						<td align="left" width="75%">
						<input id="bindphone" name="bindphone" type="text" style="width:210px;"/></td>
					</tr>
					<tr>
						<td align="left" width="25%">注册起止时间:</td>
						<td align="left" width="75%">
						<input class="easyui-datebox" style="width:100px" type="text" id="txtdateFrom" name="txtdateFrom" ></input>~
						<input class="easyui-datebox" style="width:100px" type="text" id="txtdateTo" name="txtdateTo" ></input>
						</td>
					</tr>
					<tr>
						<td align="left">美发师状态:</td>
						<td align="left">
							<select id="txtState" name="txtState" style="width:210px;">
								<option value="0">全部</option>
								<option value="1">审核中</option>
								<option value="2">审核通过</option>
								<option value="3">审核失败</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100px;height: 25px" onclick="searchInfo()">筛选</a></td>
						<!--<td><a href="javascript:void(0)" class="easyui-linkbutton" style="width: 100px;height: 25px" onclick="reset()">重置</a></td>
					--></tr>
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