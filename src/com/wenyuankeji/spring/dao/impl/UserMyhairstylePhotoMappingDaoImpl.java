package com.wenyuankeji.spring.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserMyhairstylePhotoMappingDao;
import com.wenyuankeji.spring.model.UserMyhairstylePhotoMappingModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserMyhairstylePhotoMappingDaoImpl implements
		IUserMyhairstylePhotoMappingDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserMyhairstylePhotoMappingModel searchUserMyhairstylePhotoMapping(
			int mystyleid,int type) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserMyhairstylePhotoMappingModel WHERE mystyleid=? and type=? order by createtime asc";
			Query query = session.createQuery(hql);

			query.setInteger(0, mystyleid);
			query.setInteger(1, type);
			
			UserMyhairstylePhotoMappingModel userMyhairstylePhotoMapping = null;
			if (null != query.list() && query.list().size() > 0) {
				userMyhairstylePhotoMapping = (UserMyhairstylePhotoMappingModel) query
						.list().get(0);
			}

			return userMyhairstylePhotoMapping;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public boolean addUserMyhairstylePhotoMapping(
			UserMyhairstylePhotoMappingModel o) throws BaseException {
		try {
			
			Session session = sessionFactory.getCurrentSession();
			session.save(o);
			return true;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
	@Override
	public boolean deleteUserMyhairstylePhotoMapping(int mystyleid) throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "delete from user_myhairstyle_photo_mapping where mystyleid = '"+mystyleid+"' ";
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
