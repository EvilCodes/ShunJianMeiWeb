package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.BaseWechatTradelogModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IWechatPayService {
	
	
	/**
	 * 支付宝支付
	 * @param orderId
	 * @return
	 */
	public boolean updateOrderinfo(int orderId)throws BaseException;
	
	/**
	 * 支付宝加单支付
	 * @param orderId
	 * @return
	 */
	public boolean updateAdditionalInfo(String additionalcode)throws BaseException;
	
	/**
	 * 添加微信交易日志
	 * @param o 
	 * @return
	 * @throws BaseException
	 */
	public boolean addBaseWechatTradelog(BaseWechatTradelogModel o) throws BaseException;
}
