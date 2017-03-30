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
<title>关于我们</title>
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
        	<div class="prop_store" style="top:90px;height:450px;">
				<table width="100%">
					<tr align="center">
						<td style="font-size:20px;">关于我们</td>
					</tr>
        			<tr>
        				<td align="left">
        					<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							顺间，致力于打造一个优质的美发O2O平台。用户以合理的价格，在自己附近的美发店，享受到最好的美发服务。顺间通过严格筛选合作伙伴，统一考核美发师的技术，从而确定美发师的级别及价格，统一配发烫发，染发，护理需要的物料，真正标准化技术，服务，流程，物料。
 							<br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							提供流畅体验的线上产品及性价比最高的线下服务，是我们的不懈追求。我们在做每一件事情的时候，心里始终想着我们的用户，用户就是我们未谋面的老友。
							<br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							美发师、美发店是我们最重要的合作伙伴，是我们最宝贵的资源。我们了解从业者的困难，希望通过我们的努力，可以提高美发行业的效率；增加美发师和美发店的收入；让美发这个行业更加受人尊重。
							<br><br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							顺间在快速的发展壮大中，非常欢迎各类人才加入及对我们各种鞭策，我们的联系方式如下，欢迎各种勾搭：
							<br><br>
							微信账号：shunjiankeji
							<br/>
							微博账号：顺间科技
							<br/>
							投递简历：hr@shunjian.love
							<br/>
							媒体采访：caifang@shunjian.love
							<br/>
							联系合作：hezuo@shunjian.love
                		</td>
        			</tr>
        		</table>
            </div>
        </div>
    </div>
    
</body>
</html>