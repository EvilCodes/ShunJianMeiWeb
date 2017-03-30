package com.wenyuankeji.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.MaterialBrandModel;
import com.wenyuankeji.spring.model.MaterialCategoryModel;
import com.wenyuankeji.spring.model.MaterialDeliveryrecordDetailModel;
import com.wenyuankeji.spring.model.MaterialDeliveryrecordModel;
import com.wenyuankeji.spring.model.MaterialStationModel;
import com.wenyuankeji.spring.model.MaterialinfoModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.ServiceattributeModel;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.model.UserHairpackedModel;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.model.UserMyhairstyleTimesModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IBasePictureService;
import com.wenyuankeji.spring.service.IBaseSuggestionService;
import com.wenyuankeji.spring.service.IFavoritesService;
import com.wenyuankeji.spring.service.IMaterialBrandService;
import com.wenyuankeji.spring.service.IMaterialCategoryService;
import com.wenyuankeji.spring.service.IMaterialDeliveryrecordDetailService;
import com.wenyuankeji.spring.service.IMaterialDeliveryrecordService;
import com.wenyuankeji.spring.service.IMaterialStationService;
import com.wenyuankeji.spring.service.IMaterialinfoService;
import com.wenyuankeji.spring.service.IMyBalanceService;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.service.IServiceattributeService;
import com.wenyuankeji.spring.service.IStorePhotoMappingService;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.service.ITradeRecordService;
import com.wenyuankeji.spring.service.IUserCashrecordService;
import com.wenyuankeji.spring.service.IUserEditWorkService;
import com.wenyuankeji.spring.service.IUserEvaluateService;
import com.wenyuankeji.spring.service.IUserHairpackedService;
import com.wenyuankeji.spring.service.IUserHairstyleService;
import com.wenyuankeji.spring.service.IUserMyhairstylePhotoMappingService;
import com.wenyuankeji.spring.service.IUserMyhairstyleService;
import com.wenyuankeji.spring.service.IUserMyhairstyleTimesService;
import com.wenyuankeji.spring.service.IUserPhotoMappingService;
import com.wenyuankeji.spring.service.IUserProfessionLevelServiceService;
import com.wenyuankeji.spring.service.IUserWalletService;
import com.wenyuankeji.spring.service.IUserWorkplaceService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.service.IWalletPayService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ImgCompressUtil;
import com.wenyuankeji.spring.util.PropertiesTools;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Controller
public class AppMeiFaShiController {

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IBasePictureService basePitureService;

	@Autowired
	private IUserPhotoMappingService userPhotoMappingService;

	@Autowired
	private IUsersubinfoService usersubinfoService;

	@Autowired
	private IUtilService utilService;

	@Autowired
	private IUserMyhairstyleService userMyhairstyleService;

	@Autowired
	private IUserHairstyleService userHairstyleService;

	@Autowired
	private IUserMyhairstyleTimesService userMyhairstyleTimesService;

	@Autowired
	private IServiceattributeService serviceattributeService;

	@Autowired
	private IUserMyhairstylePhotoMappingService userMyhairstylePhotoMappingService;

	@Autowired
	private IStoreinfoService storeinfoService;

	@Autowired
	private IStorePhotoMappingService storePhotoMappingService;

	@Autowired
	private IFavoritesService favoritesService;

	@Autowired
	private IUserEvaluateService userEvaluateService;

	@Autowired
	private IUserWorkplaceService userWorkplaceServices;

	@Autowired
	private IBaseSuggestionService baseSuggestionService;

	@Autowired
	private IMaterialBrandService materialBrandService;

	@Autowired
	private IMaterialCategoryService materialCategoryService;

	@Autowired
	private IMaterialinfoService materialinfoService;

	@Autowired
	private IMaterialStationService materialStationService;

	@Autowired
	private IMaterialDeliveryrecordService materialDeliveryrecordService;

	@Autowired
	private IMaterialDeliveryrecordDetailService materialDeliveryrecordDetailService;

	@Autowired
	private IUserWalletService userWalletService;

	@Autowired
	private IOrderinfoService orderinfoService;

	@Autowired
	private IUserCashrecordService userCashrecordService;

	@Autowired
	private IBaseConfigService baseConfigService;
	@Autowired
	private IUserHairpackedService userHairpackedService;
	// 1.15 我的余额
	@Autowired
	private IMyBalanceService myBalanceService;
	// 1.17 我的消费记录
	@Autowired
	private ITradeRecordService tradeRecordService;
	@Autowired
	private IWalletPayService walletPayService;

	@Autowired
	private IUserProfessionLevelServiceService userProfessionLevelServiceService;

	// add by jiazhaohui
	@Autowired
	private IUserEditWorkService userEditWorkService;

	@Value("#{configProperties['ipHost']}")
	private String ipHost;

	@Value("#{configProperties['interfaceStatus']}")
	private String interfaceStatus;

