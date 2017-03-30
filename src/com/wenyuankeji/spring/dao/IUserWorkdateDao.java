package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserWorkdateDao {

	/**
	 * 查询美发师工作日
	 * @param wdid 工作日ID
	 * @return
	 */
	public UserWorkdateModel searchUserWorkdate(int wdid)throws BaseException;

	/**
	 * 根据美发师id和工作日查询工作日信息
	 * @param userID
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws BaseException
	 */
	public UserWorkdateModel searchUserWorkdate(int userID,int year,int month,int day) throws BaseException;
	
	/**
	 * 添加工作日
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public int addUserWorkdate(UserWorkdateModel o)throws BaseException;
	
	public List<UserWorkdateModel> searchUserWorkdateByUserID(int userID)throws BaseException;
	
	//判断当前用户当天全部时间休息，则不显示
	public boolean searchUserWorkdateByUserId(int userID,String year,String month,String day) throws BaseException;
	/**
	 * 查询美发师工作日日程
	 * @param userid 用户ID num 查询天数
	 * @return
	 */
	public List<UserWorkdateModel> searchUserWorkdateByUserIDAndNum(int ID,
			int num) throws BaseException;
	
}
