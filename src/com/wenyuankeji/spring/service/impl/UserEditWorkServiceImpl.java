package com.wenyuankeji.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wenyuankeji.spring.dao.IUserEditWorkDao;
import com.wenyuankeji.spring.dao.IUserWorkdateDao;
import com.wenyuankeji.spring.dao.IUserWorkhourDao;
import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.service.IUserEditWorkService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserEditWorkModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserinfoModel;

@Service
public class UserEditWorkServiceImpl implements IUserEditWorkService{

	@Autowired
	private IUserEditWorkDao userEditWorkDao;
	
	@Autowired
	private IUserWorkhourDao userWorkhourDao;
	
	@Autowired
	private IUserWorkdateDao userWorkdateDao;
	
	@Autowired
	private IOrderinfoDao orderinfoDao;
	
	@Autowired
	private IUserinfoDao userinfoDao;
	
	/**
	 * 根据orderid和日程id得到订单持续时长
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	private int getLastHourByWorkhour(int startHour, int wdid, int orderid) throws BaseException
	{
		int last = 1;
		try
		{
			startHour = startHour + 1;
			while (startHour <= Constant.SERVICE_END_TIME)
			{
				// 查找目前的startHour时间的订单
				UserWorkhourModel workhourModel = userWorkhourDao.searchUserWorkhour(0, wdid, startHour);
				if (workhourModel == null)
				{
					startHour = startHour + 1;
				}
				else 
				{
					if (workhourModel.getOrderid() == orderid && 
							workhourModel.getOrdertype() == Constant.HOUR_ORDER_BY_PLATFORM)
					{
						last = last + 1;
						startHour = startHour + 1;
						continue;
					}
					else
					{
						break;
					}
				}
			}
		}
		catch (Exception e) 
		 {
			throw new BaseException(e.getMessage());
		} 
		
		return last;
	}
	
	/**
	 * 查看美发师某一天的工作日程
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public List<Map<String, Object>> getDayWorking(String userID, String year, 
			String month, String day) throws BaseException
	{
		try
		{
			if (userID == null || month == null || day == null)
			{
				return null;
			}
			List<Map<String, Object>> outputList = new ArrayList<Map<String, Object>>();
			int userid = Integer.parseInt(userID);
			int yearid = Integer.parseInt(year);
			int monthid = Integer.parseInt(month);
			int dayid = Integer.parseInt(day);
			UserWorkdateModel workdateModel = userWorkdateDao.searchUserWorkdate(userid, yearid, monthid, dayid);
			// 还没有排布日程的情况
			if (workdateModel == null)
			{
				return outputList;
			}
			// 检索user_workhour表，从中把日期对应的订单都取出来
			int starthour = Constant.SERVICE_START_TIME;
			while (starthour <= Constant.SERVICE_END_TIME)
			{
				UserWorkhourModel workhourModel = userWorkhourDao.searchUserWorkhour(0, workdateModel.getWdid(),
						starthour);
				if (workhourModel == null)
				{
					starthour = starthour + 1;
				}
				else
				{
					if (workhourModel.getState() == Constant.HOUR_REST_STATE || 
							workhourModel.getState() == Constant.HOUR_WORK_STATE)
					{
						// 并没有约的情况
						starthour = starthour + 1;
					}
					else
					{
						if (workhourModel.getOrdertype() == Constant.HOUR_ORDER_BY_PLATFORM)
						{
							// 订单被平台占用
							// 找到订单
							OrderinfoModel orderinfoModel = orderinfoDao.searchOrderinfo(
									workhourModel.getOrderid());
							if (orderinfoModel != null)
							{
								// 找到用户
								int customid = orderinfoModel.getCustomerid();
								UserinfoModel userinfo = userinfoDao.searchUserinfoById(customid);
								if (userinfo != null)
								{
									Map<String, Object> orderMap = new HashMap<String, Object>();
									orderMap.put("OrderID", ShunJianMeiUtil.intToString(workhourModel.getOrderid()));
									orderMap.put("Name", userinfo.getNickname());
									orderMap.put("Mobile", userinfo.getUsername());
									orderMap.put("StartHour", 
											ShunJianMeiUtil.intToString(starthour));
									int lastHour = getLastHourByWorkhour(starthour, 
											workdateModel.getWdid(), workhourModel.getOrderid());
									int endHour = starthour + lastHour;
									if (endHour > Constant.SERVICE_END_HOUR)
									{
										endHour = Constant.SERVICE_END_HOUR;
									}
									orderMap.put("EndHour", ShunJianMeiUtil.intToString(endHour));
									orderMap.put("Type", ShunJianMeiUtil.intToString(workhourModel.getOrdertype()));
									orderMap.put("Content", orderinfoModel.getStylename());
									outputList.add(orderMap);
									starthour = starthour + lastHour;
								}
								else
								{
									starthour = starthour + 1; 
								}
							}
							else
							{
								starthour = starthour + 1;
							}
						}
						else
						{
							// 订单被自编辑占用
							UserEditWorkModel editworkModel = userEditWorkDao.searchUserEditWorkModel(
									workhourModel.getOrderid());
							if (editworkModel != null)
							{
								Map<String, Object> orderMap = new HashMap<String, Object>();
								orderMap.put("OrderID", ShunJianMeiUtil.intToString(editworkModel.getOrderid()));
								orderMap.put("Name", editworkModel.getName());
								orderMap.put("Mobile", editworkModel.getMobile());
								orderMap.put("StartHour", ShunJianMeiUtil.intToString(editworkModel.getStarthour()));
								int lastHour = editworkModel.getLasthour();
								orderMap.put("EndHour", ShunJianMeiUtil.intToString(editworkModel.getEndhour()));
								orderMap.put("Type", ShunJianMeiUtil.intToString(workhourModel.getOrdertype()));
								orderMap.put("Content", editworkModel.getContent());
								outputList.add(orderMap);
								starthour = starthour + lastHour;
								
							}
							else
							{
								starthour = starthour + 1;
							}
						}
					}
				}
			}
			
			return outputList;
		}
		 catch (Exception e) 
		 {
			throw new BaseException(e.getMessage());
		}
	}
	
	/**
	 * 删除某一个自编辑工作日程
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public boolean deleteEditWorking(String userID, String orderID) throws BaseException
	{
		try
		{
			int orderid = Integer.parseInt(orderID);
			return userWorkhourDao.updateUserEditWorkDelete(orderid);
		}
		catch (Exception e) 
		{
			throw new BaseException(e.getMessage());
		}
	}
	
	/**
	 * 是否可以更新自编辑订单信息
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	private boolean canUpdateEditworkOrder(int userid, int iyear, int imonth, int iday, 
			int istartHour, int ilastHour, String orderID) throws BaseException
	{
		try
		{
			UserWorkdateModel workdateModel = userWorkdateDao.searchUserWorkdate(
					userid, iyear, imonth, iday);
			if (workdateModel == null)
			{
				return true;
			}
			else
			{
				// 得到工作小时
				int wdid = workdateModel.getWdid();
				int endhour = istartHour + ilastHour;
				while (istartHour < endhour && istartHour <= Constant.SERVICE_END_HOUR)
				{
					UserWorkhourModel workhourModel = userWorkhourDao.searchUserWorkhour(0, 
							wdid, istartHour);
					if (workhourModel == null)
					{
						istartHour = istartHour + 1;
					}
					else
					{
						// 当时小时已经排了工作了，判断是否是当前ID的工作，如果不是，则直接返回false
						if (workhourModel.getState() == Constant.HOUR_REST_STATE ||
								workhourModel.getState() == Constant.HOUR_WORK_STATE)
						{
							istartHour = istartHour + 1;
						}
						else
						{
							if (orderID == null)
							{
								return false;
							}
							if (workhourModel.getOrdertype() == Constant.HOUR_ORDER_BY_EDIT_WORK &&
									workhourModel.getOrderid() == Integer.parseInt(orderID))
							{
								istartHour = istartHour + 1;
							}
							else
							{
								return false;
							}
						}
					}
				}
			}
		}
		catch (Exception e) 
		{
			throw new BaseException(e.getMessage());
		}
		
		
		return true;
	}
	
	/**
	 * 更新自编辑订单信息
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	private boolean updateWorkhour(int userid, int iyear, int imonth, int iday, 
			int istartHour, int ilastHour, int orderID) throws BaseException
	{
		try
		{
			UserWorkdateModel workdateModel = userWorkdateDao.searchUserWorkdate(
					userid, iyear, imonth, iday);
			int wdid = 0;
			if (workdateModel == null)
			{
				// 首先插入workdate
				UserWorkdateModel userWorkdate = new UserWorkdateModel();
				userWorkdate.setUserid(userid);
				userWorkdate.setYear(iyear);
				userWorkdate.setMonth(imonth);
				userWorkdate.setDay(iday);
				userWorkdate.setCreatetime(new Date());
				// 添加工作日
				int resultId = userWorkdateDao.addUserWorkdate(userWorkdate);
				wdid = resultId;
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
			else
			{
				wdid = workdateModel.getWdid();
			}
			// 将orderID对应的工作时间设置为休息
			if (orderID > 0)
			{
				userWorkhourDao.updateUserWorkhour(orderID, Constant.HOUR_REST_STATE);
			}
			
			// 得到工作小时
			int endhour = istartHour + ilastHour;
			while (istartHour < endhour && istartHour <= Constant.SERVICE_END_HOUR)
			{
				// 设置工作情况
				userWorkhourDao.updateUserWorkhour(orderID, wdid, istartHour, Constant.HOUR_ORDER_STATE,
						Constant.HOUR_ORDER_BY_EDIT_WORK);
				istartHour = istartHour + 1;
			}
		}
		catch (Exception e) 
		{
			// 如果不成功则需要事务回归
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new BaseException(e.getMessage());
		}
		
		return true;
	}
	
	/**
	 * 保存自编辑日程
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public int addUserEditWork(Map<String, Object> inputJsonMap) throws BaseException
	{
		try
		{
			// 取得参数
			String userID = inputJsonMap.get("UserID").toString();
			String startHour = inputJsonMap.get("StartHour").toString();
			String year = inputJsonMap.get("Year").toString();
			String month = inputJsonMap.get("Month").toString();
			String day = inputJsonMap.get("Day").toString();
			String lastHour = inputJsonMap.get("LastHour").toString();
			String orderID = null;
			if (inputJsonMap.get("OrderID") != null)
			{
				orderID = inputJsonMap.get("OrderID").toString();
			}
			
			int userid = Integer.parseInt(userID);
			int iyear = Integer.parseInt(year);
			int imonth = Integer.parseInt(month);
			int iday = Integer.parseInt(day);
			int istartHour = Integer.parseInt(startHour);
			int ilastHour = Integer.parseInt(lastHour);
			
			if (canUpdateEditworkOrder(userid, iyear, imonth, iday, istartHour, ilastHour, orderID))
			{
				int dbid = userEditWorkDao.updateOrder(inputJsonMap);
				if(dbid >= 0)
				{
					// 更新workhour表
					updateWorkhour(userid, iyear, imonth, iday, istartHour, ilastHour, dbid);
					return dbid;
				}
			}
			return Constant.UPDATE_ERROR;
			
		}
		catch (Exception e) 
		{
			// 如果不成功则需要事务回归
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new BaseException(e.getMessage());
		}
	}
	
	/**
	 * 得到美发师的自编辑订单内容
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	@Override
	public List<Map<String, Object>> searchEditWork(String userID, String orderID) throws BaseException
	{
		try
		{
			List<Map<String, Object>> outputList = new ArrayList<Map<String, Object>>();
			if (userID != null && orderID != null)
			{
				UserEditWorkModel editworkModel = 
						userEditWorkDao.searchUserEditWorkModel(Integer.parseInt(orderID));
				if (editworkModel != null)
				{
					Map<String, Object> orderMap = new HashMap<String, Object>();
					orderMap.put("OrderID", orderID);
					orderMap.put("StartHour", editworkModel.getStarthour());
					orderMap.put("Name", editworkModel.getName());
					orderMap.put("Mobile", editworkModel.getMobile());
					orderMap.put("Content", editworkModel.getContent());
					orderMap.put("LastHour", editworkModel.getLasthour());
					outputList.add(orderMap);
				}
			}
			
			return outputList;
		}
		catch (Exception e)
		{
			throw new BaseException(e.getMessage());
		}
	}
}
