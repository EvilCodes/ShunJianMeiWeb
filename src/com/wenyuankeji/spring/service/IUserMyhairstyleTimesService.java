package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserMyhairstyleTimesModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserMyhairstyleTimesService {
	//查询发行时间
	public UserMyhairstyleTimesModel searchUserMyhairstyleTimes(int mystyleID, String servicecode)throws BaseException;
	
	public List<UserMyhairstyleTimesModel> searchUserMyhairstyleTimes()throws BaseException;
	/**
	 * 根据我的发型id查询发型时长
	 * @param mystyleid
	 * @return
	 * @throws BaseException
	 */
	public List<UserMyhairstyleTimesModel> searchUserMyhairstyleTimesByMystyleid(int mystyleid) throws BaseException;
}
