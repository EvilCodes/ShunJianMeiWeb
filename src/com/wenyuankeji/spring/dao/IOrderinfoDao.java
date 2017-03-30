package com.wenyuankeji.spring.dao;


import java.util.List;
import java.util.Set;

import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserTradelogSearchModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IOrderinfoDao {

	/**
	 * 根据订单ID查询订单信息
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException;
	
	/**
	 * 根据订单ID查询订单详细
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public List<OrderdetailModel> searchOrderdetail(int orderid) throws BaseException;
	
	/**
	 * 添加订单信息
	 * @param orderinfo
	 * @return 订单id
	 */
	public int addOrderinfo(OrderinfoModel orderinfo, String StoreID, double Times, String WHID) throws BaseException ;
	
	/**
	 * 添加订单详情
	 * @param orderdetails
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public boolean addOrderdetail(Set<OrderdetailModel> orderdetails,int orderid) throws BaseException;
	
	/**
	 * 查询店铺的所有订单信息
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchOrderinfoStoreId(int storeid, int page, int rows) throws BaseException;
	
	/**
	 * 根据店铺id查询订单信息
	 * @param storeid店铺id
	 * @param orderByType配需列1：状态 2：时间
	 * @param orderByVal 1：升序 2：降序
	 * @return
	 * @throws BaseException
	 */
	public List<UserTradelogSearchModel> searchOrderinfoByStoreId(int storeid,int orderByType, int orderByVal, int page,
			int rows)
			throws BaseException;

	/**
	 * 根据店铺id查询订单信息
	 * @param storeid店铺id
	 * @param orderByType配需列1：状态 2：时间
	 * @param orderByVal 1：升序 2：降序
	 * @return
	 * @throws BaseException
	 */
	public int searchOrderinfoByStoreId(int storeid,int orderByType, int orderByVal)
			throws BaseException;
	
	/**
	 * 查询店铺的所有订单数
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public int searchOrderinfoStoreId(int storeid) throws BaseException;
	
	/**
	 * 更新订单状态
	 * @param recordid
	 * @return
	 * @throws BaseException
	 * 
	 */
	public boolean updateOrderinfoModel(int orderid,int paystate)throws BaseException;
	
	/**
	 * 根据用户id查询我的订单列表
	 * @param userID
	 * @param state
	 * @param sort
	 * @param page
	 * @param rows
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchOrderList(String userID, String state,
			String sort,int pageIndex, int pageSize) throws BaseException;
	/**
	 * 根据美发师id查询我的订单列表
	 * @param userID
	 * @param state
	 * @param sort
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchOrderListByUserID(String userID, String state,
			String sort,int pageIndex, int pageSize) throws BaseException;
	
	/**
	 * 根据订单和用户加单
	 * @param recordid
	 * @return
	 * @throws BaseException
	 * 
	 */
	public boolean updateOrderinfo(int orderid,int customerid,String additionalcontent,double additionalamount)throws BaseException;

	/**
	 * 获取订单的服务项目
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public List<String> searchOrderServicenameList(String orderid)throws BaseException;

	
	/**
	 * 根据预约时间和美发师id查询订单信息
	 * @param userid
	 * @param appointmenttime
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchOrderinfoByUseridAndAppointmenttime(String userid,String appointmenttime) throws BaseException;
	
	/**
	 * 根据店铺工作日，查询订单id
	 * @param wdid
	 * @return
	 * @throws BaseException
	 */
	public List<Integer> searchOrderIdByWdid(int wdid)throws BaseException;
	
	/**
	 * 根据多个订单id，查询订单信息
	 * @param orderids
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchOrderinfo(String orderids) throws BaseException;
	
	/**
	 * 修改订单优惠券金额
	 * @param orderid
	 * @param amount
	 * @return
	 * @throws BaseException
	 * 
	 */
	public boolean updateOrderinfoCouponAmount(int orderid, double couponamount, double amount)throws BaseException;
	
	/**
	 * 查询美发师接单量
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateCountOrderNum(int userid)throws BaseException;
	
	/**
	 * 查询美发店接单量
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateCountStoreNum(int storeid)throws BaseException;
	
	/**
	 * 更新我的发型使用量
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateCountUsedNum(int hairstyleid)throws BaseException;
	
	
	/**
	 * 查询订单表，待支付状态的时间超过30分钟的订单集合
	  * @param ordertime 订单取消时间
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchOrderInfoWaitTime(String ordertime)throws BaseException;
	
	/**
	 * 查询订单表，查询配置文件中设置7天的完成订单，做分成处理
	  * @param allocationtime 分成处理时间
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchOrderInfoByAllocationtime(String allocationtime)throws BaseException;

	/**
	 * 修改加单信息
	 * @param additionalcode
	 * @return
	 * @throws BaseException
	 */
	public boolean updateAdditionalInfo(String additionalcode)throws BaseException;
	
	
	/**
	 * 根据加单ID查询订单信息
	 * @param additionalcode 加单编号
	 * @return
	 * @throws BaseException
	 */
	public OrderinfoModel searchOrderinfoByAdditionalcode(String additionalcode) throws BaseException;
	
	/**
	 * 修改订单支付类型
	 * @param additionalcode 加单编号
	 * @return
	 * @throws BaseException
	 */
	public boolean updateOrderPayType(int orderid, int type) throws BaseException;
	
	/**
	 * 修改加单支付类型
	 * @param additionalpaytype 加单编号
	 * @return
	 * @throws BaseException
	 */
	public boolean updateAdditionalPaytype(String additionalcode, int additionalpaytype) throws BaseException;
	
	/**
	 * 根据订单ID，查询订单明细物料总额
	 * @param orderid 订单编号
	 * @return
	 * @throws BaseException
	 */
	public int getMaterialidpriceTotal(int orderid) throws BaseException;
	

	/**
	 * 更新订单状态,分成处理
	 * @param orderid 订单ID
	 * @param paystate 订单状态
	 * @return
	 * @throws BaseException
	 * 
	 */
	public boolean updateOrderinfoPayState(int orderid,int paystate)throws BaseException;
	/**
	 * 根据美发师id查询我的接单列表
	 * @param userID
	 * @param state
	 * @param sort
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchFinishOrderListByUserID(String userID,
			String state, String sort, int pageIndex, int pageSize) throws BaseException ;

}
