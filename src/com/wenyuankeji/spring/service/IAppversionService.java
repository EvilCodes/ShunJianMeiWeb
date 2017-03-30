package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.AppversionModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IAppversionService {

	public AppversionModel searchAppversion(String vcode, int type) throws BaseException;
}
