package com.wenyuankeji.spring.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IStorePhotoMappingDao;
import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.dao.IUserCouponsDao;
import com.wenyuankeji.spring.dao.IUserEvaluateDao;
import com.wenyuankeji.spring.dao.IUserEvaluatePhotoMappingDao;
import com.wenyuankeji.spring.dao.IUserPhotoMappingDao;
import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.MaterialinfoModel;
import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.ServiceattributeModel;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.model.UserHairstyleMaterialModel;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserTradelogSearchModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.ICouponsService;
import com.wenyuankeji.spring.service.IMaterialinfoService;
import com.wenyuankeji.spring.service.IOrderPayService;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.service.IServiceattributeService;
import com.wenyuankeji.spring.service.IStoreWorkDateService;
import com.wenyuankeji.spring.service.IStoreWorkHourService;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.service.IUserCouponsService;
import com.wenyuankeji.spring.service.IUserHairpackedService;
import com.wenyuankeji.spring.service.IUserHairstyleMaterialService;
import com.wenyuankeji.spring.service.IUserHairstyleService;
import com.wenyuankeji.spring.service.IUserMyhairstyleService;
import com.wenyuankeji.spring.service.IUserWorkdateService;
import com.wenyuankeji.spring.service.IUserWorkhourService;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.MessagePushUtil;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;
import com.wenyuankeji.spring.util.SmsHelper;

@Service
public class OrderinfoServiceImpl implements IOrderinfoService {

	@Autowired
	private IOrderinfoDao orderinfoDao;

	@Autowired
	private IUserCouponsDao userCouponsDao;

	@Autowired
	private IUsersubinfoDao usersubinfoDao;

	@Autowired
	private IStoreinfoDao storeinfoDao;

	@Autowired
	private IUserPhotoMappingDao userPhotoMappingDao;
	
	@Autowired
	private IUserEvaluatePhotoMappingDao userEvaluatePhotoMappingDao;
	
	@Autowired
	private IStorePhotoMappingDao storePhotoMappingDao;

	@Autowired
	private IUserEvaluateDao userEvaluateDao;

	@Autowired
	private IUserinfoDao userinfoDao;

	@Autowired
	private IUserWorkhourDao userWorkhourDao;

	@Autowired
	private IUserWalletDao userWalletDao;

	@Autowired
	private IUserTradelogDao userTradelogDao;

	@Autowired
	private IUserHairstyleService userHairstyleService;

	@Autowired
	private IUserMyhairstyleService userMyhairstyleService;

	@Autowired
	private IUsersubinfoService usersubinfoService;

	@Autowired
	private IServiceattributeService serviceattributeService;

	@Autowired
	private IStoreinfoService storeinfoService;

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
	private IOrderPayService orderPayService;

	@Autowired
	private IUserHairstyleMaterialService userHairstyleMaterialService;

	@Autowired
	private IMaterialinfoService materialinfoService;
	
	@Autowired
	private IStoreWorkDateService storeWorkDateService;
	
	@Autowired
	private IStoreWorkHourService storeWorkHourService;
	
	@Autowired
	private IUserHairpackedService userHairpackedService;
	

	@Value("#{configProperties['ipHost']}")
	private String ipHost;
	
	@Value("#{configProperties['testing']}")
	private String testing;

	@Override
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException {

		return orderinfoDao.searchOrderinfo(orderid);
	}

