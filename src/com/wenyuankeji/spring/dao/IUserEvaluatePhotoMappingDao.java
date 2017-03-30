package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserEvaluatePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserEvaluatePhotoMappingDao {

	/**
	 * 查询评论配图
	 * @return
	 */
	public List<UserEvaluatePhotoMappingModel> searchUserEvaluatePhotoMappingg(int evaid)throws BaseException;
	
	/**
	 * 添加评价配图
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean addUserEvaluatePhotoMapping(UserEvaluatePhotoMappingModel o)throws BaseException;

	/**
	 * 添加评价分数
	 * @param evaluate
	 * @param score
	 * @return
	 * @throws BaseException
	 */
	public boolean addUserEvaluateScore(int evaid,String evaluate,String score) throws BaseException;

	/**
	 * 查询评论配图的地址
	 * @param evaid评论id
	 * @return
	 */
	public List<String> searchUserEvaluateicturepathList(int evaid)throws BaseException;
	
	/**
	 * 查询评价配图
	 * @return
	 */
	public List<UserEvaluatePhotoMappingModel> searchUserEvaluatePhotoMapping(int evaid)throws BaseException;


}
