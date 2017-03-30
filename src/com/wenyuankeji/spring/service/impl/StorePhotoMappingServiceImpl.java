package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBasePictureDao;
import com.wenyuankeji.spring.dao.IStorePhotoMappingDao;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.model.StorePhotoMappingModel;
import com.wenyuankeji.spring.service.IStorePhotoMappingService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class StorePhotoMappingServiceImpl implements IStorePhotoMappingService {

	@Autowired
	private IStorePhotoMappingDao storePhotoMappingDao;
	
	@Autowired
	private IBasePictureDao  basePictureDao;

	@Override
	public StorePhotoMappingModel searchStorePhoto(int storeid, int type) throws BaseException{

		return storePhotoMappingDao.searchStorePhoto(storeid, type);
	}

	@Override
	public List<StorePhotoMappingModel> searchStorePhotoMapping(int storeid,
			int type) throws BaseException{
		
		return storePhotoMappingDao.searchStorePhotoMapping(storeid, type);
	}

	@Override
	public boolean addStorePhoto(StorePhotoMappingModel storePhotoMappingModel) throws BaseException{
		
		return storePhotoMappingDao.addStorePhoto(storePhotoMappingModel);
	}

	@Override
	public boolean addOrUpdImg(int storeid,int type,String fileName,String serverPath) throws BaseException{
		
		try {
			Date date = new Date();
			//查询是否有既存的图片信息
			StorePhotoMappingModel mappingModel = storePhotoMappingDao.searchStorePhoto(storeid, type);
			if (mappingModel == null) {
				
				//首次上传图片
				
				//目前用户没有图片的场合
				//添加图片
				BasePictureModel basePictureModel = new BasePictureModel();
				basePictureModel.setPicturepath(fileName);
				//basePictureModel.setPicturepath("userImg/"+fileName);
				basePictureModel.setCreatetime(date);
				//执行添加图片
				int pid = basePictureDao.addBasePictureReturnID(basePictureModel);
				if (pid > 0) {
					//保存相册表信息
					StorePhotoMappingModel storePhotoMappingModel = new StorePhotoMappingModel();
					storePhotoMappingModel.setPicid(pid);
					storePhotoMappingModel.setStoreid(storeid);
					storePhotoMappingModel.setType(type);
					storePhotoMappingModel.setCreatetime(date);
					if (storePhotoMappingDao.addStorePhoto(storePhotoMappingModel)) {
						return true;
					}else {
						//相册保存失败
						return false;
					}
				}else {
					//图片保存失败
					return false;
				}
			}else {
				//已存在相册信息的场合
				
				//判断对应图片信息是否存在
				BasePictureModel basePictureModel = mappingModel.getBasePicture();
				if (basePictureModel != null) {
					//存在的场合：修改图片信息
					
					//判断当前传入的图片是否和服务上的一直
					if (!basePictureModel.getPicturepath().equals(fileName)) {
					//if (!basePictureModel.getPicturepath().equals("userImg/"+fileName)) {
						//不一致的场合则执行更新
						
						//删除服务的文件from
						
						/////////to
						
						//执行修改图片
						basePictureModel.setPicturepath(fileName);
						//basePictureModel.setPicturepath("userImg/"+fileName);
						if (basePictureDao.updateBasePicture(basePictureModel)) {
							return true;
						}else {
							return false;
						}
						
					}else {
						return true;
					}
					
				}else {
					//没有图片信息场合:添加图片
					basePictureModel = new BasePictureModel();
					basePictureModel.setPicturepath(fileName);
					//basePictureModel.setPicturepath("userImg/"+fileName);
					basePictureModel.setCreatetime(date);
					//执行添加图片
					int picid = basePictureDao.addBasePictureReturnID(basePictureModel);
					mappingModel.setPicid(picid);
					
					//执行修改相册
					if (storePhotoMappingDao.updateStorePhoto(mappingModel)) {
						return true;
					}else {
						return false;
					}
					
				}
				
			}
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}
	
}
