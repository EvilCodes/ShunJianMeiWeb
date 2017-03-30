package com.wenyuankeji.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BaseDistrictModel;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.OrderinfoSearchModel;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.model.UserHairpackedModel;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.model.UserQuicklogSearchModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.model.UserTradelogSearchModel;
import com.wenyuankeji.spring.model.UserTradelogSearchTXModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IBaseBusinessareaService;
import com.wenyuankeji.spring.service.IBaseCityService;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IBaseDistrictService;
import com.wenyuankeji.spring.service.IBasePictureService;
import com.wenyuankeji.spring.service.IBaseProvinceService;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.service.IStorePhotoMappingService;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.service.IUserCashrecordService;
import com.wenyuankeji.spring.service.IUserHairpackedService;
import com.wenyuankeji.spring.service.IUserHairstyleService;
import com.wenyuankeji.spring.service.IUserPhotoMappingService;
import com.wenyuankeji.spring.service.IUserProfessionLevelService;
import com.wenyuankeji.spring.service.IUserQuickpayService;
import com.wenyuankeji.spring.service.IUserTradeLogService;
import com.wenyuankeji.spring.service.IUserWalletService;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ImgCompressUtil;
import com.wenyuankeji.spring.util.MD5Util;
import com.wenyuankeji.spring.util.PropertiesTools;

@Controller
public class StoreinfoController {

	@Autowired
	private IStoreinfoService storeinfoService;

	@Autowired
	private IOrderinfoService orderinfoService;

	@Autowired
	private IUserHairstyleService userHairstyleService;

	@Autowired
	private IUsersubinfoService usersubinfoService;

	@Autowired
	private IUserProfessionLevelService userProfessionLevelService;

	@Autowired
	private IUserPhotoMappingService userPhotoMappingService;

	@Autowired
	private IBaseProvinceService baseProvinceService;

	@Autowired
	private IBaseCityService baseCityService;

	@Autowired
	private IBaseDistrictService baseDistrictService;

	@Autowired
	private IBaseBusinessareaService baseBusinessareaService;

	@Autowired
	private IBasePictureService basePictureService;

	@Autowired
	private IStorePhotoMappingService storePhotoMappingService;

	@Autowired
	private IUtilService utilService;

	@Autowired
	private IBaseConfigService baseConfigService;

	@Autowired
	private IUserWalletService userWalletService;

	@Autowired
	private IUserCashrecordService userCashrecordService;

	@Autowired
	private IUserTradeLogService userTradeLogService;
	@Autowired
	private IUserQuickpayService userQuickpayService;
	@Autowired
	private IUserHairpackedService userHairpackedService;
	
	@RequestMapping("pcLogOut/{pageType}")
	/**
	 * 跳转美发店登录
	 * @param request
	 * @param model
	 * @return
	 */
	public String pcLogOut(HttpServletRequest request,
			@PathVariable int pageType) {

		request.getSession().removeAttribute("userId");
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("password");
		request.getSession().removeAttribute("storeinfo");
		request.getSession().removeAttribute("Login");

		if (1 == pageType) {
			// 美发师
			return "personal";
		} else {
			return "myStore";
		}

	}

