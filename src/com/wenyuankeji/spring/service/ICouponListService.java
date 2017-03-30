package com.wenyuankeji.spring.service;

import java.util.List;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;


public interface ICouponListService {

	/**
	 * 查询我的优惠券
	 * @param userid 用户ID
	 * @return
	 * @throws BaseException
	 */
	public List<UserCouponsModel> searchUserCoupons(int userid,int pageSize,int pageIndex) throws BaseException;
	
}
