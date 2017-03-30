package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.UserHairpackedItemModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IUserHairpackedItemDao {
	/**
	 * 添加item项
	 * @param hairpackedItemModels 
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月12日
	 */
	public boolean addHairpackedItem(UserHairpackedItemModel hairpackedItemModels) throws BaseException;
	/**
	 * 批量添加item
	 * @param hairpackedItemModels
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月12日
	 */
	public boolean addHairpackedItemBatch(List<UserHairpackedItemModel> hairpackedItemModels) throws BaseException;
	/**
	 * 批量修改套餐项
	 * @param hairpackedItemModels
	 * @return
	 * @throws BaseException   
	 * @throws
	 * @author lnn
	 * @date 2016年1月12日
	 */
	public boolean updateHairpackedItemBatch(List<UserHairpackedItemModel> hairpackedItemModels) throws BaseException;
	/**
	 * 根据套餐id 获取套餐子项
	 * @param userHairpacked
	 * @return
	 */
	public List<UserHairpackedItemModel> searchUserHairpackedItem(Integer hairpackedid) throws BaseException ;



	/**
	 * 根据套餐id获取套餐项列表
	 * @param id
	 * @return
	 * @throws BaseException
	 */
	public List<UserHairpackedItemModel> getHairpackedItemByHairId(Integer id) throws BaseException;
}
