package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.StoreWorkhourModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IStoreWorkHourService {


	public List<StoreWorkhourModel> searchStoreHourInfo(int wdId,int hour) throws BaseException ;
	
	public boolean updateStoreWorkhour(int orderid,int wdid,int hour,int state) throws BaseException;
	
}
