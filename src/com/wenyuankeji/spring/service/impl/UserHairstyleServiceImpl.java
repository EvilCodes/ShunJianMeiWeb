package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserHairstyleDao;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.service.IUserHairstyleService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class UserHairstyleServiceImpl implements IUserHairstyleService{

	@Autowired
	private IUserHairstyleDao userHairstyleDao;

	@Override
	public List<UserHairstyleModel> searchUserHairstyle() throws BaseException{
		
		return userHairstyleDao.searchUserHairstyle();
	}

	@Override
	public UserHairstyleModel searchUserHairstyleById(int id)
			throws BaseException {
		
		return userHairstyleDao.searchUserHairstyleById(id);
	}
	
	@Override
	public List<UserHairstyleModel> searchUserHairstyleByUserID(String userID) throws BaseException{
		
		try {
			//查询发型项目id
//			List<UserMyhairstyleModel> userMyhairstyles = userHairstyleDao.searchUserMyhairstyleByUserID(userID);
//			if (null != userMyhairstyles && userMyhairstyles.size() > 0) {
//				StringBuffer styleidStringBuffer = new StringBuffer();
//				for (UserMyhairstyleModel userMyhairstyle : userMyhairstyles) {
//					
//					if (styleidStringBuffer.length() > 0) {
//						styleidStringBuffer.append(",");
//					}
//					
//					styleidStringBuffer.append("'"+userMyhairstyle.getStyleid()+"'");
//				}
				
				
				//查询发型项目
				return userHairstyleDao.searchUserHairstyle();
				
//			}
			
//			return null;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
}
