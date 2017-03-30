package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.util.BaseException;

public interface IBaseRequestkeyrecordService {
	
	/**
	 * 添加接口访问记录
	 * @return
	 */
	public int addBaseRequestKeyRecord(String keystring)throws BaseException;
	/**
	 * 添加接口之前，查询请求记录是否存在，存在后不执行操作
	 * @return
	 */
	public boolean selectBaseRequestKeyRecord(String keystring)throws BaseException;

}
