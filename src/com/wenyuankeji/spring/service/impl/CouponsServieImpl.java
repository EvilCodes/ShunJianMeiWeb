package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.ICouponsDao;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.service.ICouponsService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class CouponsServieImpl implements ICouponsService{
	
	@Autowired
	private ICouponsDao couponsDao;
	
	@Override
	public UserCouponsModel searchCoupons(int userid,String servicecode)throws BaseException {
		return couponsDao.searchCoupons(userid,servicecode);
	}

	@Override
	public CouponsModel searchCouponByID(String couponID)
			throws BaseException {
		return couponsDao.searchCouponByID(couponID);
	}

	@Override
	public List<CouponsModel> searchCouponsByOrderPay(double amount)
			throws BaseException {
		return couponsDao.searchCouponsByOrderPay(amount);
	}

}
