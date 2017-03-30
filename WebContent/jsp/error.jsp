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
<title>error</title>
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/error.js"></script>
</head>
<body>
	<div class="main" style="background:none; width: 1000px; height: 900px;">

		<div style="height:30px; width:100%; float:left;"></div>
		<div class="clear"></div>
		
		<input id="storeName" name="provinceid" type="hidden" value="${storeName}" /> 
		<input id="provinceid" name="provinceid" type="hidden" value="${provinceid}" />
		<input id="cityid" name="cityid" type="hidden" value="${cityid}" /> 
		<input id="districtid" name="districtid" type="hidden" value="${districtid}" /> 
		<input id="address" name="address" type="hidden" value="${address}" />
		<input id="tel" name="tel" type="hidden" value="${tel}" />
		<input id="carnumber" name="carnumber" type="hidden" value="${carnumber}" />
		<input id="businesshoursStart" name="businesshoursStart" type="hidden" value="${businesshoursStart}" />
		<input id="businesshoursEnd" name="businesshoursEnd" type="hidden" value="${businesshoursEnd}" />
		<input id="areaid" name="areaid" type="hidden" value="${areaid}" />
		<input id="empiricalmode" name="empiricalmode" type="hidden" value="${empiricalmode}" />
		<input id="intro" name="intro" type="hidden" value="${intro}" />
		<input id="bossname" name="bossname" type="hidden" value="${bossname}" />
		<input id="bossmobile" name="bossmobile" type="hidden" value="${bossmobile}" />
		<input id="storemanagername" name="storemanagername" type="hidden" value="${storemanagername}" />
		<input id="storemanagermobile" name="storemanagermobile" type="hidden" value="${storemanagermobile}" />
		
		<div style="width:100%; margin:10px auto; text-align:center">
			<c:choose>
				<c:when test="${fn:length(errorMsg) > 0}">
					<font size="4">${errorMsg }</font>
				</c:when>
				<c:otherwise>
					<font size="4">上传的文件大于2M，请重新上传！！！</font>
				</c:otherwise>
			</c:choose>
		</div>
		<a id="btnBack" class="button pink">返回</a>
		<div class="clear"></div>
	</div>
</body>
</html>