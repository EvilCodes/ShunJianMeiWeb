package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IYongHuFenXiangService {
	
	/**
	 * 添加优惠券
	 * @return
	 */
	public int addCoupons(String evaid,String mobile) throws BaseException;
	
	/**
	 * 查询基础设置表	
	 * @param configcode 配置编码
	 * @return
	 * @throws BaseException
	 */
	public BaseConfigModel searchBaseConfigByCode(String configcode)throws BaseException;

	/**
	 * 查询当前手机号，是否领取过优惠券
	 * @param mobile 手机号
	 * @param source 分享来源
	 * @return
	 */
	public UserCouponsModel searchUserCoupons(String mobile,int source) throws BaseException;
}
