package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IAppversionDao;
import com.wenyuankeji.spring.model.AppversionModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class AppversionDaoImpl implements IAppversionDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public AppversionModel searchAppversion(String vcode, int type)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM AppversionModel WHERE vcode > ? and type = ? ORDER BY vcode DESC";
			Query query = session.createQuery(hql);
			query.setString(0, vcode);
			query.setInteger(1, type);
			//返回最新1条
			query.setMaxResults(1);
			AppversionModel appversion = null;
			
			@SuppressWarnings("unchecked")
			List<Object> objects = query.list();
			
			if (null != objects && objects.size() > 0) {
				appversion = (AppversionModel) objects.get(0);
			}
			return appversion;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

}
