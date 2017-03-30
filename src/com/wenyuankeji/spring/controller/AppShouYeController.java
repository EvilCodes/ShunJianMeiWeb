package com.wenyuankeji.spring.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oruit.weixin.api.PayMchAPI;
import com.oruit.weixin.paymch.bean.MchPayApp;
import com.oruit.weixin.paymch.bean.Unifiedorder;
import com.oruit.weixin.paymch.bean.UnifiedorderResult;
import com.oruit.weixin.util.PayUtil;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.MaterialinfoModel;
import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.ServiceattributeModel;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.model.UserHairstyleMaterialModel;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.model.UserMyhairstyleTimesModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserWorkplaceModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IBaseBusinessareaService;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.ICouponsService;
import com.wenyuankeji.spring.service.IEvaluationListService;
import com.wenyuankeji.spring.service.IFavoritesService;
import com.wenyuankeji.spring.service.IMaterialinfoService;
import com.wenyuankeji.spring.service.IOrderPayService;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.service.IReservationService;
import com.wenyuankeji.spring.service.IServiceattributeService;
import com.wenyuankeji.spring.service.IStorePhotoMappingService;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.service.IUserCouponsService;
import com.wenyuankeji.spring.service.IUserEvaluatePhotoMappingService;
import com.wenyuankeji.spring.service.IUserEvaluateService;
import com.wenyuankeji.spring.service.IUserHairpackedService;
import com.wenyuankeji.spring.service.IUserHairstyleMaterialService;
import com.wenyuankeji.spring.service.IUserHairstyleService;
import com.wenyuankeji.spring.service.IUserMyhairstylePhotoMappingService;
import com.wenyuankeji.spring.service.IUserMyhairstyleService;
import com.wenyuankeji.spring.service.IUserMyhairstyleTimesService;
import com.wenyuankeji.spring.service.IUserPhotoMappingService;
import com.wenyuankeji.spring.service.IUserProfessionLevelServiceService;
import com.wenyuankeji.spring.service.IUserQuickpayService;
import com.wenyuankeji.spring.service.IUserShareRecordService;
import com.wenyuankeji.spring.service.IUserWalletService;
import com.wenyuankeji.spring.service.IUserWorkdateService;
import com.wenyuankeji.spring.service.IUserWorkhourService;
import com.wenyuankeji.spring.service.IUserWorkplaceService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.service.IWalletPayService;
import com.wenyuankeji.spring.service.impl.UserHairpackedServiceImpl;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ContentComparator;
import com.wenyuankeji.spring.util.MessagePushUtil;
//import com.wenyuankeji.spring.util.ContentComparator;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Controller
public class AppShouYeController {

	@Autowired
	private IBaseConfigService baseConfigService;

	@Autowired
	private IUserHairstyleService userHairstyleService;

	@Autowired
	private IUserMyhairstyleService userMyhairstyleService;

	@Autowired
	private IBaseBusinessareaService baseBusinessareaService;

	@Autowired
	private IUsersubinfoService usersubinfoService;

	@Autowired
	private IUserPhotoMappingService userPhotoMappingService;

	@Autowired
	private IFavoritesService favoritesService;

	@Autowired
	private IUserEvaluateService userEvaluateService;

	@Autowired
	private IUserMyhairstyleTimesService userMyhairstyleTimesService;

	@Autowired
	private IUserMyhairstylePhotoMappingService userMyhairstylePhotoMappingService;

	@Autowired
	private IUserProfessionLevelServiceService userProfessionLevelServiceService;

	@Autowired
	private IServiceattributeService serviceattributeService;

	@Autowired
	private IUserEvaluatePhotoMappingService userEvaluatePhotoMappingService;

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IUserWorkplaceService userWorkplaceService;

	@Autowired
	private IStoreinfoService storeinfoService;

	@Autowired
	private IStorePhotoMappingService storePhotoMappingService;

	@Autowired
	private IUserWorkplaceService userWorkplaceServices;

	@Autowired
	private IUtilService utilService;

	@Autowired
	private IUserCouponsService userCouponsService;

	@Autowired
	private IUserWorkdateService userWorkdateService;
	
	@Autowired
	private IUserWorkhourService userWorkhourService;

	@Autowired
	private ICouponsService couponsServie;

	@Autowired
	private IReservationService reservationService;

	@Autowired
	private IOrderPayService orderPayService;

	@Autowired
	private IOrderinfoService orderinfoService;

	@Autowired
	private IUserHairstyleMaterialService userHairstyleMaterialService;

	@Autowired
	private IMaterialinfoService materialinfoService;

	// 钱包支付
	@Autowired
	private IWalletPayService walletPayService;	
	
	@Autowired
	private IUserShareRecordService userShareRecordService;
	
	@Autowired
	private IUserQuickpayService userQuickpayService; 
	
	@Autowired
	private IUserWalletService userWalletService;
	
	@Autowired
	private IUserHairpackedService userHairpackedService;
	
	@Autowired
	private IUserWorkhourDao userWorkhourDao; 
	/**
	 * 评价列表
	 */
	@Autowired
	private IEvaluationListService evaluationListService;

	@Value("#{configProperties['ipHost']}")
	private String ipHost;
	
	@Value("#{configProperties['testing']}")
	private String testing;

	@Value("#{configProperties['interfaceStatus']}")
	private String interfaceStatus;

