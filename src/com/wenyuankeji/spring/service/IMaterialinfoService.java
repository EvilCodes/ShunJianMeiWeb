package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialinfoService {

	public List<MaterialinfoModel> searchMaterialinfo(String materialid)throws BaseException;
	
	/**
	 * 根据物料id查询物料名字
	 * @param materialid
	 * @return
	 * @throws BaseException
	 */
	public MaterialinfoModel searchMaterialNameById(String materialid)throws BaseException;
	
	/**
	 * 查询物料信息表
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialinfoModel> searchMaterialinfo()throws BaseException;
	
	/**
	 * 根据categoryid查询物料信息
	 * @return
	 * @throws BaseException
	 */
	public List<MaterialinfoModel> searchMaterialinfoById(int categoryid, int brandid)throws BaseException;
}
