package com.wenyuankeji.spring.dao;

import com.wenyuankeji.spring.model.BaseRequestKeyRecord;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseRequestKeyRecordDao {

	/**
	 * 添加接口访问记录
	 * @return
	 */
	public int addBaseRequestKeyRecord(BaseRequestKeyRecord o)throws BaseException;
	/**
	 * 添加接口之前，查询请求记录是否存在，存在后不执行操作
	 * @return
	 */
	public boolean selectBaseRequestKeyRecord(String keystring)throws BaseException;

}
