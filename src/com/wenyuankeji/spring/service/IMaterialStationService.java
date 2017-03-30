package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialStationModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialStationService {

	/**
	 * 查询提取点表
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialStationModel> searchMaterialStation()throws BaseException;
	
	/**
	 * 根据提取点id查询
	 * @param stationid
	 * @return
	 * @throws BaseException
	 */
	public MaterialStationModel searchMaterialStation(int stationid)throws BaseException;
}
