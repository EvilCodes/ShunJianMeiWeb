package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialBrandModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialBrandDao {

	/**
	 * 查找物料品牌表
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialBrandModel> searchMaterialBrand()throws BaseException;
}
