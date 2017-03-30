package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserMyhairstylePhotoMappingDao;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.service.IUserMyhairstylePhotoMappingService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserMyhairstylePhotoMappingServiceImpl implements
		IUserMyhairstylePhotoMappingService {

	@Autowired
	private IUserMyhairstylePhotoMappingDao userMyhairstylePhotoMappingDao;

	@Override
	public UserMyhairstylePhotoMappingModel searchUserMyhairstylePhotoMapping(
			int mystyleid,int type) throws BaseException{

		return userMyhairstylePhotoMappingDao
				.searchUserMyhairstylePhotoMapping(mystyleid,type);
	}

}
