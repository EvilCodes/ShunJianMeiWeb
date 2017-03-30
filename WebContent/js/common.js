// JavaScript Document
$(function(){
	//    nav-li hover e
    var num;
    $('.nav-main>li[id]').hover(function(){
       /*图标向上旋转*/
        $(this).children().removeClass().addClass('hover-up');
        /*下拉框出现*/
        var Obj = $(this).attr('id');
        num = Obj.substring(3, Obj.length);
        $('#box-'+num).slideDown(300);
    },function(){
        /*图标向下旋转*/
        $(this).children().removeClass().addClass('hover-down');
        /*下拉框消失*/
        $('#box-'+num).hide();
    });
//    hidden-box hover e
    $('.hidden-box').hover(function(){
        /*保持图标向上*/
        $('#li-'+num).children().removeClass().addClass('hover-up');
        $(this).show();
    },function(){
        $(this).slideUp(200);
        $('#li-'+num).children().removeClass().addClass('hover-down');
    });
	
	
	$(document).on("mouseover",".email a img",function(){
		$(this).attr("src","images/greyEmail.png")
	});
	$(document).on("mouseout",".email a img",function(){
		$(this).attr("src","images/blackEmail.png");
	});
	
	$(document).on("mouseover",".weibo a img",function(){
		$(this).attr("src","images/WeiBoGrey.png");
		$(".weibo i").show();
	});
	$(document).on("mouseout",".weibo a img",function(){
		$(this).attr("src","images/blackWeiBo.png");
		$(".weibo i").hide();
	});
	
	$(document).on("mouseover",".weixin a img",function(){
		$(this).attr("src","images/weixinGrey.png");
		$(".weixin i").show();
	});
	$(document).on("mouseout",".weixin a img",function(){
		$(this).attr("src","images/blackWeiXin.png");
		$(".weixin i").hide();
	});
	
	$(document).on("mouseover",".telNumber img",function(){
		$(this).attr("src","images/greyTel.png")
	});
	$(document).on("mouseout",".telNumber img",function(){
		$(this).attr("src","images/blackTel.png")
	});
	
	
	/*************新闻列表*************/
	$(document).on("click",".newsNav ul li",function(){
		oThis=$(this);
		num=oThis.index();
		oThis.children("img").attr("src","images/selected.png");
		oThis.siblings().children("img").attr("src","images/unSelected.png");
		$(".newsCon").children("div").eq(num).show();
		$(".newsCon").children("div").eq(num).siblings().hide();
	 });
	 
	
	 
	 
	 $(".closeVideo").click(function(){
		 	$(".CoverAudio").hide();
			$(".CoverIndex").hide();
		 });
	 $(".chiefOneCover").click(function(){
		 $(".CoverAudio").show();
			$(".CoverIndex").show();
		});
	 
	 
	 
	 
	 /***********发起人信息****************/
	$(document).on("click",".idUploadImg span i",function(){
		$(this).parent("span").hide();
	});
	
	
	$(document).on("click",".kindReturn",function(){
		$(this).children(".selectedRadius").children("img").attr("src","images/selectedRadius.png");
		$(".radius_green").children("img").attr("src","images/radius_green.png")
	});
	$(document).on("click",".virtualReturn",function(){
		$(this).children(".radius_green").children("img").attr("src","images/selectedRadius.png");
		$(".selectedRadius").children("img").attr("src","images/radius_green.png");
	});
	
	
	$(document).on("click",".broseNav ul li",function(){
		oThis=$(this);
		num=oThis.index();
		oThis.addClass("broseNavActive");
		oThis.siblings().removeClass("broseNavActive");
		$(".broseLeft_con").children(".broseLeft_conIndex").eq(num).show();
		$(".broseLeft_con").children(".broseLeft_conIndex").eq(num).siblings().hide();
	 });
	
	
		$(".tab-changes").eq(0).show();
		$(".tab3-all .posabs").eq(0).show();
		$('#tab-change li').bind('click', function() {
		  $(".tab-changes").hide();
		  $('#tab-change li').find("a").removeClass("selectli");
		  $(this).find("a").addClass("selectli");
			$(".tab-changes").eq($(this).index()).show();
		});
		$(".tab3-all h3 a").bind("click",function(){
			$(".tab3-all h3 a").removeClass("checks");
			$(this).addClass("checks");
			$(".tab3-all .posabs").hide();
			$(".tab3-all .posabs").eq($(this).index()).show();
			})
		// 弹出发邮件的JS
			$(function(){
					$("#outsidef").css("cursor","pointer");
					$("#outsidef").click(function(){
							$(".tanchukuang").css("display","block");
							var bodyHeight=$("body").css("height");
							var tanchucengHeight=$(".tanchuceng").css("height",bodyHeight);
							$(".tanchuceng").css("display","block");
					});
					$(".tanchukuang_one2").click(function(){
							$(".tanchukuang").css("display","none");
							$(".tanchuceng").css("display","none");
						});
						
				});

	
	
	/********个人中心*****************/
	$(document).on("click",".personalLeft_list ul li",function(){
		var liTxt=$(this).find("i").text();
		oThis=$(this);
		num=oThis.index();
		oThis.addClass("personalActiveSelect");
		oThis.find("em").addClass("personalActiveEm");
		oThis.siblings().find("em").removeClass("personalActiveEm");
		oThis.siblings().removeClass("personalActiveSelect");
		$(".personalRight_con").children(".personal_other_con").eq(num).show();
		$(".personalRight_con").children(".personal_other_con").eq(num).find("h3").text(liTxt);
		$(".personalRight_con").children(".personal_other_con").eq(num).siblings().hide();
		
	 });
	
	
	
	/***************项目进展********************/
	$(".propBrose").height($("html").height());
	$(".cbp_tmlabel p span img").click(function(){
		$(".propBrose").show();
		$(this).addClass("cbp_tmlabelBigImg");
	});
	$(".propBrose").click(function(){
		$(".propBrose").hide();
		$(".cbp_tmlabel p span img").removeClass("cbp_tmlabelBigImg");
	});
	
	/********************弹框****************/
	$(".uploadBtn").click(function(){
		$(".propUpload").show();
		$(".uploadBox").show();	
	});
	
	$(".uploadtitle i").click(function(){
		$(".propUpload").hide();
		$(".uploadBox").hide();	
	});
	
	$(".uploadImg ul li i").click(function(){
		$(this).parent("li").hide();	
	});
	
	
});


	 
$(document).ready(function () {
	
	/* 图片滚动效果 */
	
	/* 鼠标悬停图片效果 */
	/*$(".mr_zhe_hover").css("top", $('.mr_zhe').eq(0).height());
	$("li").mouseout(function (e) {
		if ((e.pageX < $(this).offset().left || e.pageX > ($(this).offset().left + $(this).width())) || (e.pageY < $(this).offset().top || e.pageY > ($(this).offset().top + $(this).height()))) {
			$(this).find('.mr_zhe_i').show();
			$(this).find('.mr_zhe_hover').hide().stop().animate({ top: '190px' }, { queue: false, duration: 190 });
			return false;
		}

	});
	$('.mr_zhe').mouseover(function (event) {
		$(this).find('.mr_zhe_i').hide();
		$(this).find('.mr_zhe_hover').show().stop().animate({ top: '190px' }, { queue: false, duration: 190 });
		return false;
	});*/

		
	
	
});

