package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialBrandModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialBrandService {

	/**
	 * 查找物料品牌表
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialBrandModel> searchMaterialBrand()throws BaseException;
}
