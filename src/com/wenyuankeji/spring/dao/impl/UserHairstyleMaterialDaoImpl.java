package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserHairstyleMaterialDao;
import com.wenyuankeji.spring.model.UserHairstyleMaterialModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserHairstyleMaterialDaoImpl implements IUserHairstyleMaterialDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<UserHairstyleMaterialModel> searchUserHairstyleMaterial(
			int styleid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserHairstyleMaterialModel WHERE styleid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, styleid);

			@SuppressWarnings("unchecked")
			List<UserHairstyleMaterialModel> UserHairstyleModels = (List<UserHairstyleMaterialModel>) query
					.list();
			return UserHairstyleModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UserHairstyleMaterialModel> searchUserHairstyleMaterial(
			int styleid, String servicecode) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserHairstyleMaterialModel WHERE styleid=? AND servicecode=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, styleid);
			query.setString(1, servicecode);

			@SuppressWarnings("unchecked")
			List<UserHairstyleMaterialModel> UserHairstyleModels = (List<UserHairstyleMaterialModel>) query
					.list();
			return UserHairstyleModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public UserHairstyleMaterialModel searchUserHairstyleMaterial(int styleid,
			String servicecode, int materialid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserHairstyleMaterialModel WHERE styleid=? AND servicecode=? AND materialid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, styleid);
			query.setString(1, servicecode);
			query.setInteger(2, materialid);

			UserHairstyleMaterialModel userHairstyleMaterialModel = null;
			if (null != query.list() && query.list().size() > 0) {
				userHairstyleMaterialModel = (UserHairstyleMaterialModel) query.list().get(0);
			}

			return userHairstyleMaterialModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
