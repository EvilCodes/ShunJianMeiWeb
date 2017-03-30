package com.wenyuankeji.spring.dao;


import com.wenyuankeji.spring.model.UserShareRecordModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserShareRecordDao {
	
	/**
	 * 根据评论ID查询 分享图片ID
	 * @param evaid 分享ID
	 * @return
	 * @throws BaseException
	 */
	public UserShareRecordModel searchUserShareRecord(int evaid) throws BaseException;
	
	
	/**
	 * 添加分享记录
	 * @return
	 */
	public int addUserShareRecord(UserShareRecordModel o) throws BaseException;
}
