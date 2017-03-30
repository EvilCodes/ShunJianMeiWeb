package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkplaceModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserWorkplaceService {
	
	/**
	 * 查询美发师工作日
	 * @param userid 用户ID
	 * @return
	 */
	public UserWorkdateModel searchUserWorkdate(int userid)throws BaseException;
	
	/**
	 * 查询美发师工时的栏的清单
	 * @param wdid 工作日ID
	 * @return
	 */
	public List<UserWorkhourModel> searchUserWorkhour(int wdid)throws BaseException;

	/**
	 * 查询美发师工作地点
	 * @param storeid 店铺ID
	 * @return
	 */
	public List<UserWorkplaceModel> searchUserWorkplaces(int storeid)throws BaseException;
	
	/**
	 * 查询美发师工作地点的清单
	 * @param wdid 工作日ID
	 * @return
	 */
	public List<UserWorkplaceModel> searchUserWorkplace(int wdid)throws BaseException;

	/**
	 * 获取指定日期日程
	 * @param userID美发师id
	 * @param wdDate工作日：2015-09-30
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> addHairdresserSchedule(String userID,String wdDate)throws BaseException;
	
	// add by jiazhaohui
	/**
	 * 一键设置所有美发师的工作日程
	 * @param userID美发师id
	 * @return
	 * @throws BaseException
	 */
	public boolean updateAllTimeWorking(String userID) throws BaseException;
	
	public boolean updateStoreWorkhour(int orderid,int state) throws BaseException;
	
	/**
	 * 添加指定日期日程
	 * @param userID美发师id
	 * @param wdDate工作日：2015-09-30
	 * @param addressID美发店 ID串 用|连接
	 * @return
	 * @throws BaseException
	 */
	public boolean addHairdresserSchedule(String userID,String wdDate,String addressID,List<Map<String, Object>> itemList)throws BaseException;
}
