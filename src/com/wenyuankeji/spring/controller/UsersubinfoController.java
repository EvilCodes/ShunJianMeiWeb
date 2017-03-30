package com.wenyuankeji.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.model.BaseStarinfoModel;
import com.wenyuankeji.spring.model.BaseVerificationcodeModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.model.UsersubsearchModel;
import com.wenyuankeji.spring.service.IBaseCityService;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IBaseProvinceService;
import com.wenyuankeji.spring.service.IBaseStarInfoService;
import com.wenyuankeji.spring.service.IBaseVerificationcodeService;
import com.wenyuankeji.spring.service.IUserPhotoMappingService;
import com.wenyuankeji.spring.service.IUserWalletService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ImgCompressUtil;
import com.wenyuankeji.spring.util.MD5Util;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Controller
public class UsersubinfoController {

	@Autowired
	private IUsersubinfoService usersubinfoService;

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IBaseProvinceService baseProvinceService;

	@Autowired
	private IBaseCityService baseCityService;

	@Autowired
	private IBaseStarInfoService baseStarInfoService;

	@Autowired
	private IUtilService utilService;

	@Autowired
	private IBaseVerificationcodeService baseVerificationcodeService;

	@Autowired
	private IUserPhotoMappingService userPhotoMappingService;

	@Autowired
	private IBaseConfigService baseConfigService;

	@Autowired
	private IUserWalletService userWalletService;
	

	@RequestMapping("UsersubinfoLogin")
	/**
	 * 美发师登录
	 * 
	 * @param request
	 * @param model
	 * @param loginName
	 * @param userPwd
	 * @return
	 */
	public String UsersubinfoLogin(HttpServletRequest request, Model model, String loginName, String userPwd) {
		if (loginName != "" && userPwd != "") {
			return "usersubinfo_register";
		} else {
			return "index";
		}

	}

	@RequestMapping("UsersubinfoRegister")
	/**
	 * 美发师注册
	 * 
	 * @param request
	 * @param model
	 * @param loginName
	 * @param userPwd
	 * @return
	 */
	public String UsersubinfoRegister(HttpServletRequest request, Model model, String mobile, String verification,
			String userPwd, String password) {
		if (mobile != "" && verification != "") {
			return "usersubinfo_register";
		} else {
			return "index";
		}

	}

