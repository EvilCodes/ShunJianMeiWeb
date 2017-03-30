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

import com.alibaba.fastjson.JSONObject;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.model.BaseVerificationcodeModel;
import com.wenyuankeji.spring.model.FavoritesModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IBasePictureService;
import com.wenyuankeji.spring.service.IBaseRequestkeyrecordService;
import com.wenyuankeji.spring.service.IBaseVerificationcodeService;
import com.wenyuankeji.spring.service.ICouponListService;
import com.wenyuankeji.spring.service.IFavoriteHairStyleListService;
import com.wenyuankeji.spring.service.IFavoriteHairdresserListService;
import com.wenyuankeji.spring.service.IFavoriteStoreListService;
import com.wenyuankeji.spring.service.IFavoritesService;
import com.wenyuankeji.spring.service.IMineService;
import com.wenyuankeji.spring.service.IMyBalanceService;
import com.wenyuankeji.spring.service.IStorePhotoMappingService;
import com.wenyuankeji.spring.service.ITradeRecordService;
import com.wenyuankeji.spring.service.IUserCashrecordService;
import com.wenyuankeji.spring.service.IUserCouponsService;
import com.wenyuankeji.spring.service.IUserHairpackedService;
import com.wenyuankeji.spring.service.IUserMyhairstyleService;
import com.wenyuankeji.spring.service.IUserPhotoMappingService;
import com.wenyuankeji.spring.service.IUserQuickpayService;
import com.wenyuankeji.spring.service.IUserWalletService;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.service.IWalletPayService;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Controller
public class AppGeRenController {

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IUserPhotoMappingService userPhotoMappingService;

	@Autowired
	private IBaseVerificationcodeService baseVerificationcodeService;

	@Autowired
	private IBaseConfigService baseConfigService;

	@Autowired
	private IFavoritesService favoritesService;

	@Autowired
	private IUserCashrecordService userCashrecordService;

	@Autowired
	private IUtilService utilService;
	
	@Autowired
	private IBasePictureService basePitureService;
	
	@Autowired
	private IUserWalletService userWalletService;
	
	@Autowired
	private IUserCouponsService userCouponsService;
	// 1.9 收藏发型列表服务类
	@Autowired
	private IFavoriteHairStyleListService favoriteHairStyleListService;
	// 1.10 收藏发型师列表（定义）
	@Autowired
	private IFavoriteHairdresserListService favoriteHairdresserListService;
	// 1.11 收藏商户列表（定义）
	@Autowired
	private IFavoriteStoreListService favoriteStoreListService;
	// 1.13 我的界面（定义）
	@Autowired
	private IMineService mineService;
	// 1.14 我的优惠券
	@Autowired
	private ICouponListService couponListService;
	// 1.15 我的余额
	@Autowired
	private IMyBalanceService myBalanceService;
	// 1.17 我的消费记录
	@Autowired
	private ITradeRecordService tradeRecordService;
	@Autowired
	private IWalletPayService walletPayService;	
	
	@Autowired
	private IUserMyhairstyleService userMyhairstyleService;
	@Autowired
	private IStorePhotoMappingService storePhotoMappingService;
	
	@Autowired
	private IBaseRequestkeyrecordService baseRequestKeyRecordService;
	
	@Autowired
	private IUserHairpackedService userHairpackedService;
	

	@Autowired
	private IUserQuickpayService userQuickpayService; 

	@Value("#{configProperties['ipHost']}")
	private String ipHost;

	@Value("#{configProperties['interfaceStatus']}")
	private String interfaceStatus;

