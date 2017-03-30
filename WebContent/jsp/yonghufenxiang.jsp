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
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<title>发型助力，颜值爆表！</title>
<script type="text/javascript">
	 
	// 分享标题
	var SharingTitle = "我刚在“顺间”做了一次美发，没有办卡和推销。超赞！";
	// 分享描述
	var SharingContent = "在线预约顶级美发师，没有任何隐形消费，100%正品高端用料，首创7天不满意全额退款，赶紧来试一试吧！";
	// 分享链接
	var SharingUrl = '${strBackUrl}';
	// 分享图标
	/* var SharingImage = "http://shunjianonline.com/images/share_shunjian.png"; */
	var SharingImage = "http://localhost:8081/images/share_shunjian.png";
		
	var appId = '${AppId}';
	var nonceStr = '${NonceStr}';
	var timestamp = '${Timestamp}';
	var signature = '${Signature}';
	/* alert("SharingUrl:" + SharingUrl);
	alert("appId:"+appId);
	alert("nonceStr:"+nonceStr);
	alert("timestamp:"+timestamp);
	alert("signature:"+signature); */
	
	wx.config({
	    appId: appId,
	    timestamp: timestamp,
	    nonceStr: nonceStr,
	    signature: signature,
	    jsApiList: 
		[
	        'checkJsApi',
	        'onMenuShareTimeline',
	        'onMenuShareAppMessage',
	        'onMenuShareQQ',
	        'onMenuShareWeibo'
	    ]
	});
	wx.showMenuItems({
	    menuList: [
	        "menuItem:share:appMessage",
	        "menuItem:share:timeline",
	        "menuItem:share:qq",
	        "menuItem:share:QZone"
	    ]
	});
	wx.ready(function () {
	    wx.onMenuShareTimeline({
	        title: SharingTitle, // 分享标题
	        link: SharingUrl,
	        imgUrl: SharingImage // 分享图标
	    });
	    // 获取“分享给朋友”按钮点击状态及自定义分享内容接口
	    wx.onMenuShareAppMessage({
	        title: SharingTitle, // 分享标题
	        desc: SharingContent, // 分享描述
	        link: SharingUrl,
	        imgUrl: SharingImage,// 分享图标
	        type: 'link', // 分享类型,music、video或link，不填默认为link
	    });
	    // 分享到QQ
	    wx.onMenuShareQQ({
	        title: SharingTitle, // 分享标题
	        desc: SharingContent, // 分享描述
	        link: SharingUrl, // 分享链接
	        imgUrl: SharingImage, // 分享图标
	        success: function () {
	            // 用户确认分享后执行的回调函数
	        },
	        cancel: function () {
	            // 用户取消分享后执行的回调函数
	        }
	    });
	    // 分享到腾讯微博
	    wx.onMenuShareWeibo({
	        title: SharingTitle, // 分享标题
	        desc: SharingContent, // 分享描述
	        link: SharingUrl, // 分享链接
	        imgUrl: SharingImage, // 分享图标
	        success: function () {
	            // 用户确认分享后执行的回调函数
	        },
	        cancel: function () {
	            // 用户取消分享后执行的回调函数
	        }
	    });
	    // 分享到QQ空间
	    wx.onMenuShareQZone({
	        title: SharingTitle, // 分享标题
	        desc: SharingContent, // 分享描述
	        link: SharingUrl, // 分享链接
	        imgUrl: SharingImage, // 分享图标
	        success: function () {
	            // 用户确认分享后执行的回调函数
	        },
	        cancel: function () {
	            // 用户取消分享后执行的回调函数
	        }
	    });
	
	});
	wx.error(function (res) {
	    alert('wx.error: ' + JSON.stringify(res));
	});
</script>
</head>
<body style="background:#ffffff;">
<input type="hidden" id="hidEvaid" value="${evaid}">
    <div class="top">
		<c:choose>
			<c:when test="${picturepath != ''}">
	        	<img src="${picturepath}" />
			</c:when>
			<c:otherwise>
	        	<img src="<%=path %>/html/yonghufenxiang/images/beauty.png" />
			</c:otherwise>
		</c:choose>
    </div>
    <div  style="position:fixed; top:2px; left: 2px;">
    </div>
    <div class="foot">
        <table class="table">
            <tr>
                <td style="text-align:center;"><font class="font">领取<span style="font-size: 20px;color:#ce3e34"> 100 </span>元新春礼券</font></td>
            </tr>
        </table>
    </div>
    <div class="foot">
        <input id="mobile" type="text" class = "input" placeholder = '输入手机号' maxlength="11">
    </div>
    <div class="foot">
        <input type="submit" class = "get" value = "领取优惠券" onclick="updateCoupon()">
    </div>
    <div class="foot2">
        <input type="submit" class = "download" value = "App下载" onclick="appDownload()">
    </div>
</body>
</html>

