package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserHairstyleDao;
import com.wenyuankeji.spring.model.UserHairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserHairstyleDaoImpl implements IUserHairstyleDao{

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public List<UserHairstyleModel> searchUserHairstyle() throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserHairstyleModel order by displayorder";
			Query query = session.createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<UserHairstyleModel> UserHairstyleModels = (List<UserHairstyleModel>)query.list();
			return UserHairstyleModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public List<UserHairstyleModel> searchUserHairstyle(String styleids) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserHairstyleModel where styleid in ("+styleids+") ";
			Query query = session.createQuery(hql);
			
			//query.setString(0, styleids);
			
			@SuppressWarnings("unchecked")
			List<UserHairstyleModel> UserHairstyleModels = (List<UserHairstyleModel>)query.list();
			return UserHairstyleModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public List<UserMyhairstyleModel> searchUserMyhairstyleByUserID(String userID) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstyleModel where userid = ?";
			Query query = session.createQuery(hql);
			
			query.setString(0, userID);
			
			@SuppressWarnings("unchecked")
			List<UserMyhairstyleModel> userMyhairstyles = (List<UserMyhairstyleModel>)query.list();
			return userMyhairstyles;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	

	@Override
	public UserHairstyleModel searchUserHairstyleById(int id)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserHairstyleModel WHERE styleid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, id);

			UserHairstyleModel userHairstyle = null;
			if (null != query.list() && query.list().size() > 0) {
				userHairstyle = (UserHairstyleModel) query.list().get(0);
			}

			return userHairstyle;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateUsednum(int mystyleid) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			String sql = "update user_myhairstyle set usednum = usednum + 1 where mystyleid = '"+mystyleid+"'";
			
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
