package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserMyhairstylePhotoMappingDao {

	/**
	 * 查询发型相册
	 * @param mystyleid 我的发型ID
	 * @param type 类型1列表图,2相册
	 * @return
	 */
	public UserMyhairstylePhotoMappingModel searchUserMyhairstylePhotoMapping(int mystyleid,int type)throws BaseException;
	
	/**
	 * 添加发型相册图片
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean addUserMyhairstylePhotoMapping(UserMyhairstylePhotoMappingModel o)throws BaseException;

	/**
	 * 根据发型id删除发型相册图片
	 * @param mystyleid
	 * @return
	 * @throws BaseException
	 */
	public boolean deleteUserMyhairstylePhotoMapping(int mystyleid) throws BaseException;

}
