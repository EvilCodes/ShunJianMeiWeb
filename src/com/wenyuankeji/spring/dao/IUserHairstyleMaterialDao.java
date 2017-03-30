package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserHairstyleMaterialModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserHairstyleMaterialDao {

	public List<UserHairstyleMaterialModel> searchUserHairstyleMaterial(int styleid)throws BaseException;
	
	public List<UserHairstyleMaterialModel> searchUserHairstyleMaterial(int styleid, String servicecode)throws BaseException;
	
	public UserHairstyleMaterialModel searchUserHairstyleMaterial(int styleid, String servicecode, int materialid)throws BaseException;
}
