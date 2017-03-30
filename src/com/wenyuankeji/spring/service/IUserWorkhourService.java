package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserWorkhourService {

	/**
	 * 根据工时id查询工时信息
	 * @param whid
	 * @return
	 * @throws BaseException
	 */
	public UserWorkhourModel searchUserWorkhour(int whid)throws BaseException;
	
	public boolean updateUserWorkhour(int orderid,int state) throws BaseException;
	
	public UserWorkhourModel searchUserWorkhour(int orderid,int wdid,int hour)throws BaseException;
	
	public boolean updateUserWorkhour(int orderid,int wdid,int hour,int state) throws BaseException;
	
	/**
	 * 根据美发师id,起始日期查询美发师后几天日程
	 * @param userID
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> searchScheduleService (int ID,int num) throws BaseException;
}
