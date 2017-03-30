package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseConfigServiceImpl implements IBaseConfigService{

	@Autowired
	private IBaseConfigDao baseConfigDao;

	@Override
	public List<BaseConfigModel> searchBaseConfig()throws BaseException{
		
		return baseConfigDao.searchBaseConfig();
	}
	
	@Override
	public List<BaseConfigModel> searchBaseConfig(String configcode)throws BaseException{
		
		return baseConfigDao.searchBaseConfig(configcode);
	}
	
	@Override
	public BasePictureModel searchBasePicture(String configvalue) throws BaseException{
		
		return baseConfigDao.searchBasePicture(configvalue);
	}

	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode)throws BaseException {
		
		return baseConfigDao.searchBaseConfigByCode(configcode);
	}
	
}
