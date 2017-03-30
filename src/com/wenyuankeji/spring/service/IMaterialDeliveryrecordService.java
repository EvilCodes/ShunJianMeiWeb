package com.wenyuankeji.spring.service;

import java.util.List;
import java.util.Map;

import com.wenyuankeji.spring.model.MaterialDeliveryrecordModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialDeliveryrecordService {

	/**
	 * 根据记录id查找
	 * @param materialid
	 * @return
	 * @throws BaseException
	 */
	public MaterialDeliveryrecordModel searchMaterialDeliveryrecordById(int recordid)throws BaseException;
	
	
	/**
	 * 根据用户id查找
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(int userid)throws BaseException;
	/**
	 * 根据用户id以及状态查找
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(
			int userid,int state) throws BaseException;
	
	/**
	 * 根据记录id用户id以及状态查找
	 * @param userid
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialDeliveryrecordModel> searchmaterialDeliveryrecord(
			int recordid, int userid,int state) throws BaseException;
	
	/**
	 * 根据记录id修改提货记录
	 * @param recordid
	 * @return
	 * @throws BaseException
	 */
	public boolean updateMaterialDeliveryrecordModel(int recordid)throws BaseException;
	
	/**
	 * 保存提取信息
	 * @param userID 用户ID
	 * @param deliverytime 提取日期
	 * @param addressID 提取地点ID
	 * @param itemList MaterialID物料IDNum数量
	 * @return
	 * @throws BaseException
	 */
	public boolean addMaterialDeliveryrecord(String userID,String deliverytime,String addressID,List<Map<String, Object>> itemList)throws BaseException;
}

