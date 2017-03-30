package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseStarInfoDao;
import com.wenyuankeji.spring.model.BaseStarinfoModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseStarInfoDaoImpl implements IBaseStarInfoDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<BaseStarinfoModel> searchBaseStarInfo() throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM BaseStarinfoModel ORDER BY starid ASC";
			Query query = session.createQuery(hql);

			@SuppressWarnings("unchecked")
			List<BaseStarinfoModel> baseStarInfoList = (List<BaseStarinfoModel>) query.list();
			return baseStarInfoList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
