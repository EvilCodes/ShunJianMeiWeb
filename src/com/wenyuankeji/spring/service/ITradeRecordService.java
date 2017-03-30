package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.util.BaseException;

public interface ITradeRecordService {

	/**
	 * 根据用户查询钱包的卡片
	 * @param userid 用户ID
	 * @param ownertype 所有者类型: 1用户2美发师3店铺
	 * @return
	 * @throws BaseException
	 */
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException;
	/**
	 * 根据钱包ID，查询交易记录
	 * @param walletid
	 * @return
	 */
	public List<UserTradelogModel> searchUserTradelog(int walletid,int pageSize,int pageIndex,int type,int sort)throws BaseException;
	
	/**
	 * 根据订单ID查询订单信息
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException;
}
