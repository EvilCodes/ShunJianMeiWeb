package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBasePictureDao;
import com.wenyuankeji.spring.dao.IUserPhotoMappingDao;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.service.IUserPhotoMappingService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserPhotoMappingServiceImpl implements IUserPhotoMappingService {

	@Autowired
	private IUserPhotoMappingDao userPhotoMappingDao;
	@Autowired
	private IBasePictureDao  basePictureDao;
	
	@Override
	public UserPhotoMappingModel searchUserPhoto(int userid, int type) throws BaseException{
		return userPhotoMappingDao.searchUserPhoto(userid, type);
	}

	@Override
	public UserPhotoMappingModel searchUserPhoto(int userid, int type,int sortnum) throws BaseException{
		
		return userPhotoMappingDao.searchUserPhoto(userid, type, sortnum);
	}
	
	@Override
	public UserPhotoMappingModel addUserPhoto(UserPhotoMappingModel userphoto) throws BaseException{
		return userPhotoMappingDao.addUserPhoto(userphoto);
	}
	
	@Override
	public boolean updateUserPhoto(UserPhotoMappingModel userphoto) throws BaseException{
		
		return userPhotoMappingDao.updateUserPhoto(userphoto);
	}
	
	@Override
	public List<UserPhotoMappingModel> searchUserPhotos(int userid, int type) throws BaseException{
		return userPhotoMappingDao.searchUserPhotos(userid, type);
	}

	@Override
	public float searchUserPhotoMax() throws BaseException {
		
		return userPhotoMappingDao.searchUserPhotoMax();
	}

	@Override
	public boolean addUserPhotoInfo(UserPhotoMappingModel userphoto) throws BaseException {
		return userPhotoMappingDao.addUserPhotoInfo(userphoto);
	}
	
//	@Override
//	public boolean addOrUpdImg(int userId,int type,String fileName,String serverPath) throws BaseException{
//		
//		try {
//			Date date = new Date();
//			//查询是否有既存的图片信息
//			UserPhotoMappingModel mappingModel = userPhotoMappingDao.searchUserPhoto(userId, type);
//			if (mappingModel == null) {
//				
//				//首次上传图片
//				
//				//目前用户没有图片的场合
//				//添加图片
//				BasePictureModel basePictureModel = new BasePictureModel();
//				basePictureModel.setPicturepath("userImg/"+fileName);
//				basePictureModel.setCreatetime(date);
//				//执行添加图片
//				int pid = basePictureDao.addBasePictureReturnID(basePictureModel);
//				if (pid > 0) {
//					//保存相册表信息
//					UserPhotoMappingModel userPhotoMappingModel = new UserPhotoMappingModel();
//					userPhotoMappingModel.setPicid(pid);
//					userPhotoMappingModel.setUserid(userId);
//					userPhotoMappingModel.setType(type);
//					userPhotoMappingModel.setCreatetime(date);
//					if (userPhotoMappingDao.addUserPhoto(userPhotoMappingModel) != null) {
//						return true;
//					}else {
//						//相册保存失败
//						return false;
//					}
//				}else {
//					//图片保存失败
//					return false;
//				}
//			}else {
//				//已存在相册信息的场合
//				
//				//判断对应图片信息是否存在
//				BasePictureModel basePictureModel = mappingModel.getBasePicture();
//				if (basePictureModel != null) {
//					//存在的场合：修改图片信息
//					
//					//判断当前传入的图片是否和服务上的一直
//					if (!basePictureModel.getPicturepath().equals("userImg/"+fileName)) {
//						//不一致的场合则执行更新
//						
//						//删除服务的文件from
//						
//						/////////to
//						
//						//执行修改图片
//						basePictureModel.setPicturepath("userImg/"+fileName);
//						if (basePictureDao.updateBasePicture(basePictureModel)) {
//							return true;
//						}else {
//							return false;
//						}
//						
//					}else {
//						return true;
//					}
//					
//				}else {
//					//没有图片信息场合:添加图片
//					basePictureModel = new BasePictureModel();
//					basePictureModel.setPicturepath("userImg/"+fileName);
//					basePictureModel.setCreatetime(date);
//					//执行添加图片
//					int picid = basePictureDao.addBasePictureReturnID(basePictureModel);
//					mappingModel.setPicid(picid);
//					
//					//执行修改相册
//					if (userPhotoMappingDao.updateUserPhoto(mappingModel)) {
//						return true;
//					}else {
//						return false;
//					}
//					
//				}
//				
//			}
//			
//		} catch (Exception e) {
//			throw new BaseException(e.getMessage());
//		}
//		
//	}
	
	@Override
	public boolean addOrUpdImg(int userId,int type,int sortnum,String fileName,String serverPath) throws BaseException{
		
		try {
			Date date = new Date();
			//查询是否有既存的图片信息
			UserPhotoMappingModel mappingModel = null;
			
			if (sortnum == 0) {
				mappingModel = userPhotoMappingDao.searchUserPhoto(userId, type);
			}else {
				mappingModel = userPhotoMappingDao.searchUserPhoto(userId, type,sortnum);
			}
			
			if (mappingModel == null) {
				
				//首次上传图片
				
				//目前用户没有图片的场合
				//添加图片
				BasePictureModel basePictureModel = new BasePictureModel();
				basePictureModel.setPicturepath("userImg/"+fileName);
				basePictureModel.setCreatetime(date);
				//执行添加图片
				int pid = basePictureDao.addBasePictureReturnID(basePictureModel);
				if (pid > 0) {
					//保存相册表信息
					UserPhotoMappingModel userPhotoMappingModel = new UserPhotoMappingModel();
					userPhotoMappingModel.setPicid(pid);
					userPhotoMappingModel.setUserid(userId);
					userPhotoMappingModel.setType(type);
					userPhotoMappingModel.setSortnum(sortnum);
					userPhotoMappingModel.setCreatetime(date);
					if (userPhotoMappingDao.addUserPhoto(userPhotoMappingModel) != null) {
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
					if (!basePictureModel.getPicturepath().equals("userImg/"+fileName)) {
						//不一致的场合则执行更新
						
						//删除服务的文件from
						
						/////////to
						
						//执行修改图片
						basePictureModel.setPicturepath("userImg/"+fileName);
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
					basePictureModel.setPicturepath("userImg/"+fileName);
					basePictureModel.setCreatetime(date);
					//执行添加图片
					int picid = basePictureDao.addBasePictureReturnID(basePictureModel);
					mappingModel.setPicid(picid);
					
					//执行修改相册
					if (userPhotoMappingDao.updateUserPhoto(mappingModel)) {
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
