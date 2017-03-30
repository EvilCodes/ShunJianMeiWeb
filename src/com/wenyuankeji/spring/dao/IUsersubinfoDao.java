package com.wenyuankeji.spring.dao;

import java.util.List;
import java.util.Set;

import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUsersubinfoDao {

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
	public int addUserSubInfo(String sql);
	
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
	public UsersubinfoModel searchUsersubinfo(int userid)throws BaseException;
	
	/**
	 * 根据商圈id和美发师工作日查询美发师id
	 * @param AreaID
	 * @param futureDays
	 * @return
	 * @throws BaseException
	 */
	public Set<String> searchUsersubinfoIdList(int AreaID,String futureDay)throws BaseException;
	/**
	 * 查询美发师列表
	 * @param Sort
	 * @param PageSize
	 * @param PageIndex
	 * @param userids
	 * @return
	 * @throws BaseException
	 */
	public List<UsersubinfoModel> searchUsersubinfoList(int Sort, int CityID ,int PageSize, int PageIndex,Set<String> userids,String Infversion)throws BaseException;
	
	/**
	 * 美发师信息验证
	 * @param UsersubinfoModel
	 * @return
	 * @throws BaseException
	 */
	public boolean updateStoreinfo(UsersubinfoModel usersubinfo) throws BaseException;
	
	/**
	 * 更新接单量
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateOrdernum(int userid) throws BaseException;
	
	/**
	 * 更新评分
	 * @param userid
	 * @param score
	 * @return
	 * @throws BaseException
	 */
	public boolean updateScore(int userid,int score) throws BaseException;
	
	// add by jiazhaohui
	/**
	 * 设置美发师的默认信息
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateMasterDefaultSetting(int userid, String workDay, String workDayHour, String StoreId,
			String WorkShop, String Address) 
			throws BaseException;
	
	/**
	 * 根据店的id查询用户列表
	 * @param storeId
	 * @return
	 * @throws BaseException
	 */
	public List<UsersubinfoModel> searchSubUserinfoByStoreId(int storeId) throws BaseException;
}
