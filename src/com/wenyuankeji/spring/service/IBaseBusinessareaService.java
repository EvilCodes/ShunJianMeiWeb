package com.wenyuankeji.spring.service;

import java.util.List;

import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseBusinessareaService {

	public List<BaseBusinessareaModel> searchBaseBusinessarea(int cityid) throws BaseException;
}