	@RequestMapping("doUserSubInfoLogin")
	/**
	 * 美发师登录
	 * 
	 * @param request
	 * @param model
	 * @param loginName
	 * @param userPwd
	 * @return
	 */
	public @ResponseBody Map<String, Object> doUserSubInfoLogin(HttpServletRequest request, Model model,
			String loginName, String password) {
		UserinfoModel userInfo = null;
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("info", false);
		try {
			userInfo = userinfoService.searchUserinfo(loginName, MD5Util.Encryption(password),2);
		} catch (BaseException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("userName", loginName);
		session.setAttribute("password", MD5Util.Encryption(password));
		if (null != userInfo) {
			outMap.put("info", true);
			session.setAttribute("userId", userInfo.getUserid());
			session.setAttribute("Login", true);
		}
		return outMap;
	}

	@RequestMapping("goUsersubinfoRegister")
	/**
	 * 跳转美发师注册
	 * 
	 * @param model
	 * @return
	 */
	public String goUsersubinfoRegister(HttpServletRequest request, Model model) {

		return "usersubinfo_register";
	}

	@RequestMapping("doUsersubinfoRegister")
	/**
	 * 新用户注册
	 * 
	 * @param request
	 * @param model
	 * @param bindphone
	 * @param password
	 * @param verificationCode
	 * @return
	 */
	public @ResponseBody Map<String, Object> doUsersubinfoRegister(HttpServletRequest request, Model model,
			String bindphone, String password, String verificationCode) {

		Map<String, Object> outMap = new HashMap<String, Object>();

		try {
			// 判断验证码
			BaseVerificationcodeModel baseVerificationcodeModel = baseVerificationcodeService
					.searchBaseVerificationcode(bindphone);
			if (null == baseVerificationcodeModel || !(verificationCode.equals(baseVerificationcodeModel.getCode()))) {

				outMap.put("info", false);
				outMap.put("msg", "验证码错误");
				return outMap;
			}
			Date date = new Date();
			UserinfoModel userInfo = new UserinfoModel();
			userInfo.setUsername(bindphone);
			userInfo.setPassword(MD5Util.Encryption(password));
			userInfo.setBindphone(bindphone);
			userInfo.setUsertype(2);
			userInfo.setUserstate(1);
			userInfo.setCreatetime(date);
			userInfo.setUpdatetime(date);

			int userid = userinfoService.addUserInfo(userInfo);
			if (userid > 0) {
				outMap.put("info", true);
			} else {
				outMap.put("info", false);
				outMap.put("msg", "注册失败！");
			}
			request.getSession().setAttribute("userId", userid);
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return outMap;
	}

	@RequestMapping("goUsersubinfo_verification")
	/**
	 * 跳转美发师完善信息
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUsersubinfo_verification(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId").toString();
		try {
			UserinfoModel userInfoModel = userinfoService.searchUserinfoById(Integer.parseInt(userId));
			UsersubinfoModel userSubInfoModel = usersubinfoService.searchUserSubInfoByUserId(Integer.parseInt(userId));
			model.addAttribute("userInfoModel", userInfoModel);
			model.addAttribute("userSubInfoModel", userSubInfoModel);
			model.addAttribute("errorMsg", "");

			List<BaseProvinceModel> baseProvinceList = new ArrayList<BaseProvinceModel>();
			List<BaseCityModel> baseCityModelList = new ArrayList<BaseCityModel>();

			//省份
			baseProvinceList = baseProvinceService.searchBaseProvince();
			//省份默认
			BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
			baseProvinceModel.setProvinceid(0);
			baseProvinceModel.setProvincename("请选择");
			baseProvinceList.add(0, baseProvinceModel);
			//城市
			if (userInfoModel.getProvinceid() == 0) {
				BaseCityModel baseCityModel = new BaseCityModel();
				baseCityModel.setCityid(0);
				baseCityModel.setCityname("请选择");
				baseCityModelList.add(0,baseCityModel);
			}else {
				baseCityModelList = baseCityService.searchBaseCityByProvinceId(userInfoModel.getProvinceid());
			}
			
			UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(Integer.parseInt(userId));
			
			//身份证正面
			UserPhotoMappingModel  userPhotoMappingModel2 = userPhotoMappingService.searchUserPhoto(userInfoModelSession.getUserid(), 2);
			if(null != userPhotoMappingModel2 && userPhotoMappingModel2.getBasePicture() != null){
				String path = userPhotoMappingModel2.getBasePicture().getPicturepath();
				String imgName = path.substring(8);
				model.addAttribute("imgFileName_zm",  path);
				model.addAttribute("hidfileName_zm", imgName);
			}else{
				model.addAttribute("imgFileName_zm",  "images/defaultAvatar.png");
				
			}
			
			//身份证背面
			UserPhotoMappingModel  userPhotoMappingModel3 = userPhotoMappingService.searchUserPhoto(userInfoModelSession.getUserid(), 3);
			if(null != userPhotoMappingModel3 && userPhotoMappingModel3.getBasePicture() != null){
				String path = userPhotoMappingModel3.getBasePicture().getPicturepath();
				String imgName = path.substring(8);
				model.addAttribute("imgFileName_bm",  path);
				model.addAttribute("hidfileName_bm", imgName);
			}else{
				model.addAttribute("imgFileName_bm",  "images/defaultAvatar.png");
				
			}
			
			//手持身份证
			UserPhotoMappingModel  userPhotoMappingModel4 = userPhotoMappingService.searchUserPhoto(userInfoModelSession.getUserid(), 4);
			if(null != userPhotoMappingModel4 && userPhotoMappingModel4.getBasePicture() != null){
				String path = userPhotoMappingModel4.getBasePicture().getPicturepath();
				String imgName = path.substring(8);
				model.addAttribute("imgFileName_sc",  path);
				model.addAttribute("hidfileName_sc", imgName);
			}else{
				model.addAttribute("imgFileName_sc",  "images/defaultAvatar.png");
				
			}
			
			model.addAttribute("baseProvinceList", baseProvinceList);
			model.addAttribute("baseCityModelList", baseCityModelList);
		} catch (BaseException e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return "usersubinfo_verification";
	}
	
	@RequestMapping("UploadUserInfoImages")
	public void UploadUserInfoImages(HttpServletRequest request,
			@RequestParam("fileName") CommonsMultipartFile file, String fileType,
			Model model) throws IOException {
		HttpSession session = request.getSession();
		String path = "";
		if (!file.isEmpty()) {

			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().indexOf("."));

			Map<String, String> fileTypeMap = new HashMap<String, String>();
			fileTypeMap.put(".jpg", ".jpg");
			fileTypeMap.put(".jepg", ".jepg");
			fileTypeMap.put(".png", ".png");
			fileTypeMap.put(".doc", ".doc");

			if (fileTypeMap.get(type) == null) {
				model.addAttribute("errorMsg", "上传的文件格式错误！");
				return;
			}
			
			//5MB
			long fileSize = 5242880;
			
			if(file.getSize() > fileSize){ 
				model.addAttribute("errorMsg", "上传的文件不能大于5MB！");
				return;
			}
			try {
//				//保存文件并压缩，存储的文件地址在config文件中配置
//				ImgCompressUtil compressUtil=new ImgCompressUtil(file.getInputStream());
//				//成功后返回图片存储的路径
//				session.setAttribute(fileType, compressUtil.getImg128());
				
				String fileName = System.currentTimeMillis() + type;
				String tempPath = request.getServletContext().getRealPath("/userImg/");
				File pathFile = new File(tempPath);
				if (!pathFile.exists()) {
					pathFile.mkdir();
				}
				path = tempPath + "/" + fileName;
				File localFile = new File(path);
//				fileName_tx
				
				//保存文件并压缩，存储的文件地址在config文件中配置
				ImgCompressUtil compressUtil=new ImgCompressUtil(file.getInputStream());
				if(fileType.equals("fileName_tx")){//头像
					//compressUtil.getImgFreedom(480,220,path);
					compressUtil.getImgFreedom(128,128,path);
				}else if(fileType.equals("fileName_xx1")){
					compressUtil.getImgFreedom(480,220,path);
				}else{
					compressUtil.getImgFreedom(480,320,path);
				}
//				 写文件到本地
//				file.transferTo(localFile);
				session.setAttribute(fileType, fileName);
			} catch (Exception e) {
				model.addAttribute("errorMsg", "上传失败！");
			}
		}
	}
	
	
	@RequestMapping("getSessionUserInfoImageId")
	/*
	 * 获取session里的图片ID
	 */
	public @ResponseBody Map<String, Object> getSessionUserInfoImageId(HttpServletRequest request, Model model, String fileType) {
		Map<String, Object> fileNameMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String fileName = (String)session.getAttribute(fileType);
		if(fileName != null &&  fileName.length() > 0){
			fileNameMap.put("info", true);
			fileNameMap.put("fileName", fileName);
		}else {
			fileNameMap.put("info", false);
			fileNameMap.put("msg", "上传文件不能大于5M");
		}
		
		return fileNameMap;
	}

	@RequestMapping("doSaveUserSubInfo")
	public String doSaveUserSubInfo(HttpServletRequest request, Model model, String truename, String email,int sex,
			String nationality,String language,int provinceid, int cityid, String address, String household, String contact, String relationship,
			String contactmobile, String idcard,int buttonFlag, String fileName_zm,String fileName_bm,String fileName_sc)
					throws BaseException, IOException {

		HttpSession session = request.getSession();
		String result = "";
		try {
			//美发师扩展信息
			UsersubinfoModel userSubInfo = new UsersubinfoModel();
			userSubInfo.setTruename(truename);
			userSubInfo.setEmail(email);
			//美发师的国籍
			userSubInfo.setNationality(nationality);
			//美发师的语言
			userSubInfo.setLanguage(language);
			//测试
			userSubInfo.setAddress(address);
			userSubInfo.setHousehold(household);
			userSubInfo.setContact(contact);
			userSubInfo.setContactmobile(contactmobile);
			userSubInfo.setRelationship(relationship);
			userSubInfo.setIdcard(idcard);
			userSubInfo.setCreatetime(new Date());
	
			userSubInfo.setLevelid(1);// 资深发型师
			BaseStarinfoModel baseStarinfoModel = new BaseStarinfoModel();
			baseStarinfoModel.setStarid(1);
			userSubInfo.setBaseStarinfoModel(baseStarinfoModel);
			userSubInfo.setStarid(1);// 摩羯座
	
			UserinfoModel userInfoModel = new UserinfoModel();
			userInfoModel.setProvinceid(provinceid);
			userInfoModel.setCityid(cityid);
			int userId = 0;
			//判断session的用户是否为空
			if (null != session.getAttribute("userId")) {
				userId = Integer.parseInt(session.getAttribute("userId").toString());
				userSubInfo.setUserid(userId);
				userSubInfo.setCheckstate(0);//审核状态：未提交审核
				userInfoModel.setUserid(userId);
				UsersubinfoModel userSubInfoTemp = usersubinfoService.searchUserSubInfoByUserId(userId);
				UserinfoModel userInfoTemp = userinfoService.searchUserinfoById(userId);
				
				saveImage(fileName_zm,userId,2,0,request);//保存身份证正面
				saveImage(fileName_bm,userId,3,0,request);//保存身份证背面
				saveImage(fileName_sc,userId,4,0,request);//保存手持身份证
				
				//判断用户是否为新增用户，否则执行修改
				if (0 == userSubInfoTemp.getUserid()) {
					// 信息不存在，新增美发师扩展信息
					// add by jiazhaohui
					// 默认的美发师等级是五星
					userSubInfo.setScore(Constant.MAX_EVAL_STAR);
					int addResult = usersubinfoService.addUserSubInfo(userSubInfo);
					if (addResult > 0) {
						userInfoTemp.setSex(sex);
						userInfoTemp.setProvinceid(provinceid);
						userInfoTemp.setCityid(cityid);
						userInfoTemp.setUpdatetime(new Date());
						if (userinfoService.updateUserInfoByUserId(userInfoTemp)) {
							if(buttonFlag == 0){
								result = "usersubinfo_verification";
							}else{
								result = "usersubinfo_information";
							}
							model.addAttribute("errorMsg", "保存成功！");
						} else {
							result = "usersubinfo_verification";
							model.addAttribute("errorMsg", "保存失败！");
						}
						
					} else {
						// 新增失败
						result = "error";
						model.addAttribute("errorMsg", "保存失败！");
					}
				} else {
					// 信息存在，更新美发师扩展信息
					userSubInfoTemp.setTruename(truename);
					userSubInfoTemp.setEmail(email);
					userSubInfoTemp.setNationality(nationality);
					userSubInfoTemp.setLanguage(language);
					userSubInfoTemp.setAddress(address);
					userSubInfoTemp.setHousehold(household);
					userSubInfoTemp.setContact(contact);
					userSubInfoTemp.setContactmobile(contactmobile);
					userSubInfoTemp.setRelationship(relationship);
					userSubInfoTemp.setIdcard(idcard);
					
					if(userSubInfoTemp.getCheckstate() != 1 && userSubInfoTemp.getCheckstate() != 2){
						userSubInfoTemp.setCheckstate(0);//审核状态：未提交审核
					}
					
					boolean updateResult = usersubinfoService.updateUserSubInfoByUserId(userSubInfoTemp);
					if (updateResult) {
						userInfoTemp.setSex(sex);
						// 更新成功
						// 更新userinfo表里的省市信息
						userInfoTemp.setProvinceid(provinceid);
						userInfoTemp.setCityid(cityid);
						userInfoTemp.setUpdatetime(new Date());
						if (userinfoService.updateUserInfoByUserId(userInfoTemp)) {
							if(buttonFlag == 0){
								result = "usersubinfo_verification";
							}else{
								result = "usersubinfo_information";
							}
							model.addAttribute("errorMsg", "保存成功！");
						} else {
							result = "usersubinfo_verification";
							model.addAttribute("errorMsg", "保存失败！");
						}
					} else {
						// 更新失败
						result = "error";
						model.addAttribute("errorMsg", "保存失败！");
					}
				}
				
				if(buttonFlag == 0){
					//跳转第一页
					model.addAttribute("userInfoModel", userInfoTemp);
					model.addAttribute("userSubInfoModel", userSubInfo);
					List<BaseProvinceModel> baseProvinceList = new ArrayList<BaseProvinceModel>();
					List<BaseCityModel> baseCityModelList = new ArrayList<BaseCityModel>();
					
					//省份
					baseProvinceList = baseProvinceService.searchBaseProvince();
					BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
					baseProvinceModel.setProvinceid(0);
					baseProvinceModel.setProvincename("请选择");
					baseProvinceList.add(0, baseProvinceModel);
					//城市
					if (userInfoModel.getProvinceid() == 0) {
						BaseCityModel baseCityModel = new BaseCityModel();
						baseCityModel.setCityid(0);
						baseCityModel.setCityname("请选择");
						baseCityModelList.add(0,baseCityModel);
					}else {
						baseCityModelList = baseCityService.searchBaseCityByProvinceId(userInfoModel.getProvinceid());
					}
					model.addAttribute("baseProvinceList", baseProvinceList);
					model.addAttribute("baseCityModelList", baseCityModelList);
					
					
					setImageAttribute(userInfoTemp.getUserid(), 2, "imgFileName_zm","hidfileName_zm","defaultAvatar.png", model, request); //设置身份证正面到Attribute
					setImageAttribute(userInfoTemp.getUserid(), 3, "imgFileName_bm","hidfileName_bm","defaultAvatar.png", model, request); //设置身份证背面到Attribute
					setImageAttribute(userInfoTemp.getUserid(), 4, "imgFileName_sc","hidfileName_sc","defaultAvatar.png", model, request); //设置手持身份证到Attribute
					
				}else{
					
					UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
					UsersubinfoModel userSubInfoModelSession = usersubinfoService.searchUserSubInfoByUserId(userId);

					List<BaseStarinfoModel> baseStarInfoList = new ArrayList<BaseStarinfoModel>();

					baseStarInfoList = baseStarInfoService.searchBaseStarInfo();
					BaseStarinfoModel baseStarinfoModelSession = new BaseStarinfoModel();
					baseStarinfoModelSession.setStarid(0);
					baseStarinfoModelSession.setStarname("请选择");
					baseStarInfoList.add(0, baseStarinfoModelSession);
					
					List<BaseConfigModel> hobbyList = baseConfigService.searchBaseConfig("hobby");
					List<BaseConfigModel> hairstyleList = baseConfigService.searchBaseConfig("hairstyle");

					model.addAttribute("hobbyList", hobbyList);
					model.addAttribute("hairstyleList", hairstyleList);

					model.addAttribute("baseStarInfoList", baseStarInfoList);
					model.addAttribute("userInfoModel", userInfoModelSession);
					model.addAttribute("userSubInfoModel", userSubInfoModelSession);
					model.addAttribute("errorMsg", "");
					
					
					//页面中显示对应的图片
					setImageAttribute(userInfoModelSession.getUserid(), 1, "userPhoto","hidFileName_tx", "defaultAvatar.png",model, request); //设置我的头像到Attribute
					setImageAttribute(userInfoModelSession.getUserid(), 7, "userImage","hidFileName_xx","defaultAvatar.png", model, request); //设置我的形象到Attribute
					setImageAttribute(userInfoModelSession.getUserid(), 6, "userWork","hidFileName_zp","defaultAvatar.png", model, request); //设置我的代表作品到Attribute
					
				}
			}
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return result;
	}

	@RequestMapping("goUsersubinfo_information")
	/**
	 * 跳转美发师完善基本信息
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUsersubinfo_information(HttpServletRequest request, Model model) {
		try {
			HttpSession session = request.getSession();
			int userId = 0;
			if (null != session.getAttribute("userId")) {
				userId = Integer.parseInt(session.getAttribute("userId").toString());
				UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
				UsersubinfoModel userSubInfoModelSession = usersubinfoService.searchUserSubInfoByUserId(userId);

				List<BaseStarinfoModel> baseStarInfoList = new ArrayList<BaseStarinfoModel>();

				baseStarInfoList = baseStarInfoService.searchBaseStarInfo();
				BaseStarinfoModel baseStarinfoModelSession = new BaseStarinfoModel();
				baseStarinfoModelSession.setStarid(0);
				baseStarinfoModelSession.setStarname("请选择");
				baseStarInfoList.add(0, baseStarinfoModelSession);
				
				List<BaseConfigModel> hobbyList = baseConfigService.searchBaseConfig("hobby");
				List<BaseConfigModel> hairstyleList = baseConfigService.searchBaseConfig("hairstyle");

				model.addAttribute("hobbyList", hobbyList);
				model.addAttribute("hairstyleList", hairstyleList);

				model.addAttribute("baseStarInfoList", baseStarInfoList);
				model.addAttribute("userInfoModel", userInfoModelSession);
				model.addAttribute("userSubInfoModel", userSubInfoModelSession);
				model.addAttribute("errorMsg", "");
				
				setImageAttribute(userInfoModelSession.getUserid(), 1, "userPhoto","hidFileName_tx", "defaultAvatar.png",model, request); //设置我的头像到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 7, "userImage","hidFileName_xx","defaultAvatar.png", model, request); //设置我的形象到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 6, "userWork","hidFileName_zp","defaultAvatar.png", model, request); //设置我的代表作品到Attribute
			}
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return "usersubinfo_information";
	}

	@RequestMapping("doSaveUserInfo")
	/**
	 * 保存美发师验证信息
	 * 
	 * @param request
	 * @param model
	 * @param bindphone
	 * @param password
	 * @param verificationCode
	 * @return
	 */
	public String doSaveUserInfo(HttpServletRequest request, Model model, String nickname, int starid, int workinglife,
			String intro, String workhistory, String hairstyle, String hobbies,int buttonFlag,
			String fileName_tx,String fileName_xx1,String fileName_xx2,String fileName_xx3,String fileName_zp1,String fileName_zp2,String fileName_zp3,String fileName_zp4) {
		String result = "";
		String errorMsg = "";


		try {
			UsersubinfoModel userSubInfo = new UsersubinfoModel();

			userSubInfo.setStarid(starid);
			userSubInfo.setWorkinglife(workinglife);
			userSubInfo.setIntro(intro);
			userSubInfo.setWorkhistory(workhistory);
			userSubInfo.setHairstyle(hairstyle);
			userSubInfo.setHobbies(hobbies);

			UserinfoModel userInfoModel = new UserinfoModel();
			userInfoModel.setNickname(nickname);

			HttpSession session = request.getSession();
			int userId = 0;
			if (null != session.getAttribute("userId")) {
				userId = Integer.parseInt(session.getAttribute("userId").toString());
				
				saveImage(fileName_tx,userId,1,0,request);//保存我的头像
				saveImage(fileName_xx1,userId,7,1,request);//保存我的形象1
				saveImage(fileName_xx2,userId,7,2,request);//保存我的形象2
				saveImage(fileName_xx3,userId,7,3,request);//保存我的形象3
				saveImage(fileName_zp1,userId,6,1,request);//保存我的代表作品1
				saveImage(fileName_zp2,userId,6,2,request);//保存我的代表作品2
				saveImage(fileName_zp3,userId,6,3,request);//保存我的代表作品3
				saveImage(fileName_zp4,userId,6,4,request);//保存我的代表作品4
				
				
				userSubInfo.setUserid(userId);
				if(buttonFlag == 1){
					userSubInfo.setCheckstate(1);//审核状态：审核中
				}else{
					userSubInfo.setCheckstate(0);//审核状态：未提交审核
				}
				
				userInfoModel.setUserid(userId);
				UsersubinfoModel userSubInfoTemp = usersubinfoService.searchUserSubInfoByUserId(userId);
				UserinfoModel userInfoTemp = userinfoService.searchUserinfoById(userId);
				if (0 == userSubInfoTemp.getUserid()) {
					// 信息不存在，新增美发师扩展信息
					// add by jiazhaohui
					// 默认的美发师等级是五星
					userSubInfo.setScore(Constant.MAX_EVAL_STAR);
					int addResult = usersubinfoService.addUserSubInfo(userSubInfo);
					if (addResult > 0) {
						// 新增成功
						// 更新userinfo表里的昵称信息
						userInfoTemp.setNickname(nickname);
						if (userinfoService.updateUserInfoByUserId(userInfoTemp)) {
							if(buttonFlag == 0){
								errorMsg = "保存成功！";
								result = "usersubinfo_information";
							}else{
								errorMsg = "填写完毕，请等待管理员审核。";
								result = "usersubinfo_verification";
							}
						} else {
							errorMsg = "操作失败！";
							result = "usersubinfo_information";
						}

					} else {
						// 新增失败
						errorMsg = "操作失败！";
						result = "usersubinfo_information";
					}
				} else {
					// 信息存在，更新美发师扩展信息
					userSubInfoTemp.setStarid(starid);
					userSubInfoTemp.setWorkinglife(workinglife);
					userSubInfoTemp.setIntro(intro);
					userSubInfoTemp.setWorkhistory(workhistory);
					userSubInfoTemp.setHairstyle(hairstyle);
					userSubInfoTemp.setHobbies(hobbies);
					if(buttonFlag == 1){
						userSubInfoTemp.setCheckstate(1);//审核状态：审核中
					}else{
						userSubInfoTemp.setCheckstate(0);//审核状态：未提交审核
					}
					boolean updateResult = usersubinfoService.updateUserSubInfoByUserId(userSubInfoTemp);
					
					//查询美发师是否有钱包
					UserWalletModel userWallet = userWalletService.searchUserWallet(userSubInfoTemp.getUserid(), 2);
					//如果没有钱包，添加钱包
					if(userWallet == null){
						UserWalletModel o = new UserWalletModel();
						o.setOwnerid(userSubInfoTemp.getUserid());
						o.setBalance(0);
						//2美发师
						o.setOwnertype(2);
						o.setCreatetime(new Date());
						o.setUpdatetime(new Date());
						//生成钱包
						userWalletService.addUserWallet(o);
					}
					
					if (updateResult) {
						// 更新成功
						// 更新userinfo表里的昵称信息
						userInfoTemp.setNickname(nickname);
						if (userinfoService.updateUserInfoByUserId(userInfoTemp)) {
							if(buttonFlag == 0){
								errorMsg = "保存成功！";
								result = "usersubinfo_information";
							}else{
								errorMsg = "填写完毕，请等待管理员审核。";
								result = "usersubinfo_verification";
							}
						} else {
							errorMsg = "操作失败！";
							result = "usersubinfo_information";
						}
					} else {
						// 更新失败
						errorMsg = "操作失败！";
						result = "usersubinfo_information";
					}
				}
			} else {
				errorMsg = "操作失败！";
				result = "usersubinfo_information";
			}
			if(buttonFlag == 0){
				//保存操作，跳转到当前页面
				UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
				UsersubinfoModel userSubInfoModelSession = usersubinfoService.searchUserSubInfoByUserId(userId);

				List<BaseStarinfoModel> baseStarInfoList = new ArrayList<BaseStarinfoModel>();

				baseStarInfoList = baseStarInfoService.searchBaseStarInfo();
				BaseStarinfoModel baseStarinfoModelSession = new BaseStarinfoModel();
				baseStarinfoModelSession.setStarid(0);
				baseStarinfoModelSession.setStarname("请选择");
				baseStarInfoList.add(0, baseStarinfoModelSession);
				
				List<BaseConfigModel> hobbyList = baseConfigService.searchBaseConfig("hobby");
				List<BaseConfigModel> hairstyleList = baseConfigService.searchBaseConfig("hairstyle");

				model.addAttribute("hobbyList", hobbyList);
				model.addAttribute("hairstyleList", hairstyleList);

				model.addAttribute("baseStarInfoList", baseStarInfoList);
				model.addAttribute("userInfoModel", userInfoModelSession);
				model.addAttribute("userSubInfoModel", userSubInfoModelSession);
				
				setImageAttribute(userInfoModelSession.getUserid(), 1, "userPhoto","hidFileName_tx", "defaultAvatar.png",model, request); //设置我的头像到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 7, "userImage","hidFileName_xx","defaultAvatar.png", model, request); //设置我的形象到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 6, "userWork","hidFileName_zp","defaultAvatar.png", model, request); //设置我的代表作品到Attribute
				
				model.addAttribute("errorMsg", errorMsg);
			}else{
				UserinfoModel userInfoModelSession = userinfoService.searchUserinfoById(userId);
				UsersubinfoModel userSubInfoModelSession = usersubinfoService.searchUserSubInfoByUserId(userId);
				model.addAttribute("userInfoModel", userInfoModelSession);
				model.addAttribute("userSubInfoModel", userSubInfoModelSession);
				model.addAttribute("errorMsg", "填写完毕，请等待管理员审核。");

				List<BaseProvinceModel> baseProvinceList = new ArrayList<BaseProvinceModel>();
				List<BaseCityModel> baseCityModelList = new ArrayList<BaseCityModel>();

				//省份
				baseProvinceList = baseProvinceService.searchBaseProvince();
				//省份默认
				BaseProvinceModel baseProvinceModel = new BaseProvinceModel();
				baseProvinceModel.setProvinceid(0);
				baseProvinceModel.setProvincename("请选择");
				baseProvinceList.add(0, baseProvinceModel);
				//城市
				if (userInfoModelSession.getProvinceid() == 0) {
					BaseCityModel baseCityModel = new BaseCityModel();
					baseCityModel.setCityid(0);
					baseCityModel.setCityname("请选择");
					baseCityModelList.add(0,baseCityModel);
				}else {
					baseCityModelList = baseCityService.searchBaseCityByProvinceId(userInfoModelSession.getProvinceid());
				}
				
				
				setImageAttribute(userInfoModelSession.getUserid(), 2, "imgFileName_zm","hidfileName_zm","defaultAvatar.png", model, request); //设置身份证正面到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 3, "imgFileName_bm","hidfileName_bm","defaultAvatar.png", model, request); //设置身份证背面到Attribute
				setImageAttribute(userInfoModelSession.getUserid(), 4, "imgFileName_sc","hidfileName_sc","defaultAvatar.png", model, request); //设置手持身份证到Attribute
				
				model.addAttribute("baseProvinceList", baseProvinceList);
				model.addAttribute("baseCityModelList", baseCityModelList);	
			}
				
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return result;
	}

	/*********** UP mupengyuan *****************/

	/************ lin *******************/

	@RequestMapping("getCommonSendVCode")
	public @ResponseBody Map<String, Object> getCommonSendVCode(HttpServletRequest request,
			HttpServletResponse response, String userName) {

		// 取得请求参数
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 根据传入的参数查找对应的用户
			UserinfoModel userinfo = userinfoService.searchUserinfoByUserName(userName,2);

			// 注册
			// 判断用户名是否存在
			if (null != userinfo) {
				outJsonMap.put("info", false);
				outJsonMap.put("msg", "手机号已注册!");

				return outJsonMap;
			}

			// 设置验证码,发型师注册
			if (baseVerificationcodeService.addBaseVerificationcode(userName, 3)) {
				// 成功
				outJsonMap.put("info", true);
			} else {
				// 失败
				outJsonMap.put("info", false);
				outJsonMap.put("msg", "获取验证码失败!");
			}

		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return outJsonMap;
	}

	/********* lin *******************/

	/************ 管理端 ************/
	@RequestMapping("goUsersubinfoManage")
	/**
	 * 跳转美发店登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goUsersubinfoManage(HttpServletRequest request, Model model) {

		return "usersubinfo_manage";
	}

	@RequestMapping("UsersubinfoManage")
	/**
	 * 订单详细列表
	 * 
	 * @param request
	 * @param model
	 * @param storeid
	 *            店铺ID
	 * @param paystate
	 *            支付类型
	 * @param createtime
	 *            创建时间
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示条数
	 * @return
	 */
	public @ResponseBody Map<String, Object> UsersubinfoManage(HttpServletRequest request, Model model, String cityid,
			String userid, String tel, String startTime, String endTime, String checkstate, String page, String rows)
					throws BaseException {

		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		// 每页显示条数
		int intRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);

		List<UserinfoModel> userinfoModels = usersubinfoService.searchUserinfo(cityid, userid, tel, startTime, endTime,
				checkstate, intPage, intRows);

		List<UsersubsearchModel> usersubsearchModels = new ArrayList<UsersubsearchModel>();

		for (int i = 0; i < userinfoModels.size(); i++) {
			UserinfoModel userinfoModel = userinfoModels.get(i);

			// 重新定义页面显示的实体
			UsersubsearchModel usersubsearchModel = new UsersubsearchModel();
			// 查询美发师扩展信息
			UsersubinfoModel usersubinfoModel = usersubinfoService.searchUsersubinfo(userinfoModel.getUserid());

			// ID
			usersubsearchModel.setUserid(userinfoModel.getUserid());
			// 手机
			usersubsearchModel.setBindphone(userinfoModel.getBindphone());
			// 昵称
			usersubsearchModel.setNickname(userinfoModel.getNickname());
			// 服务等级
			usersubsearchModel.setLevelid(usersubinfoModel.getLevelid());
			usersubsearchModel.setLevelname(usersubinfoModel.getUserProfessionLevel().getLevelname());
			// 定级
			usersubsearchModel.setScore((int) usersubinfoModel.getScore());
			// 状态
			int checkState = usersubinfoModel.getCheckstate();
			usersubsearchModel.setCheckstate(checkState);

			usersubsearchModels.add(usersubsearchModel);
		}
		// 存放总记录数，必须的
		jsonMap.put("total",
				usersubinfoService.searchUserinfoCount(cityid, userid, tel, startTime, endTime, checkstate));
		// rows键 存放每页记录 list
		jsonMap.put("rows", usersubsearchModels);
		return jsonMap;
	}
	
	/**
	 * 保存图片到数据库
	 * @param fileName
	 * @param userId
	 * @param type
	 * @param sortNum 0为单张图片,1、2、3、4为多张图片
	 * @param request
	 */
	public void saveImage(String fileName,int userId,int type,int sortNum,HttpServletRequest request){
		//Date date = new Date();
		if(!"".equals(fileName)){
			String serverPath = request.getServletContext().getRealPath("/userImg/");
			try {
				userPhotoMappingService.addOrUpdImg(userId, type, sortNum,fileName, serverPath);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置图片的Attribute
	 * @param userId
	 * @param type 图片类型
	 * @param imgFileName
	 * @param hidFileName
	 * @param defaultImagePath 默认图片路径
	 * @param model
	 * @param request
	 */
	public void setImageAttribute(int userId,int type,String imgFileName,String hidFileName,String defaultImagePath,Model model,HttpServletRequest request){
		try {
			if(type == Constant.IMAGE_TYPE_06 || type == Constant.IMAGE_TYPE_07){
				List<UserPhotoMappingModel> userPhotoMappingModels = userPhotoMappingService.searchUserPhotos(userId, type);
				int size = 0;
				if(type == Constant.IMAGE_TYPE_07){
					size = 3;//我的形象循环3次
				}else{
					size = 4;//我的作品循环4次
				}
				
				//有图片，设置图片;没有图片，设置默认图片
				for (int i = 0; i < size; i++) {
					if(null != userPhotoMappingModels){
						for (int j = 0; j < userPhotoMappingModels.size(); j++) {
							UserPhotoMappingModel userPhotoMappingModel = userPhotoMappingModels.get(j);
							if(null != userPhotoMappingModel){
								if((i+1) != userPhotoMappingModel.getSortnum()){
									model.addAttribute(imgFileName+(i+1),  "images/"+defaultImagePath);
								}else{
									if(userPhotoMappingModel.getBasePicture() != null){
										String path = userPhotoMappingModel.getBasePicture().getPicturepath();
										String imgName = path.substring(8);
										model.addAttribute(imgFileName+(i+1), path);
										model.addAttribute(hidFileName+(i+1), imgName);
										break;
									}
								}
							}
						}
					}else{
						model.addAttribute(imgFileName+(i+1),  "images/"+defaultImagePath);
					}
				}
			}else{
				UserPhotoMappingModel userPhotoMappingModel = userPhotoMappingService.searchUserPhoto(userId, type);
				if(null != userPhotoMappingModel && userPhotoMappingModel.getBasePicture() != null){
					String path = userPhotoMappingModel.getBasePicture().getPicturepath();
					String imgName = path.substring(8);
					model.addAttribute(imgFileName,  path);
					model.addAttribute(hidFileName, imgName);
				}else{
					model.addAttribute(imgFileName,  "images/"+defaultImagePath);
				}
			}
		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
	}
	
	@RequestMapping("checkUserinfo")
	/**
	 * 设置图片的Attribute
	 * @param userName
	 * @param model
	 * @param request
	 */
	public @ResponseBody Map<String, Object> checkUserinfo(String userName, HttpServletRequest request, Model model) {
		// 取得请求参数
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		try {
			// 根据传入的参数查找对应的用户
			UserinfoModel userinfo = userinfoService.searchUserinfoByUserName(userName,2);

			// 判断用户名是否存在
			if (null == userinfo) {
				outJsonMap.put("info", true);
			}else{
				outJsonMap.put("info", false);
			}
			return outJsonMap;

		} catch (Exception e) {
			// 保存异常信息
			utilService.addBaseException(request.getRequestURI(), "", e.getMessage());
		}
		return outJsonMap;
	}
}