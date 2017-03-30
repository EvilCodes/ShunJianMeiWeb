package com.wenyuankeji.spring.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IStoreWorkdateDao;
import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.dao.IUserWorkplaceDao;
import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.model.StoreWorkhourModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserWorkplaceModel;
import com.wenyuankeji.spring.service.IStoreinfoService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;

@Service
public class StoreinfoServiceImpl implements IStoreinfoService {

	@Autowired
	private IStoreinfoDao storeinfoDao;

	@Autowired
	private IUserWorkhourDao userWorkhourDao;

	@Autowired
	private IUserWorkplaceDao userWorkplaceDao;

	@Autowired
	private IStoreWorkdateDao storeWorkdateDao;

	@Override
	public List<StoreinfoModel> searchStoreinfo(String userid, String Score,
			String orderquantity, double Longitude, double Latitude,
			String Car, int PageSize, int PageIndex,String Type,String Infversion) throws BaseException {

		return storeinfoDao.searchStoreinfo(userid, Score, orderquantity,
				Longitude, Latitude, Car, PageSize, PageIndex, Type,Infversion);
	}

	@Override
	public StoreinfoModel searchStoreinfo(int storeid) throws BaseException {

		return storeinfoDao.searchStoreinfo(storeid);
	}

	@Override
	public List<StoreinfoModel> searchStoreinfoList(int whid, int times)
			throws BaseException {

		// 工作年月日
		UserWorkhourModel userWorkhour = userWorkhourDao
				.searchUserWorkhour(whid);

		if (null == userWorkhour) {
			return null;
		}
		
		
		Map<String, Integer> userWorkdataInfoMap = new HashMap<String, Integer>();
		// 工作日
		userWorkdataInfoMap.put("workDataYear", userWorkhour.getUserWorkdate()
				.getYear());
		userWorkdataInfoMap.put("workDataMonth", userWorkhour.getUserWorkdate()
				.getMonth());
		userWorkdataInfoMap.put("workDataDay", userWorkhour.getUserWorkdate()
				.getDay());

		// 美发师工作日id
		int wdid = userWorkhour.getWdid();

		// 根据美发师工作日id找到工作地点
		List<UserWorkplaceModel> userWorkplaces = userWorkplaceDao
				.searchUserWorkplace(wdid);

		if (null == userWorkplaces || userWorkplaces.size() == 0) {
			return null;
		}
		// 记录店铺信息
		Map<Integer, StoreinfoModel> storeMap = new HashMap<Integer, StoreinfoModel>();

/*		StringBuffer storeids = new StringBuffer();

		for (UserWorkplaceModel o : userWorkplaces) {
			storeids.append("'").append(o.getStoreid()).append("',");
			storeMap.put(o.getStoreid(), o.getStoreinfo());
		}
		// 生成in条件
		String storeidsStr = storeids.substring(0, storeids.length() - 1);

		// 更具店铺id查询店铺的工作日
		List<StoreWorkdateModel> storeWorkdateList = storeWorkdateDao
				.searchStoreWorkdateList(storeidsStr, userWorkdataInfoMap);

		if (null == storeWorkdateList || storeWorkdateList.size() == 0) {

			return null;

		} else {*/

			List<StoreinfoModel> storeinfoList = new ArrayList<StoreinfoModel>();

			// 获取总的时长(小时)
			int time = times / 60;

			if (times % 60 > 0) {
				time = time + 1;
			}
			// 开始时间
			int startHour = userWorkhour.getHour();
			// 结束时间
			int endhour = startHour + (time - 1);

			// 判断店铺工位信息
			/*for (StoreWorkdateModel o : storeWorkdateList) {

				List<Integer> chairList = storeWorkdateDao.searchStoreHourInfo(
						o.getWdid(), startHour, endhour);

				if (null != chairList && chairList.size() > 0) {
					for (int i = 0; i < chairList.size(); i++) {

						Object obj = chairList.get(i);
						int chair = Integer.parseInt(obj.toString());

						if (chair == time) {
							storeinfoList.add(storeMap.get(o.getStoreid()));
							break;
						}
					}
				}
			}*/
			
			//当前首个工时
			int hour = userWorkhour.getHour();
			UserWorkhourModel userWorkhourModel = userWorkhourDao.searchUserWorkhour(0, wdid, hour);
			if(userWorkhourModel.getState() != 0){
				return null;
			}
			for(int i = 1; i < time; i++){		
				//下一个工时
				UserWorkhourModel nextUserWorkhourModel = userWorkhourDao.searchUserWorkhour(0, wdid, hour + i);
				
				if(nextUserWorkhourModel != null){
					//状态不可约
					if(nextUserWorkhourModel.getState() != 0){
						return null;
					}
					//时长超过1小时时，21点不可约
					/*int nextHour = nextUserWorkhourModel.getHour();
					if(nextHour >= 21){
						return null;
					}*/
				}
			}
			
			//判断当前工时对应的工作地点
			List<UserWorkplaceModel> userWorkplace = userWorkplaceDao.searchUserWorkplace(wdid);
			
			if(userWorkplace == null){
				return null;
			}else{
				//返回店铺集合
				for (UserWorkplaceModel o : userWorkplace){
					StoreinfoModel store = storeinfoDao.searchStoreinfo(o.getStoreid());
					storeinfoList.add(store);
				}
			}
		
			return storeinfoList;

		//}

	}

