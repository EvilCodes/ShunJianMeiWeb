package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserCashrecordService {

	/**
	 * 添加提现记录,更新钱包，添加流水记录
	 * @throws BaseException
	 */
	public boolean addUserCashrecord(UserCashrecordModel userCashrecord,UserWalletModel userWalletModel) throws BaseException;
	
	/**
	 * 添加提现记录并修改金额
	 * @throws BaseException
	 */
	public boolean addUserCashrecordEditBalance(UserCashrecordModel userCashrecord, float balance,int ownerid, int ownertype,UserWalletModel userWalletModel) throws BaseException;
}
