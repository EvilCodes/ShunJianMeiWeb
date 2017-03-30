package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBasePictureService {

	public BasePictureModel searchBasePicture(int configvalue)throws BaseException;
	
	public BasePictureModel searchBasePicture(String picturepath)throws BaseException;

	public boolean addBasePicture(BasePictureModel basePictureModel)throws BaseException;
	
	public BasePictureModel searchBasePictureByID(int picid) throws BaseException;
	
	public Integer addBasePictureReturnID(BasePictureModel basePictureModel) throws BaseException;
}
