package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserHairstyleMaterialDao;
import com.wenyuankeji.spring.model.UserHairstyleMaterialModel;
import com.wenyuankeji.spring.service.IUserHairstyleMaterialService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserHairstyleMaterialServiceImpl implements
		IUserHairstyleMaterialService {

	@Autowired
	private IUserHairstyleMaterialDao userHairstyleMaterialDao;

	@Override
	public List<UserHairstyleMaterialModel> searchUserHairstyleMaterial(
			int styleid) throws BaseException {

		return userHairstyleMaterialDao.searchUserHairstyleMaterial(styleid);
	}

	@Override
	public List<UserHairstyleMaterialModel> searchUserHairstyleMaterial(
			int styleid, String servicecode) throws BaseException {

		return userHairstyleMaterialDao.searchUserHairstyleMaterial(styleid,
				servicecode);
	}

	@Override
	public UserHairstyleMaterialModel searchUserHairstyleMaterial(int styleid,
			String servicecode, int materialid) throws BaseException {

		return userHairstyleMaterialDao.searchUserHairstyleMaterial(styleid,
				servicecode, materialid);
	}

}
