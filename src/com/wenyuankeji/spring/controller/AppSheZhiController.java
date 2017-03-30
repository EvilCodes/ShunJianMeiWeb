package com.wenyuankeji.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wenyuankeji.spring.model.AppversionModel;
import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.service.IAppversionService;
import com.wenyuankeji.spring.service.IBaseSuggestionService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Controller
public class AppSheZhiController {

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IBaseSuggestionService baseSuggestionService;

	@Autowired
	private IUtilService utilService;

	@Autowired
	private IAppversionService appversionService;

	@Value("#{configProperties['interfaceStatus']}")
	private String interfaceStatus;

	@SuppressWarnings("unchecked")
	@RequestMapping("ChangePassWord")
	/**
	 * 修改用户密码
	 * @param request
	 * @param response
	 * @param UserId
	 * @param OldPassWord
	 * @param NewPassWord
	 */
	public void ChangePassWord(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '1|修改成功',");
			sb.append("'data': [");
			sb.append("],");
			sb.append("'total': 1");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
					.getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				String UserId = inputJsonMap.get("UserID").toString();
				String OldPassWord = inputJsonMap.get("OldPassWord").toString();
				String NewPassWord = inputJsonMap.get("NewPassWord").toString();

				// 执行修改用户密码
				if (userinfoService.updateUserinfo(Integer.parseInt(UserId),
						OldPassWord, NewPassWord, 1)) {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1);
					outJsonMap.put(Constant.DATA, "");
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				}
			} catch (Exception e) {
				// 保存异常信息
				int exceptionId = utilService.addBaseException(
						request.getRequestURI(), request
								.getAttribute("inputJsonInfo").toString(),
						e.getMessage());
				// 设置错误id
				outJsonMap.put(Constant.CODE, Constant.CODE_1200);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
			}

			// 保存请求记录
			utilService.addBaseRequestlog(request.getRequestURI(), request
					.getAttribute("inputJsonInfo").toString(), "",
					ShunJianMeiUtil.outputString(outJsonMap));
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("Feedback")
	/**
	 * 添加意见建议
	 * @param request
	 * @param response
	 * @param UserId
	 * @param Content
	 */
	public void Feedback(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("],");
			sb.append("'total': 1");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
					.getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				String UserId = inputJsonMap.get("UserID").toString();
				String email = inputJsonMap.get("Contact").toString();
				String Content = inputJsonMap.get("Content").toString();
				// base64解密
				String base64Content = ShunJianMeiUtil.getFromBase64(Content);

				BaseSuggestionModel baseSuggestion = new BaseSuggestionModel();
				baseSuggestion.setUserid(Integer.parseInt(UserId));
				baseSuggestion.setEmail(email);
				baseSuggestion.setContent(base64Content);
				baseSuggestion.setSource(1);
				baseSuggestion.setCreatetime(new Date());

				// 执行添加意见建议
				if (baseSuggestionService.addBaseSuggestion(baseSuggestion)) {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, "");
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				}
			} catch (Exception e) {
				// 保存异常信息
				int exceptionId = utilService.addBaseException(
						request.getRequestURI(), request
								.getAttribute("inputJsonInfo").toString(),
						e.getMessage());
				// 设置错误id
				outJsonMap.put(Constant.CODE, Constant.CODE_1200);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
			}

			// 保存请求记录
			utilService.addBaseRequestlog(request.getRequestURI(), request
					.getAttribute("inputJsonInfo").toString(), "",
					ShunJianMeiUtil.outputString(outJsonMap));
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("CheckVersion")
	/**
	 * 检查版本升级
	 * @param request
	 * @param response
	 * @param UserId
	 * @param Content
	 */
	public void CheckVersion(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("],");
			sb.append("'total': 1");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
					.getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				String VCode = inputJsonMap.get("VersionCode").toString();
				String Type = inputJsonMap.get("SystemType").toString();
				// 查询最新版本
				AppversionModel appversion =  appversionService.searchAppversion(VCode, Integer.parseInt(Type));
				
				if (appversion != null) {
					List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
					Map<String, Object> appversionMap = new HashMap<String, Object>();

					appversionMap.put("VCode", appversion.getVcode());
					appversionMap.put("VName", appversion.getVname());
					appversionMap.put("Intro", appversion.getIntro());
					appversionMap.put("URL", appversion.getUrl());
					appversionMap.put("Important", ShunJianMeiUtil.intToString(appversion.getImportant()));
					
					listMap.add(appversionMap);
					
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, listMap);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "您已是最新版本");
				}
			} catch (Exception e) {
				// 保存异常信息
				int exceptionId = utilService.addBaseException(
						request.getRequestURI(), request
								.getAttribute("inputJsonInfo").toString(),
						e.getMessage());
				// 设置错误id
				outJsonMap.put(Constant.CODE, Constant.CODE_1200);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
			}

			// 保存请求记录
			utilService.addBaseRequestlog(request.getRequestURI(), request
					.getAttribute("inputJsonInfo").toString(), "",
					ShunJianMeiUtil.outputString(outJsonMap));
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}
}
