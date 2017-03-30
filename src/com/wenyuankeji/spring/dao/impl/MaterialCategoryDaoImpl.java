package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IMaterialCategoryDao;
import com.wenyuankeji.spring.model.MaterialCategoryModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class MaterialCategoryDaoImpl implements IMaterialCategoryDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<MaterialCategoryModel> searchMaterialCategory()
			throws BaseException {
		
		try{
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM MaterialCategoryModel";
			Query query = session.createQuery(hql);
	
			@SuppressWarnings("unchecked")
			List<MaterialCategoryModel> materialCategory = (List<MaterialCategoryModel>)query.list();
	
			return materialCategory;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
