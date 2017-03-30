package com.wenyuankeji.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseSuggestionDao;
import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.service.IBaseSuggestionService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class BaseSuggestionServiceImpl implements IBaseSuggestionService {

	@Autowired
	private IBaseSuggestionDao baseSuggestionDao;
	
	@Override
	public boolean addBaseSuggestion(BaseSuggestionModel baseSuggestion) throws BaseException{
		try {
			return baseSuggestionDao.addBaseSuggestion(baseSuggestion);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
}
