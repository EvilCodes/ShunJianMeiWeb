package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserPhotoMappingDao {

	/**
	 * 更具用户id和图片类型查询图片信息
	 * @param userid用户id
	 * @param type图片类型
	 * @return
	 */
	public UserPhotoMappingModel searchUserPhoto(int userid,int type)throws BaseException;
	
	public List<UserPhotoMappingModel> searchUserPhotos(int userid,int type)throws BaseException;
	
	public UserPhotoMappingModel addUserPhoto(UserPhotoMappingModel userphoto)throws BaseException;
	

	public boolean updateUserPhoto(UserPhotoMappingModel userphoto) throws BaseException;
	
	public List<UserPhotoMappingModel> searchUserPhotoList(int userid, int type)throws BaseException;
	
	public UserPhotoMappingModel searchUserPhoto(int userid, int type,int sortnum) throws BaseException;
	
	public float searchUserPhotoMax()throws BaseException;
	
	public boolean addUserPhotoInfo(UserPhotoMappingModel userphoto) throws BaseException;
}