	@SuppressWarnings("unchecked")
	@RequestMapping("HomePage")
	/**
	 * 2.1	首页（定义）
	 * @param request
	 * @param response
	 */
	public void HomePage(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{'ID': '1',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'ID': '2',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'ID': '3',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'ID': '4',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'ID': '5',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'}");
			sb.append("],");
			sb.append("'total': 5");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {
			// 取得请求参数

			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			// String UserID = inputJsonMap.get("UserID").toString();

			try {
				List<BaseConfigModel> baseConfigList = baseConfigService
						.searchBaseConfig(Constant.CONFIGCODE_04);

				List<Map<String, Object>> pictureList = new ArrayList<Map<String, Object>>();

				for (BaseConfigModel obj : baseConfigList) {
					Map<String, Object> pictureMap = new HashMap<String, Object>();

					BasePictureModel basePicture = baseConfigService
							.searchBasePicture(obj.getConfigvalue());
					if (basePicture != null) {
						pictureMap.put("ID", obj.getConfigvalue());
						pictureMap.put("Photo",
								ipHost + basePicture.getPicturepath());
						
					}else{
						pictureMap.put("ID", "");
						pictureMap.put("Photo", "");
					}
					pictureList.add(pictureMap);
				}

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, pictureList);
				outJsonMap.put(Constant.TOTAL, 1);
			} catch (BaseException e) {
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
	@RequestMapping("HairStyle")
	/**
	 * 2.2	发型分类（定义）
	 * @param request
	 * @param response
	 */
	public void HairStyle(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{'ID': '1',");
			sb.append("'Name': '女士长发'},");
			sb.append("{'ID': '2',");
			sb.append("'Name': '女士中发'},");
			sb.append("{'ID': '3',");
			sb.append("'Name': '女士短发'},");
			sb.append("{'ID': '4',");
			sb.append("'Name': '男士'},");
			sb.append("{'ID': '5',");
			sb.append("'Name': '儿童'}");
			sb.append("],");
			sb.append("'total': 5");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			// String UserID = inputJsonMap.get("UserID").toString();

			try {
				List<UserHairstyleModel> userHairstyleList = userHairstyleService
						.searchUserHairstyle();

				List<Map<String, Object>> pictureList = new ArrayList<Map<String, Object>>();

				for (UserHairstyleModel obj : userHairstyleList) {
					Map<String, Object> pictureMap = new HashMap<String, Object>();

					pictureMap.put("ID",
							ShunJianMeiUtil.intToString(obj.getStyleid()));
					pictureMap.put("Name", obj.getName());
					pictureList.add(pictureMap);
				}

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, pictureList);
				outJsonMap.put(Constant.TOTAL, 1);

			} catch (BaseException e) {
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
	@RequestMapping("HairStyleList")
	/**
	 * 2.3	发型列表（定义）
	 * @param request
	 * @param response
	 */
	public void HairStyleList(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '女士中发',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Price': '300.00',");
			sb.append("'UsedNum': '100'");
			sb.append("}");
			sb.append("],");
			sb.append("'total': 5");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
					.getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				// String userid = inputJsonMap.get("UserID").toString();
				String cityid = inputJsonMap.get("CityID").toString();
				String styleid = inputJsonMap.get("ID").toString();
				String sort = inputJsonMap.get("Sort").toString();
				int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
						.toString());
				int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
						.toString());
				String version = inputJsonMap.get("Infversion").toString();
				// 发型师ID列表
				String userIdStr = userinfoService.searchUserinfoByCityId(Integer.parseInt(cityid),version);

				if (!("").equals(userIdStr) && userIdStr.length() > 0) {
					// 返回集合
					List<Map<String, Object>> myhairstyleList = new ArrayList<Map<String, Object>>();
					// 当前发型师发布的发型
					List<UserMyhairstyleModel> userMyhairstyleModels = userMyhairstyleService
							.searchUserMyhairstyle(userIdStr, styleid, sort,pageSize, pageIndex);

					if(userMyhairstyleModels != null && userMyhairstyleModels.size() > 0){
						for (UserMyhairstyleModel objList : userMyhairstyleModels) {
							Map<String, Object> myhairstyleMap = new HashMap<String, Object>();
	
							UserMyhairstylePhotoMappingModel userMyhairstylePhotoMappingModel = userMyhairstyleService
									.searchUserMyhairstylePhotoMapping(
											objList.getMystyleid(), 1);
	
							myhairstyleMap.put("ID", ShunJianMeiUtil
									.intToString(objList.getMystyleid()));
							myhairstyleMap.put("Name", objList.getName());
	
							if (userMyhairstylePhotoMappingModel != null
									&& userMyhairstylePhotoMappingModel
											.getBasePicture() != null) {
								myhairstyleMap.put("Photo", ipHost
										+ userMyhairstylePhotoMappingModel
												.getBasePicture().getPicturepath());
							} else {
								myhairstyleMap.put("Photo", "");
							}
	
							myhairstyleMap.put("Price", ShunJianMeiUtil
									.floatToString((objList.getPrice())));
							
							UsersubinfoModel usersubinfo = usersubinfoService.searchUsersubinfo(objList.getUserid());
							
							if(usersubinfo != null){
								myhairstyleMap.put("UsedNum", ShunJianMeiUtil
										.intToString(usersubinfo.getOrdernum()));
							}else{
								myhairstyleMap.put("UsedNum", "");
							}
							
							myhairstyleList.add(myhairstyleMap);
						}
	
						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
						outJsonMap.put(Constant.DATA, myhairstyleList);
						outJsonMap.put(Constant.TOTAL, myhairstyleList.size());
					}else{
						// 无数据
						outJsonMap.put(Constant.CODE, Constant.CODE_2100);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
					}
				} else {
					// 保存请求记录
					utilService.addBaseRequestlog(request.getRequestURI(),
							request.getAttribute("inputJsonInfo")
									.toString(), "",
							ShunJianMeiUtil.outputString(outJsonMap));
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				}
			} catch (BaseException e) {
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

			// return outJsonMap;
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("HairStyleList2")
	/**
	 * 2.3	发型列表（定义）
	 * @param request
	 * @param response
	 */
	public void HairStyleList2(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '女士中发',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Price': '300.00',");
			sb.append("'UsedNum': '100'");
			sb.append("}");
			sb.append("],");
			sb.append("'total': 5");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
					.getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				// String userid = inputJsonMap.get("UserID").toString();
				String cityid = inputJsonMap.get("CityID").toString();
				String styleid = inputJsonMap.get("ID").toString();
				String sort = inputJsonMap.get("Sort").toString();
				int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
						.toString());
				int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
						.toString());
				String version = inputJsonMap.get("Infversion").toString();
				// 发型师ID列表
				String userIdStr = userinfoService.searchUserinfoByCityId(Integer.parseInt(cityid),version);

				if (!("").equals(userIdStr) && userIdStr.length() > 0) {
					// 返回集合
					List<Map<String, Object>> myhairstyleList = new ArrayList<Map<String, Object>>();
					// 当前发型师发布的发型
					List<UserMyhairstyleModel> userMyhairstyleModels = userMyhairstyleService
							.searchUserMyhairstyle(userIdStr, styleid, sort,pageSize, pageIndex);

					if(userMyhairstyleModels != null && userMyhairstyleModels.size() > 0){
						for (UserMyhairstyleModel objList : userMyhairstyleModels) {
							Map<String, Object> myhairstyleMap = new HashMap<String, Object>();
	
							UserMyhairstylePhotoMappingModel userMyhairstylePhotoMappingModel = userMyhairstyleService
									.searchUserMyhairstylePhotoMapping(
											objList.getMystyleid(), 1);
	
							myhairstyleMap.put("ID", ShunJianMeiUtil
									.intToString(objList.getMystyleid()));
							myhairstyleMap.put("Name", objList.getName());
	
							if (userMyhairstylePhotoMappingModel != null
									&& userMyhairstylePhotoMappingModel
											.getBasePicture() != null) {
								myhairstyleMap.put("Photo", ipHost
										+ userMyhairstylePhotoMappingModel
												.getBasePicture().getPicturepath());
							} else {
								myhairstyleMap.put("Photo", "");
							}
	
							myhairstyleMap.put("Price", ShunJianMeiUtil
									.floatToString((objList.getPrice())));
							
							UsersubinfoModel usersubinfo = usersubinfoService.searchUsersubinfo(objList.getUserid());
							
							if(usersubinfo != null){
								myhairstyleMap.put("UsedNum", ShunJianMeiUtil
										.intToString(usersubinfo.getOrdernum()));
							}else{
								myhairstyleMap.put("UsedNum", "");
							}
							
							myhairstyleList.add(myhairstyleMap);
						}
	
						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
						outJsonMap.put(Constant.DATA, myhairstyleList);
						outJsonMap.put(Constant.TOTAL, myhairstyleList.size());
					}else{
						// 无数据
						outJsonMap.put(Constant.CODE, Constant.CODE_2100);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
					}
				} else {
					// 保存请求记录
					utilService.addBaseRequestlog(request.getRequestURI(),
							request.getAttribute("inputJsonInfo")
									.toString(), "",
							ShunJianMeiUtil.outputString(outJsonMap));
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				}
			} catch (BaseException e) {
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

			// return outJsonMap;
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("HairStyleDetail")
	/**
	 * 2.4	发型详情（定义）
	 * @param request
	 * @param response
	 */
	public void HairStyleDetail(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '女士中发',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Price': '300.00',");
			sb.append("'IsFavorite': '0',");
			sb.append("'ServiceTimes':");
			sb.append("{");
			sb.append("'洗剪吹': '30',");
			sb.append("'烫发': '120',");
			sb.append("'染发': '120',");
			sb.append("'护理': '60'");
			sb.append("},");
			sb.append("'Intro': '发型简介',");
			sb.append("'User':");
			sb.append("{");
			sb.append("'UserID': '1',");
			sb.append("'Name': '阿东',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'LevelName': '发型总监',");
			sb.append("'Star': '双子座',");
			sb.append("'Score': '4',");
			sb.append("'OrderNum': '132'");
			sb.append("},");
			sb.append("'CommentNum': '122',");
			sb.append("'Comment':");

			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '小美',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Time': '2015-09-09 12:00:02',");
			sb.append("'Content': '这里是评价',");
			sb.append("'Score': '5',");
			sb.append("'Item':");
			sb.append("[");

			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'}");

			sb.append("]");
			sb.append("}");

			sb.append("}");
			sb.append("],");
			sb.append("'total': 1");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
					.getAttribute("inputJsonMap");
			List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			try {

				// 我的发型ID
				int mystyleID = Integer.parseInt(inputJsonMap.get("ID")
						.toString());
				int ID =  Integer.parseInt(inputJsonMap.get("UserID").toString());

				// 我的发型
				UserMyhairstyleModel userMyhairstyle = userMyhairstyleService
						.searchUserMyhairstyle(mystyleID);

				if (userMyhairstyle != null) {
					// 美发师ID
					int userID = userMyhairstyle.getUserid();

					// 发型相册
					UserMyhairstylePhotoMappingModel userMyhairstylePhotoMapping = userMyhairstylePhotoMappingService
							.searchUserMyhairstylePhotoMapping(mystyleID, 2);

					// 发型师
					Map<String, Object> usersubinfoMap = new HashMap<String, Object>();
					UsersubinfoModel usersubinfo = usersubinfoService
							.searchUsersubinfo(userID);
					// 用户ID
					usersubinfoMap.put("UserID",
							ShunJianMeiUtil.intToString(userID));
					// 昵称
					usersubinfoMap.put("Name", usersubinfo.getUserinfoModel()
							.getNickname());

					UserPhotoMappingModel wp1 = userPhotoMappingService
							.searchUserPhoto(usersubinfo.getUserid(), 1);

					if (wp1 != null && wp1.getBasePicture() != null) {
						usersubinfoMap.put("Photo", ipHost
								+ wp1.getBasePicture().getPicturepath());
					} else {
						usersubinfoMap.put("Photo", "");
					}

					usersubinfoMap.put("LevelName", usersubinfo
							.getUserProfessionLevel().getLevelname());
					usersubinfoMap.put("Star", usersubinfo
							.getBaseStarinfoModel().getStarname());
					usersubinfoMap.put("Score", ShunJianMeiUtil
							.floatToString(usersubinfo.getScore()));
					usersubinfoMap.put("OrderNum", ShunJianMeiUtil
							.intToString(usersubinfo.getOrdernum()));
					
					usersubinfoMap.put("istype", usersubinfo.getIstype());
					//hasPacked
					//判断是否有套餐
					if(usersubinfo.getIstype() == 1){
						JSONObject jsonResult = userHairpackedService.searchUserHairpackedByHairdresserid(usersubinfo.getUserid()+"");
						if(jsonResult.getJSONArray("data").isEmpty()){
							usersubinfoMap.put("hasPacked", 0);
						}else{
							usersubinfoMap.put("hasPacked", 1);
						}
					}

					// 评论
					List<UserEvaluateModel> userEvaluateList = userEvaluateService
							.searchUserEvaluate(userID, 1);

					Map<String, Object> userEvaluateMap = new HashMap<String, Object>();

					if (userEvaluateList != null && userEvaluateList.size() > 0) {
						UserEvaluateModel userEvaluateModel = userEvaluateList.get(0);
						UserinfoModel userinfo = userinfoService
								.searchUserinfoById(userEvaluateModel.getUserid());

						userEvaluateMap.put("ID", ShunJianMeiUtil
								.intToString(userinfo.getUserid()));
						userEvaluateMap.put("Name", userinfo.getNickname());
						// 评论相册
						UserPhotoMappingModel wp2 = userPhotoMappingService
								.searchUserPhoto(userinfo.getUserid(), 1);

						if (wp2 != null && wp2.getBasePicture() != null) {
							userEvaluateMap.put("Photo", ipHost
									+ wp2.getBasePicture().getPicturepath());
						} else {
							userEvaluateMap.put("Photo", "");
						}

						userEvaluateMap.put("Time", ShunJianMeiUtil
								.dateConvertString(userEvaluateModel
										.getCreatetime()));
						userEvaluateMap.put("Content", userEvaluateModel
								.getContent());
						userEvaluateMap.put("Score", ShunJianMeiUtil
								.floatToString(userEvaluateModel
										.getScore()));
						
						List<Map<String, Object>> userEvaluatePhotoMappingList = new ArrayList<Map<String, Object>>();
						
						//根据当前的评价ID，查询对应的配图列表
						List<UserEvaluatePhotoMappingModel> list = userEvaluatePhotoMappingService
								.searchUserEvaluatePhotoMappingg(userEvaluateModel.getEvaid());
						if (list != null && list.size() > 0) {
							for (UserEvaluatePhotoMappingModel obj : list) {
								Map<String, Object> map = new HashMap<String, Object>();
								if (obj != null && obj.getBasePicture() !=null) {
									map.put("Photo", ipHost+ obj.getBasePicture().getPicturepath());
								} else {
									map.put("Photo", "");
								}
								userEvaluatePhotoMappingList.add(map);
							}
						}
						
						/*// 评论图片
						List<Map<String, Object>> userEvaluatePhotoMappingList = new ArrayList<Map<String, Object>>();
						for (UserEvaluateModel obj : userEvaluateList) {
							Map<String, Object> map = new HashMap<String, Object>();

							UserEvaluatePhotoMappingModel userEvaluatePhotoMapping = userEvaluatePhotoMappingService
									.searchUserEvaluatePhotoMappingg(obj
											.getEvaid());

							if (userEvaluatePhotoMapping != null
									&& userEvaluatePhotoMapping
											.getBasePicture() != null) {
								map.put("Photo", ipHost
										+ userEvaluatePhotoMapping
												.getBasePicture()
												.getPicturepath());

							} else {
								map.put("Photo", "");
							}

							userEvaluatePhotoMappingList.add(map);
						}*/
						userEvaluateMap.put("Item",userEvaluatePhotoMappingList);
					}

					// 发型
					Map<String, Object> myhairstyleMap = new HashMap<String, Object>();
					myhairstyleMap.put("ID", ShunJianMeiUtil
							.intToString(userMyhairstyle.getMystyleid()));
					myhairstyleMap.put("Name", userMyhairstyle.getName());

					if (userMyhairstylePhotoMapping != null
							&& userMyhairstylePhotoMapping.getBasePicture() != null) {
						myhairstyleMap.put("Photo", ipHost
								+ userMyhairstylePhotoMapping.getBasePicture()
										.getPicturepath());
					} else {
						myhairstyleMap.put("Photo", "");
					}

					myhairstyleMap.put("Price", ShunJianMeiUtil
							.floatToString(userMyhairstyle.getPrice()));
					// 收藏
					//FavoritesModel favorites = favoritesService.searchFavorites(userID, 1, mystyleID);
					FavoritesModel favorites = favoritesService.searchFavorites(ID, 1, mystyleID);

					if (null != favorites)
						myhairstyleMap.put("IsFavorite", "1");
					else
						myhairstyleMap.put("IsFavorite", "0");
					// 美发师职称等级服务价格
					List<UserProfessionLevelServiceModel> userProfessionLevelServiceModelList = userProfessionLevelServiceService
							.searchUserProfessionLevelService(usersubinfo
									.getLevelid());
					Map<String, Object> map = new HashMap<String, Object>();
					for (UserProfessionLevelServiceModel obj : userProfessionLevelServiceModelList) {
						// 服务属性
						ServiceattributeModel serviceattribute = serviceattributeService
								.searchServiceattribute(obj.getServicecode());
						// 发型时间
						UserMyhairstyleTimesModel userMyhairstyleTimes = userMyhairstyleTimesService
								.searchUserMyhairstyleTimes(mystyleID,serviceattribute
										.getServicecode());

						map.put(serviceattribute.getServicename(), 
								ShunJianMeiUtil.intToString(userMyhairstyleTimes.getTimes()));
					}

					myhairstyleMap.put("ServiceTimes", map);
					myhairstyleMap.put("Intro", userMyhairstyle.getIntro());
					myhairstyleMap.put("User", usersubinfoMap);
					myhairstyleMap.put("CommentNum", ShunJianMeiUtil
							.intToString(userEvaluateList.size()));
					myhairstyleMap.put("Comment", userEvaluateMap);

					outList.add(myhairstyleMap);
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);

				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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
	@RequestMapping("BusinessArea")
	/**
	 * 2.5	商圈（定义）
	 * @param request
	 * @param response
	 */
	public void BusinessArea(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '国贸'");
			sb.append("},");

			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '双井'");
			sb.append("},");

			sb.append("{");
			sb.append("'ID': '3',");
			sb.append("'Name': '望京'");
			sb.append("}");

			sb.append("],");
			sb.append("'total': 3");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");
			
			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				// String UserID = inputJsonMap.get("UserID").toString();
				int cityId = Integer.parseInt(inputJsonMap.get("CityID")
						.toString());

				// 将商圈表中的信息存入集合中
				List<BaseBusinessareaModel> baseBusinessareaList = baseBusinessareaService
						.searchBaseBusinessarea(cityId);
				// 判断商圈表中是否有值
				if (null == baseBusinessareaList
						|| baseBusinessareaList.size() == 0) {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				} else {
					// 将输出的结果存入集合中
					List<Map<String, Object>> businessareaList = new ArrayList<Map<String, Object>>();
					// 遍历商圈表中的信息
					for (BaseBusinessareaModel obj : baseBusinessareaList) {
						// 存储商圈的ID和名称
						Map<String, Object> businessareaMap = new HashMap<String, Object>();
						// 商圈ID
						businessareaMap.put("ID",
								ShunJianMeiUtil.intToString(obj.getAreaid()));
						// 商圈名称
						businessareaMap.put("Name", obj.getAreaname());
						// 给输出的结果集合中添加数据
						businessareaList.add(businessareaMap);
					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, businessareaList);
					outJsonMap.put(Constant.TOTAL, 5);
				}

				// 保存请求记录
				utilService.addBaseRequestlog(request.getRequestURI(), request
						.getAttribute("inputJsonInfo").toString(),
						"", ShunJianMeiUtil.outputString(outJsonMap));

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
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("HairdresserList")
	/**
	 * 2.6	发型师列表（定义）
	 * @param request
	 * @param response
	 */
	public void HairdresserList(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '阿东',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Image': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'LevelName': '发型总监',");
			sb.append("'Star': '双子座',");
			sb.append("'Score': '4',");
			sb.append("'OrderNum': '132'");
			sb.append("}");

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
				// 用户ID
				// int UserID =
				// Integer.parseInt((inputJsonMap.get("UserID").toString()));
				// 1~7未来7天
				int Days = Integer.parseInt((inputJsonMap.get("Days")
						.toString()));
				// 商圈ID 0代表全部商圈
				int AreaID = Integer.parseInt((inputJsonMap.get("AreaID")
						.toString()));
				// 城市ID
				int CityID = Integer.parseInt((inputJsonMap.get("CityID")
						.toString()));
				// 1发型师级别高到低 2接单量高到低 3评价高到低
				int Sort = Integer.parseInt((inputJsonMap.get("Sort")
						.toString()));
				// 返回数据行数
				int PageSize = Integer.parseInt((inputJsonMap.get("PageSize")
						.toString()));
				// 当前页数 从1开始
				int PageIndex = Integer.parseInt((inputJsonMap.get("PageIndex")
						.toString()));
				//请求的版本号
				String Infversion=inputJsonMap.get("Infversion").toString();
				// 查询美发师列表
				List<UsersubinfoModel> userMyhairstyles = usersubinfoService
						.searchUsersubinfoList(Days, AreaID, CityID, Sort,
								PageSize, PageIndex,Infversion);

				// 判断是否有用户
				if (null != userMyhairstyles && userMyhairstyles.size() > 0) {

					List<Map<String, Object>> hairdresserList = new ArrayList<Map<String, Object>>();

					for (UsersubinfoModel usersubinfo : userMyhairstyles) {

						// 将发型师的属性以键值对的形式存入集合中
						Map<String, Object> hairdresserMap = new HashMap<String, Object>();

						// 将发型师的属性值取出
						hairdresserMap.put("ID", ShunJianMeiUtil
								.intToString(usersubinfo.getUserid()));
						hairdresserMap.put("Name", usersubinfo
								.getUserinfoModel().getNickname());
						// add by jiazhaohui
						// 美发师的国籍和语言
						hairdresserMap.put("Nationality", usersubinfo.getNationality());
						hairdresserMap.put("Language", usersubinfo.getLanguage());
						hairdresserMap.put("Flagpng", usersubinfo.getFlagpng());

						String istypeString="资深合作";
						if(usersubinfo.getIstype()==0){
							istypeString="资深合作";
						}else if(usersubinfo.getIstype()==1){
							istypeString="自由定价";
						}
						hairdresserMap.put("istypeString", istypeString);//是否是直营
						
						//判断是否有套餐
						if(usersubinfo.getIstype() == 1){
							JSONObject jsonResult = userHairpackedService.searchUserHairpackedByHairdresserid(usersubinfo.getUserid()+"");
							if(jsonResult.getJSONArray("data").isEmpty()){
								hairdresserMap.put("hasPacked", 0);
							}else{
								hairdresserMap.put("hasPacked", 1);
							}
						}
						
						// 判断用户相册中是否有值
						UserPhotoMappingModel userPhoto = userPhotoMappingService
								.searchUserPhoto(usersubinfo.getUserid(), 1);
						if (null != userPhoto
								&& null != userPhoto.getBasePicture()) {
							hairdresserMap.put("Photo", ipHost
									+ userPhoto.getBasePicture()
											.getPicturepath());
						} else {
							hairdresserMap.put("Photo", "");
						}

						// 获取配图
						userPhoto = userPhotoMappingService.searchUserPhoto(
								usersubinfo.getUserid(), 7, 1);

						if (null != userPhoto
								&& null != userPhoto.getBasePicture()) {
							hairdresserMap.put("Image", ipHost
									+ userPhoto.getBasePicture()
											.getPicturepath());
						} else {
							hairdresserMap.put("Image", "");
						}

						hairdresserMap.put("LevelName", usersubinfo
								.getUserProfessionLevel().getLevelname());
						
						//洗剪吹的价格						
						UserProfessionLevelServiceModel userProfessionLevelService = userProfessionLevelServiceService
							.searchUserProfessionLevelService(usersubinfo.getLevelid(), "xjc");
						if(userProfessionLevelService != null){
							hairdresserMap.put("Price", ShunJianMeiUtil.intToString(
									userProfessionLevelService.getPrice()));
						}else{
							hairdresserMap.put("Price", "0");
						}

						hairdresserMap.put("Star", usersubinfo
								.getBaseStarinfoModel().getStarname());
						hairdresserMap.put("Score", ShunJianMeiUtil
								.floatToString(usersubinfo.getScore()));
						hairdresserMap.put("OrderNum", ShunJianMeiUtil
								.intToString(usersubinfo.getOrdernum()));
						
						if(Days == 0){
							hairdresserList.add(hairdresserMap);
						}else{
							//判断当前发型师工时全部是休息状态
							//未来工作日
							String futureDay = ShunJianMeiUtil.getFutureDay(Days);
							String year = futureDay.substring(0, 4);// 获取年份
							String month = futureDay.substring(4, 6);// 获取月份
							String day = futureDay.substring(6, 8);// 获取日
							if(userWorkdateService.searchUserWorkdateByUserId(usersubinfo.getUserid(),
									year, month, day)){
								hairdresserList.add(hairdresserMap);
							}
						}
					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, hairdresserList);
					outJsonMap.put(Constant.TOTAL, 1);

				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				}
			} catch (Exception e) {
				// 保存异常信息
				int exceptionId = utilService.addBaseException(
						request.getRequestURI(), request
								.getAttribute("inputJsonInfo").toString(),
						e.getMessage() == null ? e.toString() : e.getMessage());
				// 设置错误id
				outJsonMap.put(Constant.CODE, Constant.CODE_1200);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
			}

			// 保存请求记录
			utilService.addBaseRequestlog(request.getRequestURI(), request
					.getAttribute("inputJsonInfo").toString(), "",
					ShunJianMeiUtil.outputString(outJsonMap));

			// return outJsonMap;
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("HairdresserList2")
	/**
	 * 2.6	发型师列表（定义）
	 * @param request
	 * @param response
	 */
	public void HairdresserList2(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '阿东',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Image': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'LevelName': '发型总监',");
			sb.append("'Star': '双子座',");
			sb.append("'Score': '4',");
			sb.append("'OrderNum': '132'");
			sb.append("}");

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
				// 用户ID
				// int UserID =
				// Integer.parseInt((inputJsonMap.get("UserID").toString()));
				// 1~7未来7天
				int Days = Integer.parseInt((inputJsonMap.get("Days")
						.toString()));
				// 商圈ID 0代表全部商圈
				int AreaID = Integer.parseInt((inputJsonMap.get("AreaID")
						.toString()));
				// 城市ID
				int CityID = Integer.parseInt((inputJsonMap.get("CityID")
						.toString()));
				// 1发型师级别高到低 2接单量高到低 3评价高到低
				int Sort = Integer.parseInt((inputJsonMap.get("Sort")
						.toString()));
				// 返回数据行数
				int PageSize = Integer.parseInt((inputJsonMap.get("PageSize")
						.toString()));
				// 当前页数 从1开始
				int PageIndex = Integer.parseInt((inputJsonMap.get("PageIndex")
						.toString()));
				//请求的版本号
				String Infversion=inputJsonMap.get("Infversion").toString();
				// 查询美发师列表
				List<UsersubinfoModel> userMyhairstyles = usersubinfoService
						.searchUsersubinfoList(Days, AreaID, CityID, Sort,PageSize, PageIndex,Infversion);

				// 判断是否有用户
				if (null != userMyhairstyles && userMyhairstyles.size() > 0) {

					List<Map<String, Object>> hairdresserList = new ArrayList<Map<String, Object>>();
					for (UsersubinfoModel usersubinfo : userMyhairstyles) {

						// 将发型师的属性以键值对的形式存入集合中
						Map<String, Object> hairdresserMap = new HashMap<String, Object>();

						// 将发型师的属性值取出
						hairdresserMap.put("ID", ShunJianMeiUtil
								.intToString(usersubinfo.getUserid()));
						hairdresserMap.put("Name", usersubinfo
								.getUserinfoModel().getNickname());
						// add by jiazhaohui
						// 美发师的国籍和语言
						hairdresserMap.put("Nationality", usersubinfo.getNationality());
						hairdresserMap.put("Language", usersubinfo.getLanguage());
						hairdresserMap.put("Flagpng", usersubinfo.getFlagpng());

						String istypeString="资深合作";
						if(usersubinfo.getIstype()==0){
							istypeString="资深合作";
						}else if(usersubinfo.getIstype()==1){
							istypeString="自由定价";
						}
						hairdresserMap.put("istypeString", istypeString);//是否是直营
						
						//判断是否有套餐
						if(usersubinfo.getIstype() == 1){
							JSONObject jsonResult = userHairpackedService.searchUserHairpackedByHairdresserid(usersubinfo.getUserid()+"");
							if(jsonResult.getJSONArray("data").isEmpty()){
								hairdresserMap.put("hasPacked", 0);
							}else{
								hairdresserMap.put("hasPacked", 1);
							}
						}
						
						// 判断用户相册中是否有值
						UserPhotoMappingModel userPhoto = userPhotoMappingService
								.searchUserPhoto(usersubinfo.getUserid(), 1);
						if (null != userPhoto
								&& null != userPhoto.getBasePicture()) {
							hairdresserMap.put("Photo", ipHost
									+ userPhoto.getBasePicture()
											.getPicturepath());
						} else {
							hairdresserMap.put("Photo", "");
						}

						// 获取配图
						userPhoto = userPhotoMappingService.searchUserPhoto(
								usersubinfo.getUserid(), 7, 1);

						if (null != userPhoto
								&& null != userPhoto.getBasePicture()) {
							hairdresserMap.put("Image", ipHost
									+ userPhoto.getBasePicture()
											.getPicturepath());
						} else {
							hairdresserMap.put("Image", "");
						}

						hairdresserMap.put("LevelName", usersubinfo
								.getUserProfessionLevel().getLevelname());
						
						//洗剪吹的价格						
						UserProfessionLevelServiceModel userProfessionLevelService = userProfessionLevelServiceService
							.searchUserProfessionLevelService(usersubinfo.getLevelid(), "xjc");
						if(userProfessionLevelService != null){
							hairdresserMap.put("Price", ShunJianMeiUtil.intToString(
									userProfessionLevelService.getPrice()));
						}else{
							hairdresserMap.put("Price", "0");
						}

						hairdresserMap.put("Star", usersubinfo
								.getBaseStarinfoModel().getStarname());
						hairdresserMap.put("Score", ShunJianMeiUtil
								.floatToString(usersubinfo.getScore()));
						hairdresserMap.put("OrderNum", ShunJianMeiUtil
								.intToString(usersubinfo.getOrdernum()));
						
						if(Days == 0){
							hairdresserList.add(hairdresserMap);
						}else{
							//判断当前发型师工时全部是休息状态
							//未来工作日
							String futureDay = ShunJianMeiUtil.getFutureDay2(Days);
							String year = futureDay.substring(0, 4);// 获取年份
							String month = futureDay.substring(4, 6);// 获取月份
							String day = futureDay.substring(6, 8);// 获取日
							if(userWorkdateService.searchUserWorkdateByUserId(usersubinfo.getUserid(),
									year, month, day)){
								hairdresserList.add(hairdresserMap);
							}
						}
					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, hairdresserList);
					outJsonMap.put(Constant.TOTAL, 1);

				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				}
			} catch (Exception e) {
				// 保存异常信息
				int exceptionId = utilService.addBaseException(
						request.getRequestURI(), request
								.getAttribute("inputJsonInfo").toString(),
						e.getMessage() == null ? e.toString() : e.getMessage());
				// 设置错误id
				outJsonMap.put(Constant.CODE, Constant.CODE_1200);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
			}

			// 保存请求记录
			utilService.addBaseRequestlog(request.getRequestURI(), request
					.getAttribute("inputJsonInfo").toString(), "",
					ShunJianMeiUtil.outputString(outJsonMap));

			// return outJsonMap;
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("HairdresserDetail")
	/**
	 * 2.7	发型师详情
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> HairdresserDetail(
			HttpServletRequest request, HttpServletResponse response) {

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		if (Integer.parseInt(interfaceStatus) == 1) {
			List<Map<String, Object>> hairStyleList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairdresserMap = new HashMap<String, Object>();
			hairdresserMap.put("UserID", "1");
			hairdresserMap.put("Name", "阿东");
			hairdresserMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			hairdresserMap.put("LevelName", "发型总监");
			hairdresserMap.put("Star", "双子座");
			hairdresserMap.put("Score", "4");
			hairdresserMap.put("OrderNum", "132");
			hairdresserMap.put("IsFavorite", "0");

			List<Map<String, Object>> imagesList = new ArrayList<Map<String, Object>>();
			Map<String, Object> imagesMap1 = new HashMap<String, Object>();
			imagesMap1.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			Map<String, Object> imagesMap2 = new HashMap<String, Object>();
			imagesMap2.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			Map<String, Object> imagesMap3 = new HashMap<String, Object>();
			imagesMap3.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			imagesList.add(imagesMap1);
			imagesList.add(imagesMap2);
			imagesList.add(imagesMap3);
			hairdresserMap.put("Images", imagesList);

			hairdresserMap.put("CommentNum", "122");
			Map<String, Object> commentMap = new HashMap<String, Object>();
			commentMap.put("ID", "1");
			commentMap.put("Name", "小美");
			commentMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			commentMap.put("Time", "2015-09-09 12:00:02");
			commentMap.put("Content", "这里是评价");
			commentMap.put("Score", "5");
			commentMap.put("Item", imagesList);

			hairdresserMap.put("Comment", commentMap);

			Map<String, Object> introMap = new HashMap<String, Object>();

			List<Map<String, Object>> hairList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairMap1 = new HashMap<String, Object>();
			hairMap1.put("Name", "新娘盘头");
			Map<String, Object> hairMap2 = new HashMap<String, Object>();
			hairMap2.put("Name", "女士长发造型");
			Map<String, Object> hairMap3 = new HashMap<String, Object>();
			hairMap3.put("Name", "烫染");
			hairList.add(hairMap1);
			hairList.add(hairMap2);
			hairList.add(hairMap3);

			introMap.put("Hair", hairList);

			List<Map<String, Object>> hobbiesList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hobbiesMap1 = new HashMap<String, Object>();
			hobbiesMap1.put("Name", "音乐");
			Map<String, Object> hobbiesMap2 = new HashMap<String, Object>();
			hobbiesMap2.put("Name", "登山");
			Map<String, Object> hobbiesMap3 = new HashMap<String, Object>();
			hobbiesMap3.put("Name", "睡觉");
			hobbiesList.add(hobbiesMap1);
			hobbiesList.add(hobbiesMap2);
			hobbiesList.add(hobbiesMap3);

			introMap.put("Hobbies", hobbiesList);
			introMap.put("Content", "这里是个人简介");

			hairdresserMap.put("Intro", introMap);

			Map<String, Object> hairStyleMap = new HashMap<String, Object>();
			hairStyleMap.put("ID", "1");
			hairStyleMap.put("Name", "女士中发");
			hairStyleMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			hairStyleMap.put("Price", "300.00");
			hairStyleMap.put("UsedNum", "100");

			hairStyleList.add(hairStyleMap);

			hairdresserMap.put("HairStyle", hairStyleList);

			outList.add(hairdresserMap);
			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, 1);
			return outJsonMap;
			// 输出流
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
					.getAttribute("inputJsonMap");
			try {
				// 用户ID
				int UserId = Integer.parseInt(inputJsonMap.get("UserID")
						.toString());
				// 发型师ID
				int ID = Integer.parseInt(inputJsonMap.get("ID").toString());

				// /////////////////////////////////

				Map<String, Object> outMap = new HashMap<String, Object>();

				// 查询美发师信息
				UsersubinfoModel usersubinfo = usersubinfoService
						.searchUsersubinfo(ID);

				if (null != usersubinfo) {
					UserinfoModel userinfo = usersubinfo.getUserinfoModel();
					if (null != userinfo) {

						outMap.put("UserID", ShunJianMeiUtil
								.intToString(usersubinfo.getUserid()));
						outMap.put("Name", userinfo.getNickname());
						// add by jiazhaohui
						// 添加美发师国籍和语言
						outMap.put("Nationality", usersubinfo.getNationality());
						outMap.put("Language", usersubinfo.getLanguage());
						outMap.put("Flagpng", usersubinfo.getFlagpng());

						UserPhotoMappingModel usersubinfoPhoto = userPhotoMappingService
								.searchUserPhoto(userinfo.getUserid(), 1);
						if (null != usersubinfoPhoto
								&& usersubinfoPhoto.getBasePicture() != null) {
							outMap.put("Photo", ipHost
									+ usersubinfoPhoto.getBasePicture()
											.getPicturepath());
						} else {
							outMap.put("Photo", "");
						}

						outMap.put("LevelName", usersubinfo
								.getUserProfessionLevel().getLevelname());
						outMap.put("Star", usersubinfo.getBaseStarinfoModel()
								.getStarname());
						outMap.put("Score", ShunJianMeiUtil
								.floatToString(usersubinfo.getScore()));
						outMap.put("OrderNum", ShunJianMeiUtil
								.intToString(usersubinfo.getOrdernum()));
						outMap.put("WorkingLife", ShunJianMeiUtil
								.intToString(usersubinfo.getWorkinglife()));
						// 是否收藏
						FavoritesModel favorites = favoritesService
								.searchFavorites(UserId, 2, ID);
						if (null != favorites) {
							outMap.put("IsFavorite", "1");
						} else {
							outMap.put("IsFavorite", "0");
						}
						
						//洗剪吹的价格						
						UserProfessionLevelServiceModel userProfessionLevelService = userProfessionLevelServiceService
							.searchUserProfessionLevelService(usersubinfo.getLevelid(), "xjc");
						if(userProfessionLevelService != null){
							outMap.put("Price", ShunJianMeiUtil.intToString(
									userProfessionLevelService.getPrice()));
						}else{
							outMap.put("Price", "0");
						}

						// 发型师代表作品
						// 将输出的结果存入集合中
						List<Map<String, Object>> userImagesList = new ArrayList<Map<String, Object>>();

						List<UserPhotoMappingModel> userImages = userPhotoMappingService
								.searchUserPhotos(ID, 7);
						if (userImages != null && userImages.size() != 0) {
							for (UserPhotoMappingModel obj : userImages) {

								Map<String, Object> userPhotoMap = new HashMap<String, Object>();

								if (null != obj
										&& obj.getBasePicture()!= null) {
									userPhotoMap.put("Photo", ipHost
											+ obj.getBasePicture()
													.getPicturepath());
								} else {
									userPhotoMap.put("Photo", "");
								}
								// 给输出的结果集合中添加数据
								userImagesList.add(userPhotoMap);
							}
						}

						outMap.put("Images", userImagesList);

						// ////////获取美发师的评价
						// 评论
						List<UserEvaluateModel> userEvaluateList = userEvaluateService
								.searchCommentNum(1, ID);
						if (userEvaluateList != null
								&& userEvaluateList.size() > 0) {
							// 评价数
							outMap.put("CommentNum", ShunJianMeiUtil
									.intToString(userEvaluateList.size()));

							// 评价Comment
							UserEvaluateModel commentInfo = userEvaluateList
									.get(0);
							UserinfoModel commentUserinfo = userinfoService
									.searchUserinfoById(commentInfo.getUserid());

							Map<String, Object> userEvaluateMap = new HashMap<String, Object>();

							if (commentUserinfo != null) {

								userEvaluateMap.put("ID", ShunJianMeiUtil
										.intToString(commentUserinfo
												.getUserid()));
								userEvaluateMap.put("Name",
										commentUserinfo.getNickname());

								UserPhotoMappingModel wpmm = userPhotoMappingService
										.searchUserPhoto(
												commentUserinfo.getUserid(), 1);
								if (null != wpmm
										&& null != wpmm.getBasePicture()) {

									userEvaluateMap.put("Photo", ipHost
											+ wpmm.getBasePicture()
													.getPicturepath());
								} else {
									userEvaluateMap.put("Photo", "");
								}

								userEvaluateMap.put("Time", ShunJianMeiUtil
										.dateConvertString(commentInfo
												.getCreatetime()));
								userEvaluateMap.put("Content",
										commentInfo.getContent());
								userEvaluateMap.put("Score", ShunJianMeiUtil
										.floatToString(commentInfo.getScore()));
								// /////////////配图
								List<Map<String, String>> contentPathList = new ArrayList<Map<String, String>>();

								List<String> contentPath = userEvaluatePhotoMappingService
										.searchUserEvaluateicturepathList(commentInfo
												.getEvaid());
								for (String path : contentPath) {
									Map<String, String> pathMap = new HashMap<String, String>();
									pathMap.put("Photo", ipHost + path);
									contentPathList.add(pathMap);
								}
								userEvaluateMap.put("Item", contentPathList);
							}
							outMap.put("Comment", userEvaluateMap);
						} else {
							Map<String, Object> userEvaluateMap = new HashMap<String, Object>();
							outMap.put("Comment", userEvaluateMap);
							outMap.put("CommentNum", "0");
						}
						// ////评论end

						// 简介Intro
						Map<String, Object> introMap = new HashMap<String, Object>();
						// 擅长发型
						List<Object> hairList = new ArrayList<Object>();
						String hairStr[] = usersubinfo.getHairstyle()
								.split(",");
						for (String hair : hairStr) {
							Map<String, Object> hairMap = new HashMap<String, Object>();
							hairMap.put("Name", hair);
							hairList.add(hairMap);
						}
						introMap.put("Hair", hairList);
						// 兴趣爱好
						List<Object> hobbiesList = new ArrayList<Object>();
						String hobbiesStr[] = usersubinfo.getHobbies().split(
								",");
						for (String hobbies : hobbiesStr) {
							Map<String, Object> hobbiesMap = new HashMap<String, Object>();
							hobbiesMap.put("Name", hobbies);
							hobbiesList.add(hobbiesMap);
						}
						introMap.put("Hobbies", hobbiesList);
						// 个人简介
						introMap.put("Content", usersubinfo.getIntro());
						outMap.put("Intro", introMap);
						// 简介end

						// 我的发型HairStyle
						List<Map<String, String>> hairStyleList = new ArrayList<Map<String, String>>();

						List<UserMyhairstyleModel> userMyhairstyleList = userMyhairstyleService
								.searchUserMyhairstyleUserId(ID);
						if (null != userMyhairstyleList
								&& userMyhairstyleList.size() > 0) {
							for (UserMyhairstyleModel userMyhairstyle : userMyhairstyleList) {
								Map<String, String> hairStyleMap = new HashMap<String, String>();

								hairStyleMap.put("ID", ShunJianMeiUtil
										.intToString(userMyhairstyle
												.getMystyleid()));
								hairStyleMap.put("Name",
										userMyhairstyle.getName());

								UserMyhairstylePhotoMappingModel umomm = userMyhairstylePhotoMappingService
										.searchUserMyhairstylePhotoMapping(
												userMyhairstyle.getMystyleid(),
												1);
								if (umomm != null
										&& umomm.getBasePicture() != null) {
									hairStyleMap.put("Photo", ipHost
											+ umomm.getBasePicture()
													.getPicturepath());
								} else {
									hairStyleMap.put("Photo", "");
								}

								hairStyleMap.put("Price", ShunJianMeiUtil
										.floatToString(userMyhairstyle
												.getPrice()));

								hairStyleMap.put("UsedNum", ShunJianMeiUtil
										.intToString(userMyhairstyle
												.getUsednum()));

								hairStyleList.add(hairStyleMap);
							}

						}
						outMap.put("HairStyle", hairStyleList);
						outMap.put("istype", usersubinfo.getIstype());
						//查询美发师当前是否有套餐 0：自营；1：非自营
						if(usersubinfo.getIstype() == 1){
							JSONArray json =  this.userHairpackedService.searchUserHairpackedByHairdresserid(""+usersubinfo.getUserid()).getJSONArray("data");
							if(json.isEmpty()){//说明没有套餐
								outMap.put("hasPacked", 0);
							}else{
								outMap.put("hasPacked", 1);
							}
						}
						
						// ///////////////////////////////
						outList.add(outMap);
						
						
						
						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
						outJsonMap.put(Constant.DATA, outList);
						outJsonMap.put(Constant.TOTAL, 1);

					} else {
						outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					}

				} else {
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				}

				// ////////////////////////////////

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

			return outJsonMap;
			// 输出流
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("HairdresserSchedule")
	/**
	 * 2.8	发型师日程（定义）
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> HairdresserSchedule(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (Integer.parseInt(interfaceStatus) == 1) {
			List<Map<String, Object>> outJsonList = new ArrayList<Map<String, Object>>();
			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
			Map<String, Object> itemMap1 = new HashMap<String, Object>();
			itemMap1.put("ID", "1");
			itemMap1.put("Name", "木北造型（国贸店）");
			itemMap1.put("Address", "丰台富丰路4号");
			Map<String, Object> itemMap2 = new HashMap<String, Object>();
			itemMap2.put("ID", "2");
			itemMap2.put("Name", "木北造型（劲松店）");
			itemMap2.put("Address", "丰台富丰路5号");
			itemList.add(itemMap1);
			itemList.add(itemMap2);

			List<Map<String, Object>> itemsList = new ArrayList<Map<String, Object>>();
			Map<String, Object> itemsMap1 = new HashMap<String, Object>();
			itemsMap1.put("Hour", "9");
			itemsMap1.put("State", "1");
			itemsMap1.put("Item", itemList);
			Map<String, Object> itemsMap2 = new HashMap<String, Object>();
			itemsMap2.put("Hour", "10");
			itemsMap2.put("State", "0");
			itemsMap2.put("Item", itemList);

			itemsList.add(itemsMap1);
			itemsList.add(itemsMap2);

			Map<String, Object> hairdresser = new HashMap<String, Object>();
			hairdresser.put("Date", "9.13");
			hairdresser.put("WeekDay", "周日");
			hairdresser.put("Item", itemsList);

			outJsonList.add(hairdresser);

			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outJsonList);
			outJsonMap.put(Constant.TOTAL, 1);
			return outJsonMap;
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			List<Map<String, Object>> outJsonList = new ArrayList<Map<String, Object>>();
			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				// 用户ID
				// String UserId = inputJsonMap.get("UserID").toString();
				// 发型师ID
				int ID = Integer.parseInt(inputJsonMap.get("ID").toString());

				// UserWorkdateModel userWorkdateModel = userWorkplaceService
				// .searchUserWorkdate(ID);
	
				
				 outJsonList = userWorkhourService.searchScheduleService(ID,Constant.FUTURE_SEVEN_DAY);
				
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outJsonList);
					outJsonMap.put(Constant.TOTAL, 1);
				
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
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		}
	}

	@RequestMapping("StoreList")
	@SuppressWarnings("unchecked")
	/**
	 * 2.9	店铺列表（定义）
	 * @param request
	 * @param response
	 */
	public void StoreList(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '木北造型(科技园店)',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Address': '丰台富丰路4号',");
			sb.append("'Score': '4',");
			sb.append("'Distance': '4.5km',");
			sb.append("'OrderNum': '542',");
			sb.append("'CarNum': '10'");
			sb.append("},");

			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '木北造型(总部基地店)',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Address': '丰台富丰路18号',");
			sb.append("'Score': '5',");
			sb.append("'Distance': '2.5km',");
			sb.append("'OrderNum': '189',");
			sb.append("'CarNum': '20'");
			sb.append("}");

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
				// 城市ID
				String cityid = inputJsonMap.get("CityID").toString();
				// 经度
				double longitude = Double.parseDouble(inputJsonMap.get(
						"Longitude").toString());
				// 纬度
				double latitude = Double.parseDouble(inputJsonMap.get(
						"Latitude").toString());
				// 返回数据行数
				int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
						.toString());
				// 当前页数 从1开始
				int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
						.toString());
				// 店铺评价星级 默认1从高到低 0从低到高
				String score = inputJsonMap.get("Score").toString();
				// 订单 默认1从多到少 0从少到多
				String orderquantity = inputJsonMap.get("Order").toString();
				// 车位数 默认1从多到少
				String car = inputJsonMap.get("Car").toString();
				
				//排序类型 1按星级2按订单3按距离（距离之后近到远）4按车位数
				String type = inputJsonMap.get("Type").toString();
				
				String Infversion=inputJsonMap.get("Infversion").toString();
				// 获取店铺列表
				List<StoreinfoModel> storeinfoModel = storeinfoService
						.searchStoreinfo(cityid, score, orderquantity,
								longitude, latitude, car, pageSize, pageIndex, type,Infversion);
				
				List<Map<String, Object>> storeinfoList = new ArrayList<Map<String, Object>>();
				
				if (null != storeinfoModel && storeinfoModel.size() > 0) {
					//离我距离添加到list中
					for (StoreinfoModel obj : storeinfoModel) {  
						// 根据经纬度计算距离
						double mylongitude = 0;
						double mylatitude = 0;
						if (null != obj.getLongitude() && obj.getLongitude().length() > 0) {
							mylongitude = Double.parseDouble(obj.getLongitude());
						}
						if (null != obj.getLatitude() && obj.getLatitude().length() > 0) {
							mylatitude = Double.parseDouble(obj.getLatitude());
						}
						String distance = ShunJianMeiUtil
								.LantitudeLongitudeDist(mylongitude,
										mylatitude,
										longitude, latitude);
						
						if(distance.equals("<1")){
							obj.setDistance(Long.parseLong("0"));
						}else{
							obj.setDistance(Long.parseLong(distance));
						}

					}
					//如果排序类型是3的话，重新整理list
					if (type.equals("3")) {
						
						//根据离我距离排序，得到整理后的list
						Collections.sort(storeinfoModel, new ContentComparator());  
					}
					
					for (StoreinfoModel obj : storeinfoModel) {
						Map<String, Object> storeinfoMap = new HashMap<String, Object>();

						storeinfoMap.put("ID",
								ShunJianMeiUtil.intToString(obj.getStoreid()));
						storeinfoMap.put("Name", obj.getName());
						StorePhotoMappingModel storePhotoMappingModel = storePhotoMappingService
								.searchStorePhoto(obj.getStoreid(), 1);

						if (storePhotoMappingModel != null
								&& storePhotoMappingModel.getBasePicture() != null) {
							storeinfoMap.put("Photo", ipHost
									+ storePhotoMappingModel.getBasePicture()
											.getPicturepath());
						} else {
							storeinfoMap.put("Photo", "");
						}

						storeinfoMap.put("Address", obj.getAddress());
						storeinfoMap.put("Score", ShunJianMeiUtil.floatToString(obj.getScore()));
						
						if(obj.getDistance() == 0){
							storeinfoMap.put("Distance", "<1");
						}else{
							storeinfoMap.put("Distance", String.valueOf(obj.getDistance()));
						}

						storeinfoMap.put("OrderNum", ShunJianMeiUtil
								.intToString(obj.getOrderquantity()));
						storeinfoMap
								.put("CarNum", ShunJianMeiUtil.intToString(obj
										.getCarnumber()));
						
						// add by jiazhaohui
						storeinfoMap.put("Quickpay", ShunJianMeiUtil.intToString(obj.getQuickpay()));
						storeinfoList.add(storeinfoMap);

					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, storeinfoList);
					outJsonMap.put(Constant.TOTAL, 1);

				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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

	@RequestMapping("StoreList2")
	@SuppressWarnings("unchecked")
	/**
	 * 2.9	店铺列表（定义）
	 * @param request
	 * @param response
	 */
	public void StoreList2(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '木北造型(科技园店)',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Address': '丰台富丰路4号',");
			sb.append("'Score': '4',");
			sb.append("'Distance': '4.5km',");
			sb.append("'OrderNum': '542',");
			sb.append("'CarNum': '10'");
			sb.append("},");

			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '木北造型(总部基地店)',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Address': '丰台富丰路18号',");
			sb.append("'Score': '5',");
			sb.append("'Distance': '2.5km',");
			sb.append("'OrderNum': '189',");
			sb.append("'CarNum': '20'");
			sb.append("}");

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
				// 城市ID
				String cityid = inputJsonMap.get("CityID").toString();
				// 经度
				double longitude = Double.parseDouble(inputJsonMap.get(
						"Longitude").toString());
				// 纬度
				double latitude = Double.parseDouble(inputJsonMap.get(
						"Latitude").toString());
				// 返回数据行数
				int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
						.toString());
				// 当前页数 从1开始
				int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
						.toString());
				// 店铺评价星级 默认1从高到低 0从低到高
				String score = inputJsonMap.get("Score").toString();
				// 订单 默认1从多到少 0从少到多
				String orderquantity = inputJsonMap.get("Order").toString();
				// 车位数 默认1从多到少
				String car = inputJsonMap.get("Car").toString();
				
				//排序类型 1按星级2按订单3按距离（距离之后近到远）4按车位数
				String type = inputJsonMap.get("Type").toString();
				
				String Infversion=inputJsonMap.get("Infversion").toString();
				
				// 获取店铺列表
				List<StoreinfoModel> storeinfoModel = storeinfoService
						.searchStoreinfo(cityid, score, orderquantity,
								longitude, latitude, car, pageSize, pageIndex, type,Infversion);
				
				List<Map<String, Object>> storeinfoList = new ArrayList<Map<String, Object>>();
				
				if (null != storeinfoModel && storeinfoModel.size() > 0) {
					//离我距离添加到list中
					for (StoreinfoModel obj : storeinfoModel) {  
						// 根据经纬度计算距离
						double mylongitude = 0;
						double mylatitude = 0;
						if (null != obj.getLongitude() && obj.getLongitude().length() > 0) {
							mylongitude = Double.parseDouble(obj.getLongitude());
						}
						if (null != obj.getLatitude() && obj.getLatitude().length() > 0) {
							mylatitude = Double.parseDouble(obj.getLatitude());
						}
						String distance = ShunJianMeiUtil
								.LantitudeLongitudeDist(mylongitude,
										mylatitude,
										longitude, latitude);
						
						if(distance.equals("<1")){
							obj.setDistance(Long.parseLong("0"));
						}else{
							obj.setDistance(Long.parseLong(distance));
						}

					}
					//如果排序类型是3的话，重新整理list
					if (type.equals("3")) {
						
						//根据离我距离排序，得到整理后的list
						Collections.sort(storeinfoModel, new ContentComparator());  
					}
					
					for (StoreinfoModel obj : storeinfoModel) {
						Map<String, Object> storeinfoMap = new HashMap<String, Object>();

						storeinfoMap.put("ID",
								ShunJianMeiUtil.intToString(obj.getStoreid()));
						storeinfoMap.put("Name", obj.getName());
						StorePhotoMappingModel storePhotoMappingModel = storePhotoMappingService
								.searchStorePhoto(obj.getStoreid(), 1);

						if (storePhotoMappingModel != null
								&& storePhotoMappingModel.getBasePicture() != null) {
							storeinfoMap.put("Photo", ipHost
									+ storePhotoMappingModel.getBasePicture()
											.getPicturepath());
						} else {
							storeinfoMap.put("Photo", "");
						}

						storeinfoMap.put("Address", obj.getAddress());
						storeinfoMap.put("Score", ShunJianMeiUtil.floatToString(obj.getScore()));
						
						if(obj.getDistance() == 0){
							storeinfoMap.put("Distance", "<1");
						}else{
							storeinfoMap.put("Distance", String.valueOf(obj.getDistance()));
						}

						storeinfoMap.put("OrderNum", ShunJianMeiUtil
								.intToString(obj.getOrderquantity()));
						storeinfoMap
								.put("CarNum", ShunJianMeiUtil.intToString(obj
										.getCarnumber()));
						
						String istypeString=obj.getIstype()==0?"深度合作":"自由定价";
						storeinfoMap.put("istype", istypeString);
						
						// add by jiazhaohui
						storeinfoMap.put("Quickpay", ShunJianMeiUtil.intToString(obj.getQuickpay()));
						storeinfoList.add(storeinfoMap);

					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, storeinfoList);
					outJsonMap.put(Constant.TOTAL, 1);

				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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
	@RequestMapping("StoreDetail")
	/**
	 * 2.10	店铺详情（定义）
	 * @param request
	 * @param response
	 */
	public void StoreDetail(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [{");
			sb.append("'ID': '1',");
			sb.append("'Name': '木北造型(科技园店)',");
			sb.append("'Address': '丰台富丰路4号',");
			sb.append("'BusinessHours': '9:00 至 21:00',");
			sb.append("'Score': '4',");
			sb.append("'Distance': '4.5Km',");
			sb.append("'OrderNum': '542',");
			sb.append("'CarNum': '10',");
			sb.append("'IsFavorite': '0',");
			sb.append("'Tel': '18688888888',");
			sb.append("'Intro': '这里是简介这里是简介',");
			sb.append("'Images': [");
			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'}");
			sb.append("],");
			sb.append("'CommentNum': '122',");
			sb.append("'Comment': {");
			sb.append("'ID': '1',");
			sb.append("'Name': '小美',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Time': '2015-09-09 12:00:02',");
			sb.append("'Content': '这里是评价评价评价',");
			sb.append("'Score': '5',");
			sb.append("'Item': [");
			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'},");
			sb.append("{'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'}");
			sb.append("]},");
			sb.append("'Schedule': [{");
			sb.append("'Date': '9.13',");
			sb.append("'WeekDay': '周日',");
			sb.append("'Item': [{");
			sb.append("'ID': '1',");
			sb.append("'Name': '阿东',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'LevelName': '发型总监',");
			sb.append("'Star': '双子座',");
			sb.append("'Score': '4',");
			sb.append("'OrderNum': '132'");
			sb.append("}");
			sb.append("]");
			sb.append("}");
			sb.append("]");
			sb.append("}");
			sb.append("],");
			sb.append("'total': 1");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			try {
				// 美发店ID
				String ID = inputJsonMap.get("ID").toString();
				
				// 精度
				String Longitude = inputJsonMap.get("Longitude").toString();
				// 维度
				String Latitude = inputJsonMap.get("Latitude").toString();
				
				// 用户ID
				String UserID = inputJsonMap.get("UserID").toString();
				// 美发店
				StoreinfoModel storeinfo = storeinfoService
						.searchStoreinfo(Integer.parseInt(ID));

				if (null != storeinfo) {
					// 美发店相册
					StorePhotoMappingModel storePhotoMapping1 = storePhotoMappingService
							.searchStorePhoto(storeinfo.getStoreid(), 1);

					List<Map<String, Object>> storePhotoMappingList = new ArrayList<Map<String, Object>>();

					if (storePhotoMapping1 != null
							&& storePhotoMapping1.getBasePicture() != null) {
						Map<String, Object> map = new HashMap<String, Object>();

						map.put("Photo", ipHost
								+ storePhotoMapping1.getBasePicture()
										.getPicturepath());

						storePhotoMappingList.add(map);
					} else {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("Photo", "");
						storePhotoMappingList.add(map);
					}

					StorePhotoMappingModel storePhotoMapping2 = storePhotoMappingService
							.searchStorePhoto(storeinfo.getStoreid(), 2);

					if (storePhotoMapping2 != null
							&& storePhotoMapping2.getBasePicture() != null) {
						Map<String, Object> map = new HashMap<String, Object>();

						map.put("Photo", ipHost
								+ storePhotoMapping2.getBasePicture()
										.getPicturepath());

						storePhotoMappingList.add(map);
					} else {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("Photo", "");
						storePhotoMappingList.add(map);
					}

					// 评论
					List<UserEvaluateModel> userEvaluateList = userEvaluateService
							.searchUserEvaluate(Integer.parseInt(ID), 2);

					Map<String, Object> userEvaluateMap = new HashMap<String, Object>();

					if (null != userEvaluateList && userEvaluateList.size() > 0) {

						//获取第一条店铺评价
						UserEvaluateModel userEvaluateModel = userEvaluateList.get(0);
						
						UserinfoModel userinfo = userinfoService
								.searchUserinfoById(userEvaluateModel.getUserid());

						userEvaluateMap.put("ID", ShunJianMeiUtil
								.intToString(userEvaluateList.get(0)
										.getUserid()));
						userEvaluateMap.put("Name", userinfo.getNickname());
						UserPhotoMappingModel userPhotoMapping = userPhotoMappingService
								.searchUserPhoto(userinfo.getUserid(), 1);
						if (userPhotoMapping != null) {
							userEvaluateMap.put("Photo", ipHost
									+ userPhotoMapping.getBasePicture()
											.getPicturepath());
						} else {
							userEvaluateMap.put("Photo", "");
						}
						userEvaluateMap.put("Time", ShunJianMeiUtil
								.dateConvertString(userEvaluateModel
										.getCreatetime()));
						userEvaluateMap.put("Content", userEvaluateModel
								.getContent());
						userEvaluateMap.put("Score", ShunJianMeiUtil
								.floatToString(userEvaluateModel
										.getScore()));

						List<Map<String, Object>> userEvaluatePhotoMappingList = new ArrayList<Map<String, Object>>();
						
						//根据当前的评价ID，查询对应的配图列表
						List<UserEvaluatePhotoMappingModel> list = userEvaluatePhotoMappingService
								.searchUserEvaluatePhotoMappingg(userEvaluateModel.getEvaid());
						if (list != null && list.size() > 0) {
							for (UserEvaluatePhotoMappingModel obj : list) {
								Map<String, Object> map = new HashMap<String, Object>();
								if (obj != null && obj.getBasePicture() !=null) {
									map.put("Photo", ipHost+ obj.getBasePicture().getPicturepath());
								} else {
									map.put("Photo", "");
								}
								userEvaluatePhotoMappingList.add(map);
							}
						}
						
						// 评论图片
						/*List<Map<String, Object>> userEvaluatePhotoMappingList = new ArrayList<Map<String, Object>>();
						for (UserEvaluateModel obj : userEvaluateList) {
							Map<String, Object> map = new HashMap<String, Object>();
	
							UserEvaluatePhotoMappingModel userEvaluatePhotoMapping = userEvaluatePhotoMappingService
									.searchUserEvaluatePhotoMappingg(obj.getEvaid());
							if (userEvaluatePhotoMapping != null && userEvaluatePhotoMapping.getBasePicture() !=null) {
								map.put("Photo", ipHost
										+ userEvaluatePhotoMapping.getBasePicture()
												.getPicturepath());
							} else {
								map.put("Photo", "");
							}
	
							userEvaluatePhotoMappingList.add(map);
						}*/
						
						userEvaluateMap.put("Item", userEvaluatePhotoMappingList);
					}

					// 发型师工作地点
					List<UserWorkplaceModel> userWorkplacelist = userWorkplaceServices
							.searchUserWorkplaces(storeinfo.getStoreid());

					// 返回的发型师集合
					List<Map<String, Object>> scheduleList = new ArrayList<Map<String, Object>>();

					List<Map<String, Object>> usersubinfoList = new ArrayList<Map<String, Object>>();
					String chktempDate = "";
					int dataCount = 0;
					int listCount = 0;
					Map<String, Object> scheduleMap = new HashMap<String, Object>();
					
					if(userWorkplacelist != null){
						for (UserWorkplaceModel userWorkplace : userWorkplacelist) {
							listCount ++;
							
							// 发型师
							Map<String, Object> usersubinfoMap = new HashMap<String, Object>();
	
							String tempDate = userWorkplace.getUserWorkdate()
									.getYear()
									+ "-"
									+ userWorkplace.getUserWorkdate()
											.getMonth()
									+ "-"
									+ userWorkplace.getUserWorkdate().getDay();

							if (!tempDate.equals(chktempDate)) {
								
								if (dataCount > 7) {
									break;
								}
								
								chktempDate = tempDate;
								dataCount++;
								
								
								if (usersubinfoList.size() > 0) {
									scheduleMap.put("Item", usersubinfoList);
									scheduleList.add(scheduleMap);
								}
								scheduleMap = new HashMap<String, Object>();
								usersubinfoList = new ArrayList<Map<String, Object>>();
								
							}
							
							//美发师信息
							UsersubinfoModel usersubinfo = usersubinfoService
									.searchUsersubinfo(userWorkplace
											.getUserWorkdate().getUserid());
							if (usersubinfo != null) {
								usersubinfoMap.put("ID", ShunJianMeiUtil
										.intToString(usersubinfo.getUserid()));
								UserinfoModel userinfo = userinfoService.searchUserinfoById(usersubinfo.getUserid());
								usersubinfoMap.put("Name", userinfo.getNickname());
								//usersubinfoMap.put("Name", usersubinfo.getTruename());
								UserPhotoMappingModel userPhotoMapping = userPhotoMappingService
										.searchUserPhoto(
												usersubinfo.getUserid(), 1);
								if (userPhotoMapping != null && userPhotoMapping.getBasePicture() != null) {
									usersubinfoMap.put("Photo", ipHost
											+ userPhotoMapping.getBasePicture()
													.getPicturepath());
								} else {
									usersubinfoMap.put("Photo", "");
								}
								usersubinfoMap.put("LevelName", usersubinfo
										.getUserProfessionLevel()
										.getLevelname());
								usersubinfoMap.put("Star", usersubinfo
										.getBaseStarinfoModel().getStarname());
								usersubinfoMap.put("Score", ShunJianMeiUtil
										.floatToString(usersubinfo.getScore()));
								usersubinfoMap
										.put("OrderNum", ShunJianMeiUtil
												.intToString(usersubinfo
														.getOrdernum()));
							} else {
								usersubinfoMap.put("ID", "");
								usersubinfoMap.put("Name", "");
								usersubinfoMap.put("Photo", "");
								usersubinfoMap.put("LevelName", "");
								usersubinfoMap.put("Star", "");
								usersubinfoMap.put("Score", "");
								usersubinfoMap.put("OrderNum", "");
							}
							//添加工作日
							scheduleMap.put("Date", userWorkplace
									.getUserWorkdate().getMonth()
									+ "."
									+ String.format("%02d",userWorkplace.getUserWorkdate().getDay()));
							
							scheduleMap.put("WeekDay",
									ShunJianMeiUtil.dayForWeek(tempDate));
							
							usersubinfoList.add(usersubinfoMap);
							
							if(listCount == userWorkplacelist.size()){
								
								scheduleMap.put("Item", usersubinfoList);
								scheduleList.add(scheduleMap);
							}
						}
					}

					// 美发店Map
					Map<String, Object> storeinfoMap = new HashMap<String, Object>();
					storeinfoMap
							.put("ID", ShunJianMeiUtil.intToString(storeinfo
									.getStoreid()));
					storeinfoMap.put("Name", storeinfo.getName());
					storeinfoMap.put("Address", storeinfo.getAddress());
					storeinfoMap.put("BusinessHours",
							storeinfo.getBusinesshours());
					storeinfoMap
							.put("Score", ShunJianMeiUtil
									.floatToString(storeinfo.getScore()));

					storeinfoMap.put("Longitude", storeinfo.getLongitude());
					storeinfoMap.put("Latitude", storeinfo.getLatitude());
					storeinfoMap.put("istype",(storeinfo.getIstype()==null || storeinfo.getIstype() == 0?0:1));
					if ("".equals(Longitude) || "".equals(Latitude)
							||"0".equals(Longitude) || "0".equals(Latitude)) {
						storeinfoMap.put("Distance", "-1");
					}else {
						
						if (null == storeinfo.getLongitude() ||
								null ==  storeinfo.getLatitude()||
								"".equals(storeinfo.getLongitude()) || "".equals(storeinfo.getLatitude())) {
							storeinfoMap.put("Distance", "-1");
						}else {
							
							String distance = ShunJianMeiUtil.LantitudeLongitudeDist(Double.parseDouble(Longitude), 
									Double.parseDouble(Latitude),
									Double.parseDouble(storeinfo.getLongitude()),
									Double.parseDouble(storeinfo.getLatitude()));
							
							storeinfoMap.put("Distance", distance);
						}
						
					}
					
					
					

					storeinfoMap.put("OrderNum", ShunJianMeiUtil
							.intToString(storeinfo.getOrderquantity()));
					storeinfoMap.put("CarNum", ShunJianMeiUtil
							.intToString(storeinfo.getCarnumber()));
					storeinfoMap.put("Tel", storeinfo.getTel());
					storeinfoMap.put("Intro", storeinfo.getIntro());
					// add by jiazhaohui
					// 闪惠支付优惠的介绍
					int qpay = storeinfo.getQuickpay();
					storeinfoMap.put("Quickpay", qpay);
					BaseConfigModel baseConfig = baseConfigService.
							searchBaseConfigByCode("coupon_pay_cus_desc");
					if (baseConfig != null)
					{
						storeinfoMap.put("Perferiential", baseConfig.getConfigvalue());
					}
					// 收藏
					FavoritesModel favorites = favoritesService
							.searchFavorites(Integer.parseInt(UserID), 3,
									Integer.parseInt(ID));
					if (null != favorites)
						storeinfoMap.put("IsFavorite", "1");
					else
						storeinfoMap.put("IsFavorite", "0");
					storeinfoMap.put("Images", storePhotoMappingList);
					storeinfoMap.put("CommentNum", ShunJianMeiUtil
							.intToString(userEvaluateList.size()));
					storeinfoMap.put("Comment", userEvaluateMap);
					// List<Map<String, Object>> scheduleList = new
					// ArrayList<Map<String, Object>>();
					// scheduleList.add(scheduleMap);
					storeinfoMap.put("Schedule", scheduleList);

					outList.add(storeinfoMap);

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 保存请求记录
					utilService.addBaseRequestlog(request.getRequestURI(),
							request.getAttribute("inputJsonInfo")
									.toString(), "",
							ShunJianMeiUtil.outputString(outJsonMap));
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
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
	@RequestMapping("ReservationService")
	/**
	 * 2.12	预约服务（定义）
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> ReservationService(
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> reservationMap = new HashMap<String, Object>();
			reservationMap.put("ID", "1");
			reservationMap.put("Name", "阿东");
			reservationMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			reservationMap.put("LevelName", "发型总监");
			reservationMap.put("Star", "双子座");
			reservationMap.put("Score", "4");
			reservationMap.put("OrderNum", "132");

			List<Map<String, Object>> hairList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairMap1 = new HashMap<String, Object>();
			hairMap1.put("Code", "xjc");
			hairMap1.put("Price", "15");
			hairMap1.put("Times", "20");
			Map<String, Object> hairMap2 = new HashMap<String, Object>();
			hairMap2.put("Code", "xc");
			hairMap2.put("Price", "15");
			hairMap2.put("Times", "20");
			Map<String, Object> hairMap3 = new HashMap<String, Object>();
			hairMap3.put("Code", "tf");
			hairMap3.put("Price", "15");
			hairMap3.put("Times", "20");
			Map<String, Object> hairMap4 = new HashMap<String, Object>();
			hairMap4.put("Code", "rf");
			hairMap4.put("Price", "15");
			hairMap4.put("Times", "20");
			Map<String, Object> hairMap5 = new HashMap<String, Object>();
			hairMap5.put("Code", "hl");
			hairMap5.put("Price", "15");
			hairMap5.put("Times", "20");
			hairList.add(hairMap1);
			hairList.add(hairMap2);
			hairList.add(hairMap3);
			hairList.add(hairMap4);
			hairList.add(hairMap5);

			List<Map<String, Object>> hairStyleList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairStylenMap1 = new HashMap<String, Object>();
			hairStylenMap1.put("Name", "女士长发");
			hairStylenMap1.put("Times", "15");
			hairStylenMap1.put("Item", hairList);

			Map<String, Object> hairStylenMap2 = new HashMap<String, Object>();
			hairStylenMap2.put("Name", "女士中发");
			hairStylenMap2.put("Times", "25");
			hairStylenMap2.put("Item", hairList);

			hairStyleList.add(hairStylenMap1);
			hairStyleList.add(hairStylenMap2);

			reservationMap.put("HairStyle", hairStyleList);

			List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
			Map<String, Object> itemMap1 = new HashMap<String, Object>();
			itemMap1.put("WHID", "1");
			itemMap1.put("Hour", "9");
			itemMap1.put("State", "1");
			Map<String, Object> itemMap2 = new HashMap<String, Object>();
			itemMap2.put("WHID", "2");
			itemMap2.put("Hour", "10");
			itemMap2.put("State", "0");
			itemList.add(itemMap1);
			itemList.add(itemMap2);

			Map<String, Object> scheduleStylenMap = new HashMap<String, Object>();
			scheduleStylenMap.put("Date", "9.13");
			scheduleStylenMap.put("WeekDay", "周日");
			scheduleStylenMap.put("Item", itemList);

			reservationMap.put("Schedule", scheduleStylenMap);

			outList.add(reservationMap);

			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, 1);

			// 输出流
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		} else {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			try {

				// 工作日的集合
				Map<String, Object> subinfoMap = new HashMap<String, Object>();

				// 用户ID
				// String UserId = inputJsonMap.get("UserID").toString();
				// 发型师ID
				int ID = Integer.parseInt(inputJsonMap.get("ID").toString());
				// 发型ID
				int hairStyleID = Integer.parseInt(inputJsonMap.get(
						"HairStyleID").toString());

				// 获取美发师扩展信息的列表
				UsersubinfoModel usersubinfoModel = reservationService
						.searchUsersubinfo(ID);
				UserinfoModel userinfo = userinfoService.searchUserinfoById(ID);
				subinfoMap.put("ID", ShunJianMeiUtil.intToString(ID));
				// subinfoMap.put("Name", usersubinfoModel.getTruename());
				subinfoMap.put("Name", userinfo.getNickname());
				// 用户相册的卡片
				UserPhotoMappingModel userPhotoMappingModel = reservationService
						.searchUserPhoto(ID, 1);
				if (userPhotoMappingModel != null) {
					subinfoMap.put("Photo", ipHost
							+ userPhotoMappingModel.getBasePicture()
									.getPicturepath());
				} else {
					subinfoMap.put("Photo", "");
				}
				subinfoMap.put("LevelName", usersubinfoModel
						.getUserProfessionLevel().getLevelname());
				subinfoMap.put("Star", usersubinfoModel.getBaseStarinfoModel()
						.getStarname());
				subinfoMap.put("Score", ShunJianMeiUtil
						.floatToString(usersubinfoModel.getScore()));
				subinfoMap.put("OrderNum", ShunJianMeiUtil
						.intToString(usersubinfoModel.getOrdernum()));

				// 发型项目 start
				// 发型分类的集合
				List<UserHairstyleModel> userHairstyleModels = reservationService
						.searchUserHairstyle();
				List<Map<String, Object>> userHairstyleList = new ArrayList<Map<String, Object>>();
				//把当前的发型放到List的第一项位置
				if (hairStyleID != 0) {
					
					UserHairstyleModel userHairstyle = null;
					
					for (UserHairstyleModel obj : userHairstyleModels) {
						
						if (hairStyleID == obj.getStyleid()) {
							userHairstyle = obj;
							userHairstyleModels.remove(obj);

							userHairstyleModels.add(0, userHairstyle);
							break;
						}
					}
				}
				
				
				for (UserHairstyleModel obj : userHairstyleModels) {
					Map<String, Object> userHairstyleMap = new HashMap<String, Object>();
					userHairstyleMap.put("ID",
							ShunJianMeiUtil.intToString(obj.getStyleid()));
					userHairstyleMap.put("Name", obj.getName());

					// 美发师职称等级服务价格的卡片
					List<UserProfessionLevelServiceModel> userProfessionLevelServiceModels = reservationService
							.searchUserProfessionLevelService(usersubinfoModel
									.getLevelid());
					// 服务编码
//					String servercode = userProfessionLevelServiceModels.get(0)
//							.getServicecode();
					List<Map<String, Object>> userLevelList = new ArrayList<Map<String, Object>>();
					for (UserProfessionLevelServiceModel obj2 : userProfessionLevelServiceModels) {
						Map<String, Object> userLevelMap = new HashMap<String, Object>();
						userLevelMap.put("Code", obj2.getServicecode());
						userLevelMap.put("Price",
								ShunJianMeiUtil.intToString(obj2.getPrice()));
						ServiceattributeModel serviceattribute = serviceattributeService
								.searchServiceattribute(obj2.getServicecode());
						if (serviceattribute != null) {
							userLevelMap.put("Name",
									serviceattribute.getServicename());
						} else {
							userLevelMap.put("Name", "");
						}
						userLevelMap.put("Times",
								ShunJianMeiUtil.intToString(obj2.getTimes()));

						userLevelList.add(userLevelMap);
					}
//					UserMyhairstyleModel userMyhairstyleModel = reservationService
//							.searchUserMyhairstyle(hairStyleID);
					// 默认的服务编码时间
//					List<BaseConfigModel> baseConfigModels = reservationService
//							.searchBaseConfig(servercode);
//					String hairstyleTime;
//					if (userMyhairstyleModel != null) {
//						if (obj.getStyleid() == userMyhairstyleModel
//								.getStyleid()) {
//							hairstyleTime = ShunJianMeiUtil
//									.intToString(userMyhairstyleModel
//											.getTimes());
//						} else {
//
//							hairstyleTime = baseConfigModels.get(0)
//									.getConfigvalue();
//						}
//					} else {
//						hairstyleTime = baseConfigModels.get(0)
//								.getConfigvalue();
//					}

					//userHairstyleMap.put("Times", hairstyleTime);
					userHairstyleMap.put("Times", "0");

					userHairstyleMap.put("Item", userLevelList);
					userHairstyleList.add(userHairstyleMap);
				}
				subinfoMap.put("HairStyle", userHairstyleList);
				// 发型项目 end

				// 日程列表 start

				// 获取美发师工作日的列表
				// UserWorkdateModel userWorkdateModel = reservationService
				// .searchUserWorkdate(ID);

				List<Map<String, Object>> workdateList = new ArrayList<Map<String, Object>>();
	
				workdateList = userWorkhourService.searchScheduleService(ID,Constant.FUTURE_SEVEN_DAY);

				subinfoMap.put("Schedule", workdateList);

				outList.add(subinfoMap);

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 1);

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
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("ReservationStore")
	/**
	 * 2.13	预约商户
	 * @param request
	 * @param response
	 */
	public void ReservationStore(HttpServletRequest request,
			HttpServletResponse response) {
		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '木北造型(科技园店)',");
			sb.append("'Address': '丰台富丰路4号'");
			sb.append("},");

			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '木北造型(总部基地店)',");
			sb.append("'Address': '丰台富丰路18号'");
			sb.append("}");

			sb.append("],");
			sb.append("'total': 2");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			try {
				// 取得请求参数
				Map<String, Object> inputJsonMap = (Map<String, Object>) request
	                    .getAttribute("inputJsonMap");

				// 发型师id
				// String ID = inputJsonMap.get("ID").toString();
				// 工时id
				int whid = Integer
						.parseInt(inputJsonMap.get("WHID").toString());
				// 时长(分钟)
				int times = Integer.parseInt(inputJsonMap.get("Times")
						.toString());

				// 获取可预约商户信息
				List<StoreinfoModel> storeinfoList = storeinfoService
						.searchStoreinfoList(whid, times);

				if (null == storeinfoList || storeinfoList.size() == 0) {

					// 保存请求记录
					utilService.addBaseRequestlog(request.getRequestURI(),
							request.getAttribute("inputJsonInfo")
									.toString(), "",
							ShunJianMeiUtil.outputString(outJsonMap));
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);

					// return;
				} else {

					List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

					for (StoreinfoModel o : storeinfoList) {
						Map<String, String> storeinfoMap = new HashMap<String, String>();
						storeinfoMap.put("ID",
								ShunJianMeiUtil.intToString(o.getStoreid()));
						storeinfoMap.put("Name", o.getName());
						storeinfoMap.put("Address", o.getAddress());
						dataList.add(storeinfoMap);
					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, dataList);
					outJsonMap.put(Constant.TOTAL, 1);

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
	@RequestMapping("ReservationMaterial")
	/**
	 * 2.14	预约物料（定义）
	 * @param request
	 * @param response
	 */
	public void ReservationMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");

			sb.append("'Code': 'tf',");
			sb.append("'Item': [");
			sb.append("{'ID': '1','Name': '沙宣','Price': '100'},");
			sb.append("{'ID': '2','Name': '欧珀莱','Price': '200'}");
			sb.append("]");
			sb.append("},");
			sb.append("{");
			sb.append("'Code': 'rf',");
			sb.append("'Item': [");
			sb.append("{'ID': '3','Name': '沙宣','Price': '100'},");
			sb.append("{'ID': '4','Name': '欧珀莱','Price': '200'}");
			sb.append("]");
			sb.append("},");
			sb.append("{");
			sb.append("'Code': 'hl',");
			sb.append("'Item': [");
			sb.append("{'ID': '5','Name': '沙宣','Price': '100'},");
			sb.append("{'ID': '6','Name': '欧珀莱','Price': '200'}");
			sb.append("]");

			sb.append("}");

			sb.append("],");
			sb.append("'total': 3");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {
			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			try {
				// 取得请求参数
				Map<String, Object> inputJsonMap = (Map<String, Object>) request
	                    .getAttribute("inputJsonMap");
				// 发型项目ID
				int styleid = Integer.parseInt(inputJsonMap.get("ID")
						.toString());
				// 查询所有服务属性
				List<ServiceattributeModel> serviceattributeList = serviceattributeService
						.searchServiceattribute();

				List<Map<String, Object>> materialList = new ArrayList<Map<String, Object>>();

				for (ServiceattributeModel obj : serviceattributeList) {
					// 发型项目物料关系
					List<UserHairstyleMaterialModel> userHairstyleMaterialList = userHairstyleMaterialService
							.searchUserHairstyleMaterial(styleid,
									obj.getServicecode());

					if (null != userHairstyleMaterialList
							&& userHairstyleMaterialList.size() > 0) {

						String materialidStr = "";
						for (int i = 0; i < userHairstyleMaterialList.size(); i++) {
							materialidStr += userHairstyleMaterialList.get(i)
									.getMaterialid() + ",";
						}

						Map<String, Object> materiaMap = new HashMap<String, Object>();
						materiaMap.put("Code", obj.getServicecode());

						List<MaterialinfoModel> materialinfoList = materialinfoService
								.searchMaterialinfo(materialidStr);
						List<Map<String, Object>> materialinfosList = new ArrayList<Map<String, Object>>();
						for (MaterialinfoModel object : materialinfoList) {
							Map<String, Object> materialinfoMap = new HashMap<String, Object>();
							materialinfoMap.put("ID", ShunJianMeiUtil
									.intToString(object.getMaterialid()));
							materialinfoMap.put("Name",
									object.getMaterialname());
							float price = userHairstyleMaterialService
									.searchUserHairstyleMaterial(styleid,
											obj.getServicecode(),
											object.getMaterialid()).getPrice();
							materialinfoMap.put("Price",
									ShunJianMeiUtil.floatToString(price));

							materialinfosList.add(materialinfoMap);
						}
						materiaMap.put("Item", materialinfosList);

						materialList.add(materiaMap);
					}

				}

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, materialList);
				outJsonMap.put(Constant.TOTAL, 1);

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
	@RequestMapping("CommitOrder")
	/**
	 * 2.16	提交订单
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> CommitOrder(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> orderinfoMap = new HashMap<String, Object>();
			orderinfoMap.put("OrderID", "1");
			orderinfoMap.put("Total", "300");
			orderinfoMap.put("Discount", "100");
			orderinfoMap.put("Amount", "200");
			orderinfoMap.put("Wallet", "1");

			List<Map<String, Object>> CouponList = new ArrayList<Map<String, Object>>();
			Map<String, Object> CouponMap1 = new HashMap<String, Object>();
			CouponMap1.put("ID", "1");
			CouponMap1.put("Name", "全场通用100元");
			CouponMap1.put("Amount", "100");
			CouponMap1.put("StartTime", "2015-9-1");
			CouponMap1.put("EndTime", "2015-9-30");
			CouponMap1.put("Selected", "1");

			Map<String, Object> CouponMap2 = new HashMap<String, Object>();
			CouponMap2.put("ID", "2");
			CouponMap2.put("Name", "全场通用50元");
			CouponMap2.put("Amount", "100");
			CouponMap2.put("StartTime", "2015-9-1");
			CouponMap2.put("EndTime", "2015-9-30");
			CouponMap2.put("Selected", "0");

			CouponList.add(CouponMap1);
			CouponList.add(CouponMap2);

			orderinfoMap.put("Coupon", CouponList);

			List<Map<String, Object>> hairList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairMap1 = new HashMap<String, Object>();
			hairMap1.put("Code", "xjc");
			hairMap1.put("Amount", "100");
			Map<String, Object> hairMap2 = new HashMap<String, Object>();
			hairMap2.put("Code", "rf");
			hairMap2.put("Amount", "200");
			hairList.add(hairMap1);
			hairList.add(hairMap2);

			orderinfoMap.put("Item", hairList);

			outList.add(orderinfoMap);

			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, 1);

			// 输出流
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		} else {

			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			try {
								
				outJsonMap = orderinfoService.addCommitOrder(inputJsonMap, request, response);
				
				
				/*outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 1);*/

				/*Date createtime = new Date();
				// 获取参数
				String userid = inputJsonMap.get("UserID").toString();
				String ID = inputJsonMap.get("ID").toString();
				String HairStyleID = inputJsonMap.get("HairStyleID").toString();
				String StyleID = inputJsonMap.get("StyleID").toString();
				double Times = Double.parseDouble(inputJsonMap.get("Times").toString());
				String WHID = inputJsonMap.get("WHID").toString();
				String StoreID = inputJsonMap.get("StoreID").toString();
				String CouponID = inputJsonMap.get("CouponID").toString();
				String Total = inputJsonMap.get("Total").toString();
				String Amount = inputJsonMap.get("Amount").toString();

				// 优惠券变动，优惠券金额 CouponID
				String CouponIDArray[] = CouponID.split("\\|");
				//根据优惠券ID集合查询，多个优惠券金额相加
				int totalAmount = 0;
				if (CouponIDArray.length > 0) {
					for (int i = 0; i < CouponIDArray.length; i++) {
						CouponsModel couponsModel = couponsServie.searchCouponByID(CouponIDArray[i]);
						if (couponsModel != null) {
							totalAmount += couponsModel.getAmount();
						}
					}
				}
				//优惠券金额+支付金额
				int tempAmount = totalAmount + (int)Float.parseFloat(Amount);
				//总金额
				int tempTotal = (int)Float.parseFloat(Total);
				if(tempAmount != tempTotal){
					//入参实际支付支付金额，和计算后的金额有错误
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_2);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				}else{
					
					// 解析嵌套json格式的参数
					String inputJsonInfo = request
							.getAttribute("inputJsonInfo").toString();
					String Items = inputJsonInfo.substring(
							inputJsonInfo.lastIndexOf("[") + 1,
							inputJsonInfo.lastIndexOf("]"));
	
					List<Map<String, String>> itemList = null;
					if (null != Items && Items.length() > 0) {
						itemList = ShunJianMeiUtil.jsonToHashMap(Items);
					} else {
						itemList = new ArrayList<Map<String, String>>();
					}
	
					// 判断工时是否被占用
					// 预约时间
					UserWorkhourModel uwh = userWorkhourService.searchUserWorkhour(Integer.parseInt(WHID));
					int hours = ShunJianMeiUtil.getHour(Times);
					for (int i = 0; i < hours; i++) {
						UserWorkhourModel checkUwh = userWorkhourService.searchUserWorkhour(0, uwh.getWdid(), uwh.getHour()+i);
						
						//9点以后为最后一个时间段不需要判断工时是否被占用。
						if((uwh.getHour() + i) <= 21){
							if (null == checkUwh || checkUwh.getState() != 0) {
								// 工时已经被占用
								outJsonMap.put(Constant.CODE, Constant.CODE_2100);
								outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "美发师工时被占用");
								return outJsonMap;
							}
						}
					
					}
					
	
					int stateCount = 0;
					// 判断工位是否可用
					UserWorkdateModel userWorkdateModel = userWorkdateService.searchUserWorkdate(uwh.getWdid());	
					StoreWorkdateModel storeWorkdateModel = storeWorkDateService.searchStoreWorkdate(Integer.valueOf(StoreID), userWorkdateModel.getYear(), userWorkdateModel.getMonth(), userWorkdateModel.getDay());
					if(storeWorkdateModel == null){
						outJsonMap.put(Constant.CODE, Constant.CODE_2100);
						outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "美发店暂时没有座位");
						return outJsonMap;
					}
					for (int i = 0; i < hours; i++) {
						List<StoreWorkhourModel> storeWorkhourModelList = storeWorkHourService.searchStoreHourInfo(storeWorkdateModel.getWdid(), uwh.getHour()+i);
						//9点以后为最后一个时间段不需要判断工时是否被占用。
						if((uwh.getHour() + i) <= 21){
							if (null == storeWorkhourModelList || storeWorkhourModelList.size() == 0) {
								// 工位已经被占用
								outJsonMap.put(Constant.CODE, Constant.CODE_2100);
								outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "座位被占用");
								return outJsonMap;
							}
						}
						if (storeWorkhourModelList.size() > 0) {
							stateCount ++;
						}
					}
					if (stateCount != hours) {
						// 工时已经被占用
						outJsonMap.put(Constant.CODE, Constant.CODE_2100);
						outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "座位被占用");
						return outJsonMap;
					}
					
					//新增订单
					OrderinfoModel orderinfo = new OrderinfoModel();
					// 订单编号
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyyMMddHHmmssS");
					String ordercode = format.format(createtime);
					if (ordercode.length() < 17) {
						ordercode = ordercode + "1";
					}
					orderinfo.setOrdercode(ordercode);
					// 顾客ID
					orderinfo.setCustomerid(Integer.parseInt(userid));
					// 美发师ID
					orderinfo.setUserid(Integer.parseInt(ID));
					// 店铺ID
					orderinfo.setStoreid(Integer.parseInt(StoreID));
					// 发型项目ID
					orderinfo.setStyleid(Integer.parseInt(StyleID));
					// 发型ID
					orderinfo.setHairstyleid(Integer.parseInt(HairStyleID));
					orderinfo.setParentid(0);
					// 支付类型
					orderinfo.setPaytype(0);
					// 订单状态
					orderinfo.setPaystate(1);
					// 总金额
					orderinfo.setTotal(Double.parseDouble(Total));
					// 加单支付状态
					orderinfo.setAdditionalstate(0);
	
					//优惠券总金额
					orderinfo.setCouponamount(totalAmount);
	
					// 实际支付金额
					orderinfo.setAmount(Double.parseDouble(Amount));
					// 创建时间
					orderinfo.setCreatetime(createtime);
					// 预约时间
					//orderinfo.setServicebegintime(createtime);
					// 查询商户信息
					StoreinfoModel storeinfoModel = storeinfoService
							.searchStoreinfo(Integer.parseInt(StoreID));
	
					if (null != storeinfoModel) {
						// 商家地址
						orderinfo.setStoreinfo_address(storeinfoModel.getAddress());
						orderinfo.setStoreinfo_name(storeinfoModel.getName());
						orderinfo.setStoreinfo_tel(storeinfoModel.getTel());
					}
	
					//我的发型名称
					UserMyhairstyleModel userMyhairstyleModel = userMyhairstyleService
							.searchUserMyhairstyle(Integer.parseInt(StyleID));
					if (userMyhairstyleModel != null) {
						orderinfo.setUser_hairstyle_name(userMyhairstyleModel
								.getName());
					}
	
					// 查询美发师信息
					UsersubinfoModel usersubinfoModel = usersubinfoService
							.searchUsersubinfo(Integer.parseInt(ID));
					if (usersubinfoModel != null) {
						orderinfo.setUser_profession_level_name(usersubinfoModel
								.getUserProfessionLevel().getLevelname());
						orderinfo.setUsersubinfo_name(usersubinfoModel
								.getUserinfoModel().getNickname());
						orderinfo.setUsersubinfo_contactmobile(usersubinfoModel
								.getUserinfoModel().getBindphone());
					}
					
					if (null != uwh) {
						UserWorkdateModel uwd = uwh.getUserWorkdate();
						orderinfo.setAppointmenttime(ShunJianMeiUtil.StrToDate(uwd.getYear(), uwd.getMonth(), uwd.getDay(),uwh.getHour()));
					}else {
						orderinfo.setAppointmenttime(createtime);
					}
					
					// 查询发型项目
					UserHairstyleModel userHairstyleModel = userHairstyleService
							.searchUserHairstyleById(Integer.parseInt(StyleID));
					if (userHairstyleModel != null) {
						orderinfo.setStylename(userHairstyleModel.getName());
					}
	
					// 设置订单详情
					Set<OrderdetailModel> orderdetails = new HashSet<OrderdetailModel>();
					for (Map<String, String> itemMap : itemList) {
						OrderdetailModel o = new OrderdetailModel();
						// 服务属性编码
						o.setServicecode(itemMap.get("Code"));
						double price = Double.parseDouble(itemMap.get("Price").toString());
						// 时长
						o.setTimes(Integer.parseInt(itemMap.get("Times").toString()));
						// 物料ID
						String materialId = itemMap.get("MaterialID").toString();
						o.setMaterialid(Integer.parseInt(materialId));
						o.setServiceprice(price);
						if(!materialId.equals("0")){
							// 物料名称
							MaterialinfoModel materialinfo = materialinfoService
									.searchMaterialNameById(materialId);
							o.setMaterialname(materialinfo.getMaterialname());
							// 物料价格 test************************
							UserHairstyleMaterialModel userHairstyleMaterialModel = userHairstyleMaterialService
									.searchUserHairstyleMaterial(
											Integer.parseInt(StyleID),
											itemMap.get("Code"),
											Integer.parseInt(materialId));
							o.setMaterialidprice(userHairstyleMaterialModel.getPrice());
						}
						// 创建时间
						o.setCreatetime(createtime);
	
						orderdetails.add(o);
					}
	
					// 优惠券变动，设置我的优惠券信息
					List<UserCouponsModel> userCouponsList = new ArrayList<UserCouponsModel>();
	
					if (CouponIDArray.length > 0) {
						for (int i = 0; i < CouponIDArray.length; i++) {
							UserCouponsModel userCouponsModel = userCouponsService.searchCoupons(Integer.parseInt(CouponIDArray[i]), Integer.parseInt(userid));
							//CouponsModel couponsModel = couponsServie.searchCouponByID(CouponIDArray[i]);
							if (couponsModel != null) {
								UserCouponsModel userCoupons = new UserCouponsModel();
								userCoupons.setUserid(Integer.parseInt(userid));
								if (couponsModel != null) {
									userCoupons.setCouponid(couponsModel.getCouponid());
								} else {
									userCoupons.setCouponid(0);
								}
								userCoupons.setId(Integer.parseInt(CouponIDArray[i]));
								userCoupons.setCreatetime(createtime);
								userCouponsList.add(userCoupons);
							}
							userCouponsList.add(userCouponsModel);
						}
					}
	
					// 执行添加
					int OrderID = orderinfoService.addOrderinfo(orderinfo,
							orderdetails, userCouponsList);
	
					//更新工时状态
					if (OrderID > 0) {
						for (int i = 0; i < hours; i++) {
							userWorkhourService.updateUserWorkhour(OrderID, uwh.getWdid(), uwh.getHour()+i, 1);
						}
					}
					
					//更座位时状态
					StoreWorkdateModel storeWorkdate = storeWorkDateService.searchStoreWorkdate(Integer.parseInt(StoreID), 
							uwh.getUserWorkdate().getYear(), uwh.getUserWorkdate().getMonth(),
							uwh.getUserWorkdate().getDay());
					if (OrderID > 0) {
						for (int i = 0; i < hours; i++) {
							storeWorkHourService.updateStoreWorkhour(OrderID, storeWorkdate.getWdid(), uwh.getHour()+i, 1);
						}
					}
					
					//*//************ 做成返回内容 **********************//*
	
					// 返回的Map
					Map<String, Object> orderinfoMap = new HashMap<String, Object>();
	
					// 1.订单实体
					OrderinfoModel orderinfoModel = orderPayService.searchOrderinfo(OrderID);
					if (orderinfoModel != null) {
						orderinfoMap.put("OrderID",
								ShunJianMeiUtil.intToString(OrderID));
						orderinfoMap.put("Total", ShunJianMeiUtil
								.floatToString(orderinfoModel.getTotal()));
						orderinfoMap.put("Discount", ShunJianMeiUtil
								.floatToString(orderinfoModel.getCouponamount()));
						orderinfoMap.put("Amount", ShunJianMeiUtil
								.floatToString(orderinfoModel.getAmount()));
					} else {
						// 无数据
						outJsonMap.put(Constant.CODE, Constant.CODE_2100);
						return outJsonMap;
					}
	
					// 3.订单详细
					List<Map<String, Object>> outItemList = new ArrayList<Map<String, Object>>();
					List<OrderdetailModel> orderdetailModels = orderPayService
							.searchOrderdetail(OrderID);
					for (int i = 0; i < orderdetailModels.size(); i++) {
						OrderdetailModel orderdetailModel = orderdetailModels
								.get(i);
						Map<String, Object> orderdetailMap = new HashMap<String, Object>();
						
						orderdetailMap.put("Code",
								orderdetailModel.getServicecode());
						
						ServiceattributeModel sbm = serviceattributeService.searchServiceattribute(orderdetailModel.getServicecode());
						orderdetailMap.put("Name", sbm != null?sbm.getServicename():"");
						
						orderdetailMap.put("Amount", ShunJianMeiUtil
								.floatToString(orderdetailModel.getServiceprice()));
	
						//优惠券变动追加
						//根据订单编号和服务编号查询优惠券
						UserCouponsModel couponsModel = orderPayService.searchCoupons(String.valueOf(OrderID), orderdetailModel.getServicecode());
						if (null != couponsModel && null != couponsModel.getCoupons()) {
							orderdetailMap.put("Coupon", ShunJianMeiUtil.intToString(couponsModel.getCoupons().getAmount()));
						}else{
							orderdetailMap.put("Coupon", "0");
						}
						
						outItemList.add(orderdetailMap);
					}
					orderinfoMap.put("Item", outItemList);
	
					// 获取我的钱包
					UserWalletModel userWalletModel = orderPayService
							.searchUserWallet(Integer.parseInt(userid),1);//用户钱包
					if (userWalletModel != null) {
						float balance = userWalletModel.getBalance();
						if (balance >= orderinfoModel.getAmount()) {
							orderinfoMap.put("Wallet", "1");
						} else {
							orderinfoMap.put("Wallet", "0");
						}
					} else {
						orderinfoMap.put("Wallet", "0");
					}
	
					outList.add(orderinfoMap);
	
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
					

					//////////////---------推送消息-begin--------//////////////////////
					List<String> lsUserList = new ArrayList<String>();
					String jsonString = "";
					String content = "有用户预约啦，快去看看吧~";
					
					// 获取美发师的id
					lsUserList.add(String.valueOf(orderinfo.getUserid()));
					
					Map<String,Object> jsonMap = new HashMap<String,Object>();
					jsonMap.put("type", 1);
					jsonMap.put("dealid", Integer.parseInt(orderinfoMap.get("OrderID").toString()));
					
					jsonString = ShunJianMeiUtil.outputString(jsonMap);
					
					
					
					MessagePushUtil.SendPush(lsUserList,jsonString,content,2);
	                //////////////---------推送消息-end--------////////////////////////
				}
				*//******************************************/
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

		}
		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));
		return outJsonMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("CanUseCoupons")
	/**
	 * 2.15	可用优惠券
	 * @param request
	 * @param response
	 */
	public void CanUseCoupons(HttpServletRequest request,
			HttpServletResponse response) {
		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Amount ': '100',");
			sb.append("}");

			sb.append("],");
			sb.append("'total': 1");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {
			List<Map<String, Object>> userCouponsList = new ArrayList<Map<String, Object>>();
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			try {
				// 用户ID
				int userid = Integer.parseInt(inputJsonMap.get("UserID")
						.toString());
				// 服务类型
				String Code = inputJsonMap.get("Code").toString();
				String servicecode[] = Code.split("\\|");
				if (servicecode.length > 0) {
					for (int i = 0; i < servicecode.length; i++) {
						//根据服务类型查询最大的优惠券
						UserCouponsModel userCouponsModel = couponsServie.searchCoupons(userid, servicecode[i]);
						if (userCouponsModel != null) {
							Map<String, Object> userCouponsMap = new HashMap<String, Object>();
							//优惠券ID
							userCouponsMap.put("ID",
									ShunJianMeiUtil.intToString(userCouponsModel.getCoupons().getCouponid()));
							userCouponsMap.put("Amount", ShunJianMeiUtil
									.intToString(userCouponsModel.getCoupons().getAmount()));
							userCouponsList.add(userCouponsMap);
						}
					}
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, userCouponsList);
					outJsonMap.put(Constant.TOTAL, userCouponsList.size());
				}else{
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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
	@RequestMapping("UpdateOrderCoupon")
	/**
	 * 2.17	更新订单优惠券（定义）
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> UpdateOrderCoupon(
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> orderinfoMap = new HashMap<String, Object>();
			orderinfoMap.put("OrderID", "1");
			orderinfoMap.put("Total", "300");
			orderinfoMap.put("Discount", "100");
			orderinfoMap.put("Amount", "200");
			orderinfoMap.put("Wallet", "1");

			List<Map<String, Object>> CouponList = new ArrayList<Map<String, Object>>();
			Map<String, Object> CouponMap1 = new HashMap<String, Object>();
			CouponMap1.put("ID", "1");
			CouponMap1.put("Name", "全场通用100元");
			CouponMap1.put("Amount", "100");
			CouponMap1.put("StartTime", "2015-9-1");
			CouponMap1.put("EndTime", "2015-9-30");
			CouponMap1.put("Selected", "1");

			Map<String, Object> CouponMap2 = new HashMap<String, Object>();
			CouponMap2.put("ID", "2");
			CouponMap2.put("Name", "全场通用50元");
			CouponMap2.put("Amount", "100");
			CouponMap2.put("StartTime", "2015-9-1");
			CouponMap2.put("EndTime", "2015-9-30");
			CouponMap2.put("Selected", "0");

			CouponList.add(CouponMap1);
			CouponList.add(CouponMap2);

			orderinfoMap.put("Coupon", CouponList);

			List<Map<String, Object>> hairList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairMap1 = new HashMap<String, Object>();
			hairMap1.put("Code", "xjc");
			hairMap1.put("Amount", "100");
			Map<String, Object> hairMap2 = new HashMap<String, Object>();
			hairMap2.put("Code", "rf");
			hairMap2.put("Amount", "200");
			hairList.add(hairMap1);
			hairList.add(hairMap2);

			orderinfoMap.put("Item", hairList);

			outList.add(orderinfoMap);

			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, 1);

			// 输出流
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		} else {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			try {

				// 返回的Map
				Map<String, Object> orderinfoMap = new HashMap<String, Object>();

				// 用户ID
				int UserId = Integer.parseInt(inputJsonMap.get("UserID")
						.toString());
				// 订单ID
				int OrderID = Integer.parseInt(inputJsonMap.get("OrderID")
						.toString());
				// 优惠券ID
				int ID = Integer.parseInt(inputJsonMap.get("ID").toString());

				// 优惠券
				CouponsModel couponaModel = couponsServie
						.searchCouponByID(String.valueOf(ID));

				if (couponaModel != null) {
					OrderinfoModel orderinfo = orderPayService
							.searchOrderinfo(OrderID);
					// 修改订单优惠券金额和需支付金额
					double couponamount = Double.parseDouble(String
							.valueOf(couponaModel.getAmount()));
					double amount = orderinfo.getTotal() - couponamount;
					orderinfoService.updateOrderinfoCouponAmount(OrderID,
							couponamount, amount);
				}

				// 1.订单实体
				OrderinfoModel orderinfoModel = orderPayService
						.searchOrderinfo(OrderID);
				orderinfoMap.put("OrderID",
						ShunJianMeiUtil.intToString(OrderID));
				orderinfoMap.put("Total", ShunJianMeiUtil
						.floatToString(orderinfoModel.getTotal()));
				orderinfoMap.put("Discount", ShunJianMeiUtil
						.floatToString(orderinfoModel.getCouponamount()));
				orderinfoMap.put("Amount", ShunJianMeiUtil
						.floatToString(orderinfoModel.getAmount()));

				// 2.我的优惠券集合
				List<Map<String, Object>> CouponList = new ArrayList<Map<String, Object>>();
				List<UserCouponsModel> userCouponsModels = userCouponsService.searchUserCoupons(orderinfoModel.getCustomerid());
				for (int i = 0; i < userCouponsModels.size(); i++) {
					CouponsModel couponsModel = userCouponsModels.get(i).getCoupons();

					Map<String, Object> CouponMap = new HashMap<String, Object>();
					CouponMap.put("ID", ShunJianMeiUtil
							.intToString(couponsModel.getCouponid()));
					CouponMap.put("Name", couponsModel.getCouponname());
					CouponMap.put("Amount", ShunJianMeiUtil
							.intToString(couponsModel.getAmount()));
					CouponMap.put("StartTime", ShunJianMeiUtil
							.dateToString(couponsModel.getStarttime()));
					CouponMap.put("EndTime", ShunJianMeiUtil
							.dateToString(couponsModel.getEndtime()));

					// 选中当前的优惠券
					if (ID == couponsModel.getCouponid()) {
						CouponMap.put("Selected", "1");
					} else {
						CouponMap.put("Selected", "0");
					}

					CouponList.add(CouponMap);
				}
				orderinfoMap.put("Coupon", CouponList);

				// 3.订单详细
				List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
				List<OrderdetailModel> orderdetailModels = orderPayService
						.searchOrderdetail(OrderID);
				for (int i = 0; i < orderdetailModels.size(); i++) {
					OrderdetailModel orderdetailModel = orderdetailModels
							.get(i);

					Map<String, Object> orderdetailMap = new HashMap<String, Object>();
					orderdetailMap.put("Code",
							orderdetailModel.getServicecode());
					
					ServiceattributeModel sbm = serviceattributeService.searchServiceattribute(orderdetailModel.getServicecode());
					orderdetailMap.put("Name", sbm != null?sbm.getServicename():"");
					
					orderdetailMap.put("Amount", ShunJianMeiUtil
							.floatToString(orderdetailModel.getServiceprice()));

					itemList.add(orderdetailMap);
				}
				orderinfoMap.put("Item", itemList);

				// 获取我的钱包
				UserWalletModel userWalletModel = orderPayService
						.searchUserWallet(UserId,1);//用户钱包
				if (userWalletModel != null) {
					float balance = userWalletModel.getBalance();
					if (balance >= orderinfoModel.getAmount()) {
						orderinfoMap.put("Wallet", "1");
					} else {
						orderinfoMap.put("Wallet", "0");
					}
				} else {
					orderinfoMap.put("Wallet", "0");
				}

				outList.add(orderinfoMap);

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 1);

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
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("OrderList")
	/**
	 * 2.18我的订单列表
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> OrderList(
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, String>> outList = new ArrayList<Map<String, String>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, String> orderinfoMap = new HashMap<String, String>();
			orderinfoMap.put("OrderID", "10");
			orderinfoMap.put("OrderCode", "2015092410301224365");
			orderinfoMap.put("Name", "阿东");
			orderinfoMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			orderinfoMap.put("LevelName", "发型总监");
			orderinfoMap.put("Mobile", "18512345678");
			orderinfoMap.put("AppointmentTime", "2015-9-25");
			orderinfoMap.put("State", "3");
			orderinfoMap.put("StateName", "已预约");
			orderinfoMap.put("StyleName", "女士长发");
			orderinfoMap.put("StoreName", "木北造型(国贸店)");
			orderinfoMap.put("StoreTel", "01056243153");
			orderinfoMap.put("Address", "长安街88号8门8单元801");
			orderinfoMap.put("Amount", "265");

			outList.add(orderinfoMap);

			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, 1);

			// 输出流
			return outJsonMap;
		} else {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			try {

				// 用户ID
				String userID = inputJsonMap.get("UserID").toString();
				// 状态 0全部 1已预约
				String state = inputJsonMap.get("State").toString();
				// 排序 1由近到远 2由远到近
				String sort = inputJsonMap.get("Sort").toString();
				// 返回数据行数
				int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
						.toString());
				// 当前页数 从1开始
				int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
						.toString());

				outList = orderinfoService.searchOrderList(userID, state, sort,
						pageIndex, pageSize);

				if (null != outList) {

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					// outJsonMap.put(Constant.MSG, Constant.MSG_0);
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
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("UpdateOrderState")
	/**
	 * 2.20更新订单状态
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> UpdateOrderState(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			String orderid = inputJsonMap.get("OrderID").toString();
			String paystate = inputJsonMap.get("State").toString();
			
			// 判断是否修改成功
			if (orderinfoService.updateOrderinfoModel(
					Integer.parseInt(orderid), Integer.parseInt(paystate))) {

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, "");
				outJsonMap.put(Constant.TOTAL, 1);

                //////////////---------推送消息-begin--------////////////////////////
//				List<String> lsUserList = new ArrayList<String>();
//				String jsonString = "";
//				String content = "有用户预约啦，快去看看吧~";
//				
//				// 获取美发师的id
//				lsUserList.add(String.valueOf(orderinfo.getUserid()));
//				
//				Map<String,Object> jsonMap = new HashMap<String,Object>();
//				jsonMap.put("type", 1);
//				jsonMap.put("dealid", Integer.parseInt(orderinfoMap.get("OrderID").toString()));
//				
//				jsonString = ShunJianMeiUtil.outputString(jsonMap);
//				
//				
//				
//				MessagePushUtil.SendPush(lsUserList,jsonString,content,2);
                //////////////---------推送消息-end--------////////////////////////

			} else {
				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + "操作失败");
				outJsonMap.put(Constant.DATA, "");
				outJsonMap.put(Constant.TOTAL, 1);
			}

		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}

		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));

		return outJsonMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("SaveEvaluation")
	/**
	 * 2.21	评价订单
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> SaveEvaluation(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");

		try {

			if (null == inputJsonMap.get("Type")) {

				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + "缺少参数：Type");
				outJsonMap.put(Constant.DATA, "");
				outJsonMap.put(Constant.TOTAL, 1);
				return outJsonMap;
			}

			// 获得参数
			String userID = inputJsonMap.get("UserID").toString();
			String orderID = inputJsonMap.get("OrderID").toString();
			String type = inputJsonMap.get("Type").toString();
			String content = inputJsonMap.get("Content").toString();
			// String item = inputJsonMap.get("Item").toString();
			String image = inputJsonMap.get("Image").toString();
			Object itemObj = inputJsonMap.get("Item");
			List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemObj;
			
			
			//保存评价
			int evaid = userEvaluateService.addUserEvaluate(userID, orderID, type,
					content, itemList, image);
			
			if (evaid > 0) {
				List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
				Map<String, Object> resultMap = new HashMap<String, Object>();
				
				resultMap.put("EvalID", ShunJianMeiUtil.intToString(evaid));
				resultList.add(resultMap);
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1_1+"评论成功");
				outJsonMap.put(Constant.DATA, resultList);
				outJsonMap.put(Constant.TOTAL, 1);
			} else {
				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + "操作失败");
				outJsonMap.put(Constant.DATA, "");
				outJsonMap.put(Constant.TOTAL, 1);
			}

		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}

		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));
		return outJsonMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("OrderDetail")
	/**
	 * 2.19	我的订单详情
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> OrderDetail(
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> orderinfoMap = new HashMap<String, Object>();
			orderinfoMap.put("OrderID", "10");
			orderinfoMap.put("OrderCode", "2015092410301224365");
			orderinfoMap.put("State", "3");
			orderinfoMap.put("StateName", "已预约");
			orderinfoMap.put("NextStep", "等待美发师签到");
			orderinfoMap.put("AppointmentTime", "2015-9-25");
			orderinfoMap.put("Name", "阿东");
			orderinfoMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			orderinfoMap.put("LevelName", "发型总监");
			orderinfoMap.put("Mobile", "18512345678");
			orderinfoMap.put("Score", "4");
			orderinfoMap.put("OrderNum", "132");
			orderinfoMap.put("StoreName", "木北造型(国贸店)");
			orderinfoMap.put("StorePhoto",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			orderinfoMap.put("StoreTel", "01056243153");
			orderinfoMap.put("StoreScore", "4");
			orderinfoMap.put("StoreOrderNum", "124");
			orderinfoMap.put("CarNum", "10");
			orderinfoMap.put("Address", "长安街88号8门8单元801");
			orderinfoMap.put("StyleName", "女士长发");

			List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();
			Map<String, String> itemMap1 = new HashMap<String, String>();
			itemMap1.put("Name", "洗剪吹");
			Map<String, String> itemMap2 = new HashMap<String, String>();
			itemMap2.put("Name", "焗油(欧莱雅)");
			itemList.add(itemMap1);
			itemList.add(itemMap2);
			orderinfoMap.put("Item", itemList);

			orderinfoMap.put("AdditionalContent", "欧莱雅护理");
			orderinfoMap.put("AdditionalAmount", "180");
			orderinfoMap.put("Amount", "265");
			orderinfoMap.put("Discount", "0");

			// /////////////////////////
			Map<String, Object> commentMap = new HashMap<String, Object>();
			commentMap.put("ID", "1");
			commentMap.put("Name", "小美");
			commentMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			commentMap.put("Time", "2015-09-09 12:00:02");
			commentMap.put("Content", "这里对美发师的评价评价评价");
			commentMap.put("Score", "5");

			List<Map<String, String>> commentItemList = new ArrayList<Map<String, String>>();
			Map<String, String> commentItemMap1 = new HashMap<String, String>();
			commentItemMap1.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			Map<String, String> commentItemMap2 = new HashMap<String, String>();
			commentItemMap2.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			commentItemList.add(commentItemMap1);
			commentItemList.add(commentItemMap2);
			commentMap.put("Item", commentItemList);
			orderinfoMap.put("Comment", commentMap);

			// ////////////////////
			Map<String, Object> storeCommentMap = new HashMap<String, Object>();
			storeCommentMap.put("ID", "2");
			storeCommentMap.put("Name", "小美");
			storeCommentMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			storeCommentMap.put("Time", "2015-09-09 12:00:02");
			storeCommentMap.put("Content", "这里对商家的评价评价评价");
			storeCommentMap.put("Score", "5");
			List<Map<String, String>> storeCommentItemList = new ArrayList<Map<String, String>>();
			Map<String, String> storecommentItemMap1 = new HashMap<String, String>();
			storecommentItemMap1.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			Map<String, String> storecommentItemMap2 = new HashMap<String, String>();
			storecommentItemMap2.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			storeCommentItemList.add(storecommentItemMap1);
			storeCommentItemList.add(storecommentItemMap2);

			storeCommentMap.put("Item", storeCommentItemList);
			orderinfoMap.put("StoreComment", storeCommentMap);
			// ///////////////////////////
			outList.add(orderinfoMap);
			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, 1);

		} else {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");
			try {
				// 获得参数
				String userID = inputJsonMap.get("UserID").toString();
				String orderID = inputJsonMap.get("OrderID").toString();

				outList = orderinfoService.searchOrderDetail(userID, orderID);

				if (null != outList && outList.size() > 0) {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, "");
					outJsonMap.put(Constant.DATA, "");
					outJsonMap.put(Constant.TOTAL, 1);
				}

			} catch (BaseException e) {
				// 保存异常信息
				int exceptionId = utilService.addBaseException(
						request.getRequestURI(), request
								.getAttribute("inputJsonInfo").toString(),
						e.getMessage());
				// 设置错误id
				outJsonMap.put(Constant.CODE, Constant.CODE_1200);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
			}

		}
		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));
		return outJsonMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("ReservationServiceAgain")
	/**
	 * 2.22	预约服务-再来一单（定义）
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> ReservationServiceAgain(
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> reservationMap = new HashMap<String, Object>();
			reservationMap.put("ID", "1");
			reservationMap.put("Name", "阿东");
			reservationMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			reservationMap.put("LevelName", "发型总监");
			reservationMap.put("Star", "双子座");
			reservationMap.put("Score", "4");
			reservationMap.put("OrderNum", "132");

			List<Map<String, Object>> hairList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairMap1 = new HashMap<String, Object>();
			hairMap1.put("Code", "xjc");
			hairMap1.put("Price", "15");
			hairMap1.put("Times", "20");
			hairMap1.put("Selected", "1");
			Map<String, Object> hairMap2 = new HashMap<String, Object>();
			hairMap2.put("Code", "xc");
			hairMap2.put("Price", "15");
			hairMap2.put("Times", "20");
			hairMap2.put("Selected", "0");
			Map<String, Object> hairMap3 = new HashMap<String, Object>();
			hairMap3.put("Code", "tf");
			hairMap3.put("Price", "15");
			hairMap3.put("Times", "20");
			hairMap3.put("Selected", "1");
			Map<String, Object> hairMap4 = new HashMap<String, Object>();
			hairMap4.put("Code", "rf");
			hairMap4.put("Price", "15");
			hairMap4.put("Times", "20");
			hairMap4.put("Selected", "0");
			Map<String, Object> hairMap5 = new HashMap<String, Object>();
			hairMap5.put("Code", "hl");
			hairMap5.put("Price", "15");
			hairMap5.put("Times", "20");
			hairMap5.put("Selected", "0");
			hairList.add(hairMap1);
			hairList.add(hairMap2);
			hairList.add(hairMap3);
			hairList.add(hairMap4);
			hairList.add(hairMap5);

			List<Map<String, Object>> hairStyleList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairStylenMap1 = new HashMap<String, Object>();
			hairStylenMap1.put("ID", "1");
			hairStylenMap1.put("Name", "女士长发");
			hairStylenMap1.put("Times", "15");
			hairStylenMap1.put("Selected", "1");
			hairStylenMap1.put("Item", hairList);

			Map<String, Object> hairStylenMap2 = new HashMap<String, Object>();
			hairStylenMap2.put("ID", "2");
			hairStylenMap2.put("Name", "女士中发");
			hairStylenMap2.put("Times", "25");
			hairStylenMap2.put("Selected", "0");
			hairStylenMap2.put("Item", hairList);

			hairStyleList.add(hairStylenMap1);
			hairStyleList.add(hairStylenMap2);

			reservationMap.put("HairStyle", hairStyleList);

			List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
			Map<String, Object> itemMap1 = new HashMap<String, Object>();
			itemMap1.put("WHID", "1");
			itemMap1.put("Hour", "9");
			itemMap1.put("State", "1");
			Map<String, Object> itemMap2 = new HashMap<String, Object>();
			itemMap2.put("WHID", "2");
			itemMap2.put("Hour", "10");
			itemMap2.put("State", "0");
			itemList.add(itemMap1);
			itemList.add(itemMap2);

			Map<String, Object> scheduleStylenMap = new HashMap<String, Object>();
			scheduleStylenMap.put("Date", "9.13");
			scheduleStylenMap.put("WeekDay", "周日");
			scheduleStylenMap.put("Item", itemList);

			reservationMap.put("Schedule", scheduleStylenMap);

			outList.add(reservationMap);

			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, 1);

			// 输出流
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		} else {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			try {

				// 工作日的集合
				Map<String, Object> subinfoMap = new HashMap<String, Object>();

				// 用户ID
				// String UserId = inputJsonMap.get("UserID").toString();
				// 订单ID
				int OrderID = Integer.parseInt(inputJsonMap.get("OrderID")
						.toString());

				// 根据订单ID，查询订单实体
				OrderinfoModel orderinfoModel = reservationService
						.searchOrderinfo(OrderID);
				if (orderinfoModel != null) {
					// 美发师ID
					int ID = orderinfoModel.getUserid();
					// 发型ID
					//int hairStyleID = orderinfoModel.getHairstyleid();
					// 发型分类ID
					//int styleid = orderinfoModel.getStyleid();

					// 获取美发师扩展信息的列表
					UsersubinfoModel usersubinfoModel = reservationService
							.searchUsersubinfo(ID);
					UserinfoModel userinfo = userinfoService.searchUserinfoById(ID);
					subinfoMap.put("ID", ShunJianMeiUtil.intToString(ID));
					subinfoMap.put("Name", userinfo.getNickname());
					// 用户相册的卡片
					UserPhotoMappingModel userPhotoMappingModel = reservationService
							.searchUserPhoto(ID, 1);
					if (userPhotoMappingModel != null) {
						subinfoMap.put("Photo", ipHost
								+ userPhotoMappingModel.getBasePicture()
										.getPicturepath());
					} else {
						subinfoMap.put("Photo", "");
					}
					subinfoMap.put("LevelName", usersubinfoModel
							.getUserProfessionLevel().getLevelname());
					subinfoMap.put("Star", usersubinfoModel.getBaseStarinfoModel()
							.getStarname());
					subinfoMap.put("Score", ShunJianMeiUtil
							.floatToString(usersubinfoModel.getScore()));
					subinfoMap.put("OrderNum", ShunJianMeiUtil
							.intToString(usersubinfoModel.getOrdernum()));

					// 发型项目 start
					// 发型分类的集合
					List<UserHairstyleModel> userHairstyleModels = reservationService
							.searchUserHairstyle();
					List<Map<String, Object>> userHairstyleList = new ArrayList<Map<String, Object>>();

					for (UserHairstyleModel obj : userHairstyleModels) {
						Map<String, Object> userHairstyleMap = new HashMap<String, Object>();
						userHairstyleMap.put("ID",
								ShunJianMeiUtil.intToString(obj.getStyleid()));
						userHairstyleMap.put("Name", obj.getName());

						// 美发师职称等级服务价格的卡片
						List<UserProfessionLevelServiceModel> userProfessionLevelServiceModels = reservationService
								.searchUserProfessionLevelService(usersubinfoModel
										.getLevelid());
						// 服务编码
						List<Map<String, Object>> userLevelList = new ArrayList<Map<String, Object>>();
						for (UserProfessionLevelServiceModel obj2 : userProfessionLevelServiceModels) {
							Map<String, Object> userLevelMap = new HashMap<String, Object>();
							userLevelMap.put("Code", obj2.getServicecode());
							userLevelMap.put("Price",
									ShunJianMeiUtil.intToString(obj2.getPrice()));
							ServiceattributeModel serviceattribute = serviceattributeService
									.searchServiceattribute(obj2.getServicecode());
							if (serviceattribute != null) {
								userLevelMap.put("Name",
										serviceattribute.getServicename());
							} else {
								userLevelMap.put("Name", "");
							}
							userLevelMap.put("Times",
									ShunJianMeiUtil.intToString(obj2.getTimes()));

							userLevelList.add(userLevelMap);
						}
						
						userHairstyleMap.put("Times", "0");
						userHairstyleMap.put("Item", userLevelList);
						userHairstyleList.add(userHairstyleMap);
					}
					subinfoMap.put("HairStyle", userHairstyleList);
					// 发型项目 end



					// 日程列表 end
					List<Map<String, Object>> workdateList = new ArrayList<Map<String, Object>>();
	
					workdateList = userWorkhourService.searchScheduleService(ID,Constant.FUTURE_SEVEN_DAY);
					
					subinfoMap.put("Schedule", workdateList);

					outList.add(subinfoMap);

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("ReservationMaterialAgain")
	/**
	 * 2.23	预约物料-再来一单（定义）
	 * @param request
	 * @param response
	 */
	public void ReservationMaterialAgain(HttpServletRequest request,
			HttpServletResponse response) {
		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");

			sb.append("{");

			sb.append("'Code': 'tf',");
			sb.append("'Item': [");
			sb.append("{'ID': '1','Name': '沙宣','Price': '100'},");
			sb.append("{'ID': '2','Name': '欧珀莱','Price': '200'}");
			sb.append("]");
			sb.append("},");
			sb.append("{");
			sb.append("'Code': 'rf',");
			sb.append("'Item': [");
			sb.append("{'ID': '3','Name': '沙宣','Price': '100'},");
			sb.append("{'ID': '4','Name': '欧珀莱','Price': '200'}");
			sb.append("]");
			sb.append("},");
			sb.append("{");
			sb.append("'Code': 'hl',");
			sb.append("'Item': [");
			sb.append("{'ID': '5','Name': '沙宣','Price': '100'},");
			sb.append("{'ID': '6','Name': '欧珀莱','Price': '200'}");
			sb.append("]");

			sb.append("}");

			sb.append("],");
			sb.append("'total': 3");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {
			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			try {
				// 取得请求参数
				Map<String, Object> inputJsonMap = (Map<String, Object>) request
	                    .getAttribute("inputJsonMap");
				// 发型项目ID
				// int styleid = Integer.parseInt(inputJsonMap.get("ID")
				// .toString());

				// 订单ID
				int OrderID = Integer.parseInt(inputJsonMap.get("OrderID")
						.toString());

				// 根据订单ID，查询订单实体
				OrderinfoModel orderinfoModel = reservationService
						.searchOrderinfo(OrderID);
				if (orderinfoModel != null) {
					// 发型分类ID
					int styleid = orderinfoModel.getStyleid();
					// 查询所有服务属性
					List<ServiceattributeModel> serviceattributeList = serviceattributeService
							.searchServiceattribute();

					List<Map<String, Object>> materialList = new ArrayList<Map<String, Object>>();

					for (ServiceattributeModel obj : serviceattributeList) {
						// 发型项目物料关系
						List<UserHairstyleMaterialModel> userHairstyleMaterialList = userHairstyleMaterialService
								.searchUserHairstyleMaterial(styleid,
										obj.getServicecode());

						if (null != userHairstyleMaterialList
								&& userHairstyleMaterialList.size() > 0) {

							String materialidStr = "";
							for (int i = 0; i < userHairstyleMaterialList
									.size(); i++) {
								materialidStr += userHairstyleMaterialList.get(
										i).getMaterialid()
										+ ",";
							}

							Map<String, Object> materiaMap = new HashMap<String, Object>();
							materiaMap.put("Code", obj.getServicecode());

							List<MaterialinfoModel> materialinfoList = materialinfoService
									.searchMaterialinfo(materialidStr);
							List<Map<String, Object>> materialinfosList = new ArrayList<Map<String, Object>>();
							for (MaterialinfoModel object : materialinfoList) {
								Map<String, Object> materialinfoMap = new HashMap<String, Object>();
								materialinfoMap.put("ID", ShunJianMeiUtil
										.intToString(object.getMaterialid()));
								materialinfoMap.put("Name",
										object.getMaterialname());
								float price = userHairstyleMaterialService
										.searchUserHairstyleMaterial(styleid,
												obj.getServicecode(),
												object.getMaterialid())
										.getPrice();
								materialinfoMap.put("Price",
										ShunJianMeiUtil.floatToString(price));

								// 根据订单ID查询订单详情
								List<OrderdetailModel> orderdetailModels = reservationService
										.searchOrderdetail(OrderID);
								
								//选中的物料ID
								int selectFlg = 0;
								
								for (OrderdetailModel obj3 : orderdetailModels) {
									if (obj.getServicecode().equals(obj3.getServicecode()) 
											&& object.getMaterialid() == obj3.getMaterialid()){
										selectFlg = 1;
									}
								}
								//选中当前的物料
								if (selectFlg == 1) {
									materialinfoMap.put("Selected", "1");
								} else {
									materialinfoMap.put("Selected", "0");
								}

								materialinfosList.add(materialinfoMap);
							}
							materiaMap.put("Item", materialinfosList);

							materialList.add(materiaMap);
						}

					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, materialList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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
	@RequestMapping("OrderPay")
	/**
	 * 2.24	支付订单（定义）
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> OrderPay(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> orderinfoMap = new HashMap<String, Object>();
			orderinfoMap.put("OrderID", "1");
			orderinfoMap.put("Total", "300");
			orderinfoMap.put("Discount", "100");
			orderinfoMap.put("Amount", "200");
			orderinfoMap.put("Wallet", "1");

			List<Map<String, Object>> CouponList = new ArrayList<Map<String, Object>>();
			Map<String, Object> CouponMap1 = new HashMap<String, Object>();
			CouponMap1.put("ID", "1");
			CouponMap1.put("Name", "全场通用100元");
			CouponMap1.put("Amount", "100");
			CouponMap1.put("StartTime", "2015-9-1");
			CouponMap1.put("EndTime", "2015-9-30");
			CouponMap1.put("Selected", "1");

			Map<String, Object> CouponMap2 = new HashMap<String, Object>();
			CouponMap2.put("ID", "2");
			CouponMap2.put("Name", "全场通用50元");
			CouponMap2.put("Amount", "100");
			CouponMap2.put("StartTime", "2015-9-1");
			CouponMap2.put("EndTime", "2015-9-30");
			CouponMap2.put("Selected", "0");

			CouponList.add(CouponMap1);
			CouponList.add(CouponMap2);

			orderinfoMap.put("Coupon", CouponList);

			List<Map<String, Object>> hairList = new ArrayList<Map<String, Object>>();
			Map<String, Object> hairMap1 = new HashMap<String, Object>();
			hairMap1.put("Code", "xjc");
			hairMap1.put("Amount", "100");
			Map<String, Object> hairMap2 = new HashMap<String, Object>();
			hairMap2.put("Code", "rf");
			hairMap2.put("Amount", "200");
			hairList.add(hairMap1);
			hairList.add(hairMap2);

			orderinfoMap.put("Item", hairList);

			outList.add(orderinfoMap);

			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, 1);

			// 输出流
			// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
			return outJsonMap;
		} else {

			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			try {

				// 订单ID
				int OrderID = Integer.parseInt(inputJsonMap.get("OrderID")
						.toString());
				// 1.订单实体
				OrderinfoModel orderinfoModel = reservationService
						.searchOrderinfo(OrderID);
				if (orderinfoModel != null) {

					// 返回的Map
					Map<String, Object> orderinfoMap = new HashMap<String, Object>();

					orderinfoMap.put("OrderID",
							ShunJianMeiUtil.intToString(OrderID));
					orderinfoMap.put("Total", ShunJianMeiUtil
							.floatToString(orderinfoModel.getTotal()));
					orderinfoMap.put("Discount", ShunJianMeiUtil
							.floatToString(orderinfoModel.getCouponamount()));
					orderinfoMap.put("Amount", ShunJianMeiUtil
							.floatToString(orderinfoModel.getAmount()));

					/*// 2.我的优惠券集合
					List<Map<String, Object>> CouponList = new ArrayList<Map<String, Object>>();
					// 根据订单查询我的优惠券
					List<UserCouponsModel> userCouponslist = updateOrderCouponService
							.searchUserCoupon(OrderID);
					//我的优惠券ID列表集合
					String couponids = "";
					if (userCouponslist != null && userCouponslist.size() > 0) {
						for (int i = 0; i < userCouponslist.size(); i++) {
							if (i == 0) {
								couponids = String.valueOf(userCouponslist.get(i).getCouponid());
							}else{
								couponids += "," + String.valueOf(userCouponslist.get(i).getCouponid());
							}
						}
					}
					
					//符合条件的优惠券查询出来，1：金额小于支付金额 2：没有过期3：没有删除
					List<CouponsModel> couponsModels = couponsService
							.searchCouponsByOrderPay(orderinfoModel.getAmount());
					if (couponsModels != null && couponsModels.size() > 0) {
						for (int i = 0; i < couponsModels.size(); i++) {
							CouponsModel outCouponsModel = couponsModels.get(i);

							Map<String, Object> CouponMap = new HashMap<String, Object>();
							CouponMap.put("ID", ShunJianMeiUtil
									.intToString(outCouponsModel.getCouponid()));
							CouponMap.put("Name", outCouponsModel.getCouponname());
							CouponMap.put("Amount", ShunJianMeiUtil
									.intToString(outCouponsModel.getAmount()));
							CouponMap.put("StartTime", ShunJianMeiUtil
									.dateToString(outCouponsModel.getStarttime()));
							CouponMap.put("EndTime", ShunJianMeiUtil
									.dateToString(outCouponsModel.getEndtime()));

							// 选中当前的优惠券
							if (couponids.contains(ShunJianMeiUtil
									.intToString(outCouponsModel.getCouponid()))) {
								CouponMap.put("Selected", "1");
							} else {
								CouponMap.put("Selected", "0");
							}
							CouponList.add(CouponMap);
						}
					}
					orderinfoMap.put("Coupon", CouponList);*/
					//优惠券业务变动，注释部分去掉

					// 3.订单详细
					List<Map<String, Object>> outItemList = new ArrayList<Map<String, Object>>();
					List<OrderdetailModel> orderdetailModels = orderPayService
							.searchOrderdetail(OrderID);
					if (outItemList != null) {
						for (int i = 0; i < orderdetailModels.size(); i++) {
							OrderdetailModel orderdetailModel = orderdetailModels
									.get(i);

							Map<String, Object> orderdetailMap = new HashMap<String, Object>();
							orderdetailMap.put("Code",
									orderdetailModel.getServicecode());
							
							ServiceattributeModel sbm = serviceattributeService.searchServiceattribute(orderdetailModel.getServicecode());
							orderdetailMap.put("Name", sbm != null?sbm.getServicename():"");
							
							orderdetailMap.put("Amount", ShunJianMeiUtil
									.floatToString(orderdetailModel.getServiceprice()));

							//优惠券变动追加
							//根据订单编号和服务编号查询优惠券
							UserCouponsModel couponsModel = orderPayService.searchCoupons(String.valueOf(OrderID), orderdetailModel.getServicecode());
							if (null != couponsModel && null != couponsModel.getCoupons()) {
								orderdetailMap.put("Coupon", ShunJianMeiUtil.intToString(couponsModel.getCoupons().getAmount()));
							}else{
								orderdetailMap.put("Coupon", "0");
							}
							
							outItemList.add(orderdetailMap);
						}
					}
					orderinfoMap.put("Item", outItemList);

					// 获取我的钱包
					UserWalletModel userWalletModel = orderPayService
							.searchUserWallet(orderinfoModel.getCustomerid(),1);//用户钱包
					if (userWalletModel != null) {
						float balance = userWalletModel.getBalance();
						if (balance >= orderinfoModel.getAmount()) {
							orderinfoMap.put("Wallet", "1");
						} else {
							orderinfoMap.put("Wallet", "0");
						}
					} else {
						orderinfoMap.put("Wallet", "0");
					}

					outList.add(orderinfoMap);

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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

		}
		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));
		return outJsonMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("EvaluationList")
	/**
	 * 2.26	评价列表（定义）
	 * @param request
	 * @param response
	 */
	public void EvaluationList(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			// String userid = inputJsonMap.get("UserID").toString();
			// 发型师ID
			int hairdresser = Integer.parseInt(inputJsonMap.get("Hairdresser")
					.toString());
			// 发型师ID
			int styleID = Integer.parseInt(inputJsonMap.get("StyleID")
					.toString());
			// 评价ID 首次为0
			int id = Integer.parseInt(inputJsonMap.get("ID").toString());

			// 返回集合
			List<Map<String, Object>> userinfoList = new ArrayList<Map<String, Object>>();

			// 获取用户评价列表

			List<UserEvaluateModel> userEvaluateModels = evaluationListService
					.searchUserEvaluateByItem(hairdresser, id, styleID);
			if (userEvaluateModels != null && userEvaluateModels.size() > 0) {
				for (UserEvaluateModel obj : userEvaluateModels) {

					Map<String, Object> userEvaluateMap = new HashMap<String, Object>();

					// 评价ID
					userEvaluateMap.put("ID",
							ShunJianMeiUtil.intToString(obj.getEvaid()));

					// 用户实体
					UserinfoModel userinfoModel = userinfoService
							.searchUserinfoById(obj.getUserid());
					// 用户名称
					userEvaluateMap.put("Name", userinfoModel.getNickname());
					// 用户头像
					UserPhotoMappingModel userPhoto = userPhotoMappingService
							.searchUserPhoto(obj.getUserid(), 1);
					if (null != userPhoto && null != userPhoto.getBasePicture()) {
						userEvaluateMap.put("Photo", ipHost
								+ userPhoto.getBasePicture().getPicturepath());
					} else {
						userEvaluateMap.put("Photo", "");
					}
					// 评分
					userEvaluateMap.put("Score",
							ShunJianMeiUtil.intToString((int) obj.getScore()));
					// 评价时间
					userEvaluateMap.put("Time", ShunJianMeiUtil
							.dateConvertString(obj.getCreatetime()));
					// 评价内容
					userEvaluateMap.put("Content", obj.getContent());

					// 评价图表
					List<UserEvaluatePhotoMappingModel> userEvaluatePhotoMappingModels = evaluationListService
							.searchUserEvaluatePhotoMapping(obj.getEvaid());
					List<Map<String, Object>> imageList = new ArrayList<Map<String, Object>>();
					if(userEvaluatePhotoMappingModels != null){
						for (UserEvaluatePhotoMappingModel obj2 : userEvaluatePhotoMappingModels) {
							Map<String, Object> imageMap = new HashMap<String, Object>();
							if (null != obj2 && null != obj2.getBasePicture()) {
								imageMap.put("Image", ipHost
										+ obj2.getBasePicture().getPicturepath());
							} else {
								imageMap.put("Image", "");
							}
							imageList.add(imageMap);
						}
					}

					userEvaluateMap.put("Images", imageList);
					userinfoList.add(userEvaluateMap);

				}
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, userinfoList);
				outJsonMap.put(Constant.TOTAL, 1);
			} else {
				// 无数据
				outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
			}

		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
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

	@SuppressWarnings("unchecked")
	@RequestMapping("StoreEvaluationList")
	/**
	 * 2.27	评价列表（定义）
	 * @param request
	 * @param response
	 */
	public void StoreEvaluationList(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			// String userid = inputJsonMap.get("UserID").toString();
			// 店铺ID
			int storeID = Integer.parseInt(inputJsonMap.get("StoreID")
					.toString());
			// 评价ID 首次为0
			int id = Integer.parseInt(inputJsonMap.get("ID").toString());

			// 返回集合
			List<Map<String, Object>> userinfoList = new ArrayList<Map<String, Object>>();

			// 获取用户评价列表
			List<UserEvaluateModel> userEvaluateModels = evaluationListService
					.searchUserEvaluateByStore(storeID, id);
			if (userEvaluateModels != null && userEvaluateModels.size() > 0) {
				for (UserEvaluateModel obj : userEvaluateModels) {

					Map<String, Object> userEvaluateMap = new HashMap<String, Object>();

					// 评价ID
					userEvaluateMap.put("ID",
							ShunJianMeiUtil.intToString(obj.getEvaid()));

					// 用户实体
					UserinfoModel userinfoModel = userinfoService
							.searchUserinfoById(obj.getUserid());
					// 用户名称
					userEvaluateMap.put("Name", userinfoModel.getNickname());
					// 用户头像
					UserPhotoMappingModel userPhoto = userPhotoMappingService
							.searchUserPhoto(obj.getUserid(), 1);
					if (null != userPhoto && null != userPhoto.getBasePicture()) {
						userEvaluateMap.put("Photo", ipHost
								+ userPhoto.getBasePicture().getPicturepath());
					} else {
						userEvaluateMap.put("Photo", "");
					}
					// 评分
					userEvaluateMap.put("Score",
							ShunJianMeiUtil.intToString((int) obj.getScore()));
					// 评价时间
					userEvaluateMap.put("Time", ShunJianMeiUtil
							.dateConvertString(obj.getCreatetime()));
					// 评价内容
					userEvaluateMap.put("Content", obj.getContent());

					// 评价图表
					List<UserEvaluatePhotoMappingModel> userEvaluatePhotoMappingModels = evaluationListService
							.searchUserEvaluatePhotoMapping(obj.getEvaid());
					List<Map<String, Object>> imageList = new ArrayList<Map<String, Object>>();
					if (null != userEvaluatePhotoMappingModels) {
						for (UserEvaluatePhotoMappingModel obj2 : userEvaluatePhotoMappingModels) {
							Map<String, Object> imageMap = new HashMap<String, Object>();
							if (null != obj2 && null != obj2.getBasePicture()) {
								imageMap.put("Image", ipHost
										+ obj2.getBasePicture().getPicturepath());
							} else {
								imageMap.put("Image", "");
							}
							imageList.add(imageMap);
						}
					}
					

					userEvaluateMap.put("Images", imageList);
					userinfoList.add(userEvaluateMap);

				}
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, userinfoList);
				outJsonMap.put(Constant.TOTAL, 1);
			} else {
				// 无数据
				outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
			}

		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
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

	@SuppressWarnings("unchecked")
	@RequestMapping("WalletPay")
	/**
	 * 2.25	钱包支付（定义）
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> WalletPay(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			int userID = Integer
					.parseInt(inputJsonMap.get("UserID").toString());
			String orderid = inputJsonMap.get("OrderID").toString();
			float amount = Float.parseFloat(inputJsonMap.get("Amount")
					.toString());

			// 查询钱包表
			UserWalletModel userWalletModel = walletPayService
					.searchUserWallet(userID,1);
			if (userWalletModel != null) {
				//钱包金额
				float allAmount = userWalletModel.getBalance();
				//钱包余额大于支付金额
				if (allAmount >= amount) {
					// 剩余金额 = 钱包金额 - 支付金额
					float newAmount = allAmount - amount;
					userWalletModel.setBalance(newAmount);
					userWalletModel.setOwnertype(1);
		
					//判断是否为加单
					if (!"".equals(orderid) && orderid.length() > 10) {//加单
						//根据加单编号查询加单
						OrderinfoModel orderinfoModel = walletPayService.searchOrderinfoByAdditionalcode(orderid);
						// 交易记录表
						UserTradelogModel userTradelogModel = new UserTradelogModel();
						userTradelogModel.setOrderid(orderinfoModel.getOrderid());
						userTradelogModel.setWalletid(userWalletModel.getWalletid());
						userTradelogModel.setAmount(amount);
						userTradelogModel.setRemark("钱包加单支付");
						userTradelogModel.setCreatetime(new Date());
						userTradelogModel.setLogtype(2); // 支付
						// 判断是否修改成功
						if (walletPayService.updateWalletPay(userWalletModel,
								userTradelogModel,orderid)) {
			
							outJsonMap.put(Constant.CODE, Constant.CODE_1000);
							outJsonMap.put(Constant.MSG, Constant.MSG_0);
							outJsonMap.put(Constant.DATA, "");
							outJsonMap.put(Constant.TOTAL, 1);
			
						} else {
							outJsonMap.put(Constant.CODE, Constant.CODE_2000);
							outJsonMap.put(Constant.MSG, Constant.MSG_2);
							outJsonMap.put(Constant.DATA, "");
							outJsonMap.put(Constant.TOTAL, 1);
						}
					}else{
						OrderinfoModel orderinfo = orderinfoService.searchOrderinfo(Integer.parseInt(orderid));
						//不是加单
						UserTradelogModel userTradelogModel = new UserTradelogModel();
						userTradelogModel.setOrderid(Integer.parseInt(orderid));
						userTradelogModel.setWalletid(userWalletModel.getWalletid());
						userTradelogModel.setAmount(amount);
						userTradelogModel.setRemark("顾客支付");
						userTradelogModel.setCreatetime(new Date());
						userTradelogModel.setLogtype(1); // 支付
						// 判断是否修改成功
						if (walletPayService.updateWalletPay(userWalletModel,
								userTradelogModel)) {
							// add by jiazhaohui
							// 预约成功，发送短信
							orderinfoService.SendMobileMessage(orderinfo);
							//////////////---------推送消息-begin--------//////////////////////
							List<String> lsUserList = new ArrayList<String>();
							String contentMsg = "有用户预约啦，快去看看吧~";
							
							// 获取美发师的id
							lsUserList.add(String.valueOf(orderinfo.getUserid()));
							
							Map<String,Object> jsonMap = new HashMap<String,Object>();
							 jsonMap.put("type", 1);
							 jsonMap.put("dealid",Integer.parseInt(orderid));
						    
							try {
								 MessagePushUtil.SendPush(lsUserList,jsonMap,contentMsg,2);
							} catch (Exception e) {
								
							}
							 //////////////---------推送消息-end--------////////////////////////
							outJsonMap.put(Constant.CODE, Constant.CODE_1000);
							outJsonMap.put(Constant.MSG, Constant.MSG_0);
							outJsonMap.put(Constant.DATA, "");
							outJsonMap.put(Constant.TOTAL, 1);
			
						} else {
							outJsonMap.put(Constant.CODE, Constant.CODE_2000);
							outJsonMap.put(Constant.MSG, Constant.MSG_2);
							outJsonMap.put(Constant.DATA, "");
							outJsonMap.put(Constant.TOTAL, 1);
						}
					}
				}else{
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_AMOUNT);
					outJsonMap.put(Constant.DATA, "");
					outJsonMap.put(Constant.TOTAL, 1);
				}
				
			} else {
				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_2);
				outJsonMap.put(Constant.DATA, "");
				outJsonMap.put(Constant.TOTAL, 1);
			}

		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}

		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));

		return outJsonMap;
	}

	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("InitEvaluation")
	/**
	 * 2.25	钱包支付（定义）
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> InitEvaluation(
			HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Object> outList = new ArrayList<Object>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		
		try {
			
			// 获得参数
//			int userID = Integer
//					.parseInt(inputJsonMap.get("UserID").toString());
			int orderid = Integer.parseInt(inputJsonMap.get("OrderID")
					.toString());
			
			OrderinfoModel orderinfo = orderinfoService.searchOrderinfo(orderid);
			if (null != orderinfo) {
				
				Map<String, List<Map<String, String>>> dataMap = new HashMap<String, List<Map<String,String>>>();
				
				Map<String, String> userMap = new HashMap<String, String>();
				List<Map<String, String>> userList = new ArrayList<Map<String,String>>();
				UsersubinfoModel usersubinfo = usersubinfoService.searchUsersubinfo(orderinfo.getUserid());
				
				if (usersubinfo != null) {
					userMap.put("UserID", ShunJianMeiUtil
							.intToString(usersubinfo.getUserid()));
					userMap.put("Name", usersubinfo.getUserinfoModel().getNickname());
					UserPhotoMappingModel userPhotoMapping = userPhotoMappingService
							.searchUserPhoto(
									usersubinfo.getUserid(), 1);
					if (userPhotoMapping != null && userPhotoMapping.getBasePicture() != null) {
						userMap.put("Photo", ipHost
								+ userPhotoMapping.getBasePicture()
										.getPicturepath());
					} else {
						userMap.put("Photo", "");
					}
					userMap.put("LevelName", usersubinfo
							.getUserProfessionLevel()
							.getLevelname());
					userMap.put("Star", usersubinfo
							.getBaseStarinfoModel().getStarname());
					userMap.put("Score", ShunJianMeiUtil
							.floatToString(usersubinfo.getScore()));
					userMap
							.put("OrderNum", ShunJianMeiUtil
									.intToString(usersubinfo
											.getOrdernum()));
					
				} else {
					userMap.put("UserID", "");
					userMap.put("Name", "");
					userMap.put("Photo", "");
					userMap.put("LevelName", "");
					userMap.put("Star", "");
					userMap.put("Score", "");
					userMap.put("OrderNum", "");
				}
				userList.add(userMap);
				dataMap.put("User", userList);
				//////////////////////////////////
				
				Map<String, String> storeMap = new HashMap<String, String>();
				List<Map<String, String>> storeList = new ArrayList<Map<String,String>>();
				StoreinfoModel storeinfo = storeinfoService.searchStoreinfo(orderinfo.getStoreid());
				
				if (null != storeinfo) {
					storeMap.put("ID", ShunJianMeiUtil.intToString(storeinfo
							.getStoreid()));
					storeMap.put("Name", storeinfo.getName());
					
					StorePhotoMappingModel storePhotoMapping1 = storePhotoMappingService
							.searchStorePhoto(storeinfo.getStoreid(), 1);

					if (storePhotoMapping1 != null
							&& storePhotoMapping1.getBasePicture() != null) {

						storeMap.put("Photo", ipHost
								+ storePhotoMapping1.getBasePicture()
										.getPicturepath());

					} else {
						storeMap.put("Photo", "");
					}	
					
					
					storeMap.put("Address", storeinfo.getAddress());
					storeMap.put("Score", ShunJianMeiUtil
							.floatToString(storeinfo.getScore()));
					storeMap.put("OrderNum", ShunJianMeiUtil
							.intToString(storeinfo.getOrderquantity()));
					storeMap
							.put("CarNum", ShunJianMeiUtil.intToString(storeinfo
									.getCarnumber()));
					
				} else {
					storeMap.put("ID","");
					storeMap.put("Name","");
					storeMap.put("Photo", "");
					
					storeMap.put("Address", "");
					storeMap.put("Score", "");
					storeMap.put("OrderNum", "");
					storeMap.put("CarNum", "");
				}
				storeList.add(storeMap);
				dataMap.put("Store", storeList);
				///////////////////
				outList.add(dataMap);
				
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 1);
				
			}else {
				outJsonMap.put(Constant.CODE, Constant.CODE_2100);
			}
			
			
			
		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}
		
		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));
		
		return outJsonMap;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("SaveShareRecord")
	/**
	 * 2.28	保存分享记录（定义）
	 * @param request
	 * @param response
	 */
	public void SaveShareRecord(HttpServletRequest request,
			HttpServletResponse response) {

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			// 用户id
			String userid = inputJsonMap.get("UserID").toString();
			// 评价ID
			String evaid = inputJsonMap.get("EvaID").toString();
			// 图片ID,多图用过|连接
			String picID = inputJsonMap.get("PicID").toString();

			// 判断是否存入
			if (userShareRecordService.addUserShareRecord(userid, evaid, picID)) {
				Map<String, Object> picMap = new HashMap<String, Object>();
				/*if (!"".equals(picID)) {
					BasePictureModel basePictureModel = baseConfigService.searchBasePicture(picID);
					if (basePictureModel != null) {
						picMap.put("URL", ipHost + "goyonghufenxiang?evaid=" + evaid);
					}
				}*/
				//分享不论有没有图片都要返回
				picMap.put("URL", ipHost + "goyonghufenxiang?evaid=" + evaid);
				list.add(picMap);
				// 成功
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1);
				outJsonMap.put(Constant.DATA, list);
				outJsonMap.put(Constant.TOTAL, 1);
			} else {
				// 失败
				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + "操作失败");
				outJsonMap.put(Constant.DATA, "");
				outJsonMap.put(Constant.TOTAL, 1);

			}
		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
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
	
	/**
     * 微信支付下单
     * @param request
     * @return
     * @throws IOException 
     */
    @RequestMapping("/unifiedorder")
    @ResponseBody
    public String unifiedorderMethod(HttpServletRequest request,String orderid,String mount, String notifyUrl)
            throws IOException {
    	try {
    		System.out.println("-------------------微信下单--------------------------------"+orderid);
            Unifiedorder unifiedorder = new Unifiedorder();
            unifiedorder.setAppid(Constant.appid);
            unifiedorder.setMch_id(Constant.mch_id);
            unifiedorder.setNonce_str(UUID.randomUUID().toString().replace("-", ""));

            unifiedorder.setBody("顺间");  			
            //商户订单号 唯一,获取随机数
            unifiedorder.setOut_trade_no(ShunJianMeiUtil.getOut_trade_no()); 	
            //订单总金额，单位为分，详见支付金额
            unifiedorder.setTotal_fee(mount);
            unifiedorder.setSpbill_create_ip(request.getRemoteAddr());//IP
            
            unifiedorder.setNotify_url(ipHost + notifyUrl);//支付回调方法
            unifiedorder.setTrade_type("APP");//JSAPI，NATIVE，APP，WAP
            //订单id或加单id
            unifiedorder.setAttach(orderid);
            
            UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(unifiedorder, Constant.key);
            System.out.println("------------------微信下单UnifiedorderResult-----------------"+unifiedorderResult.toString() +"----"+ipHost + "pay_result");
            MchPayApp mchPayApp = PayUtil.generateMchAppData(unifiedorderResult.getPrepay_id(), 
            		Constant.mch_id, Constant.appid, Constant.key);
            
            String prepayid = mchPayApp.getPrepayid();
            System.out.println("------------------微信下单 微信支付ID--------------------------------"+prepayid);
            return prepayid;
		} catch (Exception e) {
			System.out.println("------------------微信下单异常--------------------------------");
			System.out.println(e.getMessage());
			return null;
		}
    }

	
	@SuppressWarnings("unchecked")
	@RequestMapping("WechatPay")
	/**
	 * 2.30	微信支付（定义）
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> WechatPay(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			//int userID = Integer.parseInt(inputJsonMap.get("UserID").toString());
			String orderid = inputJsonMap.get("OrderID").toString();
			
			String mount = "";
			//判断是否为加单
            if (!"".equals(orderid) && orderid.length() > 10) {//加单
            	//根据加单编号查询加单
				OrderinfoModel orderinfo = walletPayService.searchOrderinfoByAdditionalcode(orderid);
				//转换成【分】
				double centAmount = orderinfo.getAdditionalamount() * 100;
				int additionalamount = (int)centAmount;
				//加单支付金额
				mount = String.valueOf(additionalamount);
            }else{
            	OrderinfoModel orderinfo = orderinfoService.searchOrderinfo(Integer.parseInt(orderid));
            	//转换成【分】
            	double centAmount = orderinfo.getAmount() * 100;
            	int amount = (int)centAmount;
            	//订单支付金额
            	mount = String.valueOf(amount);
            	if (testing != null && testing.equals("true"))
            	{
            		mount = "1";
            	}
            }
			
			//调用微信下单
			String prepay_id = unifiedorderMethod(request, orderid, mount, "pay_result");
			
			if (null != prepay_id) {
				Map<String, Object> picMap = new HashMap<String, Object>();
				
				picMap.put("WechatPayID", prepay_id);
				list.add(picMap);
				// 成功
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, list);
				outJsonMap.put(Constant.TOTAL, 1);
			}else{
				// 成功
				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_2);
				outJsonMap.put(Constant.DATA, list);
				outJsonMap.put(Constant.TOTAL, 1);
			}
		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}

		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));

		return outJsonMap;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("StartQuickPay")
	/**
	 * 4.5	优惠支付进入的接口
	 * @param request
	 * @param response
	 */
	public void StartQuickPay(
			HttpServletRequest request, HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
				//美发店id
				String storeid = inputJsonMap.get("StoreID").toString();
				String userid = inputJsonMap.get("UserID").toString();
				
				Map<String, Object> quickPayinfo = new HashMap<String, Object>();
				//根据美发店id获取美发店的所有美发师的名字
				String masterList=storeinfoService.searchMasterList(storeid);
				quickPayinfo.put("MasterList", masterList);
				//用户优惠金额
				BaseConfigModel coupon_pay_customModel=baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_COUPON_PAY_CUSTOM);
				String coupon_pay_custom=coupon_pay_customModel.getConfigvalue();
				quickPayinfo.put("CouponPayCustom", coupon_pay_custom);
				//优惠策略描述
				BaseConfigModel coupon_pay_cus_descModel=baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_COUPON_PAY_CUS_DESC);
				String coupon_pay_cus_desc=coupon_pay_cus_descModel.getConfigvalue();
				quickPayinfo.put("CouponPayCusDesc", coupon_pay_cus_desc);
				//优惠需要满足的金额
				BaseConfigModel enough_moneyModel=baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_ENOUGH_MONEY);
				String enough_money=enough_moneyModel.getConfigvalue();
				quickPayinfo.put("EnoughMoney", enough_money);
				list.add(quickPayinfo);
				
				// add by jiazhaohui
				// 用户钱包中的钱
				UserWalletModel wallet = userWalletService.searchUserWallet(Integer.valueOf(userid)
						, Constant.WALLET_OWNERTYPE_USER);
				quickPayinfo.put("Wallet", String.valueOf(wallet.getBalance()));
						
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, list);
				outJsonMap.put(Constant.TOTAL, 1);
		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
						request.getRequestURI(),
						request.getAttribute("inputJsonInfo")
										.toString(), e.getMessage());
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping("QuickpayWithWallet")
	/**
	 * 2.25	钱包的闪惠支付
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> QuickpayWithWallet(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			
			// 获得参数
			int customID = Integer
					.parseInt(inputJsonMap.get("UserID").toString());
			String masterName = inputJsonMap.get("MasterName").toString();
			int storeid = Integer
					.parseInt(inputJsonMap.get("StoreID").toString());
			String originalPriceString = inputJsonMap.get("OriginalPrice").toString();
			//根据店铺id查询店铺
			StoreinfoModel storeinfoModel=storeinfoService.searchStoreinfo(storeid);
			
			float originalPrice= Float.valueOf(originalPriceString);
			//计算总共优惠额度
			float coupon_pay_customTotal=userQuickpayService.couponCalculation(originalPrice);
			//实际支付费用
			float payamount;
			payamount=originalPrice-coupon_pay_customTotal;
			//添加闪惠订单
			int paymentid=userQuickpayService.addQuickpayOrder(storeid,customID,payamount,masterName,Constant.PAYTYPE_WALLET,originalPrice);
			
			// 查询用户钱包表
			UserWalletModel userWalletModel = walletPayService
					.searchUserWallet(customID,Constant.WALLET_OWNERTYPE_USER);
			
			//判断钱包钱数是否足够，足够更新钱包钱数,
			if(userQuickpayService.updateUserWalletPay(userWalletModel, payamount,paymentid,masterName,storeid,originalPrice)){
				
			
					String paymentidString=ShunJianMeiUtil.intToString(paymentid);
					// 返回的Map
					UserQuickpayModel uqpModel = userQuickpayService.searchUserQuickpayInfo(paymentid);
					Map<String, Object> quickpayOrderinfoMap = new HashMap<String, Object>();
					quickpayOrderinfoMap.put("OrderID", uqpModel.getOrdercode());
					quickpayOrderinfoMap.put("StoreName", storeinfoModel.getName());
					quickpayOrderinfoMap.put("MasterName", masterName);
					quickpayOrderinfoMap.put("PayAmount", String.valueOf(payamount));
					quickpayOrderinfoMap.put("ReduceAmount", String.valueOf(coupon_pay_customTotal));
					
					outList.add(quickpayOrderinfoMap);
					
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);

						
						

				}else{
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_AMOUNT);
					outJsonMap.put(Constant.DATA, "");
					outJsonMap.put(Constant.TOTAL, 1);
				}

		} catch (Exception e) {
		
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}

		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));

		return outJsonMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("QuickpayWithWechat")
	/**
	 * 2.25	微信的闪惠支付
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> QuickpayWithWechat(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
				// 获得参数
				int customID = Integer
						.parseInt(inputJsonMap.get("UserID").toString());
				int storeid = Integer
						.parseInt(inputJsonMap.get("StoreID").toString());
				String originalPriceString = inputJsonMap.get("OriginalPrice").toString();
				String masterName=inputJsonMap.get("MasterName").toString();
				float originalPrice= Float.valueOf(originalPriceString);
				//计算总共优惠额度
				float coupon_pay_customTotal=userQuickpayService.couponCalculation(originalPrice);
				//实际支付费用
				float payamount;
				payamount=originalPrice-coupon_pay_customTotal;
				
				//添加闪惠订单
				int paymentid=userQuickpayService.addQuickpayOrder(storeid,customID,payamount,masterName,Constant.PAYTYPE_WECHAT,originalPrice);
				
				UserQuickpayModel uqpModel = userQuickpayService.searchUserQuickpayInfo(paymentid);
				String paymentidString = uqpModel.getOrdercode();
				
				//转换成【分】
            	double centAmount = payamount * 100;
            	int amount = (int)centAmount;
            	//订单支付金额
            	String mount = String.valueOf(amount);
            	if (testing != null && testing.equals("true"))
            	{
            		mount = "1";
            	}
            	
            	String prepay_id = unifiedorderMethod(request, paymentidString, mount, "quickPay_result");
    			if (null != prepay_id) {
    				Map<String, Object> picMap = new HashMap<String, Object>();
    				picMap.put("OrderID", uqpModel.getOrdercode());
    				picMap.put("PayAmount",  String.valueOf(payamount));
    				picMap.put("ReduceAmount", String.valueOf(coupon_pay_customTotal));
    				picMap.put("WechatPayID", prepay_id);
    				StoreinfoModel storeinfoModel = storeinfoService.searchStoreinfo(uqpModel.getStoreid());
    				picMap.put("StoreName", storeinfoModel.getName());
    				picMap.put("MasterName", uqpModel.getMastername());
    				
    				list.add(picMap);
    				// 成功
    				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
    				outJsonMap.put(Constant.MSG, Constant.MSG_0);
    				outJsonMap.put(Constant.DATA, list);
    				outJsonMap.put(Constant.TOTAL, 1);
    			}else{
    				// 成功
    				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
    				outJsonMap.put(Constant.MSG, Constant.MSG_2);
    				outJsonMap.put(Constant.DATA, list);
    				outJsonMap.put(Constant.TOTAL, 1);
    			}
			
		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}
		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));
		
		return outJsonMap;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("QuickpayWithAlipay")
	/**
	 * 2.25	支付宝的闪惠支付
	 * @param request
	 * @param response
	 */
	public @ResponseBody Map<String, Object> QuickpayWithAlipay(
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			int customID = Integer
					.parseInt(inputJsonMap.get("UserID").toString());
			//String masterName = inputJsonMap.get("MasterName").toString();
			int storeid = Integer
					.parseInt(inputJsonMap.get("StoreID").toString());
			String originalPriceString = inputJsonMap.get("OriginalPrice").toString();
			String masterName=inputJsonMap.get("MasterName").toString();
			float originalPrice= Float.valueOf(originalPriceString);
			//根据店铺id查询店铺
			StoreinfoModel storeinfoModel=storeinfoService.searchStoreinfo(storeid);
			//计算总共优惠额度
			float coupon_pay_customTotal=userQuickpayService.couponCalculation(originalPrice);
			//实际支付费用
			float payamount;
			payamount=originalPrice-coupon_pay_customTotal;
			
			//添加闪惠订单
			int paymentid=userQuickpayService.addQuickpayOrder(storeid,customID,payamount,masterName,Constant.PAYTYPE_ALIPAY,originalPrice);
			String paymentidString=ShunJianMeiUtil.intToString(paymentid);
			
			// 返回的Map
			Map<String, Object> quickpayOrderinfoMap = new HashMap<String, Object>();
			UserQuickpayModel uqpModel = userQuickpayService.searchUserQuickpayInfo(paymentid);
			quickpayOrderinfoMap.put("OrderID", uqpModel.getOrdercode());
			quickpayOrderinfoMap.put("StoreName", storeinfoModel.getName());
			quickpayOrderinfoMap.put("MasterName", masterName);
			quickpayOrderinfoMap.put("PayAmount", String.valueOf(payamount));
			quickpayOrderinfoMap.put("ReduceAmount", String.valueOf(coupon_pay_customTotal));
			list.add(quickpayOrderinfoMap);
			// 成功
			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, list);
			outJsonMap.put(Constant.TOTAL, 1);

		} catch (Exception e) {
			// 保存异常信息
						int exceptionId = utilService.addBaseException(
								request.getRequestURI(),
								request.getAttribute("inputJsonInfo")
										.toString(), e.getMessage());
						// 设置错误id
						outJsonMap.put(Constant.CODE, Constant.CODE_1200);
						outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}

		// 保存请求记录
				utilService.addBaseRequestlog(request.getRequestURI(), request
						.getAttribute("inputJsonInfo").toString(), "",
						ShunJianMeiUtil.outputString(outJsonMap));
		return outJsonMap;
	}
}
