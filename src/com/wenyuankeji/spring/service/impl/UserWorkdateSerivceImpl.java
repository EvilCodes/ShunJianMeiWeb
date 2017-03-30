package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserWorkdateDao;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.service.IUserWorkdateService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserWorkdateSerivceImpl implements IUserWorkdateService {

	@Autowired
	private IUserWorkdateDao serWorkdateDao;

	@Override
	public UserWorkdateModel searchUserWorkdate(int wdid)
			throws BaseException {
		
		return serWorkdateDao.searchUserWorkdate(wdid);
	}

	@Override
	public List<UserWorkdateModel> searchUserWorkdateByUserID(int userID)
			throws BaseException {
		return serWorkdateDao.searchUserWorkdateByUserID(userID);
	}

	@Override
	public boolean searchUserWorkdateByUserId(int userID, String year, String month,
			String day) throws BaseException {
		return serWorkdateDao.searchUserWorkdateByUserId(userID, year, month, day);
	}

	@Override
	public List<UserWorkdateModel> searchUserWorkdateByUserIDAndNum(int ID,
			int num) throws BaseException{
		return serWorkdateDao.searchUserWorkdateByUserIDAndNum(ID,num);
	
	}
}
