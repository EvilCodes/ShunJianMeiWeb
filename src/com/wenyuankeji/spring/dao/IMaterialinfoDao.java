package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.MaterialinfoModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IMaterialinfoDao {

	public List<MaterialinfoModel> searchMaterialinfo(String materialid)throws BaseException;
	/**
	 * 根据物料id查询物料信息
	 * @param materialid
	 * @return
	 * @throws BaseException
	 */
	public MaterialinfoModel searchMaterialById(String materialid)throws BaseException;
	
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
