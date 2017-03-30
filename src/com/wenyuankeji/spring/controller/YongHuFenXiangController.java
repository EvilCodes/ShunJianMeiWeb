package com.wenyuankeji.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.service.ICouponsService;
import com.wenyuankeji.spring.service.IYongHuFenXiangService;

@Controller
public class YongHuFenXiangController {
	
	@Autowired 
	private IYongHuFenXiangService yongHuFenXiangService;
	
	@Autowired 
	private ICouponsService couponsService;
	
	@RequestMapping("/updateCoupon")
	/**
	 * 领取优惠券
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> updateCoupon(String evaid,String mobile,
			Model model, HttpSession session) {
		Map<String, Object> editInfoMap = new HashMap<String, Object>();
		try {
			
			//判断手机号是否领取过优惠券
			UserCouponsModel userCouponsModel = yongHuFenXiangService.searchUserCoupons(mobile, Integer.parseInt(evaid));
			if (userCouponsModel != null) {
				editInfoMap.put("editInfo", false);
				editInfoMap.put("message", "当前手机号已领取过优惠券！");
			}else{
				int sum = yongHuFenXiangService.addCoupons(evaid, mobile);
				if (sum > 0) {
					editInfoMap.put("editInfo", true);
					editInfoMap.put("message", "恭喜你获得" + sum + "元优惠券！");
				} else {
					editInfoMap.put("editInfo", false);
					editInfoMap.put("message", "领取优惠券失败！");
				}
			}
		} catch (Exception e) {
			editInfoMap.put("editInfo", false);
			editInfoMap.put("message", "领取优惠券失败！");
		}
		return editInfoMap;
	}
	
	@RequestMapping("goAppDownload")
	/**
	 * 跳转App下载中间页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goAppDownload(HttpServletRequest request, Model model) {
		return "yonghufenxiangMiddle";
	}
	
}