package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseStarInfoDao;
import com.wenyuankeji.spring.model.BaseStarinfoModel;
import com.wenyuankeji.spring.service.IBaseStarInfoService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseStarInfoServiceImpl implements IBaseStarInfoService {

	@Autowired
	private IBaseStarInfoDao baseStarInfoDao;

	@Override
	public List<BaseStarinfoModel> searchBaseStarInfo() throws BaseException {
		return baseStarInfoDao.searchBaseStarInfo();
	}


}