	@SuppressWarnings("unchecked")
	@RequestMapping("H_UserLogin")
	/**
	 * 1.1登陆
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_UserLogin(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'UserID': '1',");
			sb.append("'NickName': '咔咔',");
			sb.append("'Bindphone': '18701976678',");
			sb.append("'Sex': '1',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
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
				String UserName = inputJsonMap.get("UserName").toString();
				String PassWord = inputJsonMap.get("PassWord").toString();

				List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
				Map<String, Object> userinfoMap = new HashMap<String, Object>();
				UserinfoModel userinfo = null;

				// 查询用户
				userinfo = userinfoService
						.searchUserinfo(UserName, PassWord, 2);

				// 设置返回内容
				if (null != userinfo) {

					// 判断美发师是否审核通过
					UsersubinfoModel usb = usersubinfoService
							.searchUsersubinfo(userinfo.getUserid());

					if (null == usb || usb.getCheckstate() != 2) {
						outJsonMap.put(Constant.CODE, Constant.CODE_2000);
						outJsonMap.put(Constant.MSG, Constant.MSG_1_1
								+ "登陆失败,尚未通过审核");
						outJsonMap.put(Constant.DATA, "");
						outJsonMap.put(Constant.TOTAL, 1);
					} else {
						// 修改用户最后登录信息
						userinfoService.updatelLastlogintimeById(userinfo
								.getUserid());

						userinfoMap.put("UserID", ShunJianMeiUtil
								.intToString(userinfo.getUserid()));
						userinfoMap.put("NickName", userinfo.getNickname());
						userinfoMap.put("Sex",
								ShunJianMeiUtil.intToString(userinfo.getSex()));
						userinfoMap.put("Bindphone", userinfo.getBindphone());
						UserPhotoMappingModel userPhoto = userPhotoMappingService
								.searchUserPhoto(userinfo.getUserid(), 1);
						// 判断是否有图片
						if (null != userPhoto
								&& null != userPhoto.getBasePicture()) {
							userinfoMap.put("Photo", ipHost
									+ userPhoto.getBasePicture()
											.getPicturepath());
						} else {
							userinfoMap.put("Photo", "");
						}
						outList.add(userinfoMap);
						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
						outJsonMap.put(Constant.DATA, outList);
						outJsonMap.put(Constant.TOTAL, 1);
					}

				} else {
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1
							+ "登陆失败,账号或密码错误");
					outJsonMap.put(Constant.DATA, "");
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

			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("H_HomePage")
	/**
	 * 1.2 我的主页
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_HomePage(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'UserID': '1',");
			sb.append("'NickName': '咔咔',");
			sb.append("'Sex': '1',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'BindPhone': '18701976678',");
			sb.append("'CheckState': '2',");
			sb.append("'Balance': '180',");
			sb.append("'OrderNum': '15',");
			sb.append("'EvaluateNum': '26',");
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
				String UserID = inputJsonMap.get("UserID").toString();

				// 根据id查找美发师
				UsersubinfoModel usersubinfoModel = usersubinfoService
						.searchUsersubinfo(Integer.parseInt(UserID));
				// 将输出的结果存入集合中
				List<Map<String, Object>> usersubinfoList = new ArrayList<Map<String, Object>>();
				// 根据用户查询钱包
				UserWalletModel userWallet = userWalletService
						.searchUserWallet(Integer.parseInt(UserID), 2); // 美发师钱包
				// 根据用户查评论数
				/*
				 * List<UserEvaluateModel> userEvaluateModelList =
				 * userEvaluateService
				 * .searchUserEvaluate(Integer.parseInt(UserID));
				 */
				List<UserEvaluateModel> userEvaluateModelList = userEvaluateService
						.searchUserEvaluate(Integer.parseInt(UserID), 1);
				if (null != usersubinfoModel) {
					// 存储美发师的信息
					Map<String, Object> usersubinfoMap = new HashMap<String, Object>();
					usersubinfoMap.put("UserID", ShunJianMeiUtil
							.intToString(usersubinfoModel.getUserid()));
					usersubinfoMap.put("NickName", usersubinfoModel
							.getUserinfoModel().getNickname());
					usersubinfoMap.put("Sex", ShunJianMeiUtil
							.intToString(usersubinfoModel.getUserinfoModel()
									.getSex()));
					UserPhotoMappingModel userPhoto = userPhotoMappingService
							.searchUserPhoto(usersubinfoModel.getUserid(), 1);
					if (null != userPhoto && null != userPhoto.getBasePicture()) {
						usersubinfoMap.put("Photo", ipHost
								+ userPhoto.getBasePicture().getPicturepath());
					} else {
						usersubinfoMap.put("Photo", "");
					}
					usersubinfoMap.put("BindPhone", usersubinfoModel
							.getUserinfoModel().getBindphone());
					usersubinfoMap.put("CheckState", ShunJianMeiUtil
							.intToString(usersubinfoModel.getCheckstate()));
					if (userWallet != null) {
						usersubinfoMap.put("Balance", ShunJianMeiUtil
								.floatToString(userWallet.getBalance()));
					} else {
						usersubinfoMap.put("Balance", "0.0");
					}
					usersubinfoMap.put("OrderNum", ShunJianMeiUtil
							.intToString(usersubinfoModel.getOrdernum()));
					if (userEvaluateModelList != null) {
						usersubinfoMap.put("EvaluateNum", ShunJianMeiUtil
								.intToString(userEvaluateModelList.size()));
					} else {
						usersubinfoMap.put("EvaluateNum", "");
					}
					usersubinfoList.add(usersubinfoMap);

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, usersubinfoList);
					outJsonMap.put(Constant.TOTAL, usersubinfoList.size());
				} else {
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
			// 输出流
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);

		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("H_HairStyleList")
	/**
	 * 2.1 发型列表
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_HairStyleList(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '女士长发',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Price': '300.00',");
			sb.append("'UsedNum': '100',");
			sb.append("'State': '1',");
			sb.append("}");
			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '女士中发',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Price': '300.00',");
			sb.append("'UsedNum': '100',");
			sb.append("'State': '2',");
			sb.append("}");
			sb.append("{");
			sb.append("'ID': '3',");
			sb.append("'Name': '女士短发',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Price': '300.00',");
			sb.append("'UsedNum': '100',");
			sb.append("'State': '3',");
			sb.append("}");
			sb.append("{");
			sb.append("'ID': '4',");
			sb.append("'Name': '男士',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Price': '300.00',");
			sb.append("'UsedNum': '100',");
			sb.append("'State': '4',");
			sb.append("}");
			sb.append("],");
			sb.append("'total': 4");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				// 获取参数
				String UserID = inputJsonMap.get("UserID").toString();
				// 将输出的结果存入集合中
				List<Map<String, Object>> userMyhairstyleList = new ArrayList<Map<String, Object>>();
				// 根据用户id查找我的发型
				List<UserMyhairstyleModel> userMyhairstyleModelList = userMyhairstyleService
						.searchUserMyhairstylesUserId(Integer.parseInt(UserID));

				// 判断我的发型是否为空
				if (null != userMyhairstyleModelList
						&& userMyhairstyleModelList.size() > 0) {

					for (UserMyhairstyleModel obj : userMyhairstyleModelList) {
						UserMyhairstylePhotoMappingModel userMyhairstylePhotoMappingModel = new UserMyhairstylePhotoMappingModel();
						userMyhairstylePhotoMappingModel = userMyhairstylePhotoMappingService
								.searchUserMyhairstylePhotoMapping(
										obj.getMystyleid(), 1);

						// 存储我的发型的信息
						Map<String, Object> userMyhairstyleMap = new HashMap<String, Object>();
						userMyhairstyleMap
								.put("ID", ShunJianMeiUtil.intToString(obj
										.getMystyleid()));
						userMyhairstyleMap.put("Name", obj.getName());

						if (null != userMyhairstylePhotoMappingModel) {
							userMyhairstyleMap.put("Photo", ipHost
									+ userMyhairstylePhotoMappingModel
											.getBasePicture().getPicturepath());
						} else {
							userMyhairstyleMap.put("Photo", "");
						}

						userMyhairstyleMap.put("Price",
								ShunJianMeiUtil.floatToString(obj.getPrice()));
						userMyhairstyleMap
								.put("UsedNum", ShunJianMeiUtil
										.floatToString(obj.getUsednum()));
						userMyhairstyleMap.put("State",
								ShunJianMeiUtil.intToString(obj.getState()));
						userMyhairstyleList.add(userMyhairstyleMap);

					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, userMyhairstyleList);
					outJsonMap.put(Constant.TOTAL, userMyhairstyleList.size());
				} else {
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
	@RequestMapping("H_HairStyleDetail")
	/**
	 * 2.2  我的发型详情
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_HairStyleDetail(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'PicID1': '1',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'PicID2': '1',");
			sb.append("'Image': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'HairStyle': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '女士长发',");
			sb.append("'Checked': '1',");
			sb.append("}");
			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '女士中发',");
			sb.append("'Checked': '0',");
			sb.append("}");
			sb.append("{");
			sb.append("'ID': '3',");
			sb.append("'Name': '女士短发',");
			sb.append("'Checked': '0',");
			sb.append("}");
			sb.append("{");
			sb.append("'ID': '4',");
			sb.append("'Name': '男士',");
			sb.append("'Checked': '0',");
			sb.append("}");
			sb.append("{");
			sb.append("'ID': '5',");
			sb.append("'Name': '儿童',");
			sb.append("'Checked': '0',");
			sb.append("}");
			sb.append("],");
			sb.append("'ServiceTimes': [");
			sb.append("{");
			sb.append("'Code': 'xjc',");
			sb.append("'Name': '洗剪吹',");
			sb.append("'Times': '30',");
			sb.append("}");
			sb.append("{");
			sb.append("'Code': 'xc',");
			sb.append("'Name': '洗剪',");
			sb.append("'Times': '20',");
			sb.append("}");
			sb.append("{");
			sb.append("'Code': 'tf',");
			sb.append("'Name': '烫发',");
			sb.append("'Times': '60',");
			sb.append("}");
			sb.append("{");
			sb.append("'Code': 'rf',");
			sb.append("'Name': '染发',");
			sb.append("'Times': '60',");
			sb.append("}");
			sb.append("{");
			sb.append("'Code': 'hl',");
			sb.append("'Name': '护理',");
			sb.append("'Times': '60',");
			sb.append("}");
			sb.append("],");
			sb.append("'Intro': '发型简介'");

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
				// 发型id
				String mystyleid = inputJsonMap.get("ID").toString();
				String userID = inputJsonMap.get("UserID").toString();

				// 输出的集合
				List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

				// 我的发型
				UserMyhairstyleModel userMyhairstyle = userMyhairstyleService
						.searchUserMyhairstyle(Integer.parseInt(mystyleid));

				// 我的发型集合
				Map<String, Object> userMyhairstyleMap = new HashMap<String, Object>();

				// 判断我的发型是否为空
				if (null != userMyhairstyle) {

					// 发型项目
					List<Map<String, Object>> userHairstyleList = new ArrayList<Map<String, Object>>();
					List<UserHairstyleModel> userHairstyleModelList = userHairstyleService
							.searchUserHairstyleByUserID(userID);
					// 判断发型列表是否为空
					if (null != userHairstyleModelList
							&& userHairstyleModelList.size() > 0) {
						for (UserHairstyleModel obj : userHairstyleModelList) {
							Map<String, Object> userHairstyleMap = new HashMap<String, Object>();
							userHairstyleMap.put("ID", ShunJianMeiUtil
									.intToString(obj.getStyleid()));
							userHairstyleMap.put("Name", obj.getName());
							// Checked
							if (userMyhairstyle.getStyleid() == obj
									.getStyleid()) {
								userHairstyleMap.put("Checked", "1");
							} else {
								userHairstyleMap.put("Checked", "0");
							}
							userHairstyleList.add(userHairstyleMap);
						}
					}

					// 服务时间
					List<UserMyhairstyleTimesModel> userMyhairstyleTimes = userMyhairstyleTimesService
							.searchUserMyhairstyleTimesByMystyleid(userMyhairstyle
									.getMystyleid());

					List<Map<String, Object>> userMyhairstyleTimesList = new ArrayList<Map<String, Object>>();
					// 判断我的发型时间是否为空
					if (null != userMyhairstyleTimes
							&& userMyhairstyleTimes.size() > 0) {
						for (UserMyhairstyleTimesModel obj : userMyhairstyleTimes) {
							Map<String, Object> userMyhairstyleTimesMap = new HashMap<String, Object>();
							ServiceattributeModel serviceattributeModel = serviceattributeService
									.searchServiceattribute(obj
											.getServicecode());

							userMyhairstyleTimesMap.put("Code",
									obj.getServicecode());

							if (serviceattributeModel != null) {
								userMyhairstyleTimesMap.put("Name",
										serviceattributeModel.getServicename());
							} else {
								userMyhairstyleTimesMap.put("Name", "");
							}

							userMyhairstyleTimesMap
									.put("Times", ShunJianMeiUtil
											.intToString(obj.getTimes()));
							userMyhairstyleTimesList
									.add(userMyhairstyleTimesMap);
						}
					}

					// 发型列表图
					UserMyhairstylePhotoMappingModel userMyhairstylePhotoMapping = userMyhairstylePhotoMappingService
							.searchUserMyhairstylePhotoMapping(
									userMyhairstyle.getMystyleid(), 1);
					// 发型相册图
					UserMyhairstylePhotoMappingModel userMyhairstylePhoto = userMyhairstylePhotoMappingService
							.searchUserMyhairstylePhotoMapping(
									userMyhairstyle.getMystyleid(), 2);

					userMyhairstyleMap.put("ID", ShunJianMeiUtil
							.intToString(userMyhairstyle.getMystyleid()));

					// 列表图ID,发型列表
					if (userMyhairstylePhotoMapping != null
							&& userMyhairstylePhotoMapping.getBasePicture() != null) {
						userMyhairstyleMap.put("PicID1", ShunJianMeiUtil
								.intToString(userMyhairstylePhotoMapping
										.getPicid()));
						userMyhairstyleMap.put("Photo", ipHost
								+ userMyhairstylePhotoMapping.getBasePicture()
										.getPicturepath());
					} else {
						userMyhairstyleMap.put("PicID1", "");
						userMyhairstyleMap.put("Photo", "");
					}

					// 发型相册
					if (null != userMyhairstylePhoto
							&& null != userMyhairstylePhoto.getBasePicture()) {
						userMyhairstyleMap.put("PicID2", ShunJianMeiUtil
								.intToString(userMyhairstylePhoto.getPicid()));
						userMyhairstyleMap.put("Image", ipHost
								+ userMyhairstylePhoto.getBasePicture()
										.getPicturepath());
					} else {
						userMyhairstyleMap.put("PicID2", "");
						userMyhairstyleMap.put("Image", "");
					}

					userMyhairstyleMap.put("HairStyle", userHairstyleList);
					userMyhairstyleMap.put("ServiceTimes",
							userMyhairstyleTimesList);
					userMyhairstyleMap.put("Intro", userMyhairstyle.getIntro());

					outList.add(userMyhairstyleMap);

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, outList.size());
					// }

				} else if ("0".equals(mystyleid)) {
					userMyhairstyleMap.put("ID", "");
					userMyhairstyleMap.put("PicID1", "");
					userMyhairstyleMap.put("Photo", "");
					userMyhairstyleMap.put("PicID2", "");
					userMyhairstyleMap.put("Image", "");

					// 发型项目
					List<Map<String, Object>> userHairstyleList = new ArrayList<Map<String, Object>>();
					List<UserHairstyleModel> userHairstyleModelList = userHairstyleService
							.searchUserHairstyleByUserID(userID);
					// 判断发型列表是否为空
					if (null != userHairstyleModelList
							&& userHairstyleModelList.size() > 0) {
						for (UserHairstyleModel obj : userHairstyleModelList) {
							Map<String, Object> userHairstyleMap = new HashMap<String, Object>();
							userHairstyleMap.put("ID", ShunJianMeiUtil
									.intToString(obj.getStyleid()));
							userHairstyleMap.put("Name", obj.getName());
							// Checked
							userHairstyleMap.put("Checked", "0");
							userHairstyleList.add(userHairstyleMap);
						}
					}

					userMyhairstyleMap.put("HairStyle", userHairstyleList);

					List<Map<String, Object>> userMyhairstyleTimesList = new ArrayList<Map<String, Object>>();
					// 查询美发师等级
					UsersubinfoModel usersubinfo = usersubinfoService
							.searchUsersubinfo(Integer.parseInt(userID));

					if (usersubinfo != null) {
						// 查询美发师服务编码
						// 美发师职称等级服务价格
						List<UserProfessionLevelServiceModel> userProfessionLevelServiceModelList = userProfessionLevelServiceService
								.searchUserProfessionLevelService(usersubinfo
										.getLevelid());
						if (null != userProfessionLevelServiceModelList
								&& userProfessionLevelServiceModelList.size() > 0) {
							for (UserProfessionLevelServiceModel obj : userProfessionLevelServiceModelList) {

								Map<String, Object> userMyhairstyleTimesMap = new HashMap<String, Object>();
								ServiceattributeModel serviceattributeModel = serviceattributeService
										.searchServiceattribute(obj
												.getServicecode());

								userMyhairstyleTimesMap.put("Code",
										obj.getServicecode());

								if (serviceattributeModel != null) {
									userMyhairstyleTimesMap.put("Name",
											serviceattributeModel
													.getServicename());
								} else {
									userMyhairstyleTimesMap.put("Name", "");
								}

								userMyhairstyleTimesMap.put("Times",
										ShunJianMeiUtil.intToString(obj
												.getTimes()));
								userMyhairstyleTimesList
										.add(userMyhairstyleTimesMap);

							}
						}

					}

					userMyhairstyleMap.put("ServiceTimes",
							userMyhairstyleTimesList);
					userMyhairstyleMap.put("Intro", "");

					outList.add(userMyhairstyleMap);

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
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
	@RequestMapping("H_SaveHairStyle")
	/**
	 * 2.3 保存我的发型
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_SaveHairStyle(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			String userID = inputJsonMap.get("UserID").toString();
			String iD = inputJsonMap.get("ID").toString();
			String picID1 = inputJsonMap.get("PicID1").toString();
			String picID2 = inputJsonMap.get("PicID2").toString();
			String styleID = inputJsonMap.get("StyleID").toString();
			String remark = inputJsonMap.get("Remark").toString();
			Object itemObj = inputJsonMap.get("Item");
			List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemObj;

			// 执行添加我的发型
			if (userMyhairstyleService.addUserHairstyle(userID, iD, picID1,
					picID2, styleID, remark, itemList)) {
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, "");
				outJsonMap.put(Constant.TOTAL, 1);
			} else {
				// 执行失败
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
		// 输出流
		ShunJianMeiUtil.outputJson(request, response, outJsonMap);

	}

	@RequestMapping("UploadImage")
	/**
	 * 2.4 保存图片
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public @ResponseBody
	Map<String, Object> UploadImage(HttpServletRequest request,HttpServletResponse response) {
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		Map<String, Object> userinfoMap = new HashMap<String, Object>();

		// String UserID = inputJsonMap.get("UserID").toString();
		// String Type = inputJsonMap.get("Type").toString();

		try {

			// 从item_upload.jsp中拿取数据，因为上传页的编码格式跟一般的不同，使用的是enctype="multipart/form-data"
			// form提交采用multipart/form-data,无法采用req.getParameter()取得数据
			// String itemNo = req.getParameter("itemNo");
			// System.out.println("itemNo======" + itemNo);
			/**
			 * ******************************使用 FileUpload
			 * 组件解析表单*******************
			 */
			// DiskFileItemFactory：创建 FileItem
			// 对象的工厂，在这个工厂类中可以配置内存缓冲区大小和存放临时文件的目录。
			// 获取文件上传需要保存的路径，upload文件夹需存在。
			String filePath = request.getServletContext().getRealPath(
					"/userImg/");
			// 2016年8月23日15:51:03
			/*String filePath = PropertiesTools.getValue("imageSavePath", "config.properties")+"/userImg/";
			System.out.println("filePath:"+filePath);*/
			
			
			File uploadPath = new File(filePath);

			// 如果目录不存在
			if (!uploadPath.exists()) {
				// 创建目录
				uploadPath.mkdir();
			}

			// 临时目录
			// File tempFile = new File(item.getName())构造临时对象
			File tempPath = new File(filePath + "/tempFile");
			if (!tempPath.exists()) {
				tempPath.mkdir();
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(4096);
			// the location for saving data that is larger than
			// getSizeThreshold()
			factory.setRepository(tempPath);

			// ServletFileUpload：负责处理上传的文件数据，并将每部分的数据封装成一到 FileItem 对象中。
			// 在接收上传文件数据时，会将内容保存到内存缓存区中，如果文件内容超过了 DiskFileItemFactory 指定的缓冲区的大小，
			// 那么文件将被保存到磁盘上，存储为 DiskFileItemFactory 指定目录中的临时文件。
			// 等文件数据都接收完毕后，ServletUpload再从文件中将数据写入到上传文件目录下的文件中
			ServletFileUpload upload = new ServletFileUpload(factory);
			// maximum size before a FileUploadException will be thrown
			upload.setSizeMax(1000000 * 20);

			/**
			 * *****************************解析表单传递过来的数据，返回List集合数据-类型:FileItem**
			 * ********
			 */
			try {
				// 3、判断用户的表单提交方式是不是multipart/form-data
				boolean bb = ServletFileUpload.isMultipartContent(request);
				if (bb) {
					List fileItems = upload.parseRequest(request);
					String itemNo = "";
					// Iterator iter = fileItems.iterator()取其迭代器
					// iter.hasNext()检查序列中是否还有元素
					for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
						// 获得序列中的下一个元素
						FileItem item = (FileItem) iter.next();
						
						// 判断是文件还是文本信息
						// 是普通的表单输入域
						if (item.isFormField()) {
							if ("ua".equals(item.getFieldName())) {
								itemNo = System.currentTimeMillis() + ".png";// item.getString();
							}
						}
						// 是否为input="type"输入域
						if (!item.isFormField()) {
							// 上传文件的名称和完整路径
							String fileName = item.getName();
							long size = item.getSize();
//							System.out.println("文件大小："+size);
							if(size>5*1000*1000){
								outJsonMap.put(Constant.CODE, Constant.CODE_1200);
								outJsonMap.put(Constant.MSG, "文件大小超过5M");
								return outJsonMap;
							}
							// 判断是否选择了文件
							if ((fileName == null || fileName.equals(""))
									&& size == 0) {
								outJsonMap.put(Constant.CODE,Constant.CODE_2000);
								outJsonMap.put(Constant.MSG, Constant.MSG_2);
								continue;
							}
							// 截取字符串 如：C:\WINDOWS\Debug\PASSWD.LOG
							// fileName =
							// fileName.substring(fileName.lastIndexOf("\\") +
							// 1, fileName.length());

							fileName = System.currentTimeMillis() + ".png";

							// 保存文件在服务器的物理磁盘中：第一个参数是：完整路径（不包括文件名）第二个参数是：文件名称
							// item.write(file);
							// 修改文件名和物料名一致，且强行修改了文件扩展名为gif
							// item.write(new File(uploadPath, itemNo +
							// ".gif"));
							// 将文件保存到目录下，不修改文件名
							item.write(new File(uploadPath, fileName));
							if(item.getSize()>1000*1000){
								File file=new File(filePath+"/"+fileName);
								InputStream in=new FileInputStream(file);
								ImgCompressUtil imgCompressUtil=new ImgCompressUtil(in);
								BufferedImage bi=ImageIO.read(file);
								imgCompressUtil.resizeByHeight((int)(bi.getHeight()*0.5), filePath+"/"+fileName);
							}
							// 将图片文件名写入打数据库
							BasePictureModel basePictureModel = new BasePictureModel();
							basePictureModel.setPicturepath("userImg/"
									+ fileName);
							// basePictureModel.setPicturepath("userImg/"+fileName);
							basePictureModel.setCreatetime(new Date());
							// 执行添加图片
							int picid = basePitureService
									.addBasePictureReturnID(basePictureModel);

							userinfoMap.put("ID",
									ShunJianMeiUtil.intToString(picid));
							userinfoMap.put("Image", ipHost + "userImg/"
									+ fileName);

							outList.add(userinfoMap);
							outJsonMap.put(Constant.CODE, Constant.CODE_1000);
							outJsonMap.put(Constant.MSG, Constant.MSG_1);
							outJsonMap.put(Constant.DATA, outList);
							outJsonMap.put(Constant.TOTAL, 1);
						}
					}
				} else {
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_2);
				}

			} catch (Exception e) {
				// 保存异常信息
				int exceptionId = utilService.addBaseException(
						request.getRequestURI(), "", e.getMessage());
				// 设置错误id
				outJsonMap.put(Constant.CODE, Constant.CODE_1200);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
			}

		} catch (Exception e) {
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(), "", e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}

		// 保存请求记录
		// utilService.addBaseRequestlog(request.getRequestURI(), request
		// .getAttribute("inputJsonInfo").toString(), "",
		// ShunJianMeiUtil.outputString(outJsonMap));
		// 输出流
		// ShunJianMeiUtil.outputJson(request, response, );
		return outJsonMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("H_StoreList")
	/**
	 * 2.5	商户列表（定义）
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_StoreList(HttpServletRequest request,
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
			sb.append("'Distance': '4.5Km',");
			sb.append("'OrderNum': '542',");
			sb.append("'CarNum': '10'");
			sb.append("},");
			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '木北造型(总部基地店)',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Address': '丰台富丰路18号',");
			sb.append("'Score': '5',");
			sb.append("'Distance': '2.5Km',");
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
				// 排序类型 1按星级2按订单3按距离（距离之后近到远）4按车位数
				String type = inputJsonMap.get("Type").toString();
				String Infversion = inputJsonMap.get("Infversion").toString();

				// 获取店铺列表
				List<StoreinfoModel> storeinfoModel = storeinfoService
						.searchStoreinfo(cityid, score, orderquantity,
								longitude, latitude, car, pageSize, pageIndex,
								type, Infversion);
				// 美发店
				// StoreinfoModel storeinfo = storeinfoService
				// .searchStoreinfo(Integer.parseInt(cityid));
				/*
				 * // 店铺相册 List<StorePhotoMappingModel>
				 * storePhotoMappingModelList = storePhotoMappingService
				 * .searchStorePhotoMapping(storeinfo.getStoreid(), 1);
				 * List<Map<String, Object>> storePhotoMappingList = new
				 * ArrayList<Map<String, Object>>(); for (StorePhotoMappingModel
				 * obj : storePhotoMappingModelList) { Map<String, Object> map =
				 * new HashMap<String, Object>();
				 * 
				 * if (null != obj.getBasePicture() && null !=
				 * obj.getBasePicture().getPicturepath()) { map.put("Photo",
				 * ipHost + obj.getBasePicture().getPicturepath()); }else {
				 * map.put("Photo",""); } storePhotoMappingList.add(map); }
				 */
				if (null != storeinfoModel && storeinfoModel.size() > 0) {

					List<Map<String, Object>> storeinfoList = new ArrayList<Map<String, Object>>();

					for (StoreinfoModel obj : storeinfoModel) {
						Map<String, Object> storeinfoMap = new HashMap<String, Object>();

						storeinfoMap.put("ID",
								ShunJianMeiUtil.intToString(obj.getStoreid()));
						storeinfoMap.put("Name", obj.getName());
						// storeinfoMap.put("Photo", storePhotoMappingList);
						// 店铺相册
						StorePhotoMappingModel photoMapping = storePhotoMappingService
								.searchStorePhoto(obj.getStoreid(), 1);
						if (photoMapping != null
								&& null != photoMapping.getBasePicture()) {
							storeinfoMap.put("Photo", ipHost
									+ photoMapping.getBasePicture()
											.getPicturepath());
						} else {
							storeinfoMap.put("Photo", "");
						}
						storeinfoMap.put("Address", obj.getAddress());
						storeinfoMap.put("Score",
								ShunJianMeiUtil.floatToString(obj.getScore()));
						// 根据经纬度计算距离
						double mylongitude = 0;
						double mylatitude = 0;
						if (null != obj.getLongitude()
								&& obj.getLongitude().length() > 0) {
							mylongitude = Double
									.parseDouble(obj.getLongitude());
						}
						if (null != obj.getLatitude()
								&& obj.getLatitude().length() > 0) {
							mylatitude = Double.parseDouble(obj.getLatitude());
						}

						storeinfoMap.put("Distance", ShunJianMeiUtil
								.LantitudeLongitudeDist(mylongitude,
										mylatitude, longitude, latitude));
						storeinfoMap.put("OrderNum", ShunJianMeiUtil
								.intToString(obj.getOrderquantity()));
						storeinfoMap
								.put("CarNum", ShunJianMeiUtil.intToString(obj
										.getCarnumber()));
						storeinfoList.add(storeinfoMap);

					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, storeinfoList);
					outJsonMap.put(Constant.TOTAL, storeinfoList.size());

				} else {
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
	@RequestMapping("H_StoreList2")
	/**
	 * 2.5	商户列表（定义）
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_StoreList2(HttpServletRequest request,
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
			sb.append("'Distance': '4.5Km',");
			sb.append("'OrderNum': '542',");
			sb.append("'CarNum': '10'");
			sb.append("},");
			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '木北造型(总部基地店)',");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png',");
			sb.append("'Address': '丰台富丰路18号',");
			sb.append("'Score': '5',");
			sb.append("'Distance': '2.5Km',");
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
				// 排序类型 1按星级2按订单3按距离（距离之后近到远）4按车位数
				String type = inputJsonMap.get("Type").toString();
				String Infversion = inputJsonMap.get("Infversion").toString();

				// 获取店铺列表
				List<StoreinfoModel> storeinfoModel = storeinfoService
						.searchStoreinfo(cityid, score, orderquantity,
								longitude, latitude, car, pageSize, pageIndex,
								type, Infversion);
				// 美发店
				// StoreinfoModel storeinfo = storeinfoService
				// .searchStoreinfo(Integer.parseInt(cityid));
				/*
				 * // 店铺相册 List<StorePhotoMappingModel>
				 * storePhotoMappingModelList = storePhotoMappingService
				 * .searchStorePhotoMapping(storeinfo.getStoreid(), 1);
				 * List<Map<String, Object>> storePhotoMappingList = new
				 * ArrayList<Map<String, Object>>(); for (StorePhotoMappingModel
				 * obj : storePhotoMappingModelList) { Map<String, Object> map =
				 * new HashMap<String, Object>();
				 * 
				 * if (null != obj.getBasePicture() && null !=
				 * obj.getBasePicture().getPicturepath()) { map.put("Photo",
				 * ipHost + obj.getBasePicture().getPicturepath()); }else {
				 * map.put("Photo",""); } storePhotoMappingList.add(map); }
				 */
				if (null != storeinfoModel && storeinfoModel.size() > 0) {

					List<Map<String, Object>> storeinfoList = new ArrayList<Map<String, Object>>();

					for (StoreinfoModel obj : storeinfoModel) {
						Map<String, Object> storeinfoMap = new HashMap<String, Object>();

						storeinfoMap.put("ID",
								ShunJianMeiUtil.intToString(obj.getStoreid()));
						storeinfoMap.put("Name", obj.getName());
						// storeinfoMap.put("Photo", storePhotoMappingList);
						// 店铺相册
						StorePhotoMappingModel photoMapping = storePhotoMappingService
								.searchStorePhoto(obj.getStoreid(), 1);
						if (photoMapping != null
								&& null != photoMapping.getBasePicture()) {
							storeinfoMap.put("Photo", ipHost
									+ photoMapping.getBasePicture()
											.getPicturepath());
						} else {
							storeinfoMap.put("Photo", "");
						}
						storeinfoMap.put("Address", obj.getAddress());
						storeinfoMap.put("Score",
								ShunJianMeiUtil.floatToString(obj.getScore()));
						// 根据经纬度计算距离
						double mylongitude = 0;
						double mylatitude = 0;
						if (null != obj.getLongitude()
								&& obj.getLongitude().length() > 0) {
							mylongitude = Double
									.parseDouble(obj.getLongitude());
						}
						if (null != obj.getLatitude()
								&& obj.getLatitude().length() > 0) {
							mylatitude = Double.parseDouble(obj.getLatitude());
						}

						storeinfoMap.put("Distance", ShunJianMeiUtil
								.LantitudeLongitudeDist(mylongitude,
										mylatitude, longitude, latitude));
						storeinfoMap.put("OrderNum", ShunJianMeiUtil
								.intToString(obj.getOrderquantity()));
						storeinfoMap
								.put("CarNum", ShunJianMeiUtil.intToString(obj
										.getCarnumber()));
						storeinfoMap.put("istype", obj.getIstype());
						storeinfoList.add(storeinfoMap);

					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, storeinfoList);
					outJsonMap.put(Constant.TOTAL, storeinfoList.size());

				} else {
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
	@RequestMapping("H_StoreDetail")
	/**
	 * 2.6	商户详情（定义）
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_StoreDetail(HttpServletRequest request,
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
			sb.append("{");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'");
			sb.append("},");
			sb.append("{");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'");
			sb.append("},");
			sb.append("{");
			sb.append("'Photo': 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'");
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

			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			try {
				// 美发店ID
				String ID = inputJsonMap.get("ID").toString();
				// 用户ID
				String UserID = inputJsonMap.get("UserID").toString();
				// 美发店
				StoreinfoModel storeinfo = storeinfoService
						.searchStoreinfo(Integer.parseInt(ID));

				// 输出的集合
				List<Map<String, Object>> storeinfoList = new ArrayList<Map<String, Object>>();
				// 美发店相册
				List<StorePhotoMappingModel> storePhotoMappingModelList = storePhotoMappingService
						.searchStorePhotoMapping(storeinfo.getStoreid(), 1);
				List<Map<String, Object>> storePhotoMappingList = new ArrayList<Map<String, Object>>();
				for (StorePhotoMappingModel obj : storePhotoMappingModelList) {
					Map<String, Object> map = new HashMap<String, Object>();

					map.put("Photo", ipHost
							+ obj.getBasePicture().getPicturepath());

					storePhotoMappingList.add(map);
				}

				// 美发店Map
				Map<String, Object> storeinfoMap = new HashMap<String, Object>();
				storeinfoMap.put("ID",
						ShunJianMeiUtil.intToString(storeinfo.getStoreid()));
				storeinfoMap.put("Name", storeinfo.getName());
				storeinfoMap.put("Address", storeinfo.getAddress());
				storeinfoMap.put("BusinessHours", storeinfo.getBusinesshours());
				storeinfoMap.put("Score", (int) storeinfo.getScore());
				storeinfoMap.put("Distance",
						ShunJianMeiUtil.intToString(storeinfo.getDistrictid()));
				storeinfoMap.put("OrderNum", ShunJianMeiUtil
						.intToString(storeinfo.getOrderquantity()));
				storeinfoMap.put("CarNum",
						ShunJianMeiUtil.intToString(storeinfo.getCarnumber()));
				storeinfoMap.put("Tel", storeinfo.getTel());
				storeinfoMap.put("Intro", storeinfo.getIntro());
				// 收藏
				FavoritesModel favorites = favoritesService.searchFavorites(
						Integer.parseInt(UserID), 3, Integer.parseInt(ID));
				if (null != favorites)
					storeinfoMap.put("IsFavorite", "1");
				else
					storeinfoMap.put("IsFavorite", "0");
				storeinfoMap.put("Images", storePhotoMappingList);

				storeinfoList.add(storeinfoMap);

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, storeinfoList);
				outJsonMap.put(Constant.TOTAL, storeinfoList.size());

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
	@RequestMapping("H_MaterialInfo")
	/**
	 * 2.7	物料提取信息（定义）
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_MaterialInfo(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'Address': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Address': '木北造型(科技园店),地址：丰台富丰路4号'");
			sb.append("},");
			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Address': '木北造型(总部基地店),地址：丰台富丰路14号'");
			sb.append("}");
			sb.append("],");
			sb.append("'Product': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '沙宣',");
			sb.append("'Item': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '染发',");
			sb.append("'Item': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '染发膏黑色'");
			sb.append("},");
			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '染发膏红色'");
			sb.append("}");
			sb.append("]");
			sb.append("}");
			sb.append("]");
			sb.append("},");
			sb.append("{");
			sb.append("'ID': '2',");
			sb.append("'Name': '欧莱雅',");
			sb.append("'Item': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Name': '染发',");
			sb.append("'Item': [");
			sb.append("{");
			sb.append("'ID': '3',");
			sb.append("'Name': '染发膏黑色'");
			sb.append("},");
			sb.append("{");
			sb.append("'ID': '4',");
			sb.append("'Name': '染发膏红色'");
			sb.append("}");
			sb.append("]");
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

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				Map<String, Object> map1 = new HashMap<String, Object>();
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				// 物料（orderdetail）订单详情
				/*
				 * List<MaterialinfoModel> materialinfo =
				 * materialinfoService.searchMaterialinfo();
				 * 
				 * List<Map<String, Object>> materialinfoList = new
				 * ArrayList<Map<String, Object>>(); if (null != materialinfo &&
				 * materialinfo.size() > 0) { for (MaterialinfoModel obj :
				 * materialinfo) { Map<String, Object> materialinfoMap = new
				 * HashMap<String, Object>(); materialinfoMap.put("ID",
				 * ShunJianMeiUtil .intToString(obj.getMaterialid()));
				 * materialinfoMap.put("Name", obj.getMaterialname());
				 * materialinfoList.add(materialinfoMap); } }
				 */
				// 物料品类
				/*
				 * List<MaterialCategoryModel> materialCategory =
				 * materialCategoryService .searchMaterialCategory();
				 * 
				 * List<Map<String, Object>> materialCategoryList = new
				 * ArrayList<Map<String, Object>>(); if (null !=
				 * materialCategory && materialCategory.size() > 0) { for
				 * (MaterialCategoryModel obj : materialCategory) { Map<String,
				 * Object> materialCategoryMap = new HashMap<String, Object>();
				 * materialCategoryMap.put("ID", ShunJianMeiUtil
				 * .intToString(obj.getCategoryid()));
				 * 
				 * List<MaterialinfoModel> materialinfo = materialinfoService
				 * .searchMaterialinfoById(obj.getCategoryid());
				 * List<Map<String, Object>> materialinfoList = new
				 * ArrayList<Map<String, Object>>(); if (null != materialinfo &&
				 * materialinfo.size() > 0) { for (MaterialinfoModel material :
				 * materialinfo) { Map<String, Object> materialinfoMap = new
				 * HashMap<String, Object>(); materialinfoMap.put("ID",
				 * ShunJianMeiUtil .intToString(material.getMaterialid()));
				 * materialinfoMap.put("Name", material.getMaterialname());
				 * materialinfoList.add(materialinfoMap); } }
				 * 
				 * materialCategoryMap.put("Name", obj.getCategoryname());
				 * materialCategoryMap.put("Item", materialinfoList);
				 * materialCategoryList.add(materialCategoryMap); } }
				 */
				// 物料品牌
				List<MaterialBrandModel> materialBrandList = materialBrandService
						.searchMaterialBrand();
				if (null != materialBrandList && materialBrandList.size() > 0) {
					for (MaterialBrandModel obj : materialBrandList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("ID",
								ShunJianMeiUtil.intToString(obj.getBrandid()));
						map.put("Name", obj.getBrandname());

						// 物料类别
						List<MaterialCategoryModel> materialCategory = materialCategoryService
								.searchMaterialCategory();
						List<Map<String, Object>> materialCategoryList = new ArrayList<Map<String, Object>>();
						if (null != materialCategory
								&& materialCategory.size() > 0) {
							for (MaterialCategoryModel category : materialCategory) {
								Map<String, Object> materialCategoryMap = new HashMap<String, Object>();
								materialCategoryMap.put("ID", ShunJianMeiUtil
										.intToString(category.getCategoryid()));
								// 根据物料品牌和物料类别筛选物料信息
								List<MaterialinfoModel> materialinfo = materialinfoService
										.searchMaterialinfoById(
												category.getCategoryid(),
												obj.getBrandid());
								List<Map<String, Object>> materialinfoList = new ArrayList<Map<String, Object>>();
								if (null != materialinfo
										&& materialinfo.size() > 0) {
									for (MaterialinfoModel material : materialinfo) {
										Map<String, Object> materialinfoMap = new HashMap<String, Object>();
										materialinfoMap
												.put("ID",
														ShunJianMeiUtil
																.intToString(material
																		.getMaterialid()));
										materialinfoMap.put("Name",
												material.getMaterialname());
										materialinfoList.add(materialinfoMap);
									}
								}

								materialCategoryMap.put("Name",
										category.getCategoryname());
								materialCategoryMap.put("Item",
										materialinfoList);
								materialCategoryList.add(materialCategoryMap);
							}
						}
						map.put("Item", materialCategoryList);
						list.add(map);
					}
				}

				// 提取地点（提取点表）
				List<MaterialStationModel> materialStation = materialStationService
						.searchMaterialStation();

				List<Map<String, Object>> materialStationList = new ArrayList<Map<String, Object>>();

				if (null != materialStation && materialStation.size() > 0) {
					for (MaterialStationModel obj : materialStation) {
						Map<String, Object> materialStationMap = new HashMap<String, Object>();
						materialStationMap
								.put("ID", ShunJianMeiUtil.intToString(obj
										.getStationid()));
						materialStationMap.put("Address", obj.getAddress());
						materialStationList.add(materialStationMap);
					}
				}

				map1.put("Address", materialStationList);
				map1.put("Product", list);
				list1.add(map1);

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, list1);
				outJsonMap.put(Constant.TOTAL, list1.size());

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
	@RequestMapping("H_SaveMaterialDelivery")
	/**
	 * 2.8	保存提取信息（定义）
	 * @param request
	 * @param response
	 * @param Infversion
	 */
	public void H_SaveMaterialDelivery(HttpServletRequest request,
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

				// 获取参数
				String userID = inputJsonMap.get("UserID").toString();
				String deliverytime = inputJsonMap.get("Date").toString();
				//
				String addressID = inputJsonMap.get("AddressID").toString();
				// String item = inputJsonMap.get("item").toString();

				Object itemObj = inputJsonMap.get("Item");
				List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemObj;

				// 保存提取物料信息
				if (materialDeliveryrecordService.addMaterialDeliveryrecord(
						userID, deliverytime, addressID, itemList)) {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1);
					outJsonMap.put(Constant.DATA, "");
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
	@RequestMapping("H_MaterialApply")
	/**
	 * 2.9	待提取物料（定义）
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_MaterialApply(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Date': '2015-9-23',");
			sb.append("'Address': '木北造型(科技园店),地址：丰台富丰路4号',");
			sb.append("'Item': [");
			sb.append("{");
			sb.append("'Name': '欧莱雅染发膏(黑色)',");
			sb.append("'Num': '10'");
			sb.append("},");
			sb.append("{");
			sb.append("'Name': '欧莱雅染发膏(红色)',");
			sb.append("'Num': '10'");
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

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {

				// 获取参数
				String UserID = inputJsonMap.get("UserID").toString();
				// 根据用户id查询提取记录
				List<MaterialDeliveryrecordModel> MaterialDeliveryrecord = materialDeliveryrecordService
						.searchmaterialDeliveryrecord(Integer.parseInt(UserID),
								1);
				// 输出集合
				List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
				// 判断提取记录是否为空
				if (null != MaterialDeliveryrecord
						&& MaterialDeliveryrecord.size() > 0) {

					for (MaterialDeliveryrecordModel obj : MaterialDeliveryrecord) {
						Map<String, Object> materialDeliveryrecordMap = new HashMap<String, Object>();
						// 物料名称和数量，根据提取记录id
						List<MaterialDeliveryrecordDetailModel> materialDeliveryrecordDetail = materialDeliveryrecordDetailService
								.searchmaterialDeliveryrecordDetail(obj
										.getRecordid());
						List<Map<String, Object>> materialDeliveryrecordDetailList = new ArrayList<Map<String, Object>>();
						// 判断提取记录详细是否为空
						if (null != materialDeliveryrecordDetail
								&& materialDeliveryrecordDetail.size() > 0) {
							for (MaterialDeliveryrecordDetailModel obj1 : materialDeliveryrecordDetail) {
								Map<String, Object> materialDeliveryrecordDetailMap = new HashMap<String, Object>();
								// 根据ID查询实体得到Name
								MaterialinfoModel materialinfo = materialinfoService
										.searchMaterialNameById(String
												.valueOf(obj1.getMaterialid()));
								if (materialinfo != null) {
									materialDeliveryrecordDetailMap.put("Name",
											materialinfo.getMaterialname());
								} else {
									materialDeliveryrecordDetailMap.put("Name",
											"");
								}
								materialDeliveryrecordDetailMap.put("Num",
										ShunJianMeiUtil.intToString(obj1
												.getQuantity()));
								materialDeliveryrecordDetailList
										.add(materialDeliveryrecordDetailMap);
							}
						}
						materialDeliveryrecordMap.put("ID",
								ShunJianMeiUtil.intToString(obj.getRecordid()));
						materialDeliveryrecordMap
								.put("Date", ShunJianMeiUtil
										.getAppointmentString(ShunJianMeiUtil
												.dateToString(obj
														.getAppointmenttime())));
						MaterialStationModel materialStation = materialStationService
								.searchMaterialStation(obj.getStationid());
						if (materialStation != null) {
							materialDeliveryrecordMap.put("Address",
									materialStation.getAddress());
						} else {
							materialDeliveryrecordMap.put("Address", "");
						}
						materialDeliveryrecordMap.put("Item",
								materialDeliveryrecordDetailList);
						outList.add(materialDeliveryrecordMap);
					}
				}

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, outList.size());
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
	@RequestMapping("H_MaterialRecord")
	/**
	 * 2.10	提取记录（定义）
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void H_MaterialRecord(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'ID': '1',");
			sb.append("'Date': '2015-9-23',");
			sb.append("'Address': '木北造型(科技园店),地址：丰台富丰路4号',");
			sb.append("'Item': [");
			sb.append("{");
			sb.append("'Name': '欧莱雅染发膏(黑色)',");
			sb.append("'Num': '10'");
			sb.append("},");
			sb.append("{");
			sb.append("'Name': '欧莱雅染发膏(红色)',");
			sb.append("'Num': '10'");
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

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {

				// 获取参数
				String ID = inputJsonMap.get("ID").toString();
				String UserID = inputJsonMap.get("UserID").toString();
				// 根据用户id查询提取记录
				List<MaterialDeliveryrecordModel> MaterialDeliveryrecord = materialDeliveryrecordService
						.searchmaterialDeliveryrecord(Integer.parseInt(ID),
								Integer.parseInt(UserID), 2);
				// 输出集合
				List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
				// 判断提取记录是否为空
				if (null != MaterialDeliveryrecord
						&& MaterialDeliveryrecord.size() > 0) {

					for (MaterialDeliveryrecordModel obj : MaterialDeliveryrecord) {
						Map<String, Object> materialDeliveryrecordMap = new HashMap<String, Object>();
						// 物料名称和数量，根据提取记录id
						List<MaterialDeliveryrecordDetailModel> materialDeliveryrecordDetail = materialDeliveryrecordDetailService
								.searchmaterialDeliveryrecordDetail(obj
										.getRecordid());
						List<Map<String, Object>> materialDeliveryrecordDetailList = new ArrayList<Map<String, Object>>();
						// 判断提取记录详细是否为空
						if (null != materialDeliveryrecordDetail
								&& materialDeliveryrecordDetail.size() > 0) {
							for (MaterialDeliveryrecordDetailModel obj1 : materialDeliveryrecordDetail) {
								Map<String, Object> materialDeliveryrecordDetailMap = new HashMap<String, Object>();
								MaterialinfoModel materialinfo = materialinfoService
										.searchMaterialNameById(String
												.valueOf(obj1.getMaterialid()));
								if (materialinfo != null) {
									materialDeliveryrecordDetailMap.put("Name",
											materialinfo.getMaterialname());
								} else {
									materialDeliveryrecordDetailMap.put("Name",
											"");
								}
								materialDeliveryrecordDetailMap.put("Num",
										ShunJianMeiUtil.intToString(obj1
												.getQuantity()));
								materialDeliveryrecordDetailList
										.add(materialDeliveryrecordDetailMap);
							}
						}
						materialDeliveryrecordMap.put("ID",
								ShunJianMeiUtil.intToString(obj.getRecordid()));
						materialDeliveryrecordMap.put("Date", ShunJianMeiUtil
								.dateToString(obj.getDeliverytime()));
						MaterialStationModel materialStation = materialStationService
								.searchMaterialStation(obj.getStationid());
						if (materialStation != null) {
							materialDeliveryrecordMap.put("Address",
									materialStation.getAddress());
						} else {
							materialDeliveryrecordMap.put("Address", "");
						}
						materialDeliveryrecordMap.put("Item",
								materialDeliveryrecordDetailList);
						outList.add(materialDeliveryrecordMap);
					}
				}

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
			ShunJianMeiUtil.outputJson(request, response, outJsonMap);

		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("H_UpdateRecordState")
	/**
	 * 2.11	更新提取状态（定义）
	 * @param request
	 * @param response
	 * @param Infversion
	 */
	public void H_UpdateRecordState(HttpServletRequest request,
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
				// 获得参数
				String ID = inputJsonMap.get("ID").toString();

				// 判断是否修改成功
				if (materialDeliveryrecordService
						.updateMaterialDeliveryrecordModel(Integer.parseInt(ID)))
					;
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1);
				outJsonMap.put(Constant.DATA, "");
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
	@RequestMapping("H_UpdateOrderState")
	/**
	 * 2.14	订单更新状态（定义）
	 * @param request
	 * @param response
	 * @param Infversion
	 */
	public void H_UpdateOrderState(HttpServletRequest request,
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
				// 获得参数
				String orderid = inputJsonMap.get("OrderID").toString();
				String paystate = inputJsonMap.get("State").toString();
				// 判断是否修改成功
				//
				if (orderinfoService.updateOrderinfoPaystate(
						Integer.parseInt(orderid), Integer.parseInt(paystate))) {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, "");
					outJsonMap.put(Constant.TOTAL, 1);

				} else {
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_2);
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
	@RequestMapping("AdditionalOrder")
	/**
	 * 2.15	加单（定义）
	 * @param request
	 * @param response
	 * @param Infversion
	 */
	public void AdditionalOrder(HttpServletRequest request,
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
				// 获得参数
				String customerid = inputJsonMap.get("UserID").toString();
				String orderid = inputJsonMap.get("OrderID").toString();
				String additionalcontent = inputJsonMap.get("Content")
						.toString();
				String additionalamount = inputJsonMap.get("Amount").toString();
				// 判断是否修改成功
				if (orderinfoService.updateOrderinfo(Integer.parseInt(orderid),
						Integer.parseInt(customerid), additionalcontent,
						Double.parseDouble(additionalamount))) {
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
	@RequestMapping("H_CashRecord")
	/**
	 * 2.17	提现记录（定义）
	 * @param request
	 * @param response
	 */
	public void H_CashRecord(HttpServletRequest request,
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
				// 用户id
				String userid = inputJsonMap.get("UserID").toString();
				// 开户人名字
				String username = inputJsonMap.get("Name").toString();
				// 卡号
				String cardnumber = inputJsonMap.get("CardID").toString();
				// 开户行
				String bank = inputJsonMap.get("Bank").toString();
				// 金额
				String amount = inputJsonMap.get("Amount").toString();

				// 查询钱包表，更新钱包表的金额
				UserWalletModel userWalletModel = walletPayService
						.searchUserWallet(Integer.parseInt(userid), 2);
				if (userWalletModel != null) {
					// 剩余金额 = 钱包金额 - 支付金额
					float allAmount = userWalletModel.getBalance();
					// 店铺押金
					BaseConfigModel baseConfig = baseConfigService
							.searchBaseConfigByCode("cashfund");
					// 可提现金额
					float usableAmount = allAmount
							- Float.parseFloat(baseConfig.getConfigvalue());
					// 钱包余额大于可提现金额
					if (usableAmount >= Float.parseFloat(amount)) {
						float newAmount = allAmount - Float.parseFloat(amount);
						userWalletModel.setBalance(newAmount);
						userWalletModel.setOwnertype(2);

						UserCashrecordModel userCashrecord = new UserCashrecordModel();
						// 将记录数据存入
						userCashrecord.setUserid(Integer.parseInt(userid));
						userCashrecord.setUsername(username);
						userCashrecord.setCardnumber(cardnumber);
						userCashrecord.setBank(bank);
						userCashrecord.setAmount(Double.parseDouble(amount));
						// 申请中
						userCashrecord.setState(1);
						// userCashrecord.setState(2);
						userCashrecord.setCreatetime(new Date());

						// 判断是否存入
						if (userCashrecordService.addUserCashrecord(
								userCashrecord, userWalletModel)) {
							// 成功
							outJsonMap.put(Constant.CODE, Constant.CODE_1000);
							outJsonMap.put(Constant.MSG, Constant.MSG_0);
							outJsonMap.put(Constant.DATA, "");
							outJsonMap.put(Constant.TOTAL, 1);
						} else {
							// 失败
							outJsonMap.put(Constant.CODE, Constant.CODE_2000);
							outJsonMap.put(Constant.MSG, Constant.MSG_0_0
									+ "操作失败");
							outJsonMap.put(Constant.DATA, "");
							outJsonMap.put(Constant.TOTAL, 1);
						}
					} else {
						outJsonMap.put(Constant.CODE, Constant.CODE_2000);
						outJsonMap.put(Constant.MSG, Constant.MSG_AMOUNT);
						outJsonMap.put(Constant.DATA, "");
						outJsonMap.put(Constant.TOTAL, 1);
					}
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
	@RequestMapping("H_ChangePassWord")
	/**
	 * 3.1  修改密码
	 * @param request
	 * @param response
	 * @param Infversion
	 */
	public void H_ChangePassWord(HttpServletRequest request,
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
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();
			try {
				String UserId = inputJsonMap.get("UserID").toString();
				String OldPassWord = inputJsonMap.get("OldPassWord").toString();
				String NewPassWord = inputJsonMap.get("NewPassWord").toString();

				// 执行修改用户密码
				if (userinfoService.updateUserinfo(Integer.parseInt(UserId),
						OldPassWord, NewPassWord, 2)) {
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
	@RequestMapping("H_Feedback")
	/**
	 * 3.2  意见建议
	 * @param request
	 * @param response
	 * @param Infversion
	 */
	public void H_Feedback(HttpServletRequest request,
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
				baseSuggestion.setSource(3);
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
	@RequestMapping("H_MyBalance")
	/**
	 * 1.15	我的余额（定义）
	 * @param request
	 * @param response
	 */
	public void H_MyBalance(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			String userid = inputJsonMap.get("UserID").toString();

			// 返回集合
			List<Map<String, Object>> userinfoList = new ArrayList<Map<String, Object>>();

			// 获取我的余额
			UserWalletModel userWalletModel = myBalanceService
					.searchUserWallet(Integer.parseInt(userid), 2);// 美发师余额
			Map<String, Object> userWalletMap = new HashMap<String, Object>();
			if (userWalletModel != null) {
				userWalletMap.put("Total", ShunJianMeiUtil
						.floatToString(userWalletModel.getBalance()));
				// 我的余额
				float myBalance = userWalletModel.getBalance();
				// 押金
				BaseConfigModel baseConfigModel = myBalanceService
						.searchBaseConfig(Constant.CONFIGCODE_05);
				float cashfund = 0;
				if (baseConfigModel != null) {
					cashfund = Float.parseFloat(baseConfigModel
							.getConfigvalue());
				}

				// 可提现金额 = 我的余额 - 押金
				float amount = myBalance - cashfund;

				userWalletMap.put("Amount",
						ShunJianMeiUtil.floatToString(amount));

				userinfoList.add(userWalletMap);

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

	/**
	 * 1.17 我的消费记录（定义）
	 * 
	 * @param request
	 * @param response
	 * @SuppressWarnings("unchecked")
	 * @RequestMapping("H_TradeRecord") public void
	 *                                  H_TradeRecord(HttpServletRequest
	 *                                  request, HttpServletResponse response) {
	 *                                  // 取得请求参数 inputJsonMap = (Map<String,
	 *                                  Object>) request.getSession
	 * 
	 *                                  ().getAttribute("inputJsonMap");
	 *                                  Map<String, Object> outJsonMap = new
	 *                                  HashMap<String, Object>();
	 * 
	 *                                  try {
	 * 
	 *                                  // 返回集合 List<Map<String, Object>>
	 *                                  userTradeList = new
	 * 
	 *                                  ArrayList<Map<String, Object>>();
	 * 
	 *                                  // 用户ID String userid =
	 *                                  inputJsonMap.get("UserID").toString();
	 *                                  // 返回数据行数 int pageSize =
	 *                                  Integer.parseInt
	 *                                  (inputJsonMap.get("PageSize")
	 *                                  .toString()); // 当前页数 从1开始 int pageIndex
	 *                                  = Integer.parseInt(inputJsonMap.get(
	 *                                  "PageIndex") .toString()); // 交易额类型 int
	 *                                  type =
	 *                                  Integer.parseInt(inputJsonMap.get(
	 *                                  "Type").toString()); // 交易排序 int sort =
	 *                                  Integer
	 *                                  .parseInt(inputJsonMap.get("Sort")
	 *                                  .toString());
	 * 
	 *                                  // 根据用户ID查询我的钱包ID UserWalletModel
	 *                                  userWalletModel =
	 * 
	 *                                  tradeRecordService.searchUserWallet(
	 *                                  Integer.parseInt(userid));
	 * 
	 *                                  if (userWalletModel != null) { //
	 *                                  获取的交易记录 List<UserTradelogModel>
	 *                                  userTradelogList = tradeRecordService
	 *                                  .searchUserTradelog
	 *                                  (userWalletModel.getOwnerid(), pageSize,
	 *                                  pageIndex, type, sort); if
	 *                                  (userTradelogList != null &&
	 *                                  userTradelogList.size() > 0) { for
	 *                                  (UserTradelogModel obj :
	 *                                  userTradelogList) {
	 * 
	 *                                  Map<String, Object> userTradelogMap =
	 *                                  new HashMap<String, Object>();
	 * 
	 *                                  userTradelogMap.put("ID",
	 *                                  ShunJianMeiUtil
	 *                                  .intToString(obj.getLogid()));
	 *                                  userTradelogMap.put("OrderCode", obj
	 *                                  .getOrderinfoModel().getOrdercode());
	 *                                  userTradelogMap.put("Remark",
	 *                                  obj.getRemark());
	 *                                  userTradelogMap.put("PayTime",
	 *                                  ShunJianMeiUtil
	 *                                  .dateConvertString(obj.getOrderinfoModel
	 *                                  () .getPaytime()));
	 *                                  userTradelogMap.put("Amount",
	 *                                  ShunJianMeiUtil
	 *                                  .floatToString(obj.getAmount()));
	 *                                  userTradeList.add(userTradelogMap);
	 * 
	 *                                  } outJsonMap.put(Constant.CODE,
	 * 
	 *                                  Constant.CODE_1000);
	 *                                  outJsonMap.put(Constant.MSG,
	 * 
	 *                                  Constant.MSG_0);
	 *                                  outJsonMap.put(Constant.DATA,
	 * 
	 *                                  userTradeList);
	 *                                  outJsonMap.put(Constant.TOTAL, 1); }
	 *                                  else { // 无数据
	 *                                  outJsonMap.put(Constant.CODE,
	 *                                  Constant.CODE_2100);
	 *                                  outJsonMap.put(Constant.MSG,
	 *                                  Constant.MSG_0); } } else { // 无数据
	 *                                  outJsonMap.put(Constant.CODE,
	 *                                  Constant.CODE_2100);
	 *                                  outJsonMap.put(Constant.MSG,
	 *                                  Constant.MSG_0); }
	 * 
	 *                                  } catch (Exception e) { // 保存异常信息 int
	 *                                  exceptionId =
	 *                                  utilService.addBaseException(
	 *                                  request.getRequestURI(),
	 *                                  request.getAttribute
	 * 
	 *                                  ("inputJsonInfo").toString(),
	 * 
	 *                                  e.getMessage()); // 设置错误id
	 *                                  outJsonMap.put(Constant.CODE,
	 *                                  Constant.CODE_1200);
	 *                                  outJsonMap.put(Constant.MSG,
	 *                                  Constant.MSG_0_0 +
	 * 
	 *                                  exceptionId); }
	 * 
	 *                                  // 保存请求记录
	 *                                  utilService.addBaseRequestlog(request
	 *                                  .getRequestURI(), request
	 *                                  .getAttribute
	 * 
	 *                                  ("inputJsonInfo").toString(), "",
	 *                                  ShunJianMeiUtil
	 *                                  .outputString(outJsonMap)); // 输出流
	 *                                  ShunJianMeiUtil.outputJson(request,
	 *                                  response, outJsonMap);
	 * 
	 *                                  }
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping("H_OrderList")
	/**
	 * 2.14我的订单列表
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_OrderList(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, String>> outList = new ArrayList<Map<String, String>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, String> orderinfoMap = new HashMap<String, String>();
			orderinfoMap.put("OrderID", "10");
			orderinfoMap.put("OrderCode", "2015092410301224365");
			orderinfoMap.put("Name", "阿东客户");
			orderinfoMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			// orderinfoMap.put("LevelName", "发型总监");
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

				outList = orderinfoService.searchOrderListByUserID(userID,
						state, sort, pageIndex, pageSize);

				if (null != outList) {

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
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
	@RequestMapping("H_OrderDetail")
	/**
	 * 2.13	我的订单详情
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_OrderDetail(HttpServletRequest request,
			HttpServletResponse response) {
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
			orderinfoMap.put("Name", "阿东顾客");
			orderinfoMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			// orderinfoMap.put("LevelName", "发型总监");
			orderinfoMap.put("Mobile", "18512345678");
			// orderinfoMap.put("Score", "4");
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
				String userID = inputJsonMap.get("UserID").toString();// 美发师id
				String orderID = inputJsonMap.get("OrderID").toString();

				outList = orderinfoService.searchOrderDetailForMeiFaShi(userID,
						orderID);

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
	@RequestMapping("H_TradeRecord")
	/**
	 * 2.18	我的消费记录
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_TradeRecord(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put("ID", "2");
			outMap.put("OrderCode", "2015092410301224375");
			outMap.put("Remark", "女士长发");
			outMap.put("PayTime", "2015-9-13 10:25:02");
			outMap.put("Amount", "-100");

			outList.add(outMap);

		} else {

			try {

				// 取得请求参数
				Map<String, Object> inputJsonMap = (Map<String, Object>) request
	                    .getAttribute("inputJsonMap");

				// 用户ID
				String userid = inputJsonMap.get("UserID").toString();
				// 返回数据行数
				int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
						.toString());
				// 当前页数 从1开始
				int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
						.toString());
				// 交易额类型
				int type = Integer
						.parseInt(inputJsonMap.get("Type").toString());
				// 交易排序
				int sort = Integer
						.parseInt(inputJsonMap.get("Sort").toString());

				// 根据用户ID查询我的钱包ID
				UserWalletModel userWalletModel = tradeRecordService
						.searchUserWallet(Integer.parseInt(userid), 2);

				if (userWalletModel != null) {
					// 获取的交易记录
					List<UserTradelogModel> userTradelogList = tradeRecordService
							.searchUserTradelog(userWalletModel.getWalletid(),
									pageSize, pageIndex, type, sort);

					if (userTradelogList != null && userTradelogList.size() > 0) {
						for (UserTradelogModel obj : userTradelogList) {

							Map<String, Object> userTradelogMap = new HashMap<String, Object>();

							userTradelogMap
									.put("ID", ShunJianMeiUtil.intToString(obj
											.getLogid()));
							OrderinfoModel orderinfoModel = tradeRecordService
									.searchOrderinfo(obj.getOrderid());
							if (orderinfoModel != null) {
								userTradelogMap.put("OrderCode",
										orderinfoModel.getOrdercode());
								userTradelogMap.put("AppointmentTime",
										orderinfoModel.getAppointmenttime()
												.substring(0, 10));
								userTradelogMap.put("PayTime", ShunJianMeiUtil
										.dateConvertString(orderinfoModel
												.getPaytime()));
							} else {
								userTradelogMap.put("OrderCode", "");
								userTradelogMap.put("AppointmentTime", "");
								userTradelogMap.put("PayTime", "");
							}

							userTradelogMap.put("Remark", obj.getRemark());
							userTradelogMap.put("Amount", ShunJianMeiUtil
									.floatToString(obj.getAmount()));
							outList.add(userTradelogMap);
						}

						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
						outJsonMap.put(Constant.DATA, outList);
						outJsonMap.put(Constant.TOTAL, userTradelogList.size());
					} else {
						// 无数据
						outJsonMap.put(Constant.CODE, Constant.CODE_2100);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
					}
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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
	@RequestMapping("H_BusinessAreaStoreList")
	/**
	 * 2.19	商圈商户列表
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_BusinessAreaStoreList(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put("ID", "2");
			outMap.put("Name", "木北造型(总部基地店)");
			outMap.put("Address", "丰台富丰路18号");

			outList.add(outMap);

		} else {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");
			try {
				// 获得参数
				// String userID = inputJsonMap.get("UserID").toString();
				String areaid = inputJsonMap.get("ID").toString();// 商圈ID 0全部

				List<StoreinfoModel> storeinfos = storeinfoService
						.searchStoreinfoByAreaid(areaid, 2);

				if (null != storeinfos && storeinfos.size() > 0) {

					for (StoreinfoModel o : storeinfos) {
						Map<String, Object> outMap = new HashMap<String, Object>();

						outMap.put("ID",
								ShunJianMeiUtil.intToString(o.getStoreid()));
						outMap.put("Name", o.getName());
						outMap.put("Address", o.getAddress());

						outList.add(outMap);
					}

					// 保存请求记录
					utilService.addBaseRequestlog(request.getRequestURI(),
							request.getAttribute("inputJsonInfo")
									.toString(), "",
							ShunJianMeiUtil.outputString(outJsonMap));

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, storeinfos.size());

				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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

		return outJsonMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("H_HairdresserSchedule")
	/**
	 * 2.20	获取指定日期日程
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_HairdresserSchedule(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put("Hour", "9");
			outMap.put("State", "0");

			List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();

			Map<String, String> outItmeMap = new HashMap<String, String>();
			outItmeMap.put("ID", "1");
			outItmeMap.put("Name", "木北造型（国贸店）");
			outItmeMap.put("Address", "丰台富丰路4号");
			itemList.add(outItmeMap);

			Map<String, String> outItmeMap2 = new HashMap<String, String>();
			outItmeMap2.put("ID", "1");
			outItmeMap2.put("Name", "木北造型（劲松店）");
			outItmeMap2.put("Address", "丰台富丰路5号");
			itemList.add(outItmeMap2);

			outMap.put("Item", itemList);

			outList.add(outMap);

		} else {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");
			try {
				// 获得参数
				String userID = inputJsonMap.get("UserID").toString();// 美发师id
				String wdDate = inputJsonMap.get("Date").toString();

				outList = userWorkplaceServices.addHairdresserSchedule(userID,
						wdDate);

				if (null != outList && outList.size() > 0) {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
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
	@RequestMapping("H_ScheduleOrderList")
	/**
	 * 2.22	获取指定日期预约信息
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_ScheduleOrderList(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put("OrderID", "2");
			outMap.put("OrderCode", "2015092410301224361");
			outMap.put("Name", "阿西");
			outMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			outMap.put("Mobile", "18512345678");
			outMap.put("AppointmentTime", "2015-9-21");
			outMap.put("State", "9");
			outMap.put("StateName", "已取消");
			outMap.put("StyleName", "女士长发");
			outMap.put("StoreName", "木北造型(国贸店)");
			outMap.put("StoreTel", "01056243153");
			outMap.put("Address", "长安街88号8门8单元801");
			outMap.put("Amount", "465");

			outList.add(outMap);

		} else {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");
			try {
				// 获得参数
				String userID = inputJsonMap.get("UserID").toString();// 美发师id
				String appointmenttime = inputJsonMap.get("Date").toString();

				outList = orderinfoService
						.searchOrderinfoByUseridAndAppointmenttime(userID,
								appointmenttime);
				if (outList == null || outList.size() == 0) {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
				} else {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 2);
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
	@RequestMapping("H_SaveHairdresserSchedule")
	/**
	 * 2.21保存指定日期日程
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_SaveHairdresserSchedule(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			String userID = inputJsonMap.get("UserID").toString();// 美发师id
			String wdDate = inputJsonMap.get("Date").toString();
			String addressID = inputJsonMap.get("Address").toString();

			Object itemObj = inputJsonMap.get("Item");
			List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemObj;

			if (userWorkplaceServices.addHairdresserSchedule(userID, wdDate,
					addressID, itemList)) {
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, "");
				outJsonMap.put(Constant.TOTAL, 1);
			} else {
				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_2);
			}

		} catch (BaseException e) {
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
	@RequestMapping("H_HairStyleState")
	/**
	 * 2.23	更新我的发型状态（定义）
	 * @param request
	 * @param response
	 * @param Infversion
	 */
	public void H_HairStyleState(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '1|操作成功',");
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
				// 用户ID
				// String userID = inputJsonMap.get("UserID").toString();
				// 发型ID
				int mystyleid = Integer.parseInt(inputJsonMap.get("ID")
						.toString());
				// 状态 1删除 2撤销 3发布
				int state = Integer.parseInt(inputJsonMap.get("State")
						.toString());
				UserMyhairstyleModel userMyhairstyleModel = userMyhairstyleService
						.searchUserMyhairstyle(mystyleid);
				if (userMyhairstyleModel != null) {
					int oldState = userMyhairstyleModel.getState();
					// 判断是否修改成功
					if (userMyhairstyleService.updateUserMyhairstyle(mystyleid,
							state, oldState)) {
						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_1);
						List<String> list = new ArrayList<String>();
						outJsonMap.put(Constant.DATA, list);
						outJsonMap.put(Constant.TOTAL, 1);
					} else {
						// 失败
						outJsonMap.put(Constant.CODE, Constant.CODE_2000);
						outJsonMap.put(Constant.MSG, Constant.MSG_2);
					}
				} else {
					// 失败
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_2);
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

	// add by jiazhaohui
	@SuppressWarnings("unchecked")
	@RequestMapping("H_GetMasterDefaultSetting")
	/**
	 * 4.1	获取美发师设置的默认信息
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_GetMasterDefaultSetting(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			String userID = inputJsonMap.get("UserID").toString();// 美发师id
			int userid = 0;
			userid = Integer.parseInt(userID);
			UsersubinfoModel usersubinfo = usersubinfoService
					.searchUsersubinfo(userid);
			if (usersubinfo != null) {
				String WorkDay = usersubinfo.getWorkday();
				String DayWorkHour = usersubinfo.getDayworkhour();
				String WorkShopID = usersubinfo.getStoreid();
				String WorkShop = usersubinfo.getStorename();
				String Address = usersubinfo.getStoreaddress();

				Map<String, Object> outMap = new HashMap<String, Object>();
				outMap.put("WorkDay", WorkDay);
				outMap.put("DayWorkHour", DayWorkHour);
				outMap.put("ID", WorkShopID);
				outMap.put("Name", WorkShop);
				outMap.put("Address", Address);
				outList.add(outMap);

				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 1);
			}
		} catch (BaseException e) {
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
	@RequestMapping("H_SetMasterDefaultSetting")
	/**
	 * 4.2	设置美发师的默认信息
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_SetMasterDefaultSetting(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			int userid = Integer
					.parseInt(inputJsonMap.get("UserID").toString());
			String WorkDay = inputJsonMap.get("WorkDay").toString();
			String DayWorkHour = inputJsonMap.get("DayWorkHour").toString();
			String WorkShopID = inputJsonMap.get("WorkShopID").toString();
			String WorkShop = inputJsonMap.get("WorkShop").toString();
			String Address = inputJsonMap.get("Address").toString();

			if (usersubinfoService.updateMasterDefautSetting(userid, WorkDay,
					DayWorkHour, WorkShopID, WorkShop, Address)) {
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 0);
			}
		} catch (BaseException e) {
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
	@RequestMapping("H_QuickSetting")
	/**
	 * 4.3	美发师一键设置
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_QuickSetting(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			String userID = inputJsonMap.get("UserID").toString();
			if (userWorkplaceServices.updateAllTimeWorking(userID)) {
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 0);
			}
		} catch (BaseException e) {
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
	@RequestMapping("H_GetDayWorking")
	/**
	 * 4.4	美发师获得某一个工作日的日程信息
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_GetDayWorking(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			String userID = inputJsonMap.get("UserID").toString();
			String year = inputJsonMap.get("Year").toString();
			String month = inputJsonMap.get("Month").toString();
			String day = inputJsonMap.get("Day").toString();

			outList = userEditWorkService.getDayWorking(userID, year, month,
					day);
			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_1);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, outList.size());
		} catch (BaseException e) {
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
	@RequestMapping("H_DeleteSelfWorking")
	/**
	 * 4.5	美发师删除一个自由编辑工作日程
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_DeleteSelfWorking(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			String userID = inputJsonMap.get("UserID").toString();
			String orderID = inputJsonMap.get("OrderID").toString();

			if (userEditWorkService.deleteEditWorking(userID, orderID)) {
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, outList.size());
			}

		} catch (BaseException e) {
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
	@RequestMapping("H_SaveSelfWorking")
	/**
	 * 4.5	美发师自定义工作日程，创建与更新接口
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_SaveSelfWorking(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			// 获得参数
			int orderID = userEditWorkService.addUserEditWork(inputJsonMap);
			if (orderID >= 0) {
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1);
				Map<String, Object> orderMap = new HashMap<String, Object>();
				orderMap.put("OrderID", String.valueOf(orderID));
				outList.add(orderMap);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, outList.size());
			} else {
				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, outList.size());
			}
		} catch (BaseException e) {
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
	@RequestMapping("H_GetSelfWorking")
	/**
	 * 4.5	美发师自定义工作日程，得到自定义订单接口
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_GetSelfWorking(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {

			String userID = inputJsonMap.get("UserID").toString();
			String orderID = inputJsonMap.get("OrderID").toString();
			// 获得参数
			outList = userEditWorkService.searchEditWork(userID, orderID);
			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_1);
			outJsonMap.put(Constant.DATA, outList);
			outJsonMap.put(Constant.TOTAL, outList.size());
		} catch (BaseException e) {
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

	// add by xujianhao
	@SuppressWarnings("unchecked")
	@RequestMapping("H_SetInformation")
	/**
	 * 4.6	美发师自由设置信息功能
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_SetInformation(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			String userID = inputJsonMap.get("UserID").toString();
			String picID = inputJsonMap.get("PicID").toString();
			String picID1 = inputJsonMap.get("PicID1").toString();
			String picID2 = inputJsonMap.get("PicID2").toString();
			String picID3 = inputJsonMap.get("PicID3").toString();
			String nickName = inputJsonMap.get("NickName").toString();
			String intro = inputJsonMap.get("Intro").toString();
			String hairstyle = inputJsonMap.get("Hairstyle").toString();
			String hairstyleDH = hairstyle.replace("|", ",");
			String hobbies = inputJsonMap.get("Hobbies").toString();
			String hobbiesDH = hobbies.replace("|", ",");
			String nationality = inputJsonMap.get("Nationality").toString();
			String language = inputJsonMap.get("Language").toString();
			int userid = Integer.parseInt(userID);

			UsersubinfoModel usersubinfo = usersubinfoService
					.searchUsersubinfo(userid);
			UserinfoModel userinfo = userinfoService.searchUserinfoById(userid);

			usersubinfo.setUserid(Integer.parseInt(userID));
			usersubinfo.setIntro(intro);
			usersubinfo.setHairstyle(hairstyleDH);
			usersubinfo.setHobbies(hobbiesDH);
			usersubinfo.setNationality(nationality);
			usersubinfo.setLanguage(language);

			userinfo.setUserid(Integer.parseInt(userID));
			userinfo.setNickname(nickName);

			// 执行美发师修改信息
			if ((usersubinfoService.updateUserSubInfoByUserId(usersubinfo))
					&& (userinfoService.updateUserInfoByUserId(userinfo))) {
				int id = userinfo.getUserid();
				usersubinfo = usersubinfoService.searchUsersubinfo(id);

				userinfo = userinfoService.searchUserinfoById(id);

				// 用户头像图
				UserPhotoMappingModel upmmTX = userPhotoMappingService
						.searchUserPhoto(userinfo.getUserid(), Constant.IMAGE_TYPE_01);

				// 更新user_photo_mapping 用户头像
				if (upmmTX != null) {
					upmmTX.setPicid(Integer.parseInt(picID));
					userPhotoMappingService.updateUserPhoto(upmmTX);
				} else {
					upmmTX = new UserPhotoMappingModel();
					upmmTX.setUserid(id);
					upmmTX.setPicid(Integer.parseInt(picID));
					upmmTX.setType(1);
					upmmTX.setRemark("");
					upmmTX.setSortnum(0);
					upmmTX.setCreatetime(new Date());
					// 添加到userPhotoMapping
					userPhotoMappingService.addUserPhoto(upmmTX);
				}

				// 用户形象图
				List<UserPhotoMappingModel> upmmXXList = userPhotoMappingService
						.searchUserPhotos(userinfo.getUserid(), Constant.IMAGE_TYPE_07);
				UserPhotoMappingModel upmmXX1 = null;
				int ListSize = 0;
				if (picID1!=null && !"".equals(picID1)) {
					if(upmmXXList==null){
					}else {
						ListSize = upmmXXList.size();
					}
					if(ListSize>0){
						// 更新user_photo_mapping 用户形象1
						upmmXX1 = upmmXXList.get(0);
						upmmXX1.setPicid(Integer.parseInt(picID1));
						userPhotoMappingService.updateUserPhoto(upmmXX1);
					}
					else {
						upmmXX1 = new UserPhotoMappingModel();
						upmmXX1.setUserid(id);
						upmmXX1.setPicid(Integer.parseInt(picID1));
						upmmXX1.setType(7);
						upmmXX1.setRemark("");
						upmmXX1.setSortnum(0);
						upmmXX1.setCreatetime(new Date());
						// 添加到userPhotoMapping
						userPhotoMappingService.addUserPhoto(upmmXX1);
					}
						
					
				}
				
				UserPhotoMappingModel upmmXX2 = null;
				if (picID2!=null && !"".equals(picID2)) {
					if(ListSize>1){
						if(upmmXXList==null){
						}else {
							ListSize = upmmXXList.size();
						}
						// 更新user_photo_mapping 用户形象2
						upmmXX2 = upmmXXList.get(1);
						upmmXX2.setPicid(Integer.parseInt(picID2));
						userPhotoMappingService.updateUserPhoto(upmmXX2);
					}
					 else {
						upmmXX2 = new UserPhotoMappingModel();
						upmmXX2.setUserid(id);
						upmmXX2.setPicid(Integer.parseInt(picID2));
						upmmXX2.setType(7);
						upmmXX2.setRemark("");
						upmmXX2.setSortnum(0);
						upmmXX2.setCreatetime(new Date());
						// 添加到userPhotoMapping
						userPhotoMappingService.addUserPhoto(upmmXX2);
					}
				}
				
				UserPhotoMappingModel upmmXX3 = null;
				if (picID3!=null && !"".equals(picID3)) {
					if(ListSize>2){
						if(upmmXXList==null){
						}else {
							ListSize = upmmXXList.size();
						}
						// 更新user_photo_mapping 用户形象3
						upmmXX3 = upmmXXList.get(2);
						upmmXX3.setPicid(Integer.parseInt(picID3));
						userPhotoMappingService.updateUserPhoto(upmmXX3);
					}
					else {
						upmmXX3 = new UserPhotoMappingModel();
						upmmXX3.setUserid(id);
						upmmXX3.setPicid(Integer.parseInt(picID3));
						upmmXX3.setType(7);
						upmmXX3.setRemark("");
						upmmXX3.setSortnum(0);
						upmmXX3.setCreatetime(new Date());
						// 添加到userPhotoMapping
						userPhotoMappingService.addUserPhoto(upmmXX3);
					}
				}
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 1);
			} else {
				// 无数据
				outJsonMap.put(Constant.CODE, Constant.CODE_2000);
				outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "操作失败");
				outJsonMap.put(Constant.TOTAL, 1);
			}

		} catch (BaseException e) {
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
		// ShunJianMeiUtil.outputJson(request, response, outJsonMap);
		return outJsonMap;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping("H_FinishOrderList")
	/**
	 * 2.14我的接单列表
	 * @param request
	 * @param response
	 */
	public @ResponseBody
	Map<String, Object> H_FinishOrderList(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, String>> outList = new ArrayList<Map<String, String>>();

		if (Integer.parseInt(interfaceStatus) == 1) {

			Map<String, String> orderinfoMap = new HashMap<String, String>();
			orderinfoMap.put("OrderID", "10");
			orderinfoMap.put("OrderCode", "2015092410301224365");
			orderinfoMap.put("Name", "阿东客户");
			orderinfoMap.put("Photo",
					"http://ykssdservice.ruitei.com/Resource/Photo/123.png");
			// orderinfoMap.put("LevelName", "发型总监");
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

				outList = orderinfoService.searchFinishOrderListByUserID(
						userID, state, sort, pageIndex, pageSize);

				if (null != outList) {

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
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
	@RequestMapping("H_GetInformation")
	/**
	 * 4.10	美发师获得个人信息
	 * @param request
	 * @param response
	 */
	public void H_GetInformation(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, String>> outList = new ArrayList<Map<String, String>>();

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request
                .getAttribute("inputJsonMap");
		try {
			String userID = inputJsonMap.get("UserID").toString();
			Map<String, String> masterinfo = new HashMap<String, String>();
			int id = Integer.parseInt(userID);

			UsersubinfoModel usersubinfo = usersubinfoService
					.searchUsersubinfo(id);
			UserinfoModel userinfo = userinfoService.searchUserinfoById(id);

			String intro = usersubinfo.getIntro();
			masterinfo.put("Intro", intro);
			// 用,隔开的
			String hairstyleDH = usersubinfo.getHairstyle();
			// 用|隔开
			String hairstyle = hairstyleDH.replace(",", "|");
			masterinfo.put("Hairstyle", hairstyle);
			// 用,隔开的
			String hobbiesDH = usersubinfo.getHobbies();
			// 用|隔开
			String hobbies = hobbiesDH.replace(",", "|");
			masterinfo.put("Hobbies", hobbies);
			String nationality = usersubinfo.getNationality();
			masterinfo.put("Nationality", nationality);
			String language = usersubinfo.getLanguage();
			masterinfo.put("Language", language);

			String nickName = userinfo.getNickname();
			masterinfo.put("NickName", nickName);

			// 用户头像图
			UserPhotoMappingModel upmmTX = userPhotoMappingService
					.searchUserPhoto(userinfo.getUserid(), Constant.IMAGE_TYPE_01);
			if (upmmTX == null) {
				masterinfo.put("PicID", "");
				masterinfo.put("PicturePath", "");

			} else {
				int picidInt = upmmTX.getPicid();
				String picid = ShunJianMeiUtil.intToString(picidInt);
				masterinfo.put("PicID", picid);

				BasePictureModel basePictureModel = basePitureService
						.searchBasePictureByID(picidInt);
				String picturepath = basePictureModel.getPicturepath();
				masterinfo.put("PicturePath", ipHost + picturepath);
			}

			// 用户形象图
			List<UserPhotoMappingModel> upmmXXList = userPhotoMappingService
					.searchUserPhotos(userinfo.getUserid(), Constant.IMAGE_TYPE_07);
			
			for (int i = 0; i < Constant.MAX_PIC; i++) {
				if (upmmXXList!=null&&i < upmmXXList.size()) {
					UserPhotoMappingModel upmmXX1 = upmmXXList.get(i);
					int picid1Int = upmmXX1.getPicid();
					String picid1 = ShunJianMeiUtil.intToString(picid1Int);
					masterinfo.put("PicID" + (i + 1), picid1);

					BasePictureModel basePictureModel1 = basePitureService
							.searchBasePictureByID(picid1Int);
					String picturepath1 = basePictureModel1.getPicturepath();

					masterinfo.put("PicturePath" + (i + 1), ipHost
							+ picturepath1);
				} else {
					masterinfo.put("PicID" + (i + 1), "");
					masterinfo.put("PicturePath" + (i + 1), "");
				}

			}
			outList.add(masterinfo);

			outJsonMap.put(Constant.CODE, Constant.CODE_1000);
			outJsonMap.put(Constant.MSG, Constant.MSG_0);
			outJsonMap.put(Constant.DATA, outList);
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

	@ResponseBody
	@RequestMapping(value = "H_SearchUserHairpackedByhairpackedId", produces = "text/html;charset=UTF-8")
	public String H_SearchUserHairpackedByhairpackedId(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JSONObject json = new JSONObject();
		try {
			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");
			String hairpackedid = inputJsonMap.get("hairpackedid").toString();
			json = userHairpackedService.searchUserHairpackedByid(hairpackedid);
		} catch (BaseException e) {
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			json.put(Constant.CODE, Constant.CODE_1200);
			json.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}
		// 保存请求记录,这个地方可以使用AOP对控制层进行拦截，获取返回值，进行统一保存，没必要每个方法中写一遍
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(json));
		model.addAllAttributes(json);
		return "test";
	}

	/* lnn end */

	// 通过美发师id获取当前美发师下面所有的套餐
	@RequestMapping(value = "GetAllusersubinfoPackedBysubid")
	public String getAllusersubinfoPackedBysubid(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> pricesMap = new HashMap<String, String>();
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
                    .getAttribute("inputJsonMap");
			String id = inputJsonMap.get("id").toString();

			json = this.userHairpackedService
					.searchUserHairpackedByHairdresserid(id);
		} catch (Exception e) {
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			json.put(Constant.CODE, Constant.CODE_1200);
			json.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(json));
		try {
			response.getWriter().write(json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("goPacked")
	public String goPacked(HttpServletRequest request,
			HttpServletResponse response, Model model, String storeid,
			String page, String rows) {
		return "storeinfo_taocanguanli";
	}

}
