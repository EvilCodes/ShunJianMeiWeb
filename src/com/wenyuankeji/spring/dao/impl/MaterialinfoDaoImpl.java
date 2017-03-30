package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IMaterialinfoDao;
import com.wenyuankeji.spring.model.MaterialinfoModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class MaterialinfoDaoImpl implements IMaterialinfoDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<MaterialinfoModel> searchMaterialinfo(String materialid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			//String hql = "FROM MaterialinfoModel where materialid=?";
			String hql = "FROM MaterialinfoModel where materialid in (" + materialid + ")";
			Query query = session.createQuery(hql);
			//query.setInteger(0, materialid);
			@SuppressWarnings("unchecked")
			List<MaterialinfoModel> materialinfoModels = (List<MaterialinfoModel>) query.list();
			return materialinfoModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public MaterialinfoModel searchMaterialById(String materialid)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialinfoModel where materialid = ?";
			Query query = session.createQuery(hql);
			query.setString(0, materialid);
			@SuppressWarnings("unchecked")
			List<MaterialinfoModel> materialinfoModels = (List<MaterialinfoModel>) query.list();
			
			if (null != materialinfoModels && materialinfoModels.size() > 0) {
				return materialinfoModels.get(0);
			}
			
			return null;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<MaterialinfoModel> searchMaterialinfo() throws BaseException {
		
		try{
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialinfoModel";
			Query query = session.createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<MaterialinfoModel> materialinfo = (List<MaterialinfoModel>)query.list();
	
			return materialinfo;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<MaterialinfoModel> searchMaterialinfoById(int categoryid, int brandid)
			throws BaseException {
		try{
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialinfoModel WHERE categoryid=? AND brandid=? ";
			Query query = session.createQuery(hql);
			query.setInteger(0, categoryid);
			query.setInteger(1, brandid);
			@SuppressWarnings("unchecked")
			List<MaterialinfoModel> materialinfo = (List<MaterialinfoModel>)query.list();
	
			return materialinfo;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}


}
