package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IUserWalletService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserWalletServiceImpl implements IUserWalletService {

	@Autowired
	private IUserWalletDao userWalletDao;

	@Override
	public UserWalletModel searchUserWallet(int userid, int ownertype)
			throws BaseException {

		return userWalletDao.searchUserWallet(userid, ownertype);
	}

	@Override
	public UserWalletModel addUserWallet(UserWalletModel o)
			throws BaseException {
		return userWalletDao.addUserWallet(o);
	}

}
