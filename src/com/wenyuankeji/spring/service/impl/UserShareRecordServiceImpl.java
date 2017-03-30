package com.wenyuankeji.spring.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wenyuankeji.spring.dao.IUserShareRecordDao;
import com.wenyuankeji.spring.model.UserShareRecordModel;
import com.wenyuankeji.spring.service.IUserShareRecordService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserShareRecordServiceImpl implements IUserShareRecordService{

	@Autowired
	private IUserShareRecordDao userShareRecordDao;

	@Override
	public UserShareRecordModel searchUserShareRecord(int evaid) throws BaseException {
		return userShareRecordDao.searchUserShareRecord(evaid);
	}

	@Override
	public boolean addUserShareRecord(String userid,String evaid,String picID) throws BaseException {
		try {
			Date createTime = new Date();
			UserShareRecordModel userShareRecordModel = new UserShareRecordModel();
			if (!"".equals(userid)) {
				userShareRecordModel.setUserid(Integer.parseInt(userid));
			}else{
				userShareRecordModel.setUserid(0);
			}
			userShareRecordModel.setEvaid(Integer.parseInt(evaid));
			userShareRecordModel.setCreatetime(createTime);
			userShareRecordModel.setPicid(picID);
			//执行添加
			userShareRecordDao.addUserShareRecord(userShareRecordModel);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	
}
