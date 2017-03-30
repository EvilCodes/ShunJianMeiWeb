package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseConfigDaoImpl implements IBaseConfigDao{

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public List<BaseConfigModel> searchBaseConfig() throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseConfigModel WHERE configcode='city' order by sortnum,configid";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<BaseConfigModel> baseConfigModels = (List<BaseConfigModel>)query.list();
			return baseConfigModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
	@Override
	public List<BaseConfigModel> searchBaseConfig(String configcode) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseConfigModel WHERE configcode=? order by sortnum,configid";
			Query query = session.createQuery(hql);

			query.setString(0, configcode);

			@SuppressWarnings("unchecked")
			List<BaseConfigModel> baseConfigList = (List<BaseConfigModel>)query.list();
			return baseConfigList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public BasePictureModel searchBasePicture(String configvalue) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BasePictureModel WHERE picid=?";
			Query query = session.createQuery(hql);

			query.setInteger(0, Integer.parseInt(configvalue));
			BasePictureModel basePictureModel = null;
			if (null != query.list() && query.list().size() > 0) {
				basePictureModel = (BasePictureModel)query.list().get(0);
			}
			return basePictureModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseConfigModel WHERE configcode=? order by sortnum,configid";
			Query query = session.createQuery(hql);

			query.setString(0, configcode);

			@SuppressWarnings("unchecked")
			List<BaseConfigModel> baseConfigList = (List<BaseConfigModel>)query.list();
			
			return baseConfigList.get(0);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public BaseConfigModel searchBaseConfigByCodeAndValue(String configcode,
			String configvalue) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseConfigModel WHERE configcode=? and configvalue=? order by sortnum,configid";
			Query query = session.createQuery(hql);

			query.setString(0, configcode);
			query.setString(1, configvalue);

			@SuppressWarnings("unchecked")
			List<BaseConfigModel> baseConfigList = (List<BaseConfigModel>)query.list();
			
			return baseConfigList.get(0);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