	@SuppressWarnings("unchecked")
	@RequestMapping("UserLogin")
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @param UserName
	 * @param PassWord
	 * @param Infversion
	 */
	public void UserLogin(HttpServletRequest request,
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
				userinfo = userinfoService.searchUserinfo(UserName, PassWord,1);

				// 设置返回内容
				if (null != userinfo) {

					// 修改用户最后登录信息
					userinfoService.updatelLastlogintimeById(userinfo
							.getUserid());

					userinfoMap.put("UserID",
							ShunJianMeiUtil.intToString(userinfo.getUserid()));
					userinfoMap.put("NickName", userinfo.getNickname());
					userinfoMap.put("Sex",
							ShunJianMeiUtil.intToString(userinfo.getSex()));

					UserPhotoMappingModel userPhoto = userPhotoMappingService
							.searchUserPhoto(userinfo.getUserid(), 1);

					if (null != userPhoto && null != userPhoto.getBasePicture()) {
						userinfoMap.put("Photo", ipHost
								+ userPhoto.getBasePicture().getPicturepath());
					} else {
						userinfoMap.put("Photo", "");
					}
					outList.add(userinfoMap);
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "用户名或密码错误");
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
	@RequestMapping("CommonSendVCode")
	/**
	 *1.2      发送手机验证(定义)
	 * @param request
	 * @param response
	 */
	public void CommonSendVCode(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '1|验证码以发送',");
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
				
				/*****************请求记录KEY重复验证 start*******************/
				//请求记录的KEY
				String keystring = inputJsonMap.get("Key").toString();
				//判断请求记录是否重复
				if (baseRequestKeyRecordService.selectBaseRequestKeyRecord(keystring)) {
					// 失败
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_ERROR);
					// 输出流
					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return ;
				}
				/*****************请求记录KEY重复验证 end*******************/
				
				
				// 获取外部传入的参数手机号和验证码类型
				String userName = inputJsonMap.get("Mobile").toString();
				int type = Integer.parseInt(inputJsonMap.get("Type").toString());
				
				// 根据传入的参数查找对应的用户
				UserinfoModel userinfo = userinfoService
						.searchUserinfoByUserName(userName,1);

				if (type == 1) {
					
					// 注册
					// 判断用户名是否存在
					if (null != userinfo) {
						outJsonMap.put(Constant.CODE, Constant.CODE_2000);
						outJsonMap.put(Constant.MSG, Constant.MSG_1_1
								+ "手机号已注册");

						// 保存请求记录
						utilService.addBaseRequestlog(
								request.getRequestURI(),
								request
										.getAttribute("inputJsonInfo")
										.toString(), "",
								ShunJianMeiUtil.outputString(outJsonMap));
						ShunJianMeiUtil.outputJson(request, response,
								outJsonMap);
						return;
					}

				} else if (type == 2) {
					// 用户找回密码
					if (null == userinfo) {
						outJsonMap.put(Constant.CODE, Constant.CODE_2000);
						outJsonMap.put(Constant.MSG, Constant.MSG_1_1
								+ "手机号不存在");

						// 保存请求记录
						utilService.addBaseRequestlog(
								request.getRequestURI(),
								request
										.getAttribute("inputJsonInfo")
										.toString(), "",
								ShunJianMeiUtil.outputString(outJsonMap));
						ShunJianMeiUtil.outputJson(request, response,
								outJsonMap);
						return;
					}
				}else if (type == 4) {
					// 根据传入的参数查找对应的美发师
					UserinfoModel userinfo2 = userinfoService
							.searchUserinfoByUserName(userName,2);
					// 美发师找回密码
					if (null == userinfo2) {
						outJsonMap.put(Constant.CODE, Constant.CODE_2000);
						outJsonMap.put(Constant.MSG, Constant.MSG_1_1
								+ "手机号不存在");

						// 保存请求记录
						utilService.addBaseRequestlog(
								request.getRequestURI(),
								request
										.getAttribute("inputJsonInfo")
										.toString(), "",
								ShunJianMeiUtil.outputString(outJsonMap));
						ShunJianMeiUtil.outputJson(request, response,
								outJsonMap);
						return;
					}
				}

				// 设置验证码
				if (baseVerificationcodeService.addBaseVerificationcode(
						userName, type)) {
					// 成功
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					// 失败
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "获取失败");
					outJsonMap.put(Constant.TOTAL, 1);
				}
				
				/*****************添加请求记录KEY的表 start*******************/
				baseRequestKeyRecordService.addBaseRequestKeyRecord(keystring);
				/*****************添加请求记录KEY的表  end*******************/

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
	@RequestMapping("FindPassWord")
	/**
	 * 重设密码
	 * @param request
	 * @param response
	 */
	public void FindPassWord(HttpServletRequest request,
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
				String UserName = inputJsonMap.get("UserName").toString();
				String PassWord = inputJsonMap.get("PassWord").toString();
				String VCode = inputJsonMap.get("VCode").toString();
				String Type = inputJsonMap.get("Type").toString();
				
				

				// 判断验证码
				BaseVerificationcodeModel baseVerificationcodeModel = baseVerificationcodeService
						.searchBaseVerificationcode(UserName);
				if (null == baseVerificationcodeModel
						|| !(VCode.equals(baseVerificationcodeModel.getCode()))) {

					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "验证码错误");
					outJsonMap.put(Constant.TOTAL, 1);
					// 保存请求记录
					utilService.addBaseRequestlog(request.getRequestURI(),
							request.getAttribute("inputJsonInfo")
									.toString(), "",
							ShunJianMeiUtil.outputString(outJsonMap));

					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return;
				}

