package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUsersubinfoService {

	/**
	 * 根据userId更新美发师扩展信息
	 * @param userSubInfo
	 * @return
	 */
	public boolean updateUserSubInfoByUserId(UsersubinfoModel userSubInfo) throws BaseException;
	
	/**
	 * 新增美发师扩展信息
	 * @param userSubInfo
	 * @return
	 */
	public int addUserSubInfo(UsersubinfoModel userSubInfo);
	
	public int addUserSubInfoForInsert(UsersubinfoModel o);
	
	/**
	 * 根据userId查询美发师扩展信息
	 * @param userId
	 * @return
	 */
	public UsersubinfoModel searchUserSubInfoByUserId(int userId);
	
	
	/***********UP  mupengyuan*****************/
	
	
	
	
	/**
	 * 根据美发师id查询美发师信息
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public UsersubinfoModel searchUsersubinfo(int userid) throws BaseException;
	
	/**
	 * 查询美发师列表
	 * @param Days
	 * @param AreaID
	 * @param Sort
	 * @param PageSize
	 * @param PageIndex
	 * @return
	 * @throws BaseException
	 */
	public List<UsersubinfoModel> searchUsersubinfoList(int Days,int AreaID,int CityID,
			int Sort,int PageSize,int PageIndex,String Infversion) throws BaseException;
	
	/**
	 * 美发师信息验证
	 * @param UsersubinfoModel
	 * @return
	 * @throws BaseException
	 */
	public boolean updateStoreinfo(UsersubinfoModel usersubinfo) throws BaseException;
	
	
	
	/************管理端************/
	/**
	 * 查询美发师
	 * @param cityid 城市ID
	 * @param userid 美发师ID
	 * @param tel    电话
	 * @param startTime  注册开始时间
	 * @param endTime    注册结束时间
	 * @param checkstate 审核状态
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public List<UserinfoModel> searchUserinfo(String cityid,String userid,String tel,String startTime,String endTime,String checkstate,int page, int rows) throws BaseException;
	/**
	 * 查询美发师
	 * @param cityid 城市ID
	 * @param userid 美发师ID
	 * @param tel    电话
	 * @param startTime  注册开始时间
	 * @param endTime    注册结束时间
	 * @param checkstate 审核状态
	 * @return
	 * @throws BaseException
	 */
	public int searchUserinfoCount(String cityid,String userid,String tel,String startTime,String endTime,String checkstate) throws BaseException;
	
	// add by jiazhaohui
	/**
	 * 设置美发师的默认信息
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateMasterDefautSetting(int userid, String workDay, String workDayHour, 
			String StoreId, String WorkShop, String Address) throws BaseException;

	
	/**
	 * 根据店的id查询用户列表
	 * @param storeId
	 * @return
	 * @throws BaseException
	 */
	public List<UsersubinfoModel> searchSubUserinfoByStoreId(int storeId) throws BaseException;
}
