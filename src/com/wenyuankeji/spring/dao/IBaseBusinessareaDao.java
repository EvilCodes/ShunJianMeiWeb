package com.wenyuankeji.spring.dao;

import java.util.List;

import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseBusinessareaDao {

	public List<BaseBusinessareaModel> searchBaseBusinessarea(int cityid) throws BaseException;
}
