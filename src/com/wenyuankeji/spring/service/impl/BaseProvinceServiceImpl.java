package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseProvinceDao;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.service.IBaseProvinceService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseProvinceServiceImpl implements IBaseProvinceService {

	@Autowired
	private IBaseProvinceDao baseProvinceDao;

	@Override
	public List<BaseProvinceModel> searchBaseProvince() throws BaseException {

		return baseProvinceDao.searchBaseProvince();
	}

}
