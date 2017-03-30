package com.wenyuankeji.spring.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.ITradeRecordService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class TradeRecordServiceImpl implements ITradeRecordService {

	@Autowired
	private IUserWalletDao userWalletDao;
	@Autowired
	private IUserTradelogDao userTradelogDao;
	@Autowired
	private IOrderinfoDao orderinfoDao;
	
	@Override
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException {
		return userWalletDao.searchUserWallet(userid,ownertype);
	}
	
	@Override
	public List<UserTradelogModel> searchUserTradelog(int walletid,int pageSize,int pageIndex,int type,int sort)
			throws BaseException {
		return userTradelogDao.searchUserTradelog(walletid,pageSize,pageIndex,type,sort);
	}

	@Override
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException {
		return orderinfoDao.searchOrderinfo(orderid);
	}

}
