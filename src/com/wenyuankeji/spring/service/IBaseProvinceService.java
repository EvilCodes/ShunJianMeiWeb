package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseProvinceService {

	/**
	 * 查找省份
	 * @return
	 * @throws BaseException
	 */
	public List<BaseProvinceModel> searchBaseProvince()throws BaseException;
}
