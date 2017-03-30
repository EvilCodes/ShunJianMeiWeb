package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.BaseWechatTradelogModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseWechatTradelogDao {

	/**
	 * 添加微信交易日志
	 * @param o 
	 * @return
	 * @throws BaseException
	 */
	public boolean addBaseWechatTradelog(BaseWechatTradelogModel o) throws BaseException;
	
}
