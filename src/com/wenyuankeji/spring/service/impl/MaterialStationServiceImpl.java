package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IMaterialStationDao;
import com.wenyuankeji.spring.model.MaterialStationModel;
import com.wenyuankeji.spring.service.IMaterialStationService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class MaterialStationServiceImpl implements IMaterialStationService {

	@Autowired
	private IMaterialStationDao materialStationDao;
	
	@Override
	public List<MaterialStationModel> searchMaterialStation()
			throws BaseException {
		
		return materialStationDao.searchMaterialStation();
	}

	@Override
	public MaterialStationModel searchMaterialStation(int stationid)
			throws BaseException {
		
		return materialStationDao.searchMaterialStation(stationid);
	}

}
