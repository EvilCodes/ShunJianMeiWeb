package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseVerificationcodeDao;
import com.wenyuankeji.spring.model.BaseVerificationcodeModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseVerificationcodeDaoImpl implements IBaseVerificationcodeDao {

	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public boolean addBaseVerificationcode(BaseVerificationcodeModel o) throws BaseException{
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.save(o);
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
		return true;
	}

	@Override
	public BaseVerificationcodeModel searchBaseVerificationcode(String mobile) throws BaseException{
		
		BaseVerificationcodeModel baseVerificationcodeModel = null;
		
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "FROM BaseVerificationcodeModel WHERE mobile=? and expirationtime >= SYSDATE()  ORDER BY createtime DESC";
			Query query = session.createQuery(hql);;
			
			query.setString(0, mobile);

			@SuppressWarnings("unchecked")
			List<Object> objList = query.list();
			
			if (null != objList && objList.size() > 0) {
				baseVerificationcodeModel = (BaseVerificationcodeModel) objList
						.get(0);
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		return baseVerificationcodeModel;
	}

}
