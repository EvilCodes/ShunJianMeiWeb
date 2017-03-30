package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserCashrecordDao {

	/**
	 * 添加提现记录
	 * @throws BaseException
	 */
	public boolean addUserCashrecord(UserCashrecordModel userCashrecord) throws BaseException;
	
	/**
	 * 添我的优惠卷
	 * @throws BaseException
	 */
	public Integer addUserCouponsModel(UserCouponsModel o)throws BaseException;
	
	/**
	 * 添加提现记录并修改金额
	 * @throws BaseException
	 */
	public boolean addUserCashrecordEditBalance(UserCashrecordModel userCashrecord, float balance,int ownerid, int ownertype) throws BaseException;
}
