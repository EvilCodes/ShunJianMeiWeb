package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseDistrictDao;
import com.wenyuankeji.spring.model.BaseDistrictModel;
import com.wenyuankeji.spring.service.IBaseDistrictService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseDistrictServiceImpl implements IBaseDistrictService {

	@Autowired
	private IBaseDistrictDao baseDistrictDao;

	@Override
	public List<BaseDistrictModel> searchBaseDistrictByCityId(int cityId)
			throws BaseException {
		
		return baseDistrictDao.searchBaseDistrictByCityId(cityId);
	}

}
