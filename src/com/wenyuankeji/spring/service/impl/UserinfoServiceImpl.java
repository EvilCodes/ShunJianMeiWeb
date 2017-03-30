package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.dao.ICouponsDao;
import com.wenyuankeji.spring.dao.IUserCashrecordDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.IUserinfoService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class UserinfoServiceImpl implements IUserinfoService {
	
	@Autowired
	private IUserinfoDao userinfoDao;
	@Autowired
	private ICouponsDao couponsDao;
	@Autowired
	private IUserCashrecordDao userCashrecordDao;
	@Autowired
	private IBaseConfigDao baseconfigDao;
	
	@Override
	public boolean updateUserInfoByUserId(UserinfoModel userInfo) throws BaseException{
		return userinfoDao.updateUserInfoByUserId(userInfo);
	}

	@Override
	public int addUserInfo(UserinfoModel userinfo) throws BaseException {
		return userinfoDao.addUserInfo(userinfo);
	}
	
	/***********UP  mupengyuan*****************/
	
	
	@Override
	public UserinfoModel searchUserinfo(String userName, String passWord,int userType) throws BaseException {
		return userinfoDao.searchUserinfo(userName, passWord,userType);
	}
	
	@Override
	public boolean updatelLastlogintimeById(int id) throws BaseException{
		return userinfoDao.updatelLastlogintimeById(id);
	}
	
	@Override
	public boolean updateUserinfo(UserinfoModel userinfo) throws BaseException{
		
		return userinfoDao.updateUserinfo(userinfo);
	}

	@Override
	public UserinfoModel searchUserinfoById(int userid) throws BaseException{
		
		return userinfoDao.searchUserinfoById(userid);
	}

	@Override
	public UserinfoModel addUserinfo(String userid, String nickName, String sex)throws BaseException {
		
		return userinfoDao.addUserinfo(userid, nickName, sex);
	}

	@Override
	public boolean updateUserinfo(int userID, String oldPassWord,
			String newPassWord, int userState) throws BaseException{
		
		return userinfoDao.updateUserinfo(userID, oldPassWord, newPassWord, userState);
	}

	@Override
	public boolean updateFindPassWord(String userName, String passWord, int userState) throws BaseException{
		return userinfoDao.updateFindPassWord(userName, passWord,userState);
	}

	@Override
	public boolean updateFindUserinfo(UserinfoModel userinfo) throws BaseException{
		
		return userinfoDao.updateFindUserinfo(userinfo);
	}

	@Override
	public boolean addUserinfo(UserinfoModel userinfo) throws BaseException{
		
		try {
			if (userinfoDao.addUserinfo(userinfo)) {
				Date date = new Date();
				//配置表，注册优惠券有效天数
				BaseConfigModel baseConfig = baseconfigDao.searchBaseConfigByCode("RegCouponDays");
				//配置表，TF送出优惠券数
				BaseConfigModel tfCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("RegCoupon", "tf");
				int tfNum = Integer.parseInt(tfCoupon.getValue1());
				//注册成功发送优惠券
				//烫发 40元 2张
				for (int i = 0; i < tfNum; i++) {
					//最大值
					BaseConfigModel BaseConfigModel = baseconfigDao.searchBaseConfigByCode("RegCouponMax_TF");
					int tfMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
					//最小值
					BaseConfigModel BaseConfigModel2 = baseconfigDao.searchBaseConfigByCode("RegCouponMin_TF");
					int tfMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
					
					//获取随机金额
					int  couponRandom= ShunJianMeiUtil.getShareCouponRandom(tfMin,tfMax);
					
					//添加优惠卷信息
					CouponsModel couponsModel = new CouponsModel();
					couponsModel.setCouponname("烫发");
					couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
					couponsModel.setAmount(couponRandom);
					couponsModel.setUsecondition("0");
					couponsModel.setStarttime(date);
					if(baseConfig != null && baseConfig.getConfigvalue() != ""){
						int day = Integer.parseInt(baseConfig.getConfigvalue());
						couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
					}else{
						couponsModel.setEndtime(date);
					}
					couponsModel.setDeleted(false);
					couponsModel.setRemark("用户注册优惠卷");
					couponsModel.setCreatetime(date);
					couponsModel.setServicecode("tf");
					
					couponsDao.addCoupons(couponsModel);
					
					if (couponsModel.getCouponid() > 0) {
						//添加我的优惠卷
						UserCouponsModel userCouponsModel =  new UserCouponsModel();
						userCouponsModel.setUserid(userinfo.getUserid());
						userCouponsModel.setCouponid(couponsModel.getCouponid());
						userCouponsModel.setOrderid(0);
						userCouponsModel.setCreatetime(date);
						userCouponsModel.setSource("0");
						userCashrecordDao.addUserCouponsModel(userCouponsModel);
					}
				}

				//配置表，RF送出优惠券数
				BaseConfigModel rfCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("RegCoupon", "rf");
				int rfNum = Integer.parseInt(rfCoupon.getValue1());
				//染发 40元 2张
				for (int i = 0; i < rfNum; i++) {
					//最大值
					BaseConfigModel BaseConfigModel = baseconfigDao.searchBaseConfigByCode("RegCouponMax_RF");
					int rfMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
					//最小值
					BaseConfigModel BaseConfigModel2 = baseconfigDao.searchBaseConfigByCode("RegCouponMin_RF");
					int rfMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
					
					//获取随机金额
					int  couponRandom= ShunJianMeiUtil.getShareCouponRandom(rfMin,rfMax);
					
					//添加优惠卷信息
					CouponsModel couponsModel = new CouponsModel();
					couponsModel.setCouponname("染发");
					couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
					couponsModel.setAmount(couponRandom);
					couponsModel.setUsecondition("0");
					couponsModel.setStarttime(date);
					if(baseConfig != null && baseConfig.getConfigvalue() != ""){
						int day = Integer.parseInt(baseConfig.getConfigvalue());
						couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
					}else{
						couponsModel.setEndtime(date);
					}
					couponsModel.setDeleted(false);
					couponsModel.setRemark("用户注册优惠卷");
					couponsModel.setCreatetime(date);
					couponsModel.setServicecode("rf");
					
					couponsDao.addCoupons(couponsModel);
					
					if (couponsModel.getCouponid() > 0) {
						//添加我的优惠卷
						UserCouponsModel userCouponsModel =  new UserCouponsModel();
						userCouponsModel.setUserid(userinfo.getUserid());
						userCouponsModel.setCouponid(couponsModel.getCouponid());
						userCouponsModel.setOrderid(0);
						userCouponsModel.setCreatetime(date);
						userCouponsModel.setSource("0");
						userCashrecordDao.addUserCouponsModel(userCouponsModel);
					}
				}

				//配置表，XJC送出优惠券数
				BaseConfigModel xjcCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("RegCoupon", "xjc");
				int xjcNum = Integer.parseInt(xjcCoupon.getValue1());
				//洗剪吹 10元 2张
				for (int i = 0; i < xjcNum; i++) {
					//最大值
					BaseConfigModel BaseConfigModel = baseconfigDao.searchBaseConfigByCode("RegCouponMax_XJC");
					int xjcMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
					//最小值
					BaseConfigModel BaseConfigModel2 = baseconfigDao.searchBaseConfigByCode("RegCouponMin_XJC");
					int xjcMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
					
					//获取随机金额
					int  couponRandom= ShunJianMeiUtil.getShareCouponRandom(xjcMin,xjcMax);
					
					//添加优惠卷信息
					CouponsModel couponsModel = new CouponsModel();
					couponsModel.setCouponname("设计剪发");
					couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
					couponsModel.setAmount(couponRandom);
					couponsModel.setUsecondition("0");
					couponsModel.setStarttime(date);
					if(baseConfig != null && baseConfig.getConfigvalue() != ""){
						int day = Integer.parseInt(baseConfig.getConfigvalue());
						couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
					}else{
						couponsModel.setEndtime(date);
					}
					couponsModel.setDeleted(false);
					couponsModel.setRemark("用户注册优惠卷");
					couponsModel.setCreatetime(date);
					couponsModel.setServicecode("xjc");
					
					couponsDao.addCoupons(couponsModel);
					
					if (couponsModel.getCouponid() > 0) {
						//添加我的优惠卷
						UserCouponsModel userCouponsModel =  new UserCouponsModel();
						userCouponsModel.setUserid(userinfo.getUserid());
						userCouponsModel.setCouponid(couponsModel.getCouponid());
						userCouponsModel.setOrderid(0);
						userCouponsModel.setCreatetime(date);
						userCouponsModel.setSource("0");
						userCashrecordDao.addUserCouponsModel(userCouponsModel);
					}
				}

				//配置表，XC送出优惠券数
				BaseConfigModel xcCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("RegCoupon", "xc");
				int xcNum = Integer.parseInt(xcCoupon.getValue1());
				//洗吹 5元 2张 
				for (int i = 0; i < xcNum; i++) {
					//最大值
					BaseConfigModel BaseConfigModel = baseconfigDao.searchBaseConfigByCode("RegCouponMax_XC");
					int xcMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
					//最小值
					BaseConfigModel BaseConfigModel2 = baseconfigDao.searchBaseConfigByCode("RegCouponMin_XC");
					int xcMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
					
					//获取随机金额
					int  couponRandom= ShunJianMeiUtil.getShareCouponRandom(xcMin,xcMax);
					
					//添加优惠卷信息
					CouponsModel couponsModel = new CouponsModel();
					couponsModel.setCouponname("造型");
					couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
					couponsModel.setAmount(couponRandom);
					couponsModel.setUsecondition("0");
					couponsModel.setStarttime(date);
					if(baseConfig != null && baseConfig.getConfigvalue() != ""){
						int day = Integer.parseInt(baseConfig.getConfigvalue());
						couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
					}else{
						couponsModel.setEndtime(date);
					}
					couponsModel.setDeleted(false);
					couponsModel.setRemark("用户注册优惠卷");
					couponsModel.setCreatetime(date);
					couponsModel.setServicecode("xc");
					
					couponsDao.addCoupons(couponsModel);
					
					if (couponsModel.getCouponid() > 0) {
						//添加我的优惠卷
						UserCouponsModel userCouponsModel =  new UserCouponsModel();
						userCouponsModel.setUserid(userinfo.getUserid());
						userCouponsModel.setCouponid(couponsModel.getCouponid());
						userCouponsModel.setOrderid(0);
						userCouponsModel.setCreatetime(date);
						userCouponsModel.setSource("0");
						userCashrecordDao.addUserCouponsModel(userCouponsModel);
					}
				}

				//配置表，HL送出优惠券数
				BaseConfigModel hlCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("RegCoupon", "hl");
				int hlNum = Integer.parseInt(hlCoupon.getValue1());
				//护理 12元 1张
				for (int i = 0; i < hlNum; i++) {
					//最大值
					BaseConfigModel BaseConfigModel = baseconfigDao.searchBaseConfigByCode("RegCouponMax_HL");
					int hlMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
					//最小值
					BaseConfigModel BaseConfigModel2 = baseconfigDao.searchBaseConfigByCode("RegCouponMin_HL");
					int hlMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
					
					//获取随机金额
					int  couponRandom= ShunJianMeiUtil.getShareCouponRandom(hlMin,hlMax);
					
					//添加优惠卷信息
					CouponsModel couponsModel = new CouponsModel();
					couponsModel.setCouponname("护理");
					couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
					couponsModel.setAmount(couponRandom);
					couponsModel.setUsecondition("0");
					couponsModel.setStarttime(date);
					if(baseConfig != null && baseConfig.getConfigvalue() != ""){
						int day = Integer.parseInt(baseConfig.getConfigvalue());
						couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
					}else{
						couponsModel.setEndtime(date);
					}
					couponsModel.setDeleted(false);
					couponsModel.setRemark("用户注册优惠卷");
					couponsModel.setCreatetime(date);
					couponsModel.setServicecode("hl");
					
					couponsDao.addCoupons(couponsModel);
					
					if (couponsModel.getCouponid() > 0) {
						//添加我的优惠卷
						UserCouponsModel userCouponsModel =  new UserCouponsModel();
						userCouponsModel.setUserid(userinfo.getUserid());
						userCouponsModel.setCouponid(couponsModel.getCouponid());
						userCouponsModel.setOrderid(0);
						userCouponsModel.setCreatetime(date);
						userCouponsModel.setSource("0");
						userCashrecordDao.addUserCouponsModel(userCouponsModel);
					}
				}
				//配置表，tc送出优惠券数
				BaseConfigModel tcCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("RegCoupon", "tc");
				int tcNum = Integer.parseInt(tcCoupon.getValue1());
				for (int i = 0; i < tcNum; i++) {
					//最大值
					BaseConfigModel BaseConfigModel = baseconfigDao.searchBaseConfigByCode("RegCouponMax_TC");
					int tcMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
					//最小值
					BaseConfigModel BaseConfigModel2 = baseconfigDao.searchBaseConfigByCode("RegCouponMin_TC");
					int tcMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
					
					//获取随机金额
					int  couponRandom= ShunJianMeiUtil.getShareCouponRandom(tcMin,tcMax);
					
					//添加优惠卷信息
					CouponsModel couponsModel = new CouponsModel();
					couponsModel.setCouponname("套餐");
					couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
					couponsModel.setAmount(couponRandom);
					couponsModel.setUsecondition("0");
					couponsModel.setStarttime(date);
					if(baseConfig != null && baseConfig.getConfigvalue() != ""){
						int day = Integer.parseInt(baseConfig.getConfigvalue());
						couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
					}else{
						couponsModel.setEndtime(date);
					}
					couponsModel.setDeleted(false);
					couponsModel.setRemark("用户注册优惠卷");
					couponsModel.setCreatetime(date);
					couponsModel.setServicecode("tc");
					
					couponsDao.addCoupons(couponsModel);
					
					if (couponsModel.getCouponid() > 0) {
						//添加我的优惠卷
						UserCouponsModel userCouponsModel =  new UserCouponsModel();
						userCouponsModel.setUserid(userinfo.getUserid());
						userCouponsModel.setCouponid(couponsModel.getCouponid());
						userCouponsModel.setOrderid(0);
						userCouponsModel.setCreatetime(date);
						userCouponsModel.setSource("0");
						userCashrecordDao.addUserCouponsModel(userCouponsModel);
					}
				}
				return true;
			}
			return false;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public UserinfoModel searchUserinfoByUserName(String userName,int type)throws BaseException {
		return userinfoDao.searchUserinfoByUserName(userName,type);
	}

	@Override
	public String searchUserinfoByCityId(int cityid,String version)
			throws BaseException {
		String userIdStr = "";
		//根据城市ID查询审核通过的美发师ID集合
		List<Integer> userIdList = userinfoDao.searchUserinfoByCityId(cityid,version);
		if(userIdList != null && userIdList.size() > 0){
			for (Integer obj : userIdList) {
				userIdStr += obj + ",";
			}
		}
		
		if(userIdStr != ""){
			userIdStr = userIdStr.substring(0, userIdStr.length() - 1);
		}
		
		return userIdStr;
	}

	
}
