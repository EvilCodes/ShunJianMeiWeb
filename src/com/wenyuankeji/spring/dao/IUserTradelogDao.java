package com.wenyuankeji.spring.dao;


import java.util.List;

import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserTradelogSearchTXModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserTradelogDao {

	/**
	 * 根据钱包ID，查询交易记录
	 * @param walletid 钱包ID
	 * @param pageSize 每页显示数
	 * @param pageIndex 当前页
	 * @param type 交易类型
	 * @param sort 排序
	 * @return
	 * @throws BaseException
	 */
	public List<UserTradelogModel> searchUserTradelog(int walletid,int pageSize,int pageIndex,int type,int sort)throws BaseException;
	
	
	/**
	 * 添加交易日志
	 * @param userTradelogModel
	 * @return 
	 * @throws BaseException
	 */
	public Boolean addUserTradelog(UserTradelogModel userTradelogModel)throws BaseException;
	
	/**
	 * 所有者ID，查询交易记录
	 * @param ownerid 所有者ID
	 * @param rows 每页显示数
	 * @param page 当前页
	 * @return
	 * @throws BaseException
	 */
	public List<UserTradelogSearchTXModel> searchUserTradelogTX(int ownerid,int page,
			int rows)throws BaseException;
	
	/**
	 * 所有者ID，查询交易记录
	 * @param ownerid 所有者ID
	 * @return
	 * @throws BaseException
	 */
	public int searchUserTradelogTX(int ownerid)throws BaseException;
	
	
	/**
	 * 根据订单编号和类型查询流水记录
	 * @param orderid 订单编号
	 * @param logtype 类型
	 * @return
	 * @throws BaseException
	 */
	public boolean searchUserTradelogByOrderId(int orderid,int logtype)throws BaseException;
}
