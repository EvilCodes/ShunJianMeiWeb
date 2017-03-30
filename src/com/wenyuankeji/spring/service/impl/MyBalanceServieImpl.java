package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IMyBalanceService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class MyBalanceServieImpl implements IMyBalanceService{

	
	@Autowired
	private IUserWalletDao userWalletDao;
	@Autowired
	private IBaseConfigDao baseConfigDao;

	@Override
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException {
		return userWalletDao.searchUserWallet(userid, ownertype);
	}

	@Override
	public BaseConfigModel searchBaseConfig(String configcode) throws BaseException{
		if (baseConfigDao.searchBaseConfig(configcode) != null) {
			return baseConfigDao.searchBaseConfig(configcode).get(0);
		}else{
			return null;
		}
	}

}
