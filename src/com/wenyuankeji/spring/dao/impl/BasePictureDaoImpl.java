package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBasePictureDao;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BasePictureDaoImpl implements IBasePictureDao{

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public BasePictureModel searchBasePicture(int picid)throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BasePictureModel WHERE picid=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, picid);
			
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
	public boolean addBasePicture(BasePictureModel basePictureModel) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			Serializable isok = session.save(basePictureModel);
			int result = Integer.parseInt(isok.toString());
			return result != 0;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
	@Override
	public boolean updateBasePicture(BasePictureModel basePictureModel) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			session.update(basePictureModel);
			
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public Integer addBasePictureReturnID(BasePictureModel basePictureModel) throws BaseException{
		
		Session session = sessionFactory.getCurrentSession();
		try {
			Integer id = (Integer) session.save(basePictureModel);
			return id;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public BasePictureModel searchBasePicture(String picturepath) throws BaseException{
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BasePictureModel WHERE picturepath=?";
			Query query = session.createQuery(hql);
			query.setString(0, picturepath);
			
			BasePictureModel basePictureModel = null;
			if (null != query.list() && query.list().size() > 0) {
				basePictureModel = (BasePictureModel)query.list().get(0);
			}
			return basePictureModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
}
