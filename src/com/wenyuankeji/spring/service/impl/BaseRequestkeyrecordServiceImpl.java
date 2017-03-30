package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenyuankeji.spring.dao.IBaseRequestKeyRecordDao;
import com.wenyuankeji.spring.model.BaseRequestKeyRecord;
import com.wenyuankeji.spring.service.IBaseRequestkeyrecordService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseRequestkeyrecordServiceImpl implements IBaseRequestkeyrecordService {

	
	@Autowired
	private IBaseRequestKeyRecordDao baseRequestKeyRecordDao;
	
	@Override
	public int addBaseRequestKeyRecord(String keystring) throws BaseException {
		BaseRequestKeyRecord o = new BaseRequestKeyRecord();
		o.setKeystring(keystring);//请求记录KEY
		return baseRequestKeyRecordDao.addBaseRequestKeyRecord(o);
	}

	@Override
	public boolean selectBaseRequestKeyRecord(String keystring) throws BaseException {
		return baseRequestKeyRecordDao.selectBaseRequestKeyRecord(keystring);
	}

	
}
