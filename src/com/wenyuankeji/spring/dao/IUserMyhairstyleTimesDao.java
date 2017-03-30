package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserMyhairstyleTimesModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserMyhairstyleTimesDao {
	//查询发行时间
	public UserMyhairstyleTimesModel searchUserMyhairstyleTimes(int mystyleID,String servicecode)throws BaseException;
	
	public List<UserMyhairstyleTimesModel> searchUserMyhairstyleTimes()throws BaseException;
	
	/**
	 * 添加我的发型时间
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean addUserMyhairstyleTimes(UserMyhairstyleTimesModel o)throws BaseException;
	
	/**
	 * 根据发型id删除我的发型时间
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean deleteUserMyhairstyleTimes(int mystyleid)throws BaseException;
	
	/**
	 * 根据我的发型id查询发型时长
	 * @param mystyleid
	 * @return
	 * @throws BaseException
	 */
	public List<UserMyhairstyleTimesModel> searchUserMyhairstyleTimesByMystyleid(int mystyleid) throws BaseException;
}
