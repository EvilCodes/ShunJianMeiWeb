package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IWalletPayService {
	
	/**
	 * 根据用户查询钱包的卡片
	 * @param userid 用户ID
	 * @return
	 * @throws BaseException
	 */
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException;
	
	/**
	 * 1、修改钱包金额 2、添加交易记录 3、修改订单状态
	 * @param userWalletModel 钱包实体
	 * @param userTradelogModel 交易记录实体
	 * @return 
	 * @throws BaseException
	 */
	public boolean updateWalletPay(UserWalletModel userWalletModel,UserTradelogModel userTradelogModel)throws BaseException;


	/**
	 * 1、修改钱包金额 2、添加交易记录 3、修改订单状态
	 * @param userWalletModel 钱包实体
	 * @param userTradelogModel 交易记录实体
	 * @param additionalcode 加单编号
	 * @return 
	 * @throws BaseException
	 */
	public boolean updateWalletPay(UserWalletModel userWalletModel,UserTradelogModel userTradelogModel,String additionalcode)throws BaseException;

	/**
	 * 根据加单ID查询订单信息
	 * @param additionalcode 加单编号
	 * @return
	 * @throws BaseException
	 */
	public OrderinfoModel searchOrderinfoByAdditionalcode(String additionalcode) throws BaseException;
	
	
	/**
	 * 支付宝支付
	 * @param orderId
	 * @return
	 */
	public boolean updateOrderinfo(int orderId)throws BaseException;
	
	/**
	 * 支付宝加单支付
	 * @param orderId
	 * @return
	 */
	public boolean updateAdditionalInfo(String additionalcode)throws BaseException;
	

	/**
	 * 添加支付宝交易记录
	 * @param orderId
	 * @return
	 */
	public boolean addBaseAlipaytradelog(String orderid, String content, int quickpay)throws BaseException;
	/**
	 * 闪惠：1、修改钱包金额 2、修改订单状态3、 添加交易记录 
	 * @param userWalletModel 钱包实体
	 * @param userTradelogModel 交易记录实体
	 * @return 
	 * @throws BaseException
	 */
	public boolean updateWalletQuickPay(UserWalletModel userWalletModel) throws BaseException;
}
