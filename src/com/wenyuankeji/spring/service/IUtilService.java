package com.wenyuankeji.spring.service;

public interface IUtilService {
	
	/**
	 * 添加接口访问记录
	 * @return
	 */
	public void addBaseRequestlog(String url,String para1,String para2,String result);

	/**
	 * 保存错误日志
	 * @param source
	 * @param paras
	 * @param message
	 * @return
	 */
	public int addBaseException(String source,String paras,String message);
}
