package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.model.StoreWorkhourModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IStoreinfoDao {
	
	/**
	 * 美发店信息验证
	 * @param storeinfoModel
	 * @return
	 * @throws BaseException
	 */
	public boolean updateStoreinfo(StoreinfoModel storeinfo) throws BaseException;

	/**
	 * 更具店铺id查询店铺信息
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public StoreinfoModel searchStoreinfo(int storeid) throws BaseException;
	
	public boolean updateScore(int storeid,int score) throws BaseException;
	
	
	/**
	 * 店铺列表
	 * @param cityid 城市ID
	 * @param Score  店铺评价星级 默认1从高到低 0从低到高
	 * @param Order  订单 默认1从多到少 0从少到多
	 * @param Longitude  经度
	 * @param Latitude   纬度
	 * @param Car   车位数 默认1从多到少
	 * @param PageSize   返回数据行数
	 * @param PageIndex   当前页数 从1开始
	 * @param Type 排序类型 1按星级2按订单3按距离（距离之后近到远）4按车位数
	 * @return
	 */
	public List<StoreinfoModel> searchStoreinfo(String cityid,String Score,String orderquantity,double Longitude,
			double Latitude,String Car,int PageSize,int PageIndex, String Type,String Infversion) throws BaseException;
	
	/**
	 * 根据商圈id查询美发店信息
	 * @param areaid
	 * @return
	 * @throws BaseException
	 */
	public List<StoreinfoModel> searchStoreinfoByAreaid(String areaid, int state, String storeIdStr)
			throws BaseException;
	
	/**
	 * 添加店铺工作日
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public int addStoreWorkdate(StoreWorkdateModel o)throws BaseException;
	
	/**
	 * 删除店铺工作日
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean deleteStoreWorkdate(StoreWorkdateModel o)throws BaseException;
	
	/**
	 * 添加工位信息
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public boolean addStoreWorkhour(StoreWorkhourModel o)throws BaseException;
	
	/**
	 * 根据工作日id查询工位信息
	 * @param wdid工作日i
	 * @return
	 * @throws BaseException
	 */
	public List<StoreWorkhourModel> searchStoreWorkhour(int wdid)throws BaseException;
	
	/**
	 * 查询工位信息
	 * @param wdid工作日id
	 * @param chairid工位id
	 * @param hour开始小时
	 * @return
	 * @throws BaseException
	 */
	public StoreWorkhourModel searchStoreWorkhour(int wdid,int chairid,int hour)throws BaseException;
	
	/**
	 * 查询店铺工作日id
	 * @param year
	 * @param month
	 * @param day
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public StoreWorkdateModel searchStoreWorkdate(String year,String month,String day,int storeid)throws BaseException;

	/**
	 * 根据工作日查询和店铺id查询工位
	 * @param year
	 * @param month
	 * @param day
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public List<Object[]> searchGongWeiByGongZuoRi(String year, String month, String day,
			int storeid) throws BaseException;
	
	/**
	 * 删除店铺工位工时
	 * @param wdid工作日id
	 * @param chairid工作id
	 * @return
	 * @throws BaseException
	 */
	public boolean deleteStoreWorkhour(int wdid,int chairid)throws BaseException;
	
	public boolean deleteStoreWorkhourByHour(int wdid,int hour)throws BaseException;
	/**
	 * 店铺登录
	 * @param 用户名
	 * @param 密码
	 * @return StoreinfoModel
	 * @throws BaseException
	 */
	public StoreinfoModel searchStoreinfoLogin(String username, String password)throws BaseException;
	
	public List<StoreWorkhourModel> searchStoreWorkhourByState(int wdid)
			throws BaseException;
	/**
	 * 删除工位
	 * @param wdids
	 * @return
	 * @throws BaseException
	 */
	public boolean deleteStoreWorkhourByWdids(String wdids)
			throws BaseException;
	/**
	 * 删除工作日
	 * @param wdids
	 * @return
	 * @throws BaseException
	 */
	public boolean deleteStoreWorkDateByWdids(String wdids)
			throws BaseException;

	/**
	 * 根据美发店id查询所有美发师的名字
	 * @param storeId
	 * @return
	 * @throws BaseException
	 */
	public String searchMasterList(String storeid) throws BaseException;
}
