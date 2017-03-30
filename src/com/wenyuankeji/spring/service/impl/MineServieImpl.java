package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IFavoritesDao;
import com.wenyuankeji.spring.dao.IUserCouponsDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.IMineService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class MineServieImpl implements IMineService{
	
	@Autowired
	private IUserinfoDao userinfoDao;
	@Autowired
	private IUserWalletDao userWalletDao;
	@Autowired
	private IFavoritesDao favoritesDao;
	@Autowired
	private IUserCouponsDao userCouponsDao;

	@Override
	public UserinfoModel searchUserinfoById(int userid)throws BaseException {
		return userinfoDao.searchUserinfoById(userid);
	}

	@Override
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException {
		return userWalletDao.searchUserWallet(userid,ownertype);
	}

	@Override
	public int searchFavoriteCount(int userid, int type) throws BaseException {
		return favoritesDao.searchFavoriteCount(userid, type);
	}

	@Override
	public int searchUserCouponsCount(int userid) throws BaseException {
		return userCouponsDao.searchUserCouponsCount(userid);
	}
	
	

}
