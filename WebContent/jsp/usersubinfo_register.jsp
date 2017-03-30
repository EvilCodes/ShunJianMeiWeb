<%@page import="com.wenyuankeji.spring.util.ShunJianMeiUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String date = ShunJianMeiUtil.getYYYYMMDDhhmmss();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit">
<title>美发师信息</title>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/reset.css">
<link  type="text/css" rel="stylesheet" href="<%=path%>/css/common.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/page/usersubinfo_register.js?<%=date %>"></script>
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
        </div>
    </header>
	 <div class="box main">
    	<div class="content">
    		<div class="prop" style="height: 300px;">
            	<form action="" name="" method="post">
                    <dl>
                        <dt>手机号</dt>
                        <dd><input type="text" id="bindphone" maxlength="20" name="bindphone" placeholder="请输入手机号"/></dd>
                    </dl>
                    <dl>
                        <dt>验证码</dt>
                        <dd style="width: 90px">
                        	<input type="text" id="verificationCode" name="verificationCode" maxlength="6" placeholder="请输入验证码"/>
                        </dd>
                        <div class="loginBtn" style="margin-top:-33px;margin-left:0px" align="center">
                        <input id="btn" type="button" onclick="getverificationCode(this)" value="获取验证码" /></div>
                    </dl>
                    <dl>
                        <dt>密码</dt>
                        <dd><input type="password" id="password" maxlength="50" name="password" placeholder="请输入密码"/></dd>
                    </dl>
                    <dl>
                        <dt>确认密码</dt>
                        <dd><input type="password" id="confirmPassword" maxlength="50" name="confirmPassword" placeholder="请输入密码"/></dd>
                    </dl>
                    <div class="loginBtn" align="center">
                    	<input type="button" onclick="doRegister()" value="注册" />
                    </div>
                </form>
           </div>     
        </div>
    </div>
    
    <footer class="footer-wrap">
    	<div class="footer">
        	<ul>
            	<li><a href="#">关于我们</a></li>
                <li><a href="#">加入我们</a></li>
            </ul>
        </div>
    </footer>
</body>
</html>
