package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialDeliveryrecordModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialDeliveryrecordDao {

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
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public int addMaterialDeliveryrecord(MaterialDeliveryrecordModel o)throws BaseException;
}
