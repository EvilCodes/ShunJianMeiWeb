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
<script type="text/javascript" src="<%=path%>/js/page/usersubinfo_information.js?<%=date %>"></script>
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
<style type="text/css">
.roundHeadImage{ 
width:80px; 
height:80px; 
border-radius:50px; 
}
</style>
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
    <div class="box main" style="height:1300px">
    	<div class="content">
        	<div class="prop" style="top:100px; height: 1120px;">
	        	<form name="validateinfoFrom" method="POST" enctype="multipart/form-data" action="<%=path %>/doSaveUserInfo">
				<table width="100%">
        			<tr>
        				<td width="50%">美发师信息</td>
        				<td width="50%" align="right">
        					<a href="<%=path%>/pcLogOut/1" style="cursor:hand" class="button pink">退出</a>
                		</td>
        			</tr>
        		</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left">昵称:</td>
						<td align="left" width="86%">
							<input id="buttonFlag" name="buttonFlag" type="hidden" value="0"/>
							<input id="nickname" name="nickname" type="text" maxlength="20" value="${userInfoModel.nickname }"/>
							<input id="checkFlag" name="checkFlag" type="hidden" value="${userSubInfoModel.checkstate }"/>
						</td>
					</tr>
					<tr>
						<td align="left">星座:</td>
						<td align="left">
						<input id="starid" name="starid" type="hidden" value="${userSubInfoModel.starid }"/>
							<select id="txtStarid" name="txtStarid" style="width:100px;height:22px" onchange="getStarid()">
								<c:forEach var="baseStarInfo" items="${baseStarInfoList}">
									<option value="${baseStarInfo.starid}">${baseStarInfo.starname}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left" colspan="3">我的头像:<label style="color: red">（上传的图片大小不能大于2M）</label></td>
					</tr>
					<tr>
						<td align="left">
							<img class="roundHeadImage" id="imgFileName_tx" src="<%=path%>/${userPhoto}" />
							<input id="fileName_tx" name="fileName_tx" type="hidden" value="${hidFileName_tx }" /> 
							<!-- <a id="upload_file_tx" style="cursor:hand" class="button pink">点击上传</a> -->
						</td>
				    </tr>
					<tr>
						<td align="left" colspan="3">我的形象:<label style="color: red">（上传的图片大小不能大于2M）</label></td>
					</tr>	
					<tr>
						<td>
							<img id="imgFileName_xx1" style="height:50px;width:100px" src="<%=path%>/${userImage1}" />
							<input id="fileName_xx1" name="fileName_xx1" type="hidden" value="${hidFileName_xx1 }"/> 
						</td>
						<td>
							<img id="imgFileName_xx2" style="height:50px;width:100px" src="<%=path%>/${userImage2}" />
							<input id="fileName_xx2" name="fileName_xx2" type="hidden" value="${hidFileName_xx2 }"/> 
						</td>
						<td>
							<img id="imgFileName_xx3" style="height:50px;width:100px" src="<%=path%>/${userImage3}" />
							<input id="fileName_xx3" name="fileName_xx3" type="hidden" value="${hidFileName_xx3 }"/> 
						</td>
				    </tr>
				</table>
				<hr/>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td align="left">从业年限:</td>
						<td align="left"><input id="workinglife" name="workinglife" maxlength="500" type="text" value="${userSubInfoModel.workinglife }" /></td>
					</tr>
					<tr>
						<td align="left">个人简介:</td>
						<td align="left">
							<textarea id="intro" name="intro" style="width:400px;height:100px">${userSubInfoModel.intro }</textarea>
						</td>
					</tr>
					
					<tr>
						<td align="left">工作学历经历:</td>
						<td align="left">
							<textarea id="workhistory" name="workhistory" style="width:400px;height:100px">${userSubInfoModel.workhistory }</textarea>
						</td>
					</tr>
					<tr>
						<td align="left">擅长:</td>
						<td align="left">
							<c:forEach var="hairstyle" items="${hairstyleList}" varStatus="status">
								<input type="checkbox" name="checkHairstyle" value="${hairstyle.configvalue}" />${hairstyle.configvalue}
							</c:forEach>
							<input id="hairstyle" name="hairstyle" type="hidden" value="${userSubInfoModel.hairstyle }" />
						</td>
					</tr>
					<tr>
						<td align="left">个人爱好:</td>
						<td align="left">
							<c:forEach var="hobby" items="${hobbyList}" varStatus="status">
								<input type="checkbox" name="checkHobby" value="${hobby.configvalue}" />${hobby.configvalue}
							</c:forEach>
							<input id="hobbies" name="hobbies" type="hidden" value="${userSubInfoModel.hobbies }" />
						</td>
					</tr>
				</table>
				<hr/>
				
				<p>代表作品:<label style="color: red">（上传的图片大小不能大于2M）</label></p>
				<table style="width:100%; margin:auto; border-collapse:separate; border-spacing:15px;">
					<tr>
						<td>
							<img id="imgFileName_zp1" style="height:50px;width:100px" src="<%=path%>/${userWork1}" />
							<input id="fileName_zp1" name="fileName_zp1" type="hidden" value="${hidFileName_zp1 }"/> 
						</td>
						<td>
							<img id="imgFileName_zp2" style="height:50px;width:100px" src="<%=path%>/${userWork2}" />
							<input id="fileName_zp2" name="fileName_zp2" type="hidden" value="${hidFileName_zp2 }"/> 
						</td>
						<td>
							<img id="imgFileName_zp3" style="height:50px;width:100px" src="<%=path%>/${userWork3}" />
							<input id="fileName_zp3" name="fileName_zp3" type="hidden" value="${hidFileName_zp3 }"/> 
						</td>
						<td>
							<img id="imgFileName_zp4" style="height:50px;width:100px" src="<%=path%>/${userWork4}" />
							<input id="fileName_zp4" name="fileName_zp4" type="hidden" value="${hidFileName_zp4 }"/> 
						</td>
				    </tr>
				</table>
				<br/>
				<div class="loginBtn" align="center">
                    <input id="errorMsg" name="errorMsg" type="hidden" value="${errorMsg}"/>
                    <input id="saveButton" name="saveButton" type="button" onclick="doSave()" value="保存" />
                    <input type="button" onclick="doBack()" value="上一步" />
                     <input id="completeButton" name="completeButton" type="button" onclick="doComplete()" value="完成" />
                </div>
                 </form>
            </div>
        </div>
    </div>

</body>
</html>