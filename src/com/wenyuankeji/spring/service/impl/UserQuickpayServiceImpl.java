package com.wenyuankeji.spring.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.dao.IUserQuickpayDao;
import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.dao.IUserWorkdateDao;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IUserQuickpayService;
import com.wenyuankeji.spring.service.IWalletPayService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserQuicklogSearchModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserinfoModel;

@Service
public class UserQuickpayServiceImpl implements IUserQuickpayService
{

	@Autowired
	private IUserQuickpayDao userQuickpayDao;
	
	@Autowired
	private IStoreinfoDao storeinfoDao;
	
	@Autowired
	private IWalletPayService walletPayService;
	
	@Autowired
	private IBaseConfigService baseConfigService;
	
	@Autowired
	private IUserTradelogDao userTradelogDao;
	
	@Override
	public int addQuickpayOrder(int storeid, int customID, float payamount,String masterName,int paytype,float originalPrice) throws BaseException{
		try {
			UserQuickpayModel userQuickpayModel=new UserQuickpayModel();
			// 订单编号
			//生成6位随机数
			Random random = new Random();
			int x = random.nextInt(899999);
			int a = x+100000;
			String randomString=ShunJianMeiUtil.intToString(a);
			String ordercode1=randomString;
			userQuickpayModel.setOrdercode(ordercode1);
			// 顾客ID
			userQuickpayModel.setUserid(customID);
			// 店铺ID
			userQuickpayModel.setStoreid(storeid);
			// 支付类型
			userQuickpayModel.setPaytype(paytype);
			//实际支付价格
			userQuickpayModel.setPayamount(payamount);
			//美发师名字
			userQuickpayModel.setMastername(masterName);
			// 创建时间
			userQuickpayModel.setCreatetime(new Date());
			//美发店的分成额度
			BaseConfigModel baseConfigModel1=baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_COUPON_PAY_STORE);
			String coupon_pay_storeString=baseConfigModel1.getConfigvalue();
			float coupon_pay_store=Float.parseFloat(coupon_pay_storeString);
			userQuickpayModel.setStorereduce(coupon_pay_store);
			
			//计算总共优惠额度
			float coupon_pay_customTotal=this.couponCalculation(originalPrice);
			userQuickpayModel.setCustomreduce(coupon_pay_customTotal);
			//执行增加闪惠订单
			int paymentid=userQuickpayDao.addQuickpayOrder(userQuickpayModel);
			//将paymentid加到闪惠订单编号中
			String paymentidString =ShunJianMeiUtil.intToString(paymentid);
			String ordercode =ordercode1.concat(paymentidString);
			
			userQuickpayDao.updateUserQuickpayWithOrdercode(paymentid,ordercode);
			
			
			return paymentid;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new BaseException(e.getMessage());
		}
		
	}
	@Override
	public boolean updateUserQuickpayWithPaystate(int paymentid,int paystate)
			throws BaseException {
		try {
			return userQuickpayDao.updateUserQuickpayWithPaystate(paymentid,paystate);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new BaseException(e.getMessage());
		}
		
	}
	
	// add by jiazhaohui
	// 保存用户的支付记录
	@Override
	public boolean addUserQuickpayTradelog(int paymentid) throws BaseException
	{
		try
		{
			UserQuickpayModel uqpModel = searchUserQuickpayInfo(paymentid);
			if (uqpModel != null)
			{
				UserWalletModel userWalletModel = walletPayService.searchUserWallet(uqpModel.getUserid(), 
						Constant.WALLET_OWNERTYPE_USER); 
				UserTradelogModel userTradelogModel=new UserTradelogModel();
				userTradelogModel.setOrderid(paymentid);
				userTradelogModel.setWalletid(userWalletModel.getWalletid());	
				userTradelogModel.setAmount(uqpModel.getPayamount());
				userTradelogModel.setQuickpay(Constant.QUICK_PAY);
				userTradelogModel.setRemark("顾客优惠支付:" + uqpModel.getMastername());
				userTradelogModel.setCreatetime(new Date());
				userTradelogModel.setLogtype(Constant.LOGTYPE_CUSTOM_PAY); //顾客支付
				// 2、添加交易日志
				if (userTradelogDao.addUserTradelog(userTradelogModel)) {
					return true;
				}
			}
		}
		catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new BaseException(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean updateUserWalletPay(UserWalletModel userWalletModel,
			float payamount,int paymentid,String masterName,int storeid,float originalPrice) throws BaseException {
		try {
			
			if (userWalletModel != null) {
				//客户钱包金额
				float allAmount = userWalletModel.getBalance();
				//钱包余额大于支付金额
				if (allAmount >= payamount) {
					// 剩余金额 = 钱包金额 - 支付金额
					float newAmount = allAmount - payamount;
					userWalletModel.setBalance(newAmount);
					userWalletModel.setOwnertype(Constant.WALLET_OWNERTYPE_USER);
					
					// 更新钱包
					if(walletPayService.updateWalletQuickPay(userWalletModel)){
						// 用户交易记录
						addUserQuickpayTradelog(paymentid);
						// 更新商户钱包
						this.updateStoreWalletPay(storeid, payamount, paymentid, originalPrice, masterName);
						return true;
					}else {
						return false;
					}
						
				}else {
					return false;
				}
			
		
			}else {
				return false;
			}

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new BaseException(e.getMessage());
		}
				
	}
	@Override
	public boolean updateStoreWalletPay(int storeid ,float payamount, int paymentid,float originalPrice,String masterName) throws BaseException {
	try {
		//查询美发店钱包
				UserWalletModel storeWalletModel = walletPayService
					.searchUserWallet(storeid,Constant.WALLET_OWNERTYPE_STORE);
				UserTradelogModel storeTradelogModel = new UserTradelogModel();
				
				
					//美发店钱包金额
					float masterAllAmount = storeWalletModel.getBalance();
					// 给美发店分成金额
					BaseConfigModel baseConfigModel=baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_COUPON_PAY_STORE);
					String coupon_pay_storeString=baseConfigModel.getConfigvalue();
					float coupon_pay_store=Float.parseFloat(coupon_pay_storeString);
					// 新钱包金额 = 钱包原来金额 + 应支付金额+美发店分成金额
					float masterNewAmount = masterAllAmount + originalPrice + coupon_pay_store;
				
					storeWalletModel.setBalance(masterNewAmount);
					
					storeTradelogModel.setOrderid(paymentid);
					storeTradelogModel.setWalletid(storeWalletModel.getWalletid());	
					storeTradelogModel.setAmount(originalPrice + coupon_pay_store);
					storeTradelogModel.setRemark("美发店优惠支付分成:"+masterName);
					storeTradelogModel.setQuickpay(Constant.QUICK_PAY);
					storeTradelogModel.setCreatetime(new Date());
					storeTradelogModel.setLogtype(Constant.LOGTYPE_STORE_SHARE); // 美发店分成

					if(walletPayService.updateWalletQuickPay(storeWalletModel)){
						// 保存店铺的交易记录
						userTradelogDao.addUserTradelog(storeTradelogModel);
						// 订单状态:已支付
						int paystate=Constant.QUICKPAY_PAYSTATE_SUCCESS;
						if(this.updateUserQuickpayWithPaystate(paymentid,paystate)){
							//获取美发店订单数量
							StoreinfoModel storeinfoModel=storeinfoDao.searchStoreinfo(storeid);
							int orderquantity=storeinfoModel.getOrderquantity();
							//美发店订单数量加1
							storeinfoModel.setOrderquantity(orderquantity+1);
							storeinfoDao.updateStoreinfo(storeinfoModel);
							return true;
						}else {
							return false;
						}
						
					}else {
						return false;
					}
				
	} catch (Exception e) {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		throw new BaseException(e.getMessage());
	}
		
	}
	@Override
	public UserQuickpayModel searchUserQuickpayInfo(int paymentid)
			throws BaseException {
		try {
			return userQuickpayDao.searchUserQuickpayInfo(paymentid);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	
	}
	
	/**
	 * 根据code查询闪惠订单信息
	 * @param paymentid
	 * @return UserQuickpayModel
	 */
	@Override
	public UserQuickpayModel searchUserQuickpayByCode(String code) throws BaseException
	{
		try {
			return userQuickpayDao.searchUserQuickpayByCode(code);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public float couponCalculation(float originalPrice) throws BaseException {
		//优惠的金额
		BaseConfigModel baseConfigModel=baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_COUPON_PAY_CUSTOM);
		String coupon_pay_customString=baseConfigModel.getConfigvalue();
		float coupon_pay_custom=Float.parseFloat(coupon_pay_customString);
		
		float coupon_pay_customTotal;
		//满额
		BaseConfigModel baseConfigModel2=baseConfigService.searchBaseConfigByCode(Constant.CONFIGCODE_ENOUGH_MONEY);
		String enough_moneyString=baseConfigModel2.getConfigvalue();
		float enough_money=Float.parseFloat(enough_moneyString);
		if(originalPrice>=enough_money){
			int num=(int) (originalPrice/enough_money);
			coupon_pay_customTotal=num*coupon_pay_custom;
		}else {
			coupon_pay_customTotal=0;
		}
		return coupon_pay_customTotal;
	}
	@Override
	public int searchUserQuickTotalByStoreID(int storeid) throws BaseException {
		try {
			return userQuickpayDao.searchUserQuickTotalByStoreID(storeid);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	@Override
	public List<UserQuicklogSearchModel> searchUserQuick(int storeid, int intPage,
			int intRows) throws BaseException {
		try {
			
			return userQuickpayDao.searchUserQuick(storeid,intPage,intRows);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
	

