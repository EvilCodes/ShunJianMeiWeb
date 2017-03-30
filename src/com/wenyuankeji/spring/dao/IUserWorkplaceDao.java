package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UserWorkplaceModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserWorkplaceDao {

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
	 * @param storeid 美发店ID
	 * @return
	 */
	public List<UserWorkplaceModel> searchUserWorkplaces(int storeid)throws BaseException;
	public boolean updateStoreWorkhour(int orderid,int state) throws BaseException;
	
	/**
	 * 查询美发师工作地点的清单
	 * @param wdid 工作日ID
	 * @return
	 */
	public List<UserWorkplaceModel> searchUserWorkplace(int wdid)throws BaseException;
	
	/**
	 * 添加美发师工作地点
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean addUserWorkplace(UserWorkplaceModel o)throws BaseException;
	
	public boolean deleteUserWorkplaceByWdid(int wdid) throws BaseException;

}
