package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserHairstyleDao {

	public List<UserHairstyleModel> searchUserHairstyle()throws BaseException;
	
	public UserHairstyleModel searchUserHairstyleById(int id)throws BaseException;
	
	/**
	 * 更新发型的使用人数
	 * @param mystyleid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateUsednum(int mystyleid)throws BaseException;
	
	/**
	 * 查询发型项目
	 * @param styleids
	 * @return
	 * @throws BaseException
	 */
	public List<UserHairstyleModel> searchUserHairstyle(String styleids) throws BaseException;
	
	/**
	 * 根据用户id查询我的发型
	 * @param userID
	 * @return
	 * @throws BaseException
	 */
	public List<UserMyhairstyleModel> searchUserMyhairstyleByUserID(String userID) throws BaseException;
}
