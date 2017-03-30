package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IOrderPayService {

	/**
	 * 根据订单ID查询单信息
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException;
	/**
	 * 查询我的优惠券
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public List<UserCouponsModel> searchUserCoupons(int userid) throws BaseException;
	/**
	 * 查询优惠券
	 * @return
	 */
	public List<CouponsModel> searchCoupons() throws BaseException;
	/**
	 * 根据订单ID查询订单详细
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public List<OrderdetailModel> searchOrderdetail(int orderid) throws BaseException;
	/**
	 * 根据用户查询钱包的卡片
	 * @param userid 用户ID
	 * @param ownertype 所有者类型: 1用户2美发师3店铺
	 * @return
	 * @throws BaseException
	 */
	public UserWalletModel searchUserWallet(int userid,int ownertype) throws BaseException;
	/**
	 * 根据订单ID查询优惠券
	 * @param orderid
	 * @return
	 * @throws BaseException
	 */
	public List<UserCouponsModel> searchUserCoupon(int orderid) throws BaseException;
	
	/**
	 * 支付订单，查询服务所使用的优惠券
	 * @param orderid 订单ID
	 * @param servicecode xjc设计造型 jc造型 tf烫发 rf染发 hl护理
	 * @return
	 * @throws BaseException
	 */
	public UserCouponsModel searchCoupons(String orderid,String servicecode) throws BaseException;

}
