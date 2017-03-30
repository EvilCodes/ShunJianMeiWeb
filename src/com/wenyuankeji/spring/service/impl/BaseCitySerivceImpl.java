package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseCityDao;
import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.service.IBaseCityService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseCitySerivceImpl implements IBaseCityService {

	@Autowired
	private IBaseCityDao baseCityDao;
	
	@Override
	public BaseCityModel searchBaseCity(int cityId) throws BaseException{
		
		return baseCityDao.searchBaseCity(cityId);
	}

	@Override
	public List<BaseCityModel> searchBaseCityByProvinceId(int provinceId)
			throws BaseException {
		
		return baseCityDao.searchBaseCityByProvinceId(provinceId);
	}

}
