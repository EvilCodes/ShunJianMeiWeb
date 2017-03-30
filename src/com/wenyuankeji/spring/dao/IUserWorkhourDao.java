package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserWorkhourDao {
	
	public boolean updateUserWorkhour(int orderid,int wdid,int hour,int state) throws BaseException;
	
	public boolean updateUserWorkhour(int orderid,int state) throws BaseException;
	
	public UserWorkhourModel searchUserWorkhour(int orderid,int wdid,int hour) throws BaseException;
	
	// add by jiazhaohui
	/**
	 * 更新预约状态
	 * @param ordertype 是平台预约还是自定义预约
	 * @return
	 * @throws BaseException
	 */
	public boolean updateUserWorkhour(int orderid,int wdid,int hour,int state,
			int ordertype) throws BaseException;
	/**
	 * 根据工时id查询工时信息
	 * @param whid
	 * @return
	 * @throws BaseException
	 */
	public UserWorkhourModel searchUserWorkhour(int whid)throws BaseException;
	
	/**
	 * 根据工作日id查询工作时信息
	 * @param wdid
	 * @return
	 * @throws BaseException
	 */
	public List<UserWorkhourModel> searchUserWorkhourByWdid(int wdid) throws BaseException;
	
	/**
	 * 保存工作工时
	 * @param o
	 * @return
	 */
	public boolean addUserWorkhour(UserWorkhourModel o)throws BaseException;
	
	public boolean deleteUserWorkhourByWdid(int wdid)throws BaseException;
	public List<UserWorkhourModel> searchUserWorkhourByWdidAndState(int wdid) throws BaseException;
	public boolean updateUserWorkhourState(UserWorkhourModel o) throws BaseException;
	
	//取消修改状态
	public boolean updateUserWorkhour(int orderid) throws BaseException;
	//支付完成 修改状态
	public boolean updateUserWorkhour2(int orderid) throws BaseException;
	public boolean updateStoreWorkhour(int orderid) throws BaseException;
	/**
	 * 查询工作日id下的12个时间点，判断是否全天休息
	 * @param wdid 工作日ID
	 * @return
	 * @throws BaseException
	 */
	public boolean getUserWorkHourState(int wdid) throws BaseException;
	/**
	 * 删除自由编辑的日程订单
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public boolean updateUserEditWorkDelete(int orderid) throws BaseException;
}
