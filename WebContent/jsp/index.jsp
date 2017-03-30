<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit">
<title>顺间</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/common.css">
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/common.js"></script>
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
                        <i><img id="tab1" src="<%=path%>/images/jiandao.png"/></i>
                    </li>
                    <li>
                    	<a href="#" onclick="tab_click(2)">我是美发师</a>
                    	<i></i>
                    </li>
                    <li>
                    	<a href="#" onclick="tab_click(3)">我是美发店</a>
                    	<i></i>
                    </li>
                </ul>
            </div>
        </div>
    </header>
    <div class="box">
    	<div class="content">
        	<div class="left"><img src="<%=path%>/images/phpne.png"/></div>
            <div class="right">
            	<ul>
                	<li class="apple">
                    	<i><img src="<%=path%>/images/ios.png"/></i>
                        <a href="http://a.app.qq.com/o/simple.jsp?pkgname=cc.ruit.shunjianmei&g_f=991653"><img src="<%=path%>/images/apple.png"/></a>
                    </li>
                    <li class="android">
                    	<i><img src="<%=path%>/images/android.png"/></i>
                        <a href="http://a.app.qq.com/o/simple.jsp?pkgname=cc.ruit.shunjianmei&g_f=991653"><img src="<%=path%>/images/jqr.png"/></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <footer class="footer-wrap">
    	<div class="footer">
        	<ul>
            	<li><a href="<%=path %>/goGuanyuwomen">关于我们</a></li>
                <li><a href="<%=path %>/goLianxiwomen">联系我们</a></li>
                <li><a href="http://weibo.com/shunjiankeji" target="_blank" >新浪微博</a></li>
            </ul>
        </div>
    	<div class="footer">
		Copyright@顺间 - 北京顺间科技有限公司 京ICP备15054479
        </div>
    </footer>
</body>
</html>
