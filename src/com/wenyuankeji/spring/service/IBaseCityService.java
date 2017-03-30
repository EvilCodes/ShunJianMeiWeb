package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseCityService {

	/**
	 * 根据城市id查找城市信息
	 * @param cityId
	 * @return
	 * @throws BaseException
	 */
	public BaseCityModel searchBaseCity(int cityId)throws BaseException;
	

	/**
	 * 根据省份id查找城市信息
	 * @param cityId
	 * @return
	 * @throws BaseException
	 */
	public List<BaseCityModel> searchBaseCityByProvinceId(int provinceId)throws BaseException;
}
