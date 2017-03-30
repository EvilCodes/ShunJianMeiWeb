package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseProvinceDao;
import com.wenyuankeji.spring.model.BaseProvinceModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseProvinceDaoImpl implements IBaseProvinceDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<BaseProvinceModel> searchBaseProvince() throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM BaseProvinceModel ORDER BY provinceid ASC";
			Query query = session.createQuery(hql);

			@SuppressWarnings("unchecked")
			List<BaseProvinceModel> baseProvinceList = (List<BaseProvinceModel>) query.list();
			return baseProvinceList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
