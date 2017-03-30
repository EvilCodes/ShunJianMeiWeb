package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IMaterialBrandDao;
import com.wenyuankeji.spring.model.MaterialBrandModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class MaterialBrandDaoImpl implements IMaterialBrandDao {


	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	@Override
	public List<MaterialBrandModel> searchMaterialBrand() throws BaseException {
		try{
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialBrandModel";
			Query query = session.createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<MaterialBrandModel> materialBrandModel = (List<MaterialBrandModel>)query.list();
	
			return materialBrandModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
}
