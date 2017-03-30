package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IMaterialBrandDao;
import com.wenyuankeji.spring.model.MaterialBrandModel;
import com.wenyuankeji.spring.service.IMaterialBrandService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class MaterialBrandServiceImpl implements IMaterialBrandService {

	@Autowired
	private IMaterialBrandDao materialBrandDao;
	
	@Override
	public List<MaterialBrandModel> searchMaterialBrand() throws BaseException {
		
		return materialBrandDao.searchMaterialBrand();
	}

}
