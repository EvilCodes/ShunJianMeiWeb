package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.StoreWorkdateModel;
import com.wenyuankeji.spring.model.StoreWorkhourModel;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IStoreinfoService {	
	
	/**
	 * 美发店信息验证
	 * @param storeinfoModel
	 * @return
	 * @throws BaseException
	 */
	public boolean updateStoreinfo(StoreinfoModel storeinfo) throws BaseException;

	/**
	 * 根据店铺id查询店铺信息
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public StoreinfoModel searchStoreinfo(int storeid) throws BaseException;
	
	public List<StoreinfoModel> searchStoreinfo(String userid,String Score,String orderquantity,double Longitude,
			double Latitude,String Car,int PageSize,int PageIndex,String Type,String Infversion)throws BaseException;
	
	/**
	 * 根据美发师工时id和预约时长查询店铺信息（2.13用）
	 * @param whid
	 * @param times
	 * @return
	 * @throws BaseException
	 */
	public List<StoreinfoModel> searchStoreinfoList(int whid,int times) throws BaseException;
	
	/**
	 * 根据商圈id查询美发店
	 * @param areaid
	 * @return
	 * @throws BaseException
	 */
	public List<StoreinfoModel> searchStoreinfoByAreaid(String areaid, int state) throws BaseException;
	
	/**
	 * 设置美发美发店工作日已经工位工时
	 * @param storeId美发店id
	 * @param commitType操作类型
	 * @param chairNum工位数
	 * @param swd工作日
	 * @return
	 * @throws BaseException
	 */
	public Map<String, Object> addStoreWorkdate(int storeId,int commitType,int chairNum,String swd)throws BaseException;
	
	/**
	 * 店铺登录
	 * @param 用户名
	 * @param 密码
	 * @return StoreinfoModel
	 * @throws BaseException
	 */
	public StoreinfoModel searchStoreinfoLogin(String username, String password)throws BaseException;

	/**
	 * 查询工位数
	 * @param wsdData工作日
	 * @param storeId店铺id
	 * @return
	 * @throws BaseException
	 */
	public List<StoreWorkhourModel> searchChairidCount(String wsdData,String storeId)throws BaseException;
	
	/**
	 * 根据工作日查询和店铺id查询工作日id
	 * @param wsdData
	 * @param storeId
	 * @return
	 * @throws BaseException
	 */
	public StoreWorkdateModel searchStoreWorkdate(String wsdData,String storeId)throws BaseException;

	/**
	 * 根据工作日查询和店铺id查询工位
	 * @param wsdData
	 * @param storeId
	 * @return
	 * @throws BaseException
	 */
	public List<Object[]> searchGongWeiByGongZuoRi(String wsdData, String storeId)throws BaseException;

	public boolean updateStoreWorkhour(int wdid,int type,int chairNum,int hour)throws BaseException;
	/**
	 * 根据美发店id查询所有美发师的名字
	 * @param storeId
	 * @return
	 * @throws BaseException
	 */
	public String searchMasterList(String storeid) throws BaseException;
	/**
	 * 判断美发师时间是否在美发店时间之外
	 * @param storeId
	 * @return
	 * @throws BaseException
	 */
	public boolean compareStoreAndUserTime(int hour, int wdid) throws BaseException;

}
