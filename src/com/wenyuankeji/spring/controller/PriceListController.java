package com.wenyuankeji.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PriceListController {
	@RequestMapping("pricelist")
	/**
	 * 跳转首页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String pricelist(HttpServletRequest request, Model model) {

		return "pricelist";
	}
}
