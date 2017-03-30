package com.wenyuankeji.spring.service;

import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.util.BaseException;

public interface IBaseSuggestionService {

	/**
	 * 添加意见建议
	 * @param baseSuggestion意见建议对象
	 * @return 执行结果
	 */
	public boolean addBaseSuggestion(BaseSuggestionModel baseSuggestion)throws BaseException;

}
