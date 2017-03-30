package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IUserHairstyleDao;
import com.wenyuankeji.spring.dao.IUserMyhairstyleDao;
import com.wenyuankeji.spring.dao.IUserPhotoMappingDao;
import com.wenyuankeji.spring.dao.IUserProfessionLevelServiceDao;
import com.wenyuankeji.spring.dao.IUserWorkplaceDao;
import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IReservationService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class ReservationServiceImpl implements IReservationService{

	@Autowired
	private IUsersubinfoDao usersubinfoDao;
	@Autowired
	private IUserPhotoMappingDao userPhotoMappingDao;
	@Autowired
	private IUserWorkplaceDao userWorkplaceDao;
	@Autowired
	private IUserHairstyleDao userHairstyleDao;
	@Autowired
	private IUserProfessionLevelServiceDao userProfessionLevelServiceDao;
	@Autowired
	private IBaseConfigDao baseConfigDao;
	@Autowired
	private IUserMyhairstyleDao userMyhairstyleDao;
	@Autowired
	private IOrderinfoDao orderinfoDao;
	
	@Override
	public UsersubinfoModel searchUsersubinfo(int userid) throws BaseException {
		
		return usersubinfoDao.searchUsersubinfo(userid);
	}
	@Override
	public UserPhotoMappingModel searchUserPhoto(int userid, int type) throws BaseException{
		return userPhotoMappingDao.searchUserPhoto(userid, type);
	}
	@Override
	public UserWorkdateModel searchUserWorkdate(int userid)
			throws BaseException {
		return userWorkplaceDao.searchUserWorkdate(userid);
	}
	@Override
	public List<UserWorkhourModel> searchUserWorkhour(int wdid)
			throws BaseException {
		return userWorkplaceDao.searchUserWorkhour(wdid);
	}
	@Override
	public List<UserHairstyleModel> searchUserHairstyle() throws BaseException {
		return userHairstyleDao.searchUserHairstyle();
	}
	@Override
	public List<UserProfessionLevelServiceModel> searchUserProfessionLevelService(
			int levelid) throws BaseException {
		return userProfessionLevelServiceDao.searchUserProfessionLevelService(levelid);
	}
	@Override
	public List<BaseConfigModel> searchBaseConfig(String configcode) throws BaseException{
		return baseConfigDao.searchBaseConfig(configcode);
	}
	@Override
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid)
			throws BaseException {
		return userMyhairstyleDao.searchUserMyhairstyle(mystyleid);
	}
	@Override
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException {
		return orderinfoDao.searchOrderinfo(orderid);
	}
	@Override
	public List<OrderdetailModel> searchOrderdetail(int orderid)
			throws BaseException {
		return orderinfoDao.searchOrderdetail(orderid);
	}


}
