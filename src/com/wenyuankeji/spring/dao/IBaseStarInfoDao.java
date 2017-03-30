package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.BaseStarinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseStarInfoDao {
	
	/**
	 * 获取所有星座信息
	 * @return
	 * @throws BaseException
	 */
	public List<BaseStarinfoModel> searchBaseStarInfo()throws BaseException;
}
