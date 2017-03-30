package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialDeliveryrecordDetailModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialDeliveryrecordDetailService {

	/**
	 * 根据记录id查询信息
	 * @param recordid
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialDeliveryrecordDetailModel> searchmaterialDeliveryrecordDetail(int recordid)throws BaseException;
}