	@Override
	public boolean updateStoreinfo(StoreinfoModel storeinfo)
			throws BaseException {

		return storeinfoDao.updateStoreinfo(storeinfo);
	}

	@Override
	public List<StoreinfoModel> searchStoreinfoByAreaid(String areaid, int state)
			throws BaseException {
		//List<Integer> storeIdList = storeWorkdateDao.searchStoreWorkdateInfo();
		String storeIdStr = "";
		
		/*if(storeIdList != null && storeIdList.size() > 0){
			for(int storeId : storeIdList){
				storeIdStr += storeId + ",";
			}
			storeIdStr = storeIdStr.substring(0, storeIdStr.length() - 1);
		}*/
		
		return storeinfoDao.searchStoreinfoByAreaid(areaid, state, storeIdStr);
	}

	@Override
	public Map<String, Object> addStoreWorkdate(int storeId, int commitType,
			int chairNum, String wsdData) throws BaseException {

		Map<String, Object> ouMap = new HashMap<String, Object>();

		try {

			Calendar cal = Calendar.getInstance();// 使用日历类
			int year = cal.get(Calendar.YEAR);// 得到年
			int thismonth = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
			int thisday = cal.get(Calendar.DAY_OF_MONTH);// 得到天

			String[] wsdDatas = wsdData.split("-");
			int month = Integer.parseInt(wsdDatas[0]);
			int day = Integer.parseInt(wsdDatas[1]);

//			if ((year + month + day) <= (year + thismonth + thisday)) {
//				year++;
//			}
			
			if (month <= thismonth && day <= thisday) {
				year++;
			}

			wsdData = year + "-" + wsdData;

			String[] gongzuotis = wsdData.split("-");

			if (commitType == 1) {
				Date createtime = new Date();
				// 添加工作日
				// for (String gongzuori : gongzuotiList) {
				// 添加店铺工作日
				// StoreWorkdateModel wsd = new StoreWorkdateModel();
				int wdid = 0;
				// 查询美发店工作日是否已存在
				StoreWorkdateModel wsd = storeinfoDao.searchStoreWorkdate(
						gongzuotis[0], gongzuotis[1], gongzuotis[2], storeId);
				if (wsd != null) {
					wdid = wsd.getWdid();
				} else {
					// 没有的场合，添加新的工作日
					wsd = new StoreWorkdateModel();
					wsd.setStoreid(storeId);
					wsd.setYear(Integer.parseInt(gongzuotis[0]));
					wsd.setMonth(Integer.parseInt(gongzuotis[1]));
					wsd.setDay(Integer.parseInt(gongzuotis[2]));
					wsd.setCreatetime(createtime);
					// 执行添加
					wdid = storeinfoDao.addStoreWorkdate(wsd);
				}

				// 判断工作日下是否有工位，没有工位的时候则添加
				if (storeinfoDao.searchStoreWorkhour(wdid) == null) {
					// 添加工位信息
					for (int i = 1; i <= chairNum; i++) {
						for (int j = 10; j <= 21; j++) {

							StoreWorkhourModel wdh = storeinfoDao
									.searchStoreWorkhour(wdid, i, j);

							if (null == wdh) {
								wdh = new StoreWorkhourModel();
								wdh.setWdid(wdid);
								wdh.setChairid(i);
								wdh.setHour(j);
								wdh.setState(0);
								wdh.setOrderid(0);
								wdh.setCreatetime(createtime);

								// 执行添加
								storeinfoDao.addStoreWorkhour(wdh);
							}
						}
					}

					ouMap.put("info", true);
					ouMap.put("msg", "全部预约成功");
					ouMap.put("wdid", wsd.getWdid());

				} else {
					ouMap.put("info", false);
					ouMap.put("msg", wsdData + "已存在预约无法全部预约");
				}

				// }

			}
			if (commitType == 2) {

				// List<String> wdids = new ArrayList<String>();
				// int wdidCount = 0;
				// StringBuffer wdids = new StringBuffer();
				// 删除工作日
				// for (String gongzuori : gongzuotiList) {
				// 查询工作日id
				// String[] gongzuotis = gongzuori.split("-");
				// 查询店铺工作日信息
				StoreWorkdateModel swd = storeinfoDao.searchStoreWorkdate(
						gongzuotis[0], gongzuotis[1], gongzuotis[2], storeId);

				if (null != swd) {
					// 删除工位工时
					// for (int i = 1; i <= chairNum; i++) {
					// // 执行删除
					// storeinfoDao.deleteStoreWorkhour(swd.getWdid(), i);
					// }

					// 根据更具日id，判断工位是否被占中
					List<StoreWorkhourModel> storeWorkhour = storeinfoDao
							.searchStoreWorkhourByState(swd.getWdid());
					if (null == storeWorkhour || storeWorkhour.size() == 0) {

						// if (wdids.length() != 0) {
						// wdids.append(",");
						// }
						// wdids.append("'").append(swd.getWdid()).append("'");
						// wdidCount++;

						// 删除工位
						if (storeinfoDao.deleteStoreWorkhourByWdids("'"
								+ swd.getWdid() + "'")) {
							// 删除店铺工作日
							if (storeinfoDao.deleteStoreWorkdate(swd)) {
								ouMap.put("info", true);
								ouMap.put("msg", "预约取消成功");
							} else {
								ouMap.put("info", false);
								ouMap.put("msg", "预约取消失败");
							}
						} else {
							ouMap.put("info", false);
							ouMap.put("msg", "预约取消失败");
						}

					} else {
						ouMap.put("info", false);
						ouMap.put("msg", wsdData + "已存在预约无法取消");
					}

					// 根据工作日id查询查询工位信息，如果有工位信息的场合，则不能删除工作日
					// if (storeinfoDao.searchStoreWorkhour(swd.getWdid()) ==
					// null) {
					// storeinfoDao.deleteStoreWorkdate(swd);
					// }

				}

				// }

				// if (wdidCount == gongzuotiList.size()) {
				//
				// //删除工位
				// if
				// (storeinfoDao.deleteStoreWorkhourByWdids(wdids.toString())) {
				// //删除店铺工作日
				// if
				// (storeinfoDao.deleteStoreWorkDateByWdids(wdids.toString())) {
				// ouMap.put("info", true);
				// ouMap.put("msg", "预约取消成功");
				// }
				// }
				//
				// }else {
				// ouMap.put("info", false);
				// ouMap.put("msg", "已存在预约无法取消");
				// }

			}

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return ouMap;
	}

	@Override
	public StoreinfoModel searchStoreinfoLogin(String username, String password)
			throws BaseException {

		return storeinfoDao.searchStoreinfoLogin(username, password);
	}

	@Override
	public List<StoreWorkhourModel> searchChairidCount(String wsdData,
			String storeId) throws BaseException {

		return null;
	}

	@Override
	public StoreWorkdateModel searchStoreWorkdate(String wsdData, String storeId)
			throws BaseException {
		try {

			Calendar cal = Calendar.getInstance();// 使用日历类
			int year = cal.get(Calendar.YEAR);// 得到年
			int thismonth = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
			int thisday = cal.get(Calendar.DAY_OF_MONTH);// 得到天

			String[] wsdDatas = wsdData.split("-");
			int month = Integer.parseInt(wsdDatas[0]);
			int day = Integer.parseInt(wsdDatas[1]);

//			if ((year + month + day) <= (year + thismonth + thisday)) {
//				year++;
//			}

			if (month <= thismonth && day <= thisday) {
				year++;
			}
			
			// 查询工作日
			return storeinfoDao.searchStoreWorkdate(String.valueOf(year),
					wsdDatas[0], wsdDatas[1], Integer.parseInt(storeId));

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<Object[]> searchGongWeiByGongZuoRi(String wsdData,
			String storeId) throws BaseException {

		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int thismonth = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int thisday = cal.get(Calendar.DAY_OF_MONTH);// 得到天

		String[] wsdDatas = wsdData.split("-");
		int month = Integer.parseInt(wsdDatas[0]);
		int day = Integer.parseInt(wsdDatas[1]);

//		if ((year + month + day) <= (year + thismonth + thisday)) {
//			year++;
//		}
		
		if (month <= thismonth && day <= thisday) {
			year++;
		}
		
		// 查询工位
		return storeinfoDao.searchGongWeiByGongZuoRi(String.valueOf(year),
				wsdDatas[0], wsdDatas[1], Integer.parseInt(storeId));

	}

	@Override
	public boolean updateStoreWorkhour(int wdid, int type, int chairNum,
			int hour) throws BaseException {

		if (1 == type) {
			int count = 0;
			// 先删除在添加
			if (storeinfoDao.deleteStoreWorkhourByHour(wdid, hour)) {
				// 执行添加
				Date createtime = new Date();
				for (int i = 1; i <= chairNum; i++) {

					StoreWorkhourModel wdh = new StoreWorkhourModel();

					wdh.setWdid(wdid);
					wdh.setChairid(i);
					wdh.setHour(hour);
					wdh.setState(0);
					wdh.setOrderid(0);
					wdh.setCreatetime(createtime);

					// 执行添加
					if (storeinfoDao.addStoreWorkhour(wdh)) {
						count++;
					}
				}

				if (count == chairNum) {
					return true;
				}

			}

		} else {
			// 只删除
			return storeinfoDao.deleteStoreWorkhourByHour(wdid, hour);
		}

		return false;
	}

	@Override
	public String searchMasterList(String storeid) throws BaseException {
		
		return storeinfoDao.searchMasterList(storeid);
	}

	@Override
	public boolean compareStoreAndUserTime(int hour, int wdid)
			throws BaseException {
			List<UserWorkplaceModel> userWorkplaceModelList = userWorkplaceDao.searchUserWorkplace(wdid);
			for (UserWorkplaceModel userWorkplaceModel : userWorkplaceModelList) {
				 int storeID = userWorkplaceModel.getStoreid();
				StoreinfoModel storeinfoModel = storeinfoDao.searchStoreinfo(storeID);
				String startEndTime = storeinfoModel.getBusinesshours();
				//美发店开始时间：时
				String startHourString = startEndTime.substring(0, 2);
				int startHour = Integer.parseInt(startHourString);
				//美发店开始时间：分
				String startMinString = startEndTime.substring(3, 5);
				int startMin = Integer.parseInt(startMinString);
				//美发店结束时间：时
				String endHourString = startEndTime.substring(6, 8);
				int endHour = Integer.parseInt(endHourString);
				//美发店结束时间：分
				String endMinString = startEndTime.substring(9, 11);
				int endMin = Integer.parseInt(endMinString);
				//判断美发师时间是否在美发店时间之外
				if((hour<startHour||hour>endHour)){
					return true;
				}else if(hour==startHour){
					if(startMin>0){
						return true;
					}else {
						return false;
					}
				}else if (hour==endHour) {
					if(endMin>=0){
						return false;
					}else {
						return true;
					}
				}
			}
			
			
		
		return false;
	}
}