	@RequestMapping("/getBaseProvinces")
	/**
	 * 查询所有省份
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getBaseProvinces(Model model,
			HttpSession session) throws BaseException {
		Map<String, Object> baseProvinceMap = new HashMap<String, Object>();
		List<BaseProvinceModel> baseProvinceList = baseProvinceService
				.searchBaseProvince();
		// if (baseProvinceList != null && baseProvinceList.size() > 0) {
		BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
		baseProvinceModel.setProvinceid(0);
		baseProvinceModel.setProvincename("请选择");
		baseProvinceList.add(0, baseProvinceModel);

		baseProvinceMap.put("selInfo", true);
		baseProvinceMap.put("baseProvinceList", baseProvinceList);
		// }
		return baseProvinceMap;
	}

	@RequestMapping("/getBaseBusinessareas/{city_id}")
	/**
	 * 查根据城市ID询商圈
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getBaseBusinessareas(
			@PathVariable int city_id, Model model, HttpSession session)
			throws BaseException {
		List<BaseBusinessareaModel> baseBusinessareaList = baseBusinessareaService
				.searchBaseBusinessarea(city_id);

		Map<String, Object> baseBusinessareaMap = new HashMap<String, Object>();
		// if (baseBusinessareaList != null && baseBusinessareaList.size() > 0)
		// {
		BaseBusinessareaModel baseBusinessareaModel = new BaseBusinessareaModel();
		baseBusinessareaModel.setAreaid(0);
		baseBusinessareaModel.setAreaname("请选择");
		baseBusinessareaList.add(0, baseBusinessareaModel);

		baseBusinessareaMap.put("selInfo", true);
		baseBusinessareaMap.put("baseBusinessareaList", baseBusinessareaList);
		// }
		return baseBusinessareaMap;
	}

	@RequestMapping("/getBaseCitys/{province_id}")
	/**
	 * 根据省份ID查询城市
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getProfessions(
			@PathVariable int province_id, Model model, HttpSession session)
			throws BaseException {
		List<BaseCityModel> baseCityList = baseCityService
				.searchBaseCityByProvinceId(province_id);

		Map<String, Object> baseCityMap = new HashMap<String, Object>();
		// if (baseCityList != null && baseCityList.size() > 0) {
		BaseCityModel baseCityModel = new BaseCityModel();
		baseCityModel.setCityid(0);
		baseCityModel.setCityname("请选择");
		baseCityList.add(0, baseCityModel);

		baseCityMap.put("selInfo", true);
		baseCityMap.put("baseCityList", baseCityList);
		// }
		return baseCityMap;
	}

	@RequestMapping("/getBaseDistricts/{city_id}")
	/**
	 * 根据城市ID查询区县
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> getBaseDistricts(
			@PathVariable int city_id, Model model, HttpSession session)
			throws BaseException {
		List<BaseDistrictModel> baseDistrictList = baseDistrictService
				.searchBaseDistrictByCityId(city_id);
		Map<String, Object> baseDistrictMap = new HashMap<String, Object>();
		// if (baseDistrictList != null && baseDistrictList.size() > 0) {
		BaseDistrictModel baseDistrictModel = new BaseDistrictModel();
		baseDistrictModel.setCityid(0);
		baseDistrictModel.setDistrictname("请选择");
		baseDistrictList.add(0, baseDistrictModel);

		baseDistrictMap.put("selInfo", true);
		baseDistrictMap.put("baseDistrictList", baseDistrictList);
		// }
		return baseDistrictMap;
	}

	@RequestMapping("StoreinfoLogin")
	/**
	 * 美发店登录
	 * @param request
	 * @param model
	 * @param loginName
	 * @param userPwd
	 * @return
	 */
	public @ResponseBody Map<String, Object> StoreinfoLogin(
			HttpServletRequest request, Model model, String loginName,
			String userPwd) throws BaseException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		StoreinfoModel storeinfo = storeinfoService.searchStoreinfoLogin(
				loginName, MD5Util.Encryption(userPwd));
		if (storeinfo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("storeinfo", storeinfo);
			session.setAttribute("Login", true);
			jsonMap.put("editInfo", true);
			jsonMap.put("storeinfoState", storeinfo.getState());
		} else {
			jsonMap.put("editInfo", false);
			jsonMap.put("message", "用户名密码错误！");
		}
		return jsonMap;
	}

	@RequestMapping("goStoreinfoValidateInfo")
	/**
	 * 跳转美发店验证信息
	 * @param request
	 * @param model
	 * @return
	 */
	public String goStoreinfoValidateInfo(HttpServletRequest request,
			Model model) throws NumberFormatException, BaseException {
		HttpSession session = request.getSession();
		StoreinfoModel storeinfo = (StoreinfoModel) session
				.getAttribute("storeinfo");

		setDropdownList(storeinfo, model);

		model.addAttribute("storeinfo", storeinfo);

		return "storeinfo_validateinfo";
	}

	/**
	 * 设置下拉列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public void setDropdownList(StoreinfoModel storeinfo, Model model)
			throws BaseException {

		// 省份
		List<BaseProvinceModel> baseProvinceList = baseProvinceService
				.searchBaseProvince();
		BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
		baseProvinceModel.setProvinceid(0);
		baseProvinceModel.setProvincename("请选择");
		baseProvinceList.add(0, baseProvinceModel);

		model.addAttribute("baseProvinceList", baseProvinceList);
		model.addAttribute("provinceid", storeinfo.getProvinceid());

		// 城市
		List<BaseCityModel> baseCityList = baseCityService
				.searchBaseCityByProvinceId(storeinfo.getProvinceid());

		BaseCityModel baseCityModel = new BaseCityModel();
		baseCityModel.setCityid(0);
		baseCityModel.setCityname("请选择");
		baseCityList.add(0, baseCityModel);

		model.addAttribute("baseCityList", baseCityList);
		model.addAttribute("cityid", storeinfo.getCityid());

		// 区县
		List<BaseDistrictModel> baseDistrictList = baseDistrictService
				.searchBaseDistrictByCityId(storeinfo.getCityid());
		BaseDistrictModel baseDistrictModel = new BaseDistrictModel();
		baseDistrictModel.setCityid(0);
		baseDistrictModel.setDistrictname("请选择");
		baseDistrictList.add(0, baseDistrictModel);

		model.addAttribute("baseDistrictList", baseDistrictList);
		model.addAttribute("districtid", storeinfo.getDistrictid());

		// 商圈
		List<BaseBusinessareaModel> baseBusinessareaList = baseBusinessareaService
				.searchBaseBusinessarea(storeinfo.getCityid());
		BaseBusinessareaModel baseBusinessareaModel = new BaseBusinessareaModel();
		baseBusinessareaModel.setAreaid(0);
		baseBusinessareaModel.setAreaname("请选择");
		baseBusinessareaList.add(0, baseBusinessareaModel);

		model.addAttribute("baseBusinessareaList", baseBusinessareaList);
		model.addAttribute("areaid", storeinfo.getAreaid());

		// 营业时间
		String businesshours = storeinfo.getBusinesshours();
		if (businesshours != null && businesshours != "") {
			String businesshoursStr[] = storeinfo.getBusinesshours().split("-");
			String businesshoursStart = businesshoursStr[0].toString();
			String businesshoursEnd = businesshoursStr[1].toString();
			// 开始营业时间
			model.addAttribute("businesshoursStart", businesshoursStart);
			// 结束营业时间
			model.addAttribute("businesshoursEnd", businesshoursEnd);
		} else {
			// 默认开始营业时间
			model.addAttribute("businesshoursStart", "09:00");
			// 默认营业时间
			model.addAttribute("businesshoursEnd", "09:00");
		}

		StorePhotoMappingModel storePhotoMapping1 = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 3);
		StorePhotoMappingModel storePhotoMapping2 = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 1);
		StorePhotoMappingModel storePhotoMapping3 = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 2);

		if (storePhotoMapping1 != null) {
			model.addAttribute("fileName1", storePhotoMapping1.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("fileName1", "images/defaultAvatar.png");
		}

		if (storePhotoMapping2 != null) {
			model.addAttribute("fileName2", storePhotoMapping2.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("fileName2", "images/defaultAvatar.png");
		}
		if (storePhotoMapping3 != null) {
			model.addAttribute("fileName3", storePhotoMapping3.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("fileName3", "images/defaultAvatar.png");
		}
	}

	@RequestMapping("/StoreinfoValidateInfo")
	/**
	 * 美发店验证信息
	 * @param request
	 * @param model
	 * @param storeName 商户名称
	 * @param provinceid 省份ID
	 * @param cityid 城市ID
	 * @param districtid 区ID
	 * @param address 地址
	 * @param tel 电话
	 * @param carnumber 车位数
	 * @param businesshours 营业时间
	 * @param areaid 商圈ID
	 * @param empiricalmode 经营方式
	 * @param intro 简介
	 * @param bossname 负责人名字
	 * @param bossmobile 负责人手机
	 * @param storemanagername 店长名字
	 * @param storemanagermobile 店长手机
	 * @return
	 */
	public String StoreinfoValidateInfo(HttpServletRequest request,
			Model model, String storeName, String provinceid, String cityid,
			String districtid, String address, String tel, String carnumber,
			String businesshoursStart, String businesshoursEnd, String areaid,
			String empiricalmode, String intro, String bossname,
			String bossmobile, String storemanagername,
			String storemanagermobile, String longitude, String latitude,
			String ownername, String bank, String cardnumber, String fileName1,
			String fileName2, String fileName3, String hidSaveOrOk)
			throws BaseException, IOException {

		HttpSession session = request.getSession();
		String storeId = String.valueOf(((StoreinfoModel) session
				.getAttribute("storeinfo")).getStoreid());
		String serverPath = request.getServletContext()
				.getRealPath("/userImg/");

		if (!"images/defaultAvatar.png".equals(fileName1)) {
			// 保存商户经营执照
			if (!storePhotoMappingService.addOrUpdImg(
					Integer.parseInt(storeId), 3, fileName1, serverPath)) {
				model.addAttribute("message", "商户经营执照,更新失败");
			}
		}

		if (!"images/defaultAvatar.png".equals(fileName2)) {
			// 保存商户正面照
			if (!storePhotoMappingService.addOrUpdImg(
					Integer.parseInt(storeId), 1, fileName2, serverPath)) {
				model.addAttribute("message", "商户正面照,更新失败");
			}
		}

		if (!"images/defaultAvatar.png".equals(fileName3)) {
			// 保存店内照
			if (!storePhotoMappingService.addOrUpdImg(
					Integer.parseInt(storeId), 2, fileName3, serverPath)) {
				model.addAttribute("message", "店内照,更新失败");
			}
		}

		// 添加店铺
		StoreinfoModel storeinfo = new StoreinfoModel();
		storeinfo.setStoreid(Integer.parseInt(storeId));
		storeinfo.setName(storeName);
		storeinfo.setProvinceid(Integer.parseInt(provinceid));
		storeinfo.setCityid(Integer.parseInt(cityid));
		storeinfo.setDistrictid(Integer.parseInt(districtid));
		storeinfo.setAddress(address);
		storeinfo.setTel(tel);
		if (carnumber.equals("")) {
			storeinfo.setCarnumber(0);
		} else {
			storeinfo.setCarnumber(Integer.parseInt(carnumber));
		}
		storeinfo.setBusinesshours(businesshoursStart + "-" + businesshoursEnd);
		storeinfo.setAreaid(Integer.parseInt(areaid));
		storeinfo.setEmpiricalmode(Integer.parseInt(empiricalmode));
		storeinfo.setIntro(intro);
		storeinfo.setBossname(bossname);
		storeinfo.setBossmobile(bossmobile);
		storeinfo.setStoremanagername(storemanagername);
		storeinfo.setStoremanagermobile(storemanagermobile);
		storeinfo.setLatitude(latitude);
		storeinfo.setLongitude(longitude);
		storeinfo.setOwnername(ownername);
		storeinfo.setBank(bank);
		storeinfo.setCardnumber(cardnumber);

		if (hidSaveOrOk.equals("1")) {
			storeinfo.setState(1);
		}

		// 美发店信息更新
		if (storeinfoService.updateStoreinfo(storeinfo)) {

			// 设置下拉列表
			setDropdownList(storeinfo, model);

			model.addAttribute("storeinfo", storeinfo);
			if (hidSaveOrOk == "1") {
				model.addAttribute("message", "更新成功，等待审核中!");
			} else {
				model.addAttribute("message", "保存成功!");
			}
		} else {
			if (hidSaveOrOk == "1") {
				model.addAttribute("message", "更新失败!");
			} else {
				model.addAttribute("message", "保存失败!");
			}
		}
		return "storeinfo_validateinfo";
	}
	//美发店图片上传
	@RequestMapping("UploadImages")
	public void UploadImages(HttpServletRequest request,
			@RequestParam("fileName") CommonsMultipartFile file,
			String fileType, Model model) throws IOException {
		HttpSession session = request.getSession();
		String path = "";
		if (!file.isEmpty()) {

			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().indexOf("."));

			Map<String, String> fileTypeMap = new HashMap<String, String>();
			fileTypeMap.put(".jpg", ".jpg");
			fileTypeMap.put(".jepg", ".jepg");
			fileTypeMap.put(".png", ".png");

			if (fileTypeMap.get(type) == null) {
				model.addAttribute("errorMsg", "上传的文件格式错误！");
				return;
			}

			// 5MB
			long fileSize = 5242880;

			if (file.getSize() > fileSize) {
				model.addAttribute("errorMsg", "上传的文件不能大于5MB！");
				return;
			}

			try {
				String fileName = System.currentTimeMillis() + type;
				String tempPath = request.getServletContext().getRealPath(
						"/userImg/");
				
				// 2016年8月23日15:51:03
				/*String tempPath = PropertiesTools.getValue("imageSavePath", "config.properties")+"/userImg/";
				System.out.println("tempPath:"+tempPath);*/
				
				File pathFile = new File(tempPath);
				if (!pathFile.exists()) {
					pathFile.mkdir();
				}
				path = tempPath + "/" + fileName;
				File localFile = new File(path);
				// 写文件到本地
				file.transferTo(localFile);
				session.setAttribute(fileType, "/userImg/" + fileName);
			} catch (Exception e) {
				model.addAttribute("errorMsg", "上传失败！");
			}
		}
		// return jsonMap;
	}
	//美发店店面和店内照上传，设置了图片480*320
	@RequestMapping("storeUploadImages")
	public void storeUploadImages(HttpServletRequest request,
			@RequestParam("fileName") CommonsMultipartFile file,
			String fileType, Model model) throws IOException {
		HttpSession session = request.getSession();
		String path = "";
		if (!file.isEmpty()) {

			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().indexOf("."));

			Map<String, String> fileTypeMap = new HashMap<String, String>();
			fileTypeMap.put(".jpg", ".jpg");
			fileTypeMap.put(".jepg", ".jepg");
			fileTypeMap.put(".png", ".png");
			if (fileTypeMap.get(type) == null) {
				model.addAttribute("errorMsg", "上传的文件格式错误！");
				return;
			}
			// 5MB
			long fileSize = 5242880;

			if (file.getSize() > fileSize) {
				model.addAttribute("errorMsg", "上传的文件不能大于5MB！");
				return;
			}

			try {
				String fileName = System.currentTimeMillis() + type;
				String tempPath = request.getServletContext().getRealPath(
						"/userImg/");
				
				// 2016年8月23日15:51:03
				/*String tempPath = PropertiesTools.getValue("imageSavePath", "config.properties")+"/userImg/";
				System.out.println("tempPath2:"+tempPath);*/
				
				File pathFile = new File(tempPath);
				if (!pathFile.exists()) {
					pathFile.mkdir();
				}
				path = tempPath + "/" + fileName;
//				File localFile = new File(path);
				// 写文件到本地
//				file.transferTo(localFile);
				InputStream is=file.getInputStream();
				try {
					ImgCompressUtil util = new ImgCompressUtil(is);
					util.getImgFreedom(480, 320, path);
				} finally{
					is.close();
				}
				session.setAttribute(fileType, "/userImg/" + fileName);
			} catch (Exception e) {
				model.addAttribute("errorMsg", "上传失败！");
			}
		}
		// return jsonMap;
	}
	
	@RequestMapping("getSessionImageId")
	/*
	 * 获取session里的图片ID
	 */
	public @ResponseBody Map<String, Object> getSessionImageId(
			HttpServletRequest request, Model model, String fileType) {
		Map<String, Object> fileNameMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String fileName = (String) session.getAttribute(fileType);
		if (fileName != null && fileName.length() > 0) {
			fileNameMap.put("info", true);
			fileNameMap.put("fileName", fileName);
		} else {
			fileNameMap.put("info", false);
			fileNameMap.put("msg", "上传文件不能大于5M！");
		}

		return fileNameMap;
	}

	@RequestMapping("goOrderinfoManage")
	/**
	 * 跳转美发店订单管理
	 * @param request
	 * @param model
	 * @param storeid
	 * @param paystate
	 * @param createtime
	 * @return
	 */
	public String goOrderinfoManage(HttpServletRequest request, Model model) {

		return "orderinfo_manage";
	}

	@RequestMapping("OrderinfoManage")
	/**
	 * 订单详细列表
	 * @param request
	 * @param model
	 * @param storeid 店铺ID
	 * @param paystate 支付类型
	 * @param createtime 创建时间
	 * @param page 当前页
	 * @param rows 每页显示条数
	 * @return
	 */
	public @ResponseBody Map<String, Object> OrderinfoManage(
			HttpServletRequest request, Model model, String page, String rows)
			throws BaseException {

		HttpSession session = request.getSession();
		StoreinfoModel sessionStoreinfo = (StoreinfoModel) session
				.getAttribute("storeinfo");

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "20"
				: rows);
		List<OrderinfoModel> orderinfoList = orderinfoService
				.searchOrderinfoStoreId(sessionStoreinfo.getStoreid(), intPage,
						intRows);
		List<OrderinfoSearchModel> orderinfoSearchList = new ArrayList<OrderinfoSearchModel>();

		for (int i = 0; i < orderinfoList.size(); i++) {
			OrderinfoModel orderinfo = orderinfoList.get(i);
			OrderinfoSearchModel orderinfoSearch = new OrderinfoSearchModel();

			if (orderinfo.getUserid() != 0 && orderinfo.getStoreid() != 0 && orderinfo.getUserid() != 0) {
				UserHairstyleModel userHairstyle = userHairstyleService
						.searchUserHairstyleById(orderinfo.getStyleid());
				UsersubinfoModel usersubinfo = usersubinfoService
						.searchUsersubinfo(orderinfo.getUserid());
				StoreinfoModel storeinfo = storeinfoService
						.searchStoreinfo(orderinfo.getStoreid());

				if (usersubinfo != null) {
					UserProfessionLevelModel userProfessionLevel = userProfessionLevelService
							.searchUserProfessionLevel(usersubinfo.getLevelid());
					UserPhotoMappingModel userPhotoMapping = userPhotoMappingService
							.searchUserPhoto(usersubinfo.getUserid(), 2);

					if (userProfessionLevel != null) {
						orderinfoSearch
								.setUser_profession_level_name(userProfessionLevel
										.getLevelname());
					}
					if (userPhotoMapping != null) {
						orderinfoSearch.setUsersubinfo_pic(userPhotoMapping
								.getBasePicture().getPicturepath());
					}
				}

				orderinfoSearch.setOrderid(orderinfo.getOrderid());
				if (userHairstyle != null) {
					orderinfoSearch.setUser_hairstyle_name(userHairstyle
							.getName());
				}
				if (usersubinfo != null) {
					orderinfoSearch.setUsersubinfo_name(usersubinfo
							.getTruename());
					orderinfoSearch.setUsersubinfo_contactmobile(usersubinfo
							.getContactmobile());
				}

				orderinfoSearch.setAppointmenttime(orderinfo.getAppointmenttime());

				if (storeinfo != null) {
					orderinfoSearch.setStoreinfo_name(storeinfo.getName());
					orderinfoSearch.setStoreinfo_tel(storeinfo.getTel());
					orderinfoSearch
							.setStoreinfo_address(storeinfo.getAddress());
				}

				orderinfoSearch.setAmount(Float.parseFloat(String
						.valueOf(orderinfo.getAmount())));

				orderinfoSearch.setPaystate(orderinfo.getPaystate());
				orderinfoSearch.setIshairpacked(orderinfo.getIshairpacked());
			}
			orderinfoSearchList.add(orderinfoSearch);
		}
		// 存放总记录数，必须的
		jsonMap.put("total", orderinfoService
				.searchOrderinfoStoreId(sessionStoreinfo.getStoreid()));
		// rows键 存放每页记录 list
		jsonMap.put("rows", orderinfoSearchList);
		return jsonMap;
	}

	@RequestMapping("goOrderinfoDetail/{orderid}/{type}")
	/**
	 * 跳转美发店订单详细
	 * @param request
	 * @param model
	 * @param storeid
	 * @param paystate
	 * @param createtime
	 * @return
	 */
	public String goOrderinfoDetail(@PathVariable int orderid,
			@PathVariable int type, HttpServletRequest request, Model model)
			throws BaseException {
		// 订单信息
		OrderinfoModel orderinfo = orderinfoService.searchOrderinfo(orderid);
		// 美发师
		UsersubinfoModel usersubinfo = usersubinfoService
				.searchUsersubinfo(orderinfo.getUserid());
		UserPhotoMappingModel userPhotoMapping = userPhotoMappingService
				.searchUserPhoto(usersubinfo.getUserid(), 1);
		// 店铺信息
		StoreinfoModel storeinfo = storeinfoService.searchStoreinfo(orderinfo
				.getStoreid());
		StorePhotoMappingModel storePhotoMapping = storePhotoMappingService
				.searchStorePhoto(storeinfo.getStoreid(), 1);
		// 发型信息
		UserHairstyleModel userHairstyle = userHairstyleService
				.searchUserHairstyleById(orderinfo.getStyleid());
		// 下一步状态
		String nowStatus = Constant.PAYSTATE_MAP.get(orderinfo.getPaystate());
		// 下一步状态
		String nextStatus = Constant.NEXT_STEP_MAP.get(orderinfo.getPaystate());

		model.addAttribute("orderinfo", orderinfo);

		model.addAttribute("usersubinfo", usersubinfo);

		if (null != userPhotoMapping
				&& null != userPhotoMapping.getBasePicture()) {
			model.addAttribute("userPhoto", userPhotoMapping.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("userPhoto", "");
		}

		if (null != usersubinfo.getUserProfessionLevel()) {
			model.addAttribute("userLevel", usersubinfo
					.getUserProfessionLevel().getLevelname());
		} else {
			model.addAttribute("userLevel", "");
		}

		model.addAttribute("storeinfo", storeinfo);
		if (null != storePhotoMapping
				&& null != storePhotoMapping.getBasePicture()) {
			model.addAttribute("storePhoto", storePhotoMapping.getBasePicture()
					.getPicturepath());
		} else {
			model.addAttribute("storePhoto", "");
		}

		model.addAttribute("userHairstyle", userHairstyle.getName());
		model.addAttribute("nowStatus", nowStatus);
		model.addAttribute("nextStatus", nextStatus);

		if (type == 0) {
			return "orderinfo_detail";
		} else {
			return "storeinfo_richengguanli_orderinfo_detail";
		}
	}

	@RequestMapping("goRiZhiGuanLi")
	/**
	 * 跳转日志管理画面
	 * @param request
	 * @param model
	 * @return
	 */
	public String goRiZhiGuanLi(HttpServletRequest request, Model model) {

		SimpleDateFormat sdf = new SimpleDateFormat("M-dd");

		// 获取后七日
		for (int i = 1; i <= 7; i++) {

			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, i);
			model.addAttribute("td" + i, sdf.format(c.getTime()));
		}

		// 获取后周
		for (int i = 8; i <= 14; i++) {

			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, i);
			model.addAttribute("td" + i, sdf.format(c.getTime()));
		}

		return "storeinfo_richengguanli";
	}

	@RequestMapping("doAddRiZhiGuanLi")
	/**
	 * 设置日程
	 * @param request
	 * @param riqiType 1:后七天 2：后周
	 * @param commitType 1:全部可预约  2：取消预约
	 * @param storeId 店铺id
	 * @param chairNum 座位数
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> doAddRiZhiGuanLi(
			HttpServletRequest request, String swdDate, int commitType,
			int storeId, int chairNum, Model model) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");

		// List<String> gongzuotiList = new ArrayList<String>();

		// if (riqiType == 1) {
		// // 后七天
		// for (int i = 1; i <= 7; i++) {
		//
		// Calendar c = Calendar.getInstance();
		// c.add(Calendar.DAY_OF_MONTH, i);
		// gongzuotiList.add(sdf.format(c.getTime()));
		// }
		// } else {
		// for (int i = 8; i <= 14; i++) {
		//
		// Calendar c = Calendar.getInstance();
		// c.add(Calendar.DAY_OF_MONTH, i);
		// gongzuotiList.add(sdf.format(c.getTime()));
		// }
		// }

		try {

			outJsonMap = storeinfoService.addStoreWorkdate(storeId, commitType,
					chairNum, swdDate);

		} catch (BaseException e) {

			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(), "", e.getMessage());

			outJsonMap.put("info", false);
			outJsonMap.put("msg", "服务器异常code:" + exceptionId);
		}

		return outJsonMap;
	}

	@RequestMapping("goStoreinfoWodeyue")
	/**
	 * 跳转美发店我的余额
	 * @param request
	 * @param model
	 * @return
	 */
	public String goStoreinfoWodeyue(HttpServletRequest request, Model model)
			throws BaseException {

		HttpSession session = request.getSession();
		// 店铺
		StoreinfoModel storeinfo = (StoreinfoModel) session
				.getAttribute("storeinfo");
		// 店铺账户余额
		UserWalletModel userWallet = userWalletService.searchUserWallet(
				storeinfo.getStoreid(), 3);
		// 店铺押金
		BaseConfigModel baseConfig = baseConfigService
				.searchBaseConfigByCode("storecashfund");
		float jine = 0;
		float yajin = Float.parseFloat(baseConfig.getConfigvalue());
		if (userWallet != null) {
			jine = userWallet.getBalance();
			yajin = jine - Float.parseFloat(baseConfig.getConfigvalue());
		}
		// float yajin = jine - Float.parseFloat(baseConfig.getConfigvalue());
		// 店铺订单集合
		/*List<OrderinfoModel> orderinfoList = orderinfoService
				.searchOrderinfoByStoreId(storeinfo.getStoreid(), 0, 1);
				
		model.addAttribute("orderinfoList", orderinfoList);
				*/

		model.addAttribute("storeinfo", storeinfo);
		model.addAttribute("balance",
				userWallet != null ? userWallet.getBalance() : "0");
		model.addAttribute("yajin", yajin);

		return "storeinfo_wodeyue";
	}

	@RequestMapping("getOrderInfoByID")
	public @ResponseBody Map<String, Object> getOrderInfoByID(
			HttpServletRequest request, int orderType, int orderVal,
			String page, String rows) {
		Map<String, Object> outmaMap = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		// 店铺
		StoreinfoModel storeinfo = (StoreinfoModel) session.getAttribute("storeinfo");
		try {
			// 当前页
			int intPage = Integer.parseInt((page == null || page == "0") ? "1"
					: page);
			// 每页显示条数
			int intRows = Integer.parseInt((rows == null || rows == "0") ? "20"
					: rows);
			List<UserTradelogSearchModel> orderinfoList = orderinfoService
					.searchOrderinfoByStoreId(storeinfo.getStoreid(), orderType,
							orderVal, intPage, intRows);
			// 存放总记录数，必须的
			outmaMap.put("total", orderinfoService
					.searchOrderinfoByStoreId(storeinfo.getStoreid(), orderType,
							orderVal));
			// rows键 存放每页记录 list
			outmaMap.put("rows", orderinfoList);
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "",
					e.getMessage());
		}
		return outmaMap;
	}
	

	@RequestMapping("getStoreinfoTixian")
	public @ResponseBody Map<String, Object> getStoreinfoTixian(
			HttpServletRequest request,
			String page, String rows) {
		Map<String, Object> outmaMap = new HashMap<String, Object>();

		HttpSession session = request.getSession();
		// 店铺
		StoreinfoModel storeinfo = (StoreinfoModel) session.getAttribute("storeinfo");
		try {
			// 当前页
			int intPage = Integer.parseInt((page == null || page == "0") ? "1"
					: page);
			// 每页显示条数
			int intRows = Integer.parseInt((rows == null || rows == "0") ? "20"
					: rows);
			
			// 根据店铺ID查询店铺钱包钱包ID
			UserWalletModel userWalletModel = userWalletService
					.searchUserWallet(storeinfo.getStoreid(), 3);
			
			List<UserTradelogSearchTXModel> orderinfoList = userTradeLogService
					.searchUserTradelogTX(userWalletModel.getWalletid(), intPage, intRows);
			// 存放总记录数，必须的
			outmaMap.put("total", userTradeLogService
					.searchUserTradelogTX(storeinfo.getStoreid()));
			// rows键 存放每页记录 list
			outmaMap.put("rows", orderinfoList);
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "",
					e.getMessage());
		}
		return outmaMap;
	}

	@RequestMapping("goStoreinfoTixian")
	/**
	 * 跳转提现
	 * @param request
	 * @param model
	 * @return
	 */
	public String goStoreinfoTixian(HttpServletRequest request, Model model)
			throws BaseException {

		HttpSession session = request.getSession();
		// 店铺
		StoreinfoModel storeinfo = (StoreinfoModel) session
				.getAttribute("storeinfo");
		// 店铺账户余额
		UserWalletModel userWallet = userWalletService.searchUserWallet(
				storeinfo.getStoreid(), 3);
		// 店铺押金
		BaseConfigModel baseConfig = baseConfigService
				.searchBaseConfigByCode("storecashfund");

		float jine = 0;
		float yajin = Float.parseFloat(baseConfig.getConfigvalue());
		if (userWallet != null) {
			jine = userWallet.getBalance();
			yajin = jine - Float.parseFloat(baseConfig.getConfigvalue());
		}

		model.addAttribute("balance",
				userWallet != null ? userWallet.getBalance() : "0");
		model.addAttribute("yajin", yajin);
		
		model.addAttribute("cardnumber", storeinfo.getCardnumber());
		model.addAttribute("bank", storeinfo.getBank());
		model.addAttribute("ownername", storeinfo.getOwnername());
		
		return "storeinfo_tixian";
	}

	@RequestMapping("StoreinfoTixian")
	/**
	 * 跳转提现
	 * @param request
	 * @param model
	 * @return
	 */
	public @ResponseBody Map<String, Object> StoreinfoTixian(
			HttpServletRequest request, Model model, String amount,
			String carNumber, String userName, String bank)
			throws BaseException {
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		// 店铺
		StoreinfoModel storeinfo = (StoreinfoModel) session
				.getAttribute("storeinfo");
		// 店铺账户余额
		UserWalletModel userWallet = userWalletService.searchUserWallet(
				storeinfo.getStoreid(), 3);
		// 计算提现后的金额
		float balance = 0;

		if (null != userWallet) {
			balance = userWallet.getBalance() - Float.parseFloat(amount);
		}

		UserCashrecordModel userCashrecord = new UserCashrecordModel();
		userCashrecord.setUserid(storeinfo.getStoreid());
		userCashrecord.setUsername(storeinfo.getOwnername());
		userCashrecord.setBank(storeinfo.getBank());
		userCashrecord.setCardnumber(storeinfo.getCardnumber());
		userCashrecord.setState(1);
		userCashrecord.setAmount(Double.parseDouble(amount));
		userCashrecord.setCreatetime(new Date());
		// 执行提现并修改店铺的金额
		if (userCashrecordService.addUserCashrecordEditBalance(userCashrecord,
				balance, storeinfo.getStoreid(), 3, userWallet)) {
			jsonMap.put("editInfo", true);
		} else {
			jsonMap.put("editInfo", false);
		}
		return jsonMap;
	}

	@RequestMapping("getOrderinfoBySwdid")
	/**
	 * 根据店铺工作日以及
	 * @param request
	 * @param swdDate
	 * @param storeId
	 * @return
	 */
	public @ResponseBody Map<String, Object> getOrderinfoBySwdid(
			HttpServletRequest request, String swdDate, String storeId) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		try {

			// 查询店铺工作日
			StoreWorkdateModel storeWorkdate = storeinfoService
					.searchStoreWorkdate(swdDate, storeId);
			if (null != storeWorkdate) {
				// 查询订单
				List<OrderinfoModel> orderinfoList = orderinfoService
						.searchOrderinfoByWdid(storeWorkdate.getWdid());

				if (null != orderinfoList && orderinfoList.size() > 0) {
					outJsonMap.put("info", true);
					outJsonMap.put("data", orderinfoList);
				} else {
					outJsonMap.put("info", false);
					outJsonMap.put("msg", "没有订单信息！");
				}

			} else {
				outJsonMap.put("info", false);
				outJsonMap.put("msg", "没有订单信息！");
			}

		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "",
					e.getMessage());
		}
		return outJsonMap;
	}

	@RequestMapping("getGongWeiByGongZuoRi")
	public @ResponseBody Map<String, Object> getGongWeiByGongZuoRi(
			HttpServletRequest request, String swdDate, String storeId) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 查询工位
			List<Object[]> objects = storeinfoService.searchGongWeiByGongZuoRi(
					swdDate, storeId);
			if (null != objects && objects.size() > 0) {

				List<Map<String, String>> gongweiList = new ArrayList<Map<String, String>>();

				for (Object[] o : objects) {
					Map<String, String> gongweiMap = new HashMap<String, String>();
					gongweiMap.put("hour", String.valueOf(o[0]));
					gongweiMap.put("chairidCount", String.valueOf(o[1]));
					gongweiMap.put("orderid", String.valueOf(o[2]));
					gongweiList.add(gongweiMap);
				}
				outJsonMap.put("info", true);
				outJsonMap.put("data", gongweiList);
				outJsonMap.put("wdid", objects.get(0)[3]);
			} else {
				outJsonMap.put("info", false);
			}

		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "",
					e.getMessage());
		}

		return outJsonMap;
	}

	@RequestMapping("doUpdateStoreWorkhour")
	public @ResponseBody Map<String, Object> doUpdateStoreWorkhour(
			HttpServletRequest request, int wdid, int type, int chairNum,
			int hour) {

		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			if (storeinfoService
					.updateStoreWorkhour(wdid, type, chairNum, hour)) {
				outJsonMap.put("info", true);
			} else {
				outJsonMap.put("info", false);
			}

		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "",
					e.getMessage());
		}

		return outJsonMap;

	}
	
	@RequestMapping("getUserQuickpay")
	public @ResponseBody Map<String, Object> getUserQuickpay(
			HttpServletRequest request,String page, String rows) {
		Map<String, Object> outmaMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		// 店铺
		StoreinfoModel storeinfo = (StoreinfoModel) session.getAttribute("storeinfo");
		try {
			// 当前页
			int intPage = Integer.parseInt((page == null || page == "0") ? "1"
					: page);
			// 每页显示条数
			int intRows = Integer.parseInt((rows == null || rows == "0") ? "20"
					: rows);
			List<UserQuicklogSearchModel> userQuickpayList = userQuickpayService
					.searchUserQuick(storeinfo.getStoreid(),intPage,intRows);
			
			// 存放总记录数，必须的
			outmaMap.put("total", userQuickpayService
					.searchUserQuickTotalByStoreID(storeinfo.getStoreid()));
			// rows键 存放每页记录 list
			outmaMap.put("rows", userQuickpayList);
		} catch (Exception e) {
			// 保存异常信息
						utilService.addBaseException(request.getRequestURI(), "",
								e.getMessage());
		}
		return outmaMap;
	}
	
	// add by jiazhaohui
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "H_SearchUserHairpackedByStoreid", produces = "text/html;charset=UTF-8")
	public String searchUserHairpackedByStoreid(HttpServletRequest request,
			HttpServletResponse response, String page, String rows) {
		Map<String, String> pricesMap = new HashMap<String, String>();
		JSONObject json = new JSONObject();
		HttpSession session = request.getSession();
		StoreinfoModel sessionStoreinfo = (StoreinfoModel) session
				.getAttribute("storeinfo");
		String storeid = sessionStoreinfo.getStoreid() + "";
		List<UserHairpackedModel> list = new ArrayList<UserHairpackedModel>();
		try {
			if (storeid == null || storeid.isEmpty()) {
				json.put(Constant.CODE, Constant.CODE_1102);
				json.put(Constant.MSG, Constant.MSG_ERROR);
			} else {
				if ("".equals(page) || page == null) {
					page = "1";
				}
				if ("".equals(rows) || rows == null) {
					rows = "10";
				}
				list = userHairpackedService
						.searchUserHairpackedByStoreidList(storeid,
								Integer.parseInt(page), Integer.parseInt(rows));
			}
		} catch (BaseException e) {
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getSession().getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			json.put(Constant.CODE, Constant.CODE_1200);
			json.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}
		Map jsonMap = new HashMap();
		int packedLength = this.userHairpackedService.getPackedLength(storeid);
		// 存放总记录数，必须的
		jsonMap.put("total", packedLength);
		// rows键 存放每页记录 list
		jsonMap.put("rows", list);
		return JSONObject.toJSONString(jsonMap);
	}
	
	@RequestMapping("goAddPacked")
	public String goAddPacked(HttpServletRequest request,
			HttpServletResponse response) {
		// 根据店id查询全部美发师
		HttpSession session = request.getSession();
		StoreinfoModel sessionStoreinfo = (StoreinfoModel) session
				.getAttribute("storeinfo");
		if (sessionStoreinfo != null) {
			try {
				List<UsersubinfoModel> list = this.usersubinfoService
						.searchSubUserinfoByStoreId(sessionStoreinfo
								.getStoreid());
				request.setAttribute("usersubinfoList", list);
				request.setAttribute("storeid", sessionStoreinfo.getStoreid());
				// 告诉前台这是add
				request.setAttribute("pagetype", "add");
			} catch (BaseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.sendRedirect("/shunjianmeiweb/goMyStore");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "storeinfo_taocanadorup";
	}
	
	/* lnn */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "H_AddUserHairpacked", produces = "text/json;charset=UTF-8")
	public String addUserHairpacked(HttpServletRequest request,
			HttpServletResponse response, Model model, String jsonPack) {
		Map<String, String> pricesMap = new HashMap<String, String>();
		JSONObject json = new JSONObject();
		try {
			// 取得请求参数
			// inputJsonMap = (Map<String, Object>)
			// request.getSession().getAttribute("inputJsonMap");
			JSONObject parseObject = JSONObject.parseObject(jsonPack);
			Integer storeid = Integer.parseInt(parseObject.get("storeid")
					.toString());
			String name = parseObject.get("name").toString();
			String intro = parseObject.get("intro").toString();
			String hairdresserids = parseObject.get("hairdresserids")
					.toString();
			String prices = parseObject.get("prices").toString();
			Integer times = Integer.parseInt(parseObject.get("times")
					.toString());
			Integer style = Integer.parseInt(parseObject.get("style")
					.toString());

			List<String> hairdresseridList = new ArrayList<String>();
			if (!StringUtils.isEmpty(hairdresserids)) {
				hairdresseridList = Arrays.asList(hairdresserids.split("_"));
			}
			// 校验数据
			// 拼装prices
			String[] hairStyles = prices.split("_");
			for (String str : hairStyles) {
				String[] hairStyle = str.split("-");
				pricesMap.put(hairStyle[0], hairStyle[1]);
			}
			json = userHairpackedService.addUserHairpacked(storeid, name,
					intro, times, style, hairdresseridList, pricesMap);
		} catch (BaseException e) {

		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("modifyHairPacked")
	/**
	 * 修改套餐上下架
	 * @param request
	 * @param response
	 * @return
	 */
	public String modifyHairPacked(HttpServletRequest request,
			HttpServletResponse response, String jsonString) {
		JSONObject parseObject = JSONObject.parseObject(jsonString);
		String packed_id = parseObject.getString("packid");// 套餐id
		String packed_state = parseObject.getString("state");// 套餐状态
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.DATA, "");
		map.put(Constant.MSG, "");
		try {
			if (this.userHairpackedService.updateHairPackedForType(packed_id,
					packed_state)) {
				map.put(Constant.CODE, Constant.CODE_1000);
			} else {
				map.put(Constant.CODE, Constant.CODE_1200);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(map);
	}
	
	/**
	 * 异步处理美发师与套餐关联
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "modifyMyHairPacked", produces = "text/json;charset=UTF-8")
	public String modifyMyHairPacked(HttpServletRequest request,
			HttpServletResponse response, String jsonString) {
		JSONObject parseObject = JSONObject.parseObject(jsonString);
		String packed_id = parseObject.getString("hairpackedid");// 套餐id
		String opration = parseObject.getString("operation");// 操作类型 1添加 2减少
		String user_id = parseObject.getString("userid");// 用户id

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.DATA, "");
		map.put(Constant.MSG, "");
		try {
			if (this.userHairpackedService.updateHairPackedForUserId(packed_id,
					opration, user_id)) {
				map.put(Constant.CODE, Constant.CODE_1000);
			} else {
				map.put(Constant.CODE, Constant.CODE_1200);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BaseException e) {
			e.printStackTrace();
		}

		return JSONObject.toJSONString(map);
	}
	
	@RequestMapping("goUpdatePacked")
	public String goUpdatePacked(HttpServletRequest request,
			HttpServletResponse response, String hairpackid) {
		// 根据店id查询全部美发师
		HttpSession session = request.getSession();
		StoreinfoModel sessionStoreinfo = (StoreinfoModel) session
				.getAttribute("storeinfo");
		if (sessionStoreinfo != null) {
			try {
				List<UsersubinfoModel> list = this.usersubinfoService
						.searchSubUserinfoByStoreId(sessionStoreinfo
								.getStoreid());
				request.setAttribute("usersubinfoList", list);
				request.setAttribute("storeid", sessionStoreinfo.getStoreid());
				// 告诉前台这是upadte
				request.setAttribute("pagetype", "update");
				JSONObject searchUserHairpackedByid = this.userHairpackedService
						.searchUserHairpackedByid(hairpackid);
				request.setAttribute("data",
						searchUserHairpackedByid.getJSONObject("data"));// 前台显示的数据
			} catch (BaseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.sendRedirect("/shunjianmeiweb/goMyStore");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "storeinfo_taocanadorup";
	}
}