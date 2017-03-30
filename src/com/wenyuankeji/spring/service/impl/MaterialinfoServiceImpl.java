package com.wenyuankeji.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IMaterialinfoDao;
import com.wenyuankeji.spring.model.MaterialinfoModel;
import com.wenyuankeji.spring.service.IMaterialinfoService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class MaterialinfoServiceImpl implements IMaterialinfoService {

	@Autowired
	private IMaterialinfoDao materialinfoDao;

	@Override
	public List<MaterialinfoModel> searchMaterialinfo(String materialid)
			throws BaseException {

		return materialinfoDao.searchMaterialinfo(materialid.substring(0,
				materialid.length() - 1));
	}

	@Override
	public MaterialinfoModel searchMaterialNameById(String materialid)
			throws BaseException {

		return materialinfoDao.searchMaterialById(materialid);
	}

	@Override
	public List<MaterialinfoModel> searchMaterialinfo() throws BaseException {

		return materialinfoDao.searchMaterialinfo();
	}

	@Override
	public List<MaterialinfoModel> searchMaterialinfoById(int categoryid, int brandid)
			throws BaseException {

		return materialinfoDao.searchMaterialinfoById(categoryid, brandid);
	}

}
