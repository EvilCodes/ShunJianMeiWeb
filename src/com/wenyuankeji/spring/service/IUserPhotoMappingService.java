package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserPhotoMappingService {
	/**
	 * 更具用户id和图片类型查询图片信息
	 * @param userid用户id
	 * @param type图片类型
	 * @return
	 */
	public UserPhotoMappingModel searchUserPhoto(int userid,int type)throws BaseException;
	
	public boolean updateUserPhoto(UserPhotoMappingModel userphoto) throws BaseException;

	public UserPhotoMappingModel searchUserPhoto(int userid, int type,int sortnum) throws BaseException;
	
	public UserPhotoMappingModel addUserPhoto(UserPhotoMappingModel userphoto)throws BaseException;
	

	//public List<UserPhotoMappingModel> searchUserPhotoList(int userid, int type)throws BaseException;
	
	public List<UserPhotoMappingModel> searchUserPhotos(int userid,int type)throws BaseException;
	
	//public boolean addOrUpdImg(int userId,int type,String fileName,String serverPath)throws BaseException;
	
	public float searchUserPhotoMax()throws BaseException;
	
	/**
	 * 用户端保存图片
	 * @param userId用户id
	 * @param type图片类型
	 * @param sortnum
	 * @param fileName新的文件名字
	 * @param serverPath
	 * @return
	 * @throws BaseException
	 */
	public boolean addOrUpdImg(int userId,int type,int sortnum,String fileName,String serverPath) throws BaseException;
	
	public boolean addUserPhotoInfo(UserPhotoMappingModel userphoto) throws BaseException;
}
