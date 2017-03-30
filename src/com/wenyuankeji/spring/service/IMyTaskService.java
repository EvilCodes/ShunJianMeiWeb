package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMyTaskService {
	/**
	 * 分成处理
	 * @param orderinfoModel 用户ID
	 * @return
	 * @throws BaseException
	 */
	public boolean updateAllocationSet(OrderinfoModel orderinfoModel) throws BaseException;
	
	/**
	 * 根据订单编号和类型查询流水记录
	 * @param orderid 订单编号
	 * @param logtype 类型
	 * @return
	 * @throws BaseException
	 */
	public boolean searchUserTradelogByOrderId(int orderid,int logtype)throws BaseException;
}
