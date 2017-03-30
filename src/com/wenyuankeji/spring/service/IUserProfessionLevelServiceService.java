package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserProfessionLevelServiceService {
	/**
	 * 查询美发师职称等级服务价格
	 * @return
	 */
	public List<UserProfessionLevelServiceModel> searchUserProfessionLevelService(int levelid)throws BaseException;
	
	/**
	 * 根据职称等级和服务编码查询发型师职称等级服务价格
	 * @param levelid 职称等级ID
	 * @param servicecode 服务属性编码
	 * @return
	 */
	public UserProfessionLevelServiceModel searchUserProfessionLevelService(int levelid,String servicecode)throws BaseException;
}
