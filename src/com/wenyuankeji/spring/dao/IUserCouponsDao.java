package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserCouponsDao {

	
	public UserCouponsModel searchUserCouponsByID(int id)
			throws BaseException;
	
	public List<UserCouponsModel> searchUserCoupons(int userid) throws BaseException;
	
	/**
	 * 添加我的优惠券
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public int addUserCoupons(UserCouponsModel o) throws BaseException;
	
	/**
	 * 查询我的优惠券数
	 * @param userid 用户ID
	 * @return
	 * @throws BaseException
	 */
	public int searchUserCouponsCount(int userid) throws BaseException;
	
	/**
	 * 查询我的优惠券列表，带分页
	 * @param userid 用户ID
	 * @param pageSize 每页显示数
	 * @param pageIndex 当前页
	 * @return
	 * @throws BaseException
	 */
	public List<UserCouponsModel> searchUserCoupons(int userid,int pageSize,int pageIndex) throws BaseException;
	
	/**
	 * 根据订单ID查询优惠券
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public List<UserCouponsModel> searchUserCoupon(int orderid) throws BaseException;
	
	/**
	 * 查询当前手机号，是否领取过优惠券
	 * @param mobile 手机号
	 * @param source 分享来源
	 * @return
	 */
	public UserCouponsModel searchUserCoupons(String mobile,int source) throws BaseException;
	
	public boolean updateUserCoupons(int orderId) throws BaseException;
	
	public boolean updateUserCoupons(int id,int orderId) throws BaseException;
	
	/**
	 * 支付订单，查询服务所使用的优惠券
	 * @param orderid 订单ID
	 * @param servicecode xjc设计造型 jc造型 tf烫发 rf染发 hl护理
	 * @return
	 * @throws BaseException
	 */
	public UserCouponsModel searchCoupons(String orderid,String servicecode) throws BaseException;
	
	/**
	 * 根据优惠券ID，查询我的优惠券ID
	 * @return
	 * @throws BaseException
	 */
	public UserCouponsModel searchCoupons(int couponid,int userid) throws BaseException;
	
	/**
	 * 注册的时候，根据注册的用户手机号，更新我的优惠券表的用户id
	 * @param mobile 手机号
	 * @param userid 用户id
	 * @return
	 * @throws BaseException
	 */
	public boolean updateUserCoupons(String mobile,int userid) throws BaseException;
	
}
