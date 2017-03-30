package com.wenyuankeji.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenyuankeji.spring.dao.IUserCashrecordDao;
import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IUserCashrecordService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserCashrecordServiceImpl implements IUserCashrecordService {

	@Autowired
	private IUserCashrecordDao userCashrecordDao;
	@Autowired
	private IUserTradelogDao userTradelogDao;
	@Autowired
	private IUserWalletDao userWalletDao;
	
	@Override
	public boolean addUserCashrecord(UserCashrecordModel userCashrecord,UserWalletModel userWalletModel)
			throws BaseException {
	
		// 1、修改钱包金额
		if (userWalletDao.updateUserWalletByUserId(userWalletModel)) {
			//1、添加提现记录
			if (userCashrecordDao.addUserCashrecord(userCashrecord)) {
				//添加提现流水
				UserTradelogModel userTradelogModel = new UserTradelogModel();
				userTradelogModel.setWalletid(userWalletModel.getWalletid());
				userTradelogModel.setAmount((float) userCashrecord.getAmount());
				userTradelogModel.setLogtype(4);//4提现
				userTradelogModel.setRemark("提现");
				userTradelogModel.setCreatetime(new Date());
				return userTradelogDao.addUserTradelog(userTradelogModel);
			}
		}
		return false;
	}

	@Override
	public boolean addUserCashrecordEditBalance(
			UserCashrecordModel userCashrecord, float balance, int ownerid,
			int ownertype, UserWalletModel userWalletModel) throws BaseException {
		
		if(userCashrecordDao.addUserCashrecordEditBalance(userCashrecord, balance, ownerid, ownertype)){
			//1、添加提现记录
			if (userCashrecordDao.addUserCashrecord(userCashrecord)) {
				//添加提现流水
				UserTradelogModel userTradelogModel = new UserTradelogModel();
				userTradelogModel.setWalletid(userWalletModel.getWalletid());
				userTradelogModel.setAmount((float) userCashrecord.getAmount());
				userTradelogModel.setLogtype(4);//4提现
				userTradelogModel.setRemark("提现");
				userTradelogModel.setCreatetime(new Date());
				return userTradelogDao.addUserTradelog(userTradelogModel);
			}
		}
		
		return false;
	}

}
