package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.ICouponsDao;
import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IUserCouponsDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IOrderPayService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class OrderPayServiceImpl implements IOrderPayService{

	@Autowired
	private IOrderinfoDao orderinfoDao;
	@Autowired
	private ICouponsDao couponsDao;
	@Autowired
	private IUserCouponsDao userCouponsDao;
	@Autowired
	private IUserWalletDao userWalletDao;
	
	@Override
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException {
		return orderinfoDao.searchOrderinfo(orderid);
	}

	@Override
	public List<UserCouponsModel> searchUserCoupons(int userid)
			throws BaseException {
		return userCouponsDao.searchUserCoupons(userid);
	}

	@Override
	public List<CouponsModel> searchCoupons() throws BaseException {
		return couponsDao.searchCoupons();
	}

	@Override
	public List<OrderdetailModel> searchOrderdetail(int orderid)
			throws BaseException {
		return orderinfoDao.searchOrderdetail(orderid);
	}
	
	@Override
	public List<UserCouponsModel> searchUserCoupon(int orderid) throws BaseException {
		return userCouponsDao.searchUserCoupon(orderid);
	}

	@Override
	public UserCouponsModel searchCoupons(String orderid, String servicecode) throws BaseException {
		return userCouponsDao.searchCoupons(orderid, servicecode);
	}

	@Override
	public UserWalletModel searchUserWallet(int userid, int ownertype) throws BaseException {
		return userWalletDao.searchUserWallet(userid, ownertype);
	}

	
}
