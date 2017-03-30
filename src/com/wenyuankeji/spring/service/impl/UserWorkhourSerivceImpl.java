package com.wenyuankeji.spring.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserWorkplaceModel;
import com.wenyuankeji.spring.service.IReservationService;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.service.IUserWorkdateService;
import com.wenyuankeji.spring.service.IUserWorkhourService;
import com.wenyuankeji.spring.service.IUserWorkplaceService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class UserWorkhourSerivceImpl implements IUserWorkhourService {

	@Autowired
	private IUserWorkdateService userWorkdateService;
	
	@Autowired
	private IReservationService reservationService;
	
	@Autowired
	private IStoreinfoService storeinfoService;
	
	@Autowired
	private IUserWorkplaceService userWorkplaceService;
	
	@Autowired
	private IUserWorkhourDao userWorkhourDao;

	@Override
	public UserWorkhourModel searchUserWorkhour(int whid) throws BaseException {
		return userWorkhourDao.searchUserWorkhour(whid);
	}
	
	@Override
	public boolean updateUserWorkhour(int orderid,int wdid,int hour,int state) throws BaseException{
		return userWorkhourDao.updateUserWorkhour(orderid, wdid, hour, state);
	}
	
	@Override
	public UserWorkhourModel searchUserWorkhour(int orderid,int wdid,int hour) throws BaseException{
		return userWorkhourDao.searchUserWorkhour(orderid, wdid, hour);
	}
	
	@Override
	public boolean updateUserWorkhour(int orderid,int state) throws BaseException{
		return userWorkhourDao.updateUserWorkhour(orderid,state);
	}

	@Override
	public List<Map<String, Object>> searchScheduleService(int ID,int num)
			throws BaseException {
		
		List<Map<String, Object>> outJsonList = new ArrayList<Map<String, Object>>();
		// UserWorkdateModel userWorkdateModel = userWorkplaceService
		// .searchUserWorkdate(ID);

		// 获取美发师工作日的列表
		List<UserWorkdateModel> userWorkdates = userWorkdateService
				.searchUserWorkdateByUserIDAndNum(ID,num);

		if (userWorkdates != null) {

			for (UserWorkdateModel userWorkdateModel : userWorkdates) {
				// 工作日的集合
				Map<String, Object> workdateMap = new HashMap<String, Object>();

				// 获取年月日
				String year = ShunJianMeiUtil
						.intToString(userWorkdateModel.getYear());
				String month = ShunJianMeiUtil
						.intToString(userWorkdateModel.getMonth());
				String day = ShunJianMeiUtil
						.intToString(userWorkdateModel.getDay());

				// 根据当前年月日返回星期几
				String dateString;
				try {
				dateString = ShunJianMeiUtil.formatDateString(
							year, month, day);
				String dayForWeek = ShunJianMeiUtil
						.dayForWeek(dateString);
				// 日期
				workdateMap.put("Date", month + "." + day);
				// 星期
				workdateMap.put("WeekDay", dayForWeek);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 获取美发师工时的列表
				int wdid = userWorkdateModel.getWdid();

				// 获取美发师工作地点的列表
				List<UserWorkplaceModel> userWorkplaceModels = userWorkplaceService
						.searchUserWorkplace(wdid);

				
				List<UserWorkhourModel> userWorkhourModels = reservationService
						.searchUserWorkhour(wdid);

				List<Map<String, Object>> workHourList = new ArrayList<Map<String, Object>>();

				for (UserWorkhourModel obj : userWorkhourModels) {

					Map<String, Object> workhourMap = new HashMap<String, Object>();
					workhourMap.put("WHID", ShunJianMeiUtil.intToString(obj.getWhid()));
					workhourMap.put("Hour",
							ShunJianMeiUtil.intToString(obj.getHour()));
					Calendar c = Calendar.getInstance();
					int hourNow = c.get(Calendar.HOUR_OF_DAY); 
					int dateNow = c.get(Calendar.DATE); 
					int yearNow = c.get(Calendar.YEAR); 
					int monthNow = c.get(Calendar.MONTH)+1; 
					//判断时间是否已过
					 if (monthNow == userWorkdateModel.getMonth()&&yearNow == userWorkdateModel.getYear()
								&&dateNow == userWorkdateModel.getDay()&&hourNow >= obj.getHour()) {
							workhourMap
							.put("State", ShunJianMeiUtil
									.intToString(Constant.HOUR_OUTOFTIME_STATE));
					}
					//判断美发师时间是否在美发店时间之内，不在则改为休息
					 else if(storeinfoService.compareStoreAndUserTime(obj.getHour(),wdid)){
						workhourMap
						.put("State", ShunJianMeiUtil
								.intToString(Constant.HOUR_REST_STATE));
					}
					else
					{
						workhourMap
						.put("State", ShunJianMeiUtil
								.intToString(obj.getState()));
					}
					// 工作地
					List<Map<String, Object>> workplaceList = new ArrayList<Map<String, Object>>();
					if (userWorkplaceModels != null) {

						for (UserWorkplaceModel obj2 : userWorkplaceModels) {
							Map<String, Object> workplaceMap = new HashMap<String, Object>();

							// 获取店铺ID
							int storeid = obj2.getStoreid();

							StoreinfoModel storeinfoModel = storeinfoService
									.searchStoreinfo(storeid);
							workplaceMap.put("ID", ShunJianMeiUtil
									.intToString(storeid));

							if (storeinfoModel != null) {
								workplaceMap.put("Name",
										storeinfoModel.getName());
								workplaceMap.put("Address",
										storeinfoModel.getAddress());

							} else {
								workplaceMap.put("Name", "");
								workplaceMap.put("Address", "");
							}
							workplaceList.add(workplaceMap);
						}

					}
					workhourMap.put("Item", workplaceList);

					workHourList.add(workhourMap);

				}

				workdateMap.put("Item", workHourList);
				// 工作日list
				outJsonList.add(workdateMap);
			}
		
	}else {
		return outJsonList;
	}
		return outJsonList;
	}	
}
