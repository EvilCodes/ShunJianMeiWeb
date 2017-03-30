package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialDeliveryrecordDetailModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialDeliveryrecordDetailDao {

	/**
	 * 根据记录id查询信息
	 * @param recordid
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialDeliveryrecordDetailModel> searchmaterialDeliveryrecordDetail(int recordid)throws BaseException;

	/**
	 * 添加提取记录明细
	 * @param o
	 * @return
	 * @throws BaseException
	 */
	public int addMaterialDeliveryrecordDetail(MaterialDeliveryrecordDetailModel o)throws BaseException;
}
