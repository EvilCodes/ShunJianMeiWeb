package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserProfessionLevelService {
	/**
	 * 根据职称等级和服务编码查询发型师职称等级服务价格
	 * @param levelid 职称等级ID
	 * @return
	 */
	public UserProfessionLevelModel searchUserProfessionLevel(int levelid)throws BaseException;
}
