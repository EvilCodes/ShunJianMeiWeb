package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialCategoryModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialCategoryService {


	/**
	 * 查物料品类
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialCategoryModel> searchMaterialCategory()throws BaseException;
}
