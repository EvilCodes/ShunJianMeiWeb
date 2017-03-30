package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.OrderdetailModel;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserPhotoMappingModel;
import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.model.UserWorkdateModel;
import com.wenyuankeji.spring.model.UserWorkhourModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IReservationService {
	/**
	 * 根据美发师id查询美发师信息
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public UsersubinfoModel searchUsersubinfo(int userid)throws BaseException;
	
	/**
	 * 更具用户id和图片类型查询图片信息
	 * @param userid用户id
	 * @param type图片类型
	 * @return
	 */
	public UserPhotoMappingModel searchUserPhoto(int userid,int type)throws BaseException;
	
	/**
	 * 查询美发师工作日
	 * @param userid 用户ID
	 * @return
	 */
	public UserWorkdateModel searchUserWorkdate(int userid)throws BaseException;
	
	/**
	 * 查询美发师工时的栏的清单
	 * @param wdid 工作日ID
	 * @return
	 */
	public List<UserWorkhourModel> searchUserWorkhour(int wdid)throws BaseException;
	
	/**
	 * 查询发型项目的列表
	 * @return
	 * @throws BaseException
	 */
	public List<UserHairstyleModel> searchUserHairstyle()throws BaseException;
	
	/**
	 * 查询美发师职称等级服务价格
	 * @param levelid 等级ID
	 * @return
	 */
	public List<UserProfessionLevelServiceModel> searchUserProfessionLevelService(int levelid)throws BaseException;

	/**
	 * 查询服务编码的默认时间
	 * @param configcode
	 * @return
	 */
	public List<BaseConfigModel> searchBaseConfig(String configcode)throws BaseException;
	
	/**
	 * 查询我的发型
	 * @param mystyleid发型ID
	 * @return
	 */
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid)throws BaseException;
	
	/**
	 * 根据订单ID查询订单信息
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public OrderinfoModel searchOrderinfo(int orderid) throws BaseException;
	/**
	 * 根据订单ID查询订单详细
	 * @param orderid 订单ID
	 * @return
	 * @throws BaseException
	 */
	public List<OrderdetailModel> searchOrderdetail(int orderid) throws BaseException;
}
