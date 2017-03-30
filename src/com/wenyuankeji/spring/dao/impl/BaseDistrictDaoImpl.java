package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseDistrictDao;
import com.wenyuankeji.spring.model.BaseDistrictModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseDistrictDaoImpl implements IBaseDistrictDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<BaseDistrictModel> searchBaseDistrictByCityId(int cityId)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM BaseDistrictModel WHERE cityid=? ORDER BY districtid";
			Query query = session.createQuery(hql);
			query.setInteger(0, cityId);

			@SuppressWarnings("unchecked")
			List<BaseDistrictModel> baseDistrictList = (List<BaseDistrictModel>) query
					.list();
			return baseDistrictList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

}
