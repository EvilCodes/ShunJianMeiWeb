package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.util.BaseException;

public interface IBaseAlipaytradelogDao {

	public boolean addBaseAlipaytradelog(String orderid, String content, int quickpay) throws BaseException;
	
}
