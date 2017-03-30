package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

public interface ICouponsService {

	/**
	 * 查询用户的优惠卷
	 * @param userid 用户ID
	 * @param servicecode xjc设计造型 jc造型 tf烫发 rf染发 hl护理
	 * @return
	 * @throws BaseException
	 */
	public UserCouponsModel searchCoupons(int userid,String  servicecode)throws BaseException;
	
	/**
	 * 根据id查询优惠券信息
	 * @param amount
	 * @return
	 */
	public CouponsModel searchCouponByID(String couponID)throws BaseException;
	
	/**
	 * 查询优惠券OrderPay
	 * @return
	 */
	public List<CouponsModel> searchCouponsByOrderPay(double amount) throws BaseException;
}
