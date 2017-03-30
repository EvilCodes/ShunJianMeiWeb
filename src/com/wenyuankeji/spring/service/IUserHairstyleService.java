package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserHairstyleService {

	public List<UserHairstyleModel> searchUserHairstyle()throws BaseException;
	
	public UserHairstyleModel searchUserHairstyleById(int id)throws BaseException;
	
	/**
	 * 根据用户id查询
	 * @param userID
	 * @return
	 * @throws BaseException
	 */
	public List<UserHairstyleModel> searchUserHairstyleByUserID(String userID) throws BaseException;
}
