package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseConfigDao {

	public List<BaseConfigModel> searchBaseConfig()throws BaseException;

	public List<BaseConfigModel> searchBaseConfig(String configcode)throws BaseException;
	
	public BaseConfigModel searchBaseConfigByCode(String configcode)throws BaseException;
	
	public BasePictureModel searchBasePicture(String configvalue)throws BaseException;
	
	public BaseConfigModel searchBaseConfigByCodeAndValue(String configcode, String configvalue)throws BaseException;
}
