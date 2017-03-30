package com.wenyuankeji.spring.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wenyuankeji.spring.dao.IUserWorkdateDao;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.dao.IUserWorkplaceDao;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkplaceModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.service.IUsersubinfoService;
import com.wenyuankeji.spring.service.IUserWorkplaceService;
import com.wenyuankeji.spring.service.IUtilService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class UserWorkplaceSerivceImpl implements IUserWorkplaceService {

	@Autowired
	private IUserWorkplaceDao userWorkplaceDao;
	
	@Autowired
	private IUserWorkdateDao userWorkdateDao;
	
	@Autowired
	private IUserWorkhourDao userWorkhourDao;
	
	@Autowired
	private IUtilService utilService;
	
	// add by jiazhaohui
	@Autowired
	private IUsersubinfoService userSubinfoService; 
	
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
	public List<UserWorkplaceModel> searchUserWorkplace(int wdid)
			throws BaseException {
		return userWorkplaceDao.searchUserWorkplace(wdid);
	}

	@Override
	public List<UserWorkplaceModel> searchUserWorkplaces(int storeid)
			throws BaseException {
		return userWorkplaceDao.searchUserWorkplaces(storeid);
	}

	@Override
	public List<Map<String, Object>> addHairdresserSchedule(String userID,
			String wdDate) throws BaseException {

		List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
		try {
			UserWorkdateModel workDate = new UserWorkdateModel();
			// 查询美发师工作日
			String[] dates = wdDate.split("-");
			UserWorkdateModel userWorkdate = userWorkdateDao.searchUserWorkdate(
					Integer.parseInt(userID), Integer.parseInt(dates[0]),
					Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
	
			// 工作日ID
			int wdid = 0;
	
			if (userWorkdate == null) {
				workDate.setUserid(Integer.parseInt(userID));
				workDate.setYear(Integer.parseInt(dates[0]));
				workDate.setMonth(Integer.parseInt(dates[1]));
				workDate.setDay(Integer.parseInt(dates[2]));
				workDate.setCreatetime(new Date());
				// 添加工作日
				int resultId = userWorkdateDao.addUserWorkdate(workDate);
				if (resultId > 0) {
	
					// 循环添加工作工时，10点到21点。
					for (int i = Constant.SERVICE_START_TIME; i < Constant.SERVICE_END_TIME + 1; i++) {
						UserWorkhourModel o = new UserWorkhourModel();
						o.setWdid(resultId);
						o.setHour(i);
						o.setState(-1);
						// 添加工作工时
						userWorkhourDao.addUserWorkhour(o);
					}
				}
				wdid = workDate.getWdid();
			} else {
				wdid = userWorkdate.getWdid();
			}
	
			// 查询美发师工作地
			List<UserWorkplaceModel> wdpList = userWorkplaceDao
					.searchUserWorkplace(wdid);
	
			// 查询美发师工作工时
			List<UserWorkhourModel> wdhList = userWorkhourDao
					.searchUserWorkhourByWdid(wdid);
	
			if (null == wdpList) {
				return null;
			}
	
			// 做成返回内容
			for (UserWorkhourModel wdh : wdhList) {
				Map<String, Object> outMap = new HashMap<String, Object>();
				outMap.put("Hour", ShunJianMeiUtil.intToString(wdh.getHour()));
				Calendar c = Calendar.getInstance();
				int hourNow = c.get(Calendar.HOUR_OF_DAY); 
				int dateNow = c.get(Calendar.DATE); 
				int yearNow = c.get(Calendar.YEAR); 
				int monthNow = c.get(Calendar.MONTH)+1; 
				if(userWorkdate==null&&workDate!=null){
					//判断时间是否已过
					 if (monthNow == workDate.getMonth()&&yearNow == workDate.getYear()
								&&dateNow == workDate.getDay()&&hourNow >= wdh.getHour()) {
						 outMap
							.put("State", ShunJianMeiUtil
									.intToString(Constant.HOUR_OUTOFTIME_STATE));
					}else {
						outMap.put("State", ShunJianMeiUtil.intToString(wdh.getState()));
					}
					
				}else {
					//判断时间是否已过
					 if (monthNow == userWorkdate.getMonth()&&yearNow == userWorkdate.getYear()
								&&dateNow == userWorkdate.getDay()&&hourNow >= wdh.getHour()) {
						 outMap
							.put("State", ShunJianMeiUtil
									.intToString(Constant.HOUR_OUTOFTIME_STATE));
					}else {
						outMap.put("State", ShunJianMeiUtil.intToString(wdh.getState()));
					}
				}
				List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();
	
				for (UserWorkplaceModel wdp : wdpList) {
					Map<String, String> itmeMap = new HashMap<String, String>();
					itmeMap.put("ID", ShunJianMeiUtil.intToString(wdp
							.getStoreinfo().getStoreid()));
					itmeMap.put("Name", wdp.getStoreinfo().getName());
					itmeMap.put("Address", wdp.getStoreinfo().getAddress());
	
					itemList.add(itmeMap);
					outMap.put("Item", itemList);
				}
	
				outList.add(outMap);
			}

		}catch (Exception e) {
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			utilService.addBaseException(
					"addHairdresserSchedule","",e.getMessage());
		}
		return outList;
	}

	@Override
	public boolean updateStoreWorkhour(int orderid, int state)
			throws BaseException {
		return userWorkhourDao.updateUserWorkhour(orderid, state);
	}

	@Override
	public boolean addHairdresserSchedule(String userID, String wdDate,
			String addressID, List<Map<String, Object>> itemList)
			throws BaseException {

		try {
			String[] dates = wdDate.split("-");

			// 查询美发法师工作日是否已存在
			UserWorkdateModel userWorkdate = userWorkdateDao
					.searchUserWorkdate(Integer.parseInt(userID),
							Integer.parseInt(dates[0]),
							Integer.parseInt(dates[1]),
							Integer.parseInt(dates[2]));
			// 工时ID
			int wdid = userWorkdate.getWdid();

			String[] addressIDs = addressID.split("[|]");

			// 工时item补位空的场合，判断工时是否被预约
			if (itemList.size() > 0) {

				// 初始化数据
				// 先判断工时表里有没有数据。如果没有添加 10-21点数据 state= -1.
				List<UserWorkhourModel> userWorkhour = userWorkhourDao.searchUserWorkhourByWdid(wdid);
				if (userWorkhour == null || userWorkhour.size() == 0) {
					for (int i = Constant.SERVICE_START_TIME; i < Constant.SERVICE_END_TIME + 1; i++) {
						UserWorkhourModel workhourModel = new UserWorkhourModel();
						workhourModel.setWdid(wdid);
						workhourModel.setHour(i);
						workhourModel.setState(-1);
					}
				}

				// 根据itemList修改对应工时表状态
				for (int i = 0; i < itemList.size(); i++) {
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap = itemList.get(i);
					UserWorkhourModel workhourModel = new UserWorkhourModel();
					workhourModel.setWdid(wdid);
					workhourModel.setHour(Integer.parseInt(resultMap.get("Hour").toString()));
					workhourModel.setState(Integer.parseInt(resultMap.get("State").toString()));
					if (!userWorkhourDao.updateUserWorkhourState(workhourModel)) {
						return false;
					}
				}
			}
			if (addressIDs.length > 0) {
				// 删除现有工作地点
				userWorkplaceDao.deleteUserWorkplaceByWdid(wdid);
				
				//添加工作地点之前判断是不是全天休息，全天休息的话，不添加工作地点
				if (userWorkhourDao.getUserWorkHourState(wdid)) {
					return true;
				}else{
					// 添加新的工作地点
					for (String storeid : addressIDs) {
						UserWorkplaceModel uwp = new UserWorkplaceModel();
						uwp.setWdid(wdid);
						uwp.setStoreid(Integer.parseInt(storeid));
						// 执行添加工作地
						if (!userWorkplaceDao.addUserWorkplace(uwp)) {
							return false;
						}
					}
				}
			}
		} catch (Exception e) {
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			utilService.addBaseException(
					"addHairdresserSchedule","",e.getMessage());
		}
		return true;
	}

	@Override
	// add by jiazhaohui
	/**
	 * 一键设置所有美发师的工作日程
	 * @param userID美发师id
	 * @return
	 * @throws BaseException
	 */
	public boolean updateAllTimeWorking(String userID) throws BaseException
	{
		try
		{
			int userid = 0;
			userid = Integer.parseInt(userID);
			UsersubinfoModel userSubInfo = userSubinfoService.searchUsersubinfo(userid);
			if (userSubInfo != null)
			{
				String workDay = userSubInfo.getWorkday();
				String dayWorkHour = userSubInfo.getDayworkhour();
				String storeId = userSubInfo.getStoreid();
				if (workDay != null && dayWorkHour != null && storeId != null)
				{
					int storeid = Integer.parseInt(storeId);
					String[] workDays = workDay.split("\\|");
					String[] dayWorkHours = dayWorkHour.split("\\|");
					
					// 从今天开始的14天，开始全部设置
					for (int i = 0; i <= Constant.MAX_MASTER_NEXT_WORKING_DAYS; i++)
					{
						Calendar c = Calendar.getInstance();
						c.add(Calendar.DAY_OF_MONTH, i);
						int year = c.get(Calendar.YEAR);
						int month = c.get(Calendar.MONTH) + 1;
						int day = c.get(Calendar.DATE);
						// 得到星期，本地化方式判定：一周中哪一天是第一天
						boolean isFirstSunday = (c.getFirstDayOfWeek() == Calendar.SUNDAY);
						int weekDay = c.get(Calendar.DAY_OF_WEEK);
						if(isFirstSunday)
						{
						  weekDay = weekDay - 1;
						  if(weekDay == 0)
						  {
						    weekDay = 7;
						  }
						}
						int wdid = 0;
						
						// 查询美发师是否设置了这个日期
						UserWorkdateModel userWorkdate = userWorkdateDao.searchUserWorkdate(
								userid, year, month, day);
						if (userWorkdate == null)
						{
							// 还没有设置这个日期，则设置这个日期
							userWorkdate = new UserWorkdateModel();
							userWorkdate.setUserid(userid);
							userWorkdate.setYear(year);
							userWorkdate.setMonth(month);
							userWorkdate.setDay(day);
							userWorkdate.setCreatetime(new Date());
							// 添加工作日
							int resultId = userWorkdateDao.addUserWorkdate(userWorkdate);
							wdid = resultId;
							if (resultId > 0) {
				
								// 循环添加工作工时，10点到21点。
								for (int j = Constant.SERVICE_START_TIME; j < Constant.SERVICE_END_TIME + 1; j++)
								{
									UserWorkhourModel o = new UserWorkhourModel();
									o.setWdid(resultId);
									o.setHour(j);
									o.setState(-1);
									// 添加工作工时
									userWorkhourDao.addUserWorkhour(o);
								}
							}
						}
						else
						{
							wdid = userWorkdate.getWdid();
						}
						// 美发师工作日期设置完成之后，开始设置每一个工时
						for (int j = Constant.SERVICE_START_TIME; j < Constant.SERVICE_END_TIME + 1; j++)
						{
							UserWorkhourModel workhourModel = 
									userWorkhourDao.searchUserWorkhour(0, wdid, j);
							if (workhourModel.getState() == Constant.HOUR_LOCK_STATE || 
									workhourModel.getState() == Constant.HOUR_ORDER_STATE)
							{
								continue;
							}
							
							int state = getHourWorkState(weekDay, j, workDays, dayWorkHours);
							workhourModel.setState(state);
							if (!userWorkhourDao.updateUserWorkhourState(workhourModel)) {
								continue;
							}
						}
						// 添加当天的工作地点
						// 先删除当前所有的工作地点
						userWorkplaceDao.deleteUserWorkplaceByWdid(wdid);
						int dayState = getDayWorkState(weekDay, workDays);
						if (dayState != Constant.HOUR_REST_STATE)
						{
							// 当天不休息
							UserWorkplaceModel uwp = new UserWorkplaceModel();
							uwp.setWdid(wdid);
							uwp.setStoreid(storeid);
							// 执行添加工作地
							userWorkplaceDao.addUserWorkplace(uwp);
						}
					}
				}
			}
			return true;
		}
		 catch (Exception e) 
		 {
			//事务回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			utilService.addBaseException(
					"updateAllTimeWorking","",e.getMessage());
		}
		return false;
	}
	
	// add by jiazhaohui
	// 判定当前小时是否休息
	private int getHourWorkState(int weekDay, int nowHour, 
			String[] workDays, String[] dayWorkHours)
	{
		for (int i = 0; i < workDays.length; i++)
		{
			if (Integer.parseInt(workDays[i]) == weekDay)
			{
				for (int j = 0; j < dayWorkHours.length; j++)
				{
					if (Integer.parseInt(dayWorkHours[j]) == nowHour)
					{
						return Constant.HOUR_WORK_STATE; 
					}
				}
			}
		}
		return Constant.HOUR_REST_STATE;
	}
	
	// 判定当日是否休息
	private int getDayWorkState(int weekDay, String[] workDays)
	{
		for (int i = 0; i < workDays.length; i++)
		{
			if (Integer.parseInt(workDays[i]) == weekDay)
			{
				return Constant.HOUR_WORK_STATE;
			}
		}
		return Constant.HOUR_REST_STATE;
	}
}
