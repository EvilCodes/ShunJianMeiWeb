package com.wenyuankeji.spring.dao;

import java.util.Map;

import com.wenyuankeji.spring.model.UserEditWorkModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserEditWorkDao {

	
	/**
	 * 根据订单ID查找订单信息
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public UserEditWorkModel searchUserEditWorkModel(int orderid)throws BaseException;
	
	/**
	 * 更新自编辑订单
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public int updateOrder(Map<String, Object> inputJsonMap) throws BaseException;

}
