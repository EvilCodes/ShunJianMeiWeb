package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserTradelogSearchModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IOrderinfoService {

	/**
	 * 根据订单ID查询订单信息
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException;
	
	/**
	 * 添加订单信息
	 * @param orderinfo
	 * @return 订单id
	 */
	public int addOrderinfo(OrderinfoModel orderinfo,Set<OrderdetailModel> orderdetails,List<UserCouponsModel> userCouponsList, String StoreID, double Times, String WHID) throws BaseException ;
	
	/**
	 * 根据美发工作日id查询订单信息
	 * @param storeid
	 * @return
	 * @throws BaseException
	 */
	public List<OrderinfoModel> searchOrderinfoByWdid(int wdid) throws BaseException;
	
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
	 */
	public boolean updateOrderinfoModel(int orderid,int paystate)throws BaseException;

	/**
	 * 查询我的订单列表
	 * @param userID
	 * @param state
	 * @param sort
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, String>> searchOrderList(String userID, String state,
			String sort,int pageIndex, int pageSize) throws BaseException;
	
	
	/**
	 * 订单成功的时候短信提示
	 * @param orderinfo 订单信息
	 * @return
	 * @throws BaseException
	 */
	public void SendMobileMessage(OrderinfoModel orderinfo) throws BaseException;
	
	
	public boolean updateOrderinfoPaystate(int orderid, int paystate)
			throws BaseException;
	
	/**
	 * 根据美发师id查询我的订单列表
	 * @param userID
	 * @param state
	 * @param sort
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, String>> searchOrderListByUserID(String userID, String state,
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
	 * 查询订单详细(美发师)
	 * @param userID
	 * @param orderID
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> searchOrderDetailForMeiFaShi(String userID, String orderID) throws BaseException;
	

	/**
	 * 查询订单详细(用户端)
	 * @param userID
	 * @param orderID
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> searchOrderDetail(String userID, String orderID) throws BaseException;
	
	/**
	 * 根据预约时间和美发师id查询订单信息
	 * @param userID
	 * @param appointmenttime
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, Object>> searchOrderinfoByUseridAndAppointmenttime(String userID, String appointmenttime)throws BaseException;
	
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
	 * 更新订单状态,定时任务
	 * @param recordid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateOrderinfoState(int orderid,int paystate)throws BaseException;
	
	
	/**
	 * 修改加单信息
	 * @param additionalcode
	 * @return
	 * @throws BaseException
	 */
	public boolean updateAdditionalInfo(String additionalcode)throws BaseException;
	
	
	/**
	 * 根据订单ID，查询订单明细物料总额
	 * @param orderid 订单编号
	 * @return
	 * @throws BaseException
	 */
	public int getMaterialidpriceTotal(int orderid) throws BaseException;
	
	/**
	 * 根据加单编号，查询订单信息
	 * @param additionalcode 加单编号
	 * @return
	 * @throws BaseException
	 */
	public OrderinfoModel searchOrderinfoByAdditionalcode(String additionalcode) throws BaseException;
	
	/**
	 * 提交订单
	 * 
	 * @return
	 * @throws BaseException
	 */
	public Map<String, Object> addCommitOrder(Map<String, Object> paraMap, HttpServletRequest request, HttpServletResponse response) throws BaseException;
	/**
	 * 根据美发师id查询我的接单列表
	 * @param userID
	 * @param state
	 * @param sort
	 * @return
	 * @throws BaseException
	 */
	public List<Map<String, String>> searchFinishOrderListByUserID(
			String userID, String state, String sort, int pageIndex,
			int pageSize) throws BaseException;
}
