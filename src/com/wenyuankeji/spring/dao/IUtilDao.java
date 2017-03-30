package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.BaseRequestlog;
import com.wenyuankeji.spring.model.BaseException;

public interface IUtilDao {

	/**
	 * 添加接口访问记录
	 * @return
	 */
	public void addBaseRequestlog(BaseRequestlog o);
	/**
	 * 添加错误日志
	 * @param o
	 */
	public int addBaseException(BaseException o);
}
