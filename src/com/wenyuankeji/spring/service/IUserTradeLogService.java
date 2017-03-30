package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserTradelogSearchTXModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserTradeLogService {
	
	public boolean addUserTradelog(UserTradelogModel userTradelogModel) throws BaseException;
	
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
}
