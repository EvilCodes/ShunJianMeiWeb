package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IMaterialDeliveryrecordDetailDao;
import com.wenyuankeji.spring.model.MaterialDeliveryrecordDetailModel;
import com.wenyuankeji.spring.service.IMaterialDeliveryrecordDetailService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class MaterialDeliveryrecordDetailServiceImpl implements
		IMaterialDeliveryrecordDetailService {

	@Autowired
	private IMaterialDeliveryrecordDetailDao materialDeliveryrecordDetailDao;
	
	@Override
	public List<MaterialDeliveryrecordDetailModel> searchmaterialDeliveryrecordDetail(
			int recordid) throws BaseException {
		
		return materialDeliveryrecordDetailDao.searchmaterialDeliveryrecordDetail(recordid);
	}

}
