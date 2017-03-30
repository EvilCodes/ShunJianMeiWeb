package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseBusinessareaDao;
import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseBusinessareaDaoImpl implements IBaseBusinessareaDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public List<BaseBusinessareaModel> searchBaseBusinessarea(int cityid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM BaseBusinessareaModel WHERE cityid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, cityid);
			@SuppressWarnings("unchecked")
			List<BaseBusinessareaModel> baseBusinessareaModel = (List<BaseBusinessareaModel>)query.list();
			return baseBusinessareaModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

}
