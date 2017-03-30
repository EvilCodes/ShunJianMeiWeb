package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserHairpackedModel;
import com.wenyuankeji.spring.util.BaseException;

/**
 * 发型套餐数据操作层
 * 
 * @author lnn
 */
public interface IUserHairpackedDao {

	/**
	 * 查询套餐列表（只查询可用的）
	 * @param userHairpacked
	 * @return
	 */
	public List<UserHairpackedModel> searchUserHairpacked(UserHairpackedModel userHairpacked, int page, int rows) throws BaseException ;
	/**
	 * 查询套餐列表（可用的不可用的都查询）
	 * @param userHairpacked
	 * @return
	 */
	public List<UserHairpackedModel> searchUserHairpackedAll(UserHairpackedModel userHairpacked, int page, int rows) throws BaseException ;
	/**
	 * 根据id查询套餐 
	 * @param userHairpacked
	 * @return
	 */
	public UserHairpackedModel searchUserHairpackedById(Integer id) throws BaseException ;
	/**
	 * 添加套餐 
	 * @param userHairpacked
	 * @return
	 */
	public boolean addUserHairpacked(UserHairpackedModel userHairpacked) throws BaseException ;
	/**
	 * 修改套餐信息
	 * @param userHairpacked
	 * @return
	 */
	public boolean updateUserHairpacked(UserHairpackedModel userHairpacked) throws BaseException ;
	/**
	 * 根据美套餐id查询
	 * @param id
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月14日
	 */
	public UserHairpackedModel searchUserHairpackedByUserHairpacedId(Integer id)
			throws BaseException;
	
	
	
	public List<UserHairpackedModel> searchUserHairpackedLength(UserHairpackedModel userHairpacked);
	UserHairpackedModel searchUserHairpackedByIdnoState(Integer id)
			throws BaseException;
}
