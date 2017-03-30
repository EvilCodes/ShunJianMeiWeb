package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserProfessionLevelServiceDao;
import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.service.IUserProfessionLevelServiceService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserProfessionLevelServiceServiceImpl implements IUserProfessionLevelServiceService {

	@Autowired
	private IUserProfessionLevelServiceDao userProfessionLevelServiceDao;

	@Override
	public List<UserProfessionLevelServiceModel> searchUserProfessionLevelService(int levelid) throws BaseException{

		return userProfessionLevelServiceDao.searchUserProfessionLevelService(levelid);
	}

	@Override
	public UserProfessionLevelServiceModel searchUserProfessionLevelService(
			int levelid, String servicecode) throws BaseException{
		return userProfessionLevelServiceDao.searchUserProfessionLevelService(levelid, servicecode);
	}

}
