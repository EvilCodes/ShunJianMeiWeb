package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserCouponsDao;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.service.ICouponListService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class CouponListServieImpl implements ICouponListService{

	@Autowired
	private IUserCouponsDao userCouponsDao;
	
	@Override
	public List<UserCouponsModel> searchUserCoupons(int userid,int pageSize,int pageIndex)
			throws BaseException {
		return userCouponsDao.searchUserCoupons(userid,pageSize,pageIndex);
	}

}
