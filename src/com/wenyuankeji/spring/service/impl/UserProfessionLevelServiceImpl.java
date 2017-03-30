package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserProfessionLevelDao;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.service.IUserProfessionLevelService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserProfessionLevelServiceImpl implements
		IUserProfessionLevelService {

	@Autowired
	private IUserProfessionLevelDao userProfessionLevelDao;

	@Override
	public UserProfessionLevelModel searchUserProfessionLevel(int levelid) throws BaseException{

		return userProfessionLevelDao.searchUserProfessionLevel(levelid);
	}

}
