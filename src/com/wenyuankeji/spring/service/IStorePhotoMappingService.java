package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IStorePhotoMappingService {

	public StorePhotoMappingModel searchStorePhoto(int storeid, int type)throws BaseException;

	public List<StorePhotoMappingModel> searchStorePhotoMapping(int storeid,
			int type)throws BaseException;

	public boolean addStorePhoto(StorePhotoMappingModel storePhotoMappingModel)throws BaseException;
	
	public boolean addOrUpdImg(int storeid,int type,String fileName,String serverPath) throws BaseException;
}
