package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.AppversionModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IAppversionDao {

	public AppversionModel searchAppversion(String vcode, int type) throws BaseException;
	
}
