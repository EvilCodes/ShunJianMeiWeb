package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IServiceattributeDao;
import com.wenyuankeji.spring.model.ServiceattributeModel;
import com.wenyuankeji.spring.service.IServiceattributeService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class ServiceattributeImpl implements IServiceattributeService {

	@Autowired
	private IServiceattributeDao serviceattributeDao;

	@Override
	public ServiceattributeModel searchServiceattribute(String servicecode) throws BaseException{

		return serviceattributeDao.searchServiceattribute(servicecode);
	}

	@Override
	public List<ServiceattributeModel> searchServiceattribute() throws BaseException{
		
		return serviceattributeDao.searchServiceattribute();
	}

}
