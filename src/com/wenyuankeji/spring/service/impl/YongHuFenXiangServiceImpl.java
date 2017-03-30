package com.wenyuankeji.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.dao.ICouponsDao;
import com.wenyuankeji.spring.dao.IUserCouponsDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.IYongHuFenXiangService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class YongHuFenXiangServiceImpl implements IYongHuFenXiangService{

	@Autowired
	private ICouponsDao couponsDao;
	
	@Autowired
	private IUserCouponsDao userCouponDao;
	
	@Autowired 
	private IBaseConfigDao baseConfigDao;
	
	@Autowired 
	private IUserinfoDao userinfoDao;
	
	@Autowired
	private IBaseConfigDao baseconfigDao;
	
	@Override
	public int addCoupons(String evaid,String mobile) throws BaseException {
		//------wangmeng-------
		int sum = 0;
		
		try {
			Date date = new Date();
			
			// add by jiazhaohui
			// 分享优惠券的天数
			BaseConfigModel baseConfig = baseconfigDao.searchBaseConfigByCode("ShareCouponDays");
			int day = 1;
			if(baseConfig != null && baseConfig.getConfigvalue() != "")
			{
				day = Integer.parseInt(baseConfig.getConfigvalue());
			}
			// 用户ID
			int userId = 0;
			UserinfoModel userinfoModel = userinfoDao.searchUserinfoByUserName(mobile, 1);
			if (userinfoModel != null) {
				userId = userinfoModel.getUserid();
			}
			
			BaseConfigModel tfCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("ShareCoupon", "tf");
			int tfNum = Integer.parseInt(tfCoupon.getValue1());
			//烫发 40元 2张
			for (int i = 0; i < tfNum; i++) 
			{
				//最大值
				BaseConfigModel BaseConfigModel = baseConfigDao.searchBaseConfigByCode("ShareCouponMax_TF");
				int tfMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
				//最小值
				BaseConfigModel BaseConfigModel2 = baseConfigDao.searchBaseConfigByCode("ShareCouponMin_TF");
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
				couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
				couponsModel.setDeleted(false);
				couponsModel.setRemark("分享领取优惠券");
				couponsModel.setCreatetime(date);
				couponsModel.setServicecode("tf");
				couponsDao.addCoupons(couponsModel);
				
				if (couponsModel.getCouponid() > 0) {
					//添加我的优惠卷
					UserCouponsModel userCouponsModel =  new UserCouponsModel();
					userCouponsModel.setUserid(userId);
					userCouponsModel.setCouponid(couponsModel.getCouponid());
					userCouponsModel.setOrderid(0);
					userCouponsModel.setCreatetime(date);
					userCouponsModel.setMobile(mobile);
					userCouponsModel.setSource(evaid);
					userCouponDao.addUserCoupons(userCouponsModel);
					sum += couponsModel.getAmount();
				}
			}
			//染发 40元 2张
			BaseConfigModel rfCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("ShareCoupon", "rf");
			int rfNum = Integer.parseInt(rfCoupon.getValue1());
			for (int i = 0; i < rfNum; i++) {
				//最大值
				BaseConfigModel BaseConfigModel = baseConfigDao.searchBaseConfigByCode("ShareCouponMax_RF");
				int rfMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
				//最小值
				BaseConfigModel BaseConfigModel2 = baseConfigDao.searchBaseConfigByCode("ShareCouponMin_RF");
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
				couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
				couponsModel.setDeleted(false);
				couponsModel.setRemark("分享领取优惠券");
				couponsModel.setCreatetime(date);
				couponsModel.setServicecode("rf");
				
				couponsDao.addCoupons(couponsModel);
				
				if (couponsModel.getCouponid() > 0) {
					//添加我的优惠卷
					UserCouponsModel userCouponsModel =  new UserCouponsModel();
					userCouponsModel.setUserid(userId);
					userCouponsModel.setCouponid(couponsModel.getCouponid());
					userCouponsModel.setOrderid(0);
					userCouponsModel.setCreatetime(date);
					userCouponsModel.setMobile(mobile);
					userCouponsModel.setSource(evaid);
					userCouponDao.addUserCoupons(userCouponsModel);
					sum += couponsModel.getAmount();
				}
			}
			//洗剪吹 1张
			BaseConfigModel cutCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("ShareCoupon", "xjc");
			int cutNum = Integer.parseInt(cutCoupon.getValue1());
			for (int i = 0; i < cutNum; i++) 
			{			
				//最大值
				BaseConfigModel BaseConfigModel = baseConfigDao.searchBaseConfigByCode("ShareCouponMax_XJC");
				int xjcMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
				//最小值
				BaseConfigModel BaseConfigModel2 = baseConfigDao.searchBaseConfigByCode("ShareCouponMin_XJC");
				int xjcMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
				
				//获取随机金额
				int couponRandom = ShunJianMeiUtil.getShareCouponRandom(xjcMin,xjcMax);
				//配置表，分享优惠券有效天数
				baseConfig = baseconfigDao.searchBaseConfigByCode("ShareCouponDays");
				//添加优惠卷信息				
				CouponsModel couponsModel = new CouponsModel();
				couponsModel.setCouponname("设计剪发");
				couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
				couponsModel.setAmount(couponRandom);
				couponsModel.setUsecondition("0");
				couponsModel.setStarttime(date);
				if(baseConfig != null && baseConfig.getConfigvalue() != ""){
					couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
				}else{
					couponsModel.setEndtime(date);
				}
				couponsModel.setDeleted(false);
				couponsModel.setRemark("分享领取优惠券");
				couponsModel.setCreatetime(date);
				couponsModel.setServicecode("xjc");
				
				//------wangmeng-------
				couponsDao.addCoupons(couponsModel);
				
				if (couponsModel.getCouponid() > 0) {
					//添加我的优惠卷
					UserCouponsModel userCouponsModel =  new UserCouponsModel();
					//根据手机号，查询用户id
					userinfoModel = userinfoDao.searchUserinfoByUserName(mobile, 1);
					if (userinfoModel != null) {
						userCouponsModel.setUserid(userinfoModel.getUserid());
					}else{
						userCouponsModel.setUserid(0);
					}
					userCouponsModel.setCouponid(couponsModel.getCouponid());
					userCouponsModel.setOrderid(0);
					userCouponsModel.setCreatetime(date);
					userCouponsModel.setMobile(mobile);
					userCouponsModel.setSource(evaid);
					
					//------wangmeng-------
					userCouponDao.addUserCoupons(userCouponsModel);
					sum += couponsModel.getAmount();
				}
			}
			//洗吹 5元 2张
			BaseConfigModel xcCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("ShareCoupon", "xc");
			int xcNum = Integer.parseInt(xcCoupon.getValue1());
			for (int i = 0; i < xcNum; i++) 
			{
				//最大值
				BaseConfigModel BaseConfigModel = baseConfigDao.searchBaseConfigByCode("ShareCouponMax_XC");
				int xcMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
				//最小值
				BaseConfigModel BaseConfigModel2 = baseConfigDao.searchBaseConfigByCode("ShareCouponMin_XC");
				int xcMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
				
				//获取随机金额
				int couponRandom= ShunJianMeiUtil.getShareCouponRandom(xcMin,xcMax);
				
				//添加优惠卷信息
				CouponsModel couponsModel = new CouponsModel();
				couponsModel.setCouponname("造型");
				couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
				couponsModel.setAmount(couponRandom);
				couponsModel.setUsecondition("0");
				couponsModel.setStarttime(date);
				couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
				couponsModel.setDeleted(false);
				couponsModel.setRemark("分享领取优惠券");
				couponsModel.setCreatetime(date);
				couponsModel.setServicecode("xc");
				
				couponsDao.addCoupons(couponsModel);
				
				if (couponsModel.getCouponid() > 0) {
					//添加我的优惠卷
					UserCouponsModel userCouponsModel =  new UserCouponsModel();
					userCouponsModel.setUserid(userId);
					userCouponsModel.setCouponid(couponsModel.getCouponid());
					userCouponsModel.setOrderid(0);
					userCouponsModel.setCreatetime(date);
					userCouponsModel.setMobile(mobile);
					userCouponsModel.setSource(evaid);
					userCouponDao.addUserCoupons(userCouponsModel);
					sum += couponsModel.getAmount();
				}
			}
			//护理 12元 1张
			BaseConfigModel hlCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("ShareCoupon", "hl");
			int hlNum = Integer.parseInt(hlCoupon.getValue1());
			for (int i = 0; i < hlNum; i++) 
			{
				//最大值
				BaseConfigModel BaseConfigModel = baseConfigDao.searchBaseConfigByCode("ShareCouponMax_HL");
				int hlMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
				//最小值
				BaseConfigModel BaseConfigModel2 = baseConfigDao.searchBaseConfigByCode("ShareCouponMin_HL");
				int hlMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
				
				//获取随机金额
				int couponRandom= ShunJianMeiUtil.getShareCouponRandom(hlMin,hlMax);
				
				//添加优惠卷信息
				CouponsModel couponsModel = new CouponsModel();
				couponsModel.setCouponname("护理");
				couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
				couponsModel.setAmount(couponRandom);
				couponsModel.setUsecondition("0");
				couponsModel.setStarttime(date);
				couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
				couponsModel.setDeleted(false);
				couponsModel.setRemark("分享领取优惠券");
				couponsModel.setCreatetime(date);
				couponsModel.setServicecode("hl");
				
				couponsDao.addCoupons(couponsModel);
				
				if (couponsModel.getCouponid() > 0) {
					//添加我的优惠卷
					UserCouponsModel userCouponsModel =  new UserCouponsModel();
					userCouponsModel.setUserid(userId);
					userCouponsModel.setCouponid(couponsModel.getCouponid());
					userCouponsModel.setOrderid(0);
					userCouponsModel.setCreatetime(date);
					userCouponsModel.setMobile(mobile);
					userCouponsModel.setSource(evaid);
					userCouponDao.addUserCoupons(userCouponsModel);
					sum += couponsModel.getAmount();
				}
			}
			//套餐 优惠券
			BaseConfigModel tcCoupon = baseconfigDao.searchBaseConfigByCodeAndValue("ShareCoupon", "tc");
			int ctNum = Integer.parseInt(tcCoupon.getValue1());
			for (int i = 0; i < ctNum; i++) 
			{
				//最大值
				BaseConfigModel BaseConfigModel = baseConfigDao.searchBaseConfigByCode("ShareCouponMax_TC");
				int tcMax = Integer.parseInt(BaseConfigModel.getConfigvalue());
				//最小值
				BaseConfigModel BaseConfigModel2 = baseConfigDao.searchBaseConfigByCode("ShareCouponMin_TC");
				int tcMin = Integer.parseInt(BaseConfigModel2.getConfigvalue());
				
				//获取随机金额
				int couponRandom= ShunJianMeiUtil.getShareCouponRandom(tcMin,tcMax);
				
				//添加优惠卷信息
				CouponsModel couponsModel = new CouponsModel();
				couponsModel.setCouponname("套餐");
				couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
				couponsModel.setAmount(couponRandom);
				couponsModel.setUsecondition("0");
				couponsModel.setStarttime(date);
				couponsModel.setEndtime(ShunJianMeiUtil.getDateByDay(day));
				couponsModel.setDeleted(false);
				couponsModel.setRemark("分享领取优惠券");
				couponsModel.setCreatetime(date);
				couponsModel.setServicecode("tc");
				
				couponsDao.addCoupons(couponsModel);
				
				if (couponsModel.getCouponid() > 0) {
					//添加我的优惠卷
					UserCouponsModel userCouponsModel =  new UserCouponsModel();
					userCouponsModel.setUserid(userId);
					userCouponsModel.setCouponid(couponsModel.getCouponid());
					userCouponsModel.setOrderid(0);
					userCouponsModel.setCreatetime(date);
					userCouponsModel.setMobile(mobile);
					userCouponsModel.setSource(evaid);
					userCouponDao.addUserCoupons(userCouponsModel);
					sum += couponsModel.getAmount();
				}
			}
		} catch (Exception e) {
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			sum = 0;
		}
		return sum;
	}

	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode) throws BaseException {
		return baseConfigDao.searchBaseConfigByCode(configcode);
	}

	@Override
	public UserCouponsModel searchUserCoupons(String mobile, int source) throws BaseException {
		return userCouponDao.searchUserCoupons(mobile, source);
	}

}
