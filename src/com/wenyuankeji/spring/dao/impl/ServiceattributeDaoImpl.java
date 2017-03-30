package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IServiceattributeDao;
import com.wenyuankeji.spring.model.ServiceattributeModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class ServiceattributeDaoImpl implements IServiceattributeDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ServiceattributeModel searchServiceattribute(String servicecode) throws BaseException{

		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM ServiceattributeModel WHERE servicecode=? ";
			Query query = session.createQuery(hql);

			query.setString(0, servicecode);

			ServiceattributeModel serviceattribute = null;
			
			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				serviceattribute = (ServiceattributeModel) objList.get(0);
			}

			return serviceattribute;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public List<ServiceattributeModel> searchServiceattribute() throws BaseException{

		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM ServiceattributeModel order by servicecode";
			Query query = session.createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<ServiceattributeModel> serviceattributeModel = (List<ServiceattributeModel>) query.list();
			return serviceattributeModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

}
