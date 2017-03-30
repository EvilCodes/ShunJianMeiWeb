package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBasePictureDao {

	public BasePictureModel searchBasePicture(int configvalue)throws BaseException;
	
	public BasePictureModel searchBasePicture(String picturepath)throws BaseException;

	public boolean addBasePicture(BasePictureModel basePictureModel)throws BaseException;
	
	public Integer addBasePictureReturnID(BasePictureModel basePictureModel) throws BaseException;

	public boolean updateBasePicture(BasePictureModel basePictureModel) throws BaseException;

}