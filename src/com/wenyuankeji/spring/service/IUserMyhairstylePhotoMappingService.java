package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserMyhairstylePhotoMappingService {


	/**
	 * 查询发型相册
	 * @param mystyleid 我的发型ID
	 * @param type 类型1列表图,2相册
	 * @return
	 */
	public UserMyhairstylePhotoMappingModel searchUserMyhairstylePhotoMapping(int mystyleid,int type)throws BaseException;

}
