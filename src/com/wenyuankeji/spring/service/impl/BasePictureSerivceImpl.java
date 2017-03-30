package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBasePictureDao;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.service.IBasePictureService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BasePictureSerivceImpl implements IBasePictureService {

	@Autowired
	private IBasePictureDao basePictureDao;

	@Override
	public BasePictureModel searchBasePicture(int configvalue) throws BaseException{
		
		return basePictureDao.searchBasePicture(configvalue);
	}

	@Override
	public boolean addBasePicture(BasePictureModel basePictureModel) throws BaseException{
		
		return basePictureDao.addBasePicture(basePictureModel);
	}

	@Override
	public Integer addBasePictureReturnID(BasePictureModel basePictureModel) throws BaseException{
		
		return basePictureDao.addBasePictureReturnID(basePictureModel);
	}
	
	@Override
	public BasePictureModel searchBasePicture(String picturepath) throws BaseException{
		
		return basePictureDao.searchBasePicture(picturepath);
	}
	
	@Override
	public BasePictureModel searchBasePictureByID(int picid) throws BaseException{
		return basePictureDao.searchBasePicture(picid);
	}
}
