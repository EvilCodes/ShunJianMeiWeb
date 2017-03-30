package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseDistrictModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseDistrictService {

	/**
	 * 根据城市id查找区信息
	 * @param cityId
	 * @return
	 * @throws BaseException
	 */
	public List<BaseDistrictModel> searchBaseDistrictByCityId(int cityId)throws BaseException;
}
