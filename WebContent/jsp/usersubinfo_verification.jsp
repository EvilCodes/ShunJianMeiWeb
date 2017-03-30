<%@page import="com.wenyuankeji.spring.util.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.wenyuankeji.spring.util.ShunJianMeiUtil"%>
<%
	String path = request.getContextPath();
	String date = ShunJianMeiUtil.getYYYYMMDDhhmmss();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>美发师信息</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset2.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/usersubinfo_verification.js?<%=date %>"></script>
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
                    	<i><img id="tab2" src="<%=path%>/images/jiandao.png"/></i>
                    </li>
                    <li>
                    	<a href="#" onclick="tab_click(3)">我是美发店</a>
                    	<i></i>
                    </li>
                </ul>
            </div>
    </header>
    <div class="box main" style="height:970px">
    	<div class="content">
        	<div id="userInfoDiv" class="prop" style="height: 850px;top: 100px">
        		<form name="validateinfoFrom" method="POST" enctype="multipart/form-data" action="<%=path %>/doSaveUserSubInfo">
        		<table width="100%">
        			<tr>
        				<td width="50%">美发师信息->验证信息</td>
        				<td width="50%" align="right">
        					<a href="<%=path%>/pcLogOut/1" style="cursor:hand" class="button pink">退出</a>
                		</td>
        			</tr>
        			<tr>
        				<td>
        					<label id="checkStateMsg" style="color: red"></label>
        				</td>
        			</tr>
        		</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="25%">姓名:</td>
						<td align="left" width="75%"><input id="truename" name="truename" maxlength="50" type="text" value="${userSubInfoModel.truename }" /></td>
					</tr>
					<tr>
						<td align="left">性别:</td>
						<td align="left">
						
							<input id="sex1" name="sex" type="radio" value="1"  <c:if test="${userInfoModel.sex==1}"> checked="checked"</c:if>/>男
							<input id="sex2" name="sex" type="radio" value="2"  <c:if test="${userInfoModel.sex==2}"> checked="checked"</c:if>/>女
							
					</tr>
					<tr>
						<td align="left">常用邮箱:</td>
						<td align="left"><input id="email" name="email" maxlength="50" type="text" value="${userSubInfoModel.email }" /></td>
					</tr>
					<tr>
						<td align="left">国籍:</td>
						<td align="left"><input id="nationality" name="nationality" maxlength="50" type="text" value="${userSubInfoModel.nationality }" /></td>
					</tr>
					<tr>
						<td align="left">常用语言:</td>
						<td align="left"><input id="language" name="language" maxlength="50" type="text" value="${userSubInfoModel.language }" /></td>
					</tr>
					<tr>
						<td align="left">所在城市:</td>
						<td align="left">
							<input id="provinceid" name="provinceid" type="hidden" value="${userInfoModel.provinceid }"/>
							<input id="cityid" name="cityid" type="hidden" value="${userInfoModel.cityid }"/>
							<input id="buttonFlag" name="buttonFlag" type="hidden" value="0"/>
							<input id="checkFlag" name="checkFlag" type="hidden" value="${userSubInfoModel.checkstate }"/>
							<select id="txtProvinceid" name="txtProvinceid" style="width:100px;height:22px" onchange="getBaseCitys()">
								<c:forEach var="baseProvince" items="${baseProvinceList}">
									<option value="${baseProvince.provinceid}">${baseProvince.provincename}</option>
								</c:forEach>
							</select>
							<select id="txtCityid" name="txtCityid" style="width:100px;height:22px" onchange="getCityid()">
								<c:forEach var="baseCity" items="${baseCityModelList}">
									<option value="${baseCity.cityid}">${baseCity.cityname}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="left">常住地址:</td>
						<td align="left"><input id="address" name="address" maxlength="50" type="text" value="${userSubInfoModel.address }" /></td>
					</tr>
					<tr>
						<td align="left">籍贯:</td>
						<td align="left"><input id="household" name="household" maxlength="50" type="text" value="${userSubInfoModel.household }" /></td>
					</tr>
					<tr>
						<td align="left">紧急联系人:</td>
						<td align="left"><input id="contact" name="contact" maxlength="50" type="text" value="${userSubInfoModel.contact }" /></td>
					</tr>
					<tr>
						<td align="left">紧急联系人关系:</td>
						<td align="left"><input id="relationship" name="relationship" maxlength="25" type="text" value="${userSubInfoModel.relationship }" /></td>
					</tr>
					<tr>
						<td align="left">紧急联系人电话:</td>
						<td align="left"><input id="contactmobile" name="contactmobile" maxlength="20" type="text" value="${userSubInfoModel.contactmobile }" /></td>
					</tr>
					<tr>
						<td align="left">身份证:</td>
						<td align="left"><input id="idcard" name="idcard" maxlength="20" type="text" value="${userSubInfoModel.idcard }" /></td>
					</tr>
					<tr>
						<td align="left" style="display: none;">
						<td align="left"><input id="istype" name="istype" maxlength="20" type="text" value="${userSubInfoModel.istype }" style="display: none;"/></td>
					</tr>
				</table>
				<hr/>
				
				<p>身份证正面照</p>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="60%">
							<img class="roundHeadImage" id="imgFileName_zm" style="height:50px;width:100px" src="<%=path%>/${imgFileName_zm}" />
							<a id="upload_file_zm" style="cursor:hand" class="button pink">点击上传</a>
							<input id="fileName_zm" name="fileName_zm" type="hidden" value="${hidfileName_zm }"/> 
						</td>
						<td align="right" width="40%">
							<img style="height:50px;width:100px" src="<%=path%>/images/shenfenzhengmian.jpg" />
						</td>
					</tr>
				</table>
				
				<p>身份证背面照</p>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="60%">
							<img class="roundHeadImage" id="imgFileName_bm" style="height:50px;width:100px" src="<%=path%>/${imgFileName_bm}" />
							<a id="upload_file_bm" style="cursor:hand" class="button pink">点击上传</a>
							<input id="fileName_bm" name="fileName_bm" type="hidden" value="${hidfileName_bm }"/>
					 
						</td>
						<td align="right" width="40%">
							<img style="height:50px;width:100px" src="<%=path%>/images/shenfenbeimian.jpg" />
						</td>
					</tr>
				</table>
				
				<p>手持身份证照</p>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" width="60%">
							<img class="roundHeadImage" id="imgFileName_sc" style="height:50px;width:100px" src="<%=path%>/${imgFileName_sc}" />
							<a id="upload_file_sc" style="cursor:hand" class="button pink">点击上传</a>
							<input id="fileName_sc" name="fileName_sc" type="hidden" value="${hidfileName_sc }"/> 
						</td>
						<td align="right" width="40%">
							<img style="height:50px;width:100px" src="<%=path%>/images/shenfenshouchi.jpg" />
						</td>
					</tr>
				</table>
				<p style="color: red">*上传的图片大小不能大于2M</p>
				<div class="loginBtn" align="center">
					<input id="errorMsg" name="errorMsg" type="hidden" value="${errorMsg}"/>
                    <input id="saveButton" name="saveButton" type="button" onclick="doSave()" value="保存" />
                    <input type="button" onclick="doNext()" value="下一步" />
                </div>
                
                </form>
            </div>
        </div>
    </div>
    
</body>
</html>