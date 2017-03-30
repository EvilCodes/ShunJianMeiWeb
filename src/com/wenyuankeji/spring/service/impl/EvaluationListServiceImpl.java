package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserEvaluateDao;
import com.wenyuankeji.spring.dao.IUserEvaluatePhotoMappingDao;
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.service.IEvaluationListService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class EvaluationListServiceImpl implements IEvaluationListService {

	@Autowired
	private IUserEvaluateDao userEvaluateDao;
	@Autowired
	private IUserEvaluatePhotoMappingDao userEvaluatePhotoMappingDao;
	
	@Override
	public List<UserEvaluateModel> searchUserEvaluateByItem(int item, int evaid, int styleID)
			throws BaseException {
		return userEvaluateDao.searchUserEvaluateByItem(item, evaid, styleID);
	}

	@Override
	public List<UserEvaluatePhotoMappingModel> searchUserEvaluatePhotoMapping(
			int evaid) throws BaseException {
		return userEvaluatePhotoMappingDao.searchUserEvaluatePhotoMapping(evaid);
	}

	@Override
	public List<UserEvaluateModel> searchUserEvaluateByStore(int item, int evaid)
			throws BaseException {
		return userEvaluateDao.searchUserEvaluateByStore(item, evaid);
	}

	

}
