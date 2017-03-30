package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserEvaluatePhotoMappingDao;
import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.service.IUserEvaluatePhotoMappingService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserEvaluatePhotoMappingServiceImpl implements
		IUserEvaluatePhotoMappingService {

	@Autowired
	private IUserEvaluatePhotoMappingDao userEvaluatePhotoMappingDao;

	@Override
	public List<UserEvaluatePhotoMappingModel> searchUserEvaluatePhotoMappingg(int evaid) throws BaseException{
		
		return userEvaluatePhotoMappingDao.searchUserEvaluatePhotoMappingg(evaid);
	}
	
	/**
	 * 根据评论id找到配图的图片地址
	 * @param evaid
	 * @return
	 * @throws BaseException
	 */
	@Override
	public List<String> searchUserEvaluateicturepathList(int evaid) throws BaseException{
		
		return userEvaluatePhotoMappingDao.searchUserEvaluateicturepathList(evaid);
		
	}
}
