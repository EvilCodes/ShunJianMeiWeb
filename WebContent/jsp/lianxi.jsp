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
<title>联系我们</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset2.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/custom.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxupload.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/storeinfo_validateinfo.js"></script>
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
	<input id="hidmsg" type="hidden" value="${message }">
	<input id="hidstate" type="hidden" value="${storeinfo.state }">
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
                    </li>
                </ul>
            </div>
        </div>
    </header>
    <div class="box main" style="height:570px">
    	<div class="content">
        	<div class="prop_store" style="top:160px;height:330px;">
				<table width="100%">
					<tr align="center">
						<td style="font-size:20px;">联系我们</td>
					</tr>
        			<tr>
        				<td align="left">
        					<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							如果您在使用顺间过程中遇到问题，请致电：134-3955-3792，或者发送邮件：jiazhaohui@shunjian.love
 							<br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							我们欢迎爱技术、爱产品、爱美发、爱一切美好事物的小伙伴加入我们，联系邮箱：hr@shunjian.love
							<br><br>
                		</td>
        			</tr>
        		</table>
            </div>
        </div>
    </div>
    
</body>
</html>