package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IStorePhotoMappingDao {

	public StorePhotoMappingModel searchStorePhoto(int storeid, int type)throws BaseException;
	
	public List<StorePhotoMappingModel> searchStorePhotoMapping(int storeid,
			int type)throws BaseException;

	public boolean addStorePhoto(StorePhotoMappingModel storePhotoMappingModel)throws BaseException;

	public boolean updateStorePhoto(StorePhotoMappingModel storePhotoMappingModel) throws BaseException;
}
