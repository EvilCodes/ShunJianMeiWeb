package com.wenyuankeji.spring.dao;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.UserQuicklogSearchModel;
import com.wenyuankeji.spring.model.UserQuickpayModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserQuickpayDao {
	/**
	 * 添加闪惠订单信息
	 * @param 
	 * @return 闪惠订单id
	 */
	public int addQuickpayOrder(UserQuickpayModel userQuickpayModel) throws BaseException;
	/**
	 * 更新闪惠paystate
	 * @param paymentid
	 * @param paystate
	 * @return 
	 */
	public boolean updateUserQuickpayWithPaystate(int paymentid,int  paystate) throws BaseException;
	/**
	 * 更新闪惠订单编号
	 * @param paymentid
	 * @param ordercode
	 * @return 
	 */
	public boolean updateUserQuickpayWithOrdercode(int paymentid,String ordercode) throws BaseException;
	
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
	 * 根据美发店ID查询闪惠订单的数量
	 * @param storeid
	 * @return UserQuickpayModel
	 */
	public int searchUserQuickTotalByStoreID(int storeid) throws BaseException;
	/**
	 * 根据美发店ID查询闪惠订单的信息
	 * @param storeid
	 * @return UserQuickpayModel
	 */
	public List<UserQuicklogSearchModel> searchUserQuick(int storeid, int intPage,
			int intRows) throws BaseException;
	

}
