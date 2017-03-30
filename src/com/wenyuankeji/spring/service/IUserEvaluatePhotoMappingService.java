package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;


public interface IUserEvaluatePhotoMappingService {

	/**
	 * 查询评论图片
	 * @return
	 */
	public List<UserEvaluatePhotoMappingModel> searchUserEvaluatePhotoMappingg(int evaid)throws BaseException;
	
	/**
	 * 根据评论id找到配图的图片地址
	 * @param evaid
	 * @return
	 * @throws BaseException
	 */
	public List<String> searchUserEvaluateicturepathList(int evaid) throws BaseException;

}
