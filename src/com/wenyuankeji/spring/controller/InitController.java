package com.wenyuankeji.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.oruit.weixin.bean.SharingSign;
import com.oruit.weixin.api.TicketAPI;
import com.oruit.weixin.api.TokenAPI;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.model.UserShareRecordModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.service.IBasePictureService;
import com.wenyuankeji.spring.service.IUserShareRecordService;
import com.wenyuankeji.spring.service.IUserTradeLogService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;
import com.wenyuankeji.spring.util.SignatureUtil;

@Controller
public class InitController {

	
	//分享记录
	@Autowired
	private IUserShareRecordService userShareRecordService;
	@Autowired
	private IBasePictureService basepictoreService;
	@Value("#{configProperties['ipHost']}")
	private String ipHost;
	@Value("#{configProperties['projectName']}")
	private String projectName;
	
	private String appid="wxd8c9e390c3b136bc";

	private String secret="1cd16a3cdd2ffbb396f4fe59d3451c98";
	
	@Autowired
	private IUtilService utilService;
	
	
	@RequestMapping({ "index", "/" })
	/**
	 * 初始化方法
	 * @param model
	 * @return
	 */
	public String init(HttpServletRequest request, Model model){

		return "index";
	}

	
	@RequestMapping("goIndex")
	/**
	 * 跳转首页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goIndex(HttpServletRequest request, Model model) {

		return "index";
	}
	@RequestMapping("goPersonal")
	/**
	 * 跳转美发师登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goPersonal(HttpServletRequest request, Model model) {

		return "personal";
	}
	@RequestMapping("goMyStore")
	/**
	 * 跳转美发店登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goMyStore(HttpServletRequest request, Model model) {

		return "myStore";
	}
	
	@RequestMapping("goGuanyuwomen")
	/**
	 * 跳转关于我们页面
	 * @param request
	 * @return
	 */
	public String goGuanyuwomen(HttpServletRequest request) {
		return "guanyu";
	}
	
	@RequestMapping("goLianxiwomen")
	/**
	 * 跳转联系我们页面
	 * @param request
	 * @return
	 */
	public String goLianxiwomen(HttpServletRequest request) {
		return "lianxi";
	}
	
	@RequestMapping("goJiedanliucheng")
	/**
	 * 跳转接单流程页面
	 * @param request
	 * @return
	 */
	public String goJiedanliucheng(HttpServletRequest request) {
		return "jiedanliucheng";
	}
	
	@RequestMapping("goyonghufenxiang")
	/**
	 * 跳转联系我们页面
	 * @param request
	 * @param evaid 评论ID
	 * @return
	 */
	public String goyonghufenxiang(HttpServletRequest request,Model model,int evaid) throws BaseException {
		try {
			model.addAttribute("evaid", evaid);

			//根据用户评论ID查询图片
			UserShareRecordModel userShareRecordModel = userShareRecordService.searchUserShareRecord(evaid);
			if (userShareRecordModel != null ) {
				//获取图片ID
				String picId = userShareRecordModel.getPicid();
				if (!"".equals(picId) && picId.trim().length() > 0)
				{
					BasePictureModel basePictureModel = basepictoreService.searchBasePicture(Integer.parseInt(picId));
					if (basePictureModel != null) {
						model.addAttribute("picturepath", ipHost + basePictureModel.getPicturepath());
					}
				}
				else{
					model.addAttribute("picturepath", "");
				}
			}else{
				model.addAttribute("picturepath", "");
			}

			String strBackUrl = request.getRequestURL().toString(); //请求页面或其他地址 
			String reQur = request.getQueryString();
			strBackUrl += StringUtils.isBlank(reQur) ? "" : "?" + reQur;
			//log.info("分享页获取的请求地址：" + strBackUrl);

			//分享签名
			String tokens = TokenAPI.getTokenFromCache(appid, secret);
			String ticket = TicketAPI.getTicketFromCache(appid, tokens);
			SharingSign ss = SignatureUtil.sign(appid, ticket, strBackUrl);
			model.addAttribute("strBackUrl", strBackUrl);
			model.addAttribute("tokens", tokens);
			model.addAttribute("ticket", ticket);
			model.addAttribute("AppId", ss.getAppId());
			model.addAttribute("NonceStr", ss.getNonceStr());
			model.addAttribute("Timestamp", ss.getTimestamp());
			model.addAttribute("Signature", ss.getSignature());
		} catch (Exception e) {
			e.printStackTrace();
			// 保存请求记录
			utilService.addBaseRequestlog("", "", "",
					e.toString());
		}
		
		return "yonghufenxiang";
	}
}