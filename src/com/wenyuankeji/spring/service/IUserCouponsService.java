package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserCouponsService {

	public UserCouponsModel searchUserCouponsByID(int id)throws BaseException;
	
	public List<UserCouponsModel> searchUserCoupons(int userid)throws BaseException;
	
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
