package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IStoreWorkHourDao;
import com.wenyuankeji.spring.model.StoreWorkhourModel;
import com.wenyuankeji.spring.service.IStoreWorkHourService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class StoreWorkHourServiceImpl implements IStoreWorkHourService{

	@Autowired
	private IStoreWorkHourDao storeWorkHourDao;
	
	@Override
	public List<StoreWorkhourModel> searchStoreHourInfo(int wdId, int hour) throws BaseException {
		// TODO Auto-generated method stub
		return storeWorkHourDao.searchStoreHourInfo(wdId, hour);
	}

	@Override
	public boolean updateStoreWorkhour(int orderid, int wdid, int hour, int state) throws BaseException {
		// TODO Auto-generated method stub
		return storeWorkHourDao.updateStoreWorkhour(orderid, wdid, hour, state);
	}

}
