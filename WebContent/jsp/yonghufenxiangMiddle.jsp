<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="顺间美">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="顺间美"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/html/yonghufenxiang/css/reset.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/html/yonghufenxiang/css/common.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/yonghufenxiang.js"></script>
<title>下载APP</title>
</head>
<body style="background:#ffffff;">
    <div class="foot">
        <table class="table">
            <tr>
                <td style="text-align:left;"><font class="font">第1步：点击右上角“在浏览器中打开”</font></td>
            </tr>
        </table>
    </div>
    <div class="foot">
        <table class="table">
            <tr>
                <td style="text-align:left;"><font class="font">第2步：下载安卓版本</font></td>
            </tr>
        </table>
    </div>
    <div class="foot2">
        <input type="submit" class="download" value = "安卓下载" onclick="appDownload()">
    </div>
</body>
</html>

