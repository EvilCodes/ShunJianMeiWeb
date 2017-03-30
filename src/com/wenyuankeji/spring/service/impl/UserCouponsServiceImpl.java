package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserCouponsDao;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.service.IUserCouponsService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserCouponsServiceImpl implements IUserCouponsService {

	@Autowired
	private IUserCouponsDao userCouponsDao;
	
	
	@Override
	public List<UserCouponsModel> searchUserCoupons(int userid)throws BaseException {
		
		return userCouponsDao.searchUserCoupons(userid);
	}
	@Override
	public UserCouponsModel searchUserCouponsByID(int id)throws BaseException {
		
		return userCouponsDao.searchUserCouponsByID(id);
	}
	@Override
	public UserCouponsModel searchCoupons(int couponid, int userid) throws BaseException {
		return userCouponsDao.searchCoupons(couponid, userid);
	}
	@Override
	public boolean updateUserCoupons(String mobile, int userid) throws BaseException {
		return userCouponsDao.updateUserCoupons(mobile, userid);
	}
}
