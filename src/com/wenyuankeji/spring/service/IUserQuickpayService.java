package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.UserQuicklogSearchModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserQuickpayService 
{
	/**
	 * 添加闪惠订单信息
	 * @param  
	 * @param customID 
	 * @param storeid 
	 * @param payamount 
	 * @param orderinfo
	 * @return 闪惠订单id
	 */
	public int addQuickpayOrder( int storeid, int customID, float  payamount,String masterName,int paytype,float originalPrice) throws BaseException;
	/**
	 * 更新闪惠订单信息
	 * @param 
	 * @return 
	 */
	public boolean updateUserQuickpayWithPaystate(int paymentid,int paystate) throws BaseException;
	/**
	 * 更新客户闪惠后的钱包,增加交易记录，修改订单状态
	 * @param 
	 * @return 
	 */
	public boolean updateUserWalletPay(UserWalletModel userWalletModel,
			float payamount,int paymentid,String masterName,int storeid,float originalPrice) throws BaseException;
	/**
	 * 更新美发店闪惠后的钱包,增加交易记录，修改订单状态
	 * @param 
	 * @return 
	 */
	public boolean updateStoreWalletPay(int storeid, float payamount, int paymentid,float originalPrice,String masterName) throws BaseException;
	
	// add by jiazhaohui
	// 保存用户的支付记录
	public boolean addUserQuickpayTradelog(int paymentid) throws BaseException;
	
	/**
	 * 根据paymentid查询闪惠订单信息
	 * @param paymentid
	 * @return UserQuickpayModel
	 */
	public UserQuickpayModel searchUserQuickpayInfo(int paymentid) throws BaseException;

	/**
	 * 根据code查询闪惠订单信息
	 * @param paymentid
	 * @return UserQuickpayModel
	 */
	public UserQuickpayModel searchUserQuickpayByCode(String code) throws BaseException;
	
	/**
	 * 满额优惠计算的方法
	 * @param originalPrice
	 * @return coupon_pay_customTotal
	 */
	public float couponCalculation (float originalPrice) throws BaseException;
	/**
	 * 根据美发店id查询闪惠订单的数量
	 * @param storeid
	 * @return UserQuickpayModel
	 */
	public int searchUserQuickTotalByStoreID(int storeid) throws BaseException;
	/**
	 * 根据美发店id查询闪惠订单的信息
	 * @param storeid
	 * @return UserQuickpayModel
	 */
	public List<UserQuicklogSearchModel> searchUserQuick(int storeid, int intPage,
			int intRows) throws BaseException;
}
