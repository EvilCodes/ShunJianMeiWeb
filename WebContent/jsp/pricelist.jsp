<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="顺间美">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="顺间美"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/pricelist/reset.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/css/pricelist/common.css">
<title>顺间美</title>
</head>
<body>
<div class="title">我们的服务项目包含</div>
     <table class="table2 tab2">
        <tbody>
            <tr>
                <td class="title11">基础服务</td>
                <td class="title12">设计剪发</td>
                <td class="title12">造型</td>
                <td></td>
            </tr>
            <tr>
                <td class="title11">附加服务</td>
                <td class="title12">烫发(包含：烫卷，拉直，柔顺)</td>
                <td class="title12">染发</td>
                <td class="title12">护理</td>
            </tr>
        </tbody>
    </table>
    <div class="top"><img src="images/top.png"/></div>
    <div class="title">基础服务</div>
    <table class="table">
    	<tbody>
        	<tr>
            	<td>发型师等级</td>
                <td>设计剪发</td>
                <td>造型</td>
            </tr>
            <tr>
                <td>高级发型师</td>
                <td class="red">￥68</td>
                <td class="red">￥58</td>
            </tr>
            <tr>
            	<td>资深发型师</td>
                <td class="red">￥98</td>
                <td class="red">￥78</td>
            </tr>
            <tr>
            	<td>设计总监</td>
                <td class="red">￥158</td>
                <td class="red">￥138</td>
            </tr>
            <tr>
            	<td>首席总监</td>
                <td class="red">￥398</td>
                <td class="red">￥318</td>
            </tr>
        </tbody>
    </table>
    <div class="title">烫发服务/染发服务 (欧莱雅)</div>
    <table class="table tab2">
    	<tbody>
        	<tr>
            	<td>发型师等级</td>
                <td>长发</td>
                <td>中发</td>
                <td>短发</td>
            </tr>
            <tr>
                <td>高级发型师</td>
                <td class="red">￥330</td>
                <td class="red">￥310</td>
                <td class="red">￥290</td>
            </tr>
            <tr>
            	<td>资深发型师</td>
                <td class="red">￥390</td>
                <td class="red">￥370</td>
                <td class="red">￥350</td>
            </tr>
            <tr>
            	<td>设计总监</td>
                <td class="red">￥480</td>
                <td class="red">￥460</td>
                <td class="red">￥440</td>
            </tr>
            <tr>
            	<td>首席总监</td>
                <td class="red">￥880</td>
                <td class="red">￥860</td>
                <td class="red">￥840</td>
            </tr>
        </tbody>
    </table>
    <div class="title">烫发服务/染发服务 (资生堂)</div>
    <table class="table tab2">
        <tbody>
            <tr>
                <td>发型师等级</td>
                <td>长发</td>
                <td>中发</td>
                <td>短发</td>
            </tr>
            <tr>
                <td>高级发型师</td>
                <td class="red">￥370</td>
                <td class="red">￥340</td>
                <td class="red">￥325</td>
            </tr>
            <tr>
                <td>资深发型师</td>
                <td class="red">￥430</td>
                <td class="red">￥400</td>
                <td class="red">￥385</td>
            </tr>
            <tr>
                <td>设计总监</td>
                <td class="red">￥520</td>
                <td class="red">￥490</td>
                <td class="red">￥475</td>
            </tr>
            <tr>
                <td>首席总监</td>
                <td class="red">￥920</td>
                <td class="red">￥890</td>
                <td class="red">￥875</td>
            </tr>
        </tbody>
    </table>

    <div class="title">头皮护理</div>
    <table class="table tab2">
    	<tbody>
        	<tr>
            	<td>护理</td>
                <td>长发</td>
                <td>中发</td>
                <td>短发</td>
            </tr>
            <tr>
            	<td>欧莱雅深层</td>
                <td class="red">￥130</td>
                <td class="red">￥116</td>
                <td class="red">￥105</td>
            </tr>
            <tr>
            	<td>资生堂护理</td>
                <td class="red">￥180</td>
                <td class="red">￥166</td>
                <td class="red">￥155</td>
            </tr>
        </tbody>
    </table>
</body>
</html>