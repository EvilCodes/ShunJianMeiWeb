package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IMaterialCategoryDao;
import com.wenyuankeji.spring.model.MaterialCategoryModel;
import com.wenyuankeji.spring.service.IMaterialCategoryService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class MaterialCategoryServiceImpl implements IMaterialCategoryService {

	@Autowired
	private IMaterialCategoryDao materialCategoryDao;
	
	@Override
	public List<MaterialCategoryModel> searchMaterialCategory()
			throws BaseException {
		
		return materialCategoryDao.searchMaterialCategory();
	}

}
