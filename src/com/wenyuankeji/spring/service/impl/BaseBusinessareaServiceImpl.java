package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseBusinessareaDao;
import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.service.IBaseBusinessareaService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseBusinessareaServiceImpl implements IBaseBusinessareaService {

	@Autowired
	private IBaseBusinessareaDao baseBusinessareaDao;
	
	@Override
	public List<BaseBusinessareaModel> searchBaseBusinessarea(int cityid) throws BaseException {
		
		return baseBusinessareaDao.searchBaseBusinessarea(cityid);
	}

}
