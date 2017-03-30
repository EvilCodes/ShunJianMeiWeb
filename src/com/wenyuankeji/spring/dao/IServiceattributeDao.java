package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.ServiceattributeModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IServiceattributeDao {
	//查询服务属性
	public ServiceattributeModel searchServiceattribute(String servicecode)throws BaseException;
	
	//查询所有服务属性
	public List<ServiceattributeModel> searchServiceattribute()throws BaseException;
}
