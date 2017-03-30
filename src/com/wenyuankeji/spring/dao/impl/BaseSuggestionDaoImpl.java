package com.wenyuankeji.spring.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseSuggestionDao;
import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseSuggestionDaoImpl implements IBaseSuggestionDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public boolean addBaseSuggestion(BaseSuggestionModel baseSuggestion) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(baseSuggestion);		//查询添加的数据
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
