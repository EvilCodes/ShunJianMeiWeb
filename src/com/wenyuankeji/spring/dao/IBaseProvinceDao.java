package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseProvinceDao {

	/**
	 * 查找省份
	 * @return
	 * @throws BaseException
	 */
	public List<BaseProvinceModel> searchBaseProvince()throws BaseException;
}
