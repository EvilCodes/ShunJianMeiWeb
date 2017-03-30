package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserWorkdateService {
	
	/**
	 * 查询美发师工作日
	 * @param userid 用户ID
	 * @return
	 */
	public UserWorkdateModel searchUserWorkdate(int wdid)throws BaseException;
	
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
