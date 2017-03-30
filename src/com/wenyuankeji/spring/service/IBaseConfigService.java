package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseConfigService {

	public List<BaseConfigModel> searchBaseConfig()throws BaseException;
	
	public List<BaseConfigModel> searchBaseConfig(String configcode)throws BaseException;
	
	public BaseConfigModel searchBaseConfigByCode(String configcode)throws BaseException;
	
	public BasePictureModel searchBasePicture(String configvalue)throws BaseException;
	
}