	@Override
	public int addOrderinfo(OrderinfoModel orderinfo,
			Set<OrderdetailModel> orderdetails,
			List<UserCouponsModel> userCouponsList, String StoreID, double Times, String WHID) throws BaseException {
		int orderid = 0;
		try {
			// 增加订单
			orderid = orderinfoDao.addOrderinfo(orderinfo, StoreID, Times, WHID);
			// 增加订单详细
			if (orderid > 0) {
				if (orderinfoDao.addOrderdetail(orderdetails, orderid)) {
					// 添加我的优惠券
					if (userCouponsList != null && userCouponsList.size() > 0) {
						for (UserCouponsModel userCouponsModel : userCouponsList) {
							// 修改我的优惠券
							// userCouponsModel.setOrderid(orderid);
							// userCouponsDao.addUserCoupons(userCouponsModel);
							userCouponsDao.updateUserCoupons(
									userCouponsModel.getId(), orderid);
						}
					}
				}
			}
		}catch(Exception e){
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return orderid;
	}

	@Override
	public List<OrderinfoModel> searchOrderinfoStoreId(int storeid, int page,
			int rows) throws BaseException {
		return orderinfoDao.searchOrderinfoStoreId(storeid, page, rows);
	}

	@Override
	public List<UserTradelogSearchModel> searchOrderinfoByStoreId(int storeid,
			int orderByType, int orderByVal, int page, int rows)
			throws BaseException {

		return orderinfoDao.searchOrderinfoByStoreId(storeid, orderByType,
				orderByVal, page, rows);
	};

	@Override
	public int searchOrderinfoByStoreId(int storeid, int orderByType,
			int orderByVal) throws BaseException {

		return orderinfoDao.searchOrderinfoByStoreId(storeid, orderByType,
				orderByVal);
	};

	@Override
	public int searchOrderinfoStoreId(int storeid) throws BaseException {

		return orderinfoDao.searchOrderinfoStoreId(storeid);
	}

	@Override
	public boolean updateOrderinfoModel(int orderid, int paystate)
			throws BaseException {
		if (paystate == 9) {
			OrderinfoModel orderinfo = orderinfoDao.searchOrderinfo(orderid);
			if (orderinfo.getPaystate() < 4) {
				if (orderinfo.getPaystate() == 1) {
					orderinfoDao.updateOrderinfoModel(orderid, 9);
					// userWorkhourDao.updateUserWorkhour(orderid, 0);
					userWorkhourDao.updateUserWorkhour(orderid);
					userWorkhourDao.updateStoreWorkhour(orderid);
				} else {
					orderinfoDao.updateOrderinfoModel(orderid, 9);
					// userWorkhourDao.updateUserWorkhour(orderid, 0);
					// 释放美发师工位工时
					userWorkhourDao.updateUserWorkhour(orderid);
					// 释放美发店工位工时
					userWorkhourDao.updateStoreWorkhour(orderid);
					UserWalletModel userWalletModel = userWalletDao
							.searchUserWallet(orderinfo.getCustomerid(), 1);
					if (userWalletModel == null) {
						userWalletModel = new UserWalletModel();
						userWalletModel.setCreatetime(new Date());
						userWalletModel.setUpdatetime(new Date());
						userWalletModel.setOwnertype(1);
						userWalletModel.setOwnerid(orderinfo.getCustomerid());
						userWalletModel.setBalance((float) orderinfo
								.getAmount());
						userWalletModel = userWalletDao
								.addUserWallet(userWalletModel);
					} else {
						userWalletModel.setBalance(userWalletModel.getBalance()
								+ (float) orderinfo.getAmount());
						userWalletModel.setOwnertype(1);
						userWalletDao.updateUserWalletByUserId(userWalletModel);
					}
					UserTradelogModel userTradelogModel = new UserTradelogModel();
					userTradelogModel.setCreatetime(new Date());
					userTradelogModel.setLogtype(5);// 5退款
					userTradelogModel.setOrderid(orderid);
					userTradelogModel
							.setWalletid(userWalletModel.getWalletid());
					userTradelogModel.setRemark("订单取消退款");
					userTradelogModel.setAmount((float) orderinfo.getAmount());
					userTradelogDao.addUserTradelog(userTradelogModel);
				}
				// 退回优惠券
				userCouponsDao.updateUserCoupons(orderid);
			}

			// 用户取消时推送消息
			//////////////---------推送消息-begin--------//////////////////////
			List<String> lsUserList = new ArrayList<String>();
			String content = "很遗憾，有用户取消了订单。";
			
			// 获取美发师的id
			lsUserList.add(String.valueOf(orderinfo.getUserid()));
			
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			 jsonMap.put("type", 1); jsonMap.put("dealid",orderinfo.getOrderid());
			MessagePushUtil.SendPush(lsUserList,jsonMap,content,2);
	        //////////////---------推送消息-end--------////////////////////////
			return true;
		} else if (paystate == 10 || paystate == 4 || paystate == 5
				|| paystate == 6) {
			orderinfoDao.updateOrderinfoModel(orderid, paystate);
			return true;
		}

		else {
			return false;
		}
		// 更新订单状态
		// if (orderinfoDao.updateOrderinfoModel(orderid, paystate)) {
		// // 服务完成的场合：更新我的发行表的使用人数
		// if (6 == paystate) {
		// OrderinfoModel orderinfo = orderinfoDao
		// .searchOrderinfo(orderid);
		// // 更新接单数
		// usersubinfoDao.updateOrdernum(orderinfo.getUserid());
		//
		// // 更新发型使用数量
		// userHairstyleDao.updateUsednum(orderinfo.getHairstyleid());
		// }
		//
		// return true;
		// }
	}

	@Override
	public boolean updateOrderinfoPaystate(int orderid, int paystate)
			throws BaseException {
		boolean result = false;
		try{
			OrderinfoModel orderinfo = orderinfoDao.searchOrderinfo(orderid);
			// 更新订单状态
			if (orderinfoDao.updateOrderinfoModel(orderid, paystate)) {
				// 增加接单量
				if (paystate == 6) {
					orderinfoDao.updateCountUsedNum(orderinfo.getHairstyleid());
					orderinfoDao.updateCountOrderNum(orderinfo.getUserid());
					orderinfoDao.updateCountStoreNum(orderinfo.getStoreid());
					result = true;
				}
				// 美发师到店签到时推送消息
				if (paystate == 4) {
					//////////////---------推送消息-begin--------//////////////////////
					List<String> lsUserList = new ArrayList<String>();
					String content = "您预约的美发师已经到店等候了~";
					
					// 获取用户的id
					lsUserList.add(String.valueOf(orderinfo.getCustomerid()));
					
					Map<String,Object> jsonMap = new HashMap<String,Object>();
					 jsonMap.put("type", 1); 
					 jsonMap.put("dealid",orderid);
					MessagePushUtil.SendPush(lsUserList,jsonMap,content,1);
			        //////////////---------推送消息-end--------////////////////////////
				}
				result = true;
			}
		}catch(Exception e){
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			utilService.addBaseException(
					"updateOrderinfoPaystate","",e.getMessage());
		}
		return result;
	}

	@Override
	public List<Map<String, String>> searchOrderList(String userID,
			String state, String sort, int pageIndex, int pageSize)
			throws BaseException {
		List<OrderinfoModel> myOrderinfoList = orderinfoDao.searchOrderList(
				userID, state, sort, pageIndex, pageSize);
		if (null != myOrderinfoList && myOrderinfoList.size() > 0) {

			List<Map<String, String>> myOrderListMap = new ArrayList<Map<String, String>>();

			for (OrderinfoModel o : myOrderinfoList) {
				
				Map<String, String> myOrderMap = new HashMap<String, String>();

				// 订单ID
				myOrderMap.put("OrderID",
						ShunJianMeiUtil.intToString(o.getOrderid()));
				// 订单编号
				myOrderMap.put("OrderCode", o.getOrdercode());
				
				// 发型师名字
				myOrderMap.put("Name", o.getUsersubinfo_name());
				// 发型师头像
				UserPhotoMappingModel upm = userPhotoMappingDao
						.searchUserPhoto(o.getUserid(), 1);
				if (upm != null) {
					myOrderMap.put("Photo", ipHost
							+ upm.getBasePicture().getPicturepath());
				} else {
					myOrderMap.put("Photo", "");
				}

				// 发型师等级
				UsersubinfoModel sbm = usersubinfoDao.searchUsersubinfo(o
						.getUserid());
				if (sbm != null) {
					myOrderMap.put("LevelName", sbm.getUserProfessionLevel()
							.getLevelname());
				} else {
					myOrderMap.put("LevelName", "");
				}
				// 发型师电话
				myOrderMap.put("Mobile", o.getUsersubinfo_contactmobile());
				// 预约时间
				myOrderMap.put("AppointmentTime", 
						ShunJianMeiUtil.getAppointmentString(o.getAppointmenttime()) );
				// 状态
				myOrderMap.put("State",
						ShunJianMeiUtil.intToString(o.getPaystate()));
				// 状态名称
				myOrderMap.put("StateName",
						Constant.PAYSTATE_MAP.get(o.getPaystate()));
				// 发型项目名称
				myOrderMap.put("StyleName", o.getStylename());
				// 商家名称
				myOrderMap.put("StoreName", o.getStoreinfo_name());
				// 商家电话
				myOrderMap.put("StoreTel", o.getStoreinfo_tel());
				// 商家地址
				myOrderMap.put("Address", o.getStoreinfo_address());
				// 支付金额
				myOrderMap.put("Amount",
						ShunJianMeiUtil.floatToString(o.getAmount()));
				myOrderMap.put("ishairpacked", o.getIshairpacked()+"");
				myOrderMap.put("userid", o.getUserid()+"");

				myOrderListMap.add(myOrderMap);
			}

			return myOrderListMap;
		}

		return null;
	}

	@Override
	public boolean updateOrderinfo(int orderid, int customerid,
			String additionalcontent, double additionalamount)
			throws BaseException {

		return orderinfoDao.updateOrderinfo(orderid, customerid,
				additionalcontent, additionalamount);
	}

	@Override
	public List<Map<String, Object>> searchOrderDetail(String userID,
			String orderID) throws BaseException {

		try {
			List<Map<String, Object>> orderDetailList = new ArrayList<Map<String, Object>>();

			// 获取订单基本信息
			OrderinfoModel orderinfo = orderinfoDao.searchOrderinfo(Integer
					.parseInt(orderID));

			Map<String, Object> orderinfoMap = new HashMap<String, Object>();
			orderinfoMap.put("OrderID",
					ShunJianMeiUtil.intToString(orderinfo.getOrderid()));
			orderinfoMap.put("OrderCode", orderinfo.getOrdercode());
			// 状态
			orderinfoMap.put("State",
					ShunJianMeiUtil.intToString(orderinfo.getPaystate()));
			// 状态名称
			orderinfoMap.put("StateName",
					Constant.PAYSTATE_MAP.get(orderinfo.getPaystate()));
			
			if (Constant.NEXT_STEP_MAP.get(orderinfo.getPaystate()) != null) {
				orderinfoMap.put("NextStep",
						Constant.NEXT_STEP_MAP.get(orderinfo.getPaystate()));
			} else {
				orderinfoMap.put("NextStep", "");
			}

			// 预约服务时间
			orderinfoMap.put("AppointmentTime", 
					ShunJianMeiUtil.getAppointmentString(orderinfo.getAppointmenttime()));

			// 发型师ID
			orderinfoMap.put("ID",
					ShunJianMeiUtil.intToString(orderinfo.getUserid()));
			// 发型师名字
			UserinfoModel userinfo = userinfoDao.searchUserinfoById(orderinfo
					.getUserid());
			if (userinfo != null) {
				orderinfoMap.put("Name", userinfo.getNickname());
			} else {
				orderinfoMap.put("Name", "");
			}
			// 发型师头像
			UserPhotoMappingModel upm = userPhotoMappingDao.searchUserPhoto(
					orderinfo.getUserid(), 1);
			if (upm != null) {
				orderinfoMap.put("Photo", ipHost
						+ upm.getBasePicture().getPicturepath());
			} else {
				orderinfoMap.put("Photo", "");
			}
			// 发型师等级
			UsersubinfoModel sbm = usersubinfoDao.searchUsersubinfo(orderinfo
					.getUserid());
			if (sbm != null) {
				orderinfoMap.put("LevelName", sbm.getUserProfessionLevel()
						.getLevelname());
			} else {
				orderinfoMap.put("LevelName", "");
			}
			// 发型师电话
			orderinfoMap
					.put("Mobile", orderinfo.getUsersubinfo_contactmobile());

			// 获取发型师详细信息
			UsersubinfoModel usersubinfo = usersubinfoDao
					.searchUsersubinfo(orderinfo.getUserid());

			if (null != usersubinfo) {
				orderinfoMap.put("Score",
						ShunJianMeiUtil.floatToString(usersubinfo.getScore()));
				orderinfoMap.put("OrderNum",
						ShunJianMeiUtil.intToString(usersubinfo.getOrdernum()));
			} else {
				orderinfoMap.put("Score", "4");
				orderinfoMap.put("OrderNum", "");
			}

			// 美发店ID
			orderinfoMap.put("StoreID",
					ShunJianMeiUtil.intToString(orderinfo.getStoreid()));
			// 美发店信息
			orderinfoMap.put("StoreName", orderinfo.getStoreinfo_name());
			// 店铺头像
			StorePhotoMappingModel storePhoto = storePhotoMappingDao
					.searchStorePhoto(orderinfo.getStoreid(), 1);
			if (null != storePhoto) {
				orderinfoMap.put("StorePhoto", ipHost
						+ storePhoto.getBasePicture().getPicturepath());
			} else {
				orderinfoMap.put("StorePhoto", "");
			}

			// 商家电话
			orderinfoMap.put("StoreTel", orderinfo.getStoreinfo_tel());
			// 取得美发店信息
			StoreinfoModel storeinfo = storeinfoDao.searchStoreinfo(orderinfo
					.getStoreid());
			if (null != storeinfo) {
				orderinfoMap.put("StoreScore",
						ShunJianMeiUtil.floatToString(storeinfo.getScore()));
				orderinfoMap.put("StoreOrderNum", ShunJianMeiUtil
						.intToString(storeinfo.getOrderquantity()));
				orderinfoMap.put("CarNum",
						ShunJianMeiUtil.intToString(storeinfo.getCarnumber()));
			} else {
				orderinfoMap.put("StoreScore", "");
				orderinfoMap.put("StoreOrderNum", "");
				orderinfoMap.put("CarNum", "");
			}
			// 商家地址
			orderinfoMap.put("Address", orderinfo.getStoreinfo_address());
			orderinfoMap.put("StyleName", orderinfo.getStylename());

			// 服务名称
			List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();

			List<String> servicenameList = orderinfoDao
					.searchOrderServicenameList(orderID);
			if (null != servicenameList && servicenameList.size() > 0) {
				for (String servicename : servicenameList) {
					Map<String, String> itemMap = new HashMap<String, String>();
					itemMap.put("Name", servicename);
					itemList.add(itemMap);
				}
			}
			orderinfoMap.put("Item", itemList);

			// 加单编号
			orderinfoMap.put(
					"AdditionalCode",
					orderinfo.getAdditionalcode() != null ? orderinfo
							.getAdditionalcode() : "");
			// 加单内容
			orderinfoMap.put(
					"AdditionalContent",
					orderinfo.getAdditionalcontent() != null ? orderinfo
							.getAdditionalcontent() : "");
			// 加单金额
			orderinfoMap.put("AdditionalAmount",
					ShunJianMeiUtil.floatToString(orderinfo
							.getAdditionalamount() != null ? orderinfo
							.getAdditionalamount() : 0));
			// 加单状态
			orderinfoMap
					.put("AdditionalState", ShunJianMeiUtil
							.intToString(orderinfo.getAdditionalstate()));

			// 金额
			orderinfoMap.put("Amount",
					ShunJianMeiUtil.floatToString(orderinfo.getAmount()));
			// 优惠
			orderinfoMap.put("Discount",
					ShunJianMeiUtil.floatToString(orderinfo.getCouponamount()));

			// 查询评论者的名字已经头像地址
			UserinfoModel user = userinfoDao.searchUserinfoById(Integer
					.parseInt(userID));
			String userNmae = "";
			String userPhotoPath = "";
			if (null != user) {
				userNmae = user.getNickname();
				UserPhotoMappingModel userPhoto = userPhotoMappingDao
						.searchUserPhoto(Integer.parseInt(userID), 1);
				if (null != userPhoto) {
					userPhotoPath = userPhoto.getBasePicture().getPicturepath();
				}
			}

			// ///////////美发师的评价//////////////////////
			Map<String, Object> commentMap = new HashMap<String, Object>();
			// 查询对美发师的评价
			UserEvaluateModel userEvaluate1 = userEvaluateDao
					.searchUserEvaluate(Integer.parseInt(userID),
							orderinfo.getUserid(), Integer.parseInt(orderID), 1);
			if (null != userEvaluate1) {
				commentMap.put("ID",
						ShunJianMeiUtil.intToString(userEvaluate1.getEvaid()));

				commentMap.put("Name", userNmae);
				commentMap.put("Photo", ipHost + userPhotoPath);
				commentMap.put("Time", ShunJianMeiUtil
						.dateConvertString(userEvaluate1.getCreatetime()));
				commentMap.put("Content", userEvaluate1.getContent());
				commentMap
						.put("Score", ShunJianMeiUtil
								.floatToString(userEvaluate1.getScore()));

				// 设置评论图片地址
				List<Map<String, String>> commentItemList = new ArrayList<Map<String, String>>();

				List<String> contentItem = userEvaluatePhotoMappingDao
						.searchUserEvaluateicturepathList(userEvaluate1
								.getEvaid());

				if (contentItem != null && contentItem.size() > 0) {
					for (String path : contentItem) {
						Map<String, String> commentItemMap = new HashMap<String, String>();
						commentItemMap.put("Photo", ipHost + path);
						commentItemList.add(commentItemMap);
					}

				}
				commentMap.put("Item", commentItemList);
			}
			orderinfoMap.put("Comment", commentMap);
			// ///////////////////////////////////

			// ///////////店铺的评论/////////////////
			Map<String, Object> storeCommentMap = new HashMap<String, Object>();
			// 查询店铺的评价
			UserEvaluateModel userEvaluate2 = userEvaluateDao
					.searchUserEvaluate(Integer.parseInt(userID),
							orderinfo.getStoreid(), Integer.parseInt(orderID),
							2);

			if (null != userEvaluate2) {
				storeCommentMap.put("ID",
						ShunJianMeiUtil.intToString(userEvaluate2.getEvaid()));
				storeCommentMap.put("Name", userNmae);
				storeCommentMap.put("Photo", ipHost + userPhotoPath);
				storeCommentMap.put("Time", ShunJianMeiUtil
						.dateConvertString(userEvaluate2.getCreatetime()));
				storeCommentMap.put("Content", userEvaluate2.getContent());
				storeCommentMap
						.put("Score", ShunJianMeiUtil
								.floatToString(userEvaluate2.getScore()));

				// 设置评论图片地址
				List<Map<String, String>> storeCommentItemList = new ArrayList<Map<String, String>>();

				List<String> storeContentItem = userEvaluatePhotoMappingDao
						.searchUserEvaluateicturepathList(userEvaluate2
								.getEvaid());

				if (storeContentItem != null && storeContentItem.size() > 0) {
					for (String path : storeContentItem) {
						Map<String, String> storeCommentItemMap = new HashMap<String, String>();
						storeCommentItemMap.put("Photo", ipHost + path);
						storeCommentItemList.add(storeCommentItemMap);
					}

				}
				storeCommentMap.put("Item", storeCommentItemList);

			}

			orderinfoMap.put("StoreComment", storeCommentMap);

			// //////////////////////////////////
			//添加istype属性
			orderinfoMap.put("istype", usersubinfo.getIstype());
			orderDetailList.add(orderinfoMap);
			return orderDetailList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<Map<String, String>> searchOrderListByUserID(String userID,
			String state, String sort, int pageIndex, int pageSize)
			throws BaseException {

		List<OrderinfoModel> myOrderinfoList = orderinfoDao
				.searchOrderListByUserID(userID, state, sort, pageIndex,
						pageSize);
		if (null != myOrderinfoList && myOrderinfoList.size() > 0) {

			List<Map<String, String>> myOrderListMap = new ArrayList<Map<String, String>>();

			for (OrderinfoModel o : myOrderinfoList) {

				Map<String, String> myOrderMap = new HashMap<String, String>();

				// 查询顾客信息
				UserinfoModel userinfo = userinfoDao.searchUserinfoById(o
						.getCustomerid());

				// 订单ID
				myOrderMap.put("OrderID",
						ShunJianMeiUtil.intToString(o.getOrderid()));
				// 订单编号
				myOrderMap.put("OrderCode", o.getOrdercode());
		
			

				if (null != userinfo) {
					// 顾客名字
					myOrderMap.put("Name", userinfo.getNickname());
					// 发型师头像
					UserPhotoMappingModel upm = userPhotoMappingDao
							.searchUserPhoto(o.getCustomerid(), 1);
					if (upm != null) {
						myOrderMap.put("Photo", ipHost
								+ upm.getBasePicture().getPicturepath());
					} else {
						myOrderMap.put("Photo", "");
					}

					// 顾客电话
					String mobile = userinfo.getBindphone();
					if (!mobile.equals("")) {
						myOrderMap.put("Mobile", userinfo.getBindphone());
					} else {
						myOrderMap.put("Mobile", userinfo.getUsername());
					}

				} else {
					myOrderMap.put("Name", "");
					myOrderMap.put("Photo", "");
					myOrderMap.put("Mobile", "");
				}

				// 预约时间
				myOrderMap.put("AppointmentTime", 
						ShunJianMeiUtil.getAppointmentString(o.getAppointmenttime()));
				// 状态
				myOrderMap.put("State",
						ShunJianMeiUtil.intToString(o.getPaystate()));
				// 状态名称
				myOrderMap.put("StateName",
						Constant.PAYSTATE_MAP.get(o.getPaystate()));
				// 发型项目名称
				myOrderMap.put("StyleName", o.getStylename());
				// 商家名称
				myOrderMap.put("StoreName", o.getStoreinfo_name());
				// 商家电话
				myOrderMap.put("StoreTel", o.getStoreinfo_tel());
				// 商家地址
				myOrderMap.put("Address", o.getStoreinfo_address());
				// 支付金额
				myOrderMap.put("Amount",
						ShunJianMeiUtil.floatToString(o.getAmount()));

				myOrderListMap.add(myOrderMap);
			}

			return myOrderListMap;
		}

		return null;

	}

	@Override
	public List<Map<String, Object>> searchOrderDetailForMeiFaShi(
			String userID, String orderID) throws BaseException {

		List<Map<String, Object>> orderDetailList = new ArrayList<Map<String, Object>>();

		// 获取订单基本信息
		OrderinfoModel orderinfo = orderinfoDao.searchOrderinfo(Integer
				.parseInt(orderID));

		Map<String, Object> orderinfoMap = new HashMap<String, Object>();
		orderinfoMap.put("OrderID",
				ShunJianMeiUtil.intToString(orderinfo.getOrderid()));
		orderinfoMap.put("OrderCode", orderinfo.getOrdercode());
		// 状态
		orderinfoMap.put("State",
				ShunJianMeiUtil.intToString(orderinfo.getPaystate()));
		// 状态名称
		orderinfoMap.put("StateName",
				Constant.PAYSTATE_MAP.get(orderinfo.getPaystate()));

		if (Constant.NEXT_STEP_MAP.get(orderinfo.getPaystate()) != null) {
			orderinfoMap.put("NextStep",
					Constant.NEXT_STEP_MAP.get(orderinfo.getPaystate()));
		} else {
			orderinfoMap.put("NextStep", "");
		}

		orderinfoMap.put("AppointmentTime", 
				ShunJianMeiUtil.getAppointmentString(orderinfo.getAppointmenttime()));

		// 获取顾客信息
		UserinfoModel user = userinfoDao.searchUserinfoById(orderinfo
				.getCustomerid());

		if (null != user) {
			// 顾客名字
			orderinfoMap.put("Name", user.getNickname());
			// 顾客头像
			UserPhotoMappingModel upm = userPhotoMappingDao.searchUserPhoto(
					user.getUserid(), 1);
			if (upm != null) {
				orderinfoMap.put("Photo", ipHost
						+ upm.getBasePicture().getPicturepath());
			} else {
				orderinfoMap.put("Photo", "");
			}
			// 顾客电话
			orderinfoMap.put("Mobile", user.getUsername());
			//String mobile = user.getBindphone();
			/*if (!mobile.equals("")) {
				orderinfoMap.put("Mobile", user.getBindphone());
			} else {
				orderinfoMap.put("Mobile", user.getUsername());
			}*/

		} else {
			// 顾客名字
			orderinfoMap.put("Name", "");
			// 顾客头像
			orderinfoMap.put("Photo", "");
			// 顾客电话
			orderinfoMap.put("Mobile", "");
		}

		// 获取发型师详细信息
		UsersubinfoModel usersubinfo = usersubinfoDao
				.searchUsersubinfo(orderinfo.getUserid());

		if (null != usersubinfo) {
			orderinfoMap.put("OrderNum",
					ShunJianMeiUtil.intToString(usersubinfo.getOrdernum()));
		} else {
			orderinfoMap.put("OrderNum", "");
		}

		// 美发店信息

		orderinfoMap.put("StoreID",
				ShunJianMeiUtil.intToString(orderinfo.getStoreid()));
		orderinfoMap.put("StoreName", orderinfo.getStoreinfo_name());
		// 店铺头像
		StorePhotoMappingModel storePhoto = storePhotoMappingDao
				.searchStorePhoto(orderinfo.getStoreid(), 1);
		if (null != storePhoto) {
			orderinfoMap.put("StorePhoto", ipHost
					+ storePhoto.getBasePicture().getPicturepath());
		} else {
			orderinfoMap.put("StorePhoto", "");
		}

		// 商家电话
		orderinfoMap.put("StoreTel", orderinfo.getStoreinfo_tel());
		// 取得美发店信息
		StoreinfoModel storeinfo = storeinfoDao.searchStoreinfo(orderinfo
				.getStoreid());
		if (null != storeinfo) {
			orderinfoMap.put("StoreScore",
					ShunJianMeiUtil.floatToString(storeinfo.getScore()));
			orderinfoMap.put("StoreOrderNum",
					ShunJianMeiUtil.intToString(storeinfo.getOrderquantity()));
			orderinfoMap.put("CarNum",
					ShunJianMeiUtil.intToString(storeinfo.getCarnumber()));
		} else {
			orderinfoMap.put("StoreScore", "");
			orderinfoMap.put("StoreOrderNum", "");
			orderinfoMap.put("CarNum", "");
		}
		// 商家地址
		orderinfoMap.put("Address", orderinfo.getStoreinfo_address());
		orderinfoMap.put("StyleName", orderinfo.getStylename());

		// 服务名称
		List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();

		List<String> servicenameList = orderinfoDao
				.searchOrderServicenameList(orderID);
		if (null != servicenameList && servicenameList.size() > 0) {
			for (String servicename : servicenameList) {
				Map<String, String> itemMap = new HashMap<String, String>();
				itemMap.put("Name", servicename);
				itemList.add(itemMap);
			}
		}
		orderinfoMap.put("Item", itemList);

		// 加单内容
		String additionalcontent = orderinfo.getAdditionalcontent();
		if (additionalcontent != null) {
			orderinfoMap.put("AdditionalContent",
					orderinfo.getAdditionalcontent());
		} else {
			orderinfoMap.put("AdditionalContent", "");
		}
		// 加单金额
		Double additionalamount = orderinfo.getAdditionalamount();
		if (additionalamount != null) {
			orderinfoMap.put("AdditionalAmount", ShunJianMeiUtil
					.floatToString(orderinfo.getAdditionalamount()));
		} else {
			orderinfoMap.put("AdditionalAmount", "");
		}
		// 金额
		Double amount = orderinfo.getAmount();
		if (amount != null) {
			orderinfoMap.put("Amount",
					ShunJianMeiUtil.floatToString(orderinfo.getAmount()));
		} else {
			orderinfoMap.put("Amount", "");
		}
		// 加单支付状态
		orderinfoMap.put("AdditionalState",
				ShunJianMeiUtil.intToString(orderinfo.getAdditionalstate()));
		// 优惠
		Double couponamount = orderinfo.getCouponamount();
		if (couponamount != null) {
			orderinfoMap.put("Discount",
					ShunJianMeiUtil.floatToString(orderinfo.getCouponamount()));
		} else {
			orderinfoMap.put("Discount", "");
		}

		// 查询评论者的名字以及头像地址
		// UserinfoModel user =
		// userinfoDao.searchUserinfoById(Integer.parseInt(userID));
		String userNmae = "";
		String userPhotoPath = "";
		if (null != user) {
			userNmae = user.getNickname();
			UserPhotoMappingModel userPhoto = userPhotoMappingDao
					.searchUserPhoto(user.getUserid(), 1);
			if (null != userPhoto) {
				userPhotoPath = userPhoto.getBasePicture().getPicturepath();
			}
		}

		// ///////////美发师的评价//////////////////////
		Map<String, Object> commentMap = new HashMap<String, Object>();
		// 查询对美发师的评价
		UserEvaluateModel userEvaluate1 = userEvaluateDao.searchUserEvaluate(
				user.getUserid(), orderinfo.getUserid(),
				Integer.parseInt(orderID), 1);
		if (null != userEvaluate1) {
			commentMap.put("ID",
					ShunJianMeiUtil.intToString(userEvaluate1.getEvaid()));

			commentMap.put("Name", userNmae);
			commentMap.put("Photo", ipHost + userPhotoPath);
			commentMap.put("Time", ShunJianMeiUtil
					.dateConvertString(userEvaluate1.getCreatetime()));
			commentMap.put("Content", userEvaluate1.getContent());
			commentMap.put("Score",
					ShunJianMeiUtil.floatToString(userEvaluate1.getScore()));

			// 设置评论图片地址
			List<Map<String, String>> commentItemList = new ArrayList<Map<String, String>>();
			// 更具评论id查询，评论配图的路径
			List<String> contentItem = userEvaluatePhotoMappingDao
					.searchUserEvaluateicturepathList(userEvaluate1.getEvaid());

			if (contentItem != null && contentItem.size() > 0) {
				for (String path : contentItem) {
					Map<String, String> commentItemMap = new HashMap<String, String>();
					commentItemMap.put("Photo", ipHost + path);
					commentItemList.add(commentItemMap);
				}

			}
			commentMap.put("Item", commentItemList);
		}
		orderinfoMap.put("Comment", commentMap);
		// ///////////////////////////////////

		// ///////////店铺的评论/////////////////
		Map<String, Object> storeCommentMap = new HashMap<String, Object>();
		// 查询店铺的评价
		UserEvaluateModel userEvaluate2 = userEvaluateDao.searchUserEvaluate(
				user.getUserid(), orderinfo.getStoreid(),
				Integer.parseInt(orderID), 2);

		if (null != userEvaluate2) {
			storeCommentMap.put("ID",
					ShunJianMeiUtil.intToString(userEvaluate2.getEvaid()));
			storeCommentMap.put("Name", userNmae);
			storeCommentMap.put("Photo", ipHost + userPhotoPath);
			storeCommentMap.put("Time", ShunJianMeiUtil
					.dateConvertString(userEvaluate2.getCreatetime()));
			storeCommentMap.put("Content", userEvaluate2.getContent());
			storeCommentMap.put("Score",
					ShunJianMeiUtil.floatToString(userEvaluate2.getScore()));

			// 设置评论图片地址
			List<Map<String, String>> storeCommentItemList = new ArrayList<Map<String, String>>();

			List<String> storeContentItem = userEvaluatePhotoMappingDao
					.searchUserEvaluateicturepathList(userEvaluate2.getEvaid());

			if (storeContentItem != null && storeContentItem.size() > 0) {
				for (String path : storeContentItem) {
					Map<String, String> storeCommentItemMap = new HashMap<String, String>();
					storeCommentItemMap.put("Photo", ipHost + path);
					storeCommentItemList.add(storeCommentItemMap);
				}

			}
			storeCommentMap.put("Item", storeCommentItemList);

		}

		orderinfoMap.put("StoreComment", storeCommentMap);

		// //////////////////////////////////

		orderDetailList.add(orderinfoMap);
		return orderDetailList;

	}

	@Override
	public List<Map<String, Object>> searchOrderinfoByUseridAndAppointmenttime(
			String userID, String appointmenttime) throws BaseException {

		// 查询预约订单信息
		List<OrderinfoModel> orderinfos = orderinfoDao
				.searchOrderinfoByUseridAndAppointmenttime(userID,
						appointmenttime);

		if (null != orderinfos && orderinfos.size() > 0) {
 
			List<Map<String, Object>> orderDetailList = new ArrayList<Map<String, Object>>();
			// 设置返回内容
			for (OrderinfoModel orderinfo : orderinfos) {

				Map<String, Object> orderinfoMap = new HashMap<String, Object>();

				orderinfoMap.put("OrderID",
						ShunJianMeiUtil.intToString(orderinfo.getOrderid()));
				orderinfoMap.put("OrderCode", orderinfo.getOrdercode());

				// 获取顾客信息
				UserinfoModel user = userinfoDao.searchUserinfoById(orderinfo
						.getCustomerid());

				if (null != user) {
					// 顾客名字
					orderinfoMap.put("Name", user.getNickname());
					// 顾客头像
					UserPhotoMappingModel upm = userPhotoMappingDao
							.searchUserPhoto(orderinfo.getCustomerid(), 1);
					if (upm != null) {
						orderinfoMap.put("Photo", ipHost
								+ upm.getBasePicture().getPicturepath());
					} else {
						orderinfoMap.put("Photo", "");
					}
					// 顾客电话
					orderinfoMap.put("Mobile", user.getBindphone());
				} else {
					// 顾客名字
					orderinfoMap.put("Name", "");
					// 顾客头像
					orderinfoMap.put("Photo", "");
					// 顾客电话
					orderinfoMap.put("Mobile", "");
				}

				// 预约时间
				orderinfoMap.put("AppointmentTime",
						ShunJianMeiUtil.getAppointmentString(orderinfo.getAppointmenttime()));

				// 状态
				orderinfoMap.put("State",
						ShunJianMeiUtil.intToString(orderinfo.getPaystate()));
				// 状态名称
				orderinfoMap.put("StateName",
						Constant.PAYSTATE_MAP.get(orderinfo.getPaystate()));
				// 发型项目名称
				orderinfoMap.put("StyleName", orderinfo.getStylename());

				// 美发店名称
				orderinfoMap.put("StoreName", orderinfo.getStoreinfo_name());
				// 商家电话
				orderinfoMap.put("StoreTel", orderinfo.getStoreinfo_tel());
				// 金额
				orderinfoMap.put("Amount",
						ShunJianMeiUtil.floatToString(orderinfo.getAmount()));
				// 商家地址
				orderinfoMap.put("Address", orderinfo.getStoreinfo_address());

				orderDetailList.add(orderinfoMap);

			}

			return orderDetailList;

		} else {
			return null;
		}

	}

	@Override
	public List<OrderinfoModel> searchOrderinfoByWdid(int wdid)
			throws BaseException {
		try {

			// 更具工作日id查询订单id
			List<Integer> orderIDs = orderinfoDao.searchOrderIdByWdid(wdid);

			if (orderIDs != null && orderIDs.size() > 0) {

				StringBuffer ordersBuffer = new StringBuffer();

				for (Integer id : orderIDs) {
					if (ordersBuffer.length() > 0) {
						ordersBuffer.append(",");
					}
					ordersBuffer.append("'" + id + "'");
				}
				// 查询订单信息
				return orderinfoDao.searchOrderinfo(ordersBuffer.toString());
			}

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateOrderinfoCouponAmount(int orderid,
			double couponamount, double amount) throws BaseException {

		return orderinfoDao.updateOrderinfoCouponAmount(orderid, couponamount,
				amount);
	}

	@Override
	public List<OrderinfoModel> searchOrderInfoWaitTime(String ordertime)
			throws BaseException {
		return orderinfoDao.searchOrderInfoWaitTime(ordertime);
	}

	@Override
	public boolean updateOrderinfoState(int orderid, int paystate)
			throws BaseException {
		if (orderinfoDao.updateOrderinfoModel(orderid, paystate)) {// 修改订单表状态为已取消
			if (userWorkhourDao.updateUserWorkhour(orderid)) {// 释放美发师工位工时
				if (userWorkhourDao.updateStoreWorkhour(orderid)) {// 释放美发店工位工时
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateAdditionalInfo(String additionalcode)
			throws BaseException {
		return orderinfoDao.updateAdditionalInfo(additionalcode);
	}

	@Override
	public List<OrderinfoModel> searchOrderInfoByAllocationtime(
			String allocationtime) throws BaseException {
		return orderinfoDao.searchOrderInfoByAllocationtime(allocationtime);
	}

	@Override
	public int getMaterialidpriceTotal(int orderid) throws BaseException {
		return orderinfoDao.getMaterialidpriceTotal(orderid);
	}

	@Override
	public OrderinfoModel searchOrderinfoByAdditionalcode(String additionalcode)
			throws BaseException {
		return orderinfoDao.searchOrderinfoByAdditionalcode(additionalcode);
	}

	@Override
	public Map<String, Object> addCommitOrder(Map<String, Object> inputJsonMap,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		Map<String, Object> outJsonMap = new HashMap<String, Object>();
		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();

		try {
			Date createtime = new Date();
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
			String ishairpacked = "";
			if(inputJsonMap.get("type") == null){
				ishairpacked = "0";
			}else{
				ishairpacked = inputJsonMap.get("type").toString();
			}
			
			// 优惠券变动，优惠券金额 CouponID
				String CouponIDArray[] = CouponID.trim().split("\\|");
				int totalAmount = 0;
				// 根据优惠券ID集合查询，多个优惠券金额相加
				if (CouponIDArray.length > 0) {
					for (int i = 0; i < CouponIDArray.length; i++) {
						if ("".equals(CouponIDArray[i])) {
							break;
						}
						CouponsModel couponsModel = couponsServie
								.searchCouponByID(CouponIDArray[i]);
						if (couponsModel != null) {
							totalAmount += couponsModel.getAmount();
						}
					}
				}
			// 优惠券金额+支付金额
			int tempAmount = totalAmount + (int) Float.parseFloat(Amount);
			// 总金额
			int tempTotal = (int) Float.parseFloat(Total);
			if (tempAmount != tempTotal) {
				// 入参实际支付支付金额，和计算后的金额有错误
				outJsonMap.put(Constant.CODE, Constant.CODE_1000);
				outJsonMap.put(Constant.MSG, Constant.MSG_2);
				outJsonMap.put(Constant.DATA, outList);
				outJsonMap.put(Constant.TOTAL, 1);
			} else {
				Object itemObj = inputJsonMap.get("Item");
				List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemObj;

				// 判断工时是否被占用
				// 预约时间
				UserWorkhourModel uwh = userWorkhourService
						.searchUserWorkhour(Integer.parseInt(WHID));
				int hours = ShunJianMeiUtil.getHour(Times);
				for (int i = 0; i < hours; i++) {
					UserWorkhourModel checkUwh = userWorkhourService
							.searchUserWorkhour(0, uwh.getWdid(), uwh.getHour()
									+ i);

					// 9点以后为最后一个时间段不需要判断工时是否被占用。
					if ((uwh.getHour() + i) <= 21) {
						if (null == checkUwh || checkUwh.getState() != 0) {
							// 工时已经被占用
							outJsonMap.put(Constant.CODE, Constant.CODE_2100);
							outJsonMap.put(Constant.MSG, Constant.MSG_1_1
									+ "美发师工时被占用");
							return outJsonMap;
						}
					}
					
				}
				
				//int stateCount = 0;
				// 判断工位是否可用
//				UserWorkdateModel userWorkdateModel = userWorkdateService
//						.searchUserWorkdate(uwh.getWdid());
//				StoreWorkdateModel storeWorkdateModel = storeWorkDateService
//						.searchStoreWorkdate(Integer.valueOf(StoreID),
//								userWorkdateModel.getYear(),
//								userWorkdateModel.getMonth(),
//								userWorkdateModel.getDay());
//				if (storeWorkdateModel == null) {
//					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
//					outJsonMap
//							.put(Constant.MSG, Constant.MSG_1_1 + "美发店暂时没有座位");
//					return outJsonMap;
//				}
//				for (int i = 0; i < hours; i++) {
//					List<StoreWorkhourModel> storeWorkhourModelList = storeWorkHourService
//							.searchStoreHourInfo(storeWorkdateModel.getWdid(),
//									uwh.getHour() + i);
					// 9点以后为最后一个时间段不需要判断工时是否被占用。
//					if ((uwh.getHour() + i) <= 21) {
//						if (null == storeWorkhourModelList
//								|| storeWorkhourModelList.size() == 0) {
//							// 工位已经被占用
//							outJsonMap.put(Constant.CODE, Constant.CODE_2100);
//							outJsonMap.put(Constant.MSG, Constant.MSG_1_1
//									+ "座位被占用");
//							return outJsonMap;
//						}
//					}
					/*
					 * if (storeWorkhourModelList.size() > 0) { stateCount ++; }
					 */
//				}
				/*
				 * if (stateCount != hours) { // 工时已经被占用
				 * outJsonMap.put(Constant.CODE, Constant.CODE_2100);
				 * outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "座位被占用");
				 * return outJsonMap; }
				 */

				// 新增订单
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
				//产品类型 1：套餐；0：非套餐
				orderinfo.setIshairpacked(Integer.parseInt(ishairpacked));
				// 美发师ID
				orderinfo.setUserid(Integer.parseInt(ID));
				// 店铺ID
				orderinfo.setStoreid(Integer.parseInt(StoreID));
				// 发型项目ID
				orderinfo.setStyleid(Integer.parseInt(StyleID));
				if("0".equals(ishairpacked)){
					// 发型ID
					orderinfo.setHairstyleid(Integer.parseInt(HairStyleID));
				}
				orderinfo.setParentid(0);
				// 支付类型
				orderinfo.setPaytype(0);
				// 订单状态
				orderinfo.setPaystate(1);
				// 总金额
				orderinfo.setTotal(Double.parseDouble(Total));
				// 加单支付状态
				orderinfo.setAdditionalstate(0);

				// 优惠券总金额
				orderinfo.setCouponamount(totalAmount);

				// 实际支付金额
				orderinfo.setAmount(Double.parseDouble(Amount));
				// 创建时间
				orderinfo.setCreatetime(createtime);
				// 预约时间
				// orderinfo.setServicebegintime(createtime);
				// 查询商户信息
				StoreinfoModel storeinfoModel = storeinfoService
						.searchStoreinfo(Integer.parseInt(StoreID));

				if (null != storeinfoModel) {
					// 商家地址
					orderinfo.setStoreinfo_address(storeinfoModel.getAddress());
					orderinfo.setStoreinfo_name(storeinfoModel.getName());
					orderinfo.setStoreinfo_tel(storeinfoModel.getTel());
				}
				JSONObject json = null;
				if("0".equals(ishairpacked)){
					// 我的发型名称
					UserMyhairstyleModel userMyhairstyleModel = userMyhairstyleService
							.searchUserMyhairstyle(Integer.parseInt(StyleID));
					if (userMyhairstyleModel != null) {
						orderinfo.setUser_hairstyle_name(userMyhairstyleModel
								.getName());
					}
				}else{
					json = this.userHairpackedService.searchUserHairpackedByid(StyleID);
					orderinfo.setUser_hairstyle_name(json.getJSONObject("data").getString("name"));
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
					orderinfo.setAppointmenttime(ShunJianMeiUtil.StrToDate(
							uwd.getYear(), uwd.getMonth(), uwd.getDay(),
							uwh.getHour()));
				}

				// 查询发型项目
				if("0".equals(ishairpacked)){
					UserHairstyleModel userHairstyleModel = userHairstyleService
							.searchUserHairstyleById(Integer.parseInt(StyleID));
					if (userHairstyleModel != null) {
						orderinfo.setStylename(userHairstyleModel.getName());
					}
				}else{
					json = this.userHairpackedService.searchUserHairpackedByid(StyleID);
					orderinfo.setStylename(json.getJSONObject("data").getString("name"));
				}

				// 设置订单详情
				Set<OrderdetailModel> orderdetails = new HashSet<OrderdetailModel>();
				//增加 产品类型判断 0：普通产品   1：套餐产品
				if("0".equals(ishairpacked)){
					for (Map<String, Object> itemMap : itemList) {
						OrderdetailModel o = new OrderdetailModel();
						// 服务属性编码
						o.setServicecode(itemMap.get("Code").toString());
						double price = Double.parseDouble(itemMap.get("Price")
								.toString());
						// 时长
						o.setTimes(Integer
								.parseInt(itemMap.get("Times").toString()));
						// 物料ID
						String materialId = itemMap.get("MaterialID").toString();
						o.setMaterialid(Integer.parseInt(materialId));
						o.setServiceprice(price);
						if (!materialId.equals("0")) {
							// 物料名称
							MaterialinfoModel materialinfo = materialinfoService
									.searchMaterialNameById(materialId);
							o.setMaterialname(materialinfo.getMaterialname());
							// 物料价格 test************************
							UserHairstyleMaterialModel userHairstyleMaterialModel = userHairstyleMaterialService
									.searchUserHairstyleMaterial(
											Integer.parseInt(StyleID),
											itemMap.get("Code").toString(),
											Integer.parseInt(materialId));
							o.setMaterialidprice(userHairstyleMaterialModel
									.getPrice());
						}
						// 创建时间
						o.setCreatetime(createtime);
						orderdetails.add(o);
					}
				}else{
					OrderdetailModel o = new OrderdetailModel();
					o.setServicecode("tc");
					o.setPrice(0);
					o.setServiceprice(Double.parseDouble(Amount));
					o.setCreatetime(createtime);
					orderdetails.add(o);
				}
				

				// 优惠券变动，设置我的优惠券信息
				List<UserCouponsModel> userCouponsList = new ArrayList<UserCouponsModel>();

				if (CouponIDArray.length > 0) {
					for (int i = 0; i < CouponIDArray.length; i++) {
						if(!CouponIDArray[i].equals("")){
							UserCouponsModel userCouponsModel = userCouponsService
									.searchCoupons(
											Integer.parseInt(CouponIDArray[i]),
											Integer.parseInt(userid));

							userCouponsList.add(userCouponsModel);
						}
					}
				}
				
				// 执行添加
				int OrderID = addOrderinfo(orderinfo,
						orderdetails, userCouponsList, StoreID, Times, WHID);
				
				if(OrderID == -1){
					// 工时已经被占用
					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "美发师工时被占用");
					return outJsonMap;
				}
//				else if(OrderID == -2){
//					// 工位已经被占用
//					outJsonMap.put(Constant.CODE, Constant.CODE_2100);
//					outJsonMap.put(Constant.MSG, Constant.MSG_1_1 + "座位被占用");
//					return outJsonMap;
//				}
				
				// 更新工时状态
				if (OrderID > 0) {
					for (int i = 0; i < hours; i++) {
						userWorkhourService.updateUserWorkhour(OrderID,
								uwh.getWdid(), uwh.getHour() + i, 1);
					}
				}

				// 更座位时状态
//				StoreWorkdateModel storeWorkdate = storeWorkDateService
//						.searchStoreWorkdate(Integer.parseInt(StoreID), uwh
//								.getUserWorkdate().getYear(), uwh
//								.getUserWorkdate().getMonth(), uwh
//								.getUserWorkdate().getDay());
//				if (OrderID > 0) {
//					for (int i = 0; i < hours; i++) {
//						storeWorkHourService.updateStoreWorkhour(OrderID,
//								storeWorkdate.getWdid(), uwh.getHour() + i, 1);
//					}
//				}

				// *//************ 做成返回内容 **********************//*

				// 返回的Map
				Map<String, Object> orderinfoMap = new HashMap<String, Object>();

				// 1.订单实体
				OrderinfoModel orderinfoModel = orderPayService
						.searchOrderinfo(OrderID);
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

					ServiceattributeModel sbm = serviceattributeService
							.searchServiceattribute(orderdetailModel
									.getServicecode());
					orderdetailMap.put("Name",
							sbm != null ? sbm.getServicename() : "");

					orderdetailMap.put("Amount", ShunJianMeiUtil
							.floatToString(orderdetailModel.getServiceprice()));

					// 优惠券变动追加
					// 根据订单编号和服务编号查询优惠券
					UserCouponsModel couponsModel = orderPayService
							.searchCoupons(String.valueOf(OrderID),
									orderdetailModel.getServicecode());
					if (null != couponsModel
							&& null != couponsModel.getCoupons()) {
						orderdetailMap.put("Coupon", ShunJianMeiUtil
								.intToString(couponsModel.getCoupons()
										.getAmount()));
					} else {
						orderdetailMap.put("Coupon", "0");
					}

					outItemList.add(orderdetailMap);
				}
				orderinfoMap.put("Item", outItemList);

				// 获取我的钱包
				UserWalletModel userWalletModel = orderPayService
						.searchUserWallet(Integer.parseInt(userid), 1);// 用户钱包
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
			}
			/******************************************/

			return outJsonMap;
		} catch (Exception e) {
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// 保存异常信息
			int exceptionId = utilService.addBaseException(
					request.getRequestURI(),
					request.getAttribute("inputJsonInfo")
							.toString(), e.getMessage());
			// 设置错误id
			outJsonMap.put(Constant.CODE, Constant.CODE_1200);
			outJsonMap.put(Constant.MSG, Constant.MSG_0_0 + exceptionId);
		}
		return outJsonMap;
	}
	
	// add by jiazhaohui
	/**
	 * 订单成功的时候短信提示
	 * @param orderinfo 订单信息
	 * @return
	 * @throws BaseException
	 */
	@Override
	public void SendMobileMessage(OrderinfoModel orderinfo) throws BaseException
	{
		try
		{
			if (orderinfo != null)
			{
				int cusid = orderinfo.getCustomerid();
				int masterid = orderinfo.getUserid();
				String stime = orderinfo.getAppointmenttime();
				// 把最后的秒字符去掉
				String restime = stime.substring(0, stime.length() - 3);
				UserinfoModel master = userinfoDao.searchUserinfoById(masterid);
				UserinfoModel customer = userinfoDao.searchUserinfoById(cusid);
				if (master != null && customer != null)
				{
					String cusMobile = customer.getUsername();
					String masterMobile = master.getUsername();
					// add by jiazhaohui
					// 在测试服务上先关闭美发师短信
					if (testing != null && testing.equals("true"))
					{
						masterMobile = "15035695301";
					}
					String masterMsg = SmsHelper.getOrderSucMsg(restime, cusMobile);
					SmsHelper.sendNormalMessage(masterMobile, masterMsg);
					String nickname = master.getNickname();
					String adminMsg = SmsHelper.getOrderSucAdminMsg(restime, nickname, cusMobile,
							masterMobile);
					SmsHelper.sendMessageToAdmin(adminMsg);
				}
			}
		}
		catch (Exception e)
		{
		
		}
	}

	@Override
	public List<Map<String, String>> searchFinishOrderListByUserID(
			String userID, String state, String sort, int pageIndex,
			int pageSize) throws BaseException {
		List<OrderinfoModel> myOrderinfoList = orderinfoDao
				.searchFinishOrderListByUserID(userID, state, sort, pageIndex,
						pageSize);
		if (null != myOrderinfoList && myOrderinfoList.size() > 0) {

			List<Map<String, String>> myOrderListMap = new ArrayList<Map<String, String>>();

			for (OrderinfoModel o : myOrderinfoList) {

				Map<String, String> myOrderMap = new HashMap<String, String>();

				// 查询顾客信息
				UserinfoModel userinfo = userinfoDao.searchUserinfoById(o
						.getCustomerid());

				// 订单ID
				myOrderMap.put("OrderID",
						ShunJianMeiUtil.intToString(o.getOrderid()));
				// 订单编号
				myOrderMap.put("OrderCode", o.getOrdercode());
		
			

				if (null != userinfo) {
					// 顾客名字
					myOrderMap.put("Name", userinfo.getNickname());
					// 发型师头像
					UserPhotoMappingModel upm = userPhotoMappingDao
							.searchUserPhoto(o.getCustomerid(), 1);
					if (upm != null) {
						myOrderMap.put("Photo", ipHost
								+ upm.getBasePicture().getPicturepath());
					} else {
						myOrderMap.put("Photo", "");
					}

					// 顾客电话
					String mobile = userinfo.getBindphone();
					if (!mobile.equals("")) {
						myOrderMap.put("Mobile", userinfo.getBindphone());
					} else {
						myOrderMap.put("Mobile", userinfo.getUsername());
					}

				} else {
					myOrderMap.put("Name", "");
					myOrderMap.put("Photo", "");
					myOrderMap.put("Mobile", "");
				}

				// 预约时间
				myOrderMap.put("AppointmentTime", 
						ShunJianMeiUtil.getAppointmentString(o.getAppointmenttime()));
				// 状态
				myOrderMap.put("State",
						ShunJianMeiUtil.intToString(o.getPaystate()));
				// 状态名称
				myOrderMap.put("StateName",
						Constant.PAYSTATE_MAP.get(o.getPaystate()));
				// 发型项目名称
				myOrderMap.put("StyleName", o.getStylename());
				// 商家名称
				myOrderMap.put("StoreName", o.getStoreinfo_name());
				// 商家电话
				myOrderMap.put("StoreTel", o.getStoreinfo_tel());
				// 商家地址
				myOrderMap.put("Address", o.getStoreinfo_address());
				// 支付金额
				myOrderMap.put("Amount",
						ShunJianMeiUtil.floatToString(o.getAmount()));

				myOrderListMap.add(myOrderMap);
			}

			return myOrderListMap;
		}

		return null;
	}
}
