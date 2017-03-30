package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IAppversionDao;
import com.wenyuankeji.spring.model.AppversionModel;
import com.wenyuankeji.spring.service.IAppversionService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class AppversionServiceImpl implements IAppversionService {

	@Autowired
	private IAppversionDao appversionDao;

	@Override
	public AppversionModel searchAppversion(String vcode, int type)
			throws BaseException {
		
		return appversionDao.searchAppversion(vcode, type);
	}
	
}
