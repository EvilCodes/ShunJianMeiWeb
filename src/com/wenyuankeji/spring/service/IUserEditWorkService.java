package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.util.BaseException;

public interface IUserEditWorkService 
{
	/**
	 * 查看美发师某一天的工作日程
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> getDayWorking(String userID, String year, 
			String month, String day) throws BaseException;
	
	
	/**
	 * 删除某一个自编辑工作日程
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	public boolean deleteEditWorking(String userID, String orderID) throws BaseException;
	
	/**
	 * 保存自编辑日程
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	public int addUserEditWork(Map<String, Object> inputJsonMap) throws BaseException;
	
	/**
	 * 得到美发师的自编辑订单内容
	 * @param 
	 * @param 
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> searchEditWork(String userID, String orderID) throws BaseException;
}
