package com.wenyuankeji.spring.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserProfessionLevelDao;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserProfessionLevelDaoImpl implements IUserProfessionLevelDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserProfessionLevelModel searchUserProfessionLevel(int levelid) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserProfessionLevelModel WHERE levelid=? ";
			Query query = session.createQuery(hql);

			query.setInteger(0, levelid);

			UserProfessionLevelModel userProfessionLevel = null;
			if (query.list() != null && query.list().size() > 0) {
				userProfessionLevel = (UserProfessionLevelModel) query.list()
						.get(0);
			}

			return userProfessionLevel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

}
