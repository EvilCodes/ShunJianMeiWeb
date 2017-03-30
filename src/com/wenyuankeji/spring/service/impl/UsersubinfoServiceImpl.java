package com.wenyuankeji.spring.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class UsersubinfoServiceImpl implements IUsersubinfoService{

	@Autowired
	private IUsersubinfoDao usersubinfoDao;
	@Autowired
	private IUserinfoDao userinfoDao;
	
	@Autowired
	private IUtilService utilService;
	
	@Override
	public boolean updateUserSubInfoByUserId(UsersubinfoModel userSubInfo) throws BaseException{
		Boolean k=usersubinfoDao.updateUserSubInfoByUserId(userSubInfo);
		return k;
	}
	
	// add by jiazhaohui
	@Override
	public boolean updateMasterDefautSetting(int userid, String workDay, String workDayHour, 
			String StoreId, String WorkShop, String Address) 
			throws BaseException
	{
		try
		{
			return usersubinfoDao.updateMasterDefaultSetting(userid, workDay, 
					workDayHour, StoreId, WorkShop, Address);
		}
		catch (BaseException e)
		{
			utilService.addBaseException(
					"updateMasterDefautSetting","",e.getMessage());
		}
		return false;
	}

	
	@Override
	public int addUserSubInfo(UsersubinfoModel userSubInfo) {
		return usersubinfoDao.addUserSubInfo(userSubInfo);
	}
	@Override
	public int addUserSubInfoForInsert(UsersubinfoModel o) {
		
		String sql = "insert into usersubinfo (userid,truename,email,address,household,contact,contactmobile,relationship,idcard,createtime,levelid,score,ordernum,starid,workinglife,checkstate) "
				+ "VALUES('"+o.getUserid()+"','"+o.getTruename()+"','"+o.getEmail()+"','"+o.getAddress()+"','"+o.getHousehold()+"','"+o.getContact()+"','"+o.getContactmobile()+"','"+o.getRelationship()+"','"+o.getIdcard()+"',SYSDATE(),1,0,0,1,0,1)";
		
		return usersubinfoDao.addUserSubInfo(sql);
	}
	
	@Override
	public UsersubinfoModel searchUserSubInfoByUserId(int userId) {
		return usersubinfoDao.searchUserSubInfoByUserId(userId);
	}

	/***********UP  mupengyuan*****************/
	
	
	@Override
	public UsersubinfoModel searchUsersubinfo(int userid) throws BaseException {
		
		return usersubinfoDao.searchUsersubinfo(userid);
	}
	@Override
	public List<UsersubinfoModel> searchUsersubinfoList(int Days, int AreaID,int CityID,
			int Sort, int PageSize, int PageIndex,String Infversion) throws BaseException {
		
		//更具美发师工作日和商圈，获得美发师id
		Set<String> useridList = null;
		String futureDay = "";
		if (AreaID != 0 || Days != 0) {
			if (Infversion != null && "1.0".equals(Infversion)) {
				//未来工作日
				futureDay = ShunJianMeiUtil.getFutureDay(Days);
			}else if (Infversion != null && "2.0".equals(Infversion)) {
				//未来工作日
				futureDay = ShunJianMeiUtil.getFutureDay2(Days);
			}
			
			useridList = usersubinfoDao.searchUsersubinfoIdList(AreaID, futureDay);
			
			if (useridList.size() == 0) {
				useridList.add("0");
			}
		}
		
		//根据美发师id查询美发师信息
		return usersubinfoDao.searchUsersubinfoList(Sort, CityID, PageSize, PageIndex, useridList,Infversion);
	}

	/**
	 * 美发师信息验证
	 * @param UsersubinfoModel
	 * @return
	 * @throws BaseException
	 */
	public boolean updateStoreinfo(UsersubinfoModel usersubinfo) throws BaseException{
		return usersubinfoDao.updateStoreinfo(usersubinfo);
	}

	
	/************管理端************/
	@Override
	public List<UserinfoModel> searchUserinfo(String cityid, String userid,
			String tel, String startTime, String endTime, String checkstate,
			int page, int rows) throws BaseException {
		return userinfoDao.searchUserinfo(cityid, userid, tel, startTime, endTime, checkstate, page, rows);
	}

	@Override
	public int searchUserinfoCount(String cityid, String userid, String tel,
			String startTime, String endTime, String checkstate)
			throws BaseException {
		return userinfoDao.searchUserinfoCount(cityid, userid, tel, startTime, endTime, checkstate);
	}

	@Override
	public List<UsersubinfoModel> searchSubUserinfoByStoreId(int storeId)
			throws BaseException {
		return this.usersubinfoDao.searchSubUserinfoByStoreId(storeId);
	}


	
	
}
