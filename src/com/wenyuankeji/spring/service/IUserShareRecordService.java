package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.UserShareRecordModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserShareRecordService {

	/**
	 * 根据评论ID查询 分享图片ID
	 * @param evaid 分享ID
	 * @return
	 * @throws BaseException
	 */
	public UserShareRecordModel searchUserShareRecord(int evaid) throws BaseException;
	
	/**
	 * 添加分享记录
	 * @param userid
	 * @param evaid
	 * @param picID
	 * @return
	 * @throws BaseException
	 */
	public boolean addUserShareRecord(String userid,String evaid,String picID) throws BaseException;
}
