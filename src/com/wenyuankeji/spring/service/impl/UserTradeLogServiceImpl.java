package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserTradelogSearchTXModel;
import com.wenyuankeji.spring.service.IUserTradeLogService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserTradeLogServiceImpl implements IUserTradeLogService{

	@Autowired
	private IUserTradelogDao userTradelogDao;

	@Override
	public boolean addUserTradelog(UserTradelogModel userTradelogModel) throws BaseException {

		return userTradelogDao.addUserTradelog(userTradelogModel);
	}

	@Override
	public List<UserTradelogSearchTXModel> searchUserTradelogTX(int ownerid,
			int page, int rows) throws BaseException {
		
		return userTradelogDao.searchUserTradelogTX(ownerid, page, rows);
	}

	@Override
	public int searchUserTradelogTX(int ownerid) throws BaseException {
		
		return userTradelogDao.searchUserTradelogTX(ownerid);
	}
	
	
}
