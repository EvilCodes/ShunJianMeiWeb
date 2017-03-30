package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserMyhairstyleTimesDao;
import com.wenyuankeji.spring.model.UserMyhairstyleTimesModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserMyhairstyleTimesDaoImpl implements IUserMyhairstyleTimesDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserMyhairstyleTimesModel searchUserMyhairstyleTimes(int mystyleID, String servicecode) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstyleTimesModel WHERE servicecode=? and mystyleid=? ";
			Query query = session.createQuery(hql);

			query.setString(0, servicecode);
			query.setInteger(1, mystyleID);

			UserMyhairstyleTimesModel userMyhairstyleTimes = null;
			if (null != query.list() && query.list().size() > 0) {
				userMyhairstyleTimes = (UserMyhairstyleTimesModel) query.list().get(0);
			}

			return userMyhairstyleTimes;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public List<UserMyhairstyleTimesModel> searchUserMyhairstyleTimes() throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstyleTimesModel ";
			Query query = session.createQuery(hql);

			@SuppressWarnings("unchecked")
			List<UserMyhairstyleTimesModel> userMyhairstyleTimesModel = (List<UserMyhairstyleTimesModel>) query
					.list();
			return userMyhairstyleTimesModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public List<UserMyhairstyleTimesModel> searchUserMyhairstyleTimesByMystyleid(int mystyleid) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstyleTimesModel where mystyleid = ?";
			Query query = session.createQuery(hql);

			query.setInteger(0, mystyleid);
			
			
			@SuppressWarnings("unchecked")
			List<UserMyhairstyleTimesModel> userMyhairstyleTimesModel = (List<UserMyhairstyleTimesModel>) query
					.list();
			return userMyhairstyleTimesModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public boolean addUserMyhairstyleTimes(UserMyhairstyleTimesModel o)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(o);
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean deleteUserMyhairstyleTimes(int mystyleid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "delete from user_myhairstyle_times where mystyleid = '"+mystyleid+"' ";
			Query query = session.createSQLQuery(sql);
			if (query.executeUpdate() > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
