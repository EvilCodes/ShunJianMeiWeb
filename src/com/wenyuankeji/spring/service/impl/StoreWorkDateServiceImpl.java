package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IStoreWorkdateDao;
import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.service.IStoreWorkDateService;

@Service
public class StoreWorkDateServiceImpl implements IStoreWorkDateService{

	@Autowired
	private IStoreWorkdateDao storeWorkdateDao;
	
	@Override
	public StoreWorkdateModel searchStoreWorkdate(int storeId, int year, int month, int day) {
		// TODO Auto-generated method stub
		return storeWorkdateDao.searchStoreWorkdate(storeId, year, month, day);
	}

}
