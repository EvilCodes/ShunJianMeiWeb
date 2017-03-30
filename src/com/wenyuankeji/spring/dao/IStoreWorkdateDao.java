package com.wenyuankeji.spring.dao;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IStoreWorkdateDao {
	/**
	 * 根据店铺id和工作日查询店铺工作日信息
	 * @param storeids
	 * @param userWorkdataInfoMap
	 * @return
	 * @throws BaseException
	 */
	public List<StoreWorkdateModel> searchStoreWorkdateList(String storeids,
			Map<String, Integer> userWorkdataInfoMap) throws BaseException;
	
	/**
	 * 查询美发店工位工时信息
	 * @param wdid
	 * @param startHour
	 * @param endhour
	 * @return
	 * @throws BaseException
	 */
	public List<Integer> searchStoreHourInfo(int wdid,int startHour,int endhour) throws BaseException;
	
	/**
	 * 查询可约的美发店ID
	 * @return
	 * @throws BaseException
	 */
	public List<Integer> searchStoreWorkdateInfo() throws BaseException;
	
	/**
	 * 修改美发店工时表 状态可约
	 * @return
	 * @throws BaseException
	 */
	public boolean updateStoreWorkhour(int orderid) throws BaseException;
	
	public StoreWorkdateModel searchStoreWorkdate(int storeId,int year,int month,int day );
}