				if (ShunJianMeiUtil.checkVCodeDate(baseVerificationcodeModel
						.getExpirationtime())) {

					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "验证码过期");
					outJsonMap.put(Constant.TOTAL, 1);
					// 保存请求记录
					utilService.addBaseRequestlog(request.getRequestURI(),
							request.getAttribute("inputJsonInfo")
									.toString(), "",
							ShunJianMeiUtil.outputString(outJsonMap));
					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return;
				}
				//用户状态
				int userState = 1;
				if (Type.equals("2")){
					//2找回密码
					userState = 1;
				}
				else if(Type.equals("4")){
					//4发型师找回密码
					userState = 2;
				}
				// 执行修改密码
				if (userinfoService.updateFindPassWord(UserName, PassWord, userState)) {
					// 成功
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.TOTAL, 1);
				} else {
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "操作失败");
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
	@RequestMapping("OpenCityList")
	/**
	 * 1.7     获取已开通的城市(定义)
	 * @param request
	 * @param response
	 */
	public void OpenCityList(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append(" 'ID' : '1',");
			sb.append("'Name': '北京市' ");
			sb.append("}");
			sb.append("{");
			sb.append("'ID' : '2',");
			sb.append("'Name': '天津市' ");
			sb.append("}");
			sb.append("],");
			sb.append("'total': 2");
			sb.append("}");
			ShunJianMeiUtil.outputResult(request, response, sb.toString());
		} else {

			// 取得请求参数
			Map<String, Object> inputJsonMap = (Map<String, Object>) request
					.getAttribute("inputJsonMap");

			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			try {
				List<BaseConfigModel> baseConfigModel = baseConfigService
						.searchBaseConfig();

				if (null == baseConfigModel || baseConfigModel.size() == 0) {
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				} else {

					List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();

					for (BaseConfigModel obj : baseConfigModel) {
						Map<String, Object> cityMap = new HashMap<String, Object>();
						cityMap.put("ID",obj.getValue1());
						cityMap.put("Name", obj.getConfigvalue());
						cityList.add(cityMap);
					}

					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_0);
					outJsonMap.put(Constant.DATA, cityList);
					outJsonMap.put(Constant.TOTAL, 2);
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
	@RequestMapping("UserRegister")
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 */
	public void UserRegister(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'UserID ': '1'");
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
			Map<String, Object> userinfoMap = new HashMap<String, Object>();
			try {
				String UserName = inputJsonMap.get("UserName").toString();
				String PassWord = inputJsonMap.get("PassWord").toString();
				String VCode = inputJsonMap.get("VCode").toString();
				//推荐人
				String Referee = inputJsonMap.get("Referee").toString();
				// add by jiazhaohui
				String Device = null;
				if (inputJsonMap.get("Device") != null)
				{
					Device = inputJsonMap.get("Device").toString();
				}
				
				UserinfoModel userinfo = new UserinfoModel();
				userinfo.setUsername(UserName);
				userinfo.setPassword(PassWord);
				userinfo.setUserstate(1);
				userinfo.setCreatetime(new Date());
				userinfo.setUpdatetime(new Date());
				userinfo.setUsertype(1);
				userinfo.setReferee(Referee);
				userinfo.setDevice(Device);

				// 判断验证码
				BaseVerificationcodeModel baseVerificationcodeModel = baseVerificationcodeService
						.searchBaseVerificationcode(UserName);

				if (null == baseVerificationcodeModel
						|| !(VCode.equals(baseVerificationcodeModel.getCode()))) {

					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "验证码错误");
					outJsonMap.put(Constant.TOTAL, 1);
					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return;
				}

				if (ShunJianMeiUtil.checkVCodeDate(baseVerificationcodeModel
						.getExpirationtime())) {

					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "验证码过期");
					outJsonMap.put(Constant.TOTAL, 1);
					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return;
				}

				
				UserinfoModel checkUser = userinfoService.searchUserinfoByUserName(UserName, 1);
				
				if (checkUser == null) {
					// 执行用户注册
					if (userinfoService.addUserinfo(userinfo)) {
						
						UserWalletModel o = new UserWalletModel();
						o.setOwnerid(userinfo.getUserid());
						o.setBalance(0);
						//1用户
						o.setOwnertype(1);
						o.setCreatetime(new Date());
						o.setUpdatetime(new Date());
						//生成钱包
						userWalletService.addUserWallet(o);
						
						userinfoMap.put("UserID", ShunJianMeiUtil.intToString(userinfo.getUserid()));
						outList.add(userinfoMap);
						userinfoMap.put("UserID", ShunJianMeiUtil
								.intToString(userinfo.getUserid()));
						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
						outJsonMap.put(Constant.DATA, outList);
						outJsonMap.put(Constant.TOTAL, 1);
						
						//更新注册用户对应的我的优惠券的用户ID
						userCouponsService.updateUserCoupons(UserName, userinfo.getUserid());
					} else {
						// 无数据
						outJsonMap.put(Constant.CODE, Constant.CODE_2000);
						outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "操作失败");
						outJsonMap.put(Constant.TOTAL, 1);
					}
				}else{
					// 无数据
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "手机号已注册");
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
	@RequestMapping("SaveUserInfo")
	/**
	 * 保存用户信息
	 * @param request
	 * @param response
	 */
	public void SaveUserInfo(HttpServletRequest request,
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
			Map<String, Object> userinfoMap = new HashMap<String, Object>();
			try {
				String UserID = inputJsonMap.get("UserID").toString();
				String NickName = inputJsonMap.get("NickName").toString();
				String Sex = inputJsonMap.get("Sex").toString();
				UserinfoModel userinfo = new UserinfoModel();
				userinfo.setUserid(Integer.parseInt(UserID));
				userinfo.setNickname(NickName);
				userinfo.setSex(Integer.parseInt(Sex));

				// 执行修改用户密码
				if (userinfoService.updateFindUserinfo(userinfo)) {
					int id = userinfo.getUserid();
					userinfo = userinfoService.searchUserinfoById(id);
					userinfoMap.put("UserID",
							ShunJianMeiUtil.intToString(userinfo.getUserid()));
					userinfoMap.put("NickName", userinfo.getNickname());
					userinfoMap.put("Sex",
							ShunJianMeiUtil.intToString(userinfo.getSex()));

					UserPhotoMappingModel userPhoto = userPhotoMappingService
							.searchUserPhoto(userinfo.getUserid(), 1);

					if (null != userPhoto && null != userPhoto.getBasePicture()) {
						userinfoMap.put("Photo", ipHost
								+ userPhoto.getBasePicture().getPicturepath());
					} else {
						userinfoMap.put("Photo", "");
					}

					outList.add(userinfoMap);
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
	@RequestMapping("SaveUserPhoto")
	/**
	 * 保存头像
	 * @param request
	 * @param response
	 */
	public void SaveUserPhoto(HttpServletRequest request,
			HttpServletResponse response) {

		if (Integer.parseInt(interfaceStatus) == 1) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("'code': 1000,");
			sb.append("'msg': '0|操作成功',");
			sb.append("'data': [");
			sb.append("{");
			sb.append("'Photo'： 'http://ykssdservice.ruitei.com/Resource/Photo/123.png'");
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
				
				int userID = Integer.parseInt(inputJsonMap.get("UserID").toString());
				int picid = Integer.parseInt(inputJsonMap.get("ID").toString());
				//int type = Integer.parseInt(inputJsonMap.get("Type").toString());
				
				//查询图片路径
				BasePictureModel bpm = basePitureService.searchBasePictureByID(picid);
				
				if (bpm != null) {
					UserPhotoMappingModel upmm = null;
					upmm = userPhotoMappingService.searchUserPhoto(userID, 1);
					if (upmm != null) {
						upmm.setPicid(picid);
						userPhotoMappingService.updateUserPhoto(upmm);
					}else {
						upmm = new UserPhotoMappingModel();
						upmm.setUserid(userID);
						upmm.setPicid(picid);
						upmm.setType(1);
						upmm.setRemark("");
						upmm.setSortnum(0);
						upmm.setCreatetime(new Date());
						userPhotoMappingService.addUserPhoto(upmm);
					}
					
					Map<String, Object> bpmMap = new HashMap<String, Object>();
					bpmMap.put("Photo", ipHost+bpm.getPicturepath());
					outList.add(bpmMap);
					
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1);
					outJsonMap.put(Constant.DATA, outList);
					outJsonMap.put(Constant.TOTAL, 1);
					
				}else {
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
	@RequestMapping("SaveFavorite")
	/**
	 * 保存收藏
	 * @param request
	 * @param response
	 */
	public void SaveFavorite(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {
			// 用户ID
			String userid = inputJsonMap.get("UserID").toString();
			// 发型ID/发型师ID/商户ID
			String item = inputJsonMap.get("ID").toString();
			// 类型 1发型2发型师3商户
			String type = inputJsonMap.get("Type").toString();
			// 0取消收藏1添加收藏
			String state = inputJsonMap.get("State").toString();

			if (favoritesService.updateFavorites(userid, type, item, state)) {
				// 成功
				if ("1".equals(state)) {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1+"收藏成功");
					outJsonMap.put(Constant.TOTAL, 1);
				}else {
					outJsonMap.put(Constant.CODE, Constant.CODE_1000);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1+"取消收藏");
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
	@RequestMapping("FavoriteHairStyleList")
	/**
	 * 1.9	收藏发型列表（定义）
	 * @param request
	 * @param response
	 */
	public void FavoriteHairStyleList(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			String userid = inputJsonMap.get("UserID").toString();
			// 返回数据行数
			int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
					.toString());
			// 当前页数 从1开始
			int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
					.toString());

			// 返回集合
			List<Map<String, Object>> myhairstyleList = new ArrayList<Map<String, Object>>();

			// 获取我的收藏发型列表ID集合
			List<FavoritesModel> favoritesModels = favoriteHairStyleListService
					.searchFavorite(Integer.parseInt(userid), 1, pageSize,
							pageIndex);
			if (favoritesModels != null && favoritesModels.size() > 0) {
				for (FavoritesModel obj : favoritesModels) {

					// 发型ID
					int mystyleid = obj.getItem();

					UserMyhairstyleModel userMyhairstyleModel = favoriteHairStyleListService
							.searchUserMyhairstyle(mystyleid);

					// 获取发型图片
					UserMyhairstylePhotoMappingModel userMyhairstylePhotoMappingModel = userMyhairstyleService
							.searchUserMyhairstylePhotoMapping(mystyleid, 1);

					Map<String, Object> myhairstyleMap = new HashMap<String, Object>();

					myhairstyleMap.put("ID",
							ShunJianMeiUtil.intToString(mystyleid));
					myhairstyleMap.put("Name", userMyhairstyleModel.getName());
					if (null != userMyhairstylePhotoMappingModel 
							&& null != userMyhairstylePhotoMappingModel.getBasePicture()) {
						myhairstyleMap.put("Photo", ipHost
								+ userMyhairstylePhotoMappingModel.getBasePicture()
										.getPicturepath());
						
					}else{
						myhairstyleMap.put("Photo", "");
					}
					
					myhairstyleMap.put("Price", ShunJianMeiUtil
							.floatToString((userMyhairstyleModel.getPrice())));
					myhairstyleMap.put("UsedNum", ShunJianMeiUtil
							.intToString(userMyhairstyleModel.getUsednum()));
					myhairstyleList.add(myhairstyleMap);
				}
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, myhairstyleList);
				outJsonMap.put(Constant.TOTAL, favoritesModels.size());
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
	@RequestMapping("FavoriteHairdresserList")
	/**
	 * 1.10	收藏发型师列表（定义）
	 * @param request
	 * @param response
	 */
	public void FavoriteHairdresserList(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			String userid = inputJsonMap.get("UserID").toString();
			// 返回数据行数
			int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
					.toString());
			// 当前页数 从1开始
			int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
					.toString());

			// 返回集合
			List<Map<String, Object>> hairdresserList = new ArrayList<Map<String, Object>>();

			// 获取我的收藏发型师列表ID集合
			List<FavoritesModel> favoritesModels = favoriteHairStyleListService
					.searchFavorite(Integer.parseInt(userid), 2, pageSize,
							pageIndex);
			if (favoritesModels != null && favoritesModels.size() > 0) {
				for (FavoritesModel obj : favoritesModels) {
					//查询发型师类型 0：自营；1：非自营
					
					// 发型师ID
					int mystyleid = obj.getItem();
					UsersubinfoModel usersubinfoModel = favoriteHairdresserListService
							.searchUsersubinfo(mystyleid);

					Map<String, Object> hairdresserMap = new HashMap<String, Object>();

					// 将发型师的属性值取出
					hairdresserMap.put("ID", ShunJianMeiUtil
							.intToString(usersubinfoModel.getUserid()));
					hairdresserMap.put("Name", usersubinfoModel.getUserinfoModel().getNickname());
					// add by jiazhaohui
					// 国籍和语言
					hairdresserMap.put("Nationality", usersubinfoModel.getNationality());
					hairdresserMap.put("Language", usersubinfoModel.getLanguage());
					hairdresserMap.put("Flagpng", usersubinfoModel.getFlagpng());
					String str = "";
					if( usersubinfoModel.getIstype() == 0){
						str = "资深合作";
					}else{
						str = "自由定价";
					}
					hairdresserMap.put("istypeString", str);
					
					//判断是否有套餐
					if(usersubinfoModel.getIstype() == 1){
						JSONObject jsonResult = userHairpackedService.searchUserHairpackedByHairdresserid(usersubinfoModel.getUserid()+"");
						if(jsonResult.getJSONArray("data").isEmpty()){
							hairdresserMap.put("hasPacked", 0);
						}else{
							hairdresserMap.put("hasPacked", 1);
						}
					}
					
					// 头像
					UserPhotoMappingModel userPhoto = userPhotoMappingService
							.searchUserPhoto(usersubinfoModel.getUserid(), 1);
					if (null != userPhoto && null != userPhoto.getBasePicture()) {
						hairdresserMap.put("Photo", ipHost
								+ userPhoto.getBasePicture().getPicturepath());
					} else {
						hairdresserMap.put("Photo", "");
					}

					// 获取配图
					UserPhotoMappingModel userPhoto2 = userPhotoMappingService
							.searchUserPhoto(usersubinfoModel.getUserid(),7,1);
					if (null != userPhoto2
							&& null != userPhoto2.getBasePicture()) {
						hairdresserMap.put("Image", ipHost
								+ userPhoto2.getBasePicture().getPicturepath());
					} else {
						hairdresserMap.put("Image", "");
					}

					hairdresserMap.put("LevelName", usersubinfoModel
							.getUserProfessionLevel().getLevelname());

					hairdresserMap.put("Star", usersubinfoModel
							.getBaseStarinfoModel().getStarname());
					hairdresserMap.put("Score", ShunJianMeiUtil
							.floatToString(usersubinfoModel.getScore()));
					hairdresserMap.put("OrderNum", ShunJianMeiUtil
							.intToString(usersubinfoModel.getOrdernum()));

					hairdresserList.add(hairdresserMap);
				}
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, hairdresserList);
				outJsonMap.put(Constant.TOTAL, favoritesModels.size());
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
	@RequestMapping("FavoriteStoreList")
	/**
	 * 1.11	收藏商户列表（定义）
	 * @param request
	 * @param response
	 */
	public void FavoriteStoreList(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			String userid = inputJsonMap.get("UserID").toString();
			// 经度
			double longitude = Double.parseDouble(inputJsonMap.get("Longitude")
					.toString());
			// 纬度
			double latitude = Double.parseDouble(inputJsonMap.get("Latitude")
					.toString());
			// 返回数据行数
			int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
					.toString());
			// 当前页数 从1开始
			int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
					.toString());

			// 返回集合
			List<Map<String, Object>> storeinfoList = new ArrayList<Map<String, Object>>();

			// 获取我的收藏发型列表ID集合
			List<FavoritesModel> favoritesModels = favoriteHairStyleListService
					.searchFavorite(Integer.parseInt(userid), 3, pageSize,
							pageIndex);
			if (favoritesModels != null && favoritesModels.size() > 0) {
				for (FavoritesModel obj : favoritesModels) {

					// 商户ID
					int mystyleid = obj.getItem();
					// 店铺实体
					StoreinfoModel storeinfoModel = favoriteStoreListService
							.searchStoreinfo(mystyleid);
					// 店铺相册
					List<StorePhotoMappingModel> storePhotoMappingModelList = storePhotoMappingService
							.searchStorePhotoMapping(mystyleid, 1);

					Map<String, Object> storeinfoMap = new HashMap<String, Object>();

					storeinfoMap.put("ID",
							ShunJianMeiUtil.intToString(mystyleid));
					storeinfoMap.put("Name", storeinfoModel.getName());
					if (storePhotoMappingModelList != null
							&& storePhotoMappingModelList.size() > 0) {
						storeinfoMap.put("Photo", ipHost + storePhotoMappingModelList
								.get(0).getBasePicture().getPicturepath());
					} else {
						storeinfoMap.put("Photo", "");
					}
					String str = "";
					if(storeinfoModel.getIstype() == null || storeinfoModel.getIstype() == 0){
						str = "资深合作";
					}else{
						str = "自由定价";
					}
					storeinfoMap.put("istype",str);
					storeinfoMap.put("Address", storeinfoModel.getAddress());
					storeinfoMap.put("Score", ShunJianMeiUtil
							.floatToString(storeinfoModel.getScore()));
					// 根据经纬度计算距离
					String distance = ShunJianMeiUtil.LantitudeLongitudeDist(
							Double.parseDouble(storeinfoModel.getLongitude()),
							Double.parseDouble(storeinfoModel.getLatitude()),
							longitude, latitude);
					storeinfoMap.put("Distance",distance);
					storeinfoMap.put("OrderNum", ShunJianMeiUtil
							.intToString(storeinfoModel.getOrderquantity()));
					storeinfoMap.put("CarNum", ShunJianMeiUtil
							.intToString(storeinfoModel.getCarnumber()));
					// add by jiazhaohui
					// 是否有闪惠
					storeinfoMap.put("Quickpay", 
							ShunJianMeiUtil.intToString(storeinfoModel.getQuickpay()));
					storeinfoList.add(storeinfoMap);
				}
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_0);
				outJsonMap.put(Constant.DATA, storeinfoList);
				outJsonMap.put(Constant.TOTAL, favoritesModels.size());
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
	@RequestMapping("Mine")
	/**
	 * 1.13	我的界面（定义）
	 * @param request
	 * @param response
	 */
	public void Mine(HttpServletRequest request, HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			String userid = inputJsonMap.get("UserID").toString();

			// 返回集合
			List<Map<String, Object>> userinfoList = new ArrayList<Map<String, Object>>();

			UserinfoModel userinfoModel = mineService
					.searchUserinfoById(Integer.parseInt(userid));

			Map<String, Object> userinfoMap = new HashMap<String, Object>();

			if (userinfoModel != null) {
				userinfoMap.put("UserID", userid);
				userinfoMap.put("NickName", userinfoModel.getNickname());

				// 头像
				UserPhotoMappingModel userPhoto = userPhotoMappingService
						.searchUserPhoto(userinfoModel.getUserid(), 1);
				if (null != userPhoto && null != userPhoto.getBasePicture()) {
					userinfoMap.put("Photo", ipHost
							+ userPhoto.getBasePicture().getPicturepath());
				} else {
					userinfoMap.put("Photo", "");
				}
				// 性别
				if (userinfoModel.getSex() == 1) {
					userinfoMap.put("Sex", "男");
				} else {
					userinfoMap.put("Sex", "女");
				}
				//userinfoMap.put("Mobile", userinfoModel.getBindphone());
				userinfoMap.put("Mobile", userinfoModel.getUsername());

				UserWalletModel userWalletModel = mineService
						.searchUserWallet(userinfoModel.getUserid(),1);//用户钱包
				
				if (null != userWalletModel) {
					userinfoMap.put("Balance", ShunJianMeiUtil
							.floatToString(userWalletModel.getBalance()));
				}else {
					userinfoMap.put("Balance", "0.0");
				}

				int userCouponsCount = mineService
						.searchUserCouponsCount(userinfoModel.getUserid());
				userinfoMap.put("Coupons",
						ShunJianMeiUtil.intToString(userCouponsCount));

				int favoriteCount = mineService.searchFavoriteCount(
						userinfoModel.getUserid(), 0);
				userinfoMap.put("FavoriteNum",
						ShunJianMeiUtil.intToString(favoriteCount));

				userinfoList.add(userinfoMap);

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
	@RequestMapping("CouponList")
	/**
	 * 1.14	我的优惠券（定义）
	 * @param request
	 * @param response
	 */
	public void CouponList(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			String userid = inputJsonMap.get("UserID").toString();
			// 返回数据行数
			int pageSize = Integer.parseInt(inputJsonMap.get("PageSize")
					.toString());
			// 当前页数 从1开始
			int pageIndex = Integer.parseInt(inputJsonMap.get("PageIndex")
					.toString());

			// 返回集合
			List<Map<String, Object>> userinfoList = new ArrayList<Map<String, Object>>();

			// 获取我的优惠券列表
			List<UserCouponsModel> userCouponsList = couponListService
					.searchUserCoupons(Integer.parseInt(userid), pageSize,
							pageIndex);
			if (userCouponsList != null && userCouponsList.size() > 0) {
				for (UserCouponsModel obj : userCouponsList) {

					Map<String, Object> userCouponsMap = new HashMap<String, Object>();

					userCouponsMap.put("ID",
							ShunJianMeiUtil.intToString(obj.getCouponid()));
					userCouponsMap
							.put("Name", obj.getCoupons().getCouponname());
					userCouponsMap.put("Amount", ShunJianMeiUtil
							.floatToString(obj.getCoupons().getAmount()));
					userCouponsMap.put("StartTime", ShunJianMeiUtil
							.dateToString(obj.getCoupons().getStarttime()));
					userCouponsMap.put("EndTime", ShunJianMeiUtil
							.dateToString(obj.getCoupons().getEndtime()));
					userinfoList.add(userCouponsMap);

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
	@RequestMapping("MyBalance")
	/**
	 * 1.15	我的余额（定义）
	 * @param request
	 * @param response
	 */
	public void MyBalance(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		try {

			// 用户ID
			String userid = inputJsonMap.get("UserID").toString();

			// 返回集合
			List<Map<String, Object>> userinfoList = new ArrayList<Map<String, Object>>();

			// 获取我的余额
			UserWalletModel userWalletModel = myBalanceService
					.searchUserWallet(Integer.parseInt(userid),1);//用户钱包
			Map<String, Object> userWalletMap = new HashMap<String, Object>();
			if (userWalletModel != null) {
				userWalletMap.put("Total", ShunJianMeiUtil
						.floatToString(userWalletModel.getBalance()));
				// 我的余额
				float myBalance = userWalletModel.getBalance();
				// 押金
				BaseConfigModel baseConfigModel = myBalanceService
						.searchBaseConfig(Constant.CONFIGCODE_05);
				/*float cashfund = 0;
				if (baseConfigModel != null) {
					cashfund = Float.parseFloat(baseConfigModel
							.getConfigvalue());
				}

				// 可提现金额 = 我的余额 - 押金
				float amount = myBalance - cashfund;*/
				float amount = myBalance;

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

	@SuppressWarnings("unchecked")
	@RequestMapping("CashRecord")
	/**
	 * 1.16	提现记录（定义）
	 * @param request
	 * @param response
	 */
	public void CashRecord(HttpServletRequest request,
			HttpServletResponse response) {

		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
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
					.searchUserWallet(Integer.parseInt(userid),1);
			if (userWalletModel != null) {
				// 剩余金额 = 钱包金额 - 支付金额
				float allAmount = userWalletModel.getBalance();
				// 钱包余额大于提现金额
				if (allAmount >= Float.parseFloat(amount)) {
					float newAmount = allAmount - Float.parseFloat(amount);
					userWalletModel.setBalance(newAmount);
					userWalletModel.setOwnertype(1);
					
					UserCashrecordModel userCashrecord = new UserCashrecordModel();
					// 将记录数据存入
					userCashrecord.setUserid(Integer.parseInt(userid));
					userCashrecord.setUsername(username);
					userCashrecord.setCardnumber(cardnumber);
					userCashrecord.setBank(bank);
					userCashrecord.setAmount(Double.parseDouble(amount));
					//申请中
					userCashrecord.setState(1);
					//userCashrecord.setState(2);
					userCashrecord.setCreatetime(new Date());
					
					// 判断是否存入
					if (userCashrecordService.addUserCashrecord(userCashrecord,userWalletModel)) {
						// 成功
						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
						outJsonMap.put(Constant.DATA, "");
						outJsonMap.put(Constant.TOTAL, 1);
					} else {
						// 失败
						outJsonMap.put(Constant.CODE, Constant.CODE_2000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + "操作失败");
						outJsonMap.put(Constant.DATA, "");
						outJsonMap.put(Constant.TOTAL, 1);
	
					}
				}else{
					outJsonMap.put(Constant.CODE, Constant.CODE_2000);
					outJsonMap.put(Constant.MSG, Constant.MSG_AMOUNT);
					outJsonMap.put(Constant.DATA, "");
					outJsonMap.put(Constant.TOTAL, 1);
				}
			}else{
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

	@SuppressWarnings("unchecked")
	@RequestMapping("TradeRecord")
	/**
	 * 1.17	我的消费记录
	 * @param request
	 * @param response
	 */
	public void TradeRecord(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
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

				// 返回集合
				List<Map<String, Object>> userTradeList = new ArrayList<Map<String, Object>>();

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
						.searchUserWallet(Integer.parseInt(userid), 1);

				if (userWalletModel != null) {
					// 获取的交易记录
					List<UserTradelogModel> userTradelogList = tradeRecordService
							.searchUserTradelog(userWalletModel.getWalletid(),
									pageSize, pageIndex, type, sort);

					if (userTradelogList != null && userTradelogList.size() > 0) {
						for (UserTradelogModel obj : userTradelogList) {

							Map<String, Object> userTradelogMap = new HashMap<String, Object>();

							userTradelogMap.put("ID", ShunJianMeiUtil.intToString(obj.getLogid()));
							// add by jiazhaohui
							// 判断是否是闪惠，还是普通的类型
							if (obj.getQuickpay() > 0)
							{
								UserQuickpayModel uqpModel = userQuickpayService.searchUserQuickpayInfo(obj.getOrderid());
								if (uqpModel != null)
								{
									userTradelogMap.put("OrderCode", uqpModel.getOrdercode());
									userTradelogMap.put("AppointmentTime", 
											ShunJianMeiUtil.dateConvertString(uqpModel.getCreatetime()).substring(0, 10));
									userTradelogMap.put("PayTime", 
											ShunJianMeiUtil.dateConvertString(uqpModel.getCreatetime()));
								}
							}
							else
							{
								// 普通订单类型
								OrderinfoModel orderinfoModel = tradeRecordService.searchOrderinfo(obj.getOrderid());
								if (orderinfoModel != null) {
									userTradelogMap.put("OrderCode", orderinfoModel.getOrdercode());
									userTradelogMap.put("AppointmentTime", orderinfoModel.getAppointmenttime().substring(0, 10));
									userTradelogMap.put("PayTime", ShunJianMeiUtil.dateConvertString(orderinfoModel.getPaytime()));
								}else{
									userTradelogMap.put("OrderCode", "");
									userTradelogMap.put("AppointmentTime", "");
									userTradelogMap.put("PayTime", "");
								}
							}
							
							userTradelogMap.put("Remark", obj.getRemark());
							userTradelogMap.put("Amount", ShunJianMeiUtil
									.floatToString(obj.getAmount()));
							userTradeList.add(userTradelogMap);

						}
						outJsonMap.put(Constant.CODE, Constant.CODE_1000);
						outJsonMap.put(Constant.MSG, Constant.MSG_0);
						outJsonMap.put(Constant.DATA, userTradeList);
						outJsonMap.put(Constant.TOTAL, 1);
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

			} catch (Exception e) {
				// 保存异常信息
				int exceptionId = utilService.addBaseException(
						request.getRequestURI(), request
								.getAttribute

								("inputJsonInfo").toString(),

						e.getMessage());
				// 设置错误id
				outJsonMap.put(Constant.CODE, Constant.CODE_1200);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0 +

				exceptionId);
			}
		}
		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));
		// 输出流
		ShunJianMeiUtil.outputJson(request, response, outJsonMap);

	}

	
	@SuppressWarnings("unchecked")
	@RequestMapping("GetCustomerServicePhone")
	/**
	 * 1.18	获取客服电话
	 * @param request
	 * @param response
	 */
	public void GetCustomerServicePhone(HttpServletRequest request,
			HttpServletResponse response) {
		// 取得请求参数
		Map<String, Object> inputJsonMap = (Map<String, Object>) request.getAttribute(
				"inputJsonMap");
		Map<String, Object> outJsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		try {
			//配置表 客服电话
			BaseConfigModel baseConfig = baseConfigService.searchBaseConfigByCode("service_phone");
			
			if (baseConfig != null) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				
				resultMap.put("CustomerServicePhone", baseConfig.getConfigvalue());
				outList.add(resultMap);
				
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
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 +

			exceptionId);
		}
		
		// 保存请求记录
		utilService.addBaseRequestlog(request.getRequestURI(), request
				.getAttribute("inputJsonInfo").toString(), "",
				ShunJianMeiUtil.outputString(outJsonMap));
		// 输出流
		ShunJianMeiUtil.outputJson(request, response, outJsonMap);

	}
}
