package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserMyhairstyleTimesDao;
import com.wenyuankeji.spring.model.UserMyhairstyleTimesModel;
import com.wenyuankeji.spring.service.IUserMyhairstyleTimesService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserMyhairstyleTimesServiceImpl implements IUserMyhairstyleTimesService {

	@Autowired
	private IUserMyhairstyleTimesDao userMyhairstyleTimesDao;

	@Override
	public UserMyhairstyleTimesModel searchUserMyhairstyleTimes(int mystyleID, String servicecode) throws BaseException{

		return userMyhairstyleTimesDao.searchUserMyhairstyleTimes(mystyleID, servicecode);
	}

	@Override
	public List<UserMyhairstyleTimesModel> searchUserMyhairstyleTimes() throws BaseException{
		
		return userMyhairstyleTimesDao.searchUserMyhairstyleTimes();
	}

	/**
	 * 根据我的发型id查询发型时长
	 * @param mystyleid
	 * @return
	 * @throws BaseException
	 */
	public List<UserMyhairstyleTimesModel> searchUserMyhairstyleTimesByMystyleid(int mystyleid) throws BaseException{
		try {
			
			return userMyhairstyleTimesDao.searchUserMyhairstyleTimesByMystyleid(mystyleid);
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
